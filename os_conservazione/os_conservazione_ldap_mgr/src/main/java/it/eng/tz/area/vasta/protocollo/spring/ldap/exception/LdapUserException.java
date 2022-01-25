package it.eng.tz.area.vasta.protocollo.spring.ldap.exception;

public class LdapUserException extends Exception {

	private static final long serialVersionUID = 2013022095329953964L;

	public LdapUserException() {
		super();
		
	}

	public LdapUserException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public LdapUserException(String message) {
		super(message);
		
	}

	public LdapUserException(Throwable cause) {
		super(cause);
	}

}
