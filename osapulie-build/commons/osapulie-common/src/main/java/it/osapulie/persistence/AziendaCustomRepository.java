/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.persistence;

import java.util.List;

import it.osapulie.domain.Azienda;

/**
 * @author Gianluca Pindinelli
 */
public interface AziendaCustomRepository {

	/**
	 * Carica le aziende in base ai parametri passati in input.
	 * 
	 * @param piva
	 * @param ragioneSociale
	 * @param responsabile
	 * @param stato
	 * @return
	 */
	List<Azienda> findAziende(String piva, String ragioneSociale, String responsabile, Boolean stato);

}
