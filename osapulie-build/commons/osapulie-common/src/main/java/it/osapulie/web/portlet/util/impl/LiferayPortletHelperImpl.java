/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.web.portlet.util.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletModeException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.PortletURL;
import javax.portlet.ResourceResponse;
import javax.portlet.WindowStateException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.LiferayPortletMode;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletURLFactoryUtil;

import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.dto.UserInfo;
import it.osapulie.infrastructure.security.OSApuliePortletPreAuthenticatedDetails;
import it.osapulie.infrastructure.security.OSApulieUserDetails;
import it.osapulie.shared.constant.PortalConstants;
import it.osapulie.shared.enumeration.AuthenticationChannel;
import it.osapulie.shared.enumeration.DeploymentScenario;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.web.WebLayerException;
import it.osapulie.web.portlet.util.PortletHelper;

/**
 * Implementazione di {@link PortletHelper}.
 *
 * @author Mario Scalas
 * @author Gianluca Pindinelli
 */
@Component("portletHelper")
public class LiferayPortletHelperImpl implements PortletHelper {

	private final Logger log = LoggerFactory.getLogger(LiferayPortletHelperImpl.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see it.linksmt.osapulie.web.portlet.util.PortletHelper#sendStream(java.io.InputStream,
	 * javax.portlet.ResourceResponse, java.lang.String)
	 */
	@Override
	public void sendStream(InputStream sourceStream, ResourceResponse response, String mimeType) {
		try {
			response.setContentType(mimeType);
			copyStream(sourceStream, response.getPortletOutputStream());
		}
		catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.linksmt.osapulie.web.portlet.util.PortletHelper#sendStream(byte[],
	 * javax.portlet.ResourceResponse, java.lang.String)
	 */
	@Override
	public void sendStream(byte[] bytes, ResourceResponse response, String mimeType) {
		sendStreamAsAttachment(bytes, response, null, mimeType);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.linksmt.osapulie.web.portlet.util.PortletHelper#sendStreamAsAttachment(byte[],
	 * javax.portlet.ResourceResponse, java.lang.String, java.lang.String)
	 */
	@Override
	public void sendStreamAsAttachment(byte[] bytes, ResourceResponse response, String attachmentName, String mimeType) {
		if (StringUtils.hasLength(attachmentName)) {
			response.setProperty("Content-disposition", "attachment; filename=" + attachmentName);
		}
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		sendStream(bais, response, mimeType);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.linksmt.osapulie.web.portlet.util.PortletHelper#copyStream(java.io.InputStream,
	 * java.io.OutputStream)
	 */
	@Override
	public void copyStream(InputStream sourceStream, OutputStream os) {
		try {
			StreamUtil.transfer(sourceStream, os, true);
		}
		catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.linksmt.osapulie.web.portlet.util.PortletHelper#getLongParam(java.lang.String,
	 * javax.portlet.PortletRequest)
	 */
	@Override
	public Long getLongParam(String parmName, PortletRequest request) {
		String s = request.getParameter(parmName);
		Long value = 0L;
		if (s != null) {
			try {
				value = Long.parseLong(s);
			}
			catch (NumberFormatException e) {
				String[] ss = s.split(",");
				if (ss.length > 1) {
					value = Long.parseLong(ss[0]);
				}
			}
		}
		return value;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.linksmt.osapulie.web.portlet.util.PortletHelper#getUserInfo(javax.portlet.PortletRequest)
	 */
	@Override
	public UserInfo getUserInfo(PortletRequest request) {
		User user = null;
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		if (themeDisplay != null) {
			user = themeDisplay.getUser();
		}

		try {
			String userName = user.getLogin();
			List<String> roles = new ArrayList<String>();
			for (Role role : RoleLocalServiceUtil.getUserRoles(user.getUserId())) {
				roles.add(role.getName());
			}
			return new UserInfo(userName, roles.toArray(new String[roles.size()]));
		}
		catch (Exception e) {
			throw new WebLayerException(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.linksmt.osapulie.web.portlet.util.PortletHelper#getCurrentPageName(javax.portlet.
	 * PortletRequest)
	 */
	@Override
	public String getCurrentPageName(PortletRequest request) {
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

		String friendlyUrl = themeDisplay.getLayout().getFriendlyURL(); // es., "/opere"
		String pageName = friendlyUrl.substring(1); // "opere"

		return pageName;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.web.portlet.util.PortletHelper#getProfiloUtente(javax.portlet.PortletSession)
	 */
	@Override
	public ProfiloUtenteCittadino getProfiloUtente(PortletSession session) {
		return getOsApulieUserDetails().getProfiloUtenteCittadino();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.web.portlet.util.PortletHelper#getOsApulieUserDetails(javax.portlet.
	 * PortletRequest )
	 */
	@Override
	public OSApulieUserDetails getOsApulieUserDetails() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return null;
		}

		OSApulieUserDetails user = (OSApulieUserDetails) authentication.getPrincipal();
		// Aggiornamento certificato
		user.setCertificato(((OSApuliePortletPreAuthenticatedDetails) (authentication.getDetails())).getCertificato());
		return user;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.web.portlet.util.PortletHelper#getUserPreferences(javax.portlet.PortletRequest )
	 */
	@Override
	public UserPreferences getUserPreferences(PortletRequest portletRequest) {
		HttpServletRequest httpServletRequest = PortalUtil.getHttpServletRequest(portletRequest);

		UserPreferences userPreferences = null;
		if (httpServletRequest.getSession().getAttribute("userPreferences") != null) {
			userPreferences = (UserPreferences) httpServletRequest.getSession().getAttribute("userPreferences");
		}

		return userPreferences;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.web.portlet.util.PortletHelper#isCurrentUserRole(User liferayUser, String
	 * roleString)
	 */
	@Override
	public boolean userHasRole(User liferayUser, String roleString) {

		if (liferayUser != null) {
			List<Role> roles;
			try {
				roles = liferayUser.getRoles();
				for (Role role : roles) {
					if (role.getName().equals(roleString)) {
						return true;
					}
				}
			}
			catch (SystemException e) {
			}
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.web.portlet.util.PortletHelper#getDeploymentScenario()
	 */
	@Override
	public int getDeploymentScenario() {

		int tipoScenario = -1;

		try {
			String tipoScenarioByProps = PrefsPropsUtil.getString(PortalConstants.SCENARIO_DEPLOYMENT_PORTAL_EXT_PROP);

			DeploymentScenario scenarioEnumeration = DeploymentScenario.SCENARIO_DEFAULT;

			if (Validator.isNotNull(tipoScenarioByProps)) {
				int tipoScenarioByPropsInt = Integer.parseInt(tipoScenarioByProps);
				if (tipoScenarioByPropsInt == DeploymentScenario.SCENARIO_1.getScenario()) {
					scenarioEnumeration = DeploymentScenario.SCENARIO_1;
				}
				else if (tipoScenarioByPropsInt == DeploymentScenario.SCENARIO_2.getScenario()) {
					scenarioEnumeration = DeploymentScenario.SCENARIO_2;
				}
				else if (tipoScenarioByPropsInt == DeploymentScenario.SCENARIO_3.getScenario()) {
					scenarioEnumeration = DeploymentScenario.SCENARIO_3;
				}
			}

			switch (scenarioEnumeration) {
			case SCENARIO_1:
				tipoScenario = DeploymentScenario.SCENARIO_1.getScenario();
				break;
			case SCENARIO_2:
				tipoScenario = DeploymentScenario.SCENARIO_2.getScenario();
				break;
			case SCENARIO_3:
				tipoScenario = DeploymentScenario.SCENARIO_3.getScenario();
				break;
			default:
				break;
			}
		}
		catch (Exception e) {
			log.error("getTipoScenario :: " + e.getMessage(), e);
		}

		return tipoScenario;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.web.portlet.util.PortletHelper#getScenario3AuthenticationChannels()
	 */
	@Override
	public AuthenticationChannel[] getScenario3AuthenticationChannels() {

		AuthenticationChannel[] authenticationChannels = null;

		try {
			String scenario3AuthenticationChannelsString = PrefsPropsUtil.getString(PortalConstants.SCENARIO_DEPLOYMENT_AUTHENTICATION_CHANNELS_PORTAL_EXT_PROP);
			if (scenario3AuthenticationChannelsString != null) {
				String[] scenario3AuthenticationChannelsStrings = scenario3AuthenticationChannelsString.split(",");
				authenticationChannels = new AuthenticationChannel[scenario3AuthenticationChannelsStrings.length];
				for (int i = 0; i < scenario3AuthenticationChannelsStrings.length; i++) {
					String scenario3AuthenticationChannelString = scenario3AuthenticationChannelsStrings[i];
					AuthenticationChannel authenticationChannel = AuthenticationChannel.valueOf(scenario3AuthenticationChannelString.toUpperCase());
					authenticationChannels[i] = authenticationChannel;
				}
			}
		}
		catch (Exception e) {
			log.error("getScenario3AuthenticationChannels :: " + e.getMessage(), e);
		}

		return authenticationChannels;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.web.portlet.util.PortletHelper#isAuthenticationChannelEnable(it.osapulie
	 * .shared.enumeration.AuthenticationChannel)
	 */
	@Override
	public boolean isAuthenticationChannelEnable(AuthenticationChannel authenticationChannel) {

		AuthenticationChannel[] scenario3AuthenticationChannels = getScenario3AuthenticationChannels();

		if (scenario3AuthenticationChannels != null) {
			for (int i = 0; i < scenario3AuthenticationChannels.length; i++) {
				if (scenario3AuthenticationChannels[i] == authenticationChannel) {
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.web.portlet.util.PortletHelper#addRoleUser(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void addRoleUser(String username, String roleName) {

		Long companyId = PortalUtil.getDefaultCompanyId();

		try {
			User liferayUser = UserLocalServiceUtil.getUserByScreenName(companyId, username);
			Role role = RoleLocalServiceUtil.getRole(companyId, roleName);
			UserLocalServiceUtil.addRoleUsers(role.getRoleId(), new long[] { liferayUser.getUserId() });
		}
		catch (PortalException e) {
			log.error("addRoleToUser :: " + e.getMessage(), e);
		}
		catch (SystemException e) {
			log.error("addRoleToUser :: " + e.getMessage(), e);
		}

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.web.portlet.util.PortletHelper#removeRoleUser(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void removeRoleUser(String username, String roleName) {

		Long companyId = PortalUtil.getDefaultCompanyId();

		try {
			User liferayUser = UserLocalServiceUtil.getUserByScreenName(companyId, username);
			Role role = RoleLocalServiceUtil.getRole(companyId, roleName);
			UserLocalServiceUtil.deleteRoleUser(role.getRoleId(), liferayUser.getUserId());
		}
		catch (PortalException e) {
			log.error("addRoleToUser :: " + e.getMessage(), e);
		}
		catch (SystemException e) {
			log.error("addRoleToUser :: " + e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.web.portlet.util.PortletHelper#createPortletURL(javax.portlet.PortletRequest,
	 * java.util.Map, java.lang.String, java.lang.String)
	 */
	@Override
	public String createPortletURL(PortletRequest request, Map<String, String> parameters, String portletId, String lifecycle)
			throws WindowStateException, PortletModeException, PortalException, SystemException {

		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		if (portletId == null) {
			portletId = (String) request.getAttribute(WebKeys.PORTLET_ID);
		}
		if (lifecycle == null) {
			lifecycle = PortletRequest.RENDER_PHASE;
		}

		long plid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), portletId);
		HttpServletRequest httpServletRequest = PortalUtil.getHttpServletRequest(request);
		PortletURL renderUrl = PortletURLFactoryUtil.create(httpServletRequest, portletId, plid, lifecycle);
		renderUrl.setWindowState(LiferayWindowState.NORMAL);
		renderUrl.setPortletMode(LiferayPortletMode.VIEW);
		if (parameters != null) {
			for (Map.Entry<String, String> entry : parameters.entrySet()) {
				renderUrl.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return renderUrl.toString();
	}

}
