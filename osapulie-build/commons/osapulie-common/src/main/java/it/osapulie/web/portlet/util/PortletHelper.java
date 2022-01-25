/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.web.portlet.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.portlet.PortletModeException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.ResourceResponse;
import javax.portlet.WindowStateException;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.dto.UserInfo;
import it.osapulie.infrastructure.security.OSApulieUserDetails;
import it.osapulie.shared.enumeration.AuthenticationChannel;
import it.osapulie.shared.service.UserPreferences;

/**
 * Helper per il presentation layer: colleziona una serie di metodi utili per controller e view.
 *
 * @author Mario Scalas
 * @author Gianluca Pindinelli
 */
public interface PortletHelper {

	void sendStream(InputStream sourceStream, ResourceResponse response, String mimeType);

	void sendStream(byte[] bytes, ResourceResponse response, String mimeType);

	/**
	 * Serve a {@link ResourceResponse} by setting the attachment response header so that clients
	 * will know the file name to save the file as.
	 *
	 * @param bytes the content bytes
	 * @param response the portlet's {@link ResourceResponse}
	 * @param attachmentName the filename for the attachment content (i.e., "sample.pdf")
	 * @param mimeType the mime type
	 */
	void sendStreamAsAttachment(byte[] bytes, ResourceResponse response, String attachmentName, String mimeType);

	/**
	 * Copy a stream from an input source to an output destination.
	 *
	 * @param sourceStream
	 * @param destination
	 */
	void copyStream(InputStream sourceStream, OutputStream destination);

	/**
	 * Questo metodo incapsula un workaround perchè durante il post alcuni ID sono duplicati e
	 * questo non piace a Spring MVC: quindi dobbiamo leggerli noi dalla request e in qualche modo
	 * aggirare il problema.
	 *
	 * @param parmName il nome del parametro nella request
	 * @param request la portlet request
	 * @return il valore del parametro
	 */
	Long getLongParam(String parmName, PortletRequest request);

	/**
	 * Recupera le informazioni riguardo l'utente corrente.
	 *
	 * @param request la {@link PortletRequest} corrente
	 * @return
	 */
	UserInfo getUserInfo(PortletRequest request);

	/**
	 * Ottiene il nome della pagina. Ad esempio, se la URL del portale è <code>
	 *
	 * <pre>
	 * http://localhost
	 * :8080//en/web/guest/opere?p_p_id=audio_WAR_mbifrontendportlet&p_p_lifecycle=2&
	 * p_p_state=normal ... altri parametri ...
	 * </pre>
	 *
	 * </code> ritorna <code>
	 *
	 * <pre>
	 * opere
	 * </pre>
	 *
	 * </code>
	 *
	 * @param request
	 * @return il nome della pagina
	 */
	String getCurrentPageName(PortletRequest request);

	/**
	 * Recupera l'utente corrente.
	 *
	 * @return
	 */
	OSApulieUserDetails getOsApulieUserDetails();

	/**
	 * Prende dalla sessione il profilo utente relativo al cittadino connesso al portale
	 *
	 * @param session
	 * @return
	 */
	ProfiloUtenteCittadino getProfiloUtente(PortletSession session);

	/**
	 * Recupera le user preferences dalla sessione http.
	 *
	 * @param portletRequest
	 * @return
	 */
	UserPreferences getUserPreferences(PortletRequest portletRequest);

	/**
	 * Controlla che lo user appartenga al ruolo specificato.
	 *
	 * @param liferayUser
	 * @param roleName
	 * @return
	 */
	boolean userHasRole(User liferayUser, String roleName);

	/**
	 * Aggiunge il ruolo all'utente passato in input
	 *
	 * @param username
	 * @param roleName
	 */
	void addRoleUser(String username, String roleName);

	/**
	 * Rimuove il ruolo all'utente passato in input
	 *
	 * @param username
	 * @param roleName
	 */
	void removeRoleUser(String username, String roleName);

	/**
	 * Ritorna la tipologia di scenario di deployment attualmente configurata per ISA. Gli scenari
	 * possibili sono descritti nella documentazione di progetto.
	 *
	 * @return
	 */
	int getDeploymentScenario();

	/**
	 * Ritolrna l'array dei canali di autenticazione abilitati sul portale per lo scenario di
	 * deployment 3 (IDPs).
	 *
	 * @return
	 */
	AuthenticationChannel[] getScenario3AuthenticationChannels();

	/**
	 * Verifica che il canale di autenticazione passato in input sia abilitato sul portale.
	 *
	 * @param authenticationChannel
	 * @return
	 */
	boolean isAuthenticationChannelEnable(AuthenticationChannel authenticationChannel);

	/**
	 * Crea la portlet URL corrente in base ai parametri passati in input.
	 *
	 * @param request
	 * @param parameters
	 * @param portletId (opzionale) se non settato imposta la URL con il portletID attuale
	 * @param lifecycle (opzionale) se non settato imposta la URL con il lifecycle "RENDER_PHASE"
	 * @return
	 * @throws WindowStateException
	 * @throws PortletModeException
	 * @throws SystemException
	 * @throws PortalException
	 */
	String createPortletURL(PortletRequest request, Map<String, String> parameters, String portletId, String lifecycle) throws Exception;
}
