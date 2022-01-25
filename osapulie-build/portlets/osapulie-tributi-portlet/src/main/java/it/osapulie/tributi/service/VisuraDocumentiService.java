/************************************************************************************
 * Copyright (c) 2011, 2016 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.tributi.service;

import it.osapulie.documenti.web.ws.output.types.VisuraDocumentiRichiesta;
import it.osapulie.documenti.web.ws.output.types.VisuraDocumentiRisposta;
import it.osapulie.shared.service.UserPreferences;

/**
 *
 * @author Gianluca Pindinelli
 */
public interface VisuraDocumentiService {

	/**
	 * @param richiesta
	 * @return
	 */
	VisuraDocumentiRisposta richiediDatiVisura(VisuraDocumentiRichiesta richiesta, UserPreferences userPreferences);

}
