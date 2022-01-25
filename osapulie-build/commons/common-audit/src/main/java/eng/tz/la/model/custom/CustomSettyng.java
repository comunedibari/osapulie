package eng.tz.la.model.custom;

import eng.tz.la.core.Settyngs;
import eng.tz.la.model.LogSettyng;

public class CustomSettyng implements Settyngs {

	
	public void settyng(LogSettyng settyng) {
		settyng.setPath_home(System.getProperty("user.dir")+"/audit-log");
		settyng.setEncryptExtension(".ckr");
		settyng.setLogName("Audit");
		settyng.setPrintType(false);
	}

}
