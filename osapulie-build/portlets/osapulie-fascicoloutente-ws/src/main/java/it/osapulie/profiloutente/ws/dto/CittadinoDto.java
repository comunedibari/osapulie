
package it.osapulie.profiloutente.ws.dto;



public class CittadinoDto implements java.io.Serializable
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected long id;
    protected String cognome;
    protected String nome;
    protected String codiceFiscale;
    protected String mail;
    protected String mailPec;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getMailPec() {
		return mailPec;
	}
	public void setMailPec(String mailPec) {
		this.mailPec = mailPec;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codiceFiscale == null) ? 0 : codiceFiscale.hashCode());
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + ((mailPec == null) ? 0 : mailPec.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		CittadinoDto other = (CittadinoDto) obj;
		if (codiceFiscale == null) {
			if (other.codiceFiscale != null)
				return false;
		} else if (!codiceFiscale.equals(other.codiceFiscale))
			return false;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (id != other.id)
			return false;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (mailPec == null) {
			if (other.mailPec != null)
				return false;
		} else if (!mailPec.equals(other.mailPec))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}


}
