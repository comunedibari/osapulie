/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.service;

import java.util.Date;
import java.util.List;

import it.osapulie.domain.fascicoloutente.RichiestaServizio;

/**
 * @author Gianluca Pindinelli
 *
 */

public interface RichiestaServizioService {

	/**
	 * Ricerca le richieste di servizio (fascicoli) a partire dai parametri passati in input.
	 *
	 * @param idComuneISA
	 * @param cognome
	 * @param nome
	 * @param codiceFiscale
	 * @param idServizio
	 * @param a
	 * @param da
	 * @param idAzienda
	 * @return
	 */
	List<RichiestaServizio> searchRichiesteServizio(Long idComuneISA, String cognome, String nome, String codiceFiscale, Long idServizio, Long idAzienda, Date da, Date a)
			throws ServiceLayerException;

	/**
	 * Ricerca le {@link RichiestaServizio} in base ai parametri in input.
	 *
	 * @param idAzienda
	 * @param da
	 * @param a
	 * @param idServizio
	 * @return
	 * @throws ServiceLayerException
	 */
	List<RichiestaServizio> searchRichiesteServizio(Long idAzienda, Long idProfiloUtenteCittadino, Date da, Date a, Long idServizio) throws ServiceLayerException;

	/**
	 * Ritorna la {@link RichiestaServizio} in base al suo id.
	 *
	 * @param idRichiesta
	 * @return
	 * @throws ServiceLayerException
	 */
	RichiestaServizio getRichiestaServizio(Long idRichiesta) throws ServiceLayerException;
	
	/**
	 * Ricerca le richieste di servizio (fascicoli) a partire dai parametri passati in input.
	 *
	 * @param idComuneISA
	 * @param cognome
	 * @param nome
	 * @param codiceFiscale
	 * @param idServizio
	 * @param a
	 * @param da
	 * @param idAzienda
	 * @return
	 */
	List<RichiestaServizio> searchRichiesteServizioByFilters(Long idComuneISA, String codiceFiscale, Long tipologia, Long idAzienda, Date da, Date a)
			throws ServiceLayerException;


}
