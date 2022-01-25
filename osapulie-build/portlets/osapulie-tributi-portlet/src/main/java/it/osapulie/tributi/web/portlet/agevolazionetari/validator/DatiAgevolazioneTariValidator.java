package it.osapulie.tributi.web.portlet.agevolazionetari.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.osapulie.infrastructure.impl.DateUtils;
import it.osapulie.tributi.web.portlet.agevolazionetari.form.DatiAgevolazioneTari;
import it.osapulie.tributi.web.portlet.agevolazionetari.form.DatiImmobileAgevolazioneTari;

/**
 * Form validator per {@link DatiAgevolazioneTari}.
 *
 * @author Maria Michela Birtolo
 */
@Component("datiAgevolazioneTariValidator")
public class DatiAgevolazioneTariValidator implements Validator {

	private final Logger log = LoggerFactory.getLogger(DatiAgevolazioneTariValidator.class);

	@Override
	public boolean supports(Class<?> clazz) {
		return DatiAgevolazioneTari.class.isAssignableFrom(clazz);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		DatiAgevolazioneTari dati = (DatiAgevolazioneTari) target;

		if (!dati.getCognome().equals("")) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codiceFiscale", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sesso", "NotEmpty.field.required");
			validateCF(errors, dati.getCodiceFiscale(), "codiceFiscale");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idContribuente", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataNascita", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comuneNascita", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "provinciaNascita", "NotEmpty.field.required");

			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "civicoResidenza", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "indirizzoResidenza", "NotEmpty.field.required");

			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comuneResidenza", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "provinciaResidenza", "NotEmpty.field.required");
			// -- ValidationUtils.rejectIfEmptyOrWhitespace(errors, "capResidenza",
			// "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefono", "NotEmpty.field.required");
		}

		if (dati.getCognome().equals("")) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome", "NotEmpty.field.required");
		}

		// controllo la correttezza delle date
		if (!dati.getDataNascita().equals("") && !DateUtils.isData(dati.getDataNascita())) {
			errors.rejectValue("dataNascita", "NotIsDate.field.required");
		}

		boolean empty = true;
		for (int i = 0; i < dati.getImmobili().size(); i++) {
			DatiImmobileAgevolazioneTari datiImmobile = dati.getImmobili().get(i);

			if (!"".equalsIgnoreCase(datiImmobile.getIndirizzo()) && !"0".equalsIgnoreCase(datiImmobile.getIndirizzo())) {

				empty = false;
				if (datiImmobile.getCivico() != null) {
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "immobili[" + i + "].indirizzo", "Indirizzo.field.notCompleted");
					if (datiImmobile.getCivico().equalsIgnoreCase("0") || datiImmobile.getCivico().equalsIgnoreCase("-1")) {
						errors.rejectValue("immobili[" + i + "].indirizzo", "Indirizzo.field.notCompleted");
					}
				}

				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "immobili[" + i + "].foglio", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "immobili[" + i + "].particella", "NotEmpty.field.required");
				// Validazione campi numerici
				if (com.liferay.portal.kernel.util.Validator.isNotNull(datiImmobile.getFoglio())) {
					if (!com.liferay.portal.kernel.util.Validator.isDigit(datiImmobile.getFoglio())) {
						errors.rejectValue("immobili[" + i + "].foglio", "typeMismatch.number");
					}
				}
				if (com.liferay.portal.kernel.util.Validator.isNotNull(datiImmobile.getParticella())) {
					if (!com.liferay.portal.kernel.util.Validator.isDigit(datiImmobile.getParticella())) {
						errors.rejectValue("immobili[" + i + "].particella", "typeMismatch.number");
					}
				}
			}
		}
		if (empty) {
			errors.rejectValue("immobili[0].indirizzo", "Indirizzo.field.notCompleted");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "immobili[0].foglio", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "immobili[0].particella", "NotEmpty.field.required");
		}

		if (!dati.getDataDecorrenza().equals("") && !DateUtils.isData(dati.getDataDecorrenza())) {
			errors.rejectValue("dataDecorrenza", "NotIsDate.field.required");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataDecorrenza", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codiceAgevolazione", "NotEmpty.field.required");

		if (!isANumberOrEmpty(dati.getNumComponentiNucleoFamiliare())) {
			errors.rejectValue("numComponentiNucleoFamiliare", "Number.field.format");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numComponentiNucleoFamiliare", "NotEmpty.field.required");
		if (!isANumberOrEmpty(dati.getNumSoggettiHandicap())) {
			errors.rejectValue("numSoggettiHandicap", "Number.field.format");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numSoggettiHandicap", "NotEmpty.field.required");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "presenzaFigliUnGenitore", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "presenzaFigliAttivitaGenitori", "NotEmpty.field.required");

		if (!isANumberOrEmpty(dati.getRc())) {
			errors.rejectValue("rc", "Number.field.format");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rc", "NotEmpty.field.required");
		if (!isANumberOrEmpty(dati.getPag())) {
			errors.rejectValue("pag", "Number.field.format");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pag", "NotEmpty.field.required");
		if (!isANumberOrEmpty(dati.getRcpag())) {
			errors.rejectValue("rcpag", "Number.field.format");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rcpag", "NotEmpty.field.required");
		if (!isANumberOrEmpty(dati.getRpm())) {
			errors.rejectValue("rpm", "Number.field.format");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rpm", "NotEmpty.field.required");
		if (!isANumberOrEmpty(dati.getDc())) {
			errors.rejectValue("dc", "Number.field.format");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dc", "NotEmpty.field.required");
		if (!isANumberOrEmpty(dati.getPmo())) {
			errors.rejectValue("pmo", "Number.field.format");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pmo", "NotEmpty.field.required");
		if (!isANumberOrEmpty(dati.getDpm())) {
			errors.rejectValue("dpm", "Number.field.format");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dpm", "NotEmpty.field.required");
		if (!isANumberOrEmpty(dati.getPim())) {
			errors.rejectValue("pim", "Number.field.format");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pim", "NotEmpty.field.required");
		if (!isANumberOrEmpty(dati.getDpi())) {
			errors.rejectValue("dpi", "Number.field.format");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dpi", "NotEmpty.field.required");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codiceFiscaleContribuente", "NotEmpty.field.required");
		validateCF(errors, dati.getCodiceFiscaleContribuente(), "codiceFiscaleContribuente");

		if (!dati.getDataAttestazione().equals("") && !DateUtils.isData(dati.getDataAttestazione())) {
			errors.rejectValue("dataAttestazione", "NotIsDate.field.required");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataAttestazione", "NotEmpty.field.required");
		if (!dati.getDataScadenza().equals("") && !DateUtils.isData(dati.getDataScadenza())) {
			errors.rejectValue("dataScadenza", "NotIsDate.field.required");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataScadenza", "NotEmpty.field.required");

		if (!isANumberOrEmpty(dati.getValoreISE())) {
			errors.rejectValue("valoreISE", "Number.field.format");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "valoreISE", "NotEmpty.field.required");
		if (!isANumberOrEmpty(dati.getScalaEquivalenza())) {
			errors.rejectValue("scalaEquivalenza", "Number.field.format");
		}
		else if (isANumberGreaterThen(dati.getScalaEquivalenza(), 9.9)) {
			errors.rejectValue("scalaEquivalenza", "Number.scala.rangemax");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "scalaEquivalenza", "NotEmpty.field.required");
		if (!isANumberOrEmpty(dati.getValoreISEE())) {
			errors.rejectValue("valoreISEE", "Number.field.format");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "valoreISEE", "NotEmpty.field.required");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nominativoCAF", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pecCAF", "NotEmpty.field.required");
		boolean checkCAF = false;
		if (!dati.getCodiceFiscaleCAF().isEmpty()) {
			checkCAF = true;
			validateCF(errors, dati.getCodiceFiscaleCAF(), "codiceFiscaleCAF");
		}
		if (!dati.getPartitaIvaCAF().isEmpty()) {
			checkCAF = true;
			validatePIVA(errors, dati.getPartitaIvaCAF(), "partitaIvaCAF");
		}
		if (!checkCAF) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codiceFiscaleCAF", "NotEmpty.field.required");
		}

		if (!dati.getPresaVisione()) {
			errors.rejectValue("presaVisione", "condizioni.field.required");
		}
	}

	private boolean isANumberGreaterThen(String value, double max) {
		if (value.isEmpty()) {
			return false;
		}
		try {
			return Double.parseDouble(value) > max;
		}
		catch (NumberFormatException e) {
			return false;
		}
	}

	private boolean isANumberOrEmpty(String value) {
		if (value.isEmpty()) {
			return true;
		}
		try {
			Double.parseDouble(value);
			return true;
		}
		catch (NumberFormatException e) {
			return false;
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
			for (i = 0; i <= 9; i += 2) {
				s += pi.charAt(i) - '0';
			}
			for (i = 1; i <= 9; i += 2) {
				c = 2 * (pi.charAt(i) - '0');
				if (c > 9) {
					c = c - 9;
				}
				s += c;
			}
			if ((10 - s % 10) % 10 != pi.charAt(10) - '0') {
				errors.rejectValue(field, "ParIvaFormat.field.required");
				return;
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
				if (c >= '0' && c <= '9') {
					s = s + c - '0';
				}
				else {
					s = s + c - 'A';
				}
			}
			for (i = 0; i <= 14; i += 2) {
				c = cf2.charAt(i);
				if (c >= '0' && c <= '9') {
					c = c - '0' + 'A';
				}
				s = s + setdisp[c - 'A'];
			}
			if (s % 26 + 'A' != cf2.charAt(15)) {
				errors.rejectValue(field, "CodFisFormat.field.required");
				return;
			}
		}
	}
}
