package it.osapulie.sociali.web.portlet.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.osapulie.sociali.web.portlet.model.DatiRichiestaTrasporto;

@Component("datiRichiestaTrasportoValidator")
public class DatiRichiestaTrasportoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return DatiRichiestaTrasporto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		DatiRichiestaTrasporto dati = (DatiRichiestaTrasporto) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace( errors, "disTelefono", "NotEmpty.field.required" );
		ValidationUtils.rejectIfEmptyOrWhitespace( errors, "disEmail", "NotEmpty.field.required" );
		ValidationUtils.rejectIfEmptyOrWhitespace( errors, "annoIsee", "NotEmpty.field.required" );
		ValidationUtils.rejectIfEmptyOrWhitespace( errors, "isee", "NotEmpty.field.required" );
		ValidationUtils.rejectIfEmptyOrWhitespace( errors, "sportello", "NotEmpty.field.required" );
		ValidationUtils.rejectIfEmptyOrWhitespace( errors, "indirizzoSportello", "NotEmpty.field.required" );
		
		if(!dati.getTipoRichiedente().equalsIgnoreCase("a titolo personale")){
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
		
		if(dati.getIsee()!=null && !dati.getIsee().equals("")){
			if(!isNumeric(dati.getIsee())){
				errors.rejectValue( "isee", "Number.field.format" );			
			}
		}
		
		if(dati.getAnnoIsee()!=null && !dati.getAnnoIsee().equals("")){
			if(!isYear(dati.getAnnoIsee())){
				errors.rejectValue( "annoIsee", "NotIsYear.field.required" );			
			}
		}
		
		if(dati.getDisEmail()!=null && !dati.getDisEmail().equals("")){
				 String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
					 if (!Pattern.matches(regex, dati.getDisEmail())){
							errors.rejectValue("disEmail","Email.field.format");
					   }
		}
	}

	
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
        Double.parseDouble(str);  
	  }catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
	private static boolean isYear(String year){
		try
	        {
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
	            sdf.setLenient(false);
	            sdf.parse(year);
	        }
	        catch(ParseException pe)
	        {
	        	return false;
	        }
        return true;
	}
	
}
