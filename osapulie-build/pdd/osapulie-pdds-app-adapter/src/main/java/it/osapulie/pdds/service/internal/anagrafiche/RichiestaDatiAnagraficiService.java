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

import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici.ComponentiNucleoFamiliare;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici.ComponentiNucleoFamiliare.PensioniList;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici.Errore;
import it.osapulie.anagrafe.web.ws.output.types.ObjectFactory;
import it.osapulie.anagrafe.web.ws.output.types.RichiestaDatiAnagrafici;
import it.osapulie.pdds.adapter.util.XMLHelper;
import it.osapulie.pdds.common.PddService;
import it.osapulie.pdds.ws.client.visuraanagrafica.PensioneComponenteVisuraAnagrafica;
import it.osapulie.pdds.ws.client.visuraanagrafica.VisuraAnagrafica;
import it.osapulie.pdds.ws.client.visuraanagrafica.VisuraAnagraficaServiceLocator;
import it.osapulie.pdds.ws.client.visuraanagrafica.VisuraAnagraficaServicePortType;

/**
 *
 * @author Gianluca Pindinelli
 *
 */
public class RichiestaDatiAnagraficiService implements PddService {

	private final Logger log = LoggerFactory.getLogger(RichiestaDatiAnagraficiService.class);

	private XMLHelper xmlHelper;

	private String webservicesBaseUrl;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.pdds.service.AbstractResponse#getResponse(java.lang.String)
	 */
	@Override
	public String getResponse(String xml) {

		RichiestaDatiAnagrafici richiesta = xmlHelper.unmarshal(xml, RichiestaDatiAnagrafici.class);

		DatiAnagrafici risposta = new DatiAnagrafici();

		String result = null;

		try {

			URL wsdlLocation = new URL(webservicesBaseUrl + "/services/visuraAnagraficaService?wsdl");

			VisuraAnagraficaServiceLocator visuraAnagraficaServiceLocator = new VisuraAnagraficaServiceLocator();
			VisuraAnagraficaServicePortType secureSOAP11Endpoint = visuraAnagraficaServiceLocator.getSecureSOAP11Endpoint(wsdlLocation);
			VisuraAnagrafica[] visuraAnagraficaByCodiceFiscale = secureSOAP11Endpoint.getVisuraAnagrafica(richiesta.getCodiceFiscale());

			if (visuraAnagraficaByCodiceFiscale == null || visuraAnagraficaByCodiceFiscale.length == 0) {
				Errore errore = new Errore();
				errore.setCodice(3);
				risposta.setErrore(errore);
			}
			else {

				VisuraAnagrafica visuraAnagrafica = visuraAnagraficaByCodiceFiscale[0];

				risposta.setCap(visuraAnagrafica.getCap());
				risposta.setToponimoIndirizzo(visuraAnagrafica.getToponimoIndirizzo());
				risposta.setDescrizioneVia(visuraAnagrafica.getDescrizioneVia());
				risposta.setNumeroCivico(visuraAnagrafica.getNumeroCivico());
				risposta.setEsponente(visuraAnagrafica.getEsponente());
				if (visuraAnagrafica.getPiano() != null) {
					risposta.setPiano(visuraAnagrafica.getPiano().toString());
				}
				risposta.setScala(visuraAnagrafica.getScala());
				risposta.setInterno(visuraAnagrafica.getInterno());

				// Componenti
				VisuraAnagrafica[] componentiVisuraAnagrafica = secureSOAP11Endpoint.getVisureAnagraficheNucleoFamiliare(visuraAnagrafica.getIdentificativoNucleoFamiliare().intValue());
				if (componentiVisuraAnagrafica != null && componentiVisuraAnagrafica.length > 0) {

					List<ComponentiNucleoFamiliare> componentiNucleoFamiliare = risposta.getComponentiNucleoFamiliare();

					for (VisuraAnagrafica componenteVisuraAnagraficaWs : componentiVisuraAnagrafica) {

						ComponentiNucleoFamiliare componente = new ComponentiNucleoFamiliare();

						componente.setIdentificativo(componenteVisuraAnagraficaWs.getId().toString());
						componente.setCodiceFiscale(componenteVisuraAnagraficaWs.getCodiceFiscale());
						componente.setCognome(componenteVisuraAnagraficaWs.getCognome());
						componente.setNome(componenteVisuraAnagraficaWs.getNome());
						if (componenteVisuraAnagraficaWs.getDataNascita() != null) {
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(componenteVisuraAnagraficaWs.getDataNascita());
							componente.setDataNascita(calendar);
						}
						componente.setSesso(componenteVisuraAnagraficaWs.getSesso());
						componente.setStatoCivile(componenteVisuraAnagraficaWs.getStatoCivile());
						componente.setCognomeNomeConiuge(componenteVisuraAnagraficaWs.getCognomeNomeConiuge());
						componente.setRelazioneParentela(componenteVisuraAnagraficaWs.getRelazioneParentela());
						componente.setCittadinanzaItaliana(componenteVisuraAnagraficaWs.getCittadinanzaItaliana());
						componente.setCodiceIstatComuneNascita(componenteVisuraAnagraficaWs.getCodiceIstatComuneNascita());
						componente.setDescrizioneComuneEsteroNascita(componenteVisuraAnagraficaWs.getDescrizioneComuneEsteroNascita());
						componente.setStatoEsteroNascita(componenteVisuraAnagraficaWs.getStatoEsteroNascita());
						if (componenteVisuraAnagraficaWs.getNumeroAttoNascita() != null) {
							componente.setNumeroAttoNascita(componenteVisuraAnagraficaWs.getNumeroAttoNascita().intValue());
						}
						componente.setParteNascita(componenteVisuraAnagraficaWs.getParteNascita());
						componente.setSerieNascita(componenteVisuraAnagraficaWs.getSerieNascita());
						if (componenteVisuraAnagraficaWs.getAnnoAttoNascita() != null) {
							componente.setAnnoAttoNascita(componenteVisuraAnagraficaWs.getAnnoAttoNascita().intValue());
						}
						componente.setUfficioNascita(componenteVisuraAnagraficaWs.getUfficioNascita());
						if (componenteVisuraAnagraficaWs.getNumeroAttoNascitaTrascritto() != null) {
							componente.setNumeroAttoNascitaTrascritto(componenteVisuraAnagraficaWs.getNumeroAttoNascitaTrascritto().intValue());
						}
						componente.setParteNascitaTrascritto(componenteVisuraAnagraficaWs.getParteNascitaTrascritto());
						componente.setSerieNascitaTrascritto(componenteVisuraAnagraficaWs.getSerieNascitaTrascritto());
						if (componenteVisuraAnagraficaWs.getAnnoNascitaTrascritto() != null) {
							componente.setAnnoNascitaTrascritto(componenteVisuraAnagraficaWs.getAnnoNascitaTrascritto().intValue());
						}
						componente.setCodiceIstatComuneNascitaTrascritto(componenteVisuraAnagraficaWs.getCodiceIstatComuneNascitaTrascritto());
						if (componenteVisuraAnagraficaWs.getNumeroAttoMatrimonio() != null) {
							componente.setNumeroAttoMatrimonio(componenteVisuraAnagraficaWs.getNumeroAttoMatrimonio().intValue());
						}
						componente.setParteMatrimonio(componenteVisuraAnagraficaWs.getParteMatrimonio());
						componente.setSerieMatrimonio(componenteVisuraAnagraficaWs.getSerieMatrimonio());
						if (componenteVisuraAnagraficaWs.getAnnoMatrimonio() != null) {
							componente.setAnnoMatrimonio(componenteVisuraAnagraficaWs.getAnnoMatrimonio().intValue());
						}
						componente.setUfficioMatrimonio(componenteVisuraAnagraficaWs.getUfficioMatrimonio());
						componente.setCodiceIstatComuneMatrimonio(componenteVisuraAnagraficaWs.getCodiceIstatComuneMatrimonio());
						componente.setLuogoMatrimonio(componenteVisuraAnagraficaWs.getLuogoMatrimonio());
						if (componenteVisuraAnagraficaWs.getDataMatrimonio() != null) {
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(componenteVisuraAnagraficaWs.getDataMatrimonio());
							componente.setDataMatrimonio(calendar);
						}
						if (componenteVisuraAnagraficaWs.getNumeroAttoMatrimonioTrascritto() != null) {
							componente.setNumeroAttoMatrimonioTrascritto(componenteVisuraAnagraficaWs.getNumeroAttoMatrimonioTrascritto().intValue());
						}
						componente.setParteMatrimonioTrascritto(componenteVisuraAnagraficaWs.getParteMatrimonioTrascritto());
						componente.setSerieMatrimonioTrascritto(componenteVisuraAnagraficaWs.getSerieMatrimonioTrascritto());
						if (componenteVisuraAnagraficaWs.getAnnoMatrimonioTrascritto() != null) {
							componente.setAnnoMatrimonioTrascritto(componenteVisuraAnagraficaWs.getAnnoMatrimonioTrascritto().intValue());
						}
						componente.setCodiceIstatComuneMatrimonioTrascritto(componenteVisuraAnagraficaWs.getCodiceIstatComuneMatrimonioTrascritto());

						if (componenteVisuraAnagraficaWs.getDataDecorrenzaDivorzio() != null) {
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(componenteVisuraAnagraficaWs.getDataDecorrenzaDivorzio());
							componente.setDataDecorrenzaDivorzio(calendar);
						}
						if (componenteVisuraAnagraficaWs.getDataSentenzaDivorzio() != null) {
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(componenteVisuraAnagraficaWs.getDataSentenzaDivorzio());
							componente.setDataSentenzaDivorzio(calendar);
						}
						if (componenteVisuraAnagraficaWs.getNumeroSentenzaDivorzio() != null) {
							componente.setNumeroSentenzaDivorzio(componenteVisuraAnagraficaWs.getNumeroSentenzaDivorzio());
						}
						componente.setTipoDivorzio(componenteVisuraAnagraficaWs.getTipoDivorzio());
						if (componenteVisuraAnagraficaWs.getDataAttoDivorzio() != null) {
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(componenteVisuraAnagraficaWs.getDataAttoDivorzio());
							componente.setDataAttoDivorzio(calendar);
						}
						if (componenteVisuraAnagraficaWs.getNumeroAttoDivorzio() != null) {
							componente.setNumeroAttoDivorzio(componenteVisuraAnagraficaWs.getNumeroAttoDivorzio().intValue());
						}

						componente.setCodiceIstatComuneTribunaleDivorzio(componenteVisuraAnagraficaWs.getCodiceIstatComuneTribunaleDivorzio());
						componente.setParteDivorzio(componenteVisuraAnagraficaWs.getParteDivorzio());
						componente.setSerieDivorzio(componenteVisuraAnagraficaWs.getSerieDivorzio());
						componente.setVolumeDivorzio(componenteVisuraAnagraficaWs.getVolumeDivorzio());
						componente.setUfficioDivorzio(componenteVisuraAnagraficaWs.getUfficioDivorzio());
						if (componenteVisuraAnagraficaWs.getDataAttoDivorzioTrascritto() != null) {
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(componenteVisuraAnagraficaWs.getDataAttoDivorzioTrascritto());
							componente.setDataAttoDivorzioTrascritto(calendar);
						}
						if (componenteVisuraAnagraficaWs.getNumeroAttoDivorzioTrascritto() != null) {
							componente.setNumeroAttoDivorzioTrascritto(componenteVisuraAnagraficaWs.getNumeroAttoDivorzioTrascritto().intValue());
						}
						componente.setParteDivorzioTrascritto(componenteVisuraAnagraficaWs.getParteDivorzioTrascritto());
						componente.setSerieDivorzioTrascritto(componenteVisuraAnagraficaWs.getSerieDivorzioTrascritto());
						componente.setVolumeDivorzioTrascritto(componenteVisuraAnagraficaWs.getVolumeDivorzioTrascritto());
						componente.setUfficioDivorzioTrascritto(componenteVisuraAnagraficaWs.getUfficioDivorzioTrascritto());
						componente.setCodiceIstatComuneTrascrizioneDivorzio(componenteVisuraAnagraficaWs.getCodiceIstatComuneTrascrizioneDivorzio());
						if (componenteVisuraAnagraficaWs.getDataAttoVedovanza() != null) {
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(componenteVisuraAnagraficaWs.getDataAttoVedovanza());
							componente.setDataAttoVedovanza(calendar);
						}
						if (componenteVisuraAnagraficaWs.getNumeroAttoVedovanza() != null) {
							componente.setNumeroAttoVedovanza(componenteVisuraAnagraficaWs.getNumeroAttoVedovanza().intValue());
						}
						componente.setParteVedovanza(componenteVisuraAnagraficaWs.getParteVedovanza());
						componente.setSerieVedovanza(componenteVisuraAnagraficaWs.getSerieVedovanza());
						componente.setVolumeVedovanza(componenteVisuraAnagraficaWs.getVolumeVedovanza());
						componente.setUfficioVedovanza(componenteVisuraAnagraficaWs.getUfficioVedovanza());

						componente.setCodiceIstatComuneMorte(componenteVisuraAnagraficaWs.getCodiceIstatComuneMorte());
						if (componenteVisuraAnagraficaWs.getDataMorte() != null) {
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(componenteVisuraAnagraficaWs.getDataMorte());
							componente.setDataMorte(calendar);
						}
						if (componenteVisuraAnagraficaWs.getNumeroAttoMorte() != null) {
							componente.setNumeroAttoMorte(componenteVisuraAnagraficaWs.getNumeroAttoMorte().intValue());
						}

						componente.setParteMorte(componenteVisuraAnagraficaWs.getParteMorte());
						componente.setSerieMorte(componenteVisuraAnagraficaWs.getSerieMorte());
						componente.setUfficioMorte(componenteVisuraAnagraficaWs.getUfficioMorte());
						if (componenteVisuraAnagraficaWs.getAnnoMorte() != null) {
							componente.setAnnoMorte(componenteVisuraAnagraficaWs.getAnnoMorte().intValue());
						}

						componente.setCodiceIstatComuneMorteConiugeTrascritto(componenteVisuraAnagraficaWs.getCodiceIstatComuneMorteConiugeTrascritto());
						if (componenteVisuraAnagraficaWs.getNumeroAttoMorteConiugeTrascritto() != null) {
							componente.setNumeroAttoMorteConiugeTrascritto(componenteVisuraAnagraficaWs.getNumeroAttoMorteConiugeTrascritto().intValue());
						}

						componente.setParteMorteConiugeTrascritto(componenteVisuraAnagraficaWs.getParteMorteConiugeTrascritto());
						componente.setSerieMorteConiugeTrascritto(componenteVisuraAnagraficaWs.getSerieMorteConiugeTrascritto());
						if (componenteVisuraAnagraficaWs.getAnnoMorteConiugeTrascritto() != null) {
							componente.setAnnoMorteConiugeTrascritto(componenteVisuraAnagraficaWs.getAnnoMorteConiugeTrascritto().intValue());
						}

						componente.setProfessione(componenteVisuraAnagraficaWs.getProfessione());
						componente.setTitoloStudio(componenteVisuraAnagraficaWs.getTitoloStudio());

						// Pensioni
						PensioneComponenteVisuraAnagrafica[] pensioniComponenteVisuraAnagrafica = secureSOAP11Endpoint
								.getPensioniComponenteVisuraAnagrafica(componenteVisuraAnagraficaWs.getId().intValue());
						if (pensioniComponenteVisuraAnagrafica != null) {
							List<PensioniList> pensioniList = componente.getPensioniList();
							for (PensioneComponenteVisuraAnagrafica pensioneComponenteVisuraAnagrafica : pensioniComponenteVisuraAnagrafica) {
								PensioniList pensione = new PensioniList();

								pensione.setCodicePensione(pensioneComponenteVisuraAnagrafica.getCodicePensione());
								if (pensioneComponenteVisuraAnagrafica.getNumeroPensione() != null) {
									pensione.setNumeroPensione(pensioneComponenteVisuraAnagrafica.getNumeroPensione());
								}

								pensioniList.add(pensione);
							}
						}

						componente.setNumeroCartaIdentita(componenteVisuraAnagraficaWs.getNumeroCartaIdentita());
						if (componenteVisuraAnagraficaWs.getDataCartaIdentita() != null) {
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(componenteVisuraAnagraficaWs.getDataCartaIdentita());
							componente.setDataCartaIdentita(calendar);
						}
						componente.setValidaCartaIdentita(componenteVisuraAnagraficaWs.getValidaCartaIdentita());
						componente.setIdentificativoNucleoFamiliare(componenteVisuraAnagraficaWs.getIdentificativoNucleoFamiliare().intValue());
						componentiNucleoFamiliare.add(componente);
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

		if (risposta != null) {
			ObjectFactory factory = new ObjectFactory();
			JAXBElement<DatiAnagrafici> visuraAnagrafica = factory.createVisuraAnagrafica(risposta);
			result = xmlHelper.marshal(visuraAnagrafica);
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
		return "richiestaDatiAnagrafici";
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
