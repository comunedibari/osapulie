/************************************************************************************
 * Copyright (c) 2011, 2016 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.liferay.portal.kernel.util.DateUtil;
import it.osapulie.domain.Azienda;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.ProfiloUtenteCittadinoAzienda;
import it.osapulie.persistence.AziendaCustomRepository;
import it.osapulie.persistence.AziendaRepository;
import it.osapulie.pratiche.web.ws.types.ArrayList;
import it.osapulie.service.AziendaService;
import it.osapulie.service.CommonService;
import it.osapulie.service.ServiceLayerException;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.web.portlet.util.PortletHelper;
import it.osapulie.web.portlet.util.SenderHelper;

/**
 * @author Gianluca Pindinelli
 *
 */
@Service("aziendaService")
@Transactional
public class AziendaServiceImpl implements AziendaService {

	@Autowired
	private AziendaRepository aziendaRepository;

	@Autowired
	private AziendaCustomRepository aziendaCustomRepository;
	
	private static final String TEMPLATE_OPERATORE = "velocityTemplate/operatore.vm";
	
	private static final String TEMPLATE_NUOVA_AZIENDA = "velocityTemplate/aziendaCreazioneDaResponsabile.vm";

	@Inject
	private SenderHelper senderHelper;

	@Inject
	private PortletHelper helper;

	@Inject
	private CommonService commonService;
	
	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.AziendaService#getAziendaByPk(long)
	 */
	@Override
	public Azienda getAziendaByPk(long pk) throws ServiceLayerException {
		Azienda findOne = aziendaRepository.findOne(pk);
		return findOne;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.AziendaService#getAziendaByPiva(java.lang.String)
	 */
	@Override
	public Azienda getAziendaByPiva(String piva) throws ServiceLayerException {
		return aziendaRepository.findByPartitaIva(piva);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.AziendaService#deleteAzienda(long)
	 */
	@Override
	public void deleteAzienda(long idAzienda) throws ServiceLayerException {
		aziendaRepository.delete(idAzienda);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.AziendaService#updateAzienda(it.osapulie.domain.Azienda)
	 */
	@Override
	public void updateAzienda(Azienda azienda) throws ServiceLayerException {
		aziendaRepository.save(azienda);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.AziendaService#saveAzienda(it.osapulie.domain.Azienda)
	 */
	@Override
	public long saveAzienda(Azienda azienda) throws ServiceLayerException {
		Azienda save = aziendaRepository.save(azienda);
		return save.getId();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.AziendaService#setInUso(it.osapulie.domain.Azienda, long)
	 */
	@Override
	public void setInUso(Azienda azienda) {
		boolean inUso = azienda.isAttiva() || !azienda.getDelegheDeleganti().isEmpty();

		if (!inUso) {
			// Controllo associazione con altri cittadini
			List<ProfiloUtenteCittadinoAzienda> profiliUtenteCittadinoAziende = azienda.getProfiliUtenteCittadinoAzienda();
			if (profiliUtenteCittadinoAziende != null && !profiliUtenteCittadinoAziende.isEmpty()) {
				inUso = true;
			}
		}
		azienda.setInUso(inUso);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.AziendaService#getAziendeAttive()
	 */
	@Override
	public List<Azienda> getAziendeAttive() throws ServiceLayerException {
		return aziendaRepository.findByAttiva(true);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.AziendaService#searchAziende(java.lang.String, java.lang.String,
	 * java.lang.Boolean)
	 */
	@Override
	public List<Azienda> searchAziende(String piva, String ragioneSociale, String responsabile, Boolean stato) throws ServiceLayerException {
		return aziendaCustomRepository.findAziende(piva, ragioneSociale, responsabile, stato);
	}

	@Override
	public void sendMailToUser(UserPreferences userPreferences, String subject, String nomeServizio, String numeroProtocollo, List<ProfiloUtenteCittadino> listEmail,String emailComune, String ragSociale) throws Exception 
	{
		ProfiloUtenteCittadino currentProfiloUtenteCittadino = commonService.getCurrentProfiloUtenteCittadino(userPreferences);
		HashMap<String, Object> emailModel = new HashMap<String, Object>();
		emailModel.put("emailComune", emailComune);
		emailModel.put("cognomeUtenteCorrente", currentProfiloUtenteCittadino.getCognome());
		emailModel.put("nomeUtenteCorrente", currentProfiloUtenteCittadino.getNome());
		emailModel.put("cognomenome",listEmail);
		emailModel.put("ragSociale", ragSociale);
		emailModel.put("nomeServizio", nomeServizio);
		if (com.liferay.portal.kernel.util.Validator.isNotNull(numeroProtocollo)){
				emailModel.put("protocollo", numeroProtocollo);
		}
		if(listEmail.size() > 0){
			senderHelper.sendMailAggOp(subject, TEMPLATE_OPERATORE, emailModel, null, emailComune);
		}
	}
	
	@Override
	public void sendMailToAziendaNew(UserPreferences userPreferences, String subject, String nomeServizio, String numeroProtocollo, List<ProfiloUtenteCittadino> listEmail,String emailComune, String ragSociale) throws Exception 
	{
		ProfiloUtenteCittadino currentProfiloUtenteCittadino = commonService.getCurrentProfiloUtenteCittadino(userPreferences);
		HashMap<String, Object> emailModel = new HashMap<String, Object>();
		emailModel.put("emailComune", emailComune);
		emailModel.put("cognomeUtenteCorrente", currentProfiloUtenteCittadino.getCognome());
		emailModel.put("nomeUtenteCorrente", currentProfiloUtenteCittadino.getNome());
		emailModel.put("cognomenome",listEmail);
		emailModel.put("ragSociale", ragSociale);
		emailModel.put("nomeServizio", nomeServizio);
		if (com.liferay.portal.kernel.util.Validator.isNotNull(numeroProtocollo)){
				emailModel.put("protocollo", numeroProtocollo);
		}
		senderHelper.sendMailAziendaNew(subject, TEMPLATE_NUOVA_AZIENDA, emailModel, null, emailComune);
	}
}
