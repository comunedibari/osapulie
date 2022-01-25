/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.pagamenti.service.impl;

import it.osapulie.pagamenti.service.PosizioneTributariaService;
import it.osapulie.pagamenti.service.TipoServizio;
import it.osapulie.service.impl.AbstractServiceImpl;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaPagamentoRichiesta;
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaRichiesta;
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaRisposta;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementazione di {@link PosizioneTributariaService}
 *
 * @author Giovanni Barone
 */
@Service("posizioneTributariaService")
@Transactional
public class PosizioneTributariaServiceImpl extends AbstractServiceImpl implements PosizioneTributariaService {

	public PosizioneTributariaServiceImpl() {
		log.debug("PosizioneTributariaServiceImpl()");
	}

	@Override
	public VisuraPosizioneTributariaRisposta richiediDatiVisuraPosizioneTributaria(VisuraPosizioneTributariaRichiesta richiesta, UserPreferences userPreferences) {
		log.debug("richiediDatiVisuraPosizioneTributaria :: entering method");
		return esegui(TipoServizio.RICHIESTA_DATI_POSIZIONE_TRIBUTARIA, richiesta, VisuraPosizioneTributariaRisposta.class, userPreferences);
	}

	@Override
	public VisuraPosizioneTributariaRisposta richiediDatiVisuraPosizioneTributaria(VisuraPosizioneTributariaRichiesta richiesta, String uriServizio) {
		log.debug("richiediDatiVisuraPosizioneTributaria :: entering method");
		return esegui(TipoServizio.RICHIESTA_DATI_POSIZIONE_TRIBUTARIA, richiesta, VisuraPosizioneTributariaRisposta.class, uriServizio);
	}

	@Override
	public VisuraPosizioneTributariaRisposta richiediDatiVisuraPosizioneTributaria(VisuraPosizioneTributariaPagamentoRichiesta richiesta, String uriServizio) {
		log.debug("richiediDatiVisuraPosizioneTributaria :: entering method");
		return esegui(TipoServizio.RICHIESTA_DATI_POSIZIONE_TRIBUTARIA_PAGAMENTO, richiesta, VisuraPosizioneTributariaRisposta.class, uriServizio);
	}
}
