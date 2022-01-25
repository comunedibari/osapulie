package it.osapulie.pratiche.web.portlet.consultazionepratiche;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.ResourceResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.dto.Fascicolo;
import it.osapulie.pratiche.service.GestionePraticheWebService;
import it.osapulie.pratiche.web.portlet.varie.PortletConfiguration;
import it.osapulie.pratiche.web.portlet.varie.PortletConstants;
import it.osapulie.pratiche.web.portlet.varie.ServiceHelper;
import it.osapulie.pratiche.web.ws.types.AllegatiPraticaDto;
import it.osapulie.pratiche.web.ws.types.DatiRicercaPraticaWeb;
import it.osapulie.pratiche.web.ws.types.PraticaDto;
import it.osapulie.pratiche.web.ws.types.StatoPraticaDto;
import it.osapulie.pratiche.web.ws.types.TipoPraticaDto;
import it.osapulie.service.FascicoloUtenteService;
import it.osapulie.web.portlet.controller.BaseController;
import it.osapulie.web.portlet.util.PortletHelper;

/**
 * Portlet controller per il consultazione pratiche.
 *
 * @author Birtolo Maria Michela
 */
@Controller("consultazionePratichePortletController")
@RequestMapping("view")
@SessionAttributes({ "elencoPratiche", "filtriRicercaPraticheOnline", "datiPratica" })
public class ConsultazionePratichePortlet extends BaseController {

	private static Logger log = LoggerFactory.getLogger(ConsultazionePratichePortlet.class);

	private static final String JSP_PATH = "consultazionepratiche/";
	private static final String HOME = "home";
	private static final String DETAIL = "detailPratica";

	public static final String MIMETYPES_UNKNOWN = "application/unknown";

	@Inject
	private GestionePraticheWebService consultazionePraticheService;

	@Autowired
	public ResourceBundleMessageSource messageSource;

	@Inject
	private PortletHelper helper;

	@Inject
	private PortletConfiguration config;

	@Inject
	private FascicoloUtenteService fascicoloService;

	/**
	 * Default entry quando la portlet viene visualizzata per la prima volta.
	 *
	 * @param model il view model da popolare
	 * @return il view ID da renderizzare.
	 */
	@RenderMapping
	public String homePage(Model model, PortletSession session, PortletRequest actionRequest) throws Exception {
		log.debug("homePage-consultazionePratichePortletController");
		model.addAllAttributes(config.getConfiguration());
		List<StatoPraticaDto> stati = consultazionePraticheService.searchStatoPratica(helper.getUserPreferences(actionRequest));
		model.addAttribute("stati", stati);
		List<TipoPraticaDto> tipologie = consultazionePraticheService.searchTipoPraticheFrontEnd(helper.getUserPreferences(actionRequest));
		model.addAttribute("tipologie", tipologie);
		DatiRicercaPraticaWeb filtriRicercaPraticheOnline;
		if (!model.containsAttribute("filtriRicercaPraticheOnline")) {
			filtriRicercaPraticheOnline = new DatiRicercaPraticaWeb();
		}
		else {
			filtriRicercaPraticheOnline = (DatiRicercaPraticaWeb) model.asMap().get("filtriRicercaPraticheOnline");
		}

		ProfiloUtenteCittadino user = helper.getProfiloUtente(session);
		filtriRicercaPraticheOnline.setUtente(user.getCodiceFiscale());

		model.addAttribute("filtriRicercaPraticheOnline", filtriRicercaPraticheOnline);

		List<PraticaDto> elencoPratiche = new ArrayList<PraticaDto>();

		if (user.getCodiceFiscale() != null && !user.getCodiceFiscale().equals("")) {
			elencoPratiche = consultazionePraticheService.searchPratiche(filtriRicercaPraticheOnline, helper.getUserPreferences(actionRequest));
		}

		model.addAttribute("elencoPratiche", elencoPratiche);

		return toLocalViewPath(HOME);

	}

	/**
	 * Metodo che gestisce le richieste di ricerca della pratiche
	 *
	 * @param request
	 * @param model
	 * @return il view ID da renderizzare.
	 */
	@RequestMapping(params = "action=searchPraticaForm")
	public void searchPratiche(@ModelAttribute("filtriRicercaPraticheOnline") DatiRicercaPraticaWeb filtriRicercaPraticheOnline, PortletRequest request, Model model, ActionRequest actionRequest,
			ActionResponse response, PortletSession session) throws Exception {
		log.debug("searchPratiche-consultazionePratichePortletController");
		model.addAllAttributes(config.getConfiguration());
		model.addAttribute("filtriRicercaPratiche", filtriRicercaPraticheOnline);
		List<PraticaDto> praticaList = new ArrayList<PraticaDto>();
		ProfiloUtenteCittadino user = helper.getProfiloUtente(session);
		if (user.getCodiceFiscale() != null && !user.getCodiceFiscale().equals("")) {
			praticaList = consultazionePraticheService.searchPratiche(filtriRicercaPraticheOnline, helper.getUserPreferences(actionRequest));
		}
		model.addAttribute("elencoPratiche", praticaList);
		response.setRenderParameter("action", toLocalViewPath(HOME));

	}

	@ActionMapping(params = "action=save")
	public void onSaveEntity(@ModelAttribute("datiPratica") PraticaDto entity, Model model, PortletRequest portletRequest, PortletSession session, ActionRequest actionRequest, ActionResponse response)
			throws IOException, Exception {

		log.debug("onSaveEntity-ConsultazionePratichePortletController pratica= " + entity);

		try {

			AllegatiPraticaDto allegato = null;
			for (int i = 0; i < entity.getAllegati().size(); i++) {
				if (entity.getAllegati().get(i).getAllegato() != null && entity.getAllegati().get(i).getAllegato().length > 0 && entity.getAllegati().get(i).getAllegato().length <= 10485760) {
					allegato = entity.getAllegati().get(i);
					break;
				}
			}
			if (allegato != null) {

				boolean resp = consultazionePraticheService
						.uploadAllegatiToPratica(allegato.getNomeFile(), allegato.getAllegato(), entity.getNumeroPratica(), allegato.getId(), helper.getUserPreferences(portletRequest)).isReturn();
				if (resp) {
					// SCRIVO NEL FASCICOLO
					Fascicolo fascicolo = new Fascicolo();
					fascicolo.setIdProfiloUtente(helper.getProfiloUtente(session));
					fascicolo.setServizio(messageSource.getMessage("label.pratica.servizio2", null, portletRequest.getLocale()) + " " + entity.getNumeroPratica());

					fascicolo.setUserPreferences(helper.getUserPreferences(portletRequest));
					fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_CONSULTAZIONE_PRATICA);
					fascicolo.setRicercabileDaComune(true);
					fascicolo.setNumeroProtocollo(null);
					fascicolo.setChecksum(null);
					fascicoloService.saveNuovaRichiesta(fascicolo);
					model.addAttribute("message", messageSource.getMessage("message.label.operazioneEseguitaCorrettamente", null, actionRequest.getLocale()));
					model.addAllAttributes(config.getConfiguration());
					PraticaDto pratica = consultazionePraticheService.getPraticaByPk(entity.getId(), helper.getUserPreferences(actionRequest));

					if (pratica != null) {
						byte config = pratica.getTipologia().getConfigurazione();
						if (ServiceHelper.checkBitMask(config, ServiceHelper.SHOW_MITT_DEST)) {
							pratica.getTipologia().setShowMittDest(ServiceHelper.SHOW_MITT_DEST);
						}
						if (ServiceHelper.checkBitMask(config, ServiceHelper.SHOW_STATO)) {
							pratica.getTipologia().setShowStato(ServiceHelper.SHOW_STATO);
						}
						if (ServiceHelper.checkBitMask(config, ServiceHelper.SHOW_TIP_INT)) {
							pratica.getTipologia().setShowTipoInt(ServiceHelper.SHOW_TIP_INT);
						}
						if (ServiceHelper.checkBitMask(config, ServiceHelper.SHOW_LOCALITA)) {
							pratica.getTipologia().setShowLocalita(ServiceHelper.SHOW_LOCALITA);
						}
					}
					model.addAttribute("datiPratica", pratica);
				}
				else {
					model.addAttribute("portletFormError", messageSource.getMessage("message.error.operazioneNonEseguitaCorrettamente", null, actionRequest.getLocale()));
				}

			}
			else {
				model.addAttribute("tab", "vis_documenti");
				model.addAttribute("portletFormError", messageSource.getMessage("error.verificareDatiInseriti", null, actionRequest.getLocale()) + "\r\n"
						+ messageSource.getMessage("Maximum.upload.size.exceded", null, actionRequest.getLocale()));
			}
			response.setRenderParameter("action", "renderDetailForm");
		}
		catch (Throwable e) {
			log.error("onSaveEntity :: " + e.getMessage(), e);
		}
		// }
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
	public void showDetail(@RequestParam("id") Long id, Model model, @ModelAttribute("filtriRicercaPraticheOnline") DatiRicercaPraticaWeb filtriRicercaPraticheOnline, ActionRequest actionRequest,
			ActionResponse response) throws Exception {

		log.debug("showDetail- IDPratica=" + id);
		model.addAllAttributes(config.getConfiguration());
		PraticaDto pratica = consultazionePraticheService.getPraticaByPk(id, helper.getUserPreferences(actionRequest));

		if (pratica != null) {
			byte config = pratica.getTipologia().getConfigurazione();
			if (ServiceHelper.checkBitMask(config, ServiceHelper.SHOW_MITT_DEST)) {
				pratica.getTipologia().setShowMittDest(ServiceHelper.SHOW_MITT_DEST);
			}
			if (ServiceHelper.checkBitMask(config, ServiceHelper.SHOW_STATO)) {
				pratica.getTipologia().setShowStato(ServiceHelper.SHOW_STATO);
			}
			if (ServiceHelper.checkBitMask(config, ServiceHelper.SHOW_TIP_INT)) {
				pratica.getTipologia().setShowTipoInt(ServiceHelper.SHOW_TIP_INT);
			}
			if (ServiceHelper.checkBitMask(config, ServiceHelper.SHOW_LOCALITA)) {
				pratica.getTipologia().setShowLocalita(ServiceHelper.SHOW_LOCALITA);
			}
		}

		if (pratica == null) {
			log.warn(String.format("onEdit( %d ): Entity not found", id));
			model.addAttribute("portletFormError", messageSource.getMessage("error.praticaNotExist", null, actionRequest.getLocale()));
			return;
		}
		try {
			// Setting The Value in the PortletPreferences table
			// settare le preferenze in base al nome del site associato alla pratica selezionata.
			/*
			 * PortletPreferences prefs =
			 * PortletPreferencesFactoryUtil.getPortletPreferences(PortalUtil
			 * .getHttpServletRequest(actionRequest), "ShareSiteDocLib_WAR_share"); String siteId =
			 * pratica.getNumeroPratica().replace("/", ""); prefs.setValue("siteId", siteId);
			 * prefs.store(); log.debug( "prefs="+prefs.getValue("siteId", "[DEFAULT]") );
			 */
			// model.addAttribute("urlShareSite",urlShareSite);
			// List<DocInfo> doc = consultazionePraticheService.documentiContenuti(
			// pratica.getUuidContenitore() );
			// SCRIVO NEL FASCICOLO
			Fascicolo fascicolo = new Fascicolo();
			fascicolo.setIdProfiloUtente(helper.getOsApulieUserDetails().getProfiloUtenteCittadino());
			fascicolo.setServizio(messageSource.getMessage("label.consultapratica", null, actionRequest.getLocale()) + " " + pratica.getNumeroPratica());
			fascicolo.setUserPreferences(helper.getUserPreferences(actionRequest));
			fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_CONSULTAZIONE_PRATICA);
			fascicolo.setRicercabileDaComune(true);
			fascicolo.setNumeroProtocollo(null);
			fascicolo.setChecksum(null);
			fascicoloService.saveNuovaRichiesta(fascicolo);
		}
		catch (Throwable e) {
			log.error("onEdit - setPreferences :: " + e.getMessage(), e);
		}
		log.debug(String.format("download uuidContenitore=%s...", pratica.getUuidContenitore()));

		model.addAttribute("datiPratica", pratica);
		response.setRenderParameter("action", "renderDetailForm");
	}

	@ResourceMapping("downloadAllegato")
	public void serveAllegato(@RequestParam("uuidAllegato") String uuidAllegato, @RequestParam("fileNameAllegato") String fileNameAllegato, Model model, ResourceResponse response,
			PortletSession session, @ModelAttribute("datiPratica") PraticaDto datiPratica, PortletRequest portletRequest) throws Exception, Throwable {
		log.debug(String.format("download documento uuid=%s, nomeFile=%s , uuidContenitore=%s...", uuidAllegato, fileNameAllegato, datiPratica.getUuidContenitore()));
		byte[] contenuto = consultazionePraticheService.downloadDocumento(datiPratica.getUuidContenitore(), uuidAllegato, helper.getUserPreferences(portletRequest));
		log.debug("ContenutoLetto " + contenuto);
		helper.sendStreamAsAttachment(contenuto, response, fileNameAllegato, MIMETYPES_UNKNOWN);
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

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// binder.setConversionService(conversionService);
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}

}
