/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.servizicomune.web.portlet.gestioneutenti.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.osapulie.servizicomune.web.portlet.gestioneutenti.form.ProfiloUtenteCittadinoForm;
import it.osapulie.web.portlet.util.PortletUtils;

/**
 * Form validator per {@link ProfiloUtenteCittadinoForm}.
 *
 * @author Antonio Magrì
 * @author Gianluca Pindinelli
 */
@Component("profiloUtenteCittadinoValidator")
public class ProfiloUtenteCittadinoValidator implements Validator {

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return ProfiloUtenteCittadinoForm.class.equals(clazz);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {

		ProfiloUtenteCittadinoForm profiloUtenteCittadinoForm = (ProfiloUtenteCittadinoForm) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "error.label.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome", "error.label.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codiceFiscale", "error.label.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.label.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confermaPassword", "error.label.required");

		if (profiloUtenteCittadinoForm.getEmail() != null && !profiloUtenteCittadinoForm.getEmail().isEmpty()
				&& !com.liferay.portal.kernel.util.Validator.isEmailAddress(profiloUtenteCittadinoForm.getEmail())) {
			errors.rejectValue("email", "error.label.emailFormat");
		}

		if (profiloUtenteCittadinoForm.getPec() != null && !profiloUtenteCittadinoForm.getPec().isEmpty()
				&& !com.liferay.portal.kernel.util.Validator.isEmailAddress(profiloUtenteCittadinoForm.getPec())) {
			errors.rejectValue("pec", "error.label.emailFormat");
		}

		String codiceFiscale = profiloUtenteCittadinoForm.getCodiceFiscale();
		if (com.liferay.portal.kernel.util.Validator.isNotNull(codiceFiscale)) {
			String checkCodiceFiscale = PortletUtils.checkCodiceFiscale(codiceFiscale);
			if (checkCodiceFiscale != null) {
				errors.rejectValue("codiceFiscale", null, checkCodiceFiscale);
			}
		}

		if (!com.liferay.portal.kernel.util.Validator.isPassword(profiloUtenteCittadinoForm.getPassword())) {
			errors.rejectValue("password", "error.label.passwordFormat");
		}

		if (!com.liferay.portal.kernel.util.Validator.isPassword(profiloUtenteCittadinoForm.getConfermaPassword())) {
			errors.rejectValue("confermaPassword", "error.label.passwordFormat");
		}

		if (com.liferay.portal.kernel.util.Validator.isNotNull(profiloUtenteCittadinoForm.getPassword())
				&& com.liferay.portal.kernel.util.Validator.isNotNull(profiloUtenteCittadinoForm.getConfermaPassword())) {
			if (!profiloUtenteCittadinoForm.getPassword().equals(profiloUtenteCittadinoForm.getConfermaPassword())) {
				errors.rejectValue("password", "error.label.samePasswordRequired");
			}
		}
	}
}
