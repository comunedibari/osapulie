/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.servizicomune.web.portlet.gestioneutenti.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import com.liferay.portal.DuplicateUserEmailAddressException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.PwdGenerator;

import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.service.ProfiloUtenteService;
import it.osapulie.servizicomune.web.portlet.BaseController;
import it.osapulie.servizicomune.web.portlet.gestioneutenti.form.ProfiloUtenteCittadinoForm;
import it.osapulie.servizicomune.web.portlet.gestioneutenti.validator.ProfiloUtenteCittadinoValidator;
import it.osapulie.web.portlet.util.CommunicationException;
import it.osapulie.web.portlet.util.PortletConstants;
import it.osapulie.web.portlet.util.PortletHelper;
import it.osapulie.web.portlet.util.RoleConstants;
import it.osapulie.web.portlet.util.SenderHelper;

/**
 * Portlet controller per la gestione degli utenti del portale.
 *
 * @author Gianluca Pindinelli
 * @author Antonio Magrì
 *
 */
@Controller("gestioneUtentiPortletController")
@RequestMapping("view")
@SessionAttributes({ "profiloUtenteCittadinoForm", "returnUrl" })
public class GestioneUtentiPortletController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(GestioneUtentiPortletController.class);

	@Autowired
	public ResourceBundleMessageSource messageSource;

	@Autowired
	private ProfiloUtenteService profiloUtenteService;

	@Autowired
	private SenderHelper senderHelper;

	@Autowired
	private PortletHelper portletHelper;

	@Autowired
	@Qualifier("profiloUtenteCittadinoValidator")
	private ProfiloUtenteCittadinoValidator profiloUtenteCittadinoValidator;

	/**
	 * Default entry quando la portlet viene visualizzata per la prima volta.
	 *
	 * @param model il view model da popolare
	 * @return il view ID da renderizzare
	 */
	@RenderMapping
	public String homePage(@RequestParam(required = false) String codiceFiscale, @RequestParam(required = false) String returnUrl,
			@ModelAttribute ProfiloUtenteCittadinoForm profiloUtenteCittadinoForm, Model model, RenderRequest request) {

		if (codiceFiscale != null) {
			profiloUtenteCittadinoForm.setCodiceFiscale(codiceFiscale);
		}

		if (returnUrl != null) {
			try {
				model.addAttribute(PortletConstants.GESTIONE_UTENTI_RETURN_URL_REQUEST_PARAMETER, URLDecoder.decode(returnUrl, "UTF-8"));
			}
			catch (UnsupportedEncodingException e) {
				log.error("homePage :: " + e.getMessage(), e);
			}
		}

		return toLocalViewPath("home");
	}

	@ModelAttribute("profiloUtenteCittadinoForm")
	public ProfiloUtenteCittadinoForm getProfiloUtenteCittadinoForm() {
		return new ProfiloUtenteCittadinoForm();
	}

	/**
	 * Verifica se il campo password può essere settato o meno in fase di creazione utente.
	 * 
	 * @return
	 */
	@ModelAttribute("passwordEnable")
	public boolean isPasswordEnable() {

		boolean passwordEnable = false;

		User liferayUser = portletHelper.getOsApulieUserDetails().getLiferayUser();

		if (portletHelper.userHasRole(liferayUser, RoleConstants.RESPONSABILE_CAF) || portletHelper.userHasRole(liferayUser, RoleConstants.ADMINISTRATOR)) {
			passwordEnable = true;
		}

		return passwordEnable;
	}

	/**
	 * Salva l'{@link ProfiloUtenteCittadino}.
	 *
	 * @param profiloUtenteCittadinoModel
	 * @param bindingResult
	 * @param model
	 * @param request
	 * @param response
	 * @param sessionStatus
	 */
	@ActionMapping(params = "action=saveUtenteCittadino")
	public void saveUtenteCittadino(@ModelAttribute ProfiloUtenteCittadinoForm profiloUtenteCittadinoForm, BindingResult bindingResult, Model model, ActionRequest request, ActionResponse response,
			SessionStatus sessionStatus) {

		boolean edit = profiloUtenteCittadinoForm.getIdProfiloUtenteCittadino() > 0;

		// Se la password non è abilitata --> generazione automatica
		boolean passwordEnable = isPasswordEnable();
		if (!passwordEnable) {
			String password = PwdGenerator.getPassword();
			profiloUtenteCittadinoForm.setPassword(password);
			profiloUtenteCittadinoForm.setConfermaPassword(password);
		}

		profiloUtenteCittadinoValidator.validate(profiloUtenteCittadinoForm, bindingResult);
		if (bindingResult.hasErrors()) {
			response.setRenderParameter("action", "editProfiloUtenteCittadinoFail");
			model.addAttribute("formError", messageSource.getMessage("error.verificareDatiInseriti", null, request.getLocale()));
			return;
		}

		// Generazione Email di cortesia
		if (profiloUtenteCittadinoForm.getEmail() == null || profiloUtenteCittadinoForm.getEmail().equals("")) {
			String osapulieHost = request.getServerName();
			String email = profiloUtenteCittadinoForm.getCodiceFiscale() + "@" + osapulieHost;
			profiloUtenteCittadinoForm.setEmail(email);
		}

		long companyId = PortalUtil.getCompanyId(request);
		User liferayUser = null;
		if (edit) {
			try {
				liferayUser = UserLocalServiceUtil.getUserByScreenName(companyId, profiloUtenteCittadinoForm.getCodiceFiscale());
				liferayUser.setFirstName(profiloUtenteCittadinoForm.getNome());
				liferayUser.setLastName(profiloUtenteCittadinoForm.getCognome());
				liferayUser.setEmailAddress(profiloUtenteCittadinoForm.getEmail());
				UserLocalServiceUtil.updateUser(liferayUser, true);
			}
			catch (PortalException e) {
				log.error("unable to save user :: " + e.getMessage(), e);
			}
			catch (SystemException e) {
				log.error("unable to save user :: " + e.getMessage(), e);
			}

		}
		else {
			// insert tb_profilo_utente_cittadino
			ProfiloUtenteCittadino profiloUtenteCittadino = profiloUtenteService.getProfiloUtenteCittadinoByCf(profiloUtenteCittadinoForm.getCodiceFiscale());
			if (profiloUtenteCittadino == null) {
				ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

				Locale locale = LocaleUtil.getDefault();

				if (themeDisplay != null) {
					locale = themeDisplay.getLocale();
				}
				try {
					liferayUser = addUser(companyId, profiloUtenteCittadinoForm.getNome(), profiloUtenteCittadinoForm.getCognome(), profiloUtenteCittadinoForm.getEmail(),
							profiloUtenteCittadinoForm.getCodiceFiscale(), profiloUtenteCittadinoForm.getPassword(), locale, null, null);

					// Attesa inserimento profiloUtenteCittadino (max 5 sec)
					int countDown = 5;
					while (true) {
						if (--countDown == 0 || (profiloUtenteCittadino != null)) {
							break;
						}
						try {
							Thread.sleep(1000);
						}
						catch (InterruptedException e) {
						}
						try {
							profiloUtenteCittadino = profiloUtenteService.getProfiloUtenteCittadinoByCf(profiloUtenteCittadinoForm.getCodiceFiscale());
						}
						catch (Exception e) {
							log.error("saveUtenteCittadino :: " + e.getMessage(), e);
						}
					}
					profiloUtenteCittadino.setMailPec(profiloUtenteCittadinoForm.getPec().trim());
					// salvataggio profilo
					profiloUtenteService.updateProfiloUtenteCittadino(profiloUtenteCittadino);
					// invio email solo se è possibile definire la password
					if (passwordEnable) {
						sendMail(profiloUtenteCittadinoForm, request, profiloUtenteCittadino);
					}
					sessionStatus.setComplete();
				}
				catch (Exception e) {
					log.error("unable to save user :: " + e.getMessage(), e);
					if (e instanceof DuplicateUserEmailAddressException) {
						log.info("login :: redirect user to error page");
						model.addAttribute("formError", messageSource.getMessage("error.label.mailDuplicate", null, request.getLocale()));
					}
					response.setRenderParameter("action", "editProfiloUtenteCittadinoFail");
					return;
				}
			}
			else {
				// codice fiscale già registrato
				response.setRenderParameter("action", "editProfiloUtenteCittadinoFail");
				model.addAttribute("formError", messageSource.getMessage("error.label.usernameDuplicate", null, request.getLocale()));
				return;

			}
		}
		model.addAttribute("message", messageSource.getMessage("message.label.operazioneEseguitaCorrettamente", null, request.getLocale()));

	}

	/**
	 * @param profiloUtenteCittadinoForm
	 * @param request
	 * @param profiloUtenteCittadino
	 */
	private void sendMail(ProfiloUtenteCittadinoForm profiloUtenteCittadinoForm, ActionRequest request, ProfiloUtenteCittadino profiloUtenteCittadino) {
		// Invio email al cittadino
		String osapulieHost = "http://" + request.getServerName();

		// Invio email (PEC o tradizionale) all'utente
		String subjectMail = messageSource.getMessage("mail.nuovoutente.subject", new String[] { osapulieHost }, request.getLocale());
		Map<String, String> mailModel = new HashMap<String, String>();
		mailModel.put("cognome", profiloUtenteCittadino.getCognome());
		mailModel.put("nome", profiloUtenteCittadino.getNome());
		mailModel.put("username", profiloUtenteCittadino.getCodiceFiscale());
		mailModel.put("password", profiloUtenteCittadinoForm.getPassword());
		mailModel.put("osapulieHost", osapulieHost);

		try {
			senderHelper.sendCommunicationToCittadino(subjectMail, "velocityTemplate/nuovoAccount.vm", mailModel, null, profiloUtenteCittadino);
		}
		catch (CommunicationException e) {
			log.error("saveUtenteCittadino :: impossibile inviare l'email di registrazione al portale: " + e.getMessage(), e);
		}
	}

	@RenderMapping(params = "action=editProfiloUtenteCittadinoFail")
	public String editProfiloUtenteCittadinoFail(Model model, PortletRequest request) {
		return toLocalViewPath("home");
	}

	/**
	 * Utility method per calcolare e restituire il view ID all'interno della corretta alberatura di
	 * sottodirectory delle JSP: in un certo senso si tratta del namespace delle view locali a
	 * questo controller. Ad esempio: <code> toLocalViewPath( "home" ) </code> restituirà <code>
	 * "/gestioneutenti/home" </code>
	 *
	 * @param viewName l'ID della view locale
	 * @return il view ID completo del path a cui la vista appartiene
	 */
	private static String toLocalViewPath(String viewName) {
		return "gestioneutenti/" + viewName;
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
