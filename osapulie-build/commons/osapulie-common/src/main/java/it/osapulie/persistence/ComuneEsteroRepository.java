/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.persistence;

import java.util.Collection;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.osapulie.domain.ComuneEstero;

/**
 *
 * @author Gianluca Pindinelli
 *
 */
public interface ComuneEsteroRepository extends PagingAndSortingRepository<ComuneEstero, Long> {

	/**
	 * Ritorna l'elenco di tutti i comuni esteri presenti.
	 *
	 * @return
	 */
	@Override
	Collection<ComuneEstero> findAll();

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.data.repository.PagingAndSortingRepository#findAll(org.springframework.
	 * data.domain.Sort)
	 */
	@Override
	public Iterable<ComuneEstero> findAll(Sort sort);

	/**
	 *
	 * @param codiceStato
	 * @return
	 */
	ComuneEstero findByCodice(int codice);

}
