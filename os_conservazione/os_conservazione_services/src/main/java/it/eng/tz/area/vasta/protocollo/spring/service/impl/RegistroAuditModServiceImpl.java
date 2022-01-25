package it.eng.tz.area.vasta.protocollo.spring.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.eng.tz.area.vasta.protocollo.spring.dao.models.RegistroAuditModel;
import it.eng.tz.area.vasta.protocollo.spring.dao.repository.RegistroAuditModDao;
import it.eng.tz.area.vasta.protocollo.spring.service.RegistroAuditModService;

@Service("registroAuditModService")
public class RegistroAuditModServiceImpl implements RegistroAuditModService{

	@Autowired
	private RegistroAuditModDao registroAuditDao;

	@Override
	public int saveRegistroAudit(RegistroAuditModel m) throws Exception {

		return registroAuditDao.saveRegistroAudit(m);
	}

	@Override
	public int updateRegistroAudit(RegistroAuditModel m) throws Exception {

		return registroAuditDao.updateRegistroAudit(m);
	}

	@Override
	public List<RegistroAuditModel> getAllRegistroAudit() throws Exception {

		return registroAuditDao.getAllRegistroAudit();
	}

	@Override
	public List<RegistroAuditModel> getRegistroAudit(String fileName_ggddaaaa) throws Exception {

		return registroAuditDao.getRegistroAudit(fileName_ggddaaaa);
	}

	@Override
	public List<RegistroAuditModel> ricercaRegistriAudit(Date from, Date to) throws Exception {

		return registroAuditDao.ricercaRegistriAudit(from, to);
	}

	@Override
	public int generaProgressivoRegistro(String anno) throws Exception {
		return registroAuditDao.generaProgressivoRegistro(anno);
	}

	@Override
	public List<String> getAuditDaProcessare() throws Exception {
		return registroAuditDao.getAuditDaProcessare();
	}

}
