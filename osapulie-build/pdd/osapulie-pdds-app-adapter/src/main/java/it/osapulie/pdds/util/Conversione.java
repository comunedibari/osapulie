package it.osapulie.pdds.util;

public class Conversione {

	public String trasforma(double a) {
		String aa = "";
		a = a * 1;
		aa = String.valueOf(a);
		int s = aa.indexOf('.');
		aa = aa.substring(0, s);
		return aa;
	}
}
