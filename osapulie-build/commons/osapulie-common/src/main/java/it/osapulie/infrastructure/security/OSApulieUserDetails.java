/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.infrastructure.security;

import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.liferay.portal.model.Contact;

import it.osapulie.domain.Azienda;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.ProfiloUtenteCittadinoAzienda;
import it.osapulie.shared.enumeration.AuthenticationChannel;

/**
 * Implementazione di {@link UserDetails} che include il profilo utente di un cittadino utente di OS
 * Apulie.
 *
 * @author Mario Scalas
 */
public class OSApulieUserDetails implements UserDetails, CredentialsContainer {

	private static final long serialVersionUID = 8577518790550154274L;

	private final User portalUser;
	private final ProfiloUtenteCittadino profiloUtenteCittadino;
	private final com.liferay.portal.model.User liferayUser;

	/**
	 * Variabile di scambio per permettere la modifica dei dati relativi al contatto di un utente
	 */
	private Contact liferayContact;

	/**
	 * Canale di autenticazione.
	 */
	private AuthenticationChannel authenticationChannel = AuthenticationChannel.CAS;

	/**
	 * Certificato che identifica l'autenticazione forte di un utente (tramite CNS).
	 */
	private X509Certificate certificato;

	private boolean autenticatoForte;

	private Integer livelloAutenticazione;

	/**
	 * @param portalUser
	 * @param profiloUtenteCittadino
	 */
	public OSApulieUserDetails(User portalUser, ProfiloUtenteCittadino profiloUtente, com.liferay.portal.model.User liferayUser) {
		this.portalUser = portalUser;
		this.profiloUtenteCittadino = profiloUtente;
		this.liferayUser = liferayUser;
	}

	/**
	 * Verifica che esista un {@link ProfiloUtenteCittadino} associato a questo {@link UserDetails}
	 * (questo indica che l'utente è già stato profilato all'interno del sistema e sono disponibili
	 * tutte le informazioni
	 *
	 * @return <code>true</code> se l'utente risulta già essere stato profilato all'interno del
	 *         sistema, <code>false</code> altrimenti.
	 */
	public boolean isUtenteProfilato() {
		return profiloUtenteCittadino != null;
	}

	/**
	 * @return le {@link Azienda} attive associate al cittadino, <code>null</code> altrimenti.
	 */
	public List<Azienda> getAziende() {

		Set<Azienda> resultSet = new HashSet<Azienda>();

		// Caricamento aziende di cui è responsabile
		List<Azienda> aziende = profiloUtenteCittadino.getAziende();
		if (aziende != null) {
			for (Azienda azienda : aziende) {
				if (azienda.isAttiva()) {
					resultSet.add(azienda);
				}
			}
		}

		List<ProfiloUtenteCittadinoAzienda> profiliUtenteCittadinoAziende = profiloUtenteCittadino.getProfiliUtenteCittadinoAziende();
		if (profiliUtenteCittadinoAziende != null) {
			for (ProfiloUtenteCittadinoAzienda profiloUtenteCittadinoAzienda : profiliUtenteCittadinoAziende) {
				if (profiloUtenteCittadinoAzienda.getAzienda().isAttiva()) {
					resultSet.add(profiloUtenteCittadinoAzienda.getAzienda());
				}
			}
		}

		List<Azienda> result = null;
		if (!resultSet.isEmpty()) {
			result = new ArrayList<Azienda>();
			result.addAll(resultSet);
		}

		return result;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return portalUser.getAuthorities();
	}

	@Override
	public String getPassword() {
		return portalUser.getPassword();
	}

	@Override
	public String getUsername() {
		return portalUser.getUsername();
	}

	@Override
	public boolean isEnabled() {
		return portalUser.isEnabled();
	}

	@Override
	public boolean isAccountNonExpired() {
		return portalUser.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return portalUser.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return portalUser.isCredentialsNonExpired();
	}

	@Override
	public void eraseCredentials() {
		portalUser.eraseCredentials();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object rhs) {
		return portalUser.equals(rhs);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return portalUser.hashCode();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return portalUser.toString();
	}

	public com.liferay.portal.model.User getLiferayUser() {
		return liferayUser;
	}

	public X509Certificate getCertificato() {
		return certificato;
	}

	public void setCertificato(X509Certificate certificato) {
		this.certificato = certificato;
	}

	public Contact getLiferayContact() {
		return liferayContact;
	}

	public void setLiferayContact(Contact liferayContact) {
		this.liferayContact = liferayContact;
	}

	public ProfiloUtenteCittadino getProfiloUtenteCittadino() {
		return profiloUtenteCittadino;
	}

	public boolean isAutenticatoForte() {
		if (certificato != null) {
			return true;
		}
		if (profiloUtenteCittadino != null && profiloUtenteCittadino.isAutenticazioneForte()) {
			return true;
		}
		return autenticatoForte;
	}

	public void setAutenticatoForte(boolean autenticatoForte) {
		this.autenticatoForte = autenticatoForte;
	}

	/**
	 * @return the livelloAutenticazione
	 */
	public Integer getLivelloAutenticazione() {

		if (profiloUtenteCittadino != null && profiloUtenteCittadino.getLivelloAutenticazione() != null) {
			return profiloUtenteCittadino.getLivelloAutenticazione();
		}

		return livelloAutenticazione;
	}

	/**
	 * @param livelloAutenticazione the livelloAutenticazione to set
	 */
	public void setLivelloAutenticazione(Integer livelloAutenticazione) {
		this.livelloAutenticazione = livelloAutenticazione;
	}

	/**
	 * @return the authenticationChannel
	 */
	public AuthenticationChannel getAuthenticationChannel() {

		if (profiloUtenteCittadino != null && profiloUtenteCittadino.getCanaleAutenticazione() != null) {
			return AuthenticationChannel.valueOf(profiloUtenteCittadino.getCanaleAutenticazione().toUpperCase());
		}

		return authenticationChannel;
	}

	/**
	 * @param authenticationChannel the authenticationChannel to set
	 */
	public void setAuthenticationChannel(AuthenticationChannel authenticationChannel) {
		this.authenticationChannel = authenticationChannel;
	}
}
