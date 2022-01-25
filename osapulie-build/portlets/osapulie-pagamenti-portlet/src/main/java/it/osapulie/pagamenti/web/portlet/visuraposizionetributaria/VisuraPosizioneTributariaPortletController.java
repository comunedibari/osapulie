package it.osapulie.pagamenti.web.portlet.visuraposizionetributaria;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.dto.Fascicolo;
import it.osapulie.infrastructure.impl.DateUtils;
import it.osapulie.pagamenti.service.PosizioneTributariaService;
import it.osapulie.pagamenti.utils.PortletConstants;
import it.osapulie.pagamenti.utils.PortletUtils;
import it.osapulie.service.FascicoloUtenteService;
import it.osapulie.shared.service.Segnalazione;
import it.osapulie.shared.service.SegnalazioneFoglia;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.tributi.web.ws.output.types.Codifica;
import it.osapulie.tributi.web.ws.output.types.DatiOsapPermanente;
import it.osapulie.tributi.web.ws.output.types.DatiOsapPermanente.PosizioniOsap;
import it.osapulie.tributi.web.ws.output.types.DatiOsapPermanente.Rate;
import it.osapulie.tributi.web.ws.output.types.DatiOsapTemporanea;
import it.osapulie.tributi.web.ws.output.types.DatiTassaImmobili;
import it.osapulie.tributi.web.ws.output.types.DatiTassaRifiuti;
import it.osapulie.tributi.web.ws.output.types.DatiTassaRifiuti.Posizioni;
import it.osapulie.tributi.web.ws.output.types.PagamentiAffissioniType;
import it.osapulie.tributi.web.ws.output.types.PagamentiAffissioniType.PosizioniAffissione;
import it.osapulie.tributi.web.ws.output.types.PagamentiPubblicitaType;
import it.osapulie.tributi.web.ws.output.types.PagamentiPubblicitaType.PosizioniPubblicita;
import it.osapulie.tributi.web.ws.output.types.PagamentiServiziCimiterialiType;
import it.osapulie.tributi.web.ws.output.types.PagamentiServiziCimiterialiType.PosizioniServiziCimiteriali;
import it.osapulie.tributi.web.ws.output.types.PagamentiServiziCimiterialiType.PosizioniServiziCimiteriali.Defunti;
import it.osapulie.tributi.web.ws.output.types.PagamentiServiziCimiterialiType.PosizioniServiziCimiteriali.LampadaVotiva;
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaRichiesta;
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaRichiesta.ArcoTemporale;
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaRichiesta.TipoTributo;
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaRisposta;
import it.osapulie.web.portlet.controller.BaseController;
import it.osapulie.web.portlet.util.PortletHelper;

/**
 * Portlet controller per la visura posizione tributaria.
 *
 * @author Giovanni Barone
 */
@Controller("visuraPosizioneTributariaPortletController")
@RequestMapping("view")
@SessionAttributes("dati")
public class VisuraPosizioneTributariaPortletController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(VisuraPosizioneTributariaPortletController.class);

	@Inject
	private PosizioneTributariaService service;

	@Inject
	private PortletHelper helper;

	@Inject
	private FascicoloUtenteService fascicoloService;

	@Autowired
	public ResourceBundleMessageSource messageSource;

	@Value("#{applicationProperties.segnalazioniPageUrl}")
	private String segnalazioniPageUrl;

	/**
	 * Default entry quando la portlet viene visualizzata per la prima volta.
	 *
	 * @param model il view model da popolare
	 * @return il view ID da renderizzare .
	 */
	@RenderMapping
	public String homePage(Model model, PortletSession session) {
		model.addAttribute("annoCorrente", DateUtils.getAnnoCorrente());
		return toLocalViewPath("home");
	}

	@ActionMapping(params = "action=visura")
	public void visuraPosizioneTributaria(@RequestParam("startYear") String yearStart, @RequestParam("endYear") String yearEnd, @RequestParam(required = false, value = "invia") String invia,
			Model model, @ModelAttribute("dati") VisuraPosizioneTributariaRisposta dati, ActionResponse response, PortletRequest portletRequest) throws Exception {
		try {

			if (invia != null) {
				UserPreferences userPreferences = helper.getUserPreferences(portletRequest);
				String codiceFiscale = userPreferences.getCodiceFiscaleServizio();

				VisuraPosizioneTributariaRichiesta richiesta = new VisuraPosizioneTributariaRichiesta();
				TipoTributo tipoTributo = new TipoTributo();
				tipoTributo.setALL("ALL");
				richiesta.setTipoTributo(tipoTributo);
				if (yearStart != null && yearEnd != null && !yearStart.equals("") && !yearEnd.equals("")) {

					// memorizzo il campo
					model.addAttribute("yearStart", yearStart);
					model.addAttribute("yearEnd", yearEnd);

					ArcoTemporale arcoTemporale = new ArcoTemporale();
					arcoTemporale.setAnnoInizio(Integer.valueOf(yearStart));
					arcoTemporale.setAnnoFine(Integer.valueOf(yearEnd));
					richiesta.setArcoTemporale(arcoTemporale);
				}

				// Controllo profilo cittadino/azienda
				String partitaIvaServizio = userPreferences.getPartitaIvaServizio();
				if (partitaIvaServizio != null) {
					richiesta.setPartitaIva(partitaIvaServizio);
					model.addAttribute("partitaIvaServizio", partitaIvaServizio);
				}
				else {
					richiesta.setCodiceFiscale(codiceFiscale);
				}

				dati = new VisuraPosizioneTributariaRisposta();

				try {
					dati = service.richiediDatiVisuraPosizioneTributaria(richiesta, userPreferences);
				}
				catch (Exception e) {
					log.error("visuraPosizioneTributaria :: " + e.getMessage(), e);
					model.addAttribute("formError", messageSource.getMessage("exception.contactAdmin", null, portletRequest.getLocale()));
					model.addAttribute("dati", dati);
					return;
				}

				model.addAttribute("dati", dati);

				// Scrittura su fascicolo utente
				ProfiloUtenteCittadino profiloUtente = helper.getOsApulieUserDetails().getProfiloUtenteCittadino();

				Fascicolo fascicolo = new Fascicolo();
				fascicolo.setIdProfiloUtente(profiloUtente);
				fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio", null, portletRequest.getLocale()));
				fascicolo.setUserPreferences(helper.getUserPreferences(portletRequest));
				fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_VISURA_POSIZIONE_TRIBUTARIA);
				fascicolo.setRicercabileDaComune(false);
				fascicolo.setNumeroProtocollo(null);
				fascicolo.setChecksum(null);
				fascicoloService.saveNuovaRichiesta(fascicolo);
			}
			else {
				List<SegnalazioneFoglia> listaSegnalazioni = inviaSegnalazioneAction(dati, portletRequest);
				Segnalazione segnalazione = new Segnalazione();
				segnalazione.setListaSegnalazioni(listaSegnalazioni);
				segnalazione.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio", null, portletRequest.getLocale()));
				segnalazione.setServizioCode(PortletConstants.CODICE_SERVIZIO_VISURA_POSIZIONE_TRIBUTARIA);

				PortletSession session = portletRequest.getPortletSession();
				session.setAttribute(PortletConstants.SEGNALAZIONE_SHARED_ATTRIBUTE, segnalazione, PortletSession.APPLICATION_SCOPE);
			}

		}
		catch (Exception e) {
			log.error("Action :: visuraPosizioneTributaria : " + e.getMessage(), e);
		}
		if (invia != null) {
			response.setRenderParameter("action", "renderVisura");
		}
		else {
			response.sendRedirect(segnalazioniPageUrl);
		}
	}

	private List<SegnalazioneFoglia> inviaSegnalazioneAction(VisuraPosizioneTributariaRisposta datiModel, PortletRequest portletRequest) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		List<SegnalazioneFoglia> listaSegnalazioni = new ArrayList<SegnalazioneFoglia>();
		if (datiModel != null) {
			if (datiModel != null && datiModel.getElencoPagamentiOsapPermananente() != null && !datiModel.getElencoPagamentiOsapPermananente().isEmpty()) {
				for (DatiOsapPermanente osap : datiModel.getElencoPagamentiOsapPermananente()) {
					SegnalazioneFoglia nodo = new SegnalazioneFoglia(messageSource.getMessage("label.titoloPermanente", null, portletRequest.getLocale()));
					nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.numeroConto", null, portletRequest.getLocale()), osap.getContoCorrente()));
					nodo.getListaSegnalazioni()
							.add(new SegnalazioneFoglia(messageSource.getMessage("label.annoRiferimento", null, portletRequest.getLocale()), String.valueOf(osap.getAnnoRiferimento())));
					nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.numeroDocumento", null, portletRequest.getLocale()), osap.getNumeroDocumento()));
					nodo.getListaSegnalazioni()
							.add(new SegnalazioneFoglia(messageSource.getMessage("label.importoDocumento", null, portletRequest.getLocale()), String.valueOf(osap.getImportoDocumento())));
					nodo.getListaSegnalazioni()
							.add(new SegnalazioneFoglia(messageSource.getMessage("label.dataAggiornamento", null, portletRequest.getLocale()), sdf.format(osap.getDataAggiornamento().getTime())));

					if (osap.getPosizioniOsap() != null && !osap.getPosizioniOsap().isEmpty()) {
						for (PosizioniOsap posizione : osap.getPosizioniOsap()) {

							SegnalazioneFoglia nodoInterno = new SegnalazioneFoglia("");
							nodoInterno.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.zonaUtenza", null, portletRequest.getLocale()), posizione.getZonaUtenza()));
							nodoInterno.getListaSegnalazioni()
									.add(new SegnalazioneFoglia(messageSource.getMessage("label.indirizzoUtenza", null, portletRequest.getLocale()), posizione.getIndirizzoUtenza()));
							nodoInterno.getListaSegnalazioni()
									.add(new SegnalazioneFoglia(messageSource.getMessage("label.superficie", null, portletRequest.getLocale()), String.valueOf(posizione.getSuperficie())));
							nodoInterno.getListaSegnalazioni()
									.add(new SegnalazioneFoglia(messageSource.getMessage("label.mesi", null, portletRequest.getLocale()), String.valueOf(posizione.getMesi())));
							nodoInterno.getListaSegnalazioni()
									.add(new SegnalazioneFoglia(messageSource.getMessage("label.descrizioneTariffa", null, portletRequest.getLocale()), posizione.getDescrizioneTariffa()));
							nodoInterno.getListaSegnalazioni()
									.add(new SegnalazioneFoglia(messageSource.getMessage("label.importoDaPagare", null, portletRequest.getLocale()), String.valueOf(posizione.getImportoDaPagare())));

							nodo.getListaSegnalazioni().add(nodoInterno);
						}

						for (Rate rata : osap.getRate()) {

							SegnalazioneFoglia nodoInterno = new SegnalazioneFoglia("");
							nodoInterno.getListaSegnalazioni()
									.add(new SegnalazioneFoglia(messageSource.getMessage("label.identificativoRata", null, portletRequest.getLocale()), rata.getIdentificativoRata()));
							nodoInterno.getListaSegnalazioni()
									.add(new SegnalazioneFoglia(messageSource.getMessage("label.numeroRata", null, portletRequest.getLocale()), String.valueOf(rata.getNumeroRata())));
							nodoInterno.getListaSegnalazioni()
									.add(new SegnalazioneFoglia(messageSource.getMessage("label.importoRata", null, portletRequest.getLocale()), String.valueOf(rata.getImportoRata())));
							nodoInterno.getListaSegnalazioni()
									.add(new SegnalazioneFoglia(messageSource.getMessage("label.scadenzaRata", null, portletRequest.getLocale()), sdf.format(rata.getScadenzaRata().getTime())));
							nodoInterno.getListaSegnalazioni()
									.add(new SegnalazioneFoglia(messageSource.getMessage("label.dataPagamento", null, portletRequest.getLocale()), sdf.format(rata.getDataPagamento().getTime())));
							nodoInterno.getListaSegnalazioni()
									.add(new SegnalazioneFoglia(messageSource.getMessage("label.importoPagato", null, portletRequest.getLocale()), String.valueOf(rata.getImportoPagato())));

							nodo.getListaSegnalazioni().add(nodoInterno);
						}
					}

					listaSegnalazioni.add(nodo);
				}
			}

			if (datiModel != null && datiModel.getElencoPagamentiOsapTemporanea() != null && !datiModel.getElencoPagamentiOsapTemporanea().isEmpty()) {
				for (DatiOsapTemporanea osapTmp : datiModel.getElencoPagamentiOsapTemporanea()) {
					SegnalazioneFoglia nodo = new SegnalazioneFoglia(messageSource.getMessage("label.titoloTemporanea", null, portletRequest.getLocale()));
					nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.numeroConto", null, portletRequest.getLocale()), osapTmp.getContoCorrente()));
					nodo.getListaSegnalazioni()
							.add(new SegnalazioneFoglia(messageSource.getMessage("label.annoRiferimento", null, portletRequest.getLocale()), String.valueOf(osapTmp.getAnnoRiferimento())));
					nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.numeroDocumento", null, portletRequest.getLocale()), osapTmp.getNumeroDocumento()));
					nodo.getListaSegnalazioni()
							.add(new SegnalazioneFoglia(messageSource.getMessage("label.importoDocumento", null, portletRequest.getLocale()), String.valueOf(osapTmp.getImportoDocumento())));
					nodo.getListaSegnalazioni()
							.add(new SegnalazioneFoglia(messageSource.getMessage("label.dataAggiornamento", null, portletRequest.getLocale()), sdf.format(osapTmp.getDataAggiornamento().getTime())));

					if (osapTmp.getPosizioniOsap() != null && !osapTmp.getPosizioniOsap().isEmpty()) {
						for (it.osapulie.tributi.web.ws.output.types.DatiOsapTemporanea.PosizioniOsap posizione : osapTmp.getPosizioniOsap()) {

							SegnalazioneFoglia nodoInterno = new SegnalazioneFoglia("");
							nodoInterno.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.zonaUtenza", null, portletRequest.getLocale()), posizione.getZona()));
							nodoInterno.getListaSegnalazioni()
									.add(new SegnalazioneFoglia(messageSource.getMessage("label.indirizzoUtenza", null, portletRequest.getLocale()), posizione.getIndirizzoUtenza()));
							nodoInterno.getListaSegnalazioni()
									.add(new SegnalazioneFoglia(messageSource.getMessage("label.superficie", null, portletRequest.getLocale()), String.valueOf(posizione.getSuperficie())));
							nodoInterno.getListaSegnalazioni().add(
									new SegnalazioneFoglia(messageSource.getMessage("label.durataOccupazione", null, portletRequest.getLocale()), String.valueOf(posizione.getDurataOccupazione())));
							nodoInterno.getListaSegnalazioni()
									.add(new SegnalazioneFoglia(messageSource.getMessage("label.descrizioneTariffa", null, portletRequest.getLocale()), posizione.getDescrizioneTariffa()));
							nodoInterno.getListaSegnalazioni()
									.add(new SegnalazioneFoglia(messageSource.getMessage("label.importoDaPagare", null, portletRequest.getLocale()), String.valueOf(posizione.getImportoDaPagare())));
							nodoInterno.getListaSegnalazioni()
									.add(new SegnalazioneFoglia(messageSource.getMessage("label.importoPagato", null, portletRequest.getLocale()), String.valueOf(posizione.getImportoPagato())));
							nodoInterno.getListaSegnalazioni()
									.add(new SegnalazioneFoglia(messageSource.getMessage("label.dataPagamento", null, portletRequest.getLocale()), sdf.format(posizione.getDataPagamento().getTime())));

							nodo.getListaSegnalazioni().add(nodoInterno);
						}
					}

					listaSegnalazioni.add(nodo);
				}
			}

			if (datiModel != null && datiModel.getElencoPagamentiTassaCimiteriali() != null && !datiModel.getElencoPagamentiTassaCimiteriali().isEmpty()) {
				for (PagamentiServiziCimiterialiType pagamento : datiModel.getElencoPagamentiTassaCimiteriali()) {
					SegnalazioneFoglia nodo = new SegnalazioneFoglia(messageSource.getMessage("label.titoloServiciCimiteriali", null, portletRequest.getLocale()));
					nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.numeroConto", null, portletRequest.getLocale()), pagamento.getContoCorrente()));
					nodo.getListaSegnalazioni()
							.add(new SegnalazioneFoglia(messageSource.getMessage("label.annoRiferimento", null, portletRequest.getLocale()), String.valueOf(pagamento.getAnnoRiferimento())));
					nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.numeroDocumento", null, portletRequest.getLocale()), pagamento.getNumeroDocumento()));
					nodo.getListaSegnalazioni()
							.add(new SegnalazioneFoglia(messageSource.getMessage("label.importoDocumento", null, portletRequest.getLocale()), String.valueOf(pagamento.getImportoDocumento())));
					nodo.getListaSegnalazioni()
							.add(new SegnalazioneFoglia(messageSource.getMessage("label.dataAggiornamento", null, portletRequest.getLocale()), sdf.format(pagamento.getDataAggiornamento().getTime())));

					for (PosizioniServiziCimiteriali posizione : pagamento.getPosizioniServiziCimiteriali()) {
						SegnalazioneFoglia nodoPosizione = new SegnalazioneFoglia("");
						nodoPosizione.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.nomeCimitero", null, portletRequest.getLocale()), posizione.getNomecimitero()));
						nodoPosizione.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.posizione", null, portletRequest.getLocale()), posizione.getPosizione()));

						for (Defunti defunto : posizione.getDefunti()) {
							SegnalazioneFoglia nodoDefunto = new SegnalazioneFoglia("");
							nodoDefunto.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.nomeDefunto", null, portletRequest.getLocale()), defunto.getNomeDefunto()));
							nodoDefunto.getListaSegnalazioni()
									.add(new SegnalazioneFoglia(messageSource.getMessage("label.dataNascita", null, portletRequest.getLocale()), sdf.format(defunto.getDataNascita().getTime())));
							nodoDefunto.getListaSegnalazioni()
									.add(new SegnalazioneFoglia(messageSource.getMessage("label.dataMorte", null, portletRequest.getLocale()), sdf.format(defunto.getDataMorte().getTime())));

							nodoPosizione.getListaSegnalazioni().add(nodoDefunto);
						}

						for (LampadaVotiva lampada : posizione.getLampadaVotiva()) {
							SegnalazioneFoglia nodoLampada = new SegnalazioneFoglia("");
							nodoLampada.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.mesi", null, portletRequest.getLocale()), String.valueOf(lampada.getMesi())));
							nodoLampada.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.tariffa", null, portletRequest.getLocale()), lampada.getTariffa()));
							nodoLampada.getListaSegnalazioni()
									.add(new SegnalazioneFoglia(messageSource.getMessage("label.importoLampada", null, portletRequest.getLocale()), String.valueOf(lampada.getImportoLampada())));

							nodoPosizione.getListaSegnalazioni().add(nodoLampada);
						}
						nodo.getListaSegnalazioni().add(nodoPosizione);

					}

					for (it.osapulie.tributi.web.ws.output.types.PagamentiServiziCimiterialiType.Rate rata : pagamento.getRate()) {
						SegnalazioneFoglia nodoRata = new SegnalazioneFoglia("");
						nodoRata.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.identificativoRata", null, portletRequest.getLocale()), rata.getIdentificativoRata()));
						nodoRata.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.numeroRata", null, portletRequest.getLocale()), String.valueOf(rata.getNumeroRata())));
						nodoRata.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.importoRata", null, portletRequest.getLocale()), String.valueOf(rata.getImportoRata())));
						nodoRata.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.scadenzaRata", null, portletRequest.getLocale()), sdf.format(rata.getScadenzaRata().getTime())));
						nodoRata.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.dataPagamento", null, portletRequest.getLocale()), sdf.format(rata.getDataPagamento().getTime())));
						nodoRata.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.importoPagato", null, portletRequest.getLocale()), String.valueOf(rata.getImportoPagato())));

						nodo.getListaSegnalazioni().add(nodoRata);

					}

					listaSegnalazioni.add(nodo);
				}
			}
			// TODO aggiungere campi mancanti
			if (datiModel != null && datiModel.getElencoPagamentiTassaRifiuti() != null && !datiModel.getElencoPagamentiTassaRifiuti().isEmpty()) {
				for (DatiTassaRifiuti pagamento : datiModel.getElencoPagamentiTassaRifiuti()) {
					SegnalazioneFoglia nodo = new SegnalazioneFoglia(messageSource.getMessage("label.titoloTarsu", null, portletRequest.getLocale()));
					nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.numeroConto", null, portletRequest.getLocale()), pagamento.getContoCorrente()));
					nodo.getListaSegnalazioni()
							.add(new SegnalazioneFoglia(messageSource.getMessage("label.annoRiferimento", null, portletRequest.getLocale()), String.valueOf(pagamento.getAnnoRiferimento())));
					nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.numeroDocumento", null, portletRequest.getLocale()), pagamento.getNumeroDocumento()));
					// TODO verificare
					// nodo.getListaSegnalazioni().add(
					// new SegnalazioneFoglia(messageSource.getMessage("label.importoDocumento",
					// null, portletRequest.getLocale()),
					// String.valueOf(pagamento.getImportoDocumento())));
					nodo.getListaSegnalazioni()
							.add(new SegnalazioneFoglia(messageSource.getMessage("label.dataAggiornamento", null, portletRequest.getLocale()), sdf.format(pagamento.getDataAggiornamento().getTime())));

					for (Posizioni posizione : pagamento.getPosizioni()) {
						SegnalazioneFoglia nodoPosizione = new SegnalazioneFoglia("");
						nodoPosizione.getListaSegnalazioni().add(
								new SegnalazioneFoglia(messageSource.getMessage("label.identificativoUtenza", null, portletRequest.getLocale()), String.valueOf(posizione.getIdentificativoUtenza())));

						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.indirizzoUtenza", null, portletRequest.getLocale()), PortletUtils.getIndirizzoFromPosizione(posizione)));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.superficie", null, portletRequest.getLocale()), String.valueOf(posizione.getSuperficie())));

						// TODO verificare
						// nodoPosizione.getListaSegnalazioni().add(
						// new
						// SegnalazioneFoglia(messageSource.getMessage("label.descrizioneTariffa",
						// null, portletRequest.getLocale()), posizione.getDescrizioneTariffa()));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.importoTariffa", null, portletRequest.getLocale()), String.valueOf(posizione.getImportoTariffa())));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.importoDaPagare", null, portletRequest.getLocale()), String.valueOf(posizione.getImportoDaPagare())));
						nodoPosizione.getListaSegnalazioni().add(
								new SegnalazioneFoglia(messageSource.getMessage("label.addizionaleComunale", null, portletRequest.getLocale()), String.valueOf(posizione.getAddizionaleComunale())));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.maggiorazione", null, portletRequest.getLocale()), String.valueOf(posizione.getMaggiorazioneStato())));
						nodoPosizione.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.addizionaleProvinciale", null, portletRequest.getLocale()),
								String.valueOf(posizione.getAddizionaleProvinciale())));

					}

					for (it.osapulie.tributi.web.ws.output.types.DatiTassaRifiuti.Rate rata : pagamento.getRate()) {
						SegnalazioneFoglia nodoRata = new SegnalazioneFoglia("");
						nodoRata.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.identificativoRata", null, portletRequest.getLocale()), rata.getIdentificativoRata()));
						nodoRata.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.numeroRata", null, portletRequest.getLocale()), String.valueOf(rata.getNumeroRata())));
						nodoRata.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.importoRata", null, portletRequest.getLocale()), String.valueOf(rata.getImportoRata())));
						nodoRata.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.scadenzaRata", null, portletRequest.getLocale()), sdf.format(rata.getScadenzaRata().getTime())));
						nodoRata.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.dataPagamento", null, portletRequest.getLocale()), sdf.format(rata.getDataPagamento().getTime())));
						nodoRata.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.importoPagato", null, portletRequest.getLocale()), String.valueOf(rata.getImportoPagato())));

						nodo.getListaSegnalazioni().add(nodoRata);

					}

					listaSegnalazioni.add(nodo);
				}

			}

			if (datiModel != null && datiModel.getElencoPagamentiTassaAffissioni() != null && !datiModel.getElencoPagamentiTassaAffissioni().isEmpty()) {
				for (PagamentiAffissioniType pagamento : datiModel.getElencoPagamentiTassaAffissioni()) {
					SegnalazioneFoglia nodo = new SegnalazioneFoglia(messageSource.getMessage("label.titoloAffissioni", null, portletRequest.getLocale()));
					nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.numeroConto", null, portletRequest.getLocale()), pagamento.getContoCorrente()));
					nodo.getListaSegnalazioni()
							.add(new SegnalazioneFoglia(messageSource.getMessage("label.annoRiferimento", null, portletRequest.getLocale()), String.valueOf(pagamento.getAnnoRiferimento())));
					nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.numeroDocumento", null, portletRequest.getLocale()), pagamento.getNumeroDocumento()));
					nodo.getListaSegnalazioni()
							.add(new SegnalazioneFoglia(messageSource.getMessage("label.importoDocumento", null, portletRequest.getLocale()), String.valueOf(pagamento.getImportoDocumento())));
					nodo.getListaSegnalazioni()
							.add(new SegnalazioneFoglia(messageSource.getMessage("label.dataAggiornamento", null, portletRequest.getLocale()), sdf.format(pagamento.getDataAggiornamento().getTime())));

					for (PosizioniAffissione posizione : pagamento.getPosizioniAffissione()) {
						SegnalazioneFoglia nodoPosizione = new SegnalazioneFoglia("");
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.numeroFogli", null, portletRequest.getLocale()), String.valueOf(posizione.getNumeroFogli())));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.numeroManifesti", null, portletRequest.getLocale()), String.valueOf(posizione.getNumeroManifesti())));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.giorniAffissione", null, portletRequest.getLocale()), String.valueOf(posizione.getGiorniAffissione())));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.dimensioneManifesti", null, portletRequest.getLocale()), posizione.getDimensioneManifesti()));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.tariffa", null, portletRequest.getLocale()), String.valueOf(posizione.getTariffa())));

						nodo.getListaSegnalazioni().add(nodoPosizione);
					}

					for (it.osapulie.tributi.web.ws.output.types.PagamentiAffissioniType.Rate rata : pagamento.getRate()) {
						SegnalazioneFoglia nodoRata = new SegnalazioneFoglia("");
						nodoRata.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.identificativoRata", null, portletRequest.getLocale()), rata.getIdentificativoRata()));
						nodoRata.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.numeroRata", null, portletRequest.getLocale()), String.valueOf(rata.getNumeroRata())));
						nodoRata.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.importoRata", null, portletRequest.getLocale()), String.valueOf(rata.getImportoRata())));
						nodoRata.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.scadenzaRata", null, portletRequest.getLocale()), sdf.format(rata.getScadenzaRata().getTime())));
						nodoRata.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.dataPagamento", null, portletRequest.getLocale()), sdf.format(rata.getDataPagamento().getTime())));
						nodoRata.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.importoPagato", null, portletRequest.getLocale()), String.valueOf(rata.getImportoPagato())));

						nodo.getListaSegnalazioni().add(nodoRata);

					}

					listaSegnalazioni.add(nodo);
				}

			}

			if (datiModel != null && datiModel.getElencoPagamentiTassaPubblicita() != null && !datiModel.getElencoPagamentiTassaPubblicita().isEmpty()) {
				for (PagamentiPubblicitaType pagamento : datiModel.getElencoPagamentiTassaPubblicita()) {
					SegnalazioneFoglia nodo = new SegnalazioneFoglia(messageSource.getMessage("label.titoloPubblicita", null, portletRequest.getLocale()));
					nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.numeroConto", null, portletRequest.getLocale()), pagamento.getContoCorrente()));
					nodo.getListaSegnalazioni()
							.add(new SegnalazioneFoglia(messageSource.getMessage("label.annoRiferimento", null, portletRequest.getLocale()), String.valueOf(pagamento.getAnnoRiferimento())));
					nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.numeroDocumento", null, portletRequest.getLocale()), pagamento.getNumeroDocumento()));
					nodo.getListaSegnalazioni()
							.add(new SegnalazioneFoglia(messageSource.getMessage("label.importoDocumento", null, portletRequest.getLocale()), String.valueOf(pagamento.getImportoDocumento())));
					nodo.getListaSegnalazioni()
							.add(new SegnalazioneFoglia(messageSource.getMessage("label.dataAggiornamento", null, portletRequest.getLocale()), sdf.format(pagamento.getDataAggiornamento().getTime())));

					for (PosizioniPubblicita posizione : pagamento.getPosizioniPubblicita()) {
						SegnalazioneFoglia nodoPosizione = new SegnalazioneFoglia("");
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.indirizzoPubblicita", null, portletRequest.getLocale()), posizione.getIndirizzoPubblicita()));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.descrizionePubblicita", null, portletRequest.getLocale()), posizione.getDescrizionePubblicita()));
						nodoPosizione.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.mq", null, portletRequest.getLocale()), String.valueOf(posizione.getMq())));
						nodoPosizione.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.mesi", null, portletRequest.getLocale()), String.valueOf(posizione.getMesi())));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.importoPubblicita", null, portletRequest.getLocale()), String.valueOf(posizione.getImportoPubblicita())));

						nodo.getListaSegnalazioni().add(nodoPosizione);
					}

					for (it.osapulie.tributi.web.ws.output.types.PagamentiPubblicitaType.Rate rata : pagamento.getRate()) {
						SegnalazioneFoglia nodoRata = new SegnalazioneFoglia("");
						nodoRata.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.identificativoRata", null, portletRequest.getLocale()), rata.getIdentificativoRata()));
						nodoRata.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.numeroRata", null, portletRequest.getLocale()), String.valueOf(rata.getNumeroRata())));
						nodoRata.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.importoRata", null, portletRequest.getLocale()), String.valueOf(rata.getImportoRata())));
						nodoRata.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.scadenzaRata", null, portletRequest.getLocale()), sdf.format(rata.getScadenzaRata().getTime())));
						nodoRata.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.dataPagamento", null, portletRequest.getLocale()), sdf.format(rata.getDataPagamento().getTime())));
						nodoRata.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.importoPagato", null, portletRequest.getLocale()), String.valueOf(rata.getImportoPagato())));

						nodo.getListaSegnalazioni().add(nodoRata);

					}

					listaSegnalazioni.add(nodo);
				}

			}

			if (datiModel != null && datiModel.getElencoPagamentiTassaImmobili() != null && !datiModel.getElencoPagamentiTassaImmobili().isEmpty()) {
				for (DatiTassaImmobili pagamento : datiModel.getElencoPagamentiTassaImmobili()) {
					SegnalazioneFoglia nodo = new SegnalazioneFoglia(messageSource.getMessage("label.titoloIci", null, portletRequest.getLocale()));
					nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.numeroConto", null, portletRequest.getLocale()), pagamento.getContoCorrente()));
					nodo.getListaSegnalazioni()
							.add(new SegnalazioneFoglia(messageSource.getMessage("label.annoRiferimento", null, portletRequest.getLocale()), String.valueOf(pagamento.getAnnoRiferimento())));
					nodo.getListaSegnalazioni()
							.add(new SegnalazioneFoglia(messageSource.getMessage("label.dataAggiornamento", null, portletRequest.getLocale()), sdf.format(pagamento.getDataAggiornamento().getTime())));

					for (it.osapulie.tributi.web.ws.output.types.DatiTassaImmobili.Posizioni posizione : pagamento.getPosizioni()) {
						SegnalazioneFoglia nodoPosizione = new SegnalazioneFoglia("");
						nodoPosizione.getListaSegnalazioni().add(
								new SegnalazioneFoglia(messageSource.getMessage("label.indirizzoUtenzaImmobile", null, portletRequest.getLocale()), PortletUtils.getIndirizzoFromPosizione(posizione)));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.caratteristicaImmobile", null, portletRequest.getLocale()), posizione.getCaratteristicaImmobile()));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.numero", null, portletRequest.getLocale()), String.valueOf(posizione.getNumero())));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.sezione", null, portletRequest.getLocale()), String.valueOf(posizione.getSezione())));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.foglioCatastale", null, portletRequest.getLocale()), String.valueOf(posizione.getFoglio())));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.particella", null, portletRequest.getLocale()), String.valueOf(posizione.getParticella())));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.subalterno", null, portletRequest.getLocale()), String.valueOf(posizione.getSubalterno())));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.valoreImmobile", null, portletRequest.getLocale()), String.valueOf(posizione.getValoreImmobile())));
						Codifica categoriaImmobile = posizione.getCategoriaImmobile();
						if (categoriaImmobile != null) {
							nodoPosizione.getListaSegnalazioni()
									.add(new SegnalazioneFoglia(messageSource.getMessage("label.categoriaImmobile", null, portletRequest.getLocale()), categoriaImmobile.getCodice()));
						}
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.aliquota", null, portletRequest.getLocale()), String.valueOf(posizione.getAliquota())));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.importoDovuto", null, portletRequest.getLocale()), String.valueOf(posizione.getImportoDovuto())));
						nodoPosizione.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.importoDetrazioneAbPrincipale", null, portletRequest.getLocale()),
								String.valueOf(posizione.getImportoDetrazioneAbPrincipale())));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.mesiDetrazione", null, portletRequest.getLocale()), String.valueOf(posizione.getMesiDetrazione())));
						nodoPosizione.getListaSegnalazioni().add(
								new SegnalazioneFoglia(messageSource.getMessage("label.percentualePossesso", null, portletRequest.getLocale()), String.valueOf(posizione.getPercentualePossesso())));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.mesiPossesso", null, portletRequest.getLocale()), String.valueOf(posizione.getMesiPossesso())));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.posseduto3112", null, portletRequest.getLocale()), String.valueOf(posizione.isPosseduto3112())));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.riduzione", null, portletRequest.getLocale()), String.valueOf(posizione.isRiduzione())));
						nodoPosizione.getListaSegnalazioni().add(
								new SegnalazioneFoglia(messageSource.getMessage("label.abitazionePrincipale", null, portletRequest.getLocale()), String.valueOf(posizione.isAbitazionePrincipale())));

						nodo.getListaSegnalazioni().add(nodoPosizione);
					}
					// TODO aggiungere campi mancanti
					for (it.osapulie.tributi.web.ws.output.types.DatiTassaImmobili.Rate rata : pagamento.getRate()) {

						SegnalazioneFoglia nodoRata = new SegnalazioneFoglia("");
						nodoRata.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.identificativoRata", null, portletRequest.getLocale()), rata.getIdentificativoRata()));

						nodoRata.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.importoAbitazionePrincipale", null, portletRequest.getLocale()),
								String.valueOf(rata.getImportoAbitazionePrincipale())));
						nodoRata.getListaSegnalazioni().add(
								new SegnalazioneFoglia(messageSource.getMessage("label.importoAreaFabbricabile", null, portletRequest.getLocale()), String.valueOf(rata.getImportoAltriFabbricati())));
						nodoRata.getListaSegnalazioni().add(
								new SegnalazioneFoglia(messageSource.getMessage("label.importoTerreniAgricoli", null, portletRequest.getLocale()), String.valueOf(rata.getImportoTerreniAgricoli())));
						nodoRata.getListaSegnalazioni().add(
								new SegnalazioneFoglia(messageSource.getMessage("label.importoAltriFabbricati", null, portletRequest.getLocale()), String.valueOf(rata.getImportoAltriFabbricati())));
						nodoRata.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.numeroFabbricati", null, portletRequest.getLocale()), String.valueOf(rata.getNumeroFabbricati())));
						nodoRata.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.importoDapagare", null, portletRequest.getLocale()), String.valueOf(rata.getImportoDaPagare())));
						nodoRata.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.importoPagato", null, portletRequest.getLocale()), String.valueOf(rata.getImportoPagato())));
						nodoRata.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.dataPagamento", null, portletRequest.getLocale()), sdf.format(rata.getDataPagamento().getTime())));
						nodoRata.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.tipoRata", null, portletRequest.getLocale()), String.valueOf(rata.getTipoRata())));

						nodo.getListaSegnalazioni().add(nodoRata);

					}

					listaSegnalazioni.add(nodo);
				}

			}
		}

		return listaSegnalazioni;
	}

	/**
	 * Presenta la visura a video.
	 *
	 * @param model
	 * @return l'ID della view per visualizzare il fascicolo
	 */
	@RenderMapping(params = "action=renderVisura")
	public String renderVisura(Model model) {
		log.debug("Model = " + model);
		model.addAttribute("annoCorrente", DateUtils.getAnnoCorrente());
		return toLocalViewPath("mostraVisura");
	}

	@ExceptionHandler(Exception.class)
	public String onError(Exception e) {
		return "";
	}

	/**
	 * Utility method per calcolare e restituire il view ID all'interno della corretta alberatura di
	 * sottodirectory delle JSP: in un certo senso si tratta del namespace delle view locali a
	 * questo controller. Ad esempio: <code> toLocalViewPath( "home" ) </code> restituirà <code>
	 * "/fascicoloutente/home" </code>
	 *
	 * @param viewName l'ID della view locale
	 * @return il view ID completo del path a cui la vista appartiene
	 */
	private static String toLocalViewPath(String viewName) {
		return "visureposizionetributaria/" + viewName;
	}

	@ModelAttribute(value = "dati")
	public VisuraPosizioneTributariaRisposta getDati() {
		VisuraPosizioneTributariaRisposta dati = new VisuraPosizioneTributariaRisposta();
		return dati;
	}
}
