package com.monitoring.parser;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "occurrence")
public class Occurrence {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
		
	@Column
	public String jiraId;
	
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	public User user;
	
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "contrat_id")
	public Contrat contrat;
	
	@Column
	public String thread;
	
	@JoinColumn(name = "log_file_id")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public LogFile logFile;
	
	@JoinColumn(name = "trace_id")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public Trace trace;
	
	@Column(name="occured_at")
	public Date occuredAt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJiraId() {
		return jiraId;
	}

	public void setJiraId(String jiraId) {
		this.jiraId = jiraId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Contrat getContrat() {
		return contrat;
	}

	public void setContrat(Contrat contrat) {
		this.contrat = contrat;
	}

	public String getThread() {
		return thread;
	}

	public void setThread(String thread) {
		this.thread = thread;
	}

	public LogFile getLogFile() {
		return logFile;
	}

	public void setLogFile(LogFile logFile) {
		this.logFile = logFile;
	}

	public Trace getTrace() {
		return trace;
	}

	public void setTrace(Trace trace) {
		this.trace = trace;
	}

	public Date getOccuredAt() {
		return occuredAt;
	}

	public void setOccuredAt(Date occuredAt) {
		this.occuredAt = occuredAt;
	}

	@Override
	public String toString() {
		return "Occurrence [jiraId=" + jiraId + ", thread=" + thread + ", trace=" + trace + ", occuredAt=" + occuredAt
				+ "]";
	}



}
