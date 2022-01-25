/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.ProfiloUtenteCittadinoAzienda;
import it.osapulie.persistence.ProfiloUtenteCittadinoCustomRepository;
import it.osapulie.persistence.ProfiloUtenteCittadinoRepository;
import it.osapulie.service.ProfiloUtenteService;
import it.osapulie.service.ServiceLayerException;

/**
 * Implementazione di {@link ProfiloUtenteService}.
 *
 * @author Gianluca Pindinelli
 *
 */
@Service("profiloUtenteService")
@Transactional
public class ProfiloUtenteServiceImpl implements ProfiloUtenteService {

	@Inject
	private ProfiloUtenteCittadinoRepository profiloUtenteCittadinoRepository;

	@Inject
	private ProfiloUtenteCittadinoCustomRepository profiloUtenteCittadinoCustomRepository;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.ProfiloUtenteService#getAllProfiloUtente()
	 */
	@Override
	public List<ProfiloUtenteCittadino> getAllProfiloUtenteCittadino() throws ServiceLayerException {
		return (List<ProfiloUtenteCittadino>) profiloUtenteCittadinoRepository.findAll();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.ProfiloUtenteService#getProfiloUtenteCittadinoById(long)
	 */
	@Override
	public ProfiloUtenteCittadino getProfiloUtenteCittadinoById(long pk) throws ServiceLayerException {
		return profiloUtenteCittadinoRepository.findOne(pk);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.ProfiloUtenteService#getProfiloUtenteCittadinoByCf(java.lang.String)
	 */
	@Override
	public ProfiloUtenteCittadino getProfiloUtenteCittadinoByCf(String cf) throws ServiceLayerException {
		return profiloUtenteCittadinoRepository.findByCodiceFiscale(cf);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.ProfiloUtenteService#countAllProfiloUtenteCittadino()
	 */
	@Override
	public long countAllProfiloUtenteCittadino() throws ServiceLayerException {
		return profiloUtenteCittadinoRepository.count();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.service.ProfiloUtenteService#updateProfiloUtenteCittadino(it.osapulie.domain.
	 * ProfiloUtenteCittadino)
	 */
	@Override
	public void updateProfiloUtenteCittadino(ProfiloUtenteCittadino profiloUtenteCittadino) throws ServiceLayerException {
		profiloUtenteCittadinoRepository.save(profiloUtenteCittadino);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.ProfiloUtenteService#saveProfiloUtenteCittadino(it.osapulie.domain.
	 * ProfiloUtenteCittadino)
	 */
	@Override
	public long saveProfiloUtenteCittadino(ProfiloUtenteCittadino profiloUtenteCittadino) throws ServiceLayerException {
		ProfiloUtenteCittadino save = profiloUtenteCittadinoRepository.save(profiloUtenteCittadino);
		return save.getId();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.service.ProfiloUtenteService#deleteProfiloUtenteCittadinoAzienda(java.lang.Long,
	 * java.lang.Long)
	 */
	@Override
	public long deleteProfiloUtenteCittadinoAzienda(Long idProfiloUtenteCittadino, Long idAzienda) throws ServiceLayerException {
		return profiloUtenteCittadinoCustomRepository.deleteProfiloUtenteCittadinoAzienda(idProfiloUtenteCittadino, idAzienda);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.ProfiloUtenteService#searchUtentiAssociatiAzienda(java.lang.Long,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<ProfiloUtenteCittadino> searchUtentiAssociatiAzienda(Long idAzienda, String codiceFiscaleDelegato, String nomeDelegato, String cognomeDelegato) {

		List<ProfiloUtenteCittadino> results = null;
		List<ProfiloUtenteCittadinoAzienda> searchUtentiAssociatiAzienda = profiloUtenteCittadinoCustomRepository.searchUtentiAssociatiAzienda(idAzienda, codiceFiscaleDelegato, nomeDelegato,
				cognomeDelegato);
		if (searchUtentiAssociatiAzienda != null) {
			results = new ArrayList<ProfiloUtenteCittadino>();
			for (ProfiloUtenteCittadinoAzienda profiloUtenteCittadinoAzienda : searchUtentiAssociatiAzienda) {
				results.add(profiloUtenteCittadinoAzienda.getProfiloUtenteCittadino());
			}
		}

		return results;
	}

}
