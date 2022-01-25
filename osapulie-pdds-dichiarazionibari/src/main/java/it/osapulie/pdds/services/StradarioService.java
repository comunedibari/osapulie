/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pdds.services;

import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.ws.BindingProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import it.osapulie.pdds.adapter.util.XMLHelper;
import it.osapulie.pdds.common.PddService;
import it.osapulie.servizi.web.ws.types.Civico;
import it.osapulie.servizi.web.ws.types.Indirizzo;
import it.osapulie.servizi.web.ws.types.StradarioRichiesta;
import it.osapulie.servizi.web.ws.types.StradarioRisposta;
import it.osapulie.servizi.web.ws.types.StradarioRisposta.Errore;
import it.osapulie.servizi.web.ws.types.Via;
import it.sinergis.xsd.toponomastica.Coordinate;
import it.sinergis.xsd.toponomastica.DataOutputAbstractType;
import it.sinergis.xsd.toponomastica.Header;
import it.sinergis.xsd.toponomastica.IndirizzoRicercato;
import it.sinergis.xsd.toponomastica.NumeroCivico;
import it.sinergis.xsd.toponomastica.ObjectFactory;
import it.sinergis.xsd.toponomastica.Response;
import it.sinergis.xsd.toponomastica.RicercaCivicoRequestType;
import it.sinergis.xsd.toponomastica.RicercaCivicoResponseType;
import it.sinergis.xsd.toponomastica.TP08RicercaViaRequest;
import it.sinergis.xsd.toponomastica.TP08RicercaViaResponse;
import sinergis.project.toponomastica.RicercaCivicoEPService;
import sinergis.project.toponomastica.RicercaCivicoSEI;
import sinergis.project.toponomastica.RicercaViaEPService;
import sinergis.project.toponomastica.RicercaViaSEI;

/**
 *
 * @author Gianluca Pindinelli
 *
 */
public class StradarioService implements PddService {

	private static final String WSDL_RICERCA_CIVICO_STRADARIO = "wsdl/toponomastica/RicercaCivicoEPService.wsdl";
	private static final String WSDL_RICERCA_VIA_STRADARIO = "wsdl/toponomastica/RicercaViaEPService.wsdl";

	private final Logger log = LoggerFactory.getLogger(StradarioService.class);

	private XMLHelper xmlHelper;
	private XMLHelper xmlHelperStradario;
	private String webserviceRicercaCivicoUrl;
	private String webserviceRicercaViaUrl;

	@Override
	public String getResponse(String xml) {

		StradarioRichiesta richiesta = xmlHelper.unmarshal(xml, StradarioRichiesta.class);

		String result = null;
		try {
			StradarioRisposta risp = risposta(richiesta);
			result = xmlHelper.marshal(risp);

		}
		catch (Exception e) {
			log.error("getResponse :: " + e.getMessage(), e);
		}

		return result;

	}

	private StradarioRisposta risposta(StradarioRichiesta richiesta) {

		it.osapulie.servizi.web.ws.types.StradarioRichiesta.Indirizzo indirizzoRichiesta = richiesta.getIndirizzo();

		StradarioRisposta risposta = new StradarioRisposta();

		ObjectFactory objectFactory = new ObjectFactory();

		it.sinergis.xsd.toponomastica.Request request = objectFactory.createRequest();
		RicercaCivicoRequestType ricercaCivicoRequest = objectFactory.createRicercaCivicoRequestType();

		IndirizzoRicercato indirizzoRicercato = new IndirizzoRicercato();
		indirizzoRicercato.setEnteCod("01");
		if (indirizzoRichiesta.getDenominazione() != null) {
			indirizzoRicercato.setTipo("n");
			indirizzoRicercato.setViaDenomCod(indirizzoRichiesta.getDenominazione());
		}
		else if (indirizzoRichiesta.getIdentificativo() != null) {
			indirizzoRicercato.setTipo("c");
			indirizzoRicercato.setViaDenomCod(indirizzoRichiesta.getIdentificativo());
		}

		if (indirizzoRichiesta.getCivico() != null) {
			NumeroCivico numeroCivicoRicercato = new NumeroCivico();
			numeroCivicoRicercato.setCivicoNum(indirizzoRichiesta.getCivico().getNumero());
			numeroCivicoRicercato.setCivicoEsp(indirizzoRichiesta.getCivico().getEsponente());
			indirizzoRicercato.setNumeroCivico(numeroCivicoRicercato);
		}

		ricercaCivicoRequest.setIndirizzoRicercato(indirizzoRicercato);

		JAXBElement<RicercaCivicoRequestType> createServiceParamsAbstract = objectFactory.createTP03RicercaCivicoRequest(ricercaCivicoRequest);
		request.setServiceParamsAbstract(createServiceParamsAbstract);

		String stradarioRichiesta = xmlHelperStradario.marshal(request);

		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		URL wsdlLocation = classloader.getResource(WSDL_RICERCA_CIVICO_STRADARIO);

		RicercaCivicoEPService service = new RicercaCivicoEPService(wsdlLocation);

		RicercaCivicoSEI ricercaCivicoEPPort = service.getRicercaCivicoEPPort();
		BindingProvider provider = (BindingProvider) ricercaCivicoEPPort;
		provider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, webserviceRicercaCivicoUrl);

		try {
			String stradarioRicercaCivicoRisposta = ricercaCivicoEPPort.eseguiRichiesta(stradarioRichiesta);

			Response stradarioRispostaObject = xmlHelperStradario.unmarshal(stradarioRicercaCivicoRisposta, Response.class);

			Header header = stradarioRispostaObject.getHeader();

			boolean ricercaSoloVia = false;
			if (header != null && header.getIsError() != null && header.getIsError().getValue()) {
				Errore errore = new Errore();
				if (header.getErrCode().equals("VIASENZACIVICI")) {
					// ricerca solo via in servizio SIT
					TP08RicercaViaRequest tP08RicercaViaRequest = objectFactory.createTP08RicercaViaRequest();
					tP08RicercaViaRequest.setIndirizzoRicercato(indirizzoRicercato);
					tP08RicercaViaRequest.setMaxRowsRet(50L);
					JAXBElement<TP08RicercaViaRequest> createTP08RicercaViaRequest = objectFactory.createTP08RicercaViaRequest(tP08RicercaViaRequest);
					request.setServiceParamsAbstract(createTP08RicercaViaRequest);
					stradarioRichiesta = xmlHelperStradario.marshal(request);
					wsdlLocation = classloader.getResource(WSDL_RICERCA_VIA_STRADARIO);

					RicercaViaEPService ricercaViaEPService = new RicercaViaEPService(wsdlLocation);
					RicercaViaSEI ricercaViaEPPort = ricercaViaEPService.getRicercaViaEPPort();
					BindingProvider providerRicercaVia = (BindingProvider) ricercaViaEPPort;
					providerRicercaVia.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, webserviceRicercaViaUrl);

					String stradarioRicercaViaRisposta = ricercaViaEPPort.eseguiRichiesta(stradarioRichiesta);
					stradarioRispostaObject = xmlHelperStradario.unmarshal(stradarioRicercaViaRisposta, Response.class);

					header = stradarioRispostaObject.getHeader();
					if (header != null && header.getIsError() != null && header.getIsError().getValue()) {
						if (header.getErrCode().equals("NOVIA")) {
							errore.setCodice(2);
						}
						else {
							errore.setCodice(3);
						}
						errore.setDescrizione("Errore durante il caricamento delle informazioni dallo stradario: code : " + header.getErrCode() + ", message: " + header.getMessage());
						risposta.setErrore(errore);
						return risposta;
					}
					ricercaSoloVia = true;
				}
				else {
					errore.setCodice(3);
				}
				if (errore.getCodice() != 0) {
					errore.setDescrizione("Errore durante il caricamento delle informazioni dallo stradario: code : " + header.getErrCode() + ", message: " + header.getMessage());
					risposta.setErrore(errore);
					return risposta;
				}
			}

			List<it.sinergis.xsd.toponomastica.Indirizzo> indirizziStradario = null;
			List<it.sinergis.xsd.toponomastica.Via> vieStradario = null;

			// Ricerca VIA
			JAXBElement<? extends DataOutputAbstractType> dataOutputAbstract = stradarioRispostaObject.getDataOutputAbstract();
			DataOutputAbstractType value = dataOutputAbstract.getValue();

			List<Indirizzo> indirizziRisposta = new ArrayList<Indirizzo>();
			if (ricercaSoloVia) {
				JAXBElement<TP08RicercaViaResponse> createTP08RicercaViaResponse = objectFactory.createTP08RicercaViaResponse((TP08RicercaViaResponse) value);
				TP08RicercaViaResponse tp08RicercaViaResponse = createTP08RicercaViaResponse.getValue();
				if (tp08RicercaViaResponse == null || (tp08RicercaViaResponse.getVia() == null && tp08RicercaViaResponse.getElencoVie() == null)
						|| (tp08RicercaViaResponse.getVia() == null && tp08RicercaViaResponse.getElencoVie() != null && tp08RicercaViaResponse.getElencoVie().getVias() == null)
						|| (tp08RicercaViaResponse.getVia() == null && tp08RicercaViaResponse.getElencoVie() != null && tp08RicercaViaResponse.getElencoVie().getVias() != null
								&& tp08RicercaViaResponse.getElencoVie().getVias().isEmpty())) {
					Errore errore = new Errore();
					errore.setCodice(2);
					errore.setDescrizione("Errore durante il caricamento delle informazioni dallo stradario: lista indirizzi vuota o nulla.");
					risposta.setErrore(errore);
					return risposta;
				}
				vieStradario = new ArrayList<it.sinergis.xsd.toponomastica.Via>();
				if (tp08RicercaViaResponse.getElencoVie() != null) {
					vieStradario = tp08RicercaViaResponse.getElencoVie().getVias();
				}
				else {
					vieStradario.add(tp08RicercaViaResponse.getVia());
				}
				for (it.sinergis.xsd.toponomastica.Via via : vieStradario) {
					Indirizzo indirizzo = getIndirizzo(via, null);
					if (indirizzo != null) {
						indirizziRisposta.add(indirizzo);
					}
				}
			}
			else {
				JAXBElement<RicercaCivicoResponseType> createTP03RicercaCivicoResponse = objectFactory.createTP03RicercaCivicoResponse((RicercaCivicoResponseType) value);

				RicercaCivicoResponseType ricercaCivicoResponse = createTP03RicercaCivicoResponse.getValue();

				if (ricercaCivicoResponse == null || (ricercaCivicoResponse.getIndirizzo() == null && ricercaCivicoResponse.getElencoIndirizzi() == null)
						|| (ricercaCivicoResponse.getIndirizzo() == null && ricercaCivicoResponse.getElencoIndirizzi().getIndirizzos() == null)
						|| (ricercaCivicoResponse.getIndirizzo() == null && ricercaCivicoResponse.getElencoIndirizzi().getIndirizzos().isEmpty())) {
					Errore errore = new Errore();
					errore.setCodice(2);
					errore.setDescrizione("Errore durante il caricamento delle informazioni dallo stradario: lista indirizzi vuota o nulla.");
					risposta.setErrore(errore);
					return risposta;
				}
				indirizziStradario = new ArrayList<it.sinergis.xsd.toponomastica.Indirizzo>();
				if (ricercaCivicoResponse.getElencoIndirizzi() != null) {
					indirizziStradario = ricercaCivicoResponse.getElencoIndirizzi().getIndirizzos();
				}
				else {
					indirizziStradario.add(ricercaCivicoResponse.getIndirizzo());
				}
				for (it.sinergis.xsd.toponomastica.Indirizzo indirizzoStradario : indirizziStradario) {

					Indirizzo indirizzo = getIndirizzo(indirizzoStradario.getVia(), indirizzoStradario.getCivico());
					if (indirizzo != null) {
						indirizziRisposta.add(indirizzo);
					}
				}
			}

			risposta.getIndirizzo().addAll(indirizziRisposta);
		}
		catch (Exception e) {
			log.error("risposta :: " + e.getMessage(), e);
			Errore errore = new Errore();
			errore.setCodice(1);
			errore.setDescrizione("Errore durante il caricamento delle informazioni dallo stradario: " + e.getMessage());
			risposta.setErrore(errore);
			return risposta;
		}

		return risposta;
	}

	/**
	 *
	 * @param viaStradario
	 * @param civicoStradario
	 * @return
	 */
	private Indirizzo getIndirizzo(it.sinergis.xsd.toponomastica.Via viaStradario, it.sinergis.xsd.toponomastica.Civico civicoStradario) {

		Indirizzo indirizzo = new Indirizzo();

		if (viaStradario != null) {
			Via via = new Via();

			via.setIdentificativo(viaStradario.getViaCod());
			via.setDenominazione(viaStradario.getViaDenom());
			via.setTipo(viaStradario.getViaTipo());

			indirizzo.setVia(via);

			if (civicoStradario != null) {
				// Se la data fine è settata --> variazione, civico non più valido da non
				// considerare
				if (civicoStradario.getDataFine() != null) {
					return indirizzo;
				}
				Civico civico = new Civico();
				civico.setIdentificativo(civicoStradario.getCodCivico());
				BigInteger cap = civicoStradario.getCap();
				if (cap != null) {
					civico.setCap(cap.toString());
				}
				civico.setCentroStorico(civicoStradario.getCentroStorico());
				NumeroCivico numeroCivico = civicoStradario.getNumeroCivico();
				if (numeroCivico != null) {
					civico.setNumero((int) numeroCivico.getCivicoNum());
					civico.setEsponente(numeroCivico.getCivicoEsp());
				}
				civico.setLocalita(civicoStradario.getLocalitaFrazione());

				Coordinate coordinate = civicoStradario.getCoordinate();
				if (coordinate != null) {
					if (coordinate.getX() != null) {
						civico.setLongitudine(Double.parseDouble(coordinate.getX()));
					}
					if (coordinate.getY() != null) {
						civico.setLatitudine(Double.parseDouble(coordinate.getY()));
					}
				}
				civico.setPrincipale(civicoStradario.getPrincipale());
				civico.setProvvisorio(civicoStradario.getProvvisorio());
				civico.setCentroStorico(civicoStradario.getCentroStorico());

				indirizzo.setCivico(civico);
			}
		}
		return indirizzo;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.pdds.service.AbstractResponse#getName()
	 */
	@Override
	public String getName() {
		return "stradarioService";
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
	 * @return the xmlHelperStradario
	 */
	public XMLHelper getXmlHelperStradario() {
		return xmlHelperStradario;
	}

	/**
	 * @param xmlHelperStradario the xmlHelperStradario to set
	 */
	public void setXmlHelperStradario(XMLHelper xmlHelperStradario) {
		this.xmlHelperStradario = xmlHelperStradario;
	}

	/**
	 * @return the webserviceRicercaCivicoUrl
	 */
	public String getWebserviceRicercaCivicoUrl() {
		return webserviceRicercaCivicoUrl;
	}

	/**
	 * @param webserviceRicercaCivicoUrl the webserviceRicercaCivicoUrl to set
	 */
	public void setWebserviceRicercaCivicoUrl(String webserviceRicercaCivicoUrl) {
		this.webserviceRicercaCivicoUrl = webserviceRicercaCivicoUrl;
	}

	/**
	 * @return the webserviceRicercaViaUrl
	 */
	public String getWebserviceRicercaViaUrl() {
		return webserviceRicercaViaUrl;
	}

	/**
	 * @param webserviceRicercaViaUrl the webserviceRicercaViaUrl to set
	 */
	public void setWebserviceRicercaViaUrl(String webserviceRicercaViaUrl) {
		this.webserviceRicercaViaUrl = webserviceRicercaViaUrl;
	}

}
