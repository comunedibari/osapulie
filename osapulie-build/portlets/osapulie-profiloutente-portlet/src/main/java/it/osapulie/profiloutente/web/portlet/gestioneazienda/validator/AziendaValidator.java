/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.profiloutente.web.portlet.gestioneazienda.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.osapulie.domain.Azienda;
import it.osapulie.profiloutente.model.view.AziendaModel;
import it.osapulie.service.AziendaService;
import it.osapulie.web.portlet.util.PortletUtils;

/**
 * Form validator per {@link AziendaModel} (per la form relativa al profilo utente professionista).
 *
 * @author Gianluca Pindinelli
 */
@Component("aziendaValidator")
public class AziendaValidator implements Validator {

	@Autowired
	private AziendaService aziendaService;

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return AziendaModel.class.equals(clazz);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {

		AziendaModel aziendaModel = (AziendaModel) target;

		String partitaIva = aziendaModel.getPartitaIva();
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
		if (aziendaModel.getIdAzienda() == 0 && partitaIva != null) {
			Azienda profiloUtenteProfessionistaByPiva = aziendaService.getAziendaByPiva(partitaIva);
			if (profiloUtenteProfessionistaByPiva != null) {
				errors.rejectValue("partitaIva", "error.label.pivaDuplicate");
			}
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "partitaIva", "error.label.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ragioneSociale", "error.label.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "viaSede", "error.label.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nrCivicoSede", "error.label.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mailPec", "error.label.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tipoAzienda", "error.label.required");

		if (aziendaModel.getComuneSede() == 0 || aziendaModel.getComuneSede() == -1) {
			errors.rejectValue("comuneSede", "error.label.required");
		}

		if (com.liferay.portal.kernel.util.Validator.isNotNull(aziendaModel.getMail()) && !com.liferay.portal.kernel.util.Validator.isEmailAddress(aziendaModel.getMail())) {
			errors.rejectValue("mail", "error.label.emailFormat");
		}

		if (!com.liferay.portal.kernel.util.Validator.isEmailAddress(aziendaModel.getMailPec())) {
			errors.rejectValue("mailPec", "error.label.emailFormat");
		}
	}
}
