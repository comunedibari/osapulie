/************************************************************************************
 * Copyright (c) 2011, 2016 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.tributi.web.portlet.visuraprovvedimentiautotutelaimu.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import eng.tz.la.core.AuditManager;
import eng.tz.la.core.Export;
import eng.tz.la.model.OperationExportFile;
import it.osapulie.documenti.web.ws.output.types.Documento;
import it.osapulie.documenti.web.ws.output.types.VisuraDocumentiRichiesta;
import it.osapulie.documenti.web.ws.output.types.VisuraDocumentiRisposta;
import it.osapulie.domain.Audit;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.dto.Fascicolo;
import it.osapulie.infrastructure.impl.DateUtils;
import it.osapulie.persistence.AuditRepository;
import it.osapulie.service.CommonService;
import it.osapulie.service.ComuneService;
import it.osapulie.service.DelegaService;
import it.osapulie.service.DwhService;
import it.osapulie.service.FascicoloUtenteService;
import it.osapulie.service.ServizioService;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.tributi.service.AuditDwhService;
import it.osapulie.tributi.service.VisuraDocumentiService;
import it.osapulie.tributi.web.portlet.utils.PortletConstants;
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
import it.osapulie.web.portlet.util.PortletHelper;
import it.osapulie.web.portlet.util.impl.LiferayPortletHelperImpl;

/**
 * Portlet controller per la visura Provvedimenti Autotutela IMU.
 *
 * @author Gianluca Pindinelli
 */
@Controller("visuraProvvedimentiAutotutelaImuPortletController")
@RequestMapping("view")
public class VisuraProvvedimentiAutotutelaImuPortletController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(VisuraProvvedimentiAutotutelaImuPortletController.class);

	@Inject
	private VisuraDocumentiService service;

 	@Inject
 	private PortletHelper helper;

	@Inject
	private FascicoloUtenteService fascicoloService;

	@Autowired
	public ResourceBundleMessageSource messageSource;
	
	@Inject
	private AuditRepository auditRepository;

	@Inject
	private ComuneService comuneService;
	@Inject
	private CommonService commonService;
	@Inject
	private DelegaService delegaService;
	@Inject
	private ServizioService servizioService;
	@Inject
	private DwhService dwhService;
	@Inject
	private AuditDwhService auditDwhService;
	
	/**
	 * Default entry quando la portlet viene visualizzata per la prima volta.
	 *
	 * @param model il view model da popolare
	 * @param session 
	 * @return il view ID da renderizzare .
	 */
	@RenderMapping
	public String homePage(Model model, RenderRequest request, PortletSession session)throws Exception {
		log.info("home ");

		String uuidStr=UUID.randomUUID().toString();
		session.setAttribute("UUID", uuidStr);
		AuditManager auditManager= AuditConfiguration.configure()
					.audit()
					.addInizioOperazione()
					.addUuidOperazione(uuidStr)
					.addOperazioneRichiesta("VISURA PROVVEDIMENTI AUTOTUTELA IMU")
					.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
					.addPaginaCorrente(helper.getCurrentPageName(request));
			 auditManager.exportUser(false, new Export() {
			
			@Override
			public void call(OperationExportFile op) throws IOException {
		
				List<Audit> ltsAudit= auditRepository.findByFileName(op.getManager().getCodFileName());
				if(ltsAudit==null || ltsAudit!=null && ltsAudit.size()<1) {
					Date dateCreazione=new Date();
					Audit a= new Audit();
					a.setUserCod(op.getManager().getKeyUser());
					a.setGiornoMeseAnno(op.getManager().getFolderDay());
					a.setFileName(op.getManager().getCodFileName());
					a.setPathFilesystem(op.getManager().getPath_Log_User());
					a.setChecksum("0");
					a.setDataCreazione(dateCreazione);
					a.setDataUltimaModifica(dateCreazione);
					a.setCodiceRegistro("0");
					a.setStato("0");
					a.setCons("0"); 
					auditRepository.save(a);
				}
			}
		});
		auditManager.build();
		
		UserPreferences userPreferences = helper.getUserPreferences(request);
		String codiceFiscale = userPreferences.getCodiceFiscaleServizio();

		 final String uuidOperazione=uuidStr;
		 
		 	DwhServizioAttributeDTO servizioAttributeDto = new DwhServizioAttributeDTO();
			DwhDataminingDTO dataminingDto = new DwhDataminingDTO();
			DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();
		 
			servizioAttributeDto = DwhServiceAttributeUtil.get(commonService, comuneService, delegaService, servizioService, userPreferences, request, dwhService)
			.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_VISURA_PROVVEDIMENTI_AUTOTUTELA_IMU)
			.setUUID(uuidOperazione)
			.getServizioAttribute();
			
			
			dataminingDto = DwhDataminingUtil.get(codiceFiscale, dwhService)
			.setCodServizio(PortletConstants.CODICE_SERVIZIO_VISURA_PROVVEDIMENTI_AUTOTUTELA_IMU)
			.setUuidOperazione(uuidOperazione)
			.getDatamining();
			
			 tempiMediDto =DwhTempiMediUtil.get(dwhService)
			.setCodServizio(PortletConstants.CODICE_SERVIZIO_VISURA_PROVVEDIMENTI_AUTOTUTELA_IMU)
			.setNomeServizio("VISURA PROVVEDIMENTI AUTOTUTELA IMU")
			.setUuid(uuidOperazione).go_StartTime().getTempiMedi();

			 auditDwhService.invokeAuditService(servizioAttributeDto, dataminingDto, tempiMediDto);
				//MS
				tempiMediDto = DwhTempiMediUtil.get(dwhService)
				.setCodServizio(PortletConstants.CODICE_SERVIZIO_VISURA_PROVVEDIMENTI_AUTOTUTELA_IMU)
				.setNomeServizio("VISURA PROVVEDIMENTI AUTOTUTELA IMU")
				.go_EndTime(uuidOperazione)
				.getTempiMedi();

				auditDwhService.invokeAuditService(null, null, tempiMediDto);
				
				DwhServiceAttributeUtil.saveTimeFine(dwhService,new IUpdateAttribute() {
					
					  
							public String updateServizioProtocollo() {
							 
								return null;
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
					.addOperazioneRichiesta("VISURA PROVVEDIMENTI AUTOTUTELA IMU  - STEP FINALE")
					.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
					.addPaginaCorrente(helper.getCurrentPageName(request))
					.addEsitoSuccess("SUCCESS - OPERAZIONE CONCLUSA")
					.addServizioTimeString()
					.addServizioUuidTransazione(uuidOperazione)
					.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_VISURA_PROVVEDIMENTI_AUTOTUTELA_IMU)
					.setOrigin(Origin.getIp(request))
					.addCallGeo()
					.build();
			
			
		VisuraDocumentiRichiesta richiesta = new VisuraDocumentiRichiesta();
		if (userPreferences.getPartitaIvaServizio() != null) {
			richiesta.setPartitaIva(userPreferences.getPartitaIvaServizio());
		}
		else {
			richiesta.setCodiceFiscale(codiceFiscale);
		}

		richiesta.setAnno(Integer.parseInt(DateUtils.getAnnoCorrente()));
		richiesta.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_VISURA_PROVVEDIMENTI_AUTOTUTELA_IMU);

		VisuraDocumentiRisposta risposta = service.richiediDatiVisura(richiesta, userPreferences);

		model.addAttribute("risposta", risposta);
 
		return toLocalViewPath("home");
	}

	@ResourceMapping("downloadDocumento")
	public void downloadDocumento(@RequestParam("idDocumento") String idDocumento, Model model, ResourceRequest request, ResourceResponse response, PortletSession session) throws Exception {
		AuditManager auditManager= AuditConfiguration
				.configure()
				.audit().addUuidOperazione((String)session
				.getAttribute("UUID"))
				.addOperazioneRichiesta("VISURA PROVVEDIMENTI AUTOTUTELA IMU - START DOWNLOAD DOCUMENTO")
				.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
				.setOrigin(Origin.getIp(request))
				.addPaginaCorrente(helper.getCurrentPageName(request));
		auditManager
		.addFineOperazione()
		.addEsitoSuccess()
		.build();
		try {			
			UserPreferences userPreferences = helper.getUserPreferences(request);
			String codiceFiscale = userPreferences.getCodiceFiscaleServizio();

			VisuraDocumentiRichiesta richiesta = new VisuraDocumentiRichiesta();

			// Controllo profilo cittadino/azienda
			if (userPreferences.getPartitaIvaServizio() != null) {
				richiesta.setPartitaIva(userPreferences.getPartitaIvaServizio());
			}
			else {
				richiesta.setCodiceFiscale(codiceFiscale);
			}

			richiesta.setIdDocumento(idDocumento);
			richiesta.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_VISURA_PROVVEDIMENTI_AUTOTUTELA_IMU);

			VisuraDocumentiRisposta risposta = service.richiediDatiVisura(richiesta, userPreferences);

			List<Documento> documenti = risposta.getDocumento();
			if (risposta != null && documenti != null && !documenti.isEmpty()) {
				Documento documento = documenti.get(0);

				byte[] binario = documento.getBinario();
				// Scrittura su fascicolo utente
				ProfiloUtenteCittadino profiloUtente = helper.getOsApulieUserDetails().getProfiloUtenteCittadino();

				Fascicolo fascicolo = new Fascicolo();
				fascicolo.setIdProfiloUtente(profiloUtente);
				fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio", null, request.getLocale()));
				fascicolo.setUserPreferences(userPreferences);
				fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_VISURA_PROVVEDIMENTI_AUTOTUTELA_IMU);
				fascicolo.setRicercabileDaComune(false);
				fascicolo.setNumeroProtocollo(null);
				fascicolo.setChecksum(null);
				fascicoloService.saveNuovaRichiesta(fascicolo);

				 final String uuidOperazione=(String)session.getAttribute("UUID");
				 	
				 	DwhServizioAttributeDTO servizioAttributeDto = new DwhServizioAttributeDTO();
					DwhDataminingDTO dataminingDto = new DwhDataminingDTO();
					DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();
					
					dataminingDto = DwhDataminingUtil.get(codiceFiscale, dwhService)
					.setCodServizio(PortletConstants.CODICE_SERVIZIO_VISURA_PROVVEDIMENTI_AUTOTUTELA_IMU)
					.setUuidOperazione(uuidOperazione)
					.getDatamining();
					
					 tempiMediDto = DwhTempiMediUtil.get(dwhService)
					.setCodServizio(PortletConstants.CODICE_SERVIZIO_VISURA_PROVVEDIMENTI_AUTOTUTELA_IMU)
					.setNomeServizio("VISURA PROVVEDIMENTI AUTOTUTELA IMU")
					.setUuid(uuidOperazione).go_StartTime().getTempiMedi();

					servizioAttributeDto = DwhServiceAttributeUtil.get(commonService, comuneService, delegaService, servizioService, userPreferences, request, dwhService)
					.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_VISURA_PROVVEDIMENTI_AUTOTUTELA_IMU)
					.setUUID(uuidOperazione)
					.getServizioAttribute();
					
					auditDwhService.invokeAuditService(servizioAttributeDto, dataminingDto, tempiMediDto);
				
					 	
						//MS
						tempiMediDto = DwhTempiMediUtil.get(dwhService)
						.go_EndTime(uuidOperazione)
						.setCodServizio(PortletConstants.CODICE_SERVIZIO_VISURA_PROVVEDIMENTI_AUTOTUTELA_IMU)
						.setNomeServizio("VISURA PROVVEDIMENTI AUTOTUTELA IMU")
						.getTempiMedi();

						auditDwhService.invokeAuditService(null, null, tempiMediDto);
						
						 DwhServiceAttributeUtil.saveTimeFine(dwhService,new IUpdateAttribute() {
							
							  
									public String updateServizioProtocollo() {
									 
										return null;
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
							.addOperazioneRichiesta("VISURA PROVVEDIMENTI AUTOTUTELA IMU  - DOWNLOAD DOCUMENTO STEP FINALE")
							.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
							.addPaginaCorrente(helper.getCurrentPageName(request))
							.addEsitoSuccess("SUCCESS - OPERAZIONE CONCLUSA")
							.addServizioTimeString()
							.addServizioUuidTransazione(uuidOperazione)
							.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_VISURA_PROVVEDIMENTI_AUTOTUTELA_IMU)
							.setOrigin(Origin.getIp(request))
							.addCallGeo()
							.build();
				

				
				helper.sendStreamAsAttachment(binario, response, documento.getNome(), documento.getContentType());
		
			}
		}
		catch (Exception e) {
			log.error("downloadDocumento :: " + e.getMessage(), e);
			auditManager
			.addFineOperazione()
			.addEsitoError()
			.addInfo("Exception", e.getMessage())
			.build();
	
		}
		
 
	}

	/**
	 * Presenta la visura a video.
	 *
	 * @param model
	 * @return l'ID della view per visualizzare il fascicolo
	 */
	@RenderMapping(params = "action=renderVisura")
	public String renderVisura(Model model) {
		return toLocalViewPath("mostraVisura");
	}

	/**
	 * Utility method per calcolare e restituire il view ID all'interno della corretta alberatura di
	 * sottodirectory delle JSP: in un certo senso si tratta del namespace delle view locali a
	 * questo controller. Ad esempio: <code> toLocalViewPath( "home" ) </code> restituirà <code>
	 * "/visuraprovvedimentirimborsotasi/home" </code>
	 *
	 * @param viewName l'ID della view locale
	 * @return il view ID completo del path a cui la vista appartiene
	 */
	private static String toLocalViewPath(String viewName) {
		return "visuraprovvedimentiautotutelaimu/" + viewName;
	}

}
