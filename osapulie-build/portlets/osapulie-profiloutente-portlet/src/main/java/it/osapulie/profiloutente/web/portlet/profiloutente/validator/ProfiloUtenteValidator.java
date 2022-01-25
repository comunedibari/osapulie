/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.profiloutente.web.portlet.profiloutente.validator;

import javax.inject.Inject;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.osapulie.profiloutente.model.view.ProfiloUtenteModel;
import it.osapulie.shared.enumeration.DeploymentScenario;
import it.osapulie.web.portlet.util.PortletConstants;
import it.osapulie.web.portlet.util.PortletHelper;

/**
 * Form validator per {@link ProfiloUtenteModel}.
 *
 * @author Gianluca Pindinelli
 */
@Component("profiloUtenteValidator")
public class ProfiloUtenteValidator implements Validator {

	@Inject
	private PortletHelper helper;

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return ProfiloUtenteModel.class.equals(clazz);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {

		ProfiloUtenteModel profiloUtenteModel = (ProfiloUtenteModel) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "error.label.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome", "error.label.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mail", "error.label.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codiceFiscale", "error.label.required");

		// E' possibile modificare la username e la password solo se non si è autenticati in modo
		// forte o se lo scenario IDP è disabilitato
		if (!profiloUtenteModel.isAutenticatoForte() && helper.getDeploymentScenario() != DeploymentScenario.SCENARIO_3.getScenario()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.label.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.label.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rePassword", "error.label.required");
		}

		if (!com.liferay.portal.kernel.util.Validator.isEmailAddress(profiloUtenteModel.getMail())) {
			errors.rejectValue("mail", "error.label.emailFormat");
		}
		if (helper.getDeploymentScenario() != DeploymentScenario.SCENARIO_3.getScenario() && com.liferay.portal.kernel.util.Validator.isNotNull(profiloUtenteModel.getMailPec())
				&& !com.liferay.portal.kernel.util.Validator.isEmailAddress(profiloUtenteModel.getMailPec())) {
			errors.rejectValue("mailPec", "error.label.emailFormat");
		}

		if (com.liferay.portal.kernel.util.Validator.isNotNull(profiloUtenteModel.getUsername()) && (profiloUtenteModel.getUsername().length() != 16)) {
			errors.rejectValue("username", "error.label.usernameFormat");
		}

		if (!profiloUtenteModel.isAutenticatoForte()) {
			if (com.liferay.portal.kernel.util.Validator.isNotNull(profiloUtenteModel.getPassword()) && com.liferay.portal.kernel.util.Validator.isNotNull(profiloUtenteModel.getRePassword())) {
				if (!profiloUtenteModel.getPassword().equals(profiloUtenteModel.getRePassword())) {
					errors.rejectValue("password", "error.label.samePasswordRequired");
				}
			}
		}

		// Validazione campo PEC in caso di scelta canale comunicazione
		if (profiloUtenteModel.getCanaleComunicazione().equals(PortletConstants.CANALE_COMUNICAZIONE_PEC) && com.liferay.portal.kernel.util.Validator.isNull(profiloUtenteModel.getMailPec())) {
			errors.rejectValue("mailPec", "error.label.required");
		}

	}
}
