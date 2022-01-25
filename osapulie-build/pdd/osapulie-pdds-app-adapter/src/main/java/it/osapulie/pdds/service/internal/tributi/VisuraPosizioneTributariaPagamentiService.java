/************************************************************************************
 * Copyright (c) 2011, 2014 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pdds.service.internal.tributi;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;

import javax.xml.rpc.ServiceException;

//import org.apache.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.osapulie.pdds.adapter.util.XMLHelper;
import it.osapulie.pdds.common.PddService;
import it.osapulie.pdds.service.internal.CommonService;
import it.osapulie.pdds.ws.client.visuraimpostainsegnepubblicita.RataVisuraImposteInsegnePubblicita;
import it.osapulie.pdds.ws.client.visuraimpostainsegnepubblicita.VisuraImpostaInsegnePubblicitaServiceLocator;
import it.osapulie.pdds.ws.client.visuraimpostainsegnepubblicita.VisuraImpostaInsegnePubblicitaServicePortType;
import it.osapulie.pdds.ws.client.visuraimpostainsegnepubblicita.VisuraImposteInsegnePubblicita;
import it.osapulie.pdds.ws.client.visuraosappermanente.RataVisuraOsapPermanente;
import it.osapulie.pdds.ws.client.visuraosappermanente.VisuraOsapPermanente;
import it.osapulie.pdds.ws.client.visuraosappermanente.VisuraOsapPermanenteServiceLocator;
import it.osapulie.pdds.ws.client.visuraosappermanente.VisuraOsapPermanenteServicePortType;
import it.osapulie.pdds.ws.client.visuraosaptemporanea.VisuraOsapTemporanea;
import it.osapulie.pdds.ws.client.visuraosaptemporanea.VisuraOsapTemporaneaServiceLocator;
import it.osapulie.pdds.ws.client.visuraosaptemporanea.VisuraOsapTemporaneaServicePortType;
import it.osapulie.pdds.ws.client.visurapubblicheaffissioni.RataVisuraPubblicheAffissioni;
import it.osapulie.pdds.ws.client.visurapubblicheaffissioni.VisuraPubblicheAffissioni;
import it.osapulie.pdds.ws.client.visurapubblicheaffissioni.VisuraPubblicheAffissioniServiceLocator;
import it.osapulie.pdds.ws.client.visurapubblicheaffissioni.VisuraPubblicheAffissioniServicePortType;
import it.osapulie.pdds.ws.client.visuraservizicimiteriali.RataVisuraServiziCimiteriali;
import it.osapulie.pdds.ws.client.visuraservizicimiteriali.VisuraServiziCimiteriali;
import it.osapulie.pdds.ws.client.visuraservizicimiteriali.VisuraServiziCimiterialiServiceLocator;
import it.osapulie.pdds.ws.client.visuraservizicimiteriali.VisuraServiziCimiterialiServicePortType;
import it.osapulie.pdds.ws.client.visuratassaimmobili.RataVisuraTassaImmobili;
import it.osapulie.pdds.ws.client.visuratassaimmobili.VisuraTassaImmobili;
import it.osapulie.pdds.ws.client.visuratassaimmobili.VisuraTassaImmobiliServiceLocator;
import it.osapulie.pdds.ws.client.visuratassaimmobili.VisuraTassaImmobiliServicePortType;
import it.osapulie.pdds.ws.client.visuratassarifiuti.RataVisuraTassaRifiuti;
import it.osapulie.pdds.ws.client.visuratassarifiuti.VisuraTassaRifiuti;
import it.osapulie.pdds.ws.client.visuratassarifiuti.VisuraTassaRifiutiServiceLocator;
import it.osapulie.pdds.ws.client.visuratassarifiuti.VisuraTassaRifiutiServicePortType;
import it.osapulie.tributi.web.ws.output.types.DatiOsapPermanente;
import it.osapulie.tributi.web.ws.output.types.DatiOsapTemporanea;
import it.osapulie.tributi.web.ws.output.types.DatiTassaImmobili;
import it.osapulie.tributi.web.ws.output.types.DatiTassaRifiuti;
import it.osapulie.tributi.web.ws.output.types.PagamentiAffissioniType;
import it.osapulie.tributi.web.ws.output.types.PagamentiPubblicitaType;
import it.osapulie.tributi.web.ws.output.types.PagamentiServiziCimiterialiType;
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaPagamentoRichiesta;
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaRisposta;
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaPagamentoRichiesta.TipoTributo;
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaPagamentoRichiesta.TipoTributo.Tributo;
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaRisposta.Errore;

/**
 * @author Gianluca Pindinelli
 *
 */
public class VisuraPosizioneTributariaPagamentiService implements PddService {

	private final Logger log = LoggerFactory.getLogger(VisuraPosizioneTributariaPagamentiService.class);

	private XMLHelper xmlHelper;

	private CommonService commonService;

	public String webservicesBaseUrl;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.pdds.common.PddService#getResponse(java.lang.String)
	 */
	public String getResponse(String xml) {

		log.info("getResponse :: Richiesta su porta applicativa: " + xml);

		VisuraPosizioneTributariaPagamentoRichiesta richiesta = xmlHelper.unmarshal(xml, VisuraPosizioneTributariaPagamentoRichiesta.class);

		VisuraPosizioneTributariaRisposta risposta = new VisuraPosizioneTributariaRisposta();

		try {

			String codiceFiscale = richiesta.getCodiceFiscale();
			if (richiesta.getPartitaIva() != null) {
				codiceFiscale = richiesta.getPartitaIva();
			}

			String idCredito = richiesta.getIdCredito();

			TipoTributo tipoTributo = richiesta.getTipoTributo();
			if (tipoTributo != null) {
				if (tipoTributo.getALL() != null) {
					setVisurePubblicheAffissioni(risposta.getElencoPagamentiTassaAffissioni(), codiceFiscale, idCredito);
					setVisureServiziCimiteriali(risposta.getElencoPagamentiTassaCimiteriali(), codiceFiscale, idCredito);
					setVisureTassaImmobili(risposta.getElencoPagamentiTassaImmobili(), codiceFiscale, idCredito);
					setVisureTassaPubblicita(risposta.getElencoPagamentiTassaPubblicita(), codiceFiscale, idCredito);
					// Permanente
					setVisureOsapPermanente(risposta.getElencoPagamentiOsapPermananente(), codiceFiscale, idCredito);
					// Temporanea
					setVisureOsapTemporanea(risposta.getElencoPagamentiOsapTemporanea(), codiceFiscale, idCredito);
					setVisureTassaRifiuti(risposta.getElencoPagamentiTassaRifiuti(), codiceFiscale, idCredito);
				}
				else {
					Tributo tributo = tipoTributo.getTributo();
					if (tributo.getAFFISSIONI() != null) {
						setVisurePubblicheAffissioni(risposta.getElencoPagamentiTassaAffissioni(), codiceFiscale, idCredito);
					}
					else if (tributo.getCIMITERIALI() != null) {
						setVisureServiziCimiteriali(risposta.getElencoPagamentiTassaCimiteriali(), codiceFiscale, idCredito);
					}
					else if (tributo.getIMU() != null) {
						setVisureTassaImmobili(risposta.getElencoPagamentiTassaImmobili(), codiceFiscale, idCredito);
					}
					else if (tributo.getICP() != null) {
						setVisureTassaPubblicita(risposta.getElencoPagamentiTassaPubblicita(), codiceFiscale, idCredito);
					}
					else if (tributo.getOSAP() != null) {
						// Permanente
						setVisureOsapPermanente(risposta.getElencoPagamentiOsapPermananente(), codiceFiscale, idCredito);
						// Temporanea
						setVisureOsapTemporanea(risposta.getElencoPagamentiOsapTemporanea(), codiceFiscale, idCredito);
					}
					else if (tributo.getTARI() != null) {
						setVisureTassaRifiuti(risposta.getElencoPagamentiTassaRifiuti(), codiceFiscale, idCredito);
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

	/**
	 *
	 * @param elencoPagamentiTassaRifiuti
	 * @param codiceFiscale
	 * @param idCredito
	 * @throws MalformedURLException
	 * @throws ServiceException
	 * @throws RemoteException
	 */
	private void setVisureTassaRifiuti(List<DatiTassaRifiuti> elencoPagamentiTassaRifiuti, String codiceFiscale, String idCredito) throws MalformedURLException, ServiceException, RemoteException {

		URL wsdlLocation = new URL(webservicesBaseUrl + "/services/visuraTassaRifiutiService?wsdl");
		VisuraTassaRifiutiServiceLocator visuraTassaRifiutiServiceLocator = new VisuraTassaRifiutiServiceLocator();

		VisuraTassaRifiutiServicePortType secureSOAP11Endpoint = visuraTassaRifiutiServiceLocator.getSecureSOAP11Endpoint(wsdlLocation);

		RataVisuraTassaRifiuti[] rateVisuraTassaRifiuti = secureSOAP11Endpoint.getRataVisuraTassaRifiuti(idCredito);

		// Ricerca in base alle rate
		if (rateVisuraTassaRifiuti != null && rateVisuraTassaRifiuti.length > 0) {
			RataVisuraTassaRifiuti rata = rateVisuraTassaRifiuti[0];
			BigInteger idVisura = rata.getIdVisura();
			VisuraTassaRifiuti[] visuraTassaRifiuti = secureSOAP11Endpoint.getVisuraTassaRifiuti(idVisura.intValue());
			commonService.setVisureTassaRifiuti(elencoPagamentiTassaRifiuti, visuraTassaRifiuti, secureSOAP11Endpoint);
		}
	}

	/**
	 *
	 * @param elencoPagamentiOsapTemporanea
	 * @param codiceFiscale
	 * @param idCredito
	 * @throws MalformedURLException
	 * @throws ServiceException
	 * @throws RemoteException
	 */
	private void setVisureOsapTemporanea(List<DatiOsapTemporanea> elencoPagamentiOsapTemporanea, String codiceFiscale, String idCredito)
			throws MalformedURLException, ServiceException, RemoteException {

		URL wsdlLocation = new URL(webservicesBaseUrl + "/services/visuraOsapTemporaneaService?wsdl");
		VisuraOsapTemporaneaServiceLocator visuraOsapTemporaneaServiceLocator = new VisuraOsapTemporaneaServiceLocator();
		VisuraOsapTemporaneaServicePortType secureSOAP11Endpoint = visuraOsapTemporaneaServiceLocator.getSecureSOAP11Endpoint(wsdlLocation);

		VisuraOsapTemporanea[] visureOsapTemporanea = secureSOAP11Endpoint.getVisuraOsapTemporanea(idCredito);
		commonService.setVisureOsapTemporanea(elencoPagamentiOsapTemporanea, visureOsapTemporanea, secureSOAP11Endpoint);

	}

	/**
	 *
	 * @param elencoPagamentiOsapPermananente
	 * @param codiceFiscale
	 * @param idCredito
	 * @throws MalformedURLException
	 * @throws ServiceException
	 * @throws RemoteException
	 */
	private void setVisureOsapPermanente(List<DatiOsapPermanente> elencoPagamentiOsapPermananente, String codiceFiscale, String idCredito)
			throws MalformedURLException, ServiceException, RemoteException {

		URL wsdlLocation = new URL(webservicesBaseUrl + "/services/visuraOsapPermanenteService?wsdl");

		VisuraOsapPermanenteServiceLocator visuraOsapPermanenteServiceLocator = new VisuraOsapPermanenteServiceLocator();
		VisuraOsapPermanenteServicePortType secureSOAP11Endpoint = visuraOsapPermanenteServiceLocator.getSecureSOAP11Endpoint(wsdlLocation);

		RataVisuraOsapPermanente[] rateOsapPermanente = secureSOAP11Endpoint.getRataVisuraOsapPermanente(idCredito);
		if (rateOsapPermanente != null && rateOsapPermanente.length > 0) {
			RataVisuraOsapPermanente rata = rateOsapPermanente[0];
			BigInteger idVisura = rata.getIdVisura();
			VisuraOsapPermanente[] visureOsapPermanente = secureSOAP11Endpoint.getVisuraOsapPermanente(idVisura.intValue());
			commonService.setVisureOsapPermanente(elencoPagamentiOsapPermananente, visureOsapPermanente, secureSOAP11Endpoint);
		}
	}

	/**
	 *
	 * @param elencoPagamentiTassaPubblicita
	 * @param codiceFiscale
	 * @param idCredito
	 * @throws MalformedURLException
	 * @throws ServiceException
	 * @throws RemoteException
	 */
	private void setVisureTassaPubblicita(List<PagamentiPubblicitaType> elencoPagamentiTassaPubblicita, String codiceFiscale, String idCredito)
			throws MalformedURLException, ServiceException, RemoteException {

		URL wsdlLocation = new URL(webservicesBaseUrl + "/services/visuraImpostaInsegnePubblicitaService?wsdl");
		VisuraImpostaInsegnePubblicitaServiceLocator visuraImpostaInsegnePubblicitaLocator = new VisuraImpostaInsegnePubblicitaServiceLocator();
		VisuraImpostaInsegnePubblicitaServicePortType secureSOAP11Endpoint = visuraImpostaInsegnePubblicitaLocator.getSecureSOAP11Endpoint(wsdlLocation);

		RataVisuraImposteInsegnePubblicita[] rateVisuraImposteInsegnePubblicita = secureSOAP11Endpoint.getRataVisuraImposteInsegnePubblicita(idCredito);

		if (rateVisuraImposteInsegnePubblicita != null && rateVisuraImposteInsegnePubblicita.length > 0) {
			RataVisuraImposteInsegnePubblicita rata = rateVisuraImposteInsegnePubblicita[0];
			BigInteger idVisura = rata.getIdVisura();
			VisuraImposteInsegnePubblicita[] visureImposteInsegnePubblicita = secureSOAP11Endpoint.getVisuraImposteInsegnePubblicita(idVisura.intValue());
			commonService.setVisureTassaPubblicita(elencoPagamentiTassaPubblicita, visureImposteInsegnePubblicita, secureSOAP11Endpoint);
		}

	}

	/**
	 *
	 * @param elencoPagamentiTassaImmobili
	 * @param codiceFiscale
	 * @param idCredito
	 * @throws MalformedURLException
	 * @throws ServiceException
	 * @throws RemoteException
	 */
	private void setVisureTassaImmobili(List<DatiTassaImmobili> elencoPagamentiTassaImmobili, String codiceFiscale, String idCredito) throws MalformedURLException, ServiceException, RemoteException {

		URL wsdlLocation = new URL(webservicesBaseUrl + "/services/visuraTassaImmobiliService?wsdl");

		VisuraTassaImmobiliServiceLocator visuraDichiarazioneTassaImmobiliServiceLocator = new VisuraTassaImmobiliServiceLocator();
		VisuraTassaImmobiliServicePortType secureSOAP11Endpoint = visuraDichiarazioneTassaImmobiliServiceLocator.getSecureSOAP11Endpoint(wsdlLocation);
		RataVisuraTassaImmobili[] rateVisuraTassaImmobili = secureSOAP11Endpoint.getRataVisuraTassaImmobili(idCredito);
		if (rateVisuraTassaImmobili != null && rateVisuraTassaImmobili.length > 0) {
			RataVisuraTassaImmobili rataVisuraTassaImmobili = rateVisuraTassaImmobili[0];
			BigInteger idVisura = rataVisuraTassaImmobili.getIdVisura();
			VisuraTassaImmobili[] visuraTassaImmobili = secureSOAP11Endpoint.getVisuraTassaImmobili(idVisura.intValue());
			commonService.setVisureTassaImmobili(elencoPagamentiTassaImmobili, visuraTassaImmobili, secureSOAP11Endpoint);
		}

	}

	/**
	 *
	 * @param elencoPagamentiTassaCimiteriali
	 * @param codiceFiscale
	 * @param idCredito
	 * @throws MalformedURLException
	 * @throws ServiceException
	 * @throws RemoteException
	 */
	private void setVisureServiziCimiteriali(List<PagamentiServiziCimiterialiType> elencoPagamentiTassaCimiteriali, String codiceFiscale, String idCredito)
			throws MalformedURLException, ServiceException, RemoteException {

		URL wsdlLocation = new URL(webservicesBaseUrl + "/services/visuraServiziCimiterialiService?wsdl");
		VisuraServiziCimiterialiServiceLocator visuraServiziCimiterialiServiceLocator = new VisuraServiziCimiterialiServiceLocator();
		VisuraServiziCimiterialiServicePortType secureSOAP11Endpoint = visuraServiziCimiterialiServiceLocator.getSecureSOAP11Endpoint(wsdlLocation);

		RataVisuraServiziCimiteriali[] rataVisuraServiziCimiteriali = secureSOAP11Endpoint.getRataVisuraServiziCimiteriali(idCredito);
		if (rataVisuraServiziCimiteriali != null && rataVisuraServiziCimiteriali.length > 0) {
			RataVisuraServiziCimiteriali rataVisuraServiziCimiteriali2 = rataVisuraServiziCimiteriali[0];
			VisuraServiziCimiteriali[] visuraServiziCimiteriali = secureSOAP11Endpoint.getVisuraServiziCimiteriali(rataVisuraServiziCimiteriali2.getIdVisura().intValue());
			commonService.setVisureServiziCimiteriali(elencoPagamentiTassaCimiteriali, visuraServiziCimiteriali, secureSOAP11Endpoint);

		}

	}

	/**
	 *
	 * @param elencoPagamentiTassaAffissioni
	 * @param codiceFiscale
	 * @param idCredito
	 * @throws MalformedURLException
	 * @throws ServiceException
	 * @throws RemoteException
	 */
	private void setVisurePubblicheAffissioni(List<PagamentiAffissioniType> elencoPagamentiTassaAffissioni, String codiceFiscale, String idCredito)
			throws MalformedURLException, ServiceException, RemoteException {

		URL wsdlLocation = new URL(webservicesBaseUrl + "/services/visuraPubblicheAffissioniService?wsdl");
		VisuraPubblicheAffissioniServiceLocator visuraPubblicheAffissioniServiceLocator = new VisuraPubblicheAffissioniServiceLocator();
		VisuraPubblicheAffissioniServicePortType secureSOAP11Endpoint = visuraPubblicheAffissioniServiceLocator.getSecureSOAP11Endpoint(wsdlLocation);

		RataVisuraPubblicheAffissioni[] rataVisuraPubblicheAffissioni = secureSOAP11Endpoint.getRataVisuraPubblicheAffissioni(idCredito);
		if (rataVisuraPubblicheAffissioni != null && rataVisuraPubblicheAffissioni.length > 0) {
			RataVisuraPubblicheAffissioni rataVisuraPubblicheAffissioni2 = rataVisuraPubblicheAffissioni[0];

			VisuraPubblicheAffissioni[] visuraPubblicheAffissioni = secureSOAP11Endpoint.getVisuraPubblicheAffissioni(rataVisuraPubblicheAffissioni2.getIdVisura().intValue());
			commonService.setVisurePubblicheAffissioni(elencoPagamentiTassaAffissioni, visuraPubblicheAffissioni, secureSOAP11Endpoint);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.pdds.common.PddService#getName()
	 */
	public String getName() {
		return "visuraPosizioneTributariaPagamentoRichiesta";
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
	 * @return the commonService
	 */
	public CommonService getCommonService() {
		return commonService;
	}

	/**
	 * @param commonService the commonService to set
	 */
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
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
