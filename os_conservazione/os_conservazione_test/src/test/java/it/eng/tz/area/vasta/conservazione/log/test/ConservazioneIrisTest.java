package it.eng.tz.area.vasta.conservazione.log.test;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import it.eng.tz.area.vasta.conservazione.ws.sip.service.ConservazioneSipSvc;
import it.eng.tz.area.vasta.conservazione.ws.soap.results.ControlloEsitoSipResult;
import it.eng.tz.area.vasta.conservazione.ws.soap.results.ReceiveSipResult;
import it.eng.tz.area.vasta.conservazione.ws.soap.services.ConservazioneLogSvc;
import it.eng.tz.area.vasta.conservazione.ws.soap.services.EsitoSipSvc;
import it.eng.tz.area.vasta.protocollo.spring.configuration.IrisWsConservazioneConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={IrisWsConservazioneConfig.class})
@TestPropertySource("configuration.properties")
@ActiveProfiles(value= {"test"})
public class ConservazioneIrisTest {
	private static final Logger logger = LoggerFactory.getLogger(ConservazioneIrisTest.class.getName());
	@Autowired
	private ConservazioneLogSvc consSvc;
	@Autowired
	private EsitoSipSvc esitSvc;
	@Autowired
	private ConservazioneSipSvc daoSipSvc;
	@Test
	public void conserva() {
		File test = new File("logTest/logs_esempio");
		try {
			
			ReceiveSipResult risultato = consSvc.conservaLog(test.getAbsolutePath());
			logger.info(risultato.toString());
		} catch (Exception e) {
			logger.error("Errore nel processo di conservazione ", e);
		}
		finally
		{
			File[] files = test.listFiles();
			for (int i = 0; i < files.length; i++) {
				File aFile = files[i];
				String ext = FilenameUtils.getExtension(aFile.getName());
				if( ext.equals("xml") || ext.equals("zip") ) {
					if( logger.isInfoEnabled() )
					{
						logger.info("Cancello file {}",aFile.getName());
					}
					FileUtils.deleteQuietly(aFile);
				}
			}
		}
	}
	@Test
	public void controlloEsitoSip() {
		try {
			//Dovrebbe essere OK
			String idSipOk = "1a9d9e15-f6e9-4572-ba6f-d511d475e6a5";
			//SIP KO
			String idSipKo = "ec36f7ef-d9a1-4339-98a0-a6758987a3ac";
			ControlloEsitoSipResult cesr = esitSvc.controllaConservazione(idSipOk);
			logger.info(cesr.toString());
			ControlloEsitoSipResult cesrKo = esitSvc.controllaConservazione(idSipKo);
			logger.info(cesrKo.toString());
			
			String idSipOkNuovo = "122026a5-6420-456c-b171-86fb822bab21";
			ControlloEsitoSipResult cesrOkNuovo = esitSvc.controllaConservazione(idSipOkNuovo);
			logger.info(cesrOkNuovo.toString());
		} catch (Exception e) {
			logger.error("Errore controllo", e);
		}
	}
	@Test
	public void testRecuperoDaProcessare() {
		try {
			List<String> results = daoSipSvc.daProcessare();
			logger.info(results.toString());
		} catch (Exception e) {
			logger.error("Errore controllo", e);
		}
	}
}
