package eng.tz.la.model.custom;

import java.net.InetAddress;
import java.net.UnknownHostException;

import eng.tz.la.core.IMetaActor;

public class AuditMetaActor implements IMetaActor {

	 
	public String getActor() {
		 
		return "anonimus";
	}

	 
	public String getOrigin() {
		 
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {}
		  catch (Exception e) {}
		return "origin";
	}

	@Override
	public String toString() {
		return toPrint();
	}

	
	 
	public String toPrint() {
		return "" + (getOrigin() != null ? "Origin==>" + getOrigin() : "") 
				+ (getActor() != null ? " Actor==>" + getActor() + " " : "") + "|";
	}
}
