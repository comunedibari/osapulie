/************************************************************************************
 * Copyright (c) 2011, 2014 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.servizicomune.web.portlet.gestionecategorieimmobili.form;

import java.io.Serializable;
import java.util.List;

/**
 * @author Gianluca Pindinelli
 *
 */
public class GestioneCategorieImmobiliForm implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 3078276398590605257L;

	private Integer anno;

	private List<CategoriaImmobileForm> categoriaImmobileForms;

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
	 * @return the categoriaImmobileForms
	 */
	public List<CategoriaImmobileForm> getCategoriaImmobileForms() {
		return categoriaImmobileForms;
	}

	/**
	 * @param categoriaImmobileForms the categoriaImmobileForms to set
	 */
	public void setCategoriaImmobileForms(List<CategoriaImmobileForm> categoriaImmobileForms) {
		this.categoriaImmobileForms = categoriaImmobileForms;
	}

}
