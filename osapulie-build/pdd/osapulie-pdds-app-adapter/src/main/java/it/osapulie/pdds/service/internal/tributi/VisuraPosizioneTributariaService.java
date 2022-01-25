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
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaRichiesta;
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaRisposta;
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaRichiesta.ArcoTemporale;
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaRichiesta.TipoTributo;
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaRichiesta.TipoTributo.Tributo;
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaRisposta.Errore;

/**
 * @author Gianluca Pindinelli
 *
 */
public class VisuraPosizioneTributariaService implements PddService {

	private final Logger log = LoggerFactory.getLogger(VisuraPosizioneTributariaService.class);

	private XMLHelper xmlHelper;

	private CommonService commonService;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.pdds.common.PddService#getResponse(java.lang.String)
	 */
	public String getResponse(String xml) {

		log.info("getResponse :: Richiesta su porta applicativa: " + xml);

		VisuraPosizioneTributariaRichiesta richiesta = xmlHelper.unmarshal(xml, VisuraPosizioneTributariaRichiesta.class);

		VisuraPosizioneTributariaRisposta risposta = new VisuraPosizioneTributariaRisposta();

		try {

			String codiceFiscale = richiesta.getCodiceFiscale();
			if (richiesta.getPartitaIva() != null) {
				codiceFiscale = richiesta.getPartitaIva();
			}

			ArcoTemporale arcoTemporale = richiesta.getArcoTemporale();
			Integer annoInizio = arcoTemporale.getAnnoInizio();
			Integer annoFine = arcoTemporale.getAnnoFine();

			TipoTributo tipoTributo = richiesta.getTipoTributo();
			if (tipoTributo != null) {
				if (tipoTributo.getALL() != null) {
					commonService.setVisurePubblicheAffissioni(risposta.getElencoPagamentiTassaAffissioni(), codiceFiscale, annoInizio, annoFine);
					commonService.setVisureServiziCimiteriali(risposta.getElencoPagamentiTassaCimiteriali(), codiceFiscale, annoInizio, annoFine);
					commonService.setVisureTassaImmobili(risposta.getElencoPagamentiTassaImmobili(), codiceFiscale, annoInizio, annoFine);
					commonService.setVisureTassaPubblicita(risposta.getElencoPagamentiTassaPubblicita(), codiceFiscale, annoInizio, annoFine);
					// Permanente
					commonService.setVisureOsapPermanente(risposta.getElencoPagamentiOsapPermananente(), codiceFiscale, annoInizio, annoFine);
					// Temporanea
					commonService.setVisureOsapTemporanea(risposta.getElencoPagamentiOsapTemporanea(), codiceFiscale, annoInizio, annoFine);
					commonService.setVisureTassaRifiuti(risposta.getElencoPagamentiTassaRifiuti(), codiceFiscale, annoInizio, annoFine);
				}
				else {
					Tributo tributo = tipoTributo.getTributo();
					if (tributo.getAFFISSIONI() != null) {
						commonService.setVisurePubblicheAffissioni(risposta.getElencoPagamentiTassaAffissioni(), codiceFiscale, annoInizio, annoFine);
					}
					else if (tributo.getCIMITERIALI() != null) {
						commonService.setVisureServiziCimiteriali(risposta.getElencoPagamentiTassaCimiteriali(), codiceFiscale, annoInizio, annoFine);
					}
					else if (tributo.getIMU() != null) {
						commonService.setVisureTassaImmobili(risposta.getElencoPagamentiTassaImmobili(), codiceFiscale, annoInizio, annoFine);
					}
					else if (tributo.getICP() != null) {
						commonService.setVisureTassaPubblicita(risposta.getElencoPagamentiTassaPubblicita(), codiceFiscale, annoInizio, annoFine);
					}
					else if (tributo.getOSAP() != null) {
						// Permanente
						commonService.setVisureOsapPermanente(risposta.getElencoPagamentiOsapPermananente(), codiceFiscale, annoInizio, annoFine);
						// Temporanea
						commonService.setVisureOsapTemporanea(risposta.getElencoPagamentiOsapTemporanea(), codiceFiscale, annoInizio, annoFine);
					}
					else if (tributo.getTARI() != null) {
						commonService.setVisureTassaRifiuti(risposta.getElencoPagamentiTassaRifiuti(), codiceFiscale, annoInizio, annoFine);
					}
				}
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

		log.info("getResponse :: Risposta su porta applicativa: " + result);

		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.pdds.common.PddService#getName()
	 */
	public String getName() {
		return "visuraPosizioneTributariaRichiesta";
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
