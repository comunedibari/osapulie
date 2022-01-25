/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.web.portlet.tag;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceURL;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import it.osapulie.domain.servizi.Servizio;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.util.ApplicationBootstrap;

/**
 * Tag Library Stradario per il portale OSApulie.
 *
 * @author Gianluca Pindinelli
 *
 */
public class StradarioTag extends SimpleTagSupport {

	private String viaName;
	private String civicoName;
	private String esponenteName;
	private String capName;
	private String id = "0";
	private String selezionaLabel = "-- Seleziona --";
	private String cercaLabel = "Cerca";
	private String viaLabel = "Via";
	private String esponenteLabel = "Esponente";
	private String capLabel = "CAP";
	private String civicoLabel = "Civico";
	private String viaClass = "viaSelect";
	private String civicoClass = "civicoSelect";
	private String numCaratteriMin = "3";
	private String loadingImageSrc;
	private String viaOptionValue;
	private String viaOptionText;
	private String civicoOptionValue;
	private String civicoOptionText;
	private String esponenteValue;
	private String capValue;
	private String localitaValue;
	private String codiceViaValue;
	private String viaTextHiddenName = "viaTextHidden";
	private String civicoTextHiddenName = "civicoTextHidden";
	private String localitaHiddenName = "localitaHidden";
	private String codiceViaHiddenName = "codiceViaHidden";

	private VelocityEngine velocityEngine;

	private final Logger log = LoggerFactory.getLogger(StradarioTag.class.getName());

	/**
	 *
	 */
	public StradarioTag() {
		try {
			velocityEngine = new VelocityEngine();

			velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
			velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

			velocityEngine.init();
		}
		catch (Exception e) {
			log.error("StradarioTag :: " + e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag()
	 */
	@Override
	public void doTag() throws JspException, IOException {

		JspWriter out = getJspContext().getOut();
		String html = null;
		try {
			html = generateHtml();
		}
		catch (Exception e) {
			log.error("doTag :: " + e.getMessage(), e);
		}

		out.write(html);
	}

	/**
	 * @return
	 * @throws Exception
	 */
	private String generateHtml() throws Exception {

		final String templatePath = "template/stradario.vm";

		Template template = velocityEngine.getTemplate(templatePath);

		PageContext pageContext = (PageContext) getJspContext();
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

		PortletRequest portletRequest = (PortletRequest) request.getAttribute("javax.portlet.request");

		UserPreferences userPreferences = ApplicationBootstrap.INSTANCE.getPortletHelper().getUserPreferences(portletRequest);

		// Verifica se lo stradario è abilitato per il comune attuale
		// Caricamento URL
		ThemeDisplay themeDisplay = (ThemeDisplay) portletRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String currentUrl = themeDisplay.getURLCurrent();
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

		Servizio servizioByUri = ApplicationBootstrap.INSTANCE.getServizioService().getServizioByUri(currentUrl);
		boolean stradarioEnable = ApplicationBootstrap.INSTANCE.getConfigurazioneService().isStradarioEnable(servizioByUri.getCodiceServizio(), userPreferences.getIdComuneIsa());

		log.debug("stradarioEnable: " + stradarioEnable);

		RenderResponse renderResponse = (RenderResponse) request.getAttribute(JavaConstants.JAVAX_PORTLET_RESPONSE);
		ResourceURL resourceURL = renderResponse.createResourceURL();
		resourceURL.setResourceID("searchStradario");

		String portletNamespace = renderResponse.getNamespace();

		VelocityContext context = new VelocityContext();
		context.put("stradarioEnable", stradarioEnable);
		context.put("viaName", viaName);
		context.put("civicoName", civicoName);
		context.put("esponenteName", esponenteName);
		context.put("capName", capName);
		context.put("id", id);
		context.put("selezionaLabel", selezionaLabel);
		context.put("cercaLabel", cercaLabel);
		context.put("viaLabel", viaLabel);
		context.put("esponenteLabel", esponenteLabel);
		context.put("capLabel", capLabel);
		context.put("viaClass", viaClass);
		context.put("civicoLabel", civicoLabel);
		context.put("civicoClass", civicoClass);
		context.put("numCaratteriMin", numCaratteriMin);
		context.put("resourceURL", resourceURL);
		context.put("portletNamespace", portletNamespace);
		context.put("loadingImageSrc", loadingImageSrc);
		context.put("viaOptionValue", viaOptionValue);
		context.put("viaOptionText", viaOptionText);
		context.put("civicoOptionValue", civicoOptionValue);
		context.put("civicoOptionText", civicoOptionText);
		context.put("esponenteValue", esponenteValue);
		context.put("capValue", capValue);
		context.put("localitaValue", localitaValue);
		context.put("codiceViaValue", codiceViaValue);
		context.put("viaTextHiddenName", viaTextHiddenName);
		context.put("civicoTextHiddenName", civicoTextHiddenName);
		context.put("localitaHiddenName", localitaHiddenName);
		context.put("codiceViaHiddenName", codiceViaHiddenName);

		StringWriter writer = new StringWriter();
		template.merge(context, writer);

		return writer.toString();
	}

	/**
	 * @return the viaName
	 */
	public String getViaName() {
		return viaName;
	}

	/**
	 * @param viaName the viaName to set
	 */
	public void setViaName(String viaName) {
		this.viaName = viaName;
	}

	/**
	 * @return the civicoName
	 */
	public String getCivicoName() {
		return civicoName;
	}

	/**
	 * @param civicoName the civicoName to set
	 */
	public void setCivicoName(String civicoName) {
		this.civicoName = civicoName;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the selezionaLabel
	 */
	public String getSelezionaLabel() {
		return selezionaLabel;
	}

	/**
	 * @param selezionaLabel the selezionaLabel to set
	 */
	public void setSelezionaLabel(String selezionaLabel) {
		this.selezionaLabel = selezionaLabel;
	}

	/**
	 * @return the cercaLabel
	 */
	public String getCercaLabel() {
		return cercaLabel;
	}

	/**
	 * @param cercaLabel the cercaLabel to set
	 */
	public void setCercaLabel(String cercaLabel) {
		this.cercaLabel = cercaLabel;
	}

	/**
	 * @return the viaLabel
	 */
	public String getViaLabel() {
		return viaLabel;
	}

	/**
	 * @param viaLabel the viaLabel to set
	 */
	public void setViaLabel(String viaLabel) {
		this.viaLabel = viaLabel;
	}

	/**
	 * @return the civicoLabel
	 */
	public String getCivicoLabel() {
		return civicoLabel;
	}

	/**
	 * @param civicoLabel the civicoLabel to set
	 */
	public void setCivicoLabel(String civicoLabel) {
		this.civicoLabel = civicoLabel;
	}

	/**
	 * @return the viaClass
	 */
	public String getViaClass() {
		return viaClass;
	}

	/**
	 * @param viaClass the viaClass to set
	 */
	public void setViaClass(String viaClass) {
		this.viaClass = viaClass;
	}

	/**
	 * @return the civicoClass
	 */
	public String getCivicoClass() {
		return civicoClass;
	}

	/**
	 * @param civicoClass the civicoClass to set
	 */
	public void setCivicoClass(String civicoClass) {
		this.civicoClass = civicoClass;
	}

	/**
	 * @return the numCaratteriMin
	 */
	public String getNumCaratteriMin() {
		return numCaratteriMin;
	}

	/**
	 * @param numCaratteriMin the numCaratteriMin to set
	 */
	public void setNumCaratteriMin(String numCaratteriMin) {
		this.numCaratteriMin = numCaratteriMin;
	}

	/**
	 * @return the loadingImageSrc
	 */
	public String getLoadingImageSrc() {
		return loadingImageSrc;
	}

	/**
	 * @param loadingImageSrc the loadingImageSrc to set
	 */
	public void setLoadingImageSrc(String loadingImageSrc) {
		this.loadingImageSrc = loadingImageSrc;
	}

	/**
	 * @return the viaOptionValue
	 */
	public String getViaOptionValue() {
		return viaOptionValue;
	}

	/**
	 * @param viaOptionValue the viaOptionValue to set
	 */
	public void setViaOptionValue(String viaOptionValue) {
		this.viaOptionValue = viaOptionValue;
	}

	/**
	 * @return the viaOptionText
	 */
	public String getViaOptionText() {
		return viaOptionText;
	}

	/**
	 * @param viaOptionText the viaOptionText to set
	 */
	public void setViaOptionText(String viaOptionText) {
		this.viaOptionText = viaOptionText;
	}

	/**
	 * @return the civicoOptionValue
	 */
	public String getCivicoOptionValue() {
		return civicoOptionValue;
	}

	/**
	 * @param civicoOptionValue the civicoOptionValue to set
	 */
	public void setCivicoOptionValue(String civicoOptionValue) {
		this.civicoOptionValue = civicoOptionValue;
	}

	/**
	 * @return the civicoOptionText
	 */
	public String getCivicoOptionText() {
		return civicoOptionText;
	}

	/**
	 * @param civicoOptionText the civicoOptionText to set
	 */
	public void setCivicoOptionText(String civicoOptionText) {
		this.civicoOptionText = civicoOptionText;
	}

	/**
	 * @return the viaTextHiddenName
	 */
	public String getViaTextHiddenName() {
		return viaTextHiddenName;
	}

	/**
	 * @param viaTextHiddenName the viaTextHiddenName to set
	 */
	public void setViaTextHiddenName(String viaTextHiddenName) {
		this.viaTextHiddenName = viaTextHiddenName;
	}

	/**
	 * @return the civicoTextHiddenName
	 */
	public String getCivicoTextHiddenName() {
		return civicoTextHiddenName;
	}

	/**
	 * @param civicoTextHiddenName the civicoTextHiddenName to set
	 */
	public void setCivicoTextHiddenName(String civicoTextHiddenName) {
		this.civicoTextHiddenName = civicoTextHiddenName;
	}

	/**
	 * @return the velocityEngine
	 */
	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}

	/**
	 * @param velocityEngine the velocityEngine to set
	 */
	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	/**
	 * @return the esponenteName
	 */
	public String getEsponenteName() {
		return esponenteName;
	}

	/**
	 * @param esponenteName the esponenteName to set
	 */
	public void setEsponenteName(String esponenteName) {
		this.esponenteName = esponenteName;
	}

	/**
	 * @return the esponenteLabel
	 */
	public String getEsponenteLabel() {
		return esponenteLabel;
	}

	/**
	 * @param esponenteLabel the esponenteLabel to set
	 */
	public void setEsponenteLabel(String esponenteLabel) {
		this.esponenteLabel = esponenteLabel;
	}

	/**
	 * @return the esponenteValue
	 */
	public String getEsponenteValue() {
		return esponenteValue;
	}

	/**
	 * @param esponenteValue the esponenteValue to set
	 */
	public void setEsponenteValue(String esponenteValue) {
		this.esponenteValue = esponenteValue;
	}

	/**
	 * @return the capName
	 */
	public String getCapName() {
		return capName;
	}

	/**
	 * @param capName the capName to set
	 */
	public void setCapName(String capName) {
		this.capName = capName;
	}

	/**
	 * @return the capLabel
	 */
	public String getCapLabel() {
		return capLabel;
	}

	/**
	 * @param capLabel the capLabel to set
	 */
	public void setCapLabel(String capLabel) {
		this.capLabel = capLabel;
	}

	/**
	 * @return the capValue
	 */
	public String getCapValue() {
		return capValue;
	}

	/**
	 * @param capValue the capValue to set
	 */
	public void setCapValue(String capValue) {
		this.capValue = capValue;
	}

	/**
	 * @return the localitaValue
	 */
	public String getLocalitaValue() {
		return localitaValue;
	}

	/**
	 * @param localitaValue the localitaValue to set
	 */
	public void setLocalitaValue(String localitaValue) {
		this.localitaValue = localitaValue;
	}

	/**
	 * @return the localitaHiddenName
	 */
	public String getLocalitaHiddenName() {
		return localitaHiddenName;
	}

	/**
	 * @param localitaHiddenName the localitaHiddenName to set
	 */
	public void setLocalitaHiddenName(String localitaHiddenName) {
		this.localitaHiddenName = localitaHiddenName;
	}

	/**
	 * @return the codiceViaValue
	 */
	public String getCodiceViaValue() {
		return codiceViaValue;
	}

	/**
	 * @param codiceViaValue the codiceViaValue to set
	 */
	public void setCodiceViaValue(String codiceViaValue) {
		this.codiceViaValue = codiceViaValue;
	}

	/**
	 * @return the codiceViaHiddenName
	 */
	public String getCodiceViaHiddenName() {
		return codiceViaHiddenName;
	}

	/**
	 * @param codiceViaHiddenName the codiceViaHiddenName to set
	 */
	public void setCodiceViaHiddenName(String codiceViaHiddenName) {
		this.codiceViaHiddenName = codiceViaHiddenName;
	}

}
