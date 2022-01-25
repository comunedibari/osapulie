
package it.osapulie.profiloutente.ws.dto;



public class AziendaDto implements java.io.Serializable
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected long id;
    protected String partitaIva;
    protected String ragioneSociale;
    protected String mailPec;
    protected String pec;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPartitaIva() {
		return partitaIva;
	}
	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}
	public String getRagioneSociale() {
		return ragioneSociale;
	}
	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}
	public String getMailPec() {
		return mailPec;
	}
	public void setMailPec(String mailPec) {
		this.mailPec = mailPec;
	}
	public String getPec() {
		return pec;
	}
	public void setPec(String pec) {
		this.pec = pec;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((mailPec == null) ? 0 : mailPec.hashCode());
		result = prime * result
				+ ((partitaIva == null) ? 0 : partitaIva.hashCode());
		result = prime * result + ((pec == null) ? 0 : pec.hashCode());
		result = prime * result
				+ ((ragioneSociale == null) ? 0 : ragioneSociale.hashCode());
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
		AziendaDto other = (AziendaDto) obj;
		if (id != other.id)
			return false;
		if (mailPec == null) {
			if (other.mailPec != null)
				return false;
		} else if (!mailPec.equals(other.mailPec))
			return false;
		if (partitaIva == null) {
			if (other.partitaIva != null)
				return false;
		} else if (!partitaIva.equals(other.partitaIva))
			return false;
		if (pec == null) {
			if (other.pec != null)
				return false;
		} else if (!pec.equals(other.pec))
			return false;
		if (ragioneSociale == null) {
			if (other.ragioneSociale != null)
				return false;
		} else if (!ragioneSociale.equals(other.ragioneSociale))
			return false;
		return true;
	}


}
