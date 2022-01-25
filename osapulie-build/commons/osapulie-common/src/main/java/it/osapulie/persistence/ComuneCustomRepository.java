/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.persistence;

import java.util.List;

import it.osapulie.domain.Comune;

/**
 * @author Gianluca Pindinelli
 *
 */
public interface ComuneCustomRepository {

	/**
	 * Ricerca i comuni in base alla denominazione.
	 *
	 * @param denominazione
	 * @param sort
	 * @return
	 */
	List<Comune> findByDenominazione(String denominazione);
}
