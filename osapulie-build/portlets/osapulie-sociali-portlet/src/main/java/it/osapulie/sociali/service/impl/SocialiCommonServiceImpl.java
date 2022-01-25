/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.sociali.service.impl;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.portal.kernel.util.DateUtil;

import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici.ComponentiNucleoFamiliare;
import it.osapulie.domain.Comune;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.service.ComuneService;
import it.osapulie.sociali.service.SocialiCommonService;
import it.osapulie.web.portlet.util.SenderHelper;

/**
 * Servizi comuni per la portlet.
 *
 * @author Gianluca Pindinelli
 *
 */
@Service
public class SocialiCommonServiceImpl implements SocialiCommonService {

	protected Logger log = LoggerFactory.getLogger(SocialiCommonServiceImpl.class.getName());

	@Inject
	private SenderHelper senderHelper;

	@Inject
	private ComuneService comuneService;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.tributi.service.CommonService#sendMailToUser()
	 */
	@Override
	public void sendMailToUser(ProfiloUtenteCittadino profiloUtente, String subject, String nomeServizio, String numeroProtocollo) throws Exception {

		log.debug("sendMailToUser :: entering method");

		Map<String, String> emailModel = new HashMap<String, String>();
		emailModel.put("cognome", profiloUtente.getCognome());
		emailModel.put("nome", profiloUtente.getNome());
		emailModel.put("nomeServizio", nomeServizio);
		emailModel.put("dataRichiesta", DateUtil.getCurrentDate("dd/MM/yyyy - HH:mm", new Locale("it")));
		if (com.liferay.portal.kernel.util.Validator.isNotNull(numeroProtocollo)) {
			emailModel.put("protocollo", numeroProtocollo);
		}
		senderHelper.sendCommunicationToCittadino(subject, "velocityTemplate/cittadino.vm", emailModel, null, profiloUtente);

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.sociali.service.SocialiCommonService#addComponenteComuneAndProvinciaToModel(org.
	 * springframework .ui.Model,
	 * it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici.ComponentiNucleoFamiliare)
	 */
	@Override
	public void addInfoAggiuntiveComponenteToModel(Model model, ComponentiNucleoFamiliare componente) {

		// Atto Nascita
		String codiceIstatComuneNascita = componente.getCodiceIstatComuneNascita();
		if (codiceIstatComuneNascita != null) {
			Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(codiceIstatComuneNascita);
			model.addAttribute("comuneNascita", comuneByCodiceISTAT.getDenominazione());
			model.addAttribute("provinciaNascita", comuneByCodiceISTAT.getProvincia().getDenominazioneProvincia());
		}

		// Atto Nascita Trascritto
		String codiceIstatComuneNascitaTrascritto = componente.getCodiceIstatComuneNascitaTrascritto();
		if (codiceIstatComuneNascitaTrascritto != null) {
			Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(codiceIstatComuneNascitaTrascritto);
			model.addAttribute("comuneAttoNascitaTrascritto", comuneByCodiceISTAT.getDenominazione());
			model.addAttribute("provinciaAttoNascitaTrascritto", comuneByCodiceISTAT.getProvincia().getDenominazioneProvincia());
		}

		// Atto Matrimonio
		String codiceIstatComuneMatrimonio = componente.getCodiceIstatComuneMatrimonio();
		if (codiceIstatComuneMatrimonio != null) {
			Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(codiceIstatComuneMatrimonio);
			model.addAttribute("comuneMatrimonio", comuneByCodiceISTAT.getDenominazione());
		}

		// Atto Matrimonio Trascritto
		String codiceIstatMatrimonioTrascritto = componente.getCodiceIstatComuneMatrimonioTrascritto();
		if (codiceIstatMatrimonioTrascritto != null) {
			Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(codiceIstatMatrimonioTrascritto);
			model.addAttribute("comuneMatrimonioTrascritto", comuneByCodiceISTAT.getDenominazione());
		}

		// Atto divorzio
		String codiceIstatComuneDivorzio = componente.getCodiceIstatComuneTribunaleDivorzio();
		if (codiceIstatComuneDivorzio != null) {
			Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(codiceIstatComuneDivorzio);
			model.addAttribute("comuneTribunaleDivorzio", comuneByCodiceISTAT.getDenominazione());
		}

		// Atto divorzio trascritto
		String codiceIstatComuneDivorzioTrascritto = componente.getCodiceIstatComuneTrascrizioneDivorzio();
		if (codiceIstatComuneDivorzioTrascritto != null) {
			Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(codiceIstatComuneDivorzioTrascritto);
			model.addAttribute("comuneTribunaleDivorzioTrascritto", comuneByCodiceISTAT.getDenominazione());
		}

		// Atto morte
		String codiceIstatMorte = componente.getCodiceIstatComuneMorte();
		if (codiceIstatMorte != null) {
			Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(codiceIstatMorte);
			model.addAttribute("comuneMorte", comuneByCodiceISTAT.getDenominazione());
		}

		// Atto morte coniuge trascritto
		String codiceIstatMorteConiugeTrascritto = componente.getCodiceIstatComuneMorteConiugeTrascritto();
		if (codiceIstatMorteConiugeTrascritto != null) {
			Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(codiceIstatMorteConiugeTrascritto);
			model.addAttribute("comuneMorteConiugeTrascritto", comuneByCodiceISTAT.getDenominazione());
		}

	}

}
