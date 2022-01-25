/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.osapulie.domain.Backup;
import it.osapulie.persistence.BackupRepository;
import it.osapulie.service.BackupService;
import it.osapulie.service.ServiceLayerException;

/**
 * @author Gianluca Pindinelli
 *
 */
@Service("backupService")
@Transactional
public class BackupServiceImpl implements BackupService {

	@Autowired
	private BackupRepository backupRepository;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.BackupService#getBackupByPk(long)
	 */
	@Override
	public Backup getBackupByPk(long pk) throws ServiceLayerException {
		return backupRepository.findOne(pk);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.BackupService#saveBackup(it.osapulie.domain.Backup)
	 */
	@Override
	public Backup saveBackup(Backup backup) throws ServiceLayerException {
		return backupRepository.save(backup);

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.BackupService#deleteBackup(long)
	 */
	@Override
	public void deleteBackup(long idBackup) throws ServiceLayerException {
		backupRepository.delete(idBackup);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.osapulie.service.BackupService#getBackupCittadinoByComuneAndServizio(java.lang.Long,
	 * java.lang.Long, long)
	 */
	@Override
	public Backup getBackupCittadinoByComuneAndServizio(Long idProfiloUtenteCittadino, Long idComuneIsa, long idServizio) {
		return backupRepository.findByComuneISAServizioProfiloUtenteCittadino(idComuneIsa, idServizio, idProfiloUtenteCittadino);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.BackupService#getBackupAziendaByComuneAndServizio(java.lang.Long,
	 * java.lang.Long, long)
	 */
	@Override
	public Backup getBackupAziendaByComuneAndServizio(Long idAzienda, Long idComuneIsa, long idServizio) {
		return backupRepository.findByComuneISAServizioAzienda(idComuneIsa, idServizio, idAzienda);
	}

}
