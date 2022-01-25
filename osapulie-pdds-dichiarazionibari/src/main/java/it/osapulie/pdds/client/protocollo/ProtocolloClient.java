/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pdds.client.protocollo;

import java.net.URL;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import it.linksmt.protocollo.ws.client.protocollo.Allegato;
import it.linksmt.protocollo.ws.client.protocollo.Errore;
import it.linksmt.protocollo.ws.client.protocollo.Mittente;
import it.linksmt.protocollo.ws.client.protocollo.ProtocolloServer;
import it.linksmt.protocollo.ws.client.protocollo.ProtocolloServerImplService;
import it.linksmt.protocollo.ws.client.protocollo.RichiestaProtocollo.ProtocolloRequest;
import it.linksmt.protocollo.ws.client.protocollo.RichiestaProtocolloResponse.Return;

/**
 * @author Gianluca Pindinelli
 *
 */
public class ProtocolloClient {

	private static final String WSDL_PROTOCOLLO = "wsdl/protocollo/protocollo.wsdl";
	private String webserviceUrl;
	private Integer idUtenteProtocollatore;
	private String username;
	private String password;

	private final Logger log = LoggerFactory.getLogger(ProtocolloClient.class);

	public ProtocolloRisposta protocolla(ProtocolloRichiesta protocolloRichiesta) throws ProtocolloClientException {

		ProtocolloRisposta protocolloRisposta;
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			URL wsdlLocation = classloader.getResource(WSDL_PROTOCOLLO);

			ProtocolloServerImplService protocolloServerImplService = new ProtocolloServerImplService(wsdlLocation,
					new QName("http://impl.server.ws.protocollo.linksmt.it/", "ProtocolloServerImplService"));

			ProtocolloServer protocolloServerImplPort = protocolloServerImplService.getProtocolloServerImplPort();
			BindingProvider provider = (BindingProvider) protocolloServerImplPort;
			Map<String, Object> requestContext = provider.getRequestContext();
			requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, webserviceUrl);
			Map<String, List<String>> headers = new HashMap<String, List<String>>();
			headers.put("Username", Collections.singletonList(username));
			headers.put("Password", Collections.singletonList(password));
			requestContext.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

			log.info("Protocollazione '" + protocolloRichiesta.getOggetto() + "' in corso...");

			ProtocolloRequest protocolloRequest = new ProtocolloRequest();
			protocolloRequest.setAmministrazione(protocolloRichiesta.getAmministrazione());
			protocolloRequest.setAreaOrganizzativaOmogenea(protocolloRichiesta.getAoo());
			protocolloRequest.setIdUtente(idUtenteProtocollatore);
			protocolloRequest.setOggetto(protocolloRichiesta.getOggetto());

			Mittente mittente = new Mittente();
			protocolloRequest.setMittente(mittente);

			List<Allegato> allegati = protocolloRequest.getAllegati();

			List<Documento> documenti = protocolloRichiesta.getDocumenti();
			if (documenti != null) {
				for (Documento documento : documenti) {
					if (documento.isPrincipale()) {
						it.linksmt.protocollo.ws.client.protocollo.Documento documentoClient = new it.linksmt.protocollo.ws.client.protocollo.Documento();
						documentoClient.setContenuto(documento.getContenuto());
						documentoClient.setNomeFile(documento.getNome());
						documentoClient.setTitolo(documento.getTitolo());
						documentoClient.setClassifica(documento.getClassificazione());
						protocolloRequest.setDocumento(documentoClient);
					}
					else {
						Allegato allegato = new Allegato();
						it.linksmt.protocollo.ws.client.protocollo.Documento documentoClient = new it.linksmt.protocollo.ws.client.protocollo.Documento();
						documentoClient.setContenuto(documento.getContenuto());
						documentoClient.setNomeFile(documento.getNome());
						documentoClient.setTitolo(documento.getTitolo());
						documentoClient.setClassifica(documento.getClassificazione());
						allegato.setDocumento(documentoClient);
						allegati.add(allegato);
					}
				}
			}

			Return rispostaProtocollo = protocolloServerImplPort.richiestaProtocollo(protocolloRequest);

			if (rispostaProtocollo == null) {
				throw new ProtocolloClientException("Errore durante la protocollazione : risposta nulla.");
			}

			Errore errore = rispostaProtocollo.getErrore();
			if (errore != null) {
				throw new ProtocolloClientException("Errore durante la protocollazione : codice di errore: " + errore.getCodice() + ", descrizione: " + errore.getDescrizione());
			}

			Long numeroProtocollo = rispostaProtocollo.getNumeroProtocollo();
			XMLGregorianCalendar dataProtocollo = rispostaProtocollo.getDataProtocollo();
			protocolloRisposta = new ProtocolloRisposta();
			protocolloRisposta.setDataProtocollo(dataProtocollo.toGregorianCalendar().getTime());
			protocolloRisposta.setNumeroProtocollo(numeroProtocollo);

			log.info("Protocollazione '" + protocolloRichiesta.getOggetto() + "' completata: numero protocollo '" + numeroProtocollo + "'");
		}
		catch (Exception e) {
			throw new ProtocolloClientException("Errore in fase di protocollazione dei documenti nel BackOffice comunale", e);
		}

		return protocolloRisposta;
	}

	/**
	 * Ritorna il numero protocollo nel fomratp "YYYYN" (con padding a 7 cifre per il numero).
	 *
	 * @param protocolloRisposta
	 * @return
	 */
	public String getNumeroProtocollo(ProtocolloRisposta protocolloRisposta) {

		String numeroProtocollo = String.format("%07d", protocolloRisposta.getNumeroProtocollo());
		Date dataProtocollo = protocolloRisposta.getDataProtocollo();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(dataProtocollo);

		int annoProtocollo = calendar.get(Calendar.YEAR);

		numeroProtocollo = annoProtocollo + numeroProtocollo;

		return numeroProtocollo;
	}

	public void annulla(String numeroProtocollo) {

		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		URL wsdlLocation = classloader.getResource(WSDL_PROTOCOLLO);

		ProtocolloServerImplService protocolloServerImplService = new ProtocolloServerImplService(wsdlLocation,
				new QName("http://impl.server.ws.protocollo.linksmt.it/", "ProtocolloServerImplService"));

		ProtocolloServer protocolloServerImplPort = protocolloServerImplService.getProtocolloServerImplPort();
		BindingProvider provider = (BindingProvider) protocolloServerImplPort;
		provider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, webserviceUrl);

		// TODO implementare

	}

	/**
	 * @return the webserviceUrl
	 */
	public String getWebserviceUrl() {
		return webserviceUrl;
	}

	/**
	 * @param webserviceUrl the webserviceUrl to set
	 */
	public void setWebserviceUrl(String webserviceUrl) {
		this.webserviceUrl = webserviceUrl;
	}

	/**
	 * @return the idUtenteProtocollatore
	 */
	public Integer getIdUtenteProtocollatore() {
		return idUtenteProtocollatore;
	}

	/**
	 * @param idUtenteProtocollatore the idUtenteProtocollatore to set
	 */
	public void setIdUtenteProtocollatore(Integer idUtenteProtocollatore) {
		this.idUtenteProtocollatore = idUtenteProtocollatore;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
