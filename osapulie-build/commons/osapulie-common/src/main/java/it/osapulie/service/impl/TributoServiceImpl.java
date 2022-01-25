/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.service.impl;

import it.osapulie.domain.categoriaimmobile.Tributo;
import it.osapulie.persistence.TributoRepository;
import it.osapulie.service.ServiceLayerException;
import it.osapulie.service.TributoService;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gianluca Pindinelli
 *
 */
@Service("tributoService")
@Transactional
public class TributoServiceImpl implements TributoService {

	@Inject
	private TributoRepository tributoRepository;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.TipologiaService#getTipologiaById(long)
	 */
	@Override
	public Tributo getTributoById(long id) throws ServiceLayerException {
		return tributoRepository.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.osapulie.service.TipologiaService#getAllTipologia()
	 */
	@Override
	public List<Tributo> getAllTributo() throws ServiceLayerException {

		Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "nome"));

		List<Tributo> tipologiaList = tributoRepository.findAll(sort);

		return tipologiaList;
	}
}
