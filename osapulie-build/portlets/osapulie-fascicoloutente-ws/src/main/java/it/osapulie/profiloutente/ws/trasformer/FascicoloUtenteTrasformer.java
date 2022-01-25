package it.osapulie.profiloutente.ws.trasformer;

import it.osapulie.domain.Azienda;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.fascicoloutente.FascicoloUtente;
import it.osapulie.domain.fascicoloutente.RichiestaServizio;
import it.osapulie.profiloutente.ws.dto.AziendaDto;
import it.osapulie.profiloutente.ws.dto.CittadinoDto;
import it.osapulie.profiloutente.ws.dto.FascicoloUtenteDto;
import it.osapulie.profiloutente.ws.dto.RichiestaDto;

import java.util.ArrayList;
import java.util.List;

public class FascicoloUtenteTrasformer extends MainTrasformer<FascicoloUtenteDto, FascicoloUtente> {

	public FascicoloUtenteTrasformer(Class<FascicoloUtenteDto> dtoClass, Class<FascicoloUtente> entityClass) {
		super(dtoClass, entityClass);
	}
	
	private AziendaTrasformer aziendaTrasformer = new AziendaTrasformer(AziendaDto.class, Azienda.class);
	
	private CittadinoTrasformer cittadinoTrasformer = new CittadinoTrasformer(CittadinoDto.class, ProfiloUtenteCittadino.class);
	
	private RichiestaTrasformer richiestaTrasformer = new RichiestaTrasformer(RichiestaDto.class, RichiestaServizio.class);
	
	@Override
	public FascicoloUtenteDto toDto(FascicoloUtente entity) throws Exception {
		if(entity!=null){
			FascicoloUtenteDto dto = new FascicoloUtenteDto();
			dto.setAziendaDto(aziendaTrasformer.toDto(entity.getAzienda()));
			dto.setCittadinoDto(cittadinoTrasformer.toDto(entity.getCittadino()));
			if(entity.getRichieste()!=null){
				List<RichiestaDto> richiesteList = new ArrayList<RichiestaDto>();
				for(RichiestaServizio richiesta : entity.getRichieste()){
					RichiestaDto richiestaDto = richiestaTrasformer.toDto(richiesta);
					richiesteList.add(richiestaDto);
				}
				dto.setRichiesteDto(richiesteList);
			}
			return dto;
		}else{
			return null;
		}
	}
	
}
