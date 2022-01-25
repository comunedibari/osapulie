/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.Delega;
import it.osapulie.persistence.DelegaCustomRepository;
import it.osapulie.persistence.DelegaRepository;
import it.osapulie.service.DelegaService;
import it.osapulie.service.ServiceLayerException;

/**
 * Implementazione del service per gli oggetti {@link Delega}.
 *
 * @author Gianluca Pindinelli
 *
 */
@Service("delegaService")
@Transactional
public class DelegaServiceImpl implements DelegaService {

	@Inject
	private DelegaRepository delegaRepository;

	@Inject
	private DelegaCustomRepository delegaCustomRepository;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.DelegaService#getDelegaByPk(it.osapulie.domain.DelegaPK)
	 */
	@Override
	public Delega getDelegaByPk(long idDelega) throws ServiceLayerException {
		return delegaRepository.findOne(idDelega);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.DelegaService#saveDelega(it.osapulie.domain.Delega)
	 */
	@Override
	public void saveDelega(Delega delega) throws ServiceLayerException {
		try {
			delegaRepository.save(delega);
		}
		catch (Exception e) {
			throw new ServiceLayerException("There was an error when saving an object of type Delega :: " + e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.DelegaService#getAllDelega()
	 */
	@Override
	public List<Delega> getAllDelega() throws ServiceLayerException {
		return (List<Delega>) delegaRepository.findAll();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.DelegaService#deleteDelega(long)
	 */
	@Override
	public void deleteDelega(long idDelega) throws ServiceLayerException {
		delegaRepository.delete(idDelega);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.DelegaService#getAllDelegaByComuneIsa(long)
	 */
	@Override
	public List<Delega> getAllDelegaByComuneIsa(ComuneISA comuneISA) throws ServiceLayerException {
		return delegaRepository.findByComuneIsa(comuneISA);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.DelegaService#updateDelega(it.osapulie.domain.Delega)
	 */
	@Override
	public Delega updateDelega(Delega delega) throws ServiceLayerException {
		return delegaRepository.save(delega);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.DelegaService#searchDeleghe(it.osapulie.domain.ComuneISA,
	 * it.osapulie.domain.ProfiloUtenteCittadino, boolean)
	 */
	@Override
	public List<Delega> searchDeleghe(Long idComuneISA, String cognomeDelegante, String nomeDelegante, String codiceFiscaleDelegante, Boolean stato) throws ServiceLayerException {
		return delegaCustomRepository.findDeleghe(idComuneISA, cognomeDelegante, nomeDelegante, codiceFiscaleDelegante, stato);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.DelegaService#getDelegheByDelegato(long)
	 */
	@Override
	public List<Delega> getDelegheByDelegato(long idDelegato) throws ServiceLayerException {
		return delegaRepository.findByFkDelegato(idDelegato);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.DelegaService#searchDeleghe(java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.Boolean, Long)
	 */
	@Override
	public List<Delega> searchDeleghe(String cognomeDelegante, String nomeDelegante, String codiceFiscaleDelegante, Boolean stato, Long idDelegato) throws ServiceLayerException {
		return delegaCustomRepository.findDeleghe(cognomeDelegante, nomeDelegante, codiceFiscaleDelegante, stato, idDelegato);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.DelegaService#getDelegheByServizio(long)
	 */
	@Override
	public List<Delega> getDelegheByServizio(long idServizio) throws ServiceLayerException {
		return delegaCustomRepository.getDelegheByServizio(idServizio);
	}
}
