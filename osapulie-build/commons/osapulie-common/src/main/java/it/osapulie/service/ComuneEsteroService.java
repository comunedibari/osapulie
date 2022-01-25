/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.service;

import java.util.List;

import it.osapulie.domain.ComuneEstero;

/**
 * @author Gianluca Pindinelli
 *
 */
public interface ComuneEsteroService {

	/**
	 * Ritorna la lista di tutti i comuni esteri presenti nel sistema.
	 *
	 * @return
	 * @throws ServiceLayerException
	 */
	public List<ComuneEstero> getAllComuni() throws ServiceLayerException;

	/**
	 * Ritorna il {@link ComuneEstero} a partire dal suo codice.
	 *
	 * @return
	 * @throws ServiceLayerException
	 */
	ComuneEstero getComuneEsteroByCodice(Integer codice) throws ServiceLayerException;

	/**
	 * Ricerca i comuni in base alla denominazione.
	 *
	 * @return
	 * @throws ServiceLayerException
	 */
	List<ComuneEstero> searchComuni(String denominazione) throws ServiceLayerException;
}
