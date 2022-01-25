package it.eng.tz.area.vasta.protocollo.spring.service;

import java.util.Date;
import java.util.List;

import it.eng.tz.area.vasta.protocollo.spring.dao.models.RegistroAuditModel;



public interface RegistroAuditModService {

	public int saveRegistroAudit(RegistroAuditModel m) throws Exception;
	public int updateRegistroAudit(RegistroAuditModel m) throws Exception;
	public List<RegistroAuditModel> getAllRegistroAudit() throws Exception;
	public List<RegistroAuditModel> getRegistroAudit(String fileName_ggddaaaa) throws Exception;
	public List<RegistroAuditModel> ricercaRegistriAudit(Date from,Date to) throws Exception;
	public int generaProgressivoRegistro(String anno) throws Exception;
	public List<String> getAuditDaProcessare() throws Exception ;
}
