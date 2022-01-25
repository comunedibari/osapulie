/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.tributi.web.portlet.agevolazionetari.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import org.springframework.web.portlet.handler.PortletSessionRequiredException;

import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici.ComponentiNucleoFamiliare;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagraficiGenerali;
import it.osapulie.anagrafe.web.ws.output.types.RichiestaDatiAnagrafici;
import it.osapulie.domain.Azienda;
import it.osapulie.domain.Comune;
import it.osapulie.domain.ComuneEstero;
import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.Delega;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.Provincia;
import it.osapulie.domain.StatoEstero;
import it.osapulie.domain.dto.Fascicolo;
import it.osapulie.domain.servizi.Servizio;
import it.osapulie.infrastructure.ContentMimeTypes;
import it.osapulie.infrastructure.ReportService;
import it.osapulie.infrastructure.XMLHelper;
import it.osapulie.infrastructure.impl.DateUtils;
import it.osapulie.infrastructure.security.OSApulieUserDetails;
import it.osapulie.service.AziendaService;
import it.osapulie.service.CommonService;
import it.osapulie.service.ComuneEsteroService;
import it.osapulie.service.ComuneISAService;
import it.osapulie.service.ComuneService;
import it.osapulie.service.ConfigurazioneService;
import it.osapulie.service.DelegaService;
import it.osapulie.service.DwhService;
import it.osapulie.service.FascicoloUtenteService;
import it.osapulie.service.ProvinciaService;
import it.osapulie.service.ServiceLayerException;
import it.osapulie.service.ServizioService;
import it.osapulie.service.StatoEsteroService;
import it.osapulie.service.UploadDichiarazioniService;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.tributi.service.AuditDwhService;
import it.osapulie.tributi.service.TributiCommonService;
import it.osapulie.tributi.service.TributiService;
import it.osapulie.tributi.service.impl.AuditDwhServiceImpl;
import it.osapulie.tributi.web.portlet.agevolazionetari.form.DatiAgevolazioneTari;
import it.osapulie.tributi.web.portlet.agevolazionetari.form.DatiImmobileAgevolazioneTari;
import it.osapulie.tributi.web.portlet.dichiarazionetari.form.Valore;
import it.osapulie.tributi.web.portlet.utils.DatiTassaRifiutiComparator;
import it.osapulie.tributi.web.portlet.utils.PortletConstants;
import it.osapulie.tributi.web.portlet.utils.PortletUtils;
import it.osapulie.tributi.web.ws.input.types.Agevolazione;
import it.osapulie.tributi.web.ws.input.types.CAF;
import it.osapulie.tributi.web.ws.input.types.Codifica;
import it.osapulie.tributi.web.ws.input.types.Contribuente;
import it.osapulie.tributi.web.ws.input.types.Dichiarante;
import it.osapulie.tributi.web.ws.input.types.Dichiarazione;
import it.osapulie.tributi.web.ws.input.types.DichiarazioneTassaRifiutiInputRichiesta;
import it.osapulie.tributi.web.ws.input.types.DichiarazioneTassaRifiutiInputRisposta;
import it.osapulie.tributi.web.ws.input.types.DichiarazioneTassaRifiutiInputRisposta.Errore;
import it.osapulie.tributi.web.ws.input.types.Documento;
import it.osapulie.tributi.web.ws.input.types.ModelloISEE;
import it.osapulie.tributi.web.ws.input.types.ModelloISEE.Patrimonio;
import it.osapulie.tributi.web.ws.input.types.NucleoFamiliare;
import it.osapulie.tributi.web.ws.input.types.PersonaFisica;
import it.osapulie.tributi.web.ws.input.types.Tracciamento;
import it.osapulie.tributi.web.ws.input.types.Utenza;
import it.osapulie.tributi.web.ws.input.types.UtenzaDomestica;
import it.osapulie.tributi.web.ws.output.types.DatiTassaRifiuti;
import it.osapulie.tributi.web.ws.output.types.DatiTassaRifiuti.Posizioni;
import it.osapulie.tributi.web.ws.output.types.DichiarazioneTassaRifiutiRichiesta;
import it.osapulie.tributi.web.ws.output.types.DichiarazioneTassaRifiutiRisposta;
import it.osapulie.tributi.web.ws.output.types.Indirizzo;
import it.osapulie.tributi.web.ws.output.types.Indirizzo.Civico;
import it.osapulie.util.DwhDataminingUtil;
import it.osapulie.util.DwhServiceAttributeUtil;
import it.osapulie.util.DwhTempiMediUtil;
import it.osapulie.util.IUpdateAttribute;
import it.osapulie.util.audit.ApplicationContextUtil;
import it.osapulie.util.audit.AuditConfiguration;
import it.osapulie.util.audit.Origin;
import it.osapulie.util.dto.DwhDataminingDTO;
import it.osapulie.util.dto.DwhServizioAttributeDTO;
import it.osapulie.util.dto.DwhTempiMediDTO;
import it.osapulie.web.portlet.controller.BaseController;
import it.osapulie.web.portlet.util.CheckSumGenerator;
import it.osapulie.web.portlet.util.FileUploadValidator;
import it.osapulie.web.portlet.util.UploadItem;
import it.osapulie.web.portlet.util.impl.LiferayPortletHelperImpl;

/**
 * Portlet controller per il servizio Agevolazione TARI.
 *
 * @author Gianluca Pindinelli
 * @author Andrea Filomena
 */
@Controller("agevolazioneTariPortletController")
@RequestMapping("view")
@SessionAttributes({ "datiAgevolazioneTari", "param", "uploadItem" })
public class AgevolazioneTariPortletController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(AgevolazioneTariPortletController.class);

	private static final String SERVIZIO = "AGEVOLAZIONE TARI";

	public static final String REPORT_PATH = "/reports/agevolazioneTari.jrxml";
	private static final String SUB_REPORT_PATH_0 = "/reports/subreportAgevolazioneTari0.jrxml";
	private static final String SUB_REPORT_PATH_1 = "/reports/subreportAgevolazioneTari.jrxml";
	private static final String SUB_REPORT_PATH_2 = "/reports/subreportAgevolazioneTari2.jrxml";
	private static final String SUB_REPORT_PATH_3 = "/reports/subreportAgevolazioneTari3.jrxml";
	private static final String SUB_REPORT_PATH_4 = "/reports/subreportAgevolazioneTari4.jrxml";

	public static final String REPORT_PREFIX_NAME = "Agevolazione_TARI";
	public static final String REPORT_SUFFIX_NAME = ".pdf";

	public static final String JSP_PATH = "agevolazionetari/";

	private static final int NUMERO_IMMOBILI_FORM = 5;
	private static final String PERSONA_FISICA = "fisica";
	private static final String PERSONA_GIURIDICA = "giuridica";
	private static final String UTENZA_NON_DOMESTICA = "non_domestica";
	private static final String UTENZA_DOMESTICA = "domestica";
	private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");

	@Inject
	private LiferayPortletHelperImpl helper;

	@Inject
	private XMLHelper xmlHelper;

	@Inject
	private FascicoloUtenteService fascicoloService;

	@Inject
	private ReportService reportService;

	@Inject
	private UploadDichiarazioniService uploadService;

	@Inject
	@Named("fileUploadValidator")
	private FileUploadValidator validator;

	@Inject
	@Named("datiAgevolazioneTariValidator")
	private Validator agevolazioneValidator;

	@Inject
	private TributiService tributiService;

	@Inject
	private TributiCommonService tributiCommonService;

	@Inject
	private CommonService commonService;

	@Inject
	private ConfigurazioneService configurazioneService;

	@Autowired
	public ResourceBundleMessageSource messageSource;

	@Inject
	private ComuneService comuneService;

	@Inject
	private ProvinciaService provinciaService;

	@Inject
	private StatoEsteroService statoEsteroService;

	@Inject
	private ComuneEsteroService comuneEsteroService;

	@Inject
	private AziendaService aziendaService;

	@Inject
	private ComuneISAService comuneISAService;

	@Inject
	private ServizioService servizioService;

	@Inject
	private DelegaService delegaService;
 
	@Inject
	private DwhService dwhService;
	
	@Inject
	private AuditDwhService auditDwhService;
	
	
	

	/**
	 * Default entry quando la portlet viene visualizzata per la prima volta.
	 *
	 * @param model il view model da popolare
	 * @return il view ID da renderizzare.
	 */
	@RenderMapping
	public String homePage(Model model) throws Exception {
		return toLocalViewPath("home");
	}

	@ModelAttribute("bozza")
	public DatiAgevolazioneTari getBozza(PortletRequest request) {

		UserPreferences userPreferences = helper.getUserPreferences(request);
		Servizio servizio = servizioService.getServizioByCodiceServizio(PortletConstants.CODICE_SERVIZIO_AGEVOLAZIONE_TARI);
		DatiAgevolazioneTari bozza = commonService.getBozza(userPreferences, servizio.getId(), DatiAgevolazioneTari.class);

		setInfoAggiuntiveForm(bozza);

		return bozza;
	}

	@ActionMapping(params = "action=getBozzaFrom")
	public void getBozzaFrom(@ModelAttribute("bozza") DatiAgevolazioneTari bozza, Model model, ActionResponse response, ActionRequest request) throws Exception {
		model.addAttribute("datiAgevolazioneTari", bozza);
		response.setRenderParameter("action", "renderAgevolazioneTariForm");
	}

	/**
	 * Presenta la form per la dichiarazione tari.
	 *
	 * @param model
	 * @throws Exception
	 */
	@ActionMapping(params = "action=getAgevolazioneTariForm")
	public void getAgevolazioneTariForm(@ModelAttribute("bozza") DatiAgevolazioneTari bozza, Model model, ActionResponse response, ActionRequest request,PortletSession session) throws Exception {

		log.debug("getAgevolazioniTariForm");

		UserPreferences userPreferences = helper.getUserPreferences(request);

		String codiceFiscale = userPreferences.getCodiceFiscaleServizio();
		String partitaIvaServizio = userPreferences.getPartitaIvaServizio();

		final String uuidOperazione=UUID.randomUUID().toString();
		session.setAttribute("UUID", uuidOperazione);
		AuditConfiguration
					.configure()
					.audit()
					.addInizioOperazione()
					.addUuidOperazione(uuidOperazione)
					.addOperazioneRichiesta(SERVIZIO+"  - START")
					.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
					.addPaginaCorrente(helper.getCurrentPageName(request))
					.addEsitoSuccess("SUCCESS")
					.addServizioTimeString()
					.addServizioUuidTransazione(uuidOperazione)
					.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_AGEVOLAZIONE_TARI)
					.setOrigin(Origin.getIp(request))
					.addCallGeo().build();
 
		DwhServizioAttributeDTO servizioAttributeDto = new DwhServizioAttributeDTO();
		DwhDataminingDTO dataminingDto = new DwhDataminingDTO();
		DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();

		servizioAttributeDto = DwhServiceAttributeUtil.get(commonService, comuneService, delegaService, servizioService, userPreferences, request, dwhService)
		.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_AGEVOLAZIONE_TARI)
		.setUUID(uuidOperazione)
		.getServizioAttribute();	
		
		dataminingDto = DwhDataminingUtil.get(codiceFiscale, dwhService)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_AGEVOLAZIONE_TARI)
		.setUuidOperazione(uuidOperazione)
		.getDatamining();
		
		 tempiMediDto = DwhTempiMediUtil.get(dwhService)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_AGEVOLAZIONE_TARI)
		.setNomeServizio(SERVIZIO)
		.setUuid(uuidOperazione).go_StartTime().getTempiMedi();	
		
		auditDwhService.invokeAuditService(servizioAttributeDto, dataminingDto, tempiMediDto);
		
		RichiestaDatiAnagrafici richiesta = new RichiestaDatiAnagrafici();
		richiesta.setCodiceFiscale(codiceFiscale);

		try {
			ComponentiNucleoFamiliare componente = null;
			DatiAnagrafici datiAnagrafici = null;
			try {
				datiAnagrafici = tributiService.richiediDatiAnagrafici(richiesta, userPreferences);

				DatiAnagraficiGenerali richiestaGen = new DatiAnagraficiGenerali();
				richiestaGen.setCodiceFiscale(codiceFiscale);
				componente = null;
				for (int k = 0; k < datiAnagrafici.getComponentiNucleoFamiliare().size(); k++) {
					if (codiceFiscale.equals(datiAnagrafici.getComponentiNucleoFamiliare().get(k).getCodiceFiscale())) {
						componente = datiAnagrafici.getComponentiNucleoFamiliare().get(k);
					}
				}
			}
			catch (Exception e) {
				log.warn("getDichiarazioneTariForm :: " + e.getMessage());
			}

			boolean isStradarioEnabled = configurazioneService.isStradarioEnable(PortletConstants.CODICE_SERVIZIO_AGEVOLAZIONE_TARI, userPreferences.getIdComuneIsa());

			DatiAgevolazioneTari datiTari = new DatiAgevolazioneTari();
			datiTari.setUuidOperazione(uuidOperazione);
			
			// Precarico i dati dell'utente nel form
			String codiceIstatComune = userPreferences.getCodiceIstatComune();
			Comune comuneAttuale = comuneService.getComuneByCodiceISTAT(codiceIstatComune);
			if (componente != null && datiAnagrafici != null) {

				// Comune di iscrizione è il comune ISA attuale (se non specificato dopo)
				datiTari.setComuneResidenza(comuneAttuale.getCodiceIstatAN());
				datiTari.setProvinciaResidenza(comuneAttuale.getProvincia() != null ? comuneAttuale.getProvincia().getSigla() : null);

				datiTari.setCodiceFiscale(componente.getCodiceFiscale());
				datiTari.setNome(componente.getNome());
				datiTari.setCognome(componente.getCognome());
				datiTari.setDataNascita(DateUtils.getDataGGMMAAAA(componente.getDataNascita().getTime()));
				// Caricamento comune da codice ISTAT
				if (componente.isCittadinanzaItaliana() && componente.getCodiceIstatComuneNascita() != null) {
					Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(componente.getCodiceIstatComuneNascita());
					if (comuneByCodiceISTAT != null) {
						datiTari.setComuneNascita(String.valueOf(comuneByCodiceISTAT.getCodiceIstatAN()));
						datiTari.setProvinciaNascita(comuneByCodiceISTAT.getProvincia().getSigla());
					}
				}
				boolean isIndirizzoResidenzaConStradario = checkIndirizzoResidenzaConStradario(userPreferences, datiTari);
				// Se lo stradario è abilitato x il comune --> non setto i dati nello stradario (non
				// avendo i codici nella visura)
				if (!isIndirizzoResidenzaConStradario) {
					datiTari.setIndirizzoResidenza(datiAnagrafici.getDescrizioneVia());
					datiTari.setCivicoResidenza(datiAnagrafici.getNumeroCivico());
					datiTari.setEsponenteResidenza(datiAnagrafici.getEsponente());
				}

			}
			// Caricamento dati da utente in OSApulie
			else {
				ProfiloUtenteCittadino profiloUtente = commonService.getCurrentProfiloUtenteCittadino(userPreferences);
				datiTari.setCodiceFiscale(codiceFiscale);
				datiTari.setNome(profiloUtente.getNome());
				datiTari.setCognome(profiloUtente.getCognome());
				ComuneISA comuneIsa = profiloUtente.getComuneIsa();
				if (comuneIsa != null) {
					Comune comune = comuneIsa.getComune();
					if (comune != null) {
						datiTari.setComuneNascita(String.valueOf(comune.getCodiceIstatAN()));
						datiTari.setProvinciaNascita(comune.getProvincia().getSigla());
					}
				}
				it.osapulie.domain.Indirizzo residenza = profiloUtente.getResidenza();
				if (residenza != null) {
					Comune comune = residenza.getComune();
					if (comune != null && !comune.getId().equals(-1L)) {
						datiTari.setComuneResidenza(String.valueOf(comune.getCodiceIstatAN()));
						Provincia provincia = comune.getProvincia();
						if (provincia != null) {
							datiTari.setProvinciaResidenza(provincia.getSigla());
						}
					}
					boolean isIndirizzoResidenzaConStradario = checkIndirizzoResidenzaConStradario(userPreferences, datiTari);
					// Se lo stradario è abilitato x il comune --> non setto i dati nello stradario
					// (non avendo i codici nella visura)
					if (!isIndirizzoResidenzaConStradario) {
						datiTari.setIndirizzoResidenza(residenza.getVia());
					}
				}
			}

			// Caricamento dati CAF
			boolean isServizioEnableForCAF = configurazioneService.isServizioEnableForCAF(PortletConstants.CODICE_SERVIZIO_AGEVOLAZIONE_TARI, userPreferences.getIdComuneIsa());
			if (isServizioEnableForCAF) {
				Long idDelega = userPreferences.getIdDelega();
				if (idDelega != null) {
					Delega delega = delegaService.getDelegaByPk(idDelega);
					if (delega != null) {
						Azienda delegato = delega.getDelegato();
						if (delegato != null) {
							datiTari.setNominativoCAF(delegato.getRagioneSociale());
							datiTari.setPartitaIvaCAF(delegato.getPartitaIva());
							datiTari.setPecCAF(delegato.getMailPec());
						}
					}
				}
			}

			boolean visuraPosizioniTributarieActive = false;
			ComuneISA comuneByPk = comuneISAService.getComuneByPk(userPreferences.getIdComuneIsa());
			if (comuneByPk != null) {
				visuraPosizioniTributarieActive = comuneByPk.getVisuraPosizioniTributarieActive();
			}

			checkIndirizzoResidenzaConStradario(userPreferences, datiTari);

			List<DatiTassaRifiuti> situazioni = new ArrayList<DatiTassaRifiuti>();
			if (visuraPosizioniTributarieActive) {

				// Carico i dati catastali degli immobili associati al cittadino
				Integer annoCorrente = new Integer(DateUtils.getAnnoCorrente());

				DichiarazioneTassaRifiutiRichiesta richiestaSituazioni = new DichiarazioneTassaRifiutiRichiesta();
				if (partitaIvaServizio != null) {
					richiestaSituazioni.setPartitaIva(partitaIvaServizio);
				}
				else {
					richiestaSituazioni.setCodiceFiscale(codiceFiscale);
				}
				// recupero i dati dell'anno precedente e di quello corrente; se non ci sono dati
				// per l'anno corrente utilizzo gli anni precedebti ed informo l'utente
				richiestaSituazioni.setAnnoInizio(annoCorrente - 5);
				richiestaSituazioni.setAnnoFine(annoCorrente);

				DichiarazioneTassaRifiutiRisposta rispostaSituazioni = tributiService.richiediSituazioneTassaRifiuti(richiestaSituazioni, userPreferences);
				situazioni = rispostaSituazioni.getSituazione();

				Collections.sort(situazioni, new DatiTassaRifiutiComparator());
			}
			datiTari.setStradarioEnabled(isStradarioEnabled);
			// FIXME esternalizzare la configurazione del numero max di visuare immobiliari. Per
			// Bari è stato impostato a 3
			int limiteImmobili = NUMERO_IMMOBILI_FORM;
			if (userPreferences.getIdComuneIsa() == 1) {
				limiteImmobili = 3;
			}

			List<DatiTassaRifiuti.Posizioni> posizioni = new ArrayList<DatiTassaRifiuti.Posizioni>();
			if (situazioni != null && !situazioni.isEmpty()) {
				// Prelevo l'ultimo anno
				DatiTassaRifiuti situazione = situazioni.get(situazioni.size() - 1);
				posizioni = situazione.getPosizioni();
			}

			boolean interoperabilitaEnable = isInteroperabilitaEnable(request);
			datiTari.setLimitatoIscrizione(false);
			if (interoperabilitaEnable) {
				// attiva solo l'iscrizione in caso di interoperabilità e
				// se l'idContribuente non è settato su nessuna posizione
				posizioni = filtraPosizioni(posizioni);
				datiTari.setLimitatoIscrizione(posizioni.isEmpty());
			}

			datiTari.setIdContribuente(getIdContribuenteFromPosizioni(posizioni));
			datiTari.setImmobili(generateList(userPreferences, posizioni, limiteImmobili, false));
			datiTari.setDataDomanda(DateUtils.getDataGGMMAAAA(Calendar.getInstance().getTime()));

			setInfoAggiuntiveForm(datiTari);

			model.addAttribute("datiAgevolazioneTari", datiTari);
		}
		catch (Exception e) {
			log.error("getAgevolazioneTariForm :: " + e.getMessage(), e);
		}

		response.setRenderParameter("action", "renderAgevolazioneTariForm");
	}

	/**
	 * Setta la proprietà utile a capire se il servizio è destinato a funzionare mediante
	 * interoperabilità con il backoffice comunale.
	 *
	 * @param request
	 * @return
	 */
	@ModelAttribute("isInteroperabilitaEnable")
	public boolean isInteroperabilitaEnable(PortletRequest request) {
		UserPreferences userPreferences = helper.getUserPreferences(request);
		boolean interoperabilitaEnable = configurazioneService.isInteroperabilitaEnable(PortletConstants.CODICE_SERVIZIO_AGEVOLAZIONE_TARI, userPreferences.getIdComuneIsa());
		return interoperabilitaEnable;
	}

	/**
	 * Restituisce l'idContribuente in prima posizione nella concatenazione
	 * idContribuente|idUtenza|dataVariazioneUtenza|dataInizioUtenza dell'identificativo utenza di
	 * una posizione
	 *
	 * @param posizioni
	 * @return int
	 */
	private String getIdContribuenteFromPosizioni(List<Posizioni> posizioni) {
		if (posizioni != null) {
			for (int i = 0; i < posizioni.size(); i++) {
				Posizioni posizione = posizioni.get(i);
				if (posizione.getIdentificativoUtenza() != null && !posizione.getIdentificativoUtenza().isEmpty()) {
					return posizione.getIdentificativoUtenza().split("\\|")[0];
				}
			}
		}
		return "";
	}

	/**
	 * Restituisce la lista delle posizioni con idContribuente valorizzato
	 *
	 * @param posizioni
	 * @return
	 */
	private List<DatiTassaRifiuti.Posizioni> filtraPosizioni(List<DatiTassaRifiuti.Posizioni> posizioni) {

		List<DatiTassaRifiuti.Posizioni> result = new ArrayList<DatiTassaRifiuti.Posizioni>();
		for (int i = 0; i < posizioni.size(); i++) {
			Posizioni posizione = posizioni.get(i);
			if (posizione.getIdentificativoUtenza() != null && !posizione.getIdentificativoUtenza().isEmpty()) {
				result.add(posizione);
			}
		}

		return result;
	}

	/**
	 * Verifica se il comune di residenza dell'utente coincide con il comune attualmente selezionato
	 * --> se si abilito lo stradario per la selezione dell'indirizzo di residenza
	 *
	 * @param userPreferences
	 * @param datiTari
	 * @return
	 */
	private boolean checkIndirizzoResidenzaConStradario(UserPreferences userPreferences, DatiAgevolazioneTari datiTari) {

		boolean isStradarioEnabled = configurazioneService.isStradarioEnable(PortletConstants.CODICE_SERVIZIO_AGEVOLAZIONE_TARI, userPreferences.getIdComuneIsa());
		if (isStradarioEnabled && datiTari.getComuneResidenza() != null && !datiTari.getComuneResidenza().isEmpty()) {
			ComuneISA comuneByPk = comuneISAService.getComuneByPk(userPreferences.getIdComuneIsa());
			Comune comuneResidenza = comuneService.getComuneByCodiceISTAT(datiTari.getComuneResidenza());
			if (comuneResidenza != null && comuneResidenza.getId().equals(comuneByPk.getComune().getId())) {
				datiTari.setIndirizzoResidenzaConStradario(true);
				return true;
			}
		}
		datiTari.setIndirizzoResidenzaConStradario(false);

		return false;
	}

	/**
	 * Restituisce la form per l'upload delle dichiarazioni e doc.
	 *
	 * @param model
	 * @param response
	 */
	@ActionMapping(params = "ope=getUploadForm")
	public void getUploadForm(Model model, ActionResponse response) {
		UploadItem item = new UploadItem();
		item.setSignedFiles(false);
		model.addAttribute("uploadItem", item);
		response.setRenderParameter("action", "renderUploadForm");
	}

	/**
	 * Metodo che prende i campi inseriti nella form e li mette in sessione
	 *
	 * @param datiAgevolazioneTari
	 * @param result
	 * @param model
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@ActionMapping(params = "ope=generaDichiarazione")
	public void compilaDichiarazione(@RequestParam(required = false) String bozza, @ModelAttribute("datiAgevolazioneTari") DatiAgevolazioneTari datiTari, BindingResult result, Model model,
			@RequestParam(required = false) String comuneResidenzaParam, ActionResponse response, ActionRequest request,PortletSession session) throws Exception {
		log.debug("compilaDichiarazione CF=" + datiTari.getCodiceFiscale());

		setInfoAggiuntiveForm(datiTari);

		UserPreferences userPreferences = helper.getUserPreferences(request);

		// Salvataggio bozza
		if (bozza != null) {
			Servizio servizio = servizioService.getServizioByCodiceServizio(PortletConstants.CODICE_SERVIZIO_AGEVOLAZIONE_TARI);
			if(datiTari.getUuidOperazione()==null || datiTari.getUuidOperazione().isEmpty())
			datiTari.setUuidOperazione((String)session.getAttribute("UUID"));
			commonService.saveBozza(datiTari, userPreferences, servizio.getId());
			setInfoAggiuntiveForm(datiTari);
			response.setRenderParameter("action", "renderAgevolazioneTariForm");
			model.addAttribute("annoCorrente", DateUtils.getAnnoCorrente());
			model.addAttribute("message", messageSource.getMessage("label.bozza.salvata", null, request.getLocale()));
			return;
		}

		boolean checkIndirizzoResidenzaConStradario = checkIndirizzoResidenzaConStradario(userPreferences, datiTari);
		if (comuneResidenzaParam != null && !comuneResidenzaParam.isEmpty()) {
			if (checkIndirizzoResidenzaConStradario) {
				// campi vuoti indirizzo residenza
				datiTari.setIndirizzoResidenza(null);
				datiTari.setIndirizzoResidenzaTextHidden(null);
				datiTari.setCivicoResidenza(null);
				datiTari.setCivicoResidenzaTextHidden(null);
				datiTari.setEsponenteResidenza(null);
			}

			response.setRenderParameter("action", "renderAgevolazioneTariForm");
			return;
		}

		agevolazioneValidator.validate(datiTari, result);
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				log.error("Error: " + error.getCode() + " - " + error.getDefaultMessage());
			}
			datiTari.setDichiarazioneCompletata(false);
			model.addAttribute("annoCorrente", DateUtils.getAnnoCorrente());
			model.addAttribute("formError", messageSource.getMessage("error.campiObbligatori.required", null, request.getLocale()));
			response.setRenderParameter("action", "renderAgevolazioneTariForm");
		}
		else {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("comune", userPreferences.getNomeComune());
			datiTari.setDichiarazioneCompletata(true);
			model.addAttribute("datiAgevolazioneTari", datiTari);
			model.addAttribute("param", param);
			model.addAttribute("download", "si");

			response.setRenderParameter("action", "renderAgevolazioneTariForm");
		}
	}

	/**
	 * Metodo che genera il certificato e ne consente il download.
	 *
	 * @param datiAgevolazioneTari
	 * @param param
	 * @param model
	 * @param response
	 * @param session
	 * @throws Exception
	 */
	@ResourceMapping("agevolazioneTariReport")
	public void serveCertificato(@ModelAttribute("datiAgevolazioneTari") DatiAgevolazioneTari datiTari, @ModelAttribute("param") Map<String, Object> param, Model model, ResourceResponse response,
			PortletSession session, ResourceRequest request) throws Exception {
		log.debug("agezolazioniTariReport ");

		setInfoAggiuntiveForm(datiTari);

		ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(session);

		// Query the service layer for some objects
		List<DatiAgevolazioneTari> beans = new ArrayList<DatiAgevolazioneTari>();

		UserPreferences userPreferences = helper.getUserPreferences(request);
		boolean checkIndirizzoResidenzaConStradario = checkIndirizzoResidenzaConStradario(userPreferences, datiTari);
		if (!checkIndirizzoResidenzaConStradario) {
			datiTari.setIndirizzoResidenzaTextHidden(datiTari.getIndirizzoResidenza());
			datiTari.setCivicoResidenzaTextHidden(datiTari.getCivicoResidenza());
		}

		beans.add(datiTari);

		param.put("subreportParameters", beans);

		Map<String, String> subreportJrxmlMap = new HashMap<String, String>();
		subreportJrxmlMap.put("subreportParameter0", SUB_REPORT_PATH_0);
		subreportJrxmlMap.put("subreportParameter1", SUB_REPORT_PATH_1);
		subreportJrxmlMap.put("subreportParameter2", SUB_REPORT_PATH_2);
		subreportJrxmlMap.put("subreportParameter3", SUB_REPORT_PATH_3);
		subreportJrxmlMap.put("subreportParameter4", SUB_REPORT_PATH_4);

		byte[] reportBytes = reportService.jrxmlToPdf(param, beans, helper.getUserPreferences(request).getIdComuneIsa(), PortletConstants.CODICE_SERVIZIO_AGEVOLAZIONE_TARI, REPORT_PATH,
				subreportJrxmlMap);

		String md5 = CheckSumGenerator.generateCheckSum(reportBytes);

		// memorizzo la richiesta
		Fascicolo fascicolo = new Fascicolo();
		fascicolo.setIdProfiloUtente(profiloUtente);
		fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio", null, request.getLocale()));
		fascicolo.setUserPreferences(helper.getUserPreferences(request));
		fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_AGEVOLAZIONE_TARI);
		fascicolo.setRicercabileDaComune(false);
		fascicolo.setNumeroProtocollo(null);
		fascicolo.setChecksum(md5);
		fascicoloService.saveNuovaRichiesta(fascicolo);

		String reportFileName = String.format(REPORT_PREFIX_NAME);
		reportFileName += "_" + userPreferences.getCodiceFiscaleServizio() + REPORT_SUFFIX_NAME;

		helper.sendStreamAsAttachment(reportBytes, response, reportFileName, ContentMimeTypes.PDF);
	}

	/**
	 * Gestisce l'upload dei file.
	 *
	 * @param uploadItem
	 * @param result
	 * @param response
	 * @param session
	 * @param request
	 * @param model
	 * @param selectNumAllegati
	 * @throws Exception
	 */
	@ActionMapping(params = "ope=upload")
	public void upload(@ModelAttribute("uploadItem") UploadItem uploadItem, BindingResult result, ActionResponse response, PortletSession session, ActionRequest request, Model model,
			@RequestParam(required = false, value = "selectNumAllegati") String selectNumAllegati, @ModelAttribute("datiAgevolazioneTari") DatiAgevolazioneTari datiTari) {

		log.debug("uploadFile");
		if (selectNumAllegati == null) {
			OSApulieUserDetails osApulieUserDetails = helper.getOsApulieUserDetails();
			UserPreferences userPreferences = helper.getUserPreferences(request);
			ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(session);
			Azienda aziendaByPiva = null;
			String partitaIvaServizio = userPreferences.getPartitaIvaServizio();
			if (partitaIvaServizio != null) {
				aziendaByPiva = aziendaService.getAziendaByPiva(partitaIvaServizio);
				validator.validate(uploadItem, result, aziendaByPiva, PortletConstants.CODICE_SERVIZIO_AGEVOLAZIONE_TARI);
			}
			else {
				validator.validate(uploadItem, result, profiloUtente, PortletConstants.CODICE_SERVIZIO_AGEVOLAZIONE_TARI);
			}

			if (result.hasErrors()) {
				for (ObjectError error : result.getAllErrors()) {
					log.error("Error: " + error.getCode() + " - " + error.getDefaultMessage());
				}
				model.addAttribute("selectNumAllegati", "");
				model.addAttribute("formError", messageSource.getMessage("error.verificareDatiInseriti", null, request.getLocale()));
				response.setRenderParameter("action", "renderUploadForm");
			}
			else {
				try {
					Fascicolo fascicolo = new Fascicolo();
					fascicolo.setIdProfiloUtente(profiloUtente);
					fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio1", null, request.getLocale()));
					fascicolo.setUserPreferences(helper.getUserPreferences(request));
					fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_AGEVOLAZIONE_TARI);
					fascicolo.setRicercabileDaComune(true);
					fascicolo.setNumeroProtocollo(null);
					fascicolo.setChecksum(null);

					String numeroProtocollo = null;
					boolean interoperabilitaEnable = isInteroperabilitaEnable(request);

					// Generazione xml per invio a PDD o per interoperabilità (ad indirizzo PEC
					// comune)
					DichiarazioneTassaRifiutiInputRichiesta dichiarazioneTassaRifiutiInputRichiesta = getDichiarazioneTassaRifiutiInputRichiesta(datiTari, osApulieUserDetails, userPreferences);

					// Allego documenti
					List<Documento> documenti = dichiarazioneTassaRifiutiInputRichiesta.getDocumento();

					List<Documento> documentiFromUploadItem = getDocumentiFromUploadItem(uploadItem);
					documenti.addAll(documentiFromUploadItem);

					if (interoperabilitaEnable) {

						DichiarazioneTassaRifiutiInputRisposta dichiarazioneTassaRifiutiInputRisposta = tributiService.inviaDichiarazione(dichiarazioneTassaRifiutiInputRichiesta, userPreferences);
						Errore errore = dichiarazioneTassaRifiutiInputRisposta.getErrore();
						if (errore != null) {
							throw new Exception("Codice di errore: " + errore.getCodice() + ", descrizione: " + errore.getDescrizione());
						}

						// Aggiunta info aggiuntive con ID pratica
						fascicolo.addInfoAggiuntive("ID Pratica", dichiarazioneTassaRifiutiInputRisposta.getIdPratica());
						Calendar dataInserimento = dichiarazioneTassaRifiutiInputRisposta.getDataInserimento();
						if (dataInserimento != null) {
							SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
							fascicolo.addInfoAggiuntive("Data Inserimento", simpleDateFormat.format(dataInserimento.getTime()));
						}
						numeroProtocollo = PortletUtils.getNumeroProtocollo(dichiarazioneTassaRifiutiInputRisposta.getNumeroProtocollo(),
								dichiarazioneTassaRifiutiInputRisposta.getDataProtocollo().getTime());
					}
					// ... altrimenti invio a PEC/Protocollo nel modo classico
					else {
						MultipartFile multipartFileForInterop = generateMultipartFileForInterop(dichiarazioneTassaRifiutiInputRichiesta);
						if (multipartFileForInterop != null) {
							uploadItem.getMultipartFiles().add(multipartFileForInterop);
						}
						numeroProtocollo = uploadService.processaUploadDichiarazioni(SERVIZIO, uploadItem, helper.getUserPreferences(request));
					}

					if (com.liferay.portal.kernel.util.Validator.isNotNull(numeroProtocollo)) {
						fascicolo.setNumeroProtocollo(numeroProtocollo);
					}

					fascicoloService.saveNuovaRichiesta(fascicolo);

					// Invio email a cittadino
					String subject = messageSource.getMessage("mail.cittadino.communicationSubject", new String[] { SERVIZIO }, new Locale("it"));

					try {
						tributiCommonService.sendMailToUser(userPreferences, subject, SERVIZIO, numeroProtocollo);
					}
					catch (Exception e) {
						log.error("upload :: " + e.getMessage(), e);
					}

					// Invio email ad azienda
					if (partitaIvaServizio != null) {
						String subjectAzienda = messageSource.getMessage("mail.azienda.communicationSubject", new String[] { SERVIZIO }, new Locale("it"));
						try {
							tributiCommonService.sendMailToCompany(userPreferences, aziendaByPiva, subjectAzienda, SERVIZIO, numeroProtocollo);
						}
						catch (Exception e) {
							log.error("upload :: " + e.getMessage(), e);
						}
					}
					
					if(datiTari.getUuidOperazione()==null)
					datiTari.setUuidOperazione((String)session.getAttribute("UUID"));
					
					final String uuidOperazione=datiTari.getUuidOperazione();
					final String numeroProtocolloNum=numeroProtocollo;
					
					DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();
					
					//MS
					tempiMediDto = DwhTempiMediUtil.get(dwhService)
					.go_EndTime(uuidOperazione)
					.setCodServizio(PortletConstants.CODICE_SERVIZIO_AGEVOLAZIONE_TARI)
					.setNomeServizio(SERVIZIO)
					.getTempiMedi();
					
					auditDwhService.invokeAuditService(null, null, tempiMediDto);

					 DwhServiceAttributeUtil.saveTimeFine(dwhService,new IUpdateAttribute() {
						
						  
								public String updateServizioProtocollo() {
								 
									return numeroProtocolloNum;
								}
								public String updateServizioParametro3() {
									 
									return null;
								}
								public String updateServizioParametro2() {
									 
									return  null;
								}
								public String updateServizioParametro1() {
								 
									return null;
								}
								public Date updateServizioFine() {
								 
									return new Date();
								}
								public String searchUUID() {
								
									return uuidOperazione;
								}
							});				
					
						AuditConfiguration
						.configure()
						.audit()
						.addInizioOperazione()
						.addFineOperazione()
						.addUuidOperazione(uuidOperazione)
						.addOperazioneRichiesta(SERVIZIO)
						.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
						.addPaginaCorrente(helper.getCurrentPageName(request))
						.addEsitoSuccess("SUCCESS - OPERAZIONE CONCLUSA")
						.addServizioTimeString()
						.addServizioUuidTransazione(uuidOperazione)
						.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_AGEVOLAZIONE_TARI)
						.setOrigin(Origin.getIp(request))
						.addCallGeo()
						.build();
					
					
					
					// Eliminazione Bozza
					Servizio servizio = servizioService.getServizioByCodiceServizio(PortletConstants.CODICE_SERVIZIO_AGEVOLAZIONE_TARI);
					commonService.deleteBozza(userPreferences, servizio.getId());
					response.setRenderParameter("action", "renderEsitoUpload");
				}
				catch (Exception e) {
					log.error("upload :: " + e.getMessage(), e);
					model.addAttribute("formError", messageSource.getMessage("exception.unreachable.message.args", new String[] { e.getMessage() }, request.getLocale()));
					response.setRenderParameter("action", "renderUploadForm");
				}
			}
		}
		else {

			String numAllegatiSelect = request.getParameter("numAllegatiSelect");
			int numAllegatiSelectInteger = Integer.parseInt(numAllegatiSelect);
			List<MultipartFile> fileList = new ArrayList<MultipartFile>();
			for (int i = 0; i < numAllegatiSelectInteger; i++) {
				fileList.add(null);
			}
			uploadItem.setMultipartFiles(fileList);

			model.addAttribute("selectNumAllegati", numAllegatiSelect);
			model.addAttribute("uploadItem", uploadItem);
			response.setRenderParameter("action", "renderUploadForm");
		}
	}

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ModelAndView maxUploadSizeExceededException(Exception exception, PortletRequest portletRequest) {

		log.error("maxUploadSizeExceededException :: " + exception.getMessage());

		Map<Object, Object> model = new HashMap<Object, Object>();
		String maxUploadSize = String.valueOf(((MaxUploadSizeExceededException) exception).getMaxUploadSize());
		model.put("formError", messageSource.getMessage("Maximum.upload.size.exceded", new String[] { maxUploadSize }, portletRequest.getLocale()));

		return new ModelAndView(toLocalViewPath("uploadDichiarazione"), (Map) model);
	}

	/**
	 * Handler per intercettare un tentativo di upload prima di aver generato la dichiarazione.
	 *
	 * @param exception
	 * @param portletRequest
	 * @return
	 */
	@ExceptionHandler(PortletSessionRequiredException.class)
	public ModelAndView portletSessionRequiredException(Exception exception, PortletRequest portletRequest) {

		log.error("portletSessionRequiredException :: " + exception.getMessage());

		Map<Object, Object> model = new HashMap<Object, Object>();
		model.put("formError", messageSource.getMessage("exception.dichiarazione.required.message", null, portletRequest.getLocale()));

		return new ModelAndView(toLocalViewPath("uploadDichiarazione"), (Map) model);
	}

	/**
	 * Presenta la form per l'agevolazione TARI
	 *
	 * @param model
	 * @throws Exception
	 */
	@RenderMapping(params = "action=renderAgevolazioneTariForm")
	public String renderAgevolazioneTariForm(Model model) throws Exception {
		return toLocalViewPath("agevolazioneTari");
	}

	/**
	 * Presenta la form per l'upload del file.
	 *
	 * @param model
	 * @return l'ID della view
	 */
	@RenderMapping(params = "action=renderUploadForm")
	public String renderUploadForm(Model model) {
		log.debug("Model = " + model);
		return toLocalViewPath("uploadDichiarazione");
	}

	/**
	 * Presenta la jsp di esito dell'upload del file.
	 *
	 * @param model
	 * @return l'ID della view
	 */
	@RenderMapping(params = "action=renderEsitoUpload")
	public String renderEsitoUpload(Model model) {
		log.debug("Model = " + model);
		return toLocalViewPath("esitoUploadDichiarazione");
	}

	/**
	 * Utility method per calcolare e restituire il view ID all'interno della corretta alberatura di
	 * sottodirectory delle JSP: in un certo senso si tratta del namespace delle view locali a
	 * questo controller. Ad esempio: <code> toLocalViewPath( "home" ) </code> restituirà <code>
	 * "/agevolazionetari/home" </code>
	 *
	 * @param viewName l'ID della view locale
	 * @return il view ID completo del path a cui la vista appartiene
	 */
	private static String toLocalViewPath(String viewName) {
		return JSP_PATH + viewName;
	}

	/**
	 * @param datiTari
	 */
	private void setInfoAggiuntiveForm(DatiAgevolazioneTari datiTari) {

		if (datiTari == null) {
			return;
		}
		if (datiTari.getComuneNascita() != null && !datiTari.getComuneNascita().isEmpty()) {
			Comune comuneNascita = comuneService.getComuneByCodiceISTAT(datiTari.getComuneNascita());
			datiTari.setComuneNascitaHidden(comuneNascita != null ? comuneNascita.getDenominazione() : null);
		}
		if (datiTari.getComuneResidenza() != null && !datiTari.getComuneResidenza().isEmpty()) {
			Comune comuneResidenza = comuneService.getComuneByCodiceISTAT(datiTari.getComuneResidenza());
			datiTari.setComuneResidenzaHidden(comuneResidenza != null ? comuneResidenza.getDenominazione() : null);
		}

		if (datiTari.getStatoEstero() != null && !"".equalsIgnoreCase(datiTari.getStatoEstero())) {
			StatoEstero statoEstero = statoEsteroService.getStatoEsteroByCodiceStato(Integer.parseInt(datiTari.getStatoEstero()));
			datiTari.setDenominazioneEstero(statoEstero != null ? statoEstero.getDenominazione() : null);
		}
		if (datiTari.getCodiceNascitaEstero() != null && !"".equalsIgnoreCase(datiTari.getCodiceNascitaEstero())) {
			ComuneEstero comuneEstero = comuneEsteroService.getComuneEsteroByCodice(Integer.parseInt(datiTari.getCodiceNascitaEstero()));
			datiTari.setComuneNascitaEstero(comuneEstero != null ? comuneEstero.getDenominazione() : null);
		}
	}

	@ModelAttribute("listaStatiEsteri")
	public List<StatoEstero> getStatiEsteri() {
		// escludi dalla lista l'italia - codice stato 100
		return statoEsteroService.getAllStatiAndEscludi(100);
	}

	@ModelAttribute("comuniList")
	public List<Comune> getComuniList(PortletRequest request) {

		List<Comune> result = comuneService.getAllComuni();

		return result;
	}

	@ModelAttribute("provinceList")
	public List<Provincia> getProvinceList(PortletRequest request) {

		List<Provincia> result = provinciaService.getAllProvince();

		return result;
	}

	@ModelAttribute("agevolazioniList")
	public List<Valore> getAgevolazioniList() {

		List<Valore> valoriAgevolazioni = new ArrayList<Valore>();
		valoriAgevolazioni.add(new Valore("A", "l'esenzione dal tributo per le utenze suindicate, su cui sussistono le condizioni (artt. 46, 47 e 76 del DPR n. 445/2000)"));
		valoriAgevolazioni.add(new Valore("B", "l'esenzione dal tributo per le utenze suindicate, su cui sussistono le condizioni (artt. 46, 47 e 76 del DPR n. 445/2000)"));
		valoriAgevolazioni.add(new Valore("C", "l'esenzione dal tributo per le utenze suindicate, su cui sussistono le condizioni (artt. 46, 47 e 76 del DPR n. 445/2000)"));
		valoriAgevolazioni.add(new Valore("D", "la riduzione tariffaria nella misura del 20% per le utenze suindicate, su cui sussistono le condizioni (artt. 46, 47 e 76 del DPR n. 445/2000)"));

		return valoriAgevolazioni;
	}

	@ModelAttribute("agevolazioniDescrizioniList")
	public List<Valore> getAgevolazioniDescrizioniMap() {

		List<Valore> valoriAgevolazioniDescrizioni = new ArrayList<Valore>();
		valoriAgevolazioniDescrizioni.add(new Valore("A", "il valore ISEE complessivo riferito all'anno precedente del proprio nucleo familiare non supera il limite di € 4.000,00;"));
		valoriAgevolazioniDescrizioni.add(new Valore("A",
				"nessuno dei componenti il predetto nucleo familiare è proprietario ovvero titolare di diritti reali di godimento di alcun immobile su tutto il territorio nazionale."));
		valoriAgevolazioniDescrizioni.add(new Valore("B", "il valore ISEE complessivo riferito all’anno precedente del predetto nucleo familiare non supera il limite di € 10.000,00;"));
		valoriAgevolazioniDescrizioni.add(new Valore("B", "occupa un’abitazione con massimo n. 2 pertinenze, in proprietà o usufrutto o locazione, adibita ad abitazione principale;"));
		valoriAgevolazioniDescrizioni.add(new Valore("B",
				"nessuno dei componenti il predetto nucleo familiare è proprietario ovvero titolare di diritti reali di godimento di altri immobili su tutto il territorio nazionale."));
		valoriAgevolazioniDescrizioni.add(new Valore("C", "il proprio nucleo familiare ha un numero di componenti maggiore o uguale a sei;"));
		valoriAgevolazioniDescrizioni.add(new Valore("C", "il valore ISEE complessivo riferito all’anno precedente del predetto nucleo familiare non supera il limite di € 25.000,00;"));
		valoriAgevolazioniDescrizioni.add(new Valore("C", "occupa un’abitazione con massimo n. 2 pertinenze, in proprietà o usufrutto o locazione, adibita ad abitazione principale;"));
		valoriAgevolazioniDescrizioni.add(new Valore("C",
				"nessuno dei componenti il predetto nucleo familiare è proprietario ovvero titolare di diritti reali di godimento di altri immobili su tutto il territorio nazionale."));
		valoriAgevolazioniDescrizioni.add(new Valore("D", "il valore ISEE complessivo riferito all’anno precedente del predetto nucleo familiare non supera il limite di € 7.000,00;"));
		valoriAgevolazioniDescrizioni.add(new Valore("D", "occupa un’abitazione con massimo n.2 pertinenze, in proprietà o usufrutto o locazione, adibita ad abitazione principale;"));
		valoriAgevolazioniDescrizioni.add(new Valore("D",
				"nessuno dei componenti il predetto nucleo familiare è proprietario ovvero titolare di diritti reali di godimento di altri immobili su tutto il territorio nazionale."));

		return valoriAgevolazioniDescrizioni;
	}

	@ModelAttribute("visuraPosizioniTributarieActive")
	public Boolean getVisuraPosizioniTributarieActive(PortletRequest request) {

		UserPreferences userPreferences = helper.getUserPreferences(request);

		boolean result = false;

		ComuneISA comuneByPk = comuneISAService.getComuneByPk(userPreferences.getIdComuneIsa());
		if (comuneByPk != null) {
			result = comuneByPk.getVisuraPosizioniTributarieActive();
		}

		return result;
	}

	/**
	 * Genera il file XML di interoperabilità del servizio che dovrà essere allegato durante l'invio
	 * all'ente.
	 *
	 * @param richiesta
	 * @return
	 */
	private MultipartFile generateMultipartFileForInterop(DichiarazioneTassaRifiutiInputRichiesta richiesta) {

		MultipartFile multipartFile = null;

		try {
			String xml = xmlHelper.marshal(richiesta);
			multipartFile = new MockMultipartFile(PortletConstants.INTEROP_FILE_NAME, PortletConstants.INTEROP_FILE_NAME, "text/xml", xml.getBytes());
		}
		catch (Exception e) {
			log.error("generateMultipartFileForInterop() :: " + e.getMessage(), e);
		}

		return multipartFile;
	}

	private List<DatiImmobileAgevolazioneTari> generateList(UserPreferences userPreferences, List<DatiTassaRifiuti.Posizioni> posizioni, int limit, boolean nuovoImmobile) {

		ArrayList<DatiImmobileAgevolazioneTari> results = new ArrayList<DatiImmobileAgevolazioneTari>();

		if (nuovoImmobile) {
			limit++;
		}

		for (int i = 0; i < limit; i++) {
			DatiImmobileAgevolazioneTari immobile = new DatiImmobileAgevolazioneTari();
			if (posizioni.size() > 0 && i < posizioni.size()) {
				Posizioni posizione = posizioni.get(i);

				it.osapulie.tributi.web.ws.output.types.Indirizzo indirizzoUtenza = posizione.getIndirizzoUtenza();
				if (indirizzoUtenza != null) {
					it.osapulie.tributi.web.ws.output.types.Codifica via = indirizzoUtenza.getVia();
					Civico civico = indirizzoUtenza.getCivico();
					String indirizzoText = via.getCodice() != null ? via.getCodice() : via.getDescrizione();
					String civicoText = civico.getCodice() != null ? civico.getCodice() : String.valueOf(civico.getNumero());
					immobile.setIndirizzo(indirizzoText);
					immobile.setIndirizzoTextHidden(via.getDescrizione());
					immobile.setCivico(civicoText);
					immobile.setEsponente(civico.getEsponente());
					immobile.setCivicoTextHidden(String.valueOf(civico.getNumero()));
					immobile.setLocalita(indirizzoUtenza.getLocalita());
					immobile.setPiano(indirizzoUtenza.getPiano());
					immobile.setInterno(indirizzoUtenza.getInterno());
					immobile.setScala(indirizzoUtenza.getScala());
					immobile.setSuffisso(indirizzoUtenza.getSuffisso());
					immobile.setCap(indirizzoUtenza.getCap());
				}

				String superficie = posizione.getSuperficie() != null ? posizione.getSuperficie().toPlainString() : null;
				immobile.setSuperficie(superficie);
				immobile.setFoglio(String.valueOf(posizione.getFoglio()));
				immobile.setSezione(posizione.getSezione());
				immobile.setParticella(String.valueOf(posizione.getParticella()));
				immobile.setSubalterno(posizione.getSubalterno() != null ? String.valueOf(posizione.getSubalterno()) : null);

				immobile.setTipo(posizione.getTipo());
				it.osapulie.tributi.web.ws.output.types.Codifica categoriaImmobile = posizione.getCategoriaImmobile();
				if (categoriaImmobile != null) {
					String categoria = categoriaImmobile.getCodice() != null ? categoriaImmobile.getCodice() : categoriaImmobile.getDescrizione();
					immobile.setCategoria(categoria);
				}

				immobile.setIdentificativoUtenza(posizione.getIdentificativoUtenza());
			}
			results.add(immobile);
		}

		return results;
	}

	private List<DatiImmobileAgevolazioneTari> generateList(UserPreferences userPreferences, List<DatiTassaRifiuti> situazioni, int limit, boolean stradarioEnabled, boolean copy) {

		ArrayList<DatiImmobileAgevolazioneTari> results = new ArrayList<DatiImmobileAgevolazioneTari>();
		List<DatiTassaRifiuti.Posizioni> pos = new ArrayList<DatiTassaRifiuti.Posizioni>();

		if (situazioni != null && !situazioni.isEmpty()) {
			// Prelevo l'ultimo anno
			DatiTassaRifiuti situazione = situazioni.get(situazioni.size() - 1);
			pos = situazione.getPosizioni();
		}

		for (int i = 0; i < limit; i++) {
			DatiImmobileAgevolazioneTari immobile = new DatiImmobileAgevolazioneTari();
			if (pos.size() > 0 && i < pos.size()) {
				Posizioni posizione = pos.get(i);
				Indirizzo indirizzoUtenza = posizione.getIndirizzoUtenza();
				if (indirizzoUtenza != null) {
					it.osapulie.tributi.web.ws.output.types.Codifica via = indirizzoUtenza.getVia();
					Civico civico = indirizzoUtenza.getCivico();
					String indirizzoText = via.getCodice() != null ? via.getCodice() : via.getDescrizione();
					String civicoText = civico.getCodice() != null ? civico.getCodice() : String.valueOf(civico.getNumero());
					immobile.setViaCodificata(indirizzoText);
					immobile.setViaTextHidden(via.getDescrizione());
					immobile.setCivico(civicoText);
					immobile.setEsponente(civico.getEsponente());
					immobile.setCivicoTextHidden(String.valueOf(civico.getNumero()));
					immobile.setLocalita(indirizzoUtenza.getLocalita());
					immobile.setPiano(indirizzoUtenza.getPiano());
					immobile.setInterno(indirizzoUtenza.getInterno());
					immobile.setScala(indirizzoUtenza.getScala());
				}

				immobile.setFoglio(String.valueOf(posizione.getFoglio()));
				immobile.setSezione(posizione.getSezione());
				immobile.setParticella(String.valueOf(posizione.getParticella()));
				immobile.setSubalterno(String.valueOf(posizione.getSubalterno()));
				immobile.setIdentificativoUtenza(posizione.getIdentificativoUtenza());
			}
			results.add(immobile);
		}

		return results;
	}

	/**
	 * Genera il file XML dalla dichiarazione passata in input.
	 *
	 * @param datiTari
	 * @param userPreferences
	 * @return
	 */
	private DichiarazioneTassaRifiutiInputRichiesta getDichiarazioneTassaRifiutiInputRichiesta(DatiAgevolazioneTari datiTari, OSApulieUserDetails osApulieUserDetails, UserPreferences userPreferences)
			throws Exception {

		DichiarazioneTassaRifiutiInputRichiesta dichiarazioneTassaRifiutiInputRichiesta = new DichiarazioneTassaRifiutiInputRichiesta();

		Agevolazione agevolazione = new Agevolazione();

		boolean stradarioEnable = configurazioneService.isStradarioEnable(PortletConstants.CODICE_SERVIZIO_AGEVOLAZIONE_TARI, userPreferences.getIdComuneIsa());

		Dichiarazione dichiarazione = getDichiarazione(datiTari, userPreferences);
		BeanUtils.copyProperties(dichiarazione, agevolazione);

		agevolazione.setTipoUtenza(new Codifica(UTENZA_DOMESTICA, null));
		Utenza utenza = new Utenza();
		List<UtenzaDomestica> utenzeDomestica = utenza.getUtenzaDomestica();
		for (DatiImmobileAgevolazioneTari immobile : datiTari.getImmobili()) {
			if (immobile.getIdentificativoUtenza() != null) {
				UtenzaDomestica utenzaDomestica = new UtenzaDomestica();
				utenzaDomestica.setUbicazione(getIndirizzoFromImmobile(immobile, stradarioEnable));
				utenzaDomestica.setIdentificativoUtenza(immobile.getIdentificativoUtenza());
				utenzaDomestica.setUso(new Codifica(immobile.getTipo(), null));
				utenzeDomestica.add(utenzaDomestica);
			}
		}

		agevolazione.setUtenza(utenza);
		agevolazione.setCodiceAgevolazione(new Codifica(datiTari.getCodiceAgevolazione(), null));

		String dataDecorrenza = datiTari.getDataDecorrenza();
		if (dataDecorrenza != null) {
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(simpleDateFormat.parse(dataDecorrenza));
			agevolazione.setDataDecorrenza(calendar);
		}

		ModelloISEE modelloIsee = new ModelloISEE();

		NucleoFamiliare nucleoFamigliare = new NucleoFamiliare();
		nucleoFamigliare.setNumComponentiNucleoFamiliare(Integer.parseInt(datiTari.getNumComponentiNucleoFamiliare()));
		nucleoFamigliare.setNumSoggettiHandicap(Integer.parseInt(datiTari.getNumComponentiNucleoFamiliare()));
		nucleoFamigliare.setPresenzaFigliUnGenitore(datiTari.getPresenzaFigliUnGenitore());
		nucleoFamigliare.setPresenzaFigliAttivitaGenitori(datiTari.getPresenzaFigliAttivitaGenitori());
		modelloIsee.setNucleoFamiliare(nucleoFamigliare);

		modelloIsee.setPatrimonio(new Patrimonio());
		modelloIsee.getPatrimonio().setRC(getBigDecimal(datiTari.getRc()));
		modelloIsee.getPatrimonio().setPAG(getBigDecimal(datiTari.getPag()));
		modelloIsee.getPatrimonio().setRCPAG(getBigDecimal(datiTari.getRcpag()));
		modelloIsee.getPatrimonio().setRPM(getBigDecimal(datiTari.getRpm()));
		modelloIsee.getPatrimonio().setDC(getBigDecimal(datiTari.getDc()));
		modelloIsee.getPatrimonio().setPMO(getBigDecimal(datiTari.getPmo()));
		modelloIsee.getPatrimonio().setDPM(getBigDecimal(datiTari.getDpm()));
		modelloIsee.getPatrimonio().setPIM(getBigDecimal(datiTari.getPim()));
		modelloIsee.getPatrimonio().setDPI(getBigDecimal(datiTari.getDpi()));
		modelloIsee.setCodiceFiscaleDichiarante(datiTari.getCodiceFiscaleContribuente());

		String dataAttestazione = datiTari.getDataAttestazione();
		if (dataAttestazione != null) {
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(simpleDateFormat.parse(dataAttestazione));
			modelloIsee.setDataAttestazione(calendar);
		}
		String dataScadenza = datiTari.getDataScadenza();
		if (dataScadenza != null) {
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(simpleDateFormat.parse(dataScadenza));
			modelloIsee.setDataScadenza(calendar);
		}
		modelloIsee.setValoreISE(getBigDecimal(datiTari.getValoreISE()));
		modelloIsee.setScalaEquivalenza(getBigDecimal(datiTari.getScalaEquivalenza()));
		modelloIsee.setValoreISEE(getBigDecimal(datiTari.getValoreISEE()));
		agevolazione.setAttestazioneISEE(modelloIsee);

		CAF caf = new CAF();
		caf.setCodiceFiscale(datiTari.getCodiceFiscaleCAF());
		caf.setPartitaIVA(datiTari.getPartitaIvaCAF());
		caf.setNominativo(datiTari.getNominativoCAF());
		caf.setPresaVisione(datiTari.getPresaVisione() ? "1" : null);
		agevolazione.setCaf(caf);

		agevolazione.setPresaVisione(datiTari.getPresaVisione());

		agevolazione.setDocumentoIdentitaAllegato(null);
		agevolazione.setDocumentoIdentitaDelegatoAllegato(null);

		Tracciamento tracciamento = tributiService.getTracciamento(osApulieUserDetails, userPreferences);

		agevolazione.setTracciamento(tracciamento);

		dichiarazioneTassaRifiutiInputRichiesta.setAgevolazione(agevolazione);

		return dichiarazioneTassaRifiutiInputRichiesta;
	}

	/**
	 *
	 * @param datiTari
	 * @param userPreferences
	 * @return
	 * @throws ParseException
	 */
	private Dichiarazione getDichiarazione(DatiAgevolazioneTari datiTari, UserPreferences userPreferences) throws ParseException {

		OSApulieUserDetails osApulieUserDetails = helper.getOsApulieUserDetails();

		Dichiarazione dichiarazione = new Dichiarazione();

		dichiarazione.setData(Calendar.getInstance());
		dichiarazione.setDichiarante(getDichiarante(datiTari, userPreferences));

		dichiarazione.setTracciamento(tributiService.getTracciamento(osApulieUserDetails, userPreferences));

		return dichiarazione;
	}

	/**
	 * @param datiTari
	 * @param userPreferences
	 * @return
	 * @throws ParseException
	 */
	private Dichiarante getDichiarante(DatiAgevolazioneTari datiTari, UserPreferences userPreferences) throws ParseException {

		Dichiarante dichiarante = new Dichiarante();
		dichiarante.setIdentificativoContribuente(datiTari.getIdContribuente());
		Contribuente contribuente = getContribuenteFromDichiarazione(datiTari);
		BeanUtils.copyProperties(contribuente, dichiarante);

		it.osapulie.tributi.web.ws.input.types.Indirizzo indirizzo = getIndirizzoResidenzaFromDatiDichiarazione(datiTari, userPreferences);
		// TODO non c'è il cellulare
		dichiarante.setCellulare(null);
		dichiarante.setDomicilio(indirizzo);

		dichiarante.setTelefono(datiTari.getTelefono());
		dichiarante.setPec(datiTari.getPecCAF());

		return dichiarante;
	}

	/**
	 * @param datiTari
	 * @return
	 * @throws ParseException
	 */
	private Contribuente getContribuenteFromDichiarazione(DatiAgevolazioneTari datiTari) throws ParseException {
		Contribuente contribuente = new Contribuente();
		if (datiTari.getPartitaIVA() != null && !datiTari.getPartitaIVA().isEmpty()) {
			contribuente.setPartitaIVA(datiTari.getPartitaIVA());
		}
		PersonaFisica personaFisica = new PersonaFisica();
		personaFisica.setCodiceFiscale(datiTari.getCodiceFiscale());
		personaFisica.setCognome(datiTari.getCognome());
		// Distinzione tra comune di nascita Italiano e stato estero (codici ISTAT AN e codice
		// stato)
		if (datiTari.getStatoEstero() == null || datiTari.getStatoEstero().isEmpty()) {
			personaFisica.setComuneNascita(new Codifica(datiTari.getComuneNascita(), datiTari.getComuneNascitaHidden()));
		}
		else {
			personaFisica.setStatoEsteroNascita(new Codifica(datiTari.getStatoEstero(), datiTari.getDenominazioneEstero()));
			personaFisica.setComuneEsteroNascita(new Codifica(datiTari.getCodiceNascitaEstero(), datiTari.getComuneNascitaEstero()));
		}
		String dataNascita = datiTari.getDataNascita();
		if (dataNascita != null) {
			Calendar calendar = getData(dataNascita);
			personaFisica.setDataNascita(calendar);
		}
		personaFisica.setNome(datiTari.getNome());
		if (datiTari.getPartitaIVA() != null && !datiTari.getPartitaIVA().isEmpty()) {
			personaFisica.setPartitaIVA(datiTari.getPartitaIVA());
		}
		personaFisica.setProvinciaNascita(new Codifica(datiTari.getProvinciaNascita(), null));
		personaFisica.setSesso(new Codifica(datiTari.getSesso(), datiTari.getSesso()));
		contribuente.setPersonaFisica(personaFisica);

		return contribuente;
	}

	/**
	 * @param userPreferences
	 * @param datiImmobile
	 * @return
	 */

	private it.osapulie.tributi.web.ws.input.types.Indirizzo getIndirizzoResidenzaFromDatiDichiarazione(DatiAgevolazioneTari datiAgevolazioneTari, UserPreferences userPreferences) {

		it.osapulie.tributi.web.ws.input.types.Indirizzo ubicazione = new it.osapulie.tributi.web.ws.input.types.Indirizzo();
		it.osapulie.tributi.web.ws.input.types.Indirizzo.Civico civico = new it.osapulie.tributi.web.ws.input.types.Indirizzo.Civico();
		Codifica codiceVia = new Codifica();

		// Set codice catastale
		String comuneResidenza = datiAgevolazioneTari.getComuneResidenza();
		ubicazione.setComune(getCodificaComune(comuneResidenza));

		boolean isIndirizzoResidenzaConStradario = checkIndirizzoResidenzaConStradario(userPreferences, datiAgevolazioneTari);
		if (isIndirizzoResidenzaConStradario) {
			civico.setCodice(datiAgevolazioneTari.getCivicoResidenza());
			civico.setNumero(Integer.parseInt(datiAgevolazioneTari.getCivicoResidenzaTextHidden()));
			codiceVia.setCodice(datiAgevolazioneTari.getIndirizzoResidenza());
			codiceVia.setDescrizione(datiAgevolazioneTari.getIndirizzoResidenzaTextHidden());
		}
		else {
			civico.setNumero(Integer.parseInt(datiAgevolazioneTari.getCivicoResidenza()));
			codiceVia.setDescrizione(datiAgevolazioneTari.getIndirizzoResidenza());
		}
		civico.setEsponente(datiAgevolazioneTari.getEsponenteResidenza());
		ubicazione.setCivico(civico);

		ubicazione.setVia(codiceVia);

		return ubicazione;
	}

	/**
	 * Ritorna la codifica corrispondente al comune passato in input.
	 *
	 * @param comune
	 * @return
	 */
	private Codifica getCodificaComune(String codiceIstat) {

		Comune comune = comuneService.getComuneByCodiceISTAT(codiceIstat);

		return new Codifica(comune != null ? comune.getCodiceCatastale() : null, comune != null ? comune.getDenominazione() : null);
	}

	/**
	 * @param datiImmobile
	 * @param stradarioEnable
	 * @return
	 */
	private it.osapulie.tributi.web.ws.input.types.Indirizzo getIndirizzoFromImmobile(DatiImmobileAgevolazioneTari datiImmobile, boolean stradarioEnable) {
		it.osapulie.tributi.web.ws.input.types.Indirizzo ubicazione = new it.osapulie.tributi.web.ws.input.types.Indirizzo();
		it.osapulie.tributi.web.ws.input.types.Indirizzo.Civico civico = new it.osapulie.tributi.web.ws.input.types.Indirizzo.Civico();
		Codifica codiceVia = new Codifica();
		if (stradarioEnable) {
			civico.setCodice(datiImmobile.getCivico());
			civico.setEsponente(datiImmobile.getEsponente());
			if (datiImmobile.getCivicoTextHidden() != null && !datiImmobile.getCivicoTextHidden().isEmpty()) {
				civico.setNumero(Integer.parseInt(datiImmobile.getCivicoTextHidden()));
			}
			ubicazione.setCivico(civico);
			codiceVia.setCodice(datiImmobile.getIndirizzo());
			codiceVia.setDescrizione(datiImmobile.getIndirizzoTextHidden());
			ubicazione.setVia(codiceVia);
		}
		else {
			civico.setEsponente(datiImmobile.getEsponente());
			if (datiImmobile.getCivico() != null && !datiImmobile.getCivico().isEmpty()) {
				civico.setNumero(Integer.parseInt(datiImmobile.getCivico()));
			}
			ubicazione.setCivico(civico);
			codiceVia.setDescrizione(datiImmobile.getIndirizzo());
			ubicazione.setVia(codiceVia);
		}

		ubicazione.setLocalita(datiImmobile.getLocalita());
		ubicazione.setPiano(datiImmobile.getPiano());
		ubicazione.setInterno(datiImmobile.getInterno());
		ubicazione.setScala(datiImmobile.getScala());
		ubicazione.setSuffisso(datiImmobile.getSuffisso());
		ubicazione.setCap(datiImmobile.getCap());
		return ubicazione;
	}

	/**
	 * @param uploadItem
	 * @throws IOException
	 */
	private List<Documento> getDocumentiFromUploadItem(UploadItem uploadItem) throws IOException {
		List<Documento> result = new ArrayList<Documento>();

		MultipartFile generatedFile = uploadItem.getGeneratedFile();
		if (generatedFile != null) {
			Documento documento = new Documento();
			documento.setNome(generatedFile.getOriginalFilename());
			documento.setContentType(generatedFile.getContentType());
			documento.setContenuto(generatedFile.getBytes());
			documento.setDescrizione(generatedFile.getOriginalFilename());
			documento.setPrincipale(true);
			result.add(documento);
		}

		List<MultipartFile> multipartFiles = uploadItem.getMultipartFiles();
		if (multipartFiles != null) {
			for (MultipartFile multipartFile : multipartFiles) {
				Documento documento = new Documento();
				documento.setNome(multipartFile.getOriginalFilename());
				documento.setContentType(multipartFile.getContentType());
				documento.setContenuto(multipartFile.getBytes());
				documento.setDescrizione(multipartFile.getOriginalFilename());
				documento.setPrincipale(false);
				result.add(documento);
			}
		}

		return result;

	}

	/**
	 * @param data
	 * @return
	 * @throws ParseException
	 */
	private Calendar getData(String data) throws ParseException {
		Calendar calendar = null;
		if (data != null && !data.isEmpty()) {
			calendar = new GregorianCalendar();
			calendar.setTime(simpleDateFormat.parse(data));
		}
		return calendar;
	}

	private BigDecimal getBigDecimal(String valore) {
		try {
			Double.parseDouble(valore);// se è un double, si può convertire in bigdecimal
			return new BigDecimal(valore);
		}
		catch (NumberFormatException e) { // altrimenti, provo a convertirlo
			NumberFormat nf = NumberFormat.getInstance();
			try {
				valore = nf.parse(valore).toString();
				return new BigDecimal(valore);
			}
			catch (ParseException pe) {
				pe.printStackTrace();
				return null;
			}
		}
	}
}
