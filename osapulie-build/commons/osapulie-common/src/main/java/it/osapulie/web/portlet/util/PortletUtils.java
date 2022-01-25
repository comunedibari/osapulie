/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.web.portlet.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Gianluca Pindinelli
 *
 */
public class PortletUtils {

	/**
	 * Controlla che la PIVA sia nel formato corretto.
	 *
	 * @param codiceFiscale
	 * @return
	 */
	public static String checkCodiceFiscale(String codiceFiscale) {
		
		//controllo per codice fiscale immigrati
		if (codiceFiscale.length() == 11) {
			try {
				Long.parseLong(codiceFiscale);
			}
			catch (NumberFormatException e) {
				return "Il codice fiscale numerico deve essere composto esattamente da 11 numeri.";
			}
			return null;
		}
		
		int i, s, c;
		String cf2;
		int setdisp[] = { 1, 0, 5, 7, 9, 13, 15, 17, 19, 21, 2, 4, 18, 20, 11, 3, 6, 8, 12, 14, 16, 10, 22, 25, 24, 23 };
		if (codiceFiscale.length() != 16) {
			return "Il codice fiscale deve essere lungo esattamente 16 caratteri.";
		}
		cf2 = codiceFiscale.toUpperCase();
		for (i = 0; i < 16; i++) {
			c = cf2.charAt(i);
			if (!(c >= '0' && c <= '9' || c >= 'A' && c <= 'Z')) {
				return "Il codice fiscale contiene dei caratteri non validi: i soli caratteri validi sono le lettere e le cifre.";
			}
		}
		s = 0;
		for (i = 1; i <= 13; i += 2) {
			c = cf2.charAt(i);
			if (c >= '0' && c <= '9') {
				s = s + c - '0';
			}
			else {
				s = s + c - 'A';
			}
		}
		for (i = 0; i <= 14; i += 2) {
			c = cf2.charAt(i);
			if (c >= '0' && c <= '9') {
				c = c - '0' + 'A';
			}
			s = s + setdisp[c - 'A'];
		}
		if (s % 26 + 'A' != cf2.charAt(15)) {
			return "Il codice di controllo non corrisponde.";
		}
		return null;
	}

	/**
	 * Controlla che la PIVA sia nel formato corretto.
	 *
	 * @param piva
	 * @return
	 */
	public static String checkPartitaIVA(String piva) {
		int i, c, s;
		if (piva.length() != 11) {
			return "La partita IVA deve essere lunga esattamente 11 caratteri.";
		}
		for (i = 0; i < 11; i++) {
			if (piva.charAt(i) < '0' || piva.charAt(i) > '9') {
				return "La partita IVA dovrebbe contenere solo cifre.";
			}
		}
		s = 0;
		for (i = 0; i <= 9; i += 2) {
			s += piva.charAt(i) - '0';
		}
		for (i = 1; i <= 9; i += 2) {
			c = 2 * (piva.charAt(i) - '0');
			if (c > 9) {
				c = c - 9;
			}
			s += c;
		}
		if ((10 - s % 10) % 10 != piva.charAt(10) - '0') {
			return "Il codice di controllo non corrisponde.";
		}
		return null;
	}

	/**
	 * Controlla che la Patente sia nel formato corretto.
	 *
	 * @param piva
	 * @return
	 */
	public static String checkNumeroPatenteGuida(String numeroPatente) {
		if (numeroPatente.length() != 10) {
			return "Il numero patente deve essere lungo esattamente 10 caratteri.";
		}
		return null;
	}

	/**
	 * Ritorna il numero protocollo nel fomratp "YYYYN" (con padding a 7 cifre per il numero).
	 *
	 * @param numeroProtocollo
	 * @param dataProtocollo
	 * @return
	 */
	public static String getNumeroProtocollo(Long numeroProtocollo, Date dataProtocollo) {

		String numeroProtocolloResult = String.format("%07d", numeroProtocollo);
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(dataProtocollo);

		int annoProtocollo = calendar.get(Calendar.YEAR);

		numeroProtocolloResult = annoProtocollo + numeroProtocolloResult;

		return numeroProtocolloResult;
	}

	/**
	 *
	 * @param iban
	 * @return
	 */
	public static String checkIBAN(String iban) {

		if (iban == null || iban.length() != 27) {
			return "Il codice IBAN deve essere lungo esattamente 27 caratteri.";
		}

		Pattern p = Pattern.compile("IT[0-9]{2}[A-Z][0-9]{10}[A-Z0-9]{12}");
		Matcher m = p.matcher(iban);
		if (!m.find()) {
			return "Il codice IBAN non è nel formato corretto.";
		}

		return null;
	}

}
