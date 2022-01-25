package it.osapulie.tributi.web.portlet.cessazionetari.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.osapulie.tributi.web.portlet.cessazionetari.form.DatiCessazioneTari;

/**
 * Form validator per {@link DatiCessazioneTari}.
 *
 * @author Federico Melli
 */
@Component("datiCessazioneTariValidator")
public class DatiCessazioneTariValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return DatiCessazioneTari.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		DatiCessazioneTari dati = (DatiCessazioneTari) target;

		// controllo campi obbligatori
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codiceFiscale", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataNascita", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comuneNascita", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "provinciaNascita", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "indirizzoResidenza", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comuneResidenza", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "provResidenza", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tipoUtenza", "NotEmpty.field.required");

		// Immobile
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "viaUnitaImmobiliare", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "civicoUnitaImmobiliare", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fgUnitaImmobiliare", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numUnitaImmobiliare", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subUnitaImmobiliare", "NotEmpty.field.required");

		// motivi
		if (dati.getMotivi().length < 1) {
			errors.rejectValue("motivi", "error.motivazioni.notempty");
		}

	}

}
