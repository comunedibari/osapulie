
package it.osapulie.anagrafe.web.portlet.richiestacertificatoresidenzainconvivenza.controller;


import it.osapulie.anagrafe.service.AnagrafeCommonService;
import it.osapulie.anagrafe.service.TimbroDigitaleService;
import it.osapulie.anagrafe.service.VisureService;
import it.osapulie.anagrafe.web.portlet.util.PortletUtils;
import it.osapulie.anagrafe.web.portlet.util.impl.PortletConstants;
import it.osapulie.anagrafe.web.portlet.util.impl.TemplateCertificatiHelper;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici.ComponentiNucleoFamiliare;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagraficiGenerali;
import it.osapulie.anagrafe.web.ws.output.types.DatiUtente;
import it.osapulie.anagrafe.web.ws.output.types.RichiestaDatiAnagrafici;
import it.osapulie.domain.Comune;
import it.osapulie.domain.ComuneEstero;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.dto.Fascicolo;
import it.osapulie.infrastructure.ContentMimeTypes;
import it.osapulie.infrastructure.ReportService;
import it.osapulie.service.ComuneEsteroService;
import it.osapulie.service.ComuneService;
import it.osapulie.service.FascicoloUtenteService;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.web.portlet.controller.BaseController;
import it.osapulie.web.portlet.util.impl.LiferayPortletHelperImpl;
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

import javax.inject.Inject;
import javax.portlet.*;
import java.util.*;


/**
 * Portlet controller per il servizio richiesta Certificato di Residenza.
 *
 * @author Birtolo Maria Michela
 */
@Controller("richiestaCertificatoResidenzaInConvivenzaPortletController")
@RequestMapping("view")
@SessionAttributes("datiAnagrafici")
public class RichiestaCertificatoResidenzaInConvivenzaPortletController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(RichiestaCertificatoResidenzaInConvivenzaPortletController.class);

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

	/**
	 * Default entry quando la portlet viene visualizzata per la prima volta.
	 *
	 * @param model il view model da popolare
	 * @return il view ID da renderizzare.
	 */
	@RenderMapping
	public String homePage(Model model, PortletSession session, PortletRequest portletRequest) throws Exception {
		log.debug("homePage-RichiestaCertificatoResidenzaInConvivenzaPortletController");
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

		return toLocalViewPath("home");
	}

	@ActionMapping(params = "cambio=Cambia soggetto")
	public void cambioSoggetto(@RequestParam("codFisc") String codFisc, Model model, ActionResponse response, PortletSession session, @ModelAttribute("datiAnagrafici") DatiAnagrafici dati)
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

		response.setRenderParameter("action", "renderDatiAnagrafici");
	}
	@ResourceMapping("certificatoResidenzaInConvivenzaReport")
	public void serveCertificato(@RequestParam("codFisc") String codFisc, Model model, ResourceResponse response, ResourceRequest request, PortletSession session,
								 @ModelAttribute("datiAnagrafici") DatiAnagrafici dati) throws Exception {
		ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(session);
		if(codFisc.equalsIgnoreCase("MLDGPP76M12L425M") || profiloUtente.getComuneIsa().getCodiceIstat().equalsIgnoreCase("072038")){
			this.serveCertificatoLand(codFisc,model,response,request,session,dati);
		} else {
			this.serveCertificatoLand(codFisc,model,response,request,session,dati);
		}
	}

/*	@ResourceMapping("certificatoResidenzaInConvivenzaReport")
	public void serveCertificato(@RequestParam("codFisc") String codFisc, Model model, ResourceResponse response, ResourceRequest request, PortletSession session,
			@ModelAttribute("datiAnagrafici") DatiAnagrafici dati) throws Exception {*/
	public void serveCertificatoLand(@RequestParam("codFisc") String codFisc, Model model, ResourceResponse response, ResourceRequest request, PortletSession session,
							 @ModelAttribute("datiAnagrafici") DatiAnagrafici dati) throws Exception {
		log.debug("generaCertificatoResidenza cf= ..." + codFisc);
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
		DatiAnagraficiGenerali richDatiAna = new DatiAnagraficiGenerali();
		richDatiAna.setCodiceFiscale(codFisc);
		DatiUtente datiUte = visureService.richiediDatiAnagraficiAltriServizi(richDatiAna, helper.getUserPreferences(request));

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

		// genero l'xml con i dati.
		String datiCert = templateCertificatiHelper.componiXml(dati, componenteSel, utilizzo, numeroBollo, datiUte, "certificatoresidenza", helper.getUserPreferences(request),
				PortletConstants.SERVIZIO_RICHIESTA_CERTIFICATO_RESIDENZA.get(PortletConstants.CODICE), osapulieHost);

		// invoco il il servizio di timbro digitale e gli passo l'xml
		byte[] certificatoTimbrato = timbroService.richiediCertificatoTimbrato(datiCert, helper.getUserPreferences(request));

		// memorizzo la richiesta
		Fascicolo fascicolo = new Fascicolo();
		fascicolo.setIdProfiloUtente(profiloUtente);
		fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio", null, request.getLocale()));
		fascicolo.setUserPreferences(helper.getUserPreferences(request));
		fascicolo.setCodiceServizio(PortletConstants.SERVIZIO_RICHIESTA_CERTIFICATO_RESIDENZA.get(PortletConstants.CODICE));
		fascicolo.setRicercabileDaComune(false);
		fascicolo.setNumeroProtocollo(null);
		fascicolo.setChecksum(null);
		fascicoloService.saveNuovaRichiesta(fascicolo);

		// consentire il download del documento.
		String reportFileName = String.format("certificatoresidenza.pdf");
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
	 * "/richiestacertificatoresidenzainconvivenza/home" </code>
	 *
	 * @param viewName l'ID della view locale
	 * @return il view ID completo del path a cui la vista appartiene
	 */
	private static String toLocalViewPath(String viewName) {
		return "richiestacertificatoresidenzainconvivenza/" + viewName;
	}

	@ResourceMapping("reportPdf")
	public void reportPdf(@RequestParam("codFisc") String codFisc, Model model, ResourceRequest request, ResourceResponse response, PortletSession session,
			@ModelAttribute("datiAnagrafici") DatiAnagrafici dati) throws Exception {
		log.debug("certificatoResidenzaInConvivenzaReportPdfURL cf= ..." + codFisc);

		List<String> listaUsi = getUsiList();
		String usoCertificatoId = request.getParameter("uso");

		ComponentiNucleoFamiliare componenteSel = null;
		for (int i = 0; i < dati.getComponentiNucleoFamiliare().size(); i++) {
			ComponentiNucleoFamiliare componente = dati.getComponentiNucleoFamiliare().get(i);
			if (codFisc != null && codFisc.equals(componente.getCodiceFiscale())) {
				componenteSel = componente;
				break;
			}
		}

		/*
		 * L'oggetto datiUte serve a recuperare le informazioni riguardanti via, comune, cap e
		 * provincia di residenza
		 */
		DatiAnagraficiGenerali richDatiAna = new DatiAnagraficiGenerali();
		richDatiAna.setCodiceFiscale(codFisc);
		DatiUtente datiUte = visureService.richiediDatiAnagraficiAltriServizi(richDatiAna, helper.getUserPreferences(request));

		String via = (dati.getToponimoIndirizzo() != null && !dati.getToponimoIndirizzo().equals("") ? " " + dati.getToponimoIndirizzo() : "") + " " + dati.getDescrizioneVia() + " n."
				+ dati.getNumeroCivico() + (dati.getEsponente() != null && !dati.getEsponente().equals("") ? " " + dati.getEsponente() : "")
				+ (dati.getScala() != null && !dati.getScala().equals("") ? " scala " + dati.getScala() : "")
				+ (dati.getPiano() != null && !dati.getPiano().equals("") ? " piano " + dati.getPiano() : "");

		List<ComponentiNucleoFamiliare> beans = new ArrayList<ComponentiNucleoFamiliare>();
		beans.add(componenteSel);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("comune", helper.getUserPreferences(request).getNomeComune());
		param.put("viaRes", via);

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

		if (datiUte != null) {
			param.put("capRes", datiUte.getCap());
			param.put("provRes", datiUte.getProvinciaResidenza());
			param.put("comuneRes", datiUte.getComuneResidenza());
		}

		if (listaUsi != null) {
			// Setto la motivazione di utilizzo del certificato
			param.put("uso", listaUsi.get(Integer.parseInt(usoCertificatoId)));
		}

		String report_path = "/reports/richiestaCertificatoResidenza.jrxml";
		byte[] reportBytes = reportService.jrxmlToPdf(param, beans, helper.getUserPreferences(request).getIdComuneIsa(), PortletConstants.SERVIZIO_RICHIESTA_CERTIFICATO_RESIDENZA.get(PortletConstants.CODICE), report_path,
				null);

		// download del pdf
		String reportFileName = String.format("certificatoresidenza.pdf");
		helper.sendStreamAsAttachment(reportBytes, response, reportFileName, ContentMimeTypes.PDF);
	}

	/*
	 * METODO DI TEST PER DEMO 26/01/2015
	 */

	@ResourceMapping("reportPdfDEMO")
	public void reportPdfDEMO(@RequestParam("codFisc") String codFisc, Model model, ResourceRequest request, ResourceResponse response, PortletSession session,
			@ModelAttribute("datiAnagrafici") DatiAnagrafici dati) throws Exception {
		log.debug("certificatoResidenzaInConvivenzaReportPdfDEMOURL cf= ..." + codFisc);

		List<String> listaUsi = getUsiList();
		String usoCertificatoId = request.getParameter("uso");

		ComponentiNucleoFamiliare componenteSel = null;
		for (int i = 0; i < dati.getComponentiNucleoFamiliare().size(); i++) {
			ComponentiNucleoFamiliare componente = dati.getComponentiNucleoFamiliare().get(i);
			if (codFisc != null && codFisc.equals(componente.getCodiceFiscale())) {
				componenteSel = componente;
				break;
			}
		}

		/*
		 * L'oggetto datiUte serve a recuperare le informazioni riguardanti via, comune, cap e
		 * provincia di residenza
		 */
		DatiAnagraficiGenerali richDatiAna = new DatiAnagraficiGenerali();
		richDatiAna.setCodiceFiscale(codFisc);
		DatiUtente datiUte = visureService.richiediDatiAnagraficiAltriServizi(richDatiAna, helper.getUserPreferences(request));

		String via = dati.getToponimoIndirizzo() + " " + dati.getDescrizioneVia() + " n." + dati.getNumeroCivico()
				+ (dati.getEsponente() != null && !dati.getEsponente().equals("") ? " " + dati.getEsponente() : "")
				+ (dati.getScala() != null && !dati.getScala().equals("") ? " scala " + dati.getScala() : "")
				+ (dati.getPiano() != null && !dati.getPiano().equals("") ? " piano " + dati.getPiano() : "");

		List<ComponentiNucleoFamiliare> beans = new ArrayList<ComponentiNucleoFamiliare>();
		beans.add(componenteSel);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("comune", helper.getUserPreferences(request).getNomeComune());
		param.put("viaRes", via);

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

		if (datiUte != null) {
			param.put("capRes", datiUte.getCap());
			param.put("provRes", datiUte.getProvinciaResidenza());
			param.put("comuneRes", datiUte.getComuneResidenza());
		}

		if (listaUsi != null) {
			// Setto la motivazione di utilizzo del certificato
			param.put("uso", listaUsi.get(Integer.parseInt(usoCertificatoId)));
		}

		String report_path = "/reports/demo/richiestaCertificatoResidenza.jrxml";
		byte[] reportBytes = reportService.jrxmlToPdf(param, beans, helper.getUserPreferences(request).getIdComuneIsa(), PortletConstants.SERVIZIO_RICHIESTA_CERTIFICATO_RESIDENZA.get(PortletConstants.CODICE), report_path,
				null);

		// download del pdf
		String reportFileName = String.format("certificatoresidenza.pdf");
		helper.sendStreamAsAttachment(reportBytes, response, reportFileName, ContentMimeTypes.PDF);
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
