package it.audit.reflection.test;

import java.util.HashMap;

import eng.tz.la.annotation.AuditClass;
import eng.tz.la.annotation.AuditMethod;

@AuditClass
public class BeanUno {
 
	private String 
	a="Uno-aaaa",
	b="Uno-bbbb",
	d="Uno-cccc",
	c="Uno-dddd";

	@AuditMethod
	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}
	@AuditMethod
	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}
	@AuditMethod
	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}
	@AuditMethod
	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}
	
	@AuditMethod(key="HASH-MAP")
	public HashMap<String, Utente> getHashMap(){
		HashMap<String, Utente> mp= new HashMap<String, Utente>();
		mp.put("key3",new Utente("maputente","cognomeMap","897987"));
		mp.put("key2",new Utente("mapuGioglio","UmbertoMap","657857"));
		mp.put("key1",new Utente("maPeppe","LoppieMap","453545"));
		return mp;
	}
	
	
	
	
}
