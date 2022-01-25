/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.service;

import it.osapulie.domain.categoriaimmobile.TipologiaDetrazione;

import java.util.List;

/**
 * Service per gli oggetti {@link TipologiaDetrazione}.
 *
 * @author Gianluca Pindinelli
 *
 */
public interface TipologiaDetrazioneService {

	/**
	 * Carica una tipologia di detrazione immobile a partire dalla sua PK.
	 *
	 * @param id
	 * @return
	 * @throws ServiceLayerException
	 */
	public TipologiaDetrazione getTipologiaDetrazioneById(long id) throws ServiceLayerException;

	/**
	 * Carica la lista di tutte le tipologie di detrazione presenti nel sistema.
	 *
	 * @return
	 * @throws ServiceLayerException
	 */
	public List<TipologiaDetrazione> getAllTipologiaDetrazione() throws ServiceLayerException;

	/**
	 * Carica una tipologia di detrazione a partire dal suo codice identificativo.
	 *
	 * @param codice
	 * @return
	 * @throws ServiceLayerException
	 */
	public TipologiaDetrazione getTipologiaDetrazioneByCodice(String codice) throws ServiceLayerException;

}
