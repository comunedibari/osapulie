package it.eng.tz.area.vasta.documentale.cmis.spring.service.impl;

import java.util.List;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.eng.tz.area.vasta.documentale.cmis.core.CMISManager;
import it.eng.tz.area.vasta.documentale.cmis.exception.DocumentaleCmisException;
import it.eng.tz.area.vasta.documentale.cmis.spring.service.ISearchDocument;

@Service
public class SearchDocumentImpl implements ISearchDocument{

	@Autowired
	private CMISManager manager;
	
	@Override
	public List<CmisObject> getListCmisObject(String query) throws DocumentaleCmisException {
		 
		return manager.connect(null, null).getListCmisObject(query);
	}

	@Override
	public List<Document> getListDocuments(String query) throws DocumentaleCmisException {
 
		return manager.connect(null, null).getListDocument(query);
	}

	@Override
	public List<CmisObject> getListCmisObject(String cmisUser, String cmisPassword, String query)
			throws DocumentaleCmisException {
		return manager.connect(cmisUser, cmisPassword).getListCmisObject(query);
	}

	@Override
	public List<Document> getListDocuments(String cmisUser, String cmisPassword, String query)
			throws DocumentaleCmisException {
		return manager.connect(cmisUser, cmisPassword).getListDocument(query);
	}

	@Override
	public CmisObject getCmisObject(String idObjOrPath) throws DocumentaleCmisException {
		return manager.connect(null, null).getDocument(idObjOrPath);
	}

	@Override
	public Document getCmisDocument(String idObjOrPath) throws DocumentaleCmisException {
		return manager.connect(null, null).getDocument(idObjOrPath);
	}

	@Override
	public CmisObject getCmisObject(String cmisUser, String cmisPassword, String idObjOrPath)
			throws DocumentaleCmisException {
		return manager.connect(cmisUser, cmisPassword).getCmisObject(idObjOrPath);
	}

	@Override
	public Document getCmisDocument(String cmisUser, String cmisPassword, String idObjOrPath)
			throws DocumentaleCmisException {
		return manager.connect(cmisUser, cmisPassword).getDocument(idObjOrPath);
	}

	@Override
	public ItemIterable<QueryResult> getQueryResult(String query) throws DocumentaleCmisException {
		return manager.connect(null, null).getQueryResult(query);
	}

	@Override
	public ItemIterable<QueryResult> getQueryResult(String cmisUser, String cmisPassword, String query)
			throws DocumentaleCmisException {
		return manager.connect(cmisUser, cmisPassword).getQueryResult(query);
	}

	
	
	
}
