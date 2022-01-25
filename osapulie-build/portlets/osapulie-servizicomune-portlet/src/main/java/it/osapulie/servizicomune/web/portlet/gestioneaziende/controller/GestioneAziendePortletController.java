/************************************************************************************
 * Copyright (c) 2011, 2016 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.servizicomune.web.portlet.gestioneaziende.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import eng.tz.la.core.AuditManager;
import eng.tz.la.core.Export;
import eng.tz.la.model.OperationExportFile;
import it.osapulie.domain.Audit;
import it.osapulie.domain.Azienda;
import it.osapulie.domain.Comune;
import it.osapulie.domain.Indirizzo;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.ProfiloUtenteCittadinoAzienda;
import it.osapulie.enumeration.TipoAzienda;
import it.osapulie.persistence.AuditRepository;
import it.osapulie.service.AziendaService;
import it.osapulie.service.CommonService;
import it.osapulie.service.ComuneService;
import it.osapulie.service.ProfiloUtenteService;
import it.osapulie.servizicomune.web.portlet.BaseController;
import it.osapulie.servizicomune.web.portlet.gestioneaziende.form.AziendaEditForm;
import it.osapulie.servizicomune.web.portlet.gestioneaziende.form.AziendeSearchForm;
import it.osapulie.servizicomune.web.portlet.gestioneaziende.validator.AziendaValidator;
import it.osapulie.util.audit.ApplicationContextUtil;
import it.osapulie.util.audit.AuditConfiguration;
import it.osapulie.util.audit.Origin;
import it.osapulie.web.portlet.util.CommunicationException;
import it.osapulie.web.portlet.util.PortletHelper;
import it.osapulie.web.portlet.util.PortletUtils;
import it.osapulie.web.portlet.util.RoleConstants;
import it.osapulie.web.portlet.util.SenderHelper;

/**
 * Portlet controller per la gestione delle richieste di inserimento aziende.
 *
 * @author Gianluca Pindinelli
 */
@Controller("gestioneAziendePortletController")
@RequestMapping("view")
@SessionAttributes({ "aziendaEditForm" })
public class GestioneAziendePortletController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(GestioneAziendePortletController.class);

	@Autowired
	private SenderHelper senderHelper;

	@Autowired
	public ResourceBundleMessageSource messageSource;

	@Autowired
	private AziendaService aziendaService;

	@Autowired
	private ProfiloUtenteService profiloUtenteService;

	@Autowired
	private AziendaValidator aziendaValidator;

	@Autowired
	private ComuneService comuneService;

	@Inject
	private PortletHelper helper;

	@Autowired
	private CommonService commonService;
	
	@Inject
	private AuditRepository auditRepository;

	@Value("#{applicationProperties['gestioneaziende.send.email.enable']}")
	private Boolean gestioneaziendeSendEmailEnable;

	/**
	 * Default entry quando la portlet viene visualizzata per la prima volta.
	 *
	 * @param model il view model da popolare
	 * @return il view ID da renderizzare
	 */
	@RenderMapping
	public String homePage() {
		return toLocalViewPath("home");
	}

	@ModelAttribute("aziendaSearchForm")
	public AziendeSearchForm getAziendeSearchForm() {
		return new AziendeSearchForm();
	}

	/**
	 * Recupero lista aziende 
	 * @param aziendeSearchForm
	 * @return
	 */
	@ModelAttribute("aziende")
	public List<Azienda> getAziendeList(@ModelAttribute AziendeSearchForm aziendeSearchForm) {

		String partitaIva = null;
		String ragioneSociale = null;
		String codiceFiscaleResponsabile = null;
		String stato = null;
		Boolean statoBoolean = null;
		if (aziendeSearchForm != null) {
			partitaIva = aziendeSearchForm.getPartitaIva();
			ragioneSociale = aziendeSearchForm.getRagioneSociale();
			codiceFiscaleResponsabile = aziendeSearchForm.getCodiceFiscaleResponsabile();
			stato = aziendeSearchForm.getStato();
			if (stato != null && !stato.equals("")) {
				statoBoolean = Boolean.parseBoolean(stato);
			}
		}

		List<Azienda> results = aziendaService.searchAziende(partitaIva, ragioneSociale, codiceFiscaleResponsabile, statoBoolean);

		return results;

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
	 * Recuper delle info dell'azienda da dare alla view
	 * @param idAzienda
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 */
	@RenderMapping(params = "action=editAzienda")
	public String editAzienda(@RequestParam Long idAzienda, Model model, PortletRequest request, PortletSession session) throws UnsupportedEncodingException, IOException {
		
		String uuidStr=UUID.randomUUID().toString();
		session.setAttribute("UUID", uuidStr);
		AuditManager auditManager= AuditConfiguration.configure()
					.audit()
					.addInizioOperazione()
					.addUuidOperazione(uuidStr)
					.addOperazioneRichiesta("Recupero dati Azienda/CAF da modificare")
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
					
					
		try {
			AziendaEditForm aziendaEditForm = new AziendaEditForm();
			Azienda azienda = aziendaService.getAziendaByPk(idAzienda);
			aziendaService.setInUso(azienda);

			aziendaEditForm.setAttiva(azienda.isAttiva());
			aziendaEditForm.setComuneSede(azienda.getSede().getComune().getId());
			aziendaEditForm.setCodiceFiscaleResponsabile(azienda.getResponsabile().getCodiceFiscale());
			aziendaEditForm.setIdAzienda(azienda.getId());
			aziendaEditForm.setMail(azienda.getMail());
			aziendaEditForm.setMailPec(azienda.getMailPec());
			aziendaEditForm.setNrCivicoSede(azienda.getSede().getNrCivico());
			aziendaEditForm.setPartitaIva(azienda.getPartitaIva());
			aziendaEditForm.setTipo(azienda.getTipo());
			aziendaEditForm.setTipoEnte(azienda.getTipoEnte());
			List<ProfiloUtenteCittadinoAzienda> profiliUtenteCittadinoAzienda = azienda.getProfiliUtenteCittadinoAzienda();
			if (profiliUtenteCittadinoAzienda != null) {
				List<ProfiloUtenteCittadino> profiliUtentiAssociati = new ArrayList<ProfiloUtenteCittadino>();
				for (ProfiloUtenteCittadinoAzienda profiloUtenteCittadinoAzienda : profiliUtenteCittadinoAzienda) {
					profiliUtentiAssociati.add(profiloUtenteCittadinoAzienda.getProfiloUtenteCittadino());
				}
				aziendaEditForm.setProfiliUtenteAssociati(profiliUtentiAssociati);
				aziendaEditForm.setProfiliUtenteAssociatiSearch(new HashSet<ProfiloUtenteCittadino>(profiliUtentiAssociati));
			}

			aziendaEditForm.setRagioneSociale(azienda.getRagioneSociale());
			aziendaEditForm.setViaSede(azienda.getSede().getVia());
			aziendaEditForm.setAggOperatori(azienda.isAggOperatori());

			model.addAttribute("aziendaEditForm", aziendaEditForm);
		}
		catch (Exception e) {
			
			auditManager
				.addFineOperazione()
				.addEsitoError()
				.addInfo("Exception", e.getMessage())
				.build();
			
			log.error("editAzienda :: " + e.getMessage(), e);
		}

		auditManager.build();
		 
		return toLocalViewPath("editAzienda");
	}

	/**
	 * Salva l'{@link Azienda}.
	 *
	 * @param aziendaEditForm
	 * @param bindingResult
	 * @param model
	 * @param request
	 * @param response
	 * @param sessionStatus
	 */
	@ActionMapping(params = "action=saveAzienda")
	public void saveAzienda(@ModelAttribute AziendaEditForm aziendaEditForm, BindingResult bindingResult, Model model, ActionRequest request, ActionResponse response, SessionStatus sessionStatus,
			PortletRequest portletRequest, PortletSession session) {

		AuditManager auditManager= AuditConfiguration
				.configure()
				.audit()
				.addUuidOperazione((String)session.getAttribute("UUID"))
				.addOperazioneRichiesta("Modifica dati Azienda/CAF/Libero Professionista")
				.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(portletRequest))
				.setOrigin(Origin.getIp(request))
				.addPaginaCorrente(helper.getCurrentPageName(portletRequest));
		
		log.debug("saveAzienda :: entering method");
		String[] cfUtenteAssociatoButtonStrings = request.getParameterMap().get("cfUtenteAssociatoButton");
		String[] ricercaDelegato = request.getParameterMap().get("ricercaDelegato");
		try {

			// Ricerca delegato
			if (ricercaDelegato != null) {
				String codiceFiscaleDelegato = null;
				String nomeDelegato = null;
				String cognomeDelegato = null;
				if (aziendaEditForm.getCodiceFiscaleDelegato() != null && !aziendaEditForm.getCodiceFiscaleDelegato().isEmpty()) {
					codiceFiscaleDelegato = aziendaEditForm.getCodiceFiscaleDelegato();
				}
				if (aziendaEditForm.getNomeDelegato() != null && !aziendaEditForm.getNomeDelegato().isEmpty()) {
					nomeDelegato = aziendaEditForm.getNomeDelegato();
				}
				if (aziendaEditForm.getCognomeDelegato() != null && !aziendaEditForm.getCognomeDelegato().isEmpty()) {
					cognomeDelegato = aziendaEditForm.getCognomeDelegato();
				}

				Set<ProfiloUtenteCittadino> profiloUtenteCittadinoSet = null;

				// Ricerca in lista in memoria
				List<ProfiloUtenteCittadino> profiliUtenteAssociati = aziendaEditForm.getProfiliUtenteAssociati();
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

				aziendaEditForm.setProfiliUtenteAssociatiSearch(profiloUtenteCittadinoSet);
				model.addAttribute("aziendaEditForm", aziendaEditForm);
				response.setRenderParameter("action", "editAziendaFail");
				return;
			}

			// Aggiunta utente cittadino ad professionista
			if (cfUtenteAssociatoButtonStrings != null) {
				String cfUtenteAssociato = aziendaEditForm.getCfUtenteAssociato();
				if (cfUtenteAssociato != null && !cfUtenteAssociato.equals("")) {

					String checkCodiceFiscale = PortletUtils.checkCodiceFiscale(cfUtenteAssociato);
					if (checkCodiceFiscale != null) {
						model.addAttribute("formError", messageSource.getMessage("error.verificareDatiInseriti", null, request.getLocale()));
						bindingResult.rejectValue("cfUtenteAssociato", null, checkCodiceFiscale);
						response.setRenderParameter("action", "editAziendaFail");
						return;
					}

					// Ricerca utente nel sistema
					ProfiloUtenteCittadino profiloUtenteCittadinoByCf = profiloUtenteService.getProfiloUtenteCittadinoByCf(cfUtenteAssociato);
					if (profiloUtenteCittadinoByCf != null) {
						// Verifica utente già associato
						List<ProfiloUtenteCittadino> profiliUtenteAssociati = aziendaEditForm.getProfiliUtenteAssociati();
						if (profiliUtenteAssociati == null) {
							profiliUtenteAssociati = new ArrayList<ProfiloUtenteCittadino>();
							aziendaEditForm.setProfiliUtenteAssociati(profiliUtenteAssociati);
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
						aziendaEditForm.setProfiliUtenteAssociatiSearch(new HashSet<ProfiloUtenteCittadino>(profiliUtenteAssociati));
						aziendaEditForm.setCfUtenteAssociato(null);
						response.setRenderParameter("action", "editAziendaFail");
						return;
					}
					else {
						bindingResult.rejectValue("cfUtenteAssociato", "error.profiloUtenteCittadinoByCf.null");
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
				aziendaValidator.validate(aziendaEditForm, bindingResult);
				if (bindingResult.hasErrors()) {
					response.setRenderParameter("action", "editAziendaFail");
					model.addAttribute("formError", messageSource.getMessage("error.verificareDatiInseriti", null, request.getLocale()));
					return;
				}

				Azienda azienda = aziendaService.getAziendaByPk(aziendaEditForm.getIdAzienda());

				boolean isAziendaAttiva = azienda.isAttiva();

				aziendaService.setInUso(azienda);
				azienda.setAttiva(aziendaEditForm.isAttiva());

				azienda.setMail(aziendaEditForm.getMail());
				azienda.setMailPec(aziendaEditForm.getMailPec());
				azienda.setPartitaIva(aziendaEditForm.getPartitaIva());
				azienda.setRagioneSociale(aziendaEditForm.getRagioneSociale());
				azienda.setTipo(aziendaEditForm.getTipo());
				azienda.setTipoEnte(aziendaEditForm.getTipoEnte());

				Indirizzo sede = new Indirizzo();
				sede.setNrCivico(aziendaEditForm.getNrCivicoSede());
				sede.setVia(aziendaEditForm.getViaSede());

				long comuneSede = aziendaEditForm.getComuneSede();
				Comune comune = comuneService.getComuneById(comuneSede);
				sede.setComune(comune);
				azienda.setSede(sede);

				ProfiloUtenteCittadino profiloUtenteCittadino = profiloUtenteService.getProfiloUtenteCittadinoByCf(aziendaEditForm.getCodiceFiscaleResponsabile());
				azienda.setResponsabile(profiloUtenteCittadino);
				azienda.setProfiliUtenteCittadinoAzienda(null);

				if(aziendaEditForm.isAggOperatori()){
					List<ProfiloUtenteCittadino> profiliUtenteAssociati = aziendaEditForm.getProfiliUtenteAssociati();
					if (profiliUtenteAssociati != null) {
						// Associazione utenti
						for (ProfiloUtenteCittadino profiloUtenteCittadinoAssociato : profiliUtenteAssociati) {
							azienda.addProfiloUtenteCittadino(profiloUtenteCittadinoAssociato, true);
						}
					}
				}

				if (azienda.getTipo().equals(TipoAzienda.CAF.toString()))
					azienda.setAggOperatori(true);
				if (azienda.getTipo().equals(TipoAzienda.AZIENDA.toString())){
					azienda.setAggOperatori(aziendaEditForm.isAggOperatori());
				}

				updateAzienda(aziendaEditForm, azienda);

				// Aggiunta ruolo Responsabile CAF se il CAF è attivo
				if (azienda.getTipo().equals(TipoAzienda.CAF.toString())) {
					if (azienda.isAttiva()) {
						helper.addRoleUser(profiloUtenteCittadino.getCodiceFiscale(), RoleConstants.RESPONSABILE_CAF);
					}
					else {
						commonService.removeResponsabileCAFRole(profiloUtenteCittadino.getId());
					}
				}

				// Aggiunta ruolo Responsabile AZIENDA se L'AZIENDA è attiva
				if(azienda.getTipo().equals(TipoAzienda.AZIENDA.toString())){
					if (azienda.isAttiva()){
						helper.addRoleUser(profiloUtenteCittadino.getCodiceFiscale(), RoleConstants.RESPONSABILE_AZIENDA);
					}
					else{
						commonService.removeResponsabileAZIENDARole(profiloUtenteCittadino.getId());
					}
				}



				// invio email solo è stata attivata/disattivata
				if (gestioneaziendeSendEmailEnable && isAziendaAttiva != aziendaEditForm.isAttiva()) {
					
					auditManager.addInfo("Gestione send mail","Caso attivazione/disattivazione");

					// Invio email a responsabile
					String tipo = azienda.getTipo();
					if (tipo == null) {
						tipo = TipoAzienda.AZIENDA.toString();
					}
					String subjectMail = messageSource.getMessage("mail.azienda.subject", new String[] { tipo, azienda.getRagioneSociale() }, request.getLocale());
					Map<String, String> emailModel = new HashMap<String, String>();
					emailModel.put("cognome", profiloUtenteCittadino.getCognome());
					emailModel.put("nome", profiloUtenteCittadino.getNome());
					emailModel.put("ragioneSociale", azienda.getRagioneSociale());
					emailModel.put("tipoAzienda", tipo);

					String osapulieHost = request.getServerName();
					emailModel.put("osapulieHost", osapulieHost);

					String statoApprovazione = null;
					if (azienda.isAttiva()) {
						statoApprovazione = messageSource.getMessage("label.approvata", new String[] { azienda.getRagioneSociale() }, request.getLocale());
					}
					else {
						statoApprovazione = messageSource.getMessage("label.respinta", new String[] { azienda.getRagioneSociale() }, request.getLocale());
					}
					emailModel.put("statoApprovazione", statoApprovazione);

					try {
						auditManager.addInfo("Send mail","Cittadino");
						senderHelper.sendCommunicationToCittadino(subjectMail, "velocityTemplate/aziendaApprovazioneResponsabile.vm", emailModel, null, profiloUtenteCittadino);
					}
					catch (CommunicationException e) {
						auditManager
						.addFineOperazione()
						.addEsitoError()
						.addInfo("Exception", e.getMessage())
						.build();
						log.error("saveAzienda :: " + e.getMessage(), e);
					}

					// Invio email ad azienda
					emailModel = new HashMap<String, String>();
					emailModel.put("ragioneSociale", azienda.getRagioneSociale());
					emailModel.put("statoApprovazione", statoApprovazione);
					emailModel.put("tipoAzienda", tipo);
					emailModel.put("osapulieHost", osapulieHost);
					try {
						auditManager.addInfo("Send mail","Azienda");
						senderHelper.sendCommunicationToAzienda(subjectMail, "velocityTemplate/aziendaApprovazione.vm", emailModel, null, azienda);
					}
					catch (CommunicationException e) {
						auditManager
						.addFineOperazione()
						.addEsitoError()
						.addInfo("Exception", e.getMessage())
						.build();
						log.error("saveAzienda :: " + e.getMessage(), e);
					}
				}

				model.addAttribute("aziende", getAziendeList(null));
			}

			model.addAttribute("message", messageSource.getMessage("message.label.operazioneEseguitaCorrettamente", null, request.getLocale()));
			response.setRenderParameter("action", "home");
			auditManager
			.addFineOperazione()
			.addEsitoSuccess()
			.build();
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
	}

	/**
	 *
	 * @param aziendaEditForm
	 * @param azienda
	 */
	private void updateAzienda(AziendaEditForm aziendaEditForm, Azienda azienda) {
		// Reset ruoli utenti associati dell'azienda in memoria in caso di CAF
		Azienda aziendaOld = aziendaService.getAziendaByPk(aziendaEditForm.getIdAzienda());
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

		String tipoAzienda = azienda.getTipo();
		// Aggiornamento ruoli utenti associati in caso di CAF
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
	 * @param aziendaEditForm
	 * @param model
	 */
	@ActionMapping(params = "action=delProfiloUtente")
	public void delProfiloUtente(@RequestParam Long idProfiloUtente, @ModelAttribute AziendaEditForm aziendaEditForm, Model model, ActionResponse response) {

		List<ProfiloUtenteCittadino> profiliUtenteAssociati = aziendaEditForm.getProfiliUtenteAssociati();
		if (profiliUtenteAssociati != null) {
			ProfiloUtenteCittadino profiloUtenteCittadinoById = profiloUtenteService.getProfiloUtenteCittadinoById(idProfiloUtente);
			if (profiloUtenteCittadinoById != null) {
				profiliUtenteAssociati.remove(profiloUtenteCittadinoById);
			}
			aziendaEditForm.setProfiliUtenteAssociatiSearch(new HashSet<ProfiloUtenteCittadino>(profiliUtenteAssociati));
		}
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
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 */
	@ActionMapping(params = "action=delAzienda")
	public void delAzienda(@RequestParam Long idAzienda, Model model, ActionRequest request, ActionResponse response, PortletSession session) throws UnsupportedEncodingException, IOException {
		
		String uuidStr=UUID.randomUUID().toString();
		session.setAttribute("UUID", uuidStr);
		AuditManager auditManager= AuditConfiguration.configure()
					.audit()
					.addInizioOperazione()
					.addUuidOperazione(uuidStr)
					.addOperazioneRichiesta("Eliminazione azienda")
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

		try {
			// Controllo per eliminare l'entità
			Azienda azienda = aziendaService.getAziendaByPk(idAzienda);
			if (!azienda.isInUso()) {

				if(azienda.getTipo().equals(TipoAzienda.CAF.toString())){
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
				}

				if(azienda.getTipo().equals(TipoAzienda.AZIENDA.toString())){
					// Eliminazione ruoli AZIENDA
					ProfiloUtenteCittadino responsabile = azienda.getResponsabile();
					if (responsabile != null) {
						commonService.removeResponsabileAZIENDARole(responsabile.getId());
					}
					List<ProfiloUtenteCittadinoAzienda> profiliUtenteCittadinoAzienda = azienda.getProfiliUtenteCittadinoAzienda();
					if (profiliUtenteCittadinoAzienda != null) {
						for (ProfiloUtenteCittadinoAzienda profiloUtenteCittadinoAzienda : profiliUtenteCittadinoAzienda) {
							commonService.removeOperatoreAZIENDARole(idAzienda, profiloUtenteCittadinoAzienda.getProfiloUtenteCittadino().getId());
						}
					}
				}

				aziendaService.deleteAzienda(idAzienda);
				// TODO invio email?
			}

			model.addAttribute("aziende", getAziendeList(null));
		}
		catch (Exception e) {
			log.error("delAzienda :: " + e.getMessage(), e);
			model.addAttribute("formError", messageSource.getMessage("exception.generalError.title", null, request.getLocale()));
			response.setRenderParameter("action", "editAziendaFail");
			auditManager
			.addFineOperazione()
			.addEsitoError()
			.addInfo("Exception", e.getMessage())
			.build();
			return;
		}

		model.addAttribute("message", messageSource.getMessage("message.label.operazioneEseguitaCorrettamente", null, request.getLocale()));
		auditManager
		.addFineOperazione()
		.addEsitoSuccess()
		.build();
		response.setRenderParameter("action", "home");

	}

	@RenderMapping(params = "action=editAziendaFail")
	public String editAzienda() {
		return toLocalViewPath("editAzienda");
	}

	@ModelAttribute(value = "tipiAzienda")
	public TipoAzienda[] getTipiAzienda() {
		return TipoAzienda.values();
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
		return "gestioneaziende/" + viewName;
	}
}
