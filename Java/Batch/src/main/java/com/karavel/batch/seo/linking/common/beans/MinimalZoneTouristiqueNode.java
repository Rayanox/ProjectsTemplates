package com.karavel.batch.seo.linking.common.beans;

public class MinimalZoneTouristiqueNode {
	
	private Long id;
	private Integer niveau;
	
	public MinimalZoneTouristiqueNode(){}
	
	public MinimalZoneTouristiqueNode(Long id) {
		this();
		this.id = id;
	}
	
	public MinimalZoneTouristiqueNode(Long id, Integer niveau) {
		this(id);
		this.niveau = niveau;
	}

	public Long getId() {
		return id;
	}

	public Integer getNiveau() {
		return niveau;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNiveau(Integer niveau) {
		this.niveau = niveau;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((niveau == null) ? 0 : niveau.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		MinimalZoneTouristiqueNode other = (MinimalZoneTouristiqueNode) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (niveau == null) {
			if (other.niveau != null)
				return false;
		} else if (!niveau.equals(other.niveau))
			return false;
		return true;
	}
	
}
