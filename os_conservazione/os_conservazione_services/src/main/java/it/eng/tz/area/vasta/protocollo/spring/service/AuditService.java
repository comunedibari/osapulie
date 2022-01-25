package it.eng.tz.area.vasta.protocollo.spring.service;

import java.util.List;

import it.eng.tz.area.vasta.protocollo.spring.dao.models.Audit;



public interface AuditService {

 
	public List<Audit> getAllAudit() throws Exception;
	public int updateAudit(Audit m) throws Exception;
	public int saveAudit(Audit m) throws Exception;
	public List<Audit> getAudits(String ggMMaaaa) throws Exception;
	public List<Audit> getAudits(String ggMMaaaa,String stato) throws Exception;
	public List<String> getListFolder(List<String> foders) throws Exception;
}
