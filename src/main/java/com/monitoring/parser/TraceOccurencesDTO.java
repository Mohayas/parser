package com.monitoring.parser;

import java.util.Date;

public class TraceOccurencesDTO {

	private String hash;
	private int occurrences;
	private Date occuredAt;
	private String stackTrace;

	public TraceOccurencesDTO() {
		super();
	}

	public TraceOccurencesDTO(String hash, int occurrences, Date occuredAt, String stackTrace) {
		super();
		this.hash = hash;
		this.occurrences = occurrences;
		this.occuredAt = occuredAt;
		this.stackTrace = stackTrace;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public int getOccurrences() {
		return occurrences;
	}

	public void setOccurrences(int occurrences) {
		this.occurrences = occurrences;
	}

	public Date getOccuredAt() {
		return occuredAt;
	}

	public void setOccuredAt(Date occuredAt) {
		this.occuredAt = occuredAt;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

}
