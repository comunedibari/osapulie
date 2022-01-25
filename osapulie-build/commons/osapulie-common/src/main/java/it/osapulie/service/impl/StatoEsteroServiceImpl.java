/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.osapulie.domain.StatoEstero;
import it.osapulie.persistence.StatoEsteroCustomRepository;
import it.osapulie.persistence.StatoEsteroRepository;
import it.osapulie.service.ServiceLayerException;
import it.osapulie.service.StatoEsteroService;

/**
 * Implementazione del service per la gestione deggli oggetti {@link StatoEstero}.
 *
 * @author Gianluca Pindinelli
 *
 */
@Service("statoEsteroService")
@Transactional
public class StatoEsteroServiceImpl implements StatoEsteroService {

	@Inject
	private StatoEsteroRepository statoEsteroRepository;

	@Inject
	private StatoEsteroCustomRepository statoEsteroCustomRepository;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.StatoEsteroService#getAllStatiAndEscludi(java.lang.Integer)
	 */
	@Override
	public List<StatoEstero> getAllStatiAndEscludi(Integer codiceStato) throws ServiceLayerException {

		Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "denominazione"));

		List<StatoEstero> statiEsteri = statoEsteroRepository.findByCodiceStatoNot(sort, codiceStato);

		return statiEsteri;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.StatoEsteroService#getAllStati()
	 */
	@Override
	public List<StatoEstero> getAllStati() throws ServiceLayerException {

		Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "denominazione"));

		List<StatoEstero> statiEsteri = (List<StatoEstero>) statoEsteroRepository.findAll(sort);

		return statiEsteri;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.StatoEsteroService#getStatoEsteroByCodiceStato(java.lang.Integer)
	 */
	@Override
	public StatoEstero getStatoEsteroByCodiceStato(Integer codiceStato) throws ServiceLayerException {

		StatoEstero statoEstero = statoEsteroRepository.findByCodiceStato(codiceStato);
		return statoEstero;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.StatoEsteroService#searchStati(java.lang.String)
	 */
	@Override
	public List<StatoEstero> searchStati(String denominazione) throws ServiceLayerException {
		return statoEsteroCustomRepository.findByDenominazione(denominazione);
	}

}
