/************************************************************************************
 * Copyright (c) 2011, 2014 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pdds.service.internal.tributi;

//import org.apache.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.osapulie.pdds.adapter.util.XMLHelper;
import it.osapulie.pdds.common.PddService;
import it.osapulie.pdds.service.internal.CommonService;
import it.osapulie.tributi.web.ws.output.types.VisuraTassaRifiutiRichiesta;
import it.osapulie.tributi.web.ws.output.types.VisuraTassaRifiutiRisposta;
import it.osapulie.tributi.web.ws.output.types.VisuraTassaRifiutiRisposta.Errore;

/**
 * @author Gianluca Pindinelli
 *
 */
public class VisuraTassaRifiutiService implements PddService {

	private final Logger log = LoggerFactory.getLogger(VisuraTassaRifiutiService.class);

	private XMLHelper xmlHelper;

	private CommonService commonService;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.pdds.common.PddService#getName()
	 */
	public String getName() {
		return "visuraTassaRifiuti";
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.pdds.common.PddService#getResponse(java.lang.String)
	 */
	public String getResponse(String xml) {

		VisuraTassaRifiutiRichiesta richiesta = xmlHelper.unmarshal(xml, VisuraTassaRifiutiRichiesta.class);

		VisuraTassaRifiutiRisposta risposta = new VisuraTassaRifiutiRisposta();

		try {
			String codiceFiscale = richiesta.getCodiceFiscale();
			if (richiesta.getPartitaIva() != null) {
				codiceFiscale = richiesta.getPartitaIva();
			}

			commonService.setVisureTassaRifiuti(risposta.getElencoPagamenti(), codiceFiscale, richiesta.getAnnoInizio() - 1, richiesta.getAnnoFine());

			if (risposta.getElencoPagamenti() == null || risposta.getElencoPagamenti().size() == 0) {
				Errore errore = new Errore();
				errore.setCodice(3);
				risposta.setErrore(errore);
			}
		}
		catch (Exception e) {
			log.error("getResponse :: " + e.getMessage(), e);
			Errore errore = new Errore();
			errore.setCodice(4);
			errore.setDescrizione("Errore interno del server");
			risposta.setErrore(errore);
		}

		return xmlHelper.marshal(risposta);
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

	/**
	 * @return the commonService
	 */
	public CommonService getCommonService() {
		return commonService;
	}

	/**
	 * @param commonService the commonService to set
	 */
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
}
