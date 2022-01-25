package it.eng.tz.area.vasta.documentale.cmis.spring.service;

import java.util.List;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.QueryResult;

import it.eng.tz.area.vasta.documentale.cmis.exception.DocumentaleCmisException;

public interface ISearchDocument {
public List<CmisObject> getListCmisObject(String query) throws DocumentaleCmisException;
public List<Document> getListDocuments(String query) throws DocumentaleCmisException;
public List<CmisObject> getListCmisObject(String cmisUser, String cmisPassword,String query) throws DocumentaleCmisException;
public List<Document> getListDocuments(String cmisUser, String cmisPassword,String query) throws DocumentaleCmisException;
public ItemIterable<QueryResult> getQueryResult(String query) throws DocumentaleCmisException;
public ItemIterable<QueryResult> getQueryResult(String cmisUser, String cmisPassword,String query) throws DocumentaleCmisException;
public CmisObject getCmisObject(String idObjOrPath) throws DocumentaleCmisException;
public Document getCmisDocument(String idObjOrPath) throws DocumentaleCmisException;
public CmisObject getCmisObject(String cmisUser, String cmisPassword,String idObjOrPath) throws DocumentaleCmisException;
public Document getCmisDocument(String cmisUser, String cmisPassword,String idObjOrPath) throws DocumentaleCmisException;


}
