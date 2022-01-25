package it.osapulie.pagamenti.web.portlet.pagamentotosapcosap.temp;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
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

import com.liferay.portal.kernel.portlet.LiferayPortletMode;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletURLFactoryUtil;

import it.linksmt.pagamenti.adapter.domain.client.PaymentRequest;
import it.linksmt.pagamenti.adapter.domain.client.PaymentResponse;
import it.linksmt.pagamenti.adapter.ws.client.PaymentClient;
import it.linksmt.pagamenti.adapter.ws.client.impl.PaymentClientImpl;
import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.dto.Fascicolo;
import it.osapulie.pagamenti.service.PosizioneTributariaService;
import it.osapulie.pagamenti.utils.PortletConstants;
import it.osapulie.pagamenti.web.portlet.common.PagamentiCommonController;
import it.osapulie.service.ComuneISAService;
import it.osapulie.service.FascicoloUtenteService;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.tributi.web.ws.output.types.DatiOsapTemporanea;
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaRichiesta;
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaRichiesta.ArcoTemporale;
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaRichiesta.TipoTributo;
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaRichiesta.TipoTributo.Tributo;
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaRisposta;
import it.osapulie.web.portlet.util.PortletHelper;

/**
 * Portlet controller per le visure Tosap-Cosap di tipo temporanea.
 *
 * @author Giovanni Barone
 */
@Controller("pagamentoOsapTemporaneaController")
@RequestMapping("view")
@SessionAttributes("dati")
public class PagamentoOsapTemporaneaController extends PagamentiCommonController {

	private final Logger log = LoggerFactory.getLogger(PagamentoOsapTemporaneaController.class);

	@Inject
	private PosizioneTributariaService service;

	@Inject
	private PortletHelper helper;

	@Inject
	private FascicoloUtenteService fascicoloService;

	@Autowired
	public ComuneISAService comuneISAService;

	@Autowired
	public ResourceBundleMessageSource messageSource;

	@Resource(name = "applicationProperties")
	private Properties applicationProperties;

	/**
	 * Default entry quando la portlet viene visualizzata per la prima volta.
	 *
	 * @param model il view model da popolare
	 * @return il view ID da renderizzare.
	 */
	@RenderMapping
	public String homePage(Model model, PortletRequest portletRequest) {
		log.info("Home Pagamento Osap Temporanea.");
		try {
			UserPreferences userPreferences = helper.getUserPreferences(portletRequest);

			VisuraPosizioneTributariaRisposta dati = getVisuraPosizioneTributaria(portletRequest, userPreferences);
			if (dati.getElencoPagamentiOsapTemporanea() != null && !dati.getElencoPagamentiOsapTemporanea().isEmpty()) {
				model.addAttribute("dati", dati);
			}
		}
		catch (Exception e) {
			log.error("Render home :: homePage : " + e.getMessage(), e);
		}

		return toLocalViewPath("pagamentoOsapTemporanea");
	}

	/**
	 * @param portletRequest
	 * @param userPreferences
	 * @param codiceFiscale
	 * @return
	 */
	private VisuraPosizioneTributariaRisposta getVisuraPosizioneTributaria(PortletRequest portletRequest, UserPreferences userPreferences) {

		VisuraPosizioneTributariaRichiesta richiesta = new VisuraPosizioneTributariaRichiesta();
		ArcoTemporale arcoTemporale = new ArcoTemporale();
		Calendar instance = Calendar.getInstance();
		int annoInizio = instance.get(Calendar.YEAR) - 1;
		int annoFine = instance.get(Calendar.YEAR);

		arcoTemporale.setAnnoInizio(annoInizio);
		arcoTemporale.setAnnoFine(annoFine);

		richiesta.setArcoTemporale(arcoTemporale);
		if (userPreferences.getPartitaIvaServizio() != null) {
			richiesta.setPartitaIva(userPreferences.getPartitaIvaServizio());
		}
		else {
			richiesta.setCodiceFiscale(userPreferences.getCodiceFiscaleServizio());
		}

		TipoTributo TipoTributo = new TipoTributo();
		Tributo tributo = new Tributo();
		tributo.setOSAP("");
		TipoTributo.setTributo(tributo);
		richiesta.setTipoTributo(TipoTributo);

		VisuraPosizioneTributariaRisposta dati = service.richiediDatiVisuraPosizioneTributaria(richiesta, helper.getUserPreferences(portletRequest));
		return dati;
	}

	/**
	 * Presenta il form per il pagamento a video.
	 *
	 * @param model
	 * @return l'ID della view
	 */
	@RenderMapping(params = "action=renderDatiPagamento")
	public String renderDatiPagamento(RenderRequest request, Model model, @RequestParam("numeroPosizione") String numeroPosizione) {

		log.info("numeroPosizione: " + numeroPosizione);

		UserPreferences userPreferences = helper.getUserPreferences(request);
		String codiceFiscale = userPreferences.getCodiceFiscaleServizio();
		ProfiloUtenteCittadino profiloUtente = helper.getOsApulieUserDetails().getProfiloUtenteCittadino();

		try {
			VisuraPosizioneTributariaRisposta dati = getVisuraPosizioneTributaria(request, userPreferences);

			DatiOsapTemporanea datiOsapTemporanea = null;
			List<DatiOsapTemporanea> elencoPagamentiOsapTemporanea = dati.getElencoPagamentiOsapTemporanea();
			for (int i = 0; i < elencoPagamentiOsapTemporanea.size(); i++) {
				DatiOsapTemporanea datiOsapTemporanea2 = elencoPagamentiOsapTemporanea.get(i);
				if (i == Integer.parseInt(numeroPosizione)) {
					datiOsapTemporanea = datiOsapTemporanea2;
				}
			}
			if (datiOsapTemporanea != null) {

				ComuneISA comuneISA = comuneISAService.getComuneByPk(userPreferences.getIdComuneIsa());

				PaymentRequest paymentRequest = new PaymentRequest();
				String causale = userPreferences.getCodiceIstatComune() + "-" + comuneISA.getAmministrazione() + "-" + datiOsapTemporanea.getNumeroDocumento() + "-" + numeroPosizione;
				paymentRequest.setCausale(causale);
				paymentRequest.setCodiceCategoriaServizio(PortletConstants.CODICE_SERVIZIO_PAGAMENTO_COSAP_TOSAP_TEMPORANEA);
				paymentRequest.setCodiceOrganizzazione(comuneISA.getAmministrazione());
				paymentRequest.setDenominazioneCliente(profiloUtente.getCognome() + " " + profiloUtente.getNome());
				paymentRequest.setDescrizioneServizio(messageSource.getMessage("label.fascicoloUtente.servizio", null, request.getLocale()));
				paymentRequest.setIdFiscaleCliente(codiceFiscale);
				double importoServizio = datiOsapTemporanea.getImportoDocumento();
				paymentRequest.setImportoServizio(importoServizio);
				paymentRequest.setEmailQuietanza(profiloUtente.getMail());
				// Identificativo credito non esistente: generazione adhoc
				String identificativoCredito = datiOsapTemporanea.getNumeroDocumento() + "-" + datiOsapTemporanea.getAnnoRiferimento() + "-" + numeroPosizione;
				paymentRequest.setIdentificativoCredito(identificativoCredito);
				// Callback url
				ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
				HttpServletRequest origRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
				PortletURL renderUrl = PortletURLFactoryUtil.create(origRequest, themeDisplay.getPortletDisplay().getId(), themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);
				renderUrl.setWindowState(LiferayWindowState.NORMAL);
				renderUrl.setPortletMode(LiferayPortletMode.VIEW);
				renderUrl.setParameter("action", "renderPagamentoEsito");
				String callBackUrlOrganizzazione = renderUrl.toString();
				paymentRequest.setCallBackUrlOrganizzazione(callBackUrlOrganizzazione);
				String portalURL = PortalUtil.getPortalURL(origRequest);
				paymentRequest.setCallBackEndpointWsUrlOrganizzazione(portalURL + applicationProperties.getProperty("pagamenti.callback.endpoint.ws.url.organizzazione"));

				boolean pagamentoAbilitato = importoServizio > 0;
				model.addAttribute("pagamentoAbilitato", pagamentoAbilitato);
				model.addAttribute("paymentRequestDto", paymentRequest);
			}
		}
		catch (Exception e) {
			log.error("renderDatiPagamento :: " + e.getMessage(), e);
		}

		model.addAttribute("identificativoRata", numeroPosizione);

		return "common/datiPagamento";
	}

	@ActionMapping(params = "action=confermaPagamento")
	public void confermaPagamento(@RequestParam("identificativoRata") String identificativoRata, @ModelAttribute PaymentRequest paymentRequest, Model model, ActionRequest request,
			ActionResponse response) throws Exception {

		log.debug("Identificativo Rata action: " + identificativoRata);
		UserPreferences userPreferences = helper.getUserPreferences(request);
		String codiceFiscale = userPreferences.getCodiceFiscaleServizio();

		try {

			log.info("userCF=" + codiceFiscale);

			// Inizio handshake con portale dei pagamenti per generazione URL e redirect
			PaymentClient paymentClient = new PaymentClientImpl();

			PaymentResponse paymentResponse = paymentClient.paymentStart(paymentRequest, applicationProperties.getProperty("pagamenti.portal.ws.url"),
					applicationProperties.getProperty("pagamenti.portal.ws.username"), applicationProperties.getProperty("pagamenti.portal.ws.password"));

			if (paymentResponse.getMessaggioErrore() != null) {
				log.error("confermaPagamento :: errore ritornato dal portale dei pagamenti : " + paymentResponse.getMessaggioErrore());
				model.addAttribute("formError", paymentResponse.getMessaggioErrore());
				response.setRenderParameter("identificativoRata", identificativoRata);
				response.setRenderParameter("action", "renderDatiPagamento");
				return;
			}

			ProfiloUtenteCittadino profiloUtente = helper.getOsApulieUserDetails().getProfiloUtenteCittadino();

			// Scrittura su fascicolo utente
			Fascicolo fascicolo = new Fascicolo();

			Map<String, String> infoAggiuntiveMap = new LinkedHashMap<String, String>();
			infoAggiuntiveMap.put(PortletConstants.PAGAMENTO_ID_PAGAMENTO_JSON_KEY, paymentResponse.getIdentificativoPagamento());
			infoAggiuntiveMap.put(PortletConstants.PAGAMENTO_STATO_JSON_KEY, PortletConstants.PAGAMENTO_STATO_IN_ATTESA);
			infoAggiuntiveMap.put(PortletConstants.PAGAMENTO_ID_CREDITO_JSON_KEY, paymentRequest.getIdentificativoCredito());

			fascicolo.setInfoAggiuntive(new ObjectMapper().writeValueAsString(infoAggiuntiveMap));
			fascicolo.setIdProfiloUtente(profiloUtente);
			fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio", null, request.getLocale()));
			fascicolo.setUserPreferences(helper.getUserPreferences(request));
			fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_PAGAMENTO_COSAP_TOSAP_TEMPORANEA);
			fascicolo.setRicercabileDaComune(true);
			fascicolo.setNumeroProtocollo(null);
			fascicolo.setChecksum(null);
			fascicoloService.saveNuovaRichiesta(fascicolo);

			// Redirect al portale dei pagamenti
			String paymentRedirectUrl = paymentResponse.getRedirectUrl();
			response.sendRedirect(paymentRedirectUrl);

		}
		catch (Exception e) {
			log.error("Action :: confermaPagamento : " + e.getMessage(), e);
		}
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
		return "pagamentotosapcosaptemporanea/" + viewName;
	}
}
