package it.eng.tz.area.vasta.documentale.cmis.config;



public final class PropertyCmis {

	private PropertyCmis() {}

	public static final String NAME = "cmis:name";
	public static final String OBJECT_ID = "cmis:objectId";
	public static final String OBJECT_TYPE_ID = "cmis:objectTypeId";
	public static final String BASE_TYPE_ID = "cmis:baseTypeId";
	public static final String CREATED_BY = "cmis:createdBy";
	public static final String CREATION_DATE = "cmis:creationDate";
	public static final String LAST_MODIFIED_BY = "cmis:lastModifiedBy";
	public static final String LAST_MODIFICATION_DATE = "cmis:lastModificationDate";
	public static final String CHANGE_TOKEN = "cmis:changeToken";
	public static final String PATH = "cmis:path";
	

	public static final String DOCUMENT = "cmis:document";
	public static final String IS_IMMUTABLE = "cmis:isImmutable";
	public static final String IS_LATEST_VERSION = "cmis:isLatestVersion";
	public static final String IS_MAJOR_VERSION = "cmis:isMajorVersion";
	public static final String IS_LATEST_MAJOR_VERSION = "cmis:isLatestMajorVersion";
	public static final String VERSION_LABEL = "cmis:versionLabel";
	public static final String VERSION_SERIES_ID = "cmis:versionSeriesId";
	public static final String IS_VERSION_SERIES_CHECKED_OUT = "cmis:isVersionSeriesCheckedOut";
	public static final String VERSION_SERIES_CHECKED_OUT_BY = "cmis:versionSeriesCheckedOutBy";
	public static final String VERSION_SERIES_CHECKED_OUT_ID = "cmis:versionSeriesCheckedOutId";
	public static final String CHECKIN_COMMENT = "cmis:checkinComment";
	public static final String CONTENT_STREAM_LENGTH = "cmis:contentStreamLength";
	public static final String CONTENT_STREAM_MIME_TYPE = "cmis:contentStreamMimeType";
	public static final String CONTENT_STREAM_FILE_NAME = "cmis:contentStreamFileName";
	public static final String CONTENT_STREAM_ID = "cmis:contentStreamId";
	
	
	public static final String FOLDER = "cmis:folder";
	public static final String PARENT_ID = "cmis:parentId";
	public static final String ALLOWED_CHILD_OBJECT_TYPE_IDS = "cmis:allowedChildObjectTypeIds";
	
	
	public static final String RELATIONSHIP = "cmis:relationship";
	public static final String SOURCE_ID = "cmis:sourceId";
	public static final String TARGET_ID = "cmis:targetId";
	

	public static final String POLICY = "cmis:policy";
	public static final String POLICY_TEXT = "cmis:policyText";
	
	
	public static final String F21_DOCUMENTO_NAMESPACE_QNAME = "http://www.frontiere21.com/model/content/1.0";
	public static final String F21_DOCUMENTO_NAMESPACE = "D:f21:";
	public static final String F21_DOCUMENTO_TYPE = "D:f21:Documento";
	public static final String F21_DOCUMENTO_NAME = "Documento";
	public static final String F21_DOCUMENTO_DATA = "f21:Data";
	public static final String CMIS_DOCUMENT_PREFIX = "D:";
	

	public static final String F21_FOLDER_NAMESPACE_QNAME = "http://www.frontiere21.com/model/f21/folder/1.0";
	public static final String F21_FOLDER_NAMESPACE = "F:f21folder:";
	public static final String F21_BASEFOLDER_TYPE = "F:f21folder:BaseF21Folder";
	public static final String F21_FOLDER_NAME = "BaseF21Folder";
	public static final String F21_PRATICA_TYPE = "F:f21folder:Pratica";
	public static final String F21_PRATICA_NAME = "Pratica";
	public static final String CMIS_FOLDER_PREFIX = "F:";
	

	public static final String CMIS_POLICY_PREFIX = "P:";
	

	public static final String CMIS_RELATIONSHIP_PREFIX = "R:";
	


}
