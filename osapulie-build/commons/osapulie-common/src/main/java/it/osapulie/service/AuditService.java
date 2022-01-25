package it.osapulie.service;

import java.util.List;
import it.osapulie.domain.Audit;


public interface AuditService {

 
	Audit getAuditByPk(long idAudit) throws ServiceLayerException;
 
	List<Audit> getAllAudit() throws ServiceLayerException;
 
	void saveAudit(Audit audit) throws ServiceLayerException;
 
	void deleteAudit(long idAudit) throws ServiceLayerException;
 
	Audit updateAudit(Audit audit) throws ServiceLayerException;

	List<Audit> findByUserCod(String userCod) throws ServiceLayerException;
	
	List<Audit> findByGiornoMeseAnno(String giornoMeseAnno) throws ServiceLayerException;
	 

}
