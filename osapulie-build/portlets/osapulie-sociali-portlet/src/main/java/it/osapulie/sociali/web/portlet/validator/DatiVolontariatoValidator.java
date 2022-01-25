package it.osapulie.sociali.web.portlet.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.osapulie.sociali.web.portlet.model.DatiVolontariato;

@Component("datiVolontariatoValidator")
public class DatiVolontariatoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return DatiVolontariato.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace( errors, "organizzazione", "NotEmpty.field.required" );
		ValidationUtils.rejectIfEmptyOrWhitespace( errors, "indirizzo", "NotEmpty.field.required" );
		ValidationUtils.rejectIfEmptyOrWhitespace( errors, "comuneCostituzione", "NotEmpty.field.required" );
		ValidationUtils.rejectIfEmptyOrWhitespace( errors, "provCostituzione", "NotEmpty.field.required" );
		ValidationUtils.rejectIfEmptyOrWhitespace( errors, "telefono", "NotEmpty.field.required" );
		ValidationUtils.rejectIfEmptyOrWhitespace( errors, "cfAssociazione", "NotEmpty.field.required" );
		ValidationUtils.rejectIfEmptyOrWhitespace( errors, "dataCostituzione", "NotEmpty.field.required" );
	}

}
