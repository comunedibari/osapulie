/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. 
 * via R. Scotellaro, 55 - 73100 - Lecce - http://www.linksmt.it
 * All rights reserved. 
 *
 * Contributors:
 *     Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.test.web.ws;

import it.osapulie.anagrafe.service.VisureService;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici;
import it.osapulie.anagrafe.web.ws.output.types.RichiestaDatiAnagrafici;
import it.osapulie.service.UploadDichiarazioniService;
import it.osapulie.web.portlet.util.MailSenderHelper;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PDDAdapterClientTest {
	
	public static void main( String[] args ) throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext( 
				"classpath:META-INF/spring/PDDAdapterClientTest-context.xml"
				);
		/*VisureService visureService = context.getBean( VisureService.class );
		
		RichiestaDatiAnagrafici richiesta = new RichiestaDatiAnagrafici();
		richiesta.setCodiceFiscale("BRTMMC82P53F531Q");

		DatiAnagrafici dati = visureService.richiediDatiAnagrafici( richiesta );
		
		System.out.println( dati );
		*/
		UploadDichiarazioniService upDic = context.getBean( UploadDichiarazioniService.class );
		
		//upDic.processaUploadDichiarazioni( "DICHIARAZIONE", null );
		
		System.out.println( "fatto" );
		
	}
}
