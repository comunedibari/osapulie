/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.AbstractPersistable;

import it.osapulie.domain.servizi.Servizio;

/**
 * @author Gianluca Pindinelli
 *
 */
@Entity
@Table(name = "tb_backup")
public class Backup extends AbstractPersistable<Long> {

	/**
	 *
	 */
	private static final long serialVersionUID = 6930841488830679195L;

	/**
	 * Campo JSON per la definizione del contenuto di una bozza.
	 */
	@Column(name = "contenuto", nullable = false)
	private String contenuto;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_creazione", nullable = false)
	private Date dataCreazione;

	@OneToOne
	@JoinColumn(name = "fk_comune_isa")
	private ComuneISA comuneISA;

	@OneToOne
	@JoinColumn(name = "fk_servizio")
	private Servizio servizio;

	@JoinColumn(name = "fk_profiloutentecittadino")
	private ProfiloUtenteCittadino profiloUtenteCittadino;

	@OneToOne
	@JoinColumn(name = "fk_azienda")
	private Azienda azienda;

	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	/**
	 * @return the contenuto
	 */
	public String getContenuto() {
		return contenuto;
	}

	/**
	 * @param contenuto the contenuto to set
	 */
	public void setContenuto(String contenuto) {
		this.contenuto = contenuto;
	}

	/**
	 * @return the dataCreazione
	 */
	public Date getDataCreazione() {
		return dataCreazione;
	}

	/**
	 * @param dataCreazione the dataCreazione to set
	 */
	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	/**
	 * @return the comuneISA
	 */
	public ComuneISA getComuneISA() {
		return comuneISA;
	}

	/**
	 * @param comuneISA the comuneISA to set
	 */
	public void setComuneISA(ComuneISA comuneISA) {
		this.comuneISA = comuneISA;
	}

	/**
	 * @return the servizio
	 */
	public Servizio getServizio() {
		return servizio;
	}

	/**
	 * @param servizio the servizio to set
	 */
	public void setServizio(Servizio servizio) {
		this.servizio = servizio;
	}

	/**
	 * @return the profiloUtenteCittadino
	 */
	public ProfiloUtenteCittadino getProfiloUtenteCittadino() {
		return profiloUtenteCittadino;
	}

	/**
	 * @param profiloUtenteCittadino the profiloUtenteCittadino to set
	 */
	public void setProfiloUtenteCittadino(ProfiloUtenteCittadino profiloUtenteCittadino) {
		this.profiloUtenteCittadino = profiloUtenteCittadino;
	}

	/**
	 * @return the azienda
	 */
	public Azienda getAzienda() {
		return azienda;
	}

	/**
	 * @param azienda the azienda to set
	 */
	public void setAzienda(Azienda azienda) {
		this.azienda = azienda;
	}

}
