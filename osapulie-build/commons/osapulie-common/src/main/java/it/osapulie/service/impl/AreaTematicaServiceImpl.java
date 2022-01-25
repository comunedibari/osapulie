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

import it.osapulie.domain.servizi.AreaTematica;
import it.osapulie.persistence.AreaTematicaRepository;
import it.osapulie.service.AreaTematicaService;
import it.osapulie.service.ServiceLayerException;

/**
 * @author Gianluca Pindinelli
 *
 */
@Service("areaTematicaService")
@Transactional
public class AreaTematicaServiceImpl implements AreaTematicaService {

	@Inject
	private AreaTematicaRepository areaTematicaRepository;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.AreaTematicaService#getAreaTematicaById(long)
	 */
	@Override
	public AreaTematica getAreaTematicaById(long id) throws ServiceLayerException {
		return areaTematicaRepository.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.AreaTematicaService#getAllAreaTematica()
	 */
	@Override
	public List<AreaTematica> getAllAreaTematica() throws ServiceLayerException {

		Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "ordine"));

		List<AreaTematica> areaTematicaList = areaTematicaRepository.findAll(sort);

		return areaTematicaList;
	}
}
