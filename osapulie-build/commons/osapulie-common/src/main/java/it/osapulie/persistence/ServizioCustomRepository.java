/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.persistence;

import java.util.List;

import it.osapulie.domain.servizi.Servizio;

/**
 *
 * @author Gianluca Pindinelli
 *
 */
public interface ServizioCustomRepository {

	/**
	 * Ritorna la lista dei servizi in base ai parametri in input.
	 * @param idComuneISA
	 * @param idAreaTematica
	 * @param idTipologia
	 * @param idDelega
	 * @param cittadino
	 * @param azienda
	 * @param attivo
	 *
	 * @return
	 */
	List<Servizio> findByParameters(Long idComuneISA, Long idAreaTematica, Long idTipologia, Long idDelega, Boolean cittadino, Boolean azienda, boolean attivo);

}
