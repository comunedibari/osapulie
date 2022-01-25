/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 * 
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * Parametri del servizio di timbro digitale.
 * 
 * @author Maria Michela Birtolo
 */

@Entity
@Table(name = "tb_timbro_config")
public class TimbroConfig extends AbstractPersistable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9215713598139212678L;

	/**
	 * Parametri del servizio di timbro digitale.
	 */
	@Column(name = "username", nullable = true, length = 128)
	private String username;

	@Column(name = "password", nullable = true, length = 128)
	private String password;

	@Column(name = "pin", nullable = true, length = 128)
	private String pin;

	@Column(name = "service_id", length = 5)
	private int serviceId;

	@Column(name = "domain", nullable = true, length = 128)
	private String domain;

	@Column(name = "server_host", nullable = true, length = 128)
	private String serverHost;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getServerHost() {
		return serverHost;
	}

	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
