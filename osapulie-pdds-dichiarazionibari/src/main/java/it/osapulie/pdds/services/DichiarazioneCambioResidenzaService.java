/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pdds.services;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.insielmercato.egov.services.registrazionepratiche.ws.ClosurePracticeRequest;
import it.insielmercato.egov.services.registrazionepratiche.ws.ClosurePracticeResponse;
import it.insielmercato.egov.services.registrazionepratiche.ws.ComponenteCambioResidenza;
import it.insielmercato.egov.services.registrazionepratiche.ws.DeletePraticaRequest;
import it.insielmercato.egov.services.registrazionepratiche.ws.DeletePraticaResponse;
import it.insielmercato.egov.services.registrazionepratiche.ws.InfoIndirizzo;
import it.insielmercato.egov.services.registrazionepratiche.ws.InfoPraticaCambioResidenza;
import it.insielmercato.egov.services.registrazionepratiche.ws.MotivoDelloSpostamento;
import it.insielmercato.egov.services.registrazionepratiche.ws.RegistraCambioAbitazioneRequest;
import it.insielmercato.egov.services.registrazionepratiche.ws.RegistraCambioResidenzaRequest;
import it.insielmercato.egov.services.registrazionepratiche.ws.RegistrazionePraticaResponse;
import it.insielmercato.egov.services.registrazionepratiche.ws.RegistrazionePratiche;
import it.insielmercato.egov.services.registrazionepratiche.ws.RegistrazionePraticheImplService;
import it.insielmercato.egov.services.registrazionepratiche.ws.Richiedente;
import it.insielmercato.egov.services.registrazionepratiche.ws.ServiceResult;
import it.insielmercato.egov.services.registrazionepratiche.ws.TipoRegistrazioneFamiglia;
import it.insielmercato.egov.services.registrazionepratiche.ws.TipologieOccupazioneImmobile;
import it.osapulie.anagrafe.web.ws.input.types.Codifica;
import it.osapulie.anagrafe.web.ws.input.types.Componente;
import it.osapulie.anagrafe.web.ws.input.types.ContrattoLocazione;
import it.osapulie.anagrafe.web.ws.input.types.DatiCatastali;
import it.osapulie.anagrafe.web.ws.input.types.Dichiarante;
import it.osapulie.anagrafe.web.ws.input.types.Dichiarazione;
import it.osapulie.anagrafe.web.ws.input.types.DichiarazioneCambioResidenzaRichiesta;
import it.osapulie.anagrafe.web.ws.input.types.DichiarazioneCambioResidenzaRisposta;
import it.osapulie.anagrafe.web.ws.input.types.DichiarazioneCambioResidenzaRisposta.Errore;
import it.osapulie.anagrafe.web.ws.input.types.Documento;
import it.osapulie.anagrafe.web.ws.input.types.Immobile;
import it.osapulie.anagrafe.web.ws.input.types.Indirizzo;
import it.osapulie.anagrafe.web.ws.input.types.Indirizzo.Civico;
import it.osapulie.anagrafe.web.ws.input.types.Iscrizione;
import it.osapulie.anagrafe.web.ws.input.types.IscrizioneAltroMotivo;
import it.osapulie.anagrafe.web.ws.input.types.IscrizioneDaStatoEstero;
import it.osapulie.anagrafe.web.ws.input.types.IscrizioneDaStatoEsteroAIRE;
import it.osapulie.anagrafe.web.ws.input.types.Patente;
import it.osapulie.anagrafe.web.ws.input.types.TitoloOccupazioneImmobile;
import it.osapulie.anagrafe.web.ws.input.types.TitoloOccupazioneImmobile.ContrattoLocazioneComodatario;
import it.osapulie.anagrafe.web.ws.input.types.Trasferimento;
import it.osapulie.anagrafe.web.ws.input.types.Veicolo;
import it.osapulie.pdds.adapter.util.XMLHelper;
import it.osapulie.pdds.client.email.Attachment;
import it.osapulie.pdds.client.email.Contact;
import it.osapulie.pdds.client.email.EmailClient;
import it.osapulie.pdds.client.email.Message;
import it.osapulie.pdds.client.protocollo.ProtocolloClient;
import it.osapulie.pdds.client.protocollo.ProtocolloRichiesta;
import it.osapulie.pdds.client.protocollo.ProtocolloRisposta;
import it.osapulie.pdds.common.PddService;

/**
 * @author Gianluca Pindinelli
 *
 */
public class DichiarazioneCambioResidenzaService implements PddService {

	private final Logger log = LoggerFactory.getLogger(DichiarazioneTassaRifiutiService.class);

	private static final String DICHIARAZIONE_SUBJECT = "Dichiarazione CAMBIO RESIDENZA/ISCRIZIONE ANAGRAFICA";
	private static final String TIPO_REGISTRAZIONE_INTERO_NUCLEO_FAMILIARE = "1";
	private static final String TIPO_REGISTRAZIONE_CREA_NUOVA_FAMIGLIA = "2";

	private static final String METADATI_DICHIARAZIONE_FILENAME_PREFIX = "METADATI_DICHIARAZIONE_CAMBIO_RESIDENZA_";
	private static final String METADATI_DICHIARAZIONE_FILENAME_SUFFIX = ".xml";

	private static final String NOME_SERVIZIO = "dichiarazioneCambioResidenza";

	private static final String WSDL_ASCOTWEB = "wsdl/ascotweb/ascotweb.wsdl";

	private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	private ProtocolloClient protocolloClient;
	private EmailClient emailClient;
	private XMLHelper xmlHelper;

	private String webserviceUrl;
	private String emailAddressDichiarazioniResidenza;
	private String identificativoUtente;
	private String passwordUtente;

	private String amministrazioneProtocollo;
	private String aooProtocollo;
	private String classificazioneProtocollo;

	/**
	 * Errore GRAVE/bloccante
	 */
	private static final int ERROR_CODE_1 = 1;
	/**
	 * Errore saltuario ma da verificare
	 */
	private static final int ERROR_CODE_2 = 2;
	/**
	 * Errore non bloccante (ad es. invio email fallito)
	 */
	private static final int ERROR_CODE_3 = 3;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.pdds.common.PddService#getResponse(java.lang.String)
	 */
	@Override
	public String getResponse(String xml) {

		DichiarazioneCambioResidenzaRichiesta dichiarazioneCambioResidenzaRichiesta = xmlHelper.unmarshal(xml, DichiarazioneCambioResidenzaRichiesta.class);

		String result = null;
		try {
			DichiarazioneCambioResidenzaRisposta risp = risposta(dichiarazioneCambioResidenzaRichiesta, xml);
			result = xmlHelper.marshal(risp);
		}
		catch (Exception e) {
			log.error("getResponse :: " + e.getMessage(), e);
		}

		return result;
	}

	/**
	 * @param richiesta
	 * @param xml
	 * @return
	 */
	private DichiarazioneCambioResidenzaRisposta risposta(DichiarazioneCambioResidenzaRichiesta richiesta, String xml) {

		DichiarazioneCambioResidenzaRisposta risposta = null;

		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			URL wsdlLocation = classloader.getResource(WSDL_ASCOTWEB);

			RegistrazionePraticheImplService registrazionePraticheImplService = new RegistrazionePraticheImplService(wsdlLocation);
			RegistrazionePratiche registrazionePraticheImplPort = registrazionePraticheImplService.getRegistrazionePraticheImplPort();

			BindingProvider provider = (BindingProvider) registrazionePraticheImplPort;
			String url = webserviceUrl;
			if (webserviceUrl.contains("esb")) {
				// url += "/" + operation;
			}

			Map<String, Object> requestContext = provider.getRequestContext();
			requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);
			requestContext.put(BindingProvider.USERNAME_PROPERTY, identificativoUtente);
			requestContext.put(BindingProvider.PASSWORD_PROPERTY, passwordUtente);
			Map<String, List<String>> headers = new HashMap<String, List<String>>();
			requestContext.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

			Iscrizione iscrizione = richiesta.getIscrizione();
			IscrizioneDaStatoEstero iscrizioneDaStatoEstero = richiesta.getIscrizioneDaStatoEstero();
			IscrizioneDaStatoEsteroAIRE iscrizioneAIRE = richiesta.getIscrizioneAIRE();
			Trasferimento trasferimento = richiesta.getTrasferimento();
			IscrizioneAltroMotivo altro = richiesta.getAltro();

			String codiceFiscaleDichiarante = null;
			String note = null;
			// Dichiarazione cambio residenza - ISCRIZIONI
			List<Documento> documenti = richiesta.getDocumento();

			if (iscrizione != null || iscrizioneDaStatoEstero != null || iscrizioneAIRE != null || altro != null) {

				RegistraCambioResidenzaRequest cambioResidenzaRequest = new RegistraCambioResidenzaRequest();
				InfoPraticaCambioResidenza infoPratica = new InfoPraticaCambioResidenza();

				Dichiarazione dichiarazione = null;
				if (iscrizione != null) {
					dichiarazione = iscrizione;
				}
				else if (iscrizioneDaStatoEstero != null) {
					dichiarazione = iscrizioneDaStatoEstero;
					Codifica statoEstero = iscrizioneDaStatoEstero.getStatoEstero();
					if (statoEstero != null) {
						infoPratica.setCodiceStatoEsteroProvenienzaFamiglia(statoEstero.getCodice());
					}
				}
				else if (iscrizioneAIRE != null) {
					dichiarazione = iscrizioneAIRE;
					Codifica comuneIscrizioneAIRE = iscrizioneAIRE.getComuneIscrizioneAIRE();
					Codifica statoEstero = iscrizioneAIRE.getStatoEstero();
					infoPratica.setCodiceComuneProvenienzaFamiglia(comuneIscrizioneAIRE != null ? comuneIscrizioneAIRE.getCodice() : null);
					infoPratica.setCodiceStatoEsteroProvenienzaFamiglia(statoEstero != null ? statoEstero.getCodice() : null);
				}
				else if (altro != null) {
					dichiarazione = altro;
					infoPratica.setAltroMotivoFamiglia(Integer.parseInt(altro.getAltroMotivo().getCodice()));
				}

				note = dichiarazione.getNote();

				Dichiarante dichiarante = dichiarazione.getDichiarante();
				codiceFiscaleDichiarante = dichiarante.getCodiceFiscale();

				// Aggiunta documento XML per invio email
				Documento documentoXml = getDocumentoXml(xml, codiceFiscaleDichiarante);
				documenti.add(documentoXml);

				List<ComponenteCambioResidenza> famiglia = cambioResidenzaRequest.getFamiglia();
				ComponenteCambioResidenza dichiaranteRichiesta = getComponenteCambioResidenza(dichiarante);

				// Intestatario scheda
				dichiaranteRichiesta.setCodiceRelazioneParentela(1);

				famiglia.add(dichiaranteRichiesta);

				// Aggiunta altri familiari
				List<Componente> familiari = dichiarazione.getFamiliare();
				if (familiari != null) {
					for (Componente componente : familiari) {
						ComponenteCambioResidenza familiareComponenteCambioResidenza = getComponenteCambioResidenza(componente);
						famiglia.add(familiareComponenteCambioResidenza);
					}
				}

				infoPratica.setCellulareRichiedente(dichiarante.getCellulare());

				// Se iscrizione o altro --> comune di provenienza
				if (iscrizione != null || altro != null) {
					Immobile immobileProvenienza = dichiarante.getResidenza();
					if (immobileProvenienza != null) {
						Indirizzo provenienza = immobileProvenienza.getIndirizzo();
						if (provenienza != null) {
							Codifica comune = provenienza.getComune();
							if (comune != null) {
								infoPratica.setCodiceComuneProvenienzaFamiglia(comune.getCodice());
							}
						}
					}
				}
				infoPratica.setCodiceFiscaleRichiedente(dichiarante.getCodiceFiscale());
				infoPratica.setFaxRichiedente(dichiarante.getFax());
				infoPratica.setMailRichiedente(dichiarante.getEmail());
				infoPratica.setPecRichiedente(dichiarante.getPec());
				infoPratica.setTelefonoRichiedente(dichiarante.getTelefono());

				cambioResidenzaRequest.setInfoPratica(infoPratica);

				Indirizzo indirizzoAbitazione = dichiarazione.getAbitazione().getIndirizzo();

				InfoIndirizzo infoVia = getInfoIndirizzo(indirizzoAbitazione);

				cambioResidenzaRequest.setInfoVia(infoVia);

				MotivoDelloSpostamento motivoDelloSpostamento = getMotivoDelloSpostamento(dichiarazione);
				cambioResidenzaRequest.setMotivoDelloSpostamento(motivoDelloSpostamento);

				RegistrazionePraticaResponse registraCambioResidenzaResponse = registrazionePraticheImplPort.registraCambioResidenza(cambioResidenzaRequest);
				risposta = inviaPratica(registrazionePraticheImplPort, registraCambioResidenzaResponse, dichiarante, documenti, note);

			}
			else if (trasferimento != null) {

				note = trasferimento.getNote();

				Dichiarante dichiarante = trasferimento.getDichiarante();
				codiceFiscaleDichiarante = dichiarante.getCodiceFiscale();
				// Aggiunta documento XML per invio email
				Documento documentoXml = getDocumentoXml(xml, codiceFiscaleDichiarante);
				documenti.add(documentoXml);

				RegistraCambioAbitazioneRequest registraCambioAbitazioneRequest = new RegistraCambioAbitazioneRequest();
				Indirizzo indirizzoAbitazione = trasferimento.getAbitazione().getIndirizzo();

				InfoIndirizzo infoVia = getInfoIndirizzo(indirizzoAbitazione);
				registraCambioAbitazioneRequest.setInfoVia(infoVia);

				MotivoDelloSpostamento motivoDelloSpostamento = getMotivoDelloSpostamento(trasferimento);
				registraCambioAbitazioneRequest.setMotivoDelloSpostamento(motivoDelloSpostamento);

				Richiedente richiedente = new Richiedente();
				// Fondamentale per il trasferiento!
				richiedente.setIdFamiglia(dichiarante.getIdentificativoFamiglia());

				richiedente.setCellulare(dichiarante.getCellulare());
				richiedente.setCodiceFiscale(dichiarante.getCodiceFiscale());
				richiedente.setFax(dichiarante.getFax());
				richiedente.setMail(dichiarante.getEmail());
				richiedente.setPec(dichiarante.getPec());
				richiedente.setTelefono(dichiarante.getTelefono());

				registraCambioAbitazioneRequest.setRichiedente(richiedente);
				Codifica tipoRegistrazione = trasferimento.getTipoRegistrazione();
				String codice = tipoRegistrazione.getCodice();
				if (codice != null) {
					if (codice.equals(TIPO_REGISTRAZIONE_INTERO_NUCLEO_FAMILIARE)) {
						registraCambioAbitazioneRequest.setTipoRegistrazioneFamiglia(TipoRegistrazioneFamiglia.INTERO_NUCLEO_FAMILIARE);
					}
					else if (codice.equals(TIPO_REGISTRAZIONE_CREA_NUOVA_FAMIGLIA)) {
						registraCambioAbitazioneRequest.setTipoRegistrazioneFamiglia(TipoRegistrazioneFamiglia.CREA_NUOVA_FAMIGLIA);
					}
				}

				List<it.insielmercato.egov.services.registrazionepratiche.ws.Componente> famiglia = registraCambioAbitazioneRequest.getFamiglia();
				// Aggiunta dichiarante
				it.insielmercato.egov.services.registrazionepratiche.ws.Componente dichiaranteRichiesta = getComponenteCambioAbitazione(dichiarante);
				// Intestatario scheda
				dichiaranteRichiesta.setCodiceRelazioneParentela(1);
				famiglia.add(dichiaranteRichiesta);

				// Componenti famiglia
				List<Componente> familiari = trasferimento.getFamiliare();
				if (familiari != null) {
					for (Componente componente : familiari) {
						it.insielmercato.egov.services.registrazionepratiche.ws.Componente componenteCambioAbitazione = getComponenteCambioAbitazione(componente);
						famiglia.add(componenteCambioAbitazione);
					}
				}

				RegistrazionePraticaResponse registraCambioAbitazione = registrazionePraticheImplPort.registraCambioAbitazione(registraCambioAbitazioneRequest);
				risposta = inviaPratica(registrazionePraticheImplPort, registraCambioAbitazione, dichiarante, documenti, note);
			}
		}
		catch (Exception e) {
			// Errore GRAVE/bloccante (non gestito)
			risposta = new DichiarazioneCambioResidenzaRisposta();

			log.error("risposta :: " + e.getMessage(), e);
			Errore errore = new Errore();
			errore.setCodice(ERROR_CODE_1);
			errore.setDescrizione("Errore durante la comunicazione con il backoffice comunale: " + e.getMessage() + ". Contattare il servizio di assistenza.");
			risposta.setErrore(errore);
		}

		return risposta;
	}

	/**
	 * Invia la richiesta ad ASCOT.
	 *
	 * @param registrazionePraticheImplPort
	 * @param registrazionePraticaResponse
	 * @param dichiarante
	 * @param documenti
	 * @param note
	 * @return
	 */
	private DichiarazioneCambioResidenzaRisposta inviaPratica(RegistrazionePratiche registrazionePraticheImplPort, RegistrazionePraticaResponse registrazionePraticaResponse, Dichiarante dichiarante,
			List<Documento> documenti, String note) {

		DichiarazioneCambioResidenzaRisposta risposta = new DichiarazioneCambioResidenzaRisposta();

		if (registrazionePraticaResponse != null) {
			ServiceResult queryResult = registrazionePraticaResponse.getQueryResult();
			boolean success = queryResult.isSuccess();
			// OK
			if (success) {
				JAXBElement<String> idPratica = registrazionePraticaResponse.getIdPratica();

				// Invio a protocollo
				ProtocolloRisposta protocolloRisposta;
				try {
					ProtocolloRichiesta protocolloRichiesta = getProtocolloRequest(dichiarante, documenti);
					protocolloRisposta = protocolloClient.protocolla(protocolloRichiesta);

					Calendar dataProtocolloCalendar = new GregorianCalendar();
					dataProtocolloCalendar.setTime(protocolloRisposta.getDataProtocollo());

					JAXBElement<XMLGregorianCalendar> dataInserimento = registrazionePraticaResponse.getDataInserimento();

					risposta.setIdPratica(idPratica.getValue());
					risposta.setDataInserimento(dataInserimento.getValue().toGregorianCalendar());
					risposta.setNumeroProtocollo(protocolloRisposta.getNumeroProtocollo());
					risposta.setDataProtocollo(dataProtocolloCalendar);
				}
				catch (Exception e) {
					log.error("risposta :: " + e.getMessage(), e);

					// Eliminazione pratica ancora aperta
					DeletePraticaRequest deletePraticaRequest = new DeletePraticaRequest();
					deletePraticaRequest.getNumeroPratica().add(idPratica.getValue());
					DeletePraticaResponse deleteExistingOpenedRegistration = registrazionePraticheImplPort.deleteExistingOpenedRegistration(deletePraticaRequest);

					boolean successDeletePratica = deleteExistingOpenedRegistration.getQueryResult().isSuccess();
					if (successDeletePratica) {
						String messaggioErrore = "Errore durante la comunicazione con ASCOTWEB: impossibile protocollare la dichiarazione. Messaggio di errore: " + e.getMessage()
								+ ". Pratica non inserita, riprovare. Se il problema persiste contattare il servizio di assistenza.";
						log.error(messaggioErrore);
						Errore errore = new Errore();
						errore.setCodice(ERROR_CODE_2);
						errore.setDescrizione(messaggioErrore);
						risposta.setErrore(errore);
						return risposta;
					}
					else {
						// Errore GRAVE: in tal caso bisognerebbe intervenire eliminando
						// manualmente la pratica
						String errorMessage = deleteExistingOpenedRegistration.getQueryResult().getErrorMessage();
						String messaggioErrore = "Errore durante la comunicazione con ASCOTWEB: impossibile eliminare la pratica con ID '" + idPratica.getValue()
								+ "' in seguito ad un errore di comunicazione con il sistema di protocollo. Messaggio di errore: " + errorMessage + ". Contattare il servizio di assistenza.";
						log.error(messaggioErrore);
						Errore errore = new Errore();
						errore.setCodice(ERROR_CODE_1);
						errore.setDescrizione(messaggioErrore);
						risposta.setErrore(errore);
						return risposta;
					}
				}

				// invio email
				try {
					sendMail(risposta.getIdPratica(), protocolloRisposta, dichiarante.getCodiceFiscale(), documenti, note);
				}
				catch (Exception e) {
					// Errore di invio email --> avvertenza, la pratica dovrebbe comunque
					// essere inserita nel sistema ma notificato l'utente
					String messaggioErrore = "Impossibile inviare l'email al/agli indirizzo/i '" + emailAddressDichiarazioniResidenza + "'. Pratica inserita.";
					log.error("risposta :: " + messaggioErrore, e);
				}

				// Chiusura pratica (per renderla visibile in ASCOTWEB)
				ClosurePracticeRequest chiusuraPraticaRequest = new ClosurePracticeRequest();
				List<String> numeroPratica = chiusuraPraticaRequest.getNumeroPratica();
				numeroPratica.add(idPratica.getValue());

				ClosurePracticeResponse closePractice = registrazionePraticheImplPort.closePractice(chiusuraPraticaRequest);
				ServiceResult queryResultClosePractice = closePractice.getQueryResult();
				boolean successClosePratica = queryResultClosePractice.isSuccess();
				if (!successClosePratica) {
					// Errore GRAVE/bloccante: in tal caso bisognerebbe intervenire
					// eliminando manualmente la pratica
					String errorCode = queryResultClosePractice.getErrorCode();
					String errorMessage = queryResult.getErrorMessage();
					String messaggioErrore = "Errore durante la comunicazione con ASCOTWEB: impossibile chiudere la pratica con ID '" + idPratica.getValue() + "'. Error code: " + errorCode
							+ ", messaggio di errore: " + errorMessage + ". Contattare il servizio di assistenza";
					log.error(messaggioErrore);
					Errore errore = new Errore();
					errore.setCodice(ERROR_CODE_1);
					errore.setDescrizione(messaggioErrore);
					risposta.setErrore(errore);
					return risposta;
				}
			}
			// Errore da ASCOT non bloccante, si può riprovare.
			else {
				String errorCode = queryResult.getErrorCode();
				String errorMessage = queryResult.getErrorMessage();
				String messaggioErrore = "Errore durante la comunicazione con ASCOTWEB: error code: " + errorCode + ", messaggio di errore: " + errorMessage
						+ ". Pratica non inserita, riprovare. Se il problema persiste contattare il servizio di assistenza.";
				log.error(messaggioErrore);
				Errore errore = new Errore();
				errore.setCodice(ERROR_CODE_2);
				errore.setDescrizione(messaggioErrore);
				risposta.setErrore(errore);
				return risposta;
			}

		} // Risposta nulla, è successo qualcosa di insolito ma si può riprovare.
		else {
			String messaggioErrore = "Errore durante la comunicazione con ASCOTWEB: risposta NULL, Pratica non inserita, riprovare. Se il problema persiste contattare il servizio di assistenza.";
			log.error(messaggioErrore);
			Errore errore = new Errore();
			errore.setCodice(ERROR_CODE_2);
			errore.setDescrizione(messaggioErrore);
			risposta.setErrore(errore);
			return risposta;
		}

		return risposta;

	}

	/**
	 * @param xml
	 * @param codiceFiscaleDichiarante
	 * @return
	 */
	private Documento getDocumentoXml(String xml, String codiceFiscaleDichiarante) {
		Documento documentoXml = new Documento();
		documentoXml.setContentType("text/xml");
		documentoXml.setContenuto(xml.getBytes());
		documentoXml.setNome(METADATI_DICHIARAZIONE_FILENAME_PREFIX + codiceFiscaleDichiarante + METADATI_DICHIARAZIONE_FILENAME_SUFFIX);
		documentoXml.setPrincipale(false);
		return documentoXml;
	}

	/**
	 * @param richiesta
	 * @return
	 */
	private ProtocolloRichiesta getProtocolloRequest(Dichiarante dichiarante, List<Documento> documenti) {

		ProtocolloRichiesta protocolloRichiesta = new ProtocolloRichiesta();
		protocolloRichiesta.setAmministrazione(amministrazioneProtocollo);
		protocolloRichiesta.setAoo(aooProtocollo);
		List<it.osapulie.pdds.client.protocollo.Documento> documentiProtocollo = new ArrayList<it.osapulie.pdds.client.protocollo.Documento>();
		if (documenti != null) {
			for (Documento documento : documenti) {
				it.osapulie.pdds.client.protocollo.Documento documentoProtocollo = new it.osapulie.pdds.client.protocollo.Documento();
				documentoProtocollo.setClassificazione(classificazioneProtocollo);
				documentoProtocollo.setContenuto(documento.getContenuto());
				documentoProtocollo.setDescrizione(documento.getDescrizione());
				documentoProtocollo.setNome(documento.getNome());
				documentoProtocollo.setPrincipale(documento.isPrincipale());
				documentoProtocollo.setTitolo(documento.getTitolo() != null ? documento.getTitolo() : documento.getNome());
				documentiProtocollo.add(documentoProtocollo);
			}
		}

		protocolloRichiesta.setDocumenti(documentiProtocollo);
		protocolloRichiesta.setOggetto(DICHIARAZIONE_SUBJECT + " - C.F. Cittadino: " + dichiarante.getCodiceFiscale());

		return protocolloRichiesta;
	}

	/**
	 * @param indirizzo
	 * @return
	 */
	private InfoIndirizzo getInfoIndirizzo(Indirizzo indirizzo) {

		InfoIndirizzo infoVia = new InfoIndirizzo();

		// Codice via già codificato lato frontend come richiesto da ascot
		String codiceVia = indirizzo.getVia().getCodice();
		infoVia.setCodiceFrazioneVia(codiceVia);

		Civico civico = indirizzo.getCivico();
		if (civico != null) {
			infoVia.setIdentificativoCivico(civico.getCodice());
			infoVia.setNumeroCivico(String.valueOf(civico.getNumero()));
			infoVia.setNumeroCivicoBarrato(civico.getEsponente());
		}
		infoVia.setCorte(indirizzo.getCorte());
		infoVia.setInterno(indirizzo.getInterno());
		infoVia.setPiano(indirizzo.getPiano());
		infoVia.setScala(indirizzo.getScala());

		return infoVia;
	}

	/**
	 * @param dichiarazione
	 * @param dichiarante
	 * @return
	 */
	private MotivoDelloSpostamento getMotivoDelloSpostamento(Dichiarazione dichiarazione) {

		MotivoDelloSpostamento motivoDelloSpostamento = new MotivoDelloSpostamento();
		TitoloOccupazioneImmobile titoloOccupazioneImmpobile = dichiarazione.getTitoloOccupazioneImmpobile();
		Immobile immobileDestinazione = dichiarazione.getAbitazione();
		if (immobileDestinazione != null && titoloOccupazioneImmpobile != null) {
			// I dati catastali non sono obbliagtori (scelta del Comune)
			DatiCatastali datiCatastali = immobileDestinazione.getDatiCatastali();
			if (datiCatastali != null) {
				motivoDelloSpostamento.setCatastoFoglio(datiCatastali.getFoglio() != 0 ? String.valueOf(datiCatastali.getFoglio()) : null);
				motivoDelloSpostamento.setCatastoMappale(datiCatastali.getParticella() != 0 ? String.valueOf(datiCatastali.getParticella()) : null);
				motivoDelloSpostamento.setCatastoSezione(datiCatastali.getSezione());
				Integer subalterno = datiCatastali.getSubalterno();
				if (subalterno != null) {
					motivoDelloSpostamento.setCatastoSubalterno(String.valueOf(subalterno));
				}
			}

			ContrattoLocazione contrattoLocazioneIntestatario = titoloOccupazioneImmpobile.getContrattoLocazioneIntestatario();
			ContrattoLocazioneComodatario contrattoLocazioneComodatario = titoloOccupazioneImmpobile.getContrattoLocazioneComodatario();
			ContrattoLocazione contrattoLocazioneIntestatarioEdiliziaPubblica = titoloOccupazioneImmpobile.getContrattoLocazioneIntestatarioEdiliziaPubblica();
			if (contrattoLocazioneIntestatario != null || contrattoLocazioneComodatario != null || contrattoLocazioneIntestatarioEdiliziaPubblica != null) {
				if (contrattoLocazioneIntestatario != null) {
					motivoDelloSpostamento.setTitoloOccupazioneImmobile(TipologieOccupazioneImmobile.INTESTATARIO_CONTRATTO_LOCAZIONE_PRIVATO);
					setMotivoSpostamento(motivoDelloSpostamento, contrattoLocazioneIntestatario);
				}
				else if (contrattoLocazioneComodatario != null) {
					motivoDelloSpostamento.setTitoloOccupazioneImmobile(TipologieOccupazioneImmobile.COMODATARIO_USO_GRATUITO);
					if (contrattoLocazioneComodatario.getContrattoLocazione() != null) {
						setMotivoSpostamento(motivoDelloSpostamento, contrattoLocazioneComodatario.getContrattoLocazione());
					}
				}
				else if (contrattoLocazioneIntestatarioEdiliziaPubblica != null) {
					motivoDelloSpostamento.setTitoloOccupazioneImmobile(TipologieOccupazioneImmobile.INTESTATARIO_CONTRATTO_LOCAZIONE_PUBBLICO);
				}
			}
			if (titoloOccupazioneImmpobile.getProprietario() != null && titoloOccupazioneImmpobile.getProprietario()) {
				motivoDelloSpostamento.setTitoloOccupazioneImmobile(TipologieOccupazioneImmobile.LEGGITTIMO_PROPRIETARIO);
			}
			else if (titoloOccupazioneImmpobile.getTitoloUsufruttuario() != null) {
				motivoDelloSpostamento.setDatiPerVerificaUfficioAnagrafe(titoloOccupazioneImmpobile.getTitoloUsufruttuario());
				motivoDelloSpostamento.setTitoloOccupazioneImmobile(TipologieOccupazioneImmobile.USUFRUTTUARIO_CON_TITOLO);
			}
			else if (titoloOccupazioneImmpobile.getTitoloOccupante() != null) {
				motivoDelloSpostamento.setDatiPerVerificaUfficioAnagrafe(titoloOccupazioneImmpobile.getTitoloOccupante());
				motivoDelloSpostamento.setTitoloOccupazioneImmobile(TipologieOccupazioneImmobile.OCCUPANTE_LEGGITTIMO_CON_TITOLO);
			}

		}
		return motivoDelloSpostamento;
	}

	/**
	 * @param motivoDelloSpostamento
	 * @param contrattoLocazione
	 */
	private void setMotivoSpostamento(MotivoDelloSpostamento motivoDelloSpostamento, ContrattoLocazione contrattoLocazione) {
		if (contrattoLocazione.getData() != null) {
			try {
				motivoDelloSpostamento.setRegistrazioneContrattoData(getXMLGregorianCalendarDate(contrattoLocazione.getData().getTime()));
			}
			catch (DatatypeConfigurationException e) {
				log.error("risposta :: " + e.getMessage(), e);
			}
		}
		motivoDelloSpostamento.setRegistrazioneContrattoNumero(contrattoLocazione.getNumero());
		motivoDelloSpostamento.setRegistrazioneContrattoSedeAgenziaEntrate(contrattoLocazione.getAgenziaEntrate());
	}

	/**
	 * @param componente
	 * @return
	 */
	private ComponenteCambioResidenza getComponenteCambioResidenza(Componente componente) {

		ComponenteCambioResidenza componenteCambioResidenza = new ComponenteCambioResidenza();

		Codifica cittadinanza = componente.getCittadinanza();
		if (cittadinanza != null) {
			componenteCambioResidenza.setCodiceCittadinanza(Integer.parseInt(cittadinanza.getCodice()));
		}
		Codifica comuneNascita = componente.getComuneNascita();
		if (comuneNascita != null) {
			componenteCambioResidenza.setCodiceComuneNascita(comuneNascita.getCodice());
		}
		Codifica posizioneProfessionale = componente.getPosizioneProfessionale();
		if (posizioneProfessionale != null) {
			componenteCambioResidenza.setCodicePosizioneProfessionale(Integer.parseInt(posizioneProfessionale.getCodice()));
		}

		componenteCambioResidenza.setCellulare(componente.getCellulare());
		componenteCambioResidenza.setTel(componente.getTelefono());
		componenteCambioResidenza.setTelefono(componente.getTelefono());
		componenteCambioResidenza.setMail(componente.getEmail());
		componenteCambioResidenza.setPec(componente.getPec());

		componenteCambioResidenza.setCodiceFiscale(componente.getCodiceFiscale());

		// Non presente
		componenteCambioResidenza.setCodiceProfessione(0);
		// Non presente
		componenteCambioResidenza.setCodiceSettoreAttivita(0);
		Codifica statoCivile = componente.getStatoCivile();
		if (statoCivile != null) {
			componenteCambioResidenza.setCodiceStatoCivile(Integer.parseInt(statoCivile.getCodice()));
		}
		Codifica titoloStudio = componente.getTitoloStudio();
		if (titoloStudio != null) {
			componenteCambioResidenza.setCodiceTitoloStudio(Integer.parseInt(titoloStudio.getCodice()));
		}
		componenteCambioResidenza.setCognome(componente.getCognome());
		if (componente.getDataNascita() != null) {
			try {
				componenteCambioResidenza.setDataNascita(getXMLGregorianCalendarDate(componente.getDataNascita().getTime()));
			}
			catch (DatatypeConfigurationException e) {
				log.error("risposta :: " + e.getMessage(), e);
			}
		}

		Patente patente = componente.getPatente();
		if (patente != null) {
			it.insielmercato.egov.services.registrazionepratiche.ws.Patente patenteRichiesta = new it.insielmercato.egov.services.registrazionepratiche.ws.Patente();
			patenteRichiesta.setCarattereControllo(patente.getCarattereControllo());
			patenteRichiesta.setCategoria(patente.getCategoria());
			patenteRichiesta.setCodiceEnteRilasciante(patente.getOrganoRilascio().getCodice());
			Calendar dataRilascio = patente.getDataRilascio();
			if (dataRilascio != null) {
				try {
					patenteRichiesta.setDataRegistrazione(getXMLGregorianCalendarDate(dataRilascio.getTime()));
				}
				catch (DatatypeConfigurationException e) {
					log.error("getComponenteCambioResidenza :: " + e.getMessage(), e);
				}

			}
			patenteRichiesta.setNumero(patente.getNumero());
			patenteRichiesta.setSigla(patente.getSigla());
			componenteCambioResidenza.setDatiPatente(patenteRichiesta);
		}

		componenteCambioResidenza.setNome(componente.getNome());
		componenteCambioResidenza.setNumeroIdentificativoPersona(componente.getIdentificativo());
		Codifica sesso = componente.getSesso();
		if (sesso != null) {
			componenteCambioResidenza.setSesso(sesso.getCodice());
		}

		// Veicoli
		List<Veicolo> veicoli = componente.getVeicolo();
		if (veicoli != null) {
			List<it.insielmercato.egov.services.registrazionepratiche.ws.Veicolo> veicoliRichiesta = componenteCambioResidenza.getVeicoli();
			for (Veicolo veicolo : veicoli) {
				it.insielmercato.egov.services.registrazionepratiche.ws.Veicolo veicoloRichiesta = new it.insielmercato.egov.services.registrazionepratiche.ws.Veicolo();
				veicoloRichiesta.setCodiceTipoVeicolo(veicolo.getTipo().getCodice());
				veicoloRichiesta.setTarga(veicolo.getTarga());
				veicoliRichiesta.add(veicoloRichiesta);
			}
		}

		Codifica relazioneParentela = componente.getRelazioneParentela();
		if (relazioneParentela != null) {
			componenteCambioResidenza.setCodiceRelazioneParentela(Long.parseLong(relazioneParentela.getCodice()));
		}

		return componenteCambioResidenza;

	}

	/**
	 * @param componente
	 * @return
	 */
	private it.insielmercato.egov.services.registrazionepratiche.ws.Componente getComponenteCambioAbitazione(Componente componente) {

		it.insielmercato.egov.services.registrazionepratiche.ws.Componente componenteCambioAbitazione = new it.insielmercato.egov.services.registrazionepratiche.ws.Componente();

		componenteCambioAbitazione.setCell(componente.getCellulare());
		componenteCambioAbitazione.setCodiceFiscale(componente.getCodiceFiscale());

		Patente patente = componente.getPatente();
		if (patente != null) {
			it.insielmercato.egov.services.registrazionepratiche.ws.Patente patenteRichiesta = new it.insielmercato.egov.services.registrazionepratiche.ws.Patente();
			patenteRichiesta.setCarattereControllo(patente.getCarattereControllo());
			patenteRichiesta.setCategoria(patente.getCategoria());
			patenteRichiesta.setCodiceEnteRilasciante(patente.getOrganoRilascio().getCodice());
			Calendar dataRilascio = patente.getDataRilascio();
			if (dataRilascio != null) {
				try {
					patenteRichiesta.setDataRegistrazione(getXMLGregorianCalendarDate(dataRilascio.getTime()));
				}
				catch (DatatypeConfigurationException e) {
					log.error("getComponenteCambioResidenza :: " + e.getMessage(), e);
				}

			}
			patenteRichiesta.setNumero(patente.getNumero());
			patenteRichiesta.setSigla(patente.getSigla());
			componenteCambioAbitazione.setDatiPatente(patenteRichiesta);
		}

		componenteCambioAbitazione.setMail(componente.getEmail());
		componenteCambioAbitazione.setNumeroIdentificativoPersona(componente.getIdentificativo());
		componenteCambioAbitazione.setPec(componente.getPec());
		Codifica sesso = componente.getSesso();
		if (sesso != null) {
			componenteCambioAbitazione.setSesso(sesso.getCodice());
		}

		componenteCambioAbitazione.setTel(componente.getTelefono());

		// Veicoli
		List<Veicolo> veicoli = componente.getVeicolo();
		if (veicoli != null) {
			List<it.insielmercato.egov.services.registrazionepratiche.ws.Veicolo> veicoliRichiesta = componenteCambioAbitazione.getVeicoli();
			for (Veicolo veicolo : veicoli) {
				it.insielmercato.egov.services.registrazionepratiche.ws.Veicolo veicoloRichiesta = new it.insielmercato.egov.services.registrazionepratiche.ws.Veicolo();
				veicoloRichiesta.setCodiceTipoVeicolo(veicolo.getTipo().getCodice());
				veicoloRichiesta.setTarga(veicolo.getTarga());
				veicoliRichiesta.add(veicoloRichiesta);
			}
		}

		Codifica relazioneParentela = componente.getRelazioneParentela();
		if (relazioneParentela != null) {
			componenteCambioAbitazione.setCodiceRelazioneParentela(Long.parseLong(relazioneParentela.getCodice()));
		}

		return componenteCambioAbitazione;

	}

	/**
	 *
	 * @param date
	 * @return
	 * @throws DatatypeConfigurationException
	 */
	private XMLGregorianCalendar getXMLGregorianCalendarDate(Date date) throws DatatypeConfigurationException {
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(date);
		return DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
	}

	/**
	 * @param idPratica
	 * @param protocolloRisposta
	 * @param numeroProtocollo
	 * @param documenti
	 * @param note
	 * @throws Exception
	 */
	private void sendMail(String idPratica, ProtocolloRisposta protocolloRisposta, String codiceFiscaleDichiarante, List<Documento> documenti, String note) throws Exception {

		String[] emailAddressesDichiarazioniResidenza = emailAddressDichiarazioniResidenza.split(",");
		if (emailAddressesDichiarazioniResidenza != null) {
			for (String email : emailAddressesDichiarazioniResidenza) {
				Message message = new Message();
				message.setAccount(null);
				if (documenti != null) {
					List<Attachment> attachments = new ArrayList<Attachment>();
					for (Documento documento : documenti) {
						Attachment attachment = new Attachment();
						attachment.setContent(documento.getContenuto());
						attachment.setContentType(documento.getContentType());
						attachment.setName(documento.getNome());
						attachments.add(attachment);
					}
					message.setAttachments(attachments);
				}
				message.setHtml(true);
				String subject = DICHIARAZIONE_SUBJECT + " - " + codiceFiscaleDichiarante;
				if (idPratica != null && !idPratica.isEmpty()) {
					subject += " - ID Pratica: " + idPratica;
				}
				message.setSubject(subject);
				// Aggiunta note a testo
				String testo = "";
				if (protocolloRisposta != null) {
					String dataProtocollo = dateFormat.format(protocolloRisposta.getDataProtocollo());
					testo += "Dichiarazione protocollata con numero \"" + protocolloRisposta.getNumeroProtocollo() + "\" in data " + dataProtocollo + ".<br>";
				}
				if (note != null && !note.isEmpty()) {
					testo += "Note dichiarazione: <br>";
					testo += note;
				}
				message.setText(testo);
				Contact contact = new Contact();
				contact.setEmail(email);
				message.setTo(Arrays.asList(contact));
				// Invio Email
				emailClient.send(message);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.pdds.common.PddService#getName()
	 */
	@Override
	public String getName() {
		return NOME_SERVIZIO;
	}

	/**
	 * @return the xmlHelper
	 */
	public XMLHelper getXmlHelper() {
		return xmlHelper;
	}

	/**
	 * @param xmlHelper the xmlHelper to set
	 */
	public void setXmlHelper(XMLHelper xmlHelper) {
		this.xmlHelper = xmlHelper;
	}

	/**
	 * @return the webserviceUrl
	 */
	public String getWebserviceUrl() {
		return webserviceUrl;
	}

	/**
	 * @param webserviceUrl the webserviceUrl to set
	 */
	public void setWebserviceUrl(String webserviceUrl) {
		this.webserviceUrl = webserviceUrl;
	}

	/**
	 * @return the protocolloClient
	 */
	public ProtocolloClient getProtocolloClient() {
		return protocolloClient;
	}

	/**
	 * @param protocolloClient the protocolloClient to set
	 */
	public void setProtocolloClient(ProtocolloClient protocolloClient) {
		this.protocolloClient = protocolloClient;
	}

	/**
	 * @return the emailClient
	 */
	public EmailClient getEmailClient() {
		return emailClient;
	}

	/**
	 * @param emailClient the emailClient to set
	 */
	public void setEmailClient(EmailClient emailClient) {
		this.emailClient = emailClient;
	}

	/**
	 * @return the emailAddressDichiarazioniResidenza
	 */
	public String getEmailAddressDichiarazioniResidenza() {
		return emailAddressDichiarazioniResidenza;
	}

	/**
	 * @param emailAddressDichiarazioniResidenza the emailAddressDichiarazioniResidenza to set
	 */
	public void setEmailAddressDichiarazioniResidenza(String emailAddressDichiarazioniResidenza) {
		this.emailAddressDichiarazioniResidenza = emailAddressDichiarazioniResidenza;
	}

	/**
	 * @return the identificativoUtente
	 */
	public String getIdentificativoUtente() {
		return identificativoUtente;
	}

	/**
	 * @param identificativoUtente the identificativoUtente to set
	 */
	public void setIdentificativoUtente(String identificativoUtente) {
		this.identificativoUtente = identificativoUtente;
	}

	/**
	 * @return the passwordUtente
	 */
	public String getPasswordUtente() {
		return passwordUtente;
	}

	/**
	 * @param passwordUtente the passwordUtente to set
	 */
	public void setPasswordUtente(String passwordUtente) {
		this.passwordUtente = passwordUtente;
	}

	/**
	 * @return the amministrazioneProtocollo
	 */
	public String getAmministrazioneProtocollo() {
		return amministrazioneProtocollo;
	}

	/**
	 * @param amministrazioneProtocollo the amministrazioneProtocollo to set
	 */
	public void setAmministrazioneProtocollo(String amministrazioneProtocollo) {
		this.amministrazioneProtocollo = amministrazioneProtocollo;
	}

	/**
	 * @return the aooProtocollo
	 */
	public String getAooProtocollo() {
		return aooProtocollo;
	}

	/**
	 * @param aooProtocollo the aooProtocollo to set
	 */
	public void setAooProtocollo(String aooProtocollo) {
		this.aooProtocollo = aooProtocollo;
	}

	/**
	 * @return the classificazioneProtocollo
	 */
	public String getClassificazioneProtocollo() {
		return classificazioneProtocollo;
	}

	/**
	 * @param classificazioneProtocollo the classificazioneProtocollo to set
	 */
	public void setClassificazioneProtocollo(String classificazioneProtocollo) {
		this.classificazioneProtocollo = classificazioneProtocollo;
	}

}
