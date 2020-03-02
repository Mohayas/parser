package com.monitoring.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class Parser {

	String ERROR = "ERROR -";
	String INFO = "INFO  -";
	String WARN = "WARN  -";
	String CAUSED_BY = "Caused by";

	Map<String, String> mapErreur = new LinkedHashMap<String, String>();

	StringBuilder buffer = new StringBuilder();
	String threadkey = "";
	Integer countError = 0;
	Integer countLine = 0;
	String precedenteLineEnError = "";

	public abstract List<Occurrence> mapErrorsToEntities() ;

	public abstract String extractStackTrace(String error);

	public abstract String extractFromLine(String error, String spliter, int position);

	public abstract Date extractDate(String error);

	public abstract void processLogsToMap(File fileName) throws Exception;

	public abstract void processLine(String ligne);

	public abstract String getThreadKey(String line);

	public abstract void addErrorToMap(String threadkey, StringBuilder buffer);


	public abstract List<Occurrence> parse(String logsFolder);

}
