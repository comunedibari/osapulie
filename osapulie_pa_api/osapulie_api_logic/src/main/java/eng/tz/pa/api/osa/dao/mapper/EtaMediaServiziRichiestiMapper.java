package eng.tz.pa.api.osa.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import eng.tz.pa.api.osa.dao.models.ComuneServizi;
import eng.tz.pa.api.osa.dao.models.EtaMediaServiziRichiesti;

public class EtaMediaServiziRichiestiMapper implements RowMapper<EtaMediaServiziRichiesti> {

	@Override
	public EtaMediaServiziRichiesti mapRow(ResultSet res, int rowNum) throws SQLException {
		 
		EtaMediaServiziRichiesti reg = new EtaMediaServiziRichiesti();
		reg.setEta(res.getString("eta"));
		reg.setSesso(res.getString("sesso"));
		reg.setServizio_code(res.getString("servizio_code"));
		reg.setServizio_nome(res.getString("servizio_nome"));

		return reg;
	}

}


 