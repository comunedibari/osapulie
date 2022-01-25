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
import it.osapulie.tributi.service.VisuraTasiService;
import it.osapulie.tributi.web.ws.output.types.VisuraTasiRichiesta;
import it.osapulie.tributi.web.ws.output.types.VisuraTasiRisposta;

/**
 * Implementazione di {@link VisuraTasiService}
 *
 * @author Gianluca Pindinelli
 */
@Service("visuraTasiService")
@Transactional
public class VisuraTasiServiceImpl extends AbstractServiceImpl implements VisuraTasiService {

	public VisuraTasiServiceImpl() {
		log.debug("VisuraTasiServiceImpl()");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.osapulie.tributi.service.VisuraTasiService#richiediDatiVisuraTasi(it.osapulie.tributi.
	 * web.ws.types.VisuraTasiRichiesta, it.osapulie.shared.service.UserPreferences)
	 */
	@Override
	public VisuraTasiRisposta richiediDatiVisuraTasi(VisuraTasiRichiesta richiesta, UserPreferences userPreferences) {

		log.debug("richiediDatiVisuraTasi :: entering method");

		return esegui(ServiziTributi.VISURA_TASI, richiesta, VisuraTasiRisposta.class, userPreferences);
	}

}
