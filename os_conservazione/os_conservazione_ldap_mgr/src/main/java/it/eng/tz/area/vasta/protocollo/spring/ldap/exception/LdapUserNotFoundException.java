package it.eng.tz.area.vasta.protocollo.spring.ldap.exception;

public class LdapUserNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 768698266562597131L;

	public LdapUserNotFoundException() {
		super();
		
	}

	public LdapUserNotFoundException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public LdapUserNotFoundException(String message) {
		super(message);
		
	}

	public LdapUserNotFoundException(Throwable cause) {
		super(cause);
	}

}
