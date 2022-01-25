/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.service.impl;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.xml.ws.BindingProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;

import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.fascicoloutente.FascicoloUtente;
import it.osapulie.domain.fascicoloutente.RichiestaServizio;
import it.osapulie.infrastructure.XMLHelper;
import it.osapulie.infrastructure.impl.DateUtils;
import it.osapulie.infrastructure.security.OSApuliePortletPreAuthenticatedDetails;
import it.osapulie.infrastructure.security.OSApulieUserDetails;
import it.osapulie.persistence.FascicoloUtenteRepository;
import it.osapulie.service.ServiceUnreachableException;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.web.ws.pddsintegration.PddsIntegration;

/**
 * Implementazione base per eventuali service classes che usano il {@link PddsIntegration} per
 * richiedere i servizi.
 *
 * @author Mario Scalas
 * @author Gianluca Pindinelli
 */
public class AbstractServiceImpl {

	protected Logger log = LoggerFactory.getLogger(getClass());

	@Inject
	protected PddsIntegration pddsIntegration;

	@Inject
	protected PddsIntegration pddsIntegrationInput;

	@Inject
	protected XMLHelper xmlHelper;

	@Inject
	private FascicoloUtenteRepository repositoryFascicolo;

	@Value("#{applicationProperties['pddsAdapter.connection.attempts']}")
	private Integer pddsAdapterConnectionAttempts;

	/**
	 * Protected c-tor to enforce usage only for derived classes.
	 */
	protected AbstractServiceImpl() {
		// Nothing
	}

	/**
	 * Metodo che salva una nuova richiesta nel fascicolo dell'utente
	 *
	 * @param cittadino
	 * @param nomeServizio
	 */
	protected void saveNuovaRichiesta(ProfiloUtenteCittadino cittadino, String nomeServizio) {

		FascicoloUtente fascicolo = repositoryFascicolo.findByCittadino(cittadino);
		if (fascicolo == null) {
			fascicolo = new FascicoloUtente();
			fascicolo.setCittadino(cittadino);
			fascicolo.setRichieste(new ArrayList<RichiestaServizio>());
		}

		RichiestaServizio richiesta = new RichiestaServizio();
		richiesta.setDataRichiesta(DateUtils.getOggi());
		richiesta.setNomeServizio(nomeServizio);
		richiesta.setFascicolo(fascicolo);

		fascicolo.aggiungiRichiesta(richiesta);

		repositoryFascicolo.save(fascicolo);
	}

	/**
	 * Template method per l'esecuzione di una chiamata RPC al servizio web che funge da adapter per
	 * il PDDS della porta applicativa di un comune.
	 *
	 * @param nomeServizio
	 * @param richiesta
	 * @param tipoRisposta
	 * @return un oggetto contenente la risposta dal servizio remoto.
	 */
	protected <R, TR> TR esegui(String nomeServizio, R richiesta, Class<TR> tipoRisposta) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		OSApulieUserDetails user = (OSApulieUserDetails) authentication.getPrincipal();

		String uriServizio = null;
		if (user != null) {
			uriServizio = user.getProfiloUtenteCittadino().getComuneIsa().getUriServizioGateway();
		}

		String xmlRichiesta = xmlHelper.marshal(richiesta);
		String risposta = pddsIntegration.getRichiestaPdd(xmlRichiesta, nomeServizio, uriServizio);

		TR dati = null;
		if (uriServizio != null && !uriServizio.equals("")) {
			dati = xmlHelper.unmarshal(risposta, tipoRisposta);
		}

		return dati;
	}

	/**
	 * Template method per l'esecuzione di una chiamata RPC al servizio web che funge da adapter per
	 * il PDDS della porta applicativa di un comune.
	 *
	 * @param nomeServizio
	 * @param richiesta
	 * @param tipoRisposta
	 * @param userPreferences
	 * @return un oggetto contenente la risposta dal servizio remoto.
	 */
	protected <R, TR> TR esegui(String nomeServizio, R richiesta, Class<TR> tipoRisposta, UserPreferences userPreferences) {

		Assert.notNull(userPreferences, "userPreferences must be not NULL");

		return esegui(nomeServizio, richiesta, tipoRisposta, userPreferences.getUriServizioGateway());
	}

	/**
	 * Template method per l'esecuzione di una chiamata RPC al servizio web che funge da adapter per
	 * il PDDS della porta applicativa di un comune.
	 *
	 * @param nomeServizio
	 * @param richiesta
	 * @param tipoRisposta
	 * @param uriServizio
	 * @return un oggetto contenente la risposta dal servizio remoto.
	 */
	protected <R, TR> TR esegui(String nomeServizio, R richiesta, Class<TR> tipoRisposta, String uriServizio) {

		Assert.notNull(uriServizio, "uriServizio must be not NULL");

		String xmlRichiesta = xmlHelper.marshal(richiesta);
		String risposta = null;

		// Tentativi caricamento dati
		int counter = 1;
		while (counter <= pddsAdapterConnectionAttempts) {
			try {
				risposta = pddsIntegration.getRichiestaPdd(xmlRichiesta, nomeServizio, uriServizio);
				if (risposta == null || risposta.equals("")) {
					throw new ServiceUnreachableException("Risposta is null or empty");
				}
				counter = pddsAdapterConnectionAttempts + 1;
			}
			catch (Exception e) {
				log.error("esegui :: tentativo num. " + counter + " per caricamento dati per servizio '" + nomeServizio + "' da PDD '" + uriServizio + "' fallito : " + e.getMessage(), e);
				counter++;
			}
		}

		TR dati = null;
		if (uriServizio != null && !uriServizio.equals("")) {
			try {
				dati = xmlHelper.unmarshal(risposta, tipoRisposta);
			}
			catch (Exception e) {
				throw new ServiceUnreachableException(e);
			}
		}

		return dati;
	}

	/**
	 * Template method per l'esecuzione di una chiamata RPC al servizio web che funge da adapter per
	 * il PDDS della porta applicativa di un comune. Necessita di un'istanza {@link PddsIntegration}
	 * di input "pddsIntegrationInput".
	 *
	 * @param nomeServizio
	 * @param richiesta
	 * @param tipoRisposta
	 * @param userPreferences
	 * @param receiveTimeout receive timeout in millisecondi
	 * @return
	 */
	protected <R, TR> TR esegui(String nomeServizio, R richiesta, Class<TR> tipoRisposta, UserPreferences userPreferences, int receiveTimeout) {

		String uriServizioGateway = userPreferences.getUriServizioGateway();
		Assert.notNull(uriServizioGateway, "uriServizio must be not NULL");

		String xmlRichiesta = xmlHelper.marshal(richiesta);

		((BindingProvider) pddsIntegrationInput).getRequestContext().put("javax.xml.ws.client.receiveTimeout", receiveTimeout);
		String risposta = pddsIntegrationInput.getRichiestaPdd(xmlRichiesta, nomeServizio, uriServizioGateway);

		TR dati = null;
		if (uriServizioGateway != null && !uriServizioGateway.equals("")) {
			try {
				dati = xmlHelper.unmarshal(risposta, tipoRisposta);
			}
			catch (Exception e) {
				throw new ServiceUnreachableException(e);
			}
		}

		return dati;
	}

	/**
	 * Visualizza alcune informazioni riguardanti l'autenticazione sulla console corrente.
	 */
	protected void displayAuthenticationDebugInfo() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null) {
			log.warn(" **** Questo metodo avrebbe dovuto essere protetto da Spring Security! ****");
			return;
		}

		log.debug("Authentication Name = " + authentication.getName());
		log.debug("Authentication Credentials = " + authentication.getCredentials());

		OSApuliePortletPreAuthenticatedDetails details = (OSApuliePortletPreAuthenticatedDetails) authentication.getDetails();

		log.debug(String.format("Authentication Details = %s [%s]", details, details.getClass()));
		OSApulieUserDetails user = (OSApulieUserDetails) authentication.getPrincipal();
		log.debug(String.format("Authentication Principal = %s [%s]", user, user.getClass()));
	}

	/**
	 * Ritorna l'XML dell'oggetto passato in input (marshal).
	 * 
	 * @param richiesta
	 * @return
	 */
	public <R> String getXml(R richiesta) {
		String xml = xmlHelper.marshal(richiesta);
		return xml;
	}
}
