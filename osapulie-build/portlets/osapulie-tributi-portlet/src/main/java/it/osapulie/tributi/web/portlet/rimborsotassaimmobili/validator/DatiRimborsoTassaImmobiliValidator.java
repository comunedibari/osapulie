package it.osapulie.tributi.web.portlet.rimborsotassaimmobili.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.osapulie.infrastructure.impl.DateUtils;
import it.osapulie.tributi.web.portlet.rimborsotassaimmobili.form.DatiRimborsoTassaImmobili;

/**
 * Form validator per {@link DatiRimborsoTassaImmobili}.
 * 
 * @author Maria Michela Birtolo
 */
@Component("datiRimborsoTassaImmobiliValidator")
public class DatiRimborsoTassaImmobiliValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return DatiRimborsoTassaImmobili.class.isAssignableFrom(clazz);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		DatiRimborsoTassaImmobili dati = (DatiRimborsoTassaImmobili) target;

		// controllo campi obbligatori
		if (!dati.getCognome().equals("")) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codiceFiscale", "NotEmpty.field.required");
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
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pIva", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sedeLegale", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comune", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "provincia", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cap", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefonoGiu", "NotEmpty.field.required");
		}
		if (!dati.getCognomeRapp().equals("")) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nomeRapp", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codiceFiscaleRapp", "NotEmpty.field.required");
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

		if (dati.getpIva() != null && !"".equalsIgnoreCase(dati.getpIva().trim())) {
			validatePIVA(errors, dati.getpIva(), "pIva");
		}

		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "indirizzo1",
		// "NotEmpty.field.required");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "motivo", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rimborso", "NotEmpty.field.required");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descrizioneTassa", "NotEmpty.field.required");

		// controllo la correttezza delle date
		if (!dati.getDataNascita().equals("") && !DateUtils.isData(dati.getDataNascita())) {
			errors.rejectValue("dataNascita", "NotIsDate.field.required");
		}
		if (!dati.getDataNascitaRapp().equals("") && !DateUtils.isData(dati.getDataNascitaRapp())) {
			errors.rejectValue("dataNascitaRapp", "NotIsDate.field.required");
		}
		// se inserito un indirizzo devono esserci tutti i relativi campi
		// for (int i = 1; i <= 5; i++) {
		// String par = "getIndirizzo" + i;
		// try {
		// Method metodo = dati.getClass().getDeclaredMethod(par);
		// String indirizzo = (String) metodo.invoke(dati);
		// if (!indirizzo.equals("")) {
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "foglio" + i,
		// "NotEmpty.field.required");
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "num" + i, "NotEmpty.field.required");
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quota" + i,
		// "NotEmpty.field.required");
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "categoria" + i,
		// "NotEmpty.field.required");
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sezione" + i,
		// "NotEmpty.field.required");
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dovuto" + i,
		// "NotEmpty.field.required");
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "versato" + i,
		// "NotEmpty.field.required");
		// }
		// }
		// catch (SecurityException e) {
		// e.printStackTrace();
		// }
		// catch (NoSuchMethodException e) {
		// e.printStackTrace();
		// }
		// catch (IllegalArgumentException e) {
		// e.printStackTrace();
		// }
		// catch (IllegalAccessException e) {
		// e.printStackTrace();
		// }
		// catch (InvocationTargetException e) {
		// e.printStackTrace();
		// }
		// }

		// if (dati.isAccredito() == false && dati.isAssegno() == false && dati.isMandato() ==
		// false) {
		// errors.rejectValue("mandato", "NotEmpty.field.required");
		// }
		//
		// if ((dati.isAccredito() == true && dati.isAssegno() == true) || (dati.isAccredito() ==
		// true && dati.isMandato() == true) || (dati.isAssegno() == true && dati.isMandato() ==
		// true)) {
		// errors.rejectValue("mandato", "SingleSelect.field.required");
		// }
		// if (dati.isAccredito() == true) {
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "banca", "NotEmpty.field.required");
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "filiale", "NotEmpty.field.required");
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "iban", "NotEmpty.field.required");
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "intestatario",
		// "NotEmpty.field.required");
		// }
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
