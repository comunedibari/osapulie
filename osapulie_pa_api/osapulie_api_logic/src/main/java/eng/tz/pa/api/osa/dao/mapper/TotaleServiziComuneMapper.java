package eng.tz.pa.api.osa.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import eng.tz.pa.api.osa.dao.models.TotaleServiziComune;

public class TotaleServiziComuneMapper implements RowMapper<TotaleServiziComune> {

	@Override
	public TotaleServiziComune mapRow(ResultSet res, int rowNum) throws SQLException {
		 
		TotaleServiziComune reg = new TotaleServiziComune();
		reg.setDescrizione(res.getString("descrizione"));
		reg.setTotale(res.getLong("totale"));

		return reg;
	}

}


 