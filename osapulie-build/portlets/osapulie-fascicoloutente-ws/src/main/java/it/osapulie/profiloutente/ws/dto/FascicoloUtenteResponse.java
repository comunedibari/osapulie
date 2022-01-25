package it.osapulie.profiloutente.ws.dto;

public class FascicoloUtenteResponse implements java.io.Serializable
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected EsitoDto esitoDto;
    protected RisultatoDto risultatoDto;
	public EsitoDto getEsitoDto() {
		return esitoDto;
	}
	public void setEsitoDto(EsitoDto esitoDto) {
		this.esitoDto = esitoDto;
	}
	public RisultatoDto getRisultatoDto() {
		return risultatoDto;
	}
	public void setRisultatoDto(RisultatoDto risultatoDto) {
		this.risultatoDto = risultatoDto;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((esitoDto == null) ? 0 : esitoDto.hashCode());
		result = prime * result
				+ ((risultatoDto == null) ? 0 : risultatoDto.hashCode());
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
		FascicoloUtenteResponse other = (FascicoloUtenteResponse) obj;
		if (esitoDto == null) {
			if (other.esitoDto != null)
				return false;
		} else if (!esitoDto.equals(other.esitoDto))
			return false;
		if (risultatoDto == null) {
			if (other.risultatoDto != null)
				return false;
		} else if (!risultatoDto.equals(other.risultatoDto))
			return false;
		return true;
	}
}
