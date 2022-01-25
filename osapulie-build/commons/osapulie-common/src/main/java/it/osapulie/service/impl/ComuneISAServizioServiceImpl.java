/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.osapulie.domain.ComuneISAServizio;
import it.osapulie.persistence.ComuneISAServizioCustomRepository;
import it.osapulie.service.ComuneISAServizioService;
import it.osapulie.service.ServiceLayerException;

/**
 * @author Gianluca Pindinelli
 *
 */
@Service("comuneISAServizioService")
@Transactional
public class ComuneISAServizioServiceImpl implements ComuneISAServizioService {

	@Autowired
	private ComuneISAServizioCustomRepository comuneISAServizioCustomRepository;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.ComuneISAServizioService#deleteComuneISAServizio(long, long)
	 */
	@Override
	public void deleteComuneISAServizio(long idComuneISA, long idServizio) throws ServiceLayerException {
		comuneISAServizioCustomRepository.deleteComuneISAServizio(idComuneISA, idServizio);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.ComuneISAServizioService#getComuneISAServizio(long, long)
	 */
	@Override
	public ComuneISAServizio getComuneISAServizio(long idComuneISA, long idServizio) throws ServiceLayerException {
		return comuneISAServizioCustomRepository.findByIdComuneISAIdServizio(idComuneISA, idServizio);
	}

}
