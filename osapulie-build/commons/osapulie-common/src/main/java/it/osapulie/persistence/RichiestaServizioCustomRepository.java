/************************************************************************************
 * Copyright (c) 2011, 2016 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.persistence;

import java.util.Date;
import java.util.List;

import it.osapulie.domain.fascicoloutente.RichiestaServizio;

/**
 * @author Gianluca Pindinelli
 */
public interface RichiestaServizioCustomRepository {

	/**
	 * Carica le richieste di servizio (fascicoli) a partire dai parametri passati in input.
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
	List<RichiestaServizio> findRichiestaServizio(Long idComuneISA, String cognome, String nome, String codiceFiscale, Long idServizio, Long idAzienda, Date da, Date a);

	/**
	 * Carica le richieste di servizio (fascicoli) a partire dai parametri passati in input.
	 *
	 * @param idAzienda
	 * @param idProfiloUtenteCittadino
	 * @param da
	 * @param a
	 * @return
	 */
	List<RichiestaServizio> findRichiestaServizio(Long idAzienda, Long idProfiloUtenteCittadino, Date da, Date a, Long idServizio);
	
	/**
	 * Carica le richieste di servizio (fascicoli) a partire dai parametri passati in input.
	 *
	 * @param idComuneISA
	 * @param codiceFiscale
	 * @param tipologia
	 * @param a
	 * @param da
	 * @param idAzienda
	 * @return
	 */
	List<RichiestaServizio> findRichiestaServizioByFilters(Long idComuneISA,String codiceFiscale, Long tipologia, Long idAzienda, Date da, Date a);

}
