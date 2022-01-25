package it.eng.tz.area.vasta.documentale.cmis.core;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.annotation.PostConstruct;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.QueryResult;
import org.apache.chemistry.opencmis.client.api.Repository;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.data.PropertyData;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.exceptions.CmisObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import it.eng.tz.area.vasta.documentale.cmis.model.BORegistroProtocollo;

@Component
public abstract class CMISAbstractConnect extends CMISRegistroProtocollo implements Serializable {
	private Session session;
	private String cmisUser;
	private String cmisPassword;
	private Repository repository;
	String pathFolderRegistroProtocollo;
	private static final Logger log = LoggerFactory.getLogger(CMISAbstractConnect.class.getName());

	@Autowired
	private Environment env;
	
	
	public CMISAbstractConnect() {

	}

	@PostConstruct
	public void init() {
		log.info("Initializing the CMIS Connection");
		this.cmisUser = env.getProperty("area.vasta.protocollo.cmis.repository.username");
		this.cmisPassword = env.getProperty("area.vasta.protocollo.cmis.repository.password");
		this.pathFolderRegistroProtocollo=env.getProperty("area.vasta.protocollo.cmis.path.registro.protocollo");
	}

	public CMISAbstractConnect(String cmisUser, String cmisPassword) {
		this.cmisUser = cmisUser;
		this.cmisPassword = cmisPassword;
	}

	public CMISAbstractConnect connect(String cmisUser, String cmisPassword) {
		this.setCmisUserPassword(cmisUser, cmisPassword);
		initConnection(this.cmisUser, this.cmisPassword);
		return this;
	}

	public void setCmisUserPassword(String cmisUser, String cmisPassword) {
		if (cmisUser == null || cmisUser.isEmpty())
			this.cmisUser = env.getProperty("area.vasta.protocollo.cmis.repository.username");
		else
			this.cmisUser = cmisUser;
		if (cmisPassword == null || cmisPassword.isEmpty())
			this.cmisPassword = env.getProperty("area.vasta.protocollo.cmis.repository.password");
		else
			this.cmisPassword = cmisPassword;
	}

	protected Session initConnection(String cmisUser, String cmisPassword) {
		final String ATOMPUB_URL = env.getProperty("area.vasta.protocollo.cmis.repository.url");
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());
		parameters.put(SessionParameter.ATOMPUB_URL, ATOMPUB_URL);
		parameters.put(SessionParameter.OBJECT_FACTORY_CLASS,
		"org.alfresco.cmis.client.impl.AlfrescoObjectFactoryImpl");
		parameters.put(SessionParameter.CONNECT_TIMEOUT, "100000");
		parameters.put(SessionParameter.READ_TIMEOUT, "100000");

		if (null != cmisUser && cmisUser != "") {
			parameters.put(SessionParameter.USER, cmisUser);
		}

		if (null != cmisPassword && cmisPassword != "") {
			parameters.put(SessionParameter.PASSWORD, cmisPassword);
		}

		log.info("INIZIO CREAZIONE SESSIONE");

		// Creare una sessione
		try {
		SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
		List<Repository> lReposit=sessionFactory.getRepositories(parameters);
		if(lReposit!=null && lReposit.size()>0) {
			repository = lReposit.get(0);
		   session = repository.createSession();
		   log.info("SESSIONE CREATA");
			}else {
			log.error("NESSUN REPOSITORY PER I PARAMETRI PASSATI NON POSSO CREARE UNA SESSIONE  ATOMPUB_URL ["+ATOMPUB_URL+" ]");		
			}
		 if(session==null)
		  log.error("ERRORE NELLA CREAZIONE DI UNA SESSIONE  ATOMPUB_URL [ "+ATOMPUB_URL+" ]");		
		
		}catch (Exception e) {
			  log.error("ERRORE SESSIONE  ATOMPUB_URL [ "+ATOMPUB_URL+" ]",e);			 
		}
		
		 return session;

	}

	protected Session initConnectionDefault() {
		this.setCmisUserPassword(this.cmisUser, this.cmisPassword);
		return initConnection(this.cmisUser, this.cmisPassword);
	}

	public Session getSession() {
		return session == null ? initConnectionDefault() : session;
	}

	public Repository getRepository() {
		if (repository == null)
			initConnectionDefault();
		return repository;
	}

	public Document getDocument(String idObjOrPath) {
		CmisObject obj = getSession().getObject(getSession().createObjectId(idObjOrPath));
		if (obj instanceof Document) {
			return ((Document) obj);
		}
		return null;
	}

	public CmisObject getCmisObject(String idObjOrPath) {
		CmisObject obj = getSession().getObject(getSession().createObjectId(idObjOrPath));
		return obj;
	}

	public Folder getFolder(String idObjOrPath) {
		CmisObject obj = getSession().getObject(getSession().createObjectId(idObjOrPath));
		if (obj instanceof Folder) {
			return ((Folder) obj);
		}
		return null;
	}

	public Folder getFolderByPath(String path) {
		CmisObject obj = getSession().getObjectByPath(path);
		if (obj instanceof Folder) {
			return ((Folder) obj);
		}
		return null;
	}

	public List<CmisObject> getListCmisObject(String theQuery) {
		List<CmisObject> objList = new ArrayList<CmisObject>();
		ItemIterable<QueryResult> q = getSession().query(theQuery, false);
		log.info("***CMISAbstractConnect::queryList risultato per la query " + theQuery);
		for (QueryResult qr : q) {

			String objectId = "";
			PropertyData<?> propData = qr.getPropertyById("cmis:objectId"); // Atom Pub binding
			if (propData != null) {
				objectId = (String) propData.getFirstValue();
			} else {
				objectId = qr.getPropertyValueByQueryName("p.cmis:objectId"); // Web Services binding
			}
			CmisObject obj = session.getObject(session.createObjectId(objectId));
			
			objList.add(obj);
		}

		log.info("CMISAbstractConnect::queryList:size " + objList.size());

		return objList;
	}

	public ItemIterable<QueryResult> getQueryResult(String theQuery) {

		log.info("***CMISAbstractConnect::getQueryResult risultato per la query " + theQuery);
		ItemIterable<QueryResult> qresult = getSession().query(theQuery, false);
		
		log.info("CMISAbstractConnect::getQueryResult:TotalNumItem " + qresult.getTotalNumItems());

		return qresult;
	}

	public List<Document> getListDocument(String theQuery) {
		List<Document> objList = new ArrayList<Document>();
		ItemIterable<QueryResult> q = getSession().query(theQuery, false);
		log.info("***CMISAbstractConnect::getListDocument risultato per la query " + theQuery);
		for (QueryResult qr : q) {

			String objectId = "";
			PropertyData<?> propData = qr.getPropertyById("cmis:objectId"); // Atom Pub binding
			if (propData != null) {
				objectId = (String) propData.getFirstValue();
			} else {
				objectId = qr.getPropertyValueByQueryName("p.cmis:objectId"); // Web Services binding
			}
			CmisObject obj = session.getObject(session.createObjectId(objectId));
			if (obj instanceof Document) {
				objList.add((Document) obj);
			}
		}

		log.info("CMISAbstractConnect::getListDocument:size " + objList.size());

		return objList;
	}

	public List<Folder> getListFolder(String theQuery) {
		List<Folder> objList = new ArrayList<Folder>();
		ItemIterable<QueryResult> q = getSession().query(theQuery, false);
		log.info("***CMISAbstractConnect::getListFolder risultato per la query " + theQuery);
		for (QueryResult qr : q) {

			String objectId = "";
			PropertyData<?> propData = qr.getPropertyById("cmis:objectId"); // Atom Pub binding
			if (propData != null) {
				objectId = (String) propData.getFirstValue();
			} else {
				objectId = qr.getPropertyValueByQueryName("p.cmis:objectId"); // Web Services binding
			}
			CmisObject obj = session.getObject(session.createObjectId(objectId));
			if (obj instanceof Folder) {
				objList.add((Folder) obj);
			}

		}

		log.info("CMISAbstractConnect::getListFolder:size " + objList.size());

		return objList;
	}

	public Folder getCheckFolderRegistroProtocollo(Date data) throws Exception {
		 
		Folder folderPadre = getFolderByPath(getPathFolderRegistroProtocollo());
 
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dataReg = format.format(data);
		StringTokenizer tokenizer = new StringTokenizer(dataReg, "-");
		String anno = tokenizer.nextToken();
		String mese = tokenizer.nextToken();
		String giorno = tokenizer.nextToken();

		Folder folderGiorno = null;
		Folder folderAnno = null;
		Folder folderMese = null;
			
		try {
				folderAnno = (Folder) getFolderByPath(getPathFolderRegistroProtocollo() + "/" + anno);
				}catch (CmisObjectNotFoundException e) {
					folderAnno = parentCreateFolder(anno, folderPadre);
					log.error("CREO Folder folderAnno.", e.getMessage());
				}

				try {
				folderMese = (Folder) getFolderByPath(getPathFolderRegistroProtocollo() + "/" + anno + "/" + mese);
				}catch (CmisObjectNotFoundException e) {
					folderMese = parentCreateFolder(mese, folderAnno);
					log.error("CREO Folder folderMese.", e.getMessage());
				} 
				
				try {
				folderGiorno = (Folder) getFolderByPath(getPathFolderRegistroProtocollo() + "/" + anno + "/" + mese + "/" + giorno);
				}catch (CmisObjectNotFoundException e) {
					folderGiorno =  (Folder)parentCreateFolder(giorno, folderMese);
					log.error("CREO Folder folderGiorno.", e.getMessage());
				}

		return folderGiorno;
	}

	private Folder parentCreateFolder(String folderName, Folder folderParent) {

		Folder folder = null;
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put(PropertyIds.OBJECT_TYPE_ID, "cmis:folder");
		properties.put(PropertyIds.NAME, folderName);
		try {
			folder = folderParent.createFolder(properties);
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("METODO parentCreateFolder. ERRORE", e);
			}

		}
		return folder;
	}
	
	public String getPathFolderRegistroProtocollo() {
	return pathFolderRegistroProtocollo;
	}
	
	 
	public Document createDocumentRegistroProtocollo(Folder folder, BORegistroProtocollo regProtocollo) {
		return super.createDocumentRegistroProtocollo(folder, regProtocollo, getSession());
	}
	
}
