/************************************************************************************
 * Copyright (c) 2011, 2016 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.tributi.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.osapulie.documenti.web.ws.output.types.VisuraDocumentiRichiesta;
import it.osapulie.documenti.web.ws.output.types.VisuraDocumentiRisposta;
import it.osapulie.service.impl.AbstractServiceImpl;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.tributi.service.ServiziTributi;
import it.osapulie.tributi.service.VisuraDocumentiService;

/**
 * Implementazione di {@link VisuraDocumentiService}
 *
 * @author Gianluca Pindinelli
 */
@Service("visuraDocumentiService")
@Transactional
public class VisuraDocumentiImpl extends AbstractServiceImpl implements VisuraDocumentiService {

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.tributi.service.VisuraProvvedimentiRimborsoTasiService#richiediDatiVisura(it.
	 * osapulie.documenti.web.ws.types.VisuraDocumentiRichiesta,
	 * it.osapulie.shared.service.UserPreferences)
	 */
	@Override
	public VisuraDocumentiRisposta richiediDatiVisura(VisuraDocumentiRichiesta richiesta, UserPreferences userPreferences) {

		log.debug("entering method");

		return esegui(ServiziTributi.VISURA_DOCUMENTI_TRIBUTI, richiesta, VisuraDocumentiRisposta.class, userPreferences);
	}

}
