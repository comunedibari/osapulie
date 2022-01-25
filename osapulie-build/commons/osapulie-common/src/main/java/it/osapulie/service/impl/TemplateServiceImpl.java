/************************************************************************************
 * Copyright (c) 2011, 2016 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.osapulie.domain.Template;
import it.osapulie.persistence.TemplateRepository;
import it.osapulie.service.ServiceLayerException;
import it.osapulie.service.TemplateService;

/**
 * @author Gianluca Pindinelli
 *
 */
@Service("templateService")
@Transactional
public class TemplateServiceImpl implements TemplateService {

	@Inject
	private TemplateRepository templateRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.osapulie.service.TemplateService#getTemplate(java.lang.Long, java.lang.Long,
	 * java.lang.String)
	 */
	@Override
	public Template getTemplate(Long idComuneISA, Long idServizio, String tipo) throws ServiceLayerException {
		return templateRepository.findByComuneISAServizioAttivo(idComuneISA, idServizio, tipo);
	}

}
