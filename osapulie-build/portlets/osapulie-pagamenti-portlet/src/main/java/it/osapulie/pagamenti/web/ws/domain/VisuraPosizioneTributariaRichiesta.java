/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pagamenti.web.ws.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Gianluca Pindinelli
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class VisuraPosizioneTributariaRichiesta {

	@XmlElement(required = true)
	private String codiceIstatComune;

	@XmlElement(required = true)
	private String codiceFiscale;

	@XmlElement(required = true)
	private Boolean autenticatoForte;

	@XmlElement(required = true)
	private String codiceServizio;

	@XmlElement(required = true)
	private String identificativoCredito;

	/**
	 * @return the codiceIstatComune
	 */
	public String getCodiceIstatComune() {
		return codiceIstatComune;
	}

	/**
	 * @param codiceIstatComune the codiceIstatComune to set
	 */
	public void setCodiceIstatComune(String codiceIstatComune) {
		this.codiceIstatComune = codiceIstatComune;
	}

	/**
	 * @return the codiceFiscale
	 */
	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	/**
	 * @param codiceFiscale the codiceFiscale to set
	 */
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	/**
	 * @return the codiceServizio
	 */
	public String getCodiceServizio() {
		return codiceServizio;
	}

	/**
	 * @param codiceServizio the codiceServizio to set
	 */
	public void setCodiceServizio(String codiceServizio) {
		this.codiceServizio = codiceServizio;
	}

	/**
	 * @return the identificativoCredito
	 */
	public String getIdentificativoCredito() {
		return identificativoCredito;
	}

	/**
	 * @param identificativoCredito the identificativoCredito to set
	 */
	public void setIdentificativoCredito(String identificativoCredito) {
		this.identificativoCredito = identificativoCredito;
	}

	/**
	 * @return the autenticatoForte
	 */
	public Boolean getAutenticatoForte() {
		return autenticatoForte;
	}

	/**
	 * @param autenticatoForte the autenticatoForte to set
	 */
	public void setAutenticatoForte(Boolean autenticatoForte) {
		this.autenticatoForte = autenticatoForte;
	}

}
