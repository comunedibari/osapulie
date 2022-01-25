/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 * 
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.web.portlet.util;

import java.util.Random;

/**
 * Classe per la generazione delle password.
 * 
 * @author Gianluca Pindinelli
 * 
 */
public class PasswordGenerator {

	private static final int MIN_LENGTH = 5;
	private static final int MAX_LENGTH = 25;
	private static final String[] PARTS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz".split("");

	public static String generateRandomString(int length) {
		Random generator = new Random();
		if (length == 0) {
			length = MIN_LENGTH + generator.nextInt(MAX_LENGTH - MIN_LENGTH);
		}

		String s = "";
		for (int i = 0; i < length; i++) {
			s += PARTS[generator.nextInt(PARTS.length - 1) + 1];
		}
		return s;
	}
}
