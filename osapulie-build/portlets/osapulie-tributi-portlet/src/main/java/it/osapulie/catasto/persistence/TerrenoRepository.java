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

import it.osapulie.catasto.domain.Terreno;

@Service("terrenoRepository")
public class TerrenoRepository {

	protected Logger log = LoggerFactory.getLogger(TerrenoRepository.class.getName());

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

	private final String QUERY_TERRENI_BY_CF = "SELECT terreno.classe as classe, " + "	terreno.foglio as foglio, " + "terreno.particella as particella, " + "terreno.subalterno as subalterno, "
			+ "terreno.superficie as superficie, " + "terreno.deduzione as deduzione, " + "terreno.reddito_dominicale as redditoDominicale, " + "terreno.reddito_agrario as redditoAgrario "
			+ "from sportello_catastale.terreno terreno "
			+ "join sportello_catastale.titolarita titolarita on (titolarita.id_immobile=terreno.id_immobile and titolarita.tipo_immobile = terreno.tipo_immobile) "
			+ "join sportello_catastale.soggetto soggetto on (soggetto.id_soggetto=titolarita.id_soggetto) " + "where soggetto.codice_fiscale=?;";

	public List<Terreno> getTerreniByCf(String cf) {

		List<Terreno> lista = new ArrayList<Terreno>();
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName(driver).newInstance();
			connect = (Connection) DriverManager.getConnection(url + dbName, userName, password);

			preparedStatement = connect.prepareStatement(QUERY_TERRENI_BY_CF);
			preparedStatement.setString(1, cf);
			resultSet = preparedStatement.executeQuery();
			lista = getList(resultSet);

		}
		catch (Exception e) {
			log.error("getTerreniByCf :: " + e.getMessage(), e);
		}
		finally {
			close();
		}

		return lista;
	}

	private List<Terreno> getList(ResultSet resultSet) throws SQLException {
		List<Terreno> lista = new ArrayList<Terreno>();

		while (resultSet.next()) {
			Terreno t = new Terreno();
			t.setClasse(resultSet.getString("classe"));
			t.setFoglio(resultSet.getString("foglio"));
			t.setSubalterno(resultSet.getString("subalterno"));
			t.setParticella(resultSet.getString("particella"));
			t.setSuperficie(resultSet.getString("superficie"));
			t.setDeduzione(resultSet.getString("deduzione"));
			t.setRedditoDominicale(resultSet.getDouble("redditoDominicale"));
			t.setRedditoAgrario(resultSet.getDouble("redditoAgrario"));
			lista.add(t);
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
