/************************************************************************************
 * Copyright (c) 2011, 2016 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.servizicomune.web.portlet.monitoraggio;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.inject.Inject;
import javax.portlet.ActionRequest;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import eng.tz.la.core.AuditManager;
import eng.tz.la.core.Export;
import eng.tz.la.model.OperationExportFile;
import it.osapulie.domain.Audit;
import it.osapulie.domain.Azienda;
import it.osapulie.domain.Comune;
import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.Indirizzo;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.ProfiloUtenteCittadinoAzienda;
import it.osapulie.domain.fascicoloutente.RichiestaServizio;
import it.osapulie.domain.servizi.Tipologia;
import it.osapulie.enumeration.TipoAzienda;
import it.osapulie.persistence.AuditRepository;
import it.osapulie.service.AziendaService;
import it.osapulie.service.CommonService;
import it.osapulie.service.ComuneISAService;
import it.osapulie.service.ComuneService;
import it.osapulie.service.ProfiloUtenteService;
import it.osapulie.service.RichiestaServizioService;
import it.osapulie.service.TipologiaService;
import it.osapulie.servizicomune.web.portlet.BaseController;
import it.osapulie.servizicomune.web.portlet.gestioneaziende.form.AziendaEditForm;
import it.osapulie.servizicomune.web.portlet.gestioneaziende.form.AziendeSearchForm;
import it.osapulie.servizicomune.web.portlet.gestioneaziende.validator.AziendaValidator;
import it.osapulie.util.audit.ApplicationContextUtil;
import it.osapulie.util.audit.AuditConfiguration;
import it.osapulie.util.audit.Origin;
import it.osapulie.web.portlet.util.CommunicationException;
import it.osapulie.web.portlet.util.PortletHelper;
import it.osapulie.web.portlet.util.PortletUtils;
import it.osapulie.web.portlet.util.RoleConstants;
import it.osapulie.web.portlet.util.SenderHelper;

/**
 * Portlet controller per la gestione delle richieste di inserimento aziende.
 *
 * @author Gianluca Pindinelli
 */
@Controller("monitoraggioPortletController")
@RequestMapping("view")

public class MonitoraggioLogPortletController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(MonitoraggioLogPortletController.class);

	@Autowired
	private SenderHelper senderHelper;

	@Autowired
	public ResourceBundleMessageSource messageSource;

	@Autowired
	private AziendaService aziendaService;

	@Autowired
	private ProfiloUtenteService profiloUtenteService;

	@Autowired
	private ComuneService comuneService;
	
	@Autowired
	private ComuneISAService comuneIsaService;
	
	@Autowired
	private TipologiaService tipologiaService;

	@Inject
	private PortletHelper helper;

	@Autowired
	private CommonService commonService;
	
	@Autowired
	private RichiestaServizioService richiestaServizioService;

	
	@Inject
	private AuditRepository auditRepository;

	/**
	 * Default entry quando la portlet viene visualizzata per la prima volta.
	 *
	 * @param model il view model da popolare
	 * @return il view ID da renderizzare
	 */
	@RenderMapping
	public String homePage() {
		return toLocalViewPath("home");
	}

	@ModelAttribute("monitorSearchForm")
	public MonitorSearchForm getMonitorSearchForm() {
		return new MonitorSearchForm();
	}

	/**
	 * Recupero lista aziende 
	 * @param aziendeSearchForm
	 * @return
	 * @throws ParseException 
	 */
	@ModelAttribute("richieste")
	public List<RichiestaServizio> getRichiesteList(@ModelAttribute MonitorSearchForm monitorSearchForm) throws ParseException {
		List<RichiestaServizio> results = null;
		Long comune = null;
		Long intermediario = null;
		Long tipologia = null;
		String codiceFiscaleResponsabile = null;
		Date da = null;
		Date a = null;
		
		if (monitorSearchForm != null) {
			codiceFiscaleResponsabile = monitorSearchForm.getCodiceFiscaleResponsabile();
			comune = monitorSearchForm.getComune();
			intermediario = monitorSearchForm.getIntermediario();
			tipologia = monitorSearchForm.getTipologia();
			if(monitorSearchForm.getDataRichiestaDa() != null && !monitorSearchForm.getDataRichiestaDa().equals(""))
				da = new SimpleDateFormat("yyyy-MM-dd").parse(monitorSearchForm.getDataRichiestaDa());
			if(monitorSearchForm.getDataRichiestaA() != null && !monitorSearchForm.getDataRichiestaA().equals(""))
				a = new SimpleDateFormat("yyyy-MM-dd").parse(monitorSearchForm.getDataRichiestaA());
			
			if((codiceFiscaleResponsabile == null || codiceFiscaleResponsabile.equals(""))  && comune == null && intermediario == null && tipologia == null && da == null && a == null)
				results = null;
			else
				results = richiestaServizioService.searchRichiesteServizioByFilters(comune, codiceFiscaleResponsabile, tipologia, intermediario, da, a);
			
		}

		
		return results;

	}
	
	/**
	 * Recupero lista aziende 
	 * @param aziendeSearchForm
	 * @return
	 */
	@ModelAttribute("aziende")
	public List<Azienda> getAziendeList() {
		try {
			return aziendaService.getAziendeAttive();
		}
		catch (Exception e) {
			log.error("getAzieneList :: " + e.getMessage(), e);
		}
		return null;

	}


	@ModelAttribute(value = "comuni")
	public List<ComuneISA> getComuneList() {

		try {
			return comuneIsaService.getAllComuni();
		}
		catch (Exception e) {
			log.error("getComuneList :: " + e.getMessage(), e);
		}
		return null;
	}
	
	@ModelAttribute(value = "tipologie")
	public List<Tipologia> getTipologieList() {

		try {
			return tipologiaService.getAllTipologia();
		}
		catch (Exception e) {
			log.error("getTipologieList :: " + e.getMessage(), e);
		}
		return null;
	}


	

	/**
	 * Utility method per calcolare e restituire il view ID all'interno della corretta alberatura di
	 * sottodirectory delle JSP: in un certo senso si tratta del namespace delle view locali a
	 * questo controller. Ad esempio: <code> toLocalViewPath( "home" ) </code> restituirà <code>
	 * "/monitoraggio/home" </code>
	 *
	 * @param viewName l'ID della view locale
	 * @return il view ID completo del path a cui la vista appartiene
	 */
	private static String toLocalViewPath(String viewName) {
		return "monitoraggio/" + viewName;
	}
}
