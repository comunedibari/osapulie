package it.eng.tz.area.vasta.protocollo.spring.ldap.svc;

import it.eng.tz.area.vasta.protocollo.spring.ldap.exception.LdapUserException;
import it.eng.tz.area.vasta.protocollo.spring.ldap.exception.LdapUserNotFoundException;
import it.eng.tz.area.vasta.protocollo.spring.ldap.models.LdapUserModel;

public interface LdapUserSvc {

	LdapUserModel findByUsername(String username) throws LdapUserNotFoundException;
	LdapUserModel findByUsernameAndPassword(String username, String password) throws LdapUserNotFoundException;
	void saveLdapUser( LdapUserModel user ) throws LdapUserException;
}
