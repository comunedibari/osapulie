package it.eng.tz.area.vasta.protocollo.spring.dao.repository;


import static it.eng.tz.area.vasta.protocollo.utils.Utils.printTaskInfo;

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

import it.eng.tz.area.vasta.protocollo.exception.ProtocolloDbException;
import it.eng.tz.area.vasta.protocollo.spring.dao.mapper.AuditMapper;
import it.eng.tz.area.vasta.protocollo.spring.dao.models.Audit;

@Repository
public class AuditDao {
	private static final Logger logger = LoggerFactory.getLogger(AuditDao.class);
	private static final String TASK_ID = "AUDIT_DAO";
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

	public List<Audit> getAudits(String ggMMaaaa) throws Exception 
	{
		StopWatch sw = new StopWatch(TASK_ID);
		 
		try 
		{
			StringBuilder sb = new StringBuilder();
 
			sb.append("SELECT * FROM tb_audit ");
			sb.append(" WHERE ");
			sb.append("giorno_mese_anno");
			sb.append(" = :giorno_mese_anno");
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("giorno_mese_anno", ggMMaaaa);
			sw.start("QUERY AUDIT");
			List<Audit> audits = this.jdbcTemplate.query(sb.toString(), parameters, new AuditMapper());
			sw.stop();
			if( logger.isDebugEnabled() )
			{
				logger.debug("SQL [{}] PARAMETRI [{}] AUDIT TROVATI [{}]", sb.toString(), parameters, (audits != null ? audits.size() : "0"));
			}			
			return audits;
		}
		catch (Exception e)
		{
			String msg = "Errore nel recupero registri protocollo";
			logger.error(msg,e);
			throw new ProtocolloDbException(msg, e);
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
	
	
	public List<Audit> getAudits(String ggMMaaaa,String stato) throws Exception 
	{
		StopWatch sw = new StopWatch(TASK_ID);
		 
		try 
		{
			StringBuilder sb = new StringBuilder();
 
			sb.append("SELECT * FROM tb_audit ");
			sb.append(" WHERE ");
			sb.append("giorno_mese_anno");
			sb.append(" = :giorno_mese_anno");
			sb.append(" AND ");
			sb.append("stato");
			sb.append(" = :stato");
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("giorno_mese_anno", ggMMaaaa);
			parameters.put("stato", stato);
			sw.start("QUERY AUDIT");
			List<Audit> audits = this.jdbcTemplate.query(sb.toString(), parameters, new AuditMapper());
			sw.stop();
			if( logger.isDebugEnabled() )
			{
				logger.debug("SQL [{}] PARAMETRI [{}] AUDIT CON STATO A [{}] TROVATI [{}]", sb.toString(), parameters, stato,(audits != null ? audits.size() : "0"));
			}			
			return audits;
		}
		catch (Exception e)
		{
			String msg = "Errore nel recupero registri protocollo";
			logger.error(msg,e);
			throw new ProtocolloDbException(msg, e);
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
	
	
	
	
	public List<Audit> getAllAudit() throws Exception 
	{
		StopWatch sw = new StopWatch(TASK_ID);
	 
		try 
		{
			StringBuilder sb = new StringBuilder();
 
			sb.append("SELECT * FROM tb_audit");
			 
			List<Audit> audits = this.jdbcTemplate.query(sb.toString(), new AuditMapper());
			sw.stop();
			if( logger.isDebugEnabled() )
			{
				logger.debug("SQL [{}] AUDIT TROVATI [{}]", sb.toString(), (audits != null ? audits.size() : "0"));
			}			
			return audits;
		}
		catch (Exception e)
		{
			String msg = "Errore nel recupero audits";
			logger.error(msg,e);
			throw new ProtocolloDbException(msg, e);
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
	
	public int updateAudit(Audit m) throws Exception{
		
		final String UPDATE_REGISTRO_AUDIT = "UPDATE tb_audit SET checksum=:checksum,codice_registro=:codice_registro,stato=:stato,cons=:cons WHERE ID=:ID ;";	
		try {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("checksum", m.getChecksum());
		parameters.put("codice_registro", m.getCodiceRegistro());
		parameters.put("stato", m.getStato());
		parameters.put("cons", m.getCons());
		parameters.put("ID", m.getId());
		
		return jdbcTemplate.update(UPDATE_REGISTRO_AUDIT, parameters);
		
		}catch (Exception e)
		{
			String msg = "updateAudit Errore  UPDATE AUDIT["+UPDATE_REGISTRO_AUDIT+"]";
			logger.error(msg,e);
			throw new ProtocolloDbException(msg, e);
		}
	}
 
	public int saveAudit(Audit m) throws Exception{
		final String INSERT_AUDIT = "INSERT INTO `tb_audit` (`userCod`, `giorno_mese_anno`, `file_name`, "
				+ "`path_filesystem`, `checksum`, `data_creazione`, `data_ultima_modifica`, `codice_registro`, `stato`, `cons`) "
				+ " VALUES (:userCod, "
				+ ":giorno_mese_anno, "
				+ ":file_name, "
				+ ":path_filesystem, "
				+ ":checksum, "
				+ ":data_creazione, "
				+ ":data_ultima_modifica, "
				+ ":codice_registro, "
				+ ":stato, "
				+ ":cons);";
		try {
			
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("userCod", m.getUserCod());
		parameters.put("giorno_mese_anno", m.getGiornoMeseAnno());
		parameters.put("path_filesystem", m.getPathFilesystem());
		parameters.put("file_name", m.getFileName());
		parameters.put("checksum", m.getChecksum());
		parameters.put("data_creazione", m.getDataCreazione());
		parameters.put("data_ultima_modifica", m.getDataUltimaModifica());
		parameters.put("codice_registro", m.getCodiceRegistro());
		parameters.put("data_creazione", m.getDataCreazione());
		parameters.put("stato", m.getStato());
		parameters.put("cons", m.getCons());
 
		 
		return jdbcTemplate.update(INSERT_AUDIT,parameters);
		
		}catch (Exception e)
		{
			String msg = "saveAudit Errore INSERT AUDIT["+INSERT_AUDIT+"]";
			logger.error(msg,e);
			throw new ProtocolloDbException(msg, e);
		}
		
	  } 
		
	 
		public List<String> getListFolder(List<String> folders) throws Exception 
		{

			try 
			{
				StringBuilder sb = new StringBuilder();
				sb.append("SELECT DISTINCT giorno_mese_anno FROM tb_audit WHERE giorno_mese_anno NOT IN( :folders ) AND giorno_mese_anno IS NOT NULL AND giorno_mese_anno!='';");
				Map<String, List<String>> parameters = new HashMap<String, List<String>>();
				parameters.put("folders",folders);
				 
				List<String> audits =(List<String>) this.jdbcTemplate.queryForList(sb.toString(),parameters,String.class);
				
				if( logger.isDebugEnabled() )
				{
					logger.debug("getAuditDaProcessare > SQL [{}] AUDIT TROVATI [{}]", sb.toString(), (audits != null ? audits.size() : "0"));
				}			
				return audits;
			}
			catch (Exception e)
			{
				String msg = "Errore nel recupero audits";
				logger.error(msg,e);
				throw new ProtocolloDbException(msg, e);
			}
						
		}
	
	
}