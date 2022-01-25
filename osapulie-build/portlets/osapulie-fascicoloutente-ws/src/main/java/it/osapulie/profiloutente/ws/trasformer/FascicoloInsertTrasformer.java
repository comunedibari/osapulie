package it.osapulie.profiloutente.ws.trasformer;

import it.osapulie.domain.dto.Fascicolo;
import it.osapulie.profiloutente.ws.dto.FascicoloInsertDto;
import it.osapulie.shared.service.UserPreferences;

public class FascicoloInsertTrasformer extends MainTrasformer<FascicoloInsertDto, Fascicolo> {

	public FascicoloInsertTrasformer(Class<FascicoloInsertDto> dtoClass, Class<Fascicolo> entity) {
		super(dtoClass, entity);
	}
	
	@Override
	public Fascicolo fromDto(FascicoloInsertDto dto) throws Exception {
		if(dto!=null){
			Fascicolo entity = super.fromDto(dto);
			UserPreferences userPreferences = new UserPreferences();
			if(dto.getCodiceFiscaleServizio()!=null){
				userPreferences.setCodiceFiscaleServizio(dto.getCodiceFiscaleServizio());
			}
			if(dto.getCodiceIstatComune()!=null){
				userPreferences.setCodiceIstatComune(dto.getCodiceIstatComune());
			}
			if(dto.getUriServizioGateway()!=null){
				userPreferences.setUriServizioGateway(dto.getUriServizioGateway());
			}
			if(dto.getPartitaIvaServizio()!=null){
				userPreferences.setPartitaIvaServizio(dto.getPartitaIvaServizio());
			}
//			if(dto.getIdDelega()!=null){
//				userPreferences.setIdDelega(dto.getIdDelega());
//			}
			entity.setUserPreferences(userPreferences);
			return entity;
		}else{
			return null;
		}
	}
	
}
