package it.eng.tz.area.vasta.protocollo.spring.dao.repository;


import static it.eng.tz.area.vasta.protocollo.utils.Utils.printTaskInfo;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import it.eng.tz.area.vasta.protocollo.exception.ProtocolloDbException;
import it.eng.tz.area.vasta.protocollo.spring.dao.models.RegistroProtocolloModel;

@Repository
public class RegistroProtocolloDao {
	private static final Logger logger = LoggerFactory.getLogger(RegistroProtocolloDao.class);
	private static final String TASK_ID = "REGISTRO_PROTOCOLLO_DAO";
	private NamedParameterJdbcTemplate jdbcTemplate;
//	@Autowired
//	@Qualifier("hikariDs")
//	private DataSource ds;
//	@PostConstruct
//	public void initialize()
//	{
//		if (ds == null)
//		{
//			throw new IllegalArgumentException("Impossibile costruire il repository. Datasource nullo");
//		}
//		jdbcTemplate = new NamedParameterJdbcTemplate(ds);
//	}
	private static final String GET_REGISTRO_PPROTOCOLLO_BY_DATA ="SELECT * FROM adocServ.registro_protocollo where data_prima_reg=? AND data_ultima_reg=? ;";
	public List<RegistroProtocolloModel> ricercaRegistriProtocollo(Date from,Date to) throws ProtocolloDbException 
	{
		StopWatch sw = new StopWatch(TASK_ID);
		try 
		{
			StringBuilder sb = new StringBuilder("SELECT ");
//			sb.append(ID_REGISTRO_PROTOCOLLO_COLUMN_NAME);
//			sb.append(", ");
//			sb.append(PUNTATORE_ALFRESCO_COLUMN_NAME);
//			sb.append(", ");
//		 	sb.append(DATA_CREAZIONE_COLUMN_NAME);
//			sb.append(", ");
//			sb.append(FLAG_GENERATO_COLUMN_NAME);
//			sb.append(", ");
//			sb.append(PATH_FILESYSTEM_COLUMN_NAME);
//			sb.append(", ");
//			sb.append(FILE_NAME_COLUMN_NAME);
//			sb.append(", ");
//			sb.append(DESTINATARIO_COLUMN_NAME);
//			sb.append(", ");
//			sb.append(CHECKSUM_COLUMN_NAME);
//			sb.append(", ");
//			sb.append(CODICE_AMMINISTRAZIONE_COLUMN_NAME);
//			sb.append(", ");
//			sb.append(CODICE_AOO_COLUMN_NAME);
//			sb.append(", ");
//			sb.append(SOGGETTO_PRODUTTORE_2_COLUMN_NAME);
//			sb.append(", ");
//			sb.append(SOGGETTO_PRODUTTORE_COLUMN_NAME);
//			sb.append(", ");
//			sb.append(RESPONSABILE_COLUMN_NAME);
//			sb.append(", ");
//			sb.append(OGGETTO_COLUMN_NAME);
//			sb.append(", ");
//			sb.append(CODICE_REGISTRO_COLUMN_NAME);
//			sb.append(", ");
//			sb.append(PROGRESSIVO_COLUMN_NAME);
//			sb.append(", ");
//			sb.append(ANNO_COLUMN_NAME);
//			sb.append(", ");
//			sb.append(NUMERO_PRIMA_REG_COLUMN_NAME);
//			sb.append(", ");
//			sb.append(NUMERO_ULTIMA_REG_COLUMN_NAME);
//			sb.append(", ");
//			sb.append(DATA_PRIMA_REG_COLUMN_NAME);
//			sb.append(", ");
//			sb.append(DATA_ULTIMA_REG_COLUMN_NAME);
//			sb.append(" FROM registro_protocollo ");
//			sb.append(" WHERE ");
//			sb.append(DATA_CREAZIONE_COLUMN_NAME);
//			sb.append(" >= :dataDa ");
//			sb.append(" AND ");
//			sb.append(DATA_CREAZIONE_COLUMN_NAME);
//			sb.append("< :dataA");
//			sb.append(" ORDER BY ");
//			sb.append(DATA_CREAZIONE_COLUMN_NAME);
			sb.append(" DESC");
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("dataDa", from);
			parameters.put("dataA", to);
			sw.start("QUERY REGISTRO PROTOCOLLO");
			List<RegistroProtocolloModel> registri = null;//(List<RegistroProtocolloModel>)this.jdbcTemplate.query(sb.toString());
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
	
	
	public List<RegistroProtocolloModel> getRegistroProtocollo(Date data_prima_reg) throws ProtocolloDbException 
	{
		StopWatch sw = new StopWatch(TASK_ID);
		try 
		{
			StringBuilder sb = new StringBuilder("SELECT ");
//			sb.append(ID_REGISTRO_PROTOCOLLO_COLUMN_NAME);
//			sb.append(", ");
//			sb.append(PUNTATORE_ALFRESCO_COLUMN_NAME);
//			sb.append(", ");
//		 	sb.append(DATA_CREAZIONE_COLUMN_NAME);
//			sb.append(", ");
//			sb.append(FLAG_GENERATO_COLUMN_NAME);
//			sb.append(", ");
//			sb.append(PATH_FILESYSTEM_COLUMN_NAME);
//			sb.append(", ");
//			sb.append(FILE_NAME_COLUMN_NAME);
//			sb.append(", ");
//			sb.append(DESTINATARIO_COLUMN_NAME);
//			sb.append(", ");
//			sb.append(CHECKSUM_COLUMN_NAME);
//			sb.append(", ");
//			sb.append(CODICE_AMMINISTRAZIONE_COLUMN_NAME);
//			sb.append(", ");
//			sb.append(CODICE_AOO_COLUMN_NAME);
//			sb.append(", ");
//			sb.append(SOGGETTO_PRODUTTORE_2_COLUMN_NAME);
//			sb.append(", ");
//			sb.append(SOGGETTO_PRODUTTORE_COLUMN_NAME);
//			sb.append(", ");
//			sb.append(RESPONSABILE_COLUMN_NAME);
//			sb.append(", ");
//			sb.append(OGGETTO_COLUMN_NAME);
//			sb.append(", ");
//			sb.append(CODICE_REGISTRO_COLUMN_NAME);
//			sb.append(", ");
//			sb.append(PROGRESSIVO_COLUMN_NAME);
//			sb.append(", ");
//			sb.append(ANNO_COLUMN_NAME);
//			sb.append(", ");
//			sb.append(NUMERO_PRIMA_REG_COLUMN_NAME);
//			sb.append(", ");
//			sb.append(NUMERO_ULTIMA_REG_COLUMN_NAME);
//			sb.append(", ");
//			sb.append(DATA_PRIMA_REG_COLUMN_NAME);
//			sb.append(", ");
//			sb.append(DATA_ULTIMA_REG_COLUMN_NAME);
//			sb.append(" FROM registro_protocollo ");
//			sb.append(" WHERE ");
//			sb.append(DATA_PRIMA_REG_COLUMN_NAME);
//			sb.append(" = :dataPrimaReg");
//			sb.append(" AND ");
//			sb.append(DATA_ULTIMA_REG_COLUMN_NAME);
//			sb.append(" = :dataUltimaReg");
//			sb.append(" ORDER BY ");
//			sb.append(DATA_PRIMA_REG_COLUMN_NAME);
			sb.append(" DESC");
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("dataPrimaReg", data_prima_reg);
			parameters.put("dataUltimaReg", data_prima_reg);
			sw.start("QUERY REGISTRO PROTOCOLLO");
			List<RegistroProtocolloModel> registri = null;//this.jdbcTemplate.query(sb.toString(), parameters, new RegistroProtocolloMapper());
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
	
	
	
	public int getLastProgressivoRegistro(String anno)throws ProtocolloDbException{
		final String GET_LAST_PROGRESSIVO_REGISTRO_PPROTOCOLLO = "SELECT max(progressivo) as progressivo FROM registro_protocollo where anno=:anno ;" ;	
		try {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("anno", anno);
		Integer count = jdbcTemplate.queryForObject(GET_LAST_PROGRESSIVO_REGISTRO_PPROTOCOLLO, parameters, Integer.class);
		if(count==null) {
			count=new Integer(0);
			logger.info("Non ho trovato recordo assegno il numero 0 PROGRESSIVO_REGISTRO  Query:: "+ GET_LAST_PROGRESSIVO_REGISTRO_PPROTOCOLLO);
		}
		return count.intValue();
		
		}catch (Exception e)
		{
			String msg = "getLastProgressivoRegistro Errore nel recupero PROGRESSIVO_REGISTRO";
			logger.error(msg,e);
			throw new ProtocolloDbException(msg, e);
		}
		 
	}
	
	public int updateRegistroProtocollo(RegistroProtocolloModel m) throws ProtocolloDbException{
		
		final String UPDATE_REGISTRO_PPROTOCOLLO = "UPDATE registro_protocollo SET puntatore_alfresco=:puntatore,flag_generato=:flag WHERE data_prima_reg=:data ;";	
		try {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("puntatore", m.getRepositoryPointer());
		parameters.put("flag", m.getFlagGenerato().booleanValue());
		parameters.put("data", m.getDataPrimaReg());
		
		return jdbcTemplate.update(UPDATE_REGISTRO_PPROTOCOLLO, parameters);
		
		}catch (Exception e)
		{
			String msg = "updateRegistroProtocollo Errore  UPDATE REGISTRO PPROTOCOLLO["+UPDATE_REGISTRO_PPROTOCOLLO+"]";
			logger.error(msg,e);
			throw new ProtocolloDbException(msg, e);
		}
	}
	
	
	public int generaNewRegistroProtocollo(RegistroProtocolloModel m) throws ProtocolloDbException{
		final String INSERT_REGISTRO_PPROTOCOLLO = "INSERT INTO `registro_protocollo` (`puntatore_alfresco`, `data_creazione`, `flag_generato`, "
				+ "`path_filesystem`, `file_name`, `destinatario`, `checksum`, `codice_amministrazione`, `codice_aoo`, `sogetto_produttore2`, `soggetto_produttore`,"
				+ "`responsabile`, `oggetto`, `codice_registro`, `progressivo`, `anno`, `numero_prima_reg`, `numero_ultima_reg`, `data_prima_reg`, `data_ultima_reg`) "
				+ " VALUES (:puntatore_alfresco, "
				+ ":data_creazione, "
				+ ":flag_generato, "
				+ ":path_filesystem, "
				+ ":file_name, "
				+ ":destinatario, "
				+ ":checksum, "
				+ ":codice_amministrazione, "
				+ ":codice_aoo, "
				+ ":sogetto_produttore2, "
				+ ":soggetto_produttore, "
				+ ":responsabile, "
				+ ":oggetto, "
				+ ":codice_registro, "
				+ ":progressivo, "
				+ ":anno, "
				+ ":numero_prima_reg, "
				+ ":numero_ultima_reg, "
				+ ":data_prima_reg, "
				+ ":data_ultima_reg);";
		try {
			
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("puntatore_alfresco", m.getRepositoryPointer());
		parameters.put("data_creazione", m.getDataCreazione());
		parameters.put("flag_generato", m.getFlagGenerato().booleanValue());
		parameters.put("path_filesystem", m.getPathFileSystem());
		parameters.put("file_name", m.getFileName());
		parameters.put("destinatario", m.getDestinatario());
		parameters.put("checksum", m.getChecksum());
		parameters.put("codice_amministrazione", m.getCodiceAmm());
		parameters.put("codice_aoo", m.getCodiceAoo());
		parameters.put("sogetto_produttore2", m.getSoggettoProduttore2());
		parameters.put("soggetto_produttore", m.getDenominazioneSoggettoProduttore());
		parameters.put("responsabile", m.getResponsabile());
		parameters.put("oggetto", m.getOggetto());
		parameters.put("codice_registro", m.getCodiceRegistro());
		parameters.put("progressivo", m.getProgressivo());
		parameters.put("anno", m.getAnno());
		parameters.put("numero_prima_reg", m.getNumeroPrimaReg());
		parameters.put("numero_ultima_reg", m.getNumeroUltimaReg());
		parameters.put("data_prima_reg", m.getDataPrimaReg());
		parameters.put("data_ultima_reg", m.getDataUltimaReg());
		 
		return jdbcTemplate.update(INSERT_REGISTRO_PPROTOCOLLO,parameters);
		
		}catch (Exception e)
		{
			String msg = "generaNewRegistroProtocollo Errore INSERT REGISTRO PPROTOCOLLO["+INSERT_REGISTRO_PPROTOCOLLO+"]";
			logger.error(msg,e);
			throw new ProtocolloDbException(msg, e);
		}
		
	  } 
		
	
	
	
}