/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.osapulie.domain.ProfiloUtenteCittadino;

/**
 * @author Mario Scalas
 * @author Maria Michela Birtolo
 */
public interface ProfiloUtenteCittadinoRepository extends CrudRepository<ProfiloUtenteCittadino, Long> {

	/**
	 * Recupera il {@link ProfiloUtenteCittadino} dato il codice fiscale del cittadino.
	 *
	 * @param codiceFiscale
	 * @return ProfiloUtenteCittadino
	 */
	ProfiloUtenteCittadino findByCodiceFiscale(String codiceFiscale);
	
	@Query("SELECT i.gestoreComune FROM Comune a, ComuneISA i where a.id = ?1 and a.codiceIstatAN = i.codiceIstat and i.attivo=1")
	ProfiloUtenteCittadino findByComune(long l);

}
