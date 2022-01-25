package eng.tz.pa.api.osa.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import eng.tz.pa.api.osa.dao.models.TempoMedioServiziRichiesti;

public class TempoMedioServiziRichiestiMapper implements RowMapper<TempoMedioServiziRichiesti> {

	@Override
	public TempoMedioServiziRichiesti mapRow(ResultSet res, int rowNum) throws SQLException {
		 
		TempoMedioServiziRichiesti reg = new TempoMedioServiziRichiesti();
		reg.setTempo_medio(res.getString("tempo_medio"));
		reg.setServizio_code(res.getString("servizio_code"));
		reg.setServizio_nome(res.getString("servizio_nome"));

		return reg;
	}

}


 