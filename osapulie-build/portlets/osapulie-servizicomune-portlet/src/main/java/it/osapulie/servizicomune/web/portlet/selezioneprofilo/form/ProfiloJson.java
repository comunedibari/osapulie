/************************************************************************************
 * Copyright (c) 2011, 2016 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.servizicomune.web.portlet.selezioneprofilo.form;

import java.io.Serializable;
import java.util.List;

/**
 * Rappresenta il profilo (formato JSON) associato ad un utente (CF o PIVA);
 *
 * @author Gianluca Pindinelli
 *
 */
public class ProfiloJson implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String text;

	private List<ProfiloJson> children;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the children
	 */
	public List<ProfiloJson> getChildren() {
		return children;
	}

	/**
	 * @param children the children to set
	 */
	public void setChildren(List<ProfiloJson> children) {
		this.children = children;
	}
}
