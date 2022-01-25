package it.osapulie.pagamenti.web.portlet.servizicimiteriali;

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
import it.osapulie.pagamenti.service.ServiziCimiterialiService;
import it.osapulie.pagamenti.utils.PortletConstants;
import it.osapulie.service.FascicoloUtenteService;
import it.osapulie.shared.service.Segnalazione;
import it.osapulie.shared.service.SegnalazioneFoglia;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.tributi.web.ws.output.types.PagamentiServiziCimiterialiRichiesta;
import it.osapulie.tributi.web.ws.output.types.PagamentiServiziCimiterialiRisposta;
import it.osapulie.tributi.web.ws.output.types.PagamentiServiziCimiterialiType;
import it.osapulie.tributi.web.ws.output.types.PagamentiServiziCimiterialiType.PosizioniServiziCimiteriali;
import it.osapulie.tributi.web.ws.output.types.PagamentiServiziCimiterialiType.PosizioniServiziCimiteriali.Defunti;
import it.osapulie.tributi.web.ws.output.types.PagamentiServiziCimiterialiType.PosizioniServiziCimiteriali.LampadaVotiva;
import it.osapulie.tributi.web.ws.output.types.PagamentiServiziCimiterialiType.Rate;
import it.osapulie.web.portlet.controller.BaseController;
import it.osapulie.web.portlet.util.PortletHelper;

/**
 * Portlet controller per la visura Servizi Cimiteriali
 *
 * @author Giovanni Barone
 */
@Controller("visuraServiziCimiterialiPortletController")
@RequestMapping("view")
@SessionAttributes("dati")
public class VisuraServiziCimiterialiPortletController extends BaseController {

	private static Logger log = LoggerFactory.getLogger(VisuraServiziCimiterialiPortletController.class);

	@Inject
	private ServiziCimiterialiService service;

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
	 * @return il view ID da renderizzare.
	 */
	@RenderMapping
	public String homePage(Model model, PortletSession session) {
		log.info("home ");

		return toLocalViewPath("home");
	}

	@ActionMapping(params = "action=visura")
	public void visuraServiciCimiteriali(@RequestParam("dayStart") String dayStart, @RequestParam("monthStart") String monthStart, @RequestParam("yearStart") String yearStart,
			@RequestParam("dayEnd") String dayEnd, @RequestParam("monthEnd") String monthEnd, @RequestParam("yearEnd") String yearEnd, Model model,
			@RequestParam(required = false, value = "invia") String invia, @ModelAttribute("dati") PagamentiServiziCimiterialiRisposta dati, ActionResponse response, PortletRequest portletRequest)
			throws Exception {

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
				log.error("Action :: visuraServiciCimiteriali : " + e.getMessage());
			}

			try {
				UserPreferences userPreferences = helper.getUserPreferences(portletRequest);
				String codiceFiscale = userPreferences.getCodiceFiscaleServizio();
				log.info("userCF=" + codiceFiscale);

				PagamentiServiziCimiterialiRichiesta richiesta = new PagamentiServiziCimiterialiRichiesta();
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
				richiesta.setCodiceFiscale(codiceFiscale);

				dati = new PagamentiServiziCimiterialiRisposta();
				dati = service.richiediDatiServiziCimiteriali(richiesta, helper.getUserPreferences(portletRequest));

				model.addAttribute("dati", dati);

				// Scrittura su fascicolo utente
				ProfiloUtenteCittadino profiloUtente = helper.getOsApulieUserDetails().getProfiloUtenteCittadino();

				Fascicolo fascicolo = new Fascicolo();
				fascicolo.setIdProfiloUtente(profiloUtente);
				fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio", null, portletRequest.getLocale()));
				fascicolo.setUserPreferences(helper.getUserPreferences(portletRequest));
				fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_VISURA_POSIZIONE_SERVIZI_CIMITERIALI);
				fascicolo.setRicercabileDaComune(false);
				fascicolo.setNumeroProtocollo(null);
				fascicolo.setChecksum(null);
				fascicoloService.saveNuovaRichiesta(fascicolo);

				// fascicoloService.saveNuovaRichiesta(profiloUtente,messageSource.getMessage("label.fascicoloUtente.servizio",
				// null,portletRequest.getLocale()),
				// codiceFiscale,helper.getUserPreferences(portletRequest),PortletConstants.CODICE_SERVIZIO_VISURA_POSIZIONE_SERVIZI_CIMITERIALI,false,
				// null);
			}
			catch (Exception e) {
				log.error("Action :: visuraServiciCimiteriali : " + e.getMessage(), e);
			}
			response.setRenderParameter("action", "renderVisura");
		}

		else {

			List<SegnalazioneFoglia> listaSegnalazioni = inviaSegnalazioneAction(dati, portletRequest);

			Segnalazione segnalazione = new Segnalazione();
			segnalazione.setListaSegnalazioni(listaSegnalazioni);
			segnalazione.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio", null, portletRequest.getLocale()));
			segnalazione.setServizioCode(PortletConstants.CODICE_SERVIZIO_VISURA_POSIZIONE_SERVIZI_CIMITERIALI);

			PortletSession session = portletRequest.getPortletSession();
			session.setAttribute(PortletConstants.SEGNALAZIONE_SHARED_ATTRIBUTE, segnalazione, PortletSession.APPLICATION_SCOPE);
			response.sendRedirect(segnalazioniPageUrl);

		}

	}

	private List<SegnalazioneFoglia> inviaSegnalazioneAction(PagamentiServiziCimiterialiRisposta dati, PortletRequest portletRequest) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		List<SegnalazioneFoglia> listaSegnalazioni = new ArrayList<SegnalazioneFoglia>();
		if (dati != null && dati.getElencoPagamentiCimiteriali() != null && !dati.getElencoPagamentiCimiteriali().isEmpty()) {
			for (PagamentiServiziCimiterialiType pagamento : dati.getElencoPagamentiCimiteriali()) {
				SegnalazioneFoglia nodo = new SegnalazioneFoglia(messageSource.getMessage("label.titolo", null, portletRequest.getLocale()));
				nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.numeroConto", null, portletRequest.getLocale()), pagamento.getContoCorrente()));
				nodo.getListaSegnalazioni()
						.add(new SegnalazioneFoglia(messageSource.getMessage("label.annoRiferimento", null, portletRequest.getLocale()), String.valueOf(pagamento.getAnnoRiferimento())));
				nodo.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.numeroDocumento", null, portletRequest.getLocale()), pagamento.getNumeroDocumento()));
				nodo.getListaSegnalazioni()
						.add(new SegnalazioneFoglia(messageSource.getMessage("label.importoDocumento", null, portletRequest.getLocale()), String.valueOf(pagamento.getImportoDocumento())));
				nodo.getListaSegnalazioni()
						.add(new SegnalazioneFoglia(messageSource.getMessage("label.dataAggiornamento", null, portletRequest.getLocale()), sdf.format(pagamento.getDataAggiornamento().getTime())));

				for (PosizioniServiziCimiteriali posizione : pagamento.getPosizioniServiziCimiteriali()) {
					SegnalazioneFoglia nodoPosizione = new SegnalazioneFoglia("");
					nodoPosizione.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.nomeCimitero", null, portletRequest.getLocale()), posizione.getNomecimitero()));
					nodoPosizione.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.posizione", null, portletRequest.getLocale()), posizione.getPosizione()));

					for (Defunti defunto : posizione.getDefunti()) {
						SegnalazioneFoglia nodoDefunto = new SegnalazioneFoglia("");
						nodoDefunto.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.nomeDefunto", null, portletRequest.getLocale()), defunto.getNomeDefunto()));
						nodoDefunto.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.dataNascita", null, portletRequest.getLocale()), sdf.format(defunto.getDataNascita().getTime())));
						nodoDefunto.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.dataMorte", null, portletRequest.getLocale()), sdf.format(defunto.getDataMorte().getTime())));

						nodoPosizione.getListaSegnalazioni().add(nodoDefunto);
					}

					for (LampadaVotiva lampada : posizione.getLampadaVotiva()) {
						SegnalazioneFoglia nodoLampada = new SegnalazioneFoglia("");
						nodoLampada.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.mesi", null, portletRequest.getLocale()), String.valueOf(lampada.getMesi())));
						nodoLampada.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.tariffa", null, portletRequest.getLocale()), lampada.getTariffa()));
						nodoLampada.getListaSegnalazioni()
								.add(new SegnalazioneFoglia(messageSource.getMessage("label.importoLampada", null, portletRequest.getLocale()), String.valueOf(lampada.getImportoLampada())));

						nodoPosizione.getListaSegnalazioni().add(nodoLampada);
					}
					nodo.getListaSegnalazioni().add(nodoPosizione);

				}

				for (Rate rata : pagamento.getRate()) {
					SegnalazioneFoglia nodoRata = new SegnalazioneFoglia("");
					nodoRata.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.identificativoRata", null, portletRequest.getLocale()), rata.getIdentificativoRata()));
					nodoRata.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.numeroRata", null, portletRequest.getLocale()), String.valueOf(rata.getNumeroRata())));
					nodoRata.getListaSegnalazioni().add(new SegnalazioneFoglia(messageSource.getMessage("label.importoRata", null, portletRequest.getLocale()), String.valueOf(rata.getImportoRata())));
					nodoRata.getListaSegnalazioni()
							.add(new SegnalazioneFoglia(messageSource.getMessage("label.scadenzaRata", null, portletRequest.getLocale()), sdf.format(rata.getScadenzaRata().getTime())));
					nodoRata.getListaSegnalazioni()
							.add(new SegnalazioneFoglia(messageSource.getMessage("label.dataPagamento", null, portletRequest.getLocale()), sdf.format(rata.getDataPagamento().getTime())));
					nodoRata.getListaSegnalazioni()
							.add(new SegnalazioneFoglia(messageSource.getMessage("label.importoPagato", null, portletRequest.getLocale()), String.valueOf(rata.getImportoPagato())));

					nodo.getListaSegnalazioni().add(nodoRata);

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
		return toLocalViewPath("mostraVisuraServiziCimiteriali");
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
		return "visureservizicimiteriali/" + viewName;
	}

	@ModelAttribute(value = "dati")
	public PagamentiServiziCimiterialiRisposta getDati() {
		PagamentiServiziCimiterialiRisposta dati = new PagamentiServiziCimiterialiRisposta();
		return dati;
	}
}
