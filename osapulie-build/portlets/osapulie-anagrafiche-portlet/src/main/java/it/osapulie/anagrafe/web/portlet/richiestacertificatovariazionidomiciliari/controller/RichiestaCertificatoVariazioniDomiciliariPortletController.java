package it.osapulie.anagrafe.web.portlet.richiestacertificatovariazionidomiciliari.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
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

import com.liferay.portal.kernel.util.PrefsPropsUtil;

import it.osapulie.anagrafe.service.AnagrafeCommonService;
import it.osapulie.anagrafe.service.TimbroDigitaleService;
import it.osapulie.anagrafe.service.VisureService;
import it.osapulie.anagrafe.web.portlet.util.impl.PortletConstants;
import it.osapulie.anagrafe.web.portlet.util.impl.TemplateCertificatiHelper;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici.ComponentiNucleoFamiliare;
import it.osapulie.anagrafe.web.ws.output.types.DatiIndirizzo;
import it.osapulie.anagrafe.web.ws.output.types.DatiIndirizzo.VariazioniDomicilio;
import it.osapulie.anagrafe.web.ws.output.types.RichiestaDatiAnagrafici;
import it.osapulie.anagrafe.web.ws.output.types.RichiestaVariazioniDomiciliari;
import it.osapulie.domain.Comune;
import it.osapulie.domain.ComuneEstero;
import it.osapulie.domain.Delega;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.dto.Fascicolo;
import it.osapulie.infrastructure.ContentMimeTypes;
import it.osapulie.infrastructure.ReportService;
import it.osapulie.service.ComuneEsteroService;
import it.osapulie.service.ComuneService;
import it.osapulie.service.DelegaService;
import it.osapulie.service.FascicoloUtenteService;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.web.portlet.controller.BaseController;
import it.osapulie.web.portlet.util.impl.LiferayPortletHelperImpl;

/**
 * Portlet controller per il servizio richiesta Certificato Variazioni Domiciliari.
 *
 * @author Birtolo Maria Michela
 */
@Controller("richiestaCertificatoVariazioniDomiciliariPortletController")
@RequestMapping("view")
@SessionAttributes("datiAnagrafici")
public class RichiestaCertificatoVariazioniDomiciliariPortletController extends BaseController {

	private static final String REPORT_PATH = "/reports/richiestaCertificatoVariazioniDomiciliari.jrxml";
	private static final String SUB_REPORT_PATH = "richiestaCertificatoVariazioniDomiciliari_subreport1.jrxml";
	private static final String REPORT_NAME = "certificatovariazionedomiciliari.pdf";

	private final Logger log = LoggerFactory.getLogger(RichiestaCertificatoVariazioniDomiciliariPortletController.class);

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
	private ComuneService comuneService;

	@Inject
	private ComuneEsteroService comuneEsteroService;
	
	@Inject
	private DelegaService delegaService;

	/**
	 * Default entry quando la portlet viene visualizzata per la prima volta.
	 *
	 * @param model il view model da popolare
	 * @return il view ID da renderizzare.
	 */
	@RenderMapping
	public String homePage(Model model, PortletSession session, PortletRequest portletRequest) throws Exception {
		log.debug("homePage-richiestaCertificatoVariazioniDomiciliariPortletController");
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
			if (codiceFiscale != null && codiceFiscale.toLowerCase().equals(componente.getCodiceFiscale().toLowerCase())) {
				anagrafeCommonService.addInfoAggiuntiveComponenteToModel(model, componente);
				model.addAttribute("componenteFamiglia", componente);
				break;
			}
		}

		try {
			model.addAttribute("wacomSignatureSdk", PrefsPropsUtil.getString("wacom.signature.sdk"));
		} catch (Exception e) {
			log.error("renderEditForm :: " + e.getMessage(), e);
		}
		
		if(userPreferences.getIdDelega() != null) {
			Delega delega =delegaService.getDelegaByPk(userPreferences.getIdDelega());
			model.addAttribute("firma",delega.getFirmaGrafometrica());
		}
		else
			model.addAttribute("firma",false);
		
		return toLocalViewPath("home");
	}

	@ActionMapping(params = "cambio=Cambia soggetto")
	public void cambioSoggetto(@RequestParam("codFisc") String codFisc, Model model, ActionResponse response, PortletSession session,PortletRequest portletRequest, @ModelAttribute("datiAnagrafici") DatiAnagrafici dati)
			throws Exception {
		log.debug("cambioSoggetto cf= ..." + codFisc);

		List<ComponentiNucleoFamiliare> componenti = dati.getComponentiNucleoFamiliare();
		for (int i = 0; i < componenti.size(); i++) {
			ComponentiNucleoFamiliare componente = componenti.get(i);
			if (codFisc != null && codFisc.toLowerCase().equals(componente.getCodiceFiscale().toLowerCase())) {
				anagrafeCommonService.addInfoAggiuntiveComponenteToModel(model, componente);
				model.addAttribute("componenteFamiglia", componente);
				break;
			}
		}
		
		UserPreferences userPreferences = helper.getUserPreferences(portletRequest);
		
		if(userPreferences.getIdDelega() != null) {
			Delega delega =delegaService.getDelegaByPk(userPreferences.getIdDelega());
			model.addAttribute("firma",delega.getFirmaGrafometrica());
		}
		else
			model.addAttribute("firma",false);

		try {
			model.addAttribute("wacomSignatureSdk", PrefsPropsUtil.getString("wacom.signature.sdk"));
		} catch (Exception e) {
			log.error("renderEditForm :: " + e.getMessage(), e);
		}


		response.setRenderParameter("action", "renderDatiAnagrafici");
	}

	@ResourceMapping("certificatoVariazioniDomiciliariReport")
	public void serveCertificato(@RequestParam("codFisc") String codFisc, Model model, ResourceResponse response, PortletSession session, PortletRequest portletRequest, @ModelAttribute("datiAnagrafici") DatiAnagrafici dati,
			ResourceRequest request) throws Exception {
		log.debug("generaCertificatoVariazioniDomiciliari cf= ..." + codFisc);
		ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(session);

		ComponentiNucleoFamiliare componenteSel = null;
		List<ComponentiNucleoFamiliare> componenti = dati.getComponentiNucleoFamiliare();
		for (int i = 0; i < componenti.size(); i++) {
			ComponentiNucleoFamiliare componente = componenti.get(i);
			if (codFisc != null && codFisc.toLowerCase().equals(componente.getCodiceFiscale().toLowerCase())) {
				componenteSel = componente;
				break;
			}
		}
		
		UserPreferences userPreferences = helper.getUserPreferences(portletRequest);
		
		if(userPreferences.getIdDelega() != null) {
			Delega delega =delegaService.getDelegaByPk(userPreferences.getIdDelega());
			model.addAttribute("firma",delega.getFirmaGrafometrica());
		}
		else
			model.addAttribute("firma",false);

		String osapulieHost = request.getServerName();

		// genero l'xml con i dati.
		String datiCert = templateCertificatiHelper.componiXml(dati, componenteSel, "", null, null, "certificatovariazionidomiciliari", helper.getUserPreferences(request),
				PortletConstants.CODICE_SERVIZIO_RICHIESTA_CERTIFICATO_VARIAZIONI_DOMICILIARI, osapulieHost);

		// recupero i dati sulle variazioni domiciliari
		RichiestaVariazioniDomiciliari rich = new RichiestaVariazioniDomiciliari();
		rich.setCodiceFiscale(codFisc);
		rich.setDataFine(Calendar.getInstance());
		Calendar cal = Calendar.getInstance();
		cal.set(1900, 1, 1);
		rich.setDataPartenza(cal);
		DatiIndirizzo datiInd = visureService.richiediDatiVariazioniDomiciliari(rich, helper.getUserPreferences(request));

		datiCert = templateCertificatiHelper.componiXmlDatiVariazioniDomiciliari(datiInd, datiCert);

		// invoco il il servizio di timbro digitale e gli passo l'xml
		byte[] certificatoTimbrato = timbroService.richiediCertificatoTimbrato(datiCert, helper.getUserPreferences(request));
		// memorizzo la richiesta

		Fascicolo fascicolo = new Fascicolo();
		fascicolo.setIdProfiloUtente(profiloUtente);
		fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio", null, request.getLocale()));
		fascicolo.setUserPreferences(helper.getUserPreferences(request));
		fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_RICHIESTA_CERTIFICATO_VARIAZIONI_DOMICILIARI);
		fascicolo.setRicercabileDaComune(false);
		fascicolo.setNumeroProtocollo(null);
		fascicolo.setChecksum(null);

		fascicoloService.saveNuovaRichiesta(fascicolo);
		// consentire il download del documento.
		String reportFileName = String.format("certificatoVariazioniDomiciliari.pdf");
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
	 * "/richiestacertificatovariazionidomiciliari/home" </code>
	 *
	 * @param viewName l'ID della view locale
	 * @return il view ID completo del path a cui la vista appartiene
	 */
	private static String toLocalViewPath(String viewName) {
		return "richiestacertificatovariazionidomiciliari/" + viewName;
	}

	@ResourceMapping("reportPdf")
	public void reportPdf(@RequestParam("codFisc") String codFisc, Model model, ResourceResponse response, PortletSession session, @ModelAttribute("datiAnagrafici") DatiAnagrafici dati,
			ResourceRequest request) throws Exception {
		log.debug("certificatoVariazioniDomiciliariReportPdfURL cf= ..." + codFisc);

		ComponentiNucleoFamiliare componenteSel = null;
		for (int i = 0; i < dati.getComponentiNucleoFamiliare().size(); i++) {
			ComponentiNucleoFamiliare componente = dati.getComponentiNucleoFamiliare().get(i);
			if (codFisc != null && codFisc.toLowerCase().equals(componente.getCodiceFiscale().toLowerCase())) {
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
		Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(componenteSel.getCodiceIstatComuneNascita());
		if (comuneByCodiceISTAT != null) {
			param.put("comuneNascita", comuneByCodiceISTAT.getDenominazione());
			param.put("provinciaNascita", comuneByCodiceISTAT.getProvincia().getDenominazioneProvincia());
		}
		// Ricerca in comuni esteri
		else {
			if (!componenteSel.getCodiceIstatComuneNascita().isEmpty()) {
				ComuneEstero comuneEsteroByCodice = comuneEsteroService.getComuneEsteroByCodice(Integer.parseInt(componenteSel.getCodiceIstatComuneNascita()));
				if (comuneEsteroByCodice != null) {
					param.put("comuneNascita", comuneEsteroByCodice.getDenominazione());
				}
			}
		}

		// recupero i dati sulle variazioni domiciliari
		RichiestaVariazioniDomiciliari rich = new RichiestaVariazioniDomiciliari();
		rich.setCodiceFiscale(codFisc);
		rich.setDataFine(Calendar.getInstance());
		Calendar cal = Calendar.getInstance();
		cal.set(1900, 1, 1);
		rich.setDataPartenza(cal);
		DatiIndirizzo datiInd = visureService.richiediDatiVariazioniDomiciliari(rich, helper.getUserPreferences(request));

		List<VariazioniDomicilio> lista = new ArrayList<VariazioniDomicilio>();
		List<VariazioniDomicilio> ele = datiInd.getVariazioniDomicilio();
		for (int i = 0; i < ele.size(); i++) {
			VariazioniDomicilio dom = ele.get(i);
			lista.add(dom);
		}
		param.put("subreportParameters", lista);

		Map<String, String> subreportJrxmlMap = new HashMap<String, String>();
		subreportJrxmlMap.put("subreportParameter", SUB_REPORT_PATH);

		byte[] reportBytes = reportService.jrxmlToPdf(param, beans, helper.getUserPreferences(request).getIdComuneIsa(), PortletConstants.CODICE_SERVIZIO_RICHIESTA_CERTIFICATO_VARIAZIONI_DOMICILIARI,
				REPORT_PATH, subreportJrxmlMap);

		// download del pdf
		String reportFileName = String.format(REPORT_NAME);
		helper.sendStreamAsAttachment(reportBytes, response, reportFileName, ContentMimeTypes.PDF);
	}

}
