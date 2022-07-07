package com.karavel.batch.seo.linking.common.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class LinkingListData {

	public static final LinkingListData EMPTY_LINKINGLISTDATA = new LinkingListData();
	private static final Theme DEFAULT_THEME = Theme.NONE;

	private HashMap<Theme, List<ZoneTouristiqueNode>> linkingByTheme;
	
	
	public LinkingListData() {
		this.linkingByTheme = new HashMap<>();
		this.linkingByTheme.put(DEFAULT_THEME, new ArrayList<>());
	}
	
	
	public Set<Theme> getAllThemesOfLinking() {
		return linkingByTheme.keySet();
	}
	
	public void addLinkingListForTheme(Theme theme) {
		linkingByTheme.putIfAbsent(theme, new ArrayList<>());
		
		if(!DEFAULT_THEME.equals(theme))
			linkingByTheme.remove(DEFAULT_THEME);
	}
	
	public void addNodeLinkedForTheme(ZoneTouristiqueNode node, Theme theme) {
		List<ZoneTouristiqueNode> linkingList = this.linkingByTheme.get(theme);
		if(linkingList != null)
			linkingList.add(node);
	}
	
	public List<ZoneTouristiqueNode> getLinkingListByTheme(Theme theme) {
		if(theme == null)
			theme = Theme.NONE;
		
		return this.linkingByTheme.get(theme);
	}

	public boolean isLinkingListEmptyForTheme(Theme theme) {
		return getLinkingListByTheme(theme).isEmpty();
	}
	
}
