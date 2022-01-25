package it.eng.tz.area.vasta.documentale.cmis.spring.service.impl;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.eng.tz.area.vasta.documentale.cmis.core.CMISManager;
import it.eng.tz.area.vasta.documentale.cmis.model.BORegistroProtocollo;
import it.eng.tz.area.vasta.documentale.cmis.spring.service.IRegistroProtocollo;

@Service
public class RegistroProtocolloImpl implements IRegistroProtocollo{
	
	private static final Logger log = LoggerFactory.getLogger(RegistroProtocolloImpl.class.getName());

	
	@Autowired
	private CMISManager manager;
	
	@Override
	public Document saveRegistroProtocollo(BORegistroProtocollo registroProtocollo) {
		 Folder folder=null;
	 
			try {
				folder = manager.connect(null, null).getCheckFolderRegistroProtocollo(registroProtocollo.getDataPrimaReg());
			} catch (Exception e) {
				log.error(RegistroProtocolloImpl.class.getName()+":: saveRegistroProtocollo: ",e);
			}
		 
		return manager.connect(null, null).createDocumentRegistroProtocollo(folder, registroProtocollo);
	}

	@Override
	public Document saveRegistroProtocollo(BORegistroProtocollo registroProtocollo, String cmisUser, String cmisPassword) {
	 Folder folder=null;
	 
		try {
			folder = manager.connect(cmisUser, cmisPassword).getCheckFolderRegistroProtocollo(registroProtocollo.getDataPrimaReg());
		} catch (Exception e) {
		 
			log.error(RegistroProtocolloImpl.class.getName()+":: saveRegistroProtocollo: ",e);
		}
	 
	return manager.connect(null, null).createDocumentRegistroProtocollo(folder, registroProtocollo);
}

}
