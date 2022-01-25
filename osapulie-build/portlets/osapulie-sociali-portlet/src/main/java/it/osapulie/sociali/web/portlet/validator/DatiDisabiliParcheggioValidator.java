package it.osapulie.sociali.web.portlet.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.osapulie.sociali.web.portlet.model.DatiDisabiliParcheggio;

@Component("datiDisabiliParcheggioValidator")
public class DatiDisabiliParcheggioValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return DatiDisabiliParcheggio.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		DatiDisabiliParcheggio dati = (DatiDisabiliParcheggio) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace( errors, "cittadinanza", "NotEmpty.field.required" );
		ValidationUtils.rejectIfEmptyOrWhitespace( errors, "stato", "NotEmpty.field.required" );
		ValidationUtils.rejectIfEmptyOrWhitespace( errors, "capResidenza", "NotEmpty.field.required" );
		ValidationUtils.rejectIfEmptyOrWhitespace( errors, "email", "NotEmpty.field.required" );
		ValidationUtils.rejectIfEmptyOrWhitespace( errors, "telefono", "NotEmpty.field.required" );

		
		if(dati.getRuolo().equalsIgnoreCase("tutore")){
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "disCognome", "NotEmpty.field.required" );
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "disNome", "NotEmpty.field.required" );
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "disDataNascita", "NotEmpty.field.required" );
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "disComuneNasc", "NotEmpty.field.required" );
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "disProvinciaNasc", "NotEmpty.field.required" );
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "disComuneRes", "NotEmpty.field.required" );
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "disProvinciaRes", "NotEmpty.field.required" );
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "disIndirizzoRes", "NotEmpty.field.required" );
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "disNumCivico", "NotEmpty.field.required" );
		}
		
		if(!dati.getRichiesta().equalsIgnoreCase("primo rilascio") && !dati.getRichiesta().equalsIgnoreCase("contrassegno")){
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "numeroPass", "NotEmpty.field.required" );
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "scadenzaPass", "NotEmpty.field.required" );
			
			

		}
	}

}
