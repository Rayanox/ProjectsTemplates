package com.karavel.batch.seo.linking.configuration.linking.zones;


import com.karavel.batch.seo.linking.common.beans.geo.TopGeo;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
//Legacy, should become a @Configuration
public class TopGeoZones {

	public final static EnumMap<TopGeo, List<Long>> getZonesByTopGeo() {
		EnumMap<TopGeo, List<Long>> map = new EnumMap(TopGeo.class);
		
		map.put(TopGeo.MONTAGNE, Arrays.asList(2182L, 2185L, 2174L, 2653L, 2038L, 2661L, 3445L, 2654L, 2660L, 2684L, 2663L, 2655L,
				2658L, 2041L, 2657L, 2652L, 2656L, 2651L, 2184L));
		
		map.put(TopGeo.SUD_EST, Arrays.asList(2178L, 2153L, 2071L, 2150L, 2072L, 2043L, 2042L, 2156L, 2044L, 2117L, 2123L,
				2104L, 3471L, 2170L, 2169L, 2165L, 2124L, 2076L, 2171L, 2157L, 2119L, 3468L, 2167L, 2103L, 2125L, 2155L, 2179L));

		map.put(TopGeo.SUD_OUEST, Arrays.asList(2083L, 2082L, 2084L, 3462L, 3464L, 2112L, 2113L));

		map.put(TopGeo.NORD_EST, Arrays.asList(2670L, 2048L, 2068L, 2032L));

		map.put(TopGeo.NORD_OUEST, Arrays.asList(2053L, 2089L, 2137L, 3449L, 3448L, 2140L, 2102L, 2101L, 3450L, 2126L, 2135L,
				3455L, 2063L, 3451L, 2147L, 2068L));
		
		return map;
	}
}
