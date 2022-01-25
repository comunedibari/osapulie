package it.osapulie.pratiche.web.portlet.varie;



import java.lang.reflect.Field;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Classe per la gestione della configurazione della form di ins/mod pratica.
 * 
 * @author Maria Michela Birtolo
 * 
 */
@Component("portletConfiguration")
public class PortletConfiguration {


	@Value("#{applicationProperties.showStato}")
	private String showStato;
	
	public HashMap<String, String> getConfiguration() throws Exception{
		HashMap<String, String> map = new HashMap<String, String>();
		Field[] campi = this.getClass().getDeclaredFields();
		
		for(int i = 0 ; i < campi.length; i++){
			map.put(campi[i].getName(), (String)campi[i].get(this));
		}
		
		return map;
	}

}
