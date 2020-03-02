package com.monitoring.parser;

import java.io.File;
import java.util.Date;
import java.util.List;

public class FrontParser extends Parser {

	@Override
	public List<Occurrence> parse(String logsFolder) {

		throw new UnsupportedOperationException();
	}

	@Override
	public String extractStackTrace(String error) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Date extractDate(String error) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void processLogsToMap(File fileName) throws Exception {
		throw new UnsupportedOperationException();

	}

	@Override
	public void processLine(String ligne) {
		throw new UnsupportedOperationException();

	}

	@Override
	public String getThreadKey(String line) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addErrorToMap(String threadkey, StringBuilder buffer) {
		throw new UnsupportedOperationException();

	}

	@Override
	public String extractFromLine(String error, String spliter, int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Occurrence> mapErrorsToEntities() {
		// TODO Auto-generated method stub
		return null;
	}

}
