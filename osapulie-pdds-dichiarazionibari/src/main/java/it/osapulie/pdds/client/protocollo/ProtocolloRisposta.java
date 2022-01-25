/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pdds.client.protocollo;

import java.util.Date;

/**
 * @author Gianluca Pindinelli
 *
 */
public class ProtocolloRisposta {

	private Long numeroProtocollo;
	private Date dataProtocollo;

	/**
	 * @return the dataProtocollo
	 */
	public Date getDataProtocollo() {
		return dataProtocollo;
	}

	/**
	 * @param dataProtocollo the dataProtocollo to set
	 */
	public void setDataProtocollo(Date dataProtocollo) {
		this.dataProtocollo = dataProtocollo;
	}

	/**
	 * @return the numeroProtocollo
	 */
	public Long getNumeroProtocollo() {
		return numeroProtocollo;
	}

	/**
	 * @param numeroProtocollo the numeroProtocollo to set
	 */
	public void setNumeroProtocollo(Long numeroProtocollo) {
		this.numeroProtocollo = numeroProtocollo;
	}

}
