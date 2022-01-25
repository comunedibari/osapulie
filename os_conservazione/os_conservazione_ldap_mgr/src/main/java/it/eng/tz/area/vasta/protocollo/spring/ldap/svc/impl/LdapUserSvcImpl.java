package it.eng.tz.area.vasta.protocollo.spring.ldap.svc.impl;

import static it.eng.tz.area.vasta.protocollo.spring.ldap.models.LdapUserModel.ATTRIBUTO_LDAP_CN;
import static it.eng.tz.area.vasta.protocollo.spring.ldap.models.LdapUserModel.ATTRIBUTO_LDAP_COGNOME;
import static it.eng.tz.area.vasta.protocollo.spring.ldap.models.LdapUserModel.ATTRIBUTO_LDAP_MAIL;
import static it.eng.tz.area.vasta.protocollo.spring.ldap.models.LdapUserModel.ATTRIBUTO_LDAP_NOME;
import static it.eng.tz.area.vasta.protocollo.spring.ldap.models.LdapUserModel.ATTRIBUTO_LDAP_PASSWORD;
import static it.eng.tz.area.vasta.protocollo.spring.ldap.models.LdapUserModel.ATTRIBUTO_LDAP_USERNAME;
import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.util.List;

import javax.naming.Name;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import it.eng.tz.area.vasta.protocollo.spring.ldap.exception.LdapUserException;
import it.eng.tz.area.vasta.protocollo.spring.ldap.exception.LdapUserNotFoundException;
import it.eng.tz.area.vasta.protocollo.spring.ldap.models.LdapUserModel;
import it.eng.tz.area.vasta.protocollo.spring.ldap.models.mappers.LdapUserModelMapper;
import it.eng.tz.area.vasta.protocollo.spring.ldap.svc.LdapUserSvc;
@SuppressWarnings("deprecation")
@Service
public class LdapUserSvcImpl implements LdapUserSvc {
	private static final Logger logger = LoggerFactory.getLogger(LdapUserSvcImpl.class.getName());
	@Autowired
	private LdapTemplate ldapTemplate;
	@Autowired
	@Qualifier("ldapPwdEncoder")
	private LdapShaPasswordEncoder ldapPwdEncoder;
	@Override
	public LdapUserModel findByUsername(String username) throws LdapUserNotFoundException {
		if( !StringUtils.hasText(username) )
		{
			throw new IllegalArgumentException("Impossibile ricercare l'utente. Passato un username non valido <"+username+">");
		}
		LdapQuery query = query()
				.attributes(ATTRIBUTO_LDAP_CN, ATTRIBUTO_LDAP_COGNOME, ATTRIBUTO_LDAP_MAIL, ATTRIBUTO_LDAP_NOME, ATTRIBUTO_LDAP_PASSWORD, ATTRIBUTO_LDAP_USERNAME)
				.where("objectClass").is("person")
				.and(ATTRIBUTO_LDAP_USERNAME).is(username);
		List<LdapUserModel> results = ldapTemplate.search(query, new LdapUserModelMapper());
		if( logger.isTraceEnabled() )
		{
			logger.trace("RICERCA UTENTE LDAP CON USERNAME [{}] E QUERY [{}]", username, query);
		}
		if( results == null || results.isEmpty() )
		{
			throw new LdapUserNotFoundException("Nessun utente trovato per la username ["+username+"]");
		}
		if( results.size() > 1 )
		{
			throw new IllegalStateException("Trovati "+results.size()+" utenti con username <"+username+">");
		}
		return results.get(0);
	}

	@Override
	public LdapUserModel findByUsernameAndPassword(String username, String password) throws LdapUserNotFoundException {
		if( !StringUtils.hasText(username) )
		{
			throw new IllegalArgumentException("Impossibile ricercare l'utente. Passato un username non valido <"+username+">");
		}
		LdapQuery query = query()
				.attributes(ATTRIBUTO_LDAP_CN, ATTRIBUTO_LDAP_COGNOME, ATTRIBUTO_LDAP_MAIL, ATTRIBUTO_LDAP_NOME, ATTRIBUTO_LDAP_PASSWORD, ATTRIBUTO_LDAP_USERNAME)
				.where("objectClass").is("person")
				.and(ATTRIBUTO_LDAP_USERNAME).is(username);
		List<LdapUserModel> results = ldapTemplate.search(query, new LdapUserModelMapper());
		if( logger.isTraceEnabled() )
		{
			logger.trace("RICERCA UTENTE LDAP CON USERNAME [{}] E QUERY [{}]", username, query);
		}
		if( results == null || results.isEmpty() )
		{
			throw new LdapUserNotFoundException("Nessun utente trovato per la username ["+username+"]");
		}
		if( results.size() > 1 )
		{
			throw new IllegalStateException("Trovati "+results.size()+" utenti con username <"+username+">");
		}
		LdapUserModel result = results.get(0);
		if(!ldapPwdEncoder.matches(password, result.getPassword()))
		{
			throw new LdapUserNotFoundException("Password errata per l'utente con username ["+username+"]");
		}
		return results.get(0);
	}

	@Override
	public void saveLdapUser(LdapUserModel user) throws LdapUserException {
		ldapTemplate.bind(buildDn(user), null, toAttrs(user));
	}
	private Attributes toAttrs(LdapUserModel user)
	{
		Attributes attrs = new BasicAttributes();
		BasicAttribute ocattr = new BasicAttribute("objectclass");
		ocattr.add("top");
		ocattr.add("person");
		ocattr.add("inetOrgPerson");
		ocattr.add("organizationalPerson");
		attrs.put(ocattr);
		BasicAttribute cnattr = new BasicAttribute("cn");
		cnattr.add(user.getUsername());
		cnattr.add("ou=gruppi,ou=gruppi,dc=aaf,dc=it");
		attrs.put(cnattr);
		attrs.put(ATTRIBUTO_LDAP_COGNOME, user.getCognome());
		attrs.put(ATTRIBUTO_LDAP_MAIL, user.getMail());
		attrs.put(ATTRIBUTO_LDAP_NOME, user.getNome());
		attrs.put(ATTRIBUTO_LDAP_PASSWORD, ldapPwdEncoder.encode(user.getPassword()));
		return attrs;
	}
	private Name buildDn(LdapUserModel user) {
	    return LdapNameBuilder
	    		.newInstance()
	    		.add("cn="+user.getUsername())
	    		.build();
	  }	
}
