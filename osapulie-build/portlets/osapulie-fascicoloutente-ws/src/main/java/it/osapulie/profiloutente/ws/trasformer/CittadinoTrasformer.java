package it.osapulie.profiloutente.ws.trasformer;

import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.profiloutente.ws.dto.CittadinoDto;

public class CittadinoTrasformer extends MainTrasformer<CittadinoDto, ProfiloUtenteCittadino> {

	public CittadinoTrasformer(Class<CittadinoDto> dtoClass, Class<ProfiloUtenteCittadino> entityClass) {
		super(dtoClass, entityClass);
	}
}
