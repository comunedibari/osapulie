package it.osapulie.servizicomune.web.portlet.circolaritaanagrafica;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici.ComponentiNucleoFamiliare;
import it.osapulie.anagrafe.web.ws.output.types.RichiestaDatiAnagrafici;
import it.osapulie.domain.ComuneISA;
import it.osapulie.service.ComuneISAService;
import it.osapulie.servizicomune.model.DatiRicercaCircolaritaAnagrafica;
import it.osapulie.servizicomune.service.CittadinoService;
import it.osapulie.servizicomune.web.portlet.BaseController;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.web.portlet.util.PortletHelper;

@Controller("circolaritaAnagraficaController")
@RequestMapping("view")
@SessionAttributes({ "datiRicerca", "comuni" })
public class CircolaritaAnagraficaController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(CircolaritaAnagraficaController.class);

	@Inject
	private PortletHelper helper;

	@Autowired
	public ResourceBundleMessageSource messageSource;

	@Inject
	private ComuneISAService comuneISAService;

	@Inject
	private CittadinoService cittadinoService;

	@Value("#{applicationProperties.segnalazioniPageUrl}")
	private String segnalazioniPageUrl;

	/**
	 * Default entry quando la portlet viene visualizzata per la prima volta.
	 *
	 * @param model il view model da popolare
	 * @return il view ID da renderizzare.
	 */
	@RenderMapping
	public String homePage(Model model, PortletRequest portletRequest) {
		log.info("Home Circolarita Anagrafica.");

		List<ComuneISA> comuni = comuneISAService.getComuniAttivi();
		Map<String, String> selectComuni = new HashMap<String, String>();

		for (ComuneISA c : comuni) {
			selectComuni.put(c.getCodiceIstat(), c.getNome());
		}

		model.addAttribute("datiRicerca", new DatiRicercaCircolaritaAnagrafica());
		model.addAttribute("comuni", selectComuni);
		return toLocalViewPath("home");
	}

	@ActionMapping(params = "action=getAnagrafica")
	public void getAnagrafica(@ModelAttribute("datiRicerca") @Valid DatiRicercaCircolaritaAnagrafica datiRicerca, BindingResult result, Model model, PortletSession session, ActionResponse response,
			PortletRequest portletRequest) throws Exception {

		DatiAnagrafici dati = null;
		if (!result.hasErrors()) {

			/*
			 * Utilizzo UserPreferences per settare il comune scelto come parametro di ricerca
			 */
			UserPreferences userPreferences = new UserPreferences();

			ComuneISA comune = comuneISAService.getComuneByCodiceIstat(datiRicerca.getCodiceIstat());
			userPreferences.setCodiceIstatComune(comune.getCodiceIstat());
			userPreferences.setIdComuneIsa(comune.getId());
			userPreferences.setUriServizioGateway(comune.getUriServizioGateway());
			userPreferences.setNomeComune(comune.getNome());

			RichiestaDatiAnagrafici richiesta = new RichiestaDatiAnagrafici();
			richiesta.setCodiceFiscale(datiRicerca.getCodiceFiscale());

			dati = cittadinoService.richiediDatiAnagrafici(richiesta, helper.getUserPreferences(portletRequest));

			ComponentiNucleoFamiliare componente = null;
			List<ComponentiNucleoFamiliare> componenti = dati.getComponentiNucleoFamiliare();
			for (int i = 0; i < componenti.size(); i++) {
				componente = componenti.get(i);
				if (datiRicerca.getCodiceFiscale() != null && datiRicerca.getCodiceFiscale().equals(componente.getCodiceFiscale())) {
					cittadinoService.addInfoAggiuntiveComponenteToModel(model, componente);
					model.addAttribute("componenteFamiglia", componente);
					break;
				}
			}

		}

		model.addAttribute("datiAnagrafici", dati);
		response.setRenderParameter("action", "renderAnagrafica");
	}

	/**
	 * Presenta i dati anagrafici a video
	 *
	 * @param model
	 * @return l'ID della view per visualizzare il fascicolo
	 */
	@RenderMapping(params = "action=renderAnagrafica")
	public String renderAnagrafica(Model model) {
		log.debug("Model = " + model);
		return toLocalViewPath("mostraAnagrafica");
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
		return "circolaritaanagrafica/" + viewName;
	}
}
