package it.eng.tz.area.vasta.protocollo.spring.ldap.models;

import java.io.Serializable;

public class LdapUserModel implements Serializable {

	private static final long serialVersionUID = -3439972535234922712L;
	public static final String ATTRIBUTO_LDAP_NOME = "givenName";
	public static final String ATTRIBUTO_LDAP_COGNOME = "sn";
	public static final String ATTRIBUTO_LDAP_USERNAME = "cn";
	public static final String ATTRIBUTO_LDAP_CN = "cn";
	public static final String ATTRIBUTO_LDAP_MAIL = "mail";
	public static final String ATTRIBUTO_LDAP_PASSWORD = "userPassword";
	private String username;
	private String cn;
	private String cognome;
	private String nome;
	private String mail;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCn() {
		return cn;
	}
	public void setCn(String cn) {
		this.cn = cn;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cn == null) ? 0 : cn.hashCode());
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LdapUserModel other = (LdapUserModel) obj;
		if (cn == null) {
			if (other.cn != null)
				return false;
		} else if (!cn.equals(other.cn))
			return false;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "LdapUserModel [username=" + username + ", cn=" + cn + ", cognome=" + cognome + ", nome=" + nome
				+ ", mail=" + mail + ", password= CRYPTED]";
	}
}