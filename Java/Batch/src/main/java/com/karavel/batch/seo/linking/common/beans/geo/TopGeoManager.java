package com.karavel.batch.seo.linking.common.beans.geo;

import com.karavel.batch.seo.linking.common.beans.ZoneTouristiqueNode;
import com.karavel.batch.seo.linking.configuration.linking.zones.TopGeoZones;
import com.karavel.batch.seo.linking.exceptions.TopGeoException;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class TopGeoManager {
	
	private static final Integer LEVEL_ZT_TOP_GEO = 2;
	
	private final static EnumMap<TopGeo, List<Long>> mapZoneIdsByTopGeo = TopGeoZones.getZonesByTopGeo();
	private HashMap<Long, TopGeo> mapTopGeoByZoneId;
	
	public TopGeoManager () {
		mapTopGeoByZoneId = new HashMap<>();
		mapZoneIdsByTopGeo.keySet().stream()
			.forEach(topGeo -> {
				mapZoneIdsByTopGeo.get(topGeo).forEach(zoneId -> {
					mapTopGeoByZoneId.put(zoneId, topGeo);
				});
			});
	}
	
	/**
	 * @param nodeTarget the node reference to start from 
	 * @return a list of all nodes of the same TopGeo region as the nodeTarget's TopGeo 
	 * @throws TopGeoException 
	 */
	public List<ZoneTouristiqueNode> getZonesOfSameTopGeo(ZoneTouristiqueNode nodeTarget) throws TopGeoException {
		if(nodeTarget == null)
			throw new TopGeoException();
		
		TopGeo topGeoOfNode = mapTopGeoByZoneId.get(nodeTarget.getId());
		
		if(topGeoOfNode != null)
			return getNodesOfTopGeo(topGeoOfNode, nodeTarget);
		
		return getZonesOfSameTopGeo(nodeTarget.getParent());
	}
	
	private List<ZoneTouristiqueNode> getNodesOfTopGeo(TopGeo topGeoOfNode, ZoneTouristiqueNode node) {
		if(isLevelLessOrEquals(node, LEVEL_ZT_TOP_GEO - 1)) {
			return node.getChildren().stream()
					.filter(child -> isInTopGeo(child, topGeoOfNode))
					.collect(Collectors.toList());
		}
		
		return getNodesOfTopGeo(topGeoOfNode, node.getParent());
	}

	private boolean isLevelLessOrEquals(ZoneTouristiqueNode node, Integer levelZtTopGeo) {
		return node.getNiveau().compareTo(levelZtTopGeo) <= 0;
	}

	private boolean isInTopGeo(ZoneTouristiqueNode node, TopGeo topGeoOfNode) {
		return mapZoneIdsByTopGeo.get(topGeoOfNode).stream()
			.anyMatch(zoneId -> zoneId.equals(node.getId()));
	}
}
