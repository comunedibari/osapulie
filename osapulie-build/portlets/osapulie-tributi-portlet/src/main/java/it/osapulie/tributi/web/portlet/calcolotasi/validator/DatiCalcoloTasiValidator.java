/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.tributi.web.portlet.calcolotasi.validator;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.osapulie.infrastructure.impl.DateUtils;
import it.osapulie.tributi.web.portlet.calcolotasi.form.DatiCalcoloTasi;
import it.osapulie.tributi.web.portlet.calcolotasi.form.DatiCalcoloTasiImmobile;

/**
 * Form validator per {@link DatiCalcoloTasi}.
 *
 */
@Component("datiCalcoloTasiValidator")
public class DatiCalcoloTasiValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return DatiCalcoloTasi.class.isAssignableFrom(clazz);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		DatiCalcoloTasi dati = (DatiCalcoloTasi) target;

		// controllo campi obbligatori
		if (dati.getPartitaIva() == null) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataNascita", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comuneNascita", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "provinciaNascita", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sesso", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "indirizzoResidenza", "NotEmpty.field.required");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codiceFiscale", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comuneResidenza", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "provResidenza", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "capResidenza", "NotEmpty.field.required");

		// controllo la correttezza delle date
		if (dati.getDataNascita() != null && !dati.getDataNascita().equals("") && !DateUtils.isData(dati.getDataNascita())) {
			errors.rejectValue("dataNascita", "NotIsDate.field.required");
		}
		// Validazione nuovo immobile - viene richiamata nel caso in cui il calcolo imu viene
		// effettuato su un immobile nuovo

		if (dati.getNumeroDivCalcoloParziale().equals("new")) {
			// La validazione viene effettuata sui campi associati al form di inserimento di
			// un nuovo immobile

			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "indirizzo", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "categoria", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "aliquota", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "valoreim", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quota", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quotaMesi", "NotEmpty.field.required");

			if (!isANumber(dati.getQuotaMesi())) {
				errors.rejectValue("quotaMesi", "Number.field.format");
			}
			else {
				if (Integer.valueOf(dati.getQuotaMesi()) < 1 || Integer.valueOf(dati.getQuotaMesi()) > 12) {
					errors.rejectValue("quotaMesi", "MonthOutOfRange.field.format");
				}
			}

			if (!isANumber(dati.getQuota())) {
				errors.rejectValue("quota", "Number.field.format");
			}
			else {
				if (Double.valueOf(dati.getQuota()) < 1 || Double.valueOf(dati.getQuota()) > 100) {
					errors.rejectValue("quota", "Rate.field.format");
				}
			}

			if (!isANumber(dati.getValoreim())) {
				errors.rejectValue("valoreim", "Number.field.format");
			}

			if (dati.getPertRenditaCatC2() != null && !dati.getPertRenditaCatC2().isEmpty()) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pertPossessoPercC2", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pertPossessoMesiC2", "NotEmpty.field.required");
				if (!isANumber(dati.getPertRenditaCatC2())) {
					errors.rejectValue("pertRenditaCatC2", "Number.field.format");
				}

			}

			if (dati.getPertRenditaCatC6() != null && !dati.getPertRenditaCatC6().isEmpty()) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pertPossessoPercC6", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pertPossessoMesiC6", "NotEmpty.field.required");
				if (!isANumber(dati.getPertRenditaCatC6())) {
					errors.rejectValue("pertRenditaCatC6", "Number.field.format");
				}

			}

			if (dati.getPertRenditaCatC7() != null && !dati.getPertRenditaCatC7().isEmpty()) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pertPossessoPercC7", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pertPossessoMesiC7", "NotEmpty.field.required");
				if (!isANumber(dati.getPertRenditaCatC7())) {
					errors.rejectValue("pertRenditaCatC7", "Number.field.format");
				}
			}

			if (dati.getPertPossessoPercC2() != null && !dati.getPertPossessoPercC2().isEmpty()) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pertRenditaCatC2", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pertPossessoMesiC2", "NotEmpty.field.required");
				if (!isANumber(dati.getPertPossessoPercC2())) {
					errors.rejectValue("pertPossessoPercC2", "Number.field.format");
				}
				else {
					if (Double.valueOf(dati.getPertPossessoPercC2()) < 1 || Double.valueOf(dati.getPertPossessoPercC2()) > 100) {
						errors.rejectValue("pertPossessoPercC2", "Rate.field.format");
					}
				}

			}

			if (dati.getPertPossessoPercC6() != null && !dati.getPertPossessoPercC6().isEmpty()) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pertRenditaCatC6", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pertPossessoMesiC6", "NotEmpty.field.required");
				if (!isANumber(dati.getPertPossessoPercC6())) {
					errors.rejectValue("pertPossessoPercC6", "Number.field.format");
				}
				else {
					if (Double.valueOf(dati.getPertPossessoPercC6()) < 1 || Double.valueOf(dati.getPertPossessoPercC6()) > 100) {
						errors.rejectValue("pertPossessoPercC6", "Rate.field.format");
					}
				}
			}

			if (dati.getPertPossessoPercC7() != null && !dati.getPertPossessoPercC7().isEmpty()) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pertRenditaCatC7", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pertPossessoMesiC7", "NotEmpty.field.required");
				if (!isANumber(dati.getPertPossessoPercC7())) {
					errors.rejectValue("pertPossessoPercC7", "Number.field.format");
				}
				else {
					if (Double.valueOf(dati.getPertPossessoPercC7()) < 1 || Double.valueOf(dati.getPertPossessoPercC7()) > 100) {
						errors.rejectValue("pertPossessoPercC7", "Rate.field.format");
					}
				}
			}

			if (dati.getPertPossessoMesiC2() != null && !dati.getPertPossessoMesiC2().isEmpty()) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pertRenditaCatC2", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pertPossessoPercC2", "NotEmpty.field.required");

				if (!isANumber(dati.getPertPossessoMesiC2())) {
					errors.rejectValue("pertPossessoMesiC2", "Number.field.format");
				}
				else {
					if (Integer.valueOf(dati.getPertPossessoMesiC2()) < 1 || Integer.valueOf(dati.getPertPossessoMesiC2()) > 12) {
						errors.rejectValue("pertPossessoMesiC2", "MonthOutOfRange.field.format");
					}
				}
			}
			if (dati.getPertPossessoMesiC6() != null && !dati.getPertPossessoMesiC6().isEmpty()) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pertRenditaCatC6", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pertPossessoPercC6", "NotEmpty.field.required");

				if (!isANumber(dati.getPertPossessoMesiC6())) {
					errors.rejectValue("pertPossessoMesiC6", "Number.field.format");
				}
				else {
					if (Integer.valueOf(dati.getPertPossessoMesiC6()) < 1 || Integer.valueOf(dati.getPertPossessoMesiC6()) > 12) {
						errors.rejectValue("pertPossessoMesiC6", "MonthOutOfRange.field.format");
					}
				}
			}
			if (dati.getPertPossessoMesiC7() != null && !dati.getPertPossessoMesiC7().isEmpty()) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pertRenditaCatC7", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pertPossessoPercC7", "NotEmpty.field.required");

				if (!isANumber(dati.getPertPossessoMesiC7())) {
					errors.rejectValue("pertPossessoMesiC7", "Number.field.format");
				}
				else {
					if (Integer.valueOf(dati.getPertPossessoMesiC7()) < 1 || Integer.valueOf(dati.getPertPossessoMesiC7()) > 12) {
						errors.rejectValue("pertPossessoMesiC7", "MonthOutOfRange.field.format");
					}
				}
			}

		}
		List<DatiCalcoloTasiImmobile> datiRiepilogo = dati.getDatiRiepilogo();
		if (datiRiepilogo != null) {
			for (DatiCalcoloTasiImmobile datiCalcoloTasiImmobile : datiRiepilogo) {
				String indice = datiCalcoloTasiImmobile.getIndex();

				if (!indice.equalsIgnoreCase("new")) {
					// Validazione effettuata sui campi di immobili già presenti sul DB
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "datiRiepilogo[" + indice + "].indirizzo", "NotEmpty.field.required");
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "datiRiepilogo[" + indice + "].categoria", "NotEmpty.field.required");
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "datiRiepilogo[" + indice + "].aliquota", "NotEmpty.field.required");
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "datiRiepilogo[" + indice + "].valoreim", "NotEmpty.field.required");
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "datiRiepilogo[" + indice + "].quota", "NotEmpty.field.required");
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "datiRiepilogo[" + indice + "].quotaMesi", "NotEmpty.field.required");

					// I mesi di detenzione dell'anno (quotaMesi) devono essere valori numerici e
					// devono essere compresi tra 1 e 12

					if (!isANumber(datiCalcoloTasiImmobile.getQuotaMesi())) {
						errors.rejectValue("datiRiepilogo[" + indice + "].quotaMesi", "Number.field.format");
					}
					else {
						if (Integer.valueOf(datiCalcoloTasiImmobile.getQuotaMesi()) < 1 || Integer.valueOf(datiCalcoloTasiImmobile.getQuotaMesi()) > 12) {
							errors.rejectValue("datiRiepilogo[" + indice + "].quotaMesi", "MonthOutOfRange.field.format");
						}
					}

					// La percentuale di possesso (quota) deve essere un valore numerico e deve
					// essere compreso tra 1 e 100

					if (!isANumber(datiCalcoloTasiImmobile.getQuota())) {
						errors.rejectValue("datiRiepilogo[" + indice + "].quota", "Number.field.format");
					}
					else {
						if (Double.valueOf(datiCalcoloTasiImmobile.getQuota()) < 1 || Double.valueOf(datiCalcoloTasiImmobile.getQuota()) > 100) {
							errors.rejectValue("datiRiepilogo[" + indice + "].quota", "Rate.field.format");
						}
					}

					if (!isANumber(datiCalcoloTasiImmobile.getValoreim())) {
						errors.rejectValue("datiRiepilogo[" + indice + "].valoreim", "Number.field.format");
					}

					/*
					 * Validazione sui campi associati alle PERTINENZE
					 */

					if (datiCalcoloTasiImmobile.getPertRenditaCatC2() != null && !datiCalcoloTasiImmobile.getPertRenditaCatC2().isEmpty()) {
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "datiRiepilogo[" + indice + "].pertPossessoPercC2", "NotEmpty.field.required");
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "datiRiepilogo[" + indice + "].pertPossessoMesiC2", "NotEmpty.field.required");

						if (!isANumber(datiCalcoloTasiImmobile.getPertRenditaCatC2())) {
							errors.rejectValue("datiRiepilogo[" + indice + "].pertRenditaCatC2", "Number.field.format");
						}
					}

					if (datiCalcoloTasiImmobile.getPertRenditaCatC6() != null && !datiCalcoloTasiImmobile.getPertRenditaCatC6().isEmpty()) {
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "datiRiepilogo[" + indice + "].pertPossessoPercC6", "NotEmpty.field.required");
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "datiRiepilogo[" + indice + "].pertPossessoMesiC6", "NotEmpty.field.required");

						if (!isANumber(datiCalcoloTasiImmobile.getPertRenditaCatC6())) {
							errors.rejectValue("datiRiepilogo[" + indice + "].pertRenditaCatC6", "Number.field.format");
						}
					}

					if (datiCalcoloTasiImmobile.getPertRenditaCatC7() != null && !datiCalcoloTasiImmobile.getPertRenditaCatC7().isEmpty()) {
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "datiRiepilogo[" + indice + "].pertPossessoPercC7", "NotEmpty.field.required");
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "datiRiepilogo[" + indice + "].pertPossessoMesiC7", "NotEmpty.field.required");
						if (!isANumber(datiCalcoloTasiImmobile.getPertRenditaCatC7())) {
							errors.rejectValue("datiRiepilogo[" + indice + "].pertRenditaCatC7", "Number.field.format");
						}
					}

					if (datiCalcoloTasiImmobile.getPertPossessoPercC2() != null && !datiCalcoloTasiImmobile.getPertPossessoPercC2().isEmpty()) {
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "datiRiepilogo[" + indice + "].pertRenditaCatC2", "NotEmpty.field.required");
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "datiRiepilogo[" + indice + "].pertPossessoMesiC2", "NotEmpty.field.required");
						if (!isANumber(datiCalcoloTasiImmobile.getPertPossessoPercC2())) {
							errors.rejectValue("datiRiepilogo[" + indice + "].pertPossessoPercC2", "Number.field.format");
						}
						else {
							if (Double.valueOf(datiCalcoloTasiImmobile.getPertPossessoPercC2()) < 1 || Double.valueOf(datiCalcoloTasiImmobile.getPertPossessoPercC2()) > 100) {
								errors.rejectValue("datiRiepilogo[" + indice + "].pertPossessoPercC2", "Rate.field.format");
							}
						}
					}

					if (datiCalcoloTasiImmobile.getPertPossessoPercC6() != null && !datiCalcoloTasiImmobile.getPertPossessoPercC6().isEmpty()) {
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "datiRiepilogo[" + indice + "].pertRenditaCatC6", "NotEmpty.field.required");
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "datiRiepilogo[" + indice + "].pertPossessoMesiC6", "NotEmpty.field.required");

						if (!isANumber(datiCalcoloTasiImmobile.getPertPossessoPercC6())) {
							errors.rejectValue("datiRiepilogo[" + indice + "].pertPossessoPercC6", "Number.field.format");
						}
						else {
							if (Double.valueOf(datiCalcoloTasiImmobile.getPertPossessoPercC6()) < 1 || Double.valueOf(datiCalcoloTasiImmobile.getPertPossessoPercC6()) > 100) {
								errors.rejectValue("datiRiepilogo[" + indice + "].pertPossessoPercC6", "Rate.field.format");
							}
						}
					}

					if (datiCalcoloTasiImmobile.getPertPossessoPercC7() != null && !datiCalcoloTasiImmobile.getPertPossessoPercC7().isEmpty()) {
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "datiRiepilogo[" + indice + "].pertRenditaCatC7", "NotEmpty.field.required");
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "datiRiepilogo[" + indice + "].pertPossessoMesiC7", "NotEmpty.field.required");

						if (!isANumber(datiCalcoloTasiImmobile.getPertPossessoPercC7())) {
							errors.rejectValue("datiRiepilogo[" + indice + "].pertPossessoPercC7", "Number.field.format");
						}
						else {
							if (Double.valueOf(datiCalcoloTasiImmobile.getPertPossessoPercC7()) < 1 || Double.valueOf(datiCalcoloTasiImmobile.getPertPossessoPercC7()) > 100) {
								errors.rejectValue("datiRiepilogo[" + indice + "].pertPossessoPercC7", "Rate.field.format");
							}
						}
					}

					if (datiCalcoloTasiImmobile.getPertPossessoMesiC2() != null && !datiCalcoloTasiImmobile.getPertPossessoMesiC2().isEmpty()) {
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "datiRiepilogo[" + indice + "].pertRenditaCatC2", "NotEmpty.field.required");
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "datiRiepilogo[" + indice + "].pertPossessoPercC2", "NotEmpty.field.required");

						if (!isANumber(datiCalcoloTasiImmobile.getPertPossessoMesiC2())) {
							errors.rejectValue("datiRiepilogo[" + indice + "].pertPossessoMesiC2", "Number.field.format");
						}
						else {
							if (Integer.valueOf(datiCalcoloTasiImmobile.getPertPossessoMesiC2()) < 1 || Integer.valueOf(datiCalcoloTasiImmobile.getPertPossessoMesiC2()) > 12) {
								errors.rejectValue("datiRiepilogo[" + indice + "].pertPossessoMesiC2", "MonthOutOfRange.field.format");
							}
						}
					}
					if (datiCalcoloTasiImmobile.getPertPossessoMesiC6() != null && !datiCalcoloTasiImmobile.getPertPossessoMesiC6().isEmpty()) {
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "datiRiepilogo[" + indice + "].pertRenditaCatC6", "NotEmpty.field.required");
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "datiRiepilogo[" + indice + "].pertPossessoPercC6", "NotEmpty.field.required");

						if (!isANumber(datiCalcoloTasiImmobile.getPertPossessoMesiC6())) {
							errors.rejectValue("datiRiepilogo[" + indice + "].pertPossessoMesiC6", "Number.field.format");
						}
						else {
							if (Integer.valueOf(datiCalcoloTasiImmobile.getPertPossessoMesiC6()) < 1 || Integer.valueOf(datiCalcoloTasiImmobile.getPertPossessoMesiC6()) > 12) {
								errors.rejectValue("datiRiepilogo[" + indice + "].pertPossessoMesiC6", "MonthOutOfRange.field.format");
							}
						}
					}
					if (datiCalcoloTasiImmobile.getPertPossessoMesiC7() != null && !datiCalcoloTasiImmobile.getPertPossessoMesiC7().isEmpty()) {
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "datiRiepilogo[" + indice + "].pertRenditaCatC7", "NotEmpty.field.required");
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "datiRiepilogo[" + indice + "].pertPossessoPercC7", "NotEmpty.field.required");

						if (!isANumber(datiCalcoloTasiImmobile.getPertPossessoMesiC7())) {
							errors.rejectValue("datiRiepilogo[" + indice + "].pertPossessoMesiC7", "Number.field.format");
						}
						else {
							if (Integer.valueOf(datiCalcoloTasiImmobile.getPertPossessoMesiC7()) < 1 || Integer.valueOf(datiCalcoloTasiImmobile.getPertPossessoMesiC7()) > 12) {
								errors.rejectValue("datiRiepilogo[" + indice + "].pertPossessoMesiC7", "MonthOutOfRange.field.format");
							}
						}
					}
				}
			}
		}
	}

	private boolean isANumber(String value) {
		try {
			Double.parseDouble(value);
			return true;
		}
		catch (NumberFormatException e) {
			return false;
		}
	}
}
