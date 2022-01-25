/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.osapulie.domain.fascicoloutente.RichiestaServizio;
import it.osapulie.infrastructure.impl.DateUtils;
import it.osapulie.persistence.RichiestaServizioCustomRepository;
import it.osapulie.persistence.RichiestaServizioRepository;
import it.osapulie.service.RichiestaServizioService;
import it.osapulie.service.ServiceLayerException;

/**
 * @author Gianluca Pindinelli
 *
 */
@Service("richiestaServizioService")
@Transactional
public class RichiestaServizioServiceImpl implements RichiestaServizioService {

	@Autowired
	private RichiestaServizioCustomRepository richiestaServizioCustomRepository;

	@Autowired
	private RichiestaServizioRepository richiestaServizioRepository;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.RichiestaServizioService#searchRichiesteServizio(java.lang.Long,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.lang.Long,
	 * java.util.Date, java.util.Date)
	 */
	@Override
	public List<RichiestaServizio> searchRichiesteServizio(Long idComuneISA, String cognome, String nome, String codiceFiscale, Long idServizio, Long idAzienda, Date da, Date a)
			throws ServiceLayerException {

		// Set delle date agli estremi del giorno
		if (da != null) {
			da = DateUtils.getStartOfDay(da);
		}

		if (a != null) {
			a = DateUtils.getEndOfDay(a);
		}

		return richiestaServizioCustomRepository.findRichiestaServizio(idComuneISA, cognome, nome, codiceFiscale, idServizio, idAzienda, da, a);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.RichiestaServizioService#searchRichiesteServizio(java.lang.Long,
	 * java.lang.Long, java.util.Date, java.util.Date)
	 */
	@Override
	public List<RichiestaServizio> searchRichiesteServizio(Long idAzienda, Long idProfiloUtenteCittadino, Date da, Date a, Long idServizio) throws ServiceLayerException {
		// Set delle date agli estremi del giorno
		if (da != null) {
			da = DateUtils.getStartOfDay(da);
		}

		if (a != null) {
			a = DateUtils.getEndOfDay(a);
		}

		return richiestaServizioCustomRepository.findRichiestaServizio(idAzienda, idProfiloUtenteCittadino, da, a, idServizio);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.RichiestaServizioService#getRichiestaServizio(java.lang.Long)
	 */
	@Override
	public RichiestaServizio getRichiestaServizio(Long idRichiesta) throws ServiceLayerException {
		return richiestaServizioRepository.findOne(idRichiesta);
	}

	@Override
	public List<RichiestaServizio> searchRichiesteServizioByFilters(Long idComuneISA, String codiceFiscale,	Long tipologia, Long idAzienda, Date da, Date a) throws ServiceLayerException {
		return richiestaServizioCustomRepository.findRichiestaServizioByFilters(idComuneISA, codiceFiscale, tipologia, idAzienda, da, a);
	}

}
