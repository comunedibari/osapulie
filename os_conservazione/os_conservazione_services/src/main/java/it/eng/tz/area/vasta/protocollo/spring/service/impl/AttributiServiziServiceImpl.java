package it.eng.tz.area.vasta.protocollo.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.eng.tz.area.vasta.protocollo.spring.dao.models.AttributiServizi;
import it.eng.tz.area.vasta.protocollo.spring.dao.repository.AttributiServiziDao;
import it.eng.tz.area.vasta.protocollo.spring.service.AttributiServiziService;


@Service("attributiServiziService")
public class AttributiServiziServiceImpl implements AttributiServiziService {

	@Autowired
	private AttributiServiziDao attributiServiziDao;
 
	@Override
	@Transactional(value="txMgr", rollbackFor= {Exception.class}, readOnly=false)
	public int saveAttributeServizio(AttributiServizi m) throws Exception {
	 
		return attributiServiziDao.saveAttributeServizio(m);
	}

	 
}