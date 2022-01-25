/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.service;

import it.osapulie.domain.categoriaimmobile.Tributo;

import java.util.List;

/**
 * Service per gli oggetti {@link Tributo}.
 *
 * @author Gianluca Pindinelli
 *
 */
public interface TributoService {

	/**
	 * Carica un tributo a partire dalla sua PK.
	 *
	 * @param id
	 * @return
	 * @throws ServiceLayerException
	 */
	Tributo getTributoById(long id) throws ServiceLayerException;

	/**
	 * Carica la lista di tutti i tributi presenti nel sistema.
	 *
	 * @return
	 * @throws ServiceLayerException
	 */
	List<Tributo> getAllTributo() throws ServiceLayerException;

}
