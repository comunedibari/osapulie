/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.service;

import it.osapulie.domain.categoriaimmobile.TipologiaCategoriaImmobile;

import java.util.List;

/**
 * Service per gli oggetti {@link TipologiaCategoriaImmobile}.
 *
 * @author Gianluca Pindinelli
 *
 */
public interface TipologiaCategoriaImmobileService {

	/**
	 * Carica una tipologia categoria immobile a partire dalla sua PK.
	 *
	 * @param id
	 * @return
	 * @throws ServiceLayerException
	 */
	public TipologiaCategoriaImmobile getTipologiaCategoriaImmobileById(long id) throws ServiceLayerException;

	/**
	 * Carica la lista di tutte le tipologie di categoria di immobili presenti nel sistema.
	 *
	 * @return
	 * @throws ServiceLayerException
	 */
	public List<TipologiaCategoriaImmobile> getAllTipologiaCategoriaImmobile() throws ServiceLayerException;

	/**
	 * Carica una tipologia categoria immobile a partire dal suo codice identificativo.
	 * 
	 * @param codice
	 * @return
	 * @throws ServiceLayerException
	 */
	public TipologiaCategoriaImmobile getTipologiaCategoriaImmobileByCodice(String codice) throws ServiceLayerException;

}
