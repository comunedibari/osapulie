/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 * 
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.tributi.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.osapulie.service.impl.AbstractServiceImpl;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.tributi.service.ServiziCimiterialiService;
import it.osapulie.tributi.service.ServiziTributi;
import it.osapulie.tributi.web.ws.output.types.PagamentiServiziCimiterialiRichiesta;
import it.osapulie.tributi.web.ws.output.types.PagamentiServiziCimiterialiRisposta;

/**
 * Implementazione di {@link ServiziCimiterialiService}.
 * 
 * @author Gianluca Pindinelli
 */
@Service("serviziCimiterialiService")
@Transactional
public class ServiziCimiterialiServiceImpl extends AbstractServiceImpl implements ServiziCimiterialiService {

	@Override
	public PagamentiServiziCimiterialiRisposta richiediDatiServiziCimiteriali(PagamentiServiziCimiterialiRichiesta richiesta, UserPreferences userPreferences) {

		return esegui(ServiziTributi.RICHIESTA_DATI_SERVIZI_CIMITERIALI, richiesta, PagamentiServiziCimiterialiRisposta.class, userPreferences);

	}

}
