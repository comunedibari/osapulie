package it.eng.tz.area.vasta.protocollo.spring.ldap;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import it.eng.tz.area.vasta.protocollo.spring.configuration.LdapConfiguration;
import it.eng.tz.area.vasta.protocollo.spring.ldap.models.LdapUserModel;
import it.eng.tz.area.vasta.protocollo.spring.ldap.svc.LdapUserSvc;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={LdapConfiguration.class})
//Solo quando viene lanciato un test viene caricato questo file di properties
@TestPropertySource(value = { "classpath:test-ldap-config.properties" })
public class LdapTests {
	private static final Logger logger = LoggerFactory.getLogger(LdapTests.class.getName());
	@Autowired
	private LdapUserSvc ldapuserSvc;
	@Test
	public void testFindByUsername()
	{
		String username = "a.defano";
		try {
			LdapUserModel result = ldapuserSvc.findByUsername(username);
			logger.info("UTENTE TROVATO {}", result);
		} catch (Exception e) {
			logger.error("Errore nella ricerca per username dell'utente {}", username, e);
		}
	}
	@Test
	public void testFindByUsernameAndPassword()
	{
		String username = "a.defano";
		String pwd = "a.defano";
		try {
			LdapUserModel result = ldapuserSvc.findByUsernameAndPassword(username, pwd);
			logger.info("UTENTE TROVATO {}", result);
			
		} catch (Exception e) {
			logger.error("Errore nella ricerca per username dell'utente {}", username, e);
		}
	}	
	@Test
	public void testInserimento()
	{
		LdapUserModel utente = new LdapUserModel();
		utente.setCn("pp");
		utente.setCognome("immed");
		utente.setMail("ffimmed@ll.it");
		utente.setNome("ang");
		utente.setUsername("username");
		utente.setPassword("pass");
		try {
			ldapuserSvc.saveLdapUser(utente);
			
		} catch (Exception e) {
			logger.error("Errore nel salvataggio dell'utente {}", utente, e);
		}
	}
	@Test
	public void testInserimentoAndRicerca()
	{
		String username = UUID.randomUUID().toString();
		String password = UUID.randomUUID().toString();
		LdapUserModel utente = new LdapUserModel();
		utente.setCn(UUID.randomUUID().toString());
		utente.setCognome(UUID.randomUUID().toString());
		utente.setMail("ffimmed@ll.it");
		utente.setNome(UUID.randomUUID().toString());
		utente.setUsername(username);
		utente.setPassword(password);
		try {
			ldapuserSvc.saveLdapUser(utente);
			logger.info("UTENTE TROVATO {}", ldapuserSvc.findByUsernameAndPassword(username, password));
		} catch (Exception e) {
			logger.error("Errore nel salvataggio dell'utente {}", utente, e);
		}
	}	
}
