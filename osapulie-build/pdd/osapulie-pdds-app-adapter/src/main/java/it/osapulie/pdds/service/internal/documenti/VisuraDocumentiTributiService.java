/************************************************************************************
 * Copyright (c) 2011, 2016 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pdds.service.internal.documenti;

import java.net.URL;
import java.util.List;

//import org.apache.log4j.Logger;7
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.osapulie.documenti.web.ws.output.types.VisuraDocumentiRichiesta;
import it.osapulie.documenti.web.ws.output.types.VisuraDocumentiRisposta;
import it.osapulie.documenti.web.ws.output.types.VisuraDocumentiRisposta.Errore;
import it.osapulie.pdds.adapter.util.XMLHelper;
import it.osapulie.pdds.common.PddService;
import it.osapulie.pdds.ws.client.documenti.tributi.Documento;
import it.osapulie.pdds.ws.client.documenti.tributi.VisuraDocumentiTributiServiceLocator;
import it.osapulie.pdds.ws.client.documenti.tributi.VisuraDocumentiTributiServicePortType;

/**
 * Implementazione interna del servizio di Visura Documenti Tributi.
 *
 * @author Gianluca Pindinelli
 *
 */
public class VisuraDocumentiTributiService implements PddService {

	private final Logger log = LoggerFactory.getLogger(VisuraDocumentiTributiService.class);

	private XMLHelper xmlHelper;

	private String webservicesBaseUrl;

	@Override
	public String getResponse(String xml) {

		VisuraDocumentiRichiesta richiesta = xmlHelper.unmarshal(xml, VisuraDocumentiRichiesta.class);

		String result = null;
		try {
			VisuraDocumentiRisposta risp = risposta(richiesta);
			result = xmlHelper.marshal(risp);

		}
		catch (Exception e) {
			log.error("getResponse :: " + e.getMessage(), e);
		}

		return result;

	}

	private VisuraDocumentiRisposta risposta(VisuraDocumentiRichiesta richiesta) {

		VisuraDocumentiRisposta risposta = new VisuraDocumentiRisposta();
		try {
			URL wsdlLocation = new URL(webservicesBaseUrl + "/services/visuraDocumentiTributiService?wsdl");
			VisuraDocumentiTributiServiceLocator visuraDocumentiServiceLocator = new VisuraDocumentiTributiServiceLocator();
			VisuraDocumentiTributiServicePortType secureSOAP11Endpoint = visuraDocumentiServiceLocator.getSecureSOAP11Endpoint(wsdlLocation);
			List<it.osapulie.documenti.web.ws.output.types.Documento> documenti = risposta.getDocumento();
			final String idDocumento = richiesta.getIdDocumento();
			// Ritorna la lista di documenti
			if (idDocumento == null) {
				Documento[] documentiRemote = secureSOAP11Endpoint.getDocumenti(richiesta.getCodiceFiscale(), richiesta.getCodiceServizio(), richiesta.getAnno());
				if (documentiRemote == null || documentiRemote.length == 0) {
					Errore errore = new Errore();
					errore.setCodice(3);
					errore.setDescrizione("Nessun documento trovato.");
					risposta.setErrore(errore);
				}
				else {
					for (Documento documentoRemote : documentiRemote) {
						it.osapulie.documenti.web.ws.output.types.Documento documento = new it.osapulie.documenti.web.ws.output.types.Documento();
						documento.setNome(documentoRemote.getNome());
						documento.setContentType(documentoRemote.getContentType());
						documento.setId(documentoRemote.getId());
						documenti.add(documento);
					}
				}
			}
			// Ritorna il singolo documento
			else {
				Documento[] documentiRemote = secureSOAP11Endpoint.getDocumento(idDocumento);
				if (documentiRemote == null || documentiRemote.length == 0) {
					Errore errore = new Errore();
					errore.setCodice(3);
					errore.setDescrizione("Documento con ID '" + idDocumento + "' non trovato.");
					risposta.setErrore(errore);
				}
				else {
					Documento documentoRemote = documentiRemote[0];
					it.osapulie.documenti.web.ws.output.types.Documento documento = new it.osapulie.documenti.web.ws.output.types.Documento();
					documento.setNome(documentoRemote.getNome());
					documento.setContentType(documentoRemote.getContentType());
					documento.setId(documentoRemote.getId());
					documento.setBinario(documentoRemote.getBinario());
					risposta.getDocumento().add(documento);
				}
			}

		}
		catch (Exception e) {
			log.error("getResponse :: " + e.getMessage(), e);
			Errore errore = new Errore();
			errore.setCodice(1);
			errore.setDescrizione(e.getMessage());
			risposta.setErrore(errore);
		}

		return risposta;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.pdds.service.AbstractResponse#getName()
	 */
	@Override
	public String getName() {
		return "visuraDocumentiTributi";
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
