package it.eng.tz.area.vasta.protocollo.utils;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;

public abstract class Utils
{
	private static final Logger logger = LoggerFactory.getLogger(Utils.class.getName());
	private static final DateTimeZone EUROPE_ROME_TIME_ZONE = DateTimeZone.forID("Europe/Rome");
	private static final TimeZone DEFAULT_EUROPE_ROME_TIME_ZONE = TimeZone.getTimeZone("Europe/Rome");
	public static String formatDateTime(DateTime dt, String pattern)
	{
		if( !StringUtils.hasText(pattern) )
		{
			pattern = "dd/MM/yyyy";
		}
		DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern).withZone(EUROPE_ROME_TIME_ZONE);
		String result = formatter.print(dt);
		if( logger.isTraceEnabled() )
		{
			logger.trace("DATETIME [{}] STRINGA COSTRUITA [{}]", dt, result);
		}		
		return result;
	}
	public static DateTime todayDateTime()
	{

		DateTime result = new DateTime(EUROPE_ROME_TIME_ZONE);
		if( logger.isTraceEnabled() )
		{
			logger.trace("DATA COSTRUITA [{}]", result);
		}
		return result;
	}	
	public static DateTime toDateTime(Long instant)
	{
		if( instant <= 0 )
		{
			throw new IllegalArgumentException("Passata una data in millisecondi non valida <"+instant+">");
		}
		DateTime result = new DateTime(instant, EUROPE_ROME_TIME_ZONE);
		if( logger.isTraceEnabled() )
		{
			logger.trace("DATA IN MILLISECONDI [{}] DATA COSTRUITA [{}]", instant, result);
		}
		return result;
	}
	public static DateTime toDateTime(String data, String pattern)
	{
		if( !StringUtils.hasText(data) )
		{
			throw new IllegalArgumentException("Passata una stringa rappresentante la data nulla o vuota <"+data+">");
		}
		if( !StringUtils.hasText(pattern) )
		{
			throw new IllegalArgumentException("Passata una stringa rappresentante il pattern nulla o vuota <"+pattern+">");
		}
		DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern).withZone(EUROPE_ROME_TIME_ZONE);
		DateTime result = formatter.parseDateTime(data);

		if( logger.isTraceEnabled() )
		{
			logger.trace("STRINGA RAPPRESENTANTE LA DATA [{}] DATA COSTRUITA [{}]", data, pattern);
		}
		return result;	
	}
	public static LocalDateTime toLocalDateTime(String data, String pattern)
	{
		if( !StringUtils.hasText(data) )
		{
			throw new IllegalArgumentException("Passata una stringa rappresentante la data nulla o vuota <"+data+">");
		}
		if( !StringUtils.hasText(pattern) )
		{
			throw new IllegalArgumentException("Passata una stringa rappresentante il pattern nulla o vuota <"+pattern+">");
		}
		DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
		DateTime result = formatter.parseDateTime(data);
	
		if( logger.isTraceEnabled() )
		{
			logger.trace("STRINGA RAPPRESENTANTE LA DATA [{}] DATA COSTRUITA [{}]", data, pattern);
		}
		return result.toLocalDateTime();	
	}	
	public static Date toDate(String data, String pattern)
	{
		if( !StringUtils.hasText(data) )
		{
			throw new IllegalArgumentException("Passata una stringa rappresentante la data nulla o vuota <"+data+">");
		}
		if( !StringUtils.hasText(pattern) )
		{
			throw new IllegalArgumentException("Passata una stringa rappresentante il pattern nulla o vuota <"+pattern+">");
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		sdf.setLenient(false);
		try 
		{


			Date result = sdf.parse(data);
			
			if( logger.isTraceEnabled() )
			{
				logger.trace("STRINGA RAPPRESENTANTE LA DATA [{}] DATA COSTRUITA [{}]", data, pattern);
			}
			return result;
		} catch (Exception e) {
			logger.error("Errore nel parsing della data  {}", e.getMessage(), e);
			throw new IllegalStateException("Errore nel parsing della data; "+ e.getMessage());
		}
	}
	public static XMLGregorianCalendar dateTOXMLGregorianCalendar(DateTime date) throws DatatypeConfigurationException
	{

		GregorianCalendar gc = new GregorianCalendar();
		gc.setTimeZone(DEFAULT_EUROPE_ROME_TIME_ZONE);
		gc.setTime(date.toDate());
		XMLGregorianCalendar result = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
		if( logger.isTraceEnabled() )
		{
			logger.trace("DATA INPUT [{}] TIMEZONE UTILIZZATO [{}] XML GREGORIAN CALENDAR COSTRUITO [{}]", date, gc.getTimeZone(), result);

		}
		return result;
	}
	public static Date asDate(XMLGregorianCalendar xmlGC)
	{
		if (xmlGC == null)
		{
			return null;
		}
		else
		{
			GregorianCalendar gc = xmlGC.toGregorianCalendar();
			gc.setTimeZone(DEFAULT_EUROPE_ROME_TIME_ZONE);
			Date result = gc.getTime();
			if( logger.isTraceEnabled() )
			{
				logger.trace("XML GREGORIAN CALENDAR [{}] DATE [{}] DEFAULT TIMEZONE {}", xmlGC, result, DEFAULT_EUROPE_ROME_TIME_ZONE);
			}
			return result;
		}
	}
	public static DateTime asDateTime( XMLGregorianCalendar xmlGC )
	{
		if( null == xmlGC )
		{
			return null;
		}
		else
		{

			DateTime result = new DateTime(xmlGC.toGregorianCalendar().getTime(), EUROPE_ROME_TIME_ZONE);
			if( logger.isTraceEnabled() )
			{
				logger.trace("XML GREGORIAN CALENDAR [{}] DATE TIME[{}] DEFAULT TIMEZONE {}", xmlGC, result, EUROPE_ROME_TIME_ZONE);
			}
			return result;
		}
	}
	public static String formatXmlGregorianCalendar( XMLGregorianCalendar xmlGC, String pattern )
	{
		if( null == xmlGC )
		{
			return null;
		}
		if( !StringUtils.hasText(pattern) )
		{
			return null;
		}
		DateTimeFormatter dtf = DateTimeFormat.forPattern(pattern);
		DateTime dt = asDateTime(xmlGC);
		String result = dtf.print(dt);
		if( logger.isTraceEnabled() )
		{
			logger.trace("XML GREGORIAN CALENDAR [{}] PATTERN [{}] RESULT [{}]",xmlGC, pattern, result);
		}
		return result;
	}
	public static void printTaskInfo( Logger logger, StopWatch sw, boolean forcedStop, String customMessage )
	{
		String message = "["+ (StringUtils.hasText(sw.getLastTaskName()) ? sw.getLastTaskName().toUpperCase() : "TASK SENZA NOME")+"] ESEGUITO IN ["+sw.getLastTaskTimeMillis()+"] MILLISECONDI; "+sw.toString();
		if( forcedStop )
		{
			message += " TASK CHIUSO FORZATAMENTE";
		}
		if( sw.isRunning() )
		{
			message += " TASK IN ESECUZIONE; CHIUSURA DEL TASK NEL METODO PRINTTASKINFO";
			sw.stop();
		}
		if( StringUtils.hasText(customMessage) )
		{
			message += " "+customMessage.toUpperCase();
		}
		if( sw.getLastTaskTimeMillis() > 1000 )
		{
			if (logger.isInfoEnabled())
			{

				logger.info(message);
			}
		}
		else
		{
			if (logger.isDebugEnabled())
			{

				logger.debug(message);
			}
		}
	}	
	public static void deleteFile(String toDelete)
	{
		try
		{
			FileUtils.forceDelete(new File(toDelete));
		}
		catch (Exception e)
		{
			logger.warn("Errore nella cancellazione del file {}, {}", toDelete, e.getMessage());
		}
	}	
}