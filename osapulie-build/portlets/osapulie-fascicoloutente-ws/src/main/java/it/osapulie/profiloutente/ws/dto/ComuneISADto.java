
package it.osapulie.profiloutente.ws.dto;


public class ComuneISADto implements java.io.Serializable
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected long id;
    protected String codiceIstat;
    protected String nome;
    protected String descrizione;
    protected String cap;
    protected String pec;
    protected String uriServizioGateway;
    protected String uriProtocollo;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCodiceIstat() {
		return codiceIstat;
	}
	public void setCodiceIstat(String codiceIstat) {
		this.codiceIstat = codiceIstat;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getCap() {
		return cap;
	}
	public void setCap(String cap) {
		this.cap = cap;
	}
	public String getPec() {
		return pec;
	}
	public void setPec(String pec) {
		this.pec = pec;
	}
	public String getUriServizioGateway() {
		return uriServizioGateway;
	}
	public void setUriServizioGateway(String uriServizioGateway) {
		this.uriServizioGateway = uriServizioGateway;
	}
	public String getUriProtocollo() {
		return uriProtocollo;
	}
	public void setUriProtocollo(String uriProtocollo) {
		this.uriProtocollo = uriProtocollo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cap == null) ? 0 : cap.hashCode());
		result = prime * result
				+ ((codiceIstat == null) ? 0 : codiceIstat.hashCode());
		result = prime * result
				+ ((descrizione == null) ? 0 : descrizione.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((pec == null) ? 0 : pec.hashCode());
		result = prime * result
				+ ((uriProtocollo == null) ? 0 : uriProtocollo.hashCode());
		result = prime
				* result
				+ ((uriServizioGateway == null) ? 0 : uriServizioGateway
						.hashCode());
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
		ComuneISADto other = (ComuneISADto) obj;
		if (cap == null) {
			if (other.cap != null)
				return false;
		} else if (!cap.equals(other.cap))
			return false;
		if (codiceIstat == null) {
			if (other.codiceIstat != null)
				return false;
		} else if (!codiceIstat.equals(other.codiceIstat))
			return false;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
			return false;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (pec == null) {
			if (other.pec != null)
				return false;
		} else if (!pec.equals(other.pec))
			return false;
		if (uriProtocollo == null) {
			if (other.uriProtocollo != null)
				return false;
		} else if (!uriProtocollo.equals(other.uriProtocollo))
			return false;
		if (uriServizioGateway == null) {
			if (other.uriServizioGateway != null)
				return false;
		} else if (!uriServizioGateway.equals(other.uriServizioGateway))
			return false;
		return true;
	}

}
