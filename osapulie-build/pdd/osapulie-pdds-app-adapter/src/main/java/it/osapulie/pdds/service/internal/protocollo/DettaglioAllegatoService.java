/************************************************************************************
 * Copyright (c) 2011, 2014 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pdds.service.internal.protocollo;

import it.osapulie.pdds.adapter.util.XMLHelper;
import it.osapulie.pdds.common.PddService;
import it.osapulie.pdds.ws.client.protocollo.protocollo.Documento;
import it.osapulie.pdds.ws.client.protocollo.protocollo.Errore;
import it.osapulie.pdds.ws.client.protocollo.protocollo.GetAllegatoDettaglioAllegatoRequest;
import it.osapulie.pdds.ws.client.protocollo.protocollo.GetAllegatoResponseReturn;
import it.osapulie.pdds.ws.client.protocollo.protocollo.ProtocolloServer;
import it.osapulie.pdds.ws.client.protocollo.protocollo.ProtocolloServerImplServiceLocator;
import it.osapulie.protocollo.web.ws.types.Allegato;
import it.osapulie.protocollo.web.ws.types.DettaglioAllegatoRequest;
import it.osapulie.protocollo.web.ws.types.DettaglioAllegatoResponse;

import java.net.MalformedURLException;
import java.net.URL;

//import org.apache.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Gianluca Pindinelli
 *
 */
public class DettaglioAllegatoService implements PddService {

	private final Logger log = LoggerFactory.getLogger(DettaglioAllegatoService.class);

	private XMLHelper xmlHelper;
	private String webservicesBaseUrl;
	private String protocolloUsername;
	private String protocolloPassword;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.pdds.common.PddService#getResponse(java.lang.String)
	 */
	public String getResponse(String xml) {

		log.info("getResponse :: Richiesta su porta applicativa: " + xml);

		DettaglioAllegatoResponse risposta = null;
		try {
			risposta = getDettaglioAllegato(xml);
		}
		catch (MalformedURLException e) {
			log.error("getResponse :: " + e.getMessage(), e);
		}
		String result = xmlHelper.marshal(risposta);

		log.info("getResponse :: Risposta su porta applicativa: " + result);

		return result;
	}

	/**
	 * @param xml
	 * @return
	 * @throws MalformedURLException
	 */
	private DettaglioAllegatoResponse getDettaglioAllegato(String xml) throws MalformedURLException {

		DettaglioAllegatoResponse dettaglioAllegatoResponse = null;

		try {
			DettaglioAllegatoRequest dettaglioAllegatoRequest = xmlHelper.unmarshal(xml);

			String identificativo = dettaglioAllegatoRequest.getCollocazioneTelematica();

			URL url = new URL(webservicesBaseUrl + "/adoc/protocollo?wsdl");

			ProtocolloServerImplServiceLocator protocolloServerImplService = new ProtocolloServerImplServiceLocator();
			ProtocolloServer protocolloServer = protocolloServerImplService.getProtocolloServerImplPort(url);

			GetAllegatoDettaglioAllegatoRequest request = new GetAllegatoDettaglioAllegatoRequest();
			request.setIdentificativo(identificativo);

			dettaglioAllegatoResponse = new DettaglioAllegatoResponse();

			GetAllegatoResponseReturn allegatoReturn = protocolloServer.getAllegato(request);
			Errore errore = allegatoReturn.getErrore();
			if (errore != null) {
				it.osapulie.protocollo.web.ws.types.Errore error = new it.osapulie.protocollo.web.ws.types.Errore();
				error.setCodice(errore.getCodice());
				error.setDescrizione(errore.getDescrizione());
				dettaglioAllegatoResponse.setErrore(error);
				return dettaglioAllegatoResponse;
			}
			it.osapulie.pdds.ws.client.protocollo.protocollo.Allegato allegato = allegatoReturn.getAllegato();
			Documento documento = allegato.getDocumento();

			Allegato osapulieAllegato = new Allegato();
			it.osapulie.protocollo.web.ws.types.Documento osapulieDocumento = new it.osapulie.protocollo.web.ws.types.Documento();

			osapulieDocumento.setClassifica(documento.getClassifica());
			osapulieDocumento.setCollocazioneTelematica(documento.getCollocazioneTelematica());
			osapulieDocumento.setContenuto(documento.getContenuto());
			osapulieDocumento.setDettaglio(documento.getDettaglio());
			it.osapulie.pdds.ws.client.protocollo.protocollo.ImprontaMIME improntaMIME = documento.getImprontaMIME();

			if (improntaMIME != null) {
				it.osapulie.protocollo.web.ws.types.ImprontaMIME osapulieImprontaMIME = new it.osapulie.protocollo.web.ws.types.ImprontaMIME();
				osapulieImprontaMIME.setAlgoritmo(improntaMIME.getAlgoritmo());
				osapulieImprontaMIME.setCodifica(improntaMIME.getCodifica());
				osapulieImprontaMIME.setValue(improntaMIME.get_value());
				osapulieDocumento.setImprontaMIME(osapulieImprontaMIME);
			}

			osapulieDocumento.setNomeFile(documento.getNomeFile());
			osapulieDocumento.setTipoRiferimento(documento.getTipoRiferimento());
			osapulieDocumento.setTitolo(documento.getTitolo());

			osapulieAllegato.setDocumento(osapulieDocumento);

			dettaglioAllegatoResponse.setAllegato(osapulieAllegato);
		}
		catch (Exception e) {
			log.error("getDettaglioAllegato :: " + e.getMessage(), e);
		}

		return dettaglioAllegatoResponse;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.pdds.common.PddService#getName()
	 */
	public String getName() {
		return "dettaglioAllegato";
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

	/**
	 * @return the protocolloUsername
	 */
	public String getProtocolloUsername() {
		return protocolloUsername;
	}

	/**
	 * @param protocolloUsername the protocolloUsername to set
	 */
	public void setProtocolloUsername(String protocolloUsername) {
		this.protocolloUsername = protocolloUsername;
	}

	/**
	 * @return the protocolloPassword
	 */
	public String getProtocolloPassword() {
		return protocolloPassword;
	}

	/**
	 * @param protocolloPassword the protocolloPassword to set
	 */
	public void setProtocolloPassword(String protocolloPassword) {
		this.protocolloPassword = protocolloPassword;
	}

}
