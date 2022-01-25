/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 * 
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.pagamenti.service;

import it.osapulie.shared.service.UserPreferences;
import it.osapulie.tributi.web.ws.output.types.PagamentiOsapPermanenteRichiesta;
import it.osapulie.tributi.web.ws.output.types.PagamentiOsapPermanenteRisposta;
import it.osapulie.tributi.web.ws.output.types.PagamentiOsapTemporaneaRichiesta;
import it.osapulie.tributi.web.ws.output.types.PagamentiOsapTemporaneaRisposta;

/**
 * 
 * @author Mario Scalas, Giovanni Barone
 */
public interface VisureTosapCosapService {

	/**
	 * @param richiesta
	 * @return
	 */
	PagamentiOsapPermanenteRisposta richiediDatiTosapCosapPermanente(PagamentiOsapPermanenteRichiesta richiesta, UserPreferences userPreferences);

	PagamentiOsapTemporaneaRisposta richiediDatiTosapCosapTemporanea(PagamentiOsapTemporaneaRichiesta richiesta, UserPreferences userPreferences);
}
