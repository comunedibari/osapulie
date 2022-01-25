/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.osapulie.domain.Backup;

/**
 * @author Gianluca Pindinelli
 */
public interface BackupRepository extends CrudRepository<Backup, Long> {

	/**
	 * Recupera la {@link Backup} in base ai parametri di input.
	 *
	 * @param idComuneISA
	 * @param idServizio
	 * @param idProfiloUtenteCittadino
	 * @return
	 */
	@Query("SELECT b FROM Backup b WHERE b.comuneISA.id = ?1 AND b.servizio.id = ?2 AND b.profiloUtenteCittadino.id = ?3")
	Backup findByComuneISAServizioProfiloUtenteCittadino(Long idComuneISA, Long idServizio, Long idProfiloUtenteCittadino);

	/**
	 * Recupera la {@link Backup} in base ai parametri di input.
	 *
	 * @param idComuneISA
	 * @param idServizio
	 * @param idProfiloUtenteCittadino
	 * @return
	 */
	@Query("SELECT b FROM Backup b WHERE b.comuneISA.id = ?1 AND b.servizio.id = ?2 AND b.azienda.id = ?3")
	Backup findByComuneISAServizioAzienda(Long idComuneISA, Long idServizio, Long idAzienda);

}
