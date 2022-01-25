/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.anagrafe.service.impl;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.portal.kernel.util.DateUtil;

import it.osapulie.anagrafe.service.AnagrafeCommonService;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici.ComponentiNucleoFamiliare;
import it.osapulie.domain.Comune;
import it.osapulie.domain.ComuneEstero;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.service.CommonService;
import it.osapulie.service.ComuneEsteroService;
import it.osapulie.service.ComuneService;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.web.portlet.util.SenderHelper;

/**
 * Servizi comuni per la portlet Anagrafe.
 *
 * @author Gianluca Pindinelli
 *
 */
@Service
public class AnagrafeCommonServiceImpl implements AnagrafeCommonService {

	protected Logger log = LoggerFactory.getLogger(AnagrafeCommonServiceImpl.class.getName());

	@Inject
	private SenderHelper senderHelper;

	@Inject
	private ComuneService comuneService;

	@Inject
	private ComuneEsteroService comuneEsteroService;

	@Inject
	private CommonService commonService;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.anagrafe.service.AnagrafeCommonService#sendMailToUser(it.osapulie.shared.service.
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
		senderHelper.sendCommunicationToCittadino(subject, "velocityTemplate/cittadino.vm", emailModel, null, currentProfiloUtenteCittadino);

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.anagrafe.service.AnagrafeCommonService#addComponenteComuneAndProvinciaToModel(
	 * org. springframework .ui.Model,
	 * it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici.ComponentiNucleoFamiliare)
	 */
	@Override
	public void addInfoAggiuntiveComponenteToModel(Model model, ComponentiNucleoFamiliare componente) {

		// Atto Nascita
		String codiceIstatComuneNascita = componente.getCodiceIstatComuneNascita();
		if (codiceIstatComuneNascita != null) {
			Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(codiceIstatComuneNascita);
			if (comuneByCodiceISTAT != null) {
				model.addAttribute("comuneNascita", comuneByCodiceISTAT.getDenominazione());
				model.addAttribute("provinciaNascita", comuneByCodiceISTAT.getProvincia().getDenominazioneProvincia());
			}
			// Ricerca in comuni esteri
			else {
				if (!codiceIstatComuneNascita.isEmpty()) {
					ComuneEstero comuneEsteroByCodice = comuneEsteroService.getComuneEsteroByCodice(Integer.parseInt(codiceIstatComuneNascita));
					if (comuneEsteroByCodice != null) {
						model.addAttribute("comuneNascita", comuneEsteroByCodice.getDenominazione());
					}
				}
			}
		}

		// Atto Nascita Trascritto
		String codiceIstatComuneNascitaTrascritto = componente.getCodiceIstatComuneNascitaTrascritto();
		if (codiceIstatComuneNascitaTrascritto != null) {
			Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(codiceIstatComuneNascitaTrascritto);
			if (comuneByCodiceISTAT != null) {
				model.addAttribute("comuneAttoNascitaTrascritto", comuneByCodiceISTAT.getDenominazione());
				model.addAttribute("provinciaAttoNascitaTrascritto", comuneByCodiceISTAT.getProvincia().getDenominazioneProvincia());
			}
		}

		// Atto Matrimonio
		String codiceIstatComuneMatrimonio = componente.getCodiceIstatComuneMatrimonio();
		if (codiceIstatComuneMatrimonio != null) {
			Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(codiceIstatComuneMatrimonio);
			if (comuneByCodiceISTAT != null) {
				model.addAttribute("comuneMatrimonio", comuneByCodiceISTAT.getDenominazione());
			}
		}

		// Atto Matrimonio Trascritto
		String codiceIstatMatrimonioTrascritto = componente.getCodiceIstatComuneMatrimonioTrascritto();
		if (codiceIstatMatrimonioTrascritto != null) {
			Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(codiceIstatMatrimonioTrascritto);
			if (comuneByCodiceISTAT != null) {
				model.addAttribute("comuneMatrimonioTrascritto", comuneByCodiceISTAT.getDenominazione());
			}
		}

		// Atto divorzio
		String codiceIstatComuneDivorzio = componente.getCodiceIstatComuneTribunaleDivorzio();
		if (codiceIstatComuneDivorzio != null) {
			Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(codiceIstatComuneDivorzio);
			if (comuneByCodiceISTAT != null) {
				model.addAttribute("comuneTribunaleDivorzio", comuneByCodiceISTAT.getDenominazione());
			}
		}

		// Atto divorzio trascritto
		String codiceIstatComuneDivorzioTrascritto = componente.getCodiceIstatComuneTrascrizioneDivorzio();
		if (codiceIstatComuneDivorzioTrascritto != null) {
			Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(codiceIstatComuneDivorzioTrascritto);
			if (comuneByCodiceISTAT != null) {
				model.addAttribute("comuneTribunaleDivorzioTrascritto", comuneByCodiceISTAT.getDenominazione());
			}
		}

		// Atto morte
		String codiceIstatMorte = componente.getCodiceIstatComuneMorte();
		if (codiceIstatMorte != null) {
			Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(codiceIstatMorte);
			if (comuneByCodiceISTAT != null) {
				model.addAttribute("comuneMorte", comuneByCodiceISTAT.getDenominazione());
			}
		}

		// Atto morte coniuge trascritto
		String codiceIstatMorteConiugeTrascritto = componente.getCodiceIstatComuneMorteConiugeTrascritto();
		if (codiceIstatMorteConiugeTrascritto != null) {
			Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(codiceIstatMorteConiugeTrascritto);
			if (comuneByCodiceISTAT != null) {
				model.addAttribute("comuneMorteConiugeTrascritto", comuneByCodiceISTAT.getDenominazione());
			}
		}

	}
}
