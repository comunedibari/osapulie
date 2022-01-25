package eng.tz.pa.api.osa.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import eng.tz.pa.api.osa.dao.models.ServiziRichiestiPercCompletati;
import eng.tz.pa.api.osa.dao.models.TempoMedioServiziRichiesti;

public class ServiziRichiestiPercCompletatiMapper implements RowMapper<ServiziRichiestiPercCompletati> {

	@Override
	public ServiziRichiestiPercCompletati mapRow(ResultSet res, int rowNum) throws SQLException {

		ServiziRichiestiPercCompletati reg = new ServiziRichiestiPercCompletati();
		reg.setCompletati(res.getString("COMPLETATI"));
		reg.setIncompleti(res.getString("INCOMPLETI"));
		reg.setPercentuale_completamento(res.getString("PERCENTUALE_COMPLETAMENTO"));
		reg.setServizio_code(res.getString("COD_SERVIZIO"));
		reg.setServizio_nome(res.getString("NOME_SERVIZIO"));
		reg.setTotale_richieste(res.getString("TOTALE_RICHIESTI"));
		return reg;
	}

}


 