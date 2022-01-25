package it.osapulie.util.audit;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import eng.tz.la.core.IMetaActor;
import it.osapulie.infrastructure.security.OSApulieUserDetails;




public class AuditCustomMeta implements IMetaActor {

	@Override
	public String getActor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			OSApulieUserDetails user = (OSApulieUserDetails) authentication.getPrincipal();
			return user.getUsername();
		}

		return "anonimus";
	}

	@Override
	public String getOrigin() {
		 
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
	 
		}
		return "origin";
	}

	public static AuditCustomMeta get() {
		return new AuditCustomMeta();
	}
	
	@Override
	public String toPrint() {
		return "" + (getOrigin() != null ? "Origin==>" + getOrigin() : "") 
				+ (getActor() != null ? " Actor==>" + getActor() + " " : "") + "|";
	}
	
}
