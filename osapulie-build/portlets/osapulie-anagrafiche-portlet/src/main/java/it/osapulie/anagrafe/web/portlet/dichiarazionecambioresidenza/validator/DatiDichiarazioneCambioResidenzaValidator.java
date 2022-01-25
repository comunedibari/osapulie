package it.osapulie.anagrafe.web.portlet.dichiarazionecambioresidenza.validator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.osapulie.anagrafe.web.portlet.dichiarazionecambioresidenza.controller.DichiarazioneCambioResidenzaPortletController;
import it.osapulie.anagrafe.web.portlet.dichiarazionecambioresidenza.form.DatiDichiarazioneCambioResidenza;
import it.osapulie.anagrafe.web.portlet.dichiarazionecambioresidenza.form.Veicolo;
import it.osapulie.anagrafe.web.portlet.util.PortletUtils;
import it.osapulie.anagrafe.web.portlet.varie.form.Componente;
import it.osapulie.infrastructure.impl.DateUtils;

/**
 * Form validator per {@link DatiDichiarazioneCambioResidenza}.
 *
 * @author Maria Michela Birtolo
 * @author Gianluca Pindinelli
 * @author Antonio Magrì
 */
@Component("datiDichiarazioneCambioResidenzaValidator")
public class DatiDichiarazioneCambioResidenzaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return DatiDichiarazioneCambioResidenza.class.isAssignableFrom(clazz);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {

		DatiDichiarazioneCambioResidenza dati = (DatiDichiarazioneCambioResidenza) target;

		// controllo campi obbligatori
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tipoDichiarazione", "NotEmpty.field.required");
		
		
		// Controllo indirizzo email/pec
		if (com.liferay.portal.kernel.util.Validator.isNotNull(dati.getEmail())) {
			if (!com.liferay.portal.kernel.util.Validator.isEmailAddress(dati.getEmail())) {
				errors.rejectValue("email", "typeMismatch.email");
			}
		}
		if (com.liferay.portal.kernel.util.Validator.isNotNull(dati.getPec())) {
			if (!com.liferay.portal.kernel.util.Validator.isEmailAddress(dati.getPec())) {
				errors.rejectValue("pec", "typeMismatch.email");
			}
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "recEmail", "NotEmpty.field.required");
		if (com.liferay.portal.kernel.util.Validator.isNotNull(dati.getRecEmail())) {
			if (!com.liferay.portal.kernel.util.Validator.isEmailAddress(dati.getRecEmail())) {
				errors.rejectValue("recEmail", "typeMismatch.email");
			}
		}
		if (com.liferay.portal.kernel.util.Validator.isNotNull(dati.getRecPec())) {
			if (!com.liferay.portal.kernel.util.Validator.isEmailAddress(dati.getRecPec())) {
				errors.rejectValue("recPec", "typeMismatch.email");
			}
		}

		String tipoDichiarazione = dati.getTipoDichiarazione();
		if (tipoDichiarazione != null) {
			if (tipoDichiarazione.equalsIgnoreCase(DichiarazioneCambioResidenzaPortletController.TIPO_DICHIARAZIONE_STESSO_COMUNE)) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tipoCambioAbitazione", "NotEmpty.field.required");

				// Controllo identificativo utente
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "identificativoUtente", "error.identificativoUtente.required");

			}
			/*
			 * se il tipo di dichiarazione è "estero", è obbligatorio inserire lo stato estero di
			 * provenienza
			 */
			if (tipoDichiarazione.equalsIgnoreCase(DichiarazioneCambioResidenzaPortletController.TIPO_DICHIARAZIONE_ESTERO)
					|| tipoDichiarazione.equalsIgnoreCase(DichiarazioneCambioResidenzaPortletController.TIPO_DICHIARAZIONE_AIRE)) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "statoEstero", "NotEmpty.field.required");
			}

			if (tipoDichiarazione.equalsIgnoreCase(DichiarazioneCambioResidenzaPortletController.TIPO_DICHIARAZIONE_AIRE)) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comuneIscrizioneAIRE", "NotEmpty.field.required");
			}

			/*
			 * se il tipo di dichiarazione è "altro", è obbligatorio inserire il motivo nella
			 * variabile altroMotivoDichiarazione
			 */
			if (tipoDichiarazione.equalsIgnoreCase(DichiarazioneCambioResidenzaPortletController.TIPO_DICHIARAZIONE_ALTRO)) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "altroMotivoDichiarazione", "NotEmpty.field.required");
			}

			/*
			 * se il tipo di dichiarazione è "altroComune", è obbligatorio inserire la provenienza
			 */
			if (tipoDichiarazione.equalsIgnoreCase(DichiarazioneCambioResidenzaPortletController.TIPO_DICHIARAZIONE_ALTRO_COMUNE)) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comuneResidenza", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "provinciaResidenza", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "indirizzoResidenza", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "civicoResidenza", "NotEmpty.field.required");
				String civicoResidenza = dati.getCivicoResidenza();
				if (com.liferay.portal.kernel.util.Validator.isNotNull(civicoResidenza)) {
					if (!com.liferay.portal.kernel.util.Validator.isDigit(civicoResidenza)) {
						errors.rejectValue("civicoResidenza", "typeMismatch.number");
					}
				}
			}
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sesso", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codiceFiscale", "NotEmpty.field.required");

		String codiceFiscaleDichiarante = dati.getCodiceFiscale();
		if (codiceFiscaleDichiarante != null) {
			String checkCodiceFiscale = PortletUtils.checkCodiceFiscale(codiceFiscaleDichiarante);
			if (checkCodiceFiscale != null) {
				errors.rejectValue("codiceFiscale", null, checkCodiceFiscale);
			}
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataNascita", "NotEmpty.field.required");

		// Validazione comune nascita in base allo stato
		if (dati.getStatoEsteroNascita() != null && dati.getStatoEsteroNascita().equals("")) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comuneNascita", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "provinciaNascita", "NotEmpty.field.required");
		}
		else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comuneNascitaEstero", "NotEmpty.field.required");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cittadinanza", "NotEmpty.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "statoCivile", "NotEmpty.field.required");

		if (com.liferay.portal.kernel.util.Validator.isNull(dati.getNuovaVia())) {
			errors.rejectValue("nuovaVia", "NotEmpty.field.required");
		}
		
		// campo nuovo piano Residenza reso obbligatorio
		
		if (com.liferay.portal.kernel.util.Validator.isNull(dati.getNuovoPiano())) {
			errors.rejectValue("nuovoPiano", "NotEmpty.field.required");
		}
		
		// campo detaglio numero piano nuova residenza reso obligatorio
		if (com.liferay.portal.kernel.util.Validator.isNull(dati.getDettaglioPianoResidenza())) {
			errors.rejectValue("dettaglioPianoResidenza", "NotEmpty.field.required");
		}
		
		//  campo interno nuova abitazione reso obbligatorio
		
		if (com.liferay.portal.kernel.util.Validator.isNull(dati.getNuovoInterno())) {
			errors.rejectValue("nuovoInterno", "NotEmpty.field.required");
		}
		
			
		// validazione risposte di documentazioneTitoloAbitativo in Dati relativi all'abitazione 7  campi
		
		if (com.liferay.portal.kernel.util.Validator.equals(dati.getTitoloAbitativo2(),"C")) {
			errors.rejectValue("titoloAbitativo2", "NotEmpty.field.error");
		}
		if (com.liferay.portal.kernel.util.Validator.equals(dati.getTitoloAbitativo3(),"B")) {
			errors.rejectValue("titoloAbitativo3", "NotEmpty.field.error");
		}
		if (com.liferay.portal.kernel.util.Validator.equals(dati.getTitoloAbitativo4(),"B")) {
			errors.rejectValue("titoloAbitativo4","NotEmpty.field.error" );
		}
		if (com.liferay.portal.kernel.util.Validator.equals(dati.getTitoloAbitativo5(),"B")) {
			errors.rejectValue("titoloAbitativo5", "NotEmpty.field.error" );
		}
		if (com.liferay.portal.kernel.util.Validator.equals(dati.getTitoloAbitativo7(),"C")) {
			errors.rejectValue("titoloAbitativo7", "NotEmpty.field.error" );
		}
		///////////////////////////////////////////////////////////////////////////////////////////////
			
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nuovoNum", "NotEmpty.field.required");

		// se c'è lo stato estero devono esserci anche indEstero e i dati sul
		// soggiorno
		// if (!dati.getStatoEstero().equals("") && !dati.getStatoEstero().equals("")) {
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "questura", "NotEmpty.field.required");
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataRilascio",
		// "NotEmpty.field.required");
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numSogg", "NotEmpty.field.required");
		// }

		// controllo la correttezza delle date
		if (dati.getDataNascita() != null && !dati.getDataNascita().equals("") && !DateUtils.isData(dati.getDataNascita())) {
			errors.rejectValue("dataNascita", "NotIsDate.field.required");
		}
		if (dati.getDataRilascio() != null && !dati.getDataRilascio().equals("") && !DateUtils.isData(dati.getDataRilascio())) {
			errors.rejectValue("dataRilascio", "NotIsDate.field.required");
		}
		if (dati.getDataRilascioPatente() != null && !dati.getDataRilascioPatente().equals("") && !DateUtils.isData(dati.getDataRilascioPatente())) {
			errors.rejectValue("dataRilascioPatente", "NotIsDate.field.required");
		}

		if (com.liferay.portal.kernel.util.Validator.isNotNull(dati.getTipoPatente())) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numPatente", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataRilascioPatente", "NotEmpty.field.required");
		}

		String numPatente = dati.getNumPatente();
		if (com.liferay.portal.kernel.util.Validator.isNotNull(numPatente)) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tipoPatente", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataRilascioPatente", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "organoRilascioPatente", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "provPatente", "NotEmpty.field.required");
			String checkNumeroPatenteGuida = PortletUtils.checkNumeroPatenteGuida(numPatente);
			if (checkNumeroPatenteGuida != null) {
				errors.rejectValue("numPatente", null, checkNumeroPatenteGuida);
			}
		}

		// Veicoli
		List<Veicolo> veicoli = dati.getVeicoli();
		if (veicoli != null) {
			for (int i = 0; i < veicoli.size(); i++) {
				Veicolo veicolo = veicoli.get(i);
				String targa = veicolo.getTarga();
				if (veicolo != null && targa != null && !targa.isEmpty()) {
					if (targa.length() > 8) {
						errors.rejectValue("veicoli[" + i + "].targa", "error.field.max8caratteri");
					}
				}
			}
		}

		/*
		 * Validazione campi associati ai familiari
		 *
		 */
		if (dati.getFamiliari() != null) {
			Set<String> codiciFiscaliSet = new HashSet<String>();
			codiciFiscaliSet.add(codiceFiscaleDichiarante);

			int occupantiSize = dati.getFamiliari().size();
			for (int i = 0; i < occupantiSize; i++) {
				Componente componente = dati.getFamiliari().get(i);

				if (tipoDichiarazione != null && tipoDichiarazione.equalsIgnoreCase(DichiarazioneCambioResidenzaPortletController.TIPO_DICHIARAZIONE_STESSO_COMUNE)) {
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "familiari[" + i + "].identificativoUtente", "error.identificativoUtente.required");
				}

				// Controllo indirizzo email/pec
				if (com.liferay.portal.kernel.util.Validator.isNotNull(componente.getEmail())) {
					if (!com.liferay.portal.kernel.util.Validator.isEmailAddress(componente.getEmail())) {
						errors.rejectValue("familiari[" + i + "].email", "typeMismatch.email");
					}
				}
				if (com.liferay.portal.kernel.util.Validator.isNotNull(componente.getPec())) {
					if (!com.liferay.portal.kernel.util.Validator.isEmailAddress(componente.getPec())) {
						errors.rejectValue("familiari[" + i + "].pec", "typeMismatch.email");
					}
				}

				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "familiari[" + i + "].nome", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "familiari[" + i + "].cognome", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "familiari[" + i + "].codiceFiscale", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "familiari[" + i + "].statoCivile", "NotEmpty.field.required");
				if (componente.getCodiceFiscale() != null) {
					String checkCodiceFiscale = PortletUtils.checkCodiceFiscale(componente.getCodiceFiscale());
					if (checkCodiceFiscale != null) {
						errors.rejectValue("familiari[" + i + "].codiceFiscale", null, checkCodiceFiscale);
					}
					codiciFiscaliSet.add(componente.getCodiceFiscale());
				}
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "familiari[" + i + "].dataNascitaString", "NotEmpty.field.required");
				// Validazione comune nascita in base allo stato
				if (componente.getStatoEsteroNascita() != null && componente.getStatoEsteroNascita().equals("")) {
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "familiari[" + i + "].comuneNascita", "NotEmpty.field.required");
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "familiari[" + i + "].provinciaNascita", "NotEmpty.field.required");
				}
				else {
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "familiari[" + i + "].comuneNascitaEstero", "NotEmpty.field.required");
				}
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "familiari[" + i + "].sesso", "NotEmpty.field.required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "familiari[" + i + "].parentela", "NotEmpty.field.required");

				if (!componente.getDataNascitaString().equals("") && !DateUtils.isData(componente.getDataNascitaString())) {
					errors.rejectValue("familiari[" + i + "].dataNascitaString", "NotIsDate.field.required");
				}

				if ("".equalsIgnoreCase(componente.getCittadinanza())) {
					errors.rejectValue("familiari[" + i + "].cittadinanza", "NotEmpty.field.required");
				}

				if (!componente.getDataRilascioPatente().equals("") && !DateUtils.isData(componente.getDataRilascioPatente())) {
					errors.rejectValue("familiari[" + i + "].dataRilascioPatente", "NotIsDate.field.required");
				}

				if (com.liferay.portal.kernel.util.Validator.isNotNull(componente.getTipoPatente())) {
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "familiari[" + i + "].numPatente", "NotEmpty.field.required");
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "familiari[" + i + "].dataRilascioPatente", "NotEmpty.field.required");
				}

				if (com.liferay.portal.kernel.util.Validator.isNotNull(componente.getNumPatente())) {
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "familiari[" + i + "].tipoPatente", "NotEmpty.field.required");
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "familiari[" + i + "].dataRilascioPatente", "NotEmpty.field.required");
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "familiari[" + i + "].organoRilascioPatente", "NotEmpty.field.required");
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "familiari[" + i + "].provPatente", "NotEmpty.field.required");
					String checkNumeroPatenteGuida = PortletUtils.checkNumeroPatenteGuida(componente.getNumPatente());
					if (checkNumeroPatenteGuida != null) {
						errors.rejectValue("familiari[" + i + "].numPatente", null, checkNumeroPatenteGuida);
					}
				}

				// Veicoli
				List<Veicolo> veicoliComponente = componente.getVeicoli();
				if (veicoliComponente != null) {
					for (int j = 0; j < veicoliComponente.size(); j++) {
						Veicolo veicolo = veicoliComponente.get(j);
						String targa = veicolo.getTarga();
						if (veicolo != null && targa != null && !targa.isEmpty()) {
							if (targa.length() > 8) {
								errors.rejectValue("familiari[" + i + "].veicoli[" + j + "].targa", "error.field.max8caratteri");
							}
						}
					}
				}
			}

			if (codiciFiscaliSet.size() != occupantiSize + 1) {
				errors.rejectValue("familiari[0].codiceFiscale", "error.codicifiscali.univoci");
			}
		}

		/* Validazione in caso di inserimento di una persona già iscritta */
		if (dati.isFlagIscritto()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "iscrittoCognome", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "iscrittoNome", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "iscrittoLuogoNascita", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "iscrittoDataNascita", "NotEmpty.field.required");

			if (!dati.getIscrittoDataNascita().equals("") && !DateUtils.isData(dati.getIscrittoDataNascita())) {
				errors.rejectValue("iscrittoDataNascita", "NotIsDate.field.required");
			}
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "iscrittoParentela", "NotEmpty.field.required");
			if (dati.isIscrittoParentela()) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "iscrittoTipoParentela", "NotEmpty.field.required");
			}

		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titoloAbitativo", "NotEmpty.field.required");

		ValidationUtils.rejectIfEmpty(errors, "dichiarazioneTitoloAbitativo", "NotEmpty.field.required");

		if (!dati.isDichiarazioneTitoloAbitativo()) {
			ValidationUtils.rejectIfEmpty(errors, "dichiarazioneTitoloAbitativo", "NotEmpty.field.required");
		}

		// Validazione campi numerici
		if (com.liferay.portal.kernel.util.Validator.isNotNull(dati.getFoglio())) {
			if (!com.liferay.portal.kernel.util.Validator.isDigit(dati.getFoglio())) {
				errors.rejectValue("foglio", "typeMismatch.number");
			}
		}
		if (com.liferay.portal.kernel.util.Validator.isNotNull(dati.getParticella())) {
			if (!com.liferay.portal.kernel.util.Validator.isDigit(dati.getParticella())) {
				errors.rejectValue("particella", "typeMismatch.number");
			}
		}
		if (com.liferay.portal.kernel.util.Validator.isNotNull(dati.getSubalterno())) {
			if (!com.liferay.portal.kernel.util.Validator.isDigit(dati.getSubalterno())) {
				errors.rejectValue("subalterno", "typeMismatch.number");
			}
		}

		// Recapito numero civico
		if (com.liferay.portal.kernel.util.Validator.isNotNull(dati.getRecCivico())) {
			if (!com.liferay.portal.kernel.util.Validator.isDigit(dati.getRecCivico())) {
				errors.rejectValue("recCivico", "typeMismatch.number");
			}
		}

		if (DichiarazioneCambioResidenzaPortletController.TITOLO_ABITATIVO_INTESTATARIO_CONTRATTO.equalsIgnoreCase(dati.getTitoloAbitativo())) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titoloAbitativoAgenzia1", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titoloAbitativoData1", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titoloAbitativoNumero1", "NotEmpty.field.required");
			if (!dati.getTitoloAbitativoData1().equals("") && !DateUtils.isData(dati.getTitoloAbitativoData1())) {
				errors.rejectValue("titoloAbitativoData1", "NotIsDate.field.required");
			}
			if (!isANumberOrEmpty(dati.getTitoloAbitativoNumero1())) {
				errors.rejectValue("titoloAbitativoNumero1", "Number.field.format");
			}
		}
		if (DichiarazioneCambioResidenzaPortletController.TITOLO_ABITATIVO_CONTRATTO_COMODATO_GRATUITO.equalsIgnoreCase(dati.getTitoloAbitativo())) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titoloAbitativoAgenzia2", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titoloAbitativoData2", "NotEmpty.field.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titoloAbitativoNumero2", "NotEmpty.field.required");
			if (!dati.getTitoloAbitativoData2().equals("") && !DateUtils.isData(dati.getTitoloAbitativoData2())) {
				errors.rejectValue("titoloAbitativoData2", "NotIsDate.field.required");
			}
			if (!isANumberOrEmpty(dati.getTitoloAbitativoNumero1())) {
				errors.rejectValue("titoloAbitativoData2", "Number.field.format");
			}
		}
		if (DichiarazioneCambioResidenzaPortletController.TITOLO_ABITATIVO_USUFRUTTUARIO.equalsIgnoreCase(dati.getTitoloAbitativo())) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titoloAbitativoAltro1", "NotEmpty.field.required");
		}
		if (DichiarazioneCambioResidenzaPortletController.TITOLO_ABITATIVO_ALTRO.equalsIgnoreCase(dati.getTitoloAbitativo())) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titoloAbitativoAltro2", "NotEmpty.field.required");
		}

		//MS controllo se necessario la firma grafometrica
		if(dati.isFirmaGrafometrica())
		{
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firmaGrafometricaBase64", "NotEmpty.field.required");
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
}
