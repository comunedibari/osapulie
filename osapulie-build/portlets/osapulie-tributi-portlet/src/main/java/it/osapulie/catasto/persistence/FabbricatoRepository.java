package it.osapulie.catasto.persistence;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.Connection;

import it.osapulie.catasto.domain.Fabbricato;

@Service("fabbricatoRepository")
public class FabbricatoRepository {

	protected Logger log = LoggerFactory.getLogger(FabbricatoRepository.class.getName());

	private Connection connect = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	@Value("#{applicationProperties.url}")
	private String url;
	@Value("#{applicationProperties.dbName}")
	private String dbName;
	@Value("#{applicationProperties.driver}")
	private String driver;
	@Value("#{applicationProperties.userName}")
	private String userName;
	@Value("#{applicationProperties.password}")
	private String password;

	private final String QUERY_FABBRICATO_BY_CF = "select " + "fabbricato.sezione as sezione, " + "fabbricato.sezione_urbana as sezioneUrbana, " + "fabbricato.foglio as foglio, "
			+ "fabbricato.particella as particella, " + "fabbricato.subalterno as subalterno, " + "fabbricato.zona as zona, " + "fabbricato.categoria as categoria, "
			+ "fabbricato.classe as classe,  " + "fabbricato.consistenza as consistenza, " + "fabbricato.superficie as superficie, " + "fabbricato.rendita as rendita, "
			+ "fabbricato.indirizzo as indirizzo, " + "fabbricato.civico as civico " + "from sportello_catastale.fabbricato fabbricato "
			+ "join sportello_catastale.titolarita titolarita on (fabbricato.id_immobile=titolarita.id_immobile and fabbricato.tipo_immobile=titolarita.tipo_immobile) "
			+ "join sportello_catastale.soggetto soggetto on (soggetto.id_soggetto=titolarita.id_soggetto) " + "where soggetto.codice_fiscale=?; ";

	public List<Fabbricato> getFabbricatiByCf(String cf) {

		List<Fabbricato> lista = new ArrayList<Fabbricato>();
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName(driver).newInstance();
			connect = (Connection) DriverManager.getConnection(url + dbName, userName, password);

			preparedStatement = connect.prepareStatement(QUERY_FABBRICATO_BY_CF);
			preparedStatement.setString(1, cf);
			resultSet = preparedStatement.executeQuery();
			lista = getList(resultSet);

		}
		catch (Exception e) {
			log.error("getFabbricatiByCf :: " + e.getMessage(), e);
		}
		finally {
			close();
		}

		return lista;
	}

	private List<Fabbricato> getList(ResultSet resultSet) throws SQLException {
		List<Fabbricato> lista = new ArrayList<Fabbricato>();

		while (resultSet.next()) {
			Fabbricato f = new Fabbricato();
			f.setClasse(resultSet.getString("classe"));
			f.setFoglio(resultSet.getString("foglio"));
			f.setParticella(resultSet.getString("particella"));
			f.setSubalterno(resultSet.getString("subalterno"));
			f.setIndirizzo(resultSet.getString("indirizzo"));
			f.setCivico(resultSet.getString("civico"));
			f.setSezioneUrbana(resultSet.getString("sezioneUrbana"));
			f.setZona(resultSet.getString("zona"));
			f.setCategoria(resultSet.getString("categoria"));
			f.setConsistenza(resultSet.getInt("consistenza"));
			f.setRendita(resultSet.getDouble("rendita"));
			f.setSuperficie(resultSet.getInt("superficie"));
			lista.add(f);
		}

		return lista;

	}

	// You need to close the resultSet
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (connect != null) {
				connect.close();
			}
		}
		catch (Exception e) {
			log.error("close :: " + e.getMessage(), e);
		}
	}
}
