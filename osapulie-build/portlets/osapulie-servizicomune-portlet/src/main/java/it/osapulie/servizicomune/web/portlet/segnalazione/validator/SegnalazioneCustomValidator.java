/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.servizicomune.web.portlet.segnalazione.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.osapulie.servizicomune.web.portlet.segnalazione.form.SegnalazioneCustomForm;

/**
 * Form validator per {@link SegnalazioneCustomForm}.
 *
 * @author Gianluca Pindinelli
 */
@Component("segnalazioneCustomValidator")
public class SegnalazioneCustomValidator implements Validator {

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return SegnalazioneCustomForm.class.equals(clazz);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "motivazione", "error.label.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "note", "error.label.required");
	}
}
