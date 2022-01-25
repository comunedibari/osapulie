/************************************************************************************
 * Copyright (c) 2011, 2016 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.service;

import it.osapulie.domain.Template;

/**
 * @author Gianluca Pindinelli
 *
 */
public interface TemplateService {

	/**
	 * Carica un {@link Template} relativo ad un dato ComuneISA e Servizio.
	 *
	 * @param idComuneISA
	 * @param idServizio
	 * @param tipo
	 * @return
	 * @throws ServiceLayerException
	 */
	Template getTemplate(Long idComuneISA, Long idServizio, String tipo) throws ServiceLayerException;

}
