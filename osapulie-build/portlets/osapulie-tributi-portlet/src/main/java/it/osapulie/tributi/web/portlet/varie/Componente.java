/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.tributi.web.portlet.varie;

import java.io.Serializable;

/**
 * @author Gianluca Pindinelli
 *
 */
public class Componente implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String codiceFiscale;
	private String nome;
	private String cognome;
	private String dataNascitaString;
	private String telefono;
	private String cellulare;

	private String comuneNascita;
	private String provinciaNascita;
	private String sesso;
	private String cittadinanza;
	private String parentela;
	private String statoCivile;
	private String professione;
	private String condNonProfessionale;
	private String titoloStudio;
	private String tipoPatente;
	private String numPatente;
	private String dataRilascioPatente;
	private String organoRilascioPatente;
	private String provPatente;
	private String targheAutoveicoli;
	private String targheRimorchi;
	private String targheMotoveicoli;
	private String targheCiclomotori;

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
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * @param cognome the cognome to set
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the cellulare
	 */
	public String getCellulare() {
		return cellulare;
	}

	/**
	 * @param cellulare the cellulare to set
	 */
	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}

	/**
	 * @return the comuneNascita
	 */
	public String getComuneNascita() {
		return comuneNascita;
	}

	/**
	 * @param comuneNascita the comuneNascita to set
	 */
	public void setComuneNascita(String comuneNascita) {
		this.comuneNascita = comuneNascita;
	}

	/**
	 * @return the provinciaNascita
	 */
	public String getProvinciaNascita() {
		return provinciaNascita;
	}

	/**
	 * @param provinciaNascita the provinciaNascita to set
	 */
	public void setProvinciaNascita(String provinciaNascita) {
		this.provinciaNascita = provinciaNascita;
	}

	/**
	 * @return the sesso
	 */
	public String getSesso() {
		return sesso;
	}

	/**
	 * @param sesso the sesso to set
	 */
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	/**
	 * @return the cittadinanza
	 */
	public String getCittadinanza() {
		return cittadinanza;
	}

	/**
	 * @param cittadinanza the cittadinanza to set
	 */
	public void setCittadinanza(String cittadinanza) {
		this.cittadinanza = cittadinanza;
	}

	/**
	 * @return the parentela
	 */
	public String getParentela() {
		return parentela;
	}

	/**
	 * @param parentela the parentela to set
	 */
	public void setParentela(String parentela) {
		this.parentela = parentela;
	}

	/**
	 * @return the statoCivile
	 */
	public String getStatoCivile() {
		return statoCivile;
	}

	/**
	 * @param statoCivile the statoCivile to set
	 */
	public void setStatoCivile(String statoCivile) {
		this.statoCivile = statoCivile;
	}

	/**
	 * @return the professione
	 */
	public String getProfessione() {
		return professione;
	}

	/**
	 * @param professione the professione to set
	 */
	public void setProfessione(String professione) {
		this.professione = professione;
	}

	/**
	 * @return the condNonProfessionale
	 */
	public String getCondNonProfessionale() {
		return condNonProfessionale;
	}

	/**
	 * @param condNonProfessionale the condNonProfessionale to set
	 */
	public void setCondNonProfessionale(String condNonProfessionale) {
		this.condNonProfessionale = condNonProfessionale;
	}

	/**
	 * @return the titoloStudio
	 */
	public String getTitoloStudio() {
		return titoloStudio;
	}

	/**
	 * @param titoloStudio the titoloStudio to set
	 */
	public void setTitoloStudio(String titoloStudio) {
		this.titoloStudio = titoloStudio;
	}

	/**
	 * @return the tipoPatente
	 */
	public String getTipoPatente() {
		return tipoPatente;
	}

	/**
	 * @param tipoPatente the tipoPatente to set
	 */
	public void setTipoPatente(String tipoPatente) {
		this.tipoPatente = tipoPatente;
	}

	/**
	 * @return the numPatente
	 */
	public String getNumPatente() {
		return numPatente;
	}

	/**
	 * @param numPatente the numPatente to set
	 */
	public void setNumPatente(String numPatente) {
		this.numPatente = numPatente;
	}

	/**
	 * @return the dataRilascioPatente
	 */
	public String getDataRilascioPatente() {
		return dataRilascioPatente;
	}

	/**
	 * @param dataRilascioPatente the dataRilascioPatente to set
	 */
	public void setDataRilascioPatente(String dataRilascioPatente) {
		this.dataRilascioPatente = dataRilascioPatente;
	}

	/**
	 * @return the organoRilascioPatente
	 */
	public String getOrganoRilascioPatente() {
		return organoRilascioPatente;
	}

	/**
	 * @param organoRilascioPatente the organoRilascioPatente to set
	 */
	public void setOrganoRilascioPatente(String organoRilascioPatente) {
		this.organoRilascioPatente = organoRilascioPatente;
	}

	/**
	 * @return the provPatente
	 */
	public String getProvPatente() {
		return provPatente;
	}

	/**
	 * @param provPatente the provPatente to set
	 */
	public void setProvPatente(String provPatente) {
		this.provPatente = provPatente;
	}

	/**
	 * @return the targheAutoveicoli
	 */
	public String getTargheAutoveicoli() {
		return targheAutoveicoli;
	}

	/**
	 * @param targheAutoveicoli the targheAutoveicoli to set
	 */
	public void setTargheAutoveicoli(String targheAutoveicoli) {
		this.targheAutoveicoli = targheAutoveicoli;
	}

	/**
	 * @return the targheRimorchi
	 */
	public String getTargheRimorchi() {
		return targheRimorchi;
	}

	/**
	 * @param targheRimorchi the targheRimorchi to set
	 */
	public void setTargheRimorchi(String targheRimorchi) {
		this.targheRimorchi = targheRimorchi;
	}

	/**
	 * @return the targheMotoveicoli
	 */
	public String getTargheMotoveicoli() {
		return targheMotoveicoli;
	}

	/**
	 * @param targheMotoveicoli the targheMotoveicoli to set
	 */
	public void setTargheMotoveicoli(String targheMotoveicoli) {
		this.targheMotoveicoli = targheMotoveicoli;
	}

	/**
	 * @return the targheCiclomotori
	 */
	public String getTargheCiclomotori() {
		return targheCiclomotori;
	}

	/**
	 * @param targheCiclomotori the targheCiclomotori to set
	 */
	public void setTargheCiclomotori(String targheCiclomotori) {
		this.targheCiclomotori = targheCiclomotori;
	}

	/**
	 * @return the dataNascitaString
	 */
	public String getDataNascitaString() {
		return dataNascitaString;
	}

	/**
	 * @param dataNascitaString the dataNascitaString to set
	 */
	public void setDataNascitaString(String dataNascitaString) {
		this.dataNascitaString = dataNascitaString;
	}

}
