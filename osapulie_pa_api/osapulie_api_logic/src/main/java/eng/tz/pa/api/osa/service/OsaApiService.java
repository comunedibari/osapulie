package eng.tz.pa.api.osa.service;
import java.util.List;

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


public interface OsaApiService {

	public List<ComuneServizi> getListComuneServizi() throws Exception;
	public List<RichiesteServiziUtente> getListRichiesteServiziUtente() throws Exception;
	public List<RichiesteServiziAzienda> getListRichiesteServiziAzienda() throws Exception;
	public List<TotaleAccessiServizi> getListTotaleAccessiServizi() throws Exception;
	public List<TotaleAccessiServizi> getListTotaleAllServizi() throws Exception;
	public List<TotaleServiziComune> getListTotaleServiziComune() throws Exception;
	public List<TotaleAccessiServiziPerData> getRichiesteServiziPeData(String dfrom,String dto) throws Exception;
	public List<RichiesteServiziPerAzienda> getRichiesteServiziPerAzienda(String cIdUser) throws Exception ;	
	public List<InfoAzienda> getInfoAzienda() throws Exception ;
	public List<CambioResidenzaPartenzeArrivi> getCambioResidenzaPartenzeArrivi() throws Exception ;
	
	public List<DwhServizioAttribute> getDwhServizioAttribute(String codeOrName) throws Exception ;
	public List<EtaMediaServiziRichiesti> getDwhEtaMediaServiziRichiesti() throws Exception ;
	public List<TempoMedioServiziRichiesti> getDwhTempoMedioServiziRichiesti() throws Exception ;
	public List<ServiziRichiestiPercCompletati> getDwhServiziRichiestiPercCompletati() throws Exception ;
	public List<ServiziRichiestiGeolocalizzazione> getDwhServiziGeolocalizzazione() throws Exception ;
	public List<Servizio> getDwhServizio() throws Exception;
	public List<ServiziRichiestiPerFasciaDiEta> getDwhServiziRichiestiPerFasciaDiEta() throws Exception;
}
