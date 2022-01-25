/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.profiloutente.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.liferay.portal.PwdEncryptorException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.User;

import it.osapulie.domain.Comune;
import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.Indirizzo;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.infrastructure.security.OSApulieUserDetails;
import it.osapulie.profiloutente.model.view.ProfiloUtenteModel;
import it.osapulie.web.portlet.util.PortletConstants;

/**
 * Classe di utility per l'oggetto {@link ProfiloUtenteModel}.
 *
 * @author Gianluca Pindinelli
 *
 */
public class ProfiloUtenteModelUtil {

	protected static Logger log = LoggerFactory.getLogger(ProfiloUtenteModelUtil.class.getName());

	public static ProfiloUtenteModel getProfiloUtenteFromOSApulieUserDetails(OSApulieUserDetails osApulieUserDetails) {

		log.debug("getProfiloUtenteFromOSApulieUserDetails :: entering method");

		ProfiloUtenteModel profiloUtenteModel = null;

		ProfiloUtenteCittadino profiloUtente = osApulieUserDetails.getProfiloUtenteCittadino();

		if (Validator.isNotNull(osApulieUserDetails)) {

			profiloUtenteModel = new ProfiloUtenteModel();

			if (osApulieUserDetails.isAutenticatoForte() || osApulieUserDetails.getProfiloUtenteCittadino().isAutenticazioneForte()) {
				profiloUtenteModel.setAutenticatoForte(true);
			}

			profiloUtenteModel.setAutenticazioneForte(osApulieUserDetails.getProfiloUtenteCittadino().isAutenticazioneForte());

			profiloUtenteModel.setNome(osApulieUserDetails.getLiferayUser().getFirstName());
			profiloUtenteModel.setSecondoNome(osApulieUserDetails.getLiferayUser().getMiddleName());
			profiloUtenteModel.setCognome(osApulieUserDetails.getLiferayUser().getLastName());
			try {
				profiloUtenteModel.setUomo(osApulieUserDetails.getLiferayUser().getMale());
			}
			catch (PortalException e) {
				log.warn("getProfiloUtenteFromOSApulieUserDetails :: " + e.getMessage(), e);
			}
			catch (SystemException e) {
				log.warn("getProfiloUtenteFromOSApulieUserDetails :: " + e.getMessage(), e);
			}

			try {
				profiloUtenteModel.setDataNascita(osApulieUserDetails.getLiferayUser().getBirthday());
			}
			catch (PortalException e) {
				log.warn("getProfiloUtenteFromOSApulieUserDetails :: " + e.getMessage(), e);
			}
			catch (SystemException e) {
				log.warn("getProfiloUtenteFromOSApulieUserDetails :: " + e.getMessage(), e);
			}

			if (Validator.isNotNull(profiloUtente.getResidenza()) && Validator.isNotNull(profiloUtente.getResidenza().getComune())) {
				profiloUtenteModel.setComuneResidenza(profiloUtente.getResidenza().getComune().getId());
				profiloUtenteModel.setComuneResidenzaString(profiloUtente.getResidenza().getComune().getDenominazione());
				profiloUtenteModel.setNrCivicoResidenza(profiloUtente.getResidenza().getNrCivico());
				profiloUtenteModel.setViaResidenza(profiloUtente.getResidenza().getVia());

			}

			if (profiloUtente.getComuneIsa() != null) {
				profiloUtenteModel.setComuneIsa(profiloUtente.getComuneIsa().getId());
				profiloUtenteModel.setComuneIsaString(profiloUtente.getComuneIsa().getNome());
			}

			// Il Codice Fiscale è lo screenName di Liferay
			profiloUtenteModel.setCodiceFiscale(osApulieUserDetails.getLiferayUser().getScreenName().toUpperCase());

			profiloUtenteModel.setMail(osApulieUserDetails.getLiferayUser().getEmailAddress());
			profiloUtenteModel.setMailPec(profiloUtente.getMailPec());

			if ((profiloUtente.getCanaleComunicazione() != null) && !profiloUtente.getCanaleComunicazione().equals("")) {
				profiloUtenteModel.setCanaleComunicazione(profiloUtente.getCanaleComunicazione());
			}
			else {
				profiloUtenteModel.setCanaleComunicazione(PortletConstants.CANALE_COMUNICAZIONE_EMAIL);
			}

			profiloUtenteModel.setUsername(osApulieUserDetails.getLiferayUser().getScreenName().toUpperCase());
			profiloUtenteModel.setDataAutenticazioneForte(osApulieUserDetails.getProfiloUtenteCittadino().getDataAutenticazioneForte());

			profiloUtenteModel.setCanaleAutenticazione(osApulieUserDetails.getAuthenticationChannel().getName());
			profiloUtenteModel.setLivelloAutenticazione(osApulieUserDetails.getProfiloUtenteCittadino().getLivelloAutenticazione());
		}

		return profiloUtenteModel;
	}

	public static OSApulieUserDetails getOSApulieUserDetailsFromProfiloUtente(ProfiloUtenteModel profiloUtenteModel, OSApulieUserDetails osApulieUserDetails) {

		log.debug("getOSApulieUserDetailsFromProfiloUtente :: entering method");

		if (Validator.isNotNull(profiloUtenteModel)) {

			// Aggiornamento dati OSApulie
			ProfiloUtenteCittadino profiloUtente = osApulieUserDetails.getProfiloUtenteCittadino();

			profiloUtente.setNome(profiloUtenteModel.getNome());
			profiloUtente.setCognome(profiloUtenteModel.getCognome());

			profiloUtente.setCodiceFiscale(profiloUtenteModel.getCodiceFiscale());
			profiloUtente.setMailPec(profiloUtenteModel.getMailPec());
			profiloUtente.setCanaleComunicazione(profiloUtenteModel.getCanaleComunicazione());
			if (Validator.isNotNull(profiloUtente.getResidenza())) {
				Indirizzo indirizzo = profiloUtente.getResidenza();
				indirizzo.setNrCivico(profiloUtenteModel.getNrCivicoResidenza());
				indirizzo.setVia(profiloUtenteModel.getViaResidenza());
				if (profiloUtenteModel.getComuneResidenza() != 0) {
					if (Validator.isNotNull(profiloUtente.getResidenza().getComune())) {
						indirizzo.getComune().setId(profiloUtenteModel.getComuneResidenza());
					}
					else {
						Comune comune = new Comune();
						comune.setId(profiloUtenteModel.getComuneResidenza());
						indirizzo.setComune(comune);
						profiloUtente.setResidenza(indirizzo);
					}
				}
			}
			else {
				Indirizzo indirizzo = new Indirizzo();
				indirizzo.setNrCivico(profiloUtenteModel.getNrCivicoResidenza());
				indirizzo.setVia(profiloUtenteModel.getViaResidenza());
				Comune comune = new Comune();
				comune.setId(profiloUtenteModel.getComuneResidenza());
				indirizzo.setComune(comune);
				profiloUtente.setResidenza(indirizzo);
			}

			ComuneISA comuneISA = profiloUtenteModel.getComuneISAObject();
			profiloUtente.setComuneIsa(comuneISA);
			profiloUtente.setMail(profiloUtenteModel.getMail());
			profiloUtente.setPassword(profiloUtenteModel.getPassword());

			// Aggiornamento dati Liferay
			User liferayUser = osApulieUserDetails.getLiferayUser();

			liferayUser.setFirstName(profiloUtenteModel.getNome());
			liferayUser.setMiddleName(profiloUtenteModel.getSecondoNome());
			liferayUser.setLastName(profiloUtenteModel.getCognome());

			liferayUser.setEmailAddress(profiloUtenteModel.getMail());
			liferayUser.setScreenName(profiloUtenteModel.getUsername().toUpperCase());
			try {
				liferayUser.setPassword(PwdEncryptor.encrypt(profiloUtenteModel.getPassword()));
			}
			catch (PwdEncryptorException e1) {
				log.error("getOSApulieUserDetailsFromProfiloUtente :: " + e1.getMessage(), e1);
			}
			liferayUser.setPasswordUnencrypted(profiloUtenteModel.getPassword());
			try {
				Contact contact = liferayUser.getContact();
				contact.setBirthday(profiloUtenteModel.getDataNascita());
				contact.setMale(profiloUtenteModel.isUomo());
				osApulieUserDetails.setLiferayContact(contact);
			}
			catch (PortalException e) {
				log.error("getOSApulieUserDetailsFromProfiloUtente :: " + e.getMessage(), e);
			}
			catch (SystemException e) {
				log.error("getOSApulieUserDetailsFromProfiloUtente :: " + e.getMessage(), e);
			}
		}

		return osApulieUserDetails;

	}
}
