/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.profiloutente.web.portlet.gestioneazienda.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserModel;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import eng.tz.la.core.AuditManager;
import eng.tz.la.core.Export;
import eng.tz.la.model.OperationExportFile;
import it.osapulie.domain.Audit;
import it.osapulie.domain.Azienda;
import it.osapulie.domain.Comune;
import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.Indirizzo;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.ProfiloUtenteCittadinoAzienda;
import it.osapulie.enumeration.TipoAzienda;
import it.osapulie.infrastructure.security.OSApulieUserDetails;
import it.osapulie.persistence.AuditRepository;
import it.osapulie.persistence.ProfiloUtenteCittadinoRepository;
import it.osapulie.profiloutente.model.view.AziendaModel;
import it.osapulie.profiloutente.utils.AziendaModelUtil;
import it.osapulie.profiloutente.web.portlet.gestioneazienda.validator.AziendaValidator;
import it.osapulie.service.AziendaService;
import it.osapulie.service.CommonService;
import it.osapulie.service.ComuneISAService;
import it.osapulie.service.ComuneService;
import it.osapulie.service.ProfiloUtenteService;
import it.osapulie.shared.constant.PortalConstants;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.util.audit.ApplicationContextUtil;
import it.osapulie.util.audit.AuditConfiguration;
import it.osapulie.util.audit.Origin;
import it.osapulie.web.portlet.util.PortletConstants;
import it.osapulie.web.portlet.util.PortletHelper;
import it.osapulie.web.portlet.util.PortletUtils;
import it.osapulie.web.portlet.util.RoleConstants;

/**
 * Portlet controller per la gestione delle aziende correlate al proprio profilo utente.
 *
 * @author Gianluca Pindinelli
 */
@Controller("gestioneAziendaPortletController")
@RequestMapping("view")
@SessionAttributes("aziendaModel")
public class GestioneAziendaPortletController {

	private final Logger log = LoggerFactory.getLogger(GestioneAziendaPortletController.class);
	
	public static final String SERVIZIO = "TEST";

	@Inject
	private PortletHelper helper;

	@Inject
	private ComuneISAService comuneISAService;

	@Inject
	private ComuneService comuneService;

	@Inject
	private ProfiloUtenteService profiloUtenteService;

	@Autowired
	@Qualifier("aziendaValidator")
	private AziendaValidator aziendaValidator;

	@Autowired
	public ResourceBundleMessageSource messageSource;

	@Autowired
	private AziendaService aziendaService;

	@Autowired
	private CommonService commonService;
	
	@Autowired
	ProfiloUtenteCittadinoRepository ProfiloUtenteCittadinoRep;

	@Value("#{applicationProperties['gestioneutenti.page.url']}")
	private String gestioneUtentiPageUrl;
	
	@Inject
	private AuditRepository auditRepository;

	/**
	 * Default entry quando la portlet viene visualizzata per la prima volta.
	 *
	 * @param model il view model da popolare
	 * @return il view ID da renderizzare
	 */
	@RenderMapping
	public String homePage(Model model, PortletRequest request) {
		return toLocalViewPath("home");
	}

	@ModelAttribute("aziende")
	public List<Azienda> getAziende() {

		ProfiloUtenteCittadino profiloUtenteCittadino = helper.getOsApulieUserDetails().getProfiloUtenteCittadino();
		// Caricamento profilo utente per avere i dati aggiornati
		ProfiloUtenteCittadino profiloUtenteCittadinoById = profiloUtenteService.getProfiloUtenteCittadinoById(profiloUtenteCittadino.getId());
		List<Azienda> aziende = profiloUtenteCittadinoById.getAziende();
		// set InUso
		if (aziende != null) {
			for (Azienda azienda : aziende) {
				aziendaService.setInUso(azienda);
			}
		}

		return aziende;
	}

	private AziendaModel getAziendaModel(String idAzienda) {

		Azienda azienda = null;
		if (idAzienda != null) {
			azienda = aziendaService.getAziendaByPk(Long.parseLong(idAzienda));
		}
		else {
			azienda = new Azienda();
		}

		AziendaModel aziendaModel = AziendaModelUtil.getAziendaModelFromAzienda(azienda);
		return aziendaModel;
	}

	@RenderMapping(params = "action=editAzienda")
	public String editAzienda(@RequestParam(required = false) String idAzienda,
			@RequestParam(value = PortletConstants.GESTIONE_UTENTI_CODICE_FISCALE_REQUEST_PARAMETER, required = false) String codiceFiscale, Model model, PortletRequest request, PortletSession session) throws Exception {

		String uuidStr=UUID.randomUUID().toString();
		session.setAttribute("UUID", uuidStr);
		AuditManager auditManager= AuditConfiguration.configure()
					.audit()
					.addInizioOperazione()
					.addUuidOperazione(uuidStr)
					.addOperazioneRichiesta("Gestione Azienda/CAF/Libero Professionista")
					.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
					.setOrigin(Origin.getIp(request))
					.addPaginaCorrente(helper.getCurrentPageName(request));
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
		AziendaModel aziendaModel = getAziendaModel(idAzienda);

		aziendaModel.setCfUtenteAssociato(codiceFiscale);

		model.addAttribute("aziendaModel", aziendaModel);
		auditManager.build();
		return toLocalViewPath("editAzienda");
	}

	@RenderMapping(params = "action=editAziendaFail")
	public String editAzienda() {
		return toLocalViewPath("editAzienda");
	}

	/**
	 * Salva l'{@link Azienda} associata all'utente.
	 *
	 * @param aziendaModel
	 * @param bindingResult
	 * @param model
	 * @param request
	 * @param response
	 * @param sessionStatus
	 */
	@ActionMapping(params = "action=saveAzienda")
	public void saveAzienda(@ModelAttribute AziendaModel aziendaModel, BindingResult bindingResult, Model model, ActionRequest request, ActionResponse response, SessionStatus sessionStatus, PortletSession session, PortletRequest portletRequest) {

		log.debug("saveAzienda :: entering method");
		AuditManager auditManager= AuditConfiguration
				.configure()
				.audit()
				.addUuidOperazione((String)session.getAttribute("UUID"))
				.addOperazioneRichiesta("Modifica Azienda/CAF/Libero Professionista")
				.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
				.setOrigin(Origin.getIp(request))
				.addPaginaCorrente(helper.getCurrentPageName(request));
		UserPreferences userPreferences = helper.getUserPreferences(request);
		String subjectAssociazione = messageSource.getMessage("mail.cittadino.communicationSubject", new String[] { SERVIZIO }, null);
		String subjectNuovaAzienda = messageSource.getMessage("mail.cittadino.communicationSubjectNewAzienda", new String[] { SERVIZIO }, null);
		
		OSApulieUserDetails osApulieUserDetails = helper.getOsApulieUserDetails();
		String[] cfUtenteAssociatoButtonStrings = request.getParameterMap().get("cfUtenteAssociatoButton");
		String[] ricercaDelegato = request.getParameterMap().get("ricercaDelegato");
		try {
			// Ricerca delegato
			long idAzienda = aziendaModel.getIdAzienda();
			if (ricercaDelegato != null) {
				String codiceFiscaleDelegato = null;
				String nomeDelegato = null;
				String cognomeDelegato = null;
				if (aziendaModel.getCodiceFiscaleDelegato() != null && !aziendaModel.getCodiceFiscaleDelegato().isEmpty()) {
					codiceFiscaleDelegato = aziendaModel.getCodiceFiscaleDelegato();
				}
				if (aziendaModel.getNomeDelegato() != null && !aziendaModel.getNomeDelegato().isEmpty()) {
					nomeDelegato = aziendaModel.getNomeDelegato();
				}
				if (aziendaModel.getCognomeDelegato() != null && !aziendaModel.getCognomeDelegato().isEmpty()) {
					cognomeDelegato = aziendaModel.getCognomeDelegato();
				}

				Set<ProfiloUtenteCittadino> profiloUtenteCittadinoSet = null;

				// Ricerca in lista in memoria
				List<ProfiloUtenteCittadino> profiliUtenteAssociati = aziendaModel.getProfiliUtenteAssociati();
				if (profiliUtenteAssociati != null) {
					profiloUtenteCittadinoSet = new HashSet<ProfiloUtenteCittadino>();
					if (codiceFiscaleDelegato != null || nomeDelegato != null || cognomeDelegato != null) {
						for (ProfiloUtenteCittadino profiloUtenteCittadino : profiliUtenteAssociati) {
							boolean codiceFiscaleFound = true;
							boolean nomeDelegatoFound = true;
							boolean cognomeDelegatoFound = true;
							if (codiceFiscaleDelegato != null) {
								codiceFiscaleFound = profiloUtenteCittadino.getCodiceFiscale().toLowerCase().contains(codiceFiscaleDelegato.toLowerCase());
							}
							if (nomeDelegato != null) {
								nomeDelegatoFound = profiloUtenteCittadino.getNome().toLowerCase().contains(nomeDelegato.toLowerCase());
							}
							if (cognomeDelegato != null) {
								cognomeDelegatoFound = profiloUtenteCittadino.getCognome().toLowerCase().contains(cognomeDelegato.toLowerCase());
							}
							if (codiceFiscaleFound && nomeDelegatoFound && cognomeDelegatoFound) {
								profiloUtenteCittadinoSet.add(profiloUtenteCittadino);
							}
						}
					}
					else {
						profiloUtenteCittadinoSet = new HashSet<ProfiloUtenteCittadino>(profiliUtenteAssociati);
					}
				}

				aziendaModel.setProfiliUtenteAssociatiSearch(profiloUtenteCittadinoSet);
				model.addAttribute("aziendaModel", aziendaModel);
				response.setRenderParameter("action", "editAziendaFail");
				return;
			}

			// Aggiunta utente cittadino ad professionista
			if (cfUtenteAssociatoButtonStrings != null) {
				String cfUtenteAssociato = aziendaModel.getCfUtenteAssociato();
				if (cfUtenteAssociato != null && !cfUtenteAssociato.equals("")) {

					String checkCodiceFiscale = PortletUtils.checkCodiceFiscale(cfUtenteAssociato);
					if (checkCodiceFiscale != null) {
						model.addAttribute("formError", messageSource.getMessage("error.verificareDatiInseriti", null, request.getLocale()));
						bindingResult.rejectValue("cfUtenteAssociato", null, checkCodiceFiscale);
						response.setRenderParameter("action", "editAziendaFail");
						return;
					}

					// Verifica utente != utente loggato
					if (cfUtenteAssociato.trim().equals(osApulieUserDetails.getProfiloUtenteCittadino().getCodiceFiscale())) {
						bindingResult.rejectValue("cfUtenteAssociato", "error.profiloUtenteCittadinoByCf.same.logged");
						model.addAttribute("formError", messageSource.getMessage("error.verificareDatiInseriti", null, request.getLocale()));
						response.setRenderParameter("action", "editAziendaFail");
						return;
					}
					// Ricerca utente nel sistema
					ProfiloUtenteCittadino profiloUtenteCittadinoByCf = profiloUtenteService.getProfiloUtenteCittadinoByCf(cfUtenteAssociato);
					if (profiloUtenteCittadinoByCf != null) {
						// Verifica utente già associato
						List<ProfiloUtenteCittadino> profiliUtenteAssociati = aziendaModel.getProfiliUtenteAssociati();
						if (profiliUtenteAssociati == null) {
							profiliUtenteAssociati = new ArrayList<ProfiloUtenteCittadino>();
							aziendaModel.setProfiliUtenteAssociati(profiliUtenteAssociati);
						}
						for (ProfiloUtenteCittadino profiloUtenteCittadino : profiliUtenteAssociati) {
							if (profiloUtenteCittadino.getId().longValue() == profiloUtenteCittadinoByCf.getId().longValue()) {
								bindingResult.rejectValue("cfUtenteAssociato", "error.profiloUtenteCittadinoByCf.already.associated");
								model.addAttribute("formError", messageSource.getMessage("error.verificareDatiInseriti", null, request.getLocale()));
								response.setRenderParameter("action", "editAziendaFail");
								return;
							}
						}

						// Aggiunta utente
						profiliUtenteAssociati.add(profiloUtenteCittadinoByCf);
						List<ProfiloUtenteCittadino> listaUtentiByCf = new ArrayList<ProfiloUtenteCittadino>();
						listaUtentiByCf.add(profiloUtenteCittadinoByCf);
						aziendaModel.setProfiliUtenteAssociatiSearch(new HashSet<ProfiloUtenteCittadino>(profiliUtenteAssociati));
						aziendaModel.setCfUtenteAssociato(null);
						response.setRenderParameter("action", "editAziendaFail");
						return;
					}
					else {
						// Se CAF può aggiungere l'utente manualmente
						if (aziendaModel.getTipoAzienda().equals(TipoAzienda.CAF.toString())) {
							bindingResult.rejectValue("cfUtenteAssociato", "error.profiloUtenteCittadinoByCf.CAF.null");

							Map<String, String> parameters = new HashMap<String, String>();
							parameters.put("action", "editAzienda");
							parameters.put("idAzienda", String.valueOf(idAzienda));
							model.addAttribute("gestioneUtentiPageUrl", commonService.getGestioneUtentiUrl(request, cfUtenteAssociato, parameters, null));

							model.addAttribute("cfProfiloUtenteCittadino", cfUtenteAssociato);
						}
						else {
							bindingResult.rejectValue("cfUtenteAssociato", "error.profiloUtenteCittadinoByCf.null");
						}
						model.addAttribute("formError", messageSource.getMessage("error.verificareDatiInseriti", null, request.getLocale()));
						response.setRenderParameter("action", "editAziendaFail");
						return;
					}
				}
				else {
					bindingResult.rejectValue("cfUtenteAssociato", "error.label.required");
					model.addAttribute("formError", messageSource.getMessage("error.verificareDatiInseriti", null, request.getLocale()));
					response.setRenderParameter("action", "editAziendaFail");
					return;
				}
			}
			// Salvataggio azienda
			else {
				aziendaValidator.validate(aziendaModel, bindingResult);
				if (bindingResult.hasErrors()) {
					response.setRenderParameter("action", "editAziendaFail");
					model.addAttribute("formError", messageSource.getMessage("error.verificareDatiInseriti", null, request.getLocale()));
					return;
				}

				Azienda azienda = null;
				if (idAzienda != 0) {
					// Update
					azienda = aziendaService.getAziendaByPk(idAzienda);
					aziendaService.setInUso(azienda);
				}
				else {
					// Create
					azienda = new Azienda();
					azienda.setAttiva(false);
					//Nella creazione dell'azienda la possibilità di aggiunta operatore di default 
					//- CAF = true
					//- AZIENDA = false
					String tipoAziendaIns = aziendaModel.getTipoAzienda();
					if (tipoAziendaIns != null && tipoAziendaIns.equals(TipoAzienda.CAF.toString())){
						azienda.setAggOperatori(true);
					}else{
						if (tipoAziendaIns != null && tipoAziendaIns.equals(TipoAzienda.AZIENDA.toString())){
							azienda.setAggOperatori(false);
						}
					}
				}

				azienda.setMail(aziendaModel.getMail());
				azienda.setMailPec(aziendaModel.getMailPec());
				azienda.setPartitaIva(aziendaModel.getPartitaIva());
				azienda.setRagioneSociale(aziendaModel.getRagioneSociale());
				azienda.setTipo(aziendaModel.getTipoAzienda());
				azienda.setTipoEnte(aziendaModel.getTipoEnte());
				azienda.setTipoEnte(aziendaModel.getTipoEnte());
				Indirizzo sede = new Indirizzo();
				sede.setNrCivico(aziendaModel.getNrCivicoSede());
				sede.setVia(aziendaModel.getViaSede());

				long comuneSede = aziendaModel.getComuneSede();
				Comune comune = comuneService.getComuneById(comuneSede);
				sede.setComune(comune);
				azienda.setSede(sede);

				ProfiloUtenteCittadino profiloUtenteCittadino = osApulieUserDetails.getProfiloUtenteCittadino();
				profiloUtenteCittadino = profiloUtenteService.getProfiloUtenteCittadinoById(profiloUtenteCittadino.getId());
				azienda.setResponsabile(profiloUtenteCittadino);
				azienda.setProfiliUtenteCittadinoAzienda(null);

				if(aziendaModel.isAziendaAggOperatori()){
					List<ProfiloUtenteCittadino> profiliUtenteAssociati = aziendaModel.getProfiliUtenteAssociati();
					if (profiliUtenteAssociati != null) {
						// Associazione utenti
						for (ProfiloUtenteCittadino profiloUtenteCittadinoAssociato : profiliUtenteAssociati) {
							azienda.addProfiloUtenteCittadino(profiloUtenteCittadinoAssociato, true);
						}
					}
				}
				//recupero id responsabile comunale
				Long l = aziendaModel.getComuneSede();
				ProfiloUtenteCittadino puc = null;
				ProfiloUtenteCittadino gestoreComunaleId = ProfiloUtenteCittadinoRep.findByComune(l); 
				
				if(gestoreComunaleId!=null){
					puc=profiloUtenteService.getProfiloUtenteCittadinoById(gestoreComunaleId.getId());
				}
				String email = null;
				if(puc!=null){
					if(puc.getCanaleComunicazione().equals(PortletConstants.CANALE_COMUNICAZIONE_EMAIL) && puc.getCanaleComunicazione()!=null && !puc.getCanaleComunicazione().isEmpty()){
						 email=puc.getMail();
					}else{
						email=puc.getMailPec();
					}	
				}		
				List<ProfiloUtenteCittadino> listApp = aziendaModel.getProfiliUtenteAssociati(); 
				List<ProfiloUtenteCittadino> listDb = this.profiloUtenteService.searchUtentiAssociatiAzienda(aziendaModel.getIdAzienda(), null, null, null);
				List<ProfiloUtenteCittadino> listEmail = new ArrayList<ProfiloUtenteCittadino>();		
				if (idAzienda == 0) {
					if (listApp != null && !listApp.isEmpty() && listApp.size()>0){
						for (ProfiloUtenteCittadino cittadino : listApp){
							if (!listDb.contains(cittadino))
								listEmail.add(cittadino);
						}
					}
					aziendaService.saveAzienda(azienda);
					try{
						aziendaService.sendMailToAziendaNew(userPreferences, subjectNuovaAzienda, SERVIZIO, null, listEmail, email, aziendaModel.getRagioneSociale());
					}catch (Exception e) {
						log.error("saveMailAzienda :: " + e.getMessage(), e);
						auditManager
						.addFineOperazione()
						.addEsitoError()
						.addInfo("Exception", e.getMessage())
						.build();
					}
				}
				else {
					if (listApp != null && !listApp.isEmpty() && listApp.size()>0){
						for (ProfiloUtenteCittadino cittadino : listApp){
							if (!listDb.contains(cittadino))
								listEmail.add(cittadino);
						}	
					}
					updateAzienda(aziendaModel, azienda, portletRequest, session);
					try{
						aziendaService.sendMailToUser(userPreferences, subjectAssociazione, SERVIZIO, null,listEmail, email, aziendaModel.getRagioneSociale());
					}catch (Exception e) {
						log.error("saveMailAzienda :: " + e.getMessage(), e);
						auditManager
						.addFineOperazione()
						.addEsitoError()
						.addInfo("Exception", e.getMessage())
						.build();
					}
				}
			}

			model.addAttribute("message", messageSource.getMessage("message.label.operazioneEseguitaCorrettamente", null, request.getLocale()));
			model.addAttribute("aziende", getAziende());
			response.setRenderParameter("action", "home");
			model.addAttribute("aziendaModel", null);
			sessionStatus.setComplete();
		}
		catch (Exception e) {
			log.error("saveAzienda :: " + e.getMessage(), e);
			model.addAttribute("formError", messageSource.getMessage("exception.generalError.title", null, request.getLocale()));
			response.setRenderParameter("action", "editAziendaFail");
			auditManager
			.addFineOperazione()
			.addEsitoError()
			.addInfo("Exception", e.getMessage())
			.build();
			return;
		}
		auditManager
		.addFineOperazione()
		.addEsitoSuccess()
		.build();
	}
	
	/**
	 * @param aziendaModel
	 * @param azienda
	 * @param request 
	 * @param session 
	 */
	private void updateAzienda(AziendaModel aziendaModel, Azienda azienda, PortletRequest request, PortletSession session) {
		// Reset ruoli utenti associati dell'azienda in memoria in caso di CAF
		Azienda aziendaOld = aziendaService.getAziendaByPk(aziendaModel.getIdAzienda());
		String tipoAziendaOld = aziendaOld.getTipo();
		if (tipoAziendaOld != null && tipoAziendaOld.equals(TipoAzienda.CAF.toString())) {
			List<ProfiloUtenteCittadinoAzienda> profiliUtenteCittadinoAziendaOld = aziendaOld.getProfiliUtenteCittadinoAzienda();
			if (profiliUtenteCittadinoAziendaOld != null) {
				for (ProfiloUtenteCittadinoAzienda profiloUtenteCittadinoAzienda : profiliUtenteCittadinoAziendaOld) {
					commonService.removeOperatoreCAFRole(aziendaOld.getId(), profiloUtenteCittadinoAzienda.getProfiloUtenteCittadino().getId());
				}
			}
		}
		
		// Reset ruoli utenti associati dell'azienda in memoria in caso di AZIENDA
		if (tipoAziendaOld != null && tipoAziendaOld.equals(TipoAzienda.AZIENDA.toString())) {
			List<ProfiloUtenteCittadinoAzienda> profiliUtenteCittadinoAziendaOld = aziendaOld.getProfiliUtenteCittadinoAzienda();
			if (profiliUtenteCittadinoAziendaOld != null) {
				for (ProfiloUtenteCittadinoAzienda profiloUtenteCittadinoAzienda : profiliUtenteCittadinoAziendaOld) {
					commonService.removeOperatoreAZIENDARole(aziendaOld.getId(), profiloUtenteCittadinoAzienda.getProfiloUtenteCittadino().getId());
				}
			}
		}

		// aggiornamento azienda
		aziendaService.updateAzienda(azienda);

		// Aggiornamento ruoli utenti associati in caso di CAF
		String tipoAzienda = azienda.getTipo();
		if (tipoAzienda != null && tipoAzienda.equals(TipoAzienda.CAF.toString())) {
			List<ProfiloUtenteCittadinoAzienda> profiliUtenteCittadinoAzienda = azienda.getProfiliUtenteCittadinoAzienda();
			if (profiliUtenteCittadinoAzienda != null) {
				for (ProfiloUtenteCittadinoAzienda profiloUtenteCittadinoAzienda : profiliUtenteCittadinoAzienda) {
					helper.addRoleUser(profiloUtenteCittadinoAzienda.getProfiloUtenteCittadino().getCodiceFiscale(), RoleConstants.OPERATORE_CAF);
				}
			}
		}
		
		// Aggiornamento ruoli utenti associati in caso di AZIENDA abilitata all'aggiunta di operatori
		if (tipoAzienda != null && tipoAzienda.equals(TipoAzienda.AZIENDA.toString()) && azienda.isAggOperatori()) {
			List<ProfiloUtenteCittadinoAzienda> profiliUtenteCittadinoAzienda = azienda.getProfiliUtenteCittadinoAzienda();
			if (profiliUtenteCittadinoAzienda != null) {
				for (ProfiloUtenteCittadinoAzienda profiloUtenteCittadinoAzienda : profiliUtenteCittadinoAzienda) {
					helper.addRoleUser(profiloUtenteCittadinoAzienda.getProfiloUtenteCittadino().getCodiceFiscale(), RoleConstants.OPERATORE_AZIENDA);
				}
			}
		}
	}

	/**
	 * Elimina il {@link ProfiloUtenteCittadino} dalla lista presente in pagina.
	 *
	 * @param aziendaModel
	 * @param model
	 * @param session 
	 * @param request 
	 * @throws Exception 
	 * @throws  
	 */
	@ActionMapping(params = "action=delProfiloUtente")
	public void delProfiloUtente(@RequestParam Long idProfiloUtente, @ModelAttribute AziendaModel aziendaModel, Model model, ActionResponse response, PortletSession session, PortletRequest request) throws  Exception {

		String uuidStr=UUID.randomUUID().toString();
		session.setAttribute("UUID", uuidStr);
		AuditManager auditManager= AuditConfiguration
					.configure()
					.audit()
					.addInizioOperazione()
					.addUuidOperazione(uuidStr)
					.addOperazioneRichiesta("Modifica Azienda/CAF/Libero Professionista")
					.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
					.setOrigin(Origin.getIp(request))
					.addPaginaCorrente(helper.getCurrentPageName(request));
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
		List<ProfiloUtenteCittadino> profiliUtenteAssociati = aziendaModel.getProfiliUtenteAssociati();
		if (profiliUtenteAssociati != null) {
			ProfiloUtenteCittadino profiloUtenteCittadinoById = profiloUtenteService.getProfiloUtenteCittadinoById(idProfiloUtente);
			if (profiloUtenteCittadinoById != null) {
				profiliUtenteAssociati.remove(profiloUtenteCittadinoById);
			}
			aziendaModel.setProfiliUtenteAssociatiSearch(new HashSet<ProfiloUtenteCittadino>(profiliUtenteAssociati));
		}
		auditManager
		.addFineOperazione()
		.addEsitoSuccess()
		.build();
		response.setRenderParameter("action", "editAziendaFail");
	}

	/**
	 * Provvede ad eliminare l'entità (solo se {@link Azienda} non è attivo ed è relazionato solo
	 * all'utente corrente).
	 *
	 * @param idAzienda
	 * @param model
	 * @param request
	 * @param response
	 */
	@ActionMapping(params = "action=delAzienda")
	public void delAzienda(@RequestParam Long idAzienda, Model model, ActionRequest request, ActionResponse response) {

		try {
			// Controllo per eliminare l'entità
			OSApulieUserDetails osApulieUserDetails = helper.getOsApulieUserDetails();
			Azienda azienda = aziendaService.getAziendaByPk(idAzienda);
			if (!azienda.isInUso() && azienda.getResponsabile().getId().longValue() == osApulieUserDetails.getProfiloUtenteCittadino().getId().longValue()) {
				// Eliminazione ruoli CAF
				ProfiloUtenteCittadino responsabile = azienda.getResponsabile();
				if (responsabile != null) {
					commonService.removeResponsabileCAFRole(responsabile.getId());
				}

				List<ProfiloUtenteCittadinoAzienda> profiliUtenteCittadinoAzienda = azienda.getProfiliUtenteCittadinoAzienda();
				if (profiliUtenteCittadinoAzienda != null) {
					for (ProfiloUtenteCittadinoAzienda profiloUtenteCittadinoAzienda : profiliUtenteCittadinoAzienda) {
						commonService.removeOperatoreCAFRole(idAzienda, profiloUtenteCittadinoAzienda.getProfiloUtenteCittadino().getId());
					}
				}
				aziendaService.deleteAzienda(idAzienda);
			}
		}
		catch (Exception e) {
			log.error("delAzienda :: " + e.getMessage(), e);
			model.addAttribute("formError", messageSource.getMessage("exception.generalError.title", null, request.getLocale()));
			response.setRenderParameter("action", "editAziendaFail");
			return;
		}

		model.addAttribute("message", messageSource.getMessage("message.label.operazioneEseguitaCorrettamente", null, request.getLocale()));
		model.addAttribute("aziende", getAziende());

		response.setRenderParameter("action", "home");

	}

	@ModelAttribute(value = "comuneISAList")
	public List<ComuneISA> getComuneISAList() {
		try {

			List<ComuneISA> comuneISAs = comuneISAService.getComuniAttivi();
			return comuneISAs;
		}
		catch (Exception e) {
			log.error("getComuneISAList :: " + e.getMessage(), e);
		}
		return null;
	}

	@ModelAttribute(value = "canaliComunicazione")
	public List<String> getCanaliComunicazioneList() {

		List<String> canaliComunicazione = new ArrayList<String>();

		canaliComunicazione.add(PortletConstants.CANALE_COMUNICAZIONE_EMAIL);
		canaliComunicazione.add(PortletConstants.CANALE_COMUNICAZIONE_PEC);

		return canaliComunicazione;
	}

	@ModelAttribute(value = "tipiAzienda")
	public TipoAzienda[] getTipiAzienda() {
		return TipoAzienda.values();
	}

	@ModelAttribute(value = "comuneList")
	public List<Comune> getComuneList() {

		try {
			return comuneService.getAllComuni();
		}
		catch (Exception e) {
			log.error("getComuneList :: " + e.getMessage(), e);
		}
		return null;
	}

	/**
	 * Utility method per calcolare e restituire il view ID all'interno della corretta alberatura di
	 * sottodirectory delle JSP: in un certo senso si tratta del namespace delle view locali a
	 * questo controller. Ad esempio: <code> toLocalViewPath( "home" ) </code> restituirà <code>
	 * "/gestioneaziende/home" </code>
	 *
	 * @param viewName l'ID della view locale
	 * @return il view ID completo del path a cui la vista appartiene
	 */
	private static String toLocalViewPath(String viewName) {
		return "gestioneazienda/" + viewName;
	}
}
