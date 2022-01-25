/**
 * Copyright (c) 2011 Links Management & Technology S.p.A.
 */

package it.osapulie.pagamenti.test.infrastructure.util;

import it.osapulie.infrastructure.XMLHelper;
import it.osapulie.infrastructure.impl.XMLHelperImpl;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Unit tests for {@link XMLHelperImpl}.
 *
 * @author Mario Scalas
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class XMLHelperTests {

	private static final String CODICE_FISCALE = "sclmra75s07f112w";

	@Inject
	private XMLHelper xmlHelper;

	@Test
	public void testMarshallVisureCosapTosap() {
		// GIVEN
		/*
		 * PagamentiCosapRisposta datiOsap = new PagamentiCosapRisposta();
		 * datiOsap.setAnnoRiferimento( "2009" ); datiOsap.setNumeroConto( "123456" );
		 * 
		 * DatiOsap.UtenzeOsap utenze = new UtenzeOsap(); //utenze.setImportoDaPagare( 1000.00 );
		 * utenze.setMesi(2); utenze.setZonaUtenza( "ciccio" );
		 * datiOsap.getUtenzeOsap().add(utenze);
		 * 
		 * /*DatiOsap.UtenzeOsap.Rate rate = new Rate(); rate.setIdentificativoRata("1");
		 * rate.setNumeroRata(1); utenze.getRate().add( rate );
		 */
		// when
		/*
		 * String xml = xmlHelper.marshal(datiOsap);
		 * 
		 * DatiOsap object = xmlHelper.unmarshal( xml, DatiOsap.class ); List<UtenzeOsap>
		 * elencoUtenze = object.getUtenzeOsap();
		 * 
		 * 
		 * //then System.out.println( xml ); assertNotNull(object); assertEquals( 1,
		 * elencoUtenze.size() );
		 */
	}

	@Test
	public void testUnmarshallingVisureCosapTosap() throws Throwable {
		// given
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><visuraTosapRisposta xmlns=\"http://www.apulie.it/tributi\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" annoRiferimento=\"2004\"><utenzeOsap xmlns=\"\"><indirizzoUtenza>Piazza Pepe 122</indirizzoUtenza><superficie>12</superficie><zonaUtenza>1</zonaUtenza><descrizioneTariffa>1</descrizioneTariffa><importoDaPagare>12</importoDaPagare><mesi>10</mesi><rate><importoRata>400</importoRata><scadenzaRata>2006-09-13</scadenzaRata><numeroRata>1</numeroRata><identificativoRata>Campobasso</identificativoRata></rate><rate><importoRata>220</importoRata><scadenzaRata>2006-09-13</scadenzaRata><numeroRata>2</numeroRata><identificativoRata>Campobasso</identificativoRata></rate></utenzeOsap></visuraTosapRisposta>";
		xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><visuraTosapRisposta xmlns=\"http://www.apulie.it/tributi\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" annoRiferimento=\"2004\"><utenzeOsap><indirizzoUtenza>Piazza Pepe 122</indirizzoUtenza><superficie>12</superficie><zonaUtenza>1</zonaUtenza><descrizioneTariffa>1</descrizioneTariffa><importoDaPagare>12</importoDaPagare><mesi>10</mesi><rate><importoRata>400</importoRata><scadenzaRata>2006-09-13</scadenzaRata><numeroRata>1</numeroRata><identificativoRata>Campobasso</identificativoRata></rate><rate><importoRata>220</importoRata><scadenzaRata>2006-09-13</scadenzaRata><numeroRata>2</numeroRata><identificativoRata>Campobasso</identificativoRata></rate></utenzeOsap></visuraTosapRisposta>";
		// <?xml version="1.0" encoding="UTF-8" standalone="yes"?><visuraTosapRisposta
		// numeroConto="123456" annoRiferimento="2009"
		// xmlns="http://www.apulie.it/tributi"><utenzeOsap><zonaUtenza>ciccio</zonaUtenza><importoDaPagare>1000.00</importoDaPagare><mesi>2</mesi><rate><numeroRata>1</numeroRata><identificativoRata>1</identificativoRata></rate></utenzeOsap></visuraTosapRisposta>

		// when
		// DatiOsap object = xmlHelper.unmarshal( xml, DatiOsap.class );
		// List<UtenzeOsap> utenze = object.getUtenzeOsap();

		// then
		// assertNotNull(object);
		// assertEquals( 1, utenze.size() );

	}
}
