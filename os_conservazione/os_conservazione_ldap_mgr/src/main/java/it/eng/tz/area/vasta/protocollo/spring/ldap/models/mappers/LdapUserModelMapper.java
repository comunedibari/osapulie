package it.eng.tz.area.vasta.protocollo.spring.ldap.models.mappers;

import javax.naming.NamingException;

import org.springframework.ldap.core.ContextMapper;
import org.springframework.ldap.core.DirContextAdapter;

import it.eng.tz.area.vasta.protocollo.spring.ldap.models.LdapUserModel;
import static it.eng.tz.area.vasta.protocollo.spring.ldap.models.LdapUserModel.ATTRIBUTO_LDAP_CN;
import static it.eng.tz.area.vasta.protocollo.spring.ldap.models.LdapUserModel.ATTRIBUTO_LDAP_COGNOME;
import static it.eng.tz.area.vasta.protocollo.spring.ldap.models.LdapUserModel.ATTRIBUTO_LDAP_MAIL;
import static it.eng.tz.area.vasta.protocollo.spring.ldap.models.LdapUserModel.ATTRIBUTO_LDAP_NOME;
import static it.eng.tz.area.vasta.protocollo.spring.ldap.models.LdapUserModel.ATTRIBUTO_LDAP_PASSWORD;
public class LdapUserModelMapper implements ContextMapper<LdapUserModel> {

	@Override
	public LdapUserModel mapFromContext(Object context) throws NamingException {
		DirContextAdapter dirCtxAdapter = (DirContextAdapter)context;
		LdapUserModel result = new LdapUserModel();
		//Nell'attributo LDAP "cn" sono salvate due informazioni: username e gruppo (e.g. ou=gruppi,ou=gruppi,dc=aaf,dc=it)
		String[] attrs = dirCtxAdapter.getStringAttributes(ATTRIBUTO_LDAP_CN);
		for (int i = 0; i < attrs.length; i++) {
			String attr = attrs[i];
			if( attr.indexOf(",ou=gruppi,") == -1 )
			{
				result.setUsername(attr);
			}
			else
			{
				result.setCn(attr);
			}
		}
		result.setCognome(dirCtxAdapter.getStringAttribute(ATTRIBUTO_LDAP_COGNOME));
		result.setNome(dirCtxAdapter.getStringAttribute(ATTRIBUTO_LDAP_NOME));
		result.setMail(dirCtxAdapter.getStringAttribute(ATTRIBUTO_LDAP_MAIL));
		byte[] pwd = (byte[])dirCtxAdapter.getObjectAttribute(ATTRIBUTO_LDAP_PASSWORD);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < pwd.length; i++) {
			sb.append((char)pwd[i]);
		}
		result.setPassword(sb.toString());
		return result;
	}

}
