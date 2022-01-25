/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.service;

import it.osapulie.domain.categoriaimmobile.BaseDiCalcolo;

import java.util.List;

/**
 * Service per gli oggetti {@link BaseDiCalcolo}.
 *
 * @author Gianluca Pindinelli
 *
 */
public interface BaseDiCalcoloService {

	/**
	 * Carica un {@link BaseDiCalcolo} a partire dalla sua PK.
	 *
	 * @param id
	 * @return
	 * @throws ServiceLayerException
	 */
	public BaseDiCalcolo getBaseDiCalcoloById(long id) throws ServiceLayerException;

	/**
	 * Carica un {@link BaseDiCalcolo} a partire dal valore.
	 *
	 * @param valore
	 * @return
	 * @throws ServiceLayerException
	 */
	public BaseDiCalcolo getBaseDiCalcoloByValore(double valore) throws ServiceLayerException;

	/**
	 * Calica la lista di tutte le {@link BaseDiCalcolo} presenti nel sistema.
	 *
	 * @return
	 * @throws ServiceLayerException
	 */
	public List<BaseDiCalcolo> getAllBaseDiCalcolo() throws ServiceLayerException;

}
