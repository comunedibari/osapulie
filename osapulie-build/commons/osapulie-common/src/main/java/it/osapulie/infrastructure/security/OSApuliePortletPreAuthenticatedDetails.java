/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.infrastructure.security;

import java.security.cert.X509Certificate;

import javax.portlet.PortletRequest;

import org.springframework.security.extensions.portlet.PortletPreAuthenticatedAuthenticationDetails;

import it.osapulie.domain.ProfiloUtenteCittadino;

/**
 *
 * @author Mario Scalas
 * @author Gianluca Pindinelli
 */
public class OSApuliePortletPreAuthenticatedDetails extends PortletPreAuthenticatedAuthenticationDetails {

	private static final long serialVersionUID = 7929833053851250431L;

	private ProfiloUtenteCittadino profiloUtente;
	private X509Certificate certificato;

	/**
	 * @param request
	 */
	public OSApuliePortletPreAuthenticatedDetails(PortletRequest request) {
		super(request);
	}

	public ProfiloUtenteCittadino getProfiloUtente() {
		return profiloUtente;
	}

	public void setProfiloUtente(ProfiloUtenteCittadino profiloUtente) {
		this.profiloUtente = profiloUtente;
	}

	public X509Certificate getCertificato() {
		return certificato;
	}

	public void setCertificato(X509Certificate certificato) {
		this.certificato = certificato;
	}
}
