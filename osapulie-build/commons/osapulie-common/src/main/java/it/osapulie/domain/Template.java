/************************************************************************************
 * Copyright (c) 2011, 2016 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import it.osapulie.domain.servizi.Servizio;

/**
 * Template di documento (dichiarazione, certificato, ecc.) associato a {@link ComuneISA} e
 * {@link Servizio}.
 *
 * @author Gianluca Pindinelli
 *
 */
@Entity
@Table(name = "tb_template")
public class Template extends AbstractPersistable<Long> {

	/**
	 *
	 */
	private static final long serialVersionUID = -6107853982629728205L;

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Column(name = "nome")
	private String nome;

	@Column(name = "contenuto")
	private String contenuto;

	@Column(name = "tipo")
	private String tipo;

	@ManyToOne
	@JoinColumn(name = "fk_comuneisa", updatable = false, insertable = false, referencedColumnName = "ID")
	private ComuneISA comuneISA;

	@ManyToOne
	@JoinColumn(name = "fk_servizio", updatable = false, insertable = false, referencedColumnName = "ID")
	private Servizio servizio;

	@Column(name = "attivo", nullable = false)
	private Boolean attivo;

	@ManyToOne
	@JoinColumn(name = "fk_template", insertable = false, updatable = false)
	private Template parent;

	@JoinColumn(name = "fk_template")
	@OneToMany(mappedBy = "parent")
	private List<Template> subTemplates;

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
	 * @return the contenuto
	 */
	public String getContenuto() {
		return contenuto;
	}

	/**
	 * @param contenuto the contenuto to set
	 */
	public void setContenuto(String contenuto) {
		this.contenuto = contenuto;
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
	 * @return the attivo
	 */
	public Boolean getAttivo() {
		return attivo;
	}

	/**
	 * @param attivo the attivo to set
	 */
	public void setAttivo(Boolean attivo) {
		this.attivo = attivo;
	}

	/**
	 * @return the parent
	 */
	public Template getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(Template parent) {
		this.parent = parent;
	}

	/**
	 * @return the subTemplates
	 */
	public List<Template> getSubTemplates() {
		return subTemplates;
	}

	/**
	 * @param subTemplates the subTemplates to set
	 */
	public void setSubTemplates(List<Template> subTemplates) {
		this.subTemplates = subTemplates;
	}

}
