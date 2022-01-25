/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.pagamenti.service;

import it.osapulie.shared.service.UserPreferences;
import it.osapulie.tributi.web.ws.output.types.DatiPagamentoServiziRichiesta;
import it.osapulie.tributi.web.ws.output.types.DatiPagamentoServiziRisposta;

/**
 *
 * @author Giovanni Barone
 */
public interface PagamentoServiziService {

	/**
	 * @param richiesta
	 * @return
	 */
	DatiPagamentoServiziRisposta pagamentoOsapPermanente(DatiPagamentoServiziRichiesta richiesta, UserPreferences userPreferences);

	DatiPagamentoServiziRisposta pagamentoOsapTemporanea(DatiPagamentoServiziRichiesta richiesta, UserPreferences userPreferences);

	DatiPagamentoServiziRisposta pagamento(DatiPagamentoServiziRichiesta richiesta, String uriServizioGateway);

}
