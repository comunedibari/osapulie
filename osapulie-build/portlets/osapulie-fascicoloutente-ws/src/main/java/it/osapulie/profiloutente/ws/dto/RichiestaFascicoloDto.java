
package it.osapulie.profiloutente.ws.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="FascicoloInsertDto")
@XmlAccessorType(XmlAccessType.FIELD)
public class RichiestaFascicoloDto implements java.io.Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlElement(required=false)
	protected String codiceFiscale;
	
	@XmlElement(required=false)
	protected String partitaIva;
	
	@XmlElement(required=true)
	  private String password;
	
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public String getPartitaIva() {
		return partitaIva;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codiceFiscale == null) ? 0 : codiceFiscale.hashCode());
		result = prime * result
				+ ((partitaIva == null) ? 0 : partitaIva.hashCode());
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
		RichiestaFascicoloDto other = (RichiestaFascicoloDto) obj;
		if (codiceFiscale == null) {
			if (other.codiceFiscale != null)
				return false;
		} else if (!codiceFiscale.equals(other.codiceFiscale))
			return false;
		if (partitaIva == null) {
			if (other.partitaIva != null)
				return false;
		} else if (!partitaIva.equals(other.partitaIva))
			return false;
		return true;
	}
	
	
}
