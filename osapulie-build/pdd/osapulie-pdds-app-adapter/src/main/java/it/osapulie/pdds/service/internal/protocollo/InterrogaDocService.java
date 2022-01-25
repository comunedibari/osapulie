/************************************************************************************
 * Copyright (c) 2011, 2014 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pdds.service.internal.protocollo;

import java.net.URL;

//import org.apache.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.linksmt.protocollo.ws.client.icar.interrogaDoc.AP3InterrogaDocRequest;
import it.linksmt.protocollo.ws.client.icar.interrogaDoc.AP3InterrogaDocResponse;
import it.linksmt.protocollo.ws.client.icar.interrogaDoc.Errore;
import it.linksmt.protocollo.ws.client.icar.interrogaDoc.RichiestaRichiestaRispostaSincronaInterrogaDocType;
import it.linksmt.protocollo.ws.client.icar.interrogaDoc.RispostaRichiestaRispostaSincronaInterrogaDocType;
import it.osapulie.pdds.adapter.util.XMLHelper;
import it.osapulie.pdds.common.PddService;
import it.osapulie.pdds.ws.client.protocollo.interrogaDoc.CodiceArea;
import it.osapulie.pdds.ws.client.protocollo.interrogaDoc.InterrogaDocWSServerImplServiceLocator;
import it.osapulie.pdds.ws.client.protocollo.interrogaDoc.Richiesta_RichiestaRispostaSincrona_InterrogaDoc_Type;
import it.osapulie.pdds.ws.client.protocollo.interrogaDoc.Risposta_RichiestaRispostaSincrona_InterrogaDoc_Type;

/**
 * @author Gianluca Pindinelli
 *
 */
public class InterrogaDocService implements PddService {

	private final Logger log = LoggerFactory.getLogger(InterrogaDocService.class);

	private XMLHelper xmlHelper;

	private String webservicesBaseUrl;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.pdds.adapter.PddService#getName()
	 */
	@Override
	public String getName() {
		return "interrogaDoc";
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.pdds.adapter.PddService#getResponse(java.lang.String)
	 */
	@Override
	public String getResponse(String arg0) {

		RichiestaRichiestaRispostaSincronaInterrogaDocType richiestaFromXml = xmlHelper.unmarshal(arg0, RichiestaRichiestaRispostaSincronaInterrogaDocType.class);

		RispostaRichiestaRispostaSincronaInterrogaDocType risposta = new RispostaRichiestaRispostaSincronaInterrogaDocType();

		URL wsdlLocation = null;
		String response = null;
		try {
			wsdlLocation = new URL(webservicesBaseUrl + "/adoc/interroga_doc?wsdl");
			InterrogaDocWSServerImplServiceLocator interrogaDocWSServerImplServiceLocator = new InterrogaDocWSServerImplServiceLocator();
			Richiesta_RichiestaRispostaSincrona_InterrogaDoc_Type parameters = new Richiesta_RichiestaRispostaSincrona_InterrogaDoc_Type();
			it.osapulie.pdds.ws.client.protocollo.interrogaDoc.AP3InterrogaDocRequest aP3InterrogaDocRequest = new it.osapulie.pdds.ws.client.protocollo.interrogaDoc.AP3InterrogaDocRequest();
			AP3InterrogaDocRequest ap3InterrogaDocRequestFromXml = richiestaFromXml.getAP3InterrogaDocRequest();
			String codiceDocInf = ap3InterrogaDocRequestFromXml.getCodiceDocInf();

			aP3InterrogaDocRequest.setCodiceDocInf(codiceDocInf);

			CodiceArea codiceInterrogato = new CodiceArea(ap3InterrogaDocRequestFromXml.getCodiceInterrogato().getCodiceAmm(), ap3InterrogaDocRequestFromXml.getCodiceInterrogato().getCodiceAoo());
			aP3InterrogaDocRequest.setCodiceInterrogato(codiceInterrogato);
			CodiceArea codiceRichiedente = new CodiceArea(ap3InterrogaDocRequestFromXml.getCodiceRichiedente().getCodiceAmm(), ap3InterrogaDocRequestFromXml.getCodiceRichiedente().getCodiceAoo());
			aP3InterrogaDocRequest.setCodiceRichiedente(codiceRichiedente);

			parameters.setAP3InterrogaDocRequest(aP3InterrogaDocRequest);

			Risposta_RichiestaRispostaSincrona_InterrogaDoc_Type richiestaRispostaSincrona_InterrogaDoc = interrogaDocWSServerImplServiceLocator.getInterrogaDocWSServerImplPort(wsdlLocation)
					.richiestaRispostaSincrona_InterrogaDoc(parameters);

			it.osapulie.pdds.ws.client.protocollo.interrogaDoc.AP3InterrogaDocResponse ap3InterrogaDocResponse = richiestaRispostaSincrona_InterrogaDoc.getAP3InterrogaDocResponse();

			AP3InterrogaDocResponse value = new AP3InterrogaDocResponse();
			value.setEsito(ap3InterrogaDocResponse.isEsito());
			value.setNomeDocInf(ap3InterrogaDocResponse.getNomeDocInf());
			String mimeTypeFromClient = ap3InterrogaDocResponse.getMimeType();
			if (mimeTypeFromClient != null) {
				value.setMimeType(mimeTypeFromClient);
			}

			it.osapulie.pdds.ws.client.protocollo.interrogaDoc.Errore erroreFromClient = ap3InterrogaDocResponse.getErrore();
			if (erroreFromClient != null) {
				Errore errore = new Errore();
				errore.setCodiceErrore(erroreFromClient.getCodiceErrore());
				errore.setMsgErrore(erroreFromClient.getMsgErrore());
				value.setErrore(errore);
			}

			risposta.setAP3InterrogaDocResponse(value);

			response = xmlHelper.marshal(risposta);
		}
		catch (Exception e) {
			log.error("getResponse :: " + e.getMessage(), e);
		}

		return response;
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
