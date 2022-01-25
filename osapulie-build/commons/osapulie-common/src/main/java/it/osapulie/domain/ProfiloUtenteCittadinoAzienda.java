/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Gianluca Pindinelli
 *
 */
@Entity
@Table(name = "tb_profiloutentecittadino_azienda")
public class ProfiloUtenteCittadinoAzienda {

	@EmbeddedId
	private ProfiloUtenteCittadinoAziendaPk pk;

	@ManyToOne
	@JoinColumn(name = "fk_profiloutentecittadino", columnDefinition = "id")
	@MapsId("idProfiloUtenteCittadino")
	private ProfiloUtenteCittadino profiloUtenteCittadino;


	@ManyToOne
	@JoinColumn(name = "fk_azienda", columnDefinition = "id")
	@MapsId("idAzienda")
	private Azienda azienda;

	@Column(name = "attivo")
	private boolean attivo;

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
	 * @return the pk
	 */
	public ProfiloUtenteCittadinoAziendaPk getPk() {
		return pk;
	}

	/**
	 * @param pk the pk to set
	 */
	public void setPk(ProfiloUtenteCittadinoAziendaPk pk) {
		this.pk = pk;
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
