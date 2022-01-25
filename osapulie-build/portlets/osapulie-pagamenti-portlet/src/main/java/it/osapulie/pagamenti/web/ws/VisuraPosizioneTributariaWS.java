/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pagamenti.web.ws;

import it.osapulie.pagamenti.web.ws.domain.VisuraPosizioneTributariaRichiesta;
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaRisposta;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * WS visura posizione tributaria.
 *
 * @author Gianluca Pindinelli
 *
 */
@WebService
public interface VisuraPosizioneTributariaWS {

	/**
	 * Ritorna la visura posizione tributaria in base alla richiesta passata in input.
	 *
	 * @param visuraPosizioneTributariaRichiesta
	 * @return
	 */
	public VisuraPosizioneTributariaRisposta getVisuraPosizioneTributaria(@WebParam(name = "visuraPosizioneTributariaRichiesta") VisuraPosizioneTributariaRichiesta visuraPosizioneTributariaRichiesta);
}
