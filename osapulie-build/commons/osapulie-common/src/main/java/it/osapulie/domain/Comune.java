/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * The persistent class for the tb_comune database table.
 *
 * @author Gianluca Pindinelli
 *
 */
@Entity
@Table(name = "tb_comune")
public class Comune extends AbstractPersistable<Long> implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 69955298317664502L;

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.jpa.domain.AbstractPersistable#setId(java.io.Serializable)
	 */
	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	@Column(name = "altitudine", length = 45)
	private String altitudine;

	@Column(name = "capoluogo")
	private boolean capoluogo;

	@Column(name = "codiceComune", length = 11)
	private int codiceComune;

	@Column(name = "codiceIstat1", length = 45)
	private String codiceIstat1;

	@Column(name = "codiceIstat103", length = 45)
	private String codiceIstat103;

	@Column(name = "codiceIstatAN", length = 45)
	private String codiceIstatAN;

	@Column(name = "codiceSistemaLocaleLavoro", length = 45)
	private String codiceSistemaLocaleLavoro;

	@Column(name = "codicestat107", length = 45)
	private String codiceIstat107;

	@Column(name = "comuneLitoraneo")
	private boolean comuneLitoraneo;

	@Column(name = "comuneMontano", length = 45)
	private String comuneMontano;

	@Column(name = "denominazione", length = 100)
	private String denominazione;

	@Column(name = "denominazioneSistemaLocaleLavoro", length = 100)
	private String denominazioneSistemaLocaleLavoro;

	@Column(name = "popolazioneLegale", length = 45)
	private String popolazioneLegale;

	@Column(name = "popolazioneResidente1", length = 45)
	private String popolazioneResidente1;

	@Column(name = "popolazioneResidente2", length = 45)
	private String popolazioneResidente2;

	@Column(name = "popolazioneResidente3", length = 45)
	private String popolazioneResidente3;

	@Column(name = "superficie", length = 45)
	private String superficie;

	@Column(name = "zonaAltimetrica", length = 45)
	private String zonaAltimetrica;

	@Column(name = "codiceCatastale", length = 4)
	private String codiceCatastale;

	@JoinColumn(name = "idProvincia")
	private Provincia provincia;

	public String getAltitudine() {
		return this.altitudine;
	}

	public void setAltitudine(String altitudine) {
		this.altitudine = altitudine;
	}

	public int getCodiceComune() {
		return this.codiceComune;
	}

	public void setCodiceComune(int codiceComune) {
		this.codiceComune = codiceComune;
	}

	public String getCodiceIstat1() {
		return this.codiceIstat1;
	}

	public void setCodiceIstat1(String codiceIstat1) {
		this.codiceIstat1 = codiceIstat1;
	}

	public String getCodiceIstat103() {
		return this.codiceIstat103;
	}

	public void setCodiceIstat103(String codiceIstat103) {
		this.codiceIstat103 = codiceIstat103;
	}

	public String getCodiceIstatAN() {
		return this.codiceIstatAN;
	}

	public void setCodiceIstatAN(String codiceIstatAN) {
		this.codiceIstatAN = codiceIstatAN;
	}

	public String getCodiceSistemaLocaleLavoro() {
		return this.codiceSistemaLocaleLavoro;
	}

	public void setCodiceSistemaLocaleLavoro(String codiceSistemaLocaleLavoro) {
		this.codiceSistemaLocaleLavoro = codiceSistemaLocaleLavoro;
	}

	public String getComuneMontano() {
		return this.comuneMontano;
	}

	public void setComuneMontano(String comuneMontano) {
		this.comuneMontano = comuneMontano;
	}

	public String getDenominazione() {
		return this.denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public String getDenominazioneSistemaLocaleLavoro() {
		return this.denominazioneSistemaLocaleLavoro;
	}

	public void setDenominazioneSistemaLocaleLavoro(String denominazioneSistemaLocaleLavoro) {
		this.denominazioneSistemaLocaleLavoro = denominazioneSistemaLocaleLavoro;
	}

	public String getPopolazioneLegale() {
		return this.popolazioneLegale;
	}

	public void setPopolazioneLegale(String popolazioneLegale) {
		this.popolazioneLegale = popolazioneLegale;
	}

	public String getPopolazioneResidente1() {
		return this.popolazioneResidente1;
	}

	public void setPopolazioneResidente1(String popolazioneResidente1) {
		this.popolazioneResidente1 = popolazioneResidente1;
	}

	public String getPopolazioneResidente2() {
		return this.popolazioneResidente2;
	}

	public void setPopolazioneResidente2(String popolazioneResidente2) {
		this.popolazioneResidente2 = popolazioneResidente2;
	}

	public String getPopolazioneResidente3() {
		return this.popolazioneResidente3;
	}

	public void setPopolazioneResidente3(String popolazioneResidente3) {
		this.popolazioneResidente3 = popolazioneResidente3;
	}

	public String getSuperficie() {
		return this.superficie;
	}

	public void setSuperficie(String superficie) {
		this.superficie = superficie;
	}

	public String getZonaAltimetrica() {
		return this.zonaAltimetrica;
	}

	public void setZonaAltimetrica(String zonaAltimetrica) {
		this.zonaAltimetrica = zonaAltimetrica;
	}

	public boolean isCapoluogo() {
		return capoluogo;
	}

	public void setCapoluogo(boolean capoluogo) {
		this.capoluogo = capoluogo;
	}

	public boolean isComuneLitoraneo() {
		return comuneLitoraneo;
	}

	public void setComuneLitoraneo(boolean comuneLitoraneo) {
		this.comuneLitoraneo = comuneLitoraneo;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	/**
	 * @return the codiceCatastale
	 */
	public String getCodiceCatastale() {
		return codiceCatastale;
	}

	/**
	 * @param codiceCatastale the codiceCatastale to set
	 */
	public void setCodiceCatastale(String codiceCatastale) {
		this.codiceCatastale = codiceCatastale;
	}

	/**
	 * @return the codiceIstat107
	 */
	public String getCodiceIstat107() {
		return codiceIstat107;
	}

	/**
	 * @param codiceIstat107 the codiceIstat107 to set
	 */
	public void setCodiceIstat107(String codiceIstat107) {
		this.codiceIstat107 = codiceIstat107;
	}

}