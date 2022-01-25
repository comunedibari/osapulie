package eng.tz.pa.api.osa.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import eng.tz.pa.api.osa.dao.models.ServiziRichiestiPerFasciaDiEta;
import eng.tz.pa.api.osa.dao.models.ServiziRichiestiPercCompletati;
import eng.tz.pa.api.osa.dao.models.TempoMedioServiziRichiesti;

public class ServiziRichiestiPerFasciaDiEtaMapper implements RowMapper<ServiziRichiestiPerFasciaDiEta> {

	@Override
	public ServiziRichiestiPerFasciaDiEta mapRow(ResultSet res, int rowNum) throws SQLException {

		
		ServiziRichiestiPerFasciaDiEta reg = new ServiziRichiestiPerFasciaDiEta();
		reg.setAnno(res.getLong("ANNO"));
		reg.setEta_ninore_18(res.getLong("ninore_18"));
		reg.setEta_oltre_60(res.getLong("oltre_60"));
		reg.setEta_tra_1839(res.getLong("tra_1839"));
		reg.setEta_tra_4059(res.getLong("tra_4059"));
		reg.setPercentuale_18(res.getLong("PERC_18"));
		reg.setPercentuale_1839(res.getLong("PERC_1839"));
		reg.setPercentuale_4059(res.getLong("PERC_4059"));
		reg.setPercentuale_60(res.getLong("PERC_60"));
		reg.setTotale_servizi(res.getLong("totale_servizi"));
		reg.setServizio_code(res.getString("COD_SERVIZIO"));
		reg.setServizio_nome(res.getString("NOME_SERVIZIO"));
		 
		return reg;
	}

}


 