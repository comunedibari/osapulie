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

import eng.tz.pa.api.osa.dao.mapper.CambioResidenzaPartenzeArriviMapper;
import eng.tz.pa.api.osa.dao.mapper.InfoAziendaMapper;
import eng.tz.pa.api.osa.dao.mapper.RichiesteServiziAziendaMapper;
import eng.tz.pa.api.osa.dao.mapper.RichiesteServiziPerAziendaMapper;
import eng.tz.pa.api.osa.dao.mapper.RichiesteServiziUtenteMapper;
import eng.tz.pa.api.osa.dao.mapper.TotaleAccessiServiziPerDataMapper;
import eng.tz.pa.api.osa.dao.models.CambioResidenzaPartenzeArrivi;
import eng.tz.pa.api.osa.dao.models.InfoAzienda;
import eng.tz.pa.api.osa.dao.models.RichiesteServiziAzienda;
import eng.tz.pa.api.osa.dao.models.RichiesteServiziPerAzienda;
import eng.tz.pa.api.osa.dao.models.RichiesteServiziUtente;
import eng.tz.pa.api.osa.dao.models.TotaleAccessiServiziPerData;
import eng.tz.pa.api.osa.exception.OsaApiDbException;

@Repository
public class RichiesteServiziUtenteDao {
	private static final Logger logger = LoggerFactory.getLogger(RichiesteServiziUtenteDao.class);

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

	public List<RichiesteServiziAzienda> getListRichiesteServiziAzienda() throws Exception 
	{
		try 
		{	
			
	       String query="SELECT rs.nome_servizio, rs.data_richiesta, ta.ragione_sociale, uc.ID as coduser " +
	                    " FROM tb_richiesta_servizio rs,tb_fascicolo_utente fu,tb_profilo_utente_cittadino uc,tb_azienda ta " +
	                    " where rs.fk_fascicolo=fu.ID " +
	                    " AND fu.fk_cittadino=uc.ID AND uc.ID=ta.fk_profiloutentecittadino" +
	                    " group by rs.nome_servizio " +
	                    " order by ta.ragione_sociale asc;";
			 
			 
			List<RichiesteServiziAzienda> audits = this.jdbcTemplate.query(query, new RichiesteServiziAziendaMapper());
		 
			if( logger.isDebugEnabled() )
			{
				logger.debug("SQL [{}] RichiesteServiziAzienda TROVATI [{}]", query, (audits != null ? audits.size() : "0"));
			}			
			return audits;
		}
		catch (Exception e)
		{
			String msg = "Errore nel recupero RichiesteServiziAzienda";
			logger.error(msg,e);
			throw new OsaApiDbException(msg, e);
		}
		 	
	}
	
	
	public List<RichiesteServiziAzienda> getListRichiesteServiziAziendaConTotale() throws Exception 
	{
		try 
		{
 
			 String query="SELECT  count(rs.nome_servizio) as totale, rs.nome_servizio, ta.ragione_sociale, ta.partita_iva " +
	                    " FROM tb_richiesta_servizio rs,tb_fascicolo_utente fu,tb_profilo_utente_cittadino uc,tb_azienda ta " +
	                    " where rs.fk_fascicolo=fu.ID " +
	                    " AND fu.fk_cittadino=uc.ID AND ta.fk_profiloutentecittadino=uc.ID" +
	                    " group by rs.nome_servizio " +
	                    " order by ta.ragione_sociale asc;";	
			 
			 
			List<RichiesteServiziAzienda> audits = this.jdbcTemplate.query(query, new RichiesteServiziAziendaMapper());
		 
			if( logger.isDebugEnabled() )
			{
				logger.debug("SQL [{}] RichiesteServiziAzienda TROVATI [{}]", query, (audits != null ? audits.size() : "0"));
			}			
			return audits;
		}
		catch (Exception e)
		{
			String msg = "Errore nel recupero RichiesteServiziAzienda";
			logger.error(msg,e);
			throw new OsaApiDbException(msg, e);
		}
		 	
	}
	
	
	public List<RichiesteServiziUtente> getListRichiesteServiziUtente() throws Exception 
	{
		 
		 
		try 
		{
			
			 String query="SELECT  count(rs.nome_servizio) as totale, rs.nome_servizio as servizio, CONCAT(ta.ragione_sociale,'  ',ta.partita_iva) as user " +
	                    " FROM tb_richiesta_servizio rs,tb_fascicolo_utente fu,tb_profilo_utente_cittadino uc,tb_azienda ta " +
	                    " where rs.fk_fascicolo=fu.ID " +
	                    " AND fu.fk_cittadino=uc.ID AND ta.fk_profiloutentecittadino=uc.ID" +
	                    " group by rs.nome_servizio " +
	                    " order by ta.user asc;";		
 
//	       String query="SELECT count(rs.nome_servizio) as totale, rs.nome_servizio as servizio, CONCAT(uc.cognome ,' ' ,uc.nome) as user " +
//	                    " FROM tb_richiesta_servizio rs,tb_fascicolo_utente fu,tb_profilo_utente_cittadino uc " +
//	                    " where rs.fk_fascicolo=fu.ID " +
//	                    " AND fu.fk_cittadino=uc.ID " +
//	                    " group by rs.fk_fascicolo " +
//	                    " order by user asc;";
			 
			 
			List<RichiesteServiziUtente> audits = this.jdbcTemplate.query(query, new RichiesteServiziUtenteMapper());
		 
			if( logger.isDebugEnabled() )
			{
				logger.debug("SQL [{}] RichiesteServiziUtente TROVATI [{}]", query, (audits != null ? audits.size() : "0"));
			}			
			return audits;
		}
		catch (Exception e)
		{
			String msg = "Errore nel recupero RichiesteServiziUtente";
			logger.error(msg,e);
			throw new OsaApiDbException(msg, e);
		}
		 	
	}
	public List<TotaleAccessiServiziPerData> getRichiesteServiziPeData(String dfrom,String dto) throws Exception 
	{
		 
		 
		try 
		{
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT count(rs.nome_servizio) as totale, rs.nome_servizio, DATE_FORMAT(rs.data_richiesta, '%d-%m-%Y') as datarichiesta, concat(uc.cognome,' ', uc.nome) as utente,uc.ID as coduser " + 
					"FROM tb_richiesta_servizio rs,tb_fascicolo_utente fu,tb_profilo_utente_cittadino uc " + 
					"where rs.fk_fascicolo=fu.ID " + 
					"AND fu.fk_cittadino=uc.ID " + 
					"AND rs.data_richiesta >= :dfrom AND rs.data_richiesta  <= :dto " + 
					"group by rs.nome_servizio,datarichiesta " + 
					"order by datarichiesta asc;");
			
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("dfrom", dfrom);
			parameters.put("dto", dto);
			 
			List<TotaleAccessiServiziPerData> audits = this.jdbcTemplate.query(sb.toString(), parameters, new TotaleAccessiServiziPerDataMapper());
		 
			if( logger.isDebugEnabled() )
			{
				logger.debug("SQL [{}] PARAMETRI [{}] getRichiesteServiziPeData TROVATI [{}]", sb.toString(), parameters,(audits != null ? audits.size() : "0"));
			}			
			return audits;
		}
		catch (Exception e)
		{
			String msg = "Errore nel recupero getRichiesteServiziPeData";
			logger.error(msg,e);
			throw new OsaApiDbException(msg, e);
		}
		 
	}
	
	public List<RichiesteServiziPerAzienda> getRichiesteServiziPerAzienda(String cIdUser) throws Exception 
	{
		 
		 
		try 
		{
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT count(rs.nome_servizio) as totale, rs.nome_servizio, " + 
					"DATE_FORMAT(rs.data_richiesta, '%d-%m-%Y') as datarichiesta , " + 
					"ta.ragione_sociale, ta.partita_iva, " + 
					"uc.ID as coduser " + 
					"FROM " + 
					"tb_richiesta_servizio rs, " + 
					"tb_fascicolo_utente fu, " + 
					"tb_profilo_utente_cittadino uc , " + 
					"tb_azienda ta " + 
					"where rs.fk_fascicolo=fu.ID and " + 
					"uc.ID=ta.fk_profiloutentecittadino " + 
					"AND fu.fk_cittadino=uc.ID "); 
				if(cIdUser!=null && !cIdUser.isEmpty())
			 sb.append(" AND uc.ID =:cIdUser ");
			 sb.append("group by rs.nome_servizio,datarichiesta order by ta.ragione_sociale, datarichiesta asc;");
			
			Map<String, Object> parameters = new HashMap<String, Object>();
			if(cIdUser!=null && !cIdUser.isEmpty())
			parameters.put("cIdUser", cIdUser);
			 
			 
			List<RichiesteServiziPerAzienda> audits = this.jdbcTemplate.query(sb.toString(), parameters, new RichiesteServiziPerAziendaMapper());
		 
			if( logger.isDebugEnabled() )
			{
				logger.debug("SQL [{}] PARAMETRI [{}] getRichiesteServiziPerAzienda TROVATI [{}]", sb.toString(), parameters,(audits != null ? audits.size() : "0"));
			}			
			return audits;
		}
		catch (Exception e)
		{
			String msg = "Errore nel recupero getRichiesteServiziPerAzienda";
			logger.error(msg,e);
			throw new OsaApiDbException(msg, e);
		}
		 
	}
	
	////select ragione_sociale,partita_iva, tipo, fk_profiloutentecittadino as iduser FROM tb_azienda where attiva=1 order by ragione_sociale asc;

	public List<InfoAzienda> getInfoAzienda() throws Exception 
	{
		 
		 
		try 
		{
			StringBuilder sb = new StringBuilder();
			sb.append("select ragione_sociale,partita_iva, tipo, fk_profiloutentecittadino as iduser FROM tb_azienda where attiva=1 order by ragione_sociale asc;"); 
				 
			 
			List<InfoAzienda> audits = this.jdbcTemplate.query(sb.toString(),  new InfoAziendaMapper());
		 
			if( logger.isDebugEnabled() )
			{
				logger.debug("SQL [{}]  getInfoAzienda TROVATI [{}]", sb.toString(),(audits != null ? audits.size() : "0"));
			}			
			return audits;
		}
		catch (Exception e)
		{
			String msg = "Errore nel recupero getInfoAzienda";
			logger.error(msg,e);
			throw new OsaApiDbException(msg, e);
		}
		 
	}
	
	public List<CambioResidenzaPartenzeArrivi> getCambioResidenzaPartenzeArrivi() throws Exception 
	{
		 //totale, partenza, codIstatPartenza, arrivo, codIstatArrivo
		 
		try 
		{
			StringBuilder sb = new StringBuilder();
			sb.append("select count(c.denominazione) as totale,c.denominazione as partenza,   a.valore_attributo as codIstatPartenza ," + 
					"((select distinct z.denominazione " + 
					"from tb_attributi_servizio f,tb_comune z " + 
					"where f.nome_attributo='carrivo' and z.codiceIstatAN=f.valore_attributo and f.id_transazione=a.id_transazione " + 
					")) as arrivo, " + 
					"((select distinct p.codiceIstatAN " + 
					"from tb_attributi_servizio g,tb_comune p " + 
					"where g.nome_attributo='carrivo' and p.codiceIstatAN=g.valore_attributo and g.id_transazione=a.id_transazione " + 
					")) as codIstatArrivo " + 
					"from tb_attributi_servizio a,tb_comune c " + 
					"where a.nome_attributo='cpartenza' and c.codiceIstatAN=a.valore_attributo " + 
					"group by c.denominazione,arrivo;"); 
				 
			 
			List<CambioResidenzaPartenzeArrivi> audits = this.jdbcTemplate.query(sb.toString(),  new CambioResidenzaPartenzeArriviMapper());
		 
			if( logger.isDebugEnabled() )
			{
				logger.debug("SQL [{}]  getCambioResidenzaPartenzeArrivi TROVATI [{}]", sb.toString(),(audits != null ? audits.size() : "0"));
			}			
			return audits;
		}
		catch (Exception e)
		{
			String msg = "Errore nel recupero getCambioResidenzaPartenzeArrivi";
			logger.error(msg,e);
			throw new OsaApiDbException(msg, e);
		}
		 
	}
	
	
}