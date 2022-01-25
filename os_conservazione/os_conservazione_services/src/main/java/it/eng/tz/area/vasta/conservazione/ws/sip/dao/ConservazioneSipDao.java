package it.eng.tz.area.vasta.conservazione.ws.sip.dao;

import static it.eng.tz.area.vasta.protocollo.utils.Utils.printTaskInfo;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

import it.eng.tz.area.vasta.conservazione.ws.exception.ConservazioneSipException;
import it.eng.tz.area.vasta.conservazione.ws.sip.dao.model.ConservazioneSipModel;

@Repository
public class ConservazioneSipDao {
	private static final Logger logger = LoggerFactory.getLogger(ConservazioneSipDao.class);
	private static final String TASK_ID = "CONSERVAZIONE SIP DAO";
	private NamedParameterJdbcTemplate jdbcTemplate;
	@Autowired
	@Qualifier("dsConservazione")
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
	public void salvaConservazioneSip(ConservazioneSipModel model) throws ConservazioneSipException 
	{
		StopWatch sw = new StopWatch(TASK_ID);
		StringBuilder sb = new StringBuilder("INSERT INTO conservazione_sip.conservazione_sip ");
		sb.append(" (id_conservazione, processato, errore, sip_result_xml, data_processamento, data_creazione, folder) VALUES ");
		sb.append("(:id_conservazione, :processato, :errore, :sip_result_xml, :data_processamento, :data_creazione, :folder )");
		Map<String, Object> params = new HashMap<>();
		params.put("id_conservazione", model.getId());
		params.put("processato", model.isProcessato());
		params.put("errore", model.isErrore());
		params.put("sip_result_xml", model.getSipResultXml());
		params.put("data_processamento", model.getDataProcessamento());
		params.put("data_creazione", new Date());
		params.put("folder", model.getFolder());
		try 
		{
			sw.start("INSERIMENTO RECORD "+model.getId());
			int result = jdbcTemplate.update(sb.toString(), params);
			sw.stop();
			if( logger.isDebugEnabled() )
			{
				logger.debug("SQL [{}] PARAMETRI [{}] RECORD AGGIORNATI [{}]", sb.toString(), params, result);
			}			

		}
		catch (Exception e)
		{
			String msg = "Errore salvataggio record conservazione "+model.getId();
			logger.error(msg,e);
			throw new ConservazioneSipException(msg, e);
		}
		finally 
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
	public void aggiornaConservazioneSip(ConservazioneSipModel model) throws ConservazioneSipException 
	{
		StopWatch sw = new StopWatch(TASK_ID);
		StringBuilder sb = new StringBuilder("UPDATE conservazione_sip.conservazione_sip ");
		sb.append(" set processato = :processato, errore = :errore, sip_result_xml = :sip_result_xml, data_processamento = :data_processamento ");
		sb.append("WHERE id_conservazione = :id_conservazione");
		Map<String, Object> params = new HashMap<>();
		params.put("id_conservazione", model.getId());
		params.put("processato", model.isProcessato());
		params.put("errore", model.isErrore());
		params.put("sip_result_xml", model.getSipResultXml());
		params.put("data_processamento", model.getDataProcessamento());
		params.put("data_creazione", new Date());
		
		try 
		{
			sw.start("UPDATE RECORD "+model.getId());
			int result = jdbcTemplate.update(sb.toString(), params);
			sw.stop();
			if( logger.isDebugEnabled() )
			{
				logger.debug("SQL [{}] PARAMETRI [{}] RECORD AGGIORNATI [{}]", sb.toString(), params, result);
			}			

		}
		catch (Exception e)
		{
			String msg = "Errore aggiornameto record conservazione "+model.getId();
			logger.error(msg,e);
			throw new ConservazioneSipException(msg, e);
		}
		finally 
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
	public List<String> recuperaDaProcesare() throws ConservazioneSipException{
		StopWatch sw = new StopWatch(TASK_ID);
		StringBuilder sb = new StringBuilder("SELECT id_conservazione FROM conservazione_sip.conservazione_sip ");
		sb.append("WHERE processato = false");
		try 
		{
			Map<String, Object> params = null;
			sw.start("RECUPERO ITEM DA PROCESSARE");
			List<String> result = jdbcTemplate.queryForList(sb.toString(), params, String.class);
			sw.stop();
			if( logger.isDebugEnabled() )
			{
				logger.debug("SQL [{}] PARAMETRI [{}] RECORD RECUPERATI [{}]", sb.toString(), params, result);
			}			
			return result;
		}
		catch (Exception e)
		{
			String msg = "Errore recupero record da processare ";
			logger.error(msg,e);
			throw new ConservazioneSipException(msg, e);
		}
		finally 
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
	
	public List<String> recuperaAllFolder() throws ConservazioneSipException{
		StopWatch sw = new StopWatch(TASK_ID);
		StringBuilder sb = new StringBuilder("SELECT folder FROM conservazione_sip.conservazione_sip WHERE folder IS NOT NULL AND folder!=''");
		try 
		{
			Map<String, Object> params = null;
			sw.start("RECUPERO ITEM DA PROCESSATI");
			List<String> result = jdbcTemplate.queryForList(sb.toString(), params, String.class);
			sw.stop();
			if( logger.isDebugEnabled() )
			{
				logger.debug("SQL [{}] PARAMETRI [{}] RECORD RECUPERATI [{}]", sb.toString(), params, result);
			}			
			return result;
		}
		catch (Exception e)
		{
			String msg = "Errore recupero record da processare ";
			logger.error(msg,e);
			throw new ConservazioneSipException(msg, e);
		}
		finally 
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
	
	public List<String> recuperaFolderProcessati() throws ConservazioneSipException{
		StopWatch sw = new StopWatch(TASK_ID);
		StringBuilder sb = new StringBuilder("SELECT folder FROM conservazione_sip.conservazione_sip ");
		sb.append("WHERE processato = true AND folder IS NOT NULL AND folder!=''");
		try 
		{
			Map<String, Object> params = null;
			sw.start("RECUPERO ITEM DA PROCESSATI");
			List<String> result = jdbcTemplate.queryForList(sb.toString(), params, String.class);
			sw.stop();
			if( logger.isDebugEnabled() )
			{
				logger.debug("SQL [{}] PARAMETRI [{}] RECORD RECUPERATI [{}]", sb.toString(), params, result);
			}			
			return result;
		}
		catch (Exception e)
		{
			String msg = "Errore recupero record da processare ";
			logger.error(msg,e);
			throw new ConservazioneSipException(msg, e);
		}
		finally 
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
	
	public List<String> recuperaFolderDaProcessare() throws ConservazioneSipException{
		StopWatch sw = new StopWatch(TASK_ID);
		StringBuilder sb = new StringBuilder("SELECT folder FROM conservazione_sip.conservazione_sip ");
		sb.append("WHERE processato = false AND folder IS NOT NULL AND folder!=''");
		try 
		{
			Map<String, Object> params = null;
			sw.start("RECUPERO ITEM DA PROCESSATI");
			List<String> result = jdbcTemplate.queryForList(sb.toString(), params, String.class);
			sw.stop();
			if( logger.isDebugEnabled() )
			{
				logger.debug("SQL [{}] PARAMETRI [{}] RECORD RECUPERATI [{}]", sb.toString(), params, result);
			}			
			return result;
		}
		catch (Exception e)
		{
			String msg = "Errore recupero record da processare ";
			logger.error(msg,e);
			throw new ConservazioneSipException(msg, e);
		}
		finally 
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
	
	
	public boolean isFolderExist(String folder) throws ConservazioneSipException{
		StopWatch sw = new StopWatch(TASK_ID);
		StringBuilder sb = new StringBuilder("SELECT folder FROM conservazione_sip.conservazione_sip ");
		sb.append("WHERE folder = :folder");
		try 
		{
			Map<String, Object> params = new HashMap<>();
			params.put("folder", folder);
			
			sw.start("RECUPERO ITEM DA PROCESSARE");
			List<String> result = jdbcTemplate.queryForList(sb.toString(), params, String.class);
			sw.stop();
			if( logger.isDebugEnabled() )
			{
				logger.debug("SQL [{}] PARAMETRI [{}] RECORD RECUPERATI [{}]", sb.toString(), params, result);
			}		
			return (result!=null && result.size()>0);
		}
		catch (Exception e)
		{
			String msg = "Errore recupero record da processare ";
			logger.error(msg,e);
			throw new ConservazioneSipException(msg, e);
		}
		finally 
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
