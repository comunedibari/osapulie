package it.eng.tz.area.vasta.documentale.cmis.core;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Repository;
import org.apache.chemistry.opencmis.client.api.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.eng.tz.area.vasta.documentale.cmis.model.BORegistroProtocollo;

@Component
public class CMISManager extends CMISAbstractConnect {

	private static final long serialVersionUID = 1610016294803318660L;
	@Autowired
	public CMISManager() {
		super();
	}
	 
	public CMISManager connect(String cmisUser, String cmisPassword) {
		super.setCmisUserPassword(cmisUser,cmisPassword);
		initConnectionDefault();
		return this;
	}
	

	@Override
	public Document getDocument(String idObjOrPath) {
		return super.getDocument(idObjOrPath);
	}

	@Override
	public Session getSession() {
		return super.getSession();
	}

	@Override
	protected Session initConnection(String cmisUser, String cmisPassword) {
		return super.initConnection(cmisUser, cmisPassword);
	}

	@Override
	protected Session initConnectionDefault() {
		return super.initConnectionDefault();
	}

	@Override
	public Repository getRepository() {
		return super.getRepository();
	}

 
	public Document createDocumentRegistroProtocollo(Folder folder, BORegistroProtocollo regProtocollo) {
		return super.createDocumentRegistroProtocollo(folder, regProtocollo);
	}
 

	
}
