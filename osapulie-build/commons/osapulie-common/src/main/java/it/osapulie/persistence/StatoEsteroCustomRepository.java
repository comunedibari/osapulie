/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.persistence;

import java.util.List;

import it.osapulie.domain.StatoEstero;

/**
 *
 * @author Gianluca Pindinelli
 *
 */
public interface StatoEsteroCustomRepository {

	/**
	 *
	 * @param codiceStato
	 * @return
	 */
	List<StatoEstero> findByDenominazione(String denominazione);

}
