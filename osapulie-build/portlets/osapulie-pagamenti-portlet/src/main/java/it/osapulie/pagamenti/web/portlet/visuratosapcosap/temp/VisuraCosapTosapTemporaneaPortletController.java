package it.osapulie.pagamenti.web.portlet.visuratosapcosap.temp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.inject.Inject;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.dto.Fascicolo;
import it.osapulie.pagamenti.service.VisureTosapCosapService;
import it.osapulie.pagamenti.utils.PortletConstants;
import it.osapulie.service.FascicoloUtenteService;
import it.osapulie.shared.service.Segnalazione;
import it.osapulie.shared.service.SegnalazioneFoglia;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.tributi.web.ws.output.types.DatiOsapTemporanea;
import it.osapulie.tributi.web.ws.output.types.DatiOsapTemporanea.PosizioniOsap;
import it.osapulie.tributi.web.ws.output.types.PagamentiOsapTemporaneaRichiesta;
import it.osapulie.tributi.web.ws.output.types.PagamentiOsapTemporaneaRisposta;
import it.osapulie.web.portlet.controller.BaseController;
import it.osapulie.web.portlet.util.PortletHelper;

/**
 * Portlet controller per le visure Tosap-Cosap di tipo temporanea.
 *
 * @author Giovanni Barone
 */
@Controller("visuraCosapTosapTemporaneaPortletController")
@RequestMapping("view")
@SessionAttributes("dati")
public class VisuraCosapTosapTemporaneaPortletController extends BaseController {

	private static Logger log = LoggerFactory.getLogger(VisuraCosapTosapTemporaneaPortletController.class);

	@Inject
	private VisureTosapCosapService visureService;

	@Inject
	private PortletHelper helper;

	@Inject
	private FascicoloUtenteService fascicoloService;

	@Autowired
	public ResourceBundleMessageSource messageSource;

	@Value("#{applicationProperties.segnalazioniPageUrl}")
	private String segnalazioniPageUrl;

	/**
	 * Default entry quando la portlet viene visualizzata per la prima volta.
	 *
	 * @param model il view model da popolare
	 * @return il view ID da renderizzare (<strong>elencoOpere</strong>).
	 */
	@RenderMapping
	public String homePage(Model model, PortletSession session) {
		log.info("home ");

		return toLocalViewPath("home");
	}

	@ActionMapping(params = "action=visura")
	public void visuraOsapTemporanea(@RequestParam("dayStart") String dayStart, @RequestParam("monthStart") String monthStart, @RequestParam("yearStart") String yearStart,
			@RequestParam("dayEnd") String dayEnd, @RequestParam("monthEnd") String monthEnd, @RequestParam("yearEnd") String yearEnd, @RequestParam(required = false, value = "invia") String invia,
			Model model, @ModelAttribute("dati") PagamentiOsapTemporaneaRisposta dati, ActionResponse response, PortletRequest portletRequest) throws Exception {

		if (invia != null) {
			Calendar start = null;
			Calendar end = null;
			try {
				start = new GregorianCalendar();
				start.set(Calendar.DATE, Integer.valueOf(dayStart));
				start.set(Calendar.MONTH, Integer.valueOf(monthStart));
				start.set(Calendar.YEAR, Integer.valueOf(yearStart));

				end = new GregorianCalendar();
				end.set(Calendar.DATE, Integer.valueOf(dayEnd));
				end.set(Calendar.MONTH, Integer.valueOf(monthEnd));
				end.set(Calendar.YEAR, Integer.valueOf(yearEnd));
			}
			catch (Exception e) {
				log.error("Action :: visuraOsapTemporanea : " + e.getMessage(), e);
			}

			try {
				UserPreferences userPreferences = helper.getUserPreferences(portletRequest);
				String codiceFiscale = userPreferences.getCodiceFiscaleServizio();
				String partitaIvaServizio = userPreferences.getPartitaIvaServizio();
				log.info("userCF=" + codiceFiscale);

				PagamentiOsapTemporaneaRichiesta richiesta = new PagamentiOsapTemporaneaRichiesta();
				if (start != null && end != null) {
					richiesta.setDal(start);
					richiesta.setAl(end);

					model.addAttribute("dayStart", dayStart);
					model.addAttribute("monthStart", monthStart);
					model.addAttribute("yearStart", yearStart);
					model.addAttribute("dayEnd", dayEnd);
					model.addAttribute("monthEnd", monthEnd);
					model.addAttribute("yearEnd", yearEnd);
				}

				// Controllo profilo cittadino/azienda
				if (partitaIvaServizio != null) {
					richiesta.setPartitaIva(partitaIvaServizio);
				}
				else {
					richiesta.setCodiceFiscale(codiceFiscale);
				}

				dati = new PagamentiOsapTemporaneaRisposta();
				dati = visureService.richiediDatiTosapCosapTemporanea(richiesta, helper.getUserPreferences(portletRequest));
				model.addAttribute("dati", dati);

				// Scrittura su fascicolo utente
				ProfiloUtenteCittadino profiloUtente = helper.getOsApulieUserDetails().getProfiloUtenteCittadino();

				Fascicolo fascicolo = new Fascicolo();
				fascicolo.setIdProfiloUtente(profiloUtente);
				fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio", null, portletRequest.getLocale()));
				fascicolo.setUserPreferences(helper.getUserPreferences(portletRequest));
				fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_VISURA_COSAP_TOSAP_TEMPORANEA);
				fascicolo.setRicercabileDaComune(false);
				fascicolo.setNumeroProtocollo(null);
				fascicolo.setChecksum(null);
				fascicoloService.saveNuovaRichiesta(fascicolo);
			}
			catch (Exception e) {
				log.error("Action :: visuraOsapTemporanea : " + e.getMessage(), e);
			}
			response.setRenderParameter("action", "renderVisura");
		}
		else {

			List<SegnalazioneFoglia> listaSegnalazioni = inviaSegnalazioneAction(dati, portletRequest);

			Segnalazione segnalazione = new Segnalazione();
			segnalazione.setListaSegnalazioni(listaSegnalazioni);
			segnalazione.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio", null, portletRequest.getLocale()));
			segnalazione.setServizioCode(PortletConstants.CODICE_SERVIZIO_VISURA_COSAP_TOSAP_TEMPORANEA);

			PortletSession session = portletRequest.getPortletSession();
			session.setAttribute(PortletConstants.SEGNALAZIONE_SHARED_ATTRIBUTE, segnalazione, PortletSession.APPLICATION_SCOPE);
			response.sendRedirect(segnalazioniPageUrl);

		}

	}

	private List<SegnalazioneFoglia> inviaSegnalazioneAction(PagamentiOsapTemporaneaRisposta datiModel, PortletRequest portletRequest) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		List<SegnalazioneFoglia> listaSegnalazioni = new ArrayList<SegnalazioneFoglia>();
		if (datiModel != null && datiModel.getElencoOsapTemporanea() != null && !datiModel.getElencoOsapTemporanea().isEmpty()) {
			for (DatiOsapTemporanea osapTmp : datiModel.getElencoOsapTemporanea()) {
				SegnalazioneFoglia nodo = new SegnalazioneFoglia(messageSource.getMessage("label.titolo", null, portletRequest.getLocale()));
				nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.numeroConto", null, portletRequest.getLocale()), osapTmp.getContoCorrente()));
				nodo.getListaSegnalazioni()
						.add(new SegnalazioneFoglia(messageSource.getMessage("label.annoRiferimento", null, portletRequest.getLocale()), String.valueOf(osapTmp.getAnnoRiferimento())));
				nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.numeroDocumento", null, portletRequest.getLocale()), osapTmp.getNumeroDocumento()));
				nodo.getListaSegnalazioni()
						.add(new SegnalazioneFoglia(messageSource.getMessage("label.importoDocumento", null, portletRequest.getLocale()), String.valueOf(osapTmp.getImportoDocumento())));
				nodo.getListaSegnalazioni()
						.add(new SegnalazioneFoglia(messageSource.getMessage("label.dataAggiornamento", null, portletRequest.getLocale()), sdf.format(osapTmp.getDataAggiornamento().getTime())));

				if (osapTmp.getPosizioniOsap() != null && !osapTmp.getPosizioniOsap().isEmpty()) {
					for (PosizioniOsap posizione : osapTmp.getPosizioniOsap()) {

						SegnalazioneFoglia nodoInterno = new SegnalazioneFoglia("");
						nodoInterno.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.zonaUtenza", null, portletRequest.getLocale()), posizione.getZona()));
						nodoInterno.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.indirizzoUtenza", null, portletRequest.getLocale()), posizione.getIndirizzoUtenza()));
						nodoInterno.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.superficie", null, portletRequest.getLocale()), String.valueOf(posizione.getSuperficie())));
						nodoInterno.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.durataOccupazione", null, portletRequest.getLocale()), String.valueOf(posizione.getDurataOccupazione())));
						nodoInterno.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.descrizioneTariffa", null, portletRequest.getLocale()), posizione.getDescrizioneTariffa()));
						nodoInterno.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.importoDaPagare", null, portletRequest.getLocale()), String.valueOf(posizione.getImportoDaPagare())));
						nodoInterno.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.importoPagato", null, portletRequest.getLocale()), String.valueOf(posizione.getImportoPagato())));
						nodoInterno.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.dataPagamento", null, portletRequest.getLocale()), sdf.format(posizione.getDataPagamento().getTime())));

						nodo.getListaSegnalazioni().add(nodoInterno);
					}
				}

				listaSegnalazioni.add(nodo);
			}
		}

		return listaSegnalazioni;

	}

	/**
	 * Presenta la visura a video.
	 *
	 * @param model
	 * @return l'ID della view per visualizzare il fascicolo
	 */
	@RenderMapping(params = "action=renderVisura")
	public String renderVisura(Model model) {
		log.debug("Model = " + model);
		return toLocalViewPath("mostraVisuraTemporanea");
	}

	@ExceptionHandler(Exception.class)
	public String onError(Exception e) {
		return "";
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
		return "visuretosapcosaptemporanea/" + viewName;
	}

	@ModelAttribute(value = "dati")
	public PagamentiOsapTemporaneaRisposta getDati() {
		PagamentiOsapTemporaneaRisposta dati = new PagamentiOsapTemporaneaRisposta();
		return dati;
	}
}
