/**
 * Copyright (c) 2011 Links Management & Technology S.p.A.
 */

package it.osapulie.web.portlet;

/**
 * I ruoli utente supportati all'interno dell'applicazione.
 * TODO Risolvere l'ambiguità tra il ruolo UTENTE_REGISTRATO e CITTADINO.
 * @author Mario Scalas
 */
public class RuoliUtente {
	public static final String AMMINISTRATORE = "ROLE_Administrator";
	public static final String POWER_USER = "ROLE_Power User";
	public static final String UTENTE_REGISTRATO = "ROLE_User";
	public static final String CITTADINO = UTENTE_REGISTRATO;
}
