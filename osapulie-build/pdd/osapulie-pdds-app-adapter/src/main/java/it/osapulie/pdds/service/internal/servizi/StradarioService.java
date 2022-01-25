/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pdds.service.internal.servizi;

//import org.apache.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.osapulie.pdds.adapter.util.XMLHelper;
import it.osapulie.pdds.common.PddService;
import it.osapulie.servizi.web.ws.types.StradarioRichiesta;
import it.osapulie.servizi.web.ws.types.StradarioRisposta;

/**
 *
 * @author Gianluca Pindinelli
 *
 */
public class StradarioService implements PddService {

	private final Logger log = LoggerFactory.getLogger(StradarioService.class);

	private XMLHelper xmlHelper;

	@Override
	public String getResponse(String xml) {

		StradarioRichiesta richiesta = xmlHelper.unmarshal(xml, StradarioRichiesta.class);

		String result = null;
		try {
			StradarioRisposta risp = risposta(richiesta);
			result = xmlHelper.marshal(risp);

		}
		catch (Exception e) {
			log.error("getResponse :: " + e.getMessage(), e);
		}

		return result;

	}

	private StradarioRisposta risposta(StradarioRichiesta richiesta) {

		StradarioRisposta risposta = new StradarioRisposta();

		// TODO implementare

		return risposta;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.pdds.service.AbstractResponse#getName()
	 */
	@Override
	public String getName() {
		return "stradarioService";
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
