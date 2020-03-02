package com.monitoring.parser;

import java.util.Date;
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
@Table(name = "log_file")
public class LogFile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;

	@Column
	public String name;

	@Column(name = "parsed_at")
	public Date parsedAt;

	@Column
	public String type;

	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "logFile", fetch = FetchType.LAZY)
	private List<Occurrence> occurrences;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getParsedAt() {
		return parsedAt;
	}

	public void setParsedAt(Date parsedAt) {
		this.parsedAt = parsedAt;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Occurrence> getOccurrences() {
		return occurrences;
	}

	public void setOccurrences(List<Occurrence> occurrences) {
		this.occurrences = occurrences;
	}

	@Override
	public String toString() {
		return "LogFile [name=" + name + ", parsedAt=" + parsedAt + ", type=" + type + "]";
	}

	

}
