/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 * 
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.infrastructure.security;

/**
 * Classe per la gestione delle eccezioni relative alle operazioni riguardanti il profilo utente.
 * 
 * @author Gianluca Pindinelli
 * 
 */
public class ProfilazioneUtenteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -250172582872244693L;

	public ProfilazioneUtenteException() {
		super();
	}

	public ProfilazioneUtenteException(String msg) {
		super(msg);
	}

	public ProfilazioneUtenteException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ProfilazioneUtenteException(Throwable cause) {
		super(cause);
	}
}
