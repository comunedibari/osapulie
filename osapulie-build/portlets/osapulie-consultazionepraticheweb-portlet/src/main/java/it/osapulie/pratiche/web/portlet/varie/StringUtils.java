package it.osapulie.pratiche.web.portlet.varie;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	private StringUtils() {
	}

	/**
	 * verifica se la stringa è formata solo da simboli alfabetici
	 * 
	 * @param string
	 * @return boolean
	 */
	public static boolean isAlfabeticString(String string) {
		boolean matchFound = false;
		String patternStr = "^[a-zA-Z]+$";
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(string.subSequence(0, string.length()));
		matchFound = matcher.find();
		System.out.println((new StringBuilder()).append("Controllore.isAlfabeticString: matchFound = ").append(matchFound).toString());
		return matchFound;
	}

	/**
	 * Verifica se la stringa è alfanumerica
	 * 
	 * @param string
	 * @return boolean
	 */
	public static boolean isAlfaNumericString(String string) {
		boolean matchFound = false;
		String patternStr = "^[a-zA-Z0-9 ]+$";
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(string.subSequence(0, string.length()));
		matchFound = matcher.find();
		System.out.println((new StringBuilder()).append("Controllore.isAlfaNumericString: matchFound = ").append(matchFound).toString());
		return matchFound;
	}

	/**
	 * Restituisce una stringa contentente solo simboli alfanumerici
	 * 
	 * @param string
	 * @return string
	 */
	public static String cleanString(String string) {
		String clean = "";
		if (string.length() > 0) {
			for (int i = 0; i < string.length(); i++) {
				if ((string.charAt(i) >= 'a' && string.charAt(i) <= 'z') || (string.charAt(i) >= 'A' && string.charAt(i) <= 'Z') || (string.charAt(i) >= '0' && string.charAt(i) <= '9')) {
					clean += string.charAt(i);
				}
				else if (string.charAt(i) == ' ') {
					clean += '_';
				}
			}
		}
		return clean;
	}

}
