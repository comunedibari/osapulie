package it.eng.tz.area.vasta.protocollo.spring.configuration;

import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.sql.DataSource;

import org.apache.commons.ssl.KeyMaterial;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.SoapVersion;
import org.springframework.ws.soap.axiom.AxiomSoapMessageFactory;
import org.springframework.ws.transport.http.ClientHttpRequestMessageSender;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import it.eng.tz.area.vasta.protocollo.spring.configuration.util.KeyStoreInfo;
import it.eng.tz.area.vasta.protocollo.spring.configuration.util.ProxyRoutePlanner;
import it.eng.tz.area.vasta.protocollo.spring.configuration.util.WsKeepAliveStrategy;

@Configuration
@ComponentScan(basePackages= {"it.eng.tz.area.vasta.conservazione.ws"})
public class IrisWsConservazioneConfig {
	private static final Logger logger = LoggerFactory.getLogger(IrisWsConservazioneConfig.class.getName());
	@Autowired
	private Environment env;
	@Bean
	public ClientHttpRequestFactory requestFactory() throws Exception {
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setHttpClient(httpClient());
		return factory;
	}
	
	@Bean
	public RestTemplate restTemplate() throws Exception {
		RestTemplate restTem= new RestTemplate(requestFactory());
		return restTem;
	}
	
	@Bean(name="dsConservazione")
	public DataSource dbConservazioneSip() {
	    HikariConfig hikariConfig = new HikariConfig();
	    hikariConfig.setDriverClassName(env.getProperty("area.vasta.protocollo.db.driverClassName"));
	    hikariConfig.setJdbcUrl(env.getProperty("area.vasta.conservazione.sip.db.jdbcUrl")); 
	    hikariConfig.setUsername(env.getProperty("area.vasta.conservazione.sip.db.username"));
	    hikariConfig.setPassword(env.getProperty("area.vasta.conservazione.sip.db.password"));
	    hikariConfig.setMaximumPoolSize(Integer.parseInt(env.getProperty("area.vasta.conservazione.sip.db.maxPoolSize")));
	    hikariConfig.setConnectionTestQuery(env.getProperty("area.vasta.protocollo.db.validationQuery"));
	    hikariConfig.setPoolName("Area vasta Conservazione SIP POOL");
	    hikariConfig.setIdleTimeout(Integer.parseInt(env.getProperty("area.vasta.protocollo.db.maxIdleTime")));
	    hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", "true");
	    hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", "250");
	    hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "2048");
	    hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts", "true");
	    HikariDataSource dataSource = new HikariDataSource(hikariConfig);
	    LazyConnectionDataSourceProxy result = new LazyConnectionDataSourceProxy(dataSource);
	    
	    return result;
	}
	@Bean("txMgrConservazioneSip")
	@Autowired
	public PlatformTransactionManager txMgrConservazioneSip()
	{
		DataSourceTransactionManager result = new DataSourceTransactionManager(dbConservazioneSip());
		return result;
	}


	@Bean
	public HttpClient httpClient() throws Exception {
		int timeout = new Integer(env.getProperty("area.vasta.conservazione.web.http.client.timeout"));
		CloseableHttpClient httpClient = null;
		String keystores = env.getProperty("area.vasta.conservazione.certificate");
		PoolingHttpClientConnectionManager pcm = null;
		if (StringUtils.hasText(keystores)) {
			Resource jsonRes = new ClassPathResource(keystores);
			if (jsonRes.exists()) {

				List<KeyStoreInfo> ksInfo = objectMapper().readValue(jsonRes.getInputStream(),
						new TypeReference<List<KeyStoreInfo>>() {
				});
				SSLContext sslCtx = SSLContext.getInstance("TLS");
				List<KeyManager> keymanagers = new ArrayList<KeyManager>();
				for (KeyStoreInfo ksi : ksInfo) {
					String keystoreName = ksi.getNomeKeyStore();
					String keyStorePwd = ksi.getPasswordKeyStore();
					if (StringUtils.hasText(keystoreName)) {
						Resource keystoreRes = new ClassPathResource(keystoreName);
						KeyMaterial km = new KeyMaterial(keystoreRes.getInputStream(), keyStorePwd.toCharArray());
						KeyStore clientStore = km.getKeyStore();
						KeyManagerFactory kmfactory = KeyManagerFactory
								.getInstance(KeyManagerFactory.getDefaultAlgorithm());
						kmfactory.init(clientStore, keyStorePwd != null ? keyStorePwd.toCharArray() : null);
						keymanagers.addAll(Arrays.asList(kmfactory.getKeyManagers()));
					}
				}
				if (!keymanagers.isEmpty()) {
					// Crediamo a tutti
					X509TrustManager tm = new X509TrustManager() {

						@Override
						public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
								throws CertificateException {

						}

						@Override
						public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
								throws CertificateException {

						}

						@Override
						public java.security.cert.X509Certificate[] getAcceptedIssuers() {

							return null;
						}

					};
					sslCtx.init(keymanagers.toArray(new KeyManager[keymanagers.size()]), new TrustManager[] { tm },
							null);
					SSLConnectionSocketFactory sslConnectionFactory = new SSLConnectionSocketFactory(sslCtx);
					Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
							.register("https", sslConnectionFactory)
							.register("http", new PlainConnectionSocketFactory()).build();
					pcm = new PoolingHttpClientConnectionManager(registry);
				} else {
					if (logger.isInfoEnabled()) {
						logger.info(
								"Nessun keystore presente nel JSON di configurazione {}. Creo un PoolingHttpClientConnectionManager di default",
								keystores);
					}
					pcm = new PoolingHttpClientConnectionManager();
				}
			}
			else
			{
				pcm = new PoolingHttpClientConnectionManager();
			}
		} else {
			if (logger.isInfoEnabled()) {
				logger.info("Nessun keystore da caricare. Creo un PoolingHttpClientConnectionManager di default");
			}
			pcm = new PoolingHttpClientConnectionManager();
		}
		HttpClientBuilder hcb = HttpClientBuilder.create();
		pcm.closeIdleConnections(timeout, TimeUnit.MILLISECONDS);
		RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(timeout).setSocketTimeout(timeout)
				.setConnectTimeout(timeout).build();
		hcb.setDefaultRequestConfig(config);
		hcb.setConnectionManager(pcm).setConnectionManagerShared(true);
		boolean proxyEnable = new Boolean(env.getProperty("area.vasta.conservazione.web.http.client.proxyEnable"));
		if (proxyEnable) {
			int proxyPort = new Integer(env.getProperty("area.vasta.conservazione.web.http.client.portProxy"));
			String proxyHost = env.getProperty("area.vasta.conservazione.web.http.client.hostProxy");
			BasicCredentialsProvider credentialProvider = new BasicCredentialsProvider();
			AuthScope scope = new AuthScope(proxyHost, proxyPort);
			String usernameProxy = env.getProperty("area.vasta.conservazione.web.http.client.usernameProxy");
			String passwordProxy = env.getProperty("area.vasta.conservazione.web.http.client.passwordProxy");
			if (StringUtils.hasText(usernameProxy) && StringUtils.hasText(passwordProxy)) {

				UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(usernameProxy, passwordProxy);
				credentialProvider.setCredentials(scope, credentials);
			}
			else
			{
				String[] profiliSpringAttivi = env.getActiveProfiles();
				boolean controlloVmArguments = false;
				if( profiliSpringAttivi != null ) {
					for (int i = 0; i < profiliSpringAttivi.length; i++) {
						String profilo = profiliSpringAttivi[i];
						if( profilo.equals("test") )
						{
							controlloVmArguments = true;
							break;
						}
					}
				}
				if( controlloVmArguments )
				{
					usernameProxy = System.getProperty("usernameProxy");
					passwordProxy = System.getProperty("passwordProxy");
					if (StringUtils.hasText(usernameProxy) && StringUtils.hasText(passwordProxy)) {

						UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(usernameProxy, passwordProxy);
						credentialProvider.setCredentials(scope, credentials);
					}
				}
			}
			ProxyRoutePlanner proxyRoutPlanner = new ProxyRoutePlanner(new HttpHost(proxyHost, proxyPort),
					env.getProperty("area.vasta.conservazione.web.http.client.urlNotProxy"));
			hcb.setDefaultCredentialsProvider(credentialProvider).setRoutePlanner(proxyRoutPlanner);
		}
		WsKeepAliveStrategy cas = new WsKeepAliveStrategy();
		cas.setTimeout(new Long(timeout));
		hcb.setKeepAliveStrategy(cas);
		httpClient = hcb.build();
		return httpClient;
	}

	@Bean("objectMapper")
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		return mapper;
	}

	@Bean
	public AxiomSoapMessageFactory axiomSoapMessageFactory() {
		AxiomSoapMessageFactory factory = new AxiomSoapMessageFactory();
		factory.setPayloadCaching(true);
		factory.setAttachmentCaching(true);
		factory.setSoapVersion(SoapVersion.SOAP_11);
		return factory;
	}
	@Bean("sipResultMarshaller")
	public Jaxb2Marshaller sipResultMarshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("it.eng.tz.area.vasta.conservazione.ws.sip.result.client");
		marshaller.setMtomEnabled(true);
		formatXml(marshaller);
		return marshaller;
	}
	@Bean("sendSipMarshaller")
	public Jaxb2Marshaller sendSipMarshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("it.eng.tz.area.vasta.conservazione.ws.soap.send.sip.client");
		marshaller.setMtomEnabled(true);
		formatXml(marshaller);
		return marshaller;
	}
	@Bean("esitoSipMarshaller")
	public Jaxb2Marshaller esitoSipMarshaller() {
		Jaxb2Marshaller result = new Jaxb2Marshaller();
		result.setContextPath("it.eng.tz.area.vasta.conservazione.ws.soap.esito.sip.client");
		result.setMtomEnabled(true);
		formatXml(result);
		return result;
	}
	@Bean("receiveSipMarshaller")
	public Jaxb2Marshaller receiveSipMarshaller() {
		Jaxb2Marshaller result = new Jaxb2Marshaller();
		result.setContextPath("it.eng.tz.area.vasta.conservazione.ws.soap.receive.sip.client");
		result.setMtomEnabled(true);
		formatXml(result);
		return result;
	}
	@Bean("manifestSipMarshaller")
	public Jaxb2Marshaller manifestSipMarshaller() {
		Jaxb2Marshaller result = new Jaxb2Marshaller();
		result.setContextPath("it.eng.tz.area.vasta.conservazione.ws.sip.manifest.client");
		result.setMtomEnabled(true);
		formatXml(result);
		return result;
	}
	@Bean("sendSipWsTemplate")
	public WebServiceTemplate sendSipWsTemplate() throws Exception {
		WebServiceTemplate wsTemplateSendSip = new WebServiceTemplate(axiomSoapMessageFactory());
		wsTemplateSendSip.setMarshaller(sendSipMarshaller());
		wsTemplateSendSip.setUnmarshaller(sendSipMarshaller());
		wsTemplateSendSip.setMessageSender(clientHttpRequestMessageSender());
		wsTemplateSendSip.setDefaultUri(env.getProperty("area.vasta.conservazione.ws.send.sip.url"));
		return wsTemplateSendSip;
	}
	@Bean("esitoSipWsTemplate")
	public WebServiceTemplate esitoSipWsTemplate() throws Exception {
		WebServiceTemplate wsTemplateEsitoSip = new WebServiceTemplate(axiomSoapMessageFactory());
		wsTemplateEsitoSip.setMarshaller(esitoSipMarshaller());
		wsTemplateEsitoSip.setUnmarshaller(esitoSipMarshaller());
		wsTemplateEsitoSip.setMessageSender(clientHttpRequestMessageSender());
		wsTemplateEsitoSip.setDefaultUri(env.getProperty("area.vasta.conservazione.ws.esito.sip.url"));
		return wsTemplateEsitoSip;
	}
	@Bean("receiveSipWsTemplate")
	public WebServiceTemplate receiveSipWsTemplate() throws Exception {
		WebServiceTemplate wsTemplateEsitoSip = new WebServiceTemplate(axiomSoapMessageFactory());
		wsTemplateEsitoSip.setMarshaller(receiveSipMarshaller());
		wsTemplateEsitoSip.setUnmarshaller(receiveSipMarshaller());
		wsTemplateEsitoSip.setMessageSender(clientHttpRequestMessageSender());
		wsTemplateEsitoSip.setDefaultUri(env.getProperty("area.vasta.conservazione.ws.receive.sip.url"));
		return wsTemplateEsitoSip;
	}	
	@Bean
	public ClientHttpRequestMessageSender clientHttpRequestMessageSender() throws Exception {
		ClientHttpRequestMessageSender clientHttpRequestMessageSender = new ClientHttpRequestMessageSender(
				requestFactory());
		return clientHttpRequestMessageSender;
	}
	@Bean
	public FreeMarkerConfigurationFactoryBean getFreeMarkerConfiguration() {
		FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
		bean.setTemplateLoaderPath("classpath:/template_ftl/");
		return bean;
	}
	private void formatXml(Jaxb2Marshaller marshaller) {
		String[] profiliAttivi = env.getActiveProfiles();
		if( profiliAttivi != null )
		{
			for (int i = 0; i < profiliAttivi.length; i++) 
			{
				String profilo = profiliAttivi[i];
				if( profilo.equals("test") )
				{
					Map<String, Object> props = new java.util.HashMap<>();
					props.put(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);
					marshaller.setMarshallerProperties(props);
					break;
				}
			}
		}
	}
}
