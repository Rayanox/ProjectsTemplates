package com.karavel.batch.seo.linking.common.beans;

import com.karavel.batch.seo.linking.comparators.ZoneTouristiqueNodeIdComparator;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Eclater l'arbre en liste de niveau
 * @author Walid MELLOULI, Fabien RECCO
 *
 */
public class ZoneTouristiqueListes {
	
	// Attributes
	//------------------------------------------------
	
	private ZoneTouristiqueNodeIdComparator comparatorId;
	private Map<Integer, List<ZoneTouristiqueNode>> nodesByLevel;
	
	
	// Constructors
	//------------------------------------------------
	
	public ZoneTouristiqueListes() {
		super();
		nodesByLevel = new HashMap<>();
		comparatorId = new ZoneTouristiqueNodeIdComparator();
	}
	
	
	// Utilities
	//------------------------------------------------

	public void addNode(ZoneTouristiqueNode node, int level) {
		List<ZoneTouristiqueNode> nodes = getNodesForLevelOrCreateIfNotExists(level);
		nodes.add(node);
	}
	
	public List<ZoneTouristiqueNode> getNodes(int level) {
		return getNodesForLevelOrCreateIfNotExists(level);
	}
	
	public void trierListes() {
		if(! CollectionUtils.isEmpty(nodesByLevel)) {
			for(List<ZoneTouristiqueNode> nodes : nodesByLevel.values()) {
				Collections.sort(nodes, comparatorId);
			}
		}
	}
	
	
	// Private methods
	//------------------------------------------------

	private List<ZoneTouristiqueNode> getNodesForLevelOrCreateIfNotExists(int level) {
		List<ZoneTouristiqueNode> nodes = null;
		if(nodesByLevel.containsKey(level)) {
			nodes = nodesByLevel.get(level);
		} else {
			nodes = new ArrayList<ZoneTouristiqueNode>();
			nodesByLevel.put(level, nodes);
		}
		return nodes;
	}
		
}
