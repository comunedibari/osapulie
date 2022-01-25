/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.service.impl;

import it.osapulie.domain.categoriaimmobile.BaseDiCalcolo;
import it.osapulie.persistence.BaseDiCalcoloRepository;
import it.osapulie.service.BaseDiCalcoloService;
import it.osapulie.service.ServiceLayerException;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gianluca Pindinelli
 *
 */
@Service("baseDiCalcoloService")
@Transactional
public class BaseDiCalcoloServiceImpl implements BaseDiCalcoloService {

	@Inject
	private BaseDiCalcoloRepository baseDiCalcoloRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.osapulie.service.BaseDiCalcoloService#getBaseDiCalcoloById(long)
	 */
	@Override
	public BaseDiCalcolo getBaseDiCalcoloById(long id) throws ServiceLayerException {
		return baseDiCalcoloRepository.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.osapulie.service.BaseDiCalcoloService#getAllBaseDiCalcolo()
	 */
	@Override
	public List<BaseDiCalcolo> getAllBaseDiCalcolo() throws ServiceLayerException {

		List<BaseDiCalcolo> baseDiCalcoloList = baseDiCalcoloRepository.findAll();

		return baseDiCalcoloList;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.BaseDiCalcoloService#getBaseDiCalcoloByValore(double)
	 */
	@Override
	public BaseDiCalcolo getBaseDiCalcoloByValore(double valore) throws ServiceLayerException {
		return baseDiCalcoloRepository.findByValore(valore);
	}

}
