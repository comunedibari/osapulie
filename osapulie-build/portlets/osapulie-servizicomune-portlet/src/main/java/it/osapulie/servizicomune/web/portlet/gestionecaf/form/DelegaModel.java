/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.servizicomune.web.portlet.gestionecaf.form;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import it.osapulie.domain.Delega;
import it.osapulie.domain.servizi.Servizio;
import it.osapulie.servizicomune.model.DelegaReportModel;
import it.osapulie.util.FirmaGrafometricaResponse;

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
	private String codiceFiscaleDelegante;
	
	private String token;
	private String firma;

	private long idComuneIsa;

	private List<Servizio> serviziDisponibili;
	private List<Servizio> serviziAssociati;

	private List<String> serviziDisponibiliStrings;
	private List<String> serviziAssociatiStrings;

	private CommonsMultipartFile allegato;

	private String nomeAllegato;
	private boolean attiva;
	
	private String numeroDocumento;
	private String dataScadenzaDocumento;
	private String rilasciatoDa;
	
	private transient String stringFirma;
	private String tipoAcquisizione;
	private CommonsMultipartFile documento;
	
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

	/**
	 * @return the codiceFiscaleDelegante
	 */
	public String getCodiceFiscaleDelegante() {
		return codiceFiscaleDelegante;
	}

	/**
	 * @param codiceFiscaleDelegante the codiceFiscaleDelegante to set
	 */
	public void setCodiceFiscaleDelegante(String codiceFiscaleDelegante) {
		this.codiceFiscaleDelegante = codiceFiscaleDelegante;
	}

	/**
	 * @return the allegato
	 */
	public CommonsMultipartFile getAllegato() {
		return allegato;
	}

	/**
	 * @param allegato the allegato to set
	 */
	public void setAllegato(CommonsMultipartFile allegato) {
		this.allegato = allegato;
	}

	/**
	 * @return the nomeAllegato
	 */
	public String getNomeAllegato() {
		return nomeAllegato;
	}

	/**
	 * @param nomeAllegato the nomeAllegato to set
	 */
	public void setNomeAllegato(String nomeAllegato) {
		this.nomeAllegato = nomeAllegato;
	}

	public boolean isAttiva() {
		return attiva;
	}

	public void setAttiva(boolean attiva) {
		this.attiva = attiva;
	}

	/**
	 * @return the stringFirma
	 */
	public String getStringFirma() {
		return stringFirma;
	}

	/**
	 * @param stringFirma the stringFirma to set
	 */
	public void setStringFirma(String stringFirma) {
		this.stringFirma = stringFirma;
	}

	/**
	 * @return the numeroDocumento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * @param numeroDocumento the numeroDocumento to set
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * @return the dataScadenzaDocumento
	 */
	public String getDataScadenzaDocumento() {
		return dataScadenzaDocumento;
	}

	/**
	 * @param dataScadenzaDocumento the dataScadenzaDocumento to set
	 */
	public void setDataScadenzaDocumento(String dataScadenzaDocumento) {
		this.dataScadenzaDocumento = dataScadenzaDocumento;
	}

	/**
	 * @return the rilasciatoDa
	 */
	public String getRilasciatoDa() {
		return rilasciatoDa;
	}

	/**
	 * @param rilasciatoDa the rilasciatoDa to set
	 */
	public void setRilasciatoDa(String rilasciatoDa) {
		this.rilasciatoDa = rilasciatoDa;
	}

	/**
	 * @return the tipoAcquisizione
	 */
	public String getTipoAcquisizione() {
		return tipoAcquisizione;
	}

	/**
	 * @param tipoAcquisizione the tipoAcquisizione to set
	 */
	public void setTipoAcquisizione(String tipoAcquisizione) {
		this.tipoAcquisizione = tipoAcquisizione;
	}

	public CommonsMultipartFile getDocumento() {
		return documento;
	}

	public void setDocumento(CommonsMultipartFile documento) {
		this.documento = documento;
	}

	public String getFirma() {
		return firma;
	}

	public void setFirma(String firma) {
		this.firma = firma;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
