package com.karavel.batch.seo.linking.common.beans.browsing;

import com.karavel.batch.seo.linking.common.beans.LinkZTType;
import com.karavel.batch.seo.linking.common.beans.LinkingZTConfigurationRule;
import com.karavel.batch.seo.linking.common.beans.ZoneTouristiqueNode;
import com.karavel.batch.seo.linking.common.beans.geo.TopGeoManager;
import com.karavel.batch.seo.linking.utils.ZoneTouristiqueTreeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class BrowseInstructionWrapperBuilder {

	private final static Logger LOGGER = LoggerFactory.getLogger(BrowseInstructionWrapperBuilder.class);

	private static TopGeoManager topGeoManager = new TopGeoManager();
	
	private Iterator<BrowseConditionStep> nextBrowseConditionSteps;
	private ZoneTouristiqueNode nodeToLink;
	private LinkingZTConfigurationRule config;
	
	private BrowseInstructionWrapper 		instructionResult;

	private final ZoneTouristiqueTreeUtils zoneTouristiqueTreeUtils;
	
	private BrowseInstructionWrapperBuilder(ZoneTouristiqueTreeUtils zoneTouristiqueTreeUtils) {
		this.zoneTouristiqueTreeUtils = zoneTouristiqueTreeUtils;
	}
	
	public static BrowseInstructionWrapperBuilder initBuilder(ZoneTouristiqueTreeUtils zoneTouristiqueTreeUtils) {
		return new BrowseInstructionWrapperBuilder(zoneTouristiqueTreeUtils);
	}
	
	public BrowseInstructionWrapperBuilder withConfigurationRule(LinkingZTConfigurationRule configRule) {
		this.config = configRule;
		return this;
	}

	public BrowseInstructionWrapperBuilder forNode(ZoneTouristiqueNode nodeToLink) {
		this.nodeToLink = nodeToLink;
		return this;
	}
	
	public boolean validate() {
		boolean validated = true;
		
		if(this.config == null) {
			LOGGER.error("The configuration rules are missing from the builder");
			validated = false;
		}

		if(this.nodeToLink == null) {
			LOGGER.error("The node to link must be provided to the builder");
			validated = false;
		}
		
		return validated;
	}
	
	public BrowseInstructionWrapper build() {
		if(validate()) {
			this.instructionResult = new BrowseInstructionWrapper();
			setBrowseConditionSteps();
			processRules();
		}
		return instructionResult;
	}
	
	// Fonctions utilitaires
	
	/**
	 * 	Fonction recursive pour prendre en compte chaque règle et setter le wrapper d'instructions de parcours du graphe pour le linking
	 */
	private void processRules() {
		BrowseConditionStep nextStep = null;
		
		if(nextBrowseConditionSteps.hasNext())
			nextStep = nextBrowseConditionSteps.next();
		
		if(nextStep == null) { // No more steps to proceed
			checkAndCorrectDataIfNotFullConfig();
		}else { // Recursion de la fonction (jusqu'a avoir traité toutes les regles)

			LinkZTType conditionNextStep = nextStep.getConditionZt();
			Integer levelNextStep = nextStep.getLevelZt();
			
			if(!validateStepConfiguration(nextStep))
				throw new RuntimeException("Bad step linking configuration. At least the levelZT OR the TopGeo must be configured.");
			
			List<ZoneTouristiqueNode> nodeRules;
			if(levelNextStep != null)
				nodeRules = Arrays.asList(zoneTouristiqueTreeUtils.getZTnOrLess(nodeToLink, levelNextStep));
			else {
				nodeRules = topGeoManager.getZonesOfSameTopGeo(nodeToLink);
			}
			
			if(LinkZTType.DIFFERENT_ZTn.equals(conditionNextStep)) {
				if(!CollectionUtils.isEmpty(nodeRules))
					this.instructionResult.setNodesToSkip(nodeRules);
				
				processRules();
			}else{ // Cas SAME
				
				if(nodeRules != null) {
					this.instructionResult.setNodesToBrowseChildren(nodeRules);
				}else {
					this.instructionResult.setNodeToBrowseChildren(nodeToLink);
				}
				
				processRules();
			}
		}
	}
	
	private void checkAndCorrectDataIfNotFullConfig() {
		List<ZoneTouristiqueNode> nodesDifferentToSkip = this.instructionResult.getNodesToSkip();
		List<ZoneTouristiqueNode> nodesToBrowseChildren = this.instructionResult.getNodesToBrowseChildren();
		
		if(CollectionUtils.isEmpty(nodesToBrowseChildren) && !CollectionUtils.isEmpty(nodesDifferentToSkip)) {
			this.instructionResult.setNodeToBrowseChildren(nodesDifferentToSkip.get(0).getParent());
		}
	}
	
	private boolean validateStepConfiguration(BrowseConditionStep step) {
		return step.getTopGeo() == true || step.getLevelZt() != null;
	}

	/**
	 * Obtention des etapes de condition de parcours de l'arbre pour obtenir les noeuds candidats du linking
	 * @return
	 */
	private void setBrowseConditionSteps() {
		List<BrowseConditionStep> browseConditionSteps = BrowseConditionStepsBuilder.initBuilder()
																							.useConfigurationRule(this.config)
																							.build();
		this.nextBrowseConditionSteps = browseConditionSteps.iterator();
	}
}
