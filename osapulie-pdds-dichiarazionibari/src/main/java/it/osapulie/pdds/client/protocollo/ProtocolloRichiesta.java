/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pdds.client.protocollo;

import java.util.List;

/**
 * @author Gianluca Pindinelli
 *
 */
public class ProtocolloRichiesta {

	private String amministrazione;
	private String aoo;
	private String oggetto;

	private List<Documento> documenti;

	/**
	 * @return the amministrazione
	 */
	public String getAmministrazione() {
		return amministrazione;
	}

	/**
	 * @param amministrazione the amministrazione to set
	 */
	public void setAmministrazione(String amministrazione) {
		this.amministrazione = amministrazione;
	}

	/**
	 * @return the aoo
	 */
	public String getAoo() {
		return aoo;
	}

	/**
	 * @param aoo the aoo to set
	 */
	public void setAoo(String aoo) {
		this.aoo = aoo;
	}

	/**
	 * @return the oggetto
	 */
	public String getOggetto() {
		return oggetto;
	}

	/**
	 * @param oggetto the oggetto to set
	 */
	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}

	/**
	 * @return the documenti
	 */
	public List<Documento> getDocumenti() {
		return documenti;
	}

	/**
	 * @param documenti the documenti to set
	 */
	public void setDocumenti(List<Documento> documenti) {
		this.documenti = documenti;
	}
}
