package it.osapulie.profiloutente.ws.trasformer;

import it.osapulie.domain.Azienda;
import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.fascicoloutente.RichiestaServizio;
import it.osapulie.domain.servizi.Servizio;
import it.osapulie.profiloutente.ws.dto.AziendaDto;
import it.osapulie.profiloutente.ws.dto.CittadinoDto;
import it.osapulie.profiloutente.ws.dto.ComuneISADto;
import it.osapulie.profiloutente.ws.dto.DelegatoDto;
import it.osapulie.profiloutente.ws.dto.RichiestaDto;
import it.osapulie.profiloutente.ws.dto.ServizioDto;

import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;

public class RichiestaTrasformer extends MainTrasformer<RichiestaDto, RichiestaServizio> {
	private ComuneISATrasformer comuneISATrasformer = new ComuneISATrasformer(ComuneISADto.class, ComuneISA.class);
	
	private AziendaTrasformer aziendaTrasformer = new AziendaTrasformer(AziendaDto.class, Azienda.class);
	
	private CittadinoTrasformer cittadinoTrasformer = new CittadinoTrasformer(CittadinoDto.class, ProfiloUtenteCittadino.class);
	
	private ServizioTrasformer servizioTrasformer = new ServizioTrasformer(ServizioDto.class, Servizio.class);
	
	public RichiestaTrasformer(Class<RichiestaDto> dtoClass, Class<RichiestaServizio> entityClass) {
		super(dtoClass, entityClass);
	}
	
	@Override
	public RichiestaDto toDto(RichiestaServizio entity) throws Exception {
		if(entity!=null){
			RichiestaDto dto = super.toDto(entity);
			dto.setComuneISADto(comuneISATrasformer.toDto(entity.getComuneErogatore()));
			if(entity.getDataRichiesta()!=null){
				GregorianCalendar cal = new GregorianCalendar();
				cal.setTime(entity.getDataRichiesta());
				dto.setRichiestaDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(cal));
			}
			if(entity.getDataRisposta()!=null){
				GregorianCalendar cal = new GregorianCalendar();
				cal.setTime(entity.getDataRisposta());
				dto.setRispostaDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(cal));
			}
			if(entity.getDelegato()!=null){
				DelegatoDto delegato = new DelegatoDto();
				if(entity.getDelegato().getAzienda()!=null){
					delegato.setAziendaDto(aziendaTrasformer.toDto(entity.getDelegato().getAzienda()));
				}
				if(entity.getDelegato().getProfiloUtenteCittadino()!=null){
					delegato.setCittadinoDto(cittadinoTrasformer.toDto(entity.getDelegato().getProfiloUtenteCittadino()));
				}
				dto.setDelegatoDto(delegato);
			}
			dto.setServizioDto(servizioTrasformer.toDto(entity.getServizio()));
			return dto;
		}else{
			return null;
		}
	}
	
}
