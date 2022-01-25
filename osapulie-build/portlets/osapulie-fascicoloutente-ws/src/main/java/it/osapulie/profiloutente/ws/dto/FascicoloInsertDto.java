package it.osapulie.profiloutente.ws.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="FascicoloInsertDto")
@XmlAccessorType(XmlAccessType.FIELD)
public class FascicoloInsertDto implements Serializable{
	  public String getDescrizioneServizio() {
		return descrizioneServizio;
	}
	public void setDescrizioneServizio(String descrizioneServizio) {
		this.descrizioneServizio = descrizioneServizio;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -4049236075974927843L;
	  @XmlElement(required=true)
	  private String password;
	  
	  @XmlElement(required=false)
	  private String descrizioneServizio;
	  
	  @XmlElement(required=false)
	  private String codiceIstatComune;
	  
	  @XmlElement(required=false)
	  private String uriServizioGateway;
	  
	  @XmlElement(required=false)
	  private String codiceFiscaleServizio;
	  
	  @XmlElement(required=false)
	  private String partitaIvaServizio;
	  
//	  private Long idDelega;
	  
	  @XmlElement(required=true)
	  private String codiceServizio;
	  
	  @XmlElement(required=false)
	  private String checksum;
	  
	  @XmlElement(required=false)
	  private Boolean ricercabileDaComune;
	  
	  @XmlElement(required=false)
	  private String numeroProtocollo;
	  
	  @XmlElement(required=false)
	  private String infoAggiuntive;
	  
	public String getCodiceIstatComune() {
		return codiceIstatComune;
	}
	public void setCodiceIstatComune(String codiceIstatComune) {
		this.codiceIstatComune = codiceIstatComune;
	}
	public String getUriServizioGateway() {
		return uriServizioGateway;
	}
	public void setUriServizioGateway(String uriServizioGateway) {
		this.uriServizioGateway = uriServizioGateway;
	}
	public String getCodiceFiscaleServizio() {
		return codiceFiscaleServizio;
	}
	public void setCodiceFiscaleServizio(String codiceFiscaleServizio) {
		this.codiceFiscaleServizio = codiceFiscaleServizio;
	}
	public String getPartitaIvaServizio() {
		return partitaIvaServizio;
	}
	public void setPartitaIvaServizio(String partitaIvaServizio) {
		this.partitaIvaServizio = partitaIvaServizio;
	}
//	public Long getIdDelega() {
//		return idDelega;
//	}
//	public void setIdDelega(Long idDelega) {
//		this.idDelega = idDelega;
//	}
	public String getCodiceServizio() {
		return codiceServizio;
	}
	public void setCodiceServizio(String codiceServizio) {
		this.codiceServizio = codiceServizio;
	}
	public String getChecksum() {
		return checksum;
	}
	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}
	public Boolean getRicercabileDaComune() {
		return ricercabileDaComune;
	}
	public void setRicercabileDaComune(Boolean ricercabileDaComune) {
		this.ricercabileDaComune = ricercabileDaComune;
	}
	public String getNumeroProtocollo() {
		return numeroProtocollo;
	}
	public void setNumeroProtocollo(String numeroProtocollo) {
		this.numeroProtocollo = numeroProtocollo;
	}
	public String getInfoAggiuntive() {
		return infoAggiuntive;
	}
	public void setInfoAggiuntive(String infoAggiuntive) {
		this.infoAggiuntive = infoAggiuntive;
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
		result = prime * result
				+ ((checksum == null) ? 0 : checksum.hashCode());
		result = prime
				* result
				+ ((codiceFiscaleServizio == null) ? 0 : codiceFiscaleServizio
						.hashCode());
		result = prime
				* result
				+ ((codiceIstatComune == null) ? 0 : codiceIstatComune
						.hashCode());
		result = prime * result
				+ ((codiceServizio == null) ? 0 : codiceServizio.hashCode());
//		result = prime * result
//				+ ((idDelega == null) ? 0 : idDelega.hashCode());
		result = prime * result
				+ ((infoAggiuntive == null) ? 0 : infoAggiuntive.hashCode());
		result = prime
				* result
				+ ((numeroProtocollo == null) ? 0 : numeroProtocollo.hashCode());
		result = prime
				* result
				+ ((partitaIvaServizio == null) ? 0 : partitaIvaServizio
						.hashCode());
		result = prime
				* result
				+ ((ricercabileDaComune == null) ? 0 : ricercabileDaComune
						.hashCode());
		result = prime
				* result
				+ ((uriServizioGateway == null) ? 0 : uriServizioGateway
						.hashCode());
		result = prime
				* result
				+ ((descrizioneServizio == null) ? 0 : descrizioneServizio
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
		FascicoloInsertDto other = (FascicoloInsertDto) obj;
		if (checksum == null) {
			if (other.checksum != null)
				return false;
		} else if (!checksum.equals(other.checksum))
			return false;
		if (codiceFiscaleServizio == null) {
			if (other.codiceFiscaleServizio != null)
				return false;
		} else if (!codiceFiscaleServizio.equals(other.codiceFiscaleServizio))
			return false;
		if (codiceIstatComune == null) {
			if (other.codiceIstatComune != null)
				return false;
		} else if (!codiceIstatComune.equals(other.codiceIstatComune))
			return false;
		if (codiceServizio == null) {
			if (other.codiceServizio != null)
				return false;
		} else if (!codiceServizio.equals(other.codiceServizio))
			return false;
		
		if (descrizioneServizio == null) {
			if (other.descrizioneServizio != null)
				return false;
		} else if (!descrizioneServizio.equals(other.descrizioneServizio))
			return false;
		
//		if (idDelega == null) {
//			if (other.idDelega != null)
//				return false;
//		} else if (!idDelega.equals(other.idDelega))
//			return false;
		if (infoAggiuntive == null) {
			if (other.infoAggiuntive != null)
				return false;
		} else if (!infoAggiuntive.equals(other.infoAggiuntive))
			return false;
		if (numeroProtocollo == null) {
			if (other.numeroProtocollo != null)
				return false;
		} else if (!numeroProtocollo.equals(other.numeroProtocollo))
			return false;
		if (partitaIvaServizio == null) {
			if (other.partitaIvaServizio != null)
				return false;
		} else if (!partitaIvaServizio.equals(other.partitaIvaServizio))
			return false;
		if (ricercabileDaComune == null) {
			if (other.ricercabileDaComune != null)
				return false;
		} else if (!ricercabileDaComune.equals(other.ricercabileDaComune))
			return false;
		if (uriServizioGateway == null) {
			if (other.uriServizioGateway != null)
				return false;
		} else if (!uriServizioGateway.equals(other.uriServizioGateway))
			return false;
		return true;
	}

	  
}
