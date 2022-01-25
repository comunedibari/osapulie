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
import eng.tz.pa.api.osa.dao.mapper.DwhServizioAttributeMapper;
import eng.tz.pa.api.osa.dao.mapper.EtaMediaServiziRichiestiMapper;
import eng.tz.pa.api.osa.dao.mapper.ServiziRichiestiGeolocalizzazioneMapper;
import eng.tz.pa.api.osa.dao.mapper.ServiziRichiestiPerFasciaDiEtaMapper;
import eng.tz.pa.api.osa.dao.mapper.ServiziRichiestiPercCompletatiMapper;
import eng.tz.pa.api.osa.dao.mapper.ServizioMapper;
import eng.tz.pa.api.osa.dao.mapper.TempoMedioServiziRichiestiMapper;
import eng.tz.pa.api.osa.dao.models.DwhServizioAttribute;
import eng.tz.pa.api.osa.dao.models.EtaMediaServiziRichiesti;
import eng.tz.pa.api.osa.dao.models.ServiziRichiestiGeolocalizzazione;
import eng.tz.pa.api.osa.dao.models.ServiziRichiestiPerFasciaDiEta;
import eng.tz.pa.api.osa.dao.models.ServiziRichiestiPercCompletati;
import eng.tz.pa.api.osa.dao.models.Servizio;
import eng.tz.pa.api.osa.dao.models.TempoMedioServiziRichiesti;
import eng.tz.pa.api.osa.exception.OsaApiDbException;

@Repository
public class DwhRichiesteRepositoryDao {
	private static final Logger logger = LoggerFactory.getLogger(DwhRichiesteRepositoryDao.class);

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
	
	
	
	public List<DwhServizioAttribute> getDwhServizioAttribute(String codeOrName) throws Exception 
	{
		
		try 
		{
			Map<String, Object> parameters=null;
			StringBuilder sb = new StringBuilder();
			if(codeOrName==null || codeOrName.isEmpty())
			{
			sb.append("SELECT * FROM osapulie.tb_dwh_servizio_attribute ds");	
			}
			else 
			{
			
			sb.append("SELECT * FROM osapulie.tb_dwh_servizio_attribute ds " + 
					" where " + 
					" ds.SERVIZIO_CODE =:codice_name " + 
					" OR " + 
					" ds.SERVIZIO_NOME =:codice_name");
		
 
			parameters = new HashMap<String, Object>();
			parameters.put("codice_name", codeOrName);
			}
			 
			List<DwhServizioAttribute> listRichiesta = this.jdbcTemplate.query(sb.toString(), parameters, new DwhServizioAttributeMapper());
			 
			if( logger.isDebugEnabled() )
			{
				logger.debug("SQL [{}] PARAMETRI [{}] DwhServizioAttribute TROVATI [{}]", sb.toString(), parameters,(listRichiesta != null ? listRichiesta.size() : "0"));
			}			
			return listRichiesta;
		}
		catch (Exception e)
		{
			String msg = "Errore nel recupero dati";
			logger.error(msg,e);
			throw new OsaApiDbException(msg, e);
		}

	
	}
	
	public List<EtaMediaServiziRichiesti> getDwhEtaMediaServiziRichiesti() throws Exception 
	{
		
		try 
		{
			StringBuilder sb = new StringBuilder();
 
			sb.append("SELECT AVG(dt.ETA_RICHIEDENTE) as eta,dt.SESSO as sesso ,dt.COD_SERVIZIO as servizio_code ,s.nome_servizio as servizio_nome " + 
					" FROM " + 
					" tb_dwh_datamining dt, tb_servizio s " + 
					" where dt.ETA_RICHIEDENTE>0 " + 
					" AND " + 
					" dt.COD_SERVIZIO= s.codice_servizio " + 
					" group by " + 
					" dt.COD_SERVIZIO, dt.SESSO " + 
					" order by " + 
					" servizio_nome;");
		
 
			Map<String, Object> parameters = null;
			 
			List<EtaMediaServiziRichiesti> listRichiesta = this.jdbcTemplate.query(sb.toString(), parameters, new EtaMediaServiziRichiestiMapper());
			 
			if( logger.isDebugEnabled() )
			{
				logger.debug("SQL [{}] EtaMediaServiziRichiesti TROVATI [{}]", sb.toString(),(listRichiesta != null ? listRichiesta.size() : "0"));
			}			
			return listRichiesta;
		}
		catch (Exception e)
		{
			String msg = "Errore nel recupero dati";
			logger.error(msg,e);
			throw new OsaApiDbException(msg, e);
		}

	
	}
	
	public List<TempoMedioServiziRichiesti> getDwhTempoMedioServiziRichiesti() throws Exception 
	{
		
		try 
		{
			StringBuilder sb = new StringBuilder();
 
			sb.append("SELECT AVG(TEMPO_ESECUZIONE) as tempo_medio ,COD_SERVIZIO as servizio_code,NOME_SERVIZIO as servizio_nome " + 
					" FROM tb_dwh_tempi_medi " + 
					" where " + 
					" DATA_FINE is not null " + 
					" group by " + 
					" COD_SERVIZIO " + 
					" order by " + 
					" servizio_nome;");
		
 
			Map<String, Object> parameters = null;
			 
			List<TempoMedioServiziRichiesti> listRichiesta = this.jdbcTemplate.query(sb.toString(), parameters, new TempoMedioServiziRichiestiMapper());
			 
			if( logger.isDebugEnabled() )
			{
				logger.debug("SQL [{}]  TempoMedioServiziRichiesti TROVATI [{}]", sb.toString(),(listRichiesta != null ? listRichiesta.size() : "0"));
			}			
			return listRichiesta;
		}
		catch (Exception e)
		{
			String msg = "Errore nel recupero dati";
			logger.error(msg,e);
			throw new OsaApiDbException(msg, e);
		}

	
	}
	
	
	public List<ServiziRichiestiPercCompletati> getDwhServiziRichiestiPercCompletati() throws Exception 
	{
		
		try 
		{
			StringBuilder sb = new StringBuilder();
 
			sb.append("SELECT TOTALE_RICHIESTI,COMPLETATI,INCOMPLETI,ROUND((COMPLETATI/TOTALE_RICHIESTI*100)) as PERCENTUALE_COMPLETAMENTO, NOME_SERVIZIO, COD_SERVIZIO " + 
					" FROM " + 
					" (SELECT (SELECT count(a.COD_SERVIZIO)" + 
					" FROM " + 
					" tb_dwh_tempi_medi a " + 
					" WHERE " + 
					" a.NOME_SERVIZIO is not null and a.COD_SERVIZIO=e.COD_SERVIZIO)AS TOTALE_RICHIESTI," + 
					" (SELECT count(b.COD_SERVIZIO)" + 
					" FROM " + 
					" tb_dwh_tempi_medi b " + 
					" WHERE " + 
					" b.NOME_SERVIZIO is not null " + 
					" AND " + 
					" b.DATA_FINE IS NOT NULL and b.COD_SERVIZIO=e.COD_SERVIZIO) AS COMPLETATI," + 
					" ((SELECT count(c.COD_SERVIZIO) " + 
					" FROM " + 
					" tb_dwh_tempi_medi c " + 
					" WHERE " + 
					" c.NOME_SERVIZIO is not null " + 
					" AND " + 
					" c.DATA_FINE IS NULL and c.COD_SERVIZIO=e.COD_SERVIZIO)) AS INCOMPLETI, e.NOME_SERVIZIO, e.COD_SERVIZIO" + 
					" FROM " + 
					" tb_dwh_tempi_medi e " + 
					" WHERE e.NOME_SERVIZIO is not null " + 
					" group by " + 
					" e.COD_SERVIZIO) as SUBQUERY;");
		
 
			Map<String, Object> parameters = null;
			 
			List<ServiziRichiestiPercCompletati> listRichiesta = this.jdbcTemplate.query(sb.toString(), parameters, new ServiziRichiestiPercCompletatiMapper());
			 
			if( logger.isDebugEnabled() )
			{
				logger.debug("SQL [{}]  ServiziRichiestiPercCompletati TROVATI [{}]", sb.toString(),(listRichiesta != null ? listRichiesta.size() : "0"));
			}			
			return listRichiesta;
		}
		catch (Exception e)
		{
			String msg = "Errore nel recupero dati";
			logger.error(msg,e);
			throw new OsaApiDbException(msg, e);
		}

	
	}
	
	
	public List<ServiziRichiestiGeolocalizzazione> getDwhServiziGeolocalizzazione() throws Exception 
	{
		
		try 
		{
			StringBuilder sb = new StringBuilder();
 
			sb.append("SELECT TOTALE_SERVIZIO,TOTALE,CAP,ROUND((TOTALE/TOTALE_SERVIZIO*100)) as PERCENTUALE_LOCALE,COD_SERVIZIO,SERVIZIO_NOME,CITTA,REGIONE,LATITUDINE,LONGITUDINE,ANNO " + 
					" FROM " + 
					" (SELECT " + 
					" (SELECT count(b.COD_SERVIZIO)" + 
					" FROM " + 
					" tb_dwh_geolocalizzazione b" + 
					" WHERE " + 
					" b.COD_SERVIZIO=e.COD_SERVIZIO) AS TOTALE_SERVIZIO," + 
					" (SELECT count(a.CAP)" + 
					" FROM " + 
					" tb_dwh_geolocalizzazione a " + 
					" WHERE " + 
					" a.COD_SERVIZIO=e.COD_SERVIZIO AND a.CAP =e.CAP )AS TOTALE,e.CAP," + 
					" e.COD_SERVIZIO,ts.nome_servizio as SERVIZIO_NOME,e.CITTA,e.REGIONE,e.LATITUDINE,e.LONGITUDINE,YEAR(e.DATA_CREAZIONE) as ANNO " + 
					" FROM " + 
					" tb_dwh_geolocalizzazione e,tb_servizio ts WHERE e.CAP is not null AND e.COD_SERVIZIO=ts.codice_servizio " + 
					" group by " + 
					" e.CAP,e.COD_SERVIZIO " + 
					" order by ts.nome_servizio asc) AS SUBQUERY;");
		
 
			Map<String, Object> parameters = null;
			 
			List<ServiziRichiestiGeolocalizzazione> listRichiesta = this.jdbcTemplate.query(sb.toString(), parameters, new ServiziRichiestiGeolocalizzazioneMapper());
			 
			if( logger.isDebugEnabled() )
			{
				logger.debug("SQL [{}]  ServiziGeolocalizzazione TROVATI [{}]", sb.toString(),(listRichiesta != null ? listRichiesta.size() : "0"));
			}			
			return listRichiesta;
		}
		catch (Exception e)
		{
			String msg = "Errore nel recupero dati";
			logger.error(msg,e);
			throw new OsaApiDbException(msg, e);
		}

	
	}
	
	
	public List<Servizio> getDwhServizio() throws Exception 
	{
		
		try 
		{
			StringBuilder sb = new StringBuilder();
 
			sb.append("select ID as servizio_id, codice_servizio as servizio_code, nome_servizio as servizio_nome from tb_servizio;");
		
 
			Map<String, Object> parameters = null;
			 
			List<Servizio> listRichiesta = this.jdbcTemplate.query(sb.toString(), parameters, new ServizioMapper());
			 
			if( logger.isDebugEnabled() )
			{
				logger.debug("SQL [{}]  Servizio TROVATI [{}]", sb.toString(),(listRichiesta != null ? listRichiesta.size() : "0"));
			}			
			return listRichiesta;
		}
		catch (Exception e)
		{
			String msg = "Errore nel recupero dati";
			logger.error(msg,e);
			throw new OsaApiDbException(msg, e);
		}

	
	}
	public List<ServiziRichiestiPerFasciaDiEta> getDwhServiziRichiestiPerFasciaDiEta() throws Exception
	 
		{
			
			try 
			{
				StringBuilder sb = new StringBuilder();
	 
				sb.append(" SELECT totale_servizi,ninore_18,ROUND((ninore_18/totale_servizi*100))AS PERC_18,tra_1839,ROUND((tra_1839/totale_servizi*100))AS PERC_1839,tra_4059,ROUND((tra_4059/totale_servizi*100))AS PERC_4059,oltre_60,ROUND((oltre_60/totale_servizi*100)) AS PERC_60,COD_SERVIZIO,NOME_SERVIZIO,ANNO " + 
						" FROM " + 
						" (SELECT (SELECT count(COD_SERVIZIO) " + 
						" from tb_dwh_datamining t18 where t18.COD_SERVIZIO=dt.COD_SERVIZIO ) as totale_servizi, " + 
						" (select count(t18.ETA_RICHIEDENTE) from tb_dwh_datamining t18 where " + 
						" t18.ETA_RICHIEDENTE<18 and t18.COD_SERVIZIO=dt.COD_SERVIZIO ) as ninore_18," + 
						" (select count(t1839.ETA_RICHIEDENTE) from tb_dwh_datamining t1839 where " + 
						" t1839.ETA_RICHIEDENTE>17 and t1839.ETA_RICHIEDENTE<40   and t1839.COD_SERVIZIO=dt.COD_SERVIZIO ) as tra_1839, " + 
						" (select count(t4059.ETA_RICHIEDENTE) from tb_dwh_datamining t4059 where " + 
						" t4059.ETA_RICHIEDENTE>39 and t4059.ETA_RICHIEDENTE<60   and t4059.COD_SERVIZIO=dt.COD_SERVIZIO ) as tra_4059," + 
						" (select count(t60.ETA_RICHIEDENTE) from tb_dwh_datamining t60 where " + 
						" t60.ETA_RICHIEDENTE>59 and t60.COD_SERVIZIO=dt.COD_SERVIZIO ) as oltre_60,dt.COD_SERVIZIO AS COD_SERVIZIO,s.nome_servizio AS NOME_SERVIZIO, year(dt.DATA_RICHIESTA) as ANNO " + 
						" FROM tb_dwh_datamining dt, tb_servizio s where dt.ETA_RICHIEDENTE>0 " + 
						" AND dt.COD_SERVIZIO= s.codice_servizio " + 
						" group by dt.COD_SERVIZIO) AS SUBQ;");
			
	 
				Map<String, Object> parameters = null;
				 
				List<ServiziRichiestiPerFasciaDiEta> listRichiesta = this.jdbcTemplate.query(sb.toString(), parameters, new ServiziRichiestiPerFasciaDiEtaMapper());
				 
				if( logger.isDebugEnabled() )
				{
					logger.debug("SQL [{}]  ServiziRichiestiPerFasciaDiEta TROVATI [{}]", sb.toString(),(listRichiesta != null ? listRichiesta.size() : "0"));
				}			
				return listRichiesta;
			}
			catch (Exception e)
			{
				String msg = "Errore nel recupero dati";
				logger.error(msg,e);
				throw new OsaApiDbException(msg, e);
			}

		}
 
}
