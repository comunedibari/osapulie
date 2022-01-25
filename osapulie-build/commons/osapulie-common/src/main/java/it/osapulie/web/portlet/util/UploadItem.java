package it.osapulie.web.portlet.util;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * Classe che descrive 
 * @author Maria Michela Birtolo
 * 
 */
public class UploadItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean signedFiles;

	private MultipartFile generatedFile;
	private List<MultipartFile> multipartFiles;

	public List<MultipartFile> getMultipartFiles() {
		return multipartFiles;
	}

	public void setMultipartFiles(List<MultipartFile> multipartFiles) {
		this.multipartFiles = multipartFiles;
	}

	public boolean isSignedFiles() {
		return signedFiles;
	}

	public void setSignedFiles(boolean signedFiles) {
		this.signedFiles = signedFiles;
	}

	public MultipartFile getGeneratedFile() {
		return generatedFile;
	}

	public void setGeneratedFile(MultipartFile generatedFile) {
		this.generatedFile = generatedFile;
	}
}
