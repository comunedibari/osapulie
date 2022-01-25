/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.domain;

import it.osapulie.domain.servizi.Servizio;

import java.io.Serializable;

/**
 * Tabella di relazione tra {@link ComuneISA} e {@link Servizio}.
 *
 * @author Gianluca Pindinelli
 */
public class ComuneISAServizioId implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 2264747686408059992L;

	private long idComuneISA;

	private long idServizio;

	/**
	 * @return the idComuneISA
	 */
	public long getIdComuneISA() {
		return idComuneISA;
	}

	/**
	 * @param idComuneISA the idComuneISA to set
	 */
	public void setIdComuneISA(long idComuneISA) {
		this.idComuneISA = idComuneISA;
	}

	/**
	 * @return the idServizio
	 */
	public long getIdServizio() {
		return idServizio;
	}

	/**
	 * @param idServizio the idServizio to set
	 */
	public void setIdServizio(long idServizio) {
		this.idServizio = idServizio;
	}

}
