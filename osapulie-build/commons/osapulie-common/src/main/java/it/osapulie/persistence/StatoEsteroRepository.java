/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.persistence;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.osapulie.domain.StatoEstero;

/**
 *
 * @author Gianluca Pindinelli
 *
 */
public interface StatoEsteroRepository extends PagingAndSortingRepository<StatoEstero, Long> {

	/**
	 * Ritorna l'elenco di tutti gli stati esteri presenti.
	 *
	 * @return
	 */
	@Override
	Collection<StatoEstero> findAll();

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.data.repository.PagingAndSortingRepository#findAll(org.springframework.
	 * data.domain.Sort)
	 */
	@Override
	public Iterable<StatoEstero> findAll(Sort sort);

	/**
	 *
	 * @param codiceStato
	 * @return
	 */
	StatoEstero findByCodiceStato(Integer codiceStato);

	/**
	 *
	 * @param codiceStato
	 * @return
	 */
	List<StatoEstero> findByCodiceStatoNot(Sort sort, Integer codiceStato);

}
