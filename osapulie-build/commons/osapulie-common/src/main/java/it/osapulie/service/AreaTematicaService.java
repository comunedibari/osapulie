/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.service;

import java.util.List;

import it.osapulie.domain.servizi.AreaTematica;

/**
 * Service per gli oggetti {@link AreaTematica}.
 *
 * @author Gianluca Pindinelli
 *
 */
public interface AreaTematicaService {

	/**
	 * Carica un'area tematica servizio a partire dalla sua PK.
	 *
	 * @param id
	 * @return
	 * @throws ServiceLayerException
	 */
	public AreaTematica getAreaTematicaById(long id) throws ServiceLayerException;

	/**
	 * Calica la lista di tutte le aree tematiche presenti nel sistema.
	 *
	 * @return
	 * @throws ServiceLayerException
	 */
	public List<AreaTematica> getAllAreaTematica() throws ServiceLayerException;

}
