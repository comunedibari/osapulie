/************************************************************************************
 * Copyright (c) 2011, 2014 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pdds.service.internal.protocollo;

import it.osapulie.pdds.adapter.util.XMLHelper;
import it.osapulie.pdds.common.PddService;
import it.osapulie.pdds.ws.client.protocollo.protocollo.Allegato;
import it.osapulie.pdds.ws.client.protocollo.protocollo.Destinatario;
import it.osapulie.pdds.ws.client.protocollo.protocollo.Documento;
import it.osapulie.pdds.ws.client.protocollo.protocollo.Errore;
import it.osapulie.pdds.ws.client.protocollo.protocollo.ProtocolloServer;
import it.osapulie.pdds.ws.client.protocollo.protocollo.ProtocolloServerImplServiceLocator;
import it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloProtocolloRequest;
import it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloResponseReturn;
import it.osapulie.protocollo.web.ws.types.NuovoProtocolloRequest;
import it.osapulie.protocollo.web.ws.types.NuovoProtocolloResponse;
import it.osapulie.protocollo.web.ws.types.ProtocolloRequest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;

//import org.apache.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Gianluca Pindinelli
 *
 */
public class RichiestaProtocolloService implements PddService {

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

		NuovoProtocolloResponse risposta = null;
		try {
			risposta = sendProtocollo(xml);
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
	private NuovoProtocolloResponse sendProtocollo(String xml) throws MalformedURLException {

		NuovoProtocolloResponse nuovoProtocolloResponse = null;

		try {
			NuovoProtocolloRequest osapulieNuovoProtocolloRequest = xmlHelper.unmarshal(xml);
			ProtocolloRequest osapulieProtocolloRequest = osapulieNuovoProtocolloRequest.getProtocolloRequest();

			URL url = new URL(webservicesBaseUrl + "/adoc/protocollo?wsdl");

			ProtocolloServerImplServiceLocator protocolloServerImplService = new ProtocolloServerImplServiceLocator();
			ProtocolloServer protocolloServer = protocolloServerImplService.getProtocolloServerImplPort(url);

			RichiestaProtocolloProtocolloRequest protocolloRequest = new RichiestaProtocolloProtocolloRequest();

			protocolloRequest.setAmministrazione(osapulieProtocolloRequest.getAmministrazione());
			protocolloRequest.setAreaOrganizzativaOmogenea(osapulieProtocolloRequest.getAreaOrganizzativaOmogenea());
			if (osapulieProtocolloRequest.getIdUtente() != null) {
				protocolloRequest.setIdUtente(Integer.parseInt(osapulieProtocolloRequest.getIdUtente()));
			}
			else {
				protocolloRequest.setIdUtente(0);
			}

			protocolloRequest.setOggetto(osapulieProtocolloRequest.getOggetto());

			it.osapulie.protocollo.web.ws.types.Mittente osapulieMittente = osapulieProtocolloRequest.getMittente();
			it.osapulie.protocollo.web.ws.types.PersonaFisica osapulieMittentePersonaFisica = osapulieMittente.getPersonaFisica();

			it.osapulie.pdds.ws.client.protocollo.protocollo.Mittente mittente = new it.osapulie.pdds.ws.client.protocollo.protocollo.Mittente();
			it.osapulie.pdds.ws.client.protocollo.protocollo.PersonaFisica persona = new it.osapulie.pdds.ws.client.protocollo.protocollo.PersonaFisica();

			persona.setCodiceFiscale(osapulieMittentePersonaFisica.getCodiceFiscale());
			persona.setCognome(osapulieMittentePersonaFisica.getCognome());
			persona.setNome(osapulieMittentePersonaFisica.getNome());
			mittente.setPersonaFisica(persona);
			mittente.setComune(osapulieMittente.getComune());
			mittente.setIndirizzo(osapulieMittente.getIndirizzo());
			mittente.setNazione(osapulieMittente.getNazione());

			// TODO settare mail e pecMail (non sono previsti per aDoc)

			protocolloRequest.setMittente(mittente);

			it.osapulie.protocollo.web.ws.types.Documento osapulieDocumento = osapulieProtocolloRequest.getDocumento();

			it.osapulie.pdds.ws.client.protocollo.protocollo.Documento documento = new it.osapulie.pdds.ws.client.protocollo.protocollo.Documento();
			documento.setContenuto(osapulieDocumento.getContenuto());
			documento.setDettaglio(osapulieDocumento.getDettaglio());
			documento.setTitolo(osapulieDocumento.getTitolo());
			documento.setSunto(osapulieDocumento.getTitolo());
			documento.setNomeFile(osapulieDocumento.getNomeFile());
			documento.setCollocazioneTelematica(osapulieDocumento.getCollocazioneTelematica());
			protocolloRequest.setDocumento(documento);

			List<it.osapulie.protocollo.web.ws.types.Allegato> osapulieAllegati = osapulieProtocolloRequest.getAllegati();

			if (osapulieAllegati != null) {
				int allegatiSize = osapulieAllegati.size();
				Allegato[] allegati = new Allegato[allegatiSize];
				for (int i = 0; i < osapulieAllegati.size(); i++) {
					it.osapulie.protocollo.web.ws.types.Allegato osapulieAllegato = osapulieAllegati.get(i);
					it.osapulie.protocollo.web.ws.types.Documento osapulieAllegatoDocumento = osapulieAllegato.getDocumento();

					Documento documentoAllegato = new Documento();
					documentoAllegato.setContenuto(osapulieAllegatoDocumento.getContenuto());
					documentoAllegato.setDettaglio(osapulieAllegatoDocumento.getDettaglio());
					documentoAllegato.setTitolo(osapulieAllegatoDocumento.getTitolo());
					documentoAllegato.setSunto(osapulieAllegatoDocumento.getTitolo());
					documentoAllegato.setNomeFile(osapulieAllegatoDocumento.getNomeFile());
					documentoAllegato.setCollocazioneTelematica(osapulieAllegatoDocumento.getCollocazioneTelematica());

					it.osapulie.pdds.ws.client.protocollo.protocollo.Allegato allegato = new it.osapulie.pdds.ws.client.protocollo.protocollo.Allegato();

					allegato.setDocumento(documentoAllegato);

					allegati[i] = allegato;
				}
				protocolloRequest.setAllegati(allegati);
			}

			List<it.osapulie.protocollo.web.ws.types.Destinatario> osapulieDestinatari = osapulieProtocolloRequest.getDestinatari();

			if (osapulieDestinatari != null) {
				Destinatario[] destinatari = new Destinatario[osapulieDestinatari.size()];
				for (int i = 0; i < osapulieDestinatari.size(); i++) {
					it.osapulie.protocollo.web.ws.types.Destinatario osapulieDestinatario = osapulieDestinatari.get(i);
					it.osapulie.pdds.ws.client.protocollo.protocollo.Destinatario destinatario = new it.osapulie.pdds.ws.client.protocollo.protocollo.Destinatario();

					destinatario.setAmministrazione(osapulieDestinatario.getAmministrazione());
					destinatario.setAreaOrganizzativaOmogenea(osapulieDestinatario.getAreaOrganizzativaOmogenea());

					it.osapulie.protocollo.web.ws.types.PersonaFisica osapuliePersonaFisica = osapulieDestinatario.getPersonaFisica();

					if (osapuliePersonaFisica != null) {
						it.osapulie.pdds.ws.client.protocollo.protocollo.PersonaFisica destinatarioFisico = new it.osapulie.pdds.ws.client.protocollo.protocollo.PersonaFisica();

						destinatarioFisico.setCodiceFiscale(osapuliePersonaFisica.getCodiceFiscale());
						destinatarioFisico.setCognome(osapuliePersonaFisica.getCognome());
						destinatarioFisico.setNome(osapuliePersonaFisica.getNome());
						destinatario.setPersonaFisica(destinatarioFisico);
					}
					destinatari[i] = destinatario;
				}
				protocolloRequest.setDestinatari(destinatari);
			}

			RichiestaProtocolloResponseReturn response = protocolloServer.richiestaProtocollo(protocolloRequest);

			nuovoProtocolloResponse = new NuovoProtocolloResponse();

			Calendar dataProtocollo = response.getDataProtocollo();
			if (dataProtocollo != null) {
				nuovoProtocolloResponse.setDataProtocollo(response.getDataProtocollo());
			}
			nuovoProtocolloResponse.setNumeroProtocollo(response.getNumeroProtocollo());

			Errore errore = response.getErrore();
			if (errore != null) {
				it.osapulie.protocollo.web.ws.types.Errore innerErrore = new it.osapulie.protocollo.web.ws.types.Errore();
				innerErrore.setCodice(errore.getCodice());
				innerErrore.setDescrizione(errore.getDescrizione());
				nuovoProtocolloResponse.setErrore(innerErrore);
			}
		}
		catch (Exception e) {
			log.error("sendProtocollo :: " + e.getMessage(), e);
		}

		return nuovoProtocolloResponse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.osapulie.pdds.common.PddService#getName()
	 */
	public String getName() {
		return "nuovoProtocollo";
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
