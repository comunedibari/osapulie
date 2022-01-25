package it.eng.tz.area.vasta.protocollo.spring.service;

import it.eng.tz.area.vasta.protocollo.spring.dao.models.DwhGeolocalizzazione;

public interface DwhGeolocalizzazioneService {
	
	public int saveDwhGeolocalizzazione(DwhGeolocalizzazione geo) throws Exception;

}