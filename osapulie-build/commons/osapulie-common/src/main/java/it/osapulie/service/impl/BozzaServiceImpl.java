/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.osapulie.domain.Bozza;
import it.osapulie.persistence.BozzaRepository;
import it.osapulie.service.BozzaService;
import it.osapulie.service.ServiceLayerException;

/**
 * @author Gianluca Pindinelli
 *
 */
@Service("bozzaService")
@Transactional
public class BozzaServiceImpl implements BozzaService {

	@Autowired
	private BozzaRepository bozzaRepository;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.BozzaService#getBozzaByPk(long)
	 */
	@Override
	public Bozza getBozzaByPk(long pk) throws ServiceLayerException {
		return bozzaRepository.findOne(pk);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.BozzaService#getBozzaCittadinoByComuneAndServizio(long, long, long)
	 */
	@Override
	public Bozza getBozzaCittadinoByComuneAndServizio(long idProfiloUtenteCittadino, long idComuneISA, long idServizio) throws ServiceLayerException {
		return bozzaRepository.findByComuneISAServizioProfiloUtenteCittadino(idComuneISA, idServizio, idProfiloUtenteCittadino);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.BozzaService#getBozzaAziendaByComuneAndServizio(long, long, long)
	 */
	@Override
	public Bozza getBozzaAziendaByComuneAndServizio(long idAzienda, long idComuneISA, long idServizio) throws ServiceLayerException {
		return bozzaRepository.findByComuneISAServizioAzienda(idComuneISA, idServizio, idAzienda);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.BozzaService#saveBozza(it.osapulie.domain.Bozza)
	 */
	@Override
	public void saveBozza(Bozza bozza) throws ServiceLayerException {
		bozzaRepository.save(bozza);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.BozzaService#deleteBozza(long)
	 */
	@Override
	public void deleteBozza(long idBozza) throws ServiceLayerException {
		bozzaRepository.delete(idBozza);
	}

}
