package com.karavel.batch.seo.linking.core.steps.linking.processor;

import com.karavel.batch.seo.linking.common.beans.LinkingZTConfigurationRule;
import com.karavel.batch.seo.linking.common.beans.Theme;
import com.karavel.batch.seo.linking.common.beans.ZoneTouristiqueNode;
import com.karavel.batch.seo.linking.common.beans.browsing.BrowseInstructionWrapper;
import com.karavel.batch.seo.linking.common.beans.browsing.BrowseInstructionWrapperBuilder;
import com.karavel.batch.seo.linking.common.GenerationType;
import com.karavel.batch.seo.linking.comparators.ZoneTouristiqueNodePoidsComparator;
import com.karavel.batch.seo.linking.configuration.linking.beans.LinkingRules;
import com.karavel.batch.seo.linking.configuration.linking.zones.PrioriteZones;
import com.karavel.batch.seo.linking.core.JobData;
import com.karavel.batch.seo.linking.core.common.ZoneNode;
import com.karavel.batch.seo.linking.configuration.startup.BatchInputs;
import com.karavel.batch.seo.linking.utils.ConfigurationUtils;
import com.karavel.batch.seo.linking.utils.ZoneTouristiqueTreeUtils;
import com.karavel.tools.string.StringUtil;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import java.util.*;

@Component
public class LinkingProcessor implements ItemProcessor<ZoneNode, ZoneNode> {

    private ConfigurationUtils configurationUtils;
    private ZoneTouristiqueTreeUtils zoneTouristiqueTreeUtils;
    private LinkingRulesResolver linkingRulesResolver;
    private JobData jobData;

    private int linkingCount = 0;
    private final GenerationType generationType;

    public LinkingProcessor(BatchInputs batchInputs) {
        this.generationType = batchInputs.getGenerationType();
    }

    @Override
    public ZoneNode process(ZoneNode currentNode) {
        Optional<LinkingRules> rules = this.linkingRulesResolver.getLinkingRulesForNode(currentNode);
        if(rules.isPresent()) {

            genericLinkingZT(currentNode, rules.get().getRules());
            afficherLinkingZt(currentNode);
            updateLinkingCount(currentNode);
        }

        return currentNode;
    }

    private void updateLinkingCount(ZoneNode currentNode) {
        boolean isSlLinkingEmpty = currentNode.getZone().getLinkingSLList().isLinkingListEmptyForTheme(currentNode.getTheme());
        boolean isFpLinkingEmpty = currentNode.getZone().getLinkingFPList().isLinkingListEmptyForTheme(currentNode.getTheme());

        if(!isFpLinkingEmpty || !isSlLinkingEmpty) {
            jobData.getBatchOut().setLinkingCount(++linkingCount);
        }
    }


    /**
     * Affichage des Linkings pour le {@link ZoneTouristiqueNode} donné
     * @param node
     */
    private void afficherLinkingZt(ZoneNode node) {
        List<ZoneTouristiqueNode> touristiqueNodes = zoneTouristiqueTreeUtils.getLinkingList(generationType, node);

        ZoneTouristiqueNode zone = node.getZone();
        Theme theme = node.getTheme();

        if (!CollectionUtils.isEmpty(touristiqueNodes) ){
            System.out.print("["+ touristiqueNodes.size()+" elements liés à ["+ zone.getId() + "]"+ getThemeName(theme) + zone.getNom()+" (niveau :"+zone.getNiveau()+")] : ");
            for (ZoneTouristiqueNode touristiqueNode : touristiqueNodes) {
                System.out.print(touristiqueNode.getNom() +" (niveau : "+touristiqueNode.getNiveau()+"), ");
            }
            System.out.println();
        }
    }

    private String getThemeName(Theme theme) {
        if(theme == null || theme.getDisplayName() == null)
            return StringUtil.EMPTY_STRING;

        return String.format(" %s - ", theme.getDisplayName());
    }

    /**
     * Linking générique des ZT
     * Marche par configuration
     * @param nodeToLink
     * @param linkingZtConfigurations
     */
    private void genericLinkingZT(ZoneNode nodeToLink, List<LinkingZTConfigurationRule> linkingZtConfigurations) {
        if(linkingZtConfigurations != null) {
            for(LinkingZTConfigurationRule config : linkingZtConfigurations) {
                linkZTnForConfiguration(nodeToLink, config);
            }
        }
    }

    /**
     * Fais le linking + linking d'élargissement
     * @param nodeToLink
     * @param configuration
     */
    private void linkZTnForConfiguration(ZoneNode nodeToLink, LinkingZTConfigurationRule configuration) {
        linkZTn(nodeToLink, configuration);
        remote(nodeToLink, configuration);
    }

    private void linkZTn(ZoneNode nodeToLink, LinkingZTConfigurationRule config) {
        List<ZoneTouristiqueNode> nodeCandidateList = new ArrayList<ZoneTouristiqueNode>();

        BrowseInstructionWrapper browsingInstructions = BrowseInstructionWrapperBuilder.initBuilder(zoneTouristiqueTreeUtils)
                .withConfigurationRule(config)
                .forNode(nodeToLink.getZone())
                .build();

        browsingInstructions.getNodesToBrowseChildren().stream()
                .filter(child -> doNotSkipNode(child, browsingInstructions))
                .forEach(nodeToBrowseChildren -> {
                    browseZoneChildsToGetCandidates(browsingInstructions, nodeToLink, nodeToBrowseChildren, config, nodeCandidateList);
                });
        beginLinkCandidatesNodes(nodeToLink, getLinkingList(nodeToLink), nodeCandidateList, config);
    }

    private void browseZoneChildsToGetCandidates(BrowseInstructionWrapper browsingInstructions, ZoneNode nodeToLink, ZoneTouristiqueNode currentNodeBrowsed, LinkingZTConfigurationRule config, List<ZoneTouristiqueNode> nodeCandidateList) {
        if(currentNodeBrowsed == null || currentNodeBrowsed.getNiveau() == null || CollectionUtils.isEmpty(currentNodeBrowsed.getChildren()) || isLinkingLimitReached(nodeToLink, nodeCandidateList, config))
            return;

        currentNodeBrowsed.getChildren()
                .stream()
                .filter(child -> doNotSkipNode(child, browsingInstructions))
                .filter(child -> child.isContainsOffer())
                .forEach(child -> {
                    if(isLinkingLimitReached(nodeToLink, nodeCandidateList, config))
                        return;

                    if(ObjectUtils.equals(config.getLevel(), child.getNiveau())) {

                        boolean respectConfigurationRules = respectConfigurationRules(config, child, nodeToLink);
                        if(respectConfigurationRules) {
                            nodeCandidateList.add(child);
                        }
                    } else if(child.getNiveau() > config.getLevel()) {
                        return; // Optimisation -> inutile de continuer à parcourir les noeuds enfants si l'on est deja sur un niveau trop faible (et que l'on est donc sur de ne plus tomber sur le niveau de la config dans cette branche de l'arbre)
                    }else {
                        browseZoneChildsToGetCandidates(browsingInstructions, nodeToLink, child, config, nodeCandidateList);
                    }
                });
    }

    private boolean isLinkingLimitReached(ZoneNode nodeToLink, List<ZoneTouristiqueNode> nodeCandidateList, LinkingZTConfigurationRule config) {
        List<ZoneTouristiqueNode> linkingList = zoneTouristiqueTreeUtils.getLinkingList(generationType, nodeToLink);
        int sizeLinkingListPlusCandidates = linkingList.size() + nodeCandidateList.size();

        return sizeLinkingListPlusCandidates >= config.getNumber();
    }

    /**
     * Récupère la collection de linking selon le {@link GenerationType}
     * @param node
     * @return
     */
    protected List<ZoneTouristiqueNode> getLinkingList(ZoneNode node) {
        return zoneTouristiqueTreeUtils.getLinkingList(generationType, node);
    }

    private boolean doNotSkipNode(ZoneTouristiqueNode child, BrowseInstructionWrapper browsingInstructions) {
        if(browsingInstructions.getNodesToSkip() == null)
            return true;

        return browsingInstructions.getNodesToSkip().stream()
                .noneMatch(nodeToSkip -> child.getId().equals(nodeToSkip.getId()));
    }

    /**
     * Linking d'élargissement si pas assez de résultats (avec une règle spécifique)
     * @param nodeToLink
     * @param config
     */
    private void remote(ZoneNode nodeToLink, LinkingZTConfigurationRule config) {
        LinkingZTConfigurationRule remoteConfiguration = config.getRemote();
        // On complète si pas assez de résultats, selon la configuration "Remote"
        if(remoteConfiguration != null) {
            remoteConfiguration.setGroupName(config.getGroupName()); // On reste dans le même groupe
            // On doit setter le nombre de résultats à chercher : nbr résultats initialement attendu (dans config) - nbr de résultats récupérés
            int missingResults = nodeToLink.getZone().getMissingResults();
            if(missingResults > 0){
                remoteConfiguration.setNumber(missingResults);
                linkZTnForConfiguration(nodeToLink, remoteConfiguration);
            }
        }
    }

    private void beginLinkCandidatesNodes(ZoneNode node, List<ZoneTouristiqueNode> linkingList, List<ZoneTouristiqueNode> nodeCandidateList, LinkingZTConfigurationRule config) {
        node.getZone().setMissingResults(config.getNumber());
        linkCandidatesNodes(node, linkingList, nodeCandidateList, config);
    }

    /**
     * Faire le linking en se basant sur les poids des liens
     *
     * @param node
     * @param linkedNodes
     * @param nodeCandidateList
     * @param config
     */
    protected void linkCandidatesNodes(ZoneNode node, List<ZoneTouristiqueNode> linkedNodes, List<ZoneTouristiqueNode> nodeCandidateList, LinkingZTConfigurationRule config) {
        int constraint = linkedNodes.size() + config.getNumber();

        if (linkedNodes.size() >= constraint) {
            nodeCandidateList.clear();
            return;
        }

        nodeCandidateList.sort(new ZoneTouristiqueNodePoidsComparator());
        for (ZoneTouristiqueNode nodeCandidate : nodeCandidateList) {
            if(respectConfigurationRules(config, nodeCandidate, node)) {
                if (!nodeCandidate.equals(node.getZone())
                        && !linkedNodes.contains(nodeCandidate)) {
                    link(linkedNodes, config, nodeCandidate);

                    decrementMissingResults(node.getZone());
                    nodeCandidate.incrementPoids();
                    if (linkedNodes.size() >= constraint) {
                        nodeCandidateList.clear();
                        return;
                    }
                }
            }
        }
        nodeCandidateList.clear();
    }

    private void link(List<ZoneTouristiqueNode> linkedNodes, LinkingZTConfigurationRule config, ZoneTouristiqueNode nodeCandidate) {
        ZoneTouristiqueNode newChild = new ZoneTouristiqueNode(nodeCandidate); // Shallow Copy - Nouvelle instance pour isoler le noeud candidat et ne plus le modifier

        newChild.setGroupName(config.getGroupName());
        linkedNodes.add(newChild);
    }

    private void decrementMissingResults(ZoneTouristiqueNode node) {
        node.setMissingResults(node.getMissingResults() - 1);
    }

    /**
     * Dans cette méthode sont checkées les règles définies en configuration :
     * - Visibilité
     * - Thématique
     * @param config
     * @param nodeToLink
     * @return
     */
    private boolean respectConfigurationRules(LinkingZTConfigurationRule config, ZoneTouristiqueNode nodeCandidate, ZoneNode nodeToLink) {
        boolean respectConfigurationRules = true;

        if(config != null) {
            if(!zoneTouristiqueTreeUtils.containsUrl(nodeCandidate, nodeToLink.getTheme()))
                return false;

            if(zoneTouristiqueTreeUtils.isForbiddenInLinking(nodeCandidate)) {
                // On ne link pas les noeuds interdits dans le linking
                return false;
            }

            // On ne link pas les noeuds qui ne contiennent pas d'offre
            if(configurationUtils.isActiveFilterDestinationWithOffer() && ! nodeCandidate.isContainsOffer()) {
                return false;
            }

            // on link pas le meme noeud
            if (nodeCandidate.equals(nodeToLink.getZone())){
                return false;
            }
            // Règles de visibilité
            if(!nodeCandidate.isVisible()) { // on ne link pas sur des noeud non visible
                respectConfigurationRules &= false;
            }
			/*
			if(config.getIsVisible() != null) {
				if((BooleanUtils.isTrue(config.getIsVisible()) && ! nodeCandidate.isVisible())
						|| (BooleanUtils.isFalse(config.getIsVisible()) && nodeCandidate.isVisible())) {
					respectConfigurationRules &= false;
				}
			}
			*/
            // Règles sur la thématique
            if(config.getThematique() != null) {
                respectConfigurationRules &= ObjectUtils.equals(nodeToLink.getZone().getThematiqueId(), nodeCandidate.getThematiqueId());
            }

            if(!matchWithTopPrios(nodeCandidate, config)) {
                respectConfigurationRules = false;
            }
        }

        return respectConfigurationRules;
    }

    private boolean matchWithTopPrios(ZoneTouristiqueNode nodeCandidate, LinkingZTConfigurationRule config) {
        if(config.getTopPrioCamping() != null) {
            if(config.getTopPrioCamping().equals(1) && PrioriteZones.isNodeUnderZonePrioriteCamping(nodeCandidate))
                return true;
            else
                return false;
        }

        if(config.getTopPrioLocation() != null) {
            if(config.getTopPrioLocation().equals(1) && PrioriteZones.isNodeUnderZonePrioriteLocation(nodeCandidate))
                return true;
            else
                return false;
        }

        return true;
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
    public void setLinkingRulesResolver(LinkingRulesResolver linkingRulesResolver) {
        this.linkingRulesResolver = linkingRulesResolver;
    }

    @Autowired
    public void setJobData(JobData jobData) {
        this.jobData = jobData;
    }
}
