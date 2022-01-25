package it.osapulie.sociali.web.portlet.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {

	/**
	 * Restituisce la data nel formato GG/MM/AAAA
	 * 
	 * @param data
	 * @return String
	 */
	public static String getDataGGMMAAAA( Date data ) {
		if (data == null) {
			return "";
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy" );
			String dataString = sdf.format( data );
			return dataString;
		}
	}
	
}
