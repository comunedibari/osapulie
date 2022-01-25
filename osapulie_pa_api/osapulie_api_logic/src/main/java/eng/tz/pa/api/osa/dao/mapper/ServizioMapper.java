package eng.tz.pa.api.osa.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import eng.tz.pa.api.osa.dao.models.ComuneServizi;
import eng.tz.pa.api.osa.dao.models.Servizio;

public class ServizioMapper implements RowMapper<Servizio> {

	@Override
	public Servizio mapRow(ResultSet res, int rowNum) throws SQLException {
		 
		Servizio reg = new Servizio();
		reg.setServizio_id(res.getString("servizio_id"));
		reg.setServizio_code(res.getString("servizio_code"));
		reg.setServizio_nome(res.getString("servizio_nome"));

		return reg;
	}

}


 