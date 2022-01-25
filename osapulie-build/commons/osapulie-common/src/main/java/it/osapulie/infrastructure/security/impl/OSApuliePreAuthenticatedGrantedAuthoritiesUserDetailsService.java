/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 * 
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.infrastructure.security.impl;

import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.infrastructure.security.OSApulieUserDetails;
import it.osapulie.persistence.ProfiloUtenteCittadinoRepository;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedGrantedAuthoritiesUserDetailsService;
import org.springframework.transaction.annotation.Transactional;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * Implementazione di {@link UserDetailsService} che è a conoscenza della profilazione utente
 * implementata in OS Apulie. <br/> <strong>Nota</strong> Questo service non usa il component-scan
 * di Spring: va esplicitamente inserito all'interno del security context XML dell'applicazione
 * (ossia: è una scelta di design responsabile e si sposa con lo stile già presente in
 * <i>security-context.xml</i>).
 * 
 * @author Mario Scalas
 */
// @Service( "osapulieUserDetailsService" )
public class OSApuliePreAuthenticatedGrantedAuthoritiesUserDetailsService extends PreAuthenticatedGrantedAuthoritiesUserDetailsService {

	protected Logger log = LoggerFactory.getLogger(getClass());

	@Inject
	private ProfiloUtenteCittadinoRepository repositoryCittadini;

	/**
	 * Oltre a creare un classico {@link UserDetails} contenente username, password e ruoli,
	 * dobbiamo aggiungere le informazioni relative alla profilazione dell'utente (profilo cittadino
	 * e professionista).
	 * 
	 * @param token the authentication request token
	 * @param authorities the pre-authenticated authorities.
	 */
	@Override
	@Transactional(readOnly = true)
	protected UserDetails createuserDetails(Authentication token, List<GrantedAuthority> authorities) {
		String name = token.getName();

		// User ID in Liferay è un numero
		Long userId = null;
		try {
			userId = Long.parseLong(name);
		}
		catch (NumberFormatException e) {
			log.warn("User ID is invalid ({0})", name);
			return null;
		}

		// Utente del portale
		User portalUser = (User) super.createuserDetails(token, authorities);

		// Profilo Utente
		ProfiloUtenteCittadino profiloUtente = findProfiloUtente(name);

		// Utente Liferay
		com.liferay.portal.model.User liferayUser = null;
		try {
			liferayUser = UserLocalServiceUtil.getUser(userId);
		}
		catch (PortalException e) {
			log.error("Unable to find Liferay user ", e);
			return null;
		}
		catch (SystemException e) {
			log.error("Unable to find Liferay user ", e);
			return null;
		}

		return new OSApulieUserDetails(portalUser, profiloUtente, liferayUser);
	}

	/**
	 * Recupera il profilo utente associato all'utente corrente.
	 * 
	 * @param userName
	 * @return
	 */
	private ProfiloUtenteCittadino findProfiloUtente(String userName) {
		// Cerchiamo il profilo associato allo user name indicato: può essere un professionista o
		// (come fallback)
		// un cittadino: in caso non sia nemmeno un profilo cittadino allora viene ritornato null.
		ProfiloUtenteCittadino profiloUtente = repositoryCittadini.findOne(Long.parseLong(userName));
		return profiloUtente;
	}
}
