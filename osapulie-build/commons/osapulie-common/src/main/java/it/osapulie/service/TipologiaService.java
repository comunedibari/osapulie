/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.service;

import java.util.List;

import it.osapulie.domain.servizi.Tipologia;

/**
 * Service per gli oggetti {@link Tipologia}.
 *
 * @author Gianluca Pindinelli
 *
 */
public interface TipologiaService {

	/**
	 * Carica la tipologia servizio a partire dalla sua PK.
	 *
	 * @param id
	 * @return
	 * @throws ServiceLayerException
	 */
	public Tipologia getTipologiaById(long id) throws ServiceLayerException;

	/**
	 * Calica la lista di tutte le tipologie servizio presenti nel sistema.
	 *
	 * @return
	 * @throws ServiceLayerException
	 */
	public List<Tipologia> getAllTipologia() throws ServiceLayerException;

}
