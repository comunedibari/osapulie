
package it.osapulie.profiloutente.ws.dto;

import javax.xml.datatype.XMLGregorianCalendar;

public class RichiestaDto implements java.io.Serializable
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected long id;
	protected ComuneISADto comuneISADto;
    protected String nomeServizio;
    protected String numeroProtocollo;
    protected XMLGregorianCalendar richiestaDate;
    protected XMLGregorianCalendar rispostaDate;
    protected DelegatoDto delegatoDto;
    protected ServizioDto servizioDto;
    protected boolean ricercabileDaComune;
    protected String infoAggiuntive;
    
	public ComuneISADto getComuneISADto() {
		return comuneISADto;
	}
	public void setComuneISADto(ComuneISADto comuneISADto) {
		this.comuneISADto = comuneISADto;
	}
	public String getNomeServizio() {
		return nomeServizio;
	}
	public void setNomeServizio(String nomeServizio) {
		this.nomeServizio = nomeServizio;
	}
	public String getNumeroProtocollo() {
		return numeroProtocollo;
	}
	public void setNumeroProtocollo(String numeroProtocollo) {
		this.numeroProtocollo = numeroProtocollo;
	}
	public DelegatoDto getDelegatoDto() {
		return delegatoDto;
	}
	public void setDelegatoDto(DelegatoDto delegatoDto) {
		this.delegatoDto = delegatoDto;
	}
	public ServizioDto getServizioDto() {
		return servizioDto;
	}
	public void setServizioDto(ServizioDto servizioDto) {
		this.servizioDto = servizioDto;
	}
	public boolean isRicercabileDaComune() {
		return ricercabileDaComune;
	}
	public XMLGregorianCalendar getRichiestaDate() {
		return richiestaDate;
	}
	public void setRichiestaDate(XMLGregorianCalendar richiestaDate) {
		this.richiestaDate = richiestaDate;
	}
	public XMLGregorianCalendar getRispostaDate() {
		return rispostaDate;
	}
	public void setRispostaDate(XMLGregorianCalendar rispostaDate) {
		this.rispostaDate = rispostaDate;
	}
	public void setRicercabileDaComune(boolean ricercabileDaComune) {
		this.ricercabileDaComune = ricercabileDaComune;
	}
	public String getInfoAggiuntive() {
		return infoAggiuntive;
	}
	public void setInfoAggiuntive(String infoAggiuntive) {
		this.infoAggiuntive = infoAggiuntive;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((comuneISADto == null) ? 0 : comuneISADto.hashCode());
		result = prime * result
				+ ((richiestaDate == null) ? 0 : richiestaDate.hashCode());
		result = prime * result
				+ ((rispostaDate == null) ? 0 : rispostaDate.hashCode());
		result = prime * result
				+ ((delegatoDto == null) ? 0 : delegatoDto.hashCode());
		result = prime * result
				+ ((nomeServizio == null) ? 0 : nomeServizio.hashCode());
		result = prime
				* result
				+ ((numeroProtocollo == null) ? 0 : numeroProtocollo.hashCode());
		result = prime * result + (ricercabileDaComune ? 1231 : 1237);
		result = prime * result
				+ ((servizioDto == null) ? 0 : servizioDto.hashCode());
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
		RichiestaDto other = (RichiestaDto) obj;
		if (comuneISADto == null) {
			if (other.comuneISADto != null)
				return false;
		} else if (!comuneISADto.equals(other.comuneISADto))
			return false;
		if (richiestaDate == null) {
			if (other.richiestaDate != null)
				return false;
		} else if (!richiestaDate.equals(other.richiestaDate))
			return false;
		if (rispostaDate == null) {
			if (other.rispostaDate != null)
				return false;
		} else if (!rispostaDate.equals(other.rispostaDate))
			return false;
		if (delegatoDto == null) {
			if (other.delegatoDto != null)
				return false;
		} else if (!delegatoDto.equals(other.delegatoDto))
			return false;
		if (nomeServizio == null) {
			if (other.nomeServizio != null)
				return false;
		} else if (!nomeServizio.equals(other.nomeServizio))
			return false;
		if (numeroProtocollo == null) {
			if (other.numeroProtocollo != null)
				return false;
		} else if (!numeroProtocollo.equals(other.numeroProtocollo))
			return false;
		if (ricercabileDaComune != other.ricercabileDaComune)
			return false;
		if (servizioDto == null) {
			if (other.servizioDto != null)
				return false;
		} else if (!servizioDto.equals(other.servizioDto))
			return false;
		return true;
	}

}
