/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.service;

import java.util.List;

import it.osapulie.domain.Comune;

/**
 * @author Gianluca Pindinelli
 *
 */
public interface ComuneService {

	/**
	 * Ritorna la lista di tutti i comuni presenti nel sistema.
	 *
	 * @return
	 * @throws ServiceLayerException
	 */
	List<Comune> getAllComuni() throws ServiceLayerException;

	/**
	 * Ritorna il {@link Comune} a partire dal suo codice ISTAT.
	 *
	 * @return
	 * @throws ServiceLayerException
	 */
	Comune getComuneByCodiceISTAT(String codiceIstat) throws ServiceLayerException;

	/**
	 * Ritorna il {@link Comune} a partire dalla suo PK.
	 *
	 * @param id
	 * @return
	 * @throws ServiceLayerException
	 */
	Comune getComuneById(long id) throws ServiceLayerException;

	/**
	 * Ritorna il {@link Comune} a partire dalla suo codice catastale.
	 *
	 * @param codiceCatastale
	 * @return
	 * @throws ServiceLayerException
	 */
	Comune getComuneByCodiceCatastale(String codiceCatastale) throws ServiceLayerException;

	/**
	 * Ritorna la lista dei comuni in base alla provincia di appartenenza.
	 *
	 * @param idProvincia
	 * @return
	 * @throws ServiceLayerException
	 */
	List<Comune> getComuniByProvincia(long idProvincia) throws ServiceLayerException;

	/**
	 * Ricerca i comuni in base alla denominazione.
	 *
	 * @return
	 * @throws ServiceLayerException
	 */
	List<Comune> searchComuni(String denominazione) throws ServiceLayerException;

}
