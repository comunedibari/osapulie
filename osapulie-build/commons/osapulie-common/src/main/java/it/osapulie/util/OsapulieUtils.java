/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 * 
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.util;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.portal.kernel.mail.Account;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.util.mail.MailEngine;

/**
 * Classe di utility per il core di OSApulie.
 * 
 * @author Gianluca Pindinelli
 * 
 */
public class OsapulieUtils {

	protected static Logger log = LoggerFactory.getLogger(OsapulieUtils.class.getName());

	/**
	 * Verifica che il server SMTP per l'invio email configurato su Liferay sia raggiungibile. Da
	 * utilizzare prima di richiamare <code>MailEngine.send(message);</code> di Liferay (nel quale
	 * non vengono sollevate al chiamante le eccezzioni di tipo {@link MessagingException}).
	 * 
	 * @return
	 */
	public static boolean checkLiferaySmtpConnection() {

		Session session = MailEngine.getSession();

		String smtpHost = getSMTPProperty(session, "host");
		int smtpPort = GetterUtil.getInteger(getSMTPProperty(session, "port"), Account.PORT_SMTP);
		String user = getSMTPProperty(session, "user");
		String password = getSMTPProperty(session, "password");

		String protocol = GetterUtil.getString(session.getProperty("mail.transport.protocol"), Account.PROTOCOL_SMTP);

		try {
			Transport transport = session.getTransport(protocol);
			transport.connect(smtpHost, smtpPort, user, password);
			if (!transport.isConnected()) {
				return false;
			}
			transport.close();
			return true;
		}
		catch (MessagingException e) {
			log.error("checkLiferaySmtpConnection :: " + e.getMessage(), e);
			return false;
		}
	}

	/**
	 * Verifica che il server SMTP per l'invio email configurato con i parametri di input sia
	 * raggiungibile. Da utilizzare prima di richiamare <code>MailEngine.send(message);</code> di
	 * Liferay (nel quale non vengono sollevate al chiamante le eccezzioni di tipo
	 * {@link MessagingException}).
	 * 
	 * @param smtpHost
	 * @param smtpPort
	 * @param user
	 * @param password
	 * @param protocol
	 * @return
	 */
	public static boolean checkLiferaySmtpConnection(String smtpHost, int smtpPort, String user, String password, String protocol) {

		Session session = MailEngine.getSession();

		try {
			Transport transport = session.getTransport(protocol);
			transport.connect(smtpHost, smtpPort, user, password);
			if (!transport.isConnected()) {
				return false;
			}
			transport.close();
			return true;
		}
		catch (MessagingException e) {
			log.error("checkLiferaySmtpConnection :: " + e.getMessage(), e);
			return false;
		}
	}

	private static String getSMTPProperty(Session session, String suffix) {

		String protocol = GetterUtil.getString(session.getProperty("mail.transport.protocol"));

		if (protocol.equals(Account.PROTOCOL_SMTPS)) {
			return session.getProperty("mail.smtps." + suffix);
		}
		else {
			return session.getProperty("mail.smtp." + suffix);
		}
	}

}
