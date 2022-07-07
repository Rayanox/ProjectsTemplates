package com.karavel.batch.seo.linking.common.beans;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Zone Touristique tree node
 * @author Walid MELLOULI
 *
 */
public class ZoneTouristiqueNode extends MinimalZoneTouristiqueNode {

	
	// Attributes
	//----------------------------------------------------------
	
	private Long generatedId;
	private String nom;
	private Integer niveauReel;
	private boolean visible;
	private UriData uriData;
	private Integer poids = 1; /* le poids de l'utilisation du lien associé à ce noeud */
	private ZoneTouristiqueNode parent;
	private List<ZoneTouristiqueNode> children;
	private boolean active = true;
	private boolean toPurge = false;
	private LinkingListData linkingSLList;
	private LinkingListData linkingFPList;
	private Long produitLogementType;
	private boolean isProduit;
	private Source source;
	private boolean callUrlGeneration = false;
	private Long thematiqueId; // TODO pour la différence ETE / SKI
	private int missingResults;
	private String groupName;
	private boolean containsOffer = false;
	
	
	private Boolean zoneAssocieZoneGeographique;
	private Integer typeObjetGeograhphique;
	private Long idObjetGeographique;
	
	// Constructors
	//----------------------------------------------------------
	
	

	public ZoneTouristiqueNode() {
		super();
	}
	
	public ZoneTouristiqueNode(Long id) {
		super(id);
	}
	
	public ZoneTouristiqueNode(ZoneTouristiqueNode other) {
		setId(other.getId());
		setGeneratedId(other.getGeneratedId());
		setNom(other.getNom());
		setNiveau(other.getNiveau());
		setNiveauReel(other.getNiveauReel());
		setVisible(other.isVisible());
		setUriData(other.getUriData());
		setPoids(other.getPoids());
		setParent(other.getParent());
		setChildren(other.getChildren());
		setActive(other.isActive());
		setLinkingSLList(other.getLinkingSLList());
		setLinkingFPList(other.getLinkingFPList());
		setProduitLogementType(other.getProduitLogementType());
		setProduit(other.isProduit());
		setSource(other.getSource());
		setCallUrlGeneration(other.isCallUrlGeneration());
		setThematiqueId(other.getThematiqueId());
		setMissingResults(other.getMissingResults());
		setGroupName(other.getGroupName());
		setZoneAssocieZoneGeographique(other.getZoneAssocieZoneGeographique());
		setTypeObjetGeograhphique(other.getTypeObjetGeograhphique());
		setIdObjetGeographique(other.getIdObjetGeographique());
		setContainsOffer(other.isContainsOffer());
	}
	
	
	// Getters
	//----------------------------------------------------------

	public Long getGeneratedId()                    { return generatedId;                 }
	public String getNom()                          { return nom;                         }
	public boolean isVisible()                      { return visible;                     }
	public Source getSource()                       { return source;                      }
	public Integer getPoids()                       { return poids;                       }
	public ZoneTouristiqueNode getParent()          { return parent;                      }
	public boolean isActive()                       { return active;                      }
	public boolean isToPurge()                      { return toPurge;                     }
	public Long getProduitLogementType()            { return produitLogementType;         }
	public boolean isProduit()                      { return isProduit;                   }
	public boolean hasCallUrlGeneration()           { return callUrlGeneration;           }
	public Long getThematiqueId()                   { return thematiqueId;                }
	public int getMissingResults()                  { return missingResults;              }
	public boolean isCallUrlGeneration()            { return callUrlGeneration;           }
	public Boolean getZoneAssocieZoneGeographique() { return zoneAssocieZoneGeographique; }
	public Integer getTypeObjetGeograhphique()      { return typeObjetGeograhphique;      }
	public Long getIdObjetGeographique()            { return idObjetGeographique;         }
	public String getGroupName()                    { return groupName;                   }
	public Integer getNiveauReel()                  { return niveauReel;                  }
	public boolean isContainsOffer()                { return containsOffer;               }
	

	public UriData getUriData() {
		if(uriData == null)
			uriData = new UriData();
		return uriData;                     
	}
	
	/**
	 * @return the fils
	 */
	public List<ZoneTouristiqueNode> getChildren() {
		if (children == null) {
			children = new ArrayList<ZoneTouristiqueNode>();
		}
		return children;
	}
	
	public LinkingListData getLinkingFPList() {
		if(linkingFPList == null)
			linkingFPList = new LinkingListData();
		return linkingFPList;
	}
	
	public LinkingListData getLinkingSLList() {
		if(linkingSLList == null)
			linkingSLList = new LinkingListData();
		return linkingSLList;
	}
	
	
	// Setters
	//----------------------------------------------------------
	
	public void setGeneratedId(Long generatedId)                                    { this.generatedId = generatedId;                                 }
	public void setNom(String nom)                                                  { this.nom = nom;                                                 }
	public void setVisible(boolean visible)                                         { this.visible = visible;                                         }
	public void setUriData(UriData uri)                                             { this.uriData = uri;                                             }
	public void setSource(Source source)                                            { this.source = source;                                           }
	public void setPoids(Integer poids)                                             { this.poids = poids;                                             }
	public void setParent(ZoneTouristiqueNode parent)                               { this.parent = parent;                                           }
	public void setChildren(List<ZoneTouristiqueNode> children)                     { this.children = children;                                       }
	public void setActive(boolean active)                                           { this.active = active;                                           }
	public void setToPurge(boolean toPurge)                                         { this.toPurge = toPurge;                                         }
	public void setLinkingFPList(LinkingListData linkingFPList) 					{ this.linkingFPList = linkingFPList;							  }
	public void setLinkingSLList(LinkingListData linkingSLList) 					{ this.linkingSLList = linkingSLList;							  }
	public void setProduitLogementType(Long produitLogementType)                    { this.produitLogementType = produitLogementType;                 }
	public void setProduit(boolean isProduit)                                       { this.isProduit = isProduit;                                     }
	public void setCallUrlGeneration(boolean callUrlGeneration)                     { this.callUrlGeneration = callUrlGeneration;                     }
	public void setThematiqueId(Long thematiqueId)                                  { this.thematiqueId = thematiqueId;                               }
	public void setMissingResults(int missingResults)                               { this.missingResults = missingResults;                           }
	public void setZoneAssocieZoneGeographique(Boolean zoneAssocieZoneGeographique) { this.zoneAssocieZoneGeographique = zoneAssocieZoneGeographique; }
	public void setTypeObjetGeograhphique(Integer typeObjetGeograhphique)           { this.typeObjetGeograhphique = typeObjetGeograhphique;           }
	public void setIdObjetGeographique(Long idObjetGeographique)                    { this.idObjetGeographique = idObjetGeographique;                 }
	public void setGroupName(String groupName)                                      { this.groupName = groupName;                                     }
	public void setNiveauReel(Integer niveauReel)                                   { this.niveauReel = niveauReel;                                   }
	public void setContainsOffer(boolean containsOffer)                             { this.containsOffer = containsOffer;                             }
	
	
	// Utilitaires
	//----------------------------------------------------------
	
	public void incrementPoids() { 
		this.poids++; 
	}

	
	// Override Object's methods
	//----------------------------------------------------------
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("ZoneTouristiqueNode[id=").append(getId());
		str.append(",nom=").append(nom);
		str.append(",niveau=").append(getNiveau());
		str.append(",visible=").append(visible);
		str.append(",uri=").append(uriData);
		str.append(",poids=").append(poids);
		str.append(",parent=").append(parent != null ? parent.getId() : null);
		str.append(",active=").append(active);
		str.append(",toPurge=").append(toPurge);
		str.append(",children=").append(children != null ? children.size() : 0);
		str.append("]\n");
		return str.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ZoneTouristiqueNode)) {
			return false;
		}
		ZoneTouristiqueNode nodeToCompare = (ZoneTouristiqueNode) obj;
		boolean toReturn = false;
		if (! StringUtils.isBlank(nom)) {
			toReturn = toReturn || this.nom.equals(nodeToCompare.nom);
		}
		if (generatedId != null) {
			toReturn = toReturn || this.generatedId.equals(nodeToCompare.generatedId);
		}
		return toReturn;
	}
	
	@Override
	public int hashCode() {
		return this.nom.hashCode();
	}
}
