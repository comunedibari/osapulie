/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pagamenti.web.portlet.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.portlet.RenderRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.fascicoloutente.RichiestaServizio;
import it.osapulie.pagamenti.utils.PortletConstants;
import it.osapulie.service.FascicoloUtenteService;
import it.osapulie.web.portlet.controller.BaseController;
import it.osapulie.web.portlet.util.PortletHelper;

/**
 * @author Gianluca Pindinelli
 *
 */
public class PagamentiCommonController extends BaseController {

	protected Logger log = LoggerFactory.getLogger(PagamentiCommonController.class.getName());

	@Autowired
	public ResourceBundleMessageSource messageSource;

	@Inject
	private PortletHelper helper;

	@Inject
	private FascicoloUtenteService fascicoloService;

	/**
	 * Presenta la visura a video.
	 *
	 * @param model
	 * @return l'ID della view
	 */
	@RenderMapping(params = "action=renderPagamentoEsito")
	public String renderPagamentoEsito(@RequestParam("id_p") String identificativoPagamento, RenderRequest renderRequest, Model model) {

		ProfiloUtenteCittadino profiloUtente = helper.getOsApulieUserDetails().getProfiloUtenteCittadino();
		RichiestaServizio richiestaServizio = fascicoloService.getRichiestaServizioFascicolUtente(profiloUtente, identificativoPagamento);

		if (richiestaServizio == null) {
			log.error("confermaPagamento :: nessuna richiesta di pagamento nel fascicolo dell'utente");
			model.addAttribute("formError", "Richiesta di pagamento non trovata nel fascicolo dell'utente!");
		}
		else {
			// esito positivo
			Map<String, String> esitoMap = getEsitoMap(renderRequest, richiestaServizio);
			model.addAttribute("esitoMap", esitoMap);

			String infoAggiuntive = richiestaServizio.getInfoAggiuntive();
			HashMap<String, String> mapByString = null;
			if (infoAggiuntive != null) {
				ObjectMapper mapper = new ObjectMapper();
				TypeReference<HashMap<String, String>> typeRef = new TypeReference<HashMap<String, String>>() {
				};
				try {
					mapByString = mapper.readValue(infoAggiuntive, typeRef);
				}
				catch (IOException e) {
					log.error("renderPagamentoEsito :: " + e.getMessage(), e);
				}
			}

			if (mapByString.containsKey(PortletConstants.PAGAMENTO_STATO_JSON_KEY)) {
				String esito = mapByString.get(PortletConstants.PAGAMENTO_STATO_JSON_KEY);
				if (esito.equals(PortletConstants.PAGAMENTO_STATO_PAGATO)) {
					model.addAttribute("esito", "ok");
				}
				else {
					model.addAttribute("esito", "ko");
				}
			}
		}

		return "common/esito";
	}

	/**
	 * @param renderRequest
	 * @param richiestaServizio
	 * @return
	 */
	public Map<String, String> getEsitoMap(RenderRequest renderRequest, RichiestaServizio richiestaServizio) {
		Map<String, String> esitoMap = null;
		String infoAggiuntive = richiestaServizio.getInfoAggiuntive();
		if (infoAggiuntive != null) {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<HashMap<String, String>> typeRef = new TypeReference<HashMap<String, String>>() {
			};
			try {
				HashMap<String, String> mapByString = mapper.readValue(infoAggiuntive, typeRef);
				esitoMap = new LinkedHashMap<String, String>();
				if (mapByString.containsKey(PortletConstants.PAGAMENTO_ID_PAGAMENTO_JSON_KEY)) {
					esitoMap.put(messageSource.getMessage("label.pagamento.idPagamento", null, renderRequest.getLocale()), mapByString.get(PortletConstants.PAGAMENTO_ID_PAGAMENTO_JSON_KEY));
				}
				if (mapByString.containsKey(PortletConstants.PAGAMENTO_ID_CREDITO_JSON_KEY)) {
					esitoMap.put(messageSource.getMessage("label.pagamento.idCredito", null, renderRequest.getLocale()), mapByString.get(PortletConstants.PAGAMENTO_ID_CREDITO_JSON_KEY));
				}
				if (mapByString.containsKey(PortletConstants.PAGAMENTO_STATO_JSON_KEY)) {
					esitoMap.put(messageSource.getMessage("label.pagamento.stato", null, renderRequest.getLocale()), mapByString.get(PortletConstants.PAGAMENTO_STATO_JSON_KEY));
				}
			}
			catch (IOException e) {
				log.error("renderPagamentoEsito :: " + e.getMessage(), e);
			}
		}
		return esitoMap;
	}

}
