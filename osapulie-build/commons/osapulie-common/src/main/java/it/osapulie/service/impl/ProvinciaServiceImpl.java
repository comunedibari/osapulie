/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import it.osapulie.domain.Provincia;
import it.osapulie.persistence.ProvinciaRepository;
import it.osapulie.service.ProvinciaService;
import it.osapulie.service.ServiceLayerException;

/**
 * @author Gianluca Pindinelli
 *
 */
@Service("provinciaService")
public class ProvinciaServiceImpl implements ProvinciaService {

	@Inject
	private ProvinciaRepository provinciaRepository;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.ProvinciaService#getAllProvince()
	 */
	@Override
	public List<Provincia> getAllProvince() throws ServiceLayerException {

		Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "sigla"));

		return (List<Provincia>) provinciaRepository.findAll(sort);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.osapulie.service.ProvinciaService#getProvinciaBySigla(java.lang.String)
	 */
	@Override
	public Provincia getProvinciaBySigla(String sigla) throws ServiceLayerException {
		return provinciaRepository.findBySigla(sigla);
	}

}
