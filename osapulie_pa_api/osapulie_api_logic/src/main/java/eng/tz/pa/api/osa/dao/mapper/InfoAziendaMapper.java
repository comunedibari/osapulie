package eng.tz.pa.api.osa.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import eng.tz.pa.api.osa.dao.models.InfoAzienda;
import eng.tz.pa.api.osa.dao.models.RichiesteServiziAzienda;
import eng.tz.pa.api.osa.dao.models.RichiesteServiziPerAzienda;
import eng.tz.pa.api.osa.dao.models.RichiesteServiziUtente;

public class InfoAziendaMapper implements RowMapper<InfoAzienda> {

	@Override
	public InfoAzienda mapRow(ResultSet res, int rowNum) throws SQLException {
		 
		InfoAzienda reg = new InfoAzienda();
		reg.setRagione_sociale(res.getString("ragione_sociale"));
		reg.setPartita_iva(res.getString("partita_iva"));
		reg.setTipo(res.getString("tipo"));
		reg.setIduser(res.getString("iduser"));
		return reg;
	}

}


 