package it.osapulie.service.impl;

import java.util.List;

import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.osapulie.domain.DwhDatamining;
import it.osapulie.domain.DwhGeolocalizzazione;
import it.osapulie.domain.DwhServizioAttribute;
import it.osapulie.domain.DwhTempiMedi;
import it.osapulie.persistence.DwhDataminingRepository;
import it.osapulie.persistence.DwhGeolocalizzazioneRepository;
import it.osapulie.persistence.DwhServizioAttributeRepository;
import it.osapulie.persistence.DwhTempiMediRepository;
import it.osapulie.service.DwhService;
import it.osapulie.service.ServiceLayerException;
import it.osapulie.util.DwhAuditPayload;
import it.osapulie.util.IUpdateAttribute;
import it.osapulie.util.dto.DwhDataminingDTO;
import it.osapulie.util.dto.DwhServizioAttributeDTO;
import it.osapulie.util.dto.DwhTempiMediDTO;
import it.osapulie.web.client.DwhAuditClient;

 
@Service("dwhService")
@Transactional
public class DwhServiceImpl implements DwhService {

	@Inject
	private DwhDataminingRepository dataminingRepository;

	@Inject
	private DwhGeolocalizzazioneRepository geolocalizzazioneRepository;
	
	@Inject
	private DwhTempiMediRepository tempiMediRepository;
	
	@Inject
	private DwhServizioAttributeRepository servizioAttributeRepository;
	
	
	@Override
	public List<DwhDatamining> findByDataminingUserCod(String userCod) throws ServiceLayerException {
		 
		return dataminingRepository.findByUserCod(userCod);
	}

	@Override
	public List<DwhGeolocalizzazione> findByGeolocalizzazioneUserCod(String userCod) throws ServiceLayerException {
		 
		return geolocalizzazioneRepository.findByUserCod(userCod);
	}

	@Override
	public List<DwhTempiMedi> findByTempiMediUuidOperazione(String uuidOperazione) throws ServiceLayerException {
		 
		return tempiMediRepository.findByUuidOperazione(uuidOperazione);
	}

 
	@Override
	public DwhTempiMedi saveTempiMedi(DwhTempiMedi tempiMedi) throws ServiceLayerException {
		 
		return tempiMediRepository.save(tempiMedi);
	}

	@Override
	public DwhGeolocalizzazione saveGeolocalizzazione(DwhGeolocalizzazione geolocalizzazione)
			throws ServiceLayerException {
	
		return geolocalizzazioneRepository.save(geolocalizzazione);
	}

	@Override
	public DwhDatamining saveDatamining(DwhDatamining datamining) throws ServiceLayerException {
	
		return dataminingRepository.save(datamining);
	}

	@Override
	public List<DwhServizioAttribute> findByUuid(String uuid) throws ServiceLayerException {
		 
		return servizioAttributeRepository.findByUuid(uuid);
	}

	@Override
	public DwhServizioAttribute saveServizioAttribute(DwhServizioAttribute servizioAttribute)
			throws ServiceLayerException {
		 
		return servizioAttributeRepository.save(servizioAttribute);
	}

	@Override
	public DwhServizioAttribute updateServizioAttribute(IUpdateAttribute updateAttribute) throws ServiceLayerException {
		if(updateAttribute.searchUUID()!=null) {
		List<DwhServizioAttribute> lts=findByUuid(updateAttribute.searchUUID());
		if(lts!=null && lts.size()>0)
		{
			DwhServizioAttribute atSerAtt=lts.get(0);
			if(updateAttribute.updateServizioFine()!=null)
				atSerAtt.setServizio_fine(updateAttribute.updateServizioFine());
			if(updateAttribute.updateServizioParametro1()!=null)
				atSerAtt.setServizio_parametro1(updateAttribute.updateServizioParametro1());
			if(updateAttribute.updateServizioParametro2()!=null)
				atSerAtt.setServizio_parametro1(updateAttribute.updateServizioParametro2());
			if(updateAttribute.updateServizioParametro3()!=null)
				atSerAtt.setServizio_parametro1(updateAttribute.updateServizioParametro3());
			if(updateAttribute.updateServizioProtocollo()!=null)
				atSerAtt.setServizio_protocollo(updateAttribute.updateServizioProtocollo());
			
		return	servizioAttributeRepository.save(atSerAtt);
		}
		}
		return null;
	}

	@Override
	public DwhTempiMedi update(DwhTempiMedi tempiMedi) {
		 if(tempiMedi!=null)
		 {
		return tempiMediRepository.save(tempiMedi);
		 }
		return null;
	}

	
	

}
