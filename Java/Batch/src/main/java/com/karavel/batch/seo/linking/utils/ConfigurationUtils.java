package com.karavel.batch.seo.linking.utils;

import com.karavel.batch.seo.linking.common.beans.MinimalZoneTouristiqueNode;
import com.karavel.batch.seo.linking.common.beans.SitesEnum;
import com.karavel.batch.seo.linking.configuration.startup.GlobalConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;


/**
 * Classe Utilitaire permettant de récupérer les Configurations définies en properties
 * @author frecco
 *
 */
@Component
public class ConfigurationUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(ConfigurationUtils.class);

	private GlobalConfiguration globalConfiguration;
	

	/**
	 * Constructeur privé, pour forcer l'appel aux méthodes de façon statique
	 */
	public ConfigurationUtils(GlobalConfiguration globalConfiguration){
		this.globalConfiguration = globalConfiguration;
		init();
	}
	
	private void init() {
		getForbiddenNodesInLinkingBlocs();
	}
	
    
    // Attributes
 	//----------------------------------------------------------

	private List<MinimalZoneTouristiqueNode> forbiddenNodesInLinkingBlocs = null;
	
	
	// Methods
 	//----------------------------------------------------------
	
	public boolean useVirage(Long lvSite) {
        SitesEnum site = SitesEnum.getSite(lvSite);
        switch(site) {
            case FRAMB2C       : return false;  
            case FRAM          : return false; // Dans la V1 de FRAM pas de virage !
            case PARTIRPASCHER :
            case PROMOVACANCES :
            case ECOTOUR       :
            case OPODO         :
            case EDREAMS       :
            case GOVOYAGES     :
            default : return true;
        }
    }
	
	public boolean isFlexiFram(Long lvSite) {
        SitesEnum site = SitesEnum.getSite(lvSite);
        switch(site) {
            case FRAMB2C       :  
            case FRAM          :
            case PARTIRPASCHER :
            case PROMOVACANCES :
            case ECOTOUR       :
            case OPODO         :
            case EDREAMS       :
            case GOVOYAGES     :
            default : return true;
        }
    }
	
	/**
	 * Récupère la liste des noeuds à supprimer depuis la configuration
	 * @return
	 */
	public List<String> getNodesToDelete() {
		return globalConfiguration.getNoeudsASupprimer();
	}
	
	public List<MinimalZoneTouristiqueNode> getForbiddenNodesInLinkingBlocs() {
		if(forbiddenNodesInLinkingBlocs == null) {
			forbiddenNodesInLinkingBlocs = new ArrayList<MinimalZoneTouristiqueNode>();
			List<String> nodeListDescription = globalConfiguration.getNoeudsInterditsDansBlocLinking();
			
			if(! CollectionUtils.isEmpty(nodeListDescription)) {
				for(String nodeDescription : nodeListDescription) {
					if(nodeDescription != null) {
						String[] temp = nodeDescription.split("-");
						Long id = Long.valueOf(temp[0]);
						Integer level = Integer.valueOf(temp[1]);
						MinimalZoneTouristiqueNode node = new MinimalZoneTouristiqueNode(id, level);
						forbiddenNodesInLinkingBlocs.add(node);
					}
				}
			}
		}
		
		return forbiddenNodesInLinkingBlocs;
	}
	

	/**
	 * Récupère le niveau FP depuis la configuration
	 * @return
	 */
	public Integer getFpNodeNiveau() {
		return globalConfiguration.getFpZoneNiveau();
	}
	
	public boolean isActiveFilterDestinationWithOffer() {
        return globalConfiguration.getFiltreDestinationAvecOffre();
	}
	
	/**
	 * Récupère la liste des niveaux à purger pour les noeuds inactifs
	 * @return
	 */
	public List<Integer> getInactiveNodeLevelsToPurge() {
        return globalConfiguration.getListeNiveauxNoeudsAPurger();
	}
	

	/**
	 * Récupère la plage pour la génération des GeneratedBatchId
	 * @return
	 */
	public Long getPlage() {
        return globalConfiguration.getGeneratedIdPlage();
	}
}
