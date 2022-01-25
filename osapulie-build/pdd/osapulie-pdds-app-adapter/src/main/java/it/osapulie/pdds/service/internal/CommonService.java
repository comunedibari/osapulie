/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pdds.service.internal;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.List;

import javax.xml.rpc.ServiceException;

import it.osapulie.pdds.ws.client.visuraimpostainsegnepubblicita.PosizioneVisuraImposteInsegnePubblicita;
import it.osapulie.pdds.ws.client.visuraimpostainsegnepubblicita.RataVisuraImposteInsegnePubblicita;
import it.osapulie.pdds.ws.client.visuraimpostainsegnepubblicita.VisuraImpostaInsegnePubblicitaServiceLocator;
import it.osapulie.pdds.ws.client.visuraimpostainsegnepubblicita.VisuraImpostaInsegnePubblicitaServicePortType;
import it.osapulie.pdds.ws.client.visuraimpostainsegnepubblicita.VisuraImposteInsegnePubblicita;
import it.osapulie.pdds.ws.client.visuraosappermanente.PosizioneVisuraOsapPermanente;
import it.osapulie.pdds.ws.client.visuraosappermanente.RataVisuraOsapPermanente;
import it.osapulie.pdds.ws.client.visuraosappermanente.VisuraOsapPermanente;
import it.osapulie.pdds.ws.client.visuraosappermanente.VisuraOsapPermanenteServiceLocator;
import it.osapulie.pdds.ws.client.visuraosappermanente.VisuraOsapPermanenteServicePortType;
import it.osapulie.pdds.ws.client.visuraosaptemporanea.PosizioneVisuraOsapTemporanea;
import it.osapulie.pdds.ws.client.visuraosaptemporanea.VisuraOsapTemporanea;
import it.osapulie.pdds.ws.client.visuraosaptemporanea.VisuraOsapTemporaneaServiceLocator;
import it.osapulie.pdds.ws.client.visuraosaptemporanea.VisuraOsapTemporaneaServicePortType;
import it.osapulie.pdds.ws.client.visurapubblicheaffissioni.PosizioneVisuraPubblicheAffissioni;
import it.osapulie.pdds.ws.client.visurapubblicheaffissioni.RataVisuraPubblicheAffissioni;
import it.osapulie.pdds.ws.client.visurapubblicheaffissioni.VisuraPubblicheAffissioni;
import it.osapulie.pdds.ws.client.visurapubblicheaffissioni.VisuraPubblicheAffissioniServiceLocator;
import it.osapulie.pdds.ws.client.visurapubblicheaffissioni.VisuraPubblicheAffissioniServicePortType;
import it.osapulie.pdds.ws.client.visuraservizicimiteriali.DefuntoPosizioneVisuraServiziCimiteriali;
import it.osapulie.pdds.ws.client.visuraservizicimiteriali.LampadaVotivaPosizioneVisuraServiziCimiteriali;
import it.osapulie.pdds.ws.client.visuraservizicimiteriali.PosizioneVisuraServiziCimiteriali;
import it.osapulie.pdds.ws.client.visuraservizicimiteriali.RataVisuraServiziCimiteriali;
import it.osapulie.pdds.ws.client.visuraservizicimiteriali.VisuraServiziCimiteriali;
import it.osapulie.pdds.ws.client.visuraservizicimiteriali.VisuraServiziCimiterialiServiceLocator;
import it.osapulie.pdds.ws.client.visuraservizicimiteriali.VisuraServiziCimiterialiServicePortType;
import it.osapulie.pdds.ws.client.visuratassaimmobili.PosizioneVisuraTassaImmobili;
import it.osapulie.pdds.ws.client.visuratassaimmobili.RataVisuraTassaImmobili;
import it.osapulie.pdds.ws.client.visuratassaimmobili.VisuraTassaImmobili;
import it.osapulie.pdds.ws.client.visuratassaimmobili.VisuraTassaImmobiliServiceLocator;
import it.osapulie.pdds.ws.client.visuratassaimmobili.VisuraTassaImmobiliServicePortType;
import it.osapulie.pdds.ws.client.visuratassarifiuti.OccupazionePosizioneVisuraTassaRifiuti;
import it.osapulie.pdds.ws.client.visuratassarifiuti.PosizioneVisuraTassaRifiuti;
import it.osapulie.pdds.ws.client.visuratassarifiuti.RataVisuraTassaRifiuti;
import it.osapulie.pdds.ws.client.visuratassarifiuti.RiduzionePosizioneVisuraTassaRifiuti;
import it.osapulie.pdds.ws.client.visuratassarifiuti.VisuraTassaRifiuti;
import it.osapulie.pdds.ws.client.visuratassarifiuti.VisuraTassaRifiutiServiceLocator;
import it.osapulie.pdds.ws.client.visuratassarifiuti.VisuraTassaRifiutiServicePortType;
import it.osapulie.tributi.web.ws.output.types.Codifica;
import it.osapulie.tributi.web.ws.output.types.DatiOsapPermanente;
import it.osapulie.tributi.web.ws.output.types.DatiOsapPermanente.PosizioniOsap;
import it.osapulie.tributi.web.ws.output.types.DatiOsapTemporanea;
import it.osapulie.tributi.web.ws.output.types.DatiTassaImmobili;
import it.osapulie.tributi.web.ws.output.types.DatiTassaImmobili.Posizioni;
import it.osapulie.tributi.web.ws.output.types.DatiTassaRifiuti;
import it.osapulie.tributi.web.ws.output.types.DatiTassaRifiuti.Posizioni.OccupazioneNucleoFamiliare;
import it.osapulie.tributi.web.ws.output.types.Indirizzo;
import it.osapulie.tributi.web.ws.output.types.Indirizzo.Civico;
import it.osapulie.tributi.web.ws.output.types.PagamentiAffissioniType;
import it.osapulie.tributi.web.ws.output.types.PagamentiAffissioniType.PosizioniAffissione;
import it.osapulie.tributi.web.ws.output.types.PagamentiPubblicitaType;
import it.osapulie.tributi.web.ws.output.types.PagamentiPubblicitaType.PosizioniPubblicita;
import it.osapulie.tributi.web.ws.output.types.PagamentiServiziCimiterialiType;
import it.osapulie.tributi.web.ws.output.types.PagamentiServiziCimiterialiType.PosizioniServiziCimiteriali;
import it.osapulie.tributi.web.ws.output.types.PagamentiServiziCimiterialiType.PosizioniServiziCimiteriali.Defunti;
import it.osapulie.tributi.web.ws.output.types.PagamentiServiziCimiterialiType.PosizioniServiziCimiteriali.LampadaVotiva;
import it.osapulie.tributi.web.ws.output.types.PagamentiServiziCimiterialiType.Rate;
import it.osapulie.tributi.web.ws.output.types.Riduzione;

/**
 * @author Gianluca Pindinelli
 *
 */
public class CommonService {

	public String webservicesBaseUrl;

	/**
	 *
	 * @param elencoPagamentiTassaRifiuti
	 * @param codiceFiscale
	 * @param annoInizio
	 * @param annoFine
	 * @throws MalformedURLException
	 * @throws ServiceException
	 * @throws RemoteException
	 */
	public void setVisureTassaRifiuti(List<DatiTassaRifiuti> elencoPagamentiTassaRifiuti, String codiceFiscale, Integer annoInizio, Integer annoFine)
			throws MalformedURLException, ServiceException, RemoteException {

		URL wsdlLocation = new URL(webservicesBaseUrl + "/services/visuraTassaRifiutiService?wsdl");
		VisuraTassaRifiutiServiceLocator visuraTassaRifiutiServiceLocator = new VisuraTassaRifiutiServiceLocator();

		VisuraTassaRifiutiServicePortType secureSOAP11Endpoint = visuraTassaRifiutiServiceLocator.getSecureSOAP11Endpoint(wsdlLocation);

		VisuraTassaRifiuti[] visureTassaRifiuti = secureSOAP11Endpoint.getVisureTassaRifiuti(codiceFiscale, annoInizio, annoFine);

		setVisureTassaRifiuti(elencoPagamentiTassaRifiuti, visureTassaRifiuti, secureSOAP11Endpoint);
	}

	/**
	 * @param elencoPagamentiTassaRifiuti
	 * @param secureSOAP11Endpoint
	 * @param visureTassaRifiuti
	 * @throws RemoteException
	 */
	public void setVisureTassaRifiuti(List<DatiTassaRifiuti> elencoPagamentiTassaRifiuti, VisuraTassaRifiuti[] visureTassaRifiuti, VisuraTassaRifiutiServicePortType secureSOAP11Endpoint)
			throws RemoteException {

		if (visureTassaRifiuti != null) {
			for (VisuraTassaRifiuti visuraTassaRifiuti : visureTassaRifiuti) {
				DatiTassaRifiuti datiTassaRifiuti = new DatiTassaRifiuti();

				if (visuraTassaRifiuti.getAnnoRiferimento() != null) {
					datiTassaRifiuti.setAnnoRiferimento(visuraTassaRifiuti.getAnnoRiferimento().intValue());
				}
				datiTassaRifiuti.setContoCorrente(visuraTassaRifiuti.getContoCorrente());
				if (visuraTassaRifiuti.getDataAggiornamento() != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(visuraTassaRifiuti.getDataAggiornamento());
					datiTassaRifiuti.setDataAggiornamento(calendar);
				}
				if (visuraTassaRifiuti.getDataFine() != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(visuraTassaRifiuti.getDataFine());
					datiTassaRifiuti.setDataFine(calendar);
				}
				if (visuraTassaRifiuti.getDataInizio() != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(visuraTassaRifiuti.getDataInizio());
					datiTassaRifiuti.setDataInizio(calendar);
				}
				datiTassaRifiuti.setDescrizioneTassa(visuraTassaRifiuti.getDescrizioneTassa());
				datiTassaRifiuti.setNumeroDocumento(visuraTassaRifiuti.getNumeroDocumento());
				datiTassaRifiuti.setImportoDocumento(visuraTassaRifiuti.getImportoDocumento());

				// Posizioni
				PosizioneVisuraTassaRifiuti[] posizioniVisuraTassaRifiuti = secureSOAP11Endpoint.getPosizioniVisuraTassaRifiuti(visuraTassaRifiuti.getId().intValue());
				if (posizioniVisuraTassaRifiuti != null) {

					List<it.osapulie.tributi.web.ws.output.types.DatiTassaRifiuti.Posizioni> posizioni = datiTassaRifiuti.getPosizioni();
					for (PosizioneVisuraTassaRifiuti posizioneVisuraTassaRifiuti : posizioniVisuraTassaRifiuti) {
						it.osapulie.tributi.web.ws.output.types.DatiTassaRifiuti.Posizioni posizione = new it.osapulie.tributi.web.ws.output.types.DatiTassaRifiuti.Posizioni();

						posizione.setAddizionaleComunale(posizioneVisuraTassaRifiuti.getAddizionaleComunale());
						posizione.setAddizionaleProvinciale(posizioneVisuraTassaRifiuti.getAddizionaleProvinciale());
						posizione.setAgevolazione(posizioneVisuraTassaRifiuti.getAgevolazione());
						posizione.setAliquota(posizioneVisuraTassaRifiuti.getAliquota());

						Codifica categoriaImmobileCodifica = new Codifica(posizioneVisuraTassaRifiuti.getCategoriaImmobile(), posizioneVisuraTassaRifiuti.getCategoriaImmobile());
						posizione.setCategoriaImmobile(categoriaImmobileCodifica);

						Codifica destinazioneCodifica = new Codifica(posizioneVisuraTassaRifiuti.getDestinazione(), null);
						posizione.setDestinazione(destinazioneCodifica);
						if (posizioneVisuraTassaRifiuti.getIdentificativoUtenza() != null) {
							posizione.setIdentificativoUtenza(posizioneVisuraTassaRifiuti.getIdentificativoUtenza());
						}
						if (posizioneVisuraTassaRifiuti.getImportoDaPagare() != null) {
							posizione.setImportoDaPagare(posizioneVisuraTassaRifiuti.getImportoDaPagare());
						}
						if (posizioneVisuraTassaRifiuti.getImportoTariffa() != null) {
							posizione.setImportoTariffa(posizioneVisuraTassaRifiuti.getImportoTariffa());
						}
						Indirizzo indirizzoUtenza = new Indirizzo();
						String codiceVia = posizioneVisuraTassaRifiuti.getCodiceVia();
						String viaString = posizioneVisuraTassaRifiuti.getVia();
						String codiceCivico = posizioneVisuraTassaRifiuti.getCodiceCivico();
						BigInteger civicoBigInteger = posizioneVisuraTassaRifiuti.getCivico();
						String esponente = posizioneVisuraTassaRifiuti.getEsponente();

						Codifica via = new Codifica();
						via.setCodice(codiceVia);
						via.setDescrizione(viaString);

						indirizzoUtenza.setVia(via);

						Civico civico = new Civico();
						civico.setCodice(codiceCivico);
						civico.setEsponente(esponente);
						if (civicoBigInteger != null) {
							civico.setNumero(civicoBigInteger.intValue());
						}

						indirizzoUtenza.setCivico(civico);
						indirizzoUtenza.setCap(posizioneVisuraTassaRifiuti.getCap());
						indirizzoUtenza.setLocalita(posizioneVisuraTassaRifiuti.getLocalita());
						indirizzoUtenza.setPiano(posizioneVisuraTassaRifiuti.getPiano());
						indirizzoUtenza.setInterno(posizioneVisuraTassaRifiuti.getInterno());
						indirizzoUtenza.setScala(posizioneVisuraTassaRifiuti.getScala());
						indirizzoUtenza.setSuffisso(posizioneVisuraTassaRifiuti.getSuffisso());

						posizione.setIndirizzoUtenza(indirizzoUtenza);

						if (posizioneVisuraTassaRifiuti.getMaggiorazioneStato() != null) {
							posizione.setMaggiorazioneStato(posizioneVisuraTassaRifiuti.getMaggiorazioneStato());
						}
						if (posizioneVisuraTassaRifiuti.getFoglio() != null) {
							posizione.setFoglio(posizioneVisuraTassaRifiuti.getFoglio().intValue());
						}
						if (posizioneVisuraTassaRifiuti.getNumero() != null) {
							posizione.setNumero(posizioneVisuraTassaRifiuti.getNumero().intValue());
						}
						posizione.setSezione(posizioneVisuraTassaRifiuti.getSezione());
						if (posizioneVisuraTassaRifiuti.getParticella() != null) {
							posizione.setParticella(posizioneVisuraTassaRifiuti.getParticella().intValue());
						}
						if (posizioneVisuraTassaRifiuti.getSubalterno() != null) {
							posizione.setSubalterno(posizioneVisuraTassaRifiuti.getSubalterno().intValue());
						}

						if (posizioneVisuraTassaRifiuti.getSuperficie() != null) {
							posizione.setSuperficie(new BigDecimal(posizioneVisuraTassaRifiuti.getSuperficie()));
						}

						posizione.setTipo(posizioneVisuraTassaRifiuti.getTipo());

						// Occupazioni
						OccupazionePosizioneVisuraTassaRifiuti[] occupazioniPosizioneVisuraTassaRifiuti = secureSOAP11Endpoint
								.getOccupazioniPosizioneVisuraTassaRifiuti(posizioneVisuraTassaRifiuti.getId().intValue());

						if (occupazioniPosizioneVisuraTassaRifiuti != null) {
							List<OccupazioneNucleoFamiliare> occupazioneNucleoFamiliare = posizione.getOccupazioneNucleoFamiliare();
							for (OccupazionePosizioneVisuraTassaRifiuti occupazionePosizioneVisuraTassaRifiuti : occupazioniPosizioneVisuraTassaRifiuti) {
								OccupazioneNucleoFamiliare visuraOccupazioneNucleoFamiliare = new OccupazioneNucleoFamiliare();

								if (occupazionePosizioneVisuraTassaRifiuti.getDataFineOccupazione() != null) {
									Calendar calendar = Calendar.getInstance();
									calendar.setTime(occupazionePosizioneVisuraTassaRifiuti.getDataFineOccupazione());
									visuraOccupazioneNucleoFamiliare.setDataFineOccupazione(calendar);
								}
								if (occupazionePosizioneVisuraTassaRifiuti.getDataInizioOccupazione() != null) {
									Calendar calendar = Calendar.getInstance();
									calendar.setTime(occupazionePosizioneVisuraTassaRifiuti.getDataInizioOccupazione());
									visuraOccupazioneNucleoFamiliare.setDataInizioOccupazione(calendar);
								}

								if (occupazionePosizioneVisuraTassaRifiuti.getImportoTariffa() != null) {
									visuraOccupazioneNucleoFamiliare.setImportoTariffa(occupazionePosizioneVisuraTassaRifiuti.getImportoTariffa());
								}
								if (occupazionePosizioneVisuraTassaRifiuti.getNumeroComponenti() != null) {
									visuraOccupazioneNucleoFamiliare.setNumeroComponenti(occupazionePosizioneVisuraTassaRifiuti.getNumeroComponenti().intValue());
								}

								occupazioneNucleoFamiliare.add(visuraOccupazioneNucleoFamiliare);
							}
						}

						// Riduzioni
						RiduzionePosizioneVisuraTassaRifiuti[] riduzioniVisuraTassaRifiuti = secureSOAP11Endpoint.getRiduzioniVisuraTassaRifiuti(posizioneVisuraTassaRifiuti.getId().intValue());
						if (riduzioniVisuraTassaRifiuti != null) {
							List<Riduzione> riduzioni = posizione.getRiduzione();
							for (RiduzionePosizioneVisuraTassaRifiuti riduzionePosizioneVisuraTassaRifiuti : riduzioniVisuraTassaRifiuti) {
								Riduzione riduzione = new Riduzione();
								riduzione.setArticolo(new Codifica(riduzionePosizioneVisuraTassaRifiuti.getCodiceArticolo(), riduzionePosizioneVisuraTassaRifiuti.getDescrizioneArticolo()));
								riduzione.setNote(riduzionePosizioneVisuraTassaRifiuti.getNote());
								riduzione.setValore(new Codifica(riduzionePosizioneVisuraTassaRifiuti.getCodiceValore(), riduzionePosizioneVisuraTassaRifiuti.getDescrizioneValore()));
								riduzioni.add(riduzione);
							}
						}

						posizione.setImportoRiduzioni(posizioneVisuraTassaRifiuti.getImportoRiduzione());
						posizioni.add(posizione);
					}
				}

				// Rate
				RataVisuraTassaRifiuti[] rateVisuraTassaRifiuti = secureSOAP11Endpoint.getRateVisuraTassaRifiuti(visuraTassaRifiuti.getId().intValue());
				if (rateVisuraTassaRifiuti != null) {
					List<it.osapulie.tributi.web.ws.output.types.DatiTassaRifiuti.Rate> rate = datiTassaRifiuti.getRate();
					for (RataVisuraTassaRifiuti rataVisuraTassaRifiuti : rateVisuraTassaRifiuti) {
						it.osapulie.tributi.web.ws.output.types.DatiTassaRifiuti.Rate rata = new it.osapulie.tributi.web.ws.output.types.DatiTassaRifiuti.Rate();

						if (rataVisuraTassaRifiuti.getDataPagamento() != null) {
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(rataVisuraTassaRifiuti.getDataPagamento());
							rata.setDataPagamento(calendar);
						}
						rata.setIdentificativoRata(rataVisuraTassaRifiuti.getIdentificativoRata());
						if (rataVisuraTassaRifiuti.getImportoPagato() != null) {
							rata.setImportoPagato(rataVisuraTassaRifiuti.getImportoPagato());
						}
						if (rataVisuraTassaRifiuti.getImportoRata() != null) {
							rata.setImportoRata(rataVisuraTassaRifiuti.getImportoRata());
						}
						if (rataVisuraTassaRifiuti.getNumeroRata() != null) {
							rata.setNumeroRata(rataVisuraTassaRifiuti.getNumeroRata().intValue());
						}
						if (rataVisuraTassaRifiuti.getScadenzaRata() != null) {
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(rataVisuraTassaRifiuti.getScadenzaRata());
							rata.setScadenzaRata(calendar);
						}
						rate.add(rata);
					}
				}
				elencoPagamentiTassaRifiuti.add(datiTassaRifiuti);
			}
		}
	}

	/**
	 *
	 * @param elencoPagamentiOsapTemporanea
	 * @param codiceFiscale
	 * @param annoInizio
	 * @param annoFine
	 * @throws MalformedURLException
	 * @throws ServiceException
	 * @throws RemoteException
	 */
	public void setVisureOsapTemporanea(List<DatiOsapTemporanea> elencoPagamentiOsapTemporanea, String codiceFiscale, Integer annoInizio, Integer annoFine)
			throws MalformedURLException, ServiceException, RemoteException {

		URL wsdlLocation = new URL(webservicesBaseUrl + "/services/visuraOsapTemporaneaService?wsdl");
		VisuraOsapTemporaneaServiceLocator visuraOsapTemporaneaServiceLocator = new VisuraOsapTemporaneaServiceLocator();
		VisuraOsapTemporaneaServicePortType secureSOAP11Endpoint = visuraOsapTemporaneaServiceLocator.getSecureSOAP11Endpoint(wsdlLocation);

		VisuraOsapTemporanea[] visureOsapTemporanea = secureSOAP11Endpoint.getVisureOsapTemporanea(codiceFiscale, annoInizio, annoFine);

		setVisureOsapTemporanea(elencoPagamentiOsapTemporanea, visureOsapTemporanea, secureSOAP11Endpoint);
	}

	/**
	 * @param elencoPagamentiOsapTemporanea
	 * @param visureOsapTemporanea
	 * @param secureSOAP11Endpoint
	 * @throws RemoteException
	 */
	public void setVisureOsapTemporanea(List<DatiOsapTemporanea> elencoPagamentiOsapTemporanea, VisuraOsapTemporanea[] visureOsapTemporanea, VisuraOsapTemporaneaServicePortType secureSOAP11Endpoint)
			throws RemoteException {

		if (visureOsapTemporanea != null) {
			for (VisuraOsapTemporanea visuraOsapTemporanea : visureOsapTemporanea) {

				DatiOsapTemporanea osapTemporanea = new DatiOsapTemporanea();

				if (visuraOsapTemporanea.getAnnoRiferimento() != null) {
					osapTemporanea.setAnnoRiferimento(visuraOsapTemporanea.getAnnoRiferimento().intValue());
				}
				osapTemporanea.setContoCorrente(visuraOsapTemporanea.getContoCorrente());
				if (visuraOsapTemporanea.getDataAggiornamento() != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(visuraOsapTemporanea.getDataAggiornamento());
					osapTemporanea.setDataAggiornamento(calendar);
				}
				osapTemporanea.setImportoDocumento(visuraOsapTemporanea.getImportoDocumento());
				osapTemporanea.setNumeroDocumento(visuraOsapTemporanea.getNumeroDocumento());

				// Posizioni
				PosizioneVisuraOsapTemporanea[] posizioniVisuraOsapTemporanea = secureSOAP11Endpoint.getPosizioniVisuraOsapTemporanea(visuraOsapTemporanea.getId().intValue());
				if (posizioniVisuraOsapTemporanea != null) {

					List<it.osapulie.tributi.web.ws.output.types.DatiOsapTemporanea.PosizioniOsap> posizioniOsapTemporanee = osapTemporanea.getPosizioniOsap();

					for (PosizioneVisuraOsapTemporanea posizioneVisuraOsapTemporanea : posizioniVisuraOsapTemporanea) {

						it.osapulie.tributi.web.ws.output.types.DatiOsapTemporanea.PosizioniOsap posizioneOsapTemporanee = new it.osapulie.tributi.web.ws.output.types.DatiOsapTemporanea.PosizioniOsap();

						if (posizioneVisuraOsapTemporanea.getDataPagamento() != null) {
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(posizioneVisuraOsapTemporanea.getDataPagamento());
							posizioneOsapTemporanee.setDataPagamento(calendar);
						}
						posizioneOsapTemporanee.setDescrizioneTariffa(posizioneVisuraOsapTemporanea.getDescrizioneTariffa());
						if (posizioneVisuraOsapTemporanea.getDurataOccupazione() != null) {
							posizioneOsapTemporanee.setDurataOccupazione(posizioneVisuraOsapTemporanea.getDurataOccupazione().intValue());
						}
						if (posizioneVisuraOsapTemporanea.getImportoDaPagare() != null) {
							posizioneOsapTemporanee.setImportoDaPagare(posizioneVisuraOsapTemporanea.getImportoDaPagare());
						}
						if (posizioneVisuraOsapTemporanea.getImportoPagato() != null) {
							posizioneOsapTemporanee.setImportoPagato(posizioneVisuraOsapTemporanea.getImportoPagato());
						}
						posizioneOsapTemporanee.setIndirizzoUtenza(posizioneVisuraOsapTemporanea.getIndirizzoUtenza());
						if (posizioneVisuraOsapTemporanea.getSuperficie() != null) {
							posizioneOsapTemporanee.setSuperficie(posizioneVisuraOsapTemporanea.getSuperficie());
						}
						posizioneOsapTemporanee.setZona(posizioneVisuraOsapTemporanea.getZona());

						posizioniOsapTemporanee.add(posizioneOsapTemporanee);
					}
				}
				elencoPagamentiOsapTemporanea.add(osapTemporanea);
			}
		}
	}

	/**
	 *
	 * @param elencoPagamentiOsapPermananente
	 * @param codiceFiscale
	 * @param annoInizio
	 * @param annoFine
	 * @throws MalformedURLException
	 * @throws ServiceException
	 * @throws RemoteException
	 */
	public void setVisureOsapPermanente(List<DatiOsapPermanente> elencoPagamentiOsapPermananente, String codiceFiscale, Integer annoInizio, Integer annoFine)
			throws MalformedURLException, ServiceException, RemoteException {

		URL wsdlLocation = new URL(webservicesBaseUrl + "/services/visuraOsapPermanenteService?wsdl");

		VisuraOsapPermanenteServiceLocator visuraOsapPermanenteServiceLocator = new VisuraOsapPermanenteServiceLocator();
		VisuraOsapPermanenteServicePortType secureSOAP11Endpoint = visuraOsapPermanenteServiceLocator.getSecureSOAP11Endpoint(wsdlLocation);

		VisuraOsapPermanente[] visureOsapPermanente = secureSOAP11Endpoint.getVisureOsapPermanente(codiceFiscale, annoInizio, annoFine);

		setVisureOsapPermanente(elencoPagamentiOsapPermananente, visureOsapPermanente, secureSOAP11Endpoint);
	}

	/**
	 * @param elencoPagamentiOsapPermananente
	 * @param visureOsapPermanente
	 * @param secureSOAP11Endpoint
	 * @throws RemoteException
	 */
	public void setVisureOsapPermanente(List<DatiOsapPermanente> elencoPagamentiOsapPermananente, VisuraOsapPermanente[] visureOsapPermanente, VisuraOsapPermanenteServicePortType secureSOAP11Endpoint)
			throws RemoteException {
		if (visureOsapPermanente != null) {
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
				if (visuraOsapPermanente.getImportoDocumento() != null) {
					datiOsap.setImportoDocumento(visuraOsapPermanente.getImportoDocumento());
				}
				datiOsap.setNumeroDocumento(visuraOsapPermanente.getNumeroDocumento());

				// Posizioni
				PosizioneVisuraOsapPermanente[] posizioniVisuraOsapPermanente = secureSOAP11Endpoint.getPosizioniVisuraOsapPermanente(visuraOsapPermanente.getId().intValue());
				if (posizioniVisuraOsapPermanente != null) {

					List<PosizioniOsap> posizioniOsap = datiOsap.getPosizioniOsap();
					for (PosizioneVisuraOsapPermanente posizioneVisuraOsapPermanente : posizioniVisuraOsapPermanente) {
						PosizioniOsap posizioneOsap = new PosizioniOsap();

						posizioneOsap.setDescrizioneTariffa(posizioneVisuraOsapPermanente.getDescrizioneTariffa());
						if (posizioneVisuraOsapPermanente.getImportoDaPagare() != null) {
							posizioneOsap.setImportoDaPagare(posizioneVisuraOsapPermanente.getImportoDaPagare());
						}
						posizioneOsap.setIndirizzoUtenza(posizioneVisuraOsapPermanente.getIndirizzoUtenza());
						if (posizioneVisuraOsapPermanente.getMesi() != null) {
							posizioneOsap.setMesi(posizioneVisuraOsapPermanente.getMesi().intValue());
						}
						if (posizioneVisuraOsapPermanente.getSuperficie() != null) {
							posizioneOsap.setSuperficie(posizioneVisuraOsapPermanente.getSuperficie());
						}
						posizioneOsap.setZonaUtenza(posizioneVisuraOsapPermanente.getZonaUtenza());

						posizioniOsap.add(posizioneOsap);
					}
				}

				// Rate
				RataVisuraOsapPermanente[] rateVisuraOsapPermanente = secureSOAP11Endpoint.getRateVisuraOsapPermanente(visuraOsapPermanente.getId().intValue());
				if (rateVisuraOsapPermanente != null) {
					List<it.osapulie.tributi.web.ws.output.types.DatiOsapPermanente.Rate> rate = datiOsap.getRate();

					for (RataVisuraOsapPermanente rataVisuraOsapPermanente : rateVisuraOsapPermanente) {
						it.osapulie.tributi.web.ws.output.types.DatiOsapPermanente.Rate rata = new it.osapulie.tributi.web.ws.output.types.DatiOsapPermanente.Rate();

						if (rataVisuraOsapPermanente.getDataPagamento() != null) {
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(visuraOsapPermanente.getDataAggiornamento());
							rata.setDataPagamento(calendar);
						}

						rata.setIdentificativoRata(rataVisuraOsapPermanente.getIdentificativoRata());
						if (rataVisuraOsapPermanente.getImportoPagato() != null) {
							rata.setImportoPagato(rataVisuraOsapPermanente.getImportoPagato());
						}
						if (rataVisuraOsapPermanente.getImportoRata() != null) {
							rata.setImportoRata(rataVisuraOsapPermanente.getImportoRata());
						}
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
				elencoPagamentiOsapPermananente.add(datiOsap);
			}
		}
	}

	/**
	 *
	 * @param elencoPagamentiTassaPubblicita
	 * @param codiceFiscale
	 * @param annoInizio
	 * @param annoFine
	 * @throws MalformedURLException
	 * @throws ServiceException
	 * @throws RemoteException
	 */
	public void setVisureTassaPubblicita(List<PagamentiPubblicitaType> elencoPagamentiTassaPubblicita, String codiceFiscale, Integer annoInizio, Integer annoFine)
			throws MalformedURLException, ServiceException, RemoteException {

		URL wsdlLocation = new URL(webservicesBaseUrl + "/services/visuraImpostaInsegnePubblicitaService?wsdl");
		VisuraImpostaInsegnePubblicitaServiceLocator visuraImpostaInsegnePubblicitaLocator = new VisuraImpostaInsegnePubblicitaServiceLocator();
		VisuraImpostaInsegnePubblicitaServicePortType secureSOAP11Endpoint = visuraImpostaInsegnePubblicitaLocator.getSecureSOAP11Endpoint(wsdlLocation);
		VisuraImposteInsegnePubblicita[] visureImposteInsegnePubblicita = secureSOAP11Endpoint.getVisureImposteInsegnePubblicita(codiceFiscale, annoInizio, annoFine);
		setVisureTassaPubblicita(elencoPagamentiTassaPubblicita, visureImposteInsegnePubblicita, secureSOAP11Endpoint);
	}

	/**
	 * @param elencoPagamentiTassaPubblicita
	 * @param visureImposteInsegnePubblicita
	 * @param secureSOAP11Endpoint
	 * @throws RemoteException
	 */
	public void setVisureTassaPubblicita(List<PagamentiPubblicitaType> elencoPagamentiTassaPubblicita, VisuraImposteInsegnePubblicita[] visureImposteInsegnePubblicita,
			VisuraImpostaInsegnePubblicitaServicePortType secureSOAP11Endpoint) throws RemoteException {
		if (visureImposteInsegnePubblicita != null) {
			for (VisuraImposteInsegnePubblicita visuraImposteInsegnePubblicita : visureImposteInsegnePubblicita) {
				PagamentiPubblicitaType pagamentiPubblicitaType = new PagamentiPubblicitaType();
				if (visuraImposteInsegnePubblicita.getAnnoRiferimento() != null) {
					pagamentiPubblicitaType.setAnnoRiferimento(visuraImposteInsegnePubblicita.getAnnoRiferimento().intValue());
				}
				pagamentiPubblicitaType.setContoCorrente(visuraImposteInsegnePubblicita.getContoCorrente());
				if (visuraImposteInsegnePubblicita.getDataAggiornamento() != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(visuraImposteInsegnePubblicita.getDataAggiornamento());
					pagamentiPubblicitaType.setDataAggiornamento(calendar);
				}
				pagamentiPubblicitaType.setImportoDocumento(visuraImposteInsegnePubblicita.getImportoDocumento());
				pagamentiPubblicitaType.setNumeroDocumento(visuraImposteInsegnePubblicita.getNumeroDocumento());

				// Posizioni
				PosizioneVisuraImposteInsegnePubblicita[] posizioniVisuraServiziCimiteriali = secureSOAP11Endpoint
						.getPosizioniVisuraImposteInsegnePubblicita(visuraImposteInsegnePubblicita.getId().intValue());
				if (posizioniVisuraServiziCimiteriali != null) {
					List<PosizioniPubblicita> posizioniServiziCimiteriali = pagamentiPubblicitaType.getPosizioniPubblicita();
					for (PosizioneVisuraImposteInsegnePubblicita posizioneVisuraImposte : posizioniVisuraServiziCimiteriali) {

						PosizioniPubblicita posizioneVisura = new PosizioniPubblicita();
						posizioneVisura.setIndirizzoPubblicita(posizioneVisuraImposte.getIndirizzoPubblicita());
						posizioneVisura.setDescrizionePubblicita(posizioneVisuraImposte.getDescrizionePubblicita());
						if (posizioneVisuraImposte.getMesi() != null) {
							posizioneVisura.setMesi(posizioneVisuraImposte.getMesi().intValue());
						}
						if (posizioneVisuraImposte.getMq() != null) {
							posizioneVisura.setMq(posizioneVisuraImposte.getMq());
						}
						if (posizioneVisuraImposte.getImportoPubblicita() != null) {
							posizioneVisura.setImportoPubblicita(posizioneVisuraImposte.getImportoPubblicita());
						}

						posizioniServiziCimiteriali.add(posizioneVisura);

					}

				}
				// Rate
				RataVisuraImposteInsegnePubblicita[] rateVisuraImposteInsegnaPubblicita = secureSOAP11Endpoint.getRateVisuraImposteInsegnePubblicita(visuraImposteInsegnePubblicita.getId().intValue());
				if (rateVisuraImposteInsegnaPubblicita != null) {
					List<it.osapulie.tributi.web.ws.output.types.PagamentiPubblicitaType.Rate> rate = pagamentiPubblicitaType.getRate();
					for (RataVisuraImposteInsegnePubblicita rataVisuraImposteInsegnePubblicita : rateVisuraImposteInsegnaPubblicita) {
						it.osapulie.tributi.web.ws.output.types.PagamentiPubblicitaType.Rate rata = new it.osapulie.tributi.web.ws.output.types.PagamentiPubblicitaType.Rate();
						if (rataVisuraImposteInsegnePubblicita.getDataPagamento() != null) {
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(rataVisuraImposteInsegnePubblicita.getDataPagamento());
							rata.setDataPagamento(calendar);
						}
						rata.setIdentificativoRata(rataVisuraImposteInsegnePubblicita.getIdentificativoRata());
						if (rataVisuraImposteInsegnePubblicita.getImportoPagato() != null) {
							rata.setImportoPagato(rataVisuraImposteInsegnePubblicita.getImportoPagato());
						}
						if (rataVisuraImposteInsegnePubblicita.getImportoRata() != null) {
							rata.setImportoRata(rataVisuraImposteInsegnePubblicita.getImportoRata());
						}
						if (rataVisuraImposteInsegnePubblicita.getNumeroRata() != null) {
							rata.setNumeroRata(rataVisuraImposteInsegnePubblicita.getNumeroRata().intValue());
						}

						if (rataVisuraImposteInsegnePubblicita.getScadenzaRata() != null) {
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(rataVisuraImposteInsegnePubblicita.getScadenzaRata());
							rata.setScadenzaRata(calendar);
						}
						rate.add(rata);
					}
				}
				elencoPagamentiTassaPubblicita.add(pagamentiPubblicitaType);
			}
		}
	}

	/**
	 *
	 * @param elencoPagamentiTassaImmobili
	 * @param codiceFiscale
	 * @param annoInizio
	 * @param annoFine
	 * @throws MalformedURLException
	 * @throws ServiceException
	 * @throws RemoteException
	 */
	public void setVisureTassaImmobili(List<DatiTassaImmobili> elencoPagamentiTassaImmobili, String codiceFiscale, Integer annoInizio, Integer annoFine)
			throws MalformedURLException, ServiceException, RemoteException {

		URL wsdlLocation = new URL(webservicesBaseUrl + "/services/visuraTassaImmobiliService?wsdl");

		VisuraTassaImmobiliServiceLocator visuraDichiarazioneTassaImmobiliServiceLocator = new VisuraTassaImmobiliServiceLocator();
		VisuraTassaImmobiliServicePortType secureSOAP11Endpoint = visuraDichiarazioneTassaImmobiliServiceLocator.getSecureSOAP11Endpoint(wsdlLocation);
		VisuraTassaImmobili[] visureDichiarazioneTassaImmobili = secureSOAP11Endpoint.getVisureTassaImmobili(codiceFiscale, annoInizio, annoFine);

		setVisureTassaImmobili(elencoPagamentiTassaImmobili, visureDichiarazioneTassaImmobili, secureSOAP11Endpoint);
	}

	/**
	 * @param elencoPagamentiTassaImmobili
	 * @param visureDichiarazioneTassaImmobili
	 * @param secureSOAP11Endpoint
	 * @throws RemoteException
	 */
	public void setVisureTassaImmobili(List<DatiTassaImmobili> elencoPagamentiTassaImmobili, VisuraTassaImmobili[] visureDichiarazioneTassaImmobili,
			VisuraTassaImmobiliServicePortType secureSOAP11Endpoint) throws RemoteException {
		if (visureDichiarazioneTassaImmobili != null) {
			VisuraTassaImmobili visuraDichiarazioneTassaImmobili2 = visureDichiarazioneTassaImmobili[0];

			DatiTassaImmobili situazione = new DatiTassaImmobili();
			if (visuraDichiarazioneTassaImmobili2.getAnnoRiferimento() != null) {
				situazione.setAnnoRiferimento(visuraDichiarazioneTassaImmobili2.getAnnoRiferimento().intValue());
			}
			situazione.setContoCorrente(visuraDichiarazioneTassaImmobili2.getContoCorrente());

			if (visuraDichiarazioneTassaImmobili2.getDataAggiornamento() != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(visuraDichiarazioneTassaImmobili2.getDataAggiornamento());
				situazione.setDataAggiornamento(calendar);
			}

			situazione.setDescrizioneTassa(visuraDichiarazioneTassaImmobili2.getDescrizioneTassa());
			situazione.setImportoDocumento(visuraDichiarazioneTassaImmobili2.getImportoDocumento());

			List<Posizioni> posizioni = situazione.getPosizioni();

			// Posizioni
			PosizioneVisuraTassaImmobili[] posizioniVisuraTassaImmobili = secureSOAP11Endpoint.getPosizioniVisuraTassaImmobili(visuraDichiarazioneTassaImmobili2.getId().intValue());

			if (posizioniVisuraTassaImmobili != null) {

				for (PosizioneVisuraTassaImmobili posizioneVisuraDichiarazioneTassaImmobili : posizioniVisuraTassaImmobili) {

					Posizioni posizione = new Posizioni();

					posizione.setAbitazionePrincipale(posizioneVisuraDichiarazioneTassaImmobili.getAbitazionePrincipale());
					posizione.setAliquota(posizioneVisuraDichiarazioneTassaImmobili.getAliquota());
					posizione.setCaratteristicaImmobile(posizioneVisuraDichiarazioneTassaImmobili.getCaratteristicaImmobile());
					Codifica categoriaImmobileCodifica = new Codifica(posizioneVisuraDichiarazioneTassaImmobili.getCategoriaImmobile(),
							posizioneVisuraDichiarazioneTassaImmobili.getCategoriaImmobile());
					posizione.setCategoriaImmobile(categoriaImmobileCodifica);

					if (posizioneVisuraDichiarazioneTassaImmobili.getFoglio() != null) {
						posizione.setFoglio(posizioneVisuraDichiarazioneTassaImmobili.getFoglio().intValue());
					}
					if (posizioneVisuraDichiarazioneTassaImmobili.getNumero() != null) {
						posizione.setNumero(posizioneVisuraDichiarazioneTassaImmobili.getNumero().intValue());
					}
					posizione.setSezione(posizioneVisuraDichiarazioneTassaImmobili.getSezione());
					if (posizioneVisuraDichiarazioneTassaImmobili.getParticella() != null) {
						posizione.setParticella(posizioneVisuraDichiarazioneTassaImmobili.getParticella().intValue());
					}
					if (posizioneVisuraDichiarazioneTassaImmobili.getSubalterno() != null) {
						posizione.setSubalterno(posizioneVisuraDichiarazioneTassaImmobili.getSubalterno().intValue());
					}

					if (posizioneVisuraDichiarazioneTassaImmobili.getImportoDetrazioneAbPrincipale() != null) {
						posizione.setImportoDetrazioneAbPrincipale(posizioneVisuraDichiarazioneTassaImmobili.getImportoDetrazioneAbPrincipale());
					}
					if (posizioneVisuraDichiarazioneTassaImmobili.getImportoDovuto() != null) {
						posizione.setImportoDovuto(posizioneVisuraDichiarazioneTassaImmobili.getImportoDovuto());
					}

					Indirizzo indirizzoUtenza = new Indirizzo();
					String codiceVia = posizioneVisuraDichiarazioneTassaImmobili.getCodiceVia();
					String viaString = posizioneVisuraDichiarazioneTassaImmobili.getVia();
					String codiceCivico = posizioneVisuraDichiarazioneTassaImmobili.getCodiceCivico();
					BigInteger civicoBigInteger = posizioneVisuraDichiarazioneTassaImmobili.getCivico();
					String esponente = posizioneVisuraDichiarazioneTassaImmobili.getEsponente();

					Codifica via = new Codifica();
					via.setCodice(codiceVia);
					via.setDescrizione(viaString);

					indirizzoUtenza.setVia(via);

					Civico civico = new Civico();
					civico.setCodice(codiceCivico);
					civico.setEsponente(esponente);
					if (civicoBigInteger != null) {
						civico.setNumero(civicoBigInteger.intValue());
					}

					indirizzoUtenza.setCivico(civico);
					indirizzoUtenza.setCap(posizioneVisuraDichiarazioneTassaImmobili.getCap());
					indirizzoUtenza.setLocalita(posizioneVisuraDichiarazioneTassaImmobili.getLocalita());
					indirizzoUtenza.setPiano(posizioneVisuraDichiarazioneTassaImmobili.getPiano());
					indirizzoUtenza.setInterno(posizioneVisuraDichiarazioneTassaImmobili.getInterno());
					indirizzoUtenza.setScala(posizioneVisuraDichiarazioneTassaImmobili.getScala());
					indirizzoUtenza.setSuffisso(posizioneVisuraDichiarazioneTassaImmobili.getSuffisso());

					posizione.setIndirizzoUtenza(indirizzoUtenza);

					if (posizioneVisuraDichiarazioneTassaImmobili.getMesiDetrazione() != null) {
						posizione.setMesiDetrazione(posizioneVisuraDichiarazioneTassaImmobili.getMesiDetrazione().intValue());
					}
					if (posizioneVisuraDichiarazioneTassaImmobili.getMesiPossesso() != null) {
						posizione.setMesiPossesso(posizioneVisuraDichiarazioneTassaImmobili.getMesiPossesso().intValue());
					}
					if (posizioneVisuraDichiarazioneTassaImmobili.getPercentualePossesso() != null) {
						posizione.setPercentualePossesso(posizioneVisuraDichiarazioneTassaImmobili.getPercentualePossesso());
					}
					if (posizioneVisuraDichiarazioneTassaImmobili.getPosseduto3112() != null) {
						posizione.setPosseduto3112(posizioneVisuraDichiarazioneTassaImmobili.getPosseduto3112());
					}
					if (posizioneVisuraDichiarazioneTassaImmobili.getRiduzione() != null) {
						posizione.setRiduzione(posizioneVisuraDichiarazioneTassaImmobili.getRiduzione());
					}
					posizione.setSezione(posizioneVisuraDichiarazioneTassaImmobili.getSezione());
					if (posizioneVisuraDichiarazioneTassaImmobili.getValoreImmobile() != null) {
						posizione.setValoreImmobile(posizioneVisuraDichiarazioneTassaImmobili.getValoreImmobile());
					}

					posizione.setTipo(posizioneVisuraDichiarazioneTassaImmobili.getTipo());

					posizioni.add(posizione);

				}
			}

			// Rate
			RataVisuraTassaImmobili[] rateVisuraTassaImmobili = secureSOAP11Endpoint.getRateVisuraTassaImmobili(visuraDichiarazioneTassaImmobili2.getId().intValue());

			if (rateVisuraTassaImmobili != null) {

				List<it.osapulie.tributi.web.ws.output.types.DatiTassaImmobili.Rate> rate = situazione.getRate();

				for (RataVisuraTassaImmobili rataVisuraDichiarazioneTassaImmobili : rateVisuraTassaImmobili) {

					it.osapulie.tributi.web.ws.output.types.DatiTassaImmobili.Rate rata = new it.osapulie.tributi.web.ws.output.types.DatiTassaImmobili.Rate();

					if (rataVisuraDichiarazioneTassaImmobili.getDataPagamento() != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(rataVisuraDichiarazioneTassaImmobili.getDataPagamento());
						rata.setDataPagamento(calendar);
					}

					if (rataVisuraDichiarazioneTassaImmobili.getImportoAbitazionePrincipale() != null) {
						rata.setImportoAbitazionePrincipale(rataVisuraDichiarazioneTassaImmobili.getImportoAbitazionePrincipale());
					}
					if (rataVisuraDichiarazioneTassaImmobili.getImportoAltriFabbricati() != null) {
						rata.setImportoAltriFabbricati(rataVisuraDichiarazioneTassaImmobili.getImportoAltriFabbricati());
					}
					if (rataVisuraDichiarazioneTassaImmobili.getImportoAreaFabbricabile() != null) {
						rata.setImportoAreaFabbricabile(rataVisuraDichiarazioneTassaImmobili.getImportoAreaFabbricabile());
					}
					if (rataVisuraDichiarazioneTassaImmobili.getImportoDaPagare() != null) {
						rata.setImportoDaPagare(rataVisuraDichiarazioneTassaImmobili.getImportoDaPagare());
					}
					if (rataVisuraDichiarazioneTassaImmobili.getImportoPagato() != null) {
						rata.setImportoPagato(rataVisuraDichiarazioneTassaImmobili.getImportoPagato());
					}
					if (rataVisuraDichiarazioneTassaImmobili.getImportoTerreniAgricoli() != null) {
						rata.setImportoTerreniAgricoli(rataVisuraDichiarazioneTassaImmobili.getImportoTerreniAgricoli());
					}
					if (rataVisuraDichiarazioneTassaImmobili.getNumeroFabbricati() != null) {
						rata.setNumeroFabbricati(rataVisuraDichiarazioneTassaImmobili.getNumeroFabbricati().intValue());
					}

					rata.setTipoRata(rataVisuraDichiarazioneTassaImmobili.getTipoRata());

					rate.add(rata);
				}
			}
			elencoPagamentiTassaImmobili.add(situazione);
		}
	}

	/**
	 * @param elencoPagamentiCimiteriali
	 * @param codiceFiscale
	 * @param annoInizio
	 * @param annoFine
	 * @throws MalformedURLException
	 * @throws ServiceException
	 * @throws RemoteException
	 */
	public void setVisureServiziCimiteriali(List<PagamentiServiziCimiterialiType> elencoPagamentiCimiteriali, String codiceFiscale, Integer annoInizio, Integer annoFine)
			throws MalformedURLException, ServiceException, RemoteException {

		URL wsdlLocation = new URL(webservicesBaseUrl + "/services/visuraServiziCimiterialiService?wsdl");
		VisuraServiziCimiterialiServiceLocator visuraServiziCimiterialiServiceLocator = new VisuraServiziCimiterialiServiceLocator();
		VisuraServiziCimiterialiServicePortType secureSOAP11Endpoint = visuraServiziCimiterialiServiceLocator.getSecureSOAP11Endpoint(wsdlLocation);
		VisuraServiziCimiteriali[] visureServiziCimiteriali = secureSOAP11Endpoint.getVisureServiziCimiteriali(codiceFiscale, annoInizio, annoFine);

		setVisureServiziCimiteriali(elencoPagamentiCimiteriali, visureServiziCimiteriali, secureSOAP11Endpoint);
	}

	/**
	 * @param elencoPagamentiCimiteriali
	 * @param visureServiziCimiteriali
	 * @param secureSOAP11Endpoint
	 * @throws RemoteException
	 */
	public void setVisureServiziCimiteriali(List<PagamentiServiziCimiterialiType> elencoPagamentiCimiteriali, VisuraServiziCimiteriali[] visureServiziCimiteriali,
			VisuraServiziCimiterialiServicePortType secureSOAP11Endpoint) throws RemoteException {
		if (visureServiziCimiteriali != null) {
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
						if (rataVisuraServiziCimiteriali.getImportoPagato() != null) {
							rata.setImportoPagato(rataVisuraServiziCimiteriali.getImportoPagato());
						}
						if (rataVisuraServiziCimiteriali.getImportoRata() != null) {
							rata.setImportoRata(rataVisuraServiziCimiteriali.getImportoRata());
						}
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

	/**
	 * @param elencoPagamentiTassaAffissioni
	 * @param codiceFiscale
	 * @param annoInizio
	 * @param annoFine
	 * @throws MalformedURLException
	 * @throws ServiceException
	 * @throws RemoteException
	 */
	public void setVisurePubblicheAffissioni(List<PagamentiAffissioniType> elencoPagamentiTassaAffissioni, String codiceFiscale, Integer annoInizio, Integer annoFine)
			throws MalformedURLException, ServiceException, RemoteException {
		URL wsdlLocation = new URL(webservicesBaseUrl + "/services/visuraPubblicheAffissioniService?wsdl");
		VisuraPubblicheAffissioniServiceLocator visuraPubblicheAffissioniServiceLocator = new VisuraPubblicheAffissioniServiceLocator();
		VisuraPubblicheAffissioniServicePortType secureSOAP11Endpoint = visuraPubblicheAffissioniServiceLocator.getSecureSOAP11Endpoint(wsdlLocation);
		VisuraPubblicheAffissioni[] visurePubblicheAffissioni = secureSOAP11Endpoint.getVisurePubblicheAffissioni(codiceFiscale, annoInizio, annoFine);

		setVisurePubblicheAffissioni(elencoPagamentiTassaAffissioni, visurePubblicheAffissioni, secureSOAP11Endpoint);
	}

	/**
	 * @param elencoPagamentiTassaAffissioni
	 * @param visurePubblicheAffissioni
	 * @param secureSOAP11Endpoint
	 * @throws RemoteException
	 */
	public void setVisurePubblicheAffissioni(List<PagamentiAffissioniType> elencoPagamentiTassaAffissioni, VisuraPubblicheAffissioni[] visurePubblicheAffissioni,
			VisuraPubblicheAffissioniServicePortType secureSOAP11Endpoint) throws RemoteException {
		if (visurePubblicheAffissioni != null) {
			for (VisuraPubblicheAffissioni visuraPubblicheAffissioni : visurePubblicheAffissioni) {

				PagamentiAffissioniType pagamentiAffissioniType = new PagamentiAffissioniType();

				if (visuraPubblicheAffissioni.getAnnoRiferimento() != null) {
					pagamentiAffissioniType.setAnnoRiferimento(visuraPubblicheAffissioni.getAnnoRiferimento().intValue());
				}
				pagamentiAffissioniType.setContoCorrente(visuraPubblicheAffissioni.getContoCorrente());
				if (visuraPubblicheAffissioni.getDataAggiornamento() != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(visuraPubblicheAffissioni.getDataAggiornamento());
					pagamentiAffissioniType.setDataAggiornamento(calendar);
				}
				pagamentiAffissioniType.setImportoDocumento(visuraPubblicheAffissioni.getImportoDocumento());
				pagamentiAffissioniType.setNumeroDocumento(visuraPubblicheAffissioni.getNumeroDocumento());

				// Posizioni
				PosizioneVisuraPubblicheAffissioni[] posizioniVisuraPubblicheAffissioni = secureSOAP11Endpoint.getPosizioniVisuraPubblicheAffissioni(visuraPubblicheAffissioni.getId().intValue());

				if (posizioniVisuraPubblicheAffissioni != null) {
					List<PosizioniAffissione> posizioniAffissione = pagamentiAffissioniType.getPosizioniAffissione();
					for (PosizioneVisuraPubblicheAffissioni posizioneVisuraPubblicheAffissioni : posizioniVisuraPubblicheAffissioni) {
						PosizioniAffissione posizioneAffissione = new PosizioniAffissione();

						posizioneAffissione.setDimensioneManifesti(posizioneVisuraPubblicheAffissioni.getDimensioneManifesti());
						if (posizioneVisuraPubblicheAffissioni.getGiorniAffissione() != null) {
							posizioneAffissione.setGiorniAffissione(posizioneVisuraPubblicheAffissioni.getGiorniAffissione().intValue());
						}
						if (posizioneVisuraPubblicheAffissioni.getNumeroFogli() != null) {
							posizioneAffissione.setNumeroFogli(posizioneVisuraPubblicheAffissioni.getNumeroFogli().intValue());
						}
						if (posizioneVisuraPubblicheAffissioni.getNumeroManifesti() != null) {
							posizioneAffissione.setNumeroManifesti(posizioneVisuraPubblicheAffissioni.getNumeroManifesti().intValue());
						}
						posizioneAffissione.setTariffa(posizioneVisuraPubblicheAffissioni.getTariffa());

						posizioniAffissione.add(posizioneAffissione);
					}
				}
				// Rate
				RataVisuraPubblicheAffissioni[] rateVisuraPubblicheAffissioni = secureSOAP11Endpoint.getRateVisuraPubblicheAffissioni(visuraPubblicheAffissioni.getId().intValue());
				if (rateVisuraPubblicheAffissioni != null) {
					List<it.osapulie.tributi.web.ws.output.types.PagamentiAffissioniType.Rate> rate = pagamentiAffissioniType.getRate();
					for (RataVisuraPubblicheAffissioni rataVisuraPubblicheAffissioni : rateVisuraPubblicheAffissioni) {
						it.osapulie.tributi.web.ws.output.types.PagamentiAffissioniType.Rate rata = new it.osapulie.tributi.web.ws.output.types.PagamentiAffissioniType.Rate();

						if (rataVisuraPubblicheAffissioni.getDataPagamento() != null) {
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(rataVisuraPubblicheAffissioni.getDataPagamento());
							rata.setDataPagamento(calendar);
						}
						rata.setIdentificativoRata(rataVisuraPubblicheAffissioni.getIdentificativoRata());
						rata.setImportoPagato(rataVisuraPubblicheAffissioni.getImportoPagato());
						rata.setImportoRata(rataVisuraPubblicheAffissioni.getImportoRata());
						if (rataVisuraPubblicheAffissioni.getNumeroRata() != null) {
							rata.setNumeroRata(rataVisuraPubblicheAffissioni.getNumeroRata().intValue());
						}
						if (rataVisuraPubblicheAffissioni.getScadenzaRata() != null) {
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(rataVisuraPubblicheAffissioni.getScadenzaRata());
							rata.setScadenzaRata(calendar);
						}
						rate.add(rata);
					}
				}
				elencoPagamentiTassaAffissioni.add(pagamentiAffissioniType);
			}
		}
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
