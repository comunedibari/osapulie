package it.osapulie.anagrafe.web.portlet.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.portlet.PortletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author Gianluca Pindinelli
 *
 */
public class PortletUtils extends it.osapulie.web.portlet.util.PortletUtils {

	/**
	 *
	 */
	private static final String CERTIFICATI_BOLLO = "bollo";
	/**
	 *
	 */
	private static final String CERTIFICATI_USO = "uso";
	protected static Logger log = LoggerFactory.getLogger(PortletUtils.class.getName());

	/**
	 * Setta in request ({@link PortletRequest}) i parametri necessari alla visualizzazione delle
	 * date per un oggetto <code>liferay-ui:date</code>.
	 *
	 * @param request
	 * @param date
	 * @param yearParam
	 * @param monthParam
	 * @param dayParam
	 * @throws Exception
	 */
	public static void setDateIntoLiferayInputDate(PortletRequest request, Date date, String dayParam, String monthParam, String yearParam) throws Exception {

		Calendar calendar = new GregorianCalendar();

		if (date == null) {
			date = new Date();
		}

		calendar.setTime(date);

		request.setAttribute(dayParam, calendar.get(Calendar.DAY_OF_MONTH));
		request.setAttribute(monthParam, calendar.get(Calendar.MONTH));
		request.setAttribute(yearParam, calendar.get(Calendar.YEAR));
	}

	/**
	 * Preleva la data di un oggetto <code>liferay-ui:date</code> dalla request (
	 * {@link PortletRequest}). Se i parametri non sono trovati in request il metodo ritorna la data
	 * odierna.
	 *
	 * @param request
	 * @param dayParam
	 * @param monthParam
	 * @param yearParam
	 * @return
	 */
	public static Date getDateFromLiferayInputDate(PortletRequest request, String dayParam, String monthParam, String yearParam) {

		Calendar calendar = new GregorianCalendar();
		try {
			calendar.set(Integer.parseInt(request.getParameter(yearParam)), Integer.parseInt(request.getParameter(monthParam)), Integer.parseInt(request.getParameter(dayParam)));
		}
		catch (Exception e) {
			calendar.setTime(new Date());
			calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
		}

		return calendar.getTime();
	}

	/**
	 * Preleva la data di un oggetto <code>liferay-ui:date</code> dalla request (
	 * {@link PortletRequest}). Se i parametri non sono trovati in request il metodo ritorna la data
	 * odierna.
	 *
	 * @param request
	 * @param elementPosition
	 * @param dayParam
	 * @param monthParam
	 * @param yearParam
	 * @return
	 */
	public static Date getDateFromLiferayInputDate(PortletRequest request, int elementPosition, String dayParam, String monthParam, String yearParam) {

		Calendar calendar = new GregorianCalendar();
		try {
			calendar.set(Integer.parseInt(request.getParameterValues(yearParam)[elementPosition]), Integer.parseInt(request.getParameterValues(monthParam)[elementPosition]),
					Integer.parseInt(request.getParameterValues(dayParam)[elementPosition]));
		}
		catch (Exception e) {
			calendar.setTime(new Date());
			calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
		}

		return calendar.getTime();
	}

	public static HashMap<String, String> getYearsMap() {

		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();

		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);

		for (int i = 1950; i < year + 1; i++) {
			map.put(String.valueOf(i), String.valueOf(i));
		}

		return map;
	}

	/**
	 * Controllo che il campo numero_bollo sia valorizzato se è selezionato il primo valore della
	 * selectbox.
	 *
	 * @param request
	 * @return
	 */
	public static boolean checkBolloCertificato(PortletRequest request) {

		// Recupero la modalita di utilizzo del certificato
		String usoCertificatoId = request.getParameter(CERTIFICATI_USO);
		int uso = Integer.parseInt(usoCertificatoId);

		// Recupero informazioni sul numero di marca da bollo
		String numeroBollo = request.getParameter(CERTIFICATI_BOLLO);

		// Controllo che il campo numero_bollo sia valorizzato se è selezionato il primo
		// valore della selectbox
		if (uso == 0 && (numeroBollo == null || numeroBollo.trim().equals(""))) {
			return false;
		}
		return true;
	}

	private static String caratteriUnita[] = { " ", "uno", "due", "tre", "quattro", "cinque", "sei", "sette", "otto", "nove", "dieci", "undici", "dodici", "tredici", "quattordici", "quindici",
			"sedici", "diciassette", "diciotto", "diciannove" };
	private static String caratteriDecine[] = { "", " ", "venti", "trenta", "quaranta", "cinquanta", "sessanta", "settanta", "ottanta", "novanta" };

	/*
	 * Metodo utilizzato in certificati di nascita, in cui giorno mese e anno devono essere stampati
	 * in forma letterale
	 */
	public static String toCharactersForm(int value) {

		String result = "";

		int migliaia = (value / 1000) % 1000;
		int centinaia = (value / 100) % 10;
		int decine = (value / 10) % 10;
		int unita = value % 10;

		// migliaia
		if (migliaia > 0) {
			if (migliaia == 1) {
				result += "mille";
			}
			else {
				result += caratteriUnita[migliaia] + "mila";
			}
		}

		// centinaia
		if (centinaia > 0) {
			if (centinaia == 1) {
				result += "cento";
			}
			else {
				result += caratteriUnita[centinaia] + "cento";
			}
		}

		// verifico se le ultime due cifre rappresentano un numero da 1 a 19
		if ((value % 100) > 0 && (value % 100) < 20) {
			// i numeri da uno a 19 devono essere presi dal vettore caratteriUnita[]
			result += caratteriUnita[(value % 100)];
		}

		if ((value % 100) > 0 && (value % 100) >= 20) {
			if (unita == 1) {
				// nel caso in cui abbiamo 21, 31, 41,...
				result += caratteriDecine[decine].substring(0, caratteriDecine[decine].length() - 1) + caratteriUnita[unita];
			}

			result += caratteriDecine[decine] + caratteriUnita[unita];
		}
		return result;
	}

}
