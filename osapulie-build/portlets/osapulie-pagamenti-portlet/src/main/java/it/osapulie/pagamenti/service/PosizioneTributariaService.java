/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.pagamenti.service;

import it.osapulie.shared.service.UserPreferences;
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaPagamentoRichiesta;
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaRichiesta;
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaRisposta;

/**
 *
 * @author Giovanni Barone
 */
public interface PosizioneTributariaService {

	/**
	 * @param richiesta
	 * @return
	 */
	VisuraPosizioneTributariaRisposta richiediDatiVisuraPosizioneTributaria(VisuraPosizioneTributariaRichiesta richiesta, UserPreferences userPreferences);

	/**
	 * @param richiesta
	 * @param uriServizio
	 * @return
	 */
	VisuraPosizioneTributariaRisposta richiediDatiVisuraPosizioneTributaria(VisuraPosizioneTributariaRichiesta richiesta, String uriServizio);

	/**
	 * @param richiesta
	 * @param uriServizio
	 * @return
	 */
	VisuraPosizioneTributariaRisposta richiediDatiVisuraPosizioneTributaria(VisuraPosizioneTributariaPagamentoRichiesta richiesta, String uriServizio);

}
