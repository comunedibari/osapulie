/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.service.impl;

import it.osapulie.domain.categoriaimmobile.TipologiaCategoriaImmobile;
import it.osapulie.persistence.TipologiaCategoriaImmobileRepository;
import it.osapulie.service.ServiceLayerException;
import it.osapulie.service.TipologiaCategoriaImmobileService;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gianluca Pindinelli
 *
 */
@Service("tipologiaCategoriaImmobileService")
@Transactional
public class TipologiaCategoriaImmobileServiceImpl implements TipologiaCategoriaImmobileService {

	@Inject
	private TipologiaCategoriaImmobileRepository tipologiaCategoriaImmobileRepository;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.service.TipologiaCategoriaImmobileService#getTipologiaCategoriaImmobileById(long)
	 */
	@Override
	public TipologiaCategoriaImmobile getTipologiaCategoriaImmobileById(long id) throws ServiceLayerException {
		return tipologiaCategoriaImmobileRepository.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.TipologiaCategoriaImmobileService#getAllTipologiaCategoriaImmobile()
	 */
	@Override
	public List<TipologiaCategoriaImmobile> getAllTipologiaCategoriaImmobile() throws ServiceLayerException {

		Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "descrizione"));

		List<TipologiaCategoriaImmobile> tipologiaList = tipologiaCategoriaImmobileRepository.findAll(sort);

		return tipologiaList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.osapulie.service.TipologiaCategoriaImmobileService#getTipologiaCategoriaImmobileByCodice
	 * (java.lang.String)
	 */
	@Override
	public TipologiaCategoriaImmobile getTipologiaCategoriaImmobileByCodice(String codice) throws ServiceLayerException {
		return tipologiaCategoriaImmobileRepository.findByCodice(codice);
	}
}
