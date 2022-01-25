package it.osapulie.profiloutente.ws.trasformer;

import it.osapulie.domain.servizi.Servizio;
import it.osapulie.profiloutente.ws.dto.ServizioDto;

public class ServizioTrasformer extends MainTrasformer<ServizioDto, Servizio> {

	public ServizioTrasformer(Class<ServizioDto> dtoClass, Class<Servizio> entityClass) {
		super(dtoClass, entityClass);
	}
}
