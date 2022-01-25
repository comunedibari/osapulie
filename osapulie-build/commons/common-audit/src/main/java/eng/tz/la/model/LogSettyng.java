/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eng.tz.la.model;

import java.io.File;
import java.io.Serializable;

/**
 *
 * @author s.mariniello
 */
public class LogSettyng implements Serializable {

	private static final long serialVersionUID = 4036875535529585611L;
	private String path_home = System.getProperty("user.dir") + File.separator+"audit-log"+ File.separator;
	private String logName = "Audit";
	private String logExtension = ".txt";
	private String logFileExtension = ".file";
	private String encryptExtension = ".ckr";
	private String conservazioneFolderName = "conservazione-folder";
	private String conservazionePath = System.getProperty("user.dir") + File.separator;
	private boolean printType;
	
	public String getPath_home() {
		return path_home;
	}

	public void setPath_home(String path_home) {
		this.path_home = path_home;
	}

	public String getLogName() {
		return logName;
	}

	public void setLogName(String logName) {
		this.logName = logName;
	}

	public String getLogExtension() {
		return logExtension;
	}

	public void setLogExtension(String logExtension) {
		this.logExtension = logExtension;
	}

	public String getLogFileExtension() {
		return logFileExtension;
	}

	public void setEncryptExtension(String encryptExtension) {
		this.encryptExtension = encryptExtension;
	}

	public String getEncryptExtension() {
		return encryptExtension;
	}

	public void setConservazioneFolderName(String conservazioneFolderName) {
		this.conservazioneFolderName = conservazioneFolderName;
	}
	public String getConservazioneFolderName() {
		return conservazioneFolderName;
	}
	
	public void setConservazionePath(String conservazionePath) {
		this.conservazionePath = conservazionePath;
	}
	public String getConservazionePath() {
		return conservazionePath;
	}
	
	public String getConservazionePathComplete() {
		if(!conservazionePath.endsWith(File.separator))
			conservazionePath+=File.separator;
		conservazioneFolderName.replace("\\/", "");
		return conservazionePath +conservazioneFolderName+ File.separator;
	}
	
	public void setPrintType(boolean printType) {
		this.printType = printType;
	}
	
	public boolean isPrintType() {
		return printType;
	}
}
