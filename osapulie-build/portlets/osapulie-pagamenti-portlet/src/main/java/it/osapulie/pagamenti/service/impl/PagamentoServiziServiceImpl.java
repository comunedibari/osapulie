/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.pagamenti.service.impl;

import it.osapulie.pagamenti.service.PagamentoServiziService;
import it.osapulie.pagamenti.service.TipoServizio;
import it.osapulie.pagamenti.service.VisureTosapCosapService;
import it.osapulie.service.impl.AbstractPagamentoServiceImpl;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.tributi.web.ws.output.types.DatiPagamentoServiziRichiesta;
import it.osapulie.tributi.web.ws.output.types.DatiPagamentoServiziRisposta;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementazione di {@link VisureTosapCosapService}
 *
 * @author Giovanni Barone
 */
@Service("pagamentoServiziService")
@Transactional
public class PagamentoServiziServiceImpl extends AbstractPagamentoServiceImpl implements PagamentoServiziService {

	public PagamentoServiziServiceImpl() {
		log.debug("PagamentoServiziServiceImpl()");
	}

	@Override
	public DatiPagamentoServiziRisposta pagamentoOsapPermanente(DatiPagamentoServiziRichiesta richiesta, UserPreferences userPreferences) {

		displayAuthenticationDebugInfo();

		return esegui(TipoServizio.PAGAMENTO_SERVIZIO, richiesta, DatiPagamentoServiziRisposta.class, userPreferences);
	}

	@Override
	public DatiPagamentoServiziRisposta pagamentoOsapTemporanea(DatiPagamentoServiziRichiesta richiesta, UserPreferences userPreferences) {

		displayAuthenticationDebugInfo();

		return esegui(TipoServizio.PAGAMENTO_SERVIZIO, richiesta, DatiPagamentoServiziRisposta.class, userPreferences);
	}

	@Override
	public DatiPagamentoServiziRisposta pagamento(DatiPagamentoServiziRichiesta richiesta, String uriServizioGateway) {

		displayAuthenticationDebugInfo();

		UserPreferences userPreferences = new UserPreferences();
		userPreferences.setUriServizioGateway(uriServizioGateway);
		return esegui(TipoServizio.PAGAMENTO_SERVIZIO, richiesta, DatiPagamentoServiziRisposta.class, userPreferences);
	}
}
