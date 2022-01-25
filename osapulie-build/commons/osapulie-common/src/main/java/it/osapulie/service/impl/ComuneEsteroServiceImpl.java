/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.osapulie.domain.ComuneEstero;
import it.osapulie.persistence.ComuneEsteroCustomRepository;
import it.osapulie.persistence.ComuneEsteroRepository;
import it.osapulie.service.ComuneEsteroService;
import it.osapulie.service.ServiceLayerException;

/**
 * @author Gianluca Pindinelli
 *
 */
@Service("comuneEsteroService")
@Transactional
public class ComuneEsteroServiceImpl implements ComuneEsteroService {

	@Inject
	private ComuneEsteroRepository comuneEsteroRepository;

	@Inject
	private ComuneEsteroCustomRepository comuneEsteroCustomRepository;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.ComuneEsteroService#getAllComuni()
	 */
	@Override
	public List<ComuneEstero> getAllComuni() throws ServiceLayerException {
		return (List<ComuneEstero>) comuneEsteroRepository.findAll();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.ComuneEsteroService#getComuneEsteroByCodice(java.lang.Integer)
	 */
	@Override
	public ComuneEstero getComuneEsteroByCodice(Integer codice) throws ServiceLayerException {
		ComuneEstero comuneEstero = comuneEsteroRepository.findByCodice(codice);
		return comuneEstero;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.ComuneEsteroService#searchComuni(java.lang.String)
	 */
	@Override
	public List<ComuneEstero> searchComuni(String denominazione) throws ServiceLayerException {
		return comuneEsteroCustomRepository.findByDenominazione(denominazione);
	}

}
