/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.osapulie.domain.ComuneISA;
import it.osapulie.persistence.ComuneISARepository;
import it.osapulie.service.ComuneISAService;
import it.osapulie.service.ServiceLayerException;

/**
 * Implementazione del service per la gestione deggli oggetti {@link ComuneISA}.
 *
 * @author Gianluca Pindinelli
 *
 */
@Service("comuneISAService")
@Transactional
public class ComuneISAServiceImpl implements ComuneISAService {

	@Inject
	private ComuneISARepository comuneISARepository;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.ComuneISAService#getAllComuni()
	 */
	@Override
	public List<ComuneISA> getAllComuni() throws ServiceLayerException {

		List<ComuneISA> comuneList = (List<ComuneISA>) comuneISARepository.findAll();
		List<ComuneISA> resultList = null;
		if (comuneList != null && !comuneList.isEmpty()) {
			resultList = new ArrayList<ComuneISA>();
			for (ComuneISA comuneISA : comuneList) {
				if (comuneISA.getId() != -1) {
					resultList.add(comuneISA);
				}
			}
		}

		return resultList;
	}

	@Override
	public List<ComuneISA> getComuniAttivi() throws ServiceLayerException {

		List<ComuneISA> comuneList = comuneISARepository.findByAttivoOrderByFirstOrdineNomeAsc(true);
		List<ComuneISA> resultList = null;
		if (comuneList != null && !comuneList.isEmpty()) {
			resultList = new ArrayList<ComuneISA>();
			for (ComuneISA comuneISA : comuneList) {
				if (comuneISA.getId() != -1) {
					resultList.add(comuneISA);
				}
			}
		}

		return resultList;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.ComuneISAService#getComuneByCodiceIstat(java.lang.String)
	 */
	@Override
	public ComuneISA getComuneByCodiceIstat(String codiceIstat) throws ServiceLayerException {
		return comuneISARepository.findByCodiceIstat(codiceIstat);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.ComuneISAService#getComuneByPk(long)
	 */
	@Override
	public ComuneISA getComuneByPk(long pk) throws ServiceLayerException {
		return comuneISARepository.findOne(pk);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.ComuneISAService#deleteComuneISA(long)
	 */
	@Override
	public void deleteComuneISA(long pk) throws ServiceLayerException {
		comuneISARepository.delete(pk);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.ComuneISAService#saveComuneISA(it.osapulie.domain.ComuneISA)
	 */
	@Override
	public void saveComuneISA(ComuneISA comuneISA) throws ServiceLayerException {
		try {
			comuneISARepository.save(comuneISA);
		}
		catch (Exception e) {
			throw new ServiceLayerException("There was an error when saving an object of type ComuneISA :: " + e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.ComuneISAService#getComuneByCodiceIPA(java.lang.String)
	 */
	@Override
	public ComuneISA getComuneByCodiceIPA(String codiceIPA) throws ServiceLayerException {
		return comuneISARepository.findByAmministrazione(codiceIPA);
	}
	
}
