/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 * 
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.tributi.service;

import it.osapulie.shared.service.UserPreferences;
import it.osapulie.tributi.web.ws.output.types.PagamentiServiziCimiterialiRichiesta;
import it.osapulie.tributi.web.ws.output.types.PagamentiServiziCimiterialiRisposta;

/**
 * 
 * @author Gianluca Pindinelli
 */
public interface ServiziCimiterialiService {

	/**
	 * @param richiesta
	 * @return
	 */
	PagamentiServiziCimiterialiRisposta richiediDatiServiziCimiteriali(PagamentiServiziCimiterialiRichiesta richiesta, UserPreferences userPreferences);

}
