package com.karavel.batch.seo.linking.common.beans.browsing;

import com.karavel.batch.seo.linking.common.beans.LinkZTType;
import com.karavel.batch.seo.linking.common.beans.LinkingZTConfigurationRule;
import com.karavel.batch.seo.linking.common.beans.LinkingZTTypeRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class BrowseConditionStepsBuilder {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(BrowseConditionStepsBuilder.class);
	
	private List<BrowseConditionStep> browseConditionSteps;
	private LinkingZTConfigurationRule configRule;
	
	private BrowseConditionStepsBuilder() {
		this.browseConditionSteps = new ArrayList<>();
	}
	
	public static BrowseConditionStepsBuilder initBuilder() {
		return new BrowseConditionStepsBuilder();
	}
	
	public BrowseConditionStepsBuilder useConfigurationRule(LinkingZTConfigurationRule config) {
		this.configRule = config;
		return this;
	}
	
	private boolean validateBeforeBuild() {
		boolean validated = true;
		if(configRule == null) {
			LOGGER.warn("No configuration has been added in the builder before building the object. It implies that the returning list of condition steps will be empty");
			validated = false;
		}
		return validated;
	}
	
	public List<BrowseConditionStep> build(){
		if(validateBeforeBuild()) {
			List<LinkingZTTypeRule> linkingRules = this.configRule.getLinkingZTTypeRules();
			
			if(linkingRules != null && !linkingRules.isEmpty()) {
				LinkingZTTypeRule ruleSame = getRuleOfType(linkingRules, LinkZTType.SAME_ZTn);
				LinkingZTTypeRule ruleDifferent = getRuleOfType(linkingRules, LinkZTType.DIFFERENT_ZTn);
				
				if(ruleDifferent != null) { // L'ordre importe -> On rajoute d'abord les rule DIFFERENT avant les SAME pour faciliter (et optimiser) l'algorithme de parcours du graphe par la suite
					BrowseConditionStep differentConditionStep = new BrowseConditionStep(ruleDifferent); 
					this.browseConditionSteps.add(differentConditionStep);
				}
				
				if(ruleSame != null) {
					BrowseConditionStep sameConditionStep = new BrowseConditionStep(ruleSame); 
					this.browseConditionSteps.add(sameConditionStep);
				}
			}else {
				BrowseConditionStep singleConditionStep = new BrowseConditionStep();
				singleConditionStep.setConditionZt(this.configRule.getLinkZTType());
				singleConditionStep.setLevelZt(this.configRule.getLevelZT());
				singleConditionStep.setTopGeo(this.configRule.isTopGeo());
				this.browseConditionSteps.add(singleConditionStep);
			}
		}
		return this.browseConditionSteps;
	}

	
	
	// Fonctions utilitaires
	
	private LinkingZTTypeRule getRuleOfType(List<LinkingZTTypeRule> linkingRules, final LinkZTType ruleTypeExpected) {
		if(linkingRules == null || ruleTypeExpected == null)
			return null;
		
		return linkingRules.stream()
				.filter(rule -> ruleTypeExpected.equals(rule.getLinkZTType()))
				.findFirst()
				.orElse(null);
	}
}
