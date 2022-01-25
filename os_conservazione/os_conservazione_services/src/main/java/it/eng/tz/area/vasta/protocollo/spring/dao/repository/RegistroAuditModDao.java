package it.eng.tz.area.vasta.protocollo.spring.dao.repository;


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

import it.eng.tz.area.vasta.protocollo.exception.ProtocolloDbException;
import it.eng.tz.area.vasta.protocollo.spring.dao.mapper.RegistroAuditModMapper;
import it.eng.tz.area.vasta.protocollo.spring.dao.models.RegistroAuditModel;


@Repository
public class RegistroAuditModDao {
	private static final Logger logger = LoggerFactory.getLogger(RegistroAuditModDao.class);
	private static final String TASK_ID = "REGISTRO_AUDIT_DAO";
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

	public List<RegistroAuditModel> ricercaRegistriAudit(Date from,Date to) throws Exception 
	{
		StopWatch sw = new StopWatch(TASK_ID);
		try 
		{
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT * FROM tb_registro_audit_log ");
			sb.append(" WHERE ");
			sb.append("data_creazione");
			sb.append(" >= :dataDa ");
			sb.append(" AND ");
			sb.append("data_creazione");
			sb.append("< :dataA");
			sb.append(" ORDER BY ");
			sb.append("data_creazione");
			sb.append(" DESC");
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("dataDa", from);
			parameters.put("dataA", to);
			sw.start("QUERY REGISTRO AUDIT");
			List<RegistroAuditModel> registri = this.jdbcTemplate.query(sb.toString(), parameters, new RegistroAuditModMapper());
			sw.stop();
			if( logger.isDebugEnabled() )
			{
				logger.debug("SQL [{}] PARAMETRI [{}] REGISTRI TROVATI [{}]", sb.toString(), parameters, (registri != null ? registri.size() : "0"));
			}			
			return registri;
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
	
	
	public List<RegistroAuditModel> getRegistroAudit(String fileName_ggddaaaa) throws Exception 
	{
		StopWatch sw = new StopWatch(TASK_ID);
		String fileName=fileName_ggddaaaa;//"audit_registro_giornaliero_"+fileName_ggddaaaa+".pdf";
		try 
		{
			StringBuilder sb = new StringBuilder();
 
			sb.append("SELECT * FROM tb_registro_audit_log ");
			sb.append(" WHERE ");
			sb.append("codice_registro");
			sb.append(" = :codice_registro");
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("codice_registro", fileName);
			sw.start("QUERY REGISTRO PROTOCOLLO");
			List<RegistroAuditModel> registri = this.jdbcTemplate.query(sb.toString(), parameters, new RegistroAuditModMapper());
			sw.stop();
			if( logger.isDebugEnabled() )
			{
				logger.debug("SQL [{}] PARAMETRI [{}] REGISTRI TROVATI [{}]", sb.toString(), parameters, (registri != null ? registri.size() : "0"));
			}			
			return registri;
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
	
	
	public List<RegistroAuditModel> getAllRegistroAudit() throws Exception 
	{
		StopWatch sw = new StopWatch(TASK_ID);
	 
		try 
		{
			StringBuilder sb = new StringBuilder();
 
			sb.append("SELECT * FROM tb_registro_audit_log");
			 
			List<RegistroAuditModel> registri = this.jdbcTemplate.query(sb.toString(), new RegistroAuditModMapper());
			sw.stop();
			if( logger.isDebugEnabled() )
			{
				logger.debug("SQL [{}]  REGISTRI AUDIT TROVATI [{}]", sb.toString(), (registri != null ? registri.size() : "0"));
			}			
			return registri;
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
	
 
	public List<String> getAuditDaProcessare() throws Exception 
	{

		try 
		{
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT distinct giorno_mese_anno FROM tb_audit where stato=:stato");
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("stato","0");
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
	
	
	public int updateRegistroAudit(RegistroAuditModel m) throws Exception{
		
		final String UPDATE_REGISTRO_AUDIT = "UPDATE tb_registro_audit_log SET checksum=:checksum, flag_generato=:flag_generato WHERE file_name=:file_name ;";	
		try {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("checksum", m.getChecksum());
		parameters.put("flag_generato", m.getFlagGenerato());
		parameters.put("file_name", m.getFileName());
		
		return jdbcTemplate.update(UPDATE_REGISTRO_AUDIT, parameters);
		
		}catch (Exception e)
		{
			String msg = "updateRegistroProtocollo Errore  UPDATE REGISTRO AUDIT["+UPDATE_REGISTRO_AUDIT+"]";
			logger.error(msg,e);
			throw new ProtocolloDbException(msg, e);
		}
	}
 
	public int generaProgressivoRegistro(String anno) throws Exception{
		
		final String GENERA_CODE_REGIS_AUDIT = "SELECT MAX(t.progressivo) as prog FROM tb_registro_audit_log t WHERE t.anno=:anno;";	
		try {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("anno", anno);
	 
		Integer result=jdbcTemplate.queryForObject(GENERA_CODE_REGIS_AUDIT, parameters, Integer.class);
		if(result==null) {
			result= new Integer(0);
		}
		return (result.intValue()+1);
		}catch (Exception e)
		{
			String msg = "generaProgressivoRegistro Errore  GENERA PROGRESSIVO REGISTRO ["+GENERA_CODE_REGIS_AUDIT+"]";
			logger.error(msg,e);
			throw new ProtocolloDbException(msg, e);
		}
	}
	
	
	
	public int saveRegistroAudit(RegistroAuditModel m) throws Exception{
		final String INSERT_REGISTRO_AUDIT = "INSERT INTO `tb_registro_audit_log` "
				+"(" + 
				"`puntatore_alfresco`," + 
				"`data_creazione`," + 
				"`flag_generato`," + 
				"`path_filesystem`," + 
				"`file_name`," + 
				"`destinatario`," + 
				"`checksum`," + 
				"`codice_amministrazione`," + 
				"`codice_aoo`," + 
				"`soggetto_produttore2`," + 
				"`soggetto_produttore`," + 
				"`responsabile`," + 
				"`oggetto`," + 
				"`codice_registro`," + 
				"`progressivo`," + 
				"`anno`)"
				+ " VALUES "
				+ "(" + 
				":puntatore_alfresco, " + 
				":data_creazione, " + 
				":flag_generato, " + 
				":path_filesystem, " + 
				":file_name, " + 
				":destinatario, " + 
				":checksum, " + 
				":codice_amministrazione, " + 
				":codice_aoo, " + 
				":soggetto_produttore2, " + 
				":soggetto_produttore, " + 
				":responsabile, " + 
				":oggetto, " + 
				":codice_registro, " + 
				":progressivo, " + 
				":anno);";
				 
		try {
			
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("puntatore_alfresco", m.getPuntatoreAlfresco());
		parameters.put("data_creazione", m.getDataCreazione());
		parameters.put("flag_generato", m.getFlagGenerato());
		parameters.put("path_filesystem", m.getPathFileSystem());
		parameters.put("file_name", m.getFileName());
		parameters.put("destinatario", m.getDestinatario());
		parameters.put("checksum", m.getChecksum());
		parameters.put("codice_amministrazione", m.getCodiceAmministrazione());
		parameters.put("codice_aoo", m.getCodiceAoo());
		parameters.put("soggetto_produttore2", m.getSoggettoProduttore2());
		parameters.put("soggetto_produttore", m.getSoggettoProduttore());
		parameters.put("responsabile", m.getResponsabile());
		parameters.put("oggetto", m.getOggetto());
		parameters.put("codice_registro", m.getCodiceRegistro());
		parameters.put("progressivo", m.getProgressivo());
		parameters.put("anno", m.getAnno());
	 
		return jdbcTemplate.update(INSERT_REGISTRO_AUDIT,parameters);
		
		}catch (Exception e)
		{
			String msg = "generaNewRegistroAudit Errore INSERT REGISTRO AUDIT["+INSERT_REGISTRO_AUDIT+"]";
			logger.error(msg,e);
			throw new ProtocolloDbException(msg, e);
		}
		
	  } 
		
	
	
	
}