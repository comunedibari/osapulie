/************************************************************************************
 * Copyright (c) 2011, 2014 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pdds.service.internal.tributi;

import java.util.Calendar;
import java.util.List;

//import org.apache.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.osapulie.pdds.adapter.util.XMLHelper;
import it.osapulie.pdds.common.PddService;
import it.osapulie.pdds.service.internal.CommonService;
import it.osapulie.pdds.service.internal.anagrafiche.RichiestaDatiElettoraliService;
import it.osapulie.tributi.web.ws.output.types.DatiTassaImmobili;
import it.osapulie.tributi.web.ws.output.types.VisuraTasiRichiesta;
import it.osapulie.tributi.web.ws.output.types.VisuraTasiRisposta;
import it.osapulie.tributi.web.ws.output.types.VisuraTasiRisposta.Errore;

/**
 * @author Gianluca Pindinelli
 *
 */
public class VisuraTasiService implements PddService {

	private final Logger log = LoggerFactory.getLogger(RichiestaDatiElettoraliService.class);

	private XMLHelper xmlHelper;

	private CommonService commonService;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.pdds.common.PddService#getResponse(java.lang.String)
	 */
	public String getResponse(String xml) {

		log.debug("getResponse :: entering method");

		VisuraTasiRichiesta richiesta = xmlHelper.unmarshal(xml, VisuraTasiRichiesta.class);

		VisuraTasiRisposta risposta = new VisuraTasiRisposta();

		try {

			int annoDa = richiesta.getDal().get(Calendar.YEAR);
			int annoA = richiesta.getAl().get(Calendar.YEAR);

			List<DatiTassaImmobili> elencoPagamentiTasi = risposta.getElencoPagamentiTasi();

			String codiceFiscale = richiesta.getCodiceFiscale();
			if (richiesta.getPartitaIva() != null) {
				codiceFiscale = richiesta.getPartitaIva();
			}

			commonService.setVisureTassaImmobili(elencoPagamentiTasi, codiceFiscale, annoDa, annoA);

			if (elencoPagamentiTasi == null || elencoPagamentiTasi.isEmpty()) {
				Errore errore = new Errore();
				errore.setCodice(3);
				risposta.setErrore(errore);
			}

		}
		catch (Exception e) {
			log.error("getResponse :: " + e.getMessage(), e);
			Errore errore = new Errore();
			errore.setCodice(4);
			errore.setDescrizione(e.getMessage());
			risposta.setErrore(errore);
		}

		String result = xmlHelper.marshal(risposta);

		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.pdds.common.PddService#getName()
	 */
	public String getName() {
		return "visuraTasi";
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
