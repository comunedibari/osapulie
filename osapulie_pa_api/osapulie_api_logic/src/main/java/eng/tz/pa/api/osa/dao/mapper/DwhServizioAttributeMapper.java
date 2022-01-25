package eng.tz.pa.api.osa.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import eng.tz.pa.api.osa.dao.models.DwhServizioAttribute;

public class DwhServizioAttributeMapper implements RowMapper<DwhServizioAttribute> {

	@Override
	public DwhServizioAttribute mapRow(ResultSet res, int rowNum) throws SQLException {
		 
		DwhServizioAttribute reg = new DwhServizioAttribute();
		reg.setCittadino_autenticazione_forte(res.getBoolean("cittadino_autenticazione_forte"));
		reg.setCittadino_canale_autenticazione(res.getString("cittadino_canale_autenticazione"));
		reg.setCittadino_comune(res.getString("cittadino_comune"));
		reg.setCittadino_eta(res.getString("cittadino_eta"));
		reg.setCittadino_livello_autenticazione(res.getInt("cittadino_livello_autenticazione"));
		reg.setCittadino_provincia(res.getString("cittadino_provincia"));
		reg.setCittadino_regione(res.getString("cittadino_regione"));
		reg.setCittadino_regione(res.getString("cittadino_regione"));
		reg.setCittadino_sesso(res.getString("cittadino_sesso"));
		reg.setCittadino_userid(res.getString("cittadino_userid"));
		reg.setComune(res.getString("comune"));
		reg.setData_evento(res.getDate("data_evento"));
		reg.setEnte_comune(res.getString("ente_comune"));
		reg.setEnte_partita_iva(res.getString("ente_partita_iva"));
		reg.setEnte_provincia(res.getString("ente_provincia"));
		reg.setEnte_regione(res.getString("ente_regione"));
		reg.setEnte_tipo(res.getString("ente_tipo"));
		reg.setEnte_userid(res.getString("ente_userid"));
		reg.setHost_app(res.getString("host_app"));
		reg.setServizio_autenticazone(res.getBoolean("servizio_autenticazone"));
		reg.setServizio_code(res.getString("servizio_code"));
		reg.setServizio_data_richiesta(res.getDate("servizio_data_richiesta"));
		reg.setServizio_fine(res.getDate("servizio_fine"));
		reg.setServizio_inizio(res.getDate("servizio_inizio"));
		reg.setServizio_nome(res.getString("servizio_nome"));
		reg.setServizio_parametro1(res.getString("servizio_parametro1"));
		reg.setServizio_parametro2(res.getString("servizio_parametro2"));
		reg.setServizio_parametro3(res.getString("servizio_parametro3"));
		reg.setServizio_protocollo(res.getString("servizio_protocollo"));
		reg.setServizio_uri(res.getString("servizio_uri"));
		reg.setUuid(res.getString("uuid"));

		return reg;
	}

}


 