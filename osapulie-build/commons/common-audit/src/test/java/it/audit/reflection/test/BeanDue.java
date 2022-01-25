package it.audit.reflection.test;

import eng.tz.la.annotation.AuditClass;
import eng.tz.la.annotation.AuditField;
@AuditClass
public class BeanDue {
	
	@AuditField
	private String 
	a="Due-aaaa",
	b="Due-bbbb",
	d="Due-cccc",
	c="Due-dddd";

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

}
