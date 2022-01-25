
package it.osapulie.profiloutente.ws.dto;

public class DelegatoDto implements java.io.Serializable
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
				+ ((aziendaDto == null) ? 0 : aziendaDto.hashCode());
		result = prime * result
				+ ((cittadinoDto == null) ? 0 : cittadinoDto.hashCode());
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
		DelegatoDto other = (DelegatoDto) obj;
		if (aziendaDto == null) {
			if (other.aziendaDto != null)
				return false;
		} else if (!aziendaDto.equals(other.aziendaDto))
			return false;
		if (cittadinoDto == null) {
			if (other.cittadinoDto != null)
				return false;
		} else if (!cittadinoDto.equals(other.cittadinoDto))
			return false;
		return true;
	}
	public AziendaDto getAziendaDto() {
		return aziendaDto;
	}
	public void setAziendaDto(AziendaDto aziendaDto) {
		this.aziendaDto = aziendaDto;
	}
	public CittadinoDto getCittadinoDto() {
		return cittadinoDto;
	}
	public void setCittadinoDto(CittadinoDto cittadinoDto) {
		this.cittadinoDto = cittadinoDto;
	}
	protected AziendaDto aziendaDto;
    protected CittadinoDto cittadinoDto;


}
