package it.osapulie.pagamenti.web.portlet.pagamentotarsu;

import javax.inject.Inject;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import it.osapulie.pagamenti.service.PagamentoServiziService;
import it.osapulie.pagamenti.service.PosizioneTributariaService;
import it.osapulie.service.FascicoloUtenteService;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaRichiesta;
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaRisposta;
import it.osapulie.web.portlet.util.PortletHelper;

/**
 * Portlet controller pagamento TARSU.
 *
 * @author Giovanni Barone
 */
@Controller("pagamentoTarsuController")
@RequestMapping("view")
@SessionAttributes("dati")
@Deprecated
public class PagamentoTarsuController {

	private static Logger log = LoggerFactory.getLogger(PagamentoTarsuController.class);

	@Inject
	private PosizioneTributariaService service;

	@Inject
	private PagamentoServiziService pagamentoService;

	@Inject
	private PortletHelper helper;

	@Inject
	private FascicoloUtenteService fascicoloService;

	@Autowired
	public ResourceBundleMessageSource messageSource;

	/**
	 * Default entry quando la portlet viene visualizzata per la prima volta.
	 *
	 * @param model il view model da popolare
	 * @return il view ID da renderizzare.
	 */
	@RenderMapping
	public String homePage(Model model, PortletRequest portletRequest) {
		log.info("Home Pagamento Tarsu.");
		try {
			UserPreferences userPreferences = helper.getUserPreferences(portletRequest);
			String codiceFiscale = userPreferences.getCodiceFiscaleServizio();
			log.info("userCF=" + codiceFiscale);

			VisuraPosizioneTributariaRichiesta richiesta = new VisuraPosizioneTributariaRichiesta();
			richiesta.setCodiceFiscale(codiceFiscale);

			VisuraPosizioneTributariaRisposta dati = service.richiediDatiVisuraPosizioneTributaria(richiesta, helper.getUserPreferences(portletRequest));
			model.addAttribute("dati", dati);
		}
		catch (Exception e) {
			log.error("Render home :: homePage : " + e.getMessage(), e);
		}

		return toLocalViewPath("pagamentoTarsu");
	}

	@ActionMapping(params = "action=confermaPagamento")
	public void confermaPagamento(@RequestParam("identificativoRata") String identificativoRata, @RequestParam("nome") String nome, @RequestParam("cognome") String cognome,
			@RequestParam("numeroCarta") String numeroCarta, @RequestParam("scadenzaCarta") String scadenzaCarta, @RequestParam("cvv") String cvv, Model model, ActionResponse response,
			PortletRequest portletRequest) throws Exception {

		log.debug("Identificativo Rata action: " + identificativoRata);

		response.setRenderParameter("action", "renderPagamentoEsito");

	}

	/**
	 * Presenta il form per il pagamento a video.
	 *
	 * @param model
	 * @return l'ID della view
	 */
	@RenderMapping(params = "action=renderDatiPagamento")
	public String renderDatiPagamento(Model model, @RequestParam("identificativoRata") String identificativoRata) {
		log.info("Identificativo Rata: " + identificativoRata);

		model.addAttribute("identificativoRata", identificativoRata);
		return "common/datiPagamento";
	}

	/**
	 * Presenta la visura a video.
	 *
	 * @param model
	 * @return l'ID della view
	 */
	@RenderMapping(params = "action=renderPagamentoEsito")
	public String renderPagamentoEsito(Model model) {
		log.info("Model = " + model);
		return toLocalViewPath("esito");
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
		return "pagamentotarsu/" + viewName;
	}
}
