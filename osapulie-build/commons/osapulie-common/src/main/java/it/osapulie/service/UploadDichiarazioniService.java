package it.osapulie.service;

import java.io.IOException;

import javax.portlet.PortletException;

import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.web.portlet.util.UploadItem;

/**
 * Servizio per i casi d'uso che prevedono upload di dichiarazioni con relativa verifica firma
 * digitale e successivo invio al comune.
 *
 * @author Maria Michela Birtolo
 * @author Gianluca Pindinelli
 */

public interface UploadDichiarazioniService {

	/**
	 * Metodo che prende i file uploadati, li verifica e li invia al comune.
	 *
	 * @param tipologia
	 * @param UploadItem
	 * @param
	 *
	 */
	@Deprecated
	public String processaUploadDichiarazioni(String tipologia, UploadItem uploadItem, ProfiloUtenteCittadino profiloUtente, UserPreferences userPreferences) throws PortletException, IOException;

	/**
	 * Metodo che prende i file uploadati, li verifica e li invia al comune.
	 *
	 * @param tipologia
	 * @param uploadItem
	 * @param userPreferences
	 * @return
	 * @throws PortletException
	 * @throws IOException
	 */
	public String processaUploadDichiarazioni(String tipologia, UploadItem uploadItem, UserPreferences userPreferences) throws PortletException, IOException;

}
