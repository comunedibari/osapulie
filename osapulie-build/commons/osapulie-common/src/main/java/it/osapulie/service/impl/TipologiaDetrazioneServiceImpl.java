/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.service.impl;

import it.osapulie.domain.categoriaimmobile.TipologiaDetrazione;
import it.osapulie.persistence.TipologiaDetrazioneRepository;
import it.osapulie.service.ServiceLayerException;
import it.osapulie.service.TipologiaDetrazioneService;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gianluca Pindinelli
 *
 */
@Service("tipologiaDetrazioneService")
@Transactional
public class TipologiaDetrazioneServiceImpl implements TipologiaDetrazioneService {

	@Inject
	private TipologiaDetrazioneRepository tipologiaDetrazioneRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.osapulie.service.TipologiaDetrazioneService#getTipologiaDetrazioneById(long)
	 */
	@Override
	public TipologiaDetrazione getTipologiaDetrazioneById(long id) throws ServiceLayerException {
		return tipologiaDetrazioneRepository.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.osapulie.service.TipologiaDetrazioneService#getAllTipologiaDetrazione()
	 */
	@Override
	public List<TipologiaDetrazione> getAllTipologiaDetrazione() throws ServiceLayerException {
		return tipologiaDetrazioneRepository.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.osapulie.service.TipologiaDetrazioneService#getTipologiaDetrazioneByCodice(java.lang.String
	 * )
	 */
	@Override
	public TipologiaDetrazione getTipologiaDetrazioneByCodice(String codice) throws ServiceLayerException {
		return tipologiaDetrazioneRepository.findByCodice(codice);
	}

}
