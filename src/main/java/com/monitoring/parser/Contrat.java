package com.monitoring.parser;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "contrat")
public class Contrat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	
	@Column
	private String contratId;
	
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "contrat", fetch = FetchType.LAZY)
	private List<Occurrence> occurrences;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Occurrence> getOccurrences() {
		return occurrences;
	}

	public void setOccurrences(List<Occurrence> occurrences) {
		this.occurrences = occurrences;
	}

	public String getContratId() {
		return contratId;
	}

	public void setContratId(String contratId) {
		this.contratId = contratId;
	}

	@Override
	public String toString() {
		return "Contrat [contratId=" + contratId + "]";
	}


}
