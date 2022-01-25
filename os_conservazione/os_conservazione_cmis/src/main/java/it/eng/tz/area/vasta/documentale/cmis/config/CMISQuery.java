package it.eng.tz.area.vasta.documentale.cmis.config;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CMISQuery {

private static final Logger log = LoggerFactory.getLogger(CMISQuery.class.getName());
	
	
public static final String SEARCH_ALL_REGISTRO_DI_PROTOCOLLO="SELECT p.*, c.* FROM cmis:document as p join cons:conservazione as c on c.cmis:objectId=p.cmis:objectId";

public static String SEARCH_FROM_REGISTRO_DI_PROTOCOLLO(String from) {
	String searchFrom="'registro_protocollo_"+from+"%'";
	return "SELECT c.*, p.* FROM cons:conservazione as c join regProt:registroProtocollo as p on c.cmis:objectId=p.cmis:objectId where p.regProt:filename LIKE "+searchFrom;
}
public static String SEARCH_REGISTRO_DI_PROTOCOLLO(String name) {
	return "SELECT * FROM cmis:document where cmis:name = "+"'"+name+"'";
}
//AND d.cmis:objectId IN
public static String SEARCH_FROM_TO_REGISTRO_DI_PROTOCOLLO(String from,String to) {
	
	String queryString = "select c.*, p.* from cons:conservazione as c join regProt:registroProtocollo as p on c.cmis:objectId = p.cmis:objectId " +  
            "where p.cmis:creationDate >= TIMESTAMP "+"'"+from+"T00:00:00.000-00:00' " +
            "  and p.cmis:creationDate <= TIMESTAMP "+"'"+to+"T23:59:00.000-00:00'";
	return queryString;
}

public static String SEARCH_FROM_TO_REGISTRO_DI_PROTOCOLLO_IN(List<String> dates) {
	String searchFrom=creaStringParameterQuery_IN(dates);
	return "SELECT c.*, p.* FROM cons:conservazione as c join regProt:registroProtocollo as p on c.cmis:objectId=p.cmis:objectId where p.regProt:dataPrimaRegistrazioneEffettuataSulRegistro IN "+searchFrom;
}

public static String creaStringParameterQuery_IN(List<String> dateString) {
	StringBuilder sb = new StringBuilder("(");
	for (int i = 0; i < dateString.size(); i++) {
		sb.append("'");
		sb.append(dateString.get(i));
		sb.append("'");
		if (i >= dateString.size() - 1) { break; }
		sb.append(",");
	}
	sb.append(")");
	return sb.toString();
}

public static List<String> getListDateQuery_IN(Date from,Date to) {
	 
	List<String> giornis=new ArrayList<String>();
	Calendar calFrom = Calendar.getInstance ();
	calFrom.setTime(from);
	int year = calFrom.get(Calendar.YEAR);
	int month = calFrom.get(Calendar.MONTH)+1;
	int day = calFrom.get(Calendar.DAY_OF_MONTH);
	
	giornis.add(year+"-"+convalidaMeseGiorno(month)+"-"+convalidaMeseGiorno(day));
	
	Calendar calTo = Calendar.getInstance ();
	calTo.setTime(to);
	
	int yearF = calTo.get(Calendar.YEAR);
	int monthF = calTo.get(Calendar.MONTH)+1;
	int dayF = calTo.get(Calendar.DAY_OF_MONTH);
	
	int count=0;
	for(;;) {
		count++;

		calFrom = Calendar.getInstance ();
		calFrom.setTime(from);
		calFrom.add(Calendar.DATE , count);
		
	if(yearF==year&&monthF==month&&dayF==day)
			return giornis; 	
	
	if(calFrom.getTime().compareTo(calTo.getTime())==0) {
		log.debug("METHODO::getStringQuery_IN() - La data FROM e uguale alla data TO Stop cliclo.");
		break;
			
		}
		if(calFrom.getTime().compareTo(calTo.getTime())<0) {
	   log.debug("METHODO::getStringQuery_IN() - Lq data FROM e precedente alla data TO  continuo il cliclo.");
	
		year = calFrom.get(Calendar.YEAR);
		month = calFrom.get(Calendar.MONTH)+1;
		day = calFrom.get(Calendar.DAY_OF_MONTH);
		
		giornis.add(year+"-"+convalidaMeseGiorno(month)+"-"+convalidaMeseGiorno(day));
		
		}
		if(calFrom.getTime().compareTo(calTo.getTime())>0) {
			log.debug("METHODO::getStringQuery_IN() -  La data FROM e successiva alla data TO Stop cliclo.");
			giornis.add(year+"-"+convalidaMeseGiorno(month)+"-"+convalidaMeseGiorno(day));
			break;
		}
		  
	}
	
 
 
	giornis.add(yearF+"-"+convalidaMeseGiorno(monthF)+"-"+convalidaMeseGiorno(dayF));
	
	return giornis;
}
private static String convalidaMeseGiorno(int mg) {
	if(mg<10) {
		return "0"+mg;
	}
	return ""+mg;
}

} 
