package it.osapulie.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.osapulie.domain.Audit;
import it.osapulie.persistence.AuditRepository;
import it.osapulie.service.AuditService;
import it.osapulie.service.ServiceLayerException;

 
@Service("auditService")
@Transactional
public class AuditServiceImpl implements AuditService {

	@Inject
	private AuditRepository auditRepository;
	

	@Override
	public Audit getAuditByPk(long idAudit) throws ServiceLayerException {
 
		return auditRepository.getAuditByPk(idAudit);
		 
		
	}

	@Override
	public List<Audit> getAllAudit() throws ServiceLayerException {
		return auditRepository.getAllAudit();
	}

	@Override
	public void saveAudit(Audit audit) throws ServiceLayerException {
	auditRepository.save(audit);
		
	}

	@Override
	public void deleteAudit(long idAudit) throws ServiceLayerException {
		auditRepository.delete(idAudit);
		
	}

	@Override
	public Audit updateAudit(Audit audit) throws ServiceLayerException {
		 
		return auditRepository.save(audit);
	}

	@Override
	public List<Audit> findByUserCod(String userCod) throws ServiceLayerException {
		 
		return auditRepository.findByUserCod(userCod);
	}
	
	@Override
	public List<Audit> findByGiornoMeseAnno(String giornoMeseAnno) throws ServiceLayerException {
		return auditRepository.findByGiornoMeseAnno(giornoMeseAnno);
	}
}
