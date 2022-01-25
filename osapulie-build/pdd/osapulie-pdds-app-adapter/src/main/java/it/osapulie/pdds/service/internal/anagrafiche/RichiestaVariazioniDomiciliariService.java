/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pdds.service.internal.anagrafiche;

import java.net.URL;
import java.util.Calendar;
import java.util.List;

import javax.xml.bind.JAXBElement;

//import org.apache.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.osapulie.anagrafe.web.ws.output.types.DatiIndirizzo;
import it.osapulie.anagrafe.web.ws.output.types.ObjectFactory;
import it.osapulie.anagrafe.web.ws.output.types.RichiestaVariazioniDomiciliari;
import it.osapulie.anagrafe.web.ws.output.types.DatiIndirizzo.Errore;
import it.osapulie.anagrafe.web.ws.output.types.DatiIndirizzo.VariazioniDomicilio;
import it.osapulie.pdds.adapter.util.XMLHelper;
import it.osapulie.pdds.common.PddService;
import it.osapulie.pdds.ws.client.visuravariazionidomicilioservice.VisuraVariazioneDomicilio;
import it.osapulie.pdds.ws.client.visuravariazionidomicilioservice.VisuraVariazioniDomicilioServiceLocator;
import it.osapulie.pdds.ws.client.visuravariazionidomicilioservice.VisuraVariazioniDomicilioServicePortType;

/**
 *
 * @author Gianluca Pindinelli
 *
 */
public class RichiestaVariazioniDomiciliariService implements PddService {

	private final Logger log = LoggerFactory.getLogger(RichiestaVariazioniDomiciliariService.class);

	private XMLHelper xmlHelper;

	private String webservicesBaseUrl;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.pdds.service.AbstractResponse#getResponse(java.lang.String)
	 */
	@Override
	public String getResponse(String xml) {

		RichiestaVariazioniDomiciliari richiesta = xmlHelper.unmarshal(xml, RichiestaVariazioniDomiciliari.class);

		DatiIndirizzo risposta = new DatiIndirizzo();

		String result = null;

		try {

			URL wsdlLocation = new URL(webservicesBaseUrl + "/services/visuraVariazioniDomicilioService?wsdl");

			VisuraVariazioniDomicilioServiceLocator visuraVariazioniDomicilioServiceLocator = new VisuraVariazioniDomicilioServiceLocator();
			VisuraVariazioniDomicilioServicePortType secureSOAP11Endpoint = visuraVariazioniDomicilioServiceLocator.getSecureSOAP11Endpoint(wsdlLocation);
			VisuraVariazioneDomicilio[] visureVariazioniDomicilio = secureSOAP11Endpoint.getVisureVariazioniDomicilio(richiesta.getCodiceFiscale(), richiesta.getDataPartenza().getTime(),
					richiesta.getDataFine().getTime());

			if (visureVariazioniDomicilio == null || visureVariazioniDomicilio.length == 0) {
				Errore errore = new Errore();
				errore.setCodice(3);
				risposta.setErrore(errore);
			}
			else {
				List<VariazioniDomicilio> variazioniDomicilio = risposta.getVariazioniDomicilio();
				for (VisuraVariazioneDomicilio variazioneDomicilioWs : visureVariazioniDomicilio) {

					VariazioniDomicilio variazioneDomicilio = new VariazioniDomicilio();

					if (variazioneDomicilioWs.getDataInizioDomicilio() != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(variazioneDomicilioWs.getDataInizioDomicilio());
						variazioneDomicilio.setDataInizioInd(calendar);
					}
					variazioneDomicilio.setDescrizioneTopSto(variazioneDomicilioWs.getToponimoIndirizzo());
					variazioneDomicilio.setDescrizioneViaSto(variazioneDomicilioWs.getIndirizzo());
					variazioneDomicilio.setNumeroCivicoSto(variazioneDomicilioWs.getNumeroCivico());
					variazioneDomicilio.setEsponenteSto(variazioneDomicilioWs.getEsponente());
					if (variazioneDomicilioWs.getPiano() != null) {
						variazioneDomicilio.setPianoSto(variazioneDomicilioWs.getPiano().toString());
					}
					variazioneDomicilio.setScalaSto(variazioneDomicilioWs.getScala());
					variazioneDomicilio.setDescComune(variazioneDomicilioWs.getComune());
					variazioniDomicilio.add(variazioneDomicilio);
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
		if (risposta != null) {
			ObjectFactory factory = new ObjectFactory();
			JAXBElement<DatiIndirizzo> variazioniDomiciliari = factory.createVariazioniDomiciliari(risposta);
			result = xmlHelper.marshal(variazioniDomiciliari);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.pdds.service.AbstractResponse#getName()
	 */
	@Override
	public String getName() {
		return "richiestaVariazioniDomiciliari";
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
