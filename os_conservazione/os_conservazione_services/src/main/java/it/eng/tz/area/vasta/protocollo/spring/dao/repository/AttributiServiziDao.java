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
import it.eng.tz.area.vasta.protocollo.spring.dao.models.AttributiServizi;


@Repository
public class AttributiServiziDao {
	private static final Logger logger = LoggerFactory.getLogger(AttributiServiziDao.class);
	private static final String TASK_ID = "ATTRIBUTI_SERVIZI_DAO";
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

	public int saveAttributeServizio(AttributiServizi m) throws Exception{
		
		StopWatch sw = new StopWatch(TASK_ID);
		
		final String ATTRIBUTI_SERVIZI = "INSERT INTO `tb_attributi_servizio` (`id`, `nome_attributo`, `valore_attributo`, "
				+ "`id_transazione`, `data_creazione`, `id_servizio`) "
				+ " VALUES (:id, "
				+ ":nome_attributo, "
				+ ":valore_attributo, "
				+ ":id_transazione, "
				+ ":data_creazione, "
				+ ":id_servizio);";
		try {
			sw.start("QUERY INSERIMENTO ATTRIBUTI_SERVIZIO");
			
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("id", m.getId());
		parameters.put("nome_attributo", m.getNomeAttributo());
		parameters.put("valore_attributo", m.getValoreAttributo());
		parameters.put("id_transazione", m.getIdTransazione());
		parameters.put("data_creazione", m.getDataCreazione());
		parameters.put("id_servizio", m.getIdServizio());

		 sw.stop();
		 
		return jdbcTemplate.update(ATTRIBUTI_SERVIZI,parameters);
		
		}catch (Exception e)
		{ 
			String msg = "saveAttributeServizio Errore INSERT TB_ATTRIBUTI_SERVIZIO["+ATTRIBUTI_SERVIZI+"]";
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