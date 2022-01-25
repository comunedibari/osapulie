/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pagamenti.web.ws.impl;

import java.lang.reflect.Method;
import java.util.Properties;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.dto.Fascicolo;
import it.osapulie.domain.servizi.Servizio;
import it.osapulie.pagamenti.service.PosizioneTributariaService;
import it.osapulie.pagamenti.web.ws.VisuraPosizioneTributariaWS;
import it.osapulie.pagamenti.web.ws.domain.VisuraPosizioneTributariaRichiesta;
import it.osapulie.service.ComuneISAService;
import it.osapulie.service.FascicoloUtenteService;
import it.osapulie.service.ProfiloUtenteService;
import it.osapulie.service.ServizioService;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaPagamentoRichiesta.TipoTributo;
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaPagamentoRichiesta.TipoTributo.Tributo;
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaRisposta;
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaRisposta.Errore;

/**
 * Implementazione WS per la visura posizione tributaria.
 *
 * @author Gianluca Pindinelli
 *
 */
@WebService(endpointInterface = "it.osapulie.pagamenti.web.ws.VisuraPosizioneTributariaWS", serviceName = "getVisuraPosizioneTributaria")
public class VisuraPosizioneTributariaWSImpl implements VisuraPosizioneTributariaWS {

	protected Logger log = LoggerFactory.getLogger(VisuraPosizioneTributariaWSImpl.class.getName());

	@Inject
	private PosizioneTributariaService service;

	@Inject
	private ComuneISAService comuneISAService;

	@Inject
	private ProfiloUtenteService profiloUtenteService;

	@Inject
	private FascicoloUtenteService fascicoloService;

	@Inject
	private ServizioService servizioService;

	@Resource(name = "applicationProperties")
	private Properties applicationProperties;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.pagamenti.web.ws.VisuraPosizioneTributariaWS#getVisuraPosizioneTributariaRisposta
	 * (it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaRichiesta)
	 */
	@Override
	public VisuraPosizioneTributariaRisposta getVisuraPosizioneTributaria(final VisuraPosizioneTributariaRichiesta visuraPosizioneTributariaRichiesta) {

		log.debug("getVisuraPosizioneTributaria :: entering method");

		VisuraPosizioneTributariaRisposta richiediDatiVisuraPosizioneTributaria = new VisuraPosizioneTributariaRisposta();

		// Controllo campi obbligatori
		String errorMessage = checkCampi(visuraPosizioneTributariaRichiesta);

		if (errorMessage != null && !errorMessage.trim().equals("")) {
			Errore errore = new Errore();
			errore.setCodice(4);
			errore.setDescrizione(errorMessage);
			richiediDatiVisuraPosizioneTributaria.setErrore(errore);
			return richiediDatiVisuraPosizioneTributaria;
		}

		// Ricerca utente
		ProfiloUtenteCittadino profiloUtenteCittadinoByCf = profiloUtenteService.getProfiloUtenteCittadinoByCf(visuraPosizioneTributariaRichiesta.getCodiceFiscale());
		if (profiloUtenteCittadinoByCf == null) {
			log.error("getVisuraPosizioneTributaria :: Utenza non trovata : " + visuraPosizioneTributariaRichiesta.getCodiceFiscale());
			Errore errore = new Errore();
			errore.setCodice(4);
			errore.setDescrizione("Utenza non trovata : " + visuraPosizioneTributariaRichiesta.getCodiceFiscale());
			richiediDatiVisuraPosizioneTributaria.setErrore(errore);
			return richiediDatiVisuraPosizioneTributaria;
		}

		// Caricamento comune isa
		ComuneISA comuneByCodiceIstat = comuneISAService.getComuneByCodiceIstat(visuraPosizioneTributariaRichiesta.getCodiceIstatComune());
		if (comuneByCodiceIstat == null || (comuneByCodiceIstat != null && !comuneByCodiceIstat.getAttivo())) {
			log.error("getVisuraPosizioneTributaria :: Comune non trovato: " + visuraPosizioneTributariaRichiesta.getCodiceIstatComune());
			Errore errore = new Errore();
			errore.setCodice(4);
			errore.setDescrizione("Comune non trovato: " + visuraPosizioneTributariaRichiesta.getCodiceIstatComune());
			richiediDatiVisuraPosizioneTributaria.setErrore(errore);
			return richiediDatiVisuraPosizioneTributaria;
		}

		it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaPagamentoRichiesta richiesta = new it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaPagamentoRichiesta();
		richiesta.setCodiceFiscale(visuraPosizioneTributariaRichiesta.getCodiceFiscale());
		TipoTributo tipoTributo = new TipoTributo();

		Tributo tributo = new Tributo();

		// Reflection per scelta tributo
		String codiceServizio = applicationProperties.getProperty("pagamenti.servizio.tributo.match." + visuraPosizioneTributariaRichiesta.getCodiceServizio());
		if (codiceServizio == null || (codiceServizio != null && codiceServizio.trim().equals(""))) {
			log.error("getVisuraPosizioneTributaria :: Servizio non trovato : " + visuraPosizioneTributariaRichiesta.getCodiceServizio());
			Errore errore = new Errore();
			errore.setCodice(4);
			errore.setDescrizione("Servizio non trovato : " + visuraPosizioneTributariaRichiesta.getCodiceServizio());
			richiediDatiVisuraPosizioneTributaria.setErrore(errore);
			return richiediDatiVisuraPosizioneTributaria;
		}

		// Ricerca servizio (se attivo)
		Servizio servizioByCodiceServizio = servizioService.getServizioByCodiceServizio(visuraPosizioneTributariaRichiesta.getCodiceServizio());
		if (servizioByCodiceServizio == null) {
			log.error("getVisuraPosizioneTributaria :: Servizio non trovato : " + visuraPosizioneTributariaRichiesta.getCodiceServizio());
			Errore errore = new Errore();
			errore.setCodice(4);
			errore.setDescrizione("Servizio non trovato : " + visuraPosizioneTributariaRichiesta.getCodiceServizio());
			richiediDatiVisuraPosizioneTributaria.setErrore(errore);
			return richiediDatiVisuraPosizioneTributaria;
		}

		// Controllo utilizzo servizio da utente in base a tipologia autenticazione (da inviare
		// in input)
		// TODO verificare che il controllo seguente sia corretto
		if (servizioByCodiceServizio.isAutenticazioneForte() && !visuraPosizioneTributariaRichiesta.getAutenticatoForte()) {
			log.error("getVisuraPosizioneTributaria :: Il servizio richiede autenticazione forte : " + codiceServizio);
			Errore errore = new Errore();
			errore.setCodice(4);
			errore.setDescrizione("Il servizio richiede autenticazione forte : " + codiceServizio);
			richiediDatiVisuraPosizioneTributaria.setErrore(errore);
			return richiediDatiVisuraPosizioneTributaria;
		}

		Method method;
		try {
			method = tributo.getClass().getMethod("set" + codiceServizio, Object.class);
			method.invoke(tributo, codiceServizio);
		}
		catch (Exception e) {
			log.error("getVisuraPosizioneTributaria :: " + e.getMessage(), e);
			Errore errore = new Errore();
			errore.setCodice(4);
			errore.setDescrizione("Servizio non trovato");
			richiediDatiVisuraPosizioneTributaria.setErrore(errore);
			return richiediDatiVisuraPosizioneTributaria;
		}

		// Aggiunta entry nel fascicolo
		saveEntryFascicolo(profiloUtenteCittadinoByCf, servizioByCodiceServizio, comuneByCodiceIstat);

		tipoTributo.setTributo(tributo);
		richiesta.setTipoTributo(tipoTributo);
		richiesta.setIdCredito(visuraPosizioneTributariaRichiesta.getIdentificativoCredito());

		richiediDatiVisuraPosizioneTributaria = service.richiediDatiVisuraPosizioneTributaria(richiesta, comuneByCodiceIstat.getUriServizioGateway());

		return richiediDatiVisuraPosizioneTributaria;
	}

	/**
	 * @param profiloUtenteCittadinoByCf
	 * @param servizio
	 * @param comuneISA
	 *
	 */
	private void saveEntryFascicolo(ProfiloUtenteCittadino profiloUtenteCittadinoByCf, Servizio servizio, ComuneISA comuneISA) {

		Fascicolo fascicolo = new Fascicolo();

		fascicolo.setIdProfiloUtente(profiloUtenteCittadinoByCf);
		fascicolo.setServizio(servizio.getNomeServizio());
		UserPreferences userPreferencees = new UserPreferences();
		userPreferencees.setIdComuneIsa(comuneISA.getId());
		fascicolo.setUserPreferences(userPreferencees);
		fascicolo.setCodiceServizio(servizio.getCodiceServizio());
		fascicolo.setRicercabileDaComune(true);
		fascicolo.setNumeroProtocollo(null);
		fascicolo.setChecksum(null);
		fascicoloService.saveNuovaRichiesta(fascicolo);

	}

	/**
	 * @param visuraPosizioneTributariaRichiesta
	 * @return
	 */
	private String checkCampi(VisuraPosizioneTributariaRichiesta visuraPosizioneTributariaRichiesta) {

		StringBuilder errorMessage = new StringBuilder();
		if (visuraPosizioneTributariaRichiesta == null) {
			errorMessage.append("visuraPosizioneTributariaRichiesta nulla");
			return errorMessage.toString();
		}

		if (visuraPosizioneTributariaRichiesta.getCodiceFiscale() == null || visuraPosizioneTributariaRichiesta.getCodiceFiscale().equals("")) {
			errorMessage.append("Codice Fiscale non indicato\n");
		}
		if (visuraPosizioneTributariaRichiesta.getCodiceIstatComune() == null || visuraPosizioneTributariaRichiesta.getCodiceIstatComune().equals("")) {
			errorMessage.append("Codice ISTAT non indicato\n");
		}
		if (visuraPosizioneTributariaRichiesta.getCodiceServizio() == null || visuraPosizioneTributariaRichiesta.getCodiceServizio().equals("")) {
			errorMessage.append("Codice servizio non indicato\n");
		}
		if (visuraPosizioneTributariaRichiesta.getCodiceServizio() == null || visuraPosizioneTributariaRichiesta.getCodiceServizio().equals("")) {
			errorMessage.append("Codice servizio non indicato\n");
		}
		if (visuraPosizioneTributariaRichiesta.getIdentificativoCredito() == null || visuraPosizioneTributariaRichiesta.getIdentificativoCredito().equals("")) {
			errorMessage.append("Identificativo credito non indicato\n");
		}
		if (visuraPosizioneTributariaRichiesta.getAutenticatoForte() == null || visuraPosizioneTributariaRichiesta.getAutenticatoForte().equals("")) {
			errorMessage.append("Tipo autenticazione utente non indicato\n");
		}

		return errorMessage.toString();
	}
}
