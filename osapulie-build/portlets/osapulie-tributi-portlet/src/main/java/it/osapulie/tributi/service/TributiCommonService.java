/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.tributi.service;

import javax.portlet.PortletRequest;

import it.osapulie.domain.Azienda;
import it.osapulie.shared.service.UserPreferences;

/**
 * Servizi comuni alle portlet.
 *
 * @author Gianluca Pindinelli
 *
 */
public interface TributiCommonService {

	/**
	 * Invia la mail di conferma all'utente cittadino.
	 *
	 * @param userPreferences
	 * @param subject
	 * @param nomeServizio
	 * @param numeroProtocollo
	 * @throws Exception
	 */
	void sendMailToUser(UserPreferences userPreferences, String subject, String nomeServizio, String numeroProtocollo) throws Exception;

	/**
	 * Invia la mail di conferma all'azienda.
	 *
	 * @param userPreferences
	 * @param azienda
	 * @param subject
	 * @param nomeServizio
	 * @param numeroProtocollo
	 * @throws Exception
	 */
	void sendMailToCompany(UserPreferences userPreferences, Azienda azienda, String subject, String nomeServizio, String numeroProtocollo) throws Exception;

	/**
	 * Genera la URL per accedere alla pagina contenente la portlet di dichiarazione cambio
	 * residenza.
	 *
	 * @param request
	 * @param message messaggio di testo da visualizzare sulla pagina di destinazione
	 * @return
	 */
	String getDichiarazioneCambioResidenzaUrl(PortletRequest request, String message);

}
