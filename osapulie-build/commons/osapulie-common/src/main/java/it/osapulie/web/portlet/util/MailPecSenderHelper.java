package it.osapulie.web.portlet.util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.multipart.MultipartFile;

import com.liferay.portal.kernel.mail.Account;
import com.liferay.portal.kernel.mail.MailMessage;
import com.liferay.portal.kernel.mail.SMTPAccount;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.util.mail.MailEngine;
import com.liferay.util.mail.MailEngineException;

import it.linksmt.pec.SMTPManager;
import it.osapulie.util.OsapulieUtils;

/**
 * Classe per l'invio di mail di PEC basate su Velocity templates e con allegati.
 *
 * @author Gianluca Pindinelli
 *
 */
public class MailPecSenderHelper {

	private final Logger log = LoggerFactory.getLogger(MailPecSenderHelper.class);

	private VelocityEngine velocityEngine;

	/**
	 * Metodo per l'invio di una PEC mail.
	 *
	 * @param subject
	 * @param templateFileName
	 * @param templateModel
	 * @param attachment
	 * @param to
	 * @throws CommunicationException
	 */
	public void sendMail(String subject, String templateFileName, Map<String, String> templateModel, File[] attachment, String[] to) throws CommunicationException {

		log.debug("sendMail :: entering method");

		MailMessage message = new MailMessage();

		try {
			// Creazione SMTPS account
			String pecEnable = PrefsPropsUtil.getString("pec.mail.session.mail.smtp.enable");
			String pecHost = PrefsPropsUtil.getString("pec.mail.session.mail.smtp.host");
			String pecPort = PrefsPropsUtil.getString("pec.mail.session.mail.smtp.port");
			String pecProtocol = PrefsPropsUtil.getString("pec.mail.session.mail.transport.protocol");
			String pecUsername = PrefsPropsUtil.getString("pec.mail.session.mail.smtp.user");
			String pecPassword = PrefsPropsUtil.getString("pec.mail.session.mail.smtp.password");
			SMTPAccount smtpAccount = null;

			boolean pecEnableBoolean = Boolean.parseBoolean(pecEnable);

			if (!pecEnableBoolean) {
				throw new CommunicationException("Invio PEC mail disabilitato.");
			}

			// test raggiungibilità SMTP PEC mail server
			if (!SMTPManager.testSmtpServer(pecHost, Integer.parseInt(pecPort), pecUsername, pecPassword)) {
				throw new CommunicationException("Errore di comunicazione con il server SMTP");
			}

			smtpAccount = (SMTPAccount) Account.getInstance(pecProtocol, Integer.parseInt(pecPort));
			smtpAccount.setHost(pecHost);
			smtpAccount.setUser(pecUsername);
			smtpAccount.setPassword(pecPassword);

			message.setSMTPAccount(smtpAccount);

			InternetAddress from = new InternetAddress(PrefsPropsUtil.getString("admin.pec.email.from.address"));

			InternetAddress[] toInternetAddresses = new InternetAddress[to.length];
			for (int i = 0; i < to.length; i++) {
				String string = to[i];
				InternetAddress toInternetAddress = new InternetAddress(string);
				toInternetAddresses[i] = toInternetAddress;
			}

			String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateFileName, templateModel);

			message.setFrom(from);
			message.setTo(toInternetAddresses);
			message.setSubject(subject);
			message.setBody(body);
			message.setHTMLFormat(true);

			if (attachment != null) {
				for (File file : attachment) {
					message.addAttachment(file);
				}
			}

			// Test raggiungibilità SMTP server - Liferay non solleva eccezioni al chiamante se è
			// sollevata un'eccezione MessagingException da javax.mail.Transport!
			if (!OsapulieUtils.checkLiferaySmtpConnection(pecHost, Integer.parseInt(pecPort), pecUsername, pecPassword, pecProtocol)) {
				throw new CommunicationException("Failed to connect to a valid mail server. Please make sure one is properly configured. ");
			}

			log.debug("sendMail :: sending PEC mail to : " + to);

			MailEngine.send(message);
		}
		catch (NumberFormatException e) {
			log.error("sendMail :: " + e.getMessage(), e);
			throw new CommunicationException(e);
		}
		catch (AddressException e) {
			log.error("sendMail :: " + e.getMessage(), e);
			throw new CommunicationException(e);
		}
		catch (VelocityException e) {
			log.error("sendMail :: " + e.getMessage(), e);
			throw new CommunicationException(e);
		}
		catch (MailEngineException e) {
			log.error("sendMail :: " + e.getMessage(), e);
			throw new CommunicationException(e);
		}
		catch (MessagingException e) {
			log.error("sendMail :: " + e.getMessage(), e);
			throw new CommunicationException(e);
		}
		catch (Exception e) {
			log.error("sendMail :: " + e.getMessage(), e);
			throw new CommunicationException(e);
		}

	}

	/**
	 * Invia la PEC mail per la dichiarazione/iscrizione e relativi allegati.
	 *
	 * @param tipologia
	 * @param templateFileName
	 * @param attachment
	 * @param templateModel
	 * @param to
	 * @throws CommunicationException
	 */
	public void sendConfirmationEmail(String tipologia, String templateFileName, UploadItem attachment, Map<String, String> templateModel, String[] to) throws CommunicationException {

		MailMessage message = new MailMessage();

		try {
			// Creazione SMTPS account
			String pecEnable = PrefsPropsUtil.getString("pec.mail.session.mail.smtp.enable");
			String pecHost = PrefsPropsUtil.getString("pec.mail.session.mail.smtp.host");
			String pecPort = PrefsPropsUtil.getString("pec.mail.session.mail.smtp.port");
			String pecProtocol = PrefsPropsUtil.getString("pec.mail.session.mail.transport.protocol");
			String pecUsername = PrefsPropsUtil.getString("pec.mail.session.mail.smtp.user");
			String pecPassword = PrefsPropsUtil.getString("pec.mail.session.mail.smtp.password");
			SMTPAccount smtpAccount = null;

			boolean pecEnableBoolean = Boolean.parseBoolean(pecEnable);

			if (!pecEnableBoolean) {
				throw new CommunicationException("Invio PEC mail disabilitato.");
			}

			// test raggiungibilità SMTP PEC mail server
			if (!SMTPManager.testSmtpServer(pecHost, Integer.parseInt(pecPort), pecUsername, pecPassword)) {
				throw new CommunicationException("Errore di comunicazione con il server SMTP");
			}

			smtpAccount = (SMTPAccount) Account.getInstance(pecProtocol, Integer.parseInt(pecPort));
			smtpAccount.setHost(pecHost);
			smtpAccount.setUser(pecUsername);
			smtpAccount.setPassword(pecPassword);

			message.setSMTPAccount(smtpAccount);

			InternetAddress from = new InternetAddress(PrefsPropsUtil.getString("admin.pec.email.from.address"));

			InternetAddress[] toInternetAddresses = new InternetAddress[to.length];
			for (int i = 0; i < to.length; i++) {
				String string = to[i];
				InternetAddress toInternetAddress = new InternetAddress(string);
				toInternetAddresses[i] = toInternetAddress;
			}

			String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateFileName, templateModel);

			message.setFrom(from);
			message.setTo(toInternetAddresses);
			message.setSubject(tipologia);
			message.setBody(body);
			message.setHTMLFormat(true);

			if (attachment != null) {
				List<MultipartFile> files = attachment.getMultipartFiles();
				MultipartFile generatedFile = attachment.getGeneratedFile();

				if (!generatedFile.isEmpty()) {
					File file = new File(generatedFile.getOriginalFilename());
					generatedFile.transferTo(file);
					message.addAttachment(file);
				}

				if ((files != null) && (files.size() > 0)) {
					for (MultipartFile multipartFile : files) {
						if (multipartFile.getSize() > 0) {
							File file = new File(multipartFile.getOriginalFilename());
							multipartFile.transferTo(file);
							message.addAttachment(file);
						}
					}
				}
			}

			// Test raggiungibilità SMTP server - Liferay non solleva eccezioni al chiamante se è
			// sollevata un'eccezione MessagingException da javax.mail.Transport!
			if (!OsapulieUtils.checkLiferaySmtpConnection(pecHost, Integer.parseInt(pecPort), pecUsername, pecPassword, pecProtocol)) {
				throw new CommunicationException("Failed to connect to a valid mail server. Please make sure one is properly configured. ");
			}

			log.debug("sendConfirmationEmail :: sending PEC mail to : " + to);

			MailEngine.send(message);

		}
		catch (NumberFormatException e) {
			log.error("sendConfirmationEmail :: " + e.getMessage(), e);
			throw new CommunicationException(e);
		}
		catch (AddressException e) {
			log.error("sendConfirmationEmail :: " + e.getMessage(), e);
			throw new CommunicationException(e);
		}
		catch (VelocityException e) {
			log.error("sendConfirmationEmail :: " + e.getMessage(), e);
			throw new CommunicationException(e);
		}
		catch (IllegalStateException e) {
			log.error("sendConfirmationEmail :: " + e.getMessage(), e);
			throw new CommunicationException(e);
		}
		catch (IOException e) {
			log.error("sendConfirmationEmail :: " + e.getMessage(), e);
			throw new CommunicationException(e);
		}
		catch (MessagingException e) {
			log.error("sendConfirmationEmail :: " + e.getMessage(), e);
			throw new CommunicationException(e);
		}
		catch (Exception e) {
			log.error("sendConfirmationEmail :: " + e.getMessage(), e);
			throw new CommunicationException(e);
		}

	}

	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}	

	public void sendMailInsOper(String subject, String templateFileName, Map<String, Object> templateModel, File[] attachment, String[] to) throws CommunicationException {

		log.debug("sendMail :: entering method");

		MailMessage message = new MailMessage();

		try {
			// Creazione SMTPS account
			String pecEnable = PrefsPropsUtil.getString("pec.mail.session.mail.smtp.enable");
			String pecHost = PrefsPropsUtil.getString("pec.mail.session.mail.smtp.host");
			String pecPort = PrefsPropsUtil.getString("pec.mail.session.mail.smtp.port");
			String pecProtocol = PrefsPropsUtil.getString("pec.mail.session.mail.transport.protocol");
			String pecUsername = PrefsPropsUtil.getString("pec.mail.session.mail.smtp.user");
			String pecPassword = PrefsPropsUtil.getString("pec.mail.session.mail.smtp.password");
			SMTPAccount smtpAccount = null;

			boolean pecEnableBoolean = Boolean.parseBoolean(pecEnable);

			if (!pecEnableBoolean) {
				throw new CommunicationException("Invio PEC mail disabilitato.");
			}

			// test raggiungibilità SMTP PEC mail server
			if (!SMTPManager.testSmtpServer(pecHost, Integer.parseInt(pecPort), pecUsername, pecPassword)) {
				throw new CommunicationException("Errore di comunicazione con il server SMTP");
			}

			smtpAccount = (SMTPAccount) Account.getInstance(pecProtocol, Integer.parseInt(pecPort));
			smtpAccount.setHost(pecHost);
			smtpAccount.setUser(pecUsername);
			smtpAccount.setPassword(pecPassword);

			message.setSMTPAccount(smtpAccount);

			InternetAddress from = new InternetAddress(PrefsPropsUtil.getString("admin.pec.email.from.address"));

			InternetAddress[] toInternetAddresses = new InternetAddress[to.length];
			for (int i = 0; i < to.length; i++) {
				String string = to[i];
				InternetAddress toInternetAddress = new InternetAddress(string);
				toInternetAddresses[i] = toInternetAddress;
			}

			String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateFileName, templateModel);

			message.setFrom(from);
			message.setTo(toInternetAddresses);
			message.setSubject(subject);
			message.setBody(body);
			message.setHTMLFormat(true);

			if (attachment != null) {
				for (File file : attachment) {
					message.addAttachment(file);
				}
			}

			// Test raggiungibilità SMTP server - Liferay non solleva eccezioni al chiamante se è
			// sollevata un'eccezione MessagingException da javax.mail.Transport!
			if (!OsapulieUtils.checkLiferaySmtpConnection(pecHost, Integer.parseInt(pecPort), pecUsername, pecPassword, pecProtocol)) {
				throw new CommunicationException("Failed to connect to a valid mail server. Please make sure one is properly configured. ");
			}

			log.debug("sendMail :: sending PEC mail to : " + to);

			MailEngine.send(message);
		}
		catch (NumberFormatException e) {
			log.error("sendMail :: " + e.getMessage(), e);
			throw new CommunicationException(e);
		}
		catch (AddressException e) {
			log.error("sendMail :: " + e.getMessage(), e);
			throw new CommunicationException(e);
		}
		catch (VelocityException e) {
			log.error("sendMail :: " + e.getMessage(), e);
			throw new CommunicationException(e);
		}
		catch (MailEngineException e) {
			log.error("sendMail :: " + e.getMessage(), e);
			throw new CommunicationException(e);
		}
		catch (MessagingException e) {
			log.error("sendMail :: " + e.getMessage(), e);
			throw new CommunicationException(e);
		}
		catch (Exception e) {
			log.error("sendMail :: " + e.getMessage(), e);
			throw new CommunicationException(e);
		}
	}

	public void sendMailInsAziendaNew(String subject, String templateFileName, Map<String, Object> templateModel, File[] attachment, String[] to)throws CommunicationException
	{
		
		log.debug("sendMail :: entering method");

		MailMessage message = new MailMessage();

		try {
			// Creazione SMTPS account
			String pecEnable = PrefsPropsUtil.getString("pec.mail.session.mail.smtp.enable");
			String pecHost = PrefsPropsUtil.getString("pec.mail.session.mail.smtp.host");
			String pecPort = PrefsPropsUtil.getString("pec.mail.session.mail.smtp.port");
			String pecProtocol = PrefsPropsUtil.getString("pec.mail.session.mail.transport.protocol");
			String pecUsername = PrefsPropsUtil.getString("pec.mail.session.mail.smtp.user");
			String pecPassword = PrefsPropsUtil.getString("pec.mail.session.mail.smtp.password");
			SMTPAccount smtpAccount = null;

			boolean pecEnableBoolean = Boolean.parseBoolean(pecEnable);

			if (!pecEnableBoolean) {
				throw new CommunicationException("Invio PEC mail disabilitato.");
			}

			// test raggiungibilità SMTP PEC mail server
			if (!SMTPManager.testSmtpServer(pecHost, Integer.parseInt(pecPort), pecUsername, pecPassword)) {
				throw new CommunicationException("Errore di comunicazione con il server SMTP");
			}

			smtpAccount = (SMTPAccount) Account.getInstance(pecProtocol, Integer.parseInt(pecPort));
			smtpAccount.setHost(pecHost);
			smtpAccount.setUser(pecUsername);
			smtpAccount.setPassword(pecPassword);

			message.setSMTPAccount(smtpAccount);

			InternetAddress from = new InternetAddress(PrefsPropsUtil.getString("admin.pec.email.from.address"));

			InternetAddress[] toInternetAddresses = new InternetAddress[to.length];
			for (int i = 0; i < to.length; i++) {
				String string = to[i];
				InternetAddress toInternetAddress = new InternetAddress(string);
				toInternetAddresses[i] = toInternetAddress;
			}

			String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateFileName, templateModel);

			message.setFrom(from);
			message.setTo(toInternetAddresses);
			message.setSubject(subject);
			message.setBody(body);
			message.setHTMLFormat(true);

			if (attachment != null) {
				for (File file : attachment) {
					message.addAttachment(file);
				}
			}

			// Test raggiungibilità SMTP server - Liferay non solleva eccezioni al chiamante se è
			// sollevata un'eccezione MessagingException da javax.mail.Transport!
			if (!OsapulieUtils.checkLiferaySmtpConnection(pecHost, Integer.parseInt(pecPort), pecUsername, pecPassword, pecProtocol)) {
				throw new CommunicationException("Failed to connect to a valid mail server. Please make sure one is properly configured. ");
			}

			log.debug("sendMail :: sending PEC mail to : " + to);

			MailEngine.send(message);
		}
		catch (NumberFormatException e) {
			log.error("sendMail :: " + e.getMessage(), e);
			throw new CommunicationException(e);
		}
		catch (AddressException e) {
			log.error("sendMail :: " + e.getMessage(), e);
			throw new CommunicationException(e);
		}
		catch (VelocityException e) {
			log.error("sendMail :: " + e.getMessage(), e);
			throw new CommunicationException(e);
		}
		catch (MailEngineException e) {
			log.error("sendMail :: " + e.getMessage(), e);
			throw new CommunicationException(e);
		}
		catch (MessagingException e) {
			log.error("sendMail :: " + e.getMessage(), e);
			throw new CommunicationException(e);
		}
		catch (Exception e) {
			log.error("sendMail :: " + e.getMessage(), e);
			throw new CommunicationException(e);
		}
		
		
	}
}
