package eng.tz.la.core.util;

import java.io.IOException;
import java.util.Properties;

import eng.tz.la.core.Base64.InputStream;

public class UtilProperties {
	private static UtilProperties utilProperties;

	private Properties getResource() {
		Properties properties = new Properties();
		try {
			InputStream stream = (InputStream) this.getClass().getResourceAsStream("/application.properties");
			properties.load(stream);
		} catch (IOException e) {

		}

		return properties;

	}

	public static String getProperty(String key) {

		return getProperties().getProperty(key);
	}

	public static String getProperty(String key, String defaults) {

		return getProperties().getProperty(key, defaults);
	}

	public static Properties getProperties() {

		return getUtilProperty().getResource();
	}

	private static UtilProperties getUtilProperty() {
		if (utilProperties == null) {
			utilProperties = new UtilProperties();
		}

		return utilProperties;
	}

	private UtilProperties() {

	}
	
 
}
