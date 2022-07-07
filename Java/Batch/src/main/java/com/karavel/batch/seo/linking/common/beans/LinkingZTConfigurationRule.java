package com.karavel.batch.seo.linking.common.beans;

import java.io.Serializable;
import java.util.List;

public class LinkingZTConfigurationRule implements Serializable {
	
	/**
	 * Generatid Serial Version UID
	 */
	private static final long serialVersionUID = -8920714268688552252L;
	
	
	// Attributes
	//----------------------------------------------------------------------
	
	// Exemple : 4 ZT3 V même ZT 2, meme ZT1
	
	private Integer number; //4
	private Integer level;   // 3 (ZT3)
	private Integer topPrioLocation;
	private Integer topPrioCamping;
	private Thematique thematique;
	private LinkingZTConfigurationRule remote;
	private List<LinkingZTTypeRule> linkingZTTypeRules;
	private String groupName;
	
	private LinkZTType linkZTType; // Même (= LinkZTType#SAME_ZTn)
	private Integer levelZT; // 2 (ZT2)
	private boolean topGeo;

	
	// Getters
	//----------------------------------------------------------------------
	
	public Integer getNumber()                             { return number;             }
	public Integer getLevel()                              { return level;              }
	public LinkZTType getLinkZTType()                      { return linkZTType;         }
	public Integer getLevelZT()                            { return levelZT;            }
	public Thematique getThematique()                      { return thematique;         }
	public LinkingZTConfigurationRule getRemote()          { return remote;             }
	public List<LinkingZTTypeRule> getLinkingZTTypeRules() { return linkingZTTypeRules; }
	public String getGroupName()                           { return groupName;          }
	
	
	
	// Setters
	//----------------------------------------------------------------------

	public void setNumber(Integer number)                                         { this.number = number;                         }
	public void setLevel(Integer level)                                           { this.level = level;                           }
	public void setLinkZTType(LinkZTType linkZTType)                              { this.linkZTType = linkZTType;                 }
	public void setLevelZT(Integer levelZT)                                       { this.levelZT = levelZT;                       }
	public void setThematique(Thematique thematique)                              { this.thematique = thematique;                 }
	public void setRemote(LinkingZTConfigurationRule remote)                      { this.remote = remote;                         }
	public void setLinkingZTTypeRules(List<LinkingZTTypeRule> linkingZTTypeRules) { this.linkingZTTypeRules = linkingZTTypeRules; }
	public void setGroupName(String groupName)                                    { this.groupName = groupName;                   }

	public Integer getTopPrioLocation() {
		return topPrioLocation;
	}
	public void setTopPrioLocation(Integer topPrioLocation) {
		this.topPrioLocation = topPrioLocation;
	}
	public Integer getTopPrioCamping() {
		return topPrioCamping;
	}
	public void setTopPrioCamping(Integer topPrioCamping) {
		this.topPrioCamping = topPrioCamping;
	}
	public boolean isTopGeo() {
		return topGeo;
	}
	public void setTopGeo(boolean topGeo) {
		this.topGeo = topGeo;
	}
	
}
