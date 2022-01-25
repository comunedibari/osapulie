package it.osapulie.anagrafe.web.portlet.richiestacertificatomorte.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import it.osapulie.anagrafe.service.AnagrafeCommonService;
import it.osapulie.anagrafe.service.TimbroDigitaleService;
import it.osapulie.anagrafe.service.VisureService;
import it.osapulie.anagrafe.web.portlet.util.impl.PortletConstants;
import it.osapulie.anagrafe.web.portlet.util.impl.TemplateCertificatiHelper;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici.ComponentiNucleoFamiliare;
import it.osapulie.anagrafe.web.ws.output.types.RichiestaDatiAnagrafici;
import it.osapulie.domain.Comune;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.dto.Fascicolo;
import it.osapulie.infrastructure.ContentMimeTypes;
import it.osapulie.infrastructure.ReportService;
import it.osapulie.service.ComuneService;
import it.osapulie.service.ConfigurazioneService;
import it.osapulie.service.FascicoloUtenteService;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.web.portlet.controller.BaseController;
import it.osapulie.web.portlet.util.impl.LiferayPortletHelperImpl;

/**
 * Portlet controller per il servizio richiesta Certificato di Morte.
 *
 * @author Birtolo Maria Michela
 */
@Controller("richiestaCertificatoMortePortletController")
@RequestMapping("view")
@SessionAttributes("datiAnagrafici")
public class RichiestaCertificatoMortePortletController extends BaseController {

	/**
	 *
	 */
	private static final String DATA_NASCITA_CONDITION = "dataNascitaCondition";

	private final Logger log = LoggerFactory.getLogger(RichiestaCertificatoMortePortletController.class);

	@Inject
	private LiferayPortletHelperImpl helper;

	@Inject
	private FascicoloUtenteService fascicoloService;

	@Inject
	private VisureService visureService;

	@Inject
	private ReportService reportService;

	@Inject
	private TimbroDigitaleService timbroService;

	@Autowired
	public ResourceBundleMessageSource messageSource;

	@Inject
	private TemplateCertificatiHelper templateCertificatiHelper;

	@Inject
	private AnagrafeCommonService anagrafeCommonService;

	@Inject
	private ConfigurazioneService configurazioneService;

	@Inject
	private ComuneService comuneService;

	/**
	 * Default entry quando la portlet viene visualizzata per la prima volta.
	 *
	 * @param model il view model da popolare
	 * @return il view ID da renderizzare.
	 */
	@RenderMapping
	public String homePage(Model model, PortletSession session, PortletRequest portletRequest) throws Exception {
		log.debug("homePage-richiestaCertificatoMortePortletController");
		UserPreferences userPreferences = helper.getUserPreferences(portletRequest);
		String codiceFiscale = userPreferences.getCodiceFiscaleServizio();
		log.debug("userCF=" + codiceFiscale);
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

				// Controllo scaricamento certificato
				if (componente.getDataMorte() != null) {
					Map<String, Boolean> conditions = configurazioneService.getCondizioniGenerazioneCertificati(componente, PortletConstants.CODICE_SERVIZIO_RICHIESTA_CERTIFICATO_MORTE,
							userPreferences.getIdComuneIsa());
					if (conditions != null && !conditions.get(DATA_NASCITA_CONDITION)) {
						String conditionValue = configurazioneService.getCondizioneGenerazioneCertficiati(DATA_NASCITA_CONDITION, PortletConstants.CODICE_SERVIZIO_RICHIESTA_CERTIFICATO_MORTE,
								userPreferences.getIdComuneIsa());
						if (conditionValue != null) {
							model.addAttribute(DATA_NASCITA_CONDITION, conditionValue.substring(0, 4));
						}
					}
				}

				break;
			}
		}

		return toLocalViewPath("home");
	}

	@ActionMapping(params = "cambio=Cambia soggetto")
	public void cambioSoggetto(@RequestParam("codFisc") String codFisc, @ModelAttribute("datiAnagrafici") DatiAnagrafici dati, Model model, ActionRequest request, ActionResponse response,
			PortletSession session) throws Exception {

		log.debug("cambioSoggetto cf= ..." + codFisc);
		UserPreferences userPreferences = helper.getUserPreferences(request);
		List<ComponentiNucleoFamiliare> componenti = dati.getComponentiNucleoFamiliare();
		for (int i = 0; i < componenti.size(); i++) {
			ComponentiNucleoFamiliare componente = componenti.get(i);
			if (codFisc != null && codFisc.equals(componente.getCodiceFiscale())) {
				anagrafeCommonService.addInfoAggiuntiveComponenteToModel(model, componente);
				model.addAttribute("componenteFamiglia", componente);

				// Controllo scaricamento certificato
				if (componente.getDataMorte() != null) {
					Map<String, Boolean> conditions = configurazioneService.getCondizioniGenerazioneCertificati(componente, PortletConstants.CODICE_SERVIZIO_RICHIESTA_CERTIFICATO_MORTE,
							userPreferences.getIdComuneIsa());
					if (conditions != null && !conditions.get(DATA_NASCITA_CONDITION)) {
						String conditionValue = configurazioneService.getCondizioneGenerazioneCertficiati(DATA_NASCITA_CONDITION, PortletConstants.CODICE_SERVIZIO_RICHIESTA_CERTIFICATO_MORTE,
								userPreferences.getIdComuneIsa());
						if (conditionValue != null) {
							model.addAttribute(DATA_NASCITA_CONDITION, conditionValue.substring(0, 4));
						}
					}
				}
				break;
			}
		}

		response.setRenderParameter("action", "renderDatiAnagrafici");
	}

	@ResourceMapping("certificatoMorteReport")
	public void serveCertificato(@RequestParam("codFisc") String codFisc, Model model, ResourceResponse response, PortletSession session, @ModelAttribute("datiAnagrafici") DatiAnagrafici dati,
			ResourceRequest request) throws Exception {

		log.debug("generaCertificatoMorte cf= ..." + codFisc);

		ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(session);

		UserPreferences userPreferences = helper.getUserPreferences(request);

		ComponentiNucleoFamiliare componenteSel = null;
		List<ComponentiNucleoFamiliare> componenti = dati.getComponentiNucleoFamiliare();
		for (int i = 0; i < componenti.size(); i++) {
			ComponentiNucleoFamiliare componente = componenti.get(i);
			if (codFisc != null && codFisc.equals(componente.getCodiceFiscale())) {
				componenteSel = componente;

				// Controllo scaricamento certificato
				if (componente.getDataMorte() != null) {
					Map<String, Boolean> conditions = configurazioneService.getCondizioniGenerazioneCertificati(componente, PortletConstants.CODICE_SERVIZIO_RICHIESTA_CERTIFICATO_MORTE,
							userPreferences.getIdComuneIsa());
					if (conditions != null && !conditions.get(DATA_NASCITA_CONDITION)) {
						log.info("serveCertificato :: dataNascitaCondition : impossibile generare il certificato per : " + codFisc);
						return;
					}
				}
				break;
			}
		}

		String osapulieHost = request.getServerName();
		// genero l'xml con i dati.
		String datiCert = templateCertificatiHelper.componiXml(dati, componenteSel, "", null, null, "certificatomorte", helper.getUserPreferences(request),
				PortletConstants.CODICE_SERVIZIO_RICHIESTA_CERTIFICATO_MORTE, osapulieHost);

		// invoco il il servizio di timbro digitale e gli passo l'xml
		byte[] certificatoTimbrato = timbroService.richiediCertificatoTimbrato(datiCert, helper.getUserPreferences(request));

		// memorizzo la richiesta

		Fascicolo fascicolo = new Fascicolo();
		fascicolo.setIdProfiloUtente(profiloUtente);
		fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio", null, request.getLocale()));
		fascicolo.setUserPreferences(helper.getUserPreferences(request));
		fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_RICHIESTA_CERTIFICATO_MORTE);
		fascicolo.setRicercabileDaComune(false);
		fascicolo.setNumeroProtocollo(null);
		fascicolo.setChecksum(null);
		fascicoloService.saveNuovaRichiesta(fascicolo);

		// consentire il download del documento.
		String reportFileName = String.format("certificatomorte.pdf");
		helper.sendStreamAsAttachment(certificatoTimbrato, response, reportFileName, ContentMimeTypes.PDF);
	}

	/**
	 * Presenta la visura a video.
	 *
	 * @param model
	 * @return l'ID della view per visualizzare i dati anagrafici
	 */
	@RenderMapping(params = "action=renderDatiAnagrafici")
	public String renderVisura(Model model) {
		log.debug("Model = " + model);
		return toLocalViewPath("home");
	}

	/**
	 * Utility method per calcolare e restituire il view ID all'interno della corretta alberatura di
	 * sottodirectory delle JSP: in un certo senso si tratta del namespace delle view locali a
	 * questo controller. Ad esempio: <code> toLocalViewPath( "home" ) </code> restituirà <code>
	 * "/richiestacertificatomorte/home" </code>
	 *
	 * @param viewName l'ID della view locale
	 * @return il view ID completo del path a cui la vista appartiene
	 */
	private static String toLocalViewPath(String viewName) {
		return "richiestacertificatomorte/" + viewName;
	}

	@ResourceMapping("reportPdf")
	public void reportPdf(@RequestParam("codFisc") String codFisc, Model model, ResourceResponse response, PortletSession session, @ModelAttribute("datiAnagrafici") DatiAnagrafici dati,
			ResourceRequest request) throws Exception {
		log.debug("certificatoMorteReportPdfURL cf= ..." + codFisc);

		ComponentiNucleoFamiliare componenteSel = null;
		for (int i = 0; i < dati.getComponentiNucleoFamiliare().size(); i++) {
			ComponentiNucleoFamiliare componente = dati.getComponentiNucleoFamiliare().get(i);
			if (codFisc != null && codFisc.equals(componente.getCodiceFiscale())) {
				componenteSel = componente;
				break;
			}
		}

		List<ComponentiNucleoFamiliare> beans = new ArrayList<ComponentiNucleoFamiliare>();
		beans.add(componenteSel);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("comune", helper.getUserPreferences(request).getNomeComune());

		// mellif 20150316: comune e provincia passati come parametri in seguito all'introduzione
		// del codiceIstat
		Comune comuneNascita = comuneService.getComuneByCodiceISTAT(componenteSel.getCodiceIstatComuneNascita());
		param.put("comuneNascita", comuneNascita.getDenominazione());
		param.put("provinciaNascita", comuneNascita.getProvincia().getDenominazioneProvincia());

		Comune comuneMorte = comuneService.getComuneByCodiceISTAT(componenteSel.getCodiceIstatComuneMorte());
		param.put("comuneMorte", comuneMorte.getDenominazione());
		param.put("provinciaMorte", comuneMorte.getProvincia().getDenominazioneProvincia());

		String report_path = "/reports/richiestaCertificatoMorte.jrxml";
		byte[] reportBytes = reportService.jrxmlToPdf(param, beans, helper.getUserPreferences(request).getIdComuneIsa(), PortletConstants.CODICE_SERVIZIO_RICHIESTA_CERTIFICATO_MORTE, report_path,
				null);

		// download del pdf
		String reportFileName = String.format("certificatomorte.pdf");
		helper.sendStreamAsAttachment(reportBytes, response, reportFileName, ContentMimeTypes.PDF);
	}
}
