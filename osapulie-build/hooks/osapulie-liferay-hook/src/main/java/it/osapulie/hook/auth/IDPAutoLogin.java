/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.hook.auth;

import java.util.Calendar;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;
import com.liferay.portal.DuplicateUserEmailAddressException;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.AutoLogin;
import com.liferay.portal.security.auth.AutoLoginException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.PwdGenerator;

import it.osapulie.domain.Comune;
import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.Indirizzo;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.hook.utils.ApplicationBootstrap;
import it.osapulie.hook.utils.PwdEncryptor;
import it.osapulie.infrastructure.security.OSApulieUserDetails;
import it.osapulie.infrastructure.security.ProfilazioneUtenteException;
import it.osapulie.shared.enumeration.AuthenticationChannel;
import it.osapulie.shared.enumeration.SPID;
import it.osapulie.web.portlet.util.PortletHelper;

/**
 * Implementa le funzionalià di login per lo scenario con IDP (Regione Puglia e SPID).
 *
 * @author Gianluca Pindinelli
 *
 */
public class IDPAutoLogin implements AutoLogin {

	protected Logger log = LoggerFactory.getLogger(IDPAutoLogin.class.getName());

	private final static String AUTO_LOGIN_IDP_URL = "/idp-login,/group/guest";
	private final static String AUTO_LOGIN_REDIRECT_ANONYMOUS_USER_URL = "/c/portal/login";
	private final static String AUTO_LOGIN_REDIRECT_GENERIC_ERROR_URL = "/errore-login";
	private final static String AUTO_LOGIN_REDIRECT_DUPLICATED_EMAIL_ERROR_URL = "/errore-login-email-duplicata";
	private final static String AUTO_LOGIN_REDIRECT_ACCOUNT_IDP_REGIONE_PUGLIA_DISABLED_ERROR_URL = "/errore-login-account-regione-puglia-disabilitato";

	private String autoLoginIdpUrl = AUTO_LOGIN_IDP_URL;
	private String autoLoginRedirectAnonymousUserUrl = AUTO_LOGIN_REDIRECT_ANONYMOUS_USER_URL;
	private String autoLoginRedirectGenericErrorUrl = AUTO_LOGIN_REDIRECT_GENERIC_ERROR_URL;
	private String autoLoginRedirectDuplicatedEmailErrorUrl = AUTO_LOGIN_REDIRECT_DUPLICATED_EMAIL_ERROR_URL;
	private String autoLoginRedirectAccountIdpRegionePugliaDisabledErrorUrl = AUTO_LOGIN_REDIRECT_ACCOUNT_IDP_REGIONE_PUGLIA_DISABLED_ERROR_URL;

	/**
	 *
	 */
	public IDPAutoLogin() {
		if (PropsUtil.get("auto.login.idp.url") != null && !PropsUtil.get("auto.login.idp.url").equals("")) {
			autoLoginIdpUrl = PropsUtil.get("auto.login.idp.url");
		}
		if (PropsUtil.get("auto.login.redirect.anonymous.user.url") != null && !PropsUtil.get("auto.login.redirect.anonymous.user.url").equals("")) {
			autoLoginRedirectAnonymousUserUrl = PropsUtil.get("auto.login.redirect.anonymous.user.url");
		}
		if (PropsUtil.get("auto.login.redirect.generic.error.url") != null && !PropsUtil.get("auto.login.redirect.generic.error.url").equals("")) {
			autoLoginRedirectGenericErrorUrl = PropsUtil.get("auto.login.redirect.generic.error.url");
		}
		if (PropsUtil.get("auto.login.redirect.duplicated.email.error.url") != null && !PropsUtil.get("auto.login.redirect.duplicated.email.error.url").equals("")) {
			autoLoginRedirectDuplicatedEmailErrorUrl = PropsUtil.get("auto.login.redirect.duplicated.email.error.url");
		}
		if (PropsUtil.get("auto.login.redirect.account.idp.regione.puglia.disabled.error.url") != null
				&& !PropsUtil.get("auto.login.redirect.account.idp.regione.puglia.disabled.error.url").equals("")) {
			autoLoginRedirectAccountIdpRegionePugliaDisabledErrorUrl = PropsUtil.get("auto.login.redirect.account.idp.regione.puglia.disabled.error.url");
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.liferay.portal.security.auth.AutoLogin#login(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String[] login(HttpServletRequest request, HttpServletResponse response) throws AutoLoginException {

		log.debug("login :: entering method");

		boolean checkLogin = false;
		String[] autoLoginIdpUrls = autoLoginIdpUrl.split(",");
		for (String string : autoLoginIdpUrls) {
			if (request.getRequestURI().contains(string)) {
				checkLogin = true;
			}
		}

		if (!checkLogin) {
			return null;
		}

		String[] credentials = null;
		User user;
		try {
			// Tipologia IDP
			AuthenticationChannel authenticationChannel = AuthenticationChannel.REGIONE_PUGLIA;

			// Lettura attributi rilasciati dagli IDP dall'header della request
			String codiceFiscale = request.getHeader("codicefiscale");
			if (codiceFiscale == null || codiceFiscale.equals("")) {
				codiceFiscale = request.getHeader("fiscalNumber");
				if (codiceFiscale != null && !codiceFiscale.equals("")) {
					authenticationChannel = AuthenticationChannel.SPID;
					// Eliminazione prefisso "TINIT-"
					codiceFiscale = codiceFiscale.replaceAll("TINIT-", "");
				}
			}

			PortletHelper portletHelper = ApplicationBootstrap.INSTANCE.getPortletHelper();
			// Controllo attivazione canale di autenticazione utilizzato
			if (!portletHelper.isAuthenticationChannelEnable(authenticationChannel)) {
				request.setAttribute(AutoLogin.AUTO_LOGIN_REDIRECT, autoLoginRedirectGenericErrorUrl);
				return null;
			}

			if (codiceFiscale == null || codiceFiscale.equals("")) {
				return null;
			}

			// Controllo utente "anonimo"
			if (codiceFiscale.equals("anonimo")) {
				request.setAttribute(AutoLogin.AUTO_LOGIN_REDIRECT, autoLoginRedirectAnonymousUserUrl);
				return null;
			}

			codiceFiscale = codiceFiscale.toLowerCase();

			credentials = null;

			long companyId = PortalUtil.getCompanyId(request);

			String nome = null;
			String cognome = null;
			String userEnabled = null;
			String userIdentified = null;
			String mail = null;
			String pec = null;
			String comuneResidenza = null;
			String indirizzoResidenza = null;
			String homePhone = null;
			String mobile = null;
			String infoAggiuntive = null;
			Integer livelloAutenticazione = null;

			if (authenticationChannel.equals(AuthenticationChannel.REGIONE_PUGLIA)) {
				nome = request.getHeader("nome");
				cognome = request.getHeader("cognome");
				userEnabled = request.getHeader("userEnabled");
				userIdentified = request.getHeader("userIdentified");
				mail = request.getHeader("mail");
				pec = request.getHeader("pec");
				comuneResidenza = request.getHeader("comuneresidenza");
				indirizzoResidenza = request.getHeader("indirizzoresidenza");
				homePhone = request.getHeader("homephone");
				mobile = request.getHeader("mobile");
			}
			else if (authenticationChannel.equals(AuthenticationChannel.SPID)) {
				nome = request.getHeader(SPID.ATTRIBUTE_NAME.toString());
				cognome = request.getHeader(SPID.ATTRIBUTE_FAMILYNAME.toString());
				userEnabled = "true";
				String spidLevel = request.getHeader(SPID.ATTRIBUTE_SHIB_AUTHENTICATION_METHOD.toString());
				spidLevel = spidLevel.substring(spidLevel.length() - 1);

				livelloAutenticazione = Integer.parseInt(spidLevel);

				mail = request.getHeader(SPID.ATTRIBUTE_EMAIL.toString());
				pec = request.getHeader(SPID.ATTRIBUTE_DIGITALADDRESS.toString());
				indirizzoResidenza = request.getHeader(SPID.ATTRIBUTE_ADDRESS.toString());
				mobile = request.getHeader(SPID.ATTRIBUTE_MOBILEPHONE.toString());
				infoAggiuntive = getInfoAggiuntive(request);
			}

			log.debug("codicefiscale: " + codiceFiscale);
			log.debug("nome: " + nome);
			log.debug("cognome: " + cognome);
			log.debug("userEnabled: " + userEnabled);
			log.debug("userIdentified: " + userIdentified);
			log.debug("mail: " + mail);
			log.debug("pec: " + pec);
			log.debug("comuneResidenza: " + comuneResidenza);
			log.debug("indirizzoResidenza: " + indirizzoResidenza);
			log.debug("homePhone: " + homePhone);
			log.debug("mobile: " + mobile);
			log.debug("infoAggiuntive: " + infoAggiuntive);

			user = null;

			log.info("login :: username : " + codiceFiscale);

			// Generazione password - è necessaria per simulare il funzionamento del portale
			// durante la fase di login in cui avviene la generazione dei cookie necessari al
			// riconoscimento dell'utente loggato fuori dal contesto di liferay (ad es. nelle
			// servlet)
			String password = PwdGenerator.getPassword();

			// Controllo esistenza utente nel sistema
			try {
				user = UserLocalServiceUtil.getUserByScreenName(companyId, codiceFiscale);

				OSApulieUserDetails osApulieUserDetails = ApplicationBootstrap.INSTANCE.getProfilazioneUtenteService().getById(String.valueOf(user.getUserId()));

				ProfiloUtenteCittadino profiloUtenteCittadino = osApulieUserDetails.getProfiloUtenteCittadino();

				// Controllo che l'utente, se l'accesso è mediante IDP Regione Puglia, non abbia già
				// effettuato un accesso con SPID; in tal caso redirect verso la pagina di errore
				// corrispondente
				if (authenticationChannel.equals(AuthenticationChannel.REGIONE_PUGLIA)) {
					if (profiloUtenteCittadino.getCanaleAutenticazione() != null && profiloUtenteCittadino.getCanaleAutenticazione().equals(AuthenticationChannel.SPID.getName())) {
						log.info("login :: user whith CF: '" + codiceFiscale + "' has already logged in with the SPID account :: redirect user to error page");
						request.setAttribute(AutoLogin.AUTO_LOGIN_REDIRECT, autoLoginRedirectAccountIdpRegionePugliaDisabledErrorUrl);
						return null;
					}

				}
				user = osApulieUserDetails.getLiferayUser();

				// Aggiornamento dati LiferayUser
				user.setScreenName(codiceFiscale);
				user.setEmailAddress(mail);
				user.setFirstName(nome);
				user.setLastName(cognome);
				user.setPassword(PwdEncryptor.encrypt(password));
				user.setPasswordUnencrypted(password);
				user.setActive(Boolean.parseBoolean(userEnabled));
				user.setAgreedToTermsOfUse(true);

				user = UserLocalServiceUtil.updateUser(user);

				// Update dati ProfiloUtenteCittadino
				profiloUtenteCittadino.setCognome(cognome);
				profiloUtenteCittadino.setNome(nome);
				profiloUtenteCittadino.setMail(mail);
				profiloUtenteCittadino.setMailPec(pec);
				profiloUtenteCittadino.setPassword(password);
				profiloUtenteCittadino.setAutenticazioneForte(Boolean.parseBoolean(userIdentified));
				profiloUtenteCittadino.setCanaleAutenticazione(authenticationChannel.getName());
				profiloUtenteCittadino.setLivelloAutenticazione(livelloAutenticazione);
				profiloUtenteCittadino.setInfoAggiuntive(infoAggiuntive);

				Indirizzo residenza = profiloUtenteCittadino.getResidenza();
				if (Validator.isNotNull(residenza)) {
					if (Validator.isNotNull(indirizzoResidenza)) {
						residenza.setVia(indirizzoResidenza);
					}
					if (Validator.isNotNull(comuneResidenza)) {
						try {
							Comune comune = ApplicationBootstrap.INSTANCE.getComuneRepository().findByCodiciIstat(comuneResidenza);
							residenza.setComune(comune);
						}
						catch (Exception e) {
							log.error("login :: " + e.getMessage(), e);
						}
					}
					else {
						residenza.setComune(null);
					}
				}

				if (Validator.isNotNull(comuneResidenza)) {
					ComuneISA comuneIsa = profiloUtenteCittadino.getComuneIsa();

					if (comuneIsa != null && comuneIsa.getId() == -1) {
						try {
							ComuneISA comuneISA = ApplicationBootstrap.INSTANCE.getComuneISARepository().findByCodiceIstat(comuneResidenza);
							if (comuneISA != null) {
								profiloUtenteCittadino.setComuneIsa(comuneISA);
							}
						}
						catch (Exception e) {
							log.error("login :: " + e.getMessage(), e);
						}
					}
				}

				// Aggiornamento profilo utente (OSApulieUserDetails)
				log.info("login :: updating user " + codiceFiscale);

				ApplicationBootstrap.INSTANCE.getProfilazioneUtenteService().update(osApulieUserDetails);

				log.info("login :: user " + codiceFiscale + " updated");
			}
			catch (NoSuchUserException e) {
				log.info("login :: user don't exists : " + codiceFiscale);
			}
			catch (SystemException e) {
				log.error("login :: error when adding a new user : CF: '" + codiceFiscale + "', email address: '" + mail + "' - cause: " + e.getMessage(), e);
				if (e.getMessage().contains("org.hibernate.exception.ConstraintViolationException")) {
					log.info("login :: redirect user to error page");
					request.setAttribute(AutoLogin.AUTO_LOGIN_REDIRECT, autoLoginRedirectDuplicatedEmailErrorUrl);
				}
				return null;
			}
			catch (PortalException e) {
				log.error("login :: " + e.getMessage(), e);
			}
			catch (ProfilazioneUtenteException e) {
				log.error("login :: " + e.getMessage(), e);
			}

			// Aggiunta nuovo utente nel sistema (è sufficiente creare l'oggetto LiferayUser in
			// quanto
			// poi sarà Liferay a fare il resto su ISA (mediante onAfterCreate in UserListener)
			if (user == null) {
				ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

				Locale locale = LocaleUtil.getDefault();

				if (themeDisplay != null) {
					locale = themeDisplay.getLocale();
				}

				log.info("login :: add user " + codiceFiscale);

				try {
					user = addUser(companyId, nome, cognome, mail, codiceFiscale, password, locale, null, null);
					log.info("login :: user " + codiceFiscale + " added");
				}
				catch (Exception e) {
					log.error("login :: error when adding a new user : CF: '" + codiceFiscale + "', email address: '" + mail + "' - cause: " + e.getMessage(), e);
					if (e instanceof DuplicateUserEmailAddressException) {
						log.info("login :: redirect user to error page");
						request.setAttribute(AutoLogin.AUTO_LOGIN_REDIRECT, autoLoginRedirectDuplicatedEmailErrorUrl);
					}
					return null;
				}

				// Aggiornamento profiloUtenteCittadino dopo inserimento effettuato da
				// onAfterInsert.
				// E' necessario aspettare che avvenga l'insert da parte del listener.
				log.info("login :: updating user " + codiceFiscale);
				OSApulieUserDetails osApulieUserDetails = null;

				// Attesa inserimento profiloUtenteCittadino (max 5 sec)
				int countDown = 5;
				while (true) {
					if (--countDown == 0 || (osApulieUserDetails != null && osApulieUserDetails.getProfiloUtenteCittadino() != null)) {
						break;
					}
					try {
						Thread.sleep(1000);
					}
					catch (InterruptedException e) {
					}
					try {
						osApulieUserDetails = ApplicationBootstrap.INSTANCE.getProfilazioneUtenteService().getById(String.valueOf(user.getUserId()));
					}
					catch (Exception e) {
						log.error("login :: " + e.getMessage(), e);
					}
				}

				ProfiloUtenteCittadino profiloUtenteCittadino = osApulieUserDetails.getProfiloUtenteCittadino();

				if (osApulieUserDetails != null && profiloUtenteCittadino != null) {
					profiloUtenteCittadino.setMail(mail);
					profiloUtenteCittadino.setMailPec(pec);
					profiloUtenteCittadino.setAutenticazioneForte(Boolean.parseBoolean(userIdentified));
					profiloUtenteCittadino.setCanaleAutenticazione(authenticationChannel.getName());
					profiloUtenteCittadino.setLivelloAutenticazione(livelloAutenticazione);
					profiloUtenteCittadino.setInfoAggiuntive(infoAggiuntive);

					Indirizzo residenza = profiloUtenteCittadino.getResidenza();
					if (Validator.isNotNull(residenza)) {
						if (Validator.isNotNull(indirizzoResidenza)) {
							residenza.setVia(indirizzoResidenza);
						}
						if (Validator.isNotNull(comuneResidenza)) {
							try {
								Comune comune = ApplicationBootstrap.INSTANCE.getComuneRepository().findByCodiciIstat(comuneResidenza);
								residenza.setComune(comune);
							}
							catch (Exception e) {
								log.error("login :: " + e.getMessage(), e);
							}
						}
						else {
							residenza.setComune(null);
						}
					}

					if (Validator.isNotNull(comuneResidenza)) {
						ComuneISA comuneIsa = profiloUtenteCittadino.getComuneIsa();

						if (comuneIsa != null && comuneIsa.getId() == -1) {
							try {
								ComuneISA comuneISA = ApplicationBootstrap.INSTANCE.getComuneISARepository().findByCodiceIstat(comuneResidenza);
								if (comuneISA != null) {
									profiloUtenteCittadino.setComuneIsa(comuneISA);
								}
							}
							catch (Exception e) {
								log.error("login :: " + e.getMessage(), e);
							}
						}
					}

					try {
						ApplicationBootstrap.INSTANCE.getProfilazioneUtenteService().update(osApulieUserDetails);
						log.info("login :: user updated : " + codiceFiscale);
					}
					catch (ProfilazioneUtenteException e) {
						log.error("login :: " + e.getMessage(), e);
					}
				}
				else {
					log.error("login :: error during update of profiloUtenteCittadino for user : " + codiceFiscale);
				}
			}

			if (user == null) {
				return null;
			}

			credentials = new String[3];

			credentials[0] = String.valueOf(user.getUserId());
			credentials[1] = user.getPassword();
			credentials[2] = Boolean.TRUE.toString();
		}
		catch (Exception e) {
			log.error("login :: " + e.getMessage(), e);
			log.info("login :: redirect user to error page");
			request.setAttribute(AutoLogin.AUTO_LOGIN_REDIRECT, autoLoginRedirectGenericErrorUrl);
			return null;
		}

		return credentials;
	}

	/**
	 * @param request
	 * @return
	 */
	private String getInfoAggiuntive(HttpServletRequest request) {

		String infoAggiuntive = null;
		try {
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty(SPID.ATTRIBUTE_SPIDCODE.toString(), request.getHeader(SPID.ATTRIBUTE_SPIDCODE.toString()));
			jsonObject.addProperty(SPID.ATTRIBUTE_PLACEOFBIRTH.toString(), request.getHeader(SPID.ATTRIBUTE_PLACEOFBIRTH.toString()));
			jsonObject.addProperty(SPID.ATTRIBUTE_DATEOFBIRTH.toString(), request.getHeader(SPID.ATTRIBUTE_DATEOFBIRTH.toString()));
			jsonObject.addProperty(SPID.ATTRIBUTE_GENDER.toString(), request.getHeader(SPID.ATTRIBUTE_GENDER.toString()));
			jsonObject.addProperty(SPID.ATTRIBUTE_COMPANYNAME.toString(), request.getHeader(SPID.ATTRIBUTE_COMPANYNAME.toString()));
			jsonObject.addProperty(SPID.ATTRIBUTE_REGISTEREDOFFICE.toString(), request.getHeader(SPID.ATTRIBUTE_REGISTEREDOFFICE.toString()));
			jsonObject.addProperty(SPID.ATTRIBUTE_IVACODE.toString(), request.getHeader(SPID.ATTRIBUTE_IVACODE.toString()));
			jsonObject.addProperty(SPID.ATTRIBUTE_IDCARD.toString(), request.getHeader(SPID.ATTRIBUTE_IDCARD.toString()));

			infoAggiuntive = jsonObject.toString();
		}
		catch (Exception e) {
			log.error("getInfoAggiuntive :: " + e.getMessage(), e);
		}
		return infoAggiuntive;
	}

	/**
	 * Crea un nuovo utente in Liferay.
	 *
	 * @param companyId
	 * @param firstName
	 * @param lastName
	 * @param emailAddress
	 * @param screenName
	 * @param locale
	 * @param groupIds
	 * @param roleIds
	 * @param birtday
	 * @return
	 * @throws Exception
	 */
	protected User addUser(long companyId, String firstName, String lastName, String emailAddress, String screenName, String password, Locale locale, long[] groupIds, long[] roleIds)
			throws Exception {

		if (firstName == null) {
			firstName = StringPool.BLANK;
		}

		if (lastName == null) {
			lastName = StringPool.BLANK;
		}

		long creatorUserId = 0;
		boolean autoPassword = false;
		String password1 = password;
		String password2 = password1;
		boolean autoScreenName = false;
		long facebookId = 0;
		String openId = StringPool.BLANK;
		String middleName = StringPool.BLANK;
		int prefixId = 0;
		int suffixId = 0;
		boolean male = true;
		int birthdayMonth = Calendar.JANUARY;
		int birthdayDay = 1;
		int birthdayYear = 1970;
		String jobTitle = StringPool.BLANK;
		long[] organizationIds = null;
		long[] userGroupIds = null;
		boolean sendEmail = false;
		ServiceContext serviceContext = new ServiceContext();

		User user = UserLocalServiceUtil.addUser(creatorUserId, companyId, autoPassword, password1, password2, autoScreenName, screenName, emailAddress, facebookId, openId, locale, firstName,
				middleName, lastName, prefixId, suffixId, male, birthdayMonth, birthdayDay, birthdayYear, jobTitle, groupIds, organizationIds, roleIds, userGroupIds, sendEmail, serviceContext);

		user.setAgreedToTermsOfUse(true);

		UserLocalServiceUtil.updateUser(user);

		return user;
	}
}
