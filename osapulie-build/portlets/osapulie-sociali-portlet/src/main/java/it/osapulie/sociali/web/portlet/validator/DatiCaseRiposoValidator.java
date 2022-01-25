package it.osapulie.sociali.web.portlet.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.osapulie.sociali.web.portlet.model.DatiDichiarazioneCaseRiposo;

@Component("datiCaseRiposoValidator")
public class DatiCaseRiposoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return DatiDichiarazioneCaseRiposo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		DatiDichiarazioneCaseRiposo dati= (DatiDichiarazioneCaseRiposo) target;
		
		if(dati.getTipologia()==null){
			errors.rejectValue( "tipologia", "NotEmpty.field.required" );	
		}
		
		if(!dati.getRuolo().equalsIgnoreCase("a titolo personale")){
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "anzCognome", "NotEmpty.field.required" );
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "anzNome", "NotEmpty.field.required" );
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "anzDataNascita", "NotEmpty.field.required" );
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "anzComuneNascita", "NotEmpty.field.required" );
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "anzProvinciaNascita", "NotEmpty.field.required" );
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "anzComuneResidenza", "NotEmpty.field.required" );
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "anzProvinciaResidenza", "NotEmpty.field.required" );
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "anzIndirizzoResidenza", "NotEmpty.field.required" );
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "anzCivicoResidenza", "NotEmpty.field.required" );
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "anzTelefono", "NotEmpty.field.required" );
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
		
		if(!dati.isAppartamentoProprio()) {
			if(dati.getCanoneAppartamento()==null || dati.getCanoneAppartamento().equals("")){
				ValidationUtils.rejectIfEmptyOrWhitespace( errors, "canoneAppartamento", "NotEmpty.field.required" );
			}else{
				if(!isNumeric(dati.getCanoneAppartamento())){
					errors.rejectValue( "canoneAppartamento", "Number.field.format" );			
				}
			}
		}
	}
	
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
        Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}

}
