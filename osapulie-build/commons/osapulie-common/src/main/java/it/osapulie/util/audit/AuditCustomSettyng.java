package it.osapulie.util.audit;


import eng.tz.la.core.Settyngs;
import eng.tz.la.model.LogSettyng;

public class AuditCustomSettyng implements Settyngs{
	static AuditCustomSettyng customSettyng;
			   
	private String auditPath;
  
	public AuditCustomSettyng() {
		 this.auditPath=ApplicationContextUtil.getResource().getProperty("audit.base.path");
		 System.out.println("AUDIT-PATH = "+this.auditPath);
		  
	}
	
	@Override
	public void settyng(LogSettyng settyng) {
		if(auditPath!=null) {
		settyng.setPath_home(auditPath);
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
