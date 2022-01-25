package it.osapulie.tributi.web.portlet.variazioneimu.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.osapulie.infrastructure.impl.DateUtils;
import it.osapulie.tributi.web.portlet.variazioneimu.form.DatiVariazioneImu;

/**
 * Form validator per {@link DatiVariazioneImu}.
 *
 * @author Maria Michela Birtolo
 * @author Gianluca Pindinelli
 */
@Component("datiVariazioneImuValidator")
public class DatiVariazioneImuValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return DatiVariazioneImu.class.isAssignableFrom(clazz);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		DatiVariazioneImu dati = (DatiVariazioneImu) target;

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
		if (!dati.getDataNascita().equals("") && !DateUtils.isData(dati.getDataNascita())) {
			errors.rejectValue("dataNascita", "NotIsDate.field.required");
		}
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
				if (!Pattern.matches(regexCodiceFiscale, dati.getCodiceFiscaleDic())) {
					errors.rejectValue("codiceFiscaleDic", "CodFisFormat.field.required");
				}
			}
			if (dati.getEmailDic() != null && !dati.getEmailDic().equals("")) {
				if (!Pattern.matches(regexEmail, dati.getEmailDic())) {
					errors.rejectValue("emailDic", "EmailFormat.field.error");
				}
			}
		}

		// controllo campi obbligatori per ogni contitolare inserito
		if (dati.getContitolari() != null && dati.getContitolari().size() > 0) {
			for (int i = 0; i < dati.getContitolari().size(); i++) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contitolari[" + i + "].nome", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contitolari[" + i + "].cognome", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contitolari[" + i + "].codiceFiscale", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contitolari[" + i + "].dataNascita", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contitolari[" + i + "].comuneNascita", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contitolari[" + i + "].provinciaNascita", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contitolari[" + i + "].sesso", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contitolari[" + i + "].indirizzoResidenza", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contitolari[" + i + "].comuneResidenza", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contitolari[" + i + "].provResidenza", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contitolari[" + i + "].civico", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contitolari[" + i + "].percentualePossesso", "NotEmpty.field.required");
				if (!dati.getContitolari().get(i).getDataNascita().equals("") && !DateUtils.isData(dati.getContitolari().get(i).getDataNascita())) {
					errors.rejectValue("contitolari[" + i + "].dataNascita", "NotIsDate.field.required");
				}
				if (dati.getContitolari().get(i).getCodiceFiscale() != null && !dati.getContitolari().get(i).getCodiceFiscale().equals("")) {
					if (!Pattern.matches(regexCodiceFiscale, dati.getContitolari().get(i).getCodiceFiscale())) {
						errors.rejectValue("contitolari[" + i + "].codiceFiscale", "CodFisFormat.field.required");
					}
				}
				if (dati.getContitolari().get(i).getEmail() != null && !dati.getContitolari().get(i).getEmail().equals("")) {
					if (!Pattern.matches(regexEmail, dati.getContitolari().get(i).getEmail())) {
						errors.rejectValue("contitolari[" + i + "].email", "EmailFormat.field.error");
					}
				}
			}
		}

		// controllo campi obbligatori per immobili
		if (dati.getPosizioni() != null && dati.getPosizioni().size() > 0) {
			for (int i = 0; i < dati.getPosizioni().size(); i++) {
				if (dati.getPosizioni().get(i).isDiAcquisto() == true && dati.getPosizioni().get(i).isDiCessione() == true) {
					errors.rejectValue("posizioni[" + i + "].diCessione", "SingleSelect.field.required");
				}
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "posizioni[" + i + "].caratteristicaImmobile", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "posizioni[" + i + "].indirizzoUtenza", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "posizioni[" + i + "].valoreImmobile", "NotEmpty.field.required");
				if (!dati.getPosizioni().get(i).getDataInizioPossesso().equals("") && !DateUtils.isData(dati.getPosizioni().get(i).getDataInizioPossesso())) {
					errors.rejectValue("posizioni[" + i + "].dataInizioPossesso", "NotIsDate.field.required");
				}
				if (!dati.getPosizioni().get(i).getDataUltimazioneLavori().equals("") && !DateUtils.isData(dati.getPosizioni().get(i).getDataUltimazioneLavori())) {
					errors.rejectValue("posizioni[" + i + "].dataUltimazioneLavori", "NotIsDate.field.required");
				}
			}
		}
	}
}
