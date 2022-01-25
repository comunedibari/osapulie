/************************************************************************************
 * Copyright (c) 2011, 2016 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pdds.client.email;

import java.util.Properties;

import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * @author Gianluca Pindinelli
 *
 */
public class MailUtil {

	/**
	 * @param mailSender
	 * @param account
	 * @param debug
	 * @param fallback
	 */
	public void configureJavaMailSender(JavaMailSenderImpl mailSender, EmailAccount account, boolean debug, boolean fallback) {

		if (account != null) {
			Properties mailProperties = new Properties();
			mailProperties.put("mail.smtp.auth", account.getAuth());
			mailProperties.put("mail.smtp.debug", debug);
			Boolean starttls2 = account.getStarttls();
			if (starttls2 != null && starttls2) {
				mailProperties.put("mail.smtp.starttls.enable", starttls2);
				if (account.getStarttlsRequired() != null) {
					mailProperties.put("mail.smtp.starttls.required", account.getStarttlsRequired());
				}
				mailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
				mailProperties.put("mail.smtp.socketFactory.fallback", fallback);
				mailProperties.put("mail.smtp.socketFactory.port", account.getPort());
			}
			mailSender.setJavaMailProperties(mailProperties);
			mailSender.setHost(account.getHost());
			mailSender.setPort(account.getPort());
			mailSender.setProtocol(account.getProtocol());
			mailSender.setUsername(account.getUsername());
			mailSender.setPassword(account.getPassword());
		}
	}

}
