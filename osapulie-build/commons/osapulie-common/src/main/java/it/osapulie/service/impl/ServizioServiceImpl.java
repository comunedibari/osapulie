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

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.ComuneISAServizio;
import it.osapulie.domain.servizi.Servizio;
import it.osapulie.persistence.ComuneISARepository;
import it.osapulie.persistence.ServizioCustomRepository;
import it.osapulie.persistence.ServizioRepository;
import it.osapulie.service.ServiceLayerException;
import it.osapulie.service.ServizioService;

/**
 * @author Gianluca Pindinelli
 *
 */
@Service("servizioService")
@Transactional
public class ServizioServiceImpl implements ServizioService {

	@Inject
	private ServizioRepository servizioRepository;

	@Inject
	private ServizioCustomRepository servizioCustomRepository;

	@Inject
	private ComuneISARepository comuneISARepository;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.ServizioService#getServizioById(long)
	 */
	@Override
	public Servizio getServizioById(long id) throws ServiceLayerException {
		return servizioRepository.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.ServizioService#getAllServizio()
	 */
	@Override
	public List<Servizio> getAllServizio() throws ServiceLayerException {

		Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "nomeServizio"));

		List<Servizio> servizioList = servizioRepository.findAll(sort);

		return servizioList;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.ServizioService#getServizioByCodiceServizio(java.lang.String)
	 */
	@Override
	public Servizio getServizioByCodiceServizio(String codiceServizio) throws ServiceLayerException {
		Servizio servizio = servizioRepository.findByCodiceServizio(codiceServizio);
		return servizio;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.ServizioService#getServizioByUriScheda(java.lang.String)
	 */
	@Override
	public Servizio getServizioByUri(String uri) throws ServiceLayerException {
		Servizio servizio = servizioRepository.findByUri(uri);
		return servizio;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.ServizioService#getServizioByUriScheda(java.lang.String)
	 */
	@Override
	public Servizio getServizioByUriScheda(String uriScheda) throws ServiceLayerException {
		Servizio servizio = servizioRepository.findByUriScheda(uriScheda);
		setComuniAttivi(servizio);
		return servizio;
	}

	/**
	 * Caricamento comuni correlati in base allo stato servizio.
	 *
	 * @param servizio
	 */
	private void setComuniAttivi(Servizio servizio) {

		if (servizio != null) {
			List<ComuneISAServizio> comuni = servizio.getComuni();
			if (comuni != null) {
				List<ComuneISAServizio> comuniAttivi = new ArrayList<ComuneISAServizio>();
				for (ComuneISAServizio comuneISAServizio : comuni) {
					if (comuneISAServizio.isAttivo()) {
						ComuneISA comuneISA = comuneISARepository.findOne(comuneISAServizio.getIdComuneISA());
						if (comuneISA != null && comuneISA.getAttivo()) {
							comuniAttivi.add(comuneISAServizio);
						}
					}
				}
				servizio.setComuni(comuniAttivi);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.ServizioService#getServiziAttivi()
	 */
	@Override
	public List<Servizio> getServiziAttivi() throws ServiceLayerException {

		Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "nomeServizio"));

		List<Servizio> servizioList = servizioRepository.findByAttivo(true, sort);

		return servizioList;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.ServizioService#getServiziAttiviForDelegaByComuneISA(long)
	 */
	@Override
	public List<Servizio> getServiziAttiviForDelegaByComuneISA(long idComuneISA) throws ServiceLayerException {
		Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "nomeServizio"));
		return servizioRepository.findByComuneISAAndDelega(idComuneISA, true, sort);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.ServizioService#getServiziAttiviWithDelega()
	 */
	@Override
	public List<Servizio> getServiziAttiviForDelega() throws ServiceLayerException {
		Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "nomeServizio"));

		List<Servizio> servizioList = servizioRepository.findByDelega(true, sort);

		return servizioList;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.ServizioService#getServiziByParemeters(java.lang.Long,
	 * java.lang.Long, java.lang.Long, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean,
	 * boolean)
	 */
	@Override
	public List<Servizio> getServiziByParemeters(Long idComuneISA, Long idAreaTematica, Long idTipologia, Long idDelega, Boolean cittadino, Boolean azienda, boolean attivo)
			throws ServiceLayerException {
		List<Servizio> servizioList = servizioCustomRepository.findByParameters(idComuneISA, idAreaTematica, idTipologia, idDelega, cittadino, azienda, attivo);
		return servizioList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.osapulie.service.ServizioService#getServiziAttiviByComuneISA(long)
	 */
	@Override
	public List<Servizio> getServiziAttiviByComuneISA(long idComuneISA) throws ServiceLayerException {

		Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "nomeServizio"));

		List<Servizio> findByComuneISA = servizioRepository.findByComuneISA(idComuneISA, sort);
		return findByComuneISA;
	}

}
