/************************************************************************************
 * Copyright (c) 2011, 2014 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.tributi.web.portlet.visuratassarifiuti.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
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
import it.osapulie.service.CommonService;
import it.osapulie.service.ComuneService;
import it.osapulie.service.DelegaService;
import it.osapulie.service.DwhService;
import it.osapulie.service.FascicoloUtenteService;
import it.osapulie.service.ServizioService;
import it.osapulie.shared.service.Segnalazione;
import it.osapulie.shared.service.SegnalazioneFoglia;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.tributi.service.AuditDwhService;
import it.osapulie.tributi.service.VisuraTassaRifiutiService;
import it.osapulie.tributi.web.portlet.utils.PortletConstants;
import it.osapulie.tributi.web.portlet.utils.PortletUtils;
import it.osapulie.tributi.web.ws.output.types.Codifica;
import it.osapulie.tributi.web.ws.output.types.DatiTassaRifiuti;
import it.osapulie.tributi.web.ws.output.types.DatiTassaRifiuti.Posizioni;
import it.osapulie.tributi.web.ws.output.types.DatiTassaRifiuti.Posizioni.OccupazioneNucleoFamiliare;
import it.osapulie.util.DwhDataminingUtil;
import it.osapulie.util.DwhServiceAttributeUtil;
import it.osapulie.util.DwhTempiMediUtil;
import it.osapulie.util.IUpdateAttribute;
import it.osapulie.util.audit.ApplicationContextUtil;
import it.osapulie.util.audit.AuditConfiguration;
import it.osapulie.util.audit.Origin;
import it.osapulie.util.dto.DwhDataminingDTO;
import it.osapulie.util.dto.DwhServizioAttributeDTO;
import it.osapulie.util.dto.DwhTempiMediDTO;
import it.osapulie.tributi.web.ws.output.types.DatiTassaRifiuti.Rate;
import it.osapulie.tributi.web.ws.output.types.Riduzione;
import it.osapulie.tributi.web.ws.output.types.VisuraTassaRifiutiRichiesta;
import it.osapulie.tributi.web.ws.output.types.VisuraTassaRifiutiRisposta;
import it.osapulie.web.portlet.controller.BaseController;
import it.osapulie.web.portlet.util.PortletHelper;

/**
 * Portlet controller per la visura tassa rifiuti.
 *
 * @author Gianluca Pindinelli
 */
@Controller("visuraTassaRifiutiPortletController")
@RequestMapping("view")
@SessionAttributes("dati")
public class VisuraTassaRifiutiPortletController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(VisuraTassaRifiutiPortletController.class);

	@Inject
	private VisuraTassaRifiutiService service;

	@Inject
	private PortletHelper helper;

	@Inject
	private FascicoloUtenteService fascicoloService;

	@Inject
	private ServizioService servizioService;
	
	@Inject
	private ComuneService comuneService;
	
	@Inject
	private CommonService commonService;
	
	@Inject
	private DelegaService delegaService;
 
	@Inject
	private DwhService dwhService;
	
	@Inject
	private AuditDwhService auditDwhService;
	
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
		log.info("home ");
		model.addAttribute("annoCorrente", DateUtils.getAnnoCorrente());
		return toLocalViewPath("home");
	}

	@ActionMapping(params = "action=visura")
	public void visuraTari(@RequestParam("year") String year, @RequestParam(required = false, value = "invia") String invia, Model model, @ModelAttribute("dati") VisuraTassaRifiutiRisposta dati,
			ActionResponse response, ActionRequest request) throws Exception {
		try {

			if (invia != null) {
				UserPreferences userPreferences = helper.getUserPreferences(request);
				String codiceFiscale = userPreferences.getCodiceFiscaleServizio();
				log.info("userCF=" + codiceFiscale);

				VisuraTassaRifiutiRichiesta richiesta = new VisuraTassaRifiutiRichiesta();
				if (year != null && !year.equals("")) {

					// memorizzo il campo
					model.addAttribute("year", year);

					richiesta.setAnnoInizio(Integer.parseInt(year));
					richiesta.setAnnoFine(Integer.parseInt(year));
				}

				// Controllo profilo cittadino/azienda
				if (userPreferences.getPartitaIvaServizio() != null) {
					richiesta.setPartitaIva(userPreferences.getPartitaIvaServizio());
				}
				else {
					richiesta.setCodiceFiscale(codiceFiscale);
				}

				dati = new VisuraTassaRifiutiRisposta();
				dati = service.richiediDati(richiesta, userPreferences);
				model.addAttribute("dati", dati);

				// Scrittura su fascicolo utente
				ProfiloUtenteCittadino profiloUtente = helper.getOsApulieUserDetails().getProfiloUtenteCittadino();

				Fascicolo fascicolo = new Fascicolo();
				fascicolo.setIdProfiloUtente(profiloUtente);
				fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio", null, request.getLocale()));
				fascicolo.setUserPreferences(helper.getUserPreferences(request));
				fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_VISURA_TASSA_RIFIUTI);
				fascicolo.setRicercabileDaComune(false);
				fascicolo.setNumeroProtocollo(null);
				fascicolo.setChecksum(null);
				fascicoloService.saveNuovaRichiesta(fascicolo);
				
				final String uuidOperazione=UUID.randomUUID().toString();
				 
				AuditConfiguration
							.configure()
							.audit()
							.addInizioOperazione()
							.addUuidOperazione(uuidOperazione)
							.addOperazioneRichiesta("SERVIZIO VISURA TASSA RIFIUTI  - START")
							.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
							.addPaginaCorrente(helper.getCurrentPageName(request))
							.addEsitoSuccess("SUCCESS")
							.setOrigin(Origin.getIp(request))
							.build();
		 
				DwhServizioAttributeDTO servizioAttributeDto = new DwhServizioAttributeDTO();
				DwhDataminingDTO dataminingDto = new DwhDataminingDTO();
				DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();

				servizioAttributeDto = DwhServiceAttributeUtil.get(commonService, comuneService, delegaService, servizioService, userPreferences, request, dwhService)
				.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_VISURA_TASSA_RIFIUTI)
				.setUUID(uuidOperazione)
				.getServizioAttribute();	
				
				dataminingDto = DwhDataminingUtil.get(codiceFiscale, dwhService)
				.setCodServizio(PortletConstants.CODICE_SERVIZIO_VISURA_TASSA_RIFIUTI)
				.setUuidOperazione(uuidOperazione)
				.getDatamining();
				
				 tempiMediDto = DwhTempiMediUtil.get(dwhService)
				.setCodServizio(PortletConstants.CODICE_SERVIZIO_VISURA_TASSA_RIFIUTI)
				.setNomeServizio("SERVIZIO VISURA TASSA RIFIUTI")
				.setUuid(uuidOperazione).go_StartTime().getTempiMedi();	
				
				 
				 auditDwhService.invokeAuditService(servizioAttributeDto, dataminingDto, tempiMediDto);
					//MS
					
				 tempiMediDto = DwhTempiMediUtil.get(dwhService)
					.go_EndTime(uuidOperazione)
					.setCodServizio(PortletConstants.CODICE_SERVIZIO_VISURA_TASSA_RIFIUTI)
					.setNomeServizio("SERVIZIO VISURA TASSA RIFIUTI")
					.getTempiMedi();
				 
				 auditDwhService.invokeAuditService(null, null, tempiMediDto);

					 DwhServiceAttributeUtil.saveTimeFine(dwhService,new IUpdateAttribute() {
						
						  
								public String updateServizioProtocollo() {
								 
									return null;
								}
								public String updateServizioParametro3() {
									 
									return null;
								}
								public String updateServizioParametro2() {
									 
									return  null;
								}
								public String updateServizioParametro1() {
								 
									return null;
								}
								public Date updateServizioFine() {
								 
									return new Date();
								}
								public String searchUUID() {
								
									return uuidOperazione;
								}
							});				
					
						AuditConfiguration
						.configure()
						.audit()
						.addInizioOperazione()
						.addFineOperazione()
						.addUuidOperazione(uuidOperazione)
						.addOperazioneRichiesta("SERVIZIO VISURA TASSA RIFIUTI")
						.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
						.addPaginaCorrente(helper.getCurrentPageName(request))
						.addEsitoSuccess("SUCCESS - OPERAZIONE CONCLUSA")
						.addServizioTimeString()
						.addServizioUuidTransazione(uuidOperazione)
						.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_VISURA_TASSA_RIFIUTI)
						.setOrigin(Origin.getIp(request))
						.addCallGeo()
						.build();
 
				
				
			}
			else {
				List<SegnalazioneFoglia> listaSegnalazioni = inviaSegnalazioneAction(dati, request);
				Segnalazione segnalazione = new Segnalazione();
				segnalazione.setListaSegnalazioni(listaSegnalazioni);
				segnalazione.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio", null, request.getLocale()));
				segnalazione.setServizioCode(PortletConstants.CODICE_SERVIZIO_VISURA_TASSA_RIFIUTI);

				PortletSession session = request.getPortletSession();
				session.setAttribute(PortletConstants.SEGNALAZIONE_SHARED_ATTRIBUTE, segnalazione, PortletSession.APPLICATION_SCOPE);
			}

		}
		catch (Exception e) {
			log.error("visuraTari :: " + e.getMessage(), e);
		}
		if (invia != null) {
			response.setRenderParameter("action", "renderVisura");
		}
		else {
			response.sendRedirect(segnalazioniPageUrl);
		}
	}

	private List<SegnalazioneFoglia> inviaSegnalazioneAction(VisuraTassaRifiutiRisposta datiModel, ActionRequest request) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		List<SegnalazioneFoglia> listaSegnalazioni = new ArrayList<SegnalazioneFoglia>();
		if (datiModel != null) {

			if (datiModel != null && datiModel.getElencoPagamenti() != null && !datiModel.getElencoPagamenti().isEmpty()) {
				for (DatiTassaRifiuti pagamento : datiModel.getElencoPagamenti()) {
					SegnalazioneFoglia nodo = new SegnalazioneFoglia(messageSource.getMessage("label.titoloTassaRifiuti", null, request.getLocale()));
					if (pagamento.getContoCorrente() != null) {
						nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.contoCorrente", null, request.getLocale()), pagamento.getContoCorrente()));
					}
					nodo.getListaSegnalazioni()
							.add(new SegnalazioneFoglia(messageSource.getMessage("label.annoRiferimento", null, request.getLocale()), String.valueOf(pagamento.getAnnoRiferimento())));
					if (pagamento.getNumeroDocumento() != null) {
						nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.numeroDocumento", null, request.getLocale()), pagamento.getNumeroDocumento()));
					}

					if (pagamento.getDataAggiornamento() != null) {
						nodo.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.dataAggiornamento", null, request.getLocale()), sdf.format(pagamento.getDataAggiornamento().getTime())));
					}
					for (Posizioni posizione : pagamento.getPosizioni()) {
						SegnalazioneFoglia nodoPosizione = new SegnalazioneFoglia("");
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.identificativoUtenza", null, request.getLocale()), String.valueOf(posizione.getIdentificativoUtenza())));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.indirizzoUtenzaImmobile", null, request.getLocale()), PortletUtils.getIndirizzoFromPosizione(posizione)));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.superficie", null, request.getLocale()), String.valueOf(posizione.getSuperficie())));
						nodoPosizione.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.numero", null, request.getLocale()), String.valueOf(posizione.getNumero())));
						nodoPosizione.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.sezione", null, request.getLocale()), String.valueOf(posizione.getSezione())));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.foglioCatastale", null, request.getLocale()), String.valueOf(posizione.getFoglio())));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.particella", null, request.getLocale()), String.valueOf(posizione.getParticella())));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.subalterno", null, request.getLocale()), String.valueOf(posizione.getSubalterno())));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.destinazione", null, request.getLocale()), String.valueOf(posizione.getDestinazione())));
						nodoPosizione.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.tipo", null, request.getLocale()), String.valueOf(posizione.getTipo())));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.aliquota", null, request.getLocale()), String.valueOf(posizione.getAliquota())));
						nodoPosizione.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.agevolazione", null, request.getLocale()), posizione.getAgevolazione()));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.importoAgevolazione", null, request.getLocale()), String.valueOf(posizione.getImportoAgevolazione())));
						List<Riduzione> riduzioni = posizione.getRiduzione();
						if (riduzioni != null) {
							for (Riduzione riduzione : riduzioni) {
								Codifica articolo = riduzione.getArticolo();
								Codifica valore = riduzione.getValore();
								if (articolo != null && valore != null) {
									nodoPosizione.getListaSegnalazioni().add(
											new SegnalazioneFoglia(messageSource.getMessage("label.riduzione", null, request.getLocale()), articolo.getDescrizione() + "," + valore.getDescrizione()));
								}
							}
						}
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.importoRiduzione", null, request.getLocale()), String.valueOf(posizione.getImportoRiduzioni())));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.importoTariffa", null, request.getLocale()), String.valueOf(posizione.getImportoTariffa())));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.importoDaPagare", null, request.getLocale()), String.valueOf(posizione.getImportoDaPagare())));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.addizionaleComunale", null, request.getLocale()), String.valueOf(posizione.getAddizionaleComunale())));
						nodoPosizione.getListaSegnalazioni().add(
								new SegnalazioneFoglia(messageSource.getMessage("label.addizionaleProvinciale", null, request.getLocale()), String.valueOf(posizione.getAddizionaleProvinciale())));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.maggiorazioneStato", null, request.getLocale()), String.valueOf(posizione.getMaggiorazioneStato())));
						nodoPosizione.getListaSegnalazioni().add(
								new SegnalazioneFoglia(messageSource.getMessage("label.addizionaleProvinciale", null, request.getLocale()), String.valueOf(posizione.getAddizionaleProvinciale())));
						nodoPosizione.getListaSegnalazioni().add(
								new SegnalazioneFoglia(messageSource.getMessage("label.dataInizioOccupazione", null, request.getLocale()), sdf.format(posizione.getDataInizioOccupazione().getTime())));
						if (posizione.getDataFineOccupazione() != null) {
							nodoPosizione.getListaSegnalazioni().add(
									new SegnalazioneFoglia(messageSource.getMessage("label.dataFineOccupazione", null, request.getLocale()), sdf.format(posizione.getDataFineOccupazione().getTime())));
						}
						List<OccupazioneNucleoFamiliare> occupazioniNucleoFamiliare = posizione.getOccupazioneNucleoFamiliare();
						if (occupazioniNucleoFamiliare != null && !occupazioniNucleoFamiliare.isEmpty()) {
							for (OccupazioneNucleoFamiliare occupazioneNucleoFamiliare : occupazioniNucleoFamiliare) {
								SegnalazioneFoglia nodoOccupazione = new SegnalazioneFoglia("");
								nodoOccupazione.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.dataInizioOccupazioneNucleoFamiliare", null, request.getLocale()),
										sdf.format(occupazioneNucleoFamiliare.getDataInizioOccupazione())));
								nodoOccupazione.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.dataFineOccupazioneNucleoFamiliare", null, request.getLocale()),
										sdf.format(occupazioneNucleoFamiliare.getDataFineOccupazione())));
								nodoOccupazione.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.numeroComponentiNucleoFamiliare", null, request.getLocale()),
										String.valueOf(occupazioneNucleoFamiliare.getNumeroComponenti())));
							}
						}

						nodo.getListaSegnalazioni().add(nodoPosizione);
					}
					for (Rate rata : pagamento.getRate()) {
						SegnalazioneFoglia nodoRata = new SegnalazioneFoglia("");
						nodoRata.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.identificativoRata", null, request.getLocale()), rata.getIdentificativoRata()));
						nodoRata.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.numeroRata", null, request.getLocale()), String.valueOf(rata.getNumeroRata())));
						nodoRata.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.importoRata", null, request.getLocale()), String.valueOf(rata.getImportoRata())));
						nodoRata.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.scadenzaRata", null, request.getLocale()), sdf.format(rata.getScadenzaRata().getTime())));
						nodoRata.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.dataPagamento", null, request.getLocale()), sdf.format(rata.getDataPagamento().getTime())));
						nodoRata.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.importoPagato", null, request.getLocale()), String.valueOf(rata.getImportoPagato())));
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
		return "visuratassarifiuti/" + viewName;
	}

	@ModelAttribute(value = "dati")
	public VisuraTassaRifiutiRisposta getDati() {
		VisuraTassaRifiutiRisposta dati = new VisuraTassaRifiutiRisposta();
		return dati;
	}
}
