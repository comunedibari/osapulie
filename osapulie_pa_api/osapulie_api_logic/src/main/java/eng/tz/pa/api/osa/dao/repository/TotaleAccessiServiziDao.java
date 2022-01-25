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
import eng.tz.pa.api.osa.dao.mapper.TotaleAccessiServiziMapper;
import eng.tz.pa.api.osa.dao.models.TotaleAccessiServizi;
import eng.tz.pa.api.osa.exception.OsaApiDbException;

@Repository
public class TotaleAccessiServiziDao {
	private static final Logger logger = LoggerFactory.getLogger(TotaleAccessiServiziDao.class);

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

	public List<TotaleAccessiServizi> getListTotaleAccessiServizi() throws Exception 
	{
		 
		 
		try 
		{
			 
	   String query="SELECT count(nome_servizio) as totale, nome_servizio as descrizione FROM tb_richiesta_servizio  group by fk_servizio;";
			 
			 
			List<TotaleAccessiServizi> audits = this.jdbcTemplate.query(query, new TotaleAccessiServiziMapper());
		 
			if( logger.isDebugEnabled() )
			{
				logger.debug("SQL [{}] TotaleAccessiServizi TROVATI [{}]", query, (audits != null ? audits.size() : "0"));
			}			
			return audits;
		}
		catch (Exception e)
		{
			String msg = "Errore nel recupero TotaleAccessiServizi";
			logger.error(msg,e);
			throw new OsaApiDbException(msg, e);
		}
		 	
	}
	
	
	public List<TotaleAccessiServizi> getListTotaleAllServizi() throws Exception 
	{
	 
		 
		try 
		{
			 
	   String query="SELECT count(*) as totale,'totale servizi richiesti' as descrizione FROM tb_richiesta_servizio;";
			 
			 
			List<TotaleAccessiServizi> audits = this.jdbcTemplate.query(query, new TotaleAccessiServiziMapper());
			 
			if( logger.isDebugEnabled() )
			{
				logger.debug("SQL [{}] TotaleServiziRichiesti TROVATI [{}]", query, (audits != null ? audits.size() : "0"));
			}			
			return audits;
		}
		catch (Exception e)
		{
			String msg = "Errore nel recupero TotaleServiziRichiesti";
			logger.error(msg,e);
			throw new OsaApiDbException(msg, e);
		}
		 
	}
	
	
	public List<TotaleAccessiServizi> getTotaleAccessiServizi(String comune) throws Exception 
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
			 
			List<TotaleAccessiServizi> audits = this.jdbcTemplate.query(sb.toString(), parameters, new TotaleAccessiServiziMapper());
			 
			if( logger.isDebugEnabled() )
			{
				logger.debug("SQL [{}] PARAMETRI [{}] TotaleAccessiServizi TROVATI [{}]", sb.toString(), parameters,(audits != null ? audits.size() : "0"));
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