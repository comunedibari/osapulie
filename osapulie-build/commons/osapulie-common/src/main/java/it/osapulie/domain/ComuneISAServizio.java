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
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import it.osapulie.domain.servizi.Servizio;

/**
 * Tabella di relazione tra {@link ComuneISA} e {@link Servizio}.
 *
 * @author Gianluca Pindinelli
 */
@Entity
@Table(name = "tb_comuneisa_servizio")
@IdClass(ComuneISAServizioId.class)
public class ComuneISAServizio implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -7837661507351873181L;

	@Id
	@Column(name = "fk_comuneisa")
	private long idComuneISA;

	@Id
	@Column(name = "fk_servizio")
	private long idServizio;

	@ManyToOne
	@JoinColumn(name = "fk_comuneisa", updatable = false, insertable = false, referencedColumnName = "ID")
	private ComuneISA comuneISA;

	@ManyToOne
	@JoinColumn(name = "fk_servizio", updatable = false, insertable = false, referencedColumnName = "ID")
	private Servizio servizio;

	@Column(name = "autenticazione_forte")
	private boolean autenticazioneForte;

	@Column(name = "livello_autenticazione")
	private Integer livelloAutenticazione;

	@Column(name = "attivo")
	private boolean attivo;

	/**
	 * @return the autenticazioneForte
	 */
	public boolean isAutenticazioneForte() {
		return autenticazioneForte;
	}

	/**
	 * @param autenticazioneForte the autenticazioneForte to set
	 */
	public void setAutenticazioneForte(boolean autenticazioneForte) {
		this.autenticazioneForte = autenticazioneForte;
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
	 * @return the idComuneISA
	 */
	public long getIdComuneISA() {
		return idComuneISA;
	}

	/**
	 * @param idComuneISA the idComuneISA to set
	 */
	public void setIdComuneISA(long idComuneISA) {
		this.idComuneISA = idComuneISA;
	}

	/**
	 * @return the idServizio
	 */
	public long getIdServizio() {
		return idServizio;
	}

	/**
	 * @param idServizio the idServizio to set
	 */
	public void setIdServizio(long idServizio) {
		this.idServizio = idServizio;
	}

	/**
	 * @return the attivo
	 */
	public boolean isAttivo() {
		return attivo;
	}

	/**
	 * @param attivo the attivo to set
	 */
	public void setAttivo(boolean attivo) {
		this.attivo = attivo;
	}

	/**
	 * @return the livelloAutenticazione
	 */
	public Integer getLivelloAutenticazione() {
		return livelloAutenticazione;
	}

	/**
	 * @param livelloAutenticazione the livelloAutenticazione to set
	 */
	public void setLivelloAutenticazione(Integer livelloAutenticazione) {
		this.livelloAutenticazione = livelloAutenticazione;
	}
}
