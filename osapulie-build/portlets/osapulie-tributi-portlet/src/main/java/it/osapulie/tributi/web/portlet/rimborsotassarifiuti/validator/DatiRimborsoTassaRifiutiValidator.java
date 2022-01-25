package it.osapulie.tributi.web.portlet.rimborsotassarifiuti.validator;

import org.iban4j.IbanUtil;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.osapulie.infrastructure.impl.DateUtils;
import it.osapulie.tributi.web.portlet.rimborsotassarifiuti.form.DatiRimborsoTassaRifiuti;

/**
 * Form validator per {@link DatiRimborsoTassaRifiuti}.
 *
 * @author Maria Michela Birtolo
 */
@Component("datiRimborsoTassaRifiutiValidator")
public class DatiRimborsoTassaRifiutiValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return DatiRimborsoTassaRifiuti.class.isAssignableFrom(clazz);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		DatiRimborsoTassaRifiuti dati = (DatiRimborsoTassaRifiuti) target;

		if (!dati.getCognome().equals("")) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codiceFiscale", "NotEmpty.field.required");
			validateCF(errors, dati.getCodiceFiscale(), "codiceFiscale");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataNascita", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comuneNascita", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "provinciaNascita", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sesso", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "indirizzoResidenza", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comuneResidenza", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "provResidenza", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "capResidenza", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefono", "NotEmpty.field.required");
		}
		if (!dati.getRagSociale().equals("")) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "naturaGiuridica", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codiceFiscaleGiu", "NotEmpty.field.required");
			validateCF(errors, dati.getCodiceFiscaleGiu(), "codiceFiscaleGiu");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pIva", "NotEmpty.field.required");
			validatePIVA(errors, dati.getpIva(), "pIva");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sedeLegale", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comune", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "provincia", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cap", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefonoGiu", "NotEmpty.field.required");
		}
		if (!dati.getCognomeRapp().equals("")) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nomeRapp", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codiceFiscaleRapp", "NotEmpty.field.required");
			validateCF(errors, dati.getCodiceFiscaleRapp(), "codiceFiscaleRapp");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataNascitaRapp", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comuneNascitaRapp", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "provinciaNascitaRapp", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sessoRapp", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "indirizzoResidenzaRapp", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comuneResidenzaRapp", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "provResidenzaRapp", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "capResidenzaRapp", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefonoRapp", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "naturaCarica", "NotEmpty.field.required");
		}

		if (dati.getCognome().equals("") && dati.getCognomeRapp().equals("") && dati.getRagSociale().equals("")) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome", "NotEmpty.field.required");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "indirizzo1", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mq1", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dovuto1", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "versato1", "NotEmpty.field.required");
		validateStringToDouble(errors, dati.getMq1(), "mq1");
		validateStringToDouble(errors, dati.getDovuto1(), "dovuto1");
		validateStringToDouble(errors, dati.getVersato1(), "versato1");

		if (!"".equals(dati.getIndirizzo2()) || !"".equals(dati.getMq2()) || !"".equals(dati.getDovuto2()) || !"".equals(dati.getVersato2())) {
			validateStringToDouble(errors, dati.getMq2(), "mq2");
			validateStringToDouble(errors, dati.getDovuto2(), "dovuto2");
			validateStringToDouble(errors, dati.getVersato2(), "versato2");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "indirizzo2", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mq2", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dovuto2", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "versato2", "NotEmpty.field.required");
		}
		if (!"".equals(dati.getIndirizzo3()) || !"".equals(dati.getMq3()) || !"".equals(dati.getDovuto3()) || !"".equals(dati.getVersato3())) {
			validateStringToDouble(errors, dati.getMq3(), "mq3");
			validateStringToDouble(errors, dati.getDovuto3(), "dovuto3");
			validateStringToDouble(errors, dati.getVersato3(), "versato3");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "indirizzo3", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mq3", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dovuto3", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "versato3", "NotEmpty.field.required");
		}
		if (!"".equals(dati.getIndirizzo4()) || !"".equals(dati.getMq4()) || !"".equals(dati.getDovuto4()) || !"".equals(dati.getVersato4())) {
			validateStringToDouble(errors, dati.getMq4(), "mq4");
			validateStringToDouble(errors, dati.getDovuto4(), "dovuto4");
			validateStringToDouble(errors, dati.getVersato4(), "versato4");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "indirizzo4", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mq4", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dovuto4", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "versato4", "NotEmpty.field.required");
		}
		if (!"".equals(dati.getIndirizzo5()) || !"".equals(dati.getMq5()) || !"".equals(dati.getDovuto5()) || !"".equals(dati.getVersato5())) {
			validateStringToDouble(errors, dati.getMq5(), "mq5");
			validateStringToDouble(errors, dati.getDovuto5(), "dovuto5");
			validateStringToDouble(errors, dati.getVersato5(), "versato5");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "indirizzo5", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mq5", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dovuto5", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "versato5", "NotEmpty.field.required");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "motivo", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rimborso", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "anno", "NotEmpty.field.required");

		// controllo la correttezza delle date
		if (!dati.getDataNascita().equals("") && !DateUtils.isData(dati.getDataNascita())) {
			errors.rejectValue("dataNascita", "NotIsDate.field.required");
		}

		if (!dati.getDataNascitaRapp().equals("") && !DateUtils.isData(dati.getDataNascitaRapp())) {
			errors.rejectValue("dataNascitaRapp", "NotIsDate.field.required");
		}

		if (dati.isAccredito() == false && dati.isAssegno() == false && dati.isMandato() == false) {
			errors.rejectValue("mandato", "NotEmpty.field.required");
		}

		if ((dati.isAccredito() == true && dati.isAssegno() == true) || (dati.isAccredito() == true && dati.isMandato() == true) || (dati.isAssegno() == true && dati.isMandato() == true)) {
			errors.rejectValue("mandato", "SingleSelect.field.required");
		}
		if (dati.isAccredito() == true) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "banca", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "filiale", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "iban", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "intestatario", "NotEmpty.field.required");

			if (!"".equalsIgnoreCase(dati.getIban())) {
				try {
					IbanUtil.validate(dati.getIban());
				}
				catch (Exception e) {
					errors.rejectValue("iban", "NotIsIban.field.required");
				}
			}
		}
	}

	private void validateStringToDouble(Errors errors, String value, String field) {
		if (!"".equalsIgnoreCase(value)) {
			try {
				Double.parseDouble(value);
			}
			catch (NumberFormatException e) {
				errors.rejectValue(field, "typeMismatch");
			}
		}
	}

	private void validateCF(Errors errors, String cf, String field) {
		if (!"".equalsIgnoreCase(cf)) {
			int i, s, c;
			String cf2;
			int setdisp[] = { 1, 0, 5, 7, 9, 13, 15, 17, 19, 21, 2, 4, 18, 20, 11, 3, 6, 8, 12, 14, 16, 10, 22, 25, 24, 23 };
			if (cf.length() != 16) {
				errors.rejectValue(field, "CodFisFormat.field.required");
				return;
			}
			cf2 = cf.toUpperCase();
			for (i = 0; i < 16; i++) {
				c = cf2.charAt(i);
				if (!(c >= '0' && c <= '9' || c >= 'A' && c <= 'Z')) {
					errors.rejectValue(field, "CodFisFormat.field.required");
					return;
				}
			}
			s = 0;
			for (i = 1; i <= 13; i += 2) {
				c = cf2.charAt(i);
				if (c >= '0' && c <= '9')
					s = s + c - '0';
				else
					s = s + c - 'A';
			}
			for (i = 0; i <= 14; i += 2) {
				c = cf2.charAt(i);
				if (c >= '0' && c <= '9')
					c = c - '0' + 'A';
				s = s + setdisp[c - 'A'];
			}
			if (s % 26 + 'A' != cf2.charAt(15)) {
				errors.rejectValue(field, "CodFisFormat.field.required");
				return;
			}
		}
	}

	private void validatePIVA(Errors errors, String pi, String field) {
		if (!"".equalsIgnoreCase(pi)) {
			int i, c, s;
			if (pi.length() != 11) {
				errors.rejectValue(field, "ParIvaFormat.field.required");
				return;
			}
			for (i = 0; i < 11; i++) {
				if (pi.charAt(i) < '0' || pi.charAt(i) > '9') {
					errors.rejectValue(field, "ParIvaFormat.field.required");
					return;
				}
			}
			s = 0;
			for (i = 0; i <= 9; i += 2)
				s += pi.charAt(i) - '0';
			for (i = 1; i <= 9; i += 2) {
				c = 2 * (pi.charAt(i) - '0');
				if (c > 9)
					c = c - 9;
				s += c;
			}
			if ((10 - s % 10) % 10 != pi.charAt(10) - '0') {
				errors.rejectValue(field, "ParIvaFormat.field.required");
				return;
			}
		}
	}
}
