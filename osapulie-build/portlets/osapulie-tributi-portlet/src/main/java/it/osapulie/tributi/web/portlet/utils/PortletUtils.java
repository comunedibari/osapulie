/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.tributi.web.portlet.utils;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderResponse;
import javax.portlet.WindowStateException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.portal.kernel.portlet.LiferayWindowState;

import it.osapulie.tributi.web.ws.output.types.Codifica;
import it.osapulie.tributi.web.ws.output.types.DatiTassaImmobili.Posizioni;
import it.osapulie.tributi.web.ws.output.types.Indirizzo;
import it.osapulie.tributi.web.ws.output.types.Indirizzo.Civico;

/**
 * Classe di utility per la portlet.
 *
 * @author Gianluca Pindinelli
 *
 */
public class PortletUtils extends it.osapulie.web.portlet.util.PortletUtils {

	protected static Logger log = LoggerFactory.getLogger(PortletUtils.class.getName());

	/**
	 * Setta in request ({@link PortletRequest}) i parametri necessari alla visualizzazione delle
	 * date per un oggetto <code>liferay-ui:date</code>.
	 *
	 * @param request
	 * @param date
	 * @param yearName
	 * @param monthName
	 * @param dayName
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

	/**
	 * Setta gli oggetti comuni in request (Tag & Categories).
	 *
	 * @param request
	 */
	public static void setCommonObjects(ActionRequest request) {
		// Settaggio campi comuni (Tags & Categories)
		String[] catS = request.getParameterValues("assetCategoryIds");
		String[] tagS = request.getParameterValues("assetTagNames");

		if (catS != null && catS.length > 0) {
			String catsString = catS[0];
			request.setAttribute("curCategoryIds", catsString);
		}
		if (tagS != null && tagS.length > 0) {
			String tagsString = tagS[0];
			request.setAttribute("currentTags", tagsString);
		}

	}

	/**
	 * Crea la Liferay Url ({@link PortletURL}) in funzione dell'id portlet inviato come parametro.
	 *
	 * @param renderResponse
	 * @param portletId
	 * @return
	 */
	public static String getPortletUrlForPopup(RenderResponse renderResponse, String portletId) {

		PortletURL portletURL = renderResponse.createRenderURL();
		try {
			portletURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		}
		catch (WindowStateException e) {
			log.error("getPortletUrlForPopup :: " + e.getMessage(), e);
		}

		String portletUrlString = portletURL.toString();
		String sub1 = portletUrlString.substring(portletUrlString.indexOf("p_p_id="));
		String sub2 = sub1.substring(0, sub1.indexOf("&"));
		portletUrlString = portletUrlString.replace(sub2, "p_p_id=" + portletId);

		return portletUrlString;

	}

	/**
	 *
	 * @param object
	 * @param position
	 * @return
	 */
	public static Object getObjectByMultiField(Object object, int position) {

		if (object != null) {

			Method[] methods = object.getClass().getMethods();
			for (Method method : methods) {
				if (method != null && method.getName().startsWith("get")) {
					Object invoke;
					try {
						invoke = method.invoke(object, (Object[]) null);
						if (invoke != null && method.getReturnType().isAssignableFrom(String.class)) {
							String value = String.valueOf(invoke);

							String[] valueStrings = value.split(",");

							boolean found = false;
							try {
								String string = valueStrings[position];
								if (!string.equals("")) {
									object.getClass().getMethod(method.getName().replaceAll("get", "set"), String.class).invoke(object, string);
									found = true;
								}
							}
							catch (Exception e) {
							}
							if (!found) {
								object.getClass().getMethod(method.getName().replaceAll("get", "set"), String.class).invoke(object, "");
							}
						}
					}
					catch (Exception e) {
						continue;
					}
				}
			}
		}
		return object;
	}

	public static String getValueByMultiFieldRequest(PortletRequest request, String fieldName, int position) {
		return request.getParameter(fieldName + position);
	}

	/**
	 * @param posizioni
	 * @return
	 */
	public static String getIndirizzoFromPosizione(Posizioni posizioni) {
		String indirizzo = "";
		Indirizzo indirizzoUtenza = posizioni.getIndirizzoUtenza();
		if (indirizzoUtenza != null) {
			Codifica via = indirizzoUtenza.getVia();
			if (via != null) {
				indirizzo = via.getDescrizione();
				Civico civico = indirizzoUtenza.getCivico();
				if (civico != null) {
					indirizzo += ", " + civico.getNumero();
					if (civico.getEsponente() != null) {
						indirizzo += "/" + civico.getEsponente();
					}
				}
				if (indirizzoUtenza.getLocalita() != null) {
					indirizzo += ", località " + indirizzoUtenza.getLocalita();
				}
				if (indirizzoUtenza.getCap() != null) {
					indirizzo += ", CAP: " + indirizzoUtenza.getCap();
				}
			}
		}
		return indirizzo;
	}

	/**
	 * @param posizioni
	 * @return
	 */
	public static String getIndirizzoFromPosizione(it.osapulie.tributi.web.ws.output.types.DatiTassaRifiuti.Posizioni posizioni) {
		String indirizzo = "";
		Indirizzo indirizzoUtenza = posizioni.getIndirizzoUtenza();
		if (indirizzoUtenza != null) {
			Codifica via = indirizzoUtenza.getVia();
			if (via != null) {
				indirizzo = via.getDescrizione();
				Civico civico = indirizzoUtenza.getCivico();
				if (civico != null) {
					indirizzo += ", " + civico.getNumero();
					if (civico.getEsponente() != null) {
						indirizzo += "/" + civico.getEsponente();
					}
				}
				if (indirizzoUtenza.getLocalita() != null) {
					indirizzo += ", località " + indirizzoUtenza.getLocalita();
				}
				if (indirizzoUtenza.getCap() != null) {
					indirizzo += ", CAP: " + indirizzoUtenza.getCap();
				}
			}
		}
		return indirizzo;
	}
}
