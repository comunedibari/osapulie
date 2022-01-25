package it.osapulie.domain.dto;

/**
 * Un value object per le informazioni basilari sull'utente corrente (nome e ruoli).
 * 
 * @author Mario Scalas
 */
public class UserInfo {
	private final String userName;
	
	private final String[] roles;

	public UserInfo( String userName, String[] roles ) {
		super();
		this.userName = userName;
		this.roles = roles;
	}

	public String getUserName() {
		return userName;
	}

	public String[] getRoles() {
		return roles;
	}

	public boolean hasRole( String ruolo ) {
		for (String r : roles) {
			if (r.equals( ruolo )) {
				return true;
			}
		}
		return false;
	}
}
