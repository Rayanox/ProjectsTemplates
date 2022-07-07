package com.karavel.batch.seo.linking.common.beans.browsing;


import com.karavel.batch.seo.linking.common.beans.LinkZTType;
import com.karavel.batch.seo.linking.common.beans.LinkingZTTypeRule;

public class BrowseConditionStep {
	
	private LinkZTType conditionZt;
	private Integer levelZt;
	private boolean		topGeo;
	
	public BrowseConditionStep() {
	}

	public BrowseConditionStep(LinkingZTTypeRule linkingZtRule) {
		this.conditionZt = linkingZtRule.getLinkZTType();
		this.levelZt = linkingZtRule.getLevelZT();
		this.topGeo = linkingZtRule.getTopGeo();
	}
	
	public LinkZTType getConditionZt() {
		return conditionZt;
	}
	public void setConditionZt(LinkZTType conditionZt) {
		this.conditionZt = conditionZt;
	}
	public Integer getLevelZt() {
		return levelZt;
	}
	public void setLevelZt(Integer levelZt) {
		this.levelZt = levelZt;
	}
	public void setTopGeo(boolean topGeo) {
		this.topGeo = topGeo;
	}
	public boolean getTopGeo() {
		return topGeo;
	}
}
