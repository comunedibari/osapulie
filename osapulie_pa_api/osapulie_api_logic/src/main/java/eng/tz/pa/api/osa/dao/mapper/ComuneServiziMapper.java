package eng.tz.pa.api.osa.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import eng.tz.pa.api.osa.dao.models.ComuneServizi;

public class ComuneServiziMapper implements RowMapper<ComuneServizi> {

	@Override
	public ComuneServizi mapRow(ResultSet res, int rowNum) throws SQLException {
		 
		ComuneServizi reg = new ComuneServizi();
		reg.setNome(res.getString("nome"));
		reg.setDescrizione(res.getString("descrizione"));
		reg.setServizio(res.getString("servizio"));

		return reg;
	}

}


 