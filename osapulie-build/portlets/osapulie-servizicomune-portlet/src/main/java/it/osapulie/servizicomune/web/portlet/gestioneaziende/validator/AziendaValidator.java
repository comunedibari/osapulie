/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.servizicomune.web.portlet.gestioneaziende.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.osapulie.domain.Azienda;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.service.AziendaService;
import it.osapulie.service.ProfiloUtenteService;
import it.osapulie.servizicomune.web.portlet.gestioneaziende.form.AziendaEditForm;
import it.osapulie.web.portlet.util.PortletUtils;

/**
 * Form validator per {@link ProfiloUtenteModel} (per la form relativa al profilo utente
 * professionista).
 *
 * @author Gianluca Pindinelli
 */
@Component("aziendaValidator")
public class AziendaValidator implements Validator {

	@Autowired
	private AziendaService aziendaService;

	@Autowired
	private ProfiloUtenteService profiloUtenteService;

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return AziendaEditForm.class.equals(clazz);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {

		AziendaEditForm aziendaEditForm = (AziendaEditForm) target;

		String partitaIva = aziendaEditForm.getPartitaIva();
		// Validazione campo PIVA/CF
		if (partitaIva != null && !partitaIva.isEmpty()) {
			// Verifica se è CF o PIVA
			if (partitaIva.length() != 11 && partitaIva.length() != 16) {
				errors.rejectValue("partitaIva", "error.label.pivacf.length");
			}
			else {
				if (partitaIva.length() == 11) {
					String pIva = PortletUtils.checkPartitaIVA(partitaIva);
					if (pIva != null) {
						errors.rejectValue("partitaIva", null, pIva);
					}
				}
				else {
					String checkCodiceFiscale = PortletUtils.checkCodiceFiscale(partitaIva);
					if (checkCodiceFiscale != null) {
						errors.rejectValue("partitaIva", null, checkCodiceFiscale);
					}
				}
			}
		}

		// Controllo esistenza PIVA
		if (partitaIva != null) {
			Azienda profiloUtenteProfessionistaByPiva = aziendaService.getAziendaByPiva(partitaIva);
			if (profiloUtenteProfessionistaByPiva != null && profiloUtenteProfessionistaByPiva.getId().longValue() != aziendaEditForm.getIdAzienda()) {
				errors.rejectValue("partitaIva", "error.label.pivaDuplicate");
			}
		}

		// Controllo responsabile
		String codiceFiscaleResponsabile = aziendaEditForm.getCodiceFiscaleResponsabile();
		if (codiceFiscaleResponsabile != null && !codiceFiscaleResponsabile.equals("")) {
			String checkCodiceFiscale = PortletUtils.checkCodiceFiscale(codiceFiscaleResponsabile);
			if (checkCodiceFiscale != null) {
				errors.rejectValue("codiceFiscaleResponsabile", null, checkCodiceFiscale);
			}

			ProfiloUtenteCittadino profiloUtenteCittadino = profiloUtenteService.getProfiloUtenteCittadinoByCf(codiceFiscaleResponsabile);
			if (profiloUtenteCittadino == null) {
				errors.rejectValue("codiceFiscaleResponsabile", "error.label.responsabileNotFound");
			}
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "partitaIva", "error.label.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ragioneSociale", "error.label.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mailPec", "error.label.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tipo", "error.label.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codiceFiscaleResponsabile", "error.label.required");

		if (aziendaEditForm.getComuneSede() == 0 || aziendaEditForm.getComuneSede() == -1) {
			errors.rejectValue("comuneSede", "error.label.required");
		}

		if (com.liferay.portal.kernel.util.Validator.isNotNull(aziendaEditForm.getMail()) && !com.liferay.portal.kernel.util.Validator.isEmailAddress(aziendaEditForm.getMail())) {
			errors.rejectValue("mail", "error.label.emailFormat");
		}

		if (!com.liferay.portal.kernel.util.Validator.isEmailAddress(aziendaEditForm.getMailPec())) {
			errors.rejectValue("mailPec", "error.label.emailFormat");
		}
	}
}
