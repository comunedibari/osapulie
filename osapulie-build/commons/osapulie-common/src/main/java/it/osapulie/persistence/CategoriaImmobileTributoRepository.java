/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.persistence;

import it.osapulie.domain.categoriaimmobile.CategoriaImmobileTributo;

import java.util.Collection;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Gianluca Pindinelli
 */
public interface CategoriaImmobileTributoRepository extends PagingAndSortingRepository<CategoriaImmobileTributo, Long> {

	/**
	 * Ritorna l'elenco di tutte le categorie di immobili/tributi presenti.
	 *
	 * @return
	 */
	@Override
	Collection<CategoriaImmobileTributo> findAll();
}
