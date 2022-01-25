package it.osapulie.tributi.web.portlet.variazionetasi.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.osapulie.infrastructure.impl.DateUtils;
import it.osapulie.tributi.web.portlet.variazionetasi.form.DatiVariazioneTasi;

/**
 * Form validator per {@link DatiVariazioneTasi}.
 *
 * @author Maria Michela Birtolo
 * @author Gianluca Pindinelli
 */
@Component("datiVariazioneTasiValidator")
public class DatiVariazioneTasiValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return DatiVariazioneTasi.class.isAssignableFrom(clazz);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		DatiVariazioneTasi dati = (DatiVariazioneTasi) target;

		final String regexCodiceFiscale = "[a-zA-Z]{6}[0-9]{2}[a-zA-Z][0-9]{2}[a-zA-Z][0-9]{3}[a-zA-Z]";
		final String regexEmail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		// controllo campi obbligatori
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codiceFiscale", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataNascita", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comuneNascita", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "provinciaNascita", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sesso", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "indirizzoResidenza", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comuneResidenza", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "provResidenza", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "capResidenza", "NotEmpty.field.required");

		if (dati.getCodiceFiscale() != null && !dati.getCodiceFiscale().equals("")) {
			if (!Pattern.matches(regexCodiceFiscale, dati.getCodiceFiscale())) {
				errors.rejectValue("codiceFiscale", "CodFisFormat.field.required");
			}
		}
		if (dati.getEmail() != null && !dati.getEmail().equals("")) {
			if (!Pattern.matches(regexEmail, dati.getEmail())) {
				errors.rejectValue("email", "EmailFormat.field.error");
			}
		}
		if (!dati.getDataNascita().equals("") && !DateUtils.isData(dati.getDataNascita())) {
			errors.rejectValue("dataNascita", "NotIsDate.field.required");
		}

		if (dati.getCognomeDic() != null && !dati.getCognomeDic().equals("")) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nomeDic", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognomeDic", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataNascitaDic", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codiceFiscaleDic", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comuneNascitaDic", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "provinciaNascitaDic", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sessoDic", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "indirizzoResidenzaDic", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comuneResidenzaDic", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "provResidenzaDic", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "capResidenzaDic", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "civicoDic", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "naturaCarica", "NotEmpty.field.required");
			if (!dati.getDataNascitaDic().equals("") && !DateUtils.isData(dati.getDataNascitaDic())) {
				errors.rejectValue("dataNascitaDic", "NotIsDate.field.required");
			}
			if (dati.getCodiceFiscaleDic() != null && !dati.getCodiceFiscaleDic().equals("")) {
				if (!Pattern.matches(regexCodiceFiscale, dati.getCodiceFiscale())) {
					errors.rejectValue("codiceFiscaleDic", "CodFisFormat.field.required");
				}
			}
			if (dati.getEmailDic() != null && !dati.getEmailDic().equals("")) {
				if (!Pattern.matches(regexEmail, dati.getEmailDic())) {
					errors.rejectValue("emailDic", "EmailFormat.field.error");
				}
			}
		}

		// controllo campi obbligatori per immobili
		if (dati.getPosizioni() != null && dati.getPosizioni().size() > 0) {
			for (int i = 0; i < dati.getPosizioni().size(); i++) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "posizioni[" + i + "].indirizzoUtenza", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "posizioni[" + i + "].valoreImmobile", "NotEmpty.field.required");
				if (!dati.getPosizioni().get(i).getDataInizioOccupazione().equals("") && !DateUtils.isData(dati.getPosizioni().get(i).getDataInizioOccupazione())) {
					errors.rejectValue("posizioni[" + i + "].dataInizioOccupazione", "NotIsDate.field.required");
				}
				if (dati.getPosizioni().get(i).getTitoloProprietà() != null && dati.getPosizioni().get(i).getTitoloProprietà().equalsIgnoreCase("ALTRO")
						&& (dati.getPosizioni().get(i).getAltroTitoloProprietà() == null || dati.getPosizioni().get(i).getAltroTitoloProprietà().equals(""))) {
					errors.rejectValue("posizioni[" + i + "].altroTitoloProprietà", "NotEmpty.field.required");
				}
				if (dati.getPosizioni().get(i).getDestinazioneProprietà() != null && dati.getPosizioni().get(i).getDestinazioneProprietà().equalsIgnoreCase("ALTRO")
						&& (dati.getPosizioni().get(i).getAltroDestinazioneProprietà() == null || dati.getPosizioni().get(i).getAltroDestinazioneProprietà().equals(""))) {
					errors.rejectValue("posizioni[" + i + "].altroDestinazioneProprietà", "NotEmpty.field.required");
				}
			}
		}

		// controllo campi obbligatori per ogni familiare inserito
		if (dati.getFamiliari() != null && dati.getFamiliari().size() > 0) {
			for (int i = 0; i < dati.getFamiliari().size(); i++) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "familiari[" + i + "].nome", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "familiari[" + i + "].cognome", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "familiari[" + i + "].dataNascitaString", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "familiari[" + i + "].sesso", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "familiari[" + i + "].parentela", "NotEmpty.field.required");
				if (!dati.getFamiliari().get(i).getDataNascitaString().equals("") && !DateUtils.isData(dati.getFamiliari().get(i).getDataNascitaString())) {
					errors.rejectValue("familiari[" + i + "].dataNascitaString", "NotIsDate.field.required");
				}
			}
		}
	}
}
