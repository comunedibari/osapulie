package eng.tz.pa.api.osa.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import eng.tz.pa.api.osa.dao.models.TotaleAccessiServizi;
import eng.tz.pa.api.osa.dao.models.TotaleServiziComune;

public class TotaleAccessiServiziMapper implements RowMapper<TotaleAccessiServizi> {

	@Override
	public TotaleAccessiServizi mapRow(ResultSet res, int rowNum) throws SQLException {
		 
		TotaleAccessiServizi reg = new TotaleAccessiServizi();
		reg.setDescrizione(res.getString("descrizione"));
		reg.setTotale(res.getLong("totale"));

		return reg;
	}

}


 