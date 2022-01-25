/************************************************************************************
 * Copyright (c) 2011, 2014 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pdds.service.internal.protocollo;

import java.net.MalformedURLException;
import java.net.URL;

//import org.apache.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.linksmt.protocollo.ws.client.icar.ricercaDoc.AP3RicercaRequest;
import it.linksmt.protocollo.ws.client.icar.ricercaDoc.AP3RicercaResponse;
import it.linksmt.protocollo.ws.client.icar.ricercaDoc.Classifica;
import it.linksmt.protocollo.ws.client.icar.ricercaDoc.Errore;
import it.linksmt.protocollo.ws.client.icar.ricercaDoc.EstremiFascicolo;
import it.linksmt.protocollo.ws.client.icar.ricercaDoc.EstremiFascicoloVirtuale;
import it.linksmt.protocollo.ws.client.icar.ricercaDoc.EstremiProtocolloEstesi;
import it.linksmt.protocollo.ws.client.icar.ricercaDoc.ListaDocumenti;
import it.linksmt.protocollo.ws.client.icar.ricercaDoc.ListaDocumentiInformatici;
import it.linksmt.protocollo.ws.client.icar.ricercaDoc.ListaLivelli;
import it.linksmt.protocollo.ws.client.icar.ricercaDoc.ListaResponsabiliTrattamento;
import it.linksmt.protocollo.ws.client.icar.ricercaDoc.RichiestaRichiestaRispostaSincronaRicercaType;
import it.linksmt.protocollo.ws.client.icar.ricercaDoc.RispostaRichiestaRispostaSincronaRicercaType;
import it.osapulie.pdds.adapter.util.XMLHelper;
import it.osapulie.pdds.common.PddService;
import it.osapulie.pdds.ws.client.protocollo.ricercaDoc.CodiceArea;
import it.osapulie.pdds.ws.client.protocollo.ricercaDoc.Documento;
import it.osapulie.pdds.ws.client.protocollo.ricercaDoc.DocumentoInformatico;
import it.osapulie.pdds.ws.client.protocollo.ricercaDoc.EstremiProtocollo;
import it.osapulie.pdds.ws.client.protocollo.ricercaDoc.ResponsabileTrattamento;
import it.osapulie.pdds.ws.client.protocollo.ricercaDoc.RicercaWsServerImplServiceLocator;
import it.osapulie.pdds.ws.client.protocollo.ricercaDoc.Richiesta_RichiestaRispostaSincrona_Ricerca_Type;
import it.osapulie.pdds.ws.client.protocollo.ricercaDoc.Risposta_RichiestaRispostaSincrona_Ricerca_Type;

/**
 * @author Gianluca Pindinelli
 *
 */
public class RicercaService implements PddService {

	private final Logger log = LoggerFactory.getLogger(RicercaService.class);

	private XMLHelper xmlHelper;

	private String webservicesBaseUrl;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.pdds.adapter.PddService#getName()
	 */
	@Override
	public String getName() {
		return "ricercaDoc";
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.pdds.adapter.PddService#getResponse(java.lang.String)
	 */
	@Override
	public String getResponse(String arg0) {

		RichiestaRichiestaRispostaSincronaRicercaType request = xmlHelper.unmarshal(arg0, RichiestaRichiestaRispostaSincronaRicercaType.class);

		AP3RicercaRequest ap3RicercaRequest = request.getAP3RicercaRequest();

		RispostaRichiestaRispostaSincronaRicercaType risposta = new RispostaRichiestaRispostaSincronaRicercaType();

		AP3RicercaResponse ap3RicercaResponse = new AP3RicercaResponse();

		URL wsdlLocation = null;
		try {
			wsdlLocation = new URL(webservicesBaseUrl + "/adoc/ricerca_doc?wsdl");
		}
		catch (MalformedURLException e) {
			log.error("getResponse :: " + e.getMessage(), e);
		}
		String response = null;
		try {
			RicercaWsServerImplServiceLocator ricercaWsServerImplServiceLocator = new RicercaWsServerImplServiceLocator();

			Richiesta_RichiestaRispostaSincrona_Ricerca_Type parameters = new Richiesta_RichiestaRispostaSincrona_Ricerca_Type();
			it.osapulie.pdds.ws.client.protocollo.ricercaDoc.AP3RicercaRequest AP3RicercaRequest = new it.osapulie.pdds.ws.client.protocollo.ricercaDoc.AP3RicercaRequest();

			CodiceArea codiceInterrogato = new CodiceArea(ap3RicercaRequest.getCodiceInterrogato().getCodiceAmm(), ap3RicercaRequest.getCodiceInterrogato().getCodiceAoo());
			AP3RicercaRequest.setCodiceInterrogato(codiceInterrogato);

			CodiceArea codiceRichiedente = new CodiceArea(ap3RicercaRequest.getCodiceRichiedente().getCodiceAmm(), ap3RicercaRequest.getCodiceRichiedente().getCodiceAoo());
			AP3RicercaRequest.setCodiceRichiedente(codiceRichiedente);

			if (ap3RicercaRequest.getDataDa() != null) {
				AP3RicercaRequest.setDataDa(ap3RicercaRequest.getDataDa().toGregorianCalendar().getTime());
			}
			if (ap3RicercaRequest.getDataA() != null) {
				AP3RicercaRequest.setDataA(ap3RicercaRequest.getDataA().toGregorianCalendar().getTime());
			}

			EstremiProtocollo estremiProtocolloDest = new EstremiProtocollo(ap3RicercaRequest.getEstremiProtocolloDest().getAnno(), ap3RicercaRequest.getEstremiProtocolloDest().getNumero());
			AP3RicercaRequest.setEstremiProtocolloDest(estremiProtocolloDest);

			EstremiProtocollo estremiProtocolloMitt = new EstremiProtocollo(ap3RicercaRequest.getEstremiProtocolloMitt().getAnno(), ap3RicercaRequest.getEstremiProtocolloMitt().getNumero());
			AP3RicercaRequest.setEstremiProtocolloMitt(estremiProtocolloMitt);

			parameters.setAP3RicercaRequest(AP3RicercaRequest);

			Risposta_RichiestaRispostaSincrona_Ricerca_Type richiestaRispostaSincrona_Ricerca = ricercaWsServerImplServiceLocator.getRicercaWsServerImplPort(wsdlLocation)
					.richiestaRispostaSincrona_Ricerca(parameters);
			it.osapulie.pdds.ws.client.protocollo.ricercaDoc.AP3RicercaResponse ap3RicercaResponseFromClient = richiestaRispostaSincrona_Ricerca.getAP3RicercaResponse();

			// Errore
			if (ap3RicercaResponseFromClient.getErrore() != null) {
				Errore errore = new Errore();
				errore.setCodiceErrore(ap3RicercaResponseFromClient.getErrore().getCodiceErrore());
				errore.setMsgErrore(ap3RicercaResponseFromClient.getErrore().getMsgErrore());
				ap3RicercaResponse.setErrore(errore);
			}

			// Documenti
			if (ap3RicercaResponseFromClient.getDocumenti() != null) {
				ListaDocumenti documenti = new ListaDocumenti();
				if (ap3RicercaResponseFromClient.getDocumenti().getDocumento() != null) {
					for (Documento documentoFromCLient : ap3RicercaResponseFromClient.getDocumenti().getDocumento()) {
						it.linksmt.protocollo.ws.client.icar.ricercaDoc.Documento documento = new it.linksmt.protocollo.ws.client.icar.ricercaDoc.Documento();

						// Classifica
						if (documentoFromCLient.getClassifica() != null) {
							Classifica classifica = new Classifica();

							classifica.setCodiceClassifica(documentoFromCLient.getClassifica().getCodiceClassifica());
							classifica.setDescrizioneClassifica(documentoFromCLient.getClassifica().getDescrizioneClassifica());

							if (documentoFromCLient.getClassifica().getLivelli() != null) {
								ListaLivelli livelli = new ListaLivelli();
								if (documentoFromCLient.getClassifica().getLivelli().getLivelloClassifica() != null) {
									for (String livelloClassificaFromClient : documentoFromCLient.getClassifica().getLivelli().getLivelloClassifica()) {
										livelli.getLivelloClassifica().add(livelloClassificaFromClient);
									}
								}
								classifica.setLivelli(livelli);
							}
							documento.setClassifica(classifica);
						}
						documento.setDataRegistrazione(documentoFromCLient.getDataRegistrazione());

						// Documenti informatici
						if (documentoFromCLient.getDocumentiInformatici() != null) {
							ListaDocumentiInformatici documentiInformatici = new ListaDocumentiInformatici();
							if (documentoFromCLient.getDocumentiInformatici().getDocumentoInformatico() != null) {
								for (DocumentoInformatico documentoInformaticoFromClient : documentoFromCLient.getDocumentiInformatici().getDocumentoInformatico()) {
									it.linksmt.protocollo.ws.client.icar.ricercaDoc.DocumentoInformatico documentoInformatico = new it.linksmt.protocollo.ws.client.icar.ricercaDoc.DocumentoInformatico();
									documentoInformatico.setCodiceDocumentoInformatico(documentoInformaticoFromClient.getCodiceDocumentoInformatico());
									documentoInformatico.setDescrizioneDocumentoInformatico(documentoInformaticoFromClient.getDescrizioneDocumentoInformatico());
									documentoInformatico.setFlPrimario(documentoInformaticoFromClient.getFlPrimario());
									documentiInformatici.getDocumentoInformatico().add(documentoInformatico);
								}
							}

							documento.setDocumentiInformatici(documentiInformatici);
						}

						// Estremi fascicolo
						it.osapulie.pdds.ws.client.protocollo.ricercaDoc.EstremiFascicolo estremiFascicoloFromClient = documentoFromCLient.getEstremiFascicolo();
						if (estremiFascicoloFromClient != null) {
							EstremiFascicolo estremiFascicoloResponse = new EstremiFascicolo();
							estremiFascicoloResponse.setAnno(estremiFascicoloFromClient.getAnno());

							if (estremiFascicoloFromClient.getClassifica() != null) {
								Classifica classifica = new Classifica();
								classifica.setCodiceClassifica(estremiFascicoloFromClient.getClassifica().getCodiceClassifica());
								classifica.setDescrizioneClassifica(estremiFascicoloFromClient.getClassifica().getDescrizioneClassifica());
								if (estremiFascicoloFromClient.getClassifica().getLivelli() != null) {
									ListaLivelli livelli = new ListaLivelli();
									if (estremiFascicoloFromClient.getClassifica().getLivelli().getLivelloClassifica() != null) {
										for (String livelloFromClient : estremiFascicoloFromClient.getClassifica().getLivelli().getLivelloClassifica()) {
											livelli.getLivelloClassifica().add(livelloFromClient);
										}
									}
									classifica.setLivelli(livelli);
								}
								estremiFascicoloResponse.setClassifica(classifica);
							}
							estremiFascicoloResponse.setCodiceUfficio(estremiFascicoloFromClient.getCodiceUfficio());
							estremiFascicoloResponse.setDescrizioneUfficio(estremiFascicoloFromClient.getDescrizioneUfficio());
							estremiFascicoloResponse.setNumero(estremiFascicoloFromClient.getNumero());
							estremiFascicoloResponse.setSubnumero(estremiFascicoloFromClient.getSubnumero());

							documento.setEstremiFascicolo(estremiFascicoloResponse);
						}

						// Estremi fascicolo virtuale
						if (documentoFromCLient.getEstremiFascicoloVirtuale() != null) {
							EstremiFascicoloVirtuale estremiFascicoloVirtualeResponse = new EstremiFascicoloVirtuale();
							estremiFascicoloVirtualeResponse.setAnno(documentoFromCLient.getEstremiFascicoloVirtuale().getAnno());
							estremiFascicoloVirtualeResponse.setCodiceRegistro(documentoFromCLient.getEstremiFascicoloVirtuale().getCodiceRegistro());
							estremiFascicoloVirtualeResponse.setCodiceUfficio(documentoFromCLient.getEstremiFascicoloVirtuale().getCodiceUfficio());
							estremiFascicoloVirtualeResponse.setDescrizioneRegistro(documentoFromCLient.getEstremiFascicoloVirtuale().getDescrizioneRegistro());
							estremiFascicoloVirtualeResponse.setDescrizioneUfficio(documentoFromCLient.getEstremiFascicoloVirtuale().getDescrizioneUfficio());
							estremiFascicoloVirtualeResponse.setNumero(documentoFromCLient.getEstremiFascicoloVirtuale().getNumero());

							documento.setEstremiFascicoloVirtuale(estremiFascicoloVirtualeResponse);
						}

						// Estremi protocollo estesi
						if (documentoFromCLient.getEstremiProtocolloEstesi() != null) {
							EstremiProtocolloEstesi estremiProtocolloEstesi = new EstremiProtocolloEstesi();
							estremiProtocolloEstesi.setArrivoPartenza(documentoFromCLient.getEstremiProtocolloEstesi().getArrivoPartenza());

							if (documentoFromCLient.getEstremiProtocolloEstesi().getEstremiProtocollo() != null) {
								it.linksmt.protocollo.ws.client.icar.ricercaDoc.EstremiProtocollo estremiProtocollo = new it.linksmt.protocollo.ws.client.icar.ricercaDoc.EstremiProtocollo();
								estremiProtocollo.setAnno(documentoFromCLient.getEstremiProtocolloEstesi().getEstremiProtocollo().getAnno());
								estremiProtocollo.setNumero(documentoFromCLient.getEstremiProtocolloEstesi().getEstremiProtocollo().getNumero());
								estremiProtocolloEstesi.setEstremiProtocollo(estremiProtocollo);
							}
							documento.setEstremiProtocolloEstesi(estremiProtocolloEstesi);
						}
						documento.setOggetto(documentoFromCLient.getOggetto());

						// Responsabili trattamento
						if (documentoFromCLient.getResponsabiliTrattamento() != null) {
							ListaResponsabiliTrattamento responsabiliTrattamento = new ListaResponsabiliTrattamento();
							if (documentoFromCLient.getResponsabiliTrattamento().getResponsabileTrattamento() != null) {
								for (ResponsabileTrattamento responsabileTrattamento : documentoFromCLient.getResponsabiliTrattamento().getResponsabileTrattamento()) {
									it.linksmt.protocollo.ws.client.icar.ricercaDoc.ResponsabileTrattamento responsabileTrattamentoResponse = new it.linksmt.protocollo.ws.client.icar.ricercaDoc.ResponsabileTrattamento();
									responsabileTrattamentoResponse.setCodiceRresponsabileTrattamento(responsabileTrattamento.getCodice_RresponsabileTrattamento());
									responsabileTrattamentoResponse.setDescrizioneResponsabileTrattamento(responsabileTrattamento.getDescrizioneResponsabileTrattamento());
									responsabiliTrattamento.getResponsabileTrattamento().add(responsabileTrattamentoResponse);
								}
							}
							documento.setResponsabiliTrattamento(responsabiliTrattamento);
						}

						documenti.getDocumento().add(documento);
					}
				}

				ap3RicercaResponse.setDocumenti(documenti);
			}
			ap3RicercaResponse.setEsito(ap3RicercaResponseFromClient.isEsito());
			ap3RicercaResponse.setNumeroDocumenti(ap3RicercaResponseFromClient.getNumeroDocumenti());

			risposta.setAP3RicercaResponse(ap3RicercaResponse);

			response = xmlHelper.marshal(risposta);
		}
		catch (Exception e) {
			log.error("getResponse :: " + e.getMessage(), e);
		}

		return response;
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
