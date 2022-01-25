/**
 * Copyright (c) 2011 Links Management & Technology S.p.A.
 */

package it.osapulie.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;

/**
 * @author Mario Scalas
 */
@Embeddable
public class Indirizzo {

	@Column(name = "ind_via", nullable = true, length = 256)
	private String via;

	@Column(name = "ind_nr_civico", nullable = true, length = 16)
	private String nrCivico;

	@JoinColumn(name = "fk_ind_comune", nullable = true)
	private Comune comune;

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getNrCivico() {
		return nrCivico;
	}

	public void setNrCivico(String nrCivico) {
		this.nrCivico = nrCivico;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comune == null) ? 0 : comune.hashCode());
		result = prime * result + ((nrCivico == null) ? 0 : nrCivico.hashCode());
		result = prime * result + ((via == null) ? 0 : via.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Indirizzo other = (Indirizzo) obj;
		if (comune == null) {
			if (other.comune != null) {
				return false;
			}
		}
		else if (!comune.equals(other.comune)) {
			return false;
		}
		if (nrCivico == null) {
			if (other.nrCivico != null) {
				return false;
			}
		}
		else if (!nrCivico.equals(other.nrCivico)) {
			return false;
		}
		if (via == null) {
			if (other.via != null) {
				return false;
			}
		}
		else if (!via.equals(other.via)) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Indirizzo [via=" + via + ", nrCivico=" + nrCivico + ", comuneISA=" + comune + "]";
	}

	public Comune getComune() {
		return comune;
	}

	public void setComune(Comune comune) {
		this.comune = comune;
	}
}
