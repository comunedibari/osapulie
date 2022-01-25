package it.osapulie.pratiche.web.portlet.varie;

import it.osapulie.pratiche.web.ws.types.DocInfo;

import java.io.File;
import java.io.Serializable;


/**
 * Classe che descrive 
 * @author Maria Michela Birtolo
 * 
 */
public class UploadItemPratiche implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private File generatedFile;
	
	private DocInfo documento;
	
	/**
	 * @return the generatedFile
	 */
	public File getGeneratedFile() {
		return generatedFile;
	}

	/**
	 * @param generatedFile the generatedFile to set
	 */
	public void setGeneratedFile( File generatedFile ) {
		this.generatedFile = generatedFile;
	}

	/**
	 * @return the documento
	 */
	public DocInfo getDocumento() {
		return documento;
	}

	/**
	 * @param documento the documento to set
	 */
	public void setDocumento( DocInfo documento ) {
		this.documento = documento;
	}

	
}
