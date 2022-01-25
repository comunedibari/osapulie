/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.persistence;

import it.osapulie.domain.categoriaimmobile.Agevolazione;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Gianluca Pindinelli
 *
 */
public interface AgevolazioneRepository extends JpaRepository<Agevolazione, Long> {

	@Override
	List<Agevolazione> findAll();

}
