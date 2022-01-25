/************************************************************************************
 * Copyright (c) 2011, 2016 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pdds.client.email;

import java.util.List;
import java.util.Properties;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;

/**
 * Client per invio di email.
 *
 * @author Gianluca Pindinelli
 *
 */
public class EmailClient {

	/**
	 *
	 */
	private String protocol;
	private String host;
	private int port;
	private int socketPort;
	private boolean auth;
	private boolean starttls;
	private boolean startlls_required;
	private boolean debug;
	private boolean fallback;
	private String from;
	private String username;
	private String password;
	private int timeout;
	private int maxSendCounter;

	private MailUtil mailUtil;

	private JavaMailSenderImpl mailSender;

	private final Logger log = LoggerFactory.getLogger(EmailClient.class);

	public void init() {

		mailSender = new JavaMailSenderImpl();

		Properties mailProperties = new Properties();
		mailProperties.put("mail.smtp.timeout", timeout);
		mailProperties.put("mail.smtps.timeout", timeout);
		mailProperties.put("mail.smtp.connectiontimeout", timeout);
		mailProperties.put("mail.smtps.connectiontimeout", timeout);
		mailProperties.put("mail.smtp.auth", auth);
		if (starttls) {
			mailProperties.put("mail.smtp.starttls.enable", starttls);
		}
		if (startlls_required) {
			mailProperties.put("mail.smtp.starttls.required", startlls_required);
		}
		if (socketPort != 0) {
			mailProperties.put("mail.smtp.socketFactory.port", socketPort);
			mailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		}
		mailProperties.put("mail.smtp.debug", debug);
		mailProperties.put("mail.smtp.socketFactory.fallback", fallback);

		mailSender.setJavaMailProperties(mailProperties);
		mailSender.setHost(host);
		mailSender.setPort(port);
		mailSender.setProtocol(protocol);
		mailSender.setUsername(username);
		mailSender.setPassword(password);

	}

	@Async
	public void send(final Message message) throws Exception {

		boolean mailSended = false;
		int mailSendCounter = 1;
		while (!mailSended && mailSendCounter <= maxSendCounter) {

			log.info("Tentativo di invio email '" + message.getSubject() + "' num. " + mailSendCounter + " in corso...");

			try {

				// Sovrascrittura parametri account invio
				if (message.getAccount() != null) {
					mailUtil.configureJavaMailSender(mailSender, message.getAccount(), false, false);
				}

				mailSender.send(new MimeMessagePreparator() {

					@Override
					public void prepare(MimeMessage mimeMessage) throws Exception {
						MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

						// From
						InternetAddress sender = new InternetAddress();
						sender.setAddress(from);
						Contact fromAddress = message.getFrom();
						if (fromAddress != null) {
							sender.setAddress(fromAddress.getEmail());
							sender.setPersonal(fromAddress.getName());
						}
						mimeMessageHelper.setFrom(sender);

						// Contact
						List<Contact> contact = message.getTo();
						if (contact != null) {
							for (Contact toRecipient : contact) {
								InternetAddress recipient = new InternetAddress();
								recipient.setAddress(toRecipient.getEmail());
								recipient.setPersonal(toRecipient.getName());
								mimeMessageHelper.addTo(recipient);
							}
						}
						// CC
						List<Contact> cc = message.getCc();
						if (cc != null) {
							for (Contact ccRecipient : cc) {
								InternetAddress recipient = new InternetAddress();
								recipient.setAddress(ccRecipient.getEmail());
								recipient.setPersonal(ccRecipient.getName());
								mimeMessageHelper.addCc(recipient);
							}
						}

						// BCC
						List<Contact> bcc = message.getBcc();
						if (bcc != null) {
							for (Contact bccRecipient : bcc) {
								InternetAddress recipient = new InternetAddress();
								recipient.setAddress(bccRecipient.getEmail());
								recipient.setPersonal(bccRecipient.getName());
								mimeMessageHelper.addBcc(recipient);
							}
						}

						mimeMessageHelper.setSubject(message.getSubject());

						if (message.isHtml()) {
							mimeMessageHelper.setText(message.getText(), true);
						}
						else {
							mimeMessageHelper.setText(message.getText());
						}

						// Attachments
						List<Attachment> attachments = message.getAttachments();
						if (attachments != null) {
							for (Attachment attachment : attachments) {
								ByteArrayDataSource source = new ByteArrayDataSource(attachment.getContent(), attachment.getContentType());
								mimeMessageHelper.addAttachment(attachment.getName(), source);
							}
						}
					}
				});
				log.info("Tentativo di invio email '" + message.getSubject() + "' num. " + mailSendCounter + " completato con successo!");
				mailSended = true;
			}
			catch (Exception e) {
				log.error("Tentativo di invio email '" + message.getSubject() + "' num. " + mailSendCounter + " fallito :: " + e.getMessage(), e);
				mailSended = false;
				mailSendCounter++;
			}
		}

		if (!mailSended) {
			log.error("Invio email '" + message.getSubject() + "' fallito per numero massimo tentativi raggiunto (" + maxSendCounter + ").");
		}
		else {
			log.info("Invio email '" + message.getSubject() + "' avvenuto con successo ");
		}

	}

	/**
	 * @return the protocol
	 */
	public String getProtocol() {
		return protocol;
	}

	/**
	 * @param protocol the protocol to set
	 */
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return the socketPort
	 */
	public int getSocketPort() {
		return socketPort;
	}

	/**
	 * @param socketPort the socketPort to set
	 */
	public void setSocketPort(int socketPort) {
		this.socketPort = socketPort;
	}

	/**
	 * @return the auth
	 */
	public boolean isAuth() {
		return auth;
	}

	/**
	 * @param auth the auth to set
	 */
	public void setAuth(boolean auth) {
		this.auth = auth;
	}

	/**
	 * @return the starttls
	 */
	public boolean isStarttls() {
		return starttls;
	}

	/**
	 * @param starttls the starttls to set
	 */
	public void setStarttls(boolean starttls) {
		this.starttls = starttls;
	}

	/**
	 * @return the startlls_required
	 */
	public boolean isStartlls_required() {
		return startlls_required;
	}

	/**
	 * @param startlls_required the startlls_required to set
	 */
	public void setStartlls_required(boolean startlls_required) {
		this.startlls_required = startlls_required;
	}

	/**
	 * @return the debug
	 */
	public boolean isDebug() {
		return debug;
	}

	/**
	 * @param debug the debug to set
	 */
	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	/**
	 * @return the fallback
	 */
	public boolean isFallback() {
		return fallback;
	}

	/**
	 * @param fallback the fallback to set
	 */
	public void setFallback(boolean fallback) {
		this.fallback = fallback;
	}

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the mailUtil
	 */
	public MailUtil getMailUtil() {
		return mailUtil;
	}

	/**
	 * @param mailUtil the mailUtil to set
	 */
	public void setMailUtil(MailUtil mailUtil) {
		this.mailUtil = mailUtil;
	}

	/**
	 * @return the timeout
	 */
	public int getTimeout() {
		return timeout;
	}

	/**
	 * @param timeout the timeout to set
	 */
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	/**
	 * @return the maxSendCounter
	 */
	public int getMaxSendCounter() {
		return maxSendCounter;
	}

	/**
	 * @param maxSendCounter the maxSendCounter to set
	 */
	public void setMaxSendCounter(int maxSendCounter) {
		this.maxSendCounter = maxSendCounter;
	}
}
