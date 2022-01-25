package it.osapulie.sociali.web.portlet.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.osapulie.sociali.web.portlet.model.DatiAnzianiAssistenza;

@Component("datiAnzianiAssistenzaValidator")
public class DatiAnzianiAssistenzaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return DatiAnzianiAssistenza.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		DatiAnzianiAssistenza dati = (DatiAnzianiAssistenza) target;
		
		/*
		 * TODO Provvisoriamente i campi "telefono" e "codTesseraSanitaria" sono stati considerati obbligatori,
		 * ma è necessario verificare se il codice di tessera san. è obbligatorio e, se in mancanza di esso,
		 * sono obbligatori i campi relativi al vicino\parente
		 * */
		ValidationUtils.rejectIfEmptyOrWhitespace( errors, "telefono", "NotEmpty.field.required" );
		ValidationUtils.rejectIfEmptyOrWhitespace( errors, "codTesseraSanitaria", "NotEmpty.field.required" );

		
		if(dati.isConiugato()){
			if(dati.getMotiviConiugato()==null || dati.getServiziComune().equals("")){
				ValidationUtils.rejectIfEmptyOrWhitespace( errors, "motiviConiugato", "NotEmpty.field.required" );
			}
		}
		if(dati.isServiziSimiliComune()){
			if(dati.getServiziComune()==null || dati.getServiziComune().equals("")){
				ValidationUtils.rejectIfEmptyOrWhitespace( errors, "serviziComune", "NotEmpty.field.required" );
			}
		}
		
		if(dati.isServiziSimiliEnte()){
			if(dati.getServiziEntiPubblici()==null || dati.getServiziEntiPubblici().equals("")){
				ValidationUtils.rejectIfEmptyOrWhitespace( errors, "serviziEntiPubblici", "NotEmpty.field.required" );
			}
		}
		
	}

}
