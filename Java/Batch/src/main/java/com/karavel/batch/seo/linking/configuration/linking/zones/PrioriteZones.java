package com.karavel.batch.seo.linking.configuration.linking.zones;


import com.karavel.batch.seo.linking.common.beans.ZoneTouristiqueNode;

import java.util.Arrays;
import java.util.List;
//Legacy, should become a @Configuration
public class PrioriteZones {
	
	public final static List<Long> prioriteCampingZoneIds = Arrays.asList(2178L, 2153L, 2071L, 2053L, 2150L, 2089L, 2137L, 2083L, 2082L, 2072L, 2084L, 3449L, 2042L, 2653L,
				2156L, 2044L, 2117L, 3448L, 3462L, 2104L, 3471L);	
	
	public final static List<Long> prioriteLocationZoneIds = Arrays.asList(2178L, 2153L, 2071L, 2053L, 2150L, 2089L, 2137L, 2083L, 2082L, 2072L, 2084L, 2670L, 3449L, 2042L,
				2653L, 2156L, 2038L, 2117L, 3471L, 2165L);
	
	public static boolean isNodeUnderZonePrioriteCamping(ZoneTouristiqueNode nodeCandidate) {
		return isNodeUnderZonePriorite(nodeCandidate, prioriteCampingZoneIds);
	}
	
	public static boolean isNodeUnderZonePrioriteLocation(ZoneTouristiqueNode nodeCandidate) {
		return isNodeUnderZonePriorite(nodeCandidate, prioriteLocationZoneIds);
	}
	
	private static boolean isNodeUnderZonePriorite(ZoneTouristiqueNode node, List<Long> zoneIdPriorites) {
		if(node == null)
			return false;
		
		if(zoneIdPriorites.contains(node.getId()))
			return true;
		
		return isNodeUnderZonePriorite(node.getParent(), zoneIdPriorites);
	}
}
