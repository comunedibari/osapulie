package it.osapulie.anagrafe.web.portlet.richiestacertificatocittadinanza.controller;

import java.util.ArrayList;
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
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.util.PrefsPropsUtil;

import it.osapulie.anagrafe.service.AnagrafeCommonService;
import it.osapulie.anagrafe.service.TimbroDigitaleService;
import it.osapulie.anagrafe.service.VisureService;
import it.osapulie.anagrafe.web.portlet.util.PortletUtils;
import it.osapulie.anagrafe.web.portlet.util.impl.PortletConstants;
import it.osapulie.anagrafe.web.portlet.util.impl.TemplateCertificatiHelper;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici.ComponentiNucleoFamiliare;
import it.osapulie.anagrafe.web.ws.output.types.RichiestaDatiAnagrafici;
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
 * Portlet controller per il servizio richiesta Certificato Cittadinanza.
 *
 * @author Birtolo Maria MIchela
 */
@Controller("richiestaCertificatoCittadinanzaPortletController")
@RequestMapping("view")
@SessionAttributes("datiAnagrafici")
public class RichiestaCertificatoCittadinanzaPortletController extends BaseController {

	private static Logger log = LoggerFactory.getLogger(RichiestaCertificatoCittadinanzaPortletController.class);

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
		log.debug("homePage-richiestaCertificatoCittadinanzaPortletController");
		UserPreferences userPreferences = helper.getUserPreferences(portletRequest);
		String codiceFiscale = userPreferences.getCodiceFiscaleServizio();
		log.debug("userCF=" + codiceFiscale);
		RichiestaDatiAnagrafici richiesta = new RichiestaDatiAnagrafici();
		richiesta.setCodiceFiscale(codiceFiscale);

		DatiAnagrafici dati = visureService.richiediDatiAnagrafici(richiesta, helper.getUserPreferences(portletRequest));

		model.addAttribute("datiAnagrafici", dati);
		model.addAttribute("listaUsi", getUsiList());

		List<ComponentiNucleoFamiliare> componenti = dati.getComponentiNucleoFamiliare();
		for (int i = 0; i < componenti.size(); i++) {
			ComponentiNucleoFamiliare componente = componenti.get(i);
			if (codiceFiscale != null && codiceFiscale.equals(componente.getCodiceFiscale())) {
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

	@RequestMapping(params = "cambio=Cambia soggetto")
	public void cambioSoggetto(@RequestParam("codFisc") String codFisc, Model model, ActionResponse response, PortletSession session, PortletRequest portletRequest, @ModelAttribute("datiAnagrafici") DatiAnagrafici dati)
			throws Exception {
		log.debug("cambioSoggetto cf= ..." + codFisc);

		List<ComponentiNucleoFamiliare> componenti = dati.getComponentiNucleoFamiliare();
		for (int i = 0; i < componenti.size(); i++) {
			ComponentiNucleoFamiliare componente = componenti.get(i);
			if (codFisc != null && codFisc.equals(componente.getCodiceFiscale())) {
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

	@ResourceMapping("certificatoCittadinanzaReport")
	public void serveCertificato(@RequestParam("codFisc") String codFisc, Model model, ResourceRequest request, ResourceResponse response, PortletSession session,
			@ModelAttribute("datiAnagrafici") DatiAnagrafici dati, PortletRequest portletRequest) throws Exception {
		log.debug("generaCertificatoCitt cf= ..." + codFisc);
		ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(session);

		ComponentiNucleoFamiliare componenteSel = null;
		List<ComponentiNucleoFamiliare> componenti = dati.getComponentiNucleoFamiliare();
		for (int i = 0; i < componenti.size(); i++) {
			ComponentiNucleoFamiliare componente = componenti.get(i);
			if (codFisc != null && codFisc.equals(componente.getCodiceFiscale())) {
				componenteSel = componente;
				break;
			}
		}
		// Recupero la modalita di utilizzo del certificato
		String usoCertificatoId = request.getParameter("uso");
		int uso = Integer.parseInt(usoCertificatoId);
		String utilizzo = getUsiList().get(uso);

		// Recupero informazioni sul numero di marca da bollo
		String numeroBollo = request.getParameter("bollo");

		// Controllo che il campo numero_bollo sia valorizzato se è selezionato il primo
		// valore della selectbox
		if (!PortletUtils.checkBolloCertificato(request)) {
			throw new Exception("Impossibile procedere con la generazione del certificato");
		}

		String osapulieHost = request.getServerName();
		// genero l'xml con i dati su residenza, cittadinanza e stato libero.
		String datiCert = templateCertificatiHelper.componiXml(dati, componenteSel, utilizzo, numeroBollo, null, "certificatocittadinanza", helper.getUserPreferences(portletRequest),
				PortletConstants.CODICE_SERVIZIO_RICHIESTA_CERTIFICATO_CITTADINANZA, osapulieHost);
		UserPreferences userPreferences = helper.getUserPreferences(portletRequest);

		// invoco il il servizio di timbro digitale e gli passo l'xml
		byte[] certificatoTimbrato = timbroService.richiediCertificatoTimbrato(datiCert, userPreferences);

		// memorizzo la richiesta
		Fascicolo fascicolo = new Fascicolo();
		fascicolo.setIdProfiloUtente(profiloUtente);
		fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio", null, portletRequest.getLocale()));
		fascicolo.setUserPreferences(userPreferences);
		fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_RICHIESTA_CERTIFICATO_CITTADINANZA);
		fascicolo.setRicercabileDaComune(false);
		fascicolo.setNumeroProtocollo(null);
		fascicolo.setChecksum(null);
		fascicoloService.saveNuovaRichiesta(fascicolo);

		// consentire il download del documento.
		String reportFileName = String.format("certificatocittadinanza.pdf");
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
		model.addAttribute("listaUsi", getUsiList());
		return toLocalViewPath("home");
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
		return "richiestacertificatocittadinanza/" + viewName;
	}

	@ResourceMapping("reportPdf")
	public void reportPdf(@RequestParam("codFisc") String codFisc, Model model, ResourceRequest request, ResourceResponse response, PortletSession session,
			@ModelAttribute("datiAnagrafici") DatiAnagrafici dati, PortletRequest portletRequest) throws Exception {

		log.debug("certificatoCittadinanzaReportPdfURL cf= ..." + codFisc);

		ComponentiNucleoFamiliare componenteSel = null;
		for (int i = 0; i < dati.getComponentiNucleoFamiliare().size(); i++) {
			ComponentiNucleoFamiliare componente = dati.getComponentiNucleoFamiliare().get(i);
			if (codFisc != null && codFisc.equals(componente.getCodiceFiscale())) {
				anagrafeCommonService.addInfoAggiuntiveComponenteToModel(model, componente);
				componenteSel = componente;
				break;
			}
		}
		String usoCertificatoId = request.getParameter("uso");
		UserPreferences userPreferences = helper.getUserPreferences(portletRequest);

		byte[] reportBytes = getPdfBytes(componenteSel, usoCertificatoId, userPreferences);

		// download del pdf
		String reportFileName = String.format("certificittadinanza.pdf");
		helper.sendStreamAsAttachment(reportBytes, response, reportFileName, ContentMimeTypes.PDF);

	}

	/**
	 * @param componenteSel
	 * @param usoCertificatoId
	 * @param userPreferences
	 * @return
	 */
	private byte[] getPdfBytes(ComponentiNucleoFamiliare componenteSel, String usoCertificatoId, UserPreferences userPreferences) {
		List<String> listaUsi = getUsiList();

		List<ComponentiNucleoFamiliare> beans = new ArrayList<ComponentiNucleoFamiliare>();
		beans.add(componenteSel);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("comune", userPreferences.getNomeComune());

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
		if (listaUsi != null) {
			// Setto la motivazione di utilizzo del certificato
			param.put("uso", listaUsi.get(Integer.parseInt(usoCertificatoId)));
		}

		String report_path = "/reports/richiestaCertificatoCittadinanza.jrxml";
		byte[] reportBytes = reportService.jrxmlToPdf(param, beans, userPreferences.getIdComuneIsa(), PortletConstants.CODICE_SERVIZIO_RICHIESTA_CERTIFICATO_CITTADINANZA, report_path, null);
		return reportBytes;
	}

	private List<String> getUsiList() {
		List<String> lista = new ArrayList<String>();
		lista.add("Uso bollo (bollo da 16,00 euro da acquistare e applicare a cura del richiedente)");
		lista.add("Uso abbonamenti per trasporto di persone (articolo 24 DPR 642/72)");
		lista.add("Uso adozione e affido minore (articolo 13 DPR 642/72)");
		lista.add("Uso aiuti settore agricolo (articolo 21bis DPR 642/72 - articolo 16 Legge 153/75)");
		lista.add("Uso ammissione soci in cooperativa edilizia (legge 427/93)");
		lista.add("Uso anticipazione indennità fine rapporto (articolo 9 DPR 642/72)");
		lista.add("Uso applicazione leggi tributarie (articolo 5 DPR 642/72)");
		lista.add("Uso assegni familiari (articolo 9 - articolo 12 DPR 642/72)");
		lista.add("Uso assicurazioni sociali obbligatorie (articolo 9 DPR 642/72)");
		lista.add("Uso assistenza sanitaria (articolo 10 DPR 642/72)");
		lista.add("Uso atti, documenti e provvedimenti in materia penale (articolo 3 DPR 642/72)");
		lista.add("Uso atti e provvedimenti del procedimento innanzi alla corte costituzionale");
		lista.add("Uso attività sportive (articolo 8 bis DPR 642/72)");
		lista.add("Uso attribuzione, rettifica o modifica del codice fiscale o partita iva");
		lista.add("Uso borsa di studio (articolo 11 DPR 642/72)");
		lista.add("Uso calcolo detrazioni IRPEF (articolo 5 DPR 642/72)");
		lista.add("Uso cause scioglimento matrimonio (articolo 19 Legge 74/87)");
		lista.add("Uso controversie in materia di lavoro (articolo 10 DPR 642/72)");
		lista.add("Uso domande di sussidio (articolo 8 DPR 642/72)");
		lista.add("Uso erogazione prestiti INDAP (articolo 9 DPR 642/72)");
		lista.add("Uso esonero o frequenza insegnamento religioso (articolo 11 DPR 642/72)");
		lista.add("Uso esonero tasse scolastiche (articolo 11 DPR 642/72)");
		lista.add("Uso espatrio minore (articolo 18 DPR 642/72)");
		lista.add("Uso espropriazione (articolo 22 DPR 642/72)");
		lista.add("Uso istruzione di secondo grado (articolo 9 DPR 642/72)");
		lista.add("Uso legalizzazione di fotografia (articolo 34 comma 2 DPR 445/2000)");
		lista.add("Uso organismi utilità sociale (ONLUS) (articolo 27 bis DPR 642/72)");
		lista.add("Uso pensione (articolo 12 DPR 642/72)");
		lista.add("Uso ricongiunzione carriera ai fini contributivi (articolo 9 DPR 642/72)");
		lista.add("Uso ricorso avverso il diniego del ricongiungimento familiare dall'estero");
		lista.add("Uso successione (articolo 5 DPR 642/72)");
		lista.add("Uso tutela minori e interdetti (articolo 13 DPR 642/72)");
		lista.add("Uso volontariato (Legge 266/91)");
		return lista;

	}
}
