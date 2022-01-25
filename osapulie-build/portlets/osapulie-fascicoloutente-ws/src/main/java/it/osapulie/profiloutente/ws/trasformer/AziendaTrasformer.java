package it.osapulie.profiloutente.ws.trasformer;

import it.osapulie.domain.Azienda;
import it.osapulie.profiloutente.ws.dto.AziendaDto;

public class AziendaTrasformer extends MainTrasformer<AziendaDto, Azienda> {

	public AziendaTrasformer(Class<AziendaDto> dtoClass, Class<Azienda> entityClass) {
		super(dtoClass, entityClass);
	}
}
