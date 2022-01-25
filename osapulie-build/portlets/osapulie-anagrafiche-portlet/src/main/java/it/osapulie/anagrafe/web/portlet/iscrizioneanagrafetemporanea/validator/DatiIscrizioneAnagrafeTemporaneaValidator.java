package it.osapulie.anagrafe.web.portlet.iscrizioneanagrafetemporanea.validator;

import it.osapulie.anagrafe.web.portlet.iscrizioneanagrafetemporanea.form.DatiIscrizioneAnagrafeTemporanea;
import it.osapulie.infrastructure.impl.DateUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Form validator per {@link DatiIscrizioneAnagrafeTemporanea}.
 * 
 * @author Maria Michela Birtolo
 */
@Component( "datiIscrizioneAnagrafeTemporaneaValidator" )
public class DatiIscrizioneAnagrafeTemporaneaValidator implements Validator {

	@Override
	public boolean supports( Class<?> clazz ) {
		return DatiIscrizioneAnagrafeTemporanea.class.isAssignableFrom( clazz );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate( Object target, Errors errors ) {
		DatiIscrizioneAnagrafeTemporanea dati = (DatiIscrizioneAnagrafeTemporanea) target;

		// controllo campi obbligatori
		ValidationUtils.rejectIfEmptyOrWhitespace( errors, "nome", "NotEmpty.field.required" );
		ValidationUtils.rejectIfEmptyOrWhitespace( errors, "cognome", "NotEmpty.field.required" );
		ValidationUtils.rejectIfEmptyOrWhitespace( errors, "codiceFiscale", "NotEmpty.field.required" );
		ValidationUtils.rejectIfEmptyOrWhitespace( errors, "dataNascita", "NotEmpty.field.required" );
		ValidationUtils.rejectIfEmptyOrWhitespace( errors, "comuneNascita", "NotEmpty.field.required" );
		ValidationUtils.rejectIfEmptyOrWhitespace( errors, "provinciaNascita", "NotEmpty.field.required" );
		ValidationUtils.rejectIfEmptyOrWhitespace( errors, "cittadinanza", "NotEmpty.field.required" );

		ValidationUtils.rejectIfEmptyOrWhitespace( errors, "nuovaVia", "NotEmpty.field.required" );
		ValidationUtils.rejectIfEmptyOrWhitespace( errors, "nuovoNum", "NotEmpty.field.required" );

		if (dati.getComuneIscrizione().equals( "" ) && dati.getStatoEstero().equals( "" )) {
			errors.rejectValue( "comuneIscrizione", "NotEmpty.field.required" );
		}

		// se c'è il comune deve eserci anche l'indirizzo
		if (!dati.getComuneIscrizione().equals( "" )) {
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "indirizzoResidenza", "NotEmpty.field.required" );
		}
		// se c'è lo stato estero devono esserci anche indEstero e i dati sul
		// soggiorno
		if (!dati.getStatoEstero().equals( "" )) {
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "indirizzoEstero", "NotEmpty.field.required" );
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "questura", "NotEmpty.field.required" );
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "dataRilascio", "NotEmpty.field.required" );
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "numSogg", "NotEmpty.field.required" );
		}

		// se inserito un parente devono esserci tutti i relativi campi
		for (int i = 1; i < 7; i++) {
			String par = "getParente" + i;
			try {
				Method metodo = dati.getClass().getDeclaredMethod( par );
				String parente = (String) metodo.invoke( dati );
				if (!parente.equals( "" )) {
					ValidationUtils.rejectIfEmptyOrWhitespace( errors, "data" + i, "NotEmpty.field.required" );
					ValidationUtils.rejectIfEmptyOrWhitespace( errors, "comune" + i, "NotEmpty.field.required" );
					ValidationUtils.rejectIfEmptyOrWhitespace( errors, "parentela" + i, "NotEmpty.field.required" );
				}
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}

		// controllo la correttezza delle date
		if (!dati.getDataNascita().equals( "" ) && !DateUtils.isData( dati.getDataNascita() )) {
			errors.rejectValue( "dataNascita", "NotIsDate.field.required" );
		}
		if (!dati.getDataRilascio().equals( "" ) && !DateUtils.isData( dati.getDataRilascio() )) {
			errors.rejectValue( "dataRilascio", "NotIsDate.field.required" );			
		}
		for (int i = 1; i < 7; i++) {
			String methData = "getData" + i;
			try {
				Method metodo = dati.getClass().getDeclaredMethod( methData );
				String data = (String) metodo.invoke( dati );
				if (!data.equals( "" ) && !DateUtils.isData( data )) {
					errors.rejectValue( "data" + i, "NotIsDate.field.required" );			
				}
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
}
