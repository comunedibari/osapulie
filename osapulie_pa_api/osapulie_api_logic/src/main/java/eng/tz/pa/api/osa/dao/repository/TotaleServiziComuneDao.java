package eng.tz.pa.api.osa.dao.repository;

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
import eng.tz.pa.api.osa.dao.mapper.TotaleServiziComuneMapper;
import eng.tz.pa.api.osa.dao.models.TotaleServiziComune;
import eng.tz.pa.api.osa.exception.OsaApiDbException;

@Repository
public class TotaleServiziComuneDao {
	private static final Logger logger = LoggerFactory.getLogger(TotaleServiziComuneDao.class);
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

	public List<TotaleServiziComune> getListTotaleServiziComune() throws Exception 
	{
		 
		 
		try 
		{
			 
	   String query="SELECT count(ci.nome) as totale,ci.descrizione as descrizione " +
	            " FROM tb_comune_isa ci,tb_comuneisa_servizio cs,tb_servizio s " +
	            " where ci.ID=cs.fk_comuneisa " +
	            " and cs.fk_servizio=s.ID" +
	            " and cs.attivo=1" +
	            " group by ci.nome;";
			 
			 
			List<TotaleServiziComune> audits = this.jdbcTemplate.query(query, new TotaleServiziComuneMapper());
			 
			if( logger.isDebugEnabled() )
			{
				logger.debug("SQL [{}] TotaleServiziComune TROVATI [{}]", query, (audits != null ? audits.size() : "0"));
			}			
			return audits;
		}
		catch (Exception e)
		{
			String msg = "Errore nel recupero TotaleServiziComune";
			logger.error(msg,e);
			throw new OsaApiDbException(msg, e);
		}
		 
	}
	
	
	public List<TotaleServiziComune> getTotaleServiziComune(String comune) throws Exception 
	{
		 
		 
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
			 
			parameters.put("descrizione", comune);
			 
			List<TotaleServiziComune> audits = this.jdbcTemplate.query(sb.toString(), parameters, new TotaleServiziComuneMapper());
			 
			if( logger.isDebugEnabled() )
			{
				logger.debug("SQL [{}] PARAMETRI [{}] ComuneServizi TROVATI [{}]", sb.toString(), parameters,(audits != null ? audits.size() : "0"));
			}			
			return audits;
		}
		catch (Exception e)
		{
			String msg = "Errore nel recupero registri protocollo";
			logger.error(msg,e);
			throw new OsaApiDbException(msg, e);
		}
		 		
	}
	
 
	
}