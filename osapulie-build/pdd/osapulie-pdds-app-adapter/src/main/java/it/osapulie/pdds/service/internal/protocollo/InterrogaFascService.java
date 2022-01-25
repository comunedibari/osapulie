/************************************************************************************
 * Copyright (c) 2011, 2014 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pdds.service.internal.protocollo;

import java.net.URL;

//import org.apache.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.linksmt.protocollo.ws.client.icar.interrogaFasc.Classifica;
import it.linksmt.protocollo.ws.client.icar.interrogaFasc.EstremiProtocolloEstesi;
import it.linksmt.protocollo.ws.client.icar.interrogaFasc.ListaDocumentiInformatici;
import it.linksmt.protocollo.ws.client.icar.interrogaFasc.ListaLivelli;
import it.linksmt.protocollo.ws.client.icar.interrogaFasc.ListaResponsabiliTrattamento;
import it.linksmt.protocollo.ws.client.icar.interrogaFasc.ResponsabileTrattamento;
import it.linksmt.protocollo.ws.client.icar.interrogaFasc.RichiestaRichiestaRispostaSincronaInterrogaFascType;
import it.linksmt.protocollo.ws.client.icar.interrogaFasc.RispostaRichiestaRispostaSincronaInterrogaFascType;
import it.osapulie.pdds.adapter.util.XMLHelper;
import it.osapulie.pdds.common.PddService;
import it.osapulie.pdds.ws.client.protocollo.interrogaFasc.AP3InterrogaFascRequest;
import it.osapulie.pdds.ws.client.protocollo.interrogaFasc.AP3InterrogaFascResponse;
import it.osapulie.pdds.ws.client.protocollo.interrogaFasc.CodiceArea;
import it.osapulie.pdds.ws.client.protocollo.interrogaFasc.DocumentoFascicolo;
import it.osapulie.pdds.ws.client.protocollo.interrogaFasc.DocumentoInformatico;
import it.osapulie.pdds.ws.client.protocollo.interrogaFasc.Errore;
import it.osapulie.pdds.ws.client.protocollo.interrogaFasc.EstremiFascicolo;
import it.osapulie.pdds.ws.client.protocollo.interrogaFasc.EstremiFascicoloVirtuale;
import it.osapulie.pdds.ws.client.protocollo.interrogaFasc.EstremiProtocollo;
import it.osapulie.pdds.ws.client.protocollo.interrogaFasc.InterrogaFascWsServerImplServiceLocator;
import it.osapulie.pdds.ws.client.protocollo.interrogaFasc.ListaDocumentiFascicolo;
import it.osapulie.pdds.ws.client.protocollo.interrogaFasc.Richiesta_RichiestaRispostaSincrona_InterrogaFasc_Type;
import it.osapulie.pdds.ws.client.protocollo.interrogaFasc.Risposta_RichiestaRispostaSincrona_InterrogaFasc_Type;

/**
 * @author Gianluca Pindinelli
 *
 */
public class InterrogaFascService implements PddService {

	private final Logger log = LoggerFactory.getLogger(InterrogaFascService.class);

	private XMLHelper xmlHelper;

	private String webservicesBaseUrl;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.pdds.adapter.PddService#getName()
	 */
	@Override
	public String getName() {
		return "interrogaFasc";
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.pdds.adapter.PddService#getResponse(java.lang.String)
	 */
	@Override
	public String getResponse(String arg0) {

		RichiestaRichiestaRispostaSincronaInterrogaFascType richiestaFromXml = xmlHelper.unmarshal(arg0, RichiestaRichiestaRispostaSincronaInterrogaFascType.class);

		RispostaRichiestaRispostaSincronaInterrogaFascType risposta = new RispostaRichiestaRispostaSincronaInterrogaFascType();
		it.linksmt.protocollo.ws.client.icar.interrogaFasc.AP3InterrogaFascResponse ap3InterrogaFascResponse = new it.linksmt.protocollo.ws.client.icar.interrogaFasc.AP3InterrogaFascResponse();
		URL wsdlLocation = null;
		String response = null;
		try {
			wsdlLocation = new URL(webservicesBaseUrl + "/adoc/interroga_fasc?wsdl");
			InterrogaFascWsServerImplServiceLocator interrogaFascWSServerImplServiceLocator = new InterrogaFascWsServerImplServiceLocator();
			Richiesta_RichiestaRispostaSincrona_InterrogaFasc_Type parameters = new Richiesta_RichiestaRispostaSincrona_InterrogaFasc_Type();
			AP3InterrogaFascRequest aP3InterrogaFascRequest = new AP3InterrogaFascRequest();
			it.linksmt.protocollo.ws.client.icar.interrogaFasc.AP3InterrogaFascRequest ap3InterrogaFascRequestFromXml = richiestaFromXml.getAP3InterrogaFascRequest();

			it.linksmt.protocollo.ws.client.icar.interrogaFasc.CodiceArea codiceInterrogatoFromXml = ap3InterrogaFascRequestFromXml.getCodiceInterrogato();
			if (codiceInterrogatoFromXml != null) {
				CodiceArea codiceInterrogato = new CodiceArea();
				codiceInterrogato.setCodiceAmm(codiceInterrogatoFromXml.getCodiceAmm());
				codiceInterrogato.setCodiceAoo(codiceInterrogatoFromXml.getCodiceAoo());
				aP3InterrogaFascRequest.setCodiceInterrogato(codiceInterrogato);
			}

			it.linksmt.protocollo.ws.client.icar.interrogaFasc.CodiceArea codiceRichiedenteFromXml = ap3InterrogaFascRequestFromXml.getCodiceRichiedente();
			if (codiceRichiedenteFromXml != null) {
				CodiceArea codiceRichiedente = new CodiceArea();
				codiceRichiedente.setCodiceAmm(codiceRichiedenteFromXml.getCodiceAmm());
				codiceRichiedente.setCodiceAoo(codiceRichiedenteFromXml.getCodiceAoo());
				aP3InterrogaFascRequest.setCodiceRichiedente(codiceRichiedente);
			}

			it.linksmt.protocollo.ws.client.icar.interrogaFasc.EstremiProtocollo estremiProtocolloDestFromXml = ap3InterrogaFascRequestFromXml.getEstremiProtocolloDest();
			if (estremiProtocolloDestFromXml != null) {
				EstremiProtocollo estremiProtocolloDest = new EstremiProtocollo();
				estremiProtocolloDest.setAnno(estremiProtocolloDestFromXml.getAnno());
				estremiProtocolloDest.setNumero(estremiProtocolloDestFromXml.getNumero());
				aP3InterrogaFascRequest.setEstremiProtocolloDest(estremiProtocolloDest);
			}

			it.linksmt.protocollo.ws.client.icar.interrogaFasc.EstremiProtocollo estremiProtocolloMittFromXml = ap3InterrogaFascRequestFromXml.getEstremiProtocolloMitt();
			if (estremiProtocolloMittFromXml != null) {
				EstremiProtocollo estremiProtocolloMitt = new EstremiProtocollo();
				estremiProtocolloMitt.setAnno(estremiProtocolloMittFromXml.getAnno());
				estremiProtocolloMitt.setNumero(estremiProtocolloMittFromXml.getNumero());
				aP3InterrogaFascRequest.setEstremiProtocolloMitt(estremiProtocolloMitt);
			}

			parameters.setAP3InterrogaFascRequest(aP3InterrogaFascRequest);

			Risposta_RichiestaRispostaSincrona_InterrogaFasc_Type richiestaRispostaSincrona_InterrogaFasc = interrogaFascWSServerImplServiceLocator.getInterrogaFascWsServerImplPort(wsdlLocation)
					.richiestaRispostaSincrona_InterrogaFasc(parameters);

			AP3InterrogaFascResponse ap3InterrogaFascResponseFromClient = richiestaRispostaSincrona_InterrogaFasc.getAP3InterrogaFascResponse();

			// Errore
			Errore erroreFromClient = ap3InterrogaFascResponseFromClient.getErrore();
			if (erroreFromClient != null) {
				it.linksmt.protocollo.ws.client.icar.interrogaFasc.Errore errore = new it.linksmt.protocollo.ws.client.icar.interrogaFasc.Errore();
				errore.setCodiceErrore(erroreFromClient.getCodiceErrore());
				errore.setMsgErrore(erroreFromClient.getMsgErrore());
				ap3InterrogaFascResponse.setErrore(errore);
			}

			// Documenti fascicolo
			ListaDocumentiFascicolo documentiFascicolo = ap3InterrogaFascResponseFromClient.getDocumentiFascicolo();
			if (documentiFascicolo != null) {

				it.linksmt.protocollo.ws.client.icar.interrogaFasc.ListaDocumentiFascicolo documentiFascicoloResponse = new it.linksmt.protocollo.ws.client.icar.interrogaFasc.ListaDocumentiFascicolo();

				DocumentoFascicolo[] documentoFascicolo = documentiFascicolo.getDocumentoFascicolo();
				if (documentoFascicolo != null && documentoFascicolo.length > 0) {
					for (DocumentoFascicolo documentoFascicolo2 : documentoFascicolo) {
						it.linksmt.protocollo.ws.client.icar.interrogaFasc.DocumentoFascicolo documentoFascicoloResponse = new it.linksmt.protocollo.ws.client.icar.interrogaFasc.DocumentoFascicolo();
						if (documentoFascicolo2.getClassifica() != null) {
							Classifica classifica = new Classifica();
							classifica.setCodiceClassifica(documentoFascicolo2.getClassifica().getCodiceClassifica());
							classifica.setDescrizioneClassifica(documentoFascicolo2.getClassifica().getDescrizioneClassifica());
							if (documentoFascicolo2.getClassifica().getLivelli() != null) {
								ListaLivelli livelli = new ListaLivelli();
								if (documentoFascicolo2.getClassifica().getLivelli().getLivelloClassifica() != null) {
									for (String livelloClassifica : documentoFascicolo2.getClassifica().getLivelli().getLivelloClassifica()) {
										livelli.getLivelloClassifica().add(livelloClassifica);
									}
								}
								classifica.setLivelli(livelli);
							}
							documentoFascicoloResponse.setClassifica(classifica);
						}
						documentoFascicoloResponse.setCodiceAmm(documentoFascicolo2.getCodiceAmm());
						documentoFascicoloResponse.setCodiceAoo(documentoFascicolo2.getCodiceAoo());
						documentoFascicoloResponse.setCodiceRegistro(documentoFascicolo2.getCodiceRegistro());
						documentoFascicoloResponse.setCodiceUfficio(documentoFascicolo2.getCodiceUfficio());
						if (documentoFascicolo2.getDataRegistrazione() != null) {
							documentoFascicoloResponse.setDataRegistrazione(documentoFascicolo2.getDataRegistrazione());
						}
						documentoFascicoloResponse.setDescrCorrispondente(documentoFascicolo2.getDescrCorrispondente());
						documentoFascicoloResponse.setDescrizioneRegistro(documentoFascicolo2.getDescrizioneRegistro());
						documentoFascicoloResponse.setDescrizioneUfficio(documentoFascicolo2.getDescrizioneUfficio());
						if (documentoFascicolo2.getDocumentiInformatici() != null) {

							ListaDocumentiInformatici documentiInformatici = new ListaDocumentiInformatici();

							if (documentoFascicolo2.getDocumentiInformatici().getDocumentoInformatico() != null) {
								for (DocumentoInformatico documentoInformatico : documentoFascicolo2.getDocumentiInformatici().getDocumentoInformatico()) {
									it.linksmt.protocollo.ws.client.icar.interrogaFasc.DocumentoInformatico documentoInformaticoResponse = new it.linksmt.protocollo.ws.client.icar.interrogaFasc.DocumentoInformatico();
									documentoInformaticoResponse.setCodiceDocumentoInformatico(documentoInformatico.getCodiceDocumentoInformatico());
									documentoInformaticoResponse.setDescrizioneDocumentoInformatico(documentoInformatico.getDescrizioneDocumentoInformatico());
									documentoInformaticoResponse.setFlPrimario(documentoInformatico.getFlPrimario());
									documentiInformatici.getDocumentoInformatico().add(documentoInformaticoResponse);
								}
							}
							documentoFascicoloResponse.setDocumentiInformatici(documentiInformatici);
						}

						if (documentoFascicolo2.getEstremiProtocolloEstesi() != null) {
							EstremiProtocolloEstesi estremiProtocolloEstesi = new EstremiProtocolloEstesi();
							estremiProtocolloEstesi.setArrivoPartenza(documentoFascicolo2.getEstremiProtocolloEstesi().getArrivoPartenza());

							if (documentoFascicolo2.getEstremiProtocolloEstesi().getEstremiProtocollo() != null) {
								it.linksmt.protocollo.ws.client.icar.interrogaFasc.EstremiProtocollo estremiProtocollo = new it.linksmt.protocollo.ws.client.icar.interrogaFasc.EstremiProtocollo();
								estremiProtocollo.setAnno(documentoFascicolo2.getEstremiProtocolloEstesi().getEstremiProtocollo().getAnno());
								estremiProtocollo.setNumero(documentoFascicolo2.getEstremiProtocolloEstesi().getEstremiProtocollo().getNumero());
							}
							documentoFascicoloResponse.setEstremiProtocolloEstesi(estremiProtocolloEstesi);
						}

						documentoFascicoloResponse.setOggetto(documentoFascicolo2.getOggetto());
						if (documentoFascicolo2.getResponsabiliTrattamento() != null) {
							ListaResponsabiliTrattamento responsabiliTrattamento = new ListaResponsabiliTrattamento();

							if (documentoFascicolo2.getResponsabiliTrattamento().getResponsabileTrattamento() != null) {
								for (it.osapulie.pdds.ws.client.protocollo.interrogaFasc.ResponsabileTrattamento responsabileTrattamento : documentoFascicolo2.getResponsabiliTrattamento()
										.getResponsabileTrattamento()) {
									ResponsabileTrattamento responsabileTrattamentoResponse = new ResponsabileTrattamento();
									responsabileTrattamentoResponse.setCodiceRresponsabileTrattamento(responsabileTrattamento.getCodice_RresponsabileTrattamento());
									responsabileTrattamentoResponse.setDescrizioneResponsabileTrattamento(responsabileTrattamento.getDescrizioneResponsabileTrattamento());

									responsabiliTrattamento.getResponsabileTrattamento().add(responsabileTrattamentoResponse);
								}
							}
							documentoFascicoloResponse.setResponsabiliTrattamento(responsabiliTrattamento);
						}

						documentiFascicoloResponse.getDocumentoFascicolo().add(documentoFascicoloResponse);
					}
				}

				ap3InterrogaFascResponse.setDocumentiFascicolo(documentiFascicoloResponse);
			}

			// Estremi fascicolo
			EstremiFascicolo estremiFascicoloFromClient = ap3InterrogaFascResponseFromClient.getEstremiFascicolo();
			if (estremiFascicoloFromClient != null) {
				it.linksmt.protocollo.ws.client.icar.interrogaFasc.EstremiFascicolo estremiFascicoloResponse = new it.linksmt.protocollo.ws.client.icar.interrogaFasc.EstremiFascicolo();
				estremiFascicoloResponse.setAnno(estremiFascicoloFromClient.getAnno());
				if (estremiFascicoloFromClient.getClassifica() != null) {
					Classifica classifica = new Classifica();
					classifica.setCodiceClassifica(estremiFascicoloFromClient.getClassifica().getCodiceClassifica());
					classifica.setDescrizioneClassifica(estremiFascicoloFromClient.getClassifica().getDescrizioneClassifica());

					if (estremiFascicoloFromClient.getClassifica().getLivelli() != null) {
						ListaLivelli livelli = new ListaLivelli();
						if (estremiFascicoloFromClient.getClassifica().getLivelli().getLivelloClassifica() != null) {
							for (String livelloClassifica : estremiFascicoloFromClient.getClassifica().getLivelli().getLivelloClassifica()) {
								livelli.getLivelloClassifica().add(livelloClassifica);
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
			}

			// Estremi fascicolo virtuale
			EstremiFascicoloVirtuale estremiFascicoloVirtualeFromClient = ap3InterrogaFascResponseFromClient.getEstremiFascicoloVirtuale();
			if (estremiFascicoloVirtualeFromClient != null) {
				it.linksmt.protocollo.ws.client.icar.interrogaFasc.EstremiFascicoloVirtuale estremiFascicoloVirtualeResponse = new it.linksmt.protocollo.ws.client.icar.interrogaFasc.EstremiFascicoloVirtuale();
				estremiFascicoloVirtualeResponse.setAnno(estremiFascicoloVirtualeFromClient.getAnno());
				estremiFascicoloVirtualeResponse.setCodiceRegistro(estremiFascicoloVirtualeFromClient.getCodiceRegistro());
				estremiFascicoloVirtualeResponse.setCodiceUfficio(estremiFascicoloVirtualeFromClient.getCodiceUfficio());
				estremiFascicoloVirtualeResponse.setDescrizioneRegistro(estremiFascicoloVirtualeFromClient.getDescrizioneRegistro());
				estremiFascicoloVirtualeResponse.setDescrizioneUfficio(estremiFascicoloVirtualeFromClient.getDescrizioneUfficio());
				estremiFascicoloVirtualeResponse.setNumero(estremiFascicoloVirtualeFromClient.getNumero());

				ap3InterrogaFascResponse.setEstremiFascicoloVirtuale(estremiFascicoloVirtualeResponse);
			}

			ap3InterrogaFascResponse.setNumeroDocumenti(ap3InterrogaFascResponseFromClient.getNumeroDocumenti());
			ap3InterrogaFascResponse.setNumeroDocumentiNonVisibili(ap3InterrogaFascResponseFromClient.getNumeroDocumentiNonVisibili());

			risposta.setAP3InterrogaFascResponse(ap3InterrogaFascResponse);

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
