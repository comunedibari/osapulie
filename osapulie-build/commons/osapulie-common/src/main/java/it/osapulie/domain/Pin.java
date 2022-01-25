/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 * 
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * @author Gianluca Pindinelli
 * 
 */
@Entity
@Table(name = "tb_pin")
public class Pin extends AbstractPersistable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5810821780278525661L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_richiesta", nullable = true, length = 128)
	private Date dataRichiesta;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_attivazione", nullable = true, length = 128)
	private Date dataAttivazione;

	@Column(name = "pin")
	private String pin;

	@Column(name = "stato", length = 10)
	private String stato;

	@Column(name = "note", length = 10000)
	private String note;

	@ManyToOne
	@JoinColumn(name = "fk_cittadino", nullable = false)
	private ProfiloUtenteCittadino profiloUtenteCittadino;

	@JoinColumn(name = "fk_comune_isa", nullable = false)
	private ComuneISA comuneIsa;

	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	public Date getDataRichiesta() {
		return dataRichiesta;
	}

	public void setDataRichiesta(Date dataRichiesta) {
		this.dataRichiesta = dataRichiesta;
	}

	public Date getDataAttivazione() {
		return dataAttivazione;
	}

	public void setDataAttivazione(Date dataAttivazione) {
		this.dataAttivazione = dataAttivazione;
	}

	public ComuneISA getComuneIsa() {
		return comuneIsa;
	}

	public void setComuneIsa(ComuneISA comuneIsa) {
		this.comuneIsa = comuneIsa;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public ProfiloUtenteCittadino getProfiloUtenteCittadino() {
		return profiloUtenteCittadino;
	}

	public void setProfiloUtenteCittadino(ProfiloUtenteCittadino profiloUtenteCittadino) {
		this.profiloUtenteCittadino = profiloUtenteCittadino;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
