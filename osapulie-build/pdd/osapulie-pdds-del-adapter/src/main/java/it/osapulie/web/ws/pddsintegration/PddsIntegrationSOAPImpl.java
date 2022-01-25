/**
 * PddsIntegrationSOAPImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.web.ws.pddsintegration;

import it.osapulie.web.ws.pddsintegration.pdd.PortaDelegataComuni;

import java.rmi.RemoteException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.log4j.Logger;



/**
 * Questa classe implementa i servizi di integrazione tra Portale OsApulie e porte applicative dei vari comuni
 * @author Maria Michela Birtolo
 */
public class PddsIntegrationSOAPImpl implements it.osapulie.web.ws.pddsintegration.PddsIntegration_PortType{
	
	private Logger logger = LoggerFactory.getLogger(getClass().getName());
	/**
	 * METODO CHE IN BASE AI PARAMETRI RICEVUTI INVOCA UN SERVIZIO DELLA PORTA APPLICATIVA
	 * @param richiesta
	 * @param servizio
	 * @param url
	 *  @return string
	 *  @throws RemoteException 
	 */
    public String getRichiestaPdd(String richiesta, String servizio, String url) throws java.rmi.RemoteException {
    	String risposta = null;
    	try{  	
    		logger.debug("INIZIO getRichiestaPdd - servizio: "+servizio+" / url: "+url);
    		PortaDelegataComuni porta = new PortaDelegataComuni();
	    	risposta = porta.esegui(richiesta, servizio, url);
    	}catch(Exception e){
    		e.printStackTrace();
            logger.error(e.getMessage());
            RemoteException ax = new RemoteException(e.getMessage(),e.getCause());	            
            throw ax;            
    	}
        return risposta;
    }

}
