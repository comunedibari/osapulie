package eng.tz.pa.api.osa.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import eng.tz.pa.api.osa.dao.models.ServiziRichiestiGeolocalizzazione;
import eng.tz.pa.api.osa.dao.models.ServiziRichiestiPercCompletati;
import eng.tz.pa.api.osa.dao.models.TempoMedioServiziRichiesti;

public class ServiziRichiestiGeolocalizzazioneMapper implements RowMapper<ServiziRichiestiGeolocalizzazione> {

	@Override
	public ServiziRichiestiGeolocalizzazione mapRow(ResultSet res, int rowNum) throws SQLException {
	
		ServiziRichiestiGeolocalizzazione reg = new ServiziRichiestiGeolocalizzazione();
		reg.setAnno(res.getString("ANNO"));
		reg.setCap_locale(res.getString("CAP"));
		reg.setCitta_locale(res.getString("CITTA"));
		reg.setLatitudine(res.getString("LATITUDINE"));
		reg.setLongitudine(res.getString("LONGITUDINE"));
		reg.setPercentuale_locale(res.getString("PERCENTUALE_LOCALE"));
		reg.setRegione_locale(res.getString("REGIONE"));
		reg.setServizio_code(res.getString("COD_SERVIZIO"));
		reg.setServizio_nome(res.getString("SERVIZIO_NOME"));
		reg.setTotale_locale(res.getString("TOTALE"));
		reg.setServizio_totale_richieste(res.getString("TOTALE_SERVIZIO"));
	 
		
		return reg;
	}

}


 