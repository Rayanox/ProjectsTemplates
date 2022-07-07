package com.karavel.batch.seo.linking.core.steps.fetching;

import com.karavel.batch.seo.linking.common.GenerationType;
import com.karavel.batch.seo.linking.common.beans.SitesEnum;
import com.karavel.batch.seo.linking.common.beans.Theme;
import com.karavel.batch.seo.linking.common.beans.ZoneTouristiqueListes;
import com.karavel.batch.seo.linking.common.beans.ZoneTouristiqueNode;
import com.karavel.batch.seo.linking.configuration.startup.BatchInputs;
import com.karavel.batch.seo.linking.configuration.startup.GlobalConfiguration;
import com.karavel.batch.seo.linking.core.JobData;
import com.karavel.batch.seo.linking.core.common.ZoneNode;
import com.karavel.batch.seo.linking.dto.BatchOuts;
import com.karavel.batch.seo.linking.utils.ConfigurationUtils;
import com.karavel.batch.seo.linking.utils.ZoneTouristiqueTreeUtils;
import com.karavel.catalogue.sejour.IReferentielGeographiqueService;
import com.karavel.catalogue.sejour.in.ZonesTouristiquesIn;
import com.karavel.catalogue.sejour.out.referentielgeographique.ZoneTouristiqueVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

import static org.apache.commons.lang.StringUtils.isNotBlank;

@Component("ZonesTouristiquesFetchingTasklet")
public class ZonesTouristiquesFetchingTasklet implements Tasklet {

    private final static Logger LOGGER = LoggerFactory.getLogger(ZonesTouristiquesFetchingTasklet.class);

    private static final int MAX_ZT1_NB = 20;
    private static final Integer RACINE = 0;

    private IReferentielGeographiqueService referentielGeographiqueService;
    private ConfigurationUtils configurationUtils;
    private ZoneTouristiqueTreeUtils zoneTouristiqueTreeUtils;
    private GlobalConfiguration globalConfiguration;
    private BatchInputs batchInputs;
    private JobData jobData;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        FetchingWrapper fetchingWrapper = fetchDataFromWs();
        processZoneNodesBuilding(fetchingWrapper);
        writeToMemory(fetchingWrapper);

        return RepeatStatus.FINISHED;
    }

    private FetchingWrapper fetchDataFromWs() {
        GenerationType generationType = batchInputs.getGenerationType();

        FetchingWrapper fetchingWrapper = new FetchingWrapper();
        Long lvSite = globalConfiguration.getLvSite();

        ZoneTouristiqueVO[] allZoneTouristiques = referentielGeographiqueService.getZonesTouristiques(computeAllZonesTouristiquesIn());
        LOGGER.info("{} zones touristiques récupérées", allZoneTouristiques != null ? allZoneTouristiques.length : 0);

        fetchingWrapper.setZonesTouristiquesTab(allZoneTouristiques);
        fetchingWrapper.setZoneTouristiqueListes(zoneTouristiqueTreeUtils.createZoneTouristiqueListes(lvSite, allZoneTouristiques));
        fetchingWrapper.setRootNode(zoneTouristiqueTreeUtils.createRootNode());
        fetchingWrapper.setGenerationType(generationType);
        return fetchingWrapper;
    }

    private FetchingWrapper processZoneNodesBuilding(FetchingWrapper fetchingWrapper) {
        LOGGER.info("Calcul et génération de l'arbre...");

        ZoneTouristiqueNode rootNode = fetchingWrapper.getRootNode();
        ZoneTouristiqueListes zoneTouristiqueListes = fetchingWrapper.getZoneTouristiqueListes();


        //alimenter le niveau 1 de l'arbre
        for (ZoneTouristiqueNode node : zoneTouristiqueListes.getNodes(1)) {
            rootNode.getChildren().add(node);
            node.setParent(rootNode);
        }
        //alimenter récursivement le niveau 2, 3, 4, 5, 6 et 7 de l'arbre
        Map<Long, ZoneTouristiqueNode> pointerAlreadyAddedNodeInTree = zoneTouristiqueTreeUtils.initTreePointerMapRecursive(rootNode, null);
        zoneTouristiqueTreeUtils.addTreeElementRecursive(zoneTouristiqueListes, pointerAlreadyAddedNodeInTree, new HashMap<>(), ZoneTouristiqueTreeUtils.COUNTRY_LEVEL);

        //supprimer les noeuds générés inutiles
        zoneTouristiqueTreeUtils.deleteUnusedInactifsNodes(rootNode);
        limitRootChildren(MAX_ZT1_NB, rootNode);

        zoneTouristiqueTreeUtils.flagDestinationWithoutOffer(rootNode);

        //supprimer les noeuds specifie dans la config (propriete "liste.noeuds.a.supprimer")
        deleteNodesToDelete(rootNode);

        adaptFranceTree(rootNode);

        LOGGER.info("Fin de génération de l'arbre");

        List<ZoneNode> allNodeList = getAllNodesListFromTree(rootNode, null);
        fetchingWrapper.setFinalListOfAllRemainingNodes(allNodeList);

        return fetchingWrapper;
    }

    private void writeToMemory(FetchingWrapper fetchingWrapper) {
        BatchOuts stepOut = new BatchOuts();
        stepOut.setRootNode(fetchingWrapper.getRootNode());
        stepOut.setZoneNodes(fetchingWrapper.getFinalListOfAllRemainingNodes());

        jobData.setBatchOut(stepOut);
        jobData.setGenerationType(fetchingWrapper.getGenerationType());
    }

    private List<ZoneNode> getAllNodesListFromTree(ZoneTouristiqueNode currentNode, List<ZoneNode> allNodesCumulated) {
        if(Objects.isNull(allNodesCumulated))
            allNodesCumulated = new ArrayList<>();

        if(isNotRootNode(currentNode)) {
            for (Theme theme : currentNode.getLinkingSLList().getAllThemesOfLinking()) {
                allNodesCumulated.add(ZoneNode.buildFrom(currentNode, theme));
            }
        }

        for (ZoneTouristiqueNode childNode : currentNode.getChildren()) {
            getAllNodesListFromTree(childNode, allNodesCumulated);
        }

        return allNodesCumulated;
    }

    private boolean isNotRootNode(ZoneTouristiqueNode currentNode) {
        return !RACINE.equals(currentNode.getNiveau());
    }

    private void deleteNodesToDelete(ZoneTouristiqueNode rootNode) {
        List<String> nodesToDelete = configurationUtils.getNodesToDelete();
        if (! CollectionUtils.isEmpty(nodesToDelete)) {
            for (String nodeToDelete : nodesToDelete) {
                if (isNotBlank(nodeToDelete) && nodeToDelete.contains("-")) {
                    String id = nodeToDelete.split("-")[0];
                    String niveau = nodeToDelete.split("-")[1];
                    LOGGER.info("Suppression du noeud: [id:{}, niveau:{}]", new Object[] {id, niveau});
                    deleteNode(rootNode, Long.valueOf(id), Integer.valueOf(niveau));
                }
            }
        }
    }

    private void deleteNode(ZoneTouristiqueNode node, Long id, Integer niveau) {
        ZoneTouristiqueNode ztToDelete = null;
        for (ZoneTouristiqueNode child : node.getChildren()) {
            if (id.equals(child.getId()) && niveau.equals(child.getNiveau())) {
                ztToDelete = child;
            } else {
                deleteNode(child, id, niveau);
            }
        }
        if (ztToDelete != null) {
            node.getChildren().remove(ztToDelete);
        }
    }

    private void adaptFranceTree(ZoneTouristiqueNode rootNode) {
        Optional<ZoneTouristiqueNode> franceNode = Optional.ofNullable(getFranceNode(rootNode));

        if(franceNode.isPresent()) {
            addLinkingThemeRecurse(franceNode.get(), Theme.CAMPING, ZoneTouristiqueTreeUtils.LEVEL_ACCOMODATION);
            addLinkingThemeRecurse(franceNode.get(), Theme.LOCATION, ZoneTouristiqueTreeUtils.LEVEL_ACCOMODATION);
            addLinkingThemeRecurse(franceNode.get(), Theme.LOGEMENT_FRANCE, ZoneTouristiqueTreeUtils.LEVEL_ACCOMODATION);
        }

        removeRedundancyVls(franceNode.get());
    }

    private void removeRedundancyByRule(List<ZoneTouristiqueNode> nodes) {
        ZoneTouristiqueNode bestNode = null;

        for (ZoneTouristiqueNode node : nodes) {
            if(bestNode == null
                    || node.isContainsOffer() && !bestNode.isContainsOffer())  {
                bestNode = node;
                continue;
            }

            if(!node.isContainsOffer() && bestNode.isContainsOffer())
                continue;

            if(node.getChildren().size() > bestNode.getChildren().size()) {
                bestNode = node;
                continue;
            }
        }

        for (ZoneTouristiqueNode node : nodes) {
            if(node.getId().equals(bestNode.getId()))
                continue;

            node.getParent().getChildren().remove(node);
        }
    }

    private void removeRedundancyVls(ZoneTouristiqueNode node) {
        if(node == null || node.getNiveau() >= ZoneTouristiqueTreeUtils.LEVEL_VL)
            return;

        HashMap<String, List<ZoneTouristiqueNode>> duplicatedNodesList = getDuplicatedChildren(node);
        if(duplicatedNodesList != null) {
            duplicatedNodesList.values().stream()
                    .forEach(nodeList -> removeRedundancyByRule(nodeList));
        }

        node.getChildren()
                .stream()
                .filter(child -> child.getNiveau() < ZoneTouristiqueTreeUtils.LEVEL_VL)
                .forEach(child -> removeRedundancyVls(child));
    }

    private HashMap<String, List<ZoneTouristiqueNode>> getDuplicatedChildren(ZoneTouristiqueNode node) {
        HashMap<String, List<ZoneTouristiqueNode>> dupplicatedNodes = null;
        HashMap<String, ZoneTouristiqueNode> nodesRegistryByName = new HashMap<>();

        for (ZoneTouristiqueNode child : node.getChildren()) {
            if(nodesRegistryByName.containsKey(child.getNom())) {
                if(dupplicatedNodes == null)
                    dupplicatedNodes = new HashMap<>();

                List<ZoneTouristiqueNode> dupplicateList = dupplicatedNodes.get(child.getNom());
                if(dupplicateList == null) {
                    dupplicateList = new ArrayList<>();
                    dupplicatedNodes.put(child.getNom(), dupplicateList);
                    dupplicateList.add(nodesRegistryByName.get(child.getNom()));
                }

                dupplicateList.add(child);
            }else {
                nodesRegistryByName.put(child.getNom(), child);
            }
        }
        return dupplicatedNodes;
    }

    private void addLinkingThemeRecurse(ZoneTouristiqueNode node, Theme theme, final int levelLimit) {
        node.getLinkingFPList().addLinkingListForTheme(theme);
        node.getLinkingSLList().addLinkingListForTheme(theme);

        node.getChildren()
                .stream()
                .filter(child -> child.getNiveau() <= levelLimit)
                .forEach((ZoneTouristiqueNode childNode) -> addLinkingThemeRecurse(childNode, theme, levelLimit));
    }

    private ZoneTouristiqueNode getFranceNode(ZoneTouristiqueNode rootNode) {
        return rootNode.getChildren().stream()
                .filter(node -> ZoneTouristiqueTreeUtils.FRANCE_ID.equals(node.getId()))
                .findAny()
                .orElse(null);
    }

    /**
     * Used because of bug datas in the SI for PMVC (returning more than 1000 ZTs as ZTs 1 and 6, directly children of root level, that is impossible. This method fixes this problem
     * @param maxZt1Nb
     */
    private void limitRootChildren(int maxZt1Nb, ZoneTouristiqueNode rootNode) {
        List<ZoneTouristiqueNode> limitedZt1List = rootNode.getChildren()
                .stream()
                .limit(maxZt1Nb)
                .collect(Collectors.toList());

        rootNode.setChildren(limitedZt1List);
    }

    private ZonesTouristiquesIn computeAllZonesTouristiquesIn() {
        Long siteId = globalConfiguration.getLvSite();

        ZonesTouristiquesIn in = new ZonesTouristiquesIn();
        in.setUseVirage(configurationUtils.useVirage(siteId));
        in.setLvSite(siteId);
        in.setOldReferencial(SitesEnum.isPPC(siteId));
        in.setEnableFlexiFram(configurationUtils.isFlexiFram(siteId));
        return in;
    }

    @Autowired
    public void setConfigurationUtils(ConfigurationUtils configurationUtils) {
        this.configurationUtils = configurationUtils;
    }

    @Autowired
    public void setZoneTouristiqueTreeUtils(ZoneTouristiqueTreeUtils zoneTouristiqueTreeUtils) {
        this.zoneTouristiqueTreeUtils = zoneTouristiqueTreeUtils;
    }
    @Autowired
    public void setGlobalConfiguration(GlobalConfiguration globalConfiguration) {
        this.globalConfiguration = globalConfiguration;
    }
    @Autowired
    public void setBatchInputs(BatchInputs batchInputs) {
        this.batchInputs = batchInputs;
    }
    @Autowired
    public void setReferentielGeographiqueService(IReferentielGeographiqueService referentielGeographiqueService) {
        this.referentielGeographiqueService = referentielGeographiqueService;
    }
    @Autowired
    public void setJobData(JobData jobData) {
        this.jobData = jobData;
    }
}
