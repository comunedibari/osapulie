package it.eng.tz.area.vasta.protocollo.spring.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.eng.tz.area.vasta.protocollo.spring.dao.models.Audit;

public class AuditMapper implements RowMapper<Audit> {

	@Override
	public Audit mapRow(ResultSet res, int rowNum) throws SQLException {
		 
		Audit reg = new Audit();
		reg.setId(res.getLong("ID"));
		reg.setUserCod(res.getString("userCod"));
		reg.setFileName(res.getString("file_name"));
		reg.setPathFilesystem(res.getString("path_filesystem"));
		reg.setChecksum(res.getString("checksum"));
		reg.setDataCreazione(res.getDate("data_creazione"));
		reg.setDataUltimaModifica(res.getDate("data_ultima_modifica"));
		reg.setCodiceRegistro(res.getString("codice_registro")); 
		reg.setStato(res.getString("stato"));
		reg.setCons(res.getString("cons"));
		
		return reg;
	}

}


 