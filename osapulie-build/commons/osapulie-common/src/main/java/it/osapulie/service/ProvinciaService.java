/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.service;

import java.util.List;

import it.osapulie.domain.Provincia;

/**
 * @author Gianluca Pindinelli
 *
 */
public interface ProvinciaService {

	/**
	 * Ritorna la lista di tutte le province presenti nel sistema.
	 *
	 * @return
	 * @throws ServiceLayerException
	 */
	List<Provincia> getAllProvince() throws ServiceLayerException;

	/**
	 * Ritorna la {@link Provincia} in base alla sigla.
	 *
	 * @return
	 * @throws ServiceLayerException
	 */
	Provincia getProvinciaBySigla(String sigla) throws ServiceLayerException;

}
