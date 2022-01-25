/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.osapulie.domain.Azienda;

/**
 * @author Gianluca Pindinelli
 */
public interface AziendaRepository extends CrudRepository<Azienda, Long> {

	/**
	 * Recupera il {@link Azienda} data la PIVA del professionista.
	 *
	 * @param piva
	 * @return
	 */
	Azienda findByPartitaIva(String partitaIva);

	/**
	 * Recupera i {@link Azienda} attivi.
	 *
	 * @param attivo
	 * @return
	 */
	List<Azienda> findByAttiva(boolean attiva);

}
