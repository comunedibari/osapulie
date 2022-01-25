/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.service;

import it.osapulie.domain.Bozza;
import it.osapulie.domain.ProfiloUtenteCittadino;

/**
 * Service per la gestione dei {@link ProfiloUtenteCittadino}.
 *
 * @author Gianluca Pindinelli
 *
 */
public interface BozzaService {

	/**
	 * Ritorna la {@link Bozza} in base alla PK.
	 *
	 * @param pk
	 * @return
	 * @throws ServiceLayerException
	 */
	Bozza getBozzaByPk(long pk) throws ServiceLayerException;

	/**
	 * Ritorna la bozza del cittadino relativa al determinato servizio/comune.
	 *
	 * @param idProfiloUtenteCittadino
	 * @param idComuneISA
	 * @param idServizio
	 * @return
	 * @throws ServiceLayerException
	 */
	Bozza getBozzaCittadinoByComuneAndServizio(long idProfiloUtenteCittadino, long idComuneISA, long idServizio) throws ServiceLayerException;

	/**
	 * Ritorna la bozza dell'azienda relativa al determinato servizio/comune.
	 *
	 * @param idAzienda
	 * @param idComuneISA
	 * @param idServizio
	 * @return
	 * @throws ServiceLayerException
	 */
	Bozza getBozzaAziendaByComuneAndServizio(long idAzienda, long idComuneISA, long idServizio) throws ServiceLayerException;

	/**
	 * Salva una {@link Bozza} nel sistema.
	 *
	 * @param bozza
	 * @throws ServiceLayerException
	 */
	void saveBozza(Bozza bozza) throws ServiceLayerException;

	/**
	 * Elimina la bozza.
	 * 
	 * @param idBozza
	 * @throws ServiceLayerException
	 */
	void deleteBozza(long idBozza) throws ServiceLayerException;

}
