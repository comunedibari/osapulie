/************************************************************************************
 * Copyright (c) 2011, 2016 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.servizicomune.web.portlet.testdichiarazioni.form;

import java.io.Serializable;

/**
 * @author Gianluca Pindinelli
 *
 */
public class TestDichiarazioniForm implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 867600612876991224L;

	private Long idBackup;

	/**
	 * @return the idBackup
	 */
	public Long getIdBackup() {
		return idBackup;
	}

	/**
	 * @param idBackup the idBackup to set
	 */
	public void setIdBackup(Long idBackup) {
		this.idBackup = idBackup;
	}

}
