
package it.osapulie.profiloutente.ws.dto;


public class RisultatoDto implements java.io.Serializable
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FascicoloUtenteDto getFascicoloUtenteDto() {
		return fascicoloUtenteDto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((fascicoloUtenteDto == null) ? 0 : fascicoloUtenteDto
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
		RisultatoDto other = (RisultatoDto) obj;
		if (fascicoloUtenteDto == null) {
			if (other.fascicoloUtenteDto != null)
				return false;
		} else if (!fascicoloUtenteDto.equals(other.fascicoloUtenteDto))
			return false;
		return true;
	}

	public void setFascicoloUtenteDto(FascicoloUtenteDto fascicoloUtenteDto) {
		this.fascicoloUtenteDto = fascicoloUtenteDto;
	}

	protected FascicoloUtenteDto fascicoloUtenteDto;
}
