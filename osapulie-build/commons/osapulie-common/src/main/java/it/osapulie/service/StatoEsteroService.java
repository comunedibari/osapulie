/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.service;

import java.util.List;

import it.osapulie.domain.StatoEstero;

/**
 * @author Gianluca Pindinelli
 *
 */
public interface StatoEsteroService {

	/**
	 * Ritorna la lista di tutti gli stati presenti nel sistema escludendo uno stato in input.
	 *
	 * @return
	 * @throws ServiceLayerException
	 */
	List<StatoEstero> getAllStatiAndEscludi(Integer codiceStato) throws ServiceLayerException;

	/**
	 * Ritorna la lista di tutti gli stati esteri presenti nel sistema.
	 *
	 * @return
	 * @throws ServiceLayerException
	 */
	List<StatoEstero> getAllStati() throws ServiceLayerException;

	/**
	 * Effettua la ricerca degli stati in base alla denominazione.
	 * 
	 * @param denominazione
	 * @return
	 * @throws ServiceLayerException
	 */
	List<StatoEstero> searchStati(String denominazione) throws ServiceLayerException;

	/**
	 * Ritorna il {@link StatoEstero} a partire dal suo codice.
	 *
	 * @return
	 * @throws ServiceLayerException
	 */
	StatoEstero getStatoEsteroByCodiceStato(Integer codice) throws ServiceLayerException;
}
