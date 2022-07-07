package com.karavel.batch.seo.linking.common.beans;

import com.karavel.catalogue.sejour.in.linking.ZonesTouristiquesUriType;

public enum Theme {
	
	NONE(null, ZonesTouristiquesUriType.STANDARD, "standard", null, "sejour-voyage"),
	CAMPING(266, ZonesTouristiquesUriType.CAMPING, "camping", "Camping", "camping"),
	LOCATION(261, ZonesTouristiquesUriType.LOCATIF, "locatif", "Location", "location"),
	LOGEMENT_FRANCE(261, ZonesTouristiquesUriType.STANDARD, "logements-france", null, "location");
	
	
	
	private Integer themeId;
	private ZonesTouristiquesUriType uriType;
	private String modeName;
	private String displayName;
	private String themespace;
	
	private Theme(Integer themeId, ZonesTouristiquesUriType uriType, String modeName, String displayName, String themespace) {
		this.themeId = themeId;
		this.uriType = uriType;
		this.modeName = modeName;
		this.displayName = displayName;
		this.themespace = themespace;
	}
	
	public Integer getThemeId() {
		return themeId;
	}
	public ZonesTouristiquesUriType getUriType() {
		return uriType;
	}
	public String getModeName() {
		return modeName;
	}
	public String getDisplayName() {
		return displayName;
	}
	public String getThemespace() {
		return themespace;
	}
}
