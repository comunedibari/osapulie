package it.osapulie.pdds.database;

/*
 * import java.io.BufferedReader; import java.io.File; import java.io.FileReader;
 * 
 * import java.io.ObjectInputStream; import java.net.MalformedURLException; import java.net.URL;
 */
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

//import org.apache.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * La classe effettua la connessione a database servendosi di un file dove è settato l'istat del
 * comune e i parametri di connessione
 * 
 * @author Michele
 * 
 */

public class ConnectionManager {

	Connection conn;
	String istatcom;
	ArrayList comm = new ArrayList();
	private static Logger log = LoggerFactory.getLogger(ConnectionManager.class);

	public ArrayList parametri() throws IOException {

		ResourceBundle re = ResourceBundle.getBundle("parameters");
		comm.add(0, re.getString("MY_DRIVER"));
		comm.add(1, re.getString("MY_URL"));
		comm.add(2, re.getString("LOGIN"));
		comm.add(3, re.getString("PASSWD"));
		comm.add(4, re.getString("ISTAT"));

		return comm;

	}

	public ConnectionManager() throws SQLException {
		ArrayList arr = new ArrayList();
		try {
			arr = parametri();
		}
		catch (IOException e1) {

			e1.printStackTrace();
		}

		String MY_DRIVER = arr.get(0).toString().trim();
		String MY_URL = arr.get(1).toString().trim();
		String LOGIN = arr.get(2).toString().trim();
		String PASSWD = arr.get(3).toString().trim();
		String ISTAT = arr.get(4).toString().trim();
		setIstatcom(ISTAT);

		try {
			Class.forName(MY_DRIVER);
			conn = DriverManager.getConnection(MY_URL, LOGIN, PASSWD);
			conn.setReadOnly(false);
			conn.setAutoCommit(true);
		}
		catch (ClassNotFoundException e) {
			log.error("Driver NON TROVATO");
		}
		catch (SQLException e) {
			e.printStackTrace();
			log.error("Errore nei parametri");
		}
	}

	public Connection getConnection() {
		return conn;
	}

	/**
	 * @return Returns the istatcom.
	 */
	public String getIstatcom() {
		return istatcom;
	}

	/**
	 * @param istatcom The istatcom to set.
	 */
	public void setIstatcom(String istatcom) {
		this.istatcom = istatcom;
	}
}