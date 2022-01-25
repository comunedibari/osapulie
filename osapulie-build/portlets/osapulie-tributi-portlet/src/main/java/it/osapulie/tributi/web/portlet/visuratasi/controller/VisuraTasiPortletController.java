/************************************************************************************
 * Copyright (c) 2011, 2014 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.tributi.web.portlet.visuratasi.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import it.osapulie.tributi.service.VisuraTasiService;
import it.osapulie.tributi.web.portlet.utils.PortletConstants;
import it.osapulie.tributi.web.portlet.utils.PortletUtils;
import it.osapulie.tributi.web.ws.output.types.Codifica;
import it.osapulie.tributi.web.ws.output.types.DatiTassaImmobili;
import it.osapulie.tributi.web.ws.output.types.DatiTassaImmobili.Posizioni;
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
import it.osapulie.tributi.web.ws.output.types.VisuraTasiRichiesta;
import it.osapulie.tributi.web.ws.output.types.VisuraTasiRisposta;
import it.osapulie.web.portlet.controller.BaseController;
import it.osapulie.web.portlet.util.PortletHelper;

/**
 * Portlet controller per la visura TASI.
 *
 * @author Gianluca Pindinelli
 */
@Controller("visuraTasiPortletController")
@RequestMapping("view")
@SessionAttributes("dati")
public class VisuraTasiPortletController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(VisuraTasiPortletController.class);

	@Inject
	private VisuraTasiService service;

	@Inject
	private PortletHelper helper;

	@Inject
	private FascicoloUtenteService fascicoloService;

	@Inject
	private ComuneService comuneService;

	@Inject
	private ServizioService servizioService;

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
	public void visuraTasi(@RequestParam("startYear") String yearStart, @RequestParam("endYear") String yearEnd, @RequestParam(required = false, value = "invia") String invia, Model model,
			@ModelAttribute("dati") VisuraTasiRisposta dati, ActionResponse response, ActionRequest request) throws Exception {
		try {

			if (invia != null) {
				UserPreferences userPreferences = helper.getUserPreferences(request);
				String codiceFiscale = userPreferences.getCodiceFiscaleServizio();

				VisuraTasiRichiesta richiesta = new VisuraTasiRichiesta();
				if (yearStart != null && yearEnd != null && !yearStart.equals("") && !yearEnd.equals("")) {

					Calendar startCalendar = Calendar.getInstance();
					startCalendar.set(Calendar.MONTH, 0);
					startCalendar.set(Calendar.DAY_OF_MONTH, 1);
					Calendar endCalendar = Calendar.getInstance();
					endCalendar.set(Calendar.MONTH, 0);
					endCalendar.set(Calendar.DAY_OF_MONTH, 1);

					startCalendar.set(Calendar.YEAR, Integer.valueOf(yearStart));
					endCalendar.set(Calendar.YEAR, Integer.valueOf(yearEnd));

					// memorizzo il campo
					model.addAttribute("yearStart", yearStart);
					model.addAttribute("yearEnd", yearEnd);

					richiesta.setDal(startCalendar);
					richiesta.setAl(endCalendar);
				}

				// Controllo profilo cittadino/azienda
				if (userPreferences.getPartitaIvaServizio() != null) {
					richiesta.setPartitaIva(userPreferences.getPartitaIvaServizio());
				}
				else {
					richiesta.setCodiceFiscale(codiceFiscale);
				}

				dati = new VisuraTasiRisposta();
				dati = service.richiediDatiVisuraTasi(richiesta, userPreferences);
				model.addAttribute("dati", dati);

				// Scrittura su fascicolo utente
				ProfiloUtenteCittadino profiloUtente = helper.getOsApulieUserDetails().getProfiloUtenteCittadino();

				Fascicolo fascicolo = new Fascicolo();
				fascicolo.setIdProfiloUtente(profiloUtente);
				fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio", null, request.getLocale()));
				fascicolo.setUserPreferences(userPreferences);
				fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_VISURA_TASI);
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
							.addOperazioneRichiesta("SERVIZIO VISURA TASI  - START")
							.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
							.addPaginaCorrente(helper.getCurrentPageName(request))
							.addEsitoSuccess("SUCCESS")
							.setOrigin(Origin.getIp(request))
							.build();
		 
				DwhServizioAttributeDTO servizioAttributeDto = new DwhServizioAttributeDTO();
				DwhDataminingDTO dataminingDto = new DwhDataminingDTO();
				DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();

				servizioAttributeDto = DwhServiceAttributeUtil.get(commonService, comuneService, delegaService, servizioService, userPreferences, request, dwhService)
				.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_VISURA_TASI)
				.setUUID(uuidOperazione)
				.getServizioAttribute();	
				
				dataminingDto = DwhDataminingUtil.get(codiceFiscale, dwhService)
				.setCodServizio(PortletConstants.CODICE_SERVIZIO_VISURA_TASI)
				.setUuidOperazione(uuidOperazione)
				.getDatamining();
				
				 tempiMediDto = DwhTempiMediUtil.get(dwhService)
				.setCodServizio(PortletConstants.CODICE_SERVIZIO_VISURA_TASI)
				.setNomeServizio("SERVIZIO VISURA TASI")
				.setUuid(uuidOperazione).go_StartTime().getTempiMedi();	
				
				 
				 auditDwhService.invokeAuditService(servizioAttributeDto, dataminingDto, tempiMediDto);
					//MS
					tempiMediDto = DwhTempiMediUtil.get(dwhService)
					.go_EndTime(uuidOperazione)
					.setCodServizio(PortletConstants.CODICE_SERVIZIO_VISURA_TASI)
					.setNomeServizio("SERVIZIO VISURA TASI")
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
						.addOperazioneRichiesta("SERVIZIO VISURA TASI")
						.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
						.addPaginaCorrente(helper.getCurrentPageName(request))
						.addEsitoSuccess("SUCCESS - OPERAZIONE CONCLUSA")
						.addServizioTimeString()
						.addServizioUuidTransazione(uuidOperazione)
						.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_VISURA_TASI)
						.setOrigin(Origin.getIp(request))
						.addCallGeo()
						.build();
 
				
			}
			else {
				List<SegnalazioneFoglia> listaSegnalazioni = inviaSegnalazioneAction(dati, request);
				Segnalazione segnalazione = new Segnalazione();
				segnalazione.setListaSegnalazioni(listaSegnalazioni);
				segnalazione.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio", null, request.getLocale()));
				segnalazione.setServizioCode(PortletConstants.CODICE_SERVIZIO_VISURA_TASI);

				PortletSession session = request.getPortletSession();
				session.setAttribute(PortletConstants.SEGNALAZIONE_SHARED_ATTRIBUTE, segnalazione, PortletSession.APPLICATION_SCOPE);
			}

		}
		catch (Exception e) {
			log.error("visuraTasi :: " + e.getMessage(), e);
		}
		if (invia != null) {
			response.setRenderParameter("action", "renderVisura");
		}
		else {
			response.sendRedirect(segnalazioniPageUrl);
		}
	}

	private List<SegnalazioneFoglia> inviaSegnalazioneAction(VisuraTasiRisposta datiModel, ActionRequest request) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		List<SegnalazioneFoglia> listaSegnalazioni = new ArrayList<SegnalazioneFoglia>();
		if (datiModel != null) {

			if (datiModel != null && datiModel.getElencoPagamentiTasi() != null && !datiModel.getElencoPagamentiTasi().isEmpty()) {
				for (DatiTassaImmobili pagamento : datiModel.getElencoPagamentiTasi()) {
					SegnalazioneFoglia nodo = new SegnalazioneFoglia(messageSource.getMessage("label.titoloIci", null, request.getLocale()));
					nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.numeroConto", null, request.getLocale()), pagamento.getContoCorrente()));
					nodo.getListaSegnalazioni()
							.add(new SegnalazioneFoglia(messageSource.getMessage("label.annoRiferimento", null, request.getLocale()), String.valueOf(pagamento.getAnnoRiferimento())));
					nodo.getListaSegnalazioni()
							.add(new SegnalazioneFoglia(messageSource.getMessage("label.dataAggiornamento", null, request.getLocale()), sdf.format(pagamento.getDataAggiornamento().getTime())));
					for (Posizioni posizione : pagamento.getPosizioni()) {
						SegnalazioneFoglia nodoPosizione = new SegnalazioneFoglia("");
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.indirizzoUtenzaImmobile", null, request.getLocale()), PortletUtils.getIndirizzoFromPosizione(posizione)));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.caratteristicaImmobile", null, request.getLocale()), posizione.getCaratteristicaImmobile()));
						nodoPosizione.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.numero", null, request.getLocale()), String.valueOf(posizione.getNumero())));
						nodoPosizione.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.sezione", null, request.getLocale()), String.valueOf(posizione.getSezione())));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.foglioCatastale", null, request.getLocale()), String.valueOf(posizione.getFoglio())));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.particella", null, request.getLocale()), String.valueOf(posizione.getParticella())));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.subalterno", null, request.getLocale()), String.valueOf(posizione.getSubalterno())));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.valoreImmobile", null, request.getLocale()), String.valueOf(posizione.getValoreImmobile())));
						Codifica categoriaImmobile = posizione.getCategoriaImmobile();
						if (categoriaImmobile != null) {
							nodoPosizione.getListaSegnalazioni()
									.add(new SegnalazioneFoglia(messageSource.getMessage("label.categoriaImmobile", null, request.getLocale()), categoriaImmobile.getCodice()));
						}
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.aliquota", null, request.getLocale()), String.valueOf(posizione.getAliquota())));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.importoDovuto", null, request.getLocale()), String.valueOf(posizione.getImportoDovuto())));
						nodoPosizione.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.importoDetrazioneAbPrincipale", null, request.getLocale()),
								String.valueOf(posizione.getImportoDetrazioneAbPrincipale())));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.mesiDetrazione", null, request.getLocale()), String.valueOf(posizione.getMesiDetrazione())));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.percentualePossesso", null, request.getLocale()), String.valueOf(posizione.getPercentualePossesso())));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.mesiPossesso", null, request.getLocale()), String.valueOf(posizione.getMesiPossesso())));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.posseduto3112", null, request.getLocale()), String.valueOf(posizione.isPosseduto3112())));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.riduzione", null, request.getLocale()), String.valueOf(posizione.isRiduzione())));
						nodoPosizione.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.abitazionePrincipale", null, request.getLocale()), String.valueOf(posizione.isAbitazionePrincipale())));

						nodo.getListaSegnalazioni().add(nodoPosizione);
					}

					for (it.osapulie.tributi.web.ws.output.types.DatiTassaImmobili.Rate rata : pagamento.getRate()) {

						SegnalazioneFoglia nodoRata = new SegnalazioneFoglia("");
						nodoRata.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.identificativoRata", null, request.getLocale()), rata.getIdentificativoRata()));
						nodoRata.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.importoAbitazionePrincipale", null, request.getLocale()),
								String.valueOf(rata.getImportoAbitazionePrincipale())));
						nodoRata.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.importoAreaFabbricabile", null, request.getLocale()), String.valueOf(rata.getImportoAltriFabbricati())));
						nodoRata.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.importoTerreniAgricoli", null, request.getLocale()), String.valueOf(rata.getImportoTerreniAgricoli())));
						nodoRata.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.importoAltriFabbricati", null, request.getLocale()), String.valueOf(rata.getImportoAltriFabbricati())));
						nodoRata.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.numeroFabbricati", null, request.getLocale()), String.valueOf(rata.getNumeroFabbricati())));
						nodoRata.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.importoDapagare", null, request.getLocale()), String.valueOf(rata.getImportoDaPagare())));
						nodoRata.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.importoPagato", null, request.getLocale()), String.valueOf(rata.getImportoPagato())));
						nodoRata.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.dataPagamento", null, request.getLocale()), sdf.format(rata.getDataPagamento().getTime())));
						nodoRata.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.tipoRata", null, request.getLocale()), String.valueOf(rata.getTipoRata())));
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
		return "visuratasi/" + viewName;
	}

	@ModelAttribute(value = "dati")
	public VisuraTasiRisposta getDati() {
		VisuraTasiRisposta dati = new VisuraTasiRisposta();
		return dati;
	}
}
