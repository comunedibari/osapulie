/************************************************************************************
 * Copyright (c) 2011, 2014 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.tributi.service;

import it.osapulie.shared.service.UserPreferences;
import it.osapulie.tributi.web.ws.output.types.VisuraTassaRifiutiRichiesta;
import it.osapulie.tributi.web.ws.output.types.VisuraTassaRifiutiRisposta;

/**
 *
 * @author Gianluca Pindinelli
 */
public interface VisuraTassaRifiutiService {

	/**
	 * @param richiesta
	 * @return
	 */
	VisuraTassaRifiutiRisposta richiediDati(VisuraTassaRifiutiRichiesta richiesta, UserPreferences userPreferences);

}
