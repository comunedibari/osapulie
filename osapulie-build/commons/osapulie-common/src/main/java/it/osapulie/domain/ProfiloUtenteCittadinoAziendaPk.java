/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Tabella di relazione tra {@link ProfiloUtenteCittadino} e {@link Azienda}.
 *
 * @author Gianluca Pindinelli
 *
 */
@Embeddable
public class ProfiloUtenteCittadinoAziendaPk implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -1921732718576518984L;

	@Column(name = "fk_profiloutentecittadino")
	private Long idProfiloUtenteCittadino;

	@Column(name = "fk_azienda")
	private Long idAzienda;

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return (int) (idProfiloUtenteCittadino + idAzienda);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ProfiloUtenteCittadinoAziendaPk) {
			ProfiloUtenteCittadinoAziendaPk otherId = (ProfiloUtenteCittadinoAziendaPk) obj;
			return (otherId.idProfiloUtenteCittadino == this.idProfiloUtenteCittadino) && (otherId.idAzienda == this.idAzienda);
		}
		return false;
	}

	/**
	 * @return the idProfiloUtenteCittadino
	 */
	public Long getIdProfiloUtenteCittadino() {
		return idProfiloUtenteCittadino;
	}

	/**
	 * @param idProfiloUtenteCittadino the idProfiloUtenteCittadino to set
	 */
	public void setIdProfiloUtenteCittadino(Long idProfiloUtenteCittadino) {
		this.idProfiloUtenteCittadino = idProfiloUtenteCittadino;
	}

	/**
	 * @return the idAzienda
	 */
	public Long getIdAzienda() {
		return idAzienda;
	}

	/**
	 * @param idAzienda the idAzienda to set
	 */
	public void setIdAzienda(Long idAzienda) {
		this.idAzienda = idAzienda;
	}

}
