/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.persistence;

import org.springframework.data.repository.PagingAndSortingRepository;

import it.osapulie.domain.Provincia;

/**
 *
 * @author Gianluca Pindinelli
 *
 */
public interface ProvinciaRepository extends PagingAndSortingRepository<Provincia, Long> {

	Provincia findBySigla(String sigla);

}
