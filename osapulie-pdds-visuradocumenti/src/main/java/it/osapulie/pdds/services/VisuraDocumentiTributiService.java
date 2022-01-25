/************************************************************************************
 * Copyright (c) 2011, 2016 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pdds.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.xml.rpc.ServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import it.osapulie.documenti.web.ws.output.types.Documento;
import it.osapulie.documenti.web.ws.output.types.VisuraDocumentiRichiesta;
import it.osapulie.documenti.web.ws.output.types.VisuraDocumentiRisposta;
import it.osapulie.documenti.web.ws.output.types.VisuraDocumentiRisposta.Errore;
import it.osapulie.pdds.adapter.util.XMLHelper;
import it.osapulie.pdds.common.PddService;
import it.osapulie.pdds.ws.client.documenti.tributi.avvisibonari.VisuraDocumentiAvvisiBonariTributiServiceLocator;
import it.osapulie.pdds.ws.client.documenti.tributi.avvisibonari.VisuraDocumentiAvvisiBonariTributiServicePortType;

/**
 * Implementazione interna del servizio di visuraDocumentiTributi.
 *
 * @author Gianluca Pindinelli
 *
 */
public class VisuraDocumentiTributiService implements PddService {

	private final Logger log = LoggerFactory.getLogger(VisuraDocumentiTributiService.class);

	private XMLHelper xmlHelper;

	private String documentiFolder;
	private String documentiAvvisibonariParentFolder;
	private String webservicesBaseUrl;

	@Override
	public String getResponse(String xml) {

		VisuraDocumentiRichiesta richiesta = xmlHelper.unmarshal(xml, VisuraDocumentiRichiesta.class);

		String result = null;
		try {
			VisuraDocumentiRisposta risp = risposta(richiesta);
			result = xmlHelper.marshal(risp);
		}
		catch (Exception e) {
			log.error("getResponse :: " + e.getMessage(), e);
		}

		return result;

	}

	private VisuraDocumentiRisposta risposta(VisuraDocumentiRichiesta richiesta) {

		VisuraDocumentiRisposta risposta = new VisuraDocumentiRisposta();
		List<Documento> documenti = risposta.getDocumento();

		final String codiceServizio = richiesta.getCodiceServizio();
		final String codiceFiscale = richiesta.getCodiceFiscale();
		final String idDocumento = richiesta.getIdDocumento();

		// VISURA AVVISI BONARI TARI
		if (codiceServizio.equals("TV28")) {
			try {
				URL wsdlLocation = new URL(webservicesBaseUrl + "/services/visuraDocumentiAvvisiBonariTributiService?wsdl");
				VisuraDocumentiAvvisiBonariTributiServiceLocator visuraDocumentiAvvisiBonariTributiServiceLocator = new VisuraDocumentiAvvisiBonariTributiServiceLocator();
				VisuraDocumentiAvvisiBonariTributiServicePortType secureSOAP11Endpoint = visuraDocumentiAvvisiBonariTributiServiceLocator.getSecureSOAP11Endpoint(wsdlLocation);
				// Ritorna la lista di documenti
				if (idDocumento == null) {
					it.osapulie.pdds.ws.client.documenti.tributi.avvisibonari.Documento[] documentiRemote = secureSOAP11Endpoint.getDocumenti(codiceFiscale, richiesta.getAnno());
					if (documentiRemote == null || documentiRemote.length == 0) {
						Errore errore = new Errore();
						errore.setCodice(3);
						errore.setDescrizione("Nessun documento trovato.");
						risposta.setErrore(errore);
					}
					else {
						for (it.osapulie.pdds.ws.client.documenti.tributi.avvisibonari.Documento documentoRemote : documentiRemote) {
							String idDocumentoRemote = documentoRemote.getMatricola().toString() + "-" + documentoRemote.getAnno() + "-" + documentoRemote.getTipo().toString();
							Documento documento = new Documento();
							documento.setNome(documentoRemote.getFile().substring(documentoRemote.getFile().lastIndexOf("\\") + 1));
							documento.setDescrizione("Anno riferimento: " + documentoRemote.getAnnoRiferimento() + ", n° matricola: " + documentoRemote.getMatricola());
							documento.setContentType("application/pdf");
							documento.setId(idDocumentoRemote);
							documenti.add(documento);
						}
					}
				}
				// Ritorna il singolo documento
				else {
					int matricola = 0;
					int anno = 0;
					int tipo = 0;

					try {
						String[] split = idDocumento.split("-");
						matricola = Integer.parseInt(split[0]);
						anno = Integer.parseInt(split[1]);
						tipo = Integer.parseInt(split[2]);
					}
					catch (NumberFormatException e) {
						log.error("risposta :: " + e.getMessage(), e);
						Errore errore = new Errore();
						errore.setCodice(3);
						errore.setDescrizione("Documento con ID '" + idDocumento + "' non trovato :: errore : " + e.getMessage());
						risposta.setErrore(errore);
					}

					it.osapulie.pdds.ws.client.documenti.tributi.avvisibonari.Documento[] documentiRemote = secureSOAP11Endpoint.getDocumento(matricola, anno, tipo);
					if (documentiRemote == null || documentiRemote.length == 0) {
						Errore errore = new Errore();
						errore.setCodice(3);
						errore.setDescrizione("Documento con ID '" + idDocumento + "' non trovato.");
						risposta.setErrore(errore);
					}
					else {
						it.osapulie.pdds.ws.client.documenti.tributi.avvisibonari.Documento documentoRemote = documentiRemote[0];
						String idDocumentoRemote = documentoRemote.getMatricola().toString() + "-" + documentoRemote.getAnno() + "-" + documentoRemote.getTipo().toString();
						Documento documento = new Documento();
						String filePath = documentoRemote.getFile();
						documento.setId(idDocumentoRemote);

						// binario da filesystem
						filePath = filePath.replace("\\\\", "/");
						filePath = filePath.replace("\\", "/");
						filePath = filePath.substring(filePath.lastIndexOf("$") + 1);
						filePath = documentiAvvisibonariParentFolder + filePath;
						File file = new File(filePath);
						if (file.exists()) {
							documento.setContentType("application/pdf");
							documento.setBinario(readContentIntoByteArray(file));
							documento.setNome(file.getName());
							risposta.getDocumento().add(documento);
						}
						else {
							Errore errore = new Errore();
							errore.setCodice(3);
							errore.setDescrizione("Documento con ID '" + idDocumento + "' non trovato nella posizione : " + filePath);
							risposta.setErrore(errore);
						}
					}
				}
			}
			catch (MalformedURLException e) {
				log.error("risposta :: " + e.getMessage(), e);
			}
			catch (ServiceException e) {
				log.error("risposta :: " + e.getMessage(), e);
			}
			catch (RemoteException e) {
				log.error("risposta :: " + e.getMessage(), e);
			}
		}
		else {
			// Ritorna la lista di documenti
			String pathname = documentiFolder + File.separator + codiceServizio;
			if (idDocumento == null) {
				File folder = new File(pathname);
				if (folder.exists()) {
					File[] listOfFiles = folder.listFiles(new FilenameFilter() {

						@Override
						public boolean accept(File dir, String name) {
							return name.contains(codiceFiscale);
						}
					});
					if (listOfFiles != null) {
						for (File file : listOfFiles) {
							Documento documento = new Documento();
							documento.setNome(file.getName());
							documento.setContentType(new MimetypesFileTypeMap().getContentType(file));
							documento.setId(file.getName());
							documenti.add(documento);
						}
					}
				}
				else {
					Errore errore = new Errore();
					errore.setCodice(3);
					errore.setDescrizione("Folder '" + pathname + "' non trovata.");
					risposta.setErrore(errore);
				}
			}
			// Ritorna il singolo documento
			else {
				File folder = new File(pathname);
				if (folder.exists()) {
					final String nomeDocumento = idDocumento;
					File[] listOfFiles = folder.listFiles(new FilenameFilter() {

						@Override
						public boolean accept(File dir, String name) {
							return name.equals(nomeDocumento);
						}
					});

					if (listOfFiles != null && listOfFiles.length > 0) {
						File file = listOfFiles[0];
						Documento documento = new Documento();
						documento.setNome(file.getName());
						documento.setContentType(new MimetypesFileTypeMap().getContentType(file));
						documento.setBinario(readContentIntoByteArray(file));
						risposta.getDocumento().add(documento);
					}
					// File non trovato
					else {
						Errore errore = new Errore();
						errore.setCodice(3);
						errore.setDescrizione("Documento '" + pathname + File.separator + nomeDocumento + "' non trovato.");
						risposta.setErrore(errore);
					}
				}
			}
		}

		return risposta;
	}

	private byte[] readContentIntoByteArray(File file) {
		FileInputStream fileInputStream = null;
		byte[] bFile = new byte[(int) file.length()];
		try {
			// convert file into array of bytes
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(bFile);
			fileInputStream.close();
		}
		catch (Exception e) {
			log.error(e.getMessage());
		}
		return bFile;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.pdds.service.AbstractResponse#getName()
	 */
	@Override
	public String getName() {
		return "visuraDocumentiTributi";
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
	 * @return the documentiFolder
	 */
	public String getDocumentiFolder() {
		return documentiFolder;
	}

	/**
	 * @param documentiFolder the documentiFolder to set
	 */
	public void setDocumentiFolder(String documentiFolder) {
		this.documentiFolder = documentiFolder;
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
	 * @return the documentiAvvisibonariParentFolder
	 */
	public String getDocumentiAvvisibonariParentFolder() {
		return documentiAvvisibonariParentFolder;
	}

	/**
	 * @param documentiAvvisibonariParentFolder the documentiAvvisibonariParentFolder to set
	 */
	public void setDocumentiAvvisibonariParentFolder(String documentiAvvisibonariParentFolder) {
		this.documentiAvvisibonariParentFolder = documentiAvvisibonariParentFolder;
	}
}
