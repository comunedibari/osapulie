/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 * 
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.profiloutente.web.portlet.profiloutente.validator;

import it.osapulie.domain.Pin;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Form validator per {@link Pin}.
 * 
 * @author Gianluca Pindinelli
 */
@Component("pinValidator")
public class PinValidator implements Validator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return Pin.class.equals(clazz);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {

		Pin pin = (Pin) target;

		if (pin.getComuneIsa() == null || pin.getComuneIsa().getId() == 0) {
			errors.rejectValue("comuneIsa.id", "error.label.comuneRequired");
		}

	}
}
