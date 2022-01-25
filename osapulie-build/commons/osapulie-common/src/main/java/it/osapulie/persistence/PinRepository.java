/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 * 
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.persistence;

import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.Pin;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * @author Gianluca Pindinelli
 * 
 */
public interface PinRepository extends CrudRepository<Pin, Long> {

	List<Pin> findByComuneIsa(ComuneISA comuneISA);

}
