/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
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
import it.osapulie.tributi.web.ws.output.types.DatiTassaImmobili;
import it.osapulie.tributi.web.ws.output.types.DichiarazioneTassaImmobiliRichiesta;
import it.osapulie.tributi.web.ws.output.types.DichiarazioneTassaImmobiliRisposta;
import it.osapulie.tributi.web.ws.output.types.DichiarazioneTassaImmobiliRisposta.Errore;

/**
 *
 * @author Gianluca Pindinelli
 *
 */
public class VisuraDichiarazioneTassaImmobiliService implements PddService {

	private final Logger log = LoggerFactory.getLogger(VisuraDichiarazioneTassaImmobiliService.class);

	private XMLHelper xmlHelper;

	private CommonService commonService;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.pdds.service.AbstractResponse#getResponse(java.lang.String)
	 */
	public String getResponse(String xml) {

		DichiarazioneTassaImmobiliRichiesta richiesta = xmlHelper.unmarshal(xml, DichiarazioneTassaImmobiliRichiesta.class);

		DichiarazioneTassaImmobiliRisposta risposta = new DichiarazioneTassaImmobiliRisposta();

		String result = null;

		try {

			String codiceFiscale = richiesta.getCodiceFiscale();
			if (richiesta.getPartitaIva() != null) {
				codiceFiscale = richiesta.getPartitaIva();
			}

			List<DatiTassaImmobili> elencoPagamentiTassaImmobili = new ArrayList<DatiTassaImmobili>();
			commonService.setVisureTassaImmobili(elencoPagamentiTassaImmobili, codiceFiscale, richiesta.getAnnoInizio(), richiesta.getAnnoFine());

			if (elencoPagamentiTassaImmobili == null || elencoPagamentiTassaImmobili.isEmpty()) {
				Errore errore = new Errore();
				errore.setCodice(3);
				risposta.setErrore(errore);
			}
			else {
				risposta.getSituazione().addAll(elencoPagamentiTassaImmobili);
			}
		}
		catch (Exception e) {
			log.error("getResponse :: " + e.getMessage(), e);
			Errore errore = new Errore();
			errore.setCodice(4);
			errore.setDescrizione(e.getMessage());
			risposta.setErrore(errore);
		}
		result = xmlHelper.marshal(risposta);

		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.pdds.service.AbstractResponse#getName()
	 */
	public String getName() {
		return "visuraDichiarazioneTassaImmobili";
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
