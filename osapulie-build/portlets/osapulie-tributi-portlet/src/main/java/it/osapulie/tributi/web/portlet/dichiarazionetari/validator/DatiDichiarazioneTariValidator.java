package it.osapulie.tributi.web.portlet.dichiarazionetari.validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.osapulie.infrastructure.impl.DateUtils;
import it.osapulie.tributi.web.portlet.dichiarazionetari.controller.DichiarazioneTariPortletController;
import it.osapulie.tributi.web.portlet.dichiarazionetari.form.DatiDichiarazioneTari;
import it.osapulie.tributi.web.portlet.dichiarazionetari.form.DatiImmobile;
import it.osapulie.tributi.web.portlet.dichiarazionetari.form.Occupante;
import it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione;
import it.osapulie.tributi.web.portlet.utils.PortletUtils;

/**
 * Form validator per {@link DatiDichiarazioneTari}.
 *
 * @author Maria Michela Birtolo
 * @author Gianluca Pindinelli
 * @author Andrea Filomena
 */
@Component("datiDichiarazioneTariValidator")
public class DatiDichiarazioneTariValidator implements Validator {

	private final Logger log = LoggerFactory.getLogger(DatiDichiarazioneTariValidator.class);

	@Override
	public boolean supports(Class<?> clazz) {
		return DatiDichiarazioneTari.class.isAssignableFrom(clazz);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		DatiDichiarazioneTari dati = (DatiDichiarazioneTari) target;
		try {
			// controllo campi obbligatori
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sesso", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codiceFiscale", "NotEmpty.field.required");

			if (com.liferay.portal.kernel.util.Validator.isNotNull(dati.getCodiceFiscale())) {
				String checkCodiceFiscale = PortletUtils.checkCodiceFiscale(dati.getCodiceFiscale());
				if (checkCodiceFiscale != null) {
					errors.rejectValue("codiceFiscale", null, checkCodiceFiscale);
				}
			}

			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataNascita", "NotEmpty.field.required");
			String dataNascita = dati.getDataNascita();
			// controllo data
			if (dataNascita != null && !dataNascita.isEmpty()) {
				if (!DateUtils.isData(dataNascita)) {
					errors.rejectValue("dataNascita", "typeMismatch.data");
				}

			}

			if (dati.getStatoEstero() != null && dati.getStatoEstero().equals("")) {
				// Solo se stato Italia
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comuneNascita", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "provinciaNascita", "NotEmpty.field.required");
			}
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "civicoResidenza", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "indirizzoResidenza", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comuneResidenza", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "provResidenza", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "capResidenza", "NotEmpty.field.required");

			if (dati.getEsponenteResidenza() != null && !dati.getEsponenteResidenza().isEmpty()) {
				if (dati.getEsponenteResidenza().length() > 4) {
					errors.rejectValue("esponenteResidenza", "error.field.max.length", new String[] { "\"Esponente\"", "4" }, null);
				}
			}

			String capResidenza = dati.getCapResidenza();
			if (capResidenza != null && !capResidenza.isEmpty()) {
				if (!capResidenza.matches("[0-9]{5}")) {
					errors.rejectValue("capResidenza", "CapFormat.field.required");
				}
			}
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tipoPersona", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefono", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "aDecorrereDa", "NotEmpty.field.required");
			// controllo data
			if (dati.getaDecorrereDa() != null && !dati.getaDecorrereDa().isEmpty()) {
				if (!DateUtils.isData(dati.getaDecorrereDa())) {
					errors.rejectValue("aDecorrereDa", "typeMismatch.data");
				}
				else {
					Date inizioUtenza = DateUtils.getData(dati.getaDecorrereDa(), "dd/MM/yyyy");
					Date fineUtenza = DateUtils.getOggi();
					if (com.liferay.portal.kernel.util.DateUtil.compareTo(inizioUtenza, fineUtenza, true) > 0) {
						errors.rejectValue("aDecorrereDa", "Decorrenza.rangedata");
					}
				}
			}

			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "estremiDocumento", "NotEmpty.field.required");

			String statoEstero = dati.getStatoEstero();
			if (statoEstero != null && !statoEstero.isEmpty()) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codiceNascitaEstero", "NotEmpty.field.required");
			}

			String civicoResidenza = dati.getCivicoResidenza();
			if (civicoResidenza != null && !civicoResidenza.isEmpty()) {
				if (!com.liferay.portal.kernel.util.Validator.isDigit(civicoResidenza)) {
					errors.rejectValue("civicoResidenza", "typeMismatch.number");
				}
			}

			// se delego la domanda, devo obbligatoriamente specificare il soggetto delegato
			if (dati.isFlagDelega()) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nominativoDelegato", "NotEmpty.field.required");
			}

			if (dati.getModalitaInvio() != null && dati.getModalitaInvio().equals("altro")) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "indirizzoSpedizione", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "civicoSpedizione", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comuneSpedizione", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "provinciaSpedizione", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "capSpedizione", "NotEmpty.field.required");
				if (com.liferay.portal.kernel.util.Validator.isNotNull(dati.getCivicoSpedizione()) && !com.liferay.portal.kernel.util.Validator.isDigit(dati.getCivicoSpedizione())) {
					errors.rejectValue("civicoSpedizione", "typeMismatch.number");
				}
				String capSpedizione = dati.getCapSpedizione();
				if (capSpedizione != null && !capSpedizione.isEmpty()) {
					if (!capSpedizione.matches("[0-9]{5}")) {
						errors.rejectValue("capSpedizione", "CapFormat.field.required");
					}
				}
			}

			String identificativoUtenzeSelezionateVariazione = dati.getIdentificativoUtenzeSelezionateVariazione();
			String tipoUtenza = dati.getTipoUtenza();
			String tipoDichiarazione = dati.getTipoDichiarazione();

			if (tipoUtenza.equals(DichiarazioneTariPortletController.UTENZA_DOMESTICA)) {

				if (tipoDichiarazione.equals(DichiarazioneTariPortletController.TIPO_DICHIARAZIONE_ISCRIZIONE)
						|| DichiarazioneTariPortletController.TIPO_DICHIARAZIONE_TRASFERIMENTO.equalsIgnoreCase(tipoDichiarazione)) {

					if (!dati.isResidente() && !dati.isNonResidente()) {
						errors.rejectValue("nonResidente", "NotEmpty.field.required");
					}
					if (dati.isResidente() && !dati.isDetenzioneImmobile()) {
						errors.rejectValue("detenzioneImmobile", "NotEmpty.field.required");
					}

					if (dati.isNonResidente()) {
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "totaleNucleoInResidenza", "NotEmpty.field.required");
						if (com.liferay.portal.kernel.util.Validator.isNotNull(dati.getTotaleNucleoInResidenza())) {
							if (!com.liferay.portal.kernel.util.Validator.isDigit(dati.getTotaleNucleoInResidenza())) {
								errors.rejectValue("totaleNucleoInResidenza", "typeMismatch.number");
							}
						}
					}

					boolean empty = true;
					for (int i = 0; i < dati.getIscrizioneDom().size(); i++) {
						DatiImmobile datiImmobile = dati.getIscrizioneDom().get(i);
						if (!"".equalsIgnoreCase(datiImmobile.getIndirizzo()) && !"0".equalsIgnoreCase(datiImmobile.getIndirizzo())) {
							empty = false;
							if (datiImmobile.getCivico() != null) {
								ValidationUtils.rejectIfEmptyOrWhitespace(errors, "iscrizioneDom[" + i + "].indirizzo", "Indirizzo.field.notCompleted");
								if (datiImmobile.getCivico().equalsIgnoreCase("0") || datiImmobile.getCivico().equalsIgnoreCase("-1")) {
									errors.rejectValue("iscrizioneDom[" + i + "].indirizzo", "Indirizzo.field.notCompleted");
								}
							}
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "iscrizioneDom[" + i + "].cap", "NotEmpty.field.required");
							if (datiImmobile.getCap() != null && !datiImmobile.getCap().isEmpty()) {
								if (!datiImmobile.getCap().matches("[0-9]{5}")) {
									errors.rejectValue("iscrizioneDom[" + i + "].cap", "CapFormat.field.required");
								}
							}

							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "iscrizioneDom[" + i + "].foglio", "NotEmpty.field.required");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "iscrizioneDom[" + i + "].particella", "NotEmpty.field.required");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "iscrizioneDom[" + i + "].mq", "NotEmpty.field.required");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "iscrizioneDom[" + i + "].tipo", "NotEmpty.field.required");

							// Validazione campi numerici
							if (com.liferay.portal.kernel.util.Validator.isNotNull(datiImmobile.getFoglio())) {
								if (!com.liferay.portal.kernel.util.Validator.isDigit(datiImmobile.getFoglio())) {
									errors.rejectValue("iscrizioneDom[" + i + "].foglio", "typeMismatch.number");
								}
							}
							if (com.liferay.portal.kernel.util.Validator.isNotNull(datiImmobile.getParticella())) {
								if (!com.liferay.portal.kernel.util.Validator.isDigit(datiImmobile.getParticella())) {
									errors.rejectValue("iscrizioneDom[" + i + "].particella", "typeMismatch.number");
								}
							}
							if (com.liferay.portal.kernel.util.Validator.isNotNull(datiImmobile.getSubalterno())) {
								if (!com.liferay.portal.kernel.util.Validator.isDigit(datiImmobile.getSubalterno())) {
									errors.rejectValue("iscrizioneDom[" + i + "].subalterno", "typeMismatch.number");
								}
							}

							if (datiImmobile.getEsponente() != null && !datiImmobile.getEsponente().isEmpty()) {
								if (datiImmobile.getEsponente().length() > 4) {
									errors.rejectValue("iscrizioneDom[" + i + "].esponente", "error.field.max.length", new String[] { "\"Esponente\"", "4" }, null);
								}
							}
						}
					}
					if (empty) {
						errors.rejectValue("iscrizioneDom[0].indirizzo", "Indirizzo.field.notCompleted");
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "iscrizioneDom[0].cap", "NotEmpty.field.required");
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "iscrizioneDom[0].foglio", "NotEmpty.field.required");
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "iscrizioneDom[0].particella", "NotEmpty.field.required");
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "iscrizioneDom[0].mq", "NotEmpty.field.required");
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "iscrizioneDom[0].tipo", "NotEmpty.field.required");
					}

				}
				if (tipoDichiarazione.equals(DichiarazioneTariPortletController.TIPO_DICHIARAZIONE_VARIAZIONE)) {
					if (identificativoUtenzeSelezionateVariazione != null && !identificativoUtenzeSelezionateVariazione.isEmpty()) {
						for (int i = 0; i < dati.getVariazioneDom().size(); i++) {
							DatiImmobile datiImmobile = dati.getVariazioneDom().get(i);
							if (datiImmobile.getIdentificativoUtenza().equals(identificativoUtenzeSelezionateVariazione)) {
								// ValidationUtils.rejectIfEmptyOrWhitespace(errors,
								// "variazioneDom[" + i + "].varDiSuperficieA",
								// "NotEmpty.field.required");
								if (datiImmobile.getVarDiSuperficieA() != null && !datiImmobile.getVarDiSuperficieA().isEmpty()) {
									if (!com.liferay.portal.kernel.util.Validator.isDigit(datiImmobile.getVarDiSuperficieA())) {
										errors.rejectValue("variazioneDom[" + i + "].varDiSuperficieA", "typeMismatch.number");
									}
								}
							}
						}
					}
					else {
						boolean empty = true;
						for (int i = 0; i < dati.getVariazioneDom().size(); i++) {
							DatiImmobile datiImmobile = dati.getVariazioneDom().get(i);

							if (!"".equalsIgnoreCase(datiImmobile.getIndirizzo()) && !"0".equalsIgnoreCase(datiImmobile.getIndirizzo())) {
								empty = false;
								if (datiImmobile.getCivico() != null) {
									ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneDom[" + i + "].indirizzo", "Indirizzo.field.notCompleted");
									if (datiImmobile.getCivico().equalsIgnoreCase("0") || datiImmobile.getCivico().equalsIgnoreCase("-1")) {
										errors.rejectValue("variazioneDom[" + i + "].indirizzo", "Indirizzo.field.notCompleted");
									}
								}
								ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneDom[" + i + "].cap", "NotEmpty.field.required");
								if (datiImmobile.getCap() != null && !datiImmobile.getCap().isEmpty()) {
									if (!datiImmobile.getCap().matches("[0-9]{5}")) {
										errors.rejectValue("variazioneDom[" + i + "].cap", "CapFormat.field.required");
									}
								}

								ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneDom[" + i + "].foglio", "NotEmpty.field.required");
								ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneDom[" + i + "].particella", "NotEmpty.field.required");
								ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneDom[" + i + "].tipo", "NotEmpty.field.required");
								ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneDom[" + i + "].varDiSuperficieDa", "NotEmpty.field.required");
								ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneDom[" + i + "].varDiSuperficieA", "NotEmpty.field.required");

								// Validazione campi numerici
								if (com.liferay.portal.kernel.util.Validator.isNotNull(datiImmobile.getFoglio())) {
									if (!com.liferay.portal.kernel.util.Validator.isDigit(datiImmobile.getFoglio())) {
										errors.rejectValue("variazioneDom[" + i + "].foglio", "typeMismatch.number");
									}
								}
								if (com.liferay.portal.kernel.util.Validator.isNotNull(datiImmobile.getParticella())) {
									if (!com.liferay.portal.kernel.util.Validator.isDigit(datiImmobile.getParticella())) {
										errors.rejectValue("variazioneDom[" + i + "].particella", "typeMismatch.number");
									}
								}
								if (com.liferay.portal.kernel.util.Validator.isNotNull(datiImmobile.getSubalterno())) {
									if (!com.liferay.portal.kernel.util.Validator.isDigit(datiImmobile.getSubalterno())) {
										errors.rejectValue("variazioneDom[" + i + "].subalterno", "typeMismatch.number");
									}
								}

								if (datiImmobile.getEsponente() != null && !datiImmobile.getEsponente().isEmpty()) {
									if (datiImmobile.getEsponente().length() > 4) {
										errors.rejectValue("variazioneDom[" + i + "].esponente", "error.field.max.length", new String[] { "\"Esponente\"", "4" }, null);
									}
								}

							}
							else {
								ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneDom[" + i + "].indirizzo", "Indirizzo.field.notCompleted");
								empty = false;
							}
						}
						if (empty) {
							errors.rejectValue("iscrizioneDom[0].indirizzo", "Indirizzo.field.notCompleted");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneDom[0].cap", "NotEmpty.field.required");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneDom[0].foglio", "NotEmpty.field.required");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneDom[0].particella", "NotEmpty.field.required");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneDom[0].varDiSuperficieDa", "NotEmpty.field.required");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneDom[0].varDiSuperficieA", "NotEmpty.field.required");
						}
					}

					if (dati.getConcessioneQuoteDom().equals("rimborso")) {

						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "modalitaRimborsoDom", "NotEmpty.field.required");
						String modalitaRimborsoDom = dati.getModalitaRimborsoDom();
						if (modalitaRimborsoDom != null && modalitaRimborsoDom.equals("accredito")) {
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ibanDom", "NotEmpty.field.required");
							String checkIBAN = PortletUtils.checkIBAN(dati.getIbanDom());
							if (checkIBAN != null) {
								errors.rejectValue("ibanDom", null, checkIBAN);
							}
						}
					}
				}
				if (tipoDichiarazione.equals(DichiarazioneTariPortletController.TIPO_DICHIARAZIONE_CESSAZIONE)
						|| DichiarazioneTariPortletController.TIPO_DICHIARAZIONE_TRASFERIMENTO.equalsIgnoreCase(tipoDichiarazione)) {

					if (dati.getIdentificativoUtenzeSelezionateCessazione() != null && !dati.getIdentificativoUtenzeSelezionateCessazione().isEmpty()) {
						// data cessazione maggiore di data inizio utenza selezionata
						if (DateUtils.isData(dati.getaDecorrereDa())) {

							String _inizioUtenza = getDataInizioUtenzaFromIdentificativoUtenza(dati.getIdentificativoUtenzeSelezionateCessazione());
							Date inizioUtenza = DateUtils.getData(_inizioUtenza, "ddMMyyyy");
							Date fineUtenza = DateUtils.getData(dati.getaDecorrereDa(), "dd/MM/yyyy");
							if (com.liferay.portal.kernel.util.DateUtil.compareTo(inizioUtenza, fineUtenza, true) > 0) {
								errors.rejectValue("aDecorrereDa", "Cessazione.rangedata");
							}
						}

					}
					else {
						boolean empty = true;
						for (int i = 0; i < dati.getCessazioneDom().size(); i++) {
							DatiImmobile datiImmobile = dati.getCessazioneDom().get(i);

							if (!"".equalsIgnoreCase(datiImmobile.getIndirizzo()) && !"0".equalsIgnoreCase(datiImmobile.getIndirizzo())) {
								empty = false;
								if (datiImmobile.getCivico() != null) {
									ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cessazioneDom[" + i + "].indirizzo", "Indirizzo.field.notCompleted");
									if (datiImmobile.getCivico().equalsIgnoreCase("0")) {
										errors.rejectValue("cessazioneDom[" + i + "].indirizzo", "Indirizzo.field.notCompleted");
									}
								}
								if (datiImmobile.getCap() != null && !datiImmobile.getCap().isEmpty()) {
									if (!datiImmobile.getCap().matches("[0-9]{5}")) {
										errors.rejectValue("cessazioneDom[" + i + "].cap", "CapFormat.field.required");
									}
								}

								ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cessazioneDom[" + i + "].foglio", "NotEmpty.field.required");
								ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cessazioneDom[" + i + "].particella", "NotEmpty.field.required");
								ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cessazioneDom[" + i + "].tipo", "NotEmpty.field.required");
								ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cessazioneDom[" + i + "].mq", "NotEmpty.field.required");

								// Validazione campi numerici
								if (com.liferay.portal.kernel.util.Validator.isNotNull(datiImmobile.getFoglio())) {
									if (!com.liferay.portal.kernel.util.Validator.isDigit(datiImmobile.getFoglio())) {
										errors.rejectValue("cessazioneDom[" + i + "].foglio", "typeMismatch.number");
									}
								}
								if (com.liferay.portal.kernel.util.Validator.isNotNull(datiImmobile.getParticella())) {
									if (!com.liferay.portal.kernel.util.Validator.isDigit(datiImmobile.getParticella())) {
										errors.rejectValue("cessazioneDom[" + i + "].particella", "typeMismatch.number");
									}
								}
								if (com.liferay.portal.kernel.util.Validator.isNotNull(datiImmobile.getSubalterno())) {
									if (!com.liferay.portal.kernel.util.Validator.isDigit(datiImmobile.getSubalterno())) {
										errors.rejectValue("cessazioneDom[" + i + "].subalterno", "typeMismatch.number");
									}
								}

								if (datiImmobile.getEsponente() != null && !datiImmobile.getEsponente().isEmpty()) {
									if (datiImmobile.getEsponente().length() > 4) {
										errors.rejectValue("cessazioneDom[" + i + "].esponente", "error.field.max.length", new String[] { "\"Esponente\"", "4" }, null);
									}
								}
							}
							else {
								ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cessazioneDom[" + i + "].indirizzo", "Indirizzo.field.notCompleted");
								empty = false;
							}
						}
						if (empty) {
							errors.rejectValue("cessazioneDom[0].indirizzo", "Indirizzo.field.notCompleted");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cessazioneDom[0].cap", "NotEmpty.field.required");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cessazioneDom[0].foglio", "NotEmpty.field.required");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cessazioneDom[0].particella", "NotEmpty.field.required");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cessazioneDom[0].mq", "NotEmpty.field.required");
						}
					}
				}

				// iscrizione
				if (tipoDichiarazione.equals(DichiarazioneTariPortletController.TIPO_DICHIARAZIONE_ISCRIZIONE)
						|| DichiarazioneTariPortletController.TIPO_DICHIARAZIONE_TRASFERIMENTO.equalsIgnoreCase(tipoDichiarazione)) {

					if (dati.isResidente()) {
						// Setto la Riduzione a false (nel caso in cui sia stata settata e si è poi
						// selezionato di essere residente
						dati.setRiduzioniDomestiche(null);

						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "totaleNucleoFamiliare", "NotEmpty.field.required");
						String totaleNucleoFamiliare = dati.getTotaleNucleoFamiliare();
						if (totaleNucleoFamiliare != null && !totaleNucleoFamiliare.isEmpty()) {
							if (!com.liferay.portal.kernel.util.Validator.isDigit(totaleNucleoFamiliare)) {
								errors.rejectValue("totaleNucleoFamiliare", "typeMismatch.number");
							}
							else {
								// Controllo inserimento componenti se superiore ad 1 (compreso
								// dichiarante)
								int numeroOccupanti = 1;
								int totaleComponentiDefiniti = Integer.parseInt(totaleNucleoFamiliare);
								if (!dati.isDetenzioneImmobile() && totaleComponentiDefiniti > numeroOccupanti) {
									errors.rejectValue("totaleNucleoFamiliare", "error.numeroOccupanti.required");
								}
								// Controllo lista occupanti (compreso dichiarante)
								List<Occupante> occupanti = dati.getOccupanti();
								Set<String> codiciFiscaliSet = new HashSet<String>();
								codiciFiscaliSet.add(dati.getCodiceFiscale());
								if (occupanti != null) {
									for (int i = 0; i < occupanti.size(); i++) {
										Occupante occupante = occupanti.get(i);
										String codiceFiscale = occupante.getCodiceFiscale();
										if (com.liferay.portal.kernel.util.Validator.isNotNull(codiceFiscale)) {
											codiciFiscaliSet.add(codiceFiscale);

											ValidationUtils.rejectIfEmptyOrWhitespace(errors, "occupanti[" + i + "].cognome", "NotEmpty.field.required");
											ValidationUtils.rejectIfEmptyOrWhitespace(errors, "occupanti[" + i + "].nome", "NotEmpty.field.required");
											ValidationUtils.rejectIfEmptyOrWhitespace(errors, "occupanti[" + i + "].dataNascita", "NotEmpty.field.required");
											if (occupante.getDataNascita() != null && !occupante.getDataNascita().isEmpty()) {
												if (!DateUtils.isData(occupante.getDataNascita())) {
													errors.rejectValue("occupanti[" + i + "].dataNascita", "typeMismatch.data");
												}
											}
											String checkCodiceFiscale = PortletUtils.checkCodiceFiscale(codiceFiscale);
											if (checkCodiceFiscale != null) {
												errors.rejectValue("occupanti[" + i + "].codiceFiscale", null, checkCodiceFiscale);
											}
											numeroOccupanti++;
										}
									}

									if (codiciFiscaliSet.size() != numeroOccupanti) {
										errors.rejectValue("detenzioneImmobile", "error.codicifiscali.univoci");
									}
								}
								if (dati.isDetenzioneImmobile() && numeroOccupanti != totaleComponentiDefiniti) {
									errors.rejectValue("totaleNucleoFamiliare", "error.numeroOccupanti.mismatch");
								}
							}
						}
					}
					else {
						// Riduzioni
						List<Riduzione> riduzioniDomesticheIscrizione = dati.getRiduzioniDomesticheIscrizione();
						if (riduzioniDomesticheIscrizione != null && !riduzioniDomesticheIscrizione.isEmpty()) {
							for (int i = 0; i < riduzioniDomesticheIscrizione.size(); i++) {
								Riduzione riduzione = riduzioniDomesticheIscrizione.get(i);
								if (riduzione.getCodiceArticolo() != null && (riduzione.getValori().isEmpty() || riduzione.getValori().get(i).getChiave() == null)) {
									errors.rejectValue("riduzioniDomesticheIscrizione[" + i + "].codiceArticolo", "NotEmpty.field.required");
								}
							}
						}
					}
				}
				// variazione
				if (tipoDichiarazione.equals(DichiarazioneTariPortletController.TIPO_DICHIARAZIONE_VARIAZIONE)) {

					List<Riduzione> riduzioniDomesticheVariazione = dati.getRiduzioniDomesticheVariazione();
					if (riduzioniDomesticheVariazione != null && !riduzioniDomesticheVariazione.isEmpty()) {
						for (int i = 0; i < riduzioniDomesticheVariazione.size(); i++) {
							Riduzione riduzione = riduzioniDomesticheVariazione.get(i);
							if (riduzione.getCodiceArticolo() != null && (riduzione.getValori().isEmpty() || riduzione.getValori().get(i).getChiave() == null)) {
								errors.rejectValue("riduzioniDomesticheVariazione[" + i + "].codiceArticolo", "NotEmpty.field.required");
							}
							if (riduzione.getCodiceArticolo() != null && (dati.getTipologiaRichiestaDom() == null || dati.getTipologiaRichiestaDom().isEmpty())) {
								errors.rejectValue("tipologiaRichiestaDom", "NotEmpty.field.required");
							}
						}
					}

					if (dati.isAltroDom()) {
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "altroNoteDom", "NotEmpty.field.required");
					}

					// controlli var.anag.occu.
					// Controllo lista occupanti (compreso dichiarante)
					List<Occupante> occupanti = dati.getVariazioneOccupanti();
					Set<String> codiciFiscaliSet = new HashSet<String>();
					codiciFiscaliSet.add(dati.getCodiceFiscale());
					int numeroOccupanti = 1;
					if (occupanti != null) {
						for (int i = 0; i < occupanti.size(); i++) {
							Occupante occupante = occupanti.get(i);
							String codiceFiscale = occupante.getCodiceFiscale();
							if (com.liferay.portal.kernel.util.Validator.isNotNull(codiceFiscale)) {
								codiciFiscaliSet.add(codiceFiscale);

								ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneOccupanti[" + i + "].tipoModifica", "NotEmpty.field.required");
								ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneOccupanti[" + i + "].cognome", "NotEmpty.field.required");
								ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneOccupanti[" + i + "].nome", "NotEmpty.field.required");
								ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneOccupanti[" + i + "].dataNascita", "NotEmpty.field.required");
								String checkCodiceFiscale = PortletUtils.checkCodiceFiscale(codiceFiscale);
								if (checkCodiceFiscale != null) {
									errors.rejectValue("variazioneOccupanti[" + i + "].codiceFiscale", null, checkCodiceFiscale);
								}
								numeroOccupanti++;
							}
						}

						if (codiciFiscaliSet.size() != numeroOccupanti) {
							errors.rejectValue("varCompNucleoFam", "error.codicifiscali.univoci");
						}

						dati.setVarCompNucleoFam(numeroOccupanti > 1);

						boolean variazioneSuperficie = false;
						if (identificativoUtenzeSelezionateVariazione != null && !identificativoUtenzeSelezionateVariazione.isEmpty()) {
							for (int i = 0; i < dati.getVariazioneDom().size(); i++) {
								DatiImmobile datiImmobile = dati.getVariazioneDom().get(i);
								if (datiImmobile.getIdentificativoUtenza().equals(identificativoUtenzeSelezionateVariazione)) {

									if (datiImmobile.getVarDiSuperficieA() != null && !datiImmobile.getVarDiSuperficieA().isEmpty()) {
										variazioneSuperficie = true;
										break;
									}
								}
							}
						}
						if (numeroOccupanti == 1 && !variazioneSuperficie) {
							errors.rejectValue("varCompNucleoFam", "NotEmpty.field.variazioneDomestiche");
						}

					}
				}

				// cessazione
				if (tipoDichiarazione.equals(DichiarazioneTariPortletController.TIPO_DICHIARAZIONE_CESSAZIONE)
						|| DichiarazioneTariPortletController.TIPO_DICHIARAZIONE_TRASFERIMENTO.equalsIgnoreCase(tipoDichiarazione)) {

					String motivoCessazioneDom = dati.getMotivoCessazioneDom();

					if (motivoCessazioneDom != null) {
						if (motivoCessazioneDom.equals("decesso")) {
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nominativoDeceduto", "NotEmpty.field.required");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataDecesso", "NotEmpty.field.required");
						}

						if (motivoCessazioneDom.equals("coabitazione")) {
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "coabitanteDom", "NotEmpty.field.required");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "indirizzoCoabitazione", "NotEmpty.field.required");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "civicoCoabitazione", "NotEmpty.field.required");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "capCoabitazione", "NotEmpty.field.required");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "scalaCoabitazione", "NotEmpty.field.required");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pianoCoabitazione", "NotEmpty.field.required");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "intCoabitazione", "NotEmpty.field.required");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "suffCoabitazione", "NotEmpty.field.required");

							String civicoCoabitazione = dati.getCivicoCoabitazione();
							if (civicoCoabitazione != null && !civicoCoabitazione.isEmpty()) {
								if (!com.liferay.portal.kernel.util.Validator.isDigit(civicoCoabitazione)) {
									errors.rejectValue("civicoCoabitazione", "typeMismatch.number");
								}
							}

							String capCoabitazione = dati.getCapCoabitazione();
							if (capCoabitazione != null && !capCoabitazione.isEmpty()) {
								if (!capCoabitazione.matches("[0-9]{5}")) {
									errors.rejectValue("capCoabitazione", "CapFormat.field.required");
								}
							}

							if (dati.getEspCoabitazione() != null && !dati.getEspCoabitazione().isEmpty()) {
								if (dati.getEspCoabitazione().length() > 4) {
									errors.rejectValue("espCoabitazione", "error.field.max.length", new String[] { "\"Esponente\"", "4" }, null);
								}
							}

						}
						if (motivoCessazioneDom.equals(DichiarazioneTariPortletController.TIPO_DICHIARAZIONE_TRASFERIMENTO)) {
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comuneEmigrazioneDom", "NotEmpty.field.required");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "provEmigrazioneDom", "NotEmpty.field.required");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "viaEmigrazioneDom", "NotEmpty.field.required");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "civicoEmigrazioneDom", "NotEmpty.field.required");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "capEmigrazioneDom", "NotEmpty.field.required");

							String civicoEmigrazioneDom = dati.getCivicoEmigrazioneDom();
							if (civicoEmigrazioneDom != null && !civicoEmigrazioneDom.isEmpty()) {
								if (!com.liferay.portal.kernel.util.Validator.isDigit(civicoEmigrazioneDom)) {
									errors.rejectValue("civicoEmigrazioneDom", "typeMismatch.number");
								}
							}

							String capEmigrazioneDom = dati.getCapEmigrazioneDom();
							if (capEmigrazioneDom != null && !capEmigrazioneDom.isEmpty()) {
								if (!capEmigrazioneDom.matches("[0-9]{5}")) {
									errors.rejectValue("capEmigrazioneDom", "CapFormat.field.required");
								}
							}
						}
						if (motivoCessazioneDom.equals("altro")) {
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "altriMotiviDom", "NotEmpty.field.required");
							// TODO controllo lunghezza testo altriMotiviDom
						}

						if (dati.getConcessioneQuoteDom().equals("rimborso")) {

							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "modalitaRimborsoDom", "NotEmpty.field.required");
							String modalitaRimborsoDom = dati.getModalitaRimborsoDom();
							if (modalitaRimborsoDom != null && modalitaRimborsoDom.equals("accredito")) {
								ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ibanDom", "NotEmpty.field.required");
								String checkIBAN = PortletUtils.checkIBAN(dati.getIbanDom());
								if (checkIBAN != null) {
									errors.rejectValue("ibanDom", null, checkIBAN);
								}
							}
						}
					}
					else {
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "motivoCessazioneDom", "NotEmpty.field.required");
					}
				}

				// Controllo REA e PIVA se tipo persona giuridica
				if (dati.getTipoPersona().equals(DichiarazioneTariPortletController.PERSONA_GIURIDICA)) {
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numRea", "NotEmpty.field.required");
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "provinciaRea", "NotEmpty.field.required");
					if (com.liferay.portal.kernel.util.Validator.isNotNull(dati.getNumRea())) {
						if (!com.liferay.portal.kernel.util.Validator.isDigit(dati.getNumRea())) {
							errors.rejectValue("numRea", "typeMismatch.number");
						}
					}

					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "partitaIva", "NotEmpty.field.required");
					String partitaIva = dati.getPartitaIva();
					if (com.liferay.portal.kernel.util.Validator.isNotNull(partitaIva)) {
						String checkPartitaIVA = PortletUtils.checkPartitaIVA(partitaIva);
						if (checkPartitaIVA != null) {
							errors.rejectValue("partitaIva", null, checkPartitaIVA);
						}
					}
				}
			}

			if (tipoUtenza.equals(DichiarazioneTariPortletController.UTENZA_NON_DOMESTICA)) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numRea", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "provinciaRea", "NotEmpty.field.required");
				if (com.liferay.portal.kernel.util.Validator.isNotNull(dati.getNumRea())) {
					if (!com.liferay.portal.kernel.util.Validator.isDigit(dati.getNumRea())) {
						errors.rejectValue("numRea", "typeMismatch.number");
					}
				}

				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pec", "NotEmpty.field.required");
				// Controllo indirizzo email/pec
				if (com.liferay.portal.kernel.util.Validator.isNotNull(dati.getPec())) {
					if (!com.liferay.portal.kernel.util.Validator.isEmailAddress(dati.getPec())) {
						errors.rejectValue("pec", "typeMismatch.email");
					}
				}
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ruolo", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nomeSocieta", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "partitaIva", "NotEmpty.field.required");
				String partitaIva = dati.getPartitaIva();
				if (com.liferay.portal.kernel.util.Validator.isNotNull(partitaIva)) {
					String checkPartitaIVA = PortletUtils.checkPartitaIVA(partitaIva);
					if (checkPartitaIVA != null) {
						errors.rejectValue("partitaIva", null, checkPartitaIVA);
					}
				}
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comuneSedeLegale", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "viaSedeLegale", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "capSedeLegale", "NotEmpty.field.required");
				String capSedeLegale = dati.getCapSedeLegale();
				if (capSedeLegale != null && !capSedeLegale.isEmpty()) {
					if (!capSedeLegale.matches("[0-9]{5}")) {
						errors.rejectValue("capSedeLegale", "CapFormat.field.required");
					}
				}
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numeroSedeLegale", "NotEmpty.field.required");

				if (DichiarazioneTariPortletController.TIPO_DICHIARAZIONE_ISCRIZIONE.equalsIgnoreCase(tipoDichiarazione)
						|| DichiarazioneTariPortletController.TIPO_DICHIARAZIONE_TRASFERIMENTO.equalsIgnoreCase(tipoDichiarazione)) {

					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codiceAteco", "NotEmpty.field.required");
					if (com.liferay.portal.kernel.util.Validator.isNotNull(dati.getCodiceAteco())) {
						if (!dati.getCodiceAteco().matches("(0[1-9]|[1-9][0-9])\\.[1-9][0-9]\\.[0-9]{2}")) {
							errors.rejectValue("codiceAteco", "typeMismatch.ateco");
						}
					}

					// Riduzioni
					List<Riduzione> riduzioniNonDomesticheIscrizione = dati.getRiduzioniNonDomesticheIscrizione();
					if (riduzioniNonDomesticheIscrizione != null && !riduzioniNonDomesticheIscrizione.isEmpty()) {
						for (int i = 0; i < riduzioniNonDomesticheIscrizione.size(); i++) {
							Riduzione riduzione = riduzioniNonDomesticheIscrizione.get(i);
							if (riduzione.getCodiceArticolo() != null && (riduzione.getValori().isEmpty() || riduzione.getValori().get(i).getChiave() == null)) {
								errors.rejectValue("riduzioniNonDomesticheIscrizione[" + i + "].codiceArticolo", "NotEmpty.field.required");
							}
						}
					}
				}

				// Variazione
				if (tipoDichiarazione.equals(DichiarazioneTariPortletController.TIPO_DICHIARAZIONE_VARIAZIONE)) {

					List<Riduzione> riduzioniNonDomesticheVariazione = dati.getRiduzioniNonDomesticheVariazione();
					if (riduzioniNonDomesticheVariazione != null && !riduzioniNonDomesticheVariazione.isEmpty()) {
						for (int i = 0; i < riduzioniNonDomesticheVariazione.size(); i++) {
							Riduzione riduzione = riduzioniNonDomesticheVariazione.get(i);
							if (riduzione.getCodiceArticolo() != null && (riduzione.getValori().isEmpty() || riduzione.getValori().get(i).getChiave() == null)) {
								errors.rejectValue("riduzioniNonDomesticheVariazione[" + i + "].codiceArticolo", "NotEmpty.field.required");
							}
							if (riduzione.getCodiceArticolo() != null && (dati.getTipologiaRichiestaNonDom() == null || dati.getTipologiaRichiestaNonDom().isEmpty())) {
								errors.rejectValue("tipologiaRichiestaNonDom", "NotEmpty.field.required");
							}
						}
					}

					if (dati.isAltroNonDom()) {
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "altroNoteNonDom", "NotEmpty.field.required");
					}

					if (dati.getConcessioneQuoteNonDom().equals("rimborso")) {

						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "modalitaRimborsoNonDom", "NotEmpty.field.required");
						String modalitaRimborsoNonDom = dati.getModalitaRimborsoNonDom();
						if (modalitaRimborsoNonDom != null && modalitaRimborsoNonDom.equals("accredito")) {
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ibanNonDom", "NotEmpty.field.required");
							String checkIBAN = PortletUtils.checkIBAN(dati.getIbanNonDom());
							if (checkIBAN != null) {
								errors.rejectValue("ibanNonDom", null, checkIBAN);
							}
						}
					}
				}

				// Cessazione o Trasferimento
				if (tipoDichiarazione.equals(DichiarazioneTariPortletController.TIPO_DICHIARAZIONE_CESSAZIONE)
						|| tipoDichiarazione.equals(DichiarazioneTariPortletController.TIPO_DICHIARAZIONE_TRASFERIMENTO)) {

					String motivoCessazioneNonDom = dati.getMotivoCessazioneNonDom();
					if (motivoCessazioneNonDom != null) {
						if (motivoCessazioneNonDom.equals(DichiarazioneTariPortletController.TIPO_DICHIARAZIONE_CESSAZIONE)) {
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "attivitaCessata", "NotEmpty.field.required");
						}
						if (motivoCessazioneNonDom.equals("duplicazione")) {
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "coabitanteNonDom", "NotEmpty.field.required");
						}
						if (motivoCessazioneNonDom.equals("cambio_denominazione")) {
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vecchiaDenominazione", "NotEmpty.field.required");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nuovaDenominazione", "NotEmpty.field.required");
						}
						if (motivoCessazioneNonDom.equals(DichiarazioneTariPortletController.TIPO_DICHIARAZIONE_TRASFERIMENTO)) {
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comuneEmigrazioneNonDom", "NotEmpty.field.required");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "provEmigrazioneNonDom", "NotEmpty.field.required");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "viaEmigrazioneNonDom", "NotEmpty.field.required");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "civicoEmigrazioneNonDom", "NotEmpty.field.required");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "esponenteEmigrazioneNonDom", "NotEmpty.field.required");

							if (dati.getEsponenteEmigrazioneNonDom() != null && !dati.getEsponenteEmigrazioneNonDom().isEmpty()) {
								if (dati.getEsponenteEmigrazioneNonDom().length() > 4) {
									errors.rejectValue("esponenteEmigrazioneNonDom", "error.field.max.length", new String[] { "\"Esponente\"", "4" }, null);
								}
							}

							String civicoEmigrazioneNonDom = dati.getCivicoEmigrazioneNonDom();
							if (civicoEmigrazioneNonDom != null && !civicoEmigrazioneNonDom.isEmpty()) {
								if (!com.liferay.portal.kernel.util.Validator.isDigit(civicoEmigrazioneNonDom)) {
									errors.rejectValue("civicoEmigrazioneNonDom", "typeMismatch.number");
								}
							}
						}
						if (motivoCessazioneNonDom.equals("altro")) {
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "altriMotiviNonDom", "NotEmpty.field.required");
							// TODO controllo lunghezza testo altriMotiviDom
						}

						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "specificaRilascioImmobile", "NotEmpty.field.required");
						String specificaRilascio = dati.getSpecificaRilascioImmobile();

						if ("altro".equals(specificaRilascio)) {
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "altroSpecificaRilascioImmobile", "NotEmpty.field.required");
						}
						if (dati.getConcessioneQuoteNonDom().equals("rimborso")) {

							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "modalitaRimborsoNonDom", "NotEmpty.field.required");
							String modalitaRimborsoNonDom = dati.getModalitaRimborsoNonDom();
							if (modalitaRimborsoNonDom != null && modalitaRimborsoNonDom.equals("accredito")) {
								ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ibanNonDom", "NotEmpty.field.required");
								String checkIBAN = PortletUtils.checkIBAN(dati.getIbanNonDom());
								if (checkIBAN != null) {
									errors.rejectValue("ibanNonDom", null, checkIBAN);
								}
							}
						}
					}
					else {
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "motivoCessazioneNonDom", "NotEmpty.field.required");
					}
				}

				// Iscrizione o Trasferimento
				if (DichiarazioneTariPortletController.TIPO_DICHIARAZIONE_ISCRIZIONE.equalsIgnoreCase(tipoDichiarazione)
						|| DichiarazioneTariPortletController.TIPO_DICHIARAZIONE_TRASFERIMENTO.equalsIgnoreCase(tipoDichiarazione)) {
					boolean empty = true;
					for (int i = 0; i < dati.getIscrizioneNonDom().size(); i++) {
						DatiImmobile datiImmobile = dati.getIscrizioneNonDom().get(i);
						if (!"".equalsIgnoreCase(datiImmobile.getIndirizzo()) && !"0".equalsIgnoreCase(datiImmobile.getIndirizzo())) {
							empty = false;
							if (datiImmobile.getCivico() != null) {
								ValidationUtils.rejectIfEmptyOrWhitespace(errors, "iscrizioneNonDom[" + i + "].indirizzo", "Indirizzo.field.notCompleted");
								if (datiImmobile.getCivico().equalsIgnoreCase("0")) {
									errors.rejectValue("iscrizioneNonDom[" + i + "].indirizzo", "Indirizzo.field.notCompleted");
								}
							}
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "iscrizioneNonDom[" + i + "].cap", "NotEmpty.field.required");
							if (datiImmobile.getCap() != null && !datiImmobile.getCap().isEmpty()) {
								if (!datiImmobile.getCap().matches("[0-9]{5}")) {
									errors.rejectValue("iscrizioneNonDom[" + i + "].cap", "CapFormat.field.required");
								}
							}
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "iscrizioneNonDom[" + i + "].codAteco", "NotEmpty.field.required");
							if (com.liferay.portal.kernel.util.Validator.isNotNull(datiImmobile.getCodAteco())) {
								if (!datiImmobile.getCodAteco().matches("(0[1-9]|[1-9][0-9])\\.[1-9][0-9]\\.[0-9]{2}")) {
									errors.rejectValue("iscrizioneNonDom[" + i + "].codAteco", "typeMismatch.ateco");
								}
							}
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "iscrizioneNonDom[" + i + "].foglio", "NotEmpty.field.required");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "iscrizioneNonDom[" + i + "].particella", "NotEmpty.field.required");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "iscrizioneNonDom[" + i + "].utenzaConRiduzione", "NotEmpty.field.required");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "iscrizioneNonDom[" + i + "].tipologiaSuperficie", "NotEmpty.field.required");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "iscrizioneNonDom[" + i + "].superficieTotale", "NotEmpty.field.required");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "iscrizioneNonDom[" + i + "].superficieTassabile", "NotEmpty.field.required");

							// Validazione campi numerici
							if (com.liferay.portal.kernel.util.Validator.isNotNull(datiImmobile.getFoglio())) {
								if (!com.liferay.portal.kernel.util.Validator.isDigit(datiImmobile.getFoglio())) {
									errors.rejectValue("iscrizioneNonDom[" + i + "].foglio", "typeMismatch.number");
								}
							}
							if (com.liferay.portal.kernel.util.Validator.isNotNull(datiImmobile.getParticella())) {
								if (!com.liferay.portal.kernel.util.Validator.isDigit(datiImmobile.getParticella())) {
									errors.rejectValue("iscrizioneNonDom[" + i + "].particella", "typeMismatch.number");
								}
							}
							if (com.liferay.portal.kernel.util.Validator.isNotNull(datiImmobile.getSubalterno())) {
								if (!com.liferay.portal.kernel.util.Validator.isDigit(datiImmobile.getSubalterno())) {
									errors.rejectValue("iscrizioneNonDom[" + i + "].subalterno", "typeMismatch.number");
								}
							}

							if (datiImmobile.getEsponente() != null && !datiImmobile.getEsponente().isEmpty()) {
								if (datiImmobile.getEsponente().length() > 4) {
									errors.rejectValue("iscrizioneNonDom[" + i + "].esponente", "error.field.max.length", new String[] { "\"Esponente\"", "4" }, null);
								}
							}

						}
						validateRange(errors, datiImmobile.getSuperficieTassabile(), datiImmobile.getSuperficieTotale(), "iscrizioneNonDom[" + i + "].superficieTotale");
					}
					if (empty) {
						errors.rejectValue("iscrizioneNonDom[0].indirizzo", "Indirizzo.field.notCompleted");
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "iscrizioneNonDom[0].cap", "NotEmpty.field.required");
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "iscrizioneNonDom[0].codAteco", "NotEmpty.field.required");
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "iscrizioneNonDom[0].foglio", "NotEmpty.field.required");
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "iscrizioneNonDom[0].particella", "NotEmpty.field.required");
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "iscrizioneNonDom[0].utenzaConRiduzione", "NotEmpty.field.required");
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "iscrizioneNonDom[0].tipologiaSuperficie", "NotEmpty.field.required");
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "iscrizioneNonDom[0].superficieTotale", "NotEmpty.field.required");
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "iscrizioneNonDom[0].superficieTassabile", "NotEmpty.field.required");
					}
				}

				// Variazione
				if (DichiarazioneTariPortletController.TIPO_DICHIARAZIONE_VARIAZIONE.equalsIgnoreCase(tipoDichiarazione)) {
					if (identificativoUtenzeSelezionateVariazione != null) {
						// Controllo campi variazione o categoria
						for (int i = 0; i < dati.getVariazioneNonDom().size(); i++) {
							DatiImmobile datiImmobile = dati.getVariazioneNonDom().get(i);

							if (datiImmobile.getIdentificativoUtenza() == null && identificativoUtenzeSelezionateVariazione.isEmpty()) {

								if (!"".equalsIgnoreCase(datiImmobile.getIndirizzo()) && !"0".equalsIgnoreCase(datiImmobile.getIndirizzo())) {
									if (datiImmobile.getCivico() != null) {
										ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneNonDom[" + i + "].indirizzo", "Indirizzo.field.notCompleted");
										if (datiImmobile.getCivico().equalsIgnoreCase("0")) {
											errors.rejectValue("variazioneNonDom[" + i + "].indirizzo", "Indirizzo.field.notCompleted");
										}
									}

									ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneNonDom[" + i + "].codAteco", "NotEmpty.field.required");
									if (com.liferay.portal.kernel.util.Validator.isNotNull(datiImmobile.getCodAteco())) {
										if (!datiImmobile.getCodAteco().matches("(0[1-9]|[1-9][0-9])\\.[1-9][0-9]\\.[0-9]{2}")) {
											errors.rejectValue("variazioneNonDom[" + i + "].codAteco", "typeMismatch.ateco");
										}
									}

									ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneNonDom[" + i + "].cap", "NotEmpty.field.required");
									if (datiImmobile.getCap() != null && !datiImmobile.getCap().isEmpty()) {
										if (!datiImmobile.getCap().matches("[0-9]{5}")) {
											errors.rejectValue("variazioneNonDom[" + i + "].cap", "CapFormat.field.required");
										}
									}
									ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneNonDom[" + i + "].foglio", "NotEmpty.field.required");
									ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneNonDom[" + i + "].particella", "NotEmpty.field.required");
									ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneNonDom[" + i + "].varDiSuperficieDa", "NotEmpty.field.required");
									ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneNonDom[" + i + "].varDiSuperficieA", "NotEmpty.field.required");

									ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneNonDom[" + i + "].varDiCategoriaDa", "NotEmpty.field.required");
									if (com.liferay.portal.kernel.util.Validator.isNotNull(datiImmobile.getVarDiCategoriaDa())) {
										if (!datiImmobile.getVarDiCategoriaDa().matches("(0[1-9]|[1-9][0-9])\\.[1-9][0-9]\\.[0-9]{2}")) {
											errors.rejectValue("variazioneNonDom[" + i + "].varDiCategoriaDa", "typeMismatch.ateco");
										}
									}
									ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneNonDom[" + i + "].varDiCategoriaA", "NotEmpty.field.required");
									if (com.liferay.portal.kernel.util.Validator.isNotNull(datiImmobile.getVarDiCategoriaA())) {
										if (!datiImmobile.getVarDiCategoriaA().matches("(0[1-9]|[1-9][0-9])\\.[1-9][0-9]\\.[0-9]{2}")) {
											errors.rejectValue("variazioneNonDom[" + i + "].varDiCategoriaA", "typeMismatch.ateco");
										}
									}

									// Validazione campi numerici
									if (com.liferay.portal.kernel.util.Validator.isNotNull(datiImmobile.getFoglio())) {
										if (!com.liferay.portal.kernel.util.Validator.isDigit(datiImmobile.getFoglio())) {
											errors.rejectValue("variazioneNonDom[" + i + "].foglio", "typeMismatch.number");
										}
									}
									if (com.liferay.portal.kernel.util.Validator.isNotNull(datiImmobile.getParticella())) {
										if (!com.liferay.portal.kernel.util.Validator.isDigit(datiImmobile.getParticella())) {
											errors.rejectValue("variazioneNonDom[" + i + "].particella", "typeMismatch.number");
										}
									}
									if (com.liferay.portal.kernel.util.Validator.isNotNull(datiImmobile.getSubalterno())) {
										if (!com.liferay.portal.kernel.util.Validator.isDigit(datiImmobile.getSubalterno())) {
											errors.rejectValue("variazioneNonDom[" + i + "].subalterno", "typeMismatch.number");
										}
									}

									if (datiImmobile.getEsponente() != null && !datiImmobile.getEsponente().isEmpty()) {
										if (datiImmobile.getEsponente().length() > 4) {
											errors.rejectValue("variazioneNonDom[" + i + "].esponente", "error.field.max.length", new String[] { "\"Esponente\"", "4" }, null);
										}
									}

								}
								else {
									ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneNonDom[" + i + "].indirizzo", "Indirizzo.field.notCompleted");
								}

							}

							if (datiImmobile.getIdentificativoUtenza() != null && datiImmobile.getIdentificativoUtenza().equals(identificativoUtenzeSelezionateVariazione)) {

								ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneNonDom[" + i + "].codAteco", "NotEmpty.field.required");
								if (com.liferay.portal.kernel.util.Validator.isNotNull(datiImmobile.getCodAteco())) {
									if (!datiImmobile.getCodAteco().matches("(0[1-9]|[1-9][0-9])\\.[1-9][0-9]\\.[0-9]{2}")) {
										errors.rejectValue("variazioneNonDom[" + i + "].codAteco", "typeMismatch.ateco");
									}
								}

								ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneNonDom[" + i + "].varDiCategoriaDa", "NotEmpty.field.required");
								if (com.liferay.portal.kernel.util.Validator.isNotNull(datiImmobile.getVarDiCategoriaDa())) {
									if (!datiImmobile.getVarDiCategoriaDa().matches("(0[1-9]|[1-9][0-9])\\.[1-9][0-9]\\.[0-9]{2}")) {
										errors.rejectValue("variazioneNonDom[" + i + "].varDiCategoriaDa", "typeMismatch.ateco");
									}
								}
								ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneNonDom[" + i + "].varDiCategoriaA", "NotEmpty.field.required");
								if (com.liferay.portal.kernel.util.Validator.isNotNull(datiImmobile.getVarDiCategoriaA())) {
									if (!datiImmobile.getVarDiCategoriaA().matches("(0[1-9]|[1-9][0-9])\\.[1-9][0-9]\\.[0-9]{2}")) {
										errors.rejectValue("variazioneNonDom[" + i + "].varDiCategoriaA", "typeMismatch.ateco");
									}
								}

								// Almeno una tipologia di variazione deve essere applicata
								if (com.liferay.portal.kernel.util.Validator.isNull(datiImmobile.getVarDiCategoriaA())
										&& com.liferay.portal.kernel.util.Validator.isNull(datiImmobile.getVarDiSuperficieA())) {
									errors.rejectValue("variazioneNonDom[" + i + "].varDiSuperficieA", "NotEmpty.field.variazioneNonDomestiche");
								}
								// Solo una tipologia di variazione deve essere applicata
								if (com.liferay.portal.kernel.util.Validator.isNotNull(datiImmobile.getVarDiCategoriaA())
										&& com.liferay.portal.kernel.util.Validator.isNotNull(datiImmobile.getVarDiSuperficieA())) {
									errors.rejectValue("variazioneNonDom[" + i + "].varDiSuperficieA", "NotEmpty.field.variazioneNonDomesticheEsclusiva");
								}

								// Controllo solo numeri nel campo superficie
								if (com.liferay.portal.kernel.util.Validator.isNotNull(datiImmobile.getVarDiSuperficieA())) {
									if (!com.liferay.portal.kernel.util.Validator.isDigit(datiImmobile.getVarDiSuperficieA())) {
										errors.rejectValue("variazioneNonDom[" + i + "].varDiSuperficieA", "typeMismatch.number");
									}
								}
							}
						}
					}
					else {
						boolean empty = true;
						for (int i = 0; i < dati.getVariazioneNonDom().size(); i++) {
							DatiImmobile datiImmobile = dati.getVariazioneNonDom().get(i);
							if (!"".equalsIgnoreCase(datiImmobile.getIndirizzo()) && !"0".equalsIgnoreCase(datiImmobile.getIndirizzo())) {
								empty = false;
								if (datiImmobile.getCivico() != null) {
									ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneNonDom[" + i + "].indirizzo", "Indirizzo.field.notCompleted");
									if (datiImmobile.getCivico().equalsIgnoreCase("0")) {
										errors.rejectValue("variazioneNonDom[" + i + "].indirizzo", "Indirizzo.field.notCompleted");
									}
								}

								ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneNonDom[" + i + "].codAteco", "NotEmpty.field.required");
								if (com.liferay.portal.kernel.util.Validator.isNotNull(datiImmobile.getCodAteco())) {
									if (!datiImmobile.getCodAteco().matches("(0[1-9]|[1-9][0-9])\\.[1-9][0-9]\\.[0-9]{2}")) {
										errors.rejectValue("variazioneNonDom[" + i + "].codAteco", "typeMismatch.ateco");
									}
								}

								ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneNonDom[" + i + "].cap", "NotEmpty.field.required");
								if (datiImmobile.getCap() != null && !datiImmobile.getCap().isEmpty()) {
									if (!datiImmobile.getCap().matches("[0-9]{5}")) {
										errors.rejectValue("variazioneNonDom[" + i + "].cap", "CapFormat.field.required");
									}
								}

								ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneNonDom[" + i + "].foglio", "NotEmpty.field.required");
								ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneNonDom[" + i + "].numVisura", "NotEmpty.field.required");
								ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneNonDom[" + i + "].varDiSuperficieDa", "NotEmpty.field.required");
								ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneNonDom[" + i + "].varDiSuperficieA", "NotEmpty.field.required");

								ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneNonDom[" + i + "].varDiCategoriaDa", "NotEmpty.field.required");
								if (com.liferay.portal.kernel.util.Validator.isNotNull(datiImmobile.getVarDiCategoriaDa())) {
									if (!datiImmobile.getVarDiCategoriaDa().matches("(0[1-9]|[1-9][0-9])\\.[1-9][0-9]\\.[0-9]{2}")) {
										errors.rejectValue("variazioneNonDom[" + i + "].varDiCategoriaDa", "typeMismatch.ateco");
									}
								}
								ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneNonDom[" + i + "].varDiCategoriaA", "NotEmpty.field.required");
								if (com.liferay.portal.kernel.util.Validator.isNotNull(datiImmobile.getVarDiCategoriaA())) {
									if (!datiImmobile.getVarDiCategoriaA().matches("(0[1-9]|[1-9][0-9])\\.[1-9][0-9]\\.[0-9]{2}")) {
										errors.rejectValue("variazioneNonDom[" + i + "].varDiCategoriaA", "typeMismatch.ateco");
									}
								}

								// Validazione campi numerici
								if (com.liferay.portal.kernel.util.Validator.isNotNull(datiImmobile.getFoglio())) {
									if (!com.liferay.portal.kernel.util.Validator.isDigit(datiImmobile.getFoglio())) {
										errors.rejectValue("variazioneNonDom[" + i + "].foglio", "typeMismatch.number");
									}
								}
								if (com.liferay.portal.kernel.util.Validator.isNotNull(datiImmobile.getParticella())) {
									if (!com.liferay.portal.kernel.util.Validator.isDigit(datiImmobile.getParticella())) {
										errors.rejectValue("variazioneNonDom[" + i + "].particella", "typeMismatch.number");
									}
								}
								if (com.liferay.portal.kernel.util.Validator.isNotNull(datiImmobile.getSubalterno())) {
									if (!com.liferay.portal.kernel.util.Validator.isDigit(datiImmobile.getSubalterno())) {
										errors.rejectValue("variazioneNonDom[" + i + "].subalterno", "typeMismatch.number");
									}
								}

								if (datiImmobile.getEsponente() != null && !datiImmobile.getEsponente().isEmpty()) {
									if (datiImmobile.getEsponente().length() > 4) {
										errors.rejectValue("variazioneNonDom[" + i + "].esponente", "error.field.max.length", new String[] { "\"Esponente\"", "4" }, null);
									}
								}
							}
						}
						if (empty) {
							errors.rejectValue("variazioneNonDom[0].indirizzo", "Indirizzo.field.notCompleted");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneNonDom[0].cap", "NotEmpty.field.required");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneNonDom[0].codAteco", "NotEmpty.field.required");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneNonDom[0].foglio", "NotEmpty.field.required");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneNonDom[0].numVisura", "NotEmpty.field.required");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneNonDom[0].varDiSuperficieDa", "NotEmpty.field.required");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variazioneNonDom[0].varDiSuperficieA", "NotEmpty.field.required");
						}
					}
				}

				// Cessazione o Trasferimento
				if (DichiarazioneTariPortletController.TIPO_DICHIARAZIONE_CESSAZIONE.equalsIgnoreCase(tipoDichiarazione)
						|| DichiarazioneTariPortletController.TIPO_DICHIARAZIONE_TRASFERIMENTO.equalsIgnoreCase(tipoDichiarazione)) {
					if (dati.getIdentificativoUtenzeSelezionateCessazione() != null) {

						// data cessazione maggiore di data inizio utenza selezionata
						if (DateUtils.isData(dati.getaDecorrereDa())) {

							String _inizioUtenza = getDataInizioUtenzaFromIdentificativoUtenza(dati.getIdentificativoUtenzeSelezionateCessazione());
							Date inizioUtenza = DateUtils.getData(_inizioUtenza, "ddMMyyyy");
							Date fineUtenza = DateUtils.getData(dati.getaDecorrereDa(), "dd/MM/yyyy");
							if (com.liferay.portal.kernel.util.DateUtil.compareTo(inizioUtenza, fineUtenza, true) > 0) {
								errors.rejectValue("aDecorrereDa", "Cessazione.rangedata");
							}
						}

						// Controllo campi variazione o categoria
						for (int i = 0; i < dati.getCessazioneNonDom().size(); i++) {
							DatiImmobile datiImmobile = dati.getCessazioneNonDom().get(i);

							if (datiImmobile.getIdentificativoUtenza() == null && identificativoUtenzeSelezionateVariazione.isEmpty()) {

								if (!"".equalsIgnoreCase(datiImmobile.getIndirizzo()) && !"0".equalsIgnoreCase(datiImmobile.getIndirizzo())) {
									if (datiImmobile.getCivico() != null) {
										ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cessazioneNonDom[" + i + "].indirizzo", "Indirizzo.field.notCompleted");
										if (datiImmobile.getCivico().equalsIgnoreCase("0")) {
											errors.rejectValue("cessazioneNonDom[" + i + "].indirizzo", "Indirizzo.field.notCompleted");
										}
									}

									ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cessazioneNonDom[" + i + "].codAteco", "NotEmpty.field.required");
									if (com.liferay.portal.kernel.util.Validator.isNotNull(datiImmobile.getCodAteco())) {
										if (!datiImmobile.getCodAteco().matches("(0[1-9]|[1-9][0-9])\\.[1-9][0-9]\\.[0-9]{2}")) {
											errors.rejectValue("cessazioneNonDom[" + i + "].codAteco", "typeMismatch.ateco");
										}
									}

									ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cessazioneNonDom[" + i + "].cap", "NotEmpty.field.required");
									if (datiImmobile.getCap() != null && !datiImmobile.getCap().isEmpty()) {
										if (!datiImmobile.getCap().matches("[0-9]{5}")) {
											errors.rejectValue("cessazioneNonDom[" + i + "].cap", "CapFormat.field.required");
										}
									}

									ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cessazioneNonDom[" + i + "].foglio", "NotEmpty.field.required");
									ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cessazioneNonDom[" + i + "].particella", "NotEmpty.field.required");
									ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cessazioneNonDom[" + i + "].mq", "NotEmpty.field.required");

									// Validazione campi numerici
									if (com.liferay.portal.kernel.util.Validator.isNotNull(datiImmobile.getFoglio())) {
										if (!com.liferay.portal.kernel.util.Validator.isDigit(datiImmobile.getFoglio())) {
											errors.rejectValue("cessazioneNonDom[" + i + "].foglio", "typeMismatch.number");
										}
									}
									if (com.liferay.portal.kernel.util.Validator.isNotNull(datiImmobile.getParticella())) {
										if (!com.liferay.portal.kernel.util.Validator.isDigit(datiImmobile.getParticella())) {
											errors.rejectValue("cessazioneNonDom[" + i + "].particella", "typeMismatch.number");
										}
									}
									if (com.liferay.portal.kernel.util.Validator.isNotNull(datiImmobile.getSubalterno())) {
										if (!com.liferay.portal.kernel.util.Validator.isDigit(datiImmobile.getSubalterno())) {
											errors.rejectValue("cessazioneNonDom[" + i + "].subalterno", "typeMismatch.number");
										}
									}

									if (datiImmobile.getEsponente() != null && !datiImmobile.getEsponente().isEmpty()) {
										if (datiImmobile.getEsponente().length() > 4) {
											errors.rejectValue("cessazioneNonDom[" + i + "].esponente", "error.field.max.length", new String[] { "\"Esponente\"", "4" }, null);
										}
									}

								}
								else {
									ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cessazioneNonDom[" + i + "].indirizzo", "Indirizzo.field.notCompleted");
								}

							}

							if (datiImmobile.getIdentificativoUtenza() != null && datiImmobile.getIdentificativoUtenza().equals(identificativoUtenzeSelezionateVariazione)) {

								ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cessazioneNonDom[" + i + "].codAteco", "NotEmpty.field.required");
								if (com.liferay.portal.kernel.util.Validator.isNotNull(datiImmobile.getCodAteco())) {
									if (!datiImmobile.getCodAteco().matches("(0[1-9]|[1-9][0-9])\\.[1-9][0-9]\\.[0-9]{2}")) {
										errors.rejectValue("cessazioneNonDom[" + i + "].codAteco", "typeMismatch.ateco");
									}
								}
							}
						}
					}
					else {
						boolean empty = true;
						for (int i = 0; i < dati.getCessazioneNonDom().size(); i++) {
							DatiImmobile datiImmobile = dati.getCessazioneNonDom().get(i);
							if (!"".equalsIgnoreCase(datiImmobile.getIndirizzo()) && !"0".equalsIgnoreCase(datiImmobile.getIndirizzo())) {
								empty = false;
								if (datiImmobile.getCivico() != null) {
									ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cessazioneNonDom[" + i + "].indirizzo", "Indirizzo.field.notCompleted");
									if (datiImmobile.getCivico().equalsIgnoreCase("0")) {
										errors.rejectValue("cessazioneNonDom[" + i + "].indirizzo", "Indirizzo.field.notCompleted");
									}
								}

								ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cessazioneNonDom[" + i + "].codAteco", "NotEmpty.field.required");
								if (com.liferay.portal.kernel.util.Validator.isNotNull(datiImmobile.getCodAteco())) {
									if (!datiImmobile.getCodAteco().matches("(0[1-9]|[1-9][0-9])\\.[1-9][0-9]\\.[0-9]{2}")) {
										errors.rejectValue("cessazioneNonDom[" + i + "].codAteco", "typeMismatch.ateco");
									}
								}

								ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cessazioneNonDom[" + i + "].cap", "NotEmpty.field.required");
								if (datiImmobile.getCap() != null && !datiImmobile.getCap().isEmpty()) {
									if (!datiImmobile.getCap().matches("[0-9]{5}")) {
										errors.rejectValue("cessazioneNonDom[" + i + "].cap", "CapFormat.field.required");
									}
								}
								ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cessazioneNonDom[" + i + "].foglio", "NotEmpty.field.required");
								ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cessazioneNonDom[" + i + "].particella", "NotEmpty.field.required");
								ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cessazioneNonDom[" + i + "].mq", "NotEmpty.field.required");

								// Validazione campi numerici
								if (com.liferay.portal.kernel.util.Validator.isNotNull(datiImmobile.getFoglio())) {
									if (!com.liferay.portal.kernel.util.Validator.isDigit(datiImmobile.getFoglio())) {
										errors.rejectValue("cessazioneNonDom[" + i + "].foglio", "typeMismatch.number");
									}
								}
								if (com.liferay.portal.kernel.util.Validator.isNotNull(datiImmobile.getParticella())) {
									if (!com.liferay.portal.kernel.util.Validator.isDigit(datiImmobile.getParticella())) {
										errors.rejectValue("cessazioneNonDom[" + i + "].particella", "typeMismatch.number");
									}
								}
								if (com.liferay.portal.kernel.util.Validator.isNotNull(datiImmobile.getSubalterno())) {
									if (!com.liferay.portal.kernel.util.Validator.isDigit(datiImmobile.getSubalterno())) {
										errors.rejectValue("cessazioneNonDom[" + i + "].subalterno", "typeMismatch.number");
									}
								}

								if (datiImmobile.getEsponente() != null && !datiImmobile.getEsponente().isEmpty()) {
									if (datiImmobile.getEsponente().length() > 4) {
										errors.rejectValue("cessazioneNonDom[" + i + "].esponente", "error.field.max.length", new String[] { "\"Esponente\"", "4" }, null);
									}
								}
							}
						}
						if (empty) {
							errors.rejectValue("cessazioneNonDom[0].indirizzo", "Indirizzo.field.notCompleted");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cessazioneNonDom[0].cap", "NotEmpty.field.required");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cessazioneNonDom[0].codAteco", "NotEmpty.field.required");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cessazioneNonDom[0].foglio", "NotEmpty.field.required");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cessazioneNonDom[0].particella", "NotEmpty.field.required");
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cessazioneNonDom[0].mq", "NotEmpty.field.required");
						}
					}
				}
			}
		}
		catch (Exception e) {
			log.error("unable to  :: " + e.getMessage(), e);
		}
	}

	/**
	 * Restituisce la dataInizioUtenza in quarta posizione nella concatenazione
	 * idContribuente|idUtenza|dataVariazioneUtenza|dataInizioUtenza
	 *
	 * @param identificativoContribuente
	 * @return String
	 */
	private String getDataInizioUtenzaFromIdentificativoUtenza(String identificativoUtenza) {
		return identificativoUtenza.split("\\|")[3];
	}

	private List<String> getCheckArray(String[] vector) {
		List<String> result = new ArrayList<String>();
		if (vector != null) {
			for (int i = 0; i < vector.length; i++) {
				result.add(vector[i]);
			}
		}
		return result;
	}

	private void validateRange(Errors errors, String min, String max, String field) {
		try {
			if (Integer.parseInt(min) > Integer.parseInt(max)) {
				errors.rejectValue(field, "superficie.range");
			}
		}
		catch (Exception e) {
		}

		return;
	}
}
