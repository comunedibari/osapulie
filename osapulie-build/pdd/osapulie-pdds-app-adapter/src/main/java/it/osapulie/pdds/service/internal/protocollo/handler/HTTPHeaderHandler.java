package it.osapulie.pdds.service.internal.protocollo.handler;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.BasicHandler;
import org.apache.axis.transport.http.HTTPConstants;
//import org.apache.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class HTTPHeaderHandler extends BasicHandler {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private final Logger log = LoggerFactory.getLogger(HTTPHeaderHandler.class);

	private String username;
	private String password;

	/**
	 *
	 */
	public HTTPHeaderHandler() {

		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		Resource[] locations = new Resource[2];
		locations[0] = new ClassPathResource("/webservices.properties");
		locations[1] = new FileSystemResource("/opt/osapulie/commons.properties");
		propertiesFactoryBean.setLocations(locations);
		try {
			propertiesFactoryBean.afterPropertiesSet();
			Properties properties = propertiesFactoryBean.getObject();
			username = properties.getProperty("protocollo.ws.user");
			password = properties.getProperty("protocollo.ws.password");
		}
		catch (IOException e) {
			log.error("HTTPHeaderHandler :: " + e.getMessage(), e);
		}

	}

	public void invoke(MessageContext ctx) throws AxisFault {
		Hashtable ht = (Hashtable) ctx.getProperty(HTTPConstants.REQUEST_HEADERS);
		if (ht == null) {
			ht = new Hashtable();
		}
		ht.put("Username", username);
		ht.put("Password", password);
		ctx.setProperty(HTTPConstants.REQUEST_HEADERS, ht);
	}
}