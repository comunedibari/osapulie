/**
 * Copyright (c) 2011 Links Management & Technology S.p.A.
 */
package it.osapulie.profiloutente.web.portlet.fascicoloutente.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.beans.factory.annotation.Value;
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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import it.osapulie.domain.Azienda;
import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.ProfiloUtenteCittadinoAzienda;
import it.osapulie.domain.fascicoloutente.FascicoloUtente;
import it.osapulie.domain.fascicoloutente.RichiestaServizio;
import it.osapulie.domain.servizi.Servizio;
import it.osapulie.infrastructure.ContentMimeTypes;
import it.osapulie.infrastructure.ReportService;
import it.osapulie.infrastructure.security.OSApulieUserDetails;
import it.osapulie.profiloutente.web.portlet.fascicoloutente.form.FascicoloAziendaForm;
import it.osapulie.profiloutente.web.portlet.fascicoloutente.form.ReportAziendaForm;
import it.osapulie.profiloutente.web.portlet.fascicoloutente.form.RicevutaForm;
import it.osapulie.protocollo.web.ws.types.Allegato;
import it.osapulie.protocollo.web.ws.types.DettaglioAllegatoResponse;
import it.osapulie.protocollo.web.ws.types.DettaglioProtocolloResponse;
import it.osapulie.protocollo.web.ws.types.Documento;
import it.osapulie.protocollo.web.ws.types.Errore;
import it.osapulie.protocollo.web.ws.types.ProtocolloResponse;
import it.osapulie.service.AziendaService;
import it.osapulie.service.ComuneISAService;
import it.osapulie.service.FascicoloUtenteService;
import it.osapulie.service.RichiestaServizioService;
import it.osapulie.service.ServizioService;
import it.osapulie.web.portlet.util.CommunicationException;
import it.osapulie.web.portlet.util.PortletHelper;
import it.osapulie.web.portlet.util.RoleConstants;
import it.osapulie.web.portlet.util.SenderHelper;

/**
 * Portlet per gestire il fascicolo utente e le operazioni ad esso collegate.
 *
 * @author Mario Scalas
 * @author Maria Michela Birtolo
 * @author Gianluca Pindinelli
 */
@Controller("fascicoloUtentePortletController")
@RequestMapping("view")
@SessionAttributes({ "fascicolo", "profiloUtente", "fascicoloAziendaForm", "fascicoloAziendaReportForm" })
public class FascicoloUtentePortletController {

	private static final String REPORT_PDF_FILE_NAME = "report.pdf";
	private static final String REPORT_PDF_RICEVUTA_FILE_NAME_PREFIX = "Ricevuta_operazione_";
	private static final String REPORT_PDF_RICEVUTA_FILE_NAME_SUFFIX = ".pdf";
	private static final String REPORTS_AZIENDA_JRXML = "/reports/reportAzienda.jrxml";
	private static final String REPORTS_RICEVUTA_JRXML = "/reports/ricevutaAzienda.jrxml";

	private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

	private final Logger log = LoggerFactory.getLogger(FascicoloUtentePortletController.class);

	@Inject
	private FascicoloUtenteService fascicoloUtenteService;

	@Inject
	private RichiestaServizioService richiestaServizioService;

	@Inject
	private PortletHelper helper;

	@Inject
	private SenderHelper senderHelper;

	@Inject
	private ComuneISAService comuneISAService;

	@Inject
	private AziendaService aziendaService;

	@Inject
	private ServizioService servizioService;

	@Autowired
	public ResourceBundleMessageSource messageSource;

	@Inject
	private ReportService reportService;

	@Value("#{applicationProperties.dimensionePagina}")
	private int pageSize;

	/**
	 * Default entry quando la portlet viene visualizzata per la prima volta.
	 *
	 * @param model il view model da popolare
	 * @return il view ID da renderizzare
	 */
	@RenderMapping
	public String homePage(Model model, PortletRequest portletRequest) {

		ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(portletRequest.getPortletSession());
		model.addAttribute("profiloUtente", profiloUtente);

		// Verifica visualizzazione fascicolo azienda
		List<Azienda> aziende = helper.getOsApulieUserDetails().getAziende();
		model.addAttribute("aziende", aziende);

		return toLocalViewPath("home");
	}

	@ModelAttribute("responsabile")
	public boolean isResponsabileAzienda(PortletRequest portletRequest) {
		return helper.userHasRole(helper.getOsApulieUserDetails().getLiferayUser(), RoleConstants.RESPONSABILE_AZIENDA)
				|| helper.userHasRole(helper.getOsApulieUserDetails().getLiferayUser(), RoleConstants.RESPONSABILE_CAF)
				|| helper.userHasRole(helper.getOsApulieUserDetails().getLiferayUser(), RoleConstants.OPERATORE_AZIENDA)
				|| helper.userHasRole(helper.getOsApulieUserDetails().getLiferayUser(), RoleConstants.OPERATORE_CAF);
	}

	@ActionMapping(params = "action=mostraFascicoloCittadino")
	public void mostraFascicoloCittadino(Model model, ActionResponse response, PortletSession session) {

		ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(session);
		model.addAttribute("profiloUtente", profiloUtente);

		FascicoloUtente fascicolo = fascicoloUtenteService.getFascicoloUtenteByCfCittadino(profiloUtente.getCodiceFiscale());

		if (fascicolo != null) {
			model.addAttribute("fascicolo", fascicolo);
		}

		List<RichiestaServizio> richieste = fascicolo.getRichieste();
		if (richieste != null) {
			setInfoAggiuntiveMap(richieste);
		}

		response.setRenderParameter("action", "renderFascicoloCittadino");
	}

	@ModelAttribute("fascicoloAziendaForm")
	public FascicoloAziendaForm getFascicoloAziendaForm() {
		return new FascicoloAziendaForm();
	}

	@ModelAttribute("fascicoloAziendaReportForm")
	public FascicoloAziendaForm getFascicoloAziendaReportForm() {
		return new FascicoloAziendaForm();
	}

	@ActionMapping(params = "action=mostraFascicoloAzienda")
	public void mostraFascicoloAzienda(@ModelAttribute("fascicoloAziendaForm") FascicoloAziendaForm fascicoloAziendaForm, Model model, ActionResponse response) {

		List<Azienda> aziende = helper.getOsApulieUserDetails().getAziende();
		FascicoloUtente fascicoloUtente = null;
		if (fascicoloAziendaForm.getIdAzienda() == null && !aziende.isEmpty() && aziende.get(0).getFascicoloUtente() != null) {
			fascicoloUtente = aziende.get(0).getFascicoloUtente();
			model.addAttribute("azienda", aziende.get(0));
		}

		if (fascicoloAziendaForm.getIdAzienda() != null) {
			Azienda azienda = aziendaService.getAziendaByPk(fascicoloAziendaForm.getIdAzienda());
			if (azienda != null) {
				model.addAttribute("azienda", azienda);
				fascicoloUtente = azienda.getFascicoloUtente();
			}
		}

		if (fascicoloUtente != null) {
			fascicoloAziendaForm.setIdAzienda(fascicoloUtente.getAzienda().getId());
			model.addAttribute("fascicolo", fascicoloUtente);
		}
		else {
			model.addAttribute("fascicolo", new FascicoloUtente());
		}
		model.addAttribute("aziende", aziende);
		response.setRenderParameter("action", "renderFascicoloAzienda");
	}

	@ActionMapping(params = "action=mostraReportAziende")
	public void mostraReportAziende(@ModelAttribute("fascicoloAziendaReportForm") FascicoloAziendaForm fascicoloAziendaReportForm, Model model, ActionResponse response) {

		Azienda aziendaAttuale = null;

		// Caricamento aziende alle quali è associato l'utente loggato
		OSApulieUserDetails osApulieUserDetails = helper.getOsApulieUserDetails();
		List<Azienda> aziende = osApulieUserDetails.getAziende();
		if (aziende != null && !aziende.isEmpty()) {
			aziendaAttuale = aziende.get(0);
		}

		if (fascicoloAziendaReportForm.getIdAzienda() != null) {
			aziendaAttuale = aziendaService.getAziendaByPk(fascicoloAziendaReportForm.getIdAzienda());
		}

		if (aziendaAttuale != null) {
			fascicoloAziendaReportForm.setIdAzienda(aziendaAttuale.getId());
			model.addAttribute("azienda", aziendaAttuale);

			List<RichiestaServizio> richiesteServiziByAzienda = getRichiesteServizioAzienda(aziendaAttuale, osApulieUserDetails, fascicoloAziendaReportForm);
			model.addAttribute("richiesteServizi", richiesteServiziByAzienda);
		}

		model.addAttribute("aziende", aziende);

		// Aggiunta servizi per filtro ricerca
		List<Servizio> serviziAttiviForDelega = servizioService.getServiziAttiviForDelega();
		model.addAttribute("servizi", serviziAttiviForDelega);

		response.setRenderParameter("action", "renderReportAziende");
	}

	/**
	 * @param aziendaAttuale
	 * @param osApulieUserDetails
	 * @param fascicoloAziendaForm
	 * @return
	 */
	private List<RichiestaServizio> getRichiesteServizioAzienda(Azienda aziendaAttuale, OSApulieUserDetails osApulieUserDetails, FascicoloAziendaForm fascicoloAziendaForm) {
		List<RichiestaServizio> richiesteServiziByAzienda = null;

		Date da = null;
		Date a = null;
		Long idProfiloUtente = null;
		Long idAziendaAttuale = aziendaAttuale.getId();
		Long idServizio = fascicoloAziendaForm.getIdServizio();
		try {
			if (fascicoloAziendaForm.getDataRichiestaDa() != null && !fascicoloAziendaForm.getDataRichiestaDa().isEmpty()) {
				da = simpleDateFormat.parse(fascicoloAziendaForm.getDataRichiestaDa());
			}
			if (fascicoloAziendaForm.getDataRichiestaA() != null && !fascicoloAziendaForm.getDataRichiestaA().isEmpty()) {
				a = simpleDateFormat.parse(fascicoloAziendaForm.getDataRichiestaA());
			}
		}
		catch (ParseException e) {
			log.error("getRichiesteServizioAzienda :: " + e.getMessage(), e);
		}

		if (helper.userHasRole(osApulieUserDetails.getLiferayUser(), RoleConstants.RESPONSABILE_AZIENDA) || helper.userHasRole(osApulieUserDetails.getLiferayUser(), RoleConstants.RESPONSABILE_CAF)) {
			richiesteServiziByAzienda = richiestaServizioService.searchRichiesteServizio(idAziendaAttuale, null, da, a, idServizio);
		}
		else if (helper.userHasRole(osApulieUserDetails.getLiferayUser(), RoleConstants.OPERATORE_AZIENDA) || helper.userHasRole(osApulieUserDetails.getLiferayUser(), RoleConstants.OPERATORE_CAF)) {
			idProfiloUtente = osApulieUserDetails.getProfiloUtenteCittadino().getId();
			richiesteServiziByAzienda = richiestaServizioService.searchRichiesteServizio(idAziendaAttuale, idProfiloUtente, da, a, idServizio);
		}

		if (richiesteServiziByAzienda != null) {
			setInfoAggiuntiveMap(richiesteServiziByAzienda);
		}
		return richiesteServiziByAzienda;
	}

	@ActionMapping(params = "action=dettaglio")
	public void mostraStatoPratica(@RequestParam(required = true) String id, @RequestParam(required = true) String codiceIstat, @RequestParam(required = true) String mostraFascicoloAction,
			Model model, ActionResponse response, PortletSession session, PortletRequest portletRequest) {

		log.debug("mostraStatoPratica :: entering method :: idProto : " + id);

		ComuneISA comuneErogatore = comuneISAService.getComuneByCodiceIstat(codiceIstat);
		try {
			// invoco il servizio che recupera il dettaglio della pratica protocollata
			DettaglioProtocolloResponse risposta = senderHelper.richiestaDettaglioProtocollo(id, comuneErogatore);
			Errore errore = risposta.getErrore();
			if (errore != null) {
				model.addAttribute("formError", messageSource.getMessage("exception.notAvailable.message", null, portletRequest.getLocale()) + " "
						+ messageSource.getMessage("exception.contactAdmin", null, portletRequest.getLocale()));
				log.error("mostraStatoPratica :: errore " + errore.getCodice() + " : descrizione errore : " + errore.getDescrizione());
				return;
			}
			ProtocolloResponse protocolloResponse = risposta.getProtocolloResponse();
			Date dataProtocollazione = protocolloResponse.getDataProtocollo().getTime();

			model.addAttribute("dettaglioProtocollo", risposta);
			model.addAttribute("dataProtocollazione", dataProtocollazione);
			model.addAttribute("comuneErogatore", comuneErogatore);

			response.setRenderParameter("action", "renderDettaglioProtocollazione");
		}
		catch (Exception e) {
			log.error("mostraStatoPratica :: ERRORE: " + e.getMessage(), e);
			response.setRenderParameter("action", "renderDettaglioProtocollazione");
		}

		model.addAttribute("mostraFascicoloAction", mostraFascicoloAction);

	}

	/**
	 * Effettua l'upload del documento.
	 *
	 * @param model
	 * @return l'ID della view
	 */
	@ResourceMapping("downloadDocumento")
	public void downloadDocumento(@RequestParam(required = true) String id, @RequestParam(required = true) String codiceIstat, Model model, ResourceRequest request, ResourceResponse response) {

		ComuneISA comuneErogatore = comuneISAService.getComuneByCodiceIstat(codiceIstat);

		if (!comuneErogatore.getDownloadAllegati()) {
			return;
		}

		File file = null;
		try {
			DettaglioAllegatoResponse dettaglioAllegatoResponse = senderHelper.richiestaDettaglioAllegato(id, comuneErogatore);
			Errore errore = dettaglioAllegatoResponse.getErrore();
			if (errore != null) {
				model.addAttribute("formError",
						messageSource.getMessage("exception.notAvailable.message", null, request.getLocale()) + " " + messageSource.getMessage("exception.contactAdmin", null, request.getLocale()));
				log.error("downloadDocumento :: errore " + errore.getCodice() + " : descrizione errore : " + errore.getDescrizione());
				return;
			}
			if (dettaglioAllegatoResponse != null) {
				Allegato allegato = dettaglioAllegatoResponse.getAllegato();
				if (allegato != null) {
					Documento documento = allegato.getDocumento();
					if (documento != null && documento.getNomeFile() != null && documento.getContenuto() != null && documento.getContenuto().length > 0) {

						String nomeFile = documento.getNomeFile();
						file = File.createTempFile(nomeFile, nomeFile.substring(nomeFile.lastIndexOf(".")));
						file.deleteOnExit();
						String contentType = URLConnection.guessContentTypeFromName(nomeFile);

						helper.sendStreamAsAttachment(documento.getContenuto(), response, nomeFile, contentType);
					}
				}
			}
		}
		catch (CommunicationException e) {
			log.error("downloadDocumento :: " + e.getMessage(), e);
		}
		catch (IOException e) {
			log.error("downloadDocumento :: " + e.getMessage(), e);
		}
	}

	/**
	 * Stampa il report PDF degli elementi presenti in pagina.
	 *
	 * @param fascicoloAziendaReportForm
	 * @param idAzienda
	 * @param model
	 * @param response
	 * @param session
	 * @param request
	 * @throws Exception
	 */
	@ResourceMapping("downloadReportPDF")
	public void downloadReportPDF(@ModelAttribute("fascicoloAziendaReportForm") FascicoloAziendaForm fascicoloAziendaReportForm, @RequestParam Long idAzienda, Model model, ResourceResponse response,
			PortletSession session, ResourceRequest request) throws Exception {

		OSApulieUserDetails osApulieUserDetails = helper.getOsApulieUserDetails();
		Azienda aziendaAttuale = aziendaService.getAziendaByPk(idAzienda);

		List<ReportAziendaForm> beans = getReportAzienda(aziendaAttuale, osApulieUserDetails, fascicoloAziendaReportForm);

		List<Object> emptyBeans = new ArrayList<Object>();
		emptyBeans.add(new Object());

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("comune", helper.getUserPreferences(request).getNomeComune());
		param.put("reports", beans);
		param.put("azienda", aziendaAttuale.getRagioneSociale());

		String report_path = REPORTS_AZIENDA_JRXML;
		byte[] reportBytes = reportService.jrxmlToPdf(param, emptyBeans, helper.getUserPreferences(request).getIdComuneIsa(), report_path, null);

		// download del pdf
		String reportFileName = String.format(REPORT_PDF_FILE_NAME);
		helper.sendStreamAsAttachment(reportBytes, response, reportFileName, ContentMimeTypes.PDF);
	}

	/**
	 * Stampa la ricevuta dell'operazione effettuata dall'operatore azienda.
	 *
	 * @param idRichiesta
	 * @param model
	 * @param response
	 * @param session
	 * @param request
	 * @throws Exception
	 */
	@ResourceMapping("downloadRicevutaPDF")
	public void downloadRicevutaPDF(@RequestParam Long idRichiesta, Model model, ResourceResponse response, PortletSession session, ResourceRequest request) throws Exception {

		try {
			RichiestaServizio richiestaServizio = richiestaServizioService.getRichiestaServizio(idRichiesta);
			setInfoAggiuntive(richiestaServizio);

			RicevutaForm ricevuta = new RicevutaForm();
			if (richiestaServizio != null) {
				ProfiloUtenteCittadinoAzienda delegato = richiestaServizio.getDelegato();
				Azienda azienda = delegato.getAzienda();
				ricevuta.setAzienda(azienda.getRagioneSociale());
				ricevuta.setComune(richiestaServizio.getComuneErogatore().getNome());
				ricevuta.setDataOperazione(richiestaServizio.getDataRichiesta());
				Map<String, String> infoAggiuntiveMap = richiestaServizio.getInfoAggiuntiveMap();
				if (infoAggiuntiveMap != null) {
					ricevuta.setIdPratica(infoAggiuntiveMap.get("ID Pratica"));
				}
				ricevuta.setNomeServizio(richiestaServizio.getServizio().getNomeServizio());
				ricevuta.setNumeroProtocollo(richiestaServizio.getNumeroProtocollo());

				ProfiloUtenteCittadino delegatoAzienda = delegato.getProfiloUtenteCittadino();
				String operatore = delegatoAzienda.getCognome() + " " + delegatoAzienda.getNome();

				ricevuta.setOperatore(operatore);

				ProfiloUtenteCittadino cittadino = richiestaServizio.getFascicolo().getCittadino();
				String utente = cittadino.getCognome() + " " + cittadino.getNome() + ", C.F.: " + cittadino.getCodiceFiscale();
				ricevuta.setTipoAzienda(azienda.getTipo());
				ricevuta.setUtente(utente);
			}

			Map<String, Object> param = new HashMap<String, Object>();
			param.put("comune", helper.getUserPreferences(request).getNomeComune());

			List<RicevutaForm> beans = new ArrayList<RicevutaForm>();
			beans.add(ricevuta);

			String report_path = REPORTS_RICEVUTA_JRXML;
			byte[] reportBytes = reportService.jrxmlToPdf(param, beans, helper.getUserPreferences(request).getIdComuneIsa(), report_path, null);

			// download del pdf
			String reportFileName = REPORT_PDF_RICEVUTA_FILE_NAME_PREFIX + richiestaServizio.getFascicolo().getCittadino().getCodiceFiscale() + REPORT_PDF_RICEVUTA_FILE_NAME_SUFFIX;
			helper.sendStreamAsAttachment(reportBytes, response, reportFileName, ContentMimeTypes.PDF);
		}
		catch (Exception e) {
			log.error("downloadRicevutaPDF :: " + e.getMessage(), e);
		}
	}

	/**
	 * @param aziendaAttuale
	 * @param osApulieUserDetails
	 * @param fascicoloAziendaForm
	 * @return
	 */
	private List<ReportAziendaForm> getReportAzienda(Azienda aziendaAttuale, OSApulieUserDetails osApulieUserDetails, FascicoloAziendaForm fascicoloAziendaForm) {

		List<ReportAziendaForm> result = null;

		List<RichiestaServizio> richiesteServiziByAzienda = getRichiesteServizioAzienda(aziendaAttuale, osApulieUserDetails, fascicoloAziendaForm);
		if (richiesteServiziByAzienda != null) {
			result = new ArrayList<ReportAziendaForm>();
			for (RichiestaServizio richiestaServizio : richiesteServiziByAzienda) {
				ReportAziendaForm reportAziendaForm = new ReportAziendaForm();
				reportAziendaForm.setCittadino(richiestaServizio.getFascicolo().getCittadino().getCodiceFiscale());
				reportAziendaForm.setComune(richiestaServizio.getComuneErogatore().getNome());
				reportAziendaForm.setDataRichiesta(richiestaServizio.getDataRichiesta());
				reportAziendaForm.setId(richiestaServizio.getId());
				setInfoAggiuntive(richiestaServizio);
				Map<String, String> infoAggiuntiveMap = richiestaServizio.getInfoAggiuntiveMap();
				if (infoAggiuntiveMap != null) {
					StringBuilder stringBuilder = new StringBuilder();
					for (Map.Entry<String, String> entry : infoAggiuntiveMap.entrySet()) {
						stringBuilder.append(entry.getKey());
						stringBuilder.append(": ");
						stringBuilder.append("'");
						stringBuilder.append(entry.getValue());
						stringBuilder.append("'");
						stringBuilder.append(". ");
					}
					reportAziendaForm.setInfoAggiuntive(stringBuilder.toString());
				}
				reportAziendaForm.setNumeroProtocollo(richiestaServizio.getNumeroProtocollo());
				reportAziendaForm.setOperatore(richiestaServizio.getDelegato().getProfiloUtenteCittadino().getCognome() + " " + richiestaServizio.getDelegato().getProfiloUtenteCittadino().getNome());
				reportAziendaForm.setServizio(richiestaServizio.getServizio().getNomeServizio());
				result.add(reportAziendaForm);
			}
		}

		return result;
	}

	/**
	 * @param richiesteServizi
	 */
	private void setInfoAggiuntiveMap(List<RichiestaServizio> richiesteServizi) {
		try {
			for (RichiestaServizio richiestaServizio : richiesteServizi) {
				setInfoAggiuntive(richiestaServizio);
			}
		}
		catch (Exception e) {
			log.error("setInfoAggiuntiveMap :: " + e.getMessage(), e);
		}
	}

	/**
	 * @param richiestaServizio
	 */
	private void setInfoAggiuntive(RichiestaServizio richiestaServizio) {
		String infoAggiuntive = richiestaServizio.getInfoAggiuntive();
		if (infoAggiuntive != null && !infoAggiuntive.isEmpty() && infoAggiuntive.contains("ID")) {
			Gson gson = new Gson();
			Type type = new TypeToken<Map<String, String>>() {
			}.getType();
			Map<String, String> infoAggiuntiveMap = gson.fromJson(infoAggiuntive, type);
			richiestaServizio.setInfoAggiuntiveMap(infoAggiuntiveMap);
		}
	}

	/**
	 * Presenta il {@link FascicoloUtente} a video.
	 *
	 * @param model
	 * @return l'ID della view per visualizzare il fascicolo cittadino
	 */
	@RenderMapping(params = "action=renderFascicoloCittadino")
	public String renderFascicoloCittadino(Model model) {
		log.debug("Model = " + model);
		return toLocalViewPath("fascicoloCittadino");
	}

	/**
	 * Presenta il {@link FascicoloUtente} a video.
	 *
	 * @param model
	 * @return l'ID della view per visualizzare il fascicolo aziende
	 */
	@RenderMapping(params = "action=renderFascicoloAzienda")
	public String renderFascicoloAzienda(Model model) {
		log.debug("Model = " + model);
		return toLocalViewPath("fascicoloAzienda");
	}

	/**
	 * Presenta il {@link FascicoloUtente} a video.
	 *
	 * @param model
	 * @return l'ID della view per visualizzare il fascicolo aziende
	 */
	@RenderMapping(params = "action=renderReportAziende")
	public String renderReportAziende(Model model) {
		log.debug("Model = " + model);
		return toLocalViewPath("reportAziende");
	}

	/**
	 * Presenta il dettaglio protocollazione a video.
	 *
	 * @param model
	 * @return l'ID della view
	 */
	@RenderMapping(params = "action=renderDettaglioProtocollazione")
	public String renderDettaglioProtocollazione(Model model) {
		log.debug("Model = " + model);
		return toLocalViewPath("dettaglioProtocollazione");
	}

	/**
	 * Utility method per calcolare e restituire il view ID all'interno della corretta alberatura di
	 * sottodirectory delle JSP: in un certo senso si tratta del namespace delle view locali a
	 * questo contraller. Ad esempio: <code> toLocalViewPath( "home" ) </code> restituirà <code>
	 * "/fascicoloutente/home" </code>
	 *
	 * @param viewName l'ID della view locale
	 * @return il view ID completo del path a cui la vista appartiene
	 */
	private static String toLocalViewPath(String viewName) {
		return "fascicoloutente/" + viewName;
	}
}
