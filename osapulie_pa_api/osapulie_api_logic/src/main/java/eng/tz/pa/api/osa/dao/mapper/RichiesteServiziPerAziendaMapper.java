package eng.tz.pa.api.osa.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import eng.tz.pa.api.osa.dao.models.RichiesteServiziAzienda;
import eng.tz.pa.api.osa.dao.models.RichiesteServiziPerAzienda;
import eng.tz.pa.api.osa.dao.models.RichiesteServiziUtente;

public class RichiesteServiziPerAziendaMapper implements RowMapper<RichiesteServiziPerAzienda> {

	@Override
	public RichiesteServiziPerAzienda mapRow(ResultSet res, int rowNum) throws SQLException {
		 
		RichiesteServiziPerAzienda reg = new RichiesteServiziPerAzienda();
		reg.setTotale(res.getLong("totale"));
		reg.setServizio(res.getString("nome_servizio"));
		reg.setData_richiesta(res.getString("datarichiesta"));
		reg.setRagione_sociale(res.getString("ragione_sociale"));
		reg.setPiva(res.getString("partita_iva"));
		return reg;
	}

}


 