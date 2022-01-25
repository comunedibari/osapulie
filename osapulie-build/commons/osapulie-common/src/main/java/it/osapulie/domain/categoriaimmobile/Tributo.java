/************************************************************************************
 * Copyright (c) 2011, 2014 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.domain.categoriaimmobile;

import it.osapulie.domain.servizi.Servizio;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * Anagrafica di un tributo.
 *
 * @author Gianluca Pindinelli
 */
@Entity
@Table(name = "tb_tributo")
public class Tributo extends AbstractPersistable<Long> {

	/**
	 *
	 */
	private static final long serialVersionUID = 909304077152095864L;

	@Column(name = "nome", length = 45)
	private String nome;

	@OneToOne
	@JoinColumn(name = "fk_servizio", nullable = false, updatable = false)
	private Servizio servizio;

	@OneToMany(mappedBy = "tributo")
	private List<CategoriaImmobileTributo> categorieImmobiliTributi;

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
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
	 * @return the categorieImmobiliTributi
	 */
	public List<CategoriaImmobileTributo> getCategorieImmobiliTributi() {
		return categorieImmobiliTributi;
	}

	/**
	 * @param categorieImmobiliTributi the categorieImmobiliTributi to set
	 */
	public void setCategorieImmobiliTributi(List<CategoriaImmobileTributo> categorieImmobiliTributi) {
		this.categorieImmobiliTributi = categorieImmobiliTributi;
	}
}
