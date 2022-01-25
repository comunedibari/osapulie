package it.osapulie.sociali.service;

import it.osapulie.service.ServiceLayerException;
import it.osapulie.util.dto.DwhDataminingDTO;
import it.osapulie.util.dto.DwhServizioAttributeDTO;
import it.osapulie.util.dto.DwhTempiMediDTO;

public interface AuditDwhService {

	void invokeAuditService(DwhServizioAttributeDTO servizioAttributeDto, DwhDataminingDTO dataminingDto,
			DwhTempiMediDTO tempiMediDto) throws ServiceLayerException, Exception;

}
