package com.karavel.batch.seo.linking.common.beans;

import java.util.Optional;

public enum SitesEnum {

	FRAMB2C(41L), PROMOVACANCES(9L), PROMOVACANCES_BELGIQUE(43L), ECOTOUR(38L), SKIGLOO(37l), PARTIRPASCHER(2L), FRAM(39L), OPODO(8L), EDREAMS(18L), GOVOYAGES(4L);
	
	private Long lvSite ;

	private SitesEnum (Long siteId) {
		this.lvSite = siteId;
	}

	public Long getLvSite() {
		return this.lvSite;
	}


	// Utilities
	//-----------------------------------------

	public static SitesEnum getSite(Long id) {
		for(SitesEnum s : SitesEnum.values()) {
			if(s.getLvSite().equals(id)) {
				return s;
			}
		}
		return null;
	}

	public static Optional<SitesEnum> getSiteIfExisted(Long id) {
		return Optional.ofNullable(getSite(id));
	}

	public static boolean isPMVC(Long id){
		return SitesEnum.isSite(PROMOVACANCES, id);
	}

	public static boolean isSkigloo(Long id){
		return SitesEnum.isSite(SKIGLOO, id);
	}

	public static boolean isEcotour(Long id) {
		return SitesEnum.isSite(ECOTOUR, id);
	}

	public static boolean isPPC(Long id) {
		return SitesEnum.isSite(PARTIRPASCHER, id);
	}

	public static boolean isFRAM(Long id){
		return SitesEnum.isSite(FRAM, id);
	}

	public static boolean isOpodo(Long id){
		return isSite(OPODO, id);
	}
	
	public static boolean isEdreams(Long id){
		return isSite(EDREAMS, id);
	}
	
	public static boolean isGoVoyages(Long id){
		return isSite(GOVOYAGES, id);
	}
	
	public static boolean isOdigeo(Long id) {
		return isOdigeo(id) || isEdreams(id) || isGoVoyages(id);
	}
	
	
	// Private methods
	//-----------------------------------------
	
	private static boolean isSite(SitesEnum site, Long id) {
		return (site != null) && site.getLvSite().equals(id);
	}

}
