package it.osapulie.anagrafe.web.portlet.visuraposizioneelettorale.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.inject.Inject;
import javax.portlet.ActionRequest;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import eng.tz.la.core.AuditManager;
import eng.tz.la.core.Export;
import eng.tz.la.model.OperationExportFile;
import it.osapulie.anagrafe.service.AuditDwhService;
import it.osapulie.anagrafe.service.VisureService;
import it.osapulie.anagrafe.web.portlet.util.impl.PortletConstants;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici.ComponentiNucleoFamiliare;
import it.osapulie.anagrafe.web.ws.output.types.DatiElettorali;
import it.osapulie.anagrafe.web.ws.output.types.PosizioniElettorali;
import it.osapulie.anagrafe.web.ws.output.types.RichiestaDatiAnagrafici;
import it.osapulie.anagrafe.web.ws.output.types.RichiestaDatiElettorali;
import it.osapulie.domain.Audit;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.dto.Fascicolo;
import it.osapulie.persistence.AuditRepository;
import it.osapulie.service.CommonService;
import it.osapulie.service.ComuneService;
import it.osapulie.service.DelegaService;
import it.osapulie.service.DwhService;
import it.osapulie.service.FascicoloUtenteService;
import it.osapulie.service.ServiceLayerException;
import it.osapulie.service.ServizioService;
import it.osapulie.shared.service.Segnalazione;
import it.osapulie.shared.service.SegnalazioneFoglia;
import it.osapulie.shared.service.UserPreferences;
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
import it.osapulie.web.portlet.controller.BaseController;
import it.osapulie.web.portlet.util.impl.LiferayPortletHelperImpl;

/**
 * Portlet controller per il servizio visura posizione elettorale.
 *
 * @author Birtolo Maria MIchela
 */
@Controller("visuraPosizioneElettoralePortletController")
@RequestMapping("view")
@SessionAttributes({ "datiElettorali" })
public class VisuraPosizioneElettoralePortletController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(VisuraPosizioneElettoralePortletController.class);

	@Inject
	private VisureService visureService;

	@Inject
	private LiferayPortletHelperImpl helper;

	@Inject
	private FascicoloUtenteService fascicoloService;

	@Autowired
	public ResourceBundleMessageSource messageSource;

	@Value("#{applicationProperties.segnalazioniPageUrl}")
	private String segnalazioniPageUrl;
	
	@Inject
	private AuditRepository auditRepository;
	@Inject
	private ComuneService comuneService;
	@Inject
	private CommonService commonService;
	@Inject
	private DelegaService delegaService;
	@Inject
	private ServizioService servizioService;
	@Inject
	private DwhService dwhService;
	@Inject
	private AuditDwhService auditDwhService;
	
	/**
	 * Default entry quando la portlet viene visualizzata per la prima volta.
	 *
	 * @param model il view model da popolare
	 * @return il view ID da renderizzare (<strong>elencoOpere</strong>).
	 */
	@RenderMapping
	public String homePage(Model model, PortletSession session, PortletRequest portletRequest) throws Exception {
		log.debug("homePage");

		UserPreferences userPreferences = helper.getUserPreferences(portletRequest);
		String codiceFiscale = userPreferences.getCodiceFiscaleServizio();
		log.debug("userCF=" + codiceFiscale);

		RichiestaDatiAnagrafici richiesta = new RichiestaDatiAnagrafici();
		richiesta.setCodiceFiscale(codiceFiscale);

		DatiAnagrafici dati = visureService.richiediDatiAnagrafici(richiesta, helper.getUserPreferences(portletRequest));
		model.addAttribute("datiAnagrafici", dati);

		return toLocalViewPath("home");
	}

	@ActionMapping(params = "action=visura")
	public void visuraPosizioneElettorale(@RequestParam("codFisc") String codFisc, Model model, ActionResponse response, PortletSession session, PortletRequest portletRequest) throws Exception {
		log.debug("visuraPosizioneElettorale request codFisc= ..." + codFisc);
		
		String uuidStr=UUID.randomUUID().toString();
		session.setAttribute("UUID", uuidStr);
		AuditManager auditManager= AuditConfiguration.configure()
					.audit()
					.addInizioOperazione()
					.addUuidOperazione(uuidStr)
					.addOperazioneRichiesta("VISURA POSIZIONE ELETTORALE")
					.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(portletRequest))
					.setOrigin(Origin.getIp(portletRequest))
					.addPaginaCorrente(helper.getCurrentPageName(portletRequest));
			 auditManager.exportUser(false, new Export() {
			
			@Override
			public void call(OperationExportFile op) throws IOException {
		
				List<Audit> ltsAudit= auditRepository.findByFileName(op.getManager().getCodFileName());
				if(ltsAudit==null || ltsAudit!=null && ltsAudit.size()<1) {
					Date dateCreazione=new Date();
					Audit a= new Audit();
					a.setUserCod(op.getManager().getKeyUser());
					a.setGiornoMeseAnno(op.getManager().getFolderDay());
					a.setFileName(op.getManager().getCodFileName());
					a.setPathFilesystem(op.getManager().getPath_Log_User());
					a.setChecksum("0");
					a.setDataCreazione(dateCreazione);
					a.setDataUltimaModifica(dateCreazione);
					a.setCodiceRegistro("0");
					a.setStato("0");
					a.setCons("0"); 
					auditRepository.save(a);
				}
			}
		});

			 final String uuidOperazione=uuidStr;
				session.setAttribute("UUID", uuidOperazione);
				
				UserPreferences userPreferences = helper.getUserPreferences(portletRequest);
				
				DwhServizioAttributeDTO servizioAttributeDto = new DwhServizioAttributeDTO();
				DwhDataminingDTO dataminingDto = new DwhDataminingDTO();
				DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();
				
				servizioAttributeDto = DwhServiceAttributeUtil.get(commonService, comuneService, delegaService, servizioService,userPreferences , portletRequest, dwhService)
				.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_VISURA_POSIZIONE_ELETTORALE)
				.setUUID(uuidOperazione)
				.getServizioAttribute();
				
				
				dataminingDto = DwhDataminingUtil.get(codFisc, dwhService)
				.setCodServizio(PortletConstants.CODICE_SERVIZIO_VISURA_POSIZIONE_ELETTORALE)
				.setUuidOperazione(uuidOperazione)
				.getDatamining();
				
				 tempiMediDto = DwhTempiMediUtil.get(dwhService)
				.setCodServizio(PortletConstants.CODICE_SERVIZIO_VISURA_POSIZIONE_ELETTORALE)
				.setNomeServizio("VISURA POSIZIONE ELETTORALE")
				.setUuid(uuidOperazione).go_StartTime().getTempiMedi();

			 
				auditDwhService.invokeAuditService(servizioAttributeDto, dataminingDto, tempiMediDto);
  
			 
			 
		ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(session);

		RichiestaDatiElettorali richiestaElettorali = new RichiestaDatiElettorali();
		richiestaElettorali.setCodiceFiscale(codFisc);
		RichiestaDatiAnagrafici richiestaAnagrafici = new RichiestaDatiAnagrafici();
		richiestaAnagrafici.setCodiceFiscale(codFisc);

		DatiAnagrafici datiAnagrafici = visureService.richiediDatiAnagrafici(richiestaAnagrafici, helper.getUserPreferences(portletRequest));
		List<ComponentiNucleoFamiliare> componenti = datiAnagrafici.getComponentiNucleoFamiliare();
		for (int i = 0; i < componenti.size(); i++) {
			ComponentiNucleoFamiliare componente = componenti.get(i);
			if (codFisc != null && codFisc.equals(componente.getCodiceFiscale())) {
				model.addAttribute("componenteFamiglia", componente);
				break;
			}
		}

		DatiElettorali datiElettorali = visureService.richiediDatiElettorali(richiestaElettorali, helper.getUserPreferences(portletRequest));

		model.addAttribute("datiElettorali", datiElettorali);
		model.addAttribute("datiAnagrafici", datiAnagrafici);
		model.addAttribute("codiceFiscale", codFisc);

		Fascicolo fascicolo = new Fascicolo();
		fascicolo.setIdProfiloUtente(profiloUtente);
		fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio", null, portletRequest.getLocale()));
		fascicolo.setUserPreferences(helper.getUserPreferences(portletRequest));
		fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_VISURA_POSIZIONE_ELETTORALE);
		fascicolo.setRicercabileDaComune(false);
		fascicolo.setNumeroProtocollo(null);
		fascicolo.setChecksum(null);

		fascicoloService.saveNuovaRichiesta(fascicolo);
		auditManager.build();
		response.setRenderParameter("action", "renderVisura");
	}

	@ActionMapping(params = "action=inviaSegnalazione")
	public void inviaSegnalazioneVisura(@ModelAttribute DatiElettorali datiElettorali, ActionResponse response, PortletRequest portletRequest, ActionRequest actionRequest) {

		try {
			ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(actionRequest.getPortletSession());

			List<SegnalazioneFoglia> listaSegnalazioni = new ArrayList<SegnalazioneFoglia>();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			List<PosizioniElettorali> posizioniElettorali = datiElettorali.getPosizioniElettorali();

			if (posizioniElettorali != null && !posizioniElettorali.isEmpty()) {
				for (PosizioniElettorali posizioneElettorale : posizioniElettorali) {

					// TODO verificare lista posizioni elettorali nell'invio della segnalazione

					SegnalazioneFoglia nodo = new SegnalazioneFoglia(messageSource.getMessage("label.fascicoloUtente.servizio", null, actionRequest.getLocale()));
					nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.cf", null, actionRequest.getLocale()), profiloUtente.getCodiceFiscale()));
					nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.nome", null, actionRequest.getLocale()), posizioneElettorale.getNome()));
					nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.cognome", null, actionRequest.getLocale()), posizioneElettorale.getCognome()));
					nodo.getListaSegnalazioni()
							.add(new SegnalazioneFoglia(messageSource.getMessage("label.dataN", null, actionRequest.getLocale()), sdf.format(posizioneElettorale.getDataNascita().getTime())));
					nodo.getListaSegnalazioni()
							.add(new SegnalazioneFoglia(messageSource.getMessage("label.indRes", null, actionRequest.getLocale()),
									posizioneElettorale.getToponimoIndirizzo() + " " + posizioneElettorale.getDescrizioneVia() + ", " + posizioneElettorale.getNumeroCivico()
											+ (posizioneElettorale.getEsponente() != null ? " " + posizioneElettorale.getEsponente() : "")
											+ (posizioneElettorale.getPiano() != null ? " p." + posizioneElettorale.getPiano() : "")));
					nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.statoIscr", null, actionRequest.getLocale()),
							posizioneElettorale.getAnnoIscrizioneElett() != null ? messageSource.getMessage("label.iscritto", null, actionRequest.getLocale()) : ""));
					nodo.getListaSegnalazioni().add(
							new SegnalazioneFoglia(messageSource.getMessage("label.dataIscr", null, actionRequest.getLocale()), sdf.format(posizioneElettorale.getDataVerbaleIscrizione().getTime())));
					nodo.getListaSegnalazioni()
							.add(new SegnalazioneFoglia(messageSource.getMessage("label.annoIscr", null, actionRequest.getLocale()), "" + posizioneElettorale.getAnnoIscrizioneElett()));
					nodo.getListaSegnalazioni()
							.add(new SegnalazioneFoglia(messageSource.getMessage("label.verbaleIscr", null, actionRequest.getLocale()), posizioneElettorale.getNumVerbaleIscrizione()));
					nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.numSez", null, actionRequest.getLocale()), "" + posizioneElettorale.getNumeroSezione()));
					nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.listaGen", null, actionRequest.getLocale()), posizioneElettorale.getNumeroListaGenerale()));
					nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.listaSez", null, actionRequest.getLocale()), posizioneElettorale.getNumeroListaSezionale()));
					nodo.getListaSegnalazioni()
							.add(new SegnalazioneFoglia(messageSource.getMessage("label.descrizioneLista", null, actionRequest.getLocale()), posizioneElettorale.getDescrizioneLista()));
					nodo.getListaSegnalazioni()
							.add(new SegnalazioneFoglia(messageSource.getMessage("label.tipologiaElettore", null, actionRequest.getLocale()), posizioneElettorale.getTipoElettore()));
					nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.fascicolo", null, actionRequest.getLocale()), posizioneElettorale.getNumeroFascicolo()));
					SegnalazioneFoglia nodo1 = new SegnalazioneFoglia(messageSource.getMessage("label.tessera", null, actionRequest.getLocale()));
					nodo1.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.num", null, actionRequest.getLocale()), posizioneElettorale.getNumeroTesseraElettorale()));
					nodo1.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.dataRil", null, actionRequest.getLocale()),
							sdf.format(posizioneElettorale.getDataRilascioTesseraElett().getTime())));
					nodo.getListaSegnalazioni().add(nodo1);

					SegnalazioneFoglia nodo2 = new SegnalazioneFoglia(messageSource.getMessage("label.iscr", null, actionRequest.getLocale()));
					nodo2.getListaSegnalazioni()
							.add(new SegnalazioneFoglia(messageSource.getMessage("label.scrutatori", null, actionRequest.getLocale()), posizioneElettorale.getIscrizioneAlboScrutatori()
									? messageSource.getMessage("label.si", null, actionRequest.getLocale()) : messageSource.getMessage("label.no", null, actionRequest.getLocale())));
					nodo2.getListaSegnalazioni()
							.add(new SegnalazioneFoglia(messageSource.getMessage("label.presidenti", null, actionRequest.getLocale()), posizioneElettorale.getIscrizioneAlboPresidente()
									? messageSource.getMessage("label.si", null, actionRequest.getLocale()) : messageSource.getMessage("label.no", null, actionRequest.getLocale())));
					nodo.getListaSegnalazioni().add(nodo2);

					listaSegnalazioni.add(nodo);
				}
			}

			Segnalazione segnalazione = new Segnalazione();
			segnalazione.setListaSegnalazioni(listaSegnalazioni);
			segnalazione.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio", null, actionRequest.getLocale()));
			segnalazione.setServizioCode(PortletConstants.CODICE_SERVIZIO_VISURA_POSIZIONE_ELETTORALE);

			PortletSession session = actionRequest.getPortletSession();
			session.setAttribute(PortletConstants.SEGNALAZIONE_SHARED_ATTRIBUTE, segnalazione, PortletSession.APPLICATION_SCOPE);
			response.sendRedirect(segnalazioniPageUrl);
		}
		catch (Exception e) {
			log.error("inviaSegnalazioneVisura :: " + e.getMessage(), e);
		}
	}

	/**
	 * Presenta la visura a video.
	 *
	 * @param model
	 * @param request 
	 * @param session 
	 * @return l'ID della view per visualizzare il fascicolo
	 * @throws Exception 
	 * @throws ServiceLayerException 
	 */
	@RenderMapping(params = "action=renderVisura")
	public String renderVisura(Model model, PortletRequest request, PortletSession session) throws ServiceLayerException, Exception {
		log.debug("Model = " + model);
		 final String uuidOperazione=(String)session.getAttribute("UUID");
		 		
		
				DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();
				
				//MS
				tempiMediDto = DwhTempiMediUtil.get(dwhService)
				.go_EndTime(uuidOperazione)
				.setCodServizio(PortletConstants.CODICE_SERVIZIO_VISURA_POSIZIONE_ELETTORALE)
				.setNomeServizio("VISURA POSIZIONE ELETTORALE")
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
					.addOperazioneRichiesta("VISURA POSIZIONE ELETTORALE  - STEP FINALE")
					.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
					.addPaginaCorrente(helper.getCurrentPageName(request))
					.addEsitoSuccess("SUCCESS - OPERAZIONE CONCLUSA")
					.addServizioTimeString()
					.addServizioUuidTransazione(uuidOperazione)
					.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_VISURA_POSIZIONE_ELETTORALE)
					.setOrigin(Origin.getIp(request))
					.addCallGeo()
					.build();
				
				
		return toLocalViewPath("mostraVisura");
	}

	/**
	 * Utility method per calcolare e restituire il view ID all'interno della corretta alberatura di
	 * sottodirectory delle JSP: in un certo senso si tratta del namespace delle view locali a
	 * questo controller. Ad esempio: <code> toLocalViewPath( "home" ) </code> restituirà <code>
	 * "/visuraposizioneelettorale/home" </code>
	 *
	 * @param viewName l'ID della view locale
	 * @return il view ID completo del path a cui la vista appartiene
	 */
	private static String toLocalViewPath(String viewName) {
		return "visuraposizioneelettorale/" + viewName;
	}
}
