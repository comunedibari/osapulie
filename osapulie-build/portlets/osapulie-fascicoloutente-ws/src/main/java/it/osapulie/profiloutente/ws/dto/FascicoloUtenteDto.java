
package it.osapulie.profiloutente.ws.dto;

import java.util.List;



public class FascicoloUtenteDto implements java.io.Serializable
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected List<RichiestaDto> richiesteDto;
    protected CittadinoDto cittadinoDto;
    protected AziendaDto aziendaDto;
	public List<RichiestaDto> getRichiesteDto() {
		return richiesteDto;
	}
	public void setRichiesteDto(List<RichiestaDto> richiesteDto) {
		this.richiesteDto = richiesteDto;
	}
	public CittadinoDto getCittadinoDto() {
		return cittadinoDto;
	}
	public void setCittadinoDto(CittadinoDto cittadinoDto) {
		this.cittadinoDto = cittadinoDto;
	}
	public AziendaDto getAziendaDto() {
		return aziendaDto;
	}
	public void setAziendaDto(AziendaDto aziendaDto) {
		this.aziendaDto = aziendaDto;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((aziendaDto == null) ? 0 : aziendaDto.hashCode());
		result = prime * result
				+ ((cittadinoDto == null) ? 0 : cittadinoDto.hashCode());
		result = prime * result
				+ ((richiesteDto == null) ? 0 : richiesteDto.hashCode());
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
		FascicoloUtenteDto other = (FascicoloUtenteDto) obj;
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
		if (richiesteDto == null) {
			if (other.richiesteDto != null)
				return false;
		} else if (!richiesteDto.equals(other.richiesteDto))
			return false;
		return true;
	}


}
