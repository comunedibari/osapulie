/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.Delega;

/**
 * @author Gianluca Pindinelli
 *
 */
public interface DelegaRepository extends CrudRepository<Delega, Long> {

	List<Delega> findByComuneIsa(ComuneISA comuneISA);

	@Query("SELECT d from Delega d WHERE d.delegato.id = ?1")
	List<Delega> findByFkDelegato(long idDelegato);

}
