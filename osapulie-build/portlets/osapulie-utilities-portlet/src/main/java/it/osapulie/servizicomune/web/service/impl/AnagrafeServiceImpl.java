/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.servizicomune.web.service.impl;

import org.springframework.stereotype.Service;

import it.osapulie.anagrafe.web.ws.input.types.DichiarazioneCambioResidenzaRichiesta;
import it.osapulie.anagrafe.web.ws.input.types.DichiarazioneCambioResidenzaRisposta;
import it.osapulie.service.impl.AbstractServiceImpl;
import it.osapulie.servizicomune.web.service.AnagrafeService;
import it.osapulie.shared.service.UserPreferences;

/**
 * @author Gianluca Pindinelli
 *
 */
@Service("anagrafeService")
public class AnagrafeServiceImpl extends AbstractServiceImpl implements AnagrafeService {

	private static final int RECEIVE_TIMEOUT = 270000;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.anagrafe.service.AnagrafeService#inviaDichiarazione(it.osapulie.anagrafe.web.ws.
	 * input.types.DichiarazioneCambioResidenzaRichiesta,
	 * it.osapulie.shared.service.UserPreferences)
	 */
	@Override
	public DichiarazioneCambioResidenzaRisposta inviaDichiarazione(DichiarazioneCambioResidenzaRichiesta richiesta, UserPreferences userPreferences) {
		log.debug("inviaDichiarazione :: entering method");
		return esegui("dichiarazioneCambioResidenzaTEST", richiesta, DichiarazioneCambioResidenzaRisposta.class, userPreferences, RECEIVE_TIMEOUT);
	}

}
