/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.tributi.service.impl;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.portlet.PortletRequest;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.portal.kernel.util.DateUtil;

import it.osapulie.domain.Azienda;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.service.CommonService;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.tributi.service.TributiCommonService;
import it.osapulie.web.portlet.util.PortletHelper;
import it.osapulie.web.portlet.util.SenderHelper;

/**
 * @author Gianluca Pindinelli
 *
 */
@Service
public class TributiCommonServiceImpl implements TributiCommonService {

	private static final String TEMPLATE_AZIENDA = "velocityTemplate/azienda.vm";
	private static final String TEMPLATE_CITTADINO = "velocityTemplate/cittadino.vm";

	private static final String DICHIARAZIONE_CAMBIO_RESIDENZA_PORTLET_ID = "dichiarazionecambioresidenza_WAR_osapulieanagraficheportlet";

	protected Logger log = LoggerFactory.getLogger(TributiCommonServiceImpl.class.getName());

	@Inject
	private SenderHelper senderHelper;

	@Inject
	private PortletHelper helper;

	@Inject
	private CommonService commonService;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.tributi.service.TributiCommonService#sendMailToUser(it.osapulie.shared.service.
	 * UserPreferences, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void sendMailToUser(UserPreferences userPreferences, String subject, String nomeServizio, String numeroProtocollo) throws Exception {

		log.debug("sendMailToUser :: entering method");

		ProfiloUtenteCittadino currentProfiloUtenteCittadino = commonService.getCurrentProfiloUtenteCittadino(userPreferences);

		Map<String, String> emailModel = new HashMap<String, String>();
		emailModel.put("cognome", currentProfiloUtenteCittadino.getCognome());
		emailModel.put("nome", currentProfiloUtenteCittadino.getNome());
		emailModel.put("nomeServizio", nomeServizio);
		emailModel.put("dataRichiesta", DateUtil.getCurrentDate("dd/MM/yyyy - HH:mm", new Locale("it")));
		if (com.liferay.portal.kernel.util.Validator.isNotNull(numeroProtocollo)) {
			emailModel.put("protocollo", numeroProtocollo);
		}
		senderHelper.sendCommunicationToCittadino(subject, TEMPLATE_CITTADINO, emailModel, null, currentProfiloUtenteCittadino);

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.tributi.service.TributiCommonService#sendMailToCompany(it.osapulie.shared.service
	 * .UserPreferences, it.osapulie.domain.Azienda, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void sendMailToCompany(UserPreferences userPreferences, Azienda azienda, String subject, String nomeServizio, String numeroProtocollo) throws Exception {

		log.debug("sendMailToCompany :: entering method");

		ProfiloUtenteCittadino currentProfiloUtenteCittadino = commonService.getCurrentProfiloUtenteCittadino(userPreferences);

		Map<String, String> emailModel = new HashMap<String, String>();
		emailModel.put("ragioneSociale", azienda.getRagioneSociale());
		emailModel.put("cognome", currentProfiloUtenteCittadino.getCognome());
		emailModel.put("nome", currentProfiloUtenteCittadino.getNome());
		emailModel.put("nomeServizio", nomeServizio);
		emailModel.put("dataRichiesta", DateUtil.getCurrentDate("dd/MM/yyyy - HH:mm", new Locale("it")));
		if (com.liferay.portal.kernel.util.Validator.isNotNull(numeroProtocollo)) {
			emailModel.put("protocollo", numeroProtocollo);
		}
		senderHelper.sendCommunicationToAzienda(subject, TEMPLATE_AZIENDA, emailModel, null, azienda);

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.tributi.service.TributiCommonService#getDichiarazioneCambioResidenzaUrl(javax.
	 * portlet.PortletRequest, java.lang.String)
	 */
	@Override
	public String getDichiarazioneCambioResidenzaUrl(PortletRequest request, String message) {

		String destinationPortletUrl = null;
		try {
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("action", "getBozzaFrom");
			parameters.put("message", message);

			destinationPortletUrl = helper.createPortletURL(request, parameters, DICHIARAZIONE_CAMBIO_RESIDENZA_PORTLET_ID, PortletRequest.ACTION_PHASE);
		}
		catch (Exception e) {
			log.error("getDichiarazioneCambioResidenzaUrl :: " + e.getMessage(), e);
		}
		return destinationPortletUrl;
	}
}
