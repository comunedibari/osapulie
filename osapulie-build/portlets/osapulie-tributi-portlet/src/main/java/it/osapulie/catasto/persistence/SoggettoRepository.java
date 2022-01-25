package it.osapulie.catasto.persistence;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.Connection;

import it.osapulie.catasto.domain.Soggetto;

@Service("soggettoRepository")
public class SoggettoRepository {

	protected Logger log = LoggerFactory.getLogger(SoggettoRepository.class.getName());

	private Connection connect = null;
	// private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	private final String url = "jdbc:mysql://localhost:3306/";
	private final String dbName = "sportello_catastale";
	private final String driver = "com.mysql.jdbc.Driver";
	private final String userName = "osapulie";
	private final String password = "osapulie11";

	public Soggetto getSoggettoByCf(String cf) {

		Soggetto soggetto = new Soggetto();
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName(driver).newInstance();
			connect = (Connection) DriverManager.getConnection(url + dbName, userName, password);

			preparedStatement = connect.prepareStatement("SELECT * FROM sportello_catastale.soggetto WHERE codice_fiscale=?;");
			preparedStatement.setString(1, cf);
			resultSet = preparedStatement.executeQuery();
			soggetto = getSoggetto(resultSet);

		}
		catch (Exception e) {
			log.error("getSoggettoByCf :: " + e.getMessage(), e);
		}
		finally {
			close();
		}

		return soggetto;
	}

	private Soggetto getSoggetto(ResultSet resultSet) throws SQLException {
		Soggetto result = new Soggetto();

		while (resultSet.next()) {
			result.setCodiceAmministrativo(resultSet.getString("cod_amministrativo"));
			result.setCodiceFiscale(resultSet.getString("codice_fiscale"));
			result.setCognome(resultSet.getString("cognome"));
			result.setNome(resultSet.getString("nome"));
			result.setDataNascita(resultSet.getString("data_nascita"));
			result.setSesso(resultSet.getString("sesso"));
			result.setSezione(resultSet.getString("sezione"));
		}
		return result;

	}

	public int getIdSoggettoByCf(String cf) {

		int id = 0;
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName(driver).newInstance();
			connect = (Connection) DriverManager.getConnection(url + dbName, userName, password);

			preparedStatement = connect.prepareStatement("SELECT id_soggetto FROM sportello_catastale.soggetto WHERE codice_fiscale=?;");
			preparedStatement.setString(1, cf);
			resultSet = preparedStatement.executeQuery();
			id = resultSet.getInt("id_soggetto");

		}
		catch (Exception e) {
			log.error("getIdSoggettoByCf :: " + e.getMessage(), e);
		}
		finally {
			close();
		}

		return id;
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
