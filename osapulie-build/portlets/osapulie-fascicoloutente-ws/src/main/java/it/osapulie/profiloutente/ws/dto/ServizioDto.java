
package it.osapulie.profiloutente.ws.dto;


public class ServizioDto implements java.io.Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codiceServizio == null) ? 0 : codiceServizio.hashCode());
		result = prime * result
				+ ((descrizione == null) ? 0 : descrizione.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((nomeServizio == null) ? 0 : nomeServizio.hashCode());
		result = prime * result + ((uri == null) ? 0 : uri.hashCode());
		result = prime * result
				+ ((uriScheda == null) ? 0 : uriScheda.hashCode());
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
		ServizioDto other = (ServizioDto) obj;
		if (codiceServizio == null) {
			if (other.codiceServizio != null)
				return false;
		} else if (!codiceServizio.equals(other.codiceServizio))
			return false;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
			return false;
		if (id != other.id)
			return false;
		if (nomeServizio == null) {
			if (other.nomeServizio != null)
				return false;
		} else if (!nomeServizio.equals(other.nomeServizio))
			return false;
		if (uri == null) {
			if (other.uri != null)
				return false;
		} else if (!uri.equals(other.uri))
			return false;
		if (uriScheda == null) {
			if (other.uriScheda != null)
				return false;
		} else if (!uriScheda.equals(other.uriScheda))
			return false;
		return true;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNomeServizio() {
		return nomeServizio;
	}
	public void setNomeServizio(String nomeServizio) {
		this.nomeServizio = nomeServizio;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getCodiceServizio() {
		return codiceServizio;
	}
	public void setCodiceServizio(String codiceServizio) {
		this.codiceServizio = codiceServizio;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getUriScheda() {
		return uriScheda;
	}
	public void setUriScheda(String uriScheda) {
		this.uriScheda = uriScheda;
	}
	protected long id;
    protected String nomeServizio;
    protected String descrizione;
    protected String codiceServizio;
    protected String uri;
    protected String uriScheda;
}
