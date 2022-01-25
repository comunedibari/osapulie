/************************************************************************************
 * Copyright (c) 2011, 2016 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.osapulie.domain.Template;

/**
 * @author Gianluca Pindinelli
 *
 */
public interface TemplateRepository extends PagingAndSortingRepository<Template, Long> {

	@Query("SELECT t from Template t WHERE t.comuneISA.id = ?1 AND t.servizio.id = ?2 AND t.tipo = ?3 AND t.attivo = 1")
	Template findByComuneISAServizioAttivo(long idComuneISA, long idServizio, String tipo);

}
