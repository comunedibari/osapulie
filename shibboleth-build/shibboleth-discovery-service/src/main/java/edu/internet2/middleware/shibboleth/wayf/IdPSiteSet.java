/*
 * Licensed to the University Corporation for Advanced Internet Development, Inc. (UCAID) under one
 * or more contributor license agreements. See the NOTICE file distributed with this work for
 * additional information regarding copyright ownership. The UCAID licenses this file to You under
 * the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package edu.internet2.middleware.shibboleth.wayf;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TreeMap;

import javax.net.ssl.X509TrustManager;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.opensaml.saml2.metadata.EntitiesDescriptor;
import org.opensaml.saml2.metadata.EntityDescriptor;
import org.opensaml.saml2.metadata.IDPSSODescriptor;
import org.opensaml.saml2.metadata.Organization;
import org.opensaml.saml2.metadata.OrganizationDisplayName;
import org.opensaml.saml2.metadata.OrganizationName;
import org.opensaml.saml2.metadata.RoleDescriptor;
import org.opensaml.saml2.metadata.SPSSODescriptor;
import org.opensaml.saml2.metadata.provider.AbstractObservableMetadataProvider;
import org.opensaml.saml2.metadata.provider.AbstractReloadingMetadataProvider;
import org.opensaml.saml2.metadata.provider.FileBackedHTTPMetadataProvider;
import org.opensaml.saml2.metadata.provider.FilesystemMetadataProvider;
import org.opensaml.saml2.metadata.provider.MetadataFilter;
import org.opensaml.saml2.metadata.provider.MetadataFilterChain;
import org.opensaml.saml2.metadata.provider.MetadataProvider;
import org.opensaml.saml2.metadata.provider.MetadataProviderException;
import org.opensaml.saml2.metadata.provider.ObservableMetadataProvider;
import org.opensaml.saml2.metadata.provider.RequiredValidUntilFilter;
import org.opensaml.saml2.metadata.provider.SignatureValidationFilter;
import org.opensaml.ws.soap.client.http.HttpClientBuilder;
import org.opensaml.ws.soap.client.http.TLSProtocolSocketFactory;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.parse.ParserPool;
import org.opensaml.xml.security.credential.Credential;
import org.opensaml.xml.security.credential.StaticCredentialResolver;
import org.opensaml.xml.security.keyinfo.BasicProviderKeyInfoCredentialResolver;
import org.opensaml.xml.security.keyinfo.KeyInfoCredentialResolver;
import org.opensaml.xml.security.keyinfo.KeyInfoProvider;
import org.opensaml.xml.security.keyinfo.provider.DSAKeyValueProvider;
import org.opensaml.xml.security.keyinfo.provider.InlineX509DataProvider;
import org.opensaml.xml.security.keyinfo.provider.RSAKeyValueProvider;
import org.opensaml.xml.security.x509.BasicX509Credential;
import org.opensaml.xml.security.x509.X509Util;
import org.opensaml.xml.signature.SignatureTrustEngine;
import org.opensaml.xml.signature.impl.ExplicitKeySignatureTrustEngine;
import org.opensaml.xml.util.DatatypeHelper;
import org.opensaml.xml.util.XMLHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import edu.internet2.middleware.shibboleth.common.ShibbolethConfigurationException;
import edu.internet2.middleware.shibboleth.util.CustomHttpsSocketFactory;
import edu.internet2.middleware.shibboleth.wayf.plugins.Plugin;
import edu.internet2.middleware.shibboleth.wayf.plugins.PluginMetadataParameter;
import edu.internet2.middleware.shibboleth.wayf.plugins.provider.BindingFilter;

/**
 *
 * Represents a collection of related sites as described by a single source of metadata. This is
 * usually a federation. When the WAYF looks to see which IdP sites to show, it trims the list so as
 * to not show IdP's which do not trust the SP.
 *
 * This class is opaque outside this file. The three static methods getSitesLists,
 * searchForMatchingOrigins and lookupIdP provide mechanisms for accessing collections of
 * IdPSiteSets.
 *
 */

public class IdPSiteSet implements ObservableMetadataProvider.Observer {

	/** Handle for error output. */
	private static final Logger LOG = LoggerFactory.getLogger(IdPSiteSet.class.getName());

	/**
	 * Time for the HTTP loading. This is one-off initialized as needed.
	 */
	private static Timer timer;

	/** The OpenSaml metadata source. */
	private final ObservableMetadataProvider metadata;

	/** Is the named SP in the current metadata set? */
	private Set<String> spNames = new HashSet<String>(0);

	/** Is the named IdP in the current metadata set? */
	private Set<String> idpNames = new HashSet<String>(0);

	/** What does the configuration identify this as? */
	private final String identifier;

	/** What name should we display for this set of entities? */
	private final String displayName;

	private final String logo;

	private final String group;

	private final String groupLogo;

	private final String buttonCssClass;

	private final String groupDescription;

	private final String groupDescriptionLabel;

	private final int groupOrder;

	private final Map<String, String> groupLinks = new TreeMap<String, String>();

	private final List<RequestParameter> requestParameters = new ArrayList<RequestParameter>();

	private final String authnContextClassRef;

	private final String groupAuthnContextClassRef;

	/** Where does the metadata exist? */
	private String location;

	/** What parameters do we pass in to which plugin? */
	private final Map<Plugin, PluginMetadataParameter> plugins = new HashMap<Plugin, PluginMetadataParameter>();

	/**
	 * Create a new IdPSiteSet as described by the supplied XML segment.
	 *
	 * @param element - configuration details.
	 * @param parserPool - the parsers we initialized above.
	 * @param warnOnBadBinding if we just warn or give an error if an SP has bad entry points.
	 * @throws ShibbolethConfigurationException - if something goes wrong.
	 */
	protected IdPSiteSet(Element element, ParserPool parserPool, boolean warnOnBadBinding) throws ShibbolethConfigurationException {

		identifier = element.getAttribute("identifier");
		displayName = element.getAttribute("displayName");
		logo = element.getAttribute("logo");
		group = element.getAttribute("group");
		groupLogo = element.getAttribute("groupLogo");
		buttonCssClass = element.getAttribute("buttonCssClass");
		NodeList groupDescriptionNodeList = element.getElementsByTagName("GroupDescription");
		if (groupDescriptionNodeList != null && groupDescriptionNodeList.getLength() > 0) {
			Element groupDescriptionElement = (Element) groupDescriptionNodeList.item(0);
			groupDescription = groupDescriptionElement.getTextContent();
			groupDescriptionLabel = groupDescriptionElement.getAttribute("label");
		}
		else {
			groupDescription = null;
			groupDescriptionLabel = null;
		}

		NodeList groupLinksNodeList = element.getElementsByTagName("GroupLinks");
		if (groupLinksNodeList != null && groupLinksNodeList.getLength() > 0) {
			Element groupLinksElement = (Element) groupLinksNodeList.item(0);

			NodeList linksNodeList = groupLinksElement.getElementsByTagName("Link");
			if (linksNodeList != null && linksNodeList.getLength() > 0) {
				for (int i = 0; i < linksNodeList.getLength(); i++) {
					Element linkElement = (Element) linksNodeList.item(i);
					if (linkElement.getAttribute("label") != null && !linkElement.getAttribute("label").isEmpty()) {
						groupLinks.put(linkElement.getAttribute("label"), linkElement.getTextContent());
					}
					else {
						groupLinks.put(linkElement.getAttribute("name"), linkElement.getTextContent());
					}
				}
			}
		}

		NodeList requestParametersNodeList = element.getElementsByTagName("RequestParameters");
		if (requestParametersNodeList != null && requestParametersNodeList.getLength() > 0) {
			Element requestParametersElement = (Element) requestParametersNodeList.item(0);
			NodeList parametersNodeList = requestParametersElement.getElementsByTagName("Parameter");
			if (parametersNodeList != null && parametersNodeList.getLength() > 0) {
				for (int i = 0; i < parametersNodeList.getLength(); i++) {
					Element parameterElement = (Element) parametersNodeList.item(i);
					String name = parameterElement.getAttribute("name");
					String value = parameterElement.getAttribute("value");
					String img = parameterElement.getAttribute("img");
					String title = parameterElement.getAttribute("title");
					RequestParameter requestParameter = new RequestParameter(name, value, img, title);
					requestParameters.add(requestParameter);
				}
			}
		}

		if (element.getAttribute("groupOrder") != null && !element.getAttribute("groupOrder").equals("")) {
			groupOrder = Integer.parseInt(element.getAttribute("groupOrder"));
		}
		else {
			groupOrder = 0;
		}
		authnContextClassRef = element.getAttribute("authnContextClassRef");
		groupAuthnContextClassRef = element.getAttribute("groupAuthnContextClassRef");
		location = DatatypeHelper.safeTrimOrNullString(element.getAttribute("url"));
		if (null == location) {
			//
			// Sigh for a few releases this was documented as URI
			//
			location = element.getAttribute("url");
		}

		//
		// Configure the filters (before the metadata so we can add them before we start reading)
		//
		MetadataFilterChain filterChain = buildFilterChain(element, warnOnBadBinding);
		AbstractReloadingMetadataProvider provider;

		LOG.info("Loading Metadata for " + displayName);
		try {

			URL url = new URL(location);
			if ("file".equalsIgnoreCase(url.getProtocol())) {
				provider = new FilesystemMetadataProvider(new File(url.getFile()));
			}
			else {
				provider = buildHTTPProvider(element, url);
			}
		}
		catch (MetadataProviderException e) {
			throw new ShibbolethConfigurationException("Could not read " + location, e);
		}
		catch (MalformedURLException e) {
			throw new ShibbolethConfigurationException("Error configuring " + identifier + ": badly formed url ", e);
		}
		provider.setRefreshDelayFactor(getRefreshDelayFactor(element));
		provider.setMaxRefreshDelay(getMaxRefreshDelay(element));
		provider.setMinRefreshDelay(getMinRefreshDelay(element));
		provider.setParserPool(parserPool);
		try {
			provider.setMetadataFilter(filterChain);
			provider.initialize();
		}
		catch (MetadataProviderException e) {
			throw new ShibbolethConfigurationException("Error configuring " + identifier, e);
		}
		metadata = provider;
		metadata.getObservers().add(this);
		onEvent(metadata);
	}

	/**
	 * Initializes (if needed) and returns a timer to be used by the HTTP metadata loader.
	 *
	 * @return a common, shared time.
	 */
	private Timer getTimer() {
		if (null != timer) {
			return timer;
		}
		synchronized (this.getClass()) {
			if (null == timer) {
				timer = new Timer(true);
			}
			return timer;
		}
	}

	/**
	 * Gets the refresh delay factor for the metadata provider. Inspired by
	 * {@link edu.internet2.middleware.shibboleth.common.config.metadata.AbstractReloadingMetadataProviderBeanDefinitionParser}
	 *
	 * @param config provider configuration element
	 *
	 * @return refresh delay factor
	 */
	private float getRefreshDelayFactor(Element config) {
		float delayFactor = 0.75f;

		if (config.hasAttribute("refreshDelayFactor")) {
			String factorString = config.getAttribute("refreshDelayFactor");
			try {
				delayFactor = Float.parseFloat(factorString);
			}
			catch (NumberFormatException e) {
				LOG.error("Metadata provider '{}' had invalid refreshDelayFactor value '{}', using default value", identifier, factorString);
			}
		}

		if (delayFactor <= 0.0 || delayFactor >= 1.0) {
			LOG.error("Metadata provider '{}' had invalid refreshDelayFactor value '{}', using default value", identifier, delayFactor);
			delayFactor = 0.75f;
		}
		LOG.debug("Metadata provider '{}' refreshDelayFactor set to {}", identifier, delayFactor);
		return delayFactor;
	}

	/**
	 * Gets the maximum refresh delay for the metadata provider. Inspired by
	 * {@link edu.internet2.middleware.shibboleth.common.config.metadata.AbstractReloadingMetadataProviderBeanDefinitionParser}
	 *
	 * @param config provider configuration element
	 *
	 * @return the maximum refresh delay, in milliseconds
	 */
	private long getMaxRefreshDelay(Element config) {
		long maxRefreshDelay = 14400000L;

		String delayString = DatatypeHelper.safeTrimOrNullString(config.getAttribute("maxRefreshDelay"));

		if (null != delayString) {
			try {
				maxRefreshDelay = XMLHelper.durationToLong(delayString);
			}
			catch (IllegalArgumentException e) {
				LOG.error("Metadata provider '{}' had invalid maxRefreshDelay value '{}', using default value", identifier, delayString);
			}
		}

		if (maxRefreshDelay <= 0) {
			LOG.error("Metadata provider '{}' had invalid maxRefreshDelay value '{}', using default value", identifier, maxRefreshDelay);
			maxRefreshDelay = 14400000L;
		}

		LOG.debug("Metadata provider '{}' maxRefreshDelay set to {}", identifier, maxRefreshDelay);
		return maxRefreshDelay;
	}

	/**
	 * Gets the minimum refresh delay for the metadata provider. Inspired by
	 * {@link edu.internet2.middleware.shibboleth.common.config.metadata.AbstractReloadingMetadataProviderBeanDefinitionParser}
	 *
	 * @param config provider configuration element
	 *
	 * @return the minimum refresh delay, in milliseconds
	 */
	private int getMinRefreshDelay(Element config) {
		int minRefreshDelay = 300000;
		String delayString = DatatypeHelper.safeTrimOrNullString(config.getAttribute("minRefreshDelay"));

		if (null != delayString) {
			try {
				long delay = XMLHelper.durationToLong(delayString);
				if (delay > Integer.MAX_VALUE) {
					throw new IllegalArgumentException("Integer overflow");
				}
				minRefreshDelay = (int) delay;
			}
			catch (IllegalArgumentException e) {
				LOG.error("Metadata provider '{}' had invalid minRefreshDelay value '{}', using default value", identifier, delayString);
			}
		}

		if (minRefreshDelay <= 0) {
			LOG.error("Metadata provider '{}' had invalid minRefreshDelay value '{}', using default value", identifier, minRefreshDelay);
			minRefreshDelay = 300000;
		}

		LOG.debug("Metadata provider '{}' minRefreshDelay set to {}", identifier, minRefreshDelay);
		return minRefreshDelay;
	}

	/**
	 * Builds the HTTP client used to fetch metadata. Inspired by
	 * {@link edu.internet2.middleware.shibboleth.common.config.metadata.HTTPMetadataProviderBeanDefinitionParser}
	 *
	 * @param config the metadata provider configuration element
	 * @param metadataURL the URL from which metadata will be fetched
	 *
	 * @return the constructed HTTP client
	 */
	private HttpClient buildHttpClient(Element config, URL metadataURL) {
		HttpClientBuilder builder = new HttpClientBuilder();
		int requestTimeout = 5000;
		String timeoutString = DatatypeHelper.safeTrimOrNullString(config.getAttribute("requestTimeout"));
		String delayString = DatatypeHelper.safeTrimOrNullString(config.getAttribute("timeout"));

		if (null != delayString) {
			LOG.warn("timeout qualifier is deprecated, please use requestTimeout");
		}
		if (null != timeoutString) {
			try {
				long time = XMLHelper.durationToLong(timeoutString);
				if (time > Integer.MAX_VALUE) {
					LOG.error("Metadata provider '{}' had invalid requestTimeout value '{}', using default value", identifier, timeoutString);
				}
				else {
					requestTimeout = (int) time;
				}
			}
			catch (IllegalArgumentException e) {
				LOG.error("Metadata provider '{}' had invalid requestTimeout value '{}', using default value", identifier, timeoutString);
			}
		}
		else if (null != delayString) {
			try {
				requestTimeout = Integer.parseInt(delayString);
			}
			catch (NumberFormatException e) {
				LOG.error("Metadata provider '{}' had invalid timeout value '{}', using default value", identifier, timeoutString);
			}
		}
		LOG.debug("Metadata provider '{}' HTTP request timeout: {}ms", identifier, requestTimeout);
		builder.setConnectionTimeout(requestTimeout);

		if ("https".equalsIgnoreCase(metadataURL.getProtocol())) {

			String scheme = "https";
			Protocol baseHttps = Protocol.getProtocol(scheme);
			int defaultPort = baseHttps.getDefaultPort();

			ProtocolSocketFactory baseFactory = baseHttps.getSocketFactory();
			ProtocolSocketFactory customFactory = new CustomHttpsSocketFactory(baseFactory);

			Protocol customHttps = new Protocol(scheme, customFactory, defaultPort);
			Protocol.registerProtocol(scheme, customHttps);

			boolean disregardSslCertificate = false;
			String disregard = DatatypeHelper.safeTrimOrNullString(config.getAttribute("disregardSslCertificate"));
			if (null != disregard) {
				disregardSslCertificate = Boolean.parseBoolean(disregard);
			}
			LOG.debug("Metadata provider '{}' disregards server SSL certificate: {}", identifier, disregardSslCertificate);
			if (disregardSslCertificate) {
				builder.setHttpsProtocolSocketFactory(new TLSProtocolSocketFactory(null, buildNoTrustTrustManager()));
			}
		}
		setHttpProxySettings(builder, config);

		HttpClient httpClient = builder.buildClient();
		setHttpBasicAuthSettings(httpClient, config, metadataURL);
		return httpClient;
	}

	/**
	 * Builds a {@link X509TrustManager} which bypasses all X.509 validation steps. Inspired by
	 * {@link edu.internet2.middleware.shibboleth.common.config.metadata.HTTPMetadataProviderBeanDefinitionParser}
	 *
	 * @return the trustless trust manager
	 */
	private X509TrustManager buildNoTrustTrustManager() {
		X509TrustManager noTrustManager = new X509TrustManager() {

			/** {@inheritDoc} */
			@Override
			public void checkClientTrusted(X509Certificate[] certs, String auth) {
			}

			/** {@inheritDoc} */
			@Override
			public void checkServerTrusted(X509Certificate[] certs, String auth) {
			}

			/** {@inheritDoc} */
			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return new X509Certificate[] {};
			}
		};

		return noTrustManager;
	}

	/**
	 * Sets the HTTP proxy properties, if any, for the HTTP client used to fetch metadata. Inspired
	 * by
	 * {@link edu.internet2.middleware.shibboleth.common.config.metadata.HTTPMetadataProviderBeanDefinitionParser}
	 *
	 * @param builder the HTTP client builder
	 * @param config the metadata provider configuration
	 */
	private void setHttpProxySettings(HttpClientBuilder builder, Element config) {
		String proxyHost = DatatypeHelper.safeTrimOrNullString(config.getAttribute("proxyHost"));
		if (proxyHost == null) {
			return;
		}
		LOG.debug("Metadata provider '{}' HTTP proxy host: {}", identifier, proxyHost);
		builder.setProxyHost(proxyHost);

		String proxyPort = DatatypeHelper.safeTrimOrNullString(config.getAttribute("proxyPort"));
		if (null != proxyPort) {
			int port = Integer.parseInt(proxyPort);
			LOG.debug("Metadata provider '{}' HTTP proxy port: ", identifier, proxyPort);
			builder.setProxyPort(port);
		}

		String proxyUser = DatatypeHelper.safeTrimOrNullString(config.getAttribute("proxyUser"));
		if (proxyUser != null) {
			LOG.debug("Metadata provider '{}' HTTP proxy username: ", identifier, proxyUser);
			builder.setProxyUsername(proxyUser);
			LOG.debug("Metadata provider '{}' HTTP proxy password not shown", identifier);
			builder.setProxyPassword(DatatypeHelper.safeTrimOrNullString(config.getAttribute("proxyPassword")));
		}
	}

	/**
	 * Sets the basic authentication properties, if any, for the HTTP client used to fetch metadata.
	 * Inspired by
	 * {@link edu.internet2.middleware.shibboleth.common.config.metadata.HTTPMetadataProviderBeanDefinitionParser}
	 *
	 * @param httpClient the HTTP client
	 * @param config the metadata provider configuration
	 * @param metadataURL the URL from which metadata will be fetched
	 */
	private void setHttpBasicAuthSettings(HttpClient httpClient, Element config, URL metadataURL) {
		String authUser = DatatypeHelper.safeTrimOrNullString(config.getAttribute("basicAuthUser"));
		if (authUser == null) {
			return;
		}
		LOG.debug("Metadata provider '{}' HTTP Basic Auth username: {}", identifier, authUser);

		String authPassword = DatatypeHelper.safeTrimOrNullString(config.getAttribute("basicAuthPassword"));
		LOG.debug("Metadata provider '{}' HTTP Basic Auth password not show", identifier);

		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(authUser, authPassword);
		AuthScope authScope = new AuthScope(metadataURL.getHost(), metadataURL.getPort());
		httpClient.getState().setCredentials(authScope, credentials);
	}

	/**
	 * Create a suitable {@link AbstractObservableMetadataProvider} to read data from a URL.
	 * Inspired by
	 * {@link edu.internet2.middleware.shibboleth.common.config.metadata.HTTPMetadataProviderBeanDefinitionParser}
	 *
	 * @param element extra parameterization
	 * @param metadataURL the URL
	 * @return the provider
	 * @throws ShibbolethConfigurationException if we meet bad parameters
	 */
	private AbstractReloadingMetadataProvider buildHTTPProvider(Element element, URL metadataURL) throws ShibbolethConfigurationException {
		String spoolSpace = DatatypeHelper.safeTrimOrNullString(element.getAttribute("backingFile"));
		if (spoolSpace == null) {
			throw new ShibbolethConfigurationException("BackingFile must be specified for " + identifier);
		}

		try {
			return new FileBackedHTTPMetadataProvider(getTimer(), buildHttpClient(element, metadataURL), location, spoolSpace);
		}
		catch (MetadataProviderException e) {
			throw new ShibbolethConfigurationException("Error accessing metadataprovider from " + identifier + " at " + location);
		}
	}

	/**
	 * Create the filter chain for the metadata.
	 *
	 * @param el The configuration element
	 * @param warnOnBadBinding whether to warn on fail if the binding is bad.
	 * @return the configured filter chain
	 * @throws ShibbolethConfigurationException if any of the lookup fails
	 */
	private MetadataFilterChain buildFilterChain(Element el, boolean warnOnBadBinding) throws ShibbolethConfigurationException {
		NodeList itemElements = el.getElementsByTagNameNS(XMLConstants.CONFIG_NS, "Filter");
		List<MetadataFilter> filters = new ArrayList<MetadataFilter>(3 + itemElements.getLength());
		String param;
		MetadataFilterChain filterChain = null;

		filterChain = new MetadataFilterChain();

		//
		// Certificate first
		//

		param = DatatypeHelper.safeTrimOrNullString(el.getAttribute("certicateFile"));
		if (null != param) {
			filters.add(buildCertificateFilter(param));
		}

		//
		// Require Valid Until.
		//
		param = DatatypeHelper.safeTrimOrNullString(el.getAttribute("maxValidityInterval"));
		if (null != param) {
			filters.add(buildValidUntilFilter(param));
		}

		//
		// We always have a binding filter
		//
		filters.add(new BindingFilter(warnOnBadBinding));

		for (int i = 0; i < itemElements.getLength(); i++) {
			Element element = (Element) itemElements.item(i);
			if (null != element) {
				filters.add(createFilter(element));
			}
		}
		filterChain.setFilters(filters);

		return filterChain;
	}

	/**
	 * Create a <{@link MetadataFilter} filter as specified.
	 *
	 * @param element the specification.
	 * @return the filter
	 * @throws ShibbolethConfigurationException if there was a
	 */
	private MetadataFilter createFilter(Element element) throws ShibbolethConfigurationException {
		String ident = "<not specified>";
		String className = "<not specified>";

		ident = DatatypeHelper.safeTrimOrNullString(element.getAttribute("identifier"));

		if (null == ident) {
			LOG.error("Could not load filter with no identifier");
			throw new ShibbolethConfigurationException("Could not load filter with no identifier");
		}

		className = DatatypeHelper.safeTrimOrNullString(element.getAttribute("type"));
		if (null == className) {
			LOG.error("Filter " + identifier + " did not have a valid class name");
			throw new ShibbolethConfigurationException("Filter " + identifier + " did not have a valid class name");
		}
		try {
			//
			// Try to get hold of the Filter
			//
			Class<MetadataFilter> filterClass = (Class<MetadataFilter>) Class.forName(className);
			Class[] classParams = { Element.class };
			Constructor<MetadataFilter> constructor = filterClass.getConstructor(classParams);
			Object[] constructorParams = { element };
			return constructor.newInstance(constructorParams);
		}
		catch (ClassNotFoundException e) {
			LOG.error("Could not load filter " + ident + "()" + className + ") for " + identifier, e);
			throw new ShibbolethConfigurationException("Could not load filter", e);
		}
		catch (SecurityException e) {
			LOG.error("Could not load filter " + ident + "()" + className + ") for " + identifier, e);
			throw new ShibbolethConfigurationException("Could not load filter", e);
		}
		catch (NoSuchMethodException e) {
			LOG.error("Could not load filter " + ident + "()" + className + ") for " + identifier, e);
			throw new ShibbolethConfigurationException("Could not load filter", e);
		}
		catch (IllegalArgumentException e) {
			LOG.error("Could not load filter " + ident + "()" + className + ") for " + identifier, e);
			throw new ShibbolethConfigurationException("Could not load filter", e);
		}
		catch (InstantiationException e) {
			LOG.error("Could not load filter " + ident + "()" + className + ") for " + identifier, e);
			throw new ShibbolethConfigurationException("Could not load filter", e);
		}
		catch (IllegalAccessException e) {
			LOG.error("Could not load filter " + ident + "()" + className + ") for " + identifier, e);
			throw new ShibbolethConfigurationException("Could not load filter", e);
		}
		catch (InvocationTargetException e) {
			LOG.error("Could not load filter " + ident + "()" + className + ") for " + identifier, e);
			throw new ShibbolethConfigurationException("Could not load filter", e);
		}
	}

	/**
	 * Create validUntil Filter from the provided parameter.
	 *
	 * @param duration how long
	 * @return a filter
	 * @throws ShibbolethConfigurationException if the delay is bogus.
	 */
	private MetadataFilter buildValidUntilFilter(String duration) throws ShibbolethConfigurationException {
		MetadataFilter filter;
		try {
			filter = new RequiredValidUntilFilter(XMLHelper.durationToLong(duration) / 1000);
		}
		catch (IllegalArgumentException e) {
			throw new ShibbolethConfigurationException("Could not convert duration to delay", e);
		}
		return filter;

	}

	/**
	 * Create a new {@link MetadataFilter} from the specified certificate file.
	 *
	 * @param param The file
	 * @return The filter
	 * @throws ShibbolethConfigurationException if any subsidiary function fails
	 */
	private MetadataFilter buildCertificateFilter(String param) throws ShibbolethConfigurationException {
		Collection<X509Certificate> decodedCerts;
		try {
			decodedCerts = X509Util.decodeCertificate(new File(param));
		}
		catch (CertificateException e) {
			LOG.error("Could not load cerfificate for " + identifier, e);
			throw new ShibbolethConfigurationException("Could not load cerfificate for " + identifier, e);
		}
		Credential credential = credentialFor(new ArrayList(decodedCerts));
		SignatureTrustEngine engine = trustEngineFor(credential);
		SignatureValidationFilter filter = new SignatureValidationFilter(engine);
		filter.setRequireSignature(true);
		return filter;
	}

	/**
	 * Create a new {@link ExplicitKeySignatureTrustEngine} which understands the provided
	 * {@link Credential}. <br> Inspired by
	 * {@link edu.internet2.middleware.shibboleth.common.config.security.X509CredentialFactoryBean}
	 *
	 * @param credential the input
	 * @return the trust engine.
	 */
	private SignatureTrustEngine trustEngineFor(Credential credential) {
		StaticCredentialResolver credResolver = new StaticCredentialResolver(credential);

		List<KeyInfoProvider> keyInfoProviders = new ArrayList<KeyInfoProvider>();
		keyInfoProviders.add(new DSAKeyValueProvider());
		keyInfoProviders.add(new RSAKeyValueProvider());
		keyInfoProviders.add(new InlineX509DataProvider());
		KeyInfoCredentialResolver keyInfoCredResolver = new BasicProviderKeyInfoCredentialResolver(keyInfoProviders);

		return new ExplicitKeySignatureTrustEngine(credResolver, keyInfoCredResolver);
	}

	/**
	 * Create a {@link BasicX509Credential} from the provided certificates. <br/> Inspired by
	 * {@link edu.internet2.middleware.shibboleth.common.config.security.StaticExplicitKeySignatureTrustEngineFactoryBean}
	 *
	 * @param certificates Certificates from a file.
	 * @return an appropriate credential.
	 *
	 */
	private Credential credentialFor(List<X509Certificate> certificates) {
		BasicX509Credential credential = new BasicX509Credential();
		credential.setEntityCertificateChain(new ArrayList<X509Certificate>(certificates));
		credential.setEntityCertificate(certificates.get(0));
		credential.setPrivateKey(null);
		credential.setPublicKey(credential.getEntityCertificate().getPublicKey());
		return credential;
	}

	/**
	 * Based on (Shibboleth IdP/DS) 1.2 Origin.isMatch. There must have been a reason for it...
	 * [Kindas of] support for the search function in the wayf. This return many false positives but
	 * given the aim is to provide input for a pull down list...
	 *
	 * @param entity The entity to match.
	 * @param str The pattern to match against.
	 * @param config Provides list of tokens to not lookup
	 * @return Whether this entity matches
	 */

	private static boolean isMatch(EntityDescriptor entity, String str, HandlerConfig config) {

		Enumeration input = new StringTokenizer(str);
		while (input.hasMoreElements()) {
			String currentToken = (String) input.nextElement();

			if (config.isIgnoredForMatch(currentToken)) {
				continue;
			}

			currentToken = currentToken.toLowerCase();

			if (entity.getEntityID().indexOf(currentToken) > -1) {
				return true;
			}

			Organization org = entity.getOrganization();

			if (org != null) {

				List<OrganizationName> orgNames = org.getOrganizationNames();
				for (OrganizationName name : orgNames) {
					if (name.getName().getLocalString().toLowerCase().indexOf(currentToken) > -1) {
						return true;
					}
				}

				List<OrganizationDisplayName> orgDisplayNames = org.getDisplayNames();
				for (OrganizationDisplayName name : orgDisplayNames) {
					if (name.getName().getLocalString().toLowerCase().indexOf(currentToken) > -1) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Return all the Idp in the provided entities descriptor. If SearchMatches is non null it is
	 * populated with whatever of the IdPs matches the search string (as noted above).
	 *
	 * @param searchString to match with
	 * @param isWAYFprotocol whether this is Shibboleth or SAML Ds
	 * @param config parameter to matching
	 * @param searchMatches if non null is filled with such of the sites which match the string
	 * @return the sites which fit.
	 */
	protected Map<String, IdPSite> getIdPSites(String searchString, boolean isWAYFprotocol, HandlerConfig config, Collection<IdPSite> searchMatches) {
		XMLObject object;
		List<EntityDescriptor> entities;
		try {
			object = metadata.getMetadata();
		}
		catch (MetadataProviderException e) {
			LOG.error("Metadata for " + location + "could not be read", e);
			return null;
		}

		if (object == null) {
			return null;
		}

		// Fill in entities appropriately
		if (object instanceof EntityDescriptor) {
			entities = new ArrayList<EntityDescriptor>(1);
			entities.add((EntityDescriptor) object);
		}
		else if (object instanceof EntitiesDescriptor) {

			EntitiesDescriptor entitiesDescriptor = (EntitiesDescriptor) object;

			entities = getAllEntities(entitiesDescriptor);
		}
		else {
			return null;
		}

		//
		// populate the result (and the searchlist) from the entities list
		//

		TreeMap<String, IdPSite> result = new TreeMap<String, IdPSite>();

		for (EntityDescriptor entity : entities) {

			if (entity.isValid() && hasIdPRole(entity, isWAYFprotocol)) {

				IdPSite site = new IdPSite(entity);
				setAdvacedAttributes(site);

				result.put(site.getName(), site);
				if (searchMatches != null && isMatch(entity, searchString, config)) {
					searchMatches.add(site);
				}

			}
		} // iterate over all entities
		return result;

	}

	/**
	 * @param site
	 */
	private void setAdvacedAttributes(IdPSite site) {

		// groupAuthnContextClassRef

		IdPGroup idPGroup = new IdPGroup();
		idPGroup.setName(getGroup());
		idPGroup.setLogo(getGroupLogo());
		idPGroup.setButtonCssClass(getButtonCssClass());
		idPGroup.setOrder(getGroupOrder());
		idPGroup.setDescription(getGroupDescription());
		idPGroup.setDescriptionLabel(getGroupDescriptionLabel());

		List<Link> links = new ArrayList<Link>();
		for (Map.Entry<String, String> entry : getGroupLinks().entrySet()) {
			if (entry.getKey().startsWith("label.")) {
				Link link = new Link(null, entry.getKey(), entry.getValue());
				links.add(link);
			}
			else {
				Link link = new Link(entry.getKey(), null, entry.getValue());
				links.add(link);
			}
		}
		idPGroup.setLinks(links);

		site.setIdPGroup(idPGroup);
		site.setLogo(getLogo());
		site.setRequestParameters(getRequestParameters());
	}

	/**
	 * Return this sites (internal) identifier.
	 *
	 * @return the identifier
	 */
	protected String getIdentifier() {
		return identifier;
	}

	/**
	 * Return the human friendly name for this siteset.
	 *
	 * @return The friendly name
	 */
	protected String getDisplayName() {
		return displayName;
	}

	/**
	 * We do not need to look at a set if it doesn't know about the given SP. However if no SP is
	 * given (as per 1.1) then we do need to look. This calls lets us know whether this set is a
	 * canddiate for looking into.
	 *
	 * @param sPName the Sp we are interested in.
	 * @return whether the site contains the SP.
	 */
	protected boolean containsSP(String sPName) {

		//
		// Deal with the case where we do *not* want to search by
		// SP (also handles the 1.1 case)
		//

		if ((sPName == null) || (sPName.isEmpty())) {
			return true;
		}

		//
		// Get hold of the current object list so as to provoke observer to fire
		// if needs be.
		//

		XMLObject object;
		try {
			object = metadata.getMetadata();
		}
		catch (MetadataProviderException e) {
			return false;
		}
		//
		// Now lookup
		//

		if (object instanceof EntitiesDescriptor || object instanceof EntityDescriptor) {
			return spNames.contains(sPName);
		}
		else {
			return false;
		}
	}

	/**
	 * For plugin handling we need to know quickly if a metadataset contains the idp.
	 *
	 * @param idPName the IdP we are interested in.
	 * @return whether the site contains the IdP.
	 *
	 */

	protected boolean containsIdP(String idPName) {

		if ((idPName == null) || (idPName.length() == 0)) {
			return true;
		}

		//
		// Get hold of the current object list so as to provoke observer to fire
		// if needs be.
		//

		XMLObject object;
		try {
			object = metadata.getMetadata();
		}
		catch (MetadataProviderException e) {
			return false;
		}
		if (object instanceof EntitiesDescriptor || object instanceof EntityDescriptor) {
			return idpNames.contains(idPName);
		}
		else {
			return false;
		}
	}

	//
	// Now deal with plugins - these are delcared to use but we are
	// responsible for their parameter
	//

	/**
	 * Declares a plugin to the siteset.
	 *
	 * @param plugin what to declare
	 */
	protected void addPlugin(Plugin plugin) {

		if (plugins.containsKey(plugin)) {
			return;
		}

		PluginMetadataParameter param = plugin.refreshMetadata(metadata);

		plugins.put(plugin, param);
	}

	/**
	 * Return the parameter that this plugin uses.
	 *
	 * @param plugin the plugin we are getting the parameter for
	 * @return teh parameter.
	 */
	protected PluginMetadataParameter paramFor(Plugin plugin) {
		return plugins.get(plugin);
	}

	/**
	 * Return all the entities below the entities descriptor (collapsing lists of lists into lists).
	 *
	 * @param entitiesDescriptor the entities descriptor
	 * @return the collapsed list.
	 */

	private List<EntityDescriptor> getAllEntities(EntitiesDescriptor entitiesDescriptor) {
		List<EntityDescriptor> result = new ArrayList<EntityDescriptor>(entitiesDescriptor.getEntityDescriptors());
		for (EntitiesDescriptor entities : entitiesDescriptor.getEntitiesDescriptors()) {
			result.addAll(getAllEntities(entities));
		}
		return result;
	}

	/**
	 * Event handler.
	 *
	 * @param provider the provider we which is refreshing us.
	 */
	@Override
	public void onEvent(MetadataProvider provider) {
		Set<String> spNameSet = new HashSet<String>(0);
		Set<String> idpNameSet = new HashSet<String>(0);

		XMLObject obj;
		try {
			obj = provider.getMetadata();
		}
		catch (MetadataProviderException e) {
			LOG.error("Couldn't read metadata for " + location, e);
			return;
		}
		if (obj instanceof EntitiesDescriptor) {
			EntitiesDescriptor entitiesDescriptor = (EntitiesDescriptor) obj;

			for (EntityDescriptor entity : getAllEntities(entitiesDescriptor)) {
				if (hasSPRole(entity)) {
					spNameSet.add(entity.getEntityID());
				}
				if (hasIdPRole(entity, false)) {
					idpNameSet.add(entity.getEntityID());
				}
			}
		}
		else if (obj instanceof EntityDescriptor) {
			EntityDescriptor entity = (EntityDescriptor) obj;
			if (hasSPRole(entity)) {
				spNameSet.add(entity.getEntityID());
			}
			if (hasIdPRole(entity, false)) {
				idpNameSet.add(entity.getEntityID());
			}
		}
		else {
			LOG.error("Metadata for " + location + " isn't <EntitiesDescriptor> or <EntityDescriptor>");
			return;
		}
		//
		// Now that we have the new set sorted out commit it in
		//
		spNames = spNameSet;
		idpNames = idpNameSet;

		for (Plugin plugin : plugins.keySet()) {
			plugins.put(plugin, plugin.refreshMetadata(provider));
		}
	}

	/**
	 * Enumerate all the roles and see whether this entity can be an IdP.
	 *
	 * @param entity the entity under discoussion.
	 * @param isWAYFprotocol whether this is a WAF or DS request.
	 * @return true if one of the roles that entity has is IdPSSO
	 */
	private static boolean hasIdPRole(EntityDescriptor entity, boolean isWAYFprotocol) {

		if (isWAYFprotocol) {
			return null != IdPSite.getAddressForWAYF(entity);
		}

		List<RoleDescriptor> roles = entity.getRoleDescriptors();

		for (RoleDescriptor role : roles) {
			if (role instanceof IDPSSODescriptor) {
				//
				// So the entity knows how to be some sort of an IdP
				//
				return true;
			}
		}
		return false;
	}

	/**
	 * Enumerate all the roles and see whether this entity can be an SP.
	 *
	 * @param entity the entity under scrutiny.
	 * @return true if one of the roles that entity has is SPSSO
	 */
	private static boolean hasSPRole(EntityDescriptor entity) {
		List<RoleDescriptor> roles = entity.getRoleDescriptors();

		for (RoleDescriptor role : roles) {
			if (role instanceof SPSSODescriptor) {
				//
				// "I can do that"
				//
				return true;
			}
		}
		return false;
	}

	/**
	 * Return the idpSite for the given entity name.
	 *
	 * @param idpName the entityname to look up
	 * @return the associated idpSite
	 * @throws WayfException if the metadata couldn't resolve the entityID.
	 */
	protected IdPSite getSite(String idpName) throws WayfException {

		try {
			IdPSite idPSite = new IdPSite(metadata.getEntityDescriptor(idpName));
			setAdvacedAttributes(idPSite);
			return idPSite;
		}
		catch (MetadataProviderException e) {
			String s = "Couldn't resolve " + idpName + " in " + getDisplayName();
			LOG.error(s, e);
			throw new WayfException(s, e);
		}
	}

	/**
	 * Return the entity given the name.
	 *
	 * @param name the entityID
	 * @return the entity we are looking for.
	 * @throws WayfException if the metadata couldn't resolve the entityID.
	 */
	protected EntityDescriptor getEntity(String name) throws WayfException {
		try {
			return metadata.getEntityDescriptor(name);
		}
		catch (MetadataProviderException e) {
			String s = "Couldn't resolve " + name + " in " + getDisplayName();
			LOG.error(s, e);
			throw new WayfException(s, e);
		}

	}

	/**
	 * @return Returns the group.
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * @return Returns the groupAuthnContextClassRef.
	 */
	public String getGroupAuthnContextClassRef() {
		return groupAuthnContextClassRef;
	}

	/**
	 * @return Returns the authnContextClassRef.
	 */
	public String getAuthnContextClassRef() {
		return authnContextClassRef;
	}

	/**
	 * @return Returns the groupLogo.
	 */
	public String getGroupLogo() {
		return groupLogo;
	}

	/**
	 * @return Returns the groupOrder.
	 */
	public int getGroupOrder() {
		return groupOrder;
	}

	/**
	 * @return Returns the groupDescription.
	 */
	public String getGroupDescription() {
		return groupDescription;
	}

	/**
	 * @return Returns the groupLinks.
	 */
	public Map<String, String> getGroupLinks() {
		return groupLinks;
	}

	/**
	 * @return Returns the logo.
	 */
	public String getLogo() {
		return logo;
	}

	/**
	 * @return Returns the requestParameters.
	 */
	public List<RequestParameter> getRequestParameters() {
		return requestParameters;
	}

	/**
	 * @return Returns the buttonCssClass.
	 */
	public String getButtonCssClass() {
		return buttonCssClass;
	}

	/**
	 * @return the groupDescriptionLabel
	 */
	public String getGroupDescriptionLabel() {
		return groupDescriptionLabel;
	}

}
