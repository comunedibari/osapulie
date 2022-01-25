/************************************************************************************
 * Copyright (c) 2011, 2014 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.persistence;

import it.osapulie.domain.categoriaimmobile.CategoriaImmobile;

import java.util.Collection;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Gianluca Pindinelli
 */
public interface CategoriaImmobileRepository extends PagingAndSortingRepository<CategoriaImmobile, Long> {

	/**
	 * Ritorna l'elenco di tutte le categorie di immobili presenti.
	 *
	 * @return
	 */
	@Override
	Collection<CategoriaImmobile> findAll();
}
