package it.osapulie.anagrafe.web.portlet.visuraposizioneanagrafica.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import eng.tz.la.core.AuditManager;
import eng.tz.la.core.Export;
import eng.tz.la.model.OperationExportFile;
import it.osapulie.anagrafe.service.AnagrafeCommonService;
import it.osapulie.anagrafe.service.AuditDwhService;
import it.osapulie.anagrafe.service.VisureService;
import it.osapulie.anagrafe.web.portlet.util.impl.PortletConstants;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici.ComponentiNucleoFamiliare;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici.ComponentiNucleoFamiliare.PensioniList;
import it.osapulie.anagrafe.web.ws.output.types.RichiestaDatiAnagrafici;
import it.osapulie.domain.Audit;
import it.osapulie.domain.Comune;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.dto.Fascicolo;
import it.osapulie.persistence.AuditRepository;
import it.osapulie.service.CommonService;
import it.osapulie.service.ComuneService;
import it.osapulie.service.DelegaService;
import it.osapulie.service.DwhService;
import it.osapulie.service.FascicoloUtenteService;
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
import it.osapulie.web.portlet.util.PortletHelper;
import it.osapulie.web.portlet.util.impl.LiferayPortletHelperImpl;

/**
 * Portlet controller per il servizio visura posizione anagrafica.
 *
 * @author Birtolo Maria MIchela
 */
@Controller("visuraPosizioneAnagraficaPortletController")
@RequestMapping("view")
@SessionAttributes("datiAnagrafici")
public class VisuraPosizioneAnagraficaPortletController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(VisuraPosizioneAnagraficaPortletController.class);

	@Inject
	private VisureService visureService;

	 @Inject
	 private PortletHelper helper;

	@Inject
	private FascicoloUtenteService fascicoloService;

	@Autowired
	public ResourceBundleMessageSource messageSource;

	@Inject
	private ComuneService comuneService;

	@Inject
	private AnagrafeCommonService anagrafeCommonService;

	@Value("#{applicationProperties.segnalazioniPageUrl}")
	private String segnalazioniPageUrl;
	
	@Inject
	private AuditRepository auditRepository;
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
	 * @return il view ID da renderizzare.
	 */
	@RenderMapping
	public String homePage(Model model, PortletSession session, PortletRequest portletRequest) throws Exception {

		log.debug("homePage ");

		UserPreferences userPreferences = helper.getUserPreferences(portletRequest);
		String codiceFiscale = userPreferences.getCodiceFiscaleServizio();
		log.debug("userCF=" + codiceFiscale);
		ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(session);
		RichiestaDatiAnagrafici richiesta = new RichiestaDatiAnagrafici();
		richiesta.setCodiceFiscale(codiceFiscale);

		DatiAnagrafici dati = visureService.richiediDatiAnagrafici(richiesta, helper.getUserPreferences(portletRequest));

		model.addAttribute("datiAnagrafici", dati);
		List<ComponentiNucleoFamiliare> componenti = dati.getComponentiNucleoFamiliare();
		for (int i = 0; i < componenti.size(); i++) {
			ComponentiNucleoFamiliare componente = componenti.get(i);
			if (codiceFiscale != null && codiceFiscale.equals(componente.getCodiceFiscale())) {
				anagrafeCommonService.addInfoAggiuntiveComponenteToModel(model, componente);
				model.addAttribute("componenteFamiglia", componente);
				// Caricamento comune e provincia nascita
				String codiceIstatComuneNascita = componente.getCodiceIstatComuneNascita();
				if (codiceIstatComuneNascita != null) {
					Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(codiceIstatComuneNascita);
					if (comuneByCodiceISTAT != null) {
						model.addAttribute("comuneNascita", comuneByCodiceISTAT.getDenominazione());
						model.addAttribute("provinciaNascita", comuneByCodiceISTAT.getProvincia().getDenominazioneProvincia());
					}
				}
				break;
			}
		}

		Fascicolo fascicolo = new Fascicolo();
		fascicolo.setIdProfiloUtente(profiloUtente);
		fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio", null, portletRequest.getLocale()));
		fascicolo.setUserPreferences(helper.getUserPreferences(portletRequest));
		fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_VISURA_POSIZIONE_ANAGRAFICA);
		fascicolo.setRicercabileDaComune(false);
		fascicolo.setNumeroProtocollo(null);
		fascicolo.setChecksum(null);

		fascicoloService.saveNuovaRichiesta(fascicolo);

		final String uuidOperazione=UUID.randomUUID().toString();
		session.setAttribute("UUID", uuidOperazione);
		
		DwhServizioAttributeDTO servizioAttributeDto = new DwhServizioAttributeDTO();
		DwhDataminingDTO dataminingDto = new DwhDataminingDTO();
		DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();
		
		servizioAttributeDto = DwhServiceAttributeUtil.get(commonService, comuneService, delegaService, servizioService, userPreferences, portletRequest, dwhService)
		.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_VISURA_POSIZIONE_ANAGRAFICA)
		.setUUID(uuidOperazione)
		.getServizioAttribute();
		
		dataminingDto = DwhDataminingUtil.get(codiceFiscale, dwhService)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_VISURA_POSIZIONE_ANAGRAFICA)
		.setUuidOperazione(uuidOperazione)
		.getDatamining();
		
		 tempiMediDto = DwhTempiMediUtil.get(dwhService)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_VISURA_POSIZIONE_ANAGRAFICA)
		.setNomeServizio("VISURA POSIZIONE ANAGRAFICA")
		.setUuid(uuidOperazione).go_StartTime().getTempiMedi();

		 auditDwhService.invokeAuditService(servizioAttributeDto, dataminingDto, tempiMediDto);
		
		//MS
		tempiMediDto = DwhTempiMediUtil.get(dwhService)
		.go_EndTime(uuidOperazione)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_VISURA_POSIZIONE_ANAGRAFICA)
		.setNomeServizio("VISURA POSIZIONE ANAGRAFICA")
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
			.addOperazioneRichiesta("VISURA POSIZIONE ANAGRAFICA  - STEP FINALE")
			.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(portletRequest))
			.addPaginaCorrente(helper.getCurrentPageName(portletRequest))
			.addEsitoSuccess("SUCCESS - OPERAZIONE CONCLUSA")
			.addServizioTimeString()
			.addServizioUuidTransazione(uuidOperazione)
			.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_VISURA_POSIZIONE_ANAGRAFICA)
			.setOrigin(Origin.getIp(portletRequest))
			.addCallGeo()
			.build();
		 
		 
		return toLocalViewPath("mostraVisura");
	}

	@RequestMapping(params = "action=visura")
	public void visuraPosizioneAnagrafica(@RequestParam("id") String id, Model model, @RequestParam(required = false, value = "invia") String invia, ActionResponse response, PortletSession session,
			@ModelAttribute("datiAnagrafici") DatiAnagrafici dati, PortletRequest portletRequest) throws Exception {
		
		String uuidStr=UUID.randomUUID().toString();
		session.setAttribute("UUID", uuidStr);
		AuditManager auditManager= AuditConfiguration.configure()
					.audit()
					.addInizioOperazione()
					.addUuidOperazione(uuidStr)
					.addOperazioneRichiesta("VISURA POSIZIONE ANAGRAFICA")
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
		
		if (invia != null) {

			log.debug("visuraPosizioneAnagrafica request ID= ..." + id);
			RichiestaDatiAnagrafici richiesta = new RichiestaDatiAnagrafici();
			richiesta.setCodiceFiscale(id);

			ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(session);

			List<ComponentiNucleoFamiliare> componenti = dati.getComponentiNucleoFamiliare();
			for (int i = 0; i < componenti.size(); i++) {
				ComponentiNucleoFamiliare componente = componenti.get(i);
				if (id != null && id.equals(componente.getCodiceFiscale())) {
					anagrafeCommonService.addInfoAggiuntiveComponenteToModel(model, componente);
					model.addAttribute("componenteFamiglia", componente);
					// Caricamento comune e provincia nascita
					String codiceIstatComuneNascita = componente.getCodiceIstatComuneNascita();
					if (codiceIstatComuneNascita != null) {
						Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(codiceIstatComuneNascita);
						if (comuneByCodiceISTAT != null) {
							model.addAttribute("comuneNascita", comuneByCodiceISTAT.getDenominazione());
							model.addAttribute("provinciaNascita", comuneByCodiceISTAT.getProvincia().getDenominazioneProvincia());
						}
					}
					break;
				}
			}

			Fascicolo fascicolo = new Fascicolo();
			fascicolo.setIdProfiloUtente(profiloUtente);
			fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio", null, portletRequest.getLocale()));
			fascicolo.setUserPreferences(helper.getUserPreferences(portletRequest));
			fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_VISURA_POSIZIONE_ANAGRAFICA);
			fascicolo.setRicercabileDaComune(false);
			fascicolo.setNumeroProtocollo(null);
			fascicolo.setChecksum(null);

			fascicoloService.saveNuovaRichiesta(fascicolo);

			UserPreferences userPreferences = helper.getUserPreferences(portletRequest);
			final String uuidOperazione=UUID.randomUUID().toString();
			session.setAttribute("UUID", uuidOperazione);
			
			DwhServizioAttributeDTO servizioAttributeDto = new DwhServizioAttributeDTO();
			DwhDataminingDTO dataminingDto = new DwhDataminingDTO();
			DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();
			
			servizioAttributeDto = DwhServiceAttributeUtil.get(commonService, comuneService, delegaService, servizioService, userPreferences, portletRequest, dwhService)
			.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_VISURA_POSIZIONE_ANAGRAFICA)
			.setUUID(uuidOperazione)
			.getServizioAttribute();
			
			dataminingDto = DwhDataminingUtil.get(id, dwhService)
			.setCodServizio(PortletConstants.CODICE_SERVIZIO_VISURA_POSIZIONE_ANAGRAFICA)
			.setUuidOperazione(uuidOperazione)
			.getDatamining();
			
			 tempiMediDto = DwhTempiMediUtil.get(dwhService)
			.setCodServizio(PortletConstants.CODICE_SERVIZIO_VISURA_POSIZIONE_ANAGRAFICA)
			.setUuid(uuidOperazione).go_StartTime().getTempiMedi();

			 auditDwhService.invokeAuditService(servizioAttributeDto, dataminingDto, tempiMediDto);
			
			//MS
			tempiMediDto = DwhTempiMediUtil.get(dwhService)
			.go_EndTime(uuidOperazione)
			.setCodServizio(PortletConstants.CODICE_SERVIZIO_VISURA_POSIZIONE_ANAGRAFICA)
			.setNomeServizio("VISURA POSIZIONE ANAGRAFICA")
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
				.addOperazioneRichiesta("VISURA POSIZIONE ANAGRAFICA  - STEP FINALE")
				.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(portletRequest))
				.addPaginaCorrente(helper.getCurrentPageName(portletRequest))
				.addEsitoSuccess("SUCCESS - OPERAZIONE CONCLUSA")
				.addServizioTimeString()
				.addServizioUuidTransazione(uuidOperazione)
				.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_VISURA_POSIZIONE_ANAGRAFICA)
				.setOrigin(Origin.getIp(portletRequest))
				.addCallGeo()
				.build();
			
			 
			
			response.setRenderParameter("action", "renderVisura");
		}
		else {

			try {
				ComponentiNucleoFamiliare componente = null;
				List<ComponentiNucleoFamiliare> componenti = dati.getComponentiNucleoFamiliare();
				for (int i = 0; i < componenti.size(); i++) {
					componente = componenti.get(i);
					if (id != null && id.equals(componente.getCodiceFiscale())) {
						break;
					}
				}

				if (componente != null) {

					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					List<SegnalazioneFoglia> listaSegnalazioni = new ArrayList<SegnalazioneFoglia>();

					SegnalazioneFoglia nodo = new SegnalazioneFoglia(messageSource.getMessage("label.fascicoloUtente.servizio", null, portletRequest.getLocale()));
					nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.cf", null, portletRequest.getLocale()), componente.getCodiceFiscale()));
					nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.nome", null, portletRequest.getLocale()), componente.getNome()));
					nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.cognome", null, portletRequest.getLocale()), componente.getCognome()));
					nodo.getListaSegnalazioni()
							.add(new SegnalazioneFoglia(messageSource.getMessage("label.dataN", null, portletRequest.getLocale()), sdf.format(componente.getDataNascita().getTime())));
					nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.sesso", null, portletRequest.getLocale()), componente.getSesso()));

					String indirizzo = (dati.getToponimoIndirizzo() + " " + dati.getDescrizioneVia() + " " + dati.getNumeroCivico()).trim();
					if (dati.getEsponente() != null && !dati.getEsponente().isEmpty()) {
						indirizzo += " " + dati.getEsponente();
					}
					if (dati.getPiano() != null && !dati.getPiano().isEmpty()) {
						indirizzo += " p. " + dati.getPiano();
					}
					if (dati.getScala() != null && !dati.getScala().isEmpty()) {
						indirizzo += " s. " + dati.getScala();
					}
					if (dati.getInterno() != null && !dati.getInterno().isEmpty()) {
						indirizzo += " int. " + dati.getInterno();
					}
					nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.indRes", null, portletRequest.getLocale()), indirizzo));

					nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.statCiv", null, portletRequest.getLocale()), componente.getStatoCivile()));
					nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.coniuge", null, portletRequest.getLocale()), componente.getCognomeNomeConiuge()));
					nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.parentela", null, portletRequest.getLocale()), componente.getRelazioneParentela()));

					String cittadinanza = componente.isCittadinanzaItaliana() ? "SI" : "NO";
					nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.cittadinanza", null, portletRequest.getLocale()), cittadinanza));

					nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.prof", null, portletRequest.getLocale()), componente.getProfessione()));
					nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.studio", null, portletRequest.getLocale()), componente.getTitoloStudio()));
					nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.numCI", null, portletRequest.getLocale()), componente.getNumeroCartaIdentita()));
					Calendar dataCartaIdentita = componente.getDataCartaIdentita();
					if (dataCartaIdentita != null) {
						Date time = dataCartaIdentita.getTime();
						nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.dataCI", null, portletRequest.getLocale()), sdf.format(time)));
					}
					else {
						nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.dataCI", null, portletRequest.getLocale()), ""));
					}

					if (componente.getValidaCartaIdentita() != null) {
						String valCI = componente.getValidaCartaIdentita() ? "SI" : "NO";
						nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.valCI", null, portletRequest.getLocale()), valCI));
					}
					else {
						nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.valCI", null, portletRequest.getLocale()), ""));
					}

					String numeroAttoNascita = "";
					if (componente.getNumeroAttoNascita() != null) {
						numeroAttoNascita += "n. " + componente.getNumeroAttoNascita().toString();
					}
					if (componente.getParteNascita() != null && !componente.getParteNascita().isEmpty()) {
						numeroAttoNascita += " p. " + componente.getParteNascita();
					}
					if (componente.getSerieNascita() != null && !componente.getSerieNascita().isEmpty()) {
						numeroAttoNascita += " s. " + componente.getSerieNascita();
					}
					if (componente.getAnnoAttoNascita() != null) {
						numeroAttoNascita += " " + messageSource.getMessage("label.anno", null, portletRequest.getLocale()) + " " + componente.getAnnoAttoNascita();
					}
					if (componente.getUfficioNascita() != null && !componente.getUfficioNascita().isEmpty()) {
						numeroAttoNascita += " " + messageSource.getMessage("label.ufficio", null, portletRequest.getLocale()) + " " + componente.getUfficioNascita();
					}

					// Caricamento comune da codice ISTAT
					if (componente.isCittadinanzaItaliana() && componente.getCodiceIstatComuneNascita() != null) {
						Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(componente.getCodiceIstatComuneNascita());
						numeroAttoNascita += " " + messageSource.getMessage("label.comune", null, portletRequest.getLocale()) + " " + comuneByCodiceISTAT.getDenominazione();
						numeroAttoNascita += " " + messageSource.getMessage("label.prov", null, portletRequest.getLocale()) + " " + comuneByCodiceISTAT.getProvincia().getDenominazioneProvincia();
					}
					else {
						numeroAttoNascita += " " + messageSource.getMessage("label.comune", null, portletRequest.getLocale()) + " " + componente.getDescrizioneComuneEsteroNascita();
						numeroAttoNascita += " " + messageSource.getMessage("label.statoEsteroNascita", null, portletRequest.getLocale()) + " " + componente.getStatoEsteroNascita();
					}

					nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.attoN", null, portletRequest.getLocale()), numeroAttoNascita.trim()));

					String numeroAttoMatrimonio = "";
					if (componente.getNumeroAttoMatrimonio() != null) {
						numeroAttoMatrimonio += "n. " + componente.getNumeroAttoMatrimonio().toString();
					}
					if (componente.getParteMatrimonio() != null && !componente.getParteMatrimonio().isEmpty()) {
						numeroAttoMatrimonio += " p. " + componente.getParteMatrimonio();
					}
					if (componente.getSerieMatrimonio() != null && !componente.getSerieMatrimonio().isEmpty()) {
						numeroAttoMatrimonio += " s. " + componente.getSerieMatrimonio();
					}
					if (componente.getAnnoMatrimonio() != null) {
						numeroAttoMatrimonio += " " + messageSource.getMessage("label.anno", null, portletRequest.getLocale()) + " " + componente.getAnnoMatrimonio();
					}
					if (componente.getUfficioMatrimonio() != null && !componente.getUfficioMatrimonio().isEmpty()) {
						numeroAttoMatrimonio += " " + messageSource.getMessage("label.ufficio", null, portletRequest.getLocale()) + " " + componente.getUfficioMatrimonio();
					}
					if (componente.getLuogoMatrimonio() != null && !componente.getLuogoMatrimonio().isEmpty()) {
						numeroAttoMatrimonio += " " + messageSource.getMessage("label.luogo", null, portletRequest.getLocale()) + " " + componente.getLuogoMatrimonio();
					}

					// Caricamento comune da codice ISTAT
					if (componente.getCodiceIstatComuneMatrimonio() != null) {
						Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(componente.getCodiceIstatComuneMatrimonio());
						numeroAttoMatrimonio += " " + messageSource.getMessage("label.comune", null, portletRequest.getLocale()) + " " + comuneByCodiceISTAT.getDenominazione();
					}

					nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.attoMatrimonio", null, portletRequest.getLocale()), numeroAttoMatrimonio.trim()));

					// Caricamento pensioni
					List<PensioniList> pensioniList = componente.getPensioniList();
					if (pensioniList != null) {
						SegnalazioneFoglia nodoPensioni = new SegnalazioneFoglia(messageSource.getMessage("label.pensioni", null, portletRequest.getLocale()));
						for (int i = 0; i < pensioniList.size(); i++) {
							PensioniList pensione = pensioniList.get(i);
							SegnalazioneFoglia nodoNumeroPensione = new SegnalazioneFoglia(messageSource.getMessage("label.numeroPensione", null, portletRequest.getLocale()) + " " + (i + 1),
									pensione.getNumeroPensione());
							SegnalazioneFoglia nodoCodicePensione = new SegnalazioneFoglia(messageSource.getMessage("label.codicePensione", null, portletRequest.getLocale()) + " " + (i + 1),
									pensione.getCodicePensione());

							nodoPensioni.getListaSegnalazioni().add(nodoNumeroPensione);
							nodoPensioni.getListaSegnalazioni().add(nodoCodicePensione);
						}
						nodo.getListaSegnalazioni().add(nodoPensioni);
					}

					listaSegnalazioni.add(nodo);
					Segnalazione segnalazione = new Segnalazione();
					segnalazione.setListaSegnalazioni(listaSegnalazioni);
					segnalazione.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio", null, portletRequest.getLocale()));
					segnalazione.setServizioCode(PortletConstants.CODICE_SERVIZIO_VISURA_POSIZIONE_ANAGRAFICA);

					session.setAttribute(PortletConstants.SEGNALAZIONE_SHARED_ATTRIBUTE, segnalazione, PortletSession.APPLICATION_SCOPE);
					response.sendRedirect(segnalazioniPageUrl);
				}
			}
			catch (Exception e) {
				log.error("visuraPosizioneAnagrafica :: " + e.getMessage(), e);
				auditManager
				.addFineOperazione()
				.addEsitoError()
				.addInfo("Exception", e.getMessage())
				.build();
			}

		}
			auditManager.build();
	}

	/**
	 * Presenta la visura a video.
	 *
	 * @param model
	 * @param request 
	 * @param session 
	 * @return l'ID della view per visualizzare il fascicolo
	 */
	@RenderMapping(params = "action=renderVisura")
	public String renderVisura(Model model, PortletRequest request, PortletSession session) {
		log.debug("Model = " + model);
		AuditConfiguration.configure().audit().addUuidOperazione((String)session.getAttribute("UUID"))
				.addOperazioneRichiesta("VISURA POSIZIONE ANAGRAFICA")
				.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
				.addPaginaCorrente(helper.getCurrentPageName(request))
				.addFineOperazione()
				.addEsitoSuccess()
				.setOrigin(Origin.getIp(request))
				.build();
		return toLocalViewPath("mostraVisura");
	}

	@ActionMapping(params = "action=inviaSegnalazione")
	public void segnalazioneAnagrafica(@RequestParam("id") String id, Model model, ActionResponse response, PortletSession session, @ModelAttribute("datiAnagrafici") DatiAnagrafici dati,
			PortletRequest portletRequest) throws Exception {
		List<ComponentiNucleoFamiliare> componenti = dati.getComponentiNucleoFamiliare();
		for (int i = 0; i < componenti.size(); i++) {
			ComponentiNucleoFamiliare componente = componenti.get(i);
			if (id != null && id.equals(componente.getCodiceFiscale())) {
				System.out.println(componente.getCodiceFiscale());
				System.out.println(componente.getNome());
				break;
			}
		}
		response.setRenderParameter("action", "renderVisura");
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
		return "visuraposizioneanagrafica/" + viewName;
	}
}
