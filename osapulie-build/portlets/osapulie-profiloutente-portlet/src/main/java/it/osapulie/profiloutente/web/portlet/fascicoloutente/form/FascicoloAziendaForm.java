/************************************************************************************
 * Copyright (c) 2011, 2016 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.profiloutente.web.portlet.fascicoloutente.form;

import java.io.Serializable;

/**
 * @author Gianluca Pindinelli
 *
 */
public class FascicoloAziendaForm implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6355516736618823115L;

	private Long idAzienda;
	private String dataRichiestaDa;
	private String dataRichiestaA;
	private Long idServizio;

	/**
	 * @return the idAzienda
	 */
	public Long getIdAzienda() {
		return idAzienda;
	}

	/**
	 * @param idAzienda the idAzienda to set
	 */
	public void setIdAzienda(Long idAzienda) {
		this.idAzienda = idAzienda;
	}

	/**
	 * @return the dataRichiestaDa
	 */
	public String getDataRichiestaDa() {
		return dataRichiestaDa;
	}

	/**
	 * @param dataRichiestaDa the dataRichiestaDa to set
	 */
	public void setDataRichiestaDa(String dataRichiestaDa) {
		this.dataRichiestaDa = dataRichiestaDa;
	}

	/**
	 * @return the dataRichiestaA
	 */
	public String getDataRichiestaA() {
		return dataRichiestaA;
	}

	/**
	 * @param dataRichiestaA the dataRichiestaA to set
	 */
	public void setDataRichiestaA(String dataRichiestaA) {
		this.dataRichiestaA = dataRichiestaA;
	}

	/**
	 * @return the idServizio
	 */
	public Long getIdServizio() {
		return idServizio;
	}

	/**
	 * @param idServizio the idServizio to set
	 */
	public void setIdServizio(Long idServizio) {
		this.idServizio = idServizio;
	}

}
