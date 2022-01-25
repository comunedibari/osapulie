/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.persistence;

import it.osapulie.domain.categoriaimmobile.BaseDiCalcolo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Gianluca Pindinelli
 *
 */
public interface BaseDiCalcoloRepository extends JpaRepository<BaseDiCalcolo, Long> {

	@Override
	List<BaseDiCalcolo> findAll();

	BaseDiCalcolo findByValore(double valore);
}
