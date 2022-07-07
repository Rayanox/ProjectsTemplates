package com.karavel.batch.seo.linking.utils;

import com.karavel.batch.seo.linking.common.beans.*;
import com.karavel.batch.seo.linking.common.GenerationType;
import com.karavel.batch.seo.linking.core.common.ZoneNode;
import com.karavel.catalogue.sejour.out.referentielgeographique.ZoneTouristiqueExtendedVO;
import com.karavel.catalogue.sejour.out.referentielgeographique.ZoneTouristiqueVO;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Utility class for ZT Tree
 * 
 * @author Walid MELLOULI, Fabien RECCO
 * 
 */
@Component
public class ZoneTouristiqueTreeUtils {

	private final static Logger LOGGER = LoggerFactory.getLogger(ZoneTouristiqueTreeUtils.class);
	
	private static Map<Integer, Long> generatedIdByLevel = new HashMap<Integer, Long>();

	private ConfigurationUtils configurationUtils;
	
	// Constants
	//-------------------------------------------------------
	
	private static final String ROOT_NODE_NAME = "root";
	public static final String GENERATED_NODE_NAME = "Generated";
	
	private static final Long MULTIPLIER = 1000000L;
	
	public static final int LEVEL_ACCOMODATION = 7;
	public static final int LEVEL_VL = 6;
	public static final int COUNTRY_LEVEL = 2;
	public static final Long FRANCE_ID = 2031L;
	
	
	// Utils
	//-------------------------------------------------------
	
	public boolean isForbiddenInLinking(ZoneTouristiqueNode node) {
		boolean forbidden = false;
		if(node != null) {
			List<MinimalZoneTouristiqueNode> forbiddenUrlGenerationNodes = configurationUtils.getForbiddenNodesInLinkingBlocs();
			if(forbiddenUrlGenerationNodes != null) {
				for(MinimalZoneTouristiqueNode forbiddenNode : forbiddenUrlGenerationNodes) {
					if(forbiddenNode != null && forbiddenNode.equals(node)) {
						forbidden = true;
						break;
					}
				}
			}
		}
		return forbidden;
	}
	
	/**
	 * Check if the node contains any url
	 * @param node
	 * @return
	 */
	public boolean containsUrl(ZoneTouristiqueNode node, Theme theme) {
		String uri = node.getUriData().getUriByTheme(theme);
		return StringUtils.isNotBlank(uri) && uri.charAt(0) == '/';
	}
	
	/**
	 * Filtre les ZT qui n'ont pas d'offre
	 * @param node
	 * @return
	 */
	public boolean flagDestinationWithoutOffer(ZoneTouristiqueNode node) {
		boolean hasOffer = false;
		if(node != null) {
			hasOffer |= configurationUtils.getFpNodeNiveau().equals(node.getNiveau());
			
			if(! CollectionUtils.isEmpty(node.getChildren())) {
				for(ZoneTouristiqueNode child : node.getChildren()) {
					if(child != null) {
						boolean childHasOffer = flagDestinationWithoutOffer(child);
						hasOffer |= childHasOffer;
					}
				}
			}
		}
		node.setContainsOffer(hasOffer);
		return hasOffer;
	}
	


	/**
	 * Get linking list regarding the generationType
	 * @param generationType
	 * @param node
	 * @return
	 */
	public LinkingListData getLinkingListData(GenerationType generationType, ZoneTouristiqueNode node) {
		if(generationType != null && node != null) {
			switch(generationType) {
				case SL :
					return node.getLinkingSLList();
				case TOP_HOTEL_POUR_SEJLIST : 
				case FP :
					return node.getLinkingFPList();
			}
		}
		return LinkingListData.EMPTY_LINKINGLISTDATA;
	}
	
	/**
	 * Get linking list regarding the generationType
	 * @param generationType
	 * @param node
	 * @return
	 */
	public List<ZoneTouristiqueNode> getLinkingList(GenerationType generationType, ZoneNode node) {
		LinkingListData linkingListData = getLinkingListData(generationType, node.getZone());
		return linkingListData.getLinkingListByTheme(node.getTheme());
	}
	
	/**
	 * 
	 * @param nodeList
	 * @param pointerAlreadyAddedNodeInTree A map that contains all the nodes that have already been added in the Tree. It is a kind of index (with key = the zone ID) that points to the ZoneTouristiqueNode object added in the Tree. Useful to link perfectly each children with his parent in the tree.
	 */
	public void addNodesInTreeFromNodeList(List<ZoneTouristiqueNode> nodeList, Map<Long, ZoneTouristiqueNode> pointerAlreadyAddedNodeInTree
			, Map<Long, ZoneTouristiqueNode> pointerAlreadyAddedNodeInTreeForLevelVl) {
		nodeList.stream()
				.filter(node -> node.getParent() != null && node.getParent().getId() != null)
				.forEach(node -> linkNodeInTree(node, pointerAlreadyAddedNodeInTree, pointerAlreadyAddedNodeInTreeForLevelVl));
	}

	private void linkNodeInTree(ZoneTouristiqueNode node, Map<Long, ZoneTouristiqueNode> pointerAlreadyAddedNodeInTree, Map<Long, ZoneTouristiqueNode> pointerAlreadyAddedNodeInTreeInLevelVl) {
		ZoneTouristiqueNode parentNode = getParentNodeDependingOnLevel(node, pointerAlreadyAddedNodeInTree, pointerAlreadyAddedNodeInTreeInLevelVl);
		if(areNodesLinkable(node, parentNode)) {
			
			parentNode.getChildren().add(node);
			node.setParent(parentNode);
			
			// This is because of ID duplicated in DB... all of this is because of bad geography conception in Karavel... because of bad IT conception... because of business vision and not technical vision at all... because of... 
			if(node.getNiveau() != LEVEL_VL)
				pointerAlreadyAddedNodeInTree.put(node.getId(), node);
			else {
				ZoneTouristiqueNode zoneAlreadyAdded = pointerAlreadyAddedNodeInTreeInLevelVl.get(node.getId());
				if(zoneAlreadyAdded == null || (!zoneAlreadyAdded.isVisible() && node.isVisible())) {
					pointerAlreadyAddedNodeInTreeInLevelVl.put(node.getId(), node);
				}
			}
		}
	}

	/**
	 * One more hack because of IDs duplication in DB...
	 * @param node
	 * @param pointerAlreadyAddedNodeInTree
	 * @param pointerAlreadyAddedNodeInTreeInLevelVl
	 * @return
	 */
	private ZoneTouristiqueNode getParentNodeDependingOnLevel(ZoneTouristiqueNode node,
			Map<Long, ZoneTouristiqueNode> pointerAlreadyAddedNodeInTree,
			Map<Long, ZoneTouristiqueNode> pointerAlreadyAddedNodeInTreeInLevelVl) {
		
		if(node.getNiveau() < LEVEL_ACCOMODATION)
			return pointerAlreadyAddedNodeInTree.get(node.getParent().getId());
		else
			return pointerAlreadyAddedNodeInTreeInLevelVl.get(node.getParent().getId());
	}

	// on rattache que s'il n'est rattaché à aucun noeud parent ou s'il est déjà rattaché à un noeud parent inactif et que ce "nouveau" parent est actif (on ne peut pas avoir 2 noeuds actifs de même id et de même niveau, sinon confusion pour selectionner le parent)
	private boolean areNodesLinkable(ZoneTouristiqueNode node, ZoneTouristiqueNode parentNode) {
		if(parentNode == null)
			return false;
		
		if(node.getNiveau() <= parentNode.getNiveau())
			return false;
		
		if(!node.getParent().getId().equals(parentNode.getId()))
			return false;
		
		return node.getParent().getNiveau() == null
				|| (!node.getParent().isActive() && parentNode.isActive());
	}

	public void addTreeElementRecursive(ZoneTouristiqueListes allZoneTouristiqueListes, Map<Long, ZoneTouristiqueNode> pointerAlreadyAddedNodeInTree,
										Map<Long, ZoneTouristiqueNode> pointerAlreadyAddedNodeInTreeForLevelVl, int niveau) {
		if(pointerAlreadyAddedNodeInTree == null || pointerAlreadyAddedNodeInTree.isEmpty()) {
			ZoneTouristiqueTreeUtils.LOGGER.error("The variable 'pointerAlreadyAddedNodeInTree' cannot be null or empty and must contain the root nodes of the final Tree");
			return;
		}
		
		List<ZoneTouristiqueNode> nodeList = getListByNiveau(allZoneTouristiqueListes, niveau);
		if (nodeList != null) {
			addNodesInTreeFromNodeList(nodeList, pointerAlreadyAddedNodeInTree, pointerAlreadyAddedNodeInTreeForLevelVl);
			addTreeElementRecursive(allZoneTouristiqueListes, pointerAlreadyAddedNodeInTree, pointerAlreadyAddedNodeInTreeForLevelVl, niveau + 1);
		}
	}

	/**
	 * Delete unused inactifs nodes
	 * 
	 * @param node
	 */
	public void deleteUnusedInactifsNodes(ZoneTouristiqueNode node) {
		setPurgeFlagOnTree(node);
		purgeTree(node);
	}

	/**
	 * Get adequate list level
	 * 
	 * @param zoneTouristiqueListes
	 * @param niveau
	 * @return
	 */
	public List<ZoneTouristiqueNode> getListByNiveau(ZoneTouristiqueListes zoneTouristiqueListes, int niveau) {
		Integer fpNiveau = configurationUtils.getFpNodeNiveau();
		if(niveau <= fpNiveau) {
			return zoneTouristiqueListes.getNodes(niveau);
		} else {
			return null;
		}
	}

	/**
	 * Create a root tree node
	 * 
	 * @return
	 */
	public ZoneTouristiqueNode createRootNode() {
		ZoneTouristiqueNode root = new ZoneTouristiqueNode();
		root.setId(0L);
		root.setNom(ROOT_NODE_NAME);
		root.setNiveau(0);
		root.setNiveauReel(0);
		root.setActive(false);
		return root;
	}

	/**
	 * Create a root tree node
	 * 
	 * @return
	 */
	public ZoneTouristiqueNode createInactiveNode(ZoneTouristiqueNode parentNode, int niveau) {
		ZoneTouristiqueNode inactiveNode = new ZoneTouristiqueNode();
		inactiveNode.setId(parentNode.getId());
		inactiveNode.setActive(false);
		inactiveNode.setNiveau(niveau);
		inactiveNode.setNiveauReel(niveau);
		inactiveNode.setNom(GENERATED_NODE_NAME);
		inactiveNode.setParent(parentNode);
		return inactiveNode;
	}

	/**
	 * Create a tree node from ZoneTouristiqueVO
	 * 
	 * @param lvSite
	 * @param zt
	 * @return
	 */
	public ZoneTouristiqueNode createTreeNode(Long lvSite, ZoneTouristiqueVO zt) {
		ZoneTouristiqueNode node = new ZoneTouristiqueNode();
		node.setId(zt.getId());
		node.setNom(zt.getNom());
		node.setNiveau(zt.getNiveau());
		
		if(zt instanceof ZoneTouristiqueExtendedVO) {
			node.setNiveauReel(((ZoneTouristiqueExtendedVO) zt).getNiveauReel());
		}
		
		node.setVisible(zt.getVisible());
		node.setActive(true);
		node.setParent(new ZoneTouristiqueNode(zt.getZoneParentId()));
		node.setProduitLogementType(zt.getProduitLogementType());
		node.setProduit(zt.isProduit());
		node.setSource(getProductType(lvSite, zt));
		
		node.setTypeObjetGeograhphique(zt.getTypeObjetGeograhphique());
		node.setZoneAssocieZoneGeographique(zt.getZoneAssocieZoneGeographique());
		node.setIdObjetGeographique(zt.getIdObjetGeographique());
		
		return node;
	}
	
	/**
	 * Burst ZoneTouristiqueVO array into finer node lists
	 * 
	 * @param lvSite
	 * @param ztTab all ZT list
	 * @return object containing many levels list
	 */
	public ZoneTouristiqueListes createZoneTouristiqueListes(Long lvSite, ZoneTouristiqueVO[] ztTab) {
		ZoneTouristiqueListes ztListes = new ZoneTouristiqueListes();
		if(! ArrayUtils.isEmpty(ztTab)) {
			for (ZoneTouristiqueVO zt : ztTab) {
				if(zt.getId() == null)
					continue;
				
				ZoneTouristiqueNode node = createTreeNode(lvSite, zt);
				node.setGeneratedId(getGeneratedIdByNiveau(node.getNiveau()));
				ztListes.addNode(node, zt.getNiveau());
			}
			// trier les listes
			ztListes.trierListes();
		}
		return ztListes;
	}

	/**
	 * Display recursively all tree nodes
	 * 
	 * @param node
	 */
	public void displayTree(ZoneTouristiqueNode node) {
		if(LOGGER.isDebugEnabled()) {
			StringBuilder str = new StringBuilder("|_");
			for (int cpt = 0; cpt < node.getNiveau(); cpt++) {
				str.append("____");
			}
			LOGGER.info("{} ({}) {} (id: {}, generatedId: {}, visible: {}, url: {})",
					new Object[] { str.toString(), node.getNiveau(), node.getNom(), node.getId(), node.getGeneratedId(),
							node.isVisible(), node.getUriData() });
	
			for (ZoneTouristiqueNode childNode : node.getChildren()) {
				displayTree(childNode);
			}
		}
	}


	/**
	 * Get generated ID by level
	 * 
	 * @param niveau
	 * @return
	 */
	public Long getGeneratedIdByNiveau(int niveau) {
		Long floor = configurationUtils.getPlage();
		Long generatedId = incrementGeneratedId(niveau);
		return floor + generatedId;
	}
	
	/**
	 * Get ZT2
	 * @param node
	 * @return
	 */
	public ZoneTouristiqueNode getZT2(ZoneTouristiqueNode node) {
		return getZTn(node, 2);
	}
	
	/**
	 * Get ROOT
	 * @param node
	 * @return
	 */
	public ZoneTouristiqueNode getRoot(ZoneTouristiqueNode node) {
		return getZTn(node, 0);
	}
	
	/**
	 * Get ZT of level n (if current level > n)
	 * @param node
	 * @param n (level)
	 * @return
	 */
	public static ZoneTouristiqueNode getZTn(ZoneTouristiqueNode node, Integer n) {
		if(n != null) {
			ZoneTouristiqueNode nodeZTn = node;
			while(nodeZTn != null && nodeZTn.getNiveau() != null && nodeZTn.getNiveau().intValue() > n) {
				nodeZTn = nodeZTn.getParent();
			}
			
			if(nodeZTn != null && nodeZTn.getNiveau() != null && nodeZTn.getNiveau().intValue() == n) {
				return nodeZTn;
			}
		}
		
		return null;
	}
	
	// Cette methode gere aussi les cas ou il y aurait des sauts entre les niveaux dans le graphe - Permet d'obtenir le noeud du graphe d'une position superieure au noeud en parametre, situé au niveau 'n', mais si ce niveau là n'existe pas (saut d'un niveau par exemple: on passe de 3 à 1 alors qu'on veut le niveau 2), alors on récupèrera le noeud d'un niveau inférieur à condition que ce niveau reste supérieur au niveau du noeud en parametre 
	public ZoneTouristiqueNode getZTnOrLess(ZoneTouristiqueNode currentNodeBrowsed, Integer n) {
		if(currentNodeBrowsed == null || n == null)
			return null;
		
		ZoneTouristiqueNode result = null;
		if(isZtLessLevel(currentNodeBrowsed, n)) {
			int i = n;
			while(result == null && i <= currentNodeBrowsed.getNiveau()) {
				result = getZTn(currentNodeBrowsed, i--);
			}
		}else if(areSameLevel(currentNodeBrowsed, n)) {
			result = currentNodeBrowsed;
		}

		return result;
	}
		
	
	private boolean areSameLevel(ZoneTouristiqueNode currentNodeBrowsed, Integer n) {
		return n.compareTo(currentNodeBrowsed.getNiveau()) == 0;
	}

	private boolean isZtLessLevel(ZoneTouristiqueNode currentNodeBrowsed, Integer n) {
		return n.compareTo(currentNodeBrowsed.getNiveau()) < 0;
	}

	// Private methods
	//-------------------------------------------------------
	
	/**
	 * Set purge flag to true for eligible node to remove
	 * 
	 * @param node
	 */
	private void setPurgeFlagOnTree(ZoneTouristiqueNode node) {
		if (!node.getChildren().isEmpty()) {
			for (ZoneTouristiqueNode childNode : node.getChildren()) {
				setPurgeFlagOnTree(childNode);
			}
		}

		if (!node.isActive()) {
			if (isLevelNodeToPurgeWhenInactive(node) || (node.getChildren().size() == 1 && node.getChildren().get(0).isToPurge())) {
				// pour les VLs + FPs ou pour le reste des noeuds inutiles (autres que feuilles)
				node.setToPurge(true);
			}
		}
	}
	
	private boolean isLevelNodeToPurgeWhenInactive(ZoneTouristiqueNode node) {
		List<Integer> nodesToPurge = configurationUtils.getInactiveNodeLevelsToPurge();
		if(! CollectionUtils.isEmpty(nodesToPurge)) {
			return nodesToPurge.contains(node.getNiveau());
		}
		return false;
	}

	/**
	 * Delete node with purge flag = true
	 * 
	 * @param node
	 */
	private void purgeTree(ZoneTouristiqueNode node) {
		if (!node.getChildren().isEmpty()) {
			List<ZoneTouristiqueNode> toPurge = new ArrayList<>();
			for (ZoneTouristiqueNode childNode : node.getChildren()) {
				if (childNode.isToPurge()) {
					toPurge.add(childNode);
				}
			}
			// supprimer les noeuds a purgé
			node.getChildren().removeAll(toPurge);
			// toPurger récursivement les noeuds fils
			for (ZoneTouristiqueNode childNode : node.getChildren()) {
				purgeTree(childNode);
			}
		}
	}
	
	private Source getProductType(Long lvSite, ZoneTouristiqueVO zt) {
		if(isVirageId(lvSite, zt)) {
			return Source.VIRAGE;
		}
		return Source.CATALOGUE;
	}

	private boolean isVirageId(Long lvSite, ZoneTouristiqueVO zt) {
		return configurationUtils.useVirage(lvSite) && zt != null && ! zt.isProduit() && zt.getId() != null &&  zt.getId().longValue() >= 100000;
	}

	private Long incrementGeneratedId(int level) {
		Long generatedId = getGeneratedIdByLevelOrCreate(level);
		if(generatedId != null) {
			generatedId++;
			generatedIdByLevel.put(level, generatedId);
		}
		
		return generatedId;
		
	}

	private Long getGeneratedIdByLevelOrCreate(int level) {
		if(generatedIdByLevel.containsKey(level)) {
			return generatedIdByLevel.get(level);
		} 
		return initializeGeneratedIdLevel(level);
	}

	private long initializeGeneratedIdLevel(int level) {
		long value = level * MULTIPLIER;
		generatedIdByLevel.put(level, value);
		return value;
	}

	/**
	 * Initiate the Map of pointers to the ZoneTouristiqueNode objects of the final Tree.
	 * 
	 * @param rootNode
	 * @param pointerMapResult
	 * @return
	 */
	public Map<Long, ZoneTouristiqueNode> initTreePointerMapRecursive(ZoneTouristiqueNode rootNode, Map<Long, ZoneTouristiqueNode> pointerMapResult) {
		if(pointerMapResult == null)
			pointerMapResult = new HashMap<Long, ZoneTouristiqueNode>();
		pointerMapResult.put(rootNode.getId(), rootNode);
		if(rootNode.getChildren() != null) {
			for (ZoneTouristiqueNode nodeChild : rootNode.getChildren()) {
				initTreePointerMapRecursive(nodeChild, pointerMapResult);
			}
		}
		return pointerMapResult;
	}

	@Autowired
	public void setConfigurationUtils(ConfigurationUtils configurationUtils) {
		this.configurationUtils = configurationUtils;
	}
}
