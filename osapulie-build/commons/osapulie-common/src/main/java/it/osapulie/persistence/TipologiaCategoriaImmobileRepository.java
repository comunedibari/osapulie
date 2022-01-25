/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.persistence;

import it.osapulie.domain.categoriaimmobile.TipologiaCategoriaImmobile;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Gianluca Pindinelli
 *
 */
public interface TipologiaCategoriaImmobileRepository extends JpaRepository<TipologiaCategoriaImmobile, Long> {

	@Override
	List<TipologiaCategoriaImmobile> findAll();

	TipologiaCategoriaImmobile findByCodice(String codice);

}
