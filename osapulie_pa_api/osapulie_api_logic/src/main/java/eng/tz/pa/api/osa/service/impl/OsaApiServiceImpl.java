package eng.tz.pa.api.osa.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.tz.pa.api.osa.dao.models.CambioResidenzaPartenzeArrivi;
import eng.tz.pa.api.osa.dao.models.ComuneServizi;
import eng.tz.pa.api.osa.dao.models.DwhServizioAttribute;
import eng.tz.pa.api.osa.dao.models.EtaMediaServiziRichiesti;
import eng.tz.pa.api.osa.dao.models.InfoAzienda;
import eng.tz.pa.api.osa.dao.models.RichiesteServiziAzienda;
import eng.tz.pa.api.osa.dao.models.RichiesteServiziPerAzienda;
import eng.tz.pa.api.osa.dao.models.RichiesteServiziUtente;
import eng.tz.pa.api.osa.dao.models.ServiziRichiestiGeolocalizzazione;
import eng.tz.pa.api.osa.dao.models.ServiziRichiestiPerFasciaDiEta;
import eng.tz.pa.api.osa.dao.models.ServiziRichiestiPercCompletati;
import eng.tz.pa.api.osa.dao.models.Servizio;
import eng.tz.pa.api.osa.dao.models.TempoMedioServiziRichiesti;
import eng.tz.pa.api.osa.dao.models.TotaleAccessiServizi;
import eng.tz.pa.api.osa.dao.models.TotaleAccessiServiziPerData;
import eng.tz.pa.api.osa.dao.models.TotaleServiziComune;
import eng.tz.pa.api.osa.dao.repository.ComuneServiziDao;
import eng.tz.pa.api.osa.dao.repository.DwhRichiesteRepositoryDao;
import eng.tz.pa.api.osa.dao.repository.RichiesteServiziUtenteDao;
import eng.tz.pa.api.osa.dao.repository.TotaleAccessiServiziDao;
import eng.tz.pa.api.osa.dao.repository.TotaleServiziComuneDao;
import eng.tz.pa.api.osa.service.OsaApiService;

@Service("osaApiService")
public class OsaApiServiceImpl implements OsaApiService {

	@Autowired
	private ComuneServiziDao comuneServiziDao;
	@Autowired
	private RichiesteServiziUtenteDao richiesteServiziUtenteDao;
	@Autowired
	private TotaleAccessiServiziDao totaleAccessiServiziDao;
	@Autowired
	private TotaleServiziComuneDao totaleServiziComuneDao;
	@Autowired
	private DwhRichiesteRepositoryDao dwhRichiesteRepositoryDao;
	
	public OsaApiServiceImpl() {
		 
	}
	
	@Override
	@Transactional(value="txMgr", rollbackFor= {Exception.class}, readOnly=true)
	public List<ComuneServizi> getListComuneServizi() throws Exception {
		 
		return comuneServiziDao.getListComuneServizi();
	}

	@Override
	@Transactional(value="txMgr", rollbackFor= {Exception.class}, readOnly=true)
	public List<RichiesteServiziUtente> getListRichiesteServiziUtente() throws Exception {
		 
		return richiesteServiziUtenteDao.getListRichiesteServiziUtente();
	}
	
	@Override
	@Transactional(value="txMgr", rollbackFor= {Exception.class}, readOnly=true)
	public List<RichiesteServiziAzienda> getListRichiesteServiziAzienda() throws Exception {
		 
		return richiesteServiziUtenteDao.getListRichiesteServiziAzienda();
	}
	
	@Override
	@Transactional(value="txMgr", rollbackFor= {Exception.class}, readOnly=true)
	public List<TotaleAccessiServizi> getListTotaleAccessiServizi() throws Exception {
		 
		return totaleAccessiServiziDao.getListTotaleAccessiServizi();
	}

	@Override
	@Transactional(value="txMgr", rollbackFor= {Exception.class}, readOnly=true)
	public List<TotaleAccessiServizi> getListTotaleAllServizi() throws Exception {
 
		return totaleAccessiServiziDao.getListTotaleAllServizi();
	}

	@Override
	@Transactional(value="txMgr", rollbackFor= {Exception.class}, readOnly=true)
	public List<TotaleServiziComune> getListTotaleServiziComune() throws Exception {
	 
		return totaleServiziComuneDao.getListTotaleServiziComune();
	}

	@Override
	@Transactional(value="txMgr", rollbackFor= {Exception.class}, readOnly=true)
	public List<TotaleAccessiServiziPerData> getRichiesteServiziPeData(String dfrom, String dto) throws Exception {
		 
		return richiesteServiziUtenteDao.getRichiesteServiziPeData(dfrom, dto);
	}
	
	@Override
	@Transactional(value="txMgr", rollbackFor= {Exception.class}, readOnly=true)
	public List<RichiesteServiziPerAzienda> getRichiesteServiziPerAzienda(String cIdUser) throws Exception {
	 
		return richiesteServiziUtenteDao.getRichiesteServiziPerAzienda(cIdUser);
	}
	
	@Override
	@Transactional(value="txMgr", rollbackFor= {Exception.class}, readOnly=true)
	public List<InfoAzienda> getInfoAzienda() throws Exception {
		return richiesteServiziUtenteDao.getInfoAzienda();
				
	}
	
	@Override
	@Transactional(value="txMgr", rollbackFor= {Exception.class}, readOnly=true)
	public List<CambioResidenzaPartenzeArrivi> getCambioResidenzaPartenzeArrivi() throws Exception {
		return richiesteServiziUtenteDao.getCambioResidenzaPartenzeArrivi();
	}

	@Override
	@Transactional(value="txMgr", rollbackFor= {Exception.class}, readOnly=true)
	public List<DwhServizioAttribute> getDwhServizioAttribute(String codeOrName) throws Exception {
	 
		return dwhRichiesteRepositoryDao.getDwhServizioAttribute(codeOrName);
	}

	@Override
	@Transactional(value="txMgr", rollbackFor= {Exception.class}, readOnly=true)
	public List<EtaMediaServiziRichiesti> getDwhEtaMediaServiziRichiesti() throws Exception {
	 
		return dwhRichiesteRepositoryDao.getDwhEtaMediaServiziRichiesti();
	}

	@Override
	@Transactional(value="txMgr", rollbackFor= {Exception.class}, readOnly=true)
	public List<TempoMedioServiziRichiesti> getDwhTempoMedioServiziRichiesti() throws Exception {
		 
		return dwhRichiesteRepositoryDao.getDwhTempoMedioServiziRichiesti();
	}

	@Override
	@Transactional(value="txMgr", rollbackFor= {Exception.class}, readOnly=true)
	public List<ServiziRichiestiPercCompletati> getDwhServiziRichiestiPercCompletati() throws Exception {
	 
		return dwhRichiesteRepositoryDao.getDwhServiziRichiestiPercCompletati();
	}

	@Override
	@Transactional(value="txMgr", rollbackFor= {Exception.class}, readOnly=true)
	public List<ServiziRichiestiGeolocalizzazione> getDwhServiziGeolocalizzazione() throws Exception {
 
		return dwhRichiesteRepositoryDao.getDwhServiziGeolocalizzazione();
	}

	@Override
	@Transactional(value="txMgr", rollbackFor= {Exception.class}, readOnly=true)
	public List<Servizio> getDwhServizio() throws Exception {
 
		return dwhRichiesteRepositoryDao.getDwhServizio();
	}

	@Override
	public List<ServiziRichiestiPerFasciaDiEta> getDwhServiziRichiestiPerFasciaDiEta() throws Exception {

		return dwhRichiesteRepositoryDao.getDwhServiziRichiestiPerFasciaDiEta();
	}
	
}
