/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.anagrafe.web.portlet.varie.form;

import java.io.Serializable;
import java.util.List;

import it.osapulie.anagrafe.web.portlet.dichiarazionecambioresidenza.form.Veicolo;

/**
 * @author Gianluca Pindinelli
 *
 */
public class Componente implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String identificativoUtente;
	private String codiceFiscale;
	private String nome;
	private String cognome;
	private String dataNascitaString;
	private String telefono;
	private String cellulare;
	private String email;
	private String pec;

	private String comuneNascita;
	private String comuneNascitaHidden;
	private String provinciaNascita;
	private String statoEsteroNascita;
	private String statoEsteroNascitaHidden;
	private String comuneNascitaEstero;
	private String comuneNascitaEsteroHidden;
	private String sesso;
	private String cittadinanza;
	private String cittadinanzaHidden;
	private String parentela;
	private String parentelaHidden;
	private String statoCivile;
	private String statoCivileHidden;
	private String professione;
	private String professioneHidden;
	private String condNonProfessionale;
	private String condNonProfessionaleHidden;
	private String titoloStudio;
	private String titoloStudioHidden;
	private String tipoPatente;
	private String numPatente;
	private String dataRilascioPatente;
	private String organoRilascioPatente;
	private String organoRilascioPatenteHidden;
	private String provPatente;
	private List<Veicolo> veicoli;

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

	/**
	 * @return the veicoli
	 */
	public List<Veicolo> getVeicoli() {
		return veicoli;
	}

	/**
	 * @param veicoli the veicoli to set
	 */
	public void setVeicoli(List<Veicolo> veicoli) {
		this.veicoli = veicoli;
	}

	/**
	 * @return the pec
	 */
	public String getPec() {
		return pec;
	}

	/**
	 * @param pec the pec to set
	 */
	public void setPec(String pec) {
		this.pec = pec;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the identificativoUtente
	 */
	public String getIdentificativoUtente() {
		return identificativoUtente;
	}

	/**
	 * @param identificativoUtente the identificativoUtente to set
	 */
	public void setIdentificativoUtente(String identificativoUtente) {
		this.identificativoUtente = identificativoUtente;
	}

	/**
	 * @return the comuneNascitaHidden
	 */
	public String getComuneNascitaHidden() {
		return comuneNascitaHidden;
	}

	/**
	 * @param comuneNascitaHidden the comuneNascitaHidden to set
	 */
	public void setComuneNascitaHidden(String comuneNascitaHidden) {
		this.comuneNascitaHidden = comuneNascitaHidden;
	}

	/**
	 * @return the cittadinanzaHidden
	 */
	public String getCittadinanzaHidden() {
		return cittadinanzaHidden;
	}

	/**
	 * @param cittadinanzaHidden the cittadinanzaHidden to set
	 */
	public void setCittadinanzaHidden(String cittadinanzaHidden) {
		this.cittadinanzaHidden = cittadinanzaHidden;
	}

	/**
	 * @return the parentelaHidden
	 */
	public String getParentelaHidden() {
		return parentelaHidden;
	}

	/**
	 * @param parentelaHidden the parentelaHidden to set
	 */
	public void setParentelaHidden(String parentelaHidden) {
		this.parentelaHidden = parentelaHidden;
	}

	/**
	 * @return the statoCivileHidden
	 */
	public String getStatoCivileHidden() {
		return statoCivileHidden;
	}

	/**
	 * @param statoCivileHidden the statoCivileHidden to set
	 */
	public void setStatoCivileHidden(String statoCivileHidden) {
		this.statoCivileHidden = statoCivileHidden;
	}

	/**
	 * @return the professioneHidden
	 */
	public String getProfessioneHidden() {
		return professioneHidden;
	}

	/**
	 * @param professioneHidden the professioneHidden to set
	 */
	public void setProfessioneHidden(String professioneHidden) {
		this.professioneHidden = professioneHidden;
	}

	/**
	 * @return the condNonProfessionaleHidden
	 */
	public String getCondNonProfessionaleHidden() {
		return condNonProfessionaleHidden;
	}

	/**
	 * @param condNonProfessionaleHidden the condNonProfessionaleHidden to set
	 */
	public void setCondNonProfessionaleHidden(String condNonProfessionaleHidden) {
		this.condNonProfessionaleHidden = condNonProfessionaleHidden;
	}

	/**
	 * @return the titoloStudioHidden
	 */
	public String getTitoloStudioHidden() {
		return titoloStudioHidden;
	}

	/**
	 * @param titoloStudioHidden the titoloStudioHidden to set
	 */
	public void setTitoloStudioHidden(String titoloStudioHidden) {
		this.titoloStudioHidden = titoloStudioHidden;
	}

	/**
	 * @return the organoRilascioPatenteHidden
	 */
	public String getOrganoRilascioPatenteHidden() {
		return organoRilascioPatenteHidden;
	}

	/**
	 * @param organoRilascioPatenteHidden the organoRilascioPatenteHidden to set
	 */
	public void setOrganoRilascioPatenteHidden(String organoRilascioPatenteHidden) {
		this.organoRilascioPatenteHidden = organoRilascioPatenteHidden;
	}

	/**
	 * @return the statoEsteroNascita
	 */
	public String getStatoEsteroNascita() {
		return statoEsteroNascita;
	}

	/**
	 * @param statoEsteroNascita the statoEsteroNascita to set
	 */
	public void setStatoEsteroNascita(String statoEsteroNascita) {
		this.statoEsteroNascita = statoEsteroNascita;
	}

	/**
	 * @return the statoEsteroNascitaHidden
	 */
	public String getStatoEsteroNascitaHidden() {
		return statoEsteroNascitaHidden;
	}

	/**
	 * @param statoEsteroNascitaHidden the statoEsteroNascitaHidden to set
	 */
	public void setStatoEsteroNascitaHidden(String statoEsteroNascitaHidden) {
		this.statoEsteroNascitaHidden = statoEsteroNascitaHidden;
	}

	/**
	 * @return the comuneNascitaEstero
	 */
	public String getComuneNascitaEstero() {
		return comuneNascitaEstero;
	}

	/**
	 * @param comuneNascitaEstero the comuneNascitaEstero to set
	 */
	public void setComuneNascitaEstero(String comuneNascitaEstero) {
		this.comuneNascitaEstero = comuneNascitaEstero;
	}

	/**
	 * @return the comuneNascitaEsteroHidden
	 */
	public String getComuneNascitaEsteroHidden() {
		return comuneNascitaEsteroHidden;
	}

	/**
	 * @param comuneNascitaEsteroHidden the comuneNascitaEsteroHidden to set
	 */
	public void setComuneNascitaEsteroHidden(String comuneNascitaEsteroHidden) {
		this.comuneNascitaEsteroHidden = comuneNascitaEsteroHidden;
	}
}
