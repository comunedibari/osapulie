/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.domain.fascicoloutente;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import it.osapulie.domain.Azienda;
import it.osapulie.domain.ProfiloUtenteCittadino;

/**
 * Ogni {@link ProfiloUtenteCittadino} ha associato un {@link FascicoloUtente} che traccia le
 * richieste ai servizi erogati da ogni comune.
 *
 * @author Mario Scalas
 */
@Entity
@Table(name = "tb_fascicolo_utente")
public class FascicoloUtente extends AbstractPersistable<Long> {

	private static final long serialVersionUID = -8731308377602642010L;

	@JoinColumn(name = "fk_cittadino", nullable = false, updatable = false)
	@OneToOne
	private ProfiloUtenteCittadino cittadino;

	@JoinColumn(name = "fk_azienda", nullable = false, updatable = false)
	@OneToOne
	private Azienda azienda;

	@OrderBy("dataRichiesta DESC")
	@OneToMany(mappedBy = "fascicolo", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RichiestaServizio> richieste;

	public ProfiloUtenteCittadino getCittadino() {
		return cittadino;
	}

	public void setCittadino(ProfiloUtenteCittadino cittadino) {
		this.cittadino = cittadino;
	}

	public List<RichiestaServizio> getRichieste() {
		return richieste;
	}

	public void setRichieste(List<RichiestaServizio> richieste) {
		this.richieste = richieste;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FascicoloUtente [cittadino=" + cittadino + ", richieste=" + richieste + "]";
	}

	/**
	 * Aggiungi una nuova richiesta servizio al fascicolo.
	 *
	 * @param richiesta
	 */
	public void aggiungiRichiesta(RichiestaServizio richiesta) {
		getRichieste().add(richiesta);
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
