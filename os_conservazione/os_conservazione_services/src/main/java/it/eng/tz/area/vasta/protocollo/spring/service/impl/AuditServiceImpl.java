package it.eng.tz.area.vasta.protocollo.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.eng.tz.area.vasta.protocollo.spring.dao.models.Audit;
import it.eng.tz.area.vasta.protocollo.spring.dao.repository.AuditDao;
import it.eng.tz.area.vasta.protocollo.spring.service.AuditService;

@Service("auditService")
public class AuditServiceImpl implements AuditService {

	@Autowired
	private AuditDao auditDao;

	@Override
	@Transactional(value="txMgr", rollbackFor= {Exception.class}, readOnly=true)
	public List<Audit> getAllAudit() throws Exception {

		return auditDao.getAllAudit();
	}

	@Override
	@Transactional(value="txMgr", rollbackFor= {Exception.class}, readOnly=false)
	public int updateAudit(Audit m) throws Exception {

		return auditDao.updateAudit(m);
	}

	@Override
	@Transactional(value="txMgr", rollbackFor= {Exception.class}, readOnly=false)
	public int saveAudit(Audit m) throws Exception {

		return auditDao.saveAudit(m);
	}

	@Override
	@Transactional(value="txMgr", rollbackFor= {Exception.class}, readOnly=true)
	public List<Audit> getAudits(String ggMMaaaa) throws Exception {

		return auditDao.getAudits(ggMMaaaa);
	}

	@Override
	@Transactional(value="txMgr", rollbackFor= {Exception.class}, readOnly=true)
	public List<Audit> getAudits(String ggMMaaaa, String stato) throws Exception {
		return auditDao.getAudits(ggMMaaaa, stato);
	}
	
	@Override
	@Transactional(value="txMgr", rollbackFor= {Exception.class}, readOnly=true)
	public List<String> getListFolder(List<String> folders) throws Exception {
		return auditDao.getListFolder(folders);
	}
}