package it.eng.tz.area.vasta.protocollo.spring.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.eng.tz.area.vasta.protocollo.spring.dao.models.RegistroAuditModel;

public class RegistroAuditModMapper implements RowMapper<RegistroAuditModel> {


	@Override
	public RegistroAuditModel mapRow(ResultSet res, int rowNum) throws SQLException {
		 
		RegistroAuditModel reg = new RegistroAuditModel();
		reg.setId(res.getInt("ID"));
		reg.setPuntatoreAlfresco(res.getString("puntatore_alfresco"));
		reg.setDataCreazione(res.getDate("data_creazione"));
		reg.setFlagGenerato(res.getString("flag_generato"));
		reg.setPathFileSystem(res.getString("path_filesystem"));
		reg.setFileName(res.getString("file_name"));
		reg.setDestinatario(res.getString("destinatario"));
		reg.setChecksum(res.getString("checksum"));
		reg.setCodiceAmministrazione(res.getString("codice_amministrazione"));
		reg.setCodiceAoo(res.getString("codice_aoo"));
		reg.setSoggettoProduttore2(res.getString("soggetto_produttore2"));
		reg.setSoggettoProduttore(res.getString("soggetto_produttore"));
		reg.setResponsabile(res.getString("responsabile"));
		reg.setOggetto(res.getString("oggetto"));
		reg.setCodiceRegistro(res.getString("codice_registro"));
		reg.setProgressivo(res.getInt("progressivo"));
		reg.setAnno(res.getString("anno"));
	 
		return reg;
	}

}
