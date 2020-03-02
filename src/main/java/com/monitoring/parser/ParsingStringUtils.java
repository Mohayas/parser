package com.monitoring.parser;

import java.security.MessageDigest;

public class ParsingStringUtils {

	public static String clean(String str) {

		str = str.replaceAll("#", " ");
		str = str.replaceAll("\\[", " ");
		str = str.replaceAll("]", " ");
		str = str.replaceAll("\\{", " ");
		str = str.replaceAll("}", " ");
		str = str.replaceAll("'", " ");
		str = str.replaceAll("!", " ");
		str = str.replaceAll("\"", " ");
		str = str.replaceAll("\\(", " ");
		str = str.replaceAll("\\)", " ");
		str = str.replaceAll(":", " ");
		str = str.replaceAll("-", " ");
		str = str.replaceAll("=", " ");
		str = str.replaceAll("[0-9]", " ");

		return str;
	}

	public static String hashString(String str) {
		try {
			byte[] bytesOfSubLine = ParsingStringUtils.clean(str).getBytes("UTF-8");

			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(bytesOfSubLine);

			byte[] md5 = null;
			md5 = md.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < md5.length; i++) {
				sb.append(Integer.toString((md5[i] & 0xff) + 0x100, 16).substring(1));
			}

			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	
}
