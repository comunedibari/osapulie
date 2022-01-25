/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.servizicomune.web.service;

import it.osapulie.anagrafe.web.ws.input.types.DichiarazioneCambioResidenzaRichiesta;
import it.osapulie.anagrafe.web.ws.input.types.DichiarazioneCambioResidenzaRisposta;
import it.osapulie.shared.service.UserPreferences;

/**
 * @author Gianluca Pindinelli
 *
 */
public interface AnagrafeService {

	/**
	 * Metodo che invoca la PDD per l'invio di una dichiarazione cambio residenza.
	 *
	 * @param richiesta
	 * @param userPreferences
	 * @return
	 */
	DichiarazioneCambioResidenzaRisposta inviaDichiarazione(DichiarazioneCambioResidenzaRichiesta richiesta, UserPreferences userPreferences);

}
