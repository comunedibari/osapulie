/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.servizicomune.service;

import java.util.List;

import it.osapulie.domain.ComuneISA;
import it.osapulie.service.ServiceLayerException;
import it.osapulie.servizi.web.ws.types.RichiestaServiziAttivi;
import it.osapulie.servizi.web.ws.types.ServiziAttivi;
import it.osapulie.servizi.web.ws.types.Servizio;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.tributi.web.ws.output.types.CategorieImmobiliRichiesta;
import it.osapulie.tributi.web.ws.output.types.CategorieImmobiliRisposta;

/**
 *
 * @author Gianluca Pindinelli
 */
public interface ServiziAttiviService {

	/**
	 * Metodo che invoca la porta e restituisce l'elenco dei servizi erogati dal comune.
	 *
	 * @param richiesta
	 * @return ServiziAttivi
	 */

	ServiziAttivi richiediServiziAttivi(RichiestaServiziAttivi richiesta);

	/**
	 * Metodo che invoca la porta e restituisce l'elenco dei servizi erogati dal comune.
	 *
	 * @param richiesta
	 * @param userPreferences
	 * @return ServiziAttivi
	 */
	ServiziAttivi richiediServiziAttivi(RichiestaServiziAttivi richiesta, UserPreferences userPreferences);

	/**
	 * Metodo che invoca la porta e restituisce l'elenco dei servizi erogati dal comune.
	 *
	 * @param richiesta
	 * @param uriServizio
	 * @return
	 */
	ServiziAttivi richiediServiziAttivi(RichiestaServiziAttivi richiesta, String uriServizio);

	/**
	 * Metodo che invoca la porta e restituisce l'elenco delle categorie di immobili dal comune.
	 *
	 * @param richiesta
	 * @param uriServizio
	 * @return
	 */
	CategorieImmobiliRisposta richiediCategorieImmobili(CategorieImmobiliRichiesta richiesta, String uriServizio);

	/**
	 * Aggiorna nel sistema la tabella di relazione tra servizi e comuni.
	 *
	 * @param servizi
	 * @param comuneISA
	 */
	void updateServiziAttiviComuneISA(List<Servizio> servizi, ComuneISA comuneISA);

	/**
	 *
	 * @param comuneISA
	 * @return
	 * @throws ServiceLayerException
	 */
	List<Servizio> getServiziAttiviComuneISA(ComuneISA comuneISA) throws ServiceLayerException;

}
