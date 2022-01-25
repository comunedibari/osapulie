
package it.osapulie.profiloutente.ws.dto;


public class EsitoDto implements java.io.Serializable
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int getValore() {
		return valore;
	}
	public void setValore(int valore) {
		this.valore = valore;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	protected int valore;
    protected String descrizione;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descrizione == null) ? 0 : descrizione.hashCode());
		result = prime * result + valore;
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
		EsitoDto other = (EsitoDto) obj;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
			return false;
		if (valore != other.valore)
			return false;
		return true;
	}

}
