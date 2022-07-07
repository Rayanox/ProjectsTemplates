package com.karavel.batch.seo.linking.utils;

import org.apache.commons.lang.ArrayUtils;

public class PushObjetType {
	
	// Constants
	//------------------------------------------------------------
	
	public final static Integer VILLE_LOGEMENT = 1;
	public final static Integer VILLE_ARRIVEE = 2;
	public final static Integer ESCALE = 3;
	public final static Integer ZONE_MERE = 4;
	public final static Integer ZONE_FILLE = 5;
	public final static Integer CONTINENT_LIEN_SEO = 6;
	public final static Integer PAYS_LIEN_SEO = 7;
	public final static Integer PUSH_ZONE = 8;
	public final static Integer VILLE_LIEN_SEO = 9;
	public final static Integer SKI_DOMAINE = 10;
	public final static Integer POI = 11;
	
	
	// Arrays By type
	
	public final static Integer[] GEOGRAPHIC_COUNTRY_OBJECTS = {PAYS_LIEN_SEO};
	public final static Integer[] GEOGRAPHIC_CITY_OBJECTS    = {VILLE_LOGEMENT, VILLE_ARRIVEE, VILLE_LIEN_SEO};
	
	
	// Helpers
	//------------------------------------------------------------
	
	public static boolean isGeographicCountryObject(Integer i) {
		return isGeographicTypeObject(GEOGRAPHIC_COUNTRY_OBJECTS, i);
	}
	
	public static boolean isGeographicCityObject(Integer i) {
		return isGeographicTypeObject(GEOGRAPHIC_CITY_OBJECTS, i);
	}
	
	
	// Private methods
	//------------------------------------------------------------
	
	private static boolean isGeographicTypeObject(Integer[] type, Integer i) {
		return ArrayUtils.contains(type, i);
	}
	

}
