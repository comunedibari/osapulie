/**
 * Copyright (c) 2011 Links Management & Technology S.p.A.
 */

package it.osapulie.test.infrastructure;

import static junit.framework.Assert.assertEquals;
import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;
import static org.junit.Assert.assertNotNull;

import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici;
import it.osapulie.anagrafe.web.ws.output.types.DatiUtente;
import it.osapulie.anagrafe.web.ws.output.types.RichiestaDatiAnagrafici;
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
	public void testMarshalling() throws Throwable {
		// given
		RichiestaDatiAnagrafici object = new RichiestaDatiAnagrafici();
		object.setCodiceFiscale(CODICE_FISCALE);

		// when
		String xml = xmlHelper.marshal(object);

		// then
		String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><richiestaDatiAnagrafici><codiceFiscale>sclmra75s07f112w</codiceFiscale></richiestaDatiAnagrafici>";
		assertXMLEqual(expected, xml);
	}

	@Test
	public void testUnmarshalling() throws Throwable {
		// given
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><richiestaDatiAnagrafici><codiceFiscale>sclmra75s07f112w</codiceFiscale></richiestaDatiAnagrafici>";

		// when
		RichiestaDatiAnagrafici object = xmlHelper.unmarshal(xml);

		// then
		assertEquals(CODICE_FISCALE, object.getCodiceFiscale());
	}

	@Test
	public void testUnmarshalling_QuandoManca_XmlRoolElement() throws Throwable {
		// given
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<visuraAnagrafica xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"VisuraPosizioneAnagrafica-risposta.xsd\"> "
				+ "	<toponimoIndirizzo>toponimoIndirizzo</toponimoIndirizzo>  <descrizioneVia>descrizioneVia</descrizioneVia>  " + "	<numeroCivico>numeroCivico</numeroCivico>"
				+ "	<componentiList><codiceFiscale>codiceFiscale</codiceFiscale><cognome>cognome</cognome><nome>nome</nome>"
				+ "	<dataNascita>2001-01-01</dataNascita><sesso>sesso</sesso><statoCivile>statoCivile</statoCivile><relazioneParentela>relazioneParentela</relazioneParentela>"
				+ "	<cittadinanzaItaliana>true</cittadinanzaItaliana><identificativoIndividuale>identificativoIndividuale</identificativoIndividuale>"
				+ "	<identificativoFamiglia>identificativoFamiglia</identificativoFamiglia><descComuneNascita>descComuneNascita</descComuneNascita>"
				+ "	<descProvinciaNascita>descProvinciaNascita</descProvinciaNascita></componentiList>" + "</visuraAnagrafica>";

		// when
		DatiAnagrafici object = xmlHelper.unmarshal(xml, DatiAnagrafici.class);

		// then
		assertNotNull(object);
		assertEquals("toponimoIndirizzo", object.getToponimoIndirizzo());
		assertEquals(1, object.getComponentiNucleoFamiliare().size());
	}

	@Test
	public void testDatiAnagraficiBase() throws Throwable {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" + "<datiUtente>" + "<nome>Mario</nome>" + "<cognome>Rossi</cognome>" + "<dataNascita>1981-01-05</dataNascita>"
				+ "<comuneNascita>Iglesias</comuneNascita>" + "<provinciaNascita>SS</provinciaNascita>" + "<indirizzo>ALZATA Piazza Pepe,124</indirizzo>" + "<cap>1238</cap>"
				+ "<comuneResidenza>Campobasso</comuneResidenza>" + "<provinciaResidenza>CB</provinciaResidenza>" + "<codiceIstatResidenza>70006</codiceIstatResidenza>" + "</datiUtente>";
		DatiUtente object = xmlHelper.unmarshal(xml, DatiUtente.class);

		assertNotNull(object);
		assertEquals("1238", object.getCap());
	}
}
