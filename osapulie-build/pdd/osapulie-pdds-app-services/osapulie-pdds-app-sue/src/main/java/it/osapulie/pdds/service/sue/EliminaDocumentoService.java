/************************************************************************************
 * Copyright (c) 2011, 2014 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pdds.service.sue;

import it.osapulie.pdds.common.PddService;
import it.osapulie.pdds.service.CommonService;

import java.net.MalformedURLException;

//import org.apache.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Gianluca Pindinelli
 *
 */
public class EliminaDocumentoService extends CommonService implements PddService {

	private final Logger log = LoggerFactory.getLogger(EliminaDocumentoService.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.osapulie.pdds.common.PddService#getResponse(java.lang.String)
	 */
	public String getResponse(String xml) {

		log.info("getResponse :: Richiesta su porta applicativa: " + xml);

		String result = sendRichiesta(xml);

		log.info("getResponse :: Risposta su porta applicativa: " + result);

		return result;
	}

	/**
	 * @param xml
	 * @return
	 * @throws MalformedURLException
	 */
	private String sendRichiesta(String xml) {

		String res = xmlHelper.getXmlContent(getName(), "/xml");

		return res;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.osapulie.pdds.common.PddService#getName()
	 */
	public String getName() {
		return "eliminaDocumento";
	}

}
