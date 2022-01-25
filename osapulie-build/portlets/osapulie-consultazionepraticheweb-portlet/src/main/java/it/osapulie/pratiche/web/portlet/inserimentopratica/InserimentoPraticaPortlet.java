package it.osapulie.pratiche.web.portlet.inserimentopratica;

import java.beans.PropertyEditorSupport;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletSession;
import javax.portlet.ResourceResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.portlet.bind.PortletRequestDataBinder;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import it.osapulie.domain.Comune;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.dto.Fascicolo;
import it.osapulie.infrastructure.ContentMimeTypes;
import it.osapulie.infrastructure.ReportService;
import it.osapulie.persistence.ComuneRepository;
import it.osapulie.pratiche.service.GestionePraticheWebService;
import it.osapulie.pratiche.web.portlet.varie.PortletConstants;
import it.osapulie.pratiche.web.portlet.varie.ServiceHelper;
import it.osapulie.pratiche.web.portlet.varie.StringUtils;
import it.osapulie.pratiche.web.portlet.varie.UploadItemPratiche;
import it.osapulie.pratiche.web.ws.types.AllegatiDto;
import it.osapulie.pratiche.web.ws.types.AllegatiPraticaDto;
import it.osapulie.pratiche.web.ws.types.CampiAggiuntiviPraticaWebDto;
import it.osapulie.pratiche.web.ws.types.CampiAggiuntiviTipoPraticaDto;
import it.osapulie.pratiche.web.ws.types.ComuneDto;
import it.osapulie.pratiche.web.ws.types.DocInfo;
import it.osapulie.pratiche.web.ws.types.EliminaAllegatoResponse;
import it.osapulie.pratiche.web.ws.types.PraticaWebDto;
import it.osapulie.pratiche.web.ws.types.ProfiloUtenteDto;
import it.osapulie.pratiche.web.ws.types.SavePraticaWebResponse;
import it.osapulie.pratiche.web.ws.types.TipoImmobileDto;
import it.osapulie.pratiche.web.ws.types.TipoPraticaDto;
import it.osapulie.pratiche.web.ws.types.UploadResponse;
import it.osapulie.service.FascicoloUtenteService;
import it.osapulie.web.portlet.controller.BaseController;
import it.osapulie.web.portlet.util.PortletHelper;

/**
 * Portlet controller per il consultazione pratiche.
 *
 * @author Birtolo Maria Michela
 */
@Controller("inserimentoPraticaPortletController")
@RequestMapping("view")
@SessionAttributes({ "datiPratica", "elencoPraticheWeb", "uploadItem" })
public class InserimentoPraticaPortlet extends BaseController {

	private static Logger log = LoggerFactory.getLogger(InserimentoPraticaPortlet.class);

	private static final String JSP_PATH = "inserimentopratica/";
	private static final String HOME = "home";
	// private static final String EDIT = "insertPratica";
	private static final String INSERT = "insertPraticaTipo";
	private static final String UPLOAD = "uploadPratica";
	private static final String DETAIL = "detailPraticaWeb";
	public static final String REPORT_PATH = "/reports/";

	public static final String MIMETYPES_UNKNOWN = "application/unknown";

	@Inject
	private GestionePraticheWebService inserimentoPraticaService;

	@Inject
	private ComuneRepository comuneRepository;

	@Autowired
	public ResourceBundleMessageSource messageSource;

	@Inject
	private PortletHelper helper;

	@Inject
	@Named("praticaWebValidator")
	private Validator validator;

	@Inject
	@Named("docUploadValidator")
	private Validator uploadValidator;

	@Value("#{applicationProperties.nomeModello}")
	private String nomeModello;

	@Inject
	private ReportService reportService;

	@Inject
	private FascicoloUtenteService fascicoloService;

	@Autowired
	private ConversionService conversionService;

	/**
	 * Default entry quando la portlet viene visualizzata per la prima volta.
	 *
	 * @param model il view model da popolare
	 * @return il view ID da renderizzare.
	 */
	@RenderMapping
	public String homePage(Model model, PortletSession session, PortletRequest actionRequest) throws Exception {
		log.debug("homePage-gestionePratichePortletController");
		session.setAttribute("datiPratica", null);
		setElencoDecodifichePratica(model, actionRequest);
		ProfiloUtenteDto profilo = ServiceHelper.convertToProfiloUtente(helper.getProfiloUtente(session));
		List<PraticaWebDto> praticaList = inserimentoPraticaService.searchPraticheWeb(profilo, helper.getUserPreferences(actionRequest));

		for (int i = 0; i < praticaList.size(); i++) {
			PraticaWebDto praticaWebDto = praticaList.get(i);

			String mainReportPath = InserimentoPraticaPortlet.class.getResource(REPORT_PATH).getPath().replaceAll("%20", " ");
			String nomePdf = mainReportPath + praticaWebDto.getTipologia().getFrontendEditJspName() + ".jrxml";
			// String mainReportPath =
			// ReportServiceImpl.class.getResource(nomePdf).getPath().replaceAll("%20", " ");
			log.debug("homePage-cerco nomeFile=" + nomePdf);
			if (praticaWebDto.getInvioPec() == null || (praticaWebDto.getInvioPec() != null && !praticaWebDto.getInvioPec().equals("S"))) {
				// verifico se esiste il report pdf per la relativa tipologia di pratica
				log.debug("homePage-verifico se esiste il pdf ");
				File reportPdf = new File(nomePdf);
				if (reportPdf.exists()) {
					log.debug("homePage-esiste il pdf ");
					praticaList.get(i).setInvioPec("D");
				}
				log.debug("homePage-invioPec= " + praticaList.get(i).getInvioPec());
			}
		}
		model.addAttribute("elencoPraticheWeb", praticaList);
		return toLocalViewPath(HOME);

	}

	/**
	 * Gestisce la richiesta di modifica
	 *
	 * @param id
	 * @param model
	 * @param actionRequest
	 * @param response
	 */
	@RequestMapping(params = "action=detail")
	public void showDetail(@RequestParam("id") Long id, Model model, ActionRequest actionRequest, ActionResponse response, PortletSession session) throws Exception {

		log.debug("showDetail- IDPratica=" + id);
		setElencoDecodifichePratica(model, actionRequest);
		PraticaWebDto praticaWb = inserimentoPraticaService.getPraticaWebByPk(id, helper.getUserPreferences(actionRequest));

		if (praticaWb == null) {
			log.warn(String.format("onEdit( %d ): Entity not found", id));
			model.addAttribute("portletFormError", messageSource.getMessage("error.praticaNotExist", null, actionRequest.getLocale()));
			return;
		}

		ProfiloUtenteCittadino user = helper.getProfiloUtente(session);
		ProfiloUtenteDto prof = new ProfiloUtenteDto();
		BeanUtils.copyProperties(user, prof);
		if (user.getResidenza() != null) {
			it.osapulie.pratiche.web.ws.types.ComuneDto com = new it.osapulie.pratiche.web.ws.types.ComuneDto();
			BeanUtils.copyProperties(user.getResidenza().getComune(), com);
			prof.setComune(com);
			if (user.getResidenza().getVia() != null && !user.getResidenza().getVia().equals("null")) {
				prof.setVia(user.getResidenza().getVia());
				prof.setNrCivico(user.getResidenza().getNrCivico());
			}
		}

		praticaWb.setProfiloutente(prof);

		List<AllegatiDto> allegati = praticaWb.getTipologia().getAllegati();
		List<AllegatiPraticaDto> allegatiPratica = new ArrayList<AllegatiPraticaDto>();

		List<DocInfo> docs = inserimentoPraticaService.documentiContenuti(praticaWb.getUuidContenitore(), helper.getUserPreferences(actionRequest));
		for (int i = 0; i < allegati.size(); i++) {
			AllegatiPraticaDto allegatoPratica = new AllegatiPraticaDto();
			allegatoPratica.setAllegati(allegati.get(i));

			String nome = StringUtils.cleanString(allegati.get(i).getNomeDocumento());
			if (nome.length() > 240) {
				nome = nome.substring(0, 240);
			}
			nome = String.format("%s_%s", id, nome);

			if (docs != null && !docs.isEmpty()) {
				log.debug("showDetail- DocSize=" + docs.size());
				for (int j = 0; j < docs.size(); j++) {
					log.debug("showDetail- DocName=" + docs.get(j).getFile().getNome());
					if (docs.get(j).getFile().getNome().startsWith(nome)) {
						log.debug("File trovato con DocName=" + nome + "setto uuid=" + docs.get(j).getFile().getUuid());
						allegatoPratica.setUuidFile(docs.get(j).getFile().getUuid());
						allegatoPratica.setNomeFile(docs.get(j).getFile().getNome());
					}
				}

			}
			allegatiPratica.add(allegatoPratica);
		}
		/*
		 * COMMENTO PERCHè ALCUNE TIPOLOGIE PREVEDONO GIà IL MODELLO DI RICHIESTA E POI QUELLE CHE
		 * NON LO PREVEDONO DEVONO UPLOADARLO CON LA FUNZIONALITà DI INVIO ALLA PEC DOPO LA QUALE LA
		 * RICHIESTA DI PRATICA NON SARà PIù MODIFICABILE, QUINDI QUESTO NON DOVREBBE SERVIRE.
		 * String nome = id + "_" + StringUtils.cleanString( nomeModello ); if (docs != null &&
		 * !docs.isEmpty()) { for (int j = 0; j < docs.size(); j++) { if
		 * (docs.get(j).getFile().getNome().startsWith(nome)) { AllegatiDto allegato = new
		 * AllegatiDto(); allegato.setNomeDocumento(nomeModello); AllegatiPraticaDto allegatoPratica
		 * = new AllegatiPraticaDto(); allegatoPratica.setAllegati(allegato);
		 * allegatoPratica.setUuidFile(docs.get(j).getFile().getUuid());
		 * allegatiPratica.add(allegatoPratica); } } }
		 */
		praticaWb.getAllegati().clear();
		praticaWb.getAllegati().addAll(allegatiPratica);

		// CAMPI AGGIUNTIVI
		// List<CampiAggiuntiviDto> campiAggiuntivi =
		// (List<CampiAggiuntiviDto>)praticaWb.getTipologia().getCampiAggiuntivi();
		List<CampiAggiuntiviTipoPraticaDto> campiAggiuntivi = praticaWb.getTipologia().getCampiAggiuntiviTipoPratica();
		List<CampiAggiuntiviPraticaWebDto> datiCampiAggiuntivi = praticaWb.getCampiaggiuntivi();

		for (int i = 0; i < campiAggiuntivi.size(); i++) {
			// log.debug("controllo CA" + campiAggiuntivi.get(i).getId() + " front-end: " +
			// ServiceHelper.checkBitMask(campiAggiuntivi.get(i).getConfigCagg(),
			// ServiceHelper.FRONT_END_MAIN_PAG_MASK));

			if (ServiceHelper.checkBitMask(campiAggiuntivi.get(i).getConfigCagg(), ServiceHelper.FRONT_END_MAIN_PAG_MASK)) {
				boolean trovato = false;
				for (int j = 0; j < datiCampiAggiuntivi.size(); j++) {
					if (campiAggiuntivi.get(i).getCampiAggiuntivi().getId().equals(datiCampiAggiuntivi.get(j).getCampiAggiuntivi().getId())) {
						trovato = true;
						break;
					}
				}
				if (!trovato) {
					CampiAggiuntiviPraticaWebDto campoAggiuntivoPratica = new CampiAggiuntiviPraticaWebDto();
					campoAggiuntivoPratica.setCampiAggiuntivi(campiAggiuntivi.get(i).getCampiAggiuntivi());
					datiCampiAggiuntivi.add(campoAggiuntivoPratica);
				}
			}
		}
		praticaWb.getCampiaggiuntivi().addAll(datiCampiAggiuntivi);

		model.addAttribute("datiPratica", praticaWb);
		// response.setRenderParameter("action", "renderDetailForm");
		response.setRenderParameter("action", "renderEditForm");
	}

	/**
	 * Gestisce la richiesta di eliminazione di un allegato
	 *
	 * @param id
	 * @param page
	 * @param model
	 * @param actionRequest
	 * @param response
	 * @param session
	 */
	@ActionMapping(params = "action=deleteAllegato")
	public void onDeleteAllegato(@RequestParam("uuidAllegato") String uuidAllegato, @ModelAttribute("datiPratica") PraticaWebDto entity, Model model, ActionRequest actionRequest,
			ActionResponse response, PortletSession session) throws Exception {
		log.debug("onDeleteAllegato nome=" + uuidAllegato);
		setElencoDecodifichePratica(model, actionRequest);
		List<AllegatiPraticaDto> allegati = entity.getAllegati();
		for (int i = 0; i < allegati.size(); i++) {
			log.debug("id" + i + "=" + allegati.get(i).getAllegati().getNomeDocumento());
			if (allegati.get(i).getUuidFile() != null && allegati.get(i).getUuidFile().equals(uuidAllegato)) {
				log.debug("elimino il nodo con uuid=" + uuidAllegato);
				EliminaAllegatoResponse resp = inserimentoPraticaService.eliminaDocumento(uuidAllegato, helper.getUserPreferences(actionRequest));
				if (resp.isEliminato()) {
					log.debug("allegato trovato");
					entity.getAllegati().get(i).setUuidFile("");
					model.addAttribute("message", messageSource.getMessage("message.label.cancellazioneEseguitaCorrettamente", null, actionRequest.getLocale()));
				}
				else {
					model.addAttribute("portletFormError", resp.getError().getDescrizione());
				}
			}
		}

		// PraticaDto pratica = gestionePraticheService.getPraticaByPk(id);
		model.addAttribute("datiPratica", entity);
		model.addAttribute("tab", "vis_documenti");
		response.setRenderParameter("action", "renderEditForm");
	}

	/*
	 * ELIMINATA QUESTA FUNZIONALITA' PERCHE' CONSENTIVA LA CANCELLAZIONE SENZA IL CONTROLLO DI UN
	 * NUOVO UPLOAD IN MODIFICA.
	 *
	 * @ActionMapping(params = "action=deleteAllegato") public void
	 * onDeleteAllegato(@RequestParam("nome") String nome,@ModelAttribute("datiPratica")
	 * PraticaWebDto entity, Model model, ActionRequest actionRequest, ActionResponse response,
	 * PortletSession session)throws Exception { log.debug( "onDeleteAllegato nome="+nome );
	 * setElencoDecodifichePratica(model); List<AllegatiPraticaDto> allegati = entity.getAllegati();
	 * for(int i = 0; i < allegati.size(); i++){ log.debug("id"+i+"="+allegati.get(i).getId());
	 * if(allegati.get(i).getAllegati().getNomeDocumento().equals(nome)){ String uuid =
	 * allegati.get(
	 * i).getUrlAllegato().substring(allegati.get(i).getUrlAllegato().indexOf("SpacesStore"
	 * )+12,allegati.get(i).getUrlAllegato().lastIndexOf("/"));
	 * log.debug("elimino il nodo con uuid="+uuid); documentHandler.deleteDocument(uuid);
	 * log.debug("allegato trovato"); allegati.get(i).setUrlAllegato(""); } }
	 * entity.setAllegati(allegati); //PraticaDto pratica =
	 * gestionePraticheService.getPraticaByPk(id); model.addAttribute("datiPratica", entity);
	 * model.addAttribute( "tab", "vis_documenti" ); response.setRenderParameter("action",
	 * "renderEditForm"); }
	 */

	/**
	 * Gestisce la richiesta di nuovo inserimento
	 *
	 * @param model
	 * @param response
	 */
	@ActionMapping(params = "action=newTypePraticaForm")
	public void onNewType(Model model, PortletSession session, ActionResponse response, ActionRequest actionRequest) throws Exception {

		log.debug("onNewType-inserimentoPraticaPortletController ");
		setElencoDecodifichePratica(model, actionRequest);
		ProfiloUtenteCittadino user = helper.getProfiloUtente(session);
		ProfiloUtenteDto prof = new ProfiloUtenteDto();
		BeanUtils.copyProperties(user, prof);
		if (user.getResidenza() != null) {
			it.osapulie.pratiche.web.ws.types.ComuneDto com = new it.osapulie.pratiche.web.ws.types.ComuneDto();
			BeanUtils.copyProperties(user.getResidenza().getComune(), com);
			prof.setComune(com);
			if (user.getResidenza().getVia() != null && !user.getResidenza().getVia().equals("null")) {
				prof.setVia(user.getResidenza().getVia());
				prof.setNrCivico(user.getResidenza().getNrCivico());
			}
		}
		PraticaWebDto pratica = new PraticaWebDto();
		pratica.setProfiloutente(prof);
		pratica.setTecnico(user.getCognome() + " " + user.getNome());

		pratica.setDataRichiesta(Calendar.getInstance());

		/*
		 * pratica.setRichCf(user.getCodiceFiscale()); if (user.getResidenza() != null) {
		 * it.osapulie.pratiche.web.ws.types.ComuneDto com = new
		 * it.osapulie.pratiche.web.ws.types.ComuneDto();
		 * BeanUtils.copyProperties(user.getResidenza().getComune(), com);
		 * pratica.setRichComune(com); if(user.getResidenza().getVia() != null &&
		 * !user.getResidenza().getVia().equals( "null" ))
		 * pratica.setRichIndirizzo(user.getResidenza().getVia() + " " +
		 * user.getResidenza().getNrCivico()); } else { pratica.setRichComune(null); }
		 * pratica.setRichEmail(user.getMail()); pratica.setRichNominativo(user.getCognome() + " " +
		 * user.getNome()); pratica.setRichNome(user.getNome());
		 * pratica.setRichCognome(user.getCognome());
		 */
		// Se la tipologia disponibile a frontend e' solo una, salto la jsp di selezione
		// della tipologia e passo direttamente all'inserimento dei dati della pratica.
		List<TipoPraticaDto> tipiPratica = (List<TipoPraticaDto>) model.asMap().get("tipologie");
		String renderAction = "renderInsertForm";
		if (tipiPratica.size() == 1) {
			pratica.setTipologia(tipiPratica.get(0));
			setElencoAllegatiCampiAggiuntivi_byTipoPratica(pratica, actionRequest);

			renderAction = "renderEditForm";
		}

		model.addAttribute("datiPratica", pratica);
		response.setRenderParameter("action", renderAction);
	}

	/**
	 * Gestisce la richiesta di nuovo inserimento
	 *
	 * @param model
	 * @param response
	 */
	@ActionMapping(params = "action=newPraticaForm")
	public void onNew(@ModelAttribute("datiPratica") PraticaWebDto pratica, Model model, ActionResponse response, ActionRequest actionRequest) throws Exception {

		log.debug("onNew-gestionePratichePortletController ");
		setElencoDecodifichePratica(model, actionRequest);

		if (pratica.getTipologia() == null) {
			model.addAttribute("portletFormError", messageSource.getMessage("label.selezionaTipoPratica", null, actionRequest.getLocale()));
			response.setRenderParameter("action", "renderInsertForm");
		}
		else {
			setElencoAllegatiCampiAggiuntivi_byTipoPratica(pratica, actionRequest);
			model.addAttribute("datiPratica", pratica);
			response.setRenderParameter("action", "renderEditForm");
		}
	}

	/**
	 * Metodo che gestisce l'operazione di salvataggio dei dati inseriti nella form
	 *
	 * @param entity
	 * @param bindingResult
	 * @param aggiungi
	 * @param rimuovi
	 * @param rimuoviAttore
	 * @param filtriRicercaPratiche
	 * @param portletRequest
	 * @param session
	 * @param cerca_richiedente
	 * @param model
	 * @param actionRequest
	 * @param response
	 */
	@ActionMapping(params = "action=save")
	public void onSaveEntity(@ModelAttribute("datiPratica") PraticaWebDto entity, BindingResult bindingResult, @RequestParam(required = false, value = "cerca_richiedente") String cerca_richiedente,
			Model model, PortletRequest portletRequest, PortletSession session, ActionRequest actionRequest, ActionResponse response) throws IOException, Exception {

		log.debug("onSaveEntity-gestionePratichePortletController pratica= " + entity);
		/*
		 * if(cerca_richiedente != null){ helper.setEntityInSession(portletRequest, "datiPratica",
		 * entity); log.debug( "renderRicercarichiedente-inserimentoPraticheWebPortletController "
		 * ); Map<String,String> portletParams = new HashMap<String,String>();
		 * portletParams.put("from","inserimento-pratica"); PortletURL portletURL =
		 * helper.createPortletUrlToExternalPortlet
		 * (actionRequest,response,"gestionereferenti_WAR_servizicomuniportlet"
		 * ,portletParams,PortletRequest.RENDER_PHASE);
		 * response.sendRedirect(portletURL.toString());
		 *
		 * return; }else{
		 */
		try {
			validator.validate(entity, bindingResult);
			setElencoDecodifichePratica(model, actionRequest);
			if (bindingResult.hasErrors()) {
				for (FieldError field : bindingResult.getFieldErrors()) {
					log.error("Field Error: " + field.getObjectName() + " - " + field.getField() + " - " + field.getCode() + " - " + field.getDefaultMessage());
				}
				response.setRenderParameter("action", "renderEditForm");
				if (bindingResult.getFieldErrors("allegati*") != null && (bindingResult.getFieldErrorCount() - bindingResult.getFieldErrorCount("allegati*")) <= 0) {
					model.addAttribute("tab", "vis_documenti");
				}

				model.addAttribute("portletFormError", messageSource.getMessage("error.verificareDatiInseriti", null, actionRequest.getLocale()));

			}
			else {
				// Imposto la descrizione Immobile pari alla Tipologia.

				if (entity.getTipologia().getShowLocalita() != 0) {
					entity.setImmobileDen(entity.getImmobileTipo().getDescrizione());
				}

				entity.setRichNominativo(entity.getRichCognome() + " " + entity.getRichNome());
				entity.setStato("");
				SavePraticaWebResponse resp = inserimentoPraticaService.savePratica(entity, helper.getUserPreferences(portletRequest)).getReturn();
				if (resp.isSalvato()) {
					// SCRIVO NEL FASCICOLO
					Fascicolo fascicolo = new Fascicolo();
					fascicolo.setIdProfiloUtente(helper.getProfiloUtente(session));
					if (entity.getId() == null) {
						fascicolo.setServizio(messageSource.getMessage("label.nuovapratica.servizio1", null, portletRequest.getLocale()));
					}
					else {
						fascicolo.setServizio(messageSource.getMessage("label.modificapratica.servizio1", null, portletRequest.getLocale()) + " " + entity.getId());

						List<AllegatiDto> allegati = entity.getTipologia().getAllegati();
						List<AllegatiPraticaDto> allegatiPratica = new ArrayList<AllegatiPraticaDto>();

						List<DocInfo> docs = inserimentoPraticaService.documentiContenuti(entity.getUuidContenitore(), helper.getUserPreferences(actionRequest));
						for (int i = 0; i < allegati.size(); i++) {
							AllegatiPraticaDto allegatoPratica = new AllegatiPraticaDto();
							allegatoPratica.setAllegati(allegati.get(i));

							String nome = StringUtils.cleanString(allegati.get(i).getNomeDocumento());
							if (nome.length() > 240) {
								nome = nome.substring(0, 240);
							}
							nome = String.format("%s_%s", entity.getId(), nome);

							if (docs != null && !docs.isEmpty()) {
								log.debug("showDetail- DocSize=" + docs.size());
								for (int j = 0; j < docs.size(); j++) {
									log.debug("showDetail- DocName=" + docs.get(j).getFile().getNome());
									if (docs.get(j).getFile().getNome().startsWith(nome)) {
										log.debug("File trovato con DocName=" + nome + "setto uuid=" + docs.get(j).getFile().getUuid());
										allegatoPratica.setUuidFile(docs.get(j).getFile().getUuid());
										allegatoPratica.setNomeFile(docs.get(j).getFile().getNome());
									}
								}

							}
							allegatiPratica.add(allegatoPratica);
						}
						entity.getAllegati().clear();
						entity.getAllegati().addAll(allegatiPratica);

						model.addAttribute("datiPratica", entity);
						response.setRenderParameter("action", "renderEditForm");

					}
					fascicolo.setUserPreferences(helper.getUserPreferences(portletRequest));
					fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_INSERIMENTO_NUOVA_PRATICA);
					fascicolo.setRicercabileDaComune(true);
					fascicolo.setNumeroProtocollo(null);
					fascicolo.setChecksum(null);
					fascicoloService.saveNuovaRichiesta(fascicolo);
					// userLogService.saveOperazione(helper.getProfiloUtente(session),
					// TipoOperazione.inserisci,OggettoOperazione.praticaWeb,entity.getId(), null);
					model.addAttribute("message", messageSource.getMessage("message.label.operazioneEseguitaCorrettamente", null, actionRequest.getLocale()));
				}
				else {
					model.addAttribute("portletFormError", resp.getError().getDescrizione());
				}
			}
		}
		catch (Throwable e) {
			log.error("onSaveEntity :: " + e.getMessage(), e);
		}
		// }
	}

	/**
	 * Gestisce il ritorno dalla ricerca del referente
	 *
	 * @param model
	 * @return String
	 */
	// @RenderMapping(params = "action=ritornoFromReferenti")
	// public String ritornoFromReferenti(Model model,PortletRequest request)throws Exception {
	//
	// log.info(String.format("ritornoFromReferenti( %s )",request.getParameter("idReferente") ));
	// setElencoDecodifichePratica(model);
	// model.addAttribute("canInsert",
	// helper.userHasPermissionInsert(helper.getOsApulieUserDetails()));
	//
	// if(request.getParameter("idReferente") != null &&
	// !request.getParameter("idReferente").equals("")){
	// ReferenteDto rich =
	// referenteRepository.findOne(Long.parseLong(request.getParameter("idReferente").toString()));
	// // setElencoDecodifichePratica(model);
	// if (helper.getEntityFromSession(request, "datiPratica") != null){
	// PraticaWebDto datiPratica = new PraticaWebDto();
	// log.debug("datiPratica != null");
	// datiPratica = (PraticaWebDto) helper.getEntityFromSession(request, "datiPratica");
	// datiPratica.setRichNominativo(rich.getNominativo());
	// datiPratica.setRichCap(rich.getCap());
	// datiPratica.setRichCell(rich.getCell());
	// datiPratica.setRichCf(rich.getCf());
	// datiPratica.setRichPiva(rich.getPiva());
	// datiPratica.setRichComune(rich.getComune());
	// datiPratica.setRichEmail(rich.getEmail());
	// datiPratica.setRichIndirizzo(rich.getIndirizzo());
	//
	// model.addAttribute("datiPratica", datiPratica);
	// helper.setEntityInSession(request, "datiPratica", null);
	// return toLocalViewPath( EDIT+datiPratica.getTipologia().getId() );
	// }
	//
	// }
	// return toLocalViewPath( HOME );
	// }
	/**
	 * Metodo che recupera il modello rtf e sostituisce i dati inseriti dall'utente e ne consente il
	 * download
	 *
	 * @param id
	 * @param model
	 * @param response
	 * @param session
	 * @param datiPratica
	 * @param portletRequest
	 * @throws Exception
	 * @throws Throwable
	 */
	@ResourceMapping("downloadModello")
	public void serveModello(@RequestParam("id") Long id, Model model, ResourceResponse response, PortletSession session, PortletRequest portletRequest, PortletResponse portletResponse)
			throws Exception, Throwable {
		log.debug("download Modello id= ..." + id);
		PraticaWebDto datiPratica = inserimentoPraticaService.getPraticaWebByPk(id, helper.getUserPreferences(portletRequest));

		List<AllegatiDto> allegati = datiPratica.getTipologia().getAllegati();
		List<AllegatiPraticaDto> allegatiPratica = new ArrayList<AllegatiPraticaDto>();

		List<DocInfo> docs = inserimentoPraticaService.documentiContenuti(datiPratica.getUuidContenitore(), helper.getUserPreferences(portletRequest));
		for (int i = 0; i < allegati.size(); i++) {
			AllegatiPraticaDto allegatoPratica = new AllegatiPraticaDto();
			allegatoPratica.setAllegati(allegati.get(i));

			String nome = StringUtils.cleanString(allegati.get(i).getNomeDocumento());
			if (nome.length() > 240) {
				nome = nome.substring(0, 240);
			}
			nome = String.format("%s_%s", id, nome);

			if (docs != null && !docs.isEmpty()) {
				log.debug("showDetail- DocSize=" + docs.size());
				for (int j = 0; j < docs.size(); j++) {
					log.debug("showDetail- DocName=" + docs.get(j).getFile().getNome());
					if (docs.get(j).getFile().getNome().startsWith(nome)) {
						log.debug("File trovato con DocName=" + nome + "setto uuid=" + docs.get(j).getFile().getUuid());
						allegatoPratica.setUuidFile(docs.get(j).getFile().getUuid());
						allegatoPratica.setNomeFile(docs.get(j).getFile().getNome());
					}
				}

			}
			allegatiPratica.add(allegatoPratica);
		}
		datiPratica.getAllegati().addAll(allegatiPratica);

		List<PraticaWebDto> beans = new ArrayList<PraticaWebDto>();
		beans.add(datiPratica);

		Map<String, Object> parameters = new HashMap<String, Object>();
		if (datiPratica.getRichDataNascita() != null) {
			parameters.put("richDataNascitaDate", datiPratica.getRichDataNascita().getTime());
		}

		byte[] reportBytes = reportService.jrxmlToPdf(parameters, beans, REPORT_PATH + datiPratica.getTipologia().getFrontendEditJspName() + ".jrxml", null);

		// String md5 = CheckSumGenerator.generateCheckSum(reportBytes);

		// log.debug(md5);

		String reportFileName = String.format(datiPratica.getTipologia().getDescrizione() + ".pdf");
		// userLogService.saveOperazione(helper.getProfiloUtente(session),TipoOperazione.stampa,OggettoOperazione.pratica,datiPratica.getId(),"Download
		// modello: "+reportFileName);

		helper.sendStreamAsAttachment(reportBytes, response, reportFileName, ContentMimeTypes.PDF);
	}

	/**
	 * Restituisce la jsp da visualizzare per il dettaglio della pratica
	 *
	 * @param model
	 * @return String
	 */
	@RenderMapping(params = "action=renderDetailForm")
	public String renderDetailForm(Model model) {
		log.info(String.format("renderDetailForm( %s )", model.asMap().get("datiPratica")));

		return toLocalViewPath(DETAIL);
	}

	/**
	 * Restituisce la form per l'upload delle dichiarazioni e doc.
	 *
	 * @param model
	 * @param response
	 */
	@RequestMapping(params = "ope=getUploadForm")
	public void getUploadForm(@RequestParam("id") Long id, Model model, ActionResponse response, PortletRequest actionRequest, ActionRequest request, PortletSession session) throws Exception {
		PraticaWebDto datiPratica = inserimentoPraticaService.getPraticaWebByPk(id, helper.getUserPreferences(actionRequest));
		ProfiloUtenteCittadino user = helper.getProfiloUtente(session);
		ProfiloUtenteDto prof = new ProfiloUtenteDto();
		BeanUtils.copyProperties(user, prof);
		if (user.getResidenza() != null) {
			it.osapulie.pratiche.web.ws.types.ComuneDto com = new it.osapulie.pratiche.web.ws.types.ComuneDto();
			BeanUtils.copyProperties(user.getResidenza().getComune(), com);
			prof.setComune(com);
			if (user.getResidenza().getVia() != null && !user.getResidenza().getVia().equals("null")) {
				prof.setVia(user.getResidenza().getVia());
				prof.setNrCivico(user.getResidenza().getNrCivico());
			}
		}

		datiPratica.setProfiloutente(prof);
		// model.addAttribute("datiPratica", datiPratica);
		List<DocInfo> docs = inserimentoPraticaService.documentiContenuti(datiPratica.getUuidContenitore(), helper.getUserPreferences(actionRequest));

		// CAMPI AGGIUNTIVI
		List<CampiAggiuntiviTipoPraticaDto> campiAggiuntivi = datiPratica.getTipologia().getCampiAggiuntiviTipoPratica();
		List<CampiAggiuntiviPraticaWebDto> datiCampiAggiuntivi = datiPratica.getCampiaggiuntivi();

		for (int i = 0; i < campiAggiuntivi.size(); i++) {

			if (ServiceHelper.checkBitMask(campiAggiuntivi.get(i).getConfigCagg(), ServiceHelper.FRONT_END_MAIN_PAG_MASK)) {
				boolean trovato = false;
				for (int j = 0; j < datiCampiAggiuntivi.size(); j++) {
					if (campiAggiuntivi.get(i).getCampiAggiuntivi().getId().equals(datiCampiAggiuntivi.get(j).getCampiAggiuntivi().getId())) {
						trovato = true;
						break;
					}
				}
				if (!trovato) {
					CampiAggiuntiviPraticaWebDto campoAggiuntivoPratica = new CampiAggiuntiviPraticaWebDto();
					campoAggiuntivoPratica.setCampiAggiuntivi(campiAggiuntivi.get(i).getCampiAggiuntivi());
					datiCampiAggiuntivi.add(campoAggiuntivoPratica);
				}
			}
		}
		datiPratica.getCampiaggiuntivi().addAll(datiCampiAggiuntivi);

		// controllo obbligatorieta' degli allegati.
		List<AllegatiDto> allegati = datiPratica.getTipologia().getAllegati();
		List<AllegatiPraticaDto> allegatiPratica = new ArrayList<AllegatiPraticaDto>();
		boolean trovato2 = false;
		for (int i = 0; i < allegati.size(); i++) {
			AllegatiPraticaDto allegatoPratica = new AllegatiPraticaDto();
			allegatoPratica.setAllegati(allegati.get(i));

			String nome = StringUtils.cleanString(allegati.get(i).getNomeDocumento());
			if (nome.length() > 240) {
				nome = nome.substring(0, 240);
			}
			nome = String.format("%s_%s", id, nome);
			if (docs != null && !docs.isEmpty()) {
				log.debug("showDetail- DocSize=" + docs.size());
				for (int j = 0; j < docs.size(); j++) {
					log.debug("showDetail- DocName=" + docs.get(j).getFile().getNome());
					if (docs.get(j).getFile().getNome().startsWith(nome)) {
						log.debug("File trovato con DocName=" + nome + "setto uuid=" + docs.get(j).getFile().getUuid());
						allegatoPratica.setUuidFile(docs.get(j).getFile().getUuid());
						allegatoPratica.setNomeFile(docs.get(j).getFile().getNome());
					}
				}

			}
			allegatiPratica.add(allegatoPratica);

			if (allegati.get(i).isObbligatorio()) {
				boolean trovato = false;

				if (docs != null && !docs.isEmpty()) {

					for (int j = 0; j < docs.size(); j++) {
						log.debug("showDetail- DocName=" + docs.get(j).getFile().getNome());
						if (docs.get(j).getFile().getNome().startsWith(nome)) {
							log.debug("File trovato con DocName=" + nome + "setto uuid=" + docs.get(j).getFile().getUuid());
							trovato = true;
						}
					}

				}
				if (!trovato) {
					trovato2 = true;
				}
			}
		}
		datiPratica.getAllegati().clear();
		datiPratica.getAllegati().addAll(allegatiPratica);

		if (!trovato2) {

			UploadItemPratiche item = new UploadItemPratiche();

			String nome = id + "_" + StringUtils.cleanString(nomeModello);

			if (docs != null && !docs.isEmpty()) {
				for (int j = 0; j < docs.size(); j++) {
					if (docs.get(j).getFile().getNome().startsWith(nome)) {
						/*
						 * AllegatiDto allegato = new AllegatiDto( );
						 * allegato.setNomeDocumento(nomeModello); AllegatiPraticaDto
						 * allegatoPratica = new AllegatiPraticaDto();
						 * allegatoPratica.setAllegati(allegato);
						 * allegatoPratica.setUuidFile(docs.get(j).getFile().getUuid());
						 * allegatiPratica.add(allegatoPratica);
						 */
						byte[] content = inserimentoPraticaService.downloadDocumento(datiPratica.getUuidContenitore(), docs.get(j).getUuid(), helper.getUserPreferences(actionRequest));

						docs.get(j).getFile().setContent(content);
						log.debug("getUploadForm :: file.size = " + docs.get(j).getFile().getContent().length);
						item.setDocumento(docs.get(j));
					}
				}
			}
			model.addAttribute("datiPratica", datiPratica);
			model.addAttribute("uploadItem", item);
			response.setRenderParameter("action", "renderUploadForm");
		}
		else {
			setElencoDecodifichePratica(model, actionRequest);
			model.addAttribute("portletFormError", messageSource.getMessage("error.allegatiObbligatori", null, actionRequest.getLocale()));
			model.addAttribute("datiPratica", datiPratica);
			response.setRenderParameter("action", "renderEditForm");

		}
	}

	/**
	 * Mette nel model le decodifiche utili per la gestione della pratica
	 *
	 * @param model
	 */
	private void setElencoDecodifichePratica(Model model, PortletRequest actionRequest) throws Exception {
		Iterable<Comune> comuni = comuneRepository.findAll(new Sort(Sort.Direction.ASC, "denominazione"));
		model.addAttribute("comuni", comuni);
		List<TipoPraticaDto> tipologie = inserimentoPraticaService.searchTipoPraticheFrontEnd(helper.getUserPreferences(actionRequest));
		model.addAttribute("tipologie", tipologie);
		String categorie = inserimentoPraticaService.searchCategoriePratiche(helper.getUserPreferences(actionRequest));
		// String categorie=ServiceHelper.listToJson( cat );
		model.addAttribute("elencoCategoriePraticheJson", categorie);
		List<TipoImmobileDto> tipologieimm = inserimentoPraticaService.searchTipoImmobile(helper.getUserPreferences(actionRequest));
		model.addAttribute("tipologieImm", tipologieimm);
	}

	private void setElencoAllegatiCampiAggiuntivi_byTipoPratica(PraticaWebDto pratica, PortletRequest actionRequest) {
		List<AllegatiDto> allegati = inserimentoPraticaService.searchAllegatiTipoPratica(pratica.getTipologia(), helper.getUserPreferences(actionRequest));
		List<AllegatiPraticaDto> allegatiPratica = new ArrayList<AllegatiPraticaDto>();

		for (int i = 0; i < allegati.size(); i++) {
			AllegatiPraticaDto allegatoPratica = new AllegatiPraticaDto();
			allegatoPratica.setAllegati(allegati.get(i));
			allegatiPratica.add(allegatoPratica);
		}
		pratica.getAllegati().addAll(allegatiPratica);

		List<CampiAggiuntiviTipoPraticaDto> campiAggiuntivi = pratica.getTipologia().getCampiAggiuntiviTipoPratica();
		List<CampiAggiuntiviPraticaWebDto> datiCampiAggiuntivi = new ArrayList<CampiAggiuntiviPraticaWebDto>();

		for (int i = 0; i < campiAggiuntivi.size(); i++) {
			// log.debug("controllo CA" + campiAggiuntivi.get(i).getId() + " front-end: " +
			// ServiceHelper.checkBitMask(campiAggiuntivi.get(i).getConfigCagg(),
			// ServiceHelper.FRONT_END_MAIN_PAG_MASK));
			if (ServiceHelper.checkBitMask(campiAggiuntivi.get(i).getConfigCagg(), ServiceHelper.FRONT_END_MAIN_PAG_MASK)) {
				CampiAggiuntiviPraticaWebDto campoAggiuntivoPratica = new CampiAggiuntiviPraticaWebDto();
				campoAggiuntivoPratica.setCampiAggiuntivi(campiAggiuntivi.get(i).getCampiAggiuntivi());
				datiCampiAggiuntivi.add(campoAggiuntivoPratica);
			}
		}
		pratica.getCampiaggiuntivi().addAll(datiCampiAggiuntivi);
	}

	/**
	 * Presenta la form per l'upload del file.
	 *
	 * @param model
	 * @return l'ID della view
	 */
	@RenderMapping(params = "action=renderUploadForm")
	public String renderUploadForm(Model model) {
		log.debug("Model = " + model);
		return toLocalViewPath(UPLOAD);
	}

	/**
	 * Gestisce l'upload dei file
	 *
	 * @param uploadItem
	 * @param result
	 * @param response
	 * @param session
	 * @param portletRequest
	 * @param model
	 * @param selectNumAllegati
	 * @throws PortletException
	 * @throws IOException
	 */
	@RequestMapping(params = "ope=upload")
	public void upload(@ModelAttribute("uploadItem") UploadItemPratiche uploadItem, BindingResult result, @ModelAttribute("datiPratica") PraticaWebDto entity, ActionResponse response,
			PortletSession session, PortletRequest portletRequest, Model model) throws IOException, Exception {
		log.debug("uploadFile");

		uploadValidator.validate(uploadItem, result);

		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				log.error("Error: " + error.getCode() + " - " + error.getDefaultMessage());
			}
			uploadItem.setDocumento(null);
			model.addAttribute("uploadItem", uploadItem);
			model.addAttribute("portletFormError", messageSource.getMessage("error.verificareDatiInseriti", null, portletRequest.getLocale()));
			response.setRenderParameter("action", "renderUploadForm");
		}
		else {
			log.debug("srvice=" + inserimentoPraticaService);
			byte file[] = null;
			String nomeFile = null;

			if (uploadItem.getGeneratedFile() != null && uploadItem.getGeneratedFile().length() > 0 && uploadItem.getDocumento() == null) {
				nomeFile = uploadItem.getGeneratedFile().getName();
				file = FileCopyUtils.copyToByteArray(uploadItem.getGeneratedFile());
			}
			else {
				nomeFile = uploadItem.getDocumento().getUuid();
				file = null;
			}

			ProfiloUtenteDto prof = new ProfiloUtenteDto();
			BeanUtils.copyProperties(helper.getProfiloUtente(session), prof);
			UploadResponse resp = inserimentoPraticaService
					.processaUploadPratica(entity.getTipologia().getDescrizione(), nomeFile, file, prof, "" + entity.getId(), entity.getUuidContenitore(), helper.getUserPreferences(portletRequest))
					.getReturn();
			if (resp.isSalvato()) {
				entity.setInvioPec("S");
				entity.setStato("P");
				entity.setDataRichiesta(Calendar.getInstance());
				inserimentoPraticaService.savePratica(entity, helper.getUserPreferences(portletRequest)).getReturn();
				// userLogService.saveOperazione(helper.getProfiloUtente(session),
				// TipoOperazione.invio,OggettoOperazione.praticaWeb,entity.getId(), null);
				model.addAttribute("message", messageSource.getMessage("message.label.operazioneEseguitaCorrettamente", null, portletRequest.getLocale()));
			}
			else {
				model.addAttribute("portletFormError", resp.getError().getDescrizione());
			}
			response.setRenderParameter("action", "home");
		}
	}

	/**
	 * Restituisce la jsp da visualizzare per inserimento/modifica
	 *
	 * @param model
	 * @return String
	 */
	@RenderMapping(params = "action=renderEditForm")
	public String renderEditForm(Model model) {
		// log.info(String.format("renderEditForm( %s )",
		// ((PraticaWebDto)model.asMap().get("datiPratica")).getTipologia().getId()));
		// return toLocalViewPath(
		// EDIT+((PraticaWebDto)model.asMap().get("datiPratica")).getTipologia().getId() );

		log.info(String.format("renderEditForm( %s )", ((PraticaWebDto) model.asMap().get("datiPratica")).getTipologia().getFrontendEditJspName()));
		return toLocalViewPath(((PraticaWebDto) model.asMap().get("datiPratica")).getTipologia().getFrontendEditJspName());
	}

	/**
	 * Restituisce la jsp da visualizzare per inserimento/modifica
	 *
	 * @param model
	 * @return String
	 */
	@RenderMapping(params = "action=renderInsertForm")
	public String renderInsertForm(Model model) {
		log.info(String.format("renderInsertForm( %s )", model.asMap().get("datiPratica")));

		return toLocalViewPath(INSERT);
	}

	@ResourceMapping("downloadAllegato")
	public void serveAllegato(@RequestParam("uuidAllegato") String uuidAllegato, @RequestParam("fileNameAllegato") String fileNameAllegato, Model model, ResourceResponse response,
			PortletSession session, @ModelAttribute("datiPratica") PraticaWebDto datiPratica, PortletRequest portletRequest) throws Exception, Throwable {
		log.debug(String.format("download documento uuid=%s, nomeFile=%s ...", uuidAllegato, fileNameAllegato));
		byte[] contenuto = inserimentoPraticaService.downloadDocumento(datiPratica.getUuidContenitore(), uuidAllegato, helper.getUserPreferences(portletRequest));
		log.debug("ContenutoLetto " + contenuto);
		helper.sendStreamAsAttachment(contenuto, response, fileNameAllegato, MIMETYPES_UNKNOWN);
	}

	/**
	 * Utility method per calcolare e restituire il view ID all'interno della corretta alberatura di
	 * sottodirectory delle JSP: in un certo senso si tratta del namespace delle view locali a
	 * questo controller. Ad esempio: <code> toLocalViewPath( "home" ) </code> restituira' <code>
	 * "/gestionepratiche/home" </code>
	 *
	 * @param viewName l'ID della view locale
	 * @return il view ID completo del path a cui la vista appartiene
	 */
	private static String toLocalViewPath(String viewName) {
		return JSP_PATH + viewName;
	}

	/**
	 * Metodo che consente di definire dei custom binder per le diverse tipologie di dati
	 *
	 * @param binder
	 */
	@InitBinder
	public void initBinder(final PortletRequest portletRequest, PortletRequestDataBinder binder) {

		binder.setConversionService(conversionService);
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);

		// true passed to CustomDateEditor constructor means convert empty String to null
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true, 10));

		binder.registerCustomEditor(Calendar.class, new PropertyEditorSupport() {

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			@Override
			public String getAsText() {
				String asText = super.getAsText();
				if (asText == null || asText.equals("null")) {
					return "";
				}

				Object value = getValue();

				if (value instanceof GregorianCalendar) {
					Calendar calendar = (Calendar) value;
					Date time = calendar.getTime();
					asText = sdf.format(time);
				}
				return asText;
			}

			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				setValue(parseDate(text));
			}

			private Calendar parseDate(String dateString) {
				if (dateString != null && !dateString.isEmpty()) {
					try {
						Calendar cal = Calendar.getInstance();
						cal.setTime(sdf.parse(dateString));
						return cal;
					}
					catch (ParseException e) {
						log.error("parseDate :: " + e.getMessage(), e);
						return null;
					}
				}
				return null;
			}
		});

		binder.registerCustomEditor(TipoPraticaDto.class, "tipologia", new PropertyEditorSupport() {

			@Override
			public void setAsText(String text) {
				if (text != null && !text.equals("") && !text.equals("null")) {
					TipoPraticaDto type = inserimentoPraticaService.getTipoPraticaByPk(Long.parseLong(text), helper.getUserPreferences(portletRequest));
					setValue(type);
				}
			}
		});
		binder.registerCustomEditor(ComuneDto.class, new PropertyEditorSupport() {

			@Override
			public void setAsText(String text) {
				if (!text.equals("-1") && !text.equals("")) {
					Comune type = comuneRepository.findOne(Long.parseLong(text));
					ComuneDto comune = new ComuneDto();
					BeanUtils.copyProperties(type, comune);
					setValue(comune);
				}
				else {
					setValue(null);
				}
			}
		});

		binder.registerCustomEditor(TipoImmobileDto.class, new PropertyEditorSupport() {

			@Override
			public void setAsText(String text) {
				TipoImmobileDto type = inserimentoPraticaService.getTipoImmobileByPk(Long.parseLong(text), helper.getUserPreferences(portletRequest));
				setValue(type);
			}
		});

	}

	/*
	 * @InitBinder public void initBinder(WebDataBinder binder) {
	 *
	 * binder.setConversionService(conversionService);
	 *
	 * binder.registerCustomEditor(Calendar.class, new PropertyEditorSupport() {
	 *
	 * SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	 *
	 * @Override public String getAsText() { String asText = super.getAsText(); if (asText == null
	 * || asText.equals("null")) { return ""; }
	 *
	 * Object value = getValue();
	 *
	 * if (value instanceof GregorianCalendar) { Calendar calendar = (Calendar) value; Date time =
	 * calendar.getTime(); asText = sdf.format(time); } return asText; }
	 *
	 * @Override public void setAsText(String text) throws IllegalArgumentException {
	 * setValue(parseDate(text)); }
	 *
	 * private Calendar parseDate(String dateString) { if (dateString != null &&
	 * !dateString.isEmpty()) { try { Calendar cal = Calendar.getInstance();
	 * cal.setTime(sdf.parse(dateString)); return cal; } catch (ParseException e) {
	 * log.error("parseDate :: " + e.getMessage(), e); return null; } } return null; } }); }
	 */

}
