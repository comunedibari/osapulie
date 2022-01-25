package it.eng.tz.area.vasta.protocollo.db.tests;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import it.eng.tz.area.vasta.protocollo.spring.configuration.DBConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={DBConfiguration.class})
//Solo quando viene lanciato un test viene caricato questo file di properties
@TestPropertySource(value = { "classpath:test-db-config.properties" })
public class TestRicercaRegistriProtocolloDB {
	private static final Logger logger = LoggerFactory.getLogger(TestRicercaRegistriProtocolloDB.class.getName());

	@Test
	public void testRicercaRegProtDb()
	{
		try {
			DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MM/yyyy");
			DateTime today = new DateTime();
			DateTime from = today.withTimeAtStartOfDay().minusYears(1);
			
			logger.info("testRicercaRegProtDb ", dtf.parseDateTime("21/12/2017"));
		} catch (Exception e) {
			logger.error("Errore", e);
		}
	}
}
