/************************************************************************************
 * Copyright (c) 2011, 2016 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.servizicomune.web.portlet.gestioneaziende.form;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import it.osapulie.domain.ProfiloUtenteCittadino;

/**
 * @author Gianluca Pindinelli
 *
 */
public class AziendaEditForm implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 6636467813471500457L;

	private long idAzienda;
	private String partitaIva;
	private String ragioneSociale;
	private String viaSede;
	private String nrCivicoSede;
	private long comuneSede;
	private String mail;
	private String mailPec;
	private String cfUtenteAssociato;
	private String codiceFiscaleResponsabile;
	private String tipo;
	private String tipoEnte;
	private boolean attiva;
	private boolean aggOperatori;

	private List<ProfiloUtenteCittadino> profiliUtenteAssociati;

	/**
	 * Form ricerca
	 */
	private String codiceFiscaleDelegato;
	private String nomeDelegato;
	private String cognomeDelegato;
	private Set<ProfiloUtenteCittadino> profiliUtenteAssociatiSearch;

	/**
	 * @return the partitaIva
	 */
	public String getPartitaIva() {
		return partitaIva;
	}

	/**
	 * @param partitaIva the partitaIva to set
	 */
	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}

	/**
	 * @return the ragioneSociale
	 */
	public String getRagioneSociale() {
		return ragioneSociale;
	}

	/**
	 * @param ragioneSociale the ragioneSociale to set
	 */
	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	/**
	 * @return the viaSede
	 */
	public String getViaSede() {
		return viaSede;
	}

	/**
	 * @param viaSede the viaSede to set
	 */
	public void setViaSede(String viaSede) {
		this.viaSede = viaSede;
	}

	/**
	 * @return the nrCivicoSede
	 */
	public String getNrCivicoSede() {
		return nrCivicoSede;
	}

	/**
	 * @param nrCivicoSede the nrCivicoSede to set
	 */
	public void setNrCivicoSede(String nrCivicoSede) {
		this.nrCivicoSede = nrCivicoSede;
	}

	/**
	 * @return the comuneSede
	 */
	public long getComuneSede() {
		return comuneSede;
	}

	/**
	 * @param comuneSede the comuneSede to set
	 */
	public void setComuneSede(long comuneSede) {
		this.comuneSede = comuneSede;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return the mailPec
	 */
	public String getMailPec() {
		return mailPec;
	}

	/**
	 * @param mailPec the mailPec to set
	 */
	public void setMailPec(String mailPec) {
		this.mailPec = mailPec;
	}

	/**
	 * @return the cfUtenteAssociato
	 */
	public String getCfUtenteAssociato() {
		return cfUtenteAssociato;
	}

	/**
	 * @param cfUtenteAssociato the cfUtenteAssociato to set
	 */
	public void setCfUtenteAssociato(String cfUtenteAssociato) {
		this.cfUtenteAssociato = cfUtenteAssociato;
	}

	/**
	 * @return the profiliUtenteAssociati
	 */
	public List<ProfiloUtenteCittadino> getProfiliUtenteAssociati() {
		return profiliUtenteAssociati;
	}

	/**
	 * @param profiliUtenteAssociati the profiliUtenteAssociati to set
	 */
	public void setProfiliUtenteAssociati(List<ProfiloUtenteCittadino> profiliUtenteAssociati) {
		this.profiliUtenteAssociati = profiliUtenteAssociati;
	}

	/**
	 * @return the idAzienda
	 */
	public long getIdAzienda() {
		return idAzienda;
	}

	/**
	 * @param idAzienda the idAzienda to set
	 */
	public void setIdAzienda(long idAzienda) {
		this.idAzienda = idAzienda;
	}

	/**
	 * @return the attiva
	 */
	public boolean isAttiva() {
		return attiva;
	}

	/**
	 * @param attiva the attiva to set
	 */
	public void setAttiva(boolean attiva) {
		this.attiva = attiva;
	}

	/**
	 * @return the codiceFiscaleResponsabile
	 */
	public String getCodiceFiscaleResponsabile() {
		return codiceFiscaleResponsabile;
	}

	/**
	 * @param codiceFiscaleResponsabile the codiceFiscaleResponsabile to set
	 */
	public void setCodiceFiscaleResponsabile(String codiceFiscaleResponsabile) {
		this.codiceFiscaleResponsabile = codiceFiscaleResponsabile;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the codiceFiscaleDelegato
	 */
	public String getCodiceFiscaleDelegato() {
		return codiceFiscaleDelegato;
	}

	/**
	 * @param codiceFiscaleDelegato the codiceFiscaleDelegato to set
	 */
	public void setCodiceFiscaleDelegato(String codiceFiscaleDelegato) {
		this.codiceFiscaleDelegato = codiceFiscaleDelegato;
	}

	/**
	 * @return the nomeDelegato
	 */
	public String getNomeDelegato() {
		return nomeDelegato;
	}

	/**
	 * @param nomeDelegato the nomeDelegato to set
	 */
	public void setNomeDelegato(String nomeDelegato) {
		this.nomeDelegato = nomeDelegato;
	}

	/**
	 * @return the cognomeDelegato
	 */
	public String getCognomeDelegato() {
		return cognomeDelegato;
	}

	/**
	 * @param cognomeDelegato the cognomeDelegato to set
	 */
	public void setCognomeDelegato(String cognomeDelegato) {
		this.cognomeDelegato = cognomeDelegato;
	}

	/**
	 * @return the profiliUtenteAssociatiSearch
	 */
	public Set<ProfiloUtenteCittadino> getProfiliUtenteAssociatiSearch() {
		return profiliUtenteAssociatiSearch;
	}

	/**
	 * @param profiliUtenteAssociatiSearch the profiliUtenteAssociatiSearch to set
	 */
	public void setProfiliUtenteAssociatiSearch(Set<ProfiloUtenteCittadino> profiliUtenteAssociatiSearch) {
		this.profiliUtenteAssociatiSearch = profiliUtenteAssociatiSearch;
	}

	/**
	 * @return the aggOperatori
	 */
	public boolean isAggOperatori() {
		return aggOperatori;
	}

	/**
	 * @param aggOperatori the aggOperatori to set
	 */
	public void setAggOperatori(boolean aggOperatori) {
		this.aggOperatori = aggOperatori;
	}


	public String getTipoEnte() {
		return tipoEnte;
	}

	public void setTipoEnte(String tipoEnte) {
		this.tipoEnte = tipoEnte;
	}
}
