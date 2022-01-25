package eng.tz.pa.api.osa.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {
	
	public static String getFormattedDate(Date paramDate, String paramString)
	  {
	    String str = "";
	    if (null != paramDate)
	    {
	      SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(paramString);
	      str = localSimpleDateFormat.format(paramDate);
	    }
	    return str;
	  }
	  
	  public static Date parseDate(String paramString1, String paramString2)
	  {
	    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(paramString2);
	    Date localDate = null;
	    try
	    {
	      if ((null != paramString1) && (paramString1.length() > 0)) {
	        localDate = localSimpleDateFormat.parse(paramString1);
	      }
	    }
	    catch (Exception localParseException)
	    {
	      
	    }
	    return localDate;
	  }
	   
}
