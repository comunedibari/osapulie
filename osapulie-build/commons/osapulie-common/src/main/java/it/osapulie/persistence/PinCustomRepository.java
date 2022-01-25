/************************************************************************************
 * Copyright (c) 2011, 2016 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.persistence;

import java.util.List;

import it.osapulie.domain.Pin;

/**
 * @author Gianluca Pindinelli
 *
 */
public interface PinCustomRepository {

	/**
	 * Carica i {@link Pin} in base ai parametri di input.
	 *
	 * @param idComuneISA
	 * @param cognome
	 * @param nome
	 * @param codiceFiscale
	 * @param stato
	 * @return
	 */
	List<Pin> findPins(Long idComuneISA, String cognome, String nome, String codiceFiscale, String stato);

	/**
	 * Ritorna l'ultimo PIN assegnato ad un cittadino, <code>null</code> se non esistono alcun PIN
	 * associato.
	 * 
	 * @param idUtente
	 * @return
	 */
	Pin findLastPin(long idUtente);
}
