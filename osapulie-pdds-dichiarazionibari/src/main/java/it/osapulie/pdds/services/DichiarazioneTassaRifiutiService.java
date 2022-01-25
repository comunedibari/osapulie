/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pdds.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.ws.BindingProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import it.bari.comune.servizitributi.CategoriaCatastaleType;
import it.bari.comune.servizitributi.CommonResponseType;
import it.bari.comune.servizitributi.ContribuenteType;
import it.bari.comune.servizitributi.DatiAutorizzazioneType;
import it.bari.comune.servizitributi.DatiCatastaliNewType;
import it.bari.comune.servizitributi.DatiCessazioneType;
import it.bari.comune.servizitributi.DatiComuniType;
import it.bari.comune.servizitributi.DatiContribuenteIscrizioneType;
import it.bari.comune.servizitributi.DatiContribuenteVariazioneType;
import it.bari.comune.servizitributi.DatiIscrizioneType;
import it.bari.comune.servizitributi.DatiModelloISEEType;
import it.bari.comune.servizitributi.DatiMotivoCessazioneDomesticaType;
import it.bari.comune.servizitributi.DatiNucleoFamiliareType;
import it.bari.comune.servizitributi.DatiNuovaUtenzaDomesticaType;
import it.bari.comune.servizitributi.DatiPatrimonioType;
import it.bari.comune.servizitributi.DatiRappresentanteType;
import it.bari.comune.servizitributi.DatiRecapitoType;
import it.bari.comune.servizitributi.DatiRichiestaRimborsoType;
import it.bari.comune.servizitributi.DatiTracciamentoRequestType;
import it.bari.comune.servizitributi.DatiTrasferimentoType;
import it.bari.comune.servizitributi.DatiUtenzaCessazioneType;
import it.bari.comune.servizitributi.DatiUtenzaCommercialeIscrizioneType;
import it.bari.comune.servizitributi.DatiUtenzaCommercialeTrasferimentoType;
import it.bari.comune.servizitributi.DatiUtenzaDomesticaType;
import it.bari.comune.servizitributi.DatiUtenzaRequestType;
import it.bari.comune.servizitributi.DatiUtenzaVariazioneCategoriaType;
import it.bari.comune.servizitributi.DatiUtenzaVariazioneSuperficieCommercialeType;
import it.bari.comune.servizitributi.DatiUtenzaVariazioneSuperficieDomesticaType;
import it.bari.comune.servizitributi.DatiUtenzeDomesticheType;
import it.bari.comune.servizitributi.DatiUtenzeType;
import it.bari.comune.servizitributi.DatiVariazioneCategoriaRequestType;
import it.bari.comune.servizitributi.DatiVariazioneSuperficieRequestType;
import it.bari.comune.servizitributi.DatiVecchiaUtenzaType;
import it.bari.comune.servizitributi.ElencoDatiUtenzeCommercialiType;
import it.bari.comune.servizitributi.ElencoOccupantiImmobileType;
import it.bari.comune.servizitributi.ElencoOccupantiImmobileVariazioneType;
import it.bari.comune.servizitributi.IndirizzoComuneType;
import it.bari.comune.servizitributi.IndirizzoType;
import it.bari.comune.servizitributi.MotivoCessazioneCommercialeType;
import it.bari.comune.servizitributi.MotivoCessazionePerCoabitazioneType;
import it.bari.comune.servizitributi.MotivoCessazionePerImmobileConcessoInLocazioneType;
import it.bari.comune.servizitributi.MotivoCessazionePerImmobileRestituitoPerFineLocazioneType;
import it.bari.comune.servizitributi.MotivoCessazionePerImmobileVendutoType;
import it.bari.comune.servizitributi.MotivoCessazionePerImmobileVuotoSenzaFornitureType;
import it.bari.comune.servizitributi.OccupanteImmobileType;
import it.bari.comune.servizitributi.OccupanteImmobileVariazUtenzaDomType;
import it.bari.comune.servizitributi.PersonaFisicaType;
import it.bari.comune.servizitributi.PersonaGiuridicaType;
import it.bari.comune.servizitributi.RichiestaAgevolazioneRequestType;
import it.bari.comune.servizitributi.RichiestaAgevolazioneRequestType.ElencoUtenze;
import it.bari.comune.servizitributi.RiduzioneCommercialeIscrizioneType;
import it.bari.comune.servizitributi.RiduzioneCommercialeTrasferimentoType;
import it.bari.comune.servizitributi.RiduzioneCommercialeVariazioneType;
import it.bari.comune.servizitributi.RiduzioneDomesticaType;
import it.bari.comune.servizitributi.RiduzioneTariffaDomestica2014Type;
import it.bari.comune.servizitributi.SessoType;
import it.bari.comune.servizitributi.SetCessazioneRequestType;
import it.bari.comune.servizitributi.SetCessazioneResponseType;
import it.bari.comune.servizitributi.SetIscrizioneRequestType;
import it.bari.comune.servizitributi.SetIscrizioneResponseType;
import it.bari.comune.servizitributi.SetTrasferimentoRequestType;
import it.bari.comune.servizitributi.SetTrasferimentoResponseType;
import it.bari.comune.servizitributi.SetVariazioneCategoriaRequestType;
import it.bari.comune.servizitributi.SetVariazioneCategoriaResponseType;
import it.bari.comune.servizitributi.SetVariazioneSuperficieRequestType;
import it.bari.comune.servizitributi.SetVariazioneSuperficieResponseType;
import it.bari.comune.servizitributi.TaresService;
import it.bari.comune.servizitributi.TaresServicePortType;
import it.bari.comune.servizitributi.TipoUtenzaType;
import it.bari.comune.servizitributi.TipoVariazioneOccupanteImmobileType;
import it.bari.comune.servizitributi.UsoImmobileType;
import it.bari.comune.servizitributi.VariazioneAnagraficaPersonaFisicaType;
import it.bari.comune.servizitributi.VariazioneAnagraficaPersonaGiuridicaType;
import it.osapulie.pdds.adapter.util.XMLHelper;
import it.osapulie.pdds.client.email.Attachment;
import it.osapulie.pdds.client.email.Contact;
import it.osapulie.pdds.client.email.EmailClient;
import it.osapulie.pdds.client.email.Message;
import it.osapulie.pdds.client.protocollo.ProtocolloClient;
import it.osapulie.pdds.client.protocollo.ProtocolloRichiesta;
import it.osapulie.pdds.client.protocollo.ProtocolloRisposta;
import it.osapulie.pdds.common.PddService;
import it.osapulie.tributi.web.ws.input.types.Agevolazione;
import it.osapulie.tributi.web.ws.input.types.CAF;
import it.osapulie.tributi.web.ws.input.types.Cessazione;
import it.osapulie.tributi.web.ws.input.types.Cessazione.Rimborso;
import it.osapulie.tributi.web.ws.input.types.Cessazione.UtenzeDomestiche.CessazionePerCoabitazione;
import it.osapulie.tributi.web.ws.input.types.Codifica;
import it.osapulie.tributi.web.ws.input.types.DatiCatastali;
import it.osapulie.tributi.web.ws.input.types.Dichiarante;
import it.osapulie.tributi.web.ws.input.types.Dichiarazione;
import it.osapulie.tributi.web.ws.input.types.DichiarazioneTassaRifiutiInputRichiesta;
import it.osapulie.tributi.web.ws.input.types.DichiarazioneTassaRifiutiInputRisposta;
import it.osapulie.tributi.web.ws.input.types.DichiarazioneTassaRifiutiInputRisposta.Errore;
import it.osapulie.tributi.web.ws.input.types.Documento;
import it.osapulie.tributi.web.ws.input.types.Indirizzo;
import it.osapulie.tributi.web.ws.input.types.Indirizzo.Civico;
import it.osapulie.tributi.web.ws.input.types.Iscrizione;
import it.osapulie.tributi.web.ws.input.types.Iscrizione.UtenzeCommerciali;
import it.osapulie.tributi.web.ws.input.types.Iscrizione.UtenzeDomestiche;
import it.osapulie.tributi.web.ws.input.types.ModelloISEE;
import it.osapulie.tributi.web.ws.input.types.ModelloISEE.Patrimonio;
import it.osapulie.tributi.web.ws.input.types.NucleoFamiliare;
import it.osapulie.tributi.web.ws.input.types.OccupanteImmobile;
import it.osapulie.tributi.web.ws.input.types.OccupanteImmobileVariazione;
import it.osapulie.tributi.web.ws.input.types.PersonaFisica;
import it.osapulie.tributi.web.ws.input.types.PersonaGiuridica;
import it.osapulie.tributi.web.ws.input.types.Rappresentante;
import it.osapulie.tributi.web.ws.input.types.Recapito;
import it.osapulie.tributi.web.ws.input.types.Riduzione;
import it.osapulie.tributi.web.ws.input.types.Tracciamento;
import it.osapulie.tributi.web.ws.input.types.Trasferimento;
import it.osapulie.tributi.web.ws.input.types.Trasferimento.NuovaUtenzaCommerciale;
import it.osapulie.tributi.web.ws.input.types.Trasferimento.NuovaUtenzaDomestica;
import it.osapulie.tributi.web.ws.input.types.Utenza;
import it.osapulie.tributi.web.ws.input.types.UtenzaCommerciale;
import it.osapulie.tributi.web.ws.input.types.UtenzaCommercialeVariazione;
import it.osapulie.tributi.web.ws.input.types.UtenzaDomestica;
import it.osapulie.tributi.web.ws.input.types.UtenzaDomesticaVariazione;
import it.osapulie.tributi.web.ws.input.types.Variazione;
import it.osapulie.tributi.web.ws.input.types.VariazioneOccupanteImmobile;

/**
 * Implementazione interna del servizio di dichiarazioneTassaRifiuti.
 *
 * @author Gianluca Pindinelli
 *
 */
public class DichiarazioneTassaRifiutiService implements PddService {

	private final Logger log = LoggerFactory.getLogger(DichiarazioneTassaRifiutiService.class);

	private ProtocolloClient protocolloClient;
	private EmailClient emailClient;

	private static final String NOME_SERVIZIO = "dichiarazioneTassaRifiuti";
	private static final String WSDL_GITRI = "wsdl/gitri/TaresService.wsdl";

	private XMLHelper xmlHelper;
	private String webserviceUrl;

	private String identificativoUtente;
	private String passwordUtente;
	private String amministrazioneProtocollo;
	private String aooProtocollo;
	private String classificazioneProtocollo;

	private String emailAddressDichiarazioniTari;

	private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private final DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss.SSS");
	private final DateFormat credenzialiDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");

	private static final String DICHIARAZIONE_SUBJECT = "Dichiarazione TARI";
	private static final String AGEVOLAZIONE_SUBJECT = "Agevolazionie TARI";
	private static final String METADATI_DICHIARAZIONE_FILENAME_PREFIX = "METADATI_DICHIARAZIONE_TARI_";
	private static final String METADATI_DICHIARAZIONE_FILENAME_SUFFIX = ".xml";

	private static final String IDENTIFICATIVO_SERVIZIO_GETUTENZE = "S01";
	private static final String IDENTIFICATIVO_SERVIZIO_AGEVOLAZIONE = "S02";
	private static final String IDENTIFICATIVO_SERVIZIO_GETUTENZESTATOPRATICHE = "S03";
	private static final String IDENTIFICATIVO_SERVIZIO_ISCRIZIONE = "S04";
	private static final String IDENTIFICATIVO_SERVIZIO_TRASFERIMENTO = "S05";
	private static final String IDENTIFICATIVO_SERVIZIO_CESSAZIONE = "S06";
	private static final String IDENTIFICATIVO_SERVIZIO_VARIAZIONE_SUPERFICIE = "S07";
	private static final String IDENTIFICATIVO_SERVIZIO_VARIAZIONE_CATEGORIA = "S08";
	private static final String IDENTIFICATIVO_SERVIZIO_RIDUZIONE = "S09";

	private static final String DESCRIZIONE_SERVIZIO_GETUTENZE = "getUtenze";
	private static final String DESCRIZIONE_SERVIZIO_GETUTENZESTATOPRATICHE = "getUtenzeStatoPratiche";
	private static final String DESCRIZIONE_SERVIZIO_ISCRIZIONE = "setIscrizione";
	private static final String DESCRIZIONE_SERVIZIO_TRASFERIMENTO = "setTrasferimento";
	private static final String DESCRIZIONE_SERVIZIO_VARIAZIONE_SUPERFICIE = "setVariazioneSuperficie";
	private static final String DESCRIZIONE_SERVIZIO_VARIAZIONE_CATEGORIA = "setVariazioneCategoria";
	private static final String DESCRIZIONE_SERVIZIO_CESSAZIONE = "setCessazione";
	private static final String DESCRIZIONE_SERVIZIO_RIDUZIONE = "setRiduzione";
	private static final String DESCRIZIONE_SERVIZIO_AGEVOLAZIONE = "setAgevolazioniCAF";

	private static final String MOTIVO_CESSAZIONE_UTENZA_DOMESTICA_PER_IMMOBILE_VUOTO_SENZA_FORNITURE = "vuoto";
	private static final String MOTIVO_CESSAZIONE_UTENZA_DOMESTICA_PER_IMMOBILE_VENDUTO = "venduto";
	private static final String MOTIVO_CESSAZIONE_UTENZA_DOMESTICA_PER_IMMOBILE_RESTITUITO_PER_FINE_LOCAZIONE = "restituito";
	private static final String MOTIVO_CESSAZIONE_UTENZA_DOMESTICA_PER_IMMOBILE_CONCESSO_IN_LOCAZIONE = "concessione";

	private static final String MOTIVO_CESSAZIONE_UTENZA_COMMERCIALE_PER_CESSAZIONE = "cessazione";
	private static final String MOTIVO_CESSAZIONE_UTENZA_COMMERCIALE_PER_DUPLICAZIONE = "duplicazione";
	private static final String MOTIVO_CESSAZIONE_UTENZA_COMMERCIALE_PER_CAMBIO_DENOMINAZIONE = "cambio_denominazione";
	private static final String MOTIVO_CESSAZIONE_UTENZA_COMMERCIALE_PER_TRASFERIMENTO = "trasferimento";
	private static final String MOTIVO_CESSAZIONE_UTENZA_COMMERCIALE_ALTRO = "altro";

	// TODO cosa sono i valori delle riduzioni che vuole GITRI?
	private static final String RIDUZIONE_SUPERFICIE_ART10 = "art10";
	private static final String RIDUZIONE_TARIFFA_ART23 = "art23";
	private static final String RIDUZIONE_TARIFFA_ART24 = "art24";
	private static final String RIDUZIONE_TARIFFA_ART25 = "art25";
	private static final String RIDUZIONE_TARIFFA_ART26 = "art26";
	private static final String RIDUZIONE_TARIFFA_ART27 = "art27";
	private static final String RIDUZIONE_REVOCA = "Revoca";

	private List<String> riduzioniDomesticheList;
	private List<String> riduzioniCommercialiList;

	private Map<String, String> serviziGitriMap;

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

	/**
	 *
	 */
	public void init() {

		serviziGitriMap = new HashMap<String, String>();
		serviziGitriMap.put(IDENTIFICATIVO_SERVIZIO_GETUTENZE, DESCRIZIONE_SERVIZIO_GETUTENZE);
		serviziGitriMap.put(IDENTIFICATIVO_SERVIZIO_GETUTENZESTATOPRATICHE, DESCRIZIONE_SERVIZIO_GETUTENZESTATOPRATICHE);
		serviziGitriMap.put(IDENTIFICATIVO_SERVIZIO_ISCRIZIONE, DESCRIZIONE_SERVIZIO_ISCRIZIONE);
		serviziGitriMap.put(IDENTIFICATIVO_SERVIZIO_TRASFERIMENTO, DESCRIZIONE_SERVIZIO_TRASFERIMENTO);
		serviziGitriMap.put(IDENTIFICATIVO_SERVIZIO_VARIAZIONE_SUPERFICIE, DESCRIZIONE_SERVIZIO_VARIAZIONE_SUPERFICIE);
		serviziGitriMap.put(IDENTIFICATIVO_SERVIZIO_VARIAZIONE_CATEGORIA, DESCRIZIONE_SERVIZIO_VARIAZIONE_CATEGORIA);
		serviziGitriMap.put(IDENTIFICATIVO_SERVIZIO_CESSAZIONE, DESCRIZIONE_SERVIZIO_CESSAZIONE);
		serviziGitriMap.put(IDENTIFICATIVO_SERVIZIO_RIDUZIONE, DESCRIZIONE_SERVIZIO_RIDUZIONE);
		serviziGitriMap.put(IDENTIFICATIVO_SERVIZIO_AGEVOLAZIONE, DESCRIZIONE_SERVIZIO_AGEVOLAZIONE);

		riduzioniCommercialiList = new ArrayList<String>();
		riduzioniCommercialiList.add(RIDUZIONE_SUPERFICIE_ART10);
		riduzioniCommercialiList.add(RIDUZIONE_TARIFFA_ART24);
		riduzioniCommercialiList.add(RIDUZIONE_TARIFFA_ART25);
		riduzioniCommercialiList.add(RIDUZIONE_TARIFFA_ART26);
		riduzioniCommercialiList.add(RIDUZIONE_TARIFFA_ART27);
		riduzioniCommercialiList.add(RIDUZIONE_REVOCA);

		riduzioniDomesticheList = new ArrayList<String>();
		riduzioniDomesticheList.add(RIDUZIONE_TARIFFA_ART23);
		riduzioniDomesticheList.add(RIDUZIONE_TARIFFA_ART27);
		riduzioniDomesticheList.add(RIDUZIONE_REVOCA);
	}

	@Override
	public String getResponse(String xml) {

		DichiarazioneTassaRifiutiInputRichiesta richiesta = xmlHelper.unmarshal(xml, DichiarazioneTassaRifiutiInputRichiesta.class);

		String result = null;
		try {
			DichiarazioneTassaRifiutiInputRisposta risp = risposta(richiesta, xml);
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
	private DichiarazioneTassaRifiutiInputRisposta risposta(DichiarazioneTassaRifiutiInputRichiesta richiesta, String xml) {

		DichiarazioneTassaRifiutiInputRisposta dichiarazioneTassaRifiutiInputRisposta = new DichiarazioneTassaRifiutiInputRisposta();

		CommonResponseType risposta = null;

		try {

			String note = null;

			Iscrizione iscrizione = richiesta.getIscrizione();
			Variazione variazione = richiesta.getVariazione();
			Cessazione cessazione = richiesta.getCessazione();
			Trasferimento trasferimento = richiesta.getTrasferimento();
			Agevolazione agevolazione = richiesta.getAgevolazione();

			// Controllo esistenza richiesta
			if (iscrizione == null && variazione == null && cessazione == null && trasferimento == null && agevolazione == null) {
				String messaggioErrore = "Richiesta vuota, impossibile continuare.";
				log.error(messaggioErrore);
				Errore errore = new Errore();
				errore.setCodice(4);
				errore.setDescrizione(messaggioErrore);
				dichiarazioneTassaRifiutiInputRisposta.setErrore(errore);
				return dichiarazioneTassaRifiutiInputRisposta;
			}

			// TODO bisognerebbe verificare che non eistino già altre pratiche in lavorazione? (e
			// dunque bloccare la chiamata) --> si può fare se faccio un'iscrizione? credo di no!

			Dichiarazione dichiarazione = getDichiarazione(richiesta);

			Dichiarante dichiarante = dichiarazione.getDichiarante();
			String codiceFiscaleDichiarante = null;
			if (dichiarante.getPersonaFisica() != null) {
				codiceFiscaleDichiarante = dichiarante.getPersonaFisica().getCodiceFiscale();
			}
			else if (dichiarante.getPersonaGiuridica() != null) {
				codiceFiscaleDichiarante = dichiarante.getPersonaGiuridica().getCodiceFiscale();
			}

			ProtocolloRisposta protocolloRisposta;
			try {
				ProtocolloRichiesta protocolloRichiesta = getProtocolloRequest(richiesta, codiceFiscaleDichiarante);
				protocolloRisposta = protocolloClient.protocolla(protocolloRichiesta);
			}
			catch (Exception e) {
				log.error("risposta :: " + e.getMessage(), e);
				String messaggioErrore = "Errore durante la comunicazione con GITRI: impossibile protocollare la dichiarazione. Messaggio di errore: " + e.getMessage()
						+ ". Pratica non inserita, riprovare. Se il problema persiste contattare il servizio di assistenza.";
				log.error(messaggioErrore);
				Errore errore = new Errore();
				errore.setCodice(ERROR_CODE_2);
				errore.setDescrizione(messaggioErrore);
				dichiarazioneTassaRifiutiInputRisposta.setErrore(errore);
				return dichiarazioneTassaRifiutiInputRisposta;
			}

			String numProtocollo = protocolloRisposta.getNumeroProtocollo().toString();
			String dataProtocollo = getDateString(protocolloRisposta.getDataProtocollo());
			String allegatiString = getAllegati(richiesta);
			// iscrizione
			if (iscrizione != null) {
				note = iscrizione.getNote();
				risposta = getIscrizioneResponse(iscrizione, numProtocollo, dataProtocollo, allegatiString);
			}
			// cessazione
			else if (cessazione != null) {
				note = cessazione.getNote();
				risposta = getCessazioneResponse(cessazione, numProtocollo, dataProtocollo, allegatiString);
			}
			// trasferimento
			else if (trasferimento != null) {
				note = trasferimento.getNote();
				risposta = getTrasferimentoResponse(trasferimento, numProtocollo, dataProtocollo, allegatiString);
			}
			// variazione
			else if (variazione != null) {
				note = variazione.getNote();
				risposta = getVariazioneResponse(variazione, numProtocollo, dataProtocollo, allegatiString);
			}
			// agevolazione
			else if (agevolazione != null) {
				note = agevolazione.getNote();
				risposta = getAgevolazioneResponse(agevolazione, numProtocollo, dataProtocollo, allegatiString);
			}
			else {
				risposta = new CommonResponseType();
				risposta.setCode(0);
				risposta.setIdRichiesta(null);
			}

			// // TODO riduzione: non esiste una sezione del modulo a parte, chiedere a COPPI
			// else if (null != null) {
			//
			// }
			// // oggetto non trovato
			// else {
			// String messaggioErrore = "Nessuna richiesta presente nel pacchetto inviato.";
			// log.error(messaggioErrore);
			// Errore errore = new Errore();
			// errore.setCodice(4);
			// errore.setDescrizione(messaggioErrore);
			// dichiarazioneTassaRifiutiInputRisposta.setErrore(errore);
			// }

			if (risposta != null) {
				int code = risposta.getCode();
				// Errore
				if (code != 0) {
					String messaggioErrore = "Errore durante la comunicazione con GITRI: error code: " + code + ", messaggio di errore: " + risposta.getMessaggio()
							+ ". Pratica non inserita, riprovare. Se il problema persiste contattare il servizio di assistenza.";
					log.error(messaggioErrore);
					Errore errore = new Errore();
					errore.setCodice(ERROR_CODE_2);
					errore.setDescrizione(messaggioErrore);
					dichiarazioneTassaRifiutiInputRisposta.setErrore(errore);
					return dichiarazioneTassaRifiutiInputRisposta;
				}
				else {

					String idPratica = risposta.getIdRichiesta();
					Calendar dataProtocolloCalendar = new GregorianCalendar();
					dataProtocolloCalendar.setTime(protocolloRisposta.getDataProtocollo());
					List<Documento> documenti = richiesta.getDocumento();

					dichiarazioneTassaRifiutiInputRisposta.setIdPratica(idPratica);
					dichiarazioneTassaRifiutiInputRisposta.setDataInserimento(Calendar.getInstance());
					dichiarazioneTassaRifiutiInputRisposta.setNumeroProtocollo(protocolloRisposta.getNumeroProtocollo());
					dichiarazioneTassaRifiutiInputRisposta.setDataProtocollo(dataProtocolloCalendar);

					Documento documentoXml = getDocumentoXml(xml, codiceFiscaleDichiarante);

					documenti.add(documentoXml);

					// Invio email
					try {
						sendMail(idPratica, protocolloRisposta, codiceFiscaleDichiarante, documenti, note, richiesta);
					}
					catch (Exception e) {
						// Errore di invio email --> avvertenza, la pratica dovrebbe comunque
						// essere inserita nel sistema ma notificato l'utente
						String messaggioErrore = "Impossibile inviare l'email al/agli indirizzo/i '" + emailAddressDichiarazioniTari + "'. Pratica inserita.";
						log.error("risposta :: " + messaggioErrore, e);
					}
				}
			}
			else {
				String messaggioErrore = "Errore durante la comunicazione con GITRI: risposta NULL, Pratica non inserita, riprovare. Se il problema persiste contattare il servizio di assistenza.";
				log.error(messaggioErrore);
				Errore errore = new Errore();
				errore.setCodice(ERROR_CODE_2);
				errore.setDescrizione(messaggioErrore);
				dichiarazioneTassaRifiutiInputRisposta.setErrore(errore);
				return dichiarazioneTassaRifiutiInputRisposta;
			}
		}
		catch (Exception e) {
			// Errore GRAVE/bloccante (non gestito)
			log.error("risposta :: " + e.getMessage(), e);
			Errore errore = new Errore();
			errore.setCodice(ERROR_CODE_1);
			errore.setDescrizione("Errore durante la comunicazione con GITRI: " + e.getMessage() + ". Contattare il servizio di assistenza.");
			dichiarazioneTassaRifiutiInputRisposta.setErrore(errore);
		}

		return dichiarazioneTassaRifiutiInputRisposta;
	}

	/**
	 * @param richiesta
	 * @return
	 */
	private Dichiarazione getDichiarazione(DichiarazioneTassaRifiutiInputRichiesta richiesta) {
		Dichiarazione dichiarazione = null;

		if (richiesta.getIscrizione() != null) {
			dichiarazione = richiesta.getIscrizione();
		}
		else if (richiesta.getVariazione() != null) {
			dichiarazione = richiesta.getVariazione();
		}
		else if (richiesta.getAgevolazione() != null) {
			dichiarazione = richiesta.getAgevolazione();
		}
		else if (richiesta.getTrasferimento() != null) {
			dichiarazione = richiesta.getTrasferimento();
		}
		else if (richiesta.getCessazione() != null) {
			dichiarazione = richiesta.getCessazione();
		}
		return dichiarazione;
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
	 * Chiama GITRI per effettuare una richiesta di iscrizione.
	 *
	 * @param iscrizione
	 * @param numProtocollo
	 * @param dataProtocollo
	 * @param allegatiString
	 * @return
	 * @throws Exception
	 */
	private CommonResponseType getIscrizioneResponse(Iscrizione iscrizione, String numProtocollo, String dataProtocollo, String allegatiString) throws Exception {

		String note = iscrizione.getNote();

		// Dati accesso al servizio
		DatiAutorizzazioneType datiAutorizzazione = getDatiAutorizzazioneType(IDENTIFICATIVO_SERVIZIO_ISCRIZIONE);

		Tracciamento tracciamento = iscrizione.getTracciamento();
		DatiTracciamentoRequestType datiTracciamento = new DatiTracciamentoRequestType();
		if (tracciamento != null) {
			datiTracciamento.setCodFiscaleOperatore(tracciamento.getCodiceFiscaleOperatore());
			datiTracciamento.setEmailOperatore(tracciamento.getEmailOperatore());
			datiTracciamento.setNomeOperatore(tracciamento.getNomeOperatore());
			datiTracciamento.setPIvaOperatore(tracciamento.getPartitaIvaOperatore());
		}

		String delegato = iscrizione.getDelegato();
		String docIdentita = iscrizione.getDocumentoIdentita();

		DatiIscrizioneType datiScrizione = new DatiIscrizioneType();
		Calendar data = iscrizione.getData();
		datiScrizione.setDataDichiarazione(getDateString(data));
		datiScrizione.setNumProtocollo(numProtocollo);
		datiScrizione.setDataProtocollo(dataProtocollo);
		datiScrizione.setAllegati(allegatiString);

		int idContribuente = 0;

		Dichiarante dichiarante = iscrizione.getDichiarante();
		if (dichiarante != null) {

			DatiContribuenteIscrizioneType datiContribuente = new DatiContribuenteIscrizioneType();
			Indirizzo domicilio = dichiarante.getDomicilio();
			IndirizzoType indirizzoDomicilio = getIndirizzoType(domicilio);
			datiContribuente.setDatiDomicilio(indirizzoDomicilio);
			Indirizzo provenienza = iscrizione.getProvenienza();
			IndirizzoType indirizzoProvenienza = getIndirizzoType(provenienza);
			datiContribuente.setDatiProvenienza(indirizzoProvenienza);
			Rappresentante rappresentante = dichiarante.getRappresentante();
			if (rappresentante != null) {
				DatiRappresentanteType datiRappresentante = getRappresentanteType(rappresentante);
				datiContribuente.setDatiRappresentante(datiRappresentante);
			}

			Recapito recapito = dichiarante.getRecapito();
			if (recapito != null) {
				DatiRecapitoType recapitoType = getRecapitoType(recapito);
				datiContribuente.setDatiRecapito(recapitoType);
			}

			datiContribuente.setFax(dichiarante.getFax());
			datiContribuente.setIndirizzoPEC(dichiarante.getPec());

			idContribuente = getIdContribuenteFromIdentificativoUtenza(dichiarante.getIdentificativoContribuente());

			PersonaFisica personaFisica = dichiarante.getPersonaFisica();
			PersonaGiuridica personaGiuridica = dichiarante.getPersonaGiuridica();

			if (idContribuente > 0) {
				datiContribuente.setIdContribuente(idContribuente);
			}
			else {
				ContribuenteType nuovoContribuenteType = new ContribuenteType();
				nuovoContribuenteType.setNumeroREA(dichiarante.getNumeroREA());
				nuovoContribuenteType.setPartitaIVA(dichiarante.getPartitaIVA());

				if (personaFisica != null) {

					PersonaFisicaType personaFisicaType = new PersonaFisicaType();
					personaFisicaType.setCodiceFiscale(personaFisica.getCodiceFiscale());
					personaFisicaType.setCognome(personaFisica.getCognome());
					// Distinzione tra comune italiano e stato estero di nascita
					Codifica statoEsteroNascita = personaFisica.getStatoEsteroNascita();
					if (statoEsteroNascita != null) {
						personaFisicaType.setComuneNascita(statoEsteroNascita.getCodice());
						personaFisicaType.setComuneEsteroNascita(statoEsteroNascita.getDescrizione());
					}
					else {
						personaFisicaType.setComuneNascita(personaFisica.getComuneNascita().getCodice());
					}
					personaFisicaType.setDataNascita(getDateString(personaFisica.getDataNascita()));
					personaFisicaType.setNome(personaFisica.getNome());
					Codifica provinciaNascita = personaFisica.getProvinciaNascita();
					if (provinciaNascita != null) {
						personaFisicaType.setProvinciaNascita(provinciaNascita.getCodice());
					}
					if (personaFisica.getSesso() != null) {
						personaFisicaType.setSesso(SessoType.fromValue(personaFisica.getSesso().getCodice()));
					}
					nuovoContribuenteType.setPersonaFisica(personaFisicaType);
				}

				if (personaGiuridica != null) {

					PersonaGiuridicaType personaGiuridicaType = new PersonaGiuridicaType();
					personaGiuridicaType.setCodiceFiscale(personaGiuridica.getCodiceFiscale());
					personaGiuridicaType.setDenominazione(personaGiuridica.getRagioneDenominazioneSociale());
					nuovoContribuenteType.setPersonaGiuridica(personaGiuridicaType);
				}
				nuovoContribuenteType.setProvinciaREA(dichiarante.getProvinciaREA());
				datiContribuente.setNuovoContribuente(nuovoContribuenteType);
			}

			datiContribuente.setTelefono(dichiarante.getTelefono());

			datiScrizione.setDatiContribuente(datiContribuente);
		}

		DatiUtenzeType datiUtenze = new DatiUtenzeType();

		UtenzeCommerciali utenzeCommerciali = iscrizione.getUtenzeCommerciali();
		if (utenzeCommerciali != null) {
			ElencoDatiUtenzeCommercialiType utenzeCommercialiType = new ElencoDatiUtenzeCommercialiType();
			List<DatiUtenzaCommercialeIscrizioneType> utenzeCommercialiInnerType = utenzeCommercialiType.getUtenza();
			List<UtenzaCommerciale> utenze = utenzeCommerciali.getUtenza();
			if (utenze != null) {
				for (UtenzaCommerciale utenzaCommerciale : utenze) {

					DatiUtenzaCommercialeIscrizioneType datiUtenzaCommercialeIscrizioneType = new DatiUtenzaCommercialeIscrizioneType();
					datiUtenzaCommercialeIscrizioneType.setCodAttivita(utenzaCommerciale.getCodiceAttivita());
					datiUtenzaCommercialeIscrizioneType.setDataDecorrenza(getDateString(iscrizione.getDataDecorrenza()));
					DatiCatastali datiCatastali = utenzaCommerciale.getDatiCatastali();
					datiUtenzaCommercialeIscrizioneType.setDatiCatastali(getDatiCatastaliType(datiCatastali));
					List<Riduzione> riduzioni = utenzaCommerciale.getRiduzione();

					// ATTENZIONE: prendo solo la prima riduzione (il servizio be gestisce
					// solo 1 in riduzione!!!)
					if (riduzioni != null && !riduzioni.isEmpty()) {
						Riduzione riduzione = riduzioni.get(0);
						RiduzioneCommercialeIscrizioneType riduzioneCommercialeType = new RiduzioneCommercialeIscrizioneType();
						Codifica articolo = riduzione.getArticolo();
						Codifica valore = riduzione.getValore();
						if (articolo.getCodice().equals(RIDUZIONE_SUPERFICIE_ART10)) {
							riduzioneCommercialeType.setRiduzioneSuperficieArt10(Short.valueOf(valore.getCodice()));
						}
						else if (articolo.equals(RIDUZIONE_TARIFFA_ART24)) {
							riduzioneCommercialeType.setRiduzioneTariffaArt24(valore.getCodice());
						}
						else if (articolo.equals(RIDUZIONE_TARIFFA_ART26)) {
							riduzioneCommercialeType.setRiduzioneTariffaArt26(valore.getCodice());
						}
						datiUtenzaCommercialeIscrizioneType.setRiduzioneCommerciale(riduzioneCommercialeType);
					}
					datiUtenzaCommercialeIscrizioneType.setSupCoperta(utenzaCommerciale.getSuperficieCoperta());
					datiUtenzaCommercialeIscrizioneType.setSupIntassabile(utenzaCommerciale.getSuperficieIntassabile());
					datiUtenzaCommercialeIscrizioneType.setSupRifiutiSpeciali(utenzaCommerciale.getSuperficieRifiutiSpeciali());
					datiUtenzaCommercialeIscrizioneType.setSupScoperta(utenzaCommerciale.getSuperficieScoperta());
					Indirizzo ubicazione = utenzaCommerciale.getUbicazione();
					datiUtenzaCommercialeIscrizioneType.setUbicazione(getIndirizzoComuneType(ubicazione));

					utenzeCommercialiInnerType.add(datiUtenzaCommercialeIscrizioneType);
				}
			}
			datiUtenze.setDatiUtenzeCommerciali(utenzeCommercialiType);
		}
		UtenzeDomestiche utenzeDomestiche = iscrizione.getUtenzeDomestiche();
		if (utenzeDomestiche != null) {
			DatiUtenzeDomesticheType utenzeDomesticheType = new DatiUtenzeDomesticheType();

			utenzeDomesticheType.setContribuenteResidente(utenzeDomestiche.isContribuenteResidente());
			List<OccupanteImmobile> occupantiImmobile = utenzeDomestiche.getOccupanteImmobile();
			if (occupantiImmobile != null && !occupantiImmobile.isEmpty()) {
				ElencoOccupantiImmobileType elencoOccupantiImmobileType = new ElencoOccupantiImmobileType();
				List<OccupanteImmobileType> occupantiImmobileType = elencoOccupantiImmobileType.getOccupanteImmobile();
				for (OccupanteImmobile occupanteImmobile : occupantiImmobile) {
					OccupanteImmobileType occupanteImmobileType = new OccupanteImmobileType();
					occupanteImmobileType.setCodiceFiscale(occupanteImmobile.getCodiceFiscale());
					occupanteImmobileType.setCognome(occupanteImmobile.getCognome());
					occupanteImmobileType.setDataFineOccupazione(getDateString(occupanteImmobile.getDataFineOccupazione()));
					occupanteImmobileType.setDataInizioOccupazione(getDateString(occupanteImmobile.getDataInizioOccupazione()));
					occupanteImmobileType.setDataNascita(getDateString(occupanteImmobile.getDataNascita()));
					occupanteImmobileType.setNome(occupanteImmobile.getNome());
					occupantiImmobileType.add(occupanteImmobileType);
				}
				utenzeDomesticheType.setElencoOccupantiImmobile(elencoOccupantiImmobileType);
			}

			List<UtenzaDomestica> utenze = utenzeDomestiche.getUtenza();
			if (utenze != null) {
				List<DatiUtenzaDomesticaType> utenza = utenzeDomesticheType.getUtenza();
				for (UtenzaDomestica utenzaDomestica : utenze) {

					DatiUtenzaDomesticaType utenzaDomesticaType = new DatiUtenzaDomesticaType();
					utenzaDomesticaType.setDataDecorrenza(getDateString(iscrizione.getDataDecorrenza()));
					utenzaDomesticaType.setDatiCatastali(getDatiCatastaliType(utenzaDomestica.getDatiCatastali()));
					utenzaDomesticaType.setUbicazione(getIndirizzoComuneType(utenzaDomestica.getUbicazione()));
					Codifica categoriaCatastale = utenzaDomestica.getCategoriaCatastale();
					if (categoriaCatastale != null && categoriaCatastale.getCodice() != null && !categoriaCatastale.getCodice().isEmpty()) {
						utenzaDomesticaType.setCategoriaCatastale(CategoriaCatastaleType.fromValue(categoriaCatastale.getCodice()));
					}
					utenzaDomesticaType.setSuperficie(utenzaDomestica.getSuperficie() != null ? utenzaDomestica.getSuperficie().intValue() : null);
					utenzaDomesticaType.setUso(utenzaDomestica.getUso() != null ? UsoImmobileType.fromValue(utenzaDomestica.getUso().getCodice()) : null);

					List<Riduzione> riduzioni = utenzaDomestica.getRiduzione();
					// ATTENZIONE: prendo solo la prima riduzione (il servizio be gestisce
					// solo 1 in riduzione!!!)
					if (riduzioni != null && !riduzioni.isEmpty()) {
						Riduzione riduzione = riduzioni.get(0);
						Codifica articolo = riduzione.getArticolo();
						if (articolo.getCodice().equals(RIDUZIONE_TARIFFA_ART23)) {
							utenzeDomesticheType.setRiduzioneTariffa(RiduzioneTariffaDomestica2014Type.fromValue(riduzione.getValore().getCodice()));
						}
					}
					utenza.add(utenzaDomesticaType);
				}
			}
			utenzeDomesticheType.setNumComponentiNucleoFamiliare((short) utenzeDomestiche.getNumComponentiNucleoFamiliare());

			datiUtenze.setDatiUtenzeDomestiche(utenzeDomesticheType);
		}
		datiScrizione.setDatiUtenze(datiUtenze);

		datiScrizione.setDelegato(delegato);
		datiScrizione.setDocIdentita(docIdentita);
		datiScrizione.setNote(note);
		datiScrizione.setNumProtocollo(numProtocollo);
		if (iscrizione.getTipoUtenza() != null) {
			datiScrizione.setTipoUtenza(TipoUtenzaType.fromValue(iscrizione.getTipoUtenza().getCodice()));
		}

		SetIscrizioneRequestType iscrizioneRequestType = new SetIscrizioneRequestType();
		iscrizioneRequestType.setDatiAutorizzazione(datiAutorizzazione);
		iscrizioneRequestType.setDatiIscrizione(datiScrizione);
		iscrizioneRequestType.setDatiTracciamento(datiTracciamento);

		TaresServicePortType taresServicePort = getTaresServicePort(serviziGitriMap.get(IDENTIFICATIVO_SERVIZIO_ISCRIZIONE));
		SetIscrizioneResponseType setIscrizione = taresServicePort.setIscrizione(iscrizioneRequestType);
		return setIscrizione.getReturn();

	}

	/**
	 * Chiama GITRI per effettuare una richiesta di trasferimento.
	 *
	 * @param trasferimento
	 * @param numProtocollo
	 * @param dataProtocollo
	 * @param allegatiString
	 * @return
	 * @throws Exception
	 */
	private CommonResponseType getTrasferimentoResponse(Trasferimento trasferimento, String numProtocollo, String dataProtocollo, String allegatiString) throws Exception {

		DatiAutorizzazioneType datiAutorizzazione = getDatiAutorizzazioneType(IDENTIFICATIVO_SERVIZIO_TRASFERIMENTO);

		Tracciamento tracciamento = trasferimento.getTracciamento();
		DatiTracciamentoRequestType datiTracciamento = new DatiTracciamentoRequestType();
		if (tracciamento != null) {
			datiTracciamento.setCodFiscaleOperatore(tracciamento.getCodiceFiscaleOperatore());
			datiTracciamento.setEmailOperatore(tracciamento.getEmailOperatore());
			datiTracciamento.setNomeOperatore(tracciamento.getNomeOperatore());
			datiTracciamento.setPIvaOperatore(tracciamento.getPartitaIvaOperatore());
		}

		SetTrasferimentoRequestType trasferimentoType = new SetTrasferimentoRequestType();
		trasferimentoType.setDatiAutorizzazione(datiAutorizzazione);
		trasferimentoType.setDatiTracciamento(datiTracciamento);

		DatiTrasferimentoType datiTrasferimentoType = new DatiTrasferimentoType();
		datiTrasferimentoType.setNumProtocollo(numProtocollo);
		datiTrasferimentoType.setDataProtocollo(dataProtocollo);
		datiTrasferimentoType.setAllegati(allegatiString);
		datiTrasferimentoType.setDataDichiarazione(getDateString(trasferimento.getData()));
		datiTrasferimentoType.setDelegato(trasferimento.getDelegato());
		datiTrasferimentoType.setDocIdentita(trasferimento.getDocumentoIdentita());
		datiTrasferimentoType.setNote(trasferimento.getNote());

		if (trasferimento.getTipoUtenza() != null) {
			datiTrasferimentoType.setTipoUtenza(TipoUtenzaType.fromValue(trasferimento.getTipoUtenza().getCodice()));
		}

		NuovaUtenzaCommerciale nuovaUtenzaCommerciale = trasferimento.getNuovaUtenzaCommerciale();
		if (nuovaUtenzaCommerciale != null) {
			// Utenze commerciali multiple a fronte di una sola richiesta possibile su GITRI
			// --> prendo solo la prima
			List<UtenzaCommerciale> utenzeCommerciali = nuovaUtenzaCommerciale.getUtenza();
			if (utenzeCommerciali != null && !utenzeCommerciali.isEmpty()) {
				UtenzaCommerciale utenzaCommerciale = utenzeCommerciali.get(0);
				DatiUtenzaCommercialeTrasferimentoType nuovaUtenzaCommercialeType = new DatiUtenzaCommercialeTrasferimentoType();
				nuovaUtenzaCommercialeType.setCodAttivita(utenzaCommerciale.getCodiceAttivita());
				nuovaUtenzaCommercialeType.setDataDecorrenza(getDateString(trasferimento.getDataDecorrenza()));
				DatiCatastali datiCatastali = utenzaCommerciale.getDatiCatastali();
				if (datiCatastali != null) {
					DatiCatastaliNewType datiCatastaliType = getDatiCatastaliType(datiCatastali);
					nuovaUtenzaCommercialeType.setDatiCatastali(datiCatastaliType);
				}
				// ATTENZIONE: prendo solo la prima riduzione (il servizio be gestisce
				// solo 1 in riduzione!!!)
				List<Riduzione> riduzioni = utenzaCommerciale.getRiduzione();
				if (riduzioni != null && !riduzioni.isEmpty()) {
					Riduzione riduzione = riduzioni.get(0);
					RiduzioneCommercialeTrasferimentoType riduzioneCommercialeTrasferimentoType = new RiduzioneCommercialeTrasferimentoType();
					Codifica articolo = riduzione.getArticolo();
					if (articolo.getCodice().equals(RIDUZIONE_SUPERFICIE_ART10)) {
						riduzioneCommercialeTrasferimentoType.setRiduzioneSuperficieArt10(Short.valueOf(riduzione.getValore().getCodice()));
					}
					else if (articolo.getCodice().equals(RIDUZIONE_TARIFFA_ART24)) {
						riduzioneCommercialeTrasferimentoType.setRiduzioneTariffaArt24(riduzione.getValore().getCodice());
					}
					else if (articolo.getCodice().equals(RIDUZIONE_TARIFFA_ART26)) {
						riduzioneCommercialeTrasferimentoType.setRiduzioneTariffaArt26(riduzione.getValore().getCodice());
					}
					else if (articolo.getCodice().equals(RIDUZIONE_TARIFFA_ART27)) {
						riduzioneCommercialeTrasferimentoType.setRiduzioneTariffaArt27(riduzione.getValore().getCodice());
					}
					else if (articolo.getCodice().equals(RIDUZIONE_REVOCA)) {
						riduzioneCommercialeTrasferimentoType.setRevocaRiduzione(true);
					}
					nuovaUtenzaCommercialeType.setRiduzioneCommerciale(riduzioneCommercialeTrasferimentoType);
				}
				nuovaUtenzaCommercialeType.setSupCoperta(utenzaCommerciale.getSuperficieCoperta());
				nuovaUtenzaCommercialeType.setSupIntassabile(utenzaCommerciale.getSuperficieIntassabile());
				nuovaUtenzaCommercialeType.setSupRifiutiSpeciali(utenzaCommerciale.getSuperficieRifiutiSpeciali());
				nuovaUtenzaCommercialeType.setSupScoperta(utenzaCommerciale.getSuperficieScoperta());
				Indirizzo ubicazione = utenzaCommerciale.getUbicazione();
				if (ubicazione != null) {
					IndirizzoComuneType ubicazioneType = getIndirizzoComuneType(ubicazione);
					nuovaUtenzaCommercialeType.setUbicazione(ubicazioneType);
				}
				datiTrasferimentoType.setDatiNuovaUtenzaCommerciale(nuovaUtenzaCommercialeType);
			}
		}

		NuovaUtenzaDomestica nuovaUtenzaDomestica = trasferimento.getNuovaUtenzaDomestica();
		if (nuovaUtenzaDomestica != null) {

			DatiNuovaUtenzaDomesticaType datiNuovaUtenzaDomesticaType = new DatiNuovaUtenzaDomesticaType();
			List<OccupanteImmobileVariazione> occupantiImmobile = nuovaUtenzaDomestica.getOccupanteImmobile();

			if (occupantiImmobile != null && !occupantiImmobile.isEmpty()) {
				ElencoOccupantiImmobileVariazioneType elencoOccupantiImmobileType = new ElencoOccupantiImmobileVariazioneType();
				List<OccupanteImmobileVariazUtenzaDomType> occupanteImmobileType = elencoOccupantiImmobileType.getOccupanteImmobile();
				for (OccupanteImmobileVariazione occupanteImmobile : occupantiImmobile) {
					OccupanteImmobileVariazUtenzaDomType occupanteImmobileVariazUtenzaDomType = new OccupanteImmobileVariazUtenzaDomType();
					occupanteImmobileVariazUtenzaDomType.setCodiceFiscale(occupanteImmobile.getCodiceFiscale());
					occupanteImmobileVariazUtenzaDomType.setCognome(occupanteImmobile.getCognome());
					occupanteImmobileVariazUtenzaDomType.setNome(occupanteImmobile.getNome());
					occupanteImmobileVariazUtenzaDomType.setDataNascita(getDateString(occupanteImmobile.getDataNascita()));

					occupanteImmobileVariazUtenzaDomType.setDataFineOccupazione(getDateString(occupanteImmobile.getDataFineOccupazione()));
					occupanteImmobileVariazUtenzaDomType.setDataInizioOccupazione(getDateString(occupanteImmobile.getDataInizioOccupazione()));
					Codifica tipoVariazioneOccupanti = occupanteImmobile.getTipoVariazioneOccupanti();
					if (tipoVariazioneOccupanti != null) {
						occupanteImmobileVariazUtenzaDomType.setTipoVariazioneOccupanti(TipoVariazioneOccupanteImmobileType.fromValue(tipoVariazioneOccupanti.getCodice()));
					}
					occupanteImmobileVariazUtenzaDomType.setNote(occupanteImmobile.getNote());

					occupanteImmobileType.add(occupanteImmobileVariazUtenzaDomType);
				}
				datiNuovaUtenzaDomesticaType.setElencoOccupantiImmobile(elencoOccupantiImmobileType);
			}

			datiNuovaUtenzaDomesticaType.setNumComponentiNucleoFamiliare((short) nuovaUtenzaDomestica.getNumComponentiNucleoFamiliare());
			// Utenze domestiche multiple a fronte di una sola richiesta possibile su GITRI
			// --> prendo solo la prima
			List<UtenzaDomestica> utenzeDomestiche = nuovaUtenzaDomestica.getUtenza();
			if (utenzeDomestiche != null && !utenzeDomestiche.isEmpty()) {
				UtenzaDomestica utenzaDomestica = utenzeDomestiche.get(0);
				DatiUtenzaDomesticaType nuovaUtenzaType = new DatiUtenzaDomesticaType();
				nuovaUtenzaType.setDataDecorrenza(getDateString(trasferimento.getDataDecorrenza()));
				nuovaUtenzaType.setDatiCatastali(getDatiCatastaliType(utenzaDomestica.getDatiCatastali()));
				nuovaUtenzaType.setUbicazione(getIndirizzoComuneType(utenzaDomestica.getUbicazione()));
				Codifica categoriaCatastale = utenzaDomestica.getCategoriaCatastale();
				if (categoriaCatastale != null && categoriaCatastale.getCodice() != null && !categoriaCatastale.getCodice().isEmpty()) {
					nuovaUtenzaType.setCategoriaCatastale(CategoriaCatastaleType.fromValue(categoriaCatastale.getCodice()));
				}
				BigDecimal superficie = utenzaDomestica.getSuperficie();
				if (superficie != null) {
					nuovaUtenzaType.setSuperficie(superficie.intValue());
				}
				Codifica uso = utenzaDomestica.getUso();
				if (uso != null) {
					nuovaUtenzaType.setUso(UsoImmobileType.fromValue(uso.getCodice()));
				}
				datiNuovaUtenzaDomesticaType.setNuovaUtenza(nuovaUtenzaType);
			}

			datiTrasferimentoType.setDatiNuovaUtenzaDomestica(datiNuovaUtenzaDomesticaType);

		}

		int idContribuente = 0;

		String identificativoVecchiaUtenza = trasferimento.getIdentificativoUtenza();
		if (identificativoVecchiaUtenza != null) {

			idContribuente = getIdContribuenteFromIdentificativoUtenza(identificativoVecchiaUtenza);
			DatiVecchiaUtenzaType datiVecchiaUtenzaType = new DatiVecchiaUtenzaType();
			datiVecchiaUtenzaType.setIdUtenza(getIdUtenzaFromIdentificativoUtenza(identificativoVecchiaUtenza));
			datiVecchiaUtenzaType.setDataVariazione(getDataVariazioneUtenzaFromIdentificativoUtenza(identificativoVecchiaUtenza));
			datiTrasferimentoType.setDatiVecchiaUtenza(datiVecchiaUtenzaType);
		}

		Dichiarante dichiarante = trasferimento.getDichiarante();
		if (dichiarante != null) {
			DatiContribuenteVariazioneType datiContribuenteType = new DatiContribuenteVariazioneType();
			datiContribuenteType.setIdContribuente(idContribuente);

			Indirizzo domicilio = dichiarante.getDomicilio();
			if (domicilio != null) {
				IndirizzoType datiDomicilioType = getIndirizzoType(domicilio);
				datiContribuenteType.setDatiDomicilio(datiDomicilioType);
			}
			Rappresentante rappresentante = dichiarante.getRappresentante();
			if (rappresentante != null) {
				DatiRappresentanteType datiRappresentanteType = getRappresentanteType(rappresentante);
				datiContribuenteType.setDatiRappresentante(datiRappresentanteType);
			}
			Recapito recapito = dichiarante.getRecapito();
			if (recapito != null) {
				DatiRecapitoType datiRecapitoType = getRecapitoType(recapito);
				datiContribuenteType.setDatiRecapito(datiRecapitoType);
			}
			datiContribuenteType.setFax(dichiarante.getFax());
			datiContribuenteType.setIndirizzoPEC(dichiarante.getPec());
			datiContribuenteType.setTelefono(dichiarante.getTelefono());

			// Distinzione persona fisica/giuridica (da inviare per aggiornare i dati)
			PersonaFisica personaFisica = dichiarante.getPersonaFisica();
			if (personaFisica != null) {
				VariazioneAnagraficaPersonaFisicaType variazioneAnagraficaPersonaFisica = getVariazioneAnagraficaPersonaFisica(personaFisica);
				datiContribuenteType.setVariazioneAnagraficaPersonaFisica(variazioneAnagraficaPersonaFisica);
			}
			PersonaGiuridica personaGiuridica = dichiarante.getPersonaGiuridica();
			if (personaGiuridica != null) {
				VariazioneAnagraficaPersonaGiuridicaType variazioneAnagraficaPersonaGiuridica = getVariazioneAnagraficaPersonaGiuridica(personaGiuridica);
				datiContribuenteType.setVariazioneAnagraficaPersonaGiuridica(variazioneAnagraficaPersonaGiuridica);
			}
			datiTrasferimentoType.setDatiContribuente(datiContribuenteType);
		}

		trasferimentoType.setDatiTrasferimento(datiTrasferimentoType);

		TaresServicePortType taresServicePort = getTaresServicePort(serviziGitriMap.get(IDENTIFICATIVO_SERVIZIO_TRASFERIMENTO));

		SetTrasferimentoResponseType setTrasferimento = taresServicePort.setTrasferimento(trasferimentoType);

		return setTrasferimento.getReturn();

	}

	/**
	 * Chiama GITRI per effettuare una richiesta di variazione (di superficie o categoria).
	 *
	 * @param variazione
	 * @param numProtocollo
	 * @param dataProtocollo
	 * @param allegatiString
	 * @return
	 * @throws Exception
	 */
	private CommonResponseType getVariazioneResponse(Variazione variazione, String numProtocollo, String dataProtocollo, String allegatiString) throws Exception {

		Tracciamento tracciamento = variazione.getTracciamento();
		DatiTracciamentoRequestType datiTracciamento = new DatiTracciamentoRequestType();
		if (tracciamento != null) {
			datiTracciamento.setCodFiscaleOperatore(tracciamento.getCodiceFiscaleOperatore());
			datiTracciamento.setEmailOperatore(tracciamento.getEmailOperatore());
			datiTracciamento.setNomeOperatore(tracciamento.getNomeOperatore());
			datiTracciamento.setPIvaOperatore(tracciamento.getPartitaIvaOperatore());
		}

		it.osapulie.tributi.web.ws.input.types.Variazione.UtenzeDomestiche utenzeDomestiche = variazione.getUtenzeDomestiche();
		it.osapulie.tributi.web.ws.input.types.Variazione.UtenzeCommerciali utenzeCommerciali = variazione.getUtenzeCommerciali();

		// Superficie
		boolean isVariazioneSuperficie = false;
		List<UtenzaDomesticaVariazione> utenzeDomesticheVariazione = null;
		if (utenzeDomestiche != null) {
			utenzeDomesticheVariazione = utenzeDomestiche.getUtenza();
			for (UtenzaDomesticaVariazione utenzaDomesticaVariazione : utenzeDomesticheVariazione) {
				Integer nuovaSuperficie = utenzaDomesticaVariazione.getNuovaSuperficie();
				if (nuovaSuperficie != null) {
					isVariazioneSuperficie = true;
					break;
				}

			}
		}
		// Superficie o Categoria
		boolean isVariazioneCategoria = false;
		List<UtenzaCommercialeVariazione> utenzeCommercialiVariazione = null;
		if (utenzeCommerciali != null) {
			utenzeCommercialiVariazione = utenzeCommerciali.getUtenza();
			for (UtenzaCommercialeVariazione utenzaCommercialeVariazione : utenzeCommercialiVariazione) {
				String nuovaCategoria = utenzaCommercialeVariazione.getNuovaCategoria();
				Integer nuovaSuperficie = utenzaCommercialeVariazione.getNuovaSuperficie();
				if (nuovaCategoria != null && !nuovaCategoria.isEmpty()) {
					isVariazioneCategoria = true;
					break;
				}
				else if (nuovaSuperficie != null) {
					isVariazioneSuperficie = true;
					break;
				}
			}
		}

		Dichiarante dichiarante = variazione.getDichiarante();
		DatiContribuenteVariazioneType datiContribuenteType = new DatiContribuenteVariazioneType();
		if (dichiarante != null) {

			Indirizzo domicilio = dichiarante.getDomicilio();
			if (domicilio != null) {
				IndirizzoType datiDomicilioType = getIndirizzoType(domicilio);
				datiContribuenteType.setDatiDomicilio(datiDomicilioType);
			}
			Rappresentante rappresentante = dichiarante.getRappresentante();
			if (rappresentante != null) {
				DatiRappresentanteType datiRappresentanteType = getRappresentanteType(rappresentante);
				datiContribuenteType.setDatiRappresentante(datiRappresentanteType);
			}
			Recapito recapito = dichiarante.getRecapito();
			if (recapito != null) {
				DatiRecapitoType datiRecapitoType = getRecapitoType(recapito);
				datiContribuenteType.setDatiRecapito(datiRecapitoType);
			}
			datiContribuenteType.setFax(dichiarante.getFax());
			datiContribuenteType.setIndirizzoPEC(dichiarante.getPec());
			datiContribuenteType.setTelefono(dichiarante.getTelefono());

			PersonaFisica personaFisica = dichiarante.getPersonaFisica();
			if (personaFisica != null) {
				VariazioneAnagraficaPersonaFisicaType variazioneAnagraficaPersonaFisica = getVariazioneAnagraficaPersonaFisica(personaFisica);
				datiContribuenteType.setVariazioneAnagraficaPersonaFisica(variazioneAnagraficaPersonaFisica);
			}
			PersonaGiuridica personaGiuridica = dichiarante.getPersonaGiuridica();
			if (personaGiuridica != null) {
				VariazioneAnagraficaPersonaGiuridicaType variazioneAnagraficaPersonaGiuridicaType = getVariazioneAnagraficaPersonaGiuridica(personaGiuridica);
				datiContribuenteType.setVariazioneAnagraficaPersonaGiuridica(variazioneAnagraficaPersonaGiuridicaType);
			}
		}

		int idContribuente = 0;

		// variazione categoria (solo utenze NON domestiche)
		Codifica tipoUtenza = variazione.getTipoUtenza();
		if (isVariazioneCategoria) {

			DatiVariazioneCategoriaRequestType datiVariazioneType = new DatiVariazioneCategoriaRequestType();
			datiVariazioneType.setDataDichiarazione(getDateString(variazione.getData()));
			datiVariazioneType.setNumProtocollo(numProtocollo);
			datiVariazioneType.setDataProtocollo(dataProtocollo);
			datiVariazioneType.setAllegati(allegatiString);
			datiVariazioneType.setDelegato(variazione.getDelegato());
			datiVariazioneType.setDocIdentita(variazione.getDocumentoIdentita());
			datiVariazioneType.setNote(variazione.getNote());
			if (tipoUtenza != null) {
				datiVariazioneType.setTipoUtenza(TipoUtenzaType.fromValue(tipoUtenza.getCodice()));
			}

			// Solo x utenze commerciali
			if (utenzeCommercialiVariazione != null && !utenzeCommercialiVariazione.isEmpty()) {

				DatiUtenzaVariazioneCategoriaType datiUtenzaType = new DatiUtenzaVariazioneCategoriaType();
				UtenzaCommercialeVariazione utenzaCommercialeVariazione = utenzeCommercialiVariazione.get(0);
				String identificativoUtenza = utenzaCommercialeVariazione.getIdentificativoUtenza();
				if (identificativoUtenza != null) {

					idContribuente = getIdContribuenteFromIdentificativoUtenza(identificativoUtenza);

					datiUtenzaType.setIdUtenza(getIdUtenzaFromIdentificativoUtenza(identificativoUtenza));
					datiUtenzaType.setDataVariazione(getDateString(variazione.getDataDecorrenza()));
					datiUtenzaType.setDataDecorrenza(getDateString(variazione.getDataDecorrenza()));
				}

				datiUtenzaType.setCodAttivita(utenzaCommercialeVariazione.getNuovaCategoria());

				// TODO controllare cosa arriva dal frontend (costanti)
				// ATTENZIONE: prendo solo la prima riduzione (il servizio be gestisce
				// solo 1 in riduzione!!!)
				List<Riduzione> riduzioni = utenzaCommercialeVariazione.getRiduzione();
				if (riduzioni != null && !riduzioni.isEmpty()) {
					Riduzione riduzione = riduzioni.get(0);
					Codifica articolo = riduzione.getArticolo();
					RiduzioneCommercialeVariazioneType riduzioneCommercialeVariazioneType = new RiduzioneCommercialeVariazioneType();
					if (articolo.getCodice().equals(RIDUZIONE_SUPERFICIE_ART10)) {
						riduzioneCommercialeVariazioneType.setRiduzioneSuperficieArt10(Short.valueOf(riduzione.getValore().getCodice()));
					}
					else if (articolo.getCodice().equals(RIDUZIONE_TARIFFA_ART24)) {
						riduzioneCommercialeVariazioneType.setRiduzioneTariffaArt24(riduzione.getValore().getCodice());
					}
					else if (articolo.getCodice().equals(RIDUZIONE_TARIFFA_ART25)) {
						riduzioneCommercialeVariazioneType.setRiduzioneTariffaArt25(riduzione.getValore().getCodice());
					}
					else if (articolo.getCodice().equals(RIDUZIONE_TARIFFA_ART26)) {
						riduzioneCommercialeVariazioneType.setRiduzioneTariffaArt26(riduzione.getValore().getCodice());
					}
					else if (articolo.getCodice().equals(RIDUZIONE_TARIFFA_ART27)) {
						riduzioneCommercialeVariazioneType.setRiduzioneTariffaArt27(riduzione.getValore().getCodice());
					}
					else if (articolo.getCodice().equals(RIDUZIONE_REVOCA)) {
						riduzioneCommercialeVariazioneType.setRevocaRiduzione(true);
					}
					datiUtenzaType.setRiduzioneCommerciale(riduzioneCommercialeVariazioneType);
				}

				datiVariazioneType.setDatiUtenza(datiUtenzaType);

			}

			DatiAutorizzazioneType datiAutorizzazione = getDatiAutorizzazioneType(IDENTIFICATIVO_SERVIZIO_VARIAZIONE_CATEGORIA);
			SetVariazioneCategoriaRequestType variazioneCategoriaType = new SetVariazioneCategoriaRequestType();
			variazioneCategoriaType.setDatiAutorizzazione(datiAutorizzazione);
			variazioneCategoriaType.setDatiTracciamento(datiTracciamento);

			datiContribuenteType.setIdContribuente(idContribuente);
			datiVariazioneType.setDatiContribuente(datiContribuenteType);

			variazioneCategoriaType.setDatiVariazione(datiVariazioneType);

			TaresServicePortType taresServicePort = getTaresServicePort(serviziGitriMap.get(IDENTIFICATIVO_SERVIZIO_VARIAZIONE_CATEGORIA));
			SetVariazioneCategoriaResponseType setVariazioneCategoria = taresServicePort.setVariazioneCategoria(variazioneCategoriaType);
			return setVariazioneCategoria.getReturn();

		}
		// variazione superficie
		else {

			DatiVariazioneSuperficieRequestType datiVariazioneSuperficieType = new DatiVariazioneSuperficieRequestType();
			datiVariazioneSuperficieType.setDataDichiarazione(getDateString(variazione.getData()));
			datiVariazioneSuperficieType.setNumProtocollo(numProtocollo);
			datiVariazioneSuperficieType.setDataProtocollo(dataProtocollo);
			datiVariazioneSuperficieType.setAllegati(allegatiString);
			datiVariazioneSuperficieType.setDelegato(variazione.getDelegato());
			datiVariazioneSuperficieType.setDocIdentita(variazione.getDocumentoIdentita());
			datiVariazioneSuperficieType.setNote(variazione.getNote());
			datiVariazioneSuperficieType.setDatiContribuente(datiContribuenteType);
			if (tipoUtenza != null) {
				datiVariazioneSuperficieType.setTipoUtenza(TipoUtenzaType.fromValue(tipoUtenza.getCodice()));
			}

			if (utenzeCommercialiVariazione != null && !utenzeCommercialiVariazione.isEmpty()) {

				DatiUtenzaVariazioneSuperficieCommercialeType datiUtenzaVariazioneSuperficieCommerciale = new DatiUtenzaVariazioneSuperficieCommercialeType();
				UtenzaCommercialeVariazione utenzaCommercialeVariazione = utenzeCommercialiVariazione.get(0);
				String identificativoUtenza = utenzaCommercialeVariazione.getIdentificativoUtenza();
				if (identificativoUtenza != null) {

					idContribuente = getIdContribuenteFromIdentificativoUtenza(identificativoUtenza);

					datiUtenzaVariazioneSuperficieCommerciale.setIdUtenza(getIdUtenzaFromIdentificativoUtenza(identificativoUtenza));
					datiUtenzaVariazioneSuperficieCommerciale.setDataVariazione(getDateString(variazione.getDataDecorrenza()));
					datiUtenzaVariazioneSuperficieCommerciale.setDataDecorrenza(getDateString(variazione.getDataDecorrenza()));
				}

				if (utenzaCommercialeVariazione.getSuperficieCoperta() != null) {
					datiUtenzaVariazioneSuperficieCommerciale.setSupCoperta(utenzaCommercialeVariazione.getNuovaSuperficie());
				}
				if (utenzaCommercialeVariazione.getSuperficieScoperta() != null) {
					datiUtenzaVariazioneSuperficieCommerciale.setSupScoperta(utenzaCommercialeVariazione.getNuovaSuperficie());
				}

				datiUtenzaVariazioneSuperficieCommerciale.setSupIntassabile(utenzaCommercialeVariazione.getSuperficieIntassabile());
				datiUtenzaVariazioneSuperficieCommerciale.setSupRifiutiSpeciali(utenzaCommercialeVariazione.getSuperficieRifiutiSpeciali());

				// TODO controllare cosa arriva dal frontend (costanti)
				// ATTENZIONE: prendo solo la prima riduzione (il servizio be gestisce
				// solo 1 in riduzione!!!)
				List<Riduzione> riduzioni = utenzaCommercialeVariazione.getRiduzione();
				if (riduzioni != null && !riduzioni.isEmpty()) {
					Riduzione riduzione = riduzioni.get(0);
					Codifica articolo = riduzione.getArticolo();
					RiduzioneCommercialeVariazioneType riduzioneCommercialeVariazioneType = new RiduzioneCommercialeVariazioneType();
					if (articolo.getCodice().equals(RIDUZIONE_SUPERFICIE_ART10)) {
						riduzioneCommercialeVariazioneType.setRiduzioneSuperficieArt10(Short.valueOf(riduzione.getValore().getCodice()));
					}
					else if (articolo.getCodice().equals(RIDUZIONE_TARIFFA_ART24)) {
						riduzioneCommercialeVariazioneType.setRiduzioneTariffaArt24(riduzione.getValore().getCodice());
					}
					else if (articolo.getCodice().equals(RIDUZIONE_TARIFFA_ART25)) {
						riduzioneCommercialeVariazioneType.setRiduzioneTariffaArt25(riduzione.getValore().getCodice());
					}
					else if (articolo.getCodice().equals(RIDUZIONE_TARIFFA_ART26)) {
						riduzioneCommercialeVariazioneType.setRiduzioneTariffaArt26(riduzione.getValore().getCodice());
					}
					else if (articolo.getCodice().equals(RIDUZIONE_TARIFFA_ART27)) {
						riduzioneCommercialeVariazioneType.setRiduzioneTariffaArt27(riduzione.getValore().getCodice());
					}
					else if (articolo.getCodice().equals(RIDUZIONE_REVOCA)) {
						riduzioneCommercialeVariazioneType.setRevocaRiduzione(true);
					}
					datiUtenzaVariazioneSuperficieCommerciale.setRiduzioneCommerciale(riduzioneCommercialeVariazioneType);
				}

				datiVariazioneSuperficieType.setDatiUtenzaVariazioneSuperficieCommerciale(datiUtenzaVariazioneSuperficieCommerciale);

			}
			else if (utenzeDomesticheVariazione != null && !utenzeDomesticheVariazione.isEmpty()) {

				DatiUtenzaVariazioneSuperficieDomesticaType datiUtenzaVariazioneSuperficieDomestica = new DatiUtenzaVariazioneSuperficieDomesticaType();
				UtenzaDomesticaVariazione utenzaDomesticaVariazione = utenzeDomesticheVariazione.get(0);
				String identificativoUtenza = utenzaDomesticaVariazione.getIdentificativoUtenza();
				if (identificativoUtenza != null) {
					idContribuente = getIdContribuenteFromIdentificativoUtenza(identificativoUtenza);

					datiUtenzaVariazioneSuperficieDomestica.setIdUtenza(getIdUtenzaFromIdentificativoUtenza(identificativoUtenza));
					datiUtenzaVariazioneSuperficieDomestica.setDataVariazione(getDataVariazioneUtenzaFromIdentificativoUtenza(identificativoUtenza));
					datiUtenzaVariazioneSuperficieDomestica.setDataDecorrenza(getDateString(variazione.getDataDecorrenza()));
				}

				if (utenzaDomesticaVariazione.getNuovaSuperficie() != null) {
					datiUtenzaVariazioneSuperficieDomestica.setNuovaSuperficie(utenzaDomesticaVariazione.getNuovaSuperficie());
				}

				Integer numeroComponentiNucleoFamiliare = utenzeDomestiche.getNumeroComponentiNucleoFamiliare();
				if (numeroComponentiNucleoFamiliare != null) {
					datiUtenzaVariazioneSuperficieDomestica.setNumComponentiNucleoFamiliare(numeroComponentiNucleoFamiliare.shortValue());
				}

				// TODO CHIEDERE a COPPI: xkè si parla di numero di componenti collegati
				// alla singola utenza? sul modello sono slegati!

				List<VariazioneOccupanteImmobile> variazioniOccupantiImmobile = utenzeDomestiche.getVariazioneOccupanteImmobile();
				if (variazioniOccupantiImmobile != null && !variazioniOccupantiImmobile.isEmpty()) {
					ElencoOccupantiImmobileVariazioneType elencoVariazioniOccupantiImmobileType = new ElencoOccupantiImmobileVariazioneType();
					List<OccupanteImmobileVariazUtenzaDomType> occupanteImmobile = elencoVariazioniOccupantiImmobileType.getOccupanteImmobile();
					for (VariazioneOccupanteImmobile variazioneOccupanteImmobile : variazioniOccupantiImmobile) {
						OccupanteImmobileVariazUtenzaDomType occupanteImmobileVariazUtenzaDomType = new OccupanteImmobileVariazUtenzaDomType();
						occupanteImmobileVariazUtenzaDomType.setCodiceFiscale(variazioneOccupanteImmobile.getCodiceFiscale());
						occupanteImmobileVariazUtenzaDomType.setCognome(variazioneOccupanteImmobile.getCognome());
						occupanteImmobileVariazUtenzaDomType.setNome(variazioneOccupanteImmobile.getNome());
						if (variazioneOccupanteImmobile.getDataNascita() != null) {
							occupanteImmobileVariazUtenzaDomType.setDataNascita(getDateString(variazioneOccupanteImmobile.getDataNascita()));
						}
						if (variazioneOccupanteImmobile.getDataInizioOccupazione() != null) {
							occupanteImmobileVariazUtenzaDomType.setDataInizioOccupazione(getDateString(variazioneOccupanteImmobile.getDataInizioOccupazione()));
						}
						if (variazioneOccupanteImmobile.getDataFineOccupazione() != null) {
							occupanteImmobileVariazUtenzaDomType.setDataFineOccupazione(getDateString(variazioneOccupanteImmobile.getDataFineOccupazione()));
						}
						occupanteImmobileVariazUtenzaDomType.setNote(variazioneOccupanteImmobile.getNote());
						Codifica tipoVariazione = variazioneOccupanteImmobile.getTipoVariazione();
						if (tipoVariazione != null) {
							occupanteImmobileVariazUtenzaDomType.setTipoVariazioneOccupanti(TipoVariazioneOccupanteImmobileType.fromValue(tipoVariazione.getCodice()));
						}
						occupanteImmobile.add(occupanteImmobileVariazUtenzaDomType);
					}
					datiUtenzaVariazioneSuperficieDomestica.setElencoVariazioniOccupantiImmobile(elencoVariazioniOccupantiImmobileType);
				}

				// TODO controllare cosa arriva dal frontend (costanti)
				// ATTENZIONE: prendo solo la prima riduzione (il servizio be gestisce
				// solo 1 in riduzione!!!)
				List<Riduzione> riduzioni = utenzaDomesticaVariazione.getRiduzione();
				if (riduzioni != null && !riduzioni.isEmpty()) {
					Riduzione riduzione = riduzioni.get(0);
					RiduzioneDomesticaType riduzioneDomesticaType = new RiduzioneDomesticaType();
					Codifica articolo = riduzione.getArticolo();
					if (articolo.getCodice().equals(RIDUZIONE_TARIFFA_ART23)) {
						riduzioneDomesticaType.setRiduzioneTariffaArt23(RiduzioneTariffaDomestica2014Type.fromValue(riduzione.getValore().getCodice()));
					}
					else if (articolo.getCodice().equals(RIDUZIONE_TARIFFA_ART27)) {
						riduzioneDomesticaType.setRiduzioneTariffaArt27(Short.valueOf(riduzione.getValore().getCodice()));
					}
					else if (articolo.getCodice().equals(RIDUZIONE_REVOCA)) {
						riduzioneDomesticaType.setRevocaRiduzione(true);
					}
					datiUtenzaVariazioneSuperficieDomestica.setRiduzioneDomestica(riduzioneDomesticaType);
				}
				datiVariazioneSuperficieType.setDatiUtenzaVariazioneSuperficieDomestica(datiUtenzaVariazioneSuperficieDomestica);
			}

			DatiAutorizzazioneType datiAutorizzazione = getDatiAutorizzazioneType(IDENTIFICATIVO_SERVIZIO_VARIAZIONE_SUPERFICIE);
			SetVariazioneSuperficieRequestType variazioneSuperficieType = new SetVariazioneSuperficieRequestType();
			variazioneSuperficieType.setDatiAutorizzazione(datiAutorizzazione);
			variazioneSuperficieType.setDatiTracciamento(datiTracciamento);

			datiContribuenteType.setIdContribuente(idContribuente);
			datiVariazioneSuperficieType.setDatiContribuente(datiContribuenteType);

			variazioneSuperficieType.setDatiVariazione(datiVariazioneSuperficieType);

			TaresServicePortType taresServicePort = getTaresServicePort(serviziGitriMap.get(IDENTIFICATIVO_SERVIZIO_VARIAZIONE_SUPERFICIE));

			SetVariazioneSuperficieResponseType setVariazioneSuperficie = taresServicePort.setVariazioneSuperficie(variazioneSuperficieType);
			return setVariazioneSuperficie.getReturn();

		}
	}

	/**
	 * Chiama GITRI per effettuare una richiesta di cessazione.
	 *
	 * @param cessazione
	 * @param numProtocollo
	 * @param dataProtocollo
	 * @param allegatiString
	 * @return
	 * @throws Exception
	 */
	private CommonResponseType getCessazioneResponse(Cessazione cessazione, String numProtocollo, String dataProtocollo, String allegatiString) throws Exception {

		DatiAutorizzazioneType datiAutorizzazione = getDatiAutorizzazioneType(IDENTIFICATIVO_SERVIZIO_CESSAZIONE);

		Tracciamento tracciamento = cessazione.getTracciamento();
		DatiTracciamentoRequestType datiTracciamento = new DatiTracciamentoRequestType();
		if (tracciamento != null) {
			datiTracciamento.setCodFiscaleOperatore(tracciamento.getCodiceFiscaleOperatore());
			datiTracciamento.setEmailOperatore(tracciamento.getEmailOperatore());
			datiTracciamento.setNomeOperatore(tracciamento.getNomeOperatore());
			datiTracciamento.setPIvaOperatore(tracciamento.getPartitaIvaOperatore());
		}

		SetCessazioneRequestType cessazioneType = new SetCessazioneRequestType();
		cessazioneType.setDatiAutorizzazione(datiAutorizzazione);
		cessazioneType.setDatiTracciamento(datiTracciamento);
		DatiCessazioneType datiCessazioneType = new DatiCessazioneType();

		datiCessazioneType.setNumProtocollo(numProtocollo);
		datiCessazioneType.setDataProtocollo(dataProtocollo);
		datiCessazioneType.setAllegati(allegatiString);

		Calendar data = cessazione.getData();
		datiCessazioneType.setDataDichiarazione(getDateString(data));

		int idContribuente = 0;

		// datiUtenzaCessazioneType
		DatiUtenzaCessazioneType datiUtenzaCessazioneType = new DatiUtenzaCessazioneType();
		it.osapulie.tributi.web.ws.input.types.Cessazione.UtenzeCommerciali utenzeCommerciali = cessazione.getUtenzeCommerciali();
		it.osapulie.tributi.web.ws.input.types.Cessazione.UtenzeDomestiche utenzeDomestiche = cessazione.getUtenzeDomestiche();
		if (utenzeCommerciali != null) {
			List<UtenzaCommerciale> utenze = utenzeCommerciali.getUtenza();
			if (utenze != null && !utenze.isEmpty()) {
				UtenzaCommerciale utenza = utenze.get(0);
				String identificativoUtenza = utenza.getIdentificativoUtenza();
				if (identificativoUtenza != null) {

					idContribuente = getIdContribuenteFromIdentificativoUtenza(identificativoUtenza);

					datiUtenzaCessazioneType.setIdUtenza(getIdUtenzaFromIdentificativoUtenza(identificativoUtenza));
					datiUtenzaCessazioneType.setDataCessazione(getDateString(cessazione.getDataDecorrenza()));
					datiUtenzaCessazioneType.setDataVariazione(getDataVariazioneUtenzaFromIdentificativoUtenza(identificativoUtenza));
				}
				Codifica motivo = utenzeCommerciali.getMotivo();
				if (motivo != null) {
					MotivoCessazioneCommercialeType motivoCessazioneUtenzaCommercialeType = new MotivoCessazioneCommercialeType();
					String codice = motivo.getCodice();
					// TODO non esiste il trasferimento!! COPPI
					if (codice.equals(MOTIVO_CESSAZIONE_UTENZA_COMMERCIALE_PER_CESSAZIONE)) {
						motivoCessazioneUtenzaCommercialeType.setMotivo("1");
					}
					else if (codice.equals(MOTIVO_CESSAZIONE_UTENZA_COMMERCIALE_PER_DUPLICAZIONE)) {
						motivoCessazioneUtenzaCommercialeType.setMotivo("2");
					}
					else if (codice.equals(MOTIVO_CESSAZIONE_UTENZA_COMMERCIALE_PER_CAMBIO_DENOMINAZIONE)) {
						motivoCessazioneUtenzaCommercialeType.setNuovaDenominazione(motivo.getDescrizione());
						motivoCessazioneUtenzaCommercialeType.setMotivo("3");
					}
					else if (codice.equals(MOTIVO_CESSAZIONE_UTENZA_COMMERCIALE_ALTRO)) {
						motivoCessazioneUtenzaCommercialeType.setAltroMotivo(motivo.getDescrizione());
						motivoCessazioneUtenzaCommercialeType.setMotivo("4");
					}
					datiUtenzaCessazioneType.setMotivoCessazioneUtenzaCommerciale(motivoCessazioneUtenzaCommercialeType);
				}
			}
		}
		else if (utenzeDomestiche != null) {
			List<UtenzaDomestica> utenze = utenzeDomestiche.getUtenza();

			if (utenze != null && !utenze.isEmpty()) {
				UtenzaDomestica utenzaDomestica = utenze.get(0);
				String identificativoUtenza = utenzaDomestica.getIdentificativoUtenza();
				if (identificativoUtenza != null) {

					idContribuente = getIdContribuenteFromIdentificativoUtenza(identificativoUtenza);

					datiUtenzaCessazioneType.setIdUtenza(getIdUtenzaFromIdentificativoUtenza(identificativoUtenza));
					datiUtenzaCessazioneType.setDataCessazione(getDateString(cessazione.getDataDecorrenza()));
					datiUtenzaCessazioneType.setDataVariazione(getDataVariazioneUtenzaFromIdentificativoUtenza(identificativoUtenza));
				}
				DatiMotivoCessazioneDomesticaType motivoCessazioneUtenzaDomesticaType = new DatiMotivoCessazioneDomesticaType();

				Codifica codiceAltroMotivo = utenzeDomestiche.getAltroMotivo();
				CessazionePerCoabitazione cessazionePerCoabitazione = utenzeDomestiche.getCessazionePerCoabitazione();
				Calendar cessazionePerDecesso = utenzeDomestiche.getCessazionePerDecesso();
				Indirizzo cessazionePerEmigrazioneAltroComune = utenzeDomestiche.getCessazionePerEmigrazioneAltroComune();
				if (codiceAltroMotivo != null) {
					String codice = codiceAltroMotivo.getCodice();
					if (codice.equals(MOTIVO_CESSAZIONE_UTENZA_DOMESTICA_PER_IMMOBILE_CONCESSO_IN_LOCAZIONE)) {
						motivoCessazioneUtenzaDomesticaType.setCessazionePerImmobileConcessoInLocazione(new MotivoCessazionePerImmobileConcessoInLocazioneType());
					}
					else if (codice.equals(MOTIVO_CESSAZIONE_UTENZA_DOMESTICA_PER_IMMOBILE_RESTITUITO_PER_FINE_LOCAZIONE)) {
						motivoCessazioneUtenzaDomesticaType.setCessazionePerImmobileRestituitoPerFineLocazione(new MotivoCessazionePerImmobileRestituitoPerFineLocazioneType());
					}
					else if (codice.equals(MOTIVO_CESSAZIONE_UTENZA_DOMESTICA_PER_IMMOBILE_VENDUTO)) {
						motivoCessazioneUtenzaDomesticaType.setCessazionePerImmobileVenduto(new MotivoCessazionePerImmobileVendutoType());
					}
					else if (codice.equals(MOTIVO_CESSAZIONE_UTENZA_DOMESTICA_PER_IMMOBILE_VUOTO_SENZA_FORNITURE)) {
						motivoCessazioneUtenzaDomesticaType.setCessazionePerImmobileVuotoSenzaForniture(new MotivoCessazionePerImmobileVuotoSenzaFornitureType());
					}
					else {
						motivoCessazioneUtenzaDomesticaType.setAltroMotivo(codiceAltroMotivo.getDescrizione());
					}
				}
				else if (cessazionePerCoabitazione != null) {
					MotivoCessazionePerCoabitazioneType motivazione = new MotivoCessazionePerCoabitazioneType();
					motivazione.setCodiceFiscaleCoabitante(cessazionePerCoabitazione.getCodiceFiscaleCoabitante());
					motivazione.setUbicazione(getIndirizzoComuneType(cessazionePerCoabitazione.getUbicazione()));
					motivoCessazioneUtenzaDomesticaType.setCessazionePerCoabitazione(motivazione);
				}
				else if (cessazionePerDecesso != null) {
					motivoCessazioneUtenzaDomesticaType.setCessazionePerDecesso(getDateString(cessazionePerDecesso));
				}
				else if (cessazionePerEmigrazioneAltroComune != null) {
					motivoCessazioneUtenzaDomesticaType.setCessazionePerEmigrazioneAltroComune(getIndirizzoType(cessazionePerEmigrazioneAltroComune));
				}

				datiUtenzaCessazioneType.setMotivoCessazioneUtenzaDomestica(motivoCessazioneUtenzaDomesticaType);
			}
		}
		datiCessazioneType.setDatiUtenza(datiUtenzaCessazioneType);

		// datiRichiestaRimborsoType
		Rimborso rimborso = cessazione.getRimborso();
		if (rimborso != null) {
			DatiRichiestaRimborsoType datiRichiestaRimborsoType = new DatiRichiestaRimborsoType();
			Codifica modalitaRimborso = rimborso.getModalitaRimborso();
			if (modalitaRimborso != null) {
				datiRichiestaRimborsoType.setModalitaRimborso(modalitaRimborso.getCodice());
			}
			if (rimborso.getCodIban() != null) {
				datiRichiestaRimborsoType.setCodIbanAccredito(rimborso.getCodIban());
			}
			datiCessazioneType.setDatiRichiestaRimborso(datiRichiestaRimborsoType);
		}

		datiCessazioneType.setDelegato(cessazione.getDelegato());
		datiCessazioneType.setDocIdentita(cessazione.getDocumentoIdentita());
		datiCessazioneType.setNote(cessazione.getNote());
		if (cessazione.getTipoUtenza() != null) {
			datiCessazioneType.setTipoUtenza(TipoUtenzaType.fromValue(cessazione.getTipoUtenza().getCodice()));
		}
		cessazioneType.setDatiCessazione(datiCessazioneType);

		// datiContribuenteVariazioneType
		Dichiarante dichiarante = cessazione.getDichiarante();
		if (dichiarante != null) {
			DatiContribuenteVariazioneType datiContribuenteType = new DatiContribuenteVariazioneType();
			datiContribuenteType.setIdContribuente(idContribuente);

			Indirizzo domicilio = dichiarante.getDomicilio();
			if (domicilio != null) {
				IndirizzoType datiDomicilioType = getIndirizzoType(domicilio);
				datiContribuenteType.setDatiDomicilio(datiDomicilioType);
			}
			Rappresentante rappresentante = dichiarante.getRappresentante();
			if (rappresentante != null) {
				DatiRappresentanteType datiRappresentanteType = getRappresentanteType(rappresentante);
				datiContribuenteType.setDatiRappresentante(datiRappresentanteType);
			}
			Recapito recapito = dichiarante.getRecapito();
			if (recapito != null) {
				DatiRecapitoType datiRecapitoType = getRecapitoType(recapito);
				datiContribuenteType.setDatiRecapito(datiRecapitoType);
			}
			datiContribuenteType.setFax(dichiarante.getFax());
			datiContribuenteType.setIndirizzoPEC(dichiarante.getPec());
			datiContribuenteType.setTelefono(dichiarante.getTelefono());

			PersonaFisica personaFisica = dichiarante.getPersonaFisica();
			if (personaFisica != null) {
				VariazioneAnagraficaPersonaFisicaType variazioneAnagraficaPersonaFisicaType = getVariazioneAnagraficaPersonaFisica(personaFisica);
				datiContribuenteType.setVariazioneAnagraficaPersonaFisica(variazioneAnagraficaPersonaFisicaType);
			}
			PersonaGiuridica personaGiuridica = dichiarante.getPersonaGiuridica();
			if (personaGiuridica != null) {
				VariazioneAnagraficaPersonaGiuridicaType variazioneAnagraficaPersonaGiuridicaType = getVariazioneAnagraficaPersonaGiuridica(personaGiuridica);
				datiContribuenteType.setVariazioneAnagraficaPersonaGiuridica(variazioneAnagraficaPersonaGiuridicaType);
			}
			datiCessazioneType.setDatiContribuente(datiContribuenteType);
		}

		TaresServicePortType taresServicePort = getTaresServicePort(serviziGitriMap.get(IDENTIFICATIVO_SERVIZIO_CESSAZIONE));

		SetCessazioneResponseType setCessazione = taresServicePort.setCessazione(cessazioneType);

		return setCessazione.getReturn();

	}

	/**
	 * Chiama GITRI per effettuare una richiesta di agevolazione.
	 *
	 * @param agevolazione
	 * @param numProtocollo
	 * @param dataProtocollo
	 * @param allegatiString
	 * @return
	 * @throws Exception
	 */
	private CommonResponseType getAgevolazioneResponse(Agevolazione agevolazione, String numProtocollo, String dataProtocollo, String allegatiString) throws Exception {

		int idContribuente = 0; // valorizzato dall'utenza

		ElencoUtenze elencoUtenzeType = new ElencoUtenze();
		Utenza utenze = agevolazione.getUtenza();

		if (utenze != null) {
			List<DatiUtenzaRequestType> datiUtenzaRequestTypes = elencoUtenzeType.getUtenza();
			for (int i = 0; i < utenze.getUtenzaDomestica().size(); i++) {
				DatiUtenzaRequestType datiUtenzaRequestType = new DatiUtenzaRequestType();
				UtenzaDomestica utenzaDomestica = utenze.getUtenzaDomestica().get(i);
				String identificativoUtenza = utenzaDomestica.getIdentificativoUtenza();
				if (identificativoUtenza != null) {

					idContribuente = getIdContribuenteFromIdentificativoUtenza(identificativoUtenza);

					datiUtenzaRequestType.setIdentificativoUtenza(getIdUtenzaFromIdentificativoUtenza(identificativoUtenza));
					datiUtenzaRequestType.setDataVariazioneUtenza(getDataVariazioneUtenzaFromIdentificativoUtenza(identificativoUtenza));
					datiUtenzaRequestType.setDataInizioUtenza(getDataInizioUtenzaFromIdentificativoUtenza(identificativoUtenza));
					// prima posizione sempre per l'abitazione principale
					datiUtenzaRequestType.setTipoUtenza(i == 0 ? "1" : "2");
				}
				datiUtenzaRequestTypes.add(datiUtenzaRequestType);
			}

		}

		RichiestaAgevolazioneRequestType datiRichiesta = new RichiestaAgevolazioneRequestType();
		datiRichiesta.setIdentificativoContribuente(idContribuente);
		datiRichiesta.setNumeroProtocollo(numProtocollo);
		datiRichiesta.setDataProtocollo(dataProtocollo);
		datiRichiesta.setElencoUtenze(elencoUtenzeType);

		Calendar data = agevolazione.getData();

		CAF caf = agevolazione.getCaf();

		ModelloISEE attestazioneISEE = agevolazione.getAttestazioneISEE();
		if (attestazioneISEE != null) {
			DatiModelloISEEType datiModelloISEEType = new DatiModelloISEEType();
			datiModelloISEEType.setCodiceFiscaleDichiarante(attestazioneISEE.getCodiceFiscaleDichiarante());
			datiModelloISEEType.setDataAttestazione(getDateString(attestazioneISEE.getDataAttestazione()));
			datiModelloISEEType.setDataScadenza(getDateString(attestazioneISEE.getDataScadenza()));
			datiModelloISEEType.setValoreISE(getStringByBigDecimal(attestazioneISEE.getValoreISE()));
			datiModelloISEEType.setScalaEquivalenza(getStringByBigDecimal(attestazioneISEE.getScalaEquivalenza()));
			datiModelloISEEType.setValoreISEE(getStringByBigDecimal(attestazioneISEE.getValoreISEE()));
			NucleoFamiliare nucleoFamiliare = attestazioneISEE.getNucleoFamiliare();
			if (nucleoFamiliare != null) {
				DatiNucleoFamiliareType nucleoFamiliareType = new DatiNucleoFamiliareType();
				nucleoFamiliareType.setNumComponentiNucleoFamiliare((short) nucleoFamiliare.getNumComponentiNucleoFamiliare());
				nucleoFamiliareType.setNumSoggettiHandicap((short) nucleoFamiliare.getNumSoggettiHandicap());
				nucleoFamiliareType.setPresenzaFigliAttivitaGenitori(nucleoFamiliare.isPresenzaFigliAttivitaGenitori());
				nucleoFamiliareType.setPresenzaFigliUnGenitore(nucleoFamiliare.isPresenzaFigliUnGenitore());
				datiModelloISEEType.setDatiNucleoFamiliare(nucleoFamiliareType);
			}

			// dati patrimonio
			Patrimonio patrimonio = attestazioneISEE.getPatrimonio();
			if (patrimonio != null) {
				DatiPatrimonioType datiPatrimonioType = new DatiPatrimonioType();
				datiPatrimonioType.setDC(getStringByBigDecimal(patrimonio.getDC()));
				datiPatrimonioType.setDPI(getStringByBigDecimal(patrimonio.getDPI()));
				datiPatrimonioType.setDPM(getStringByBigDecimal(patrimonio.getDPM()));
				datiPatrimonioType.setPAG(getStringByBigDecimal(patrimonio.getPAG()));
				datiPatrimonioType.setPIM(getStringByBigDecimal(patrimonio.getPIM()));
				datiPatrimonioType.setPMO(getStringByBigDecimal(patrimonio.getPMO()));
				datiPatrimonioType.setRC(getStringByBigDecimal(patrimonio.getRC()));
				datiPatrimonioType.setRCPAG(getStringByBigDecimal(patrimonio.getRCPAG()));
				datiPatrimonioType.setRPM(getStringByBigDecimal(patrimonio.getRPM()));
				datiModelloISEEType.setDatiPatrimonio(datiPatrimonioType);
			}
			datiRichiesta.setAttestazioneISEE(datiModelloISEEType);
		}
		datiRichiesta.setCodiceAgevolazione(agevolazione.getCodiceAgevolazione().getCodice());
		if (caf != null) {
			datiRichiesta.setNominativoCAF(caf.getNominativo());
			if (caf.getCodiceFiscale() != null && !caf.getCodiceFiscale().isEmpty()) {
				datiRichiesta.setCodiceFiscaleCAF(caf.getCodiceFiscale());
			}
			if (caf.getPartitaIVA() != null && !caf.getPartitaIVA().isEmpty()) {
				datiRichiesta.setPartitaIVACAF(caf.getPartitaIVA());
			}
		}
		datiRichiesta.setDataDecorrenza(getDateString(agevolazione.getDataDecorrenza()));
		datiRichiesta.setDataPresentazioneDomanda(getDateString(data));

		if (agevolazione.isPresaVisione()) {
			datiRichiesta.setPresaVisione("1");
		}

		datiRichiesta.setPresenzaDocumentoIdentitaAllegato(agevolazione.getDocumentoIdentitaAllegato());
		datiRichiesta.setPresenzaDocumentoIdentitaDelegatoAllegato(agevolazione.getDocumentoIdentitaDelegatoAllegato());

		DatiAutorizzazioneType datiAutorizzazione = getDatiAutorizzazioneType(IDENTIFICATIVO_SERVIZIO_AGEVOLAZIONE);

		DatiComuniType datiIdentificativi = new DatiComuniType();
		datiIdentificativi.setIdentificativoContribuente(idContribuente);

		Dichiarante dichiarante = agevolazione.getDichiarante();
		if (dichiarante != null) {
			datiIdentificativi.setIndirizzoPEC(dichiarante.getPec());
			// if (dichiarante.getPersonaFisica() != null) {
			// datiIdentificativi.setCodiceFiscaleContribuente(dichiarante.getPersonaFisica().getCodiceFiscale());
			// }
			// if (dichiarante.getPersonaGiuridica() != null) {
			// datiIdentificativi.setCodiceFiscaleContribuente(dichiarante.getPersonaGiuridica().getCodiceFiscale());
			// }
		}
		if (caf != null) {
			datiRichiesta.setNominativoCAF(caf.getNominativo());
			if (caf.getCodiceFiscale() != null && !caf.getCodiceFiscale().isEmpty()) {
				datiIdentificativi.setCodiceFiscaleCAF(caf.getCodiceFiscale());
			}
			if (caf.getPartitaIVA() != null && !caf.getPartitaIVA().isEmpty()) {
				datiIdentificativi.setPartitaIVACAF(caf.getPartitaIVA());
			}

		}

		TaresServicePortType taresServicePort = getTaresServicePort(serviziGitriMap.get(IDENTIFICATIVO_SERVIZIO_AGEVOLAZIONE));
		return taresServicePort.setAgevolazioniCAF(datiAutorizzazione, datiIdentificativi, datiRichiesta);

	}

	private DatiAutorizzazioneType getDatiAutorizzazioneType(String identificativoServizio) throws NoSuchAlgorithmException {

		Calendar calendar = Calendar.getInstance();
		String dataRichiesta = getDateString(calendar);
		String oraRichiesta = getHourString(calendar);

		Date time = calendar.getTime();
		String timestamp = credenzialiDateFormat.format(time);
		String password = identificativoUtente + "#" + passwordUtente + "@" + timestamp;

		// Dati accesso al servizio
		DatiAutorizzazioneType datiAutorizzazione = new DatiAutorizzazioneType();
		datiAutorizzazione.setDataRichiesta(dataRichiesta);
		datiAutorizzazione.setIdentificativoUtente(identificativoUtente);
		datiAutorizzazione.setOraRichiesta(oraRichiesta);
		datiAutorizzazione.setPassword(generateHashString(password));

		datiAutorizzazione.setIdentificativoServizio(identificativoServizio);

		String idRichiesta = identificativoUtente + "#" + serviziGitriMap.get(identificativoServizio) + "@" + timestamp;
		datiAutorizzazione.setIdRichiesta(generateHashString(idRichiesta));

		return datiAutorizzazione;

	}

	/**
	 * @param idPratica
	 * @param protocolloRisposta
	 * @param codiceFiscaleDichiarante
	 * @param documenti
	 * @param note
	 * @param richiesta
	 * @throws Exception
	 */
	private void sendMail(String idPratica, ProtocolloRisposta protocolloRisposta, String codiceFiscaleDichiarante, List<Documento> documenti, String note,
			DichiarazioneTassaRifiutiInputRichiesta richiesta) throws Exception {

		String[] emailAddressesDichiarazioniTari = emailAddressDichiarazioniTari.split(",");
		if (emailAddressesDichiarazioniTari != null) {
			for (String email : emailAddressesDichiarazioniTari) {
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
				if (richiesta.getAgevolazione() != null) {
					subject = AGEVOLAZIONE_SUBJECT + " - " + codiceFiscaleDichiarante;
				}
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
					testo = "Note dichiarazione: <br>";
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

	private String getStringByBigDecimal(BigDecimal bigDecimal) {
		if (bigDecimal != null) {
			bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
			String res = bigDecimal.toPlainString().replace(".", ",");
			return res;
		}
		return null;
	}

	/**
	 * Crea la stringa con i titoli dei documenti allegati.
	 *
	 * @param richiesta
	 * @return
	 */
	private String getAllegati(DichiarazioneTassaRifiutiInputRichiesta richiesta) {

		StringBuilder stringBuilder = new StringBuilder();

		List<Documento> documenti = richiesta.getDocumento();
		if (documenti != null) {
			for (int i = 0; i < documenti.size(); i++) {
				Documento documento = documenti.get(i);
				if (!documento.isPrincipale()) {
					stringBuilder.append(documento.getTitolo());
					if (i != documenti.size() - 1) {
						stringBuilder.append(", ");
					}
				}
			}
		}

		return stringBuilder.toString();
	}

	/**
	 * @param richiesta
	 * @return
	 */
	private ProtocolloRichiesta getProtocolloRequest(DichiarazioneTassaRifiutiInputRichiesta richiesta, String codiceFiscale) {

		ProtocolloRichiesta protocolloRichiesta = new ProtocolloRichiesta();
		protocolloRichiesta.setAmministrazione(amministrazioneProtocollo);
		protocolloRichiesta.setAoo(aooProtocollo);
		List<it.osapulie.pdds.client.protocollo.Documento> documentiProtocollo = new ArrayList<it.osapulie.pdds.client.protocollo.Documento>();
		List<Documento> documenti = richiesta.getDocumento();
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
		String oggetto = DICHIARAZIONE_SUBJECT + " - C.F. Cittadino: " + codiceFiscale;
		if (richiesta.getAgevolazione() != null) {
			oggetto = AGEVOLAZIONE_SUBJECT + " - C.F. Cittadino: " + codiceFiscale;
		}
		protocolloRichiesta.setOggetto(oggetto);

		return protocolloRichiesta;
	}

	/**
	 * @param personaGiuridica
	 * @return
	 */
	private VariazioneAnagraficaPersonaGiuridicaType getVariazioneAnagraficaPersonaGiuridica(PersonaGiuridica personaGiuridica) {
		VariazioneAnagraficaPersonaGiuridicaType variazioneAnagraficaPersonaGiuridica = new VariazioneAnagraficaPersonaGiuridicaType();
		variazioneAnagraficaPersonaGiuridica.setCodiceFiscale(personaGiuridica.getCodiceFiscale());
		variazioneAnagraficaPersonaGiuridica.setDenominazione(personaGiuridica.getRagioneDenominazioneSociale());
		variazioneAnagraficaPersonaGiuridica.setPartitaIVA(personaGiuridica.getPartitaIva());
		return variazioneAnagraficaPersonaGiuridica;
	}

	/**
	 * @param personaFisica
	 * @return
	 */
	private VariazioneAnagraficaPersonaFisicaType getVariazioneAnagraficaPersonaFisica(PersonaFisica personaFisica) {

		VariazioneAnagraficaPersonaFisicaType variazioneAnagraficaPersonaFisica = new VariazioneAnagraficaPersonaFisicaType();
		variazioneAnagraficaPersonaFisica.setCodiceFiscale(personaFisica.getCodiceFiscale());
		variazioneAnagraficaPersonaFisica.setCognome(personaFisica.getCognome());
		// Non c'è il comune estero di nascita
		variazioneAnagraficaPersonaFisica.setComuneEsteroNascita(null);
		Codifica statoEsteroNascita = personaFisica.getStatoEsteroNascita();
		if (statoEsteroNascita != null) {
			variazioneAnagraficaPersonaFisica.setComuneNascita(statoEsteroNascita.getCodice());
			variazioneAnagraficaPersonaFisica.setComuneEsteroNascita(statoEsteroNascita.getDescrizione());
		}
		else {
			variazioneAnagraficaPersonaFisica.setComuneNascita(personaFisica.getComuneNascita().getCodice());
		}

		variazioneAnagraficaPersonaFisica.setDataNascita(getDateString(personaFisica.getDataNascita()));
		variazioneAnagraficaPersonaFisica.setNome(personaFisica.getNome());
		variazioneAnagraficaPersonaFisica.setPartitaIVA(personaFisica.getPartitaIVA());
		Codifica provinciaNascita = personaFisica.getProvinciaNascita();
		if (provinciaNascita != null) {
			variazioneAnagraficaPersonaFisica.setProvinciaNascita(provinciaNascita.getCodice());
		}
		Codifica sesso = personaFisica.getSesso();
		if (sesso != null) {
			variazioneAnagraficaPersonaFisica.setSesso(SessoType.fromValue(sesso.getCodice()));
		}
		return variazioneAnagraficaPersonaFisica;
	}

	/**
	 * Restituisce l'idContribuente in prima posizione nella concatenazione
	 * idContribuente|idUtenza|dataVariazioneUtenza|dataInizioUtenza
	 *
	 * @param identificativoContribuente
	 * @return int
	 */
	private int getIdContribuenteFromIdentificativoUtenza(String identificativoUtenza) {
		if (identificativoUtenza != null && !identificativoUtenza.isEmpty()) {
			return Integer.valueOf(identificativoUtenza.split("\\|")[0]);
		}
		else {
			return 0; // 0 out of range [1-999]
		}
	}

	/**
	 * Restituisce l'idUtenza in seconda posizione nella concatenazione
	 * idContribuente|idUtenza|dataVariazioneUtenza|dataInizioUtenza
	 *
	 * @param identificativoContribuente
	 * @return int
	 */
	private int getIdUtenzaFromIdentificativoUtenza(String identificativoUtenza) {
		return Integer.valueOf(identificativoUtenza.split("\\|")[1]);
	}

	/**
	 * Restituisce la dataVariazioneUtenza in terza posizione nella concatenazione
	 * idContribuente|idUtenza|dataVariazioneUtenza|dataInizioUtenza
	 *
	 * @param identificativoContribuente
	 * @return String
	 */
	private String getDataVariazioneUtenzaFromIdentificativoUtenza(String identificativoUtenza) {

		String dataVariazione = identificativoUtenza.split("\\|")[2];
		return getDateString(getData(dataVariazione, "ddMMyyyy"));
	}

	/**
	 * Restituisce la dataInizioUtenza in quarta posizione nella concatenazione
	 * idContribuente|idUtenza|dataVariazioneUtenza|dataInizioUtenza
	 *
	 * @param identificativoContribuente
	 * @return String
	 */
	private String getDataInizioUtenzaFromIdentificativoUtenza(String identificativoUtenza) {

		String dataInizio = identificativoUtenza.split("\\|")[3];
		return getDateString(getData(dataInizio, "ddMMyyyy"));
	}

	// private int getIdContribuenteFromStatoPraticheWS(DatiTracciamentoRequestType
	// datiTracciamento, String identificativoContribuente) throws Exception {
	//
	// Calendar calendar = Calendar.getInstance();
	// String dataRichiesta = getDateString(calendar);
	// String oraRichiesta = getHourString(calendar);
	//
	// String timestamp = credenzialiDateFormat.format(Calendar.getInstance().getTime());
	// String password = identificativoUtente + "#" + passwordUtente + "@" + timestamp;
	//
	// // Dati accesso al servizio
	// DatiAutorizzazioneType datiAutorizzazione = new DatiAutorizzazioneType();
	// datiAutorizzazione.setDataRichiesta(dataRichiesta);
	// datiAutorizzazione.setIdentificativoUtente(identificativoUtente);
	// datiAutorizzazione.setOraRichiesta(oraRichiesta);
	// datiAutorizzazione.setPassword(generateHashString(password));
	// datiAutorizzazione.setIdentificativoServizio(IDENTIFICATIVO_SERVIZIO_GETUTENZESTATOPRATICHE);
	// String idRichiesta = identificativoUtente + "#" +
	// serviziGitriMap.get(IDENTIFICATIVO_SERVIZIO_GETUTENZESTATOPRATICHE) + "@" + timestamp;
	// datiAutorizzazione.setIdRichiesta(generateHashString(idRichiesta));
	//
	// TaresServicePortType taresServicePort =
	// getTaresServicePort(serviziGitriMap.get(IDENTIFICATIVO_SERVIZIO_GETUTENZESTATOPRATICHE));
	// GetUtenzeStatoPraticheRequestType parameters = new GetUtenzeStatoPraticheRequestType();
	//
	// parameters.setDatiAutorizzazione(datiAutorizzazione);
	// parameters.setDatiTracciamento(datiTracciamento);
	// DatiIdentificativiRequestType datiIdentificativiRequest = new
	// DatiIdentificativiRequestType();
	//
	// datiIdentificativiRequest.setCodiceFiscale(identificativoContribuente);
	//
	// parameters.setDatiIdentificativi(datiIdentificativiRequest);
	// GetUtenzeStatoPraticheResponseType utenzeStatoPratiche =
	// taresServicePort.getUtenzeStatoPratiche(parameters);
	//
	// DatiUtenzeStatoPraticheResponseType risposta = utenzeStatoPratiche.getReturn();
	//
	// if (risposta != null) {
	// int code = risposta.getCode();
	// // Errore
	// if (code != 0) {
	// String messaggioErrore = "Errore durante la comunicazione con GITRI: error code: " + code +
	// ", messaggio di errore: " + risposta.getMessaggio();
	// log.error(messaggioErrore);
	// Errore errore = new Errore();
	// errore.setCodice(4);
	// errore.setDescrizione(messaggioErrore);
	// throw new Exception(messaggioErrore);
	// }
	// else {
	// return risposta.getDatiContribuente().getIdContribuente();
	// }
	// }
	// else {
	// throw new Exception("Errore durante la comunicazione con GITRI: Risposta nulla.");
	// }
	// }

	/**
	 *
	 * @param data
	 * @param format
	 * @return
	 */
	public static Date getData(String data, String format) {
		if (data == null) {
			return null;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			sdf.setLenient(false);
			Date dataDate = sdf.parse(data);
			return dataDate;
		}
		catch (ParseException pe) {
			return null;
		}
	}

	/**
	 *
	 * @param data
	 * @return
	 */
	private String getDateString(Calendar data) {
		String dateString = null;
		if (data != null) {
			dateString = dateFormat.format(data.getTime());
		}

		return dateString;
	}

	/**
	 *
	 * @param data
	 * @return
	 */
	private String getDateString(Date data) {
		String dateString = null;
		if (data != null) {
			dateString = dateFormat.format(data);
		}

		return dateString;
	}

	/**
	 *
	 * @param data
	 * @return
	 */
	private String getHourString(Calendar data) {
		String dateString = null;
		if (data != null) {
			dateString = hourFormat.format(data.getTime());
		}

		return dateString;
	}

	/**
	 * @param string
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	private String generateHashString(String string) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		messageDigest.update(string.getBytes());
		byte byteData[] = messageDigest.digest();

		// convert the byte to hex format method 1
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}

		return sb.toString();
	}

	/**
	 *
	 * @param operation
	 * @return
	 */
	private TaresServicePortType getTaresServicePort(String operation) {

		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		URL wsdlLocation = classloader.getResource(WSDL_GITRI);
		TaresService taresService = new TaresService(wsdlLocation);

		TaresServicePortType taresServicePort = taresService.getTaresServicePort();
		BindingProvider provider = (BindingProvider) taresServicePort;

		String url = webserviceUrl;
		if (webserviceUrl.contains("esb")) {
			url += "/" + operation;
		}

		provider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);
		return taresServicePort;
	}

	/**
	 * @param datiCatastali
	 * @return
	 */
	private DatiCatastaliNewType getDatiCatastaliType(DatiCatastali datiCatastali) {
		DatiCatastaliNewType datiCatastaliType = null;
		if (datiCatastali != null) {
			datiCatastaliType = new DatiCatastaliNewType();
			datiCatastaliType.setFoglio(String.valueOf(datiCatastali.getFoglio()));
			datiCatastaliType.setParticella(String.valueOf(datiCatastali.getParticella()));
			datiCatastaliType.setSezione(datiCatastali.getSezione());
			if (datiCatastali.getSubalterno() != null) {
				datiCatastaliType.setSubalterno(String.valueOf(datiCatastali.getSubalterno()));
			}
		}
		return datiCatastaliType;
	}

	/**
	 * @param recapito
	 * @return
	 */
	private DatiRecapitoType getRecapitoType(Recapito recapito) {

		DatiRecapitoType datiRecapitoType = null;
		if (recapito != null) {
			datiRecapitoType = new DatiRecapitoType();
			IndirizzoType recapitoType = getIndirizzoType(recapito);
			BeanUtils.copyProperties(recapitoType, datiRecapitoType);
			datiRecapitoType.setPresso(recapito.getPresso());
		}
		return datiRecapitoType;
	}

	/**
	 * @param rappresentante
	 * @return
	 */
	private DatiRappresentanteType getRappresentanteType(Rappresentante rappresentante) {

		DatiRappresentanteType datiRappresentante = null;

		if (rappresentante != null) {
			datiRappresentante = new DatiRappresentanteType();
			datiRappresentante.setCodiceFiscale(rappresentante.getCodiceFiscale());
			datiRappresentante.setCognome(rappresentante.getCognome());
			// Non esiste il comune estero nascita
			Codifica statoEsteroNascita = rappresentante.getStatoEsteroNascita();
			if (statoEsteroNascita != null) {
				datiRappresentante.setComuneNascita(statoEsteroNascita.getCodice());
				datiRappresentante.setComuneEsteroNascita(statoEsteroNascita.getDescrizione());
			}
			else {
				datiRappresentante.setComuneNascita(rappresentante.getComuneNascita().getCodice());
			}
			datiRappresentante.setComuneEsteroNascita(null);
			datiRappresentante.setComuneNascita(rappresentante.getComuneNascita().getCodice());
			datiRappresentante.setDataNascita(getDateString(rappresentante.getDataNascita()));
			datiRappresentante.setFax(rappresentante.getFax());
			datiRappresentante.setIndirizzoPEC(rappresentante.getPec());
			datiRappresentante.setNome(rappresentante.getNome());
			datiRappresentante.setProvinciaNascita(rappresentante.getProvinciaNascita() != null ? rappresentante.getProvinciaNascita().getCodice() : null);
			datiRappresentante.setQualifica(rappresentante.getQualifica());
			datiRappresentante.setSesso(rappresentante.getSesso() != null ? SessoType.fromValue(rappresentante.getSesso().getCodice()) : null);
			datiRappresentante.setTelefono(rappresentante.getTelefono());
		}
		return datiRappresentante;
	}

	/**
	 * @param indirizzo
	 * @return
	 */
	private IndirizzoType getIndirizzoType(Indirizzo indirizzo) {

		IndirizzoType indirizzoType = null;

		if (indirizzo != null) {
			indirizzoType = new IndirizzoType();
			Codifica via = indirizzo.getVia();
			Civico civico = indirizzo.getCivico();

			if (via != null) {
				if (via.getCodice() != null && !via.getCodice().isEmpty()) {
					indirizzoType.setViaCodificata(via.getCodice() != null ? via.getCodice() : null);
				}
				else {
					indirizzoType.setViaNonCodificata(via.getDescrizione() != null ? via.getDescrizione() : null);
				}
			}
			if (civico != null) {
				indirizzoType.setCivico(civico.getNumero());
				indirizzoType.setEsponente(civico.getEsponente());
			}
			indirizzoType.setCap(indirizzo.getCap());
			indirizzoType.setFrazione(indirizzo.getLocalita());

			indirizzoType.setInterno(indirizzo.getInterno());
			indirizzoType.setPiano(indirizzo.getPiano());
			indirizzoType.setScala(indirizzo.getScala());

			Codifica comune = indirizzo.getComune();
			if (comune != null) {
				indirizzoType.setComune(comune.getCodice());
			}
			indirizzoType.setInterno(indirizzo.getInterno());
			indirizzoType.setPiano(indirizzo.getPiano());
			indirizzoType.setScala(indirizzo.getScala());
		}

		return indirizzoType;
	}

	/**
	 * @param indirizzo
	 * @return
	 */
	private IndirizzoComuneType getIndirizzoComuneType(Indirizzo indirizzo) {

		IndirizzoComuneType indirizzoComuneType = null;

		if (indirizzo != null) {
			indirizzoComuneType = new IndirizzoComuneType();
			Codifica via = indirizzo.getVia();
			Civico civico = indirizzo.getCivico();

			if (via != null) {
				indirizzoComuneType.setViaCodificata(via.getCodice());
			}
			if (civico != null) {
				indirizzoComuneType.setCivico(civico.getNumero());
				indirizzoComuneType.setEsponente(civico.getEsponente());
			}
			indirizzoComuneType.setCap(indirizzo.getCap());
			indirizzoComuneType.setFrazione(indirizzo.getLocalita());
			indirizzoComuneType.setInterno(indirizzo.getInterno());
			indirizzoComuneType.setPiano(indirizzo.getPiano());
			indirizzoComuneType.setScala(indirizzo.getScala());
			indirizzoComuneType.setSuffisso(null);
			indirizzoComuneType.setKm(null);
		}

		return indirizzoComuneType;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.pdds.service.AbstractResponse#getName()
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
	 * @return the emailAddressDichiarazioniTari
	 */
	public String getEmailAddressDichiarazioniTari() {
		return emailAddressDichiarazioniTari;
	}

	/**
	 * @param emailAddressDichiarazioniTari the emailAddressDichiarazioniTari to set
	 */
	public void setEmailAddressDichiarazioniTari(String emailAddressDichiarazioniTari) {
		this.emailAddressDichiarazioniTari = emailAddressDichiarazioniTari;
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
