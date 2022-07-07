package com.karavel.batch.seo.linking.common.beans.browsing;

import com.karavel.batch.seo.linking.common.beans.ZoneTouristiqueNode;

import java.util.Arrays;
import java.util.List;

public class BrowseInstructionWrapper {
	
	private List<ZoneTouristiqueNode> nodesToBrowseChildren;
	private List<ZoneTouristiqueNode> nodesToSkip;
	
	public void setNodesToBrowseChildren(List<ZoneTouristiqueNode> nodesToBrowseChildren) {
		this.nodesToBrowseChildren = nodesToBrowseChildren;
	}
	public void setNodeToBrowseChildren(ZoneTouristiqueNode nodeToBrowseChildren) {
		this.nodesToBrowseChildren = Arrays.asList(nodeToBrowseChildren);
	}
	public void setNodesToSkip(List<ZoneTouristiqueNode> nodeToSkip) {
		this.nodesToSkip = nodeToSkip;
	}
	public List<ZoneTouristiqueNode> getNodesToBrowseChildren() {
		return nodesToBrowseChildren;
	}
	public List<ZoneTouristiqueNode> getNodesToSkip() {
		return nodesToSkip;
	}
}
