/************************************************************************************
 * Copyright (c) 2011, 2014 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.tributi.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.osapulie.service.impl.AbstractServiceImpl;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.tributi.service.ServiziTributi;
import it.osapulie.tributi.service.VisuraTassaRifiutiService;
import it.osapulie.tributi.web.ws.output.types.VisuraTassaRifiutiRichiesta;
import it.osapulie.tributi.web.ws.output.types.VisuraTassaRifiutiRisposta;

/**
 * Implementazione di {@link VisuraTassaRifiutiService}
 *
 * @author Gianluca Pindinelli
 */
@Service("visuraTassaRifiutiService")
@Transactional
public class VisuraTassaRifiutiServiceImpl extends AbstractServiceImpl implements VisuraTassaRifiutiService {

	public VisuraTassaRifiutiServiceImpl() {
		log.debug("VisuraTasiServiceImpl()");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.osapulie.tributi.service.VisuraTassaRifiutiService#richiediDati(it.osapulie.tributi.web
	 * .ws.types.VisuraTassaRifiutiRichiesta, it.osapulie.shared.service.UserPreferences)
	 */
	@Override
	public VisuraTassaRifiutiRisposta richiediDati(VisuraTassaRifiutiRichiesta richiesta, UserPreferences userPreferences) {

		log.debug("richiediDati :: entering method");

		return esegui(ServiziTributi.VISURA_TASSA_RIFIUTI, richiesta, VisuraTassaRifiutiRisposta.class, userPreferences);
	}

}
