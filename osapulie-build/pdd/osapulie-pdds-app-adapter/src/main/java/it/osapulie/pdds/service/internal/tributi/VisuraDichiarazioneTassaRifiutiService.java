/************************************************************************************
 * Copyright (c) 2011, 2014 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pdds.service.internal.tributi;

import java.util.ArrayList;
import java.util.List;

//import org.apache.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.osapulie.pdds.adapter.util.XMLHelper;
import it.osapulie.pdds.common.PddService;
import it.osapulie.pdds.service.internal.CommonService;
import it.osapulie.tributi.web.ws.output.types.DatiTassaRifiuti;
import it.osapulie.tributi.web.ws.output.types.DichiarazioneTassaRifiutiRichiesta;
import it.osapulie.tributi.web.ws.output.types.DichiarazioneTassaRifiutiRisposta;
import it.osapulie.tributi.web.ws.output.types.DichiarazioneTassaRifiutiRisposta.Errore;

/**
 * @author Gianluca Pindinelli
 *
 */
public class VisuraDichiarazioneTassaRifiutiService implements PddService {

	private final Logger log = LoggerFactory.getLogger(VisuraDichiarazioneTassaRifiutiService.class);

	private XMLHelper xmlHelper;

	private CommonService commonService;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.pdds.common.PddService#getName()
	 */
	public String getName() {
		return "visuraDichiarazioneTassaRifiuti";
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.pdds.common.PddService#getResponse(java.lang.String)
	 */
	public String getResponse(String xml) {

		DichiarazioneTassaRifiutiRichiesta richiesta = xmlHelper.unmarshal(xml, DichiarazioneTassaRifiutiRichiesta.class);

		DichiarazioneTassaRifiutiRisposta risposta = new DichiarazioneTassaRifiutiRisposta();

		try {
			try {
				String codiceFiscale = richiesta.getCodiceFiscale();
				if (richiesta.getPartitaIva() != null) {
					codiceFiscale = richiesta.getPartitaIva();
				}

				List<DatiTassaRifiuti> situazioni = new ArrayList<DatiTassaRifiuti>();
				commonService.setVisureTassaRifiuti(situazioni, codiceFiscale, richiesta.getAnnoInizio(), richiesta.getAnnoFine());

				if (situazioni == null || situazioni.size() == 0) {
					Errore errore = new Errore();
					errore.setCodice(3);
					risposta.setErrore(errore);
				}
				else {
					risposta.getSituazione().addAll(situazioni);
				}
			}
			catch (Exception e) {
				log.error("getResponse :: " + e.getMessage(), e);
				Errore errore = new Errore();
				errore.setCodice(4);
				errore.setDescrizione("Errore interno del server");
				risposta.setErrore(errore);
			}
		}
		catch (Throwable e) {
			log.error("getResponse :: " + e.getMessage(), e);
			Errore errore = new Errore();
			errore.setCodice(4);
			errore.setDescrizione(e.getMessage());
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
