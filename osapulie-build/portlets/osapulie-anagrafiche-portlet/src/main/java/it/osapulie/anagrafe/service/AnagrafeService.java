/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.anagrafe.service;

import it.osapulie.anagrafe.web.ws.input.types.DichiarazioneCambioResidenzaRichiesta;
import it.osapulie.anagrafe.web.ws.input.types.DichiarazioneCambioResidenzaRisposta;
import it.osapulie.anagrafe.web.ws.input.types.Tracciamento;
import it.osapulie.infrastructure.security.OSApulieUserDetails;
import it.osapulie.servizi.web.ws.types.StradarioAscotRichiesta;
import it.osapulie.servizi.web.ws.types.StradarioAscotRisposta;
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

	/**
	 * @param osApulieUserDetails
	 * @param userPreferences
	 * @return
	 */
	Tracciamento getTracciamento(OSApulieUserDetails osApulieUserDetails, UserPreferences userPreferences);

	/**
	 * Metodo che invoca la PDD per l'invio di una richiesta {@link StradarioAscotRichiesta}.
	 *
	 * @param richiesta
	 * @param userPreferences
	 * @return
	 */
	StradarioAscotRisposta inviaRichiestaStradarioAscot(StradarioAscotRichiesta richiesta, UserPreferences userPreferences);

	/**
	 * @param richiesta
	 * @return
	 */
	String getDichiarazioneXml(DichiarazioneCambioResidenzaRichiesta richiesta);

}
