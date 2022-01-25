/************************************************************************************
 * Copyright (c) 2011, 2016 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pdds.client.email;

import java.io.Serializable;
import java.util.List;

/**
 * @author Gianluca Pindinelli
 *
 */
public class Message implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String text;
	private String subject;
	private Contact from;
	private List<Contact> to;
	private List<Contact> cc;
	private List<Contact> bcc;
	private List<Attachment> attachments;
	private boolean html;
	private boolean important;
	private EmailAccount account;

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the from
	 */
	public Contact getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(Contact from) {
		this.from = from;
	}

	/**
	 * @return the to
	 */
	public List<Contact> getTo() {
		return to;
	}

	/**
	 * @param to the to to set
	 */
	public void setTo(List<Contact> contact) {
		this.to = contact;
	}

	/**
	 * @return the important
	 */
	public boolean isImportant() {
		return important;
	}

	/**
	 * @param important the important to set
	 */
	public void setImportant(boolean important) {
		this.important = important;
	}

	/**
	 * @return the attachments
	 */
	public List<Attachment> getAttachments() {
		return attachments;
	}

	/**
	 * @param attachments the attachments to set
	 */
	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	/**
	 * @return the cc
	 */
	public List<Contact> getCc() {
		return cc;
	}

	/**
	 * @param cc the cc to set
	 */
	public void setCc(List<Contact> cc) {
		this.cc = cc;
	}

	/**
	 * @return the bcc
	 */
	public List<Contact> getBcc() {
		return bcc;
	}

	/**
	 * @param bcc the bcc to set
	 */
	public void setBcc(List<Contact> bcc) {
		this.bcc = bcc;
	}

	/**
	 * @return the html
	 */
	public boolean isHtml() {
		return html;
	}

	/**
	 * @param html the html to set
	 */
	public void setHtml(boolean html) {
		this.html = html;
	}

	/**
	 * @return the account
	 */
	public EmailAccount getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(EmailAccount account) {
		this.account = account;
	}

}
