/************************************************************************************
 * Copyright (c) 2011, 2014 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pdds.service.internal.tributi;

import it.osapulie.pdds.adapter.util.XMLHelper;
import it.osapulie.pdds.common.PddService;
import it.osapulie.tributi.web.ws.output.types.DatiPagamentoServiziRisposta;

import java.util.UUID;

//import org.apache.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Gianluca Pindinelli
 *
 */
public class PagamentoServiziRichiestaService implements PddService {

	private final Logger log = LoggerFactory.getLogger(PagamentoServiziRichiestaService.class);

	private XMLHelper xmlHelper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.osapulie.pdds.common.PddService#getResponse(java.lang.String)
	 */
	public String getResponse(String xml) {

		log.info("getResponse :: Richiesta su porta applicativa: " + xml);

		// TODO implementare in base a nuovo portale pagamenti
		DatiPagamentoServiziRisposta risposta = new DatiPagamentoServiziRisposta();
		risposta.setIdentificativoOperazione(UUID.randomUUID().toString());

		String result = xmlHelper.marshal(risposta);

		log.info("getResponse :: Risposta su porta applicativa: " + result);

		return result;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.osapulie.pdds.common.PddService#getName()
	 */
	public String getName() {
		return "pagamentoServizioRichiesta";
	}

	/**
	 * @return the xmlHelper
	 */
	public XMLHelper getXmlHelper() {
		return xmlHelper;
	}

	/**
	 * @param xmlHelper the xmlHelper to set
	 */
	public void setXmlHelper(XMLHelper xmlHelper) {
		this.xmlHelper = xmlHelper;
	}
}
