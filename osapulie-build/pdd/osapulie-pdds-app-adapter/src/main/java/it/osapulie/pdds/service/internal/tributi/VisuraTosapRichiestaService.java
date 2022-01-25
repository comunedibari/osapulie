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
import it.osapulie.pdds.ws.client.visuraosappermanente.PosizioneVisuraOsapPermanente;
import it.osapulie.pdds.ws.client.visuraosappermanente.RataVisuraOsapPermanente;
import it.osapulie.pdds.ws.client.visuraosappermanente.VisuraOsapPermanente;
import it.osapulie.pdds.ws.client.visuraosappermanente.VisuraOsapPermanenteServiceLocator;
import it.osapulie.pdds.ws.client.visuraosappermanente.VisuraOsapPermanenteServicePortType;
import it.osapulie.tributi.web.ws.output.types.DatiOsapPermanente;
import it.osapulie.tributi.web.ws.output.types.PagamentiOsapPermanenteRichiesta;
import it.osapulie.tributi.web.ws.output.types.PagamentiOsapPermanenteRisposta;
import it.osapulie.tributi.web.ws.output.types.DatiOsapPermanente.PosizioniOsap;
import it.osapulie.tributi.web.ws.output.types.DatiOsapPermanente.Rate;
import it.osapulie.tributi.web.ws.output.types.PagamentiOsapPermanenteRisposta.Errore;

/**
 * @author Gianluca Pindinelli
 *
 */
public class VisuraTosapRichiestaService implements PddService {

	private final Logger log = LoggerFactory.getLogger(VisuraTosapRichiestaService.class);

	private XMLHelper xmlHelper;

	private String webservicesBaseUrl;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.pdds.common.PddService#getResponse(java.lang.String)
	 */
	public String getResponse(String xml) {

		log.info("getResponse :: Richiesta su porta applicativa: " + xml);

		PagamentiOsapPermanenteRichiesta richiesta = xmlHelper.unmarshal(xml, PagamentiOsapPermanenteRichiesta.class);

		PagamentiOsapPermanenteRisposta risposta = new PagamentiOsapPermanenteRisposta();

		try {
			URL wsdlLocation = new URL(webservicesBaseUrl + "/services/visuraOsapPermanenteService?wsdl");

			VisuraOsapPermanenteServiceLocator visuraOsapPermanenteServiceLocator = new VisuraOsapPermanenteServiceLocator();
			VisuraOsapPermanenteServicePortType secureSOAP11Endpoint = visuraOsapPermanenteServiceLocator.getSecureSOAP11Endpoint(wsdlLocation);

			int annoDa = richiesta.getDal().get(Calendar.YEAR);
			int annoA = richiesta.getAl().get(Calendar.YEAR);

			String codiceFiscale = richiesta.getCodiceFiscale();
			if (richiesta.getPartitaIva() != null) {
				codiceFiscale = richiesta.getPartitaIva();
			}

			VisuraOsapPermanente[] visureOsapPermanente = secureSOAP11Endpoint.getVisureOsapPermanente(codiceFiscale, annoDa, annoA);

			if (visureOsapPermanente == null || visureOsapPermanente.length == 0) {
				Errore errore = new Errore();
				errore.setCodice(3);
				risposta.setErrore(errore);
			}
			else {
				List<DatiOsapPermanente> elencoOsapPermanente = risposta.getElencoOsapPermanente();
				for (VisuraOsapPermanente visuraOsapPermanente : visureOsapPermanente) {
					DatiOsapPermanente datiOsap = new DatiOsapPermanente();

					if (visuraOsapPermanente.getAnnoRiferimento() != null) {
						datiOsap.setAnnoRiferimento(visuraOsapPermanente.getAnnoRiferimento().toString());
					}
					datiOsap.setContoCorrente(visuraOsapPermanente.getContoCorrente());

					if (visuraOsapPermanente.getDataAggiornamento() != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(visuraOsapPermanente.getDataAggiornamento());
						datiOsap.setDataAggiornamento(calendar);
					}

					datiOsap.setImportoDocumento(visuraOsapPermanente.getImportoDocumento());
					datiOsap.setNumeroDocumento(visuraOsapPermanente.getNumeroDocumento());

					// Posizioni
					PosizioneVisuraOsapPermanente[] posizioniVisuraOsapPermanente = secureSOAP11Endpoint.getPosizioniVisuraOsapPermanente(visuraOsapPermanente.getId().intValue());
					if (posizioniVisuraOsapPermanente != null) {

						List<PosizioniOsap> posizioniOsap = datiOsap.getPosizioniOsap();
						for (PosizioneVisuraOsapPermanente posizioneVisuraOsapPermanente : posizioniVisuraOsapPermanente) {
							PosizioniOsap posizioneOsap = new PosizioniOsap();

							posizioneOsap.setDescrizioneTariffa(posizioneVisuraOsapPermanente.getDescrizioneTariffa());
							posizioneOsap.setImportoDaPagare(posizioneVisuraOsapPermanente.getImportoDaPagare());
							posizioneOsap.setIndirizzoUtenza(posizioneVisuraOsapPermanente.getIndirizzoUtenza());
							if (posizioneVisuraOsapPermanente.getMesi() != null) {
								posizioneOsap.setMesi(posizioneVisuraOsapPermanente.getMesi().intValue());
							}
							posizioneOsap.setSuperficie(posizioneVisuraOsapPermanente.getSuperficie());
							posizioneOsap.setZonaUtenza(posizioneVisuraOsapPermanente.getZonaUtenza());

							posizioniOsap.add(posizioneOsap);
						}
					}

					// Rate
					RataVisuraOsapPermanente[] rateVisuraOsapPermanente = secureSOAP11Endpoint.getRateVisuraOsapPermanente(visuraOsapPermanente.getId().intValue());
					if (rateVisuraOsapPermanente != null) {
						List<Rate> rate = datiOsap.getRate();

						for (RataVisuraOsapPermanente rataVisuraOsapPermanente : rateVisuraOsapPermanente) {
							Rate rata = new Rate();

							if (rataVisuraOsapPermanente.getDataPagamento() != null) {
								Calendar calendar = Calendar.getInstance();
								calendar.setTime(visuraOsapPermanente.getDataAggiornamento());
								rata.setDataPagamento(calendar);
							}

							rata.setIdentificativoRata(rataVisuraOsapPermanente.getIdentificativoRata());
							rata.setImportoPagato(rataVisuraOsapPermanente.getImportoPagato());
							rata.setImportoRata(rataVisuraOsapPermanente.getImportoRata());
							if (rataVisuraOsapPermanente.getNumeroRata() != null) {
								rata.setNumeroRata(rataVisuraOsapPermanente.getNumeroRata().intValue());
							}
							if (rataVisuraOsapPermanente.getScadenzaRata() != null) {
								Calendar calendar = Calendar.getInstance();
								calendar.setTime(visuraOsapPermanente.getDataAggiornamento());
								rata.setScadenzaRata(calendar);
							}

							rate.add(rata);
						}
					}
					elencoOsapPermanente.add(datiOsap);
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
		return "visuraTosapRichiesta";
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
