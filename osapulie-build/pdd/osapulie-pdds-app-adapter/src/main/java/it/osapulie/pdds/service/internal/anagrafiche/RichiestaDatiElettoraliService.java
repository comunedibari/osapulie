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

//import org.apache.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.osapulie.anagrafe.web.ws.output.types.DatiElettorali;
import it.osapulie.anagrafe.web.ws.output.types.PosizioniElettorali;
import it.osapulie.anagrafe.web.ws.output.types.RichiestaDatiElettorali;
import it.osapulie.anagrafe.web.ws.output.types.DatiElettorali.Errore;
import it.osapulie.pdds.adapter.util.XMLHelper;
import it.osapulie.pdds.common.PddService;
import it.osapulie.pdds.ws.client.visuraposizioneelettorale.VisuraPosizioneElettorale;
import it.osapulie.pdds.ws.client.visuraposizioneelettorale.VisuraPosizioneElettoraleServiceLocator;
import it.osapulie.pdds.ws.client.visuraposizioneelettorale.VisuraPosizioneElettoraleServicePortType;

/**
 *
 * @author Gianluca Pindinelli
 *
 */
public class RichiestaDatiElettoraliService implements PddService {

	private final Logger log = LoggerFactory.getLogger(RichiestaDatiElettoraliService.class);

	private XMLHelper xmlHelper;

	private String webservicesBaseUrl;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.pdds.service.AbstractResponse#getResponse(java.lang.String)
	 */
	@Override
	public String getResponse(String xml) {

		RichiestaDatiElettorali richiesta = xmlHelper.unmarshal(xml, RichiestaDatiElettorali.class);

		DatiElettorali risposta = new DatiElettorali();

		String result = null;

		try {

			URL wsdlLocation = new URL(webservicesBaseUrl + "/services/visuraPosizioneElettoraleService?wsdl");

			VisuraPosizioneElettoraleServiceLocator visuraPosizioneElettoraleServiceLocator = new VisuraPosizioneElettoraleServiceLocator();
			VisuraPosizioneElettoraleServicePortType secureSOAP11Endpoint = visuraPosizioneElettoraleServiceLocator.getSecureSOAP11Endpoint(wsdlLocation);
			VisuraPosizioneElettorale[] visuraPosizioneElettorales = secureSOAP11Endpoint.getVisurePosizioniElettorali(richiesta.getCodiceFiscale());

			if (visuraPosizioneElettorales == null || visuraPosizioneElettorales.length == 0) {
				Errore errore = new Errore();
				errore.setCodice(3);
				risposta.setErrore(errore);
			}
			else {

				List<PosizioniElettorali> posizioniElettorali = risposta.getPosizioniElettorali();

				for (VisuraPosizioneElettorale visuraPosizioneElettorale2 : visuraPosizioneElettorales) {

					PosizioniElettorali posizioneElettorale = new PosizioniElettorali();

					posizioneElettorale.setToponimoIndirizzo(visuraPosizioneElettorale2.getToponimoIndirizzo());
					posizioneElettorale.setDescrizioneVia(visuraPosizioneElettorale2.getDescrizioneVia());
					posizioneElettorale.setNumeroCivico(visuraPosizioneElettorale2.getNumeroCivico());
					posizioneElettorale.setEsponente(visuraPosizioneElettorale2.getEsponente());
					posizioneElettorale.setPiano(visuraPosizioneElettorale2.getPiano());
					posizioneElettorale.setScala(visuraPosizioneElettorale2.getScala());
					posizioneElettorale.setInterno(visuraPosizioneElettorale2.getInterno());
					posizioneElettorale.setNome(visuraPosizioneElettorale2.getNome());
					posizioneElettorale.setCognome(visuraPosizioneElettorale2.getCognome());
					if (visuraPosizioneElettorale2.getDataNascita() != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(visuraPosizioneElettorale2.getDataNascita());
						posizioneElettorale.setDataNascita(calendar);
					}
					if (visuraPosizioneElettorale2.getDataVerbaleIscrizione() != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(visuraPosizioneElettorale2.getDataVerbaleIscrizione());
						posizioneElettorale.setDataVerbaleIscrizione(calendar);
					}

					posizioneElettorale.setNumVerbaleIscrizione(visuraPosizioneElettorale2.getNumVerbaleIscrizione());
					if (visuraPosizioneElettorale2.getAnnoIscrizioneElett() != null) {
						posizioneElettorale.setAnnoIscrizioneElett(visuraPosizioneElettorale2.getAnnoIscrizioneElett().intValue());
					}

					posizioneElettorale.setNumeroFascicolo(visuraPosizioneElettorale2.getNumeroFascicolo());
					posizioneElettorale.setNumeroListaGenerale(visuraPosizioneElettorale2.getNumeroListaGenerale());
					posizioneElettorale.setNumeroListaSezionale(visuraPosizioneElettorale2.getNumeroListaSezionale());
					posizioneElettorale.setDescrizioneLista(visuraPosizioneElettorale2.getDescrizioneLista());
					posizioneElettorale.setTipoElettore(visuraPosizioneElettorale2.getTipoElettore());

					if (visuraPosizioneElettorale2.getNumeroSezione() != null) {
						posizioneElettorale.setNumeroSezione(visuraPosizioneElettorale2.getNumeroSezione().intValue());
					}

					posizioneElettorale.setNumeroTesseraElettorale(visuraPosizioneElettorale2.getNumeroTesseraElettorale());

					if (visuraPosizioneElettorale2.getDataRilascioTesseraElett() != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(visuraPosizioneElettorale2.getDataRilascioTesseraElett());
						posizioneElettorale.setDataRilascioTesseraElett(calendar);
					}

					posizioneElettorale.setIscrizioneAlboPresidente(visuraPosizioneElettorale2.getIscrizioneAlboPresidente());
					posizioneElettorale.setIscrizioneAlboScrutatori(visuraPosizioneElettorale2.getIscrizioneAlboScrutatori());

					if (visuraPosizioneElettorale2.getNumVerbIscrAlboScrut() != null) {
						posizioneElettorale.setNumVerbIscrAlboScrut(visuraPosizioneElettorale2.getNumVerbIscrAlboScrut().intValue());
					}

					if (visuraPosizioneElettorale2.getDataVerbIscrAlboScrut() != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(visuraPosizioneElettorale2.getDataVerbIscrAlboScrut());
						posizioneElettorale.setDataVerbIscrAlboScrut(calendar.toString());
					}

					posizioniElettorali.add(posizioneElettorale);
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

		result = xmlHelper.marshal(risposta);

		return result;

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.pdds.service.AbstractResponse#getName()
	 */
	@Override
	public String getName() {
		return "richiestaDatiElettorali";
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
