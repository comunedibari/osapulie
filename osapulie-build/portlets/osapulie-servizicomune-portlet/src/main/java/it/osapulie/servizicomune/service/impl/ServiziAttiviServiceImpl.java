/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.servizicomune.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.servizi.Servizio;
import it.osapulie.service.ComuneISAService;
import it.osapulie.service.ComuneISAServizioService;
import it.osapulie.service.ServizioService;
import it.osapulie.service.impl.AbstractServiceImpl;
import it.osapulie.servizi.web.ws.types.RichiestaServiziAttivi;
import it.osapulie.servizi.web.ws.types.ServiziAttivi;
import it.osapulie.servizicomune.service.ServiziAttiviService;
import it.osapulie.servizicomune.service.TipoServizio;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.tributi.web.ws.output.types.CategorieImmobiliRichiesta;
import it.osapulie.tributi.web.ws.output.types.CategorieImmobiliRisposta;

/**
 * Implementazione di {@link ServiziAttiviService}.
 *
 * @author Gianluca Pindinelli
 */
@Service("serviziAttiviService")
public class ServiziAttiviServiceImpl extends AbstractServiceImpl implements ServiziAttiviService {

	private final Logger log = LoggerFactory.getLogger(ServiziAttiviServiceImpl.class);

	@Inject
	private ComuneISAServizioService comuneISAServizioService;

	@Inject
	private ServizioService servizioService;

	@Inject
	private ComuneISAService comuneISAService;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.servizicomune.service.ServiziAttiviService#richiediServiziAttivi(it.osapulie.
	 * servizi.web.ws.types.RichiestaServiziAttivi)
	 */
	@Override
	public ServiziAttivi richiediServiziAttivi(RichiestaServiziAttivi richiesta) {

		log.debug("richiediServiziAttivi :: entering method");

		return esegui(TipoServizio.RICHIESTA_ELENCO_SERVIZI, richiesta, ServiziAttivi.class);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.servizicomune.service.ServiziAttiviService#richiediServiziAttivi(it.osapulie.
	 * servizi.web.ws.types.RichiestaServiziAttivi, it.osapulie.shared.service.UserPreferences)
	 */
	@Override
	public ServiziAttivi richiediServiziAttivi(RichiestaServiziAttivi richiesta, UserPreferences userPreferences) {

		log.debug("richiediServiziAttivi :: entering method");

		return esegui(TipoServizio.RICHIESTA_ELENCO_SERVIZI, richiesta, ServiziAttivi.class, userPreferences);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.servizicomune.service.ServiziAttiviService#richiediServiziAttivi(it.osapulie.
	 * servizi.web.ws.types.RichiestaServiziAttivi, java.lang.String)
	 */
	@Override
	public ServiziAttivi richiediServiziAttivi(RichiestaServiziAttivi richiesta, String uriServizio) {

		log.debug("richiediServiziAttivi :: entering method");

		return esegui(TipoServizio.RICHIESTA_ELENCO_SERVIZI, richiesta, ServiziAttivi.class, uriServizio);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.servizicomune.service.ServiziAttiviService#richiediCategorieImmobili(it.osapulie.
	 * tributi.web.ws.types.CategorieImmobiliRichiesta, java.lang.String)
	 */
	@Override
	public CategorieImmobiliRisposta richiediCategorieImmobili(CategorieImmobiliRichiesta richiesta, String uriServizio) {

		log.debug("richiediCategorieImmobili :: entering method");

		return esegui(TipoServizio.RICHIESTA_ELENCO_CATEGORIE_IMMOBILI, richiesta, CategorieImmobiliRisposta.class, uriServizio);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.servizicomune.service.ServiziAttiviService#getServiziAttiviComuneISA(it.osapulie.
	 * domain.ComuneISA)
	 */
	@Override
	public List<it.osapulie.servizi.web.ws.types.Servizio> getServiziAttiviComuneISA(ComuneISA comuneISA) {

		log.debug("getServiziAttiviComuneISA :: entering method");

		String uriServizioGateway = comuneISA.getUriServizioGateway();

		RichiestaServiziAttivi richiesta = new RichiestaServiziAttivi();
		ServiziAttivi serviziAttivi = richiediServiziAttivi(richiesta, uriServizioGateway);
		if (serviziAttivi != null) {
			List<it.osapulie.servizi.web.ws.types.Servizio> servizi = serviziAttivi.getServizio();
			if (servizi != null) {
				return servizi;
			}
		}

		return null;

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.servizicomune.service.ServiziAttiviService#updateServiziAttiviComuneISA(java.util
	 * .List, it.osapulie.domain.ComuneISA)
	 */
	@Override
	public void updateServiziAttiviComuneISA(List<it.osapulie.servizi.web.ws.types.Servizio> servizi, ComuneISA comuneISA) {

		Map<String, Servizio> map = new HashMap<String, Servizio>();
		List<Servizio> allServizio = servizioService.getAllServizio();
		for (Servizio servizio : allServizio) {
			map.put(servizio.getCodiceServizio(), servizio);
		}

		// Eliminazione servizi associati
		deleteComuneISAServiziRelationship(comuneISA);
		for (it.osapulie.servizi.web.ws.types.Servizio servizio2 : servizi) {
			if (map.containsKey(servizio2.getCodiceServizio())) {
				Servizio servizio = map.get(servizio2.getCodiceServizio());
				comuneISA.addServizio(servizio, servizio2.isAutenticazioneForte(), servizio2.getLivelloAutenticazione(), servizio2.isAttivo());
			}
		}
		comuneISAService.saveComuneISA(comuneISA);

	}

	/**
	 * @param comuneISA
	 */
	private void deleteComuneISAServiziRelationship(ComuneISA comuneISA) {
		comuneISAServizioService.deleteComuneISAServizio(comuneISA.getId(), 0);
	}

}
