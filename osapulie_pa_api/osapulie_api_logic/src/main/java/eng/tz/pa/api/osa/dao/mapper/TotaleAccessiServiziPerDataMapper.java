package eng.tz.pa.api.osa.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import eng.tz.pa.api.osa.dao.models.TotaleAccessiServizi;
import eng.tz.pa.api.osa.dao.models.TotaleAccessiServiziPerData;
import eng.tz.pa.api.osa.dao.models.TotaleServiziComune;

public class TotaleAccessiServiziPerDataMapper implements RowMapper<TotaleAccessiServiziPerData> {

	@Override
	public TotaleAccessiServiziPerData mapRow(ResultSet res, int rowNum) throws SQLException {
		 
		TotaleAccessiServiziPerData reg = new TotaleAccessiServiziPerData();
		reg.setData(res.getString("datarichiesta"));
		reg.setNome_Servizio(res.getString("nome_servizio"));
		reg.setTotale(res.getLong("totale"));

		return reg;
	}

}


 