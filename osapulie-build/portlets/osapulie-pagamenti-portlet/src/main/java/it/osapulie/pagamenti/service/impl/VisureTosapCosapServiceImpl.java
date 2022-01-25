/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 * 
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.pagamenti.service.impl;

import it.osapulie.pagamenti.service.TipoServizio;
import it.osapulie.pagamenti.service.VisureTosapCosapService;
import it.osapulie.service.impl.AbstractServiceImpl;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.tributi.web.ws.output.types.PagamentiOsapPermanenteRichiesta;
import it.osapulie.tributi.web.ws.output.types.PagamentiOsapPermanenteRisposta;
import it.osapulie.tributi.web.ws.output.types.PagamentiOsapTemporaneaRichiesta;
import it.osapulie.tributi.web.ws.output.types.PagamentiOsapTemporaneaRisposta;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementazione di {@link VisureTosapCosapService}
 * 
 * @author Mario Scalas, Giovanni Barone
 */
@Service("visureTosapCosapService")
@Transactional
public class VisureTosapCosapServiceImpl extends AbstractServiceImpl implements VisureTosapCosapService {

	public VisureTosapCosapServiceImpl() {
		log.debug("VisureTosapCosapServiceImpl()");
	}

	@Override
	public PagamentiOsapPermanenteRisposta richiediDatiTosapCosapPermanente(PagamentiOsapPermanenteRichiesta richiesta, UserPreferences userPreferences) {

		displayAuthenticationDebugInfo();

		return esegui(TipoServizio.RICHIESTA_DATI_TOSAPCOSAP_PERMANENTE, richiesta, PagamentiOsapPermanenteRisposta.class, userPreferences);
	}

	@Override
	public PagamentiOsapTemporaneaRisposta richiediDatiTosapCosapTemporanea(PagamentiOsapTemporaneaRichiesta richiesta, UserPreferences userPreferences) {

		displayAuthenticationDebugInfo();

		return esegui(TipoServizio.RICHIESTA_DATI_TOSAPCOSAP_TEMPORANEA, richiesta, PagamentiOsapTemporaneaRisposta.class, userPreferences);
	}
}
