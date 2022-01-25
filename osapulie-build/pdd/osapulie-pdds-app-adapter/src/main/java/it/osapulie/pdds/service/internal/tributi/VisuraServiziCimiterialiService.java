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
import it.osapulie.pdds.ws.client.visuraservizicimiteriali.DefuntoPosizioneVisuraServiziCimiteriali;
import it.osapulie.pdds.ws.client.visuraservizicimiteriali.LampadaVotivaPosizioneVisuraServiziCimiteriali;
import it.osapulie.pdds.ws.client.visuraservizicimiteriali.PosizioneVisuraServiziCimiteriali;
import it.osapulie.pdds.ws.client.visuraservizicimiteriali.RataVisuraServiziCimiteriali;
import it.osapulie.pdds.ws.client.visuraservizicimiteriali.VisuraServiziCimiteriali;
import it.osapulie.pdds.ws.client.visuraservizicimiteriali.VisuraServiziCimiterialiServiceLocator;
import it.osapulie.pdds.ws.client.visuraservizicimiteriali.VisuraServiziCimiterialiServicePortType;
import it.osapulie.tributi.web.ws.output.types.PagamentiServiziCimiterialiRichiesta;
import it.osapulie.tributi.web.ws.output.types.PagamentiServiziCimiterialiRisposta;
import it.osapulie.tributi.web.ws.output.types.PagamentiServiziCimiterialiType;
import it.osapulie.tributi.web.ws.output.types.PagamentiServiziCimiterialiRisposta.Errore;
import it.osapulie.tributi.web.ws.output.types.PagamentiServiziCimiterialiType.PosizioniServiziCimiteriali;
import it.osapulie.tributi.web.ws.output.types.PagamentiServiziCimiterialiType.Rate;
import it.osapulie.tributi.web.ws.output.types.PagamentiServiziCimiterialiType.PosizioniServiziCimiteriali.Defunti;
import it.osapulie.tributi.web.ws.output.types.PagamentiServiziCimiterialiType.PosizioniServiziCimiteriali.LampadaVotiva;

/**
 * @author Gianluca Pindinelli
 *
 */
public class VisuraServiziCimiterialiService implements PddService {

	private final Logger log = LoggerFactory.getLogger(VisuraServiziCimiterialiService.class);

	private XMLHelper xmlHelper;

	private String webservicesBaseUrl;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.pdds.common.PddService#getResponse(java.lang.String)
	 */
	public String getResponse(String xml) {

		log.info("getResponse :: Richiesta su porta applicativa: " + xml);

		PagamentiServiziCimiterialiRichiesta richiesta = xmlHelper.unmarshal(xml, PagamentiServiziCimiterialiRichiesta.class);

		PagamentiServiziCimiterialiRisposta risposta = new PagamentiServiziCimiterialiRisposta();

		try {
			URL wsdlLocation = new URL(webservicesBaseUrl + "/services/visuraServiziCimiterialiService?wsdl");
			VisuraServiziCimiterialiServiceLocator visuraServiziCimiterialiServiceLocator = new VisuraServiziCimiterialiServiceLocator();
			VisuraServiziCimiterialiServicePortType secureSOAP11Endpoint = visuraServiziCimiterialiServiceLocator.getSecureSOAP11Endpoint(wsdlLocation);

			int annoDa = richiesta.getDal().get(Calendar.YEAR);
			int annoA = richiesta.getAl().get(Calendar.YEAR);

			String codiceFiscale = richiesta.getCodiceFiscale();
			if (richiesta.getPartitaIva() != null) {
				codiceFiscale = richiesta.getPartitaIva();
			}

			VisuraServiziCimiteriali[] visureServiziCimiteriali = secureSOAP11Endpoint.getVisureServiziCimiteriali(codiceFiscale, annoDa, annoA);

			if (visureServiziCimiteriali == null || visureServiziCimiteriali.length == 0) {
				Errore errore = new Errore();
				errore.setCodice(3);
				risposta.setErrore(errore);
			}
			else {

				List<PagamentiServiziCimiterialiType> elencoPagamentiCimiteriali = risposta.getElencoPagamentiCimiteriali();

				for (VisuraServiziCimiteriali visuraServiziCimiteriali : visureServiziCimiteriali) {

					PagamentiServiziCimiterialiType pagamentiServiziCimiterialiType = new PagamentiServiziCimiterialiType();

					if (visuraServiziCimiteriali.getAnnoRiferimento() != null) {
						pagamentiServiziCimiterialiType.setAnnoRiferimento(visuraServiziCimiteriali.getAnnoRiferimento().intValue());
					}
					pagamentiServiziCimiterialiType.setContoCorrente(visuraServiziCimiteriali.getContoCorrente());
					if (visuraServiziCimiteriali.getDataAggiornamento() != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(visuraServiziCimiteriali.getDataAggiornamento());
						pagamentiServiziCimiterialiType.setDataAggiornamento(calendar);
					}
					pagamentiServiziCimiterialiType.setImportoDocumento(visuraServiziCimiteriali.getImportoDocumento());
					pagamentiServiziCimiterialiType.setNumeroDocumento(visuraServiziCimiteriali.getNumeroDocumento());

					// Posizioni
					PosizioneVisuraServiziCimiteriali[] posizioniVisuraServiziCimiteriali = secureSOAP11Endpoint.getPosizioniVisuraServiziCimiteriali(visuraServiziCimiteriali.getId().intValue());
					if (posizioniVisuraServiziCimiteriali != null) {
						List<PosizioniServiziCimiteriali> posizioniServiziCimiteriali = pagamentiServiziCimiterialiType.getPosizioniServiziCimiteriali();
						for (PosizioneVisuraServiziCimiteriali posizioneVisuraServiziCimiteriali : posizioniVisuraServiziCimiteriali) {
							PosizioniServiziCimiteriali posizioneServiziCimiteriali = new PosizioniServiziCimiteriali();

							posizioneServiziCimiteriali.setNomecimitero(posizioneVisuraServiziCimiteriali.getNomecimitero());
							posizioneServiziCimiteriali.setPosizione(posizioneVisuraServiziCimiteriali.getPosizione());

							// Lampade votive
							LampadaVotivaPosizioneVisuraServiziCimiteriali[] lampadeVotivePosizioneVisuraServiziCimiteriali = secureSOAP11Endpoint
									.getLampadeVotivePosizioneVisuraServiziCimiteriali(posizioneVisuraServiziCimiteriali.getId().intValue());
							if (lampadeVotivePosizioneVisuraServiziCimiteriali != null) {
								List<LampadaVotiva> lampadeVotive = posizioneServiziCimiteriali.getLampadaVotiva();
								for (LampadaVotivaPosizioneVisuraServiziCimiteriali lampadaVotivaPosizioneVisuraServiziCimiteriali : lampadeVotivePosizioneVisuraServiziCimiteriali) {
									LampadaVotiva lampadaVotiva = new LampadaVotiva();

									lampadaVotiva.setImportoLampada(lampadaVotivaPosizioneVisuraServiziCimiteriali.getImportoLampada());
									if (lampadaVotivaPosizioneVisuraServiziCimiteriali.getMesi() != null) {
										lampadaVotiva.setMesi(lampadaVotivaPosizioneVisuraServiziCimiteriali.getMesi().intValue());
									}
									lampadaVotiva.setTariffa(lampadaVotivaPosizioneVisuraServiziCimiteriali.getTariffa());
									lampadeVotive.add(lampadaVotiva);
								}
							}

							// Defunti
							DefuntoPosizioneVisuraServiziCimiteriali[] defuntiPosizioneVisuraServiziCimiteriali = secureSOAP11Endpoint
									.getDefuntiPosizioneVisuraServiziCimiteriali(posizioneVisuraServiziCimiteriali.getId().intValue());

							if (defuntiPosizioneVisuraServiziCimiteriali != null) {
								List<Defunti> defunti = posizioneServiziCimiteriali.getDefunti();
								for (DefuntoPosizioneVisuraServiziCimiteriali defuntoPosizioneVisuraServiziCimiteriali : defuntiPosizioneVisuraServiziCimiteriali) {
									Defunti defunto = new Defunti();

									if (defuntoPosizioneVisuraServiziCimiteriali.getDataMorte() != null) {
										Calendar calendar = Calendar.getInstance();
										calendar.setTime(defuntoPosizioneVisuraServiziCimiteriali.getDataMorte());
										defunto.setDataMorte(calendar);
									}
									if (defuntoPosizioneVisuraServiziCimiteriali.getDataNascita() != null) {
										Calendar calendar = Calendar.getInstance();
										calendar.setTime(defuntoPosizioneVisuraServiziCimiteriali.getDataNascita());
										defunto.setDataNascita(calendar);
									}
									defunto.setNomeDefunto(defuntoPosizioneVisuraServiziCimiteriali.getNomeDefunto());

									defunti.add(defunto);
								}
							}
							posizioniServiziCimiteriali.add(posizioneServiziCimiteriali);
						}
					}

					// Rate
					RataVisuraServiziCimiteriali[] rateVisuraServiziCimiteriali = secureSOAP11Endpoint.getRateVisuraServiziCimiteriali(visuraServiziCimiteriali.getId().intValue());
					if (rateVisuraServiziCimiteriali != null) {
						List<Rate> rate = pagamentiServiziCimiterialiType.getRate();
						for (RataVisuraServiziCimiteriali rataVisuraServiziCimiteriali : rateVisuraServiziCimiteriali) {

							Rate rata = new Rate();
							if (rataVisuraServiziCimiteriali.getDataPagamento() != null) {
								Calendar calendar = Calendar.getInstance();
								calendar.setTime(rataVisuraServiziCimiteriali.getDataPagamento());
								rata.setDataPagamento(calendar);
							}

							rata.setIdentificativoRata(rataVisuraServiziCimiteriali.getIdentificativoRata());
							rata.setImportoPagato(rataVisuraServiziCimiteriali.getImportoPagato());
							rata.setImportoRata(rataVisuraServiziCimiteriali.getImportoRata());
							if (rataVisuraServiziCimiteriali.getNumeroRata() != null) {
								rata.setNumeroRata(rataVisuraServiziCimiteriali.getNumeroRata().intValue());
							}

							if (rataVisuraServiziCimiteriali.getScadenzaRata() != null) {
								Calendar calendar = Calendar.getInstance();
								calendar.setTime(rataVisuraServiziCimiteriali.getScadenzaRata());
								rata.setScadenzaRata(calendar);
							}
							rate.add(rata);
						}
					}
					elencoPagamentiCimiteriali.add(pagamentiServiziCimiterialiType);
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
		return "visuraServiziCimiteriali";
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
