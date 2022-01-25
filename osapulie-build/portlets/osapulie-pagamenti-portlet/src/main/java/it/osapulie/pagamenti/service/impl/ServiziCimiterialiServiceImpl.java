/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 * 
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.pagamenti.service.impl;

import it.osapulie.pagamenti.service.PosizioneTributariaService;
import it.osapulie.pagamenti.service.ServiziCimiterialiService;
import it.osapulie.pagamenti.service.TipoServizio;
import it.osapulie.service.impl.AbstractServiceImpl;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.tributi.web.ws.output.types.PagamentiServiziCimiterialiRichiesta;
import it.osapulie.tributi.web.ws.output.types.PagamentiServiziCimiterialiRisposta;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementazione di {@link PosizioneTributariaService}
 * 
 * @author Giovanni Barone
 */
@Service("serviziCimiterialiService")
@Transactional
public class ServiziCimiterialiServiceImpl extends AbstractServiceImpl implements ServiziCimiterialiService {

	public ServiziCimiterialiServiceImpl() {
		log.debug("ServiziCimiterialiServiceImpl()");
	}

	@Override
	public PagamentiServiziCimiterialiRisposta richiediDatiServiziCimiteriali(PagamentiServiziCimiterialiRichiesta richiesta, UserPreferences userPreferences) {

		displayAuthenticationDebugInfo();

		return esegui(TipoServizio.RICHIESTA_DATI_SERVIZI_CIMITERIALI, richiesta, PagamentiServiziCimiterialiRisposta.class, userPreferences);

	}

}
