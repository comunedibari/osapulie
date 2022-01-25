package it.osapulie.profiloutente.ws.trasformer;

import it.osapulie.domain.ComuneISA;
import it.osapulie.profiloutente.ws.dto.ComuneISADto;

public class ComuneISATrasformer extends MainTrasformer<ComuneISADto, ComuneISA> {

	public ComuneISATrasformer(Class<ComuneISADto> dtoClass, Class<ComuneISA> entityClass) {
		super(dtoClass, entityClass);
	}
}
