package eng.tz.pa.api.osa.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import eng.tz.pa.api.osa.dao.models.RichiesteServiziUtente;

public class RichiesteServiziUtenteMapper implements RowMapper<RichiesteServiziUtente> {

	@Override
	public RichiesteServiziUtente mapRow(ResultSet res, int rowNum) throws SQLException {
		 
		RichiesteServiziUtente reg = new RichiesteServiziUtente();
		reg.setTotale(res.getLong("totale"));
		reg.setServizio(res.getString("servizio"));
		reg.setUser(res.getString("user"));

		return reg;
	}

}


 