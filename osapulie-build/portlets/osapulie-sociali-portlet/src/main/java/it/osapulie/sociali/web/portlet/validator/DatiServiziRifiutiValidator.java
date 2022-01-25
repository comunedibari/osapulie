package it.osapulie.sociali.web.portlet.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.osapulie.sociali.web.portlet.model.DatiServiziRifiuti;

@Component("datiServiziRifiuti")
public class DatiServiziRifiutiValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return DatiServiziRifiuti.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		DatiServiziRifiuti dati = (DatiServiziRifiuti) target;
		ValidationUtils.rejectIfEmptyOrWhitespace( errors, "telefono", "NotEmpty.field.required" );
		ValidationUtils.rejectIfEmptyOrWhitespace( errors, "email", "NotEmpty.field.required" );
		
		if(dati.getEmail()!=null && !dati.getEmail().equals("")){
				 String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
					 if (!Pattern.matches(regex, dati.getEmail())){
							errors.rejectValue("email","Email.field.format");
		}		
		}

	}

}
