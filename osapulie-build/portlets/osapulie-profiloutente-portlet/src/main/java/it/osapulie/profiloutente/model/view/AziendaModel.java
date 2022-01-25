/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.profiloutente.model.view;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import it.osapulie.domain.Azienda;
import it.osapulie.domain.Delega;
import it.osapulie.domain.ProfiloUtenteCittadino;

/**
 * Classe model per il binding delle informazioni riguardanti il profilo aziendale di un utente.
 *
 * @author Gianluca Pindinelli
 *
 */
public class AziendaModel implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -4319364055469795846L;
	private String tipoAzienda;
	private String tipoEnte;
	private long idAzienda;

	private String partitaIva;
	private String mail;
	private String mailPec;
	private String ragioneSociale;
	private String viaSede;
	private String nrCivicoSede;
	private long comuneSede;
	private String comuneSedeString;

	private List<Azienda> aziende;
	private List<ProfiloUtenteCittadino> profiliUtenteAssociati;

	private boolean aziendaInUso;
	private boolean aziendaAttiva;
	private boolean aziendaAggOperatori;

	private String cfUtenteAssociato;

	/**
	 * <code>true</code> se la portlet di stato utente deve visualizzare il profilo selezionato dal
	 * cittadino, <code>false</code> altrimenti.
	 */
	private boolean profiloEnable;
	/**
	 * <code>true</code> se l'utente loggato ha scelto un'azienda/professionista come profilo
	 * utente, <code>false</code> altrimenti.
	 */
	private boolean profiloAzienda;
	private boolean profiloDelega;

	private Delega delega;

	/**
	 * Form ricerca
	 */
	private String codiceFiscaleDelegato;
	private String nomeDelegato;
	private String cognomeDelegato;
	private Set<ProfiloUtenteCittadino> profiliUtenteAssociatiSearch;

	/**
	 * @return the tipoAzienda
	 */
	public String getTipoAzienda() {
		return tipoAzienda;
	}

	/**
	 * @param tipoAzienda the tipoAzienda to set
	 */
	public void setTipoAzienda(String tipoAzienda) {
		this.tipoAzienda = tipoAzienda;
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
	 * @return the comuneSedeString
	 */
	public String getComuneSedeString() {
		return comuneSedeString;
	}

	/**
	 * @param comuneSedeString the comuneSedeString to set
	 */
	public void setComuneSedeString(String comuneSedeString) {
		this.comuneSedeString = comuneSedeString;
	}

	/**
	 * @return the aziende
	 */
	public List<Azienda> getAziende() {
		return aziende;
	}

	/**
	 * @param aziende the aziende to set
	 */
	public void setAziende(List<Azienda> aziende) {
		this.aziende = aziende;
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
	 * @return the aziendaInUso
	 */
	public boolean isAziendaInUso() {
		return aziendaInUso;
	}

	/**
	 * @param aziendaInUso the aziendaInUso to set
	 */
	public void setAziendaInUso(boolean aziendaInUso) {
		this.aziendaInUso = aziendaInUso;
	}

	/**
	 * @return the aziendaAttiva
	 */
	public boolean isAziendaAttiva() {
		return aziendaAttiva;
	}

	/**
	 * @param aziendaAttiva the aziendaAttiva to set
	 */
	public void setAziendaAttiva(boolean aziendaAttiva) {
		this.aziendaAttiva = aziendaAttiva;
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
	 * @return the profiloEnable
	 */
	public boolean isProfiloEnable() {
		return profiloEnable;
	}

	/**
	 * @param profiloEnable the profiloEnable to set
	 */
	public void setProfiloEnable(boolean profiloEnable) {
		this.profiloEnable = profiloEnable;
	}

	/**
	 * @return the profiloAzienda
	 */
	public boolean isProfiloAzienda() {
		return profiloAzienda;
	}

	/**
	 * @param profiloAzienda the profiloAzienda to set
	 */
	public void setProfiloAzienda(boolean profiloAzienda) {
		this.profiloAzienda = profiloAzienda;
	}

	/**
	 * @return the profiloDelega
	 */
	public boolean isProfiloDelega() {
		return profiloDelega;
	}

	/**
	 * @param profiloDelega the profiloDelega to set
	 */
	public void setProfiloDelega(boolean profiloDelega) {
		this.profiloDelega = profiloDelega;
	}

	/**
	 * @return the delega
	 */
	public Delega getDelega() {
		return delega;
	}

	/**
	 * @param delega the delega to set
	 */
	public void setDelega(Delega delega) {
		this.delega = delega;
	}

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
	 * @return the aziendaAggOperatori
	 */
	public boolean isAziendaAggOperatori() {
		return aziendaAggOperatori;
	}

	/**
	 * @param aziendaAggOperatori the aziendaAggOperatori to set
	 */
	public void setAziendaAggOperatori(boolean aziendaAggOperatori) {
		this.aziendaAggOperatori = aziendaAggOperatori;
	}

	public String getTipoEnte() {
		return tipoEnte;
	}

	public void setTipoEnte(String tipoEnte) {
		this.tipoEnte = tipoEnte;
	}
}
