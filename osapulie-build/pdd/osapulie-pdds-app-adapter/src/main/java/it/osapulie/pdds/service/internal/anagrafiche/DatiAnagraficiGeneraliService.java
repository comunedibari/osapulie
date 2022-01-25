/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pdds.service.internal.anagrafiche;

import java.net.URL;
import java.util.Calendar;

//import org.apache.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.osapulie.anagrafe.web.ws.output.types.DatiAnagraficiGenerali;
import it.osapulie.anagrafe.web.ws.output.types.DatiUtente;
import it.osapulie.anagrafe.web.ws.output.types.DatiUtente.Errore;
import it.osapulie.pdds.adapter.util.XMLHelper;
import it.osapulie.pdds.common.PddService;
import it.osapulie.pdds.ws.client.visuraanagraficalight.VisuraAnagrafica;
import it.osapulie.pdds.ws.client.visuraanagraficalight.VisuraAnagraficaLightServiceLocator;
import it.osapulie.pdds.ws.client.visuraanagraficalight.VisuraAnagraficaLightServicePortType;

/**
 * La classe con l'immissione del codice fiscale fornisce le generalità del cittadino seguendo lo
 * schema DatiAnagraficiGeneraliService.xsd.
 *
 * @author Gianluca Pindinelli
 *
 */
public class DatiAnagraficiGeneraliService implements PddService {

	private final Logger log = LoggerFactory.getLogger(DatiAnagraficiGeneraliService.class);

	private XMLHelper xmlHelper;

	private String webservicesBaseUrl;

	@Override
	public String getResponse(String xml) {

		DatiAnagraficiGenerali richiesta = xmlHelper.unmarshal(xml, DatiAnagraficiGenerali.class);

		DatiUtente risposta = new DatiUtente();

		String result = null;
		try {

			URL wsdlLocation = new URL(webservicesBaseUrl + "/services/visuraAnagraficaLightService?wsdl");

			VisuraAnagraficaLightServiceLocator visuraAnagraficaLightServiceLocator = new VisuraAnagraficaLightServiceLocator();
			VisuraAnagraficaLightServicePortType secureSOAP11Endpoint = visuraAnagraficaLightServiceLocator.getSecureSOAP11Endpoint(wsdlLocation);
			VisuraAnagrafica[] visuraAnagraficaByCodiceFiscale = secureSOAP11Endpoint.getVisuraAnagraficaLight(richiesta.getCodiceFiscale());

			if (visuraAnagraficaByCodiceFiscale == null || visuraAnagraficaByCodiceFiscale.length == 0) {
				Errore errore = new Errore();
				errore.setCodice(1);
				risposta.setErrore(errore);
			}
			else {

				VisuraAnagrafica visuraAnagrafica = visuraAnagraficaByCodiceFiscale[0];

				risposta.setIndirizzo(visuraAnagrafica.getIndirizzo());
				risposta.setNome(visuraAnagrafica.getNome());
				risposta.setCognome(visuraAnagrafica.getCognome());
				if (visuraAnagrafica.getDataNascita() != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(visuraAnagrafica.getDataNascita());
					risposta.setDataNascita(calendar);
				}

				risposta.setComuneNascita(visuraAnagrafica.getComuneNascita());
				risposta.setProvinciaNascita(visuraAnagrafica.getProvinciaNascita());
				risposta.setCap(visuraAnagrafica.getCap());

				risposta.setComuneResidenza(visuraAnagrafica.getComuneResidenza());
				risposta.setProvinciaResidenza(visuraAnagrafica.getProvinciaResidenza());
				risposta.setCodiceIstatResidenza(visuraAnagrafica.getCodiceIstatResidenza());
			}
		}
		catch (Exception e) {
			log.error("getResponse :: " + e.getMessage(), e);
			Errore errore = new Errore();
			errore.setCodice(1);
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
	@Override
	public String getName() {
		return "datiAnagraficiGenerali";
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
	 * @return the webservicesBaseUrl
	 */
	public String getWebservicesBaseUrl() {
		return webservicesBaseUrl;
	}

	/**
	 * @param webservicesBaseUrl the webservicesBaseUrl to set
	 */
	public void setWebservicesBaseUrl(String webservicesBaseUrl) {
		this.webservicesBaseUrl = webservicesBaseUrl;
	}

}
