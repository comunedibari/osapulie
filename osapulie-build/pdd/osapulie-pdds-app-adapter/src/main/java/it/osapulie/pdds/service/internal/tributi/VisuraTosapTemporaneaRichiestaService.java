/************************************************************************************
 * Copyright (c) 2011, 2014 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pdds.service.internal.tributi;

import java.net.URL;
import java.util.Calendar;
import java.util.List;

//import org.apache.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.osapulie.pdds.adapter.util.XMLHelper;
import it.osapulie.pdds.common.PddService;
import it.osapulie.pdds.ws.client.visuraosaptemporanea.PosizioneVisuraOsapTemporanea;
import it.osapulie.pdds.ws.client.visuraosaptemporanea.VisuraOsapTemporanea;
import it.osapulie.pdds.ws.client.visuraosaptemporanea.VisuraOsapTemporaneaServiceLocator;
import it.osapulie.pdds.ws.client.visuraosaptemporanea.VisuraOsapTemporaneaServicePortType;
import it.osapulie.tributi.web.ws.output.types.DatiOsapTemporanea;
import it.osapulie.tributi.web.ws.output.types.PagamentiOsapTemporaneaRichiesta;
import it.osapulie.tributi.web.ws.output.types.PagamentiOsapTemporaneaRisposta;
import it.osapulie.tributi.web.ws.output.types.DatiOsapTemporanea.PosizioniOsap;
import it.osapulie.tributi.web.ws.output.types.PagamentiOsapTemporaneaRisposta.Errore;

/**
 * @author Gianluca Pindinelli
 *
 */
public class VisuraTosapTemporaneaRichiestaService implements PddService {

	private final Logger log = LoggerFactory.getLogger(VisuraTosapTemporaneaRichiestaService.class);

	private XMLHelper xmlHelper;

	private String webservicesBaseUrl;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.pdds.common.PddService#getResponse(java.lang.String)
	 */
	public String getResponse(String xml) {

		log.info("getResponse :: Richiesta su porta applicativa: " + xml);

		PagamentiOsapTemporaneaRichiesta richiesta = xmlHelper.unmarshal(xml, PagamentiOsapTemporaneaRichiesta.class);

		PagamentiOsapTemporaneaRisposta risposta = new PagamentiOsapTemporaneaRisposta();

		VisuraOsapTemporaneaServiceLocator visuraOsapTemporaneaServiceLocator = new VisuraOsapTemporaneaServiceLocator();

		try {
			URL wsdlLocation = new URL(webservicesBaseUrl + "/services/visuraOsapTemporaneaService?wsdl");
			VisuraOsapTemporaneaServicePortType secureSOAP11Endpoint = visuraOsapTemporaneaServiceLocator.getSecureSOAP11Endpoint(wsdlLocation);

			int annoDa = richiesta.getDal().get(Calendar.YEAR);
			int annoA = richiesta.getAl().get(Calendar.YEAR);

			String codiceFiscale = richiesta.getCodiceFiscale();
			if (richiesta.getPartitaIva() != null) {
				codiceFiscale = richiesta.getPartitaIva();
			}

			VisuraOsapTemporanea[] visureOsapTemporanea = secureSOAP11Endpoint.getVisureOsapTemporanea(codiceFiscale, annoDa, annoA);

			if (visureOsapTemporanea == null || visureOsapTemporanea.length == 0) {
				Errore errore = new Errore();
				errore.setCodice(3);
				risposta.setErrore(errore);
			}
			else {

				List<DatiOsapTemporanea> elencoOsapTemporanea = risposta.getElencoOsapTemporanea();

				for (VisuraOsapTemporanea visuraOsapTemporanea : visureOsapTemporanea) {

					DatiOsapTemporanea osapTemporanea = new DatiOsapTemporanea();

					if (visuraOsapTemporanea.getAnnoRiferimento() != null) {
						osapTemporanea.setAnnoRiferimento(visuraOsapTemporanea.getAnnoRiferimento().intValue());
					}
					osapTemporanea.setContoCorrente(visuraOsapTemporanea.getContoCorrente());
					if (visuraOsapTemporanea.getDataAggiornamento() != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(visuraOsapTemporanea.getDataAggiornamento());
						osapTemporanea.setDataAggiornamento(calendar);
					}
					osapTemporanea.setImportoDocumento(visuraOsapTemporanea.getImportoDocumento());
					osapTemporanea.setNumeroDocumento(visuraOsapTemporanea.getNumeroDocumento());

					elencoOsapTemporanea.add(osapTemporanea);

					// Posizioni
					PosizioneVisuraOsapTemporanea[] posizioniVisuraOsapTemporanea = secureSOAP11Endpoint.getPosizioniVisuraOsapTemporanea(visuraOsapTemporanea.getId().intValue());
					if (posizioniVisuraOsapTemporanea != null) {

						List<PosizioniOsap> posizioniOsapTemporanee = osapTemporanea.getPosizioniOsap();

						for (PosizioneVisuraOsapTemporanea posizioneVisuraOsapTemporanea : posizioniVisuraOsapTemporanea) {

							PosizioniOsap posizioneOsapTemporanee = new PosizioniOsap();

							if (posizioneVisuraOsapTemporanea.getDataPagamento() != null) {
								Calendar calendar = Calendar.getInstance();
								calendar.setTime(posizioneVisuraOsapTemporanea.getDataPagamento());
								posizioneOsapTemporanee.setDataPagamento(calendar);
							}
							posizioneOsapTemporanee.setDescrizioneTariffa(posizioneVisuraOsapTemporanea.getDescrizioneTariffa());
							if (posizioneVisuraOsapTemporanea.getDurataOccupazione() != null) {
								posizioneOsapTemporanee.setDurataOccupazione(posizioneVisuraOsapTemporanea.getDurataOccupazione().intValue());
							}
							posizioneOsapTemporanee.setImportoDaPagare(posizioneVisuraOsapTemporanea.getImportoDaPagare());
							posizioneOsapTemporanee.setImportoPagato(posizioneVisuraOsapTemporanea.getImportoPagato());
							posizioneOsapTemporanee.setIndirizzoUtenza(posizioneVisuraOsapTemporanea.getIndirizzoUtenza());
							posizioneOsapTemporanee.setSuperficie(posizioneVisuraOsapTemporanea.getSuperficie());
							posizioneOsapTemporanee.setZona(posizioneVisuraOsapTemporanea.getZona());

							posizioniOsapTemporanee.add(posizioneOsapTemporanee);
						}
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
		return "visuraTosapTemporaneaRichiesta";
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
