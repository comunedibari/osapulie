package eng.tz.pa.api.osa.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import eng.tz.pa.api.osa.dao.models.CambioResidenzaPartenzeArrivi;
import eng.tz.pa.api.osa.dao.models.InfoAzienda;
import eng.tz.pa.api.osa.dao.models.RichiesteServiziAzienda;
import eng.tz.pa.api.osa.dao.models.RichiesteServiziPerAzienda;
import eng.tz.pa.api.osa.dao.models.RichiesteServiziUtente;

public class CambioResidenzaPartenzeArriviMapper implements RowMapper<CambioResidenzaPartenzeArrivi> {
 
	@Override
	public CambioResidenzaPartenzeArrivi mapRow(ResultSet res, int rowNum) throws SQLException {
		 
		CambioResidenzaPartenzeArrivi reg = new CambioResidenzaPartenzeArrivi();
		reg.setTotale(res.getLong("totale"));
		reg.setCod_istat_partenza(res.getString("codIstatPartenza"));
		reg.setComune_partenza(res.getString("partenza"));
		reg.setCod_istat_arrivo(res.getString("codIstatArrivo"));
		reg.setComune_arrivo(res.getString("arrivo"));
		return reg;
	}

}


 