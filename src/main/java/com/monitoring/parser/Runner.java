package com.monitoring.parser;

import java.util.List;

public class Runner {

	public static void main(String[] args) {

		// Dossier ou fichier à superviser.
		// String cosy2LogsFolder = "/home/mohayas/Desktop/log-parser/logs/cosy2";
		// String cosy3LogsFolder = "/home/mohayas/Desktop/log-parser/logs/cosy3";
		// String batchLogsFolder = "/home/mohayas/Desktop/log-parser/logs/batch";
		//
		// Parser cosy2Parser = new Cosy2Parser();
		// Parser cosy3Parser = new Cosy3Parser();
		// Parser batchParser = new BatchParser();
		// List<Occurrence> cosy2Entities = cosy2Parser.parse(cosy2LogsFolder);
		// List<Occurrence> cosy3Entities = cosy3Parser.parse(cosy3LogsFolder);
		// List<Occurrence> batchEntities = batchParser.parse(batchLogsFolder);
		// List<Occurrence> entities = new ArrayList<Occurrence>();
		// entities.addAll(batchEntities);
		// entities.addAll(cosy3Entities);
		// entities.addAll(cosy2Entities);
		//
		// System.out.println("===========================================================");
		// System.out.println("Occurrences count : " + entities.size());
		// System.out.println("===========================================================");
		//
		// for (Occurrence occurrence : entities) {
		// // System.out.println(occurrence);
		// // bw.write(occurrence.getTrace().getStackTrace()+System.lineSeparator());
		//
		// }
		// try {
		// BufferedWriter bw = new BufferedWriter(new FileWriter(new
		// File(batchLogsFolder + "\\result.txt")));
		//
		//
		// bw.close();
		//
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// TraceDao.saveAll(entities);
		List<TraceOccurencesDTO> occurences = TraceOccurencesDAO.getAll();
		for (TraceOccurencesDTO traceOccurencesDTO : occurences) {
			System.out.print(traceOccurencesDTO.getHash() + " - ");
			System.out.print(traceOccurencesDTO.getOccurrences() + " - ");
			System.out.print(traceOccurencesDTO.getOccuredAt());
			System.out.println("===========================================================");
		}

	}

}
