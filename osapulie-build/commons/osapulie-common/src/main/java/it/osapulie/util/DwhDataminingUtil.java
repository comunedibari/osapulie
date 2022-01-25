package it.osapulie.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;

import it.osapulie.domain.DwhDatamining;
import it.osapulie.service.DwhService;
import it.osapulie.util.dto.DwhDataminingDTO;

public class DwhDataminingUtil {
	private InfoUserUtil infoUser;
	private DwhDatamining datamining;
	private DwhDataminingDTO dataminingDto;
	DwhService dwhService;
	private  static DwhDataminingUtil factoryDtm;
	
	public DwhDataminingUtil(String codiceFiscale,DwhService dwhService) {
		this.infoUser = new InfoUserUtil(codiceFiscale);
		this.dwhService=dwhService;
		this.datamining= new DwhDatamining();
		this.dataminingDto = new DwhDataminingDTO(); 
	}
	
	public static DwhDataminingUtil get(String codiceFiscale,DwhService dwhService) 
	{
			factoryDtm= new DwhDataminingUtil(codiceFiscale,dwhService);
		return factoryDtm;
	}
	
	
	public DwhDatamining save() {
 
		datamining.setCod_cittadino(infoUser.getCodiceFiscale());
		datamining.setData_richiesta(new Date());
		datamining.setSesso(infoUser.getSesso());
		datamining.setEta_richiedente(infoUser.getEta());
		return dwhService.saveDatamining(datamining);
	}
	
	public DwhDataminingDTO getDatamining() throws IllegalAccessException, InvocationTargetException {
		BeanUtils.copyProperties(dataminingDto, datamining);
		dataminingDto.setCod_cittadino(infoUser.getCodiceFiscale());
		dataminingDto.setData_richiesta(new Date());
		dataminingDto.setSesso(infoUser.getSesso());
		dataminingDto.setEta_richiedente(infoUser.getEta());
		return dataminingDto;
	}
	
	public DwhDataminingUtil setCodServizio(String cod_servizio) {
		datamining.setCod_servizio(cod_servizio);
		return this;
	}
	
	public DwhDataminingUtil setUuidOperazione(String uuid) {
		datamining.setUuid_operazione(uuid);
		return this;
	}
	
	
}
