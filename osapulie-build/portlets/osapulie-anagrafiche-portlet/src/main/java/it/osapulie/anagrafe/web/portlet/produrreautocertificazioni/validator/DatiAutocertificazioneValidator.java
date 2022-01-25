package it.osapulie.anagrafe.web.portlet.produrreautocertificazioni.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import it.osapulie.anagrafe.web.portlet.produrreautocertificazioni.form.DatiAutocertificazione;
import it.osapulie.infrastructure.impl.DateUtils;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class DatiAutocertificazioneValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return DatiAutocertificazione.class.isAssignableFrom( clazz );

	}

	@Override
	public void validate(Object target, Errors errors) {
		DatiAutocertificazione dati = (DatiAutocertificazione) target;
		
		if(dati.getTipologia().equals("GENERICA") || dati.getTipologia().equals("ATTONOTORIO")){
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "generica", "NotEmpty.field.required" );

		}
		
		if(dati.getTipologia().equals("FIGLIO") || dati.getTipologia().equals("DECESSO")|| dati.getTipologia().equals("CURATORE")){
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "nome", "NotEmpty.field.required" );
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "cognome", "NotEmpty.field.required" );
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "dataNascita", "NotEmpty.field.required" );
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "comuneNascita", "NotEmpty.field.required" );
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "provinciaNascita", "NotEmpty.field.required" );
			/*
			 * Validazione date
			 * 
			 * */
			if (!dati.getDataNascita().equals( "" ) && !DateUtils.isData( dati.getDataNascita() )) {
				errors.rejectValue( "dataNascita", "NotIsDate.field.required" );
			}
			
		}
		if(dati.getTipologia().equals("DECESSO")||dati.getTipologia().equals("CURATORE")){
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "comuneResidenza", "NotEmpty.field.required" );
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "provinciaResidenza", "NotEmpty.field.required" );
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "viaResidenza", "NotEmpty.field.required" );
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "civicoResidenza", "NotEmpty.field.required" );
			}
		if(dati.getTipologia().equals("DECESSO")){
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "parentela", "NotEmpty.field.required" );
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "dataMorte", "NotEmpty.field.required" );
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "comuneMorte", "NotEmpty.field.required" );
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "provinciaMorte", "NotEmpty.field.required" );
			if (!dati.getDataMorte().equals( "" ) && !DateUtils.isData( dati.getDataMorte() )) {
				errors.rejectValue( "dataMorte", "NotIsDate.field.required" );			
			}
			}
		if(dati.getTipologia().equals("REDDITO")||dati.getTipologia().equals("DISOCCUPAZIONE")){
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "anno", "NotEmpty.field.required" );
			if(!isYear(dati.getAnno())){
				errors.rejectValue("anno","NotIsYear.field.required");
			}
			
			}
		if(dati.getTipologia().equals("REDDITO")){
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "reddito", "NotEmpty.field.required" );
			}
		if(dati.getTipologia().equals("LEVA")){
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "leva", "NotEmpty.field.required" );
			}
		if(dati.getTipologia().equals("ALBI")){
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "albo", "NotEmpty.field.required" );
			}
		if(dati.getTipologia().equals("PIVA")){
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "iva", "NotEmpty.field.required" );
			}
		if(dati.getTipologia().equals("CODFISC")){
			 String regex = "[a-zA-Z]{6}[0-9]{2}[a-zA-Z][0-9]{2}[a-zA-Z][0-9]{3}[a-zA-Z]";

			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "codiceFiscale", "NotEmpty.field.required" );
			if(!dati.getCodiceFiscale().equals("")){
				 if (!Pattern.matches(regex, dati.getCodiceFiscale()))
				   {
						errors.rejectValue("codiceFiscale","CodFisFormat.field.required");
				   }
				}
			}
}
	
	private  boolean isYear(String year){
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
