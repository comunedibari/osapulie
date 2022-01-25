package it.eng.tz.area.vasta.documentale.cmis.spring.service;

import org.apache.chemistry.opencmis.client.api.Document;
import it.eng.tz.area.vasta.documentale.cmis.exception.DocumentaleCmisException;
import it.eng.tz.area.vasta.documentale.cmis.model.BORegistroProtocollo;

public interface IRegistroProtocollo {
	public Document saveRegistroProtocollo(BORegistroProtocollo registroProtocollo ) throws DocumentaleCmisException;
	public Document saveRegistroProtocollo(BORegistroProtocollo registroProtocollo,String cmisUser, String cmisPassword) throws DocumentaleCmisException;
}
 