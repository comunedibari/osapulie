/************************************************************************************
 * Copyright (c) 2011, 2014 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pdds.service.internal.protocollo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

//import org.apache.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.osapulie.pdds.adapter.util.XMLHelper;
import it.osapulie.pdds.common.PddService;
import it.osapulie.pdds.ws.client.protocollo.protocollo.Allegato;
import it.osapulie.pdds.ws.client.protocollo.protocollo.Documento;
import it.osapulie.pdds.ws.client.protocollo.protocollo.Errore;
import it.osapulie.pdds.ws.client.protocollo.protocollo.GetProtocolloProtocolloInformazioniRequest;
import it.osapulie.pdds.ws.client.protocollo.protocollo.GetProtocolloResponseReturn;
import it.osapulie.pdds.ws.client.protocollo.protocollo.ImprontaMIME;
import it.osapulie.pdds.ws.client.protocollo.protocollo.Mittente;
import it.osapulie.pdds.ws.client.protocollo.protocollo.PersonaFisica;
import it.osapulie.pdds.ws.client.protocollo.protocollo.PersonaGiuridica;
import it.osapulie.pdds.ws.client.protocollo.protocollo.ProtocolloServer;
import it.osapulie.pdds.ws.client.protocollo.protocollo.ProtocolloServerImplServiceLocator;
import it.osapulie.protocollo.web.ws.types.DettaglioProtocolloRequest;
import it.osapulie.protocollo.web.ws.types.DettaglioProtocolloResponse;
import it.osapulie.protocollo.web.ws.types.ProtocolloResponse;

/**
 * @author Gianluca Pindinelli
 *
 */
public class DettaglioProtocolloService implements PddService {

	private final Logger log = LoggerFactory.getLogger(DettaglioProtocolloService.class);

	private XMLHelper xmlHelper;
	private String webservicesBaseUrl;
	private String protocolloUsername;
	private String protocolloPassword;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.pdds.common.PddService#getResponse(java.lang.String)
	 */
	@Override
	public String getResponse(String xml) {

		log.info("getResponse :: Richiesta su porta applicativa: " + xml);

		DettaglioProtocolloResponse risposta = null;
		try {
			risposta = getDettaglioProtocollo(xml);
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
	private DettaglioProtocolloResponse getDettaglioProtocollo(String xml) throws MalformedURLException {

		DettaglioProtocolloResponse dettaglioProtocolloResponse = null;

		try {
			DettaglioProtocolloRequest dettaglioProtocolloRequest = xmlHelper.unmarshal(xml);

			int anno = dettaglioProtocolloRequest.getAnno();
			long numeroProtocollo = dettaglioProtocolloRequest.getNumeroProtocollo();

			URL url = new URL(webservicesBaseUrl + "/adoc/protocollo?wsdl");

			ProtocolloServerImplServiceLocator protocolloServerImplService = new ProtocolloServerImplServiceLocator();
			ProtocolloServer protocolloServer = protocolloServerImplService.getProtocolloServerImplPort(url);

			GetProtocolloProtocolloInformazioniRequest protocolloInformazioniRequest = new GetProtocolloProtocolloInformazioniRequest();

			// Richiesta protocollo fittizzio con allegati
			protocolloInformazioniRequest.setAnno(String.valueOf(anno));
			protocolloInformazioniRequest.setNumeroProtocollo(numeroProtocollo);
			GetProtocolloResponseReturn protocollo = protocolloServer.getProtocollo(protocolloInformazioniRequest);

			dettaglioProtocolloResponse = new DettaglioProtocolloResponse();

			Errore errore = protocollo.getErrore();
			if (errore != null) {
				it.osapulie.protocollo.web.ws.types.Errore osapulieErrore = new it.osapulie.protocollo.web.ws.types.Errore();
				osapulieErrore.setCodice(errore.getCodice());
				osapulieErrore.setDescrizione(errore.getDescrizione());
				dettaglioProtocolloResponse.setErrore(osapulieErrore);
				return dettaglioProtocolloResponse;
			}

			ProtocolloResponse protocolloResponse = new ProtocolloResponse();

			protocolloResponse.setOggetto(protocollo.getOggetto());
			protocolloResponse.setAmministrazione(protocollo.getAmministrazione());
			protocolloResponse.setAreaOrganizzativaOmogenea(protocollo.getAreaOrganizzativaOmogenea());
			Calendar dataProtocollo = protocollo.getDataProtocollo();
			if (dataProtocollo != null) {
				protocolloResponse.setDataProtocollo(dataProtocollo);
			}
			protocolloResponse.setNumeroProtocollo(protocollo.getNumeroProtocollo());

			Documento documento = protocollo.getDocumento();
			if (documento != null) {

				it.osapulie.protocollo.web.ws.types.Documento osapulieDocumento = new it.osapulie.protocollo.web.ws.types.Documento();
				osapulieDocumento.setClassifica(documento.getClassifica());
				osapulieDocumento.setCollocazioneTelematica(documento.getCollocazioneTelematica());
				osapulieDocumento.setContenuto(documento.getContenuto());

				ImprontaMIME improntaMIME = documento.getImprontaMIME();
				if (improntaMIME != null) {
					it.osapulie.protocollo.web.ws.types.ImprontaMIME osapulieDocumentoImprontaMime = new it.osapulie.protocollo.web.ws.types.ImprontaMIME();
					osapulieDocumentoImprontaMime.setAlgoritmo(improntaMIME.getAlgoritmo());
					osapulieDocumentoImprontaMime.setCodifica(improntaMIME.getCodifica());
					osapulieDocumentoImprontaMime.setValue(improntaMIME.get_value());
					osapulieDocumento.setImprontaMIME(osapulieDocumentoImprontaMime);
				}
				osapulieDocumento.setNomeFile(documento.getNomeFile());
				osapulieDocumento.setDettaglio(documento.getDettaglio());
				osapulieDocumento.setTipoRiferimento(documento.getTipoRiferimento());
				osapulieDocumento.setTitolo(documento.getTitolo());

				protocolloResponse.setDocumento(osapulieDocumento);
			}

			Mittente mittente = protocollo.getMittente();
			if (mittente != null) {
				it.osapulie.protocollo.web.ws.types.Mittente osapulieMittente = new it.osapulie.protocollo.web.ws.types.Mittente();

				osapulieMittente.setComune(mittente.getComune());
				osapulieMittente.setIndirizzo(mittente.getIndirizzo());
				osapulieMittente.setNazione(mittente.getNazione());

				PersonaFisica personaFisica = mittente.getPersonaFisica();
				if (personaFisica != null) {
					it.osapulie.protocollo.web.ws.types.PersonaFisica osapulieMittentePersonaFisica = new it.osapulie.protocollo.web.ws.types.PersonaFisica();
					osapulieMittentePersonaFisica.setCodiceFiscale(personaFisica.getCodiceFiscale());
					osapulieMittentePersonaFisica.setCognome(personaFisica.getCognome());
					osapulieMittentePersonaFisica.setNome(personaFisica.getNome());
					osapulieMittente.setPersonaFisica(osapulieMittentePersonaFisica);
				}

				PersonaGiuridica personaGiuridica = mittente.getPersonaGiuridica();
				if (personaGiuridica != null) {
					it.osapulie.protocollo.web.ws.types.PersonaGiuridica innerPersonaGiuridica = new it.osapulie.protocollo.web.ws.types.PersonaGiuridica();
					innerPersonaGiuridica.setPartitaIVA(personaGiuridica.getPartitaIVA());
					innerPersonaGiuridica.setRagioneSociale(personaGiuridica.getRagioneSociale());
					osapulieMittente.setPersonaGiuridica(innerPersonaGiuridica);
				}
				protocolloResponse.setMittente(osapulieMittente);
			}

			Allegato[] allegati = protocollo.getAllegati();
			if (allegati != null) {

				for (Allegato allegato : allegati) {
					it.osapulie.protocollo.web.ws.types.Allegato osapulieAllegato = new it.osapulie.protocollo.web.ws.types.Allegato();

					Documento allegatoDocumento = allegato.getDocumento();
					if (allegatoDocumento != null) {
						it.osapulie.protocollo.web.ws.types.Documento osapulieAllegatoDocumento = new it.osapulie.protocollo.web.ws.types.Documento();
						osapulieAllegatoDocumento.setClassifica(allegatoDocumento.getClassifica());
						osapulieAllegatoDocumento.setCollocazioneTelematica(allegatoDocumento.getCollocazioneTelematica());
						osapulieAllegatoDocumento.setContenuto(allegatoDocumento.getContenuto());
						osapulieAllegatoDocumento.setDettaglio(allegatoDocumento.getDettaglio());

						ImprontaMIME improntaMIME = allegatoDocumento.getImprontaMIME();
						if (improntaMIME != null) {
							it.osapulie.protocollo.web.ws.types.ImprontaMIME osapulieAllegatoImprontaMIME = new it.osapulie.protocollo.web.ws.types.ImprontaMIME();
							osapulieAllegatoImprontaMIME.setAlgoritmo(improntaMIME.getAlgoritmo());
							osapulieAllegatoImprontaMIME.setCodifica(improntaMIME.getCodifica());
							osapulieAllegatoImprontaMIME.setValue(improntaMIME.get_value());
							osapulieAllegatoDocumento.setImprontaMIME(osapulieAllegatoImprontaMIME);
						}

						osapulieAllegatoDocumento.setNomeFile(allegatoDocumento.getNomeFile());
						osapulieAllegatoDocumento.setTipoRiferimento(allegatoDocumento.getTipoRiferimento());
						osapulieAllegatoDocumento.setTitolo(allegatoDocumento.getTitolo());

						osapulieAllegato.setDocumento(osapulieAllegatoDocumento);

						protocolloResponse.getAllegati().add(osapulieAllegato);
					}
				}
			}
			dettaglioProtocolloResponse.setProtocolloResponse(protocolloResponse);
		}
		catch (Exception e) {
			log.error("getDettaglioProtocollo :: " + e.getMessage(), e);
		}

		return dettaglioProtocolloResponse;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.pdds.common.PddService#getName()
	 */
	@Override
	public String getName() {
		return "dettaglioProtocollo";
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
