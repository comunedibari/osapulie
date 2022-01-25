/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.profiloutente.model.view;

import java.io.Serializable;
import java.util.List;

import it.osapulie.domain.servizi.Servizio;

/**
 * Classe model per il binding delle informazioni riguardanti la delega utente.
 *
 * @author Gianluca Pindinelli
 *
 */
public class DelegaModel implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 4575141728492471075L;
	private long idDelega;
	private long idDelegante;
	private long idDelegato;
	private String piva;

	private long idComuneIsa;

	private List<Servizio> serviziDisponibili;
	private List<Servizio> serviziAssociati;

	private List<String> serviziDisponibiliStrings;
	private List<String> serviziAssociatiStrings;

	/**
	 * @return the idDelega
	 */
	public long getIdDelega() {
		return idDelega;
	}

	/**
	 * @param idDelega the idDelega to set
	 */
	public void setIdDelega(long idDelega) {
		this.idDelega = idDelega;
	}

	/**
	 * @return the idDelegante
	 */
	public long getIdDelegante() {
		return idDelegante;
	}

	/**
	 * @param idDelegante the idDelegante to set
	 */
	public void setIdDelegante(long idDelegante) {
		this.idDelegante = idDelegante;
	}

	/**
	 * @return the idDelegato
	 */
	public long getIdDelegato() {
		return idDelegato;
	}

	/**
	 * @param idDelegato the idDelegato to set
	 */
	public void setIdDelegato(long idDelegato) {
		this.idDelegato = idDelegato;
	}

	/**
	 * @return the piva
	 */
	public String getPiva() {
		return piva;
	}

	/**
	 * @param piva the piva to set
	 */
	public void setPiva(String piva) {
		this.piva = piva;
	}

	/**
	 * @return the idComuneIsa
	 */
	public long getIdComuneIsa() {
		return idComuneIsa;
	}

	/**
	 * @param idComuneIsa the idComuneIsa to set
	 */
	public void setIdComuneIsa(long idComuneIsa) {
		this.idComuneIsa = idComuneIsa;
	}

	/**
	 * @return the serviziDisponibili
	 */
	public List<Servizio> getServiziDisponibili() {
		return serviziDisponibili;
	}

	/**
	 * @param serviziDisponibili the serviziDisponibili to set
	 */
	public void setServiziDisponibili(List<Servizio> serviziDisponibili) {
		this.serviziDisponibili = serviziDisponibili;
	}

	/**
	 * @return the serviziAssociati
	 */
	public List<Servizio> getServiziAssociati() {
		return serviziAssociati;
	}

	/**
	 * @param serviziAssociati the serviziAssociati to set
	 */
	public void setServiziAssociati(List<Servizio> serviziAssociati) {
		this.serviziAssociati = serviziAssociati;
	}

	/**
	 * @return the serviziDisponibiliStrings
	 */
	public List<String> getServiziDisponibiliStrings() {
		return serviziDisponibiliStrings;
	}

	/**
	 * @param serviziDisponibiliStrings the serviziDisponibiliStrings to set
	 */
	public void setServiziDisponibiliStrings(List<String> serviziDisponibiliStrings) {
		this.serviziDisponibiliStrings = serviziDisponibiliStrings;
	}

	/**
	 * @return the serviziAssociatiStrings
	 */
	public List<String> getServiziAssociatiStrings() {
		return serviziAssociatiStrings;
	}

	/**
	 * @param serviziAssociatiStrings the serviziAssociatiStrings to set
	 */
	public void setServiziAssociatiStrings(List<String> serviziAssociatiStrings) {
		this.serviziAssociatiStrings = serviziAssociatiStrings;
	}

}
