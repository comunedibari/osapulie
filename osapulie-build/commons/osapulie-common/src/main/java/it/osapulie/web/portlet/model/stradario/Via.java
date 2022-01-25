/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.web.portlet.model.stradario;

import java.io.Serializable;
import java.util.List;

/**
 * @author Gianluca Pindinelli
 *
 */
public class Via implements Serializable, Comparable<Via> {

	/**
	 *
	 */
	private static final long serialVersionUID = 8332335344073198225L;

	private String identificativo;
	private String denominazione;
	private String localita;
	private List<Civico> civici;

	/**
	 * @return the identificativo
	 */
	public String getIdentificativo() {
		return identificativo;
	}

	/**
	 * @param identificativo the identificativo to set
	 */
	public void setIdentificativo(String identificativo) {
		this.identificativo = identificativo;
	}

	/**
	 * @return the denominazione
	 */
	public String getDenominazione() {
		return denominazione;
	}

	/**
	 * @param denominazione the denominazione to set
	 */
	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	/**
	 * @return the civici
	 */
	public List<Civico> getCivici() {
		return civici;
	}

	/**
	 * @param civici the civici to set
	 */
	public void setCivici(List<Civico> civici) {
		this.civici = civici;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Via o) {
		return this.getDenominazione().compareTo(o.getDenominazione());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if ((obj == null) || (obj.getClass() != this.getClass())) {
			return false;
		}
		Via via = (Via) obj;
		String identificativo2 = via.getIdentificativo();
		String localita2 = via.getLocalita();
		if (getIdentificativo() != null && getLocalita() != null && identificativo2 != null && localita2 != null) {
			return this.getIdentificativo().equals(identificativo2) && this.getLocalita().equals(localita2);
		}
		else {
			if ((getIdentificativo() != null && identificativo2 != null) && (getLocalita() == null && localita2 == null)) {
				return this.getIdentificativo().equals(identificativo2);
			}
			else if ((getIdentificativo() == null && identificativo2 == null) && (getLocalita() != null && localita2 != null)) {
				return this.getLocalita().equals(localita2);
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int combinedHashCode = 0;
		if (getIdentificativo() != null && getLocalita() != null) {
			combinedHashCode = getIdentificativo().hashCode() + getLocalita().hashCode();
		}
		else {
			if (getIdentificativo() != null && getLocalita() == null) {
				combinedHashCode = getIdentificativo().hashCode();
			}
			else if (getIdentificativo() == null && getLocalita() != null) {
				combinedHashCode = getLocalita().hashCode();
			}
		}
		return prime + combinedHashCode;
	}

	/**
	 * @return the localita
	 */
	public String getLocalita() {
		return localita;
	}

	/**
	 * @param localita the localita to set
	 */
	public void setLocalita(String localita) {
		this.localita = localita;
	}

}
