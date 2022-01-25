package it.eng.tz.area.vasta.protocollo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eng.tz.la.core.Settyngs;
import eng.tz.la.model.LogSettyng;

public class AuditCustomSettyng implements Settyngs{
	private static final Logger logger = LoggerFactory.getLogger(AuditCustomSettyng.class.getName());
	static AuditCustomSettyng customSettyng;
			   
	private String auditPath;
  
	public AuditCustomSettyng() {
		 this.auditPath=ApplicationContextUtil.getResource().getProperty("audit.base.path");
		 logger.info("AuditCustomSettyng-> auditPath == "+this.auditPath);
		  
	}
	
	@Override
	public void settyng(LogSettyng settyng) {
		if(this.auditPath!=null) {
		settyng.setPath_home(this.auditPath);
		}else {
		settyng.setPath_home(System.getProperty("user.dir")+"/audit-log");	
		}
		settyng.setLogName("Audit");
		
	}

	public static AuditCustomSettyng get() {
		if(customSettyng==null) {
			customSettyng= new AuditCustomSettyng();
			 
		}
		return customSettyng;
	}
}
