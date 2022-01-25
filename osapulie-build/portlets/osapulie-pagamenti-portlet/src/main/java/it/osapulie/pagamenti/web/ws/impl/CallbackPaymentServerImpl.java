/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pagamenti.web.ws.impl;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.jws.WebService;
import javax.xml.datatype.DatatypeFactory;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.linksmt.pagamenti.adapter.domain.server.CallbackPaymentRequest;
import it.linksmt.pagamenti.adapter.domain.server.CallbackPaymentResponse;
import it.linksmt.pagamenti.adapter.exception.PaymentAdapterException;
import it.linksmt.pagamenti.adapter.ws.server.CallbackPaymentServer;
import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.dto.Fascicolo;
import it.osapulie.domain.fascicoloutente.RichiestaServizio;
import it.osapulie.domain.servizi.Servizio;
import it.osapulie.pagamenti.service.PagamentoServiziService;
import it.osapulie.pagamenti.service.TipoServizio;
import it.osapulie.pagamenti.utils.PortletConstants;
import it.osapulie.service.ComuneISAService;
import it.osapulie.service.FascicoloUtenteService;
import it.osapulie.service.ProfiloUtenteService;
import it.osapulie.service.ServizioService;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.tributi.web.ws.output.types.DatiPagamentoServiziRichiesta;
import it.osapulie.tributi.web.ws.output.types.DatiPagamentoServiziRisposta;

/**
 * Implementazione di default del WS per le chiamate di callback effettuate dal portale dei
 * pagamenti.
 *
 * @author Gianluca Pindinelli
 *
 */
@WebService(targetNamespace = "http://server.ws.adapter.pagamenti.linksmt.it/", serviceName = "paymentCallback", portName = "CallbackPaymentServer")
public class CallbackPaymentServerImpl implements CallbackPaymentServer {

	private static final String ESITO_OK = "OK";
	private static final String ESITO_KO = "KO";

	protected Logger log = LoggerFactory.getLogger(CallbackPaymentServerImpl.class.getName());

	@Inject
	private FascicoloUtenteService fascicoloService;

	@Inject
	private ProfiloUtenteService profiloUtenteService;

	@Inject
	private ServizioService servizioService;

	@Inject
	private PagamentoServiziService pagamentoServiziService;

	@Inject
	private ComuneISAService comuneISAService;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.linksmt.pagamenti.adapter.ws.server.CallbackPaymentServer#paymentDone(it.linksmt.pagamenti
	 * .adapter.domain.server.CallBackPaymentRequest)
	 */
	@Override
	public CallbackPaymentResponse paymentDone(CallbackPaymentRequest callbackPaymentRequest) throws PaymentAdapterException {

		CallbackPaymentResponse callbackPaymentResponse = new CallbackPaymentResponse();

		try {
			// Controllo esistenza utente
			ProfiloUtenteCittadino profiloUtenteCittadinoByCf = profiloUtenteService.getProfiloUtenteCittadinoByCf(callbackPaymentRequest.getIdFiscaleCliente());
			if (profiloUtenteCittadinoByCf == null) {
				callbackPaymentResponse.setEsito(ESITO_KO);
				callbackPaymentResponse.setMessaggioErrore("Utente non riconosciuto : " + callbackPaymentRequest.getIdFiscaleCliente());
				return callbackPaymentResponse;
			}

			// Controllo esistenza comune
			String identificativoOrganizzazione = callbackPaymentRequest.getIdentificativoOrganizzazione();
			ComuneISA comuneByCodiceIPA = comuneISAService.getComuneByCodiceIPA(identificativoOrganizzazione);
			if (comuneByCodiceIPA == null || (comuneByCodiceIPA != null && !comuneByCodiceIPA.getAttivo())) {
				callbackPaymentResponse.setEsito(ESITO_KO);
				callbackPaymentResponse.setMessaggioErrore("Comune non riconosciuto : " + identificativoOrganizzazione);
				return callbackPaymentResponse;
			}

			RichiestaServizio richiestaServizioFascicolUtente = fascicoloService.getRichiestaServizioFascicolUtente(profiloUtenteCittadinoByCf, callbackPaymentRequest.getIdentificativoPagamento());

			ObjectMapper mapper = new ObjectMapper();

			// Chiamata servizio di backend per informare l'ente del pagamento effettuato
			DatiPagamentoServiziRichiesta richiesta = new DatiPagamentoServiziRichiesta();

			richiesta.setCodiceFiscale(callbackPaymentRequest.getIdFiscaleCliente());
			richiesta.setCommissioni(callbackPaymentRequest.getImportoGateway());
			GregorianCalendar c = new GregorianCalendar();
			c.setTime(callbackPaymentRequest.getDataPagamento());
			richiesta.setDataPagamento(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
			richiesta.setIdentificativoCredito(callbackPaymentRequest.getIdentificativoCredito());
			richiesta.setIdentificativoPagamento(callbackPaymentRequest.getIdentificativoPagamento());
			richiesta.setImportoPagato(callbackPaymentRequest.getImportoTotale());

			String identificativoOperazione = null;
			try {
				DatiPagamentoServiziRisposta pagamento = pagamentoServiziService.pagamento(richiesta, comuneByCodiceIPA.getUriServizioGateway());
				if (pagamento != null) {
					identificativoOperazione = pagamento.getIdentificativoOperazione();

					Map<String, String> map = new LinkedHashMap<String, String>();
					map.put(PortletConstants.PAGAMENTO_RICEVUTA_PAGAMENTO_ORGANIZZAZIONE_JSON_KEY, identificativoOperazione);
					callbackPaymentResponse.setInfoAggiuntiveServizio(mapper.writeValueAsString(map));
				}
			}
			catch (Exception e) {
				log.error("paymentDone :: impossibile contattare il servizio '" + TipoServizio.PAGAMENTO_SERVIZIO + "' per l'ente " + comuneByCodiceIPA.getCodiceIstat() + " - " + e.getMessage(), e);
			}

			// Aggiornamento fascicolo utente
			if (richiestaServizioFascicolUtente != null) {
				String infoAggiuntive = richiestaServizioFascicolUtente.getInfoAggiuntive();
				TypeReference<HashMap<String, String>> typeRef = new TypeReference<HashMap<String, String>>() {
				};
				HashMap<String, String> mapByString = mapper.readValue(infoAggiuntive, typeRef);
				// Aggiornamento stato
				mapByString.put(PortletConstants.PAGAMENTO_STATO_JSON_KEY, PortletConstants.PAGAMENTO_STATO_PAGATO);

				// Aggiunta informazioni gateway
				String infoAggiuntivePagamento = callbackPaymentRequest.getInfoAggiuntive();
				if (infoAggiuntivePagamento != null) {
					HashMap<String, String> infoAggiuntivePagamentoMap = mapper.readValue(infoAggiuntivePagamento, typeRef);
					mapByString.putAll(infoAggiuntivePagamentoMap);
				}

				if (identificativoOperazione != null) {
					mapByString.put(PortletConstants.PAGAMENTO_RICEVUTA_PAGAMENTO_ORGANIZZAZIONE_JSON_KEY, identificativoOperazione);
				}

				richiestaServizioFascicolUtente.setInfoAggiuntive(new ObjectMapper().writeValueAsString(mapByString));
				// Salvataggio fascicolo
				fascicoloService.updateRichiestaServizio(richiestaServizioFascicolUtente);
			}
			// Creazione fascicolo
			else {

				Fascicolo fascicolo = new Fascicolo();

				Map<String, String> infoAggiuntiveMap = new LinkedHashMap<String, String>();
				infoAggiuntiveMap.put(PortletConstants.PAGAMENTO_ID_PAGAMENTO_JSON_KEY, callbackPaymentRequest.getIdentificativoPagamento());
				infoAggiuntiveMap.put(PortletConstants.PAGAMENTO_STATO_JSON_KEY, PortletConstants.PAGAMENTO_STATO_PAGATO);
				infoAggiuntiveMap.put(PortletConstants.PAGAMENTO_ID_CREDITO_JSON_KEY, callbackPaymentRequest.getIdentificativoCredito());
				if (identificativoOperazione != null) {
					infoAggiuntiveMap.put(PortletConstants.PAGAMENTO_RICEVUTA_PAGAMENTO_ORGANIZZAZIONE_JSON_KEY, identificativoOperazione);
				}

				fascicolo.setInfoAggiuntive(new ObjectMapper().writeValueAsString(infoAggiuntiveMap));
				fascicolo.setIdProfiloUtente(profiloUtenteCittadinoByCf);

				String identificativoServizio = callbackPaymentRequest.getIdentificativoServizio();
				fascicolo.setCodiceServizio(identificativoServizio);
				Servizio servizioByCodiceServizio = servizioService.getServizioByCodiceServizio(identificativoServizio);
				fascicolo.setServizio(servizioByCodiceServizio.getNomeServizio());

				UserPreferences userPreferences = new UserPreferences();
				userPreferences.setCodiceIstatComune(callbackPaymentRequest.getIdentificativoOrganizzazione());
				fascicolo.setUserPreferences(userPreferences);
				fascicolo.setRicercabileDaComune(true);
				fascicolo.setNumeroProtocollo(null);
				fascicolo.setChecksum(null);
				fascicolo.setInfoAggiuntive(mapper.writeValueAsString(infoAggiuntiveMap));
				// Salvataggio fascicolo
				fascicoloService.saveNuovaRichiesta(fascicolo);

			}

			callbackPaymentResponse.setEsito(ESITO_OK);
			callbackPaymentResponse.setIdentificativoPagamento(callbackPaymentRequest.getIdentificativoPagamento());

		}
		catch (Exception e) {
			log.error("paymentDone :: " + e.getMessage(), e);
			String messaggioErrore = e.getMessage();
			callbackPaymentResponse.setEsito(ESITO_KO);
			callbackPaymentResponse.setMessaggioErrore(messaggioErrore);
		}

		return callbackPaymentResponse;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.linksmt.pagamenti.adapter.ws.server.CallbackPaymentServer#paymentBefore(it.linksmt.
	 * pagamenti .adapter.domain.server.CallbackPaymentRequest)
	 */
	@Override
	public CallbackPaymentResponse paymentBefore(CallbackPaymentRequest callbackPaymentRequest) throws PaymentAdapterException {

		// Controllo esistenza utente
		String idFiscaleCliente = callbackPaymentRequest.getIdFiscaleCliente();

		CallbackPaymentResponse response = new CallbackPaymentResponse();

		ProfiloUtenteCittadino profiloUtenteCittadinoByCf = profiloUtenteService.getProfiloUtenteCittadinoByCf(idFiscaleCliente);
		if (profiloUtenteCittadinoByCf == null) {
			response.setEsito(ESITO_KO);
			response.setMessaggioErrore("Utente non riconosciuto : " + idFiscaleCliente);
			return response;
		}

		// Controllo esistenza comune
		String identificativoOrganizzazione = callbackPaymentRequest.getIdentificativoOrganizzazione();
		ComuneISA comuneByCodiceipa = comuneISAService.getComuneByCodiceIPA(identificativoOrganizzazione);
		if (comuneByCodiceipa == null || (comuneByCodiceipa != null && !comuneByCodiceipa.getAttivo())) {
			response.setEsito(ESITO_KO);
			response.setMessaggioErrore("Comune non riconosciuto : " + identificativoOrganizzazione);
			return response;
		}

		response.setEsito(ESITO_OK);

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.linksmt.pagamenti.adapter.ws.server.CallbackPaymentServer#paymentRollback(it.linksmt.
	 * pagamenti .adapter.domain.server.CallbackPaymentRequest)
	 */
	@Override
	public CallbackPaymentResponse paymentRollback(CallbackPaymentRequest callbackPaymentRequest) throws PaymentAdapterException {
		// TODO Rollback della transazione
		String idFiscaleCliente = callbackPaymentRequest.getIdFiscaleCliente();

		CallbackPaymentResponse response = new CallbackPaymentResponse();

		response.setEsito(ESITO_OK);
		response.setMessaggioErrore("Utente non riconosciuto : " + idFiscaleCliente);

		return response;
	}

}
