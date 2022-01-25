/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.service;

import java.util.List;

import it.osapulie.domain.ComuneISA;

/**
 * @author Gianluca Pindinelli
 *
 */
public interface ComuneISAService {

	/**
	 * Ritorna la lista di tutti i comuni presenti nel sistema.
	 *
	 * @return
	 * @throws ServiceLayerException
	 */
	public List<ComuneISA> getAllComuni() throws ServiceLayerException;

	/**
	 * Ritorna il comune a partire dal suo ID.
	 *
	 * @param pk
	 * @return
	 * @throws ServiceLayerException
	 */
	public ComuneISA getComuneByPk(long pk) throws ServiceLayerException;

	/**
	 * Ritorna il comune a partire dal suo codice ISTAT.
	 *
	 * @param codiceIstat
	 * @return
	 * @throws ServiceLayerException
	 */
	public ComuneISA getComuneByCodiceIstat(String codiceIstat) throws ServiceLayerException;

	/**
	 * Elimina il comune a partire dalla PK.
	 *
	 * @param pk
	 * @throws ServiceLayerException
	 */
	public void deleteComuneISA(long pk) throws ServiceLayerException;

	/**
	 * Salva un comune nel sistema.
	 *
	 * @param comuneISA
	 * @throws ServiceLayerException
	 */
	public void saveComuneISA(ComuneISA comuneISA) throws ServiceLayerException;

	/**
	 * Ritorna la lista di tutti i comuni attivi presenti nel sistema.
	 *
	 * @return
	 * @throws ServiceLayerException
	 */
	List<ComuneISA> getComuniAttivi() throws ServiceLayerException;

	/**
	 * Ritorna il comune a partire dal suo codice codiceIPA.
	 *
	 * @param codiceIPA
	 * @return
	 * @throws ServiceLayerException
	 */
	public ComuneISA getComuneByCodiceIPA(String codiceIPA) throws ServiceLayerException;

}
