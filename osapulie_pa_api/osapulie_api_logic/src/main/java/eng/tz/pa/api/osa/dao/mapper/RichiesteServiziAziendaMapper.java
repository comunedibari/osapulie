package eng.tz.pa.api.osa.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import eng.tz.pa.api.osa.dao.models.RichiesteServiziAzienda;
import eng.tz.pa.api.osa.dao.models.RichiesteServiziUtente;

public class RichiesteServiziAziendaMapper implements RowMapper<RichiesteServiziAzienda> {

	@Override
	public RichiesteServiziAzienda mapRow(ResultSet res, int rowNum) throws SQLException {
		 
		RichiesteServiziAzienda reg = new RichiesteServiziAzienda();
		reg.setCoduser(res.getString("coduser"));
		reg.setServizio(res.getString("nome_servizio"));
		reg.setData_richiesta(res.getString("data_richiesta"));
		reg.setRagione_sociale(res.getString("ragione_sociale"));
		return reg;
	}

}


 