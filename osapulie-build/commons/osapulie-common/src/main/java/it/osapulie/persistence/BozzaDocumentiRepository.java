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

import it.osapulie.domain.BozzaDocumenti;

/**
 * @author Gianluca De Felice
 *
 */
public interface BozzaDocumentiRepository extends CrudRepository<BozzaDocumenti, Long> {

	/**
	 * Recupera la {@link BozzaDocumenti} in base ai parametri di input.
	 *
	 * @param idBozza
	 * @return
	 */
	@Query("SELECT b FROM BozzaDocumenti b WHERE b.bozza.id = ?1")
	List<BozzaDocumenti> findByIdBozza(Long idBozza);
	
}
