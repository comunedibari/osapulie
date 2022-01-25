package it.osapulie.service;

import java.util.List;
import it.osapulie.domain.DwhDatamining;
import it.osapulie.domain.DwhGeolocalizzazione;
import it.osapulie.domain.DwhServizioAttribute;
import it.osapulie.domain.DwhTempiMedi;
import it.osapulie.util.IUpdateAttribute;
import it.osapulie.util.dto.DwhDataminingDTO;
import it.osapulie.util.dto.DwhServizioAttributeDTO;
import it.osapulie.util.dto.DwhTempiMediDTO;


public interface DwhService {

	List<DwhDatamining> findByDataminingUserCod(String userCod) throws ServiceLayerException;
	List<DwhGeolocalizzazione> findByGeolocalizzazioneUserCod(String userCod) throws ServiceLayerException;
	List<DwhTempiMedi> findByTempiMediUuidOperazione(String uuidOperazione) throws ServiceLayerException;
	DwhTempiMedi saveTempiMedi(DwhTempiMedi tempiMedi)throws ServiceLayerException;
	DwhTempiMedi update(DwhTempiMedi tempiMedi);
	DwhGeolocalizzazione saveGeolocalizzazione(DwhGeolocalizzazione geolocalizzazione)throws ServiceLayerException;
	DwhDatamining saveDatamining(DwhDatamining datamining)throws ServiceLayerException;
	List<DwhServizioAttribute> findByUuid(String uuid)throws ServiceLayerException;
	DwhServizioAttribute saveServizioAttribute(DwhServizioAttribute servizioAttribute)throws ServiceLayerException;
	DwhServizioAttribute updateServizioAttribute(IUpdateAttribute updateAttribute)throws ServiceLayerException;
}
