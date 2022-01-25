/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.web.portlet.interceptor;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletSecurityException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.UnavailableException;

import org.springframework.web.portlet.handler.HandlerInterceptorAdapter;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import eng.tz.la.core.AuditManager;
import it.osapulie.domain.ComuneISAServizio;
import it.osapulie.domain.Delega;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.servizi.Servizio;
import it.osapulie.service.DelegaService;
import it.osapulie.service.ServizioService;
import it.osapulie.shared.enumeration.AuthenticationChannel;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.util.audit.AuditConfiguration;
import it.osapulie.util.audit.AuditCustomMeta;
import it.osapulie.util.audit.AuditCustomSettyng;
import it.osapulie.web.UnauthorizedException;
import it.osapulie.web.portlet.util.PortletConstants;
import it.osapulie.web.portlet.util.PortletHelper;

/**
 * Interceptor per abiliare la visualizzazione delle portlet dei servizi attivi.
 *
 * @author Gianluca Pindinelli
 *
 */
public class ServicePortletAccessHandlerInterceptorAdapter extends HandlerInterceptorAdapter {

	@Inject
	private ServizioService servizioService;

	@Inject
	private DelegaService delegaService;

	@Inject
	private PortletHelper helper;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.web.portlet.handler.HandlerInterceptorAdapter#preHandle(javax.portlet
	 * .PortletRequest, javax.portlet.PortletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(PortletRequest request, PortletResponse response, Object handler) throws Exception {
		String uuidInSession=""; 
		 
		if(request.getPortletSession().getAttribute("UUID")!=null)
		uuidInSession=(String) request.getPortletSession().getAttribute("UUID");
		// Controllo se la portlet può essere renderizzata (servizio attivo o no)
		AuditManager auditManager =AuditConfiguration.configure().audit()// AuditManager.audit(AuditCustomSettyng.get()).setMetaActor(AuditCustomMeta.class)
				.addInfo("EVENT","ServicePortletAccessHandlerInterceptorAdapter -> preHandle");
		// Caricamento URL
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		String currentUrl = themeDisplay.getURLCurrent();
		auditManager.addInfo("PAGE", helper.getCurrentPageName(request))
		.addInfo("CURRENT-URL", currentUrl)
		.addInizioOperazione()
		.addPaginaCorrente(helper.getCurrentPageName(request))
		.addUrlRichiesta(currentUrl)
		.addUuidOperazione(uuidInSession);
		auditManager.addOperazioneRichiesta("InterceptorAdapter PreHandle::"); 
		if (currentUrl.contains("?")) {
			currentUrl = currentUrl.substring(0, currentUrl.indexOf("?"));
		}

		// Controllo locale
		Locale defaultLocale = LocaleUtil.getDefault();
		if (!defaultLocale.getLanguage().equals(themeDisplay.getLocale().getLanguage())) {
			Locale[] availableLocales = LanguageUtil.getAvailableLocales();
			if (availableLocales != null) {
				for (Locale locale : availableLocales) {
					if (currentUrl.startsWith("/" + locale.getLanguage())) {
						currentUrl = currentUrl.substring(currentUrl.indexOf("/" + locale.getLanguage()) + ("/" + locale.getLanguage()).length());
						break;
					}
				}
			}
		}
		auditManager.addOperazioneRichiesta("InterceptorAdapter PreHandle:: getServizioByUri ="+currentUrl);
		Servizio servizioByUri = servizioService.getServizioByUri(currentUrl);
		if (servizioByUri == null) {
			auditManager
			.addInfo("ESITO", "Service unavailable!").addEsitoError()
			.build();
			throw new UnavailableException("Service unavailable");
		}else {
		auditManager.addOperazioneRichiesta("InterceptorAdapter PreHandle:: SERVIZIO NOME ="+servizioByUri.getNomeServizio()+" SERVIZIO CODE ="+servizioByUri.getCodiceServizio()).addMetaField("Servizio", servizioByUri);
		}
		boolean attivo = servizioByUri.isAttivo();
		 
		if (!attivo) {
			auditManager
			.addInfo("ESITO", "Service Non Attivo!").addEsitoError()
			.build();
			throw new UnavailableException("Service unavailable");
		}
		
		// Controllo se il servizio è attivo per il comune selezionato e se è possibile accedere al
		// servizio in base al tipo di autenticazione (forte o debole o in base al livello)
		List<ComuneISAServizio> comuni = servizioByUri.getComuni();
		if (attivo && comuni != null) {
			UserPreferences userPreferences = helper.getUserPreferences(request);
			ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(request.getPortletSession());
			auditManager.addMetaField("UserPreferences", userPreferences)
			.addMetaField("ProfiloUtenteCittadino", profiloUtente);
			AuthenticationChannel authenticationChannel = AuthenticationChannel.CAS;
			if (profiloUtente.getCanaleAutenticazione() != null) {
				authenticationChannel = AuthenticationChannel.valueOf(profiloUtente.getCanaleAutenticazione().toUpperCase());
			}
			auditManager.addMetaField("AuthenticationChannel", authenticationChannel);
			// Controllo se il canale di autenticazione è attivo a livello di portale
			if (!helper.isAuthenticationChannelEnable(authenticationChannel)) {
				auditManager.addEsitoError().addInfo("ESITO", "UNAUTHORIZED_EXCEPTION_CANALE_NON_ATTIVO  "+PortletConstants.UNAUTHORIZED_EXCEPTION_CANALE_NON_ATTIVO)
				.build();
				throw new UnauthorizedException(PortletConstants.UNAUTHORIZED_EXCEPTION_CANALE_NON_ATTIVO);
			}

			// Se l'utente non ha selezionato un comune non può accedere ad alcun servizio
			Long idComuneIsa = userPreferences.getIdComuneIsa();
			if (idComuneIsa == null || idComuneIsa == PortletConstants.NO_COMUNE_ISA) {
				auditManager.addInfo("ESITO", "Service unavailable!").addEsitoError()
				.build(true);
				throw new UnavailableException("Service unavailable");
			}
			else {

				// Controllo accesso con delega...
				if (userPreferences.getIdDelega() != null) {
					auditManager.addInfo("CON DELEGA ", "DelegaId= "+userPreferences.getIdDelega());
					// Se il servizio non prevede utilizzo con delega...
					if (!servizioByUri.isDelega()) {
						auditManager.addEsitoError().addInfo("ESITO", "Il servizio non prevede utilizzo con delega. Service unavailable!")
						.build();
						throw new UnavailableException("Service unavailable");
					}
					// ...altrimenti verifico che la delega, se esiste per il servizio corrente,
					// risulti attiva e valida
					else {
						boolean delegaOk = false;
						Delega delegaAttuale = delegaService.getDelegaByPk(userPreferences.getIdDelega());
						if (delegaAttuale != null && delegaAttuale.isAttiva() && delegaAttuale.getComuneIsa().getId().equals(idComuneIsa)) {
							List<Servizio> serviziDelega = delegaAttuale.getServizi();
							if (serviziDelega != null) {
								for (Servizio servizioDelega : serviziDelega) {
									if (servizioDelega.getId().equals(servizioByUri.getId())) {
										delegaOk = true;
										break;
									}
								}
							}
						}

						if (!delegaOk) {
							auditManager.addEsitoError().addInfo("ESITO", "Delega non attiva o valida per il servizio corrente. Service unavailable!")
							.build();
							throw new UnavailableException("Service unavailable");
						}
					}
				}
				// ...altrimenti controllo accesso come azienda o cittadino
				else {
					// Controllo associazione profilo azienda <-> servizio per azienda (N.B.: va
					// prima del controllo cittadino!)
					if (userPreferences.getPartitaIvaServizio() != null && !userPreferences.getPartitaIvaServizio().equals("")) {
						if (!servizioByUri.isAzienda()) {
							auditManager.addEsitoError().addInfo("ESITO", "Service unavailable!")
							.build();
							throw new UnavailableException("Service unavailable");
						}
					}
					// Controllo associazione profilo cittadino <-> servizio per cittadino
					else if (userPreferences.getCodiceFiscaleServizio() != null && !userPreferences.getCodiceFiscaleServizio().equals("")) {
						if (!servizioByUri.isCittadino()) {
							auditManager.addEsitoError().addInfo("ESITO", "Service unavailable!")
							.build();
							throw new UnavailableException("Service unavailable");
						}
					}
				}
				for (ComuneISAServizio comuneISAServizio : comuni) {
					if (comuneISAServizio.getIdComuneISA() == idComuneIsa) {

						attivo = attivo && comuneISAServizio.isAttivo();
						if (!attivo) {
							auditManager.addEsitoError().addInfo("ESITO", "Servizio non attivo. Service unavailable!")
							.build();
							throw new UnavailableException("Service unavailable");
						}

						// Controllo accesso al servizio in base al canale di autenticazione
						switch (authenticationChannel) {
						case REGIONE_PUGLIA:
							// Controllo accesso in base al tipo di autenticazione
							// Autenticazione forte/debole
							if (comuneISAServizio.isAutenticazioneForte() && !profiloUtente.isAutenticazioneForte()) {
								auditManager.addEsitoError().addInfo("ESITO", "UNAUTHORIZED_EXCEPTION_AUTORIZZAZZIONI_INSUFFICIENTI_REGIONE_PUGLIA  "+PortletConstants.UNAUTHORIZED_EXCEPTION_AUTORIZZAZZIONI_INSUFFICIENTI_REGIONE_PUGLIA)
								.build();
								throw new UnauthorizedException(PortletConstants.UNAUTHORIZED_EXCEPTION_AUTORIZZAZZIONI_INSUFFICIENTI_REGIONE_PUGLIA);
							}
							break;
						case SPID:
							// Controllo che l'utente autenticato con livello di autenticazione
							// possa accedere al servizio
							if (profiloUtente.getLivelloAutenticazione() != null && comuneISAServizio.getLivelloAutenticazione() == null) {
								auditManager.addEsitoError().addInfo("ESITO", "Service unavailable!")
								.build();
								throw new UnavailableException("Service unavailable");
							}
							// Autenticazione a livelli
							if (profiloUtente.getLivelloAutenticazione() != null && (profiloUtente.getLivelloAutenticazione() < comuneISAServizio.getLivelloAutenticazione())) {
								auditManager.addEsitoError().addInfo("ESITO", "UNAUTHORIZED_EXCEPTION_AUTORIZZAZZIONI_INSUFFICIENTI_SPID  "+PortletConstants.UNAUTHORIZED_EXCEPTION_AUTORIZZAZZIONI_INSUFFICIENTI_SPID+ "_" + comuneISAServizio.getLivelloAutenticazione())
								.build(true);
								throw new UnauthorizedException(PortletConstants.UNAUTHORIZED_EXCEPTION_AUTORIZZAZZIONI_INSUFFICIENTI_SPID + "_" + comuneISAServizio.getLivelloAutenticazione());
							}
							break;
						default:
							break;
						}

						break;
					}
				}
			}  
		}
		if (!attivo) {
			auditManager.addEsitoError().addInfo("ESITO", "Request not authorized!")
			.build();
			throw new PortletSecurityException("Request not authorized");
		}
		auditManager.addEsitoSuccess().addInfo("ESITO", "Servizio->attivo=true").build();
		return attivo;
	}

	 @Override
	protected void afterCompletion(PortletRequest request, PortletResponse response, Object handler, Exception ex)
			throws Exception {
		
		 if(ex!=null) {
			String uuidInSession="";	
			 if(request.getPortletSession().getAttribute("UUID")!=null)
					uuidInSession=(String) request.getPortletSession().getAttribute("UUID");
					ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
					String currentUrl = themeDisplay.getURLCurrent();
					
		 AuditConfiguration.configure().audit()
					.addInfo("EVENT","ServicePortletAccessHandlerInterceptorAdapter -> afterCompletion")
					.addInfo("ESITO","Exception :"+(ex.getCause()!=null?ex.getCause().toString():"") +" - "+ex.getLocalizedMessage()+" - "+ex.getMessage())
					.addOperazioneRichiesta("InterceptorAdapter afterCompletion:: SERVIZIO RICHIESTO")
					.addInizioOperazione()
					.addPaginaCorrente(helper.getCurrentPageName(request))
					.addUrlRichiesta(currentUrl)
					.addUuidOperazione(uuidInSession)
					.addFineOperazione()
					.addEsitoError()
					.build();
		 }
		 
		super.afterCompletion(request, response, handler, ex);
	}
 
 
}
