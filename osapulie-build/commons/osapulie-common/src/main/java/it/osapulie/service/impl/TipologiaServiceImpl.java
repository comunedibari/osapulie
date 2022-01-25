/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
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

import it.osapulie.domain.servizi.Tipologia;
import it.osapulie.persistence.TipologiaRepository;
import it.osapulie.service.ServiceLayerException;
import it.osapulie.service.TipologiaService;

/**
 * @author Gianluca Pindinelli
 *
 */
@Service("tipologiaService")
@Transactional
public class TipologiaServiceImpl implements TipologiaService {

	@Inject
	private TipologiaRepository tipologiaRepository;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.TipologiaService#getTipologiaById(long)
	 */
	@Override
	public Tipologia getTipologiaById(long id) throws ServiceLayerException {
		return tipologiaRepository.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.TipologiaService#getAllTipologia()
	 */
	@Override
	public List<Tipologia> getAllTipologia() throws ServiceLayerException {

		Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "nome"));

		List<Tipologia> tipologiaList = tipologiaRepository.findAll(sort);

		return tipologiaList;
	}
}
