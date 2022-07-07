package com.karavel.batch.seo.linking.common.beans;

import java.io.Serializable;

/**
 * Classe permettant de définir des règles de Linking comme suit :
 * "Même ZT2"
 * <br>Alors mettre : levelZT=2, linkZTType=LinkZTType#SAME_ZTn
 * 
 * @author frecco
 *
 */
public class LinkingZTTypeRule implements Serializable {
	
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;


	// Attributes
	//---------------------------------------------------------

	private LinkZTType linkZTType;
	private Integer levelZT;
	private boolean topGeo; // A utiliser a la place de level -> plusieurs ZTs correspondent a un top geo. Voir la gestion dans le TopGeoManager


	// Getters
	//---------------------------------------------------------
	
	public LinkZTType getLinkZTType() 	{ return linkZTType; 		}
	public Integer getLevelZT()       	{ return levelZT;    		}
	public boolean getTopGeo() 		  	{ return topGeo;	   		}

	
	
	// Setters
	//---------------------------------------------------------
	
	public void setLinkZTType(LinkZTType linkZTType) 		{ this.linkZTType = linkZTType; 			}
	public void setLevelZT(Integer levelZT)          		{ this.levelZT = levelZT;       			}
	public void setTopGeo(boolean topGeo) 			 		{ this.topGeo = topGeo;		 				}
}
