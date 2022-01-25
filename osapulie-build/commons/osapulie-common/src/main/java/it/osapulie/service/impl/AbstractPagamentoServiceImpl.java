/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.service.impl;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

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
import it.osapulie.web.portlet.util.EncryptDecryptUtil;
import it.osapulie.web.ws.pddsintegration.PddsIntegration;

/**
 * Implementazione base per eventuali service classes che usano il {@link PddsIntegration} per
 * richiedere i servizi.
 *
 * @author Mario Scalas
 */
public class AbstractPagamentoServiceImpl {

	protected Logger log = LoggerFactory.getLogger(getClass());

	@Inject
	protected PddsIntegration pddsIntegration;

	@Inject
	protected XMLHelper xmlHelper;

	@Inject
	private FascicoloUtenteRepository repositoryFascicolo;

	@Value("#{applicationProperties['pddsAdapter.connection.attempts']}")
	private Integer pddsAdapterConnectionAttempts;

	/**
	 * Protected c-tor to enforce usage only for derived classes.
	 */
	protected AbstractPagamentoServiceImpl() {
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

		// Caricamento uri servizio: se l'utente ha selezionato un comune uso quello, altrimenti
		// utilizzo il comune di residenza
		String uriServizio = null;
		if (user != null) {
			uriServizio = user.getProfiloUtenteCittadino().getComuneIsa().getUriServizioGateway();
		}

		String xmlRichiesta = xmlHelper.marshal(richiesta);
		try {
			if (uriServizio != null && !uriServizio.equals("")) {
				xmlRichiesta = EncryptDecryptUtil.encrypt(xmlRichiesta, EncryptDecryptUtil.PASSKEY_CONST);
			}
		}
		catch (InvalidKeyException e) {
			log.error("AbstractPagamentoServiceImpl :: esegui EncryptDecryptUtil:: " + e.getMessage());
		}
		catch (NoSuchAlgorithmException e) {
			log.error("AbstractPagamentoServiceImpl :: esegui EncryptDecryptUtil:: " + e.getMessage());
		}
		catch (NoSuchProviderException e) {
			log.error("AbstractPagamentoServiceImpl :: esegui EncryptDecryptUtil:: " + e.getMessage());
		}
		catch (NoSuchPaddingException e) {
			log.error("AbstractPagamentoServiceImpl :: esegui EncryptDecryptUtil:: " + e.getMessage());
		}
		catch (IllegalBlockSizeException e) {
			log.error("AbstractPagamentoServiceImpl :: esegui EncryptDecryptUtil:: " + e.getMessage());
		}
		catch (BadPaddingException e) {
			log.error("AbstractPagamentoServiceImpl :: esegui EncryptDecryptUtil:: " + e.getMessage());
		}
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

		// Restituisci la risposta
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

		String uriServizio = userPreferences.getUriServizioGateway();

		String xmlRichiesta = xmlHelper.marshal(richiesta);
		try {
			if (uriServizio != null && !uriServizio.equals("")) {
				xmlRichiesta = EncryptDecryptUtil.encrypt(xmlRichiesta, EncryptDecryptUtil.PASSKEY_CONST);
			}
		}
		catch (InvalidKeyException e) {
			log.error("AbstractPagamentoServiceImpl :: esegui EncryptDecryptUtil:: " + e.getMessage());
		}
		catch (NoSuchAlgorithmException e) {
			log.error("AbstractPagamentoServiceImpl :: esegui EncryptDecryptUtil:: " + e.getMessage());
		}
		catch (NoSuchProviderException e) {
			log.error("AbstractPagamentoServiceImpl :: esegui EncryptDecryptUtil:: " + e.getMessage());
		}
		catch (NoSuchPaddingException e) {
			log.error("AbstractPagamentoServiceImpl :: esegui EncryptDecryptUtil:: " + e.getMessage());
		}
		catch (IllegalBlockSizeException e) {
			log.error("AbstractPagamentoServiceImpl :: esegui EncryptDecryptUtil:: " + e.getMessage());
		}
		catch (BadPaddingException e) {
			log.error("AbstractPagamentoServiceImpl :: esegui EncryptDecryptUtil:: " + e.getMessage());
		}

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
	 * Visualizza alcune informazioni riguardanti l'autenticazione sulla console corrente.
	 */
	protected void displayAuthenticationDebugInfo() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			log.warn(" **** Questo metodo avrebbe dovuto essere protetto da Spring Security! ****");
			return;
		}
		/*
		 * - Authentication Name = 10169 - Authentication Credentials = dummy - Authentication
		 * Details = User info: {liferay.company.id=10132, liferay.user.id=10169,
		 * user.login.id=admin, user.name.full=Administrator of Liferay};
		 * preAuthenticatedGrantedAuthorities: [ROLE_User, ROLE_Administrator, ROLE_Power User]
		 * [class org.springframework.security.extensions.portlet.
		 * PortletPreAuthenticatedAuthenticationDetails ] - Authentication Principal =
		 * org.springframework.security.core.userdetails.User@2c91035: Username: 10169; Password:
		 * [PROTECTED]; Enabled: true; AccountNonExpired: true; credentialsNonExpired: true;
		 * AccountNonLocked: true; Granted Authorities: ROLE_Administrator,ROLE_Power User,ROLE_User
		 * [class org.springframework.security.core.userdetails.User]
		 */
		log.debug("Authentication Name = " + authentication.getName());
		log.debug("Authentication Credentials = " + authentication.getCredentials());
		// PortletPreAuthenticatedAuthenticationDetails details =
		// (PortletPreAuthenticatedAuthenticationDetails) authentication.getDetails();

		OSApuliePortletPreAuthenticatedDetails details = (OSApuliePortletPreAuthenticatedDetails) authentication.getDetails();

		log.debug(String.format("Authentication Details = %s [%s]", details, details.getClass()));
		OSApulieUserDetails user = (OSApulieUserDetails) authentication.getPrincipal();
		log.debug(String.format("Authentication Principal = %s [%s]", user, user.getClass()));
	}
}
