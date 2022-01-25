/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.infrastructure.security.impl;

import java.util.LinkedHashMap;

import javax.inject.Inject;
import javax.naming.NamingException;
import javax.portlet.PortletPreferences;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ContactLocalServiceUtil;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortletKeys;

import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.infrastructure.security.LDAPManager;
import it.osapulie.infrastructure.security.OSApulieUserDetails;
import it.osapulie.infrastructure.security.ProfilazioneUtenteException;
import it.osapulie.infrastructure.security.ProfilazioneUtenteService;
import it.osapulie.persistence.ProfiloUtenteCittadinoRepository;

/**
 *
 * @author Gianluca Pindinelli
 *
 */
@Transactional
@Service
public class ProfilazioneUtenteServiceImpl implements ProfilazioneUtenteService {

	protected Logger log = LoggerFactory.getLogger(ProfilazioneUtenteServiceImpl.class.getName());

	@Inject
	private ProfiloUtenteCittadinoRepository repositoryCittadini;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.infrastructure.security.ProfilazioneUtenteService#update(it.osapulie.
	 * infrastructure . security.impl.OSApulieUserDetails)
	 */
	@Override
	public OSApulieUserDetails update(OSApulieUserDetails osApulieUserDetails) throws ProfilazioneUtenteException {

		// Caricamento lista Server LDAP settati in Liferay Portal (da preferenze della portlet)
		// Aggiornamento su LDAP
		// Creazione mappa attributi
		LinkedHashMap<String, String> attributesMap = new LinkedHashMap<String, String>();

		attributesMap.put("sn", osApulieUserDetails.getLiferayUser().getLastName());
		attributesMap.put("givenName", osApulieUserDetails.getLiferayUser().getFirstName());
		attributesMap.put("mail", osApulieUserDetails.getLiferayUser().getEmailAddress());
		if (osApulieUserDetails.getProfiloUtenteCittadino().getPassword() != null && !osApulieUserDetails.getProfiloUtenteCittadino().getPassword().trim().equals("")) {
			attributesMap.put("userPassword", osApulieUserDetails.getProfiloUtenteCittadino().getPassword());
		}

		try {
			int ownerType = PortletKeys.PREFS_OWNER_TYPE_COMPANY;
			long plid = PortletKeys.PREFS_PLID_SHARED;
			String portletId = PortletKeys.LIFERAY_PORTAL;

			long companyId = osApulieUserDetails.getLiferayUser().getCompanyId();

			PortletPreferences preferences = PortletPreferencesLocalServiceUtil.getPreferences(companyId, companyId, ownerType, plid, portletId);

			String[] ldapServerIds = preferences.getValues("ldap.server.ids", null);
			if (ldapServerIds != null) {
				// Per ogni server LDAP aggiorno l'utente
				for (String ldapServerId : ldapServerIds) {
					String hostname = preferences.getValue("ldap.base.provider.url." + ldapServerId, "");
					String username = preferences.getValue("ldap.security.principal." + ldapServerId, "");
					String password = preferences.getValue("ldap.security.credentials." + ldapServerId, "");
					String users_ou = preferences.getValue("ldap.users.dn." + ldapServerId, "");
					String groups_ou = preferences.getValue("ldap.groups.dn." + ldapServerId, "");

					try {
						// Aggiornamento utente
						LDAPManager.getInstance(hostname, username, password, users_ou, groups_ou, null).updateUser(osApulieUserDetails.getProfiloUtenteCittadino().getCodiceFiscale(),
								osApulieUserDetails.getLiferayUser().getScreenName(), attributesMap);
					}
					catch (NamingException e) {
						log.error("update on LDAP failed :: " + e.getMessage(), e);
					}
				}
			}
		}
		catch (Exception e) {
			throw new ProfilazioneUtenteException("Errore durante il salvataggio dell'utente su LDAP :: user '" + osApulieUserDetails.getProfiloUtenteCittadino().getCodiceFiscale() + "'", e);
		}

		// Aggiornamento utente su Liferay Portal
		User liferayUser = osApulieUserDetails.getLiferayUser();
		if (liferayUser != null) {
			try {
				UserLocalServiceUtil.updateUser(liferayUser, true);

				// Aggiornamento contatto
				if (osApulieUserDetails.getLiferayContact() != null) {
					ContactLocalServiceUtil.updateContact(osApulieUserDetails.getLiferayContact(), true);
				}
			}
			catch (SystemException e) {
				throw new ProfilazioneUtenteException("Errore durante il salvataggio dell'utente sul Portale Liferay :: liferayUser : '" + liferayUser.getScreenName() + "'", e);
			}
			catch (NullPointerException e) {
				throw new ProfilazioneUtenteException("Errore durante il salvataggio dell'utente sul Portale Liferay :: liferayUser NULL : ", e);
			}
		}

		// Aggiornamento profilo utente su OSApulie
		ProfiloUtenteCittadino profiloUtente = osApulieUserDetails.getProfiloUtenteCittadino();

		repositoryCittadini.save(profiloUtente);

		return osApulieUserDetails;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.infrastructure.security.ProfilazioneUtenteService#findById(java.lang.String)
	 */
	@Override
	public OSApulieUserDetails getById(String id) throws ProfilazioneUtenteException {

		ProfiloUtenteCittadino profiloUtente = repositoryCittadini.findOne(Long.parseLong(id));

		User liferayUser = null;
		try {
			liferayUser = UserLocalServiceUtil.getUser(Long.parseLong(id));
		}
		catch (NoSuchUserException e) {
		}
		catch (NumberFormatException e) {
			throw new ProfilazioneUtenteException("Errore durante il caricamento dell'utente dal Portale Liferay :: liferayUser : '" + id + "'", e);
		}
		catch (PortalException e) {
			throw new ProfilazioneUtenteException("Errore durante il caricamento dell'utente dal Portale Liferay :: liferayUser : '" + id + "'", e);
		}
		catch (SystemException e) {
			throw new ProfilazioneUtenteException("Errore durante il caricamento dell'utente dal Portale Liferay :: liferayUser : '" + id + "'", e);
		}

		OSApulieUserDetails osApulieUserDetails = new OSApulieUserDetails(null, profiloUtente, liferayUser);

		return osApulieUserDetails;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.infrastructure.security.ProfilazioneUtenteService#deleteById(java.lang.String)
	 */
	@Override
	public void deleteById(String id) throws ProfilazioneUtenteException {

		try {
			// Eliminazione da ISA
			OSApulieUserDetails osApulieUserDetails = getById(id);

			ProfiloUtenteCittadino profiloUtente = osApulieUserDetails.getProfiloUtenteCittadino();
			repositoryCittadini.delete(profiloUtente);

			// Eliminazione da LDAP
			int ownerType = PortletKeys.PREFS_OWNER_TYPE_COMPANY;
			long plid = PortletKeys.PREFS_PLID_SHARED;
			String portletId = PortletKeys.LIFERAY_PORTAL;

			long companyId = osApulieUserDetails.getLiferayUser().getCompanyId();

			PortletPreferences preferences = PortletPreferencesLocalServiceUtil.getPreferences(companyId, companyId, ownerType, plid, portletId);
			String[] ldapServerIds = preferences.getValues("ldap.server.ids", null);
			if (ldapServerIds != null) {
				// Per ogni server LDAP aggiorno l'utente
				for (String ldapServerId : ldapServerIds) {
					String hostname = preferences.getValue("ldap.base.provider.url." + ldapServerId, "");
					String username = preferences.getValue("ldap.security.principal." + ldapServerId, "");
					String password = preferences.getValue("ldap.security.credentials." + ldapServerId, "");
					String users_ou = preferences.getValue("ldap.users.dn." + ldapServerId, "");
					String groups_ou = preferences.getValue("ldap.groups.dn." + ldapServerId, "");

					try {
						// Eliminazione utente
						LDAPManager.getInstance(hostname, username, password, users_ou, groups_ou, null).deleteUser(osApulieUserDetails.getLiferayUser().getScreenName());
					}
					catch (NamingException e) {
						log.error("update on LDAP failed :: " + e.getMessage(), e);
					}
				}
			}

		}
		catch (Exception e) {
			throw new ProfilazioneUtenteException("Errore durante l'eliminazione dell'utente : " + id, e);
		}

	}
}
