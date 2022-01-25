/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.anagrafe.service;

import org.springframework.ui.Model;

import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici.ComponentiNucleoFamiliare;
import it.osapulie.shared.service.UserPreferences;

/**
 * Servizi comuni alle portlet.
 *
 * @author Gianluca Pindinelli
 *
 */
public interface AnagrafeCommonService {

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
	 * Aggiunge al {@link Model} le informazioni aggiuntive riguardanti un componente del nucleo
	 * familiare.
	 *
	 * @param model
	 * @param componente
	 */
	void addInfoAggiuntiveComponenteToModel(Model model, ComponentiNucleoFamiliare componente);

}
