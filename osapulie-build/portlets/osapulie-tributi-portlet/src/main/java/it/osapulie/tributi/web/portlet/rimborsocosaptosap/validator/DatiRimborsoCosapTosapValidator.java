package it.osapulie.tributi.web.portlet.rimborsocosaptosap.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.osapulie.tributi.web.portlet.rimborsocosaptosap.form.DatiRimborsoCosapTosap;

/**
 * Form validator per {@link DatiRimborsoCosapTosap}.
 * 
 * @author Gianluca Pindinelli
 */
@Component("datiRimborsoCosapTosapValidator")
public class DatiRimborsoCosapTosapValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return DatiRimborsoCosapTosap.class.isAssignableFrom(clazz);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		DatiRimborsoCosapTosap dati = (DatiRimborsoCosapTosap) target;

		// controllo campi obbligatori
		if (!dati.getCognome().equals("")) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codiceFiscale", "NotEmpty.field.required");
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

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "motivo", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rimborso", "NotEmpty.field.required");

		// Dati relativi il servizio cimiteriale
		// Controllo rimepimento di almeno un campo per l'importo versato
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "importoDovuto", "notEmpty.OneImportoVersato.required");

		// Controllo rimepimento di almeno un campo per l'importo versato
		if (dati.getImportoDovuto() != null && !dati.getImportoDovuto().equals("")) {
			String[] importoDovutoStrings = dati.getImportoDovuto().split(",");
			if (importoDovutoStrings.length == 0)
				errors.rejectValue("importoDovuto", "notEmpty.OneImportoVersato.required");
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
		}

		// Controllo scelta anno
		if (dati.getNumeroDocumento() == null || dati.getNumeroDocumento().equals("")) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numeroDocumento", "notEmpty.numeroDocumento.required");
		}
	}
}
