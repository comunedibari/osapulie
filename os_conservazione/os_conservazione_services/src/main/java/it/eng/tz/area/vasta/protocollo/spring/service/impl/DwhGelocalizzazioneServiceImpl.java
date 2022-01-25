package it.eng.tz.area.vasta.protocollo.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.eng.tz.area.vasta.protocollo.spring.dao.models.DwhGeolocalizzazione;
import it.eng.tz.area.vasta.protocollo.spring.dao.repository.DwhGeolocalizzazioneDao;
import it.eng.tz.area.vasta.protocollo.spring.service.DwhGeolocalizzazioneService;

@Service("dwhGeolocalizzazioneService")
public class DwhGelocalizzazioneServiceImpl implements DwhGeolocalizzazioneService {

	@Autowired
	private DwhGeolocalizzazioneDao geolocalizzazioneDao;
 
	@Override
	@Transactional(value="txMgr", rollbackFor= {Exception.class}, readOnly=false)
	public int saveDwhGeolocalizzazione(DwhGeolocalizzazione geo) throws Exception {
	 
		return geolocalizzazioneDao.saveDwhGeolocalizzazione(geo);
	}
 
}