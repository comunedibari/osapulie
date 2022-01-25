package it.eng.tz.area.vasta.protocollo.spring.service;

import it.eng.tz.area.vasta.protocollo.spring.dao.models.RegistroAuditModel;

public interface RegistroGiornalieroAuditService {
public RegistroAuditModel generaRegistro()throws Exception;
public RegistroAuditModel generaRegistro(String ggmmaaaa)throws Exception;
}
