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
import it.osapulie.domain.Pin;
import it.osapulie.persistence.PinCustomRepository;
import it.osapulie.persistence.PinRepository;
import it.osapulie.service.PinService;
import it.osapulie.service.ServiceLayerException;

/**
 * Implementazione del service per gli oggetti {@link Pin}.
 *
 * @author Gianluca Pindinelli
 *
 */
@Service("pinService")
@Transactional
public class PinServiceImpl implements PinService {

	@Inject
	private PinRepository pinRepository;

	@Inject
	private PinCustomRepository pinCustomRepository;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.DelegaService#getDelegaByPk(it.osapulie.domain.DelegaPK)
	 */
	@Override
	public Pin getPinByPk(long id) throws ServiceLayerException {
		return pinRepository.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.DelegaService#saveDelega(it.osapulie.domain.Delega)
	 */
	@Override
	public void savePin(Pin pin) throws ServiceLayerException {
		try {
			pinRepository.save(pin);
		}
		catch (Exception e) {
			throw new ServiceLayerException("There was an error when saving an object of type Pin :: " + e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.DelegaService#getAllDelega()
	 */
	@Override
	public List<Pin> getAllPin() throws ServiceLayerException {
		return (List<Pin>) pinRepository.findAll();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.DelegaService#deleteDelega(long)
	 */
	@Override
	public void deletePin(long id) throws ServiceLayerException {
		pinRepository.delete(id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.DelegaService#getAllDelegaByComuneIsa(long)
	 */
	@Override
	public List<Pin> getAllPinByComuneIsa(ComuneISA comuneISA) throws ServiceLayerException {
		return pinRepository.findByComuneIsa(comuneISA);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.DelegaService#updateDelega(it.osapulie.domain.Delega)
	 */
	@Override
	public Pin updatePin(Pin pin) throws ServiceLayerException {
		return pinRepository.save(pin);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.DelegaService#searchDeleghe(it.osapulie.domain.ComuneISA,
	 * it.osapulie.domain.ProfiloUtenteCittadino, boolean)
	 */
	@Override
	public List<Pin> searchPin(Long idComuneISA, String cognome, String nome, String codiceFiscale, String stato) throws ServiceLayerException {
		return pinCustomRepository.findPins(idComuneISA, cognome, nome, codiceFiscale, stato);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.PinService#getLastPin()
	 */
	@Override
	public Pin getLastPin(long idUtente) throws ServiceLayerException {
		return pinCustomRepository.findLastPin(idUtente);
	}
}
