package it.osapulie.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.osapulie.domain.DwhTempiMedi;
import it.osapulie.service.DwhService;
import it.osapulie.util.audit.AuditCustomMeta;
import it.osapulie.util.dto.DwhTempiMediDTO;

public class DwhTempiMediUtil {
	protected static Logger log = LoggerFactory.getLogger(DwhTempiMediUtil.class);
	private DwhService dwhService;
	private DwhTempiMedi dwhTempiMedi;
	private DwhTempiMediDTO dwhTempiMediDto;
	private static DwhTempiMediUtil factory;
	private DwhTempiMediUtil(DwhService dwhService) {
		 this.dwhService=dwhService;
		 dwhTempiMedi= new DwhTempiMedi();
	}
	 
	public static DwhTempiMediUtil get(DwhService dwhService)
	{
	 
			factory= new DwhTempiMediUtil(dwhService);
		return factory;
	}
	
	public DwhTempiMediUtil go_StartTime() {

		dwhTempiMedi.setData_inizio(new Date());
		dwhTempiMedi.setCod_user(AuditCustomMeta.get().getActor());
		
		 return this;
		
	}
	public DwhTempiMediUtil go_EndTime(String uuidOperazione) {
		
		List<DwhTempiMedi> lts=dwhService.findByTempiMediUuidOperazione(uuidOperazione);
		if(lts!=null && lts.size()>0)
		{
		dwhTempiMedi= lts.get(0);
		dwhTempiMedi.setData_fine(new Date());
		long tempo_esecuzione= (dwhTempiMedi.getData_fine().getTime() - dwhTempiMedi.getData_inizio().getTime());
		dwhTempiMedi.setTempo_esecuzione((double)tempo_esecuzione);
		 
		}
		return this;
	}
	
	public DwhTempiMedi save() {
		if(dwhTempiMedi!=null && dwhTempiMedi.getUuid_operazione()!=null)
		{
		return dwhService.saveTempiMedi(dwhTempiMedi);	
		}
		return null;
	} 
	
	public DwhTempiMediUtil setCodServizio(String cod_servizio) {
		dwhTempiMedi.setCod_servizio(cod_servizio);
		return this;
	}
	
	public DwhTempiMediUtil setNomeServizio(String nomeServizio) {
		dwhTempiMedi.setNome_servizio(nomeServizio);
		return this;
	}
	
	public DwhTempiMediUtil setUuid(String uuid) {
		dwhTempiMedi.setUuid_operazione(uuid);
		return this;
	}
	
	
	
	public DwhTempiMediDTO getTempiMedi() throws IllegalAccessException, InvocationTargetException {
		if(dwhTempiMedi!=null && dwhTempiMedi.getUuid_operazione()!=null)
		{
		dwhTempiMediDto = new DwhTempiMediDTO(); 
		log.info("DwhTempiMediDTO: "+dwhTempiMediDto);
		log.info("DwhTempiMedi: "+dwhTempiMedi);
		BeanUtils.copyProperties(dwhTempiMediDto, dwhTempiMedi);	
		
		return dwhTempiMediDto;	
		}
		return null;
	} 
	
	
}
