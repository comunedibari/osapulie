/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.osapulie.domain.Comune;
import it.osapulie.persistence.ComuneCustomRepository;
import it.osapulie.persistence.ComuneRepository;
import it.osapulie.service.ComuneService;
import it.osapulie.service.ServiceLayerException;

/**
 * Implementazione del service per la gestione deggli oggetti {@link Comune}.
 *
 * @author Gianluca Pindinelli
 *
 */
@Service("comuneService")
@Transactional
public class ComuneServiceImpl implements ComuneService {

	@Inject
	private ComuneRepository comuneRepository;

	@Inject
	private ComuneCustomRepository comuneCustomRepository;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.ComuneService#getAllComuni()
	 */
	@Override
	public List<Comune> getAllComuni() throws ServiceLayerException {

		Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "denominazione"));

		List<Comune> comuneList = (List<Comune>) comuneRepository.findAll(sort);

		// Eliminazione primo comune
		if (comuneList != null && comuneList.size() > 0) {
			if (comuneList.get(0).getId() < 0) {
				comuneList.remove(0);
			}
		}

		return comuneList;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.ComuneService#getComuneByCodiceISTAT(java.lang.String)
	 */
	@Override
	public Comune getComuneByCodiceISTAT(String codiceIstat) throws ServiceLayerException {
		return comuneRepository.findByCodiciIstat(codiceIstat);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.ComuneService#getComuneById(long)
	 */
	@Override
	public Comune getComuneById(long id) throws ServiceLayerException {
		return comuneRepository.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.ComuneService#getComuneByCodiceCatastale(java.lang.String)
	 */
	@Override
	public Comune getComuneByCodiceCatastale(String codiceCatastale) throws ServiceLayerException {
		return comuneRepository.findByCodiceCatastale(codiceCatastale);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.ComuneService#getComuniByProvincia(long)
	 */
	@Override
	public List<Comune> getComuniByProvincia(long idProvincia) throws ServiceLayerException {

		Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "denominazione"));

		List<Comune> comuneList = (List<Comune>) comuneRepository.findByIdProvincia(idProvincia, sort);

		return comuneList;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.ComuneService#searchComuni(java.lang.String)
	 */
	@Override
	public List<Comune> searchComuni(String denominazione) throws ServiceLayerException {

		List<Comune> comuneList = (List<Comune>) comuneCustomRepository.findByDenominazione(denominazione);

		return comuneList;
	}

}
