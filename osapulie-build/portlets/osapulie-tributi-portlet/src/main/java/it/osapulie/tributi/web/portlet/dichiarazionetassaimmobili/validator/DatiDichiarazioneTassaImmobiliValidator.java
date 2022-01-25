package it.osapulie.tributi.web.portlet.dichiarazionetassaimmobili.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.osapulie.infrastructure.impl.DateUtils;
import it.osapulie.tributi.web.portlet.dichiarazionetassaimmobili.form.DatiDichiarazioneTassaImmobili;

/**
 * Form validator per {@link DatiDichiarazioneTassaImmobili}.
 *
 * @author Maria Michela Birtolo
 * @author Gianluca Pindinelli
 */
@Component("datiDichiarazioneTassaImmobiliValidator")
public class DatiDichiarazioneTassaImmobiliValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return DatiDichiarazioneTassaImmobili.class.isAssignableFrom(clazz);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		DatiDichiarazioneTassaImmobili dati = (DatiDichiarazioneTassaImmobili) target;

		final String regexCodiceFiscale = "[a-zA-Z]{6}[0-9]{2}[a-zA-Z][0-9]{2}[a-zA-Z][0-9]{3}[a-zA-Z]";
		final String regexEmail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		// controllo campi obbligatori

		if (dati.getPartitaIva() == null) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataNascita", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comuneNascita", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sesso", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "provinciaNascita", "NotEmpty.field.required");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codiceFiscale", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "indirizzo", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comuneResidenza", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "provResidenza", "NotEmpty.field.required");

		if (dati.getPartitaIva() == null && dati.getCodiceFiscale() != null && !dati.getCodiceFiscale().equals("")) {
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
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "naturaCarica", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tipoPresentazioneDic", "NotEmpty.field.required");
			if (dati.isImpegnoPresentazioneTelematicaDic()) {
				if (!dati.getDataImpegnoPresentazioneDic().equals("") && !DateUtils.isData(dati.getDataImpegnoPresentazioneDic())) {
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataImpegnoPresentazioneDic", "NotEmpty.field.required");
				}
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

		// controllo la correttezza delle date
		if (!"".equalsIgnoreCase(dati.getDataNascita()) && !DateUtils.isData(dati.getDataNascita())) {
			errors.rejectValue("dataNascita", "NotIsDate.field.required");
		}
		if ((dati.getPosizioniTotalmenteImponibili() == null || dati.getPosizioniTotalmenteImponibili().size() == 0)
				&& (dati.getPosizioniParzialmenteImponibiliOEsenti() == null || dati.getPosizioniParzialmenteImponibiliOEsenti().size() == 0)) {
			errors.rejectValue("posizioniTotalmenteImponibili", "NotEmpty.field.required");
		}
		// controllo campi obbligatori per ogni immobile inserito nella dichiarazione
		if (dati.getPosizioniTotalmenteImponibili() != null && dati.getPosizioniTotalmenteImponibili().size() > 0) {
			for (int i = 0; i < dati.getPosizioniTotalmenteImponibili().size(); i++) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "posizioniTotalmenteImponibili[" + i + "].caratteristicaImmobile", "NotEmpty.field.required");
				// ValidationUtils.rejectIfEmptyOrWhitespace(errors,
				// "posizioniTotalmenteImponibili[" + i + "].indirizzoUtenza.indirizzo.via",
				// "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "posizioniTotalmenteImponibili[" + i + "].valoreImmobile", "NotEmpty.field.required");

				// ValidationUtils.rejectIfEmptyOrWhitespace(errors,
				// "posizioniTotalmenteImponibili[" + i + "].subalterno","typeMismatch");
				validateStringToDouble(errors, dati.getPosizioniTotalmenteImponibili().get(i).getClasse(), "posizioniTotalmenteImponibili[" + i + "].classe");
				validateStringToDouble(errors, dati.getPosizioniTotalmenteImponibili().get(i).getNumProt(), "posizioniTotalmenteImponibili[" + i + "].numProt");
				validateStringToDouble(errors, dati.getPosizioniTotalmenteImponibili().get(i).getAnno(), "posizioniTotalmenteImponibili[" + i + "].anno");
				validateStringToDate(errors, dati.getPosizioniTotalmenteImponibili().get(i).getDataUltimazioneLavori(), "posizioniTotalmenteImponibili[" + i + "].dataUltimazioneLavori");
				validateStringToDate(errors, dati.getPosizioniTotalmenteImponibili().get(i).getDataInizioPossesso(), "posizioniTotalmenteImponibili[" + i + "].dataInizioPossesso");

			}
		}

		if (dati.getPosizioniParzialmenteImponibiliOEsenti() != null && dati.getPosizioniParzialmenteImponibiliOEsenti().size() > 0) {
			for (int i = 0; i < dati.getPosizioniParzialmenteImponibiliOEsenti().size(); i++) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "posizioniParzialmenteImponibiliOEsenti[" + i + "].caratteristicaImmobile", "NotEmpty.field.required");
				// ValidationUtils.rejectIfEmptyOrWhitespace(errors,
				// "posizioniParzialmenteImponibiliOEsenti[" + i +
				// "].indirizzoUtenza.indirizzo.via", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "posizioniParzialmenteImponibiliOEsenti[" + i + "].valoreImmobile", "NotEmpty.field.required");

				if (!dati.getPosizioniParzialmenteImponibiliOEsenti().get(i).getDataInizioPossesso().equals("")
						&& !DateUtils.isData(dati.getPosizioniParzialmenteImponibiliOEsenti().get(i).getDataInizioPossesso())) {
					errors.rejectValue("posizioniParzialmenteImponibiliOEsenti[" + i + "].dataInizioPossesso", "NotIsDate.field.required");
				}
			}
		}
	}

	private void validateStringToDate(Errors errors, String value, String field) {
		if (!"".equalsIgnoreCase(value) && !DateUtils.isData(value)) {
			errors.rejectValue(field, "NotIsDate.field.required");
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
}
