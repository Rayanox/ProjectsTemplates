package com.karavel.batch.seo.linking.common.beans;

import java.util.HashMap;
import java.util.Set;

public class UriData {

	public static final UriData EMPTY_URIDATA = new UriData();

	private HashMap<Theme, String> uriByTheme;
	
	
	public UriData() {
		this.uriByTheme = new HashMap<>();
	}
	
	
	public Set<Theme> getAllThemesOfUris() {
		return uriByTheme.keySet();
	}
	
	public void addUriForTheme(String uri, Theme theme) {
		this.uriByTheme.put(theme, uri);
	}
	
	public String getUriByTheme(Theme theme) {
		return this.uriByTheme.get(theme);
	}
	
}
