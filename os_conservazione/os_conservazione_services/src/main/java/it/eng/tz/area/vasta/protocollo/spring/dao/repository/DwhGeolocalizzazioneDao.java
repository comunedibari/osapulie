package it.eng.tz.area.vasta.protocollo.spring.dao.repository;


import static it.eng.tz.area.vasta.protocollo.utils.Utils.printTaskInfo;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;
import it.eng.tz.area.vasta.protocollo.spring.dao.models.DwhGeolocalizzazione;


@Repository
public class DwhGeolocalizzazioneDao {
	private static final Logger logger = LoggerFactory.getLogger(DwhGeolocalizzazioneDao.class);
	private static final String TASK_ID = "DWH_GEOLOCALIZZAZIONE_DAO";
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	@Qualifier("hikariDsOsapulie")
	private DataSource ds;
	@PostConstruct
	public void initialize()
	{
		if (ds == null)
		{
			throw new IllegalArgumentException("Impossibile costruire il repository. Datasource nullo");
		}
		jdbcTemplate = new NamedParameterJdbcTemplate(ds);
	}

	public int saveDwhGeolocalizzazione(DwhGeolocalizzazione geo) throws Exception{
		
		StopWatch sw = new StopWatch(TASK_ID);
		
		final String GEOLOCALIZZAZIONE = "INSERT INTO `tb_dwh_geolocalizzazione` \n" + 
				"( " + 
				"`INDIRIZZO_IP`," + 
				"`LATITUDINE`," + 
				"`LONGITUDINE`," + 
				"`CITTA`," + 
				"`REGIONE`," + 
				"`CAP`," + 
				"`IS_EUROPEO`," + 
				"`COD_SERVIZIO`," + 
				"`COD_USER`," + 
				"`DATA_CREAZIONE`," + 
				"`UUID_OPERAZIONE` ) VALUES ( " + 
				":INDIRIZZO_IP," + 
				":LATITUDINE, " + 
				":LONGITUDINE," + 
				":CITTA," + 
				":REGIONE," + 
				":CAP," + 
				":IS_EUROPEO," + 
				":COD_SERVIZIO," + 
				":COD_USER," + 
				":DATA_CREAZIONE," + 
				":UUID_OPERAZIONE );";
		try {
		
		sw.start("QUERY INSERIMENTO DWH GEOLOCALIZZAZIONE");
			
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("INDIRIZZO_IP", geo.getIndirizzo_ip());
		parameters.put("LATITUDINE", geo.getLatitudine());
		parameters.put("LONGITUDINE", geo.getLongitudine());
		parameters.put("CITTA", geo.getCitta());
		parameters.put("REGIONE", geo.getRegione());
		parameters.put("CAP", geo.getCap());
		parameters.put("IS_EUROPEO", geo.isIs_europeo());
		parameters.put("COD_SERVIZIO", geo.getCod_servizio());
		parameters.put("COD_USER", geo.getCod_user());
		parameters.put("DATA_CREAZIONE", geo.getData_creazione());
		parameters.put("UUID_OPERAZIONE", geo.getUuid_operazione());
		sw.stop();
		 
		return jdbcTemplate.update(GEOLOCALIZZAZIONE,parameters);
		
		}catch (Exception e)
		{ 
			String msg = "saveAttributeServizio Errore INSERT TB_DWH_GEOLOCALIZZAZIONE["+GEOLOCALIZZAZIONE+"]";
			logger.error(msg,e);
			throw new Exception(msg, e);
		}finally 
		{
			boolean forcedStop = false;
			if( sw.isRunning() )
			{
				sw.stop();
				forcedStop = true;
			}
			printTaskInfo(logger, sw, forcedStop, null);
		}		
		
	  } 
 
	
}