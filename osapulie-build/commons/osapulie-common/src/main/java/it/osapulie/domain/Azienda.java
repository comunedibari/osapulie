/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import it.osapulie.domain.fascicoloutente.FascicoloUtente;

/**
 * @author Mario Scalas
 * @author Gianluca Pindinelli
 */
@Entity
@Table(name = "tb_azienda")
public class Azienda extends AbstractPersistable<Long> {

	/**
	 *
	 */
	private static final long serialVersionUID = -2605584798714862552L;

	@Column(name = "partita_iva", updatable = true, unique = true, nullable = false, length = 16)
	private String partitaIva;

	@Column(name = "ragione_sociale")
	private String ragioneSociale;

	@Embedded
	private Indirizzo sede;

	@Column(name = "mail", nullable = true, length = 128)
	private String mail;

	/**
	 * Campo mail di PEC
	 */
	@Column(name = "mail_pec", nullable = false, length = 128)
	private String mailPec;
	@JsonIgnore
	@OneToMany(mappedBy = "azienda", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProfiloUtenteCittadinoAzienda> profiliUtenteCittadinoAzienda;
	@JsonIgnore
	@OneToMany(mappedBy = "delegato", cascade = CascadeType.ALL)
	private List<Delega> delegheDeleganti;

	@Column(name = "attiva")
	private boolean attiva;

	@OneToOne
	@JoinColumn(name = "fk_profiloutentecittadino", nullable = false, updatable = false)
	private ProfiloUtenteCittadino responsabile;
	@JsonIgnore
	@OneToOne(mappedBy = "azienda")
	private FascicoloUtente fascicoloUtente;

	@Column(name = "tipo", length = 10)
	private String tipo;

	@Column(name = "tipo_ente", length = 200 )
	private String tipoEnte;

	/**
	 * Identifica se il profilo è in uso (per i controlli di modifica/eliminazione).
	 */
	@Transient
	private boolean inUso;
	
	@Column(name = "agg_operatori")
	private boolean aggOperatori;

	public void addProfiloUtenteCittadino(ProfiloUtenteCittadino profiloUtenteCittadino, boolean attivo) {

		ProfiloUtenteCittadinoAzienda association = new ProfiloUtenteCittadinoAzienda();
		association.setAzienda(this);
		association.setProfiloUtenteCittadino(profiloUtenteCittadino);
		if (association.getPk() == null) {
			ProfiloUtenteCittadinoAziendaPk pk = new ProfiloUtenteCittadinoAziendaPk();
			association.setPk(pk);
		}

		association.getPk().setIdAzienda(this.getId());
		association.getPk().setIdProfiloUtenteCittadino(profiloUtenteCittadino.getId());
		association.setAttivo(attivo);

		if (this.profiliUtenteCittadinoAzienda == null) {
			this.profiliUtenteCittadinoAzienda = new ArrayList<ProfiloUtenteCittadinoAzienda>();
		}

		this.profiliUtenteCittadinoAzienda.add(association);

		if (profiloUtenteCittadino.getProfiliUtenteCittadinoAziende() == null) {
			profiloUtenteCittadino.setProfiliUtenteCittadinoAziende(new ArrayList<ProfiloUtenteCittadinoAzienda>());
		}

		profiloUtenteCittadino.getProfiliUtenteCittadinoAziende().add(association);
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
	 * @return the sede
	 */
	public Indirizzo getSede() {
		return sede;
	}

	/**
	 * @param sede the sede to set
	 */
	public void setSede(Indirizzo sede) {
		this.sede = sede;
	}

	/**
	 * @return the delegheDeleganti
	 */
	public List<Delega> getDelegheDeleganti() {
		return delegheDeleganti;
	}

	/**
	 * @param delegheDeleganti the delegheDeleganti to set
	 */
	public void setDelegheDeleganti(List<Delega> delegheDeleganti) {
		this.delegheDeleganti = delegheDeleganti;
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
	 * @return the inUso
	 */
	public boolean isInUso() {
		return inUso;
	}

	/**
	 * @param inUso the inUso to set
	 */
	public void setInUso(boolean inUso) {
		this.inUso = inUso;
	}

	/**
	 * @return the responsabile
	 */
	public ProfiloUtenteCittadino getResponsabile() {
		return responsabile;
	}

	/**
	 * @param responsabile the responsabile to set
	 */
	public void setResponsabile(ProfiloUtenteCittadino responsabile) {
		this.responsabile = responsabile;
	}

	/**
	 * @return the profiliUtenteCittadinoAzienda
	 */
	public List<ProfiloUtenteCittadinoAzienda> getProfiliUtenteCittadinoAzienda() {
		return profiliUtenteCittadinoAzienda;
	}

	/**
	 * @param profiliUtenteCittadinoAzienda the profiliUtenteCittadinoAzienda to set
	 */
	public void setProfiliUtenteCittadinoAzienda(List<ProfiloUtenteCittadinoAzienda> profiliUtenteCittadinoAzienda) {
		this.profiliUtenteCittadinoAzienda = profiliUtenteCittadinoAzienda;
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
	 * @return the fascicoloUtente
	 */
	public FascicoloUtente getFascicoloUtente() {
		return fascicoloUtente;
	}

	/**
	 * @param fascicoloUtente the fascicoloUtente to set
	 */
	public void setFascicoloUtente(FascicoloUtente fascicoloUtente) {
		this.fascicoloUtente = fascicoloUtente;
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

	@Override
	public String toString() {
		return "Azienda [partitaIva=" + partitaIva + ", ragioneSociale=" + ragioneSociale + ", sede=" + sede + ", mail="
				+ mail + ", mailPec=" + mailPec + ", attiva=" + attiva + ", tipo=" + tipo + ", inUso=" + inUso
				+ ", aggOperatori=" + aggOperatori + ", tipoEnte=" + tipoEnte + "]";
	}


	public String getTipoEnte() {
		return tipoEnte;
	}

	public void setTipoEnte(String tipoEnte) {
		this.tipoEnte = tipoEnte;
	}
}
