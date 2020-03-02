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
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "trace")
public class Trace {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
		
	@Column
	public String hash;
	
	@Column
	public String message;
	
	@Column
	public String level;
	
	@Column
	public String emitter;
	
	@Column
	@Lob
	public String stackTrace;
	
	
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "trace", fetch = FetchType.LAZY)
	private List<Occurrence> occurrences;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getEmitter() {
		return emitter;
	}

	public void setEmitter(String emitter) {
		this.emitter = emitter;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

	@Override
	public String toString() {
		return "Trace [hash=" + hash + ", message=" + message + ", level=" + level + ", emitter=" + emitter + "]";
	}

	

}
