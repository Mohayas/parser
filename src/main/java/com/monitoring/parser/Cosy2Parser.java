package com.monitoring.parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Cosy2Parser extends Parser {

	@Override
	public List<Occurrence> parse(String logsFolder) {
		{

			File file1 = new File(logsFolder);
			List<Occurrence> entities = null;

			System.out.println("Début Matching des logs ");

			if (file1.exists() && file1.isFile()) {
				try {
					processLogsToMap(file1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (file1.exists() && file1.isDirectory()) {
				for (File tmpFile : file1.listFiles()) {
					try {
						processLogsToMap(tmpFile);
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("Fin de parsing " + tmpFile.getName());
				}
			}

			System.out.println("Fin de parsing " + logsFolder);

			System.out.println("Nombre de nombre de lignes 	" + countLine);
			System.out.println("Nombre d erreur trouvée 	" + countError);
			System.out.println("errorsMap count	" + mapErreur.size());

			try {

				entities = mapErrorsToEntities();
				return entities;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return entities;
		}

	}

	@Override
	public String extractStackTrace(String error) {

		String t[] = error.split(System.lineSeparator());
		// System.out.println(error);
		if (t.length == 1)
			return "";

		StringBuilder traceBuilder = new StringBuilder();
		for (int i = 1; i < t.length - 1; i++) {
			traceBuilder.append(t[i]);
		}
		return ParsingStringUtils.clean(traceBuilder.toString());
	}

	@Override
	public Date extractDate(String error) {

		String dateStr = extractFromLine(error, " - ", 1);

		Date date = null;
		try {

			date = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse(dateStr);

		} catch (ParseException e) {
			System.out.println("extractin date from: " + error);
			e.printStackTrace();
		}
		return date;

	}

	@Override
	public String extractFromLine(String line, String spliter, int position) {
		try {

			String t[] = line.split(spliter);
			String msg = t[position].trim();

			return msg;
		} catch (Exception e) {
			System.out.println("fullError : " + line);
			System.out.println("spliter : " + spliter);
			System.out.println("line : " + line);
			System.out.println(
					"========================================================================================================================");
			return "";
		}

	}

	@Override
	public void processLogsToMap(File fileName) throws Exception {
		String line = null;

		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			while ((line = br.readLine()) != null) {
				processLine(line);
			}
		} catch (Exception e) {
			System.err.println("Error dans le traitement de la ligne " + line);
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * permet de parsser une ligne d'erreur, regroupe la stacktrace à une ligne
	 * 
	 * @param ligne
	 */

	@Override
	public void processLine(String ligne) {
		countLine++;
		if (ligne.startsWith(ERROR)) {
			countError++;
			if (buffer.toString().isEmpty()) {
				threadkey = getThreadKey(ligne);
				buffer.append(ligne);
			} else {
				threadkey = getThreadKey(ligne);
				String precedentThreadkey = getThreadKey(precedenteLineEnError);
				// meme thread local que le thread de la pr�c�dente erreur?
				if (!threadkey.isEmpty() && threadkey.equals(precedentThreadkey)) {
					buffer.append("\r\n").append(ligne);
					// on rencontre deja une erreur Avant
					addErrorToMap(threadkey, buffer);
					buffer = new StringBuilder();
				} else {
					// une nouvelle ligne d erreur, on stock la pr�c�dente dans la map
					addErrorToMap(threadkey, buffer);
					// on initialise le buffer des logs
					buffer = new StringBuilder();
					buffer.append(ligne);
				}
			}
			precedenteLineEnError = ligne;
		} else if (ligne.startsWith(INFO) || ligne.startsWith(WARN) || ligne.startsWith("DEBUG")
				|| ligne.startsWith("FATAL")) {
			// une ligne info apres une erreur?
			if (!buffer.toString().isEmpty()) {
				addErrorToMap(threadkey, buffer);
			}
		} else {
			// ni erreur ni Info , ni Warning, donc forcément une stackstrace d'une erreur
			buffer.append("\r\n" + ligne);
		}
	}

	/**
	 * permet de calculer un thread pour l'ajouter dans la map
	 * 
	 * @param line
	 * @return
	 */

	@Override
	public String getThreadKey(String line) {
		// [Thread-10]
		// [ajp-bio-8009-exec-1859]
		// deux formes
		String threadKey = "";
		String[] zones = line.split("]");

		if (zones.length >= 2) {
			String tmp = zones[0];
			String[] zones2 = tmp.split("\\[");
			threadKey = zones2[1];
			String tmp2 = zones[1];
			// String[] zones3 = tmp2.split(" - ");
			// threadKey = threadKey + zones3[1];
		}
		// System.out.println(threadKey);
		return threadKey;
	}

	/**
	 * permet d'ajouter une erreur dans la map selon la clef du thread Key
	 * 
	 * @param threadkey
	 * @param buffer
	 */

	@Override
	public void addErrorToMap(String threadkey, StringBuilder buffer) {
		// on rencontre deja une erreur Avant
		// if (mapErreur.containsKey(threadkey)) {
		// String oldError = mapErreur.get(threadkey);
		// String newError = buffer.toString();
		// if (!isDoublon(oldError, newError)) {
		// StringBuilder bufferSecondaire = new StringBuilder();
		// bufferSecondaire.append(oldError).append("\r\n").append(buffer.toString());
		//
		// mapErreur.put(threadkey, bufferSecondaire.toString());
		// }
		// } else {
		String fullError;
		String oldError = mapErreur.get(threadkey);
		String newError = buffer.toString();
		if (oldError != null)
			fullError = oldError + System.lineSeparator() + newError;
		else
			fullError = newError;
		mapErreur.remove(threadkey);
		mapErreur.put(threadkey, fullError);
		buffer = new StringBuilder();
	}

	/**
	 * permet de detecter les doublons dans les lignes d'erreurs
	 * 
	 * @param oldError
	 * @param newError
	 * @return
	 */

	public boolean isDoublon(String oldError, String newError) {

		String newThreadKey = getThreadKey(newError.split(System.lineSeparator())[0]);
		String oldThreadKey = getThreadKey(oldError.split(System.lineSeparator())[0]);

		if (newThreadKey.equals(oldThreadKey)) {
			System.out.println(true);
			return true;
		}
		System.out.println(false);
		return false;
	}

	public void writeToFile() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("/home/mohayas/Desktop/result.txt")));
			for (Map.Entry<String, String> currentError : mapErreur.entrySet()) {
				bw.write(currentError.getValue() + System.lineSeparator());
			}
			bw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public final List<Occurrence> mapErrorsToEntities() {
		writeToFile();
		ArrayList<Occurrence> entities = new ArrayList<Occurrence>();

		for (Map.Entry<String, String> currentError : mapErreur.entrySet()) {

			String fullError = currentError.getValue();
			if (fullError.trim().equals(""))
				continue;

			String firstLine = currentError.getValue().split(System.lineSeparator())[0];

			String msg = extractFromLine(firstLine, " - ", 4);
			String emitter = extractFromLine(firstLine, " - ", 3);

			String stackTrace = extractStackTrace(fullError);
			String hash = "";
			if (!stackTrace.trim().equals(""))
				hash = ParsingStringUtils.hashString(stackTrace);

			Date occuredAt = extractDate(firstLine);

			String thread = extractFromLine(firstLine, " - ", 2).trim();
			thread = thread.replace("[", "");
			thread = thread.replace("]", "");

			Trace trace = new Trace();
			trace.setEmitter(emitter);
			trace.setHash(hash);
			trace.setLevel("ERROR");
			trace.setMessage(msg);
			trace.setStackTrace(stackTrace);

			Occurrence occurrence = new Occurrence();

			User user = new User();
			user.setLogin("anonymous");
			occurrence.setUser(user);

			Contrat contrat = new Contrat();
			contrat.setContratId("contrat holder");
			occurrence.setContrat(contrat);

			LogFile logFile = new LogFile();
			logFile.setParsedAt(new Date());
			logFile.setName("apicil.log");
			logFile.setType("COSY2");
			occurrence.setLogFile(logFile);

			occurrence.setThread(thread);
			occurrence.setOccuredAt(occuredAt);

			occurrence.setTrace(trace);

			entities.add(occurrence);

		}
		return entities;

	}
}
