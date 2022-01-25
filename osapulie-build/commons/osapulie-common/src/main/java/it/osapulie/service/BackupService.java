/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.service;

import it.osapulie.domain.Backup;
import it.osapulie.domain.Bozza;
import it.osapulie.domain.ProfiloUtenteCittadino;

/**
 * Service per la gestione dei {@link ProfiloUtenteCittadino}.
 *
 * @author Gianluca Pindinelli
 *
 */
public interface BackupService {

	/**
	 * Ritorna la {@link Bozza} in base alla PK.
	 *
	 * @param pk
	 * @return
	 * @throws ServiceLayerException
	 */
	Backup getBackupByPk(long pk) throws ServiceLayerException;

	/**
	 * Salva una {@link Bozza} nel sistema.
	 *
	 * @param backup
	 * @return
	 * @throws ServiceLayerException
	 */
	Backup saveBackup(Backup backup) throws ServiceLayerException;

	/**
	 * Elimina la bozza.
	 *
	 * @param idBackup
	 * @throws ServiceLayerException
	 */
	void deleteBackup(long idBackup) throws ServiceLayerException;

	/**
	 * @param id
	 * @param idComuneIsa
	 * @param idServizio
	 * @return
	 */
	Backup getBackupCittadinoByComuneAndServizio(Long id, Long idComuneIsa, long idServizio);

	/**
	 * @param id
	 * @param idComuneIsa
	 * @param idServizio
	 * @return
	 */
	Backup getBackupAziendaByComuneAndServizio(Long id, Long idComuneIsa, long idServizio);

}
