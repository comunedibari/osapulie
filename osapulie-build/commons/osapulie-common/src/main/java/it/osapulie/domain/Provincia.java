/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 * 
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the tb_provincia database table.
 * 
 * @author Gianluca Pindinelli
 */
@Entity
@Table(name = "tb_provincia")
public class Provincia extends AbstractPersistable<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4327466579135523191L;

	@Column(name = "codiceNUTS1", length = 45)
	private String codiceNUTS1;

	@Column(name = "codiceNUTS2", length = 45)
	private String codiceNUTS2;

	@Column(name = "codiceNUTS3", length = 45)
	private String codiceNUTS3;

	@Column(name = "codiceRegione", length = 11)
	private int codiceRegione;

	@Column(name = "codiceRipartizione", length = 11)
	private int codiceRipartizione;

	@Column(name = "denominazioneProvincia", length = 75)
	private String denominazioneProvincia;

	@Column(name = "denominazioneRegione", length = 75)
	private String denominazioneRegione;

	@Column(name = "denominazioneRegioneM", length = 45)
	private String denominazioneRegioneM;

	@Column(name = "ripartizioneGeografica", length = 75)
	private String ripartizioneGeografica;

	@Column(name = "ripartizioneGeograficaM", length = 45)
	private String ripartizioneGeograficaM;

	@Column(name = "sigla", length = 2)
	private String sigla;

	// bi-directional many-to-one association to Comune
	@OneToMany(mappedBy = "provincia", cascade = CascadeType.ALL, orphanRemoval = false)
	@JoinColumn(name = "idProvincia")
	@JsonIgnore
	private List<Comune> comuni;

	public Provincia() {
	}

	public String getCodiceNUTS1() {
		return this.codiceNUTS1;
	}

	public void setCodiceNUTS1(String codiceNUTS1) {
		this.codiceNUTS1 = codiceNUTS1;
	}

	public String getCodiceNUTS2() {
		return this.codiceNUTS2;
	}

	public void setCodiceNUTS2(String codiceNUTS2) {
		this.codiceNUTS2 = codiceNUTS2;
	}

	public String getCodiceNUTS3() {
		return this.codiceNUTS3;
	}

	public void setCodiceNUTS3(String codiceNUTS3) {
		this.codiceNUTS3 = codiceNUTS3;
	}

	public int getCodiceRegione() {
		return this.codiceRegione;
	}

	public void setCodiceRegione(int codiceRegione) {
		this.codiceRegione = codiceRegione;
	}

	public int getCodiceRipartizione() {
		return this.codiceRipartizione;
	}

	public void setCodiceRipartizione(int codiceRipartizione) {
		this.codiceRipartizione = codiceRipartizione;
	}

	public String getDenominazioneProvincia() {
		return this.denominazioneProvincia;
	}

	public void setDenominazioneProvincia(String denominazioneProvincia) {
		this.denominazioneProvincia = denominazioneProvincia;
	}

	public String getDenominazioneRegione() {
		return this.denominazioneRegione;
	}

	public void setDenominazioneRegione(String denominazioneRegione) {
		this.denominazioneRegione = denominazioneRegione;
	}

	public String getDenominazioneRegioneM() {
		return this.denominazioneRegioneM;
	}

	public void setDenominazioneRegioneM(String denominazioneRegioneM) {
		this.denominazioneRegioneM = denominazioneRegioneM;
	}

	public String getRipartizioneGeografica() {
		return this.ripartizioneGeografica;
	}

	public void setRipartizioneGeografica(String ripartizioneGeografica) {
		this.ripartizioneGeografica = ripartizioneGeografica;
	}

	public String getRipartizioneGeograficaM() {
		return this.ripartizioneGeograficaM;
	}

	public void setRipartizioneGeograficaM(String ripartizioneGeograficaM) {
		this.ripartizioneGeograficaM = ripartizioneGeograficaM;
	}

	public String getSigla() {
		return this.sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	public List<Comune> getComuni() {
		return comuni;
	}

	public void setComuni(List<Comune> comuni) {
		this.comuni = comuni;
	}
}