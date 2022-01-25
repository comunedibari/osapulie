/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.servizicomune.web.portlet.gestionecomuni.validator;

import javax.inject.Inject;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.osapulie.domain.ComuneISA;
import it.osapulie.persistence.ComuneISARepository;
import it.osapulie.web.portlet.util.PortletConstants;

/**
 * Form validator per {@link ComuneISA}.
 *
 * @author Mario Scalas
 */
@Component("editComuneValidator")
public class ComuneValidator implements Validator {

	@Inject
	private ComuneISARepository repository;

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return ComuneISA.class.equals(clazz);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {

		ComuneISA comuneISA = (ComuneISA) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codiceIstat", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cap", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "uriServizioGateway", "NotEmpty.field.required");

		if (!com.liferay.portal.kernel.util.Validator.isEmailAddress(comuneISA.getPec())) {
			errors.rejectValue("pec", "Email.field.format");
		}

		if (comuneISA.getId() == null) {
			if (repository.findByCodiceIstat(comuneISA.getCodiceIstat()) != null) {
				errors.rejectValue("codiceIstat", "Unique.constraint.failure");
			}
		}

		// Controllo selezione metodologia di comunicazione
		if (comuneISA.getCanaleComunicazione() == null || comuneISA.getCanaleComunicazione().equals("")) {
			errors.rejectValue("canaleComunicazione", "NotEmpty.field.required");
		}

		// Controllo campi obbligatori in base alla metodologia di comunicazione
		if (comuneISA.getCanaleComunicazione() != null && !comuneISA.getCanaleComunicazione().equals("")) {
			if (comuneISA.getCanaleComunicazione().equals(PortletConstants.CANALE_COMUNICAZIONE_PEC)) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pec", "NotEmpty.field.required");
			}
			else if (comuneISA.getCanaleComunicazione().equals(PortletConstants.CANALE_COMUNICAZIONE_PROTOCOLLO)) {
				// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gestoreComune.codiceFiscale",
				// "NotEmpty.field.required");
				// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "uriProtocollo",
				// "NotEmpty.field.required");
				// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idUtenteProtocollo",
				// "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codiceAOO", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amministrazione", "NotEmpty.field.required");
			}
		}

	}
}
