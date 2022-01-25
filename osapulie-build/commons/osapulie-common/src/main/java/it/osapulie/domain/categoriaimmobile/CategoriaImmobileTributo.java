/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.domain.categoriaimmobile;

import it.osapulie.domain.ComuneISA;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Tabella di relazione tra {@link CategoriaImmobile} e {@link Tributo}.
 *
 * @author Gianluca Pindinelli
 */
@Entity
@Table(name = "tb_categoriaimmobile_tributo")
public class CategoriaImmobileTributo implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -5422494829464488959L;

	@EmbeddedId
	private CategoriaImmobileTributoId pk;

	@ManyToOne
	@JoinColumn(name = "fk_tributo", updatable = false, insertable = false, referencedColumnName = "ID")
	private Tributo tributo;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = "fk_categoriaimmobile", updatable = false, insertable = false, referencedColumnName = "ID")
	private CategoriaImmobile categoriaImmobile;

	@ManyToOne
	@JoinColumn(name = "fk_comuneisa", updatable = false, insertable = false, referencedColumnName = "ID")
	private ComuneISA comuneISA;
	@JsonIgnore
	@OneToMany(mappedBy = "categoriaImmobileTributo", cascade = { CascadeType.ALL }, orphanRemoval=true)
	private List<Agevolazione> agevolazioni;
	@JsonIgnore
	@OneToMany(mappedBy = "categoriaImmobileTributo", cascade = { CascadeType.ALL }, orphanRemoval=true)
	private List<Detrazione> detrazioni;
	@JsonIgnore
	@OneToMany(mappedBy = "categoriaImmobileTributo", cascade = { CascadeType.ALL }, orphanRemoval=true)
	private List<Esenzione> esenzioni;

	@Column(name = "aliquota", nullable = true)
	private Double aliquota;

	@Column(name = "anno", nullable = true)
	private Integer anno;

	@Column(name = "codice_tributo", length = 4, nullable = true)
	private Integer codiceTributo;

	/**
	 * @return the categoriaImmobile
	 */
	public CategoriaImmobile getCategoriaImmobile() {
		return categoriaImmobile;
	}

	/**
	 * @param categoriaImmobile the categoriaImmobile to set
	 */
	public void setCategoriaImmobile(CategoriaImmobile categoriaImmobile) {
		this.categoriaImmobile = categoriaImmobile;
	}

	/**
	 * @return the pk
	 */
	public CategoriaImmobileTributoId getPk() {
		return pk;
	}

	/**
	 * @param pk the pk to set
	 */
	public void setPk(CategoriaImmobileTributoId pk) {
		this.pk = pk;
	}

	/**
	 * @return the tributo
	 */
	public Tributo getTributo() {
		return tributo;
	}

	/**
	 * @param tributo the tributo to set
	 */
	public void setTributo(Tributo tributo) {
		this.tributo = tributo;
	}

	/**
	 * @return the aliquota
	 */
	public Double getAliquota() {
		return aliquota;
	}

	/**
	 * @param aliquota the aliquota to set
	 */
	public void setAliquota(Double aliquota) {
		this.aliquota = aliquota;
	}

	/**
	 * @return the anno
	 */
	public Integer getAnno() {
		return anno;
	}

	/**
	 * @param anno the anno to set
	 */
	public void setAnno(Integer anno) {
		this.anno = anno;
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
	 * @return the esenzioni
	 */
	public List<Esenzione> getEsenzioni() {
		return esenzioni;
	}

	/**
	 * @param esenzioni the esenzioni to set
	 */
	public void setEsenzioni(List<Esenzione> esenzioni) {
		this.esenzioni = esenzioni;
	}

	/**
	 * @return the agevolazioni
	 */
	public List<Agevolazione> getAgevolazioni() {
		return agevolazioni;
	}

	/**
	 * @param agevolazioni the agevolazioni to set
	 */
	public void setAgevolazioni(List<Agevolazione> agevolazioni) {
		this.agevolazioni = agevolazioni;
	}

	/**
	 * @return the detrazioni
	 */
	public List<Detrazione> getDetrazioni() {
		return detrazioni;
	}

	/**
	 * @param detrazioni the detrazioni to set
	 */
	public void setDetrazioni(List<Detrazione> detrazioni) {
		this.detrazioni = detrazioni;
	}

	/**
	 * @return the codiceTributo
	 */
	public Integer getCodiceTributo() {
		return codiceTributo;
	}

	/**
	 * @param codiceTributo the codiceTributo to set
	 */
	public void setCodiceTributo(Integer codiceTributo) {
		this.codiceTributo = codiceTributo;
	}
}
