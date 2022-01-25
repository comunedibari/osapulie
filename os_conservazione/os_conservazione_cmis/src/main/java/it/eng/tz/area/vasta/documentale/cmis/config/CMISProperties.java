package it.eng.tz.area.vasta.documentale.cmis.config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;


@Configuration
@ComponentScan(basePackages = { "it.eng.tz.area.vasta.documentale.cmis" })
@PropertySource( value={"classpath:config.properties"}, encoding="UTF-8", ignoreResourceNotFound=false)
public class CMISProperties {
	
	@Autowired
	private Environment env;
	
	private static CMISProperties _instance =  null;
	private static final Logger log = LoggerFactory.getLogger(CMISProperties.class.getName());
 

	public CMISProperties() {
		
	}

	
	public static CMISProperties instance() {
		if (_instance == null) {
			_instance = new CMISProperties();
		}

		return _instance;
	}

 
}
