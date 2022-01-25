/************************************************************************************
 * Copyright (c) 2011, 2016 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pdds.client.email;

import java.io.Serializable;

/**
 * @author Gianluca Pindinelli
 *
 */
public class EmailAccount implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 6759719733505691905L;
	/**
	 *
	 */
	private String auth;
	private Boolean starttls;
	private Boolean starttlsRequired;

	private String host;
	private int port;
	private String protocol;
	private String username;
	private String password;

	/**
	 * @return the auth
	 */
	public String getAuth() {
		return auth;
	}

	/**
	 * @param auth the auth to set
	 */
	public void setAuth(String auth) {
		this.auth = auth;
	}

	/**
	 * @return the starttls
	 */
	public Boolean getStarttls() {
		return starttls;
	}

	/**
	 * @param starttls the starttls to set
	 */
	public void setStarttls(Boolean starttls) {
		this.starttls = starttls;
	}

	/**
	 * @return the starttlsRequired
	 */
	public Boolean getStarttlsRequired() {
		return starttlsRequired;
	}

	/**
	 * @param starttlsRequired the starttlsRequired to set
	 */
	public void setStarttlsRequired(Boolean starttlsRequired) {
		this.starttlsRequired = starttlsRequired;
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

}
