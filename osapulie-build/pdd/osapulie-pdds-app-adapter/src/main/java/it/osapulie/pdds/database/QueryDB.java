/*
 * Created on 14-set-2006
 * 
 * TODO To change the template for this generated file go to Window - Preferences - Java - Code
 * Style - Code Templates
 */
package it.osapulie.pdds.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//import org.apache.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * La classe effettua le query relative a ciascun servizio
 *
 * @author Michele
 *
 */
public class QueryDB {

	private static Logger log = LoggerFactory.getLogger(QueryDB.class);

	public ArrayList foreignkey(String codicefiscale) {

		ArrayList res = new ArrayList();
		ConnectionManager manager = null;
		Connection c = null;

		ResultSet re = null;

		try {
			manager = new ConnectionManager();
		}
		catch (SQLException e1) {

			e1.printStackTrace();
		}
		c = manager.getConnection();

		try {
			String sql = "SELECT tipocitt, Statoci, FK_relpa, FK_istatcitt, FK_tipocert, Tifamig, Istatranas, Istatisnas, FK_istatres, FK_istatnasres, FK_istatved, FK_istattrved, FK_istatmat, FK_istattrmat FROM anagrafe WHERE    PK_codfiscale ='"
					+ codicefiscale + "'";

			PreparedStatement prepain = c.prepareStatement(sql);

			re = prepain.executeQuery(sql);

			int i = 0;
			while (re.next()) {
				res.add(i, re.getString("Tipocitt"));
				res.add(i + 1, re.getString("Statoci"));
				res.add(i + 2, re.getString("FK_relpa"));
				res.add(i + 3, comune(re.getString("FK_istatcitt")).get(0));
				res.add(i + 4, re.getString("FK_tipocert"));
				res.add(i + 5, comune(re.getString("Tifamig")).get(0));
				res.add(i + 6, re.getString("Istatranas"));
				res.add(i + 7, comune(re.getString("Istatisnas")).get(0));
				res.add(i + 8, comune(re.getString("FK_istatres")).get(0));
				res.add(i + 9, re.getString("FK_istatnasres"));
				res.add(i + 10, comune(re.getString("FK_istatved")).get(0));
				res.add(i + 11, comune(re.getString("FK_istattrved")).get(0));
				res.add(i + 12, comune(re.getString("FK_istatmat")).get(0));
				res.add(i + 13, comune(re.getString("FK_istattrmat")).get(0));

				i = i + 14;
			}
			ArrayList arr2 = new ArrayList();

			if (res.contains(null)) {
				for (int j = 0; j < res.size(); j++) {
					if (res.get(j) == null) {
						arr2.add(j, "");
					}
					else {
						arr2.add(j, res.get(j));
					}
				}
				res = arr2;
			}
			c.close();
			log.debug("RISULTATO QUERY DB  " + res);
			return res;

		}

		catch (Exception e) {
			log.error("ERRORE NELLA CLASSE QueryDB");
		}
		;
		return null;
	}

	public ArrayList indirizz(String codicefiscale) {
		ArrayList res = new ArrayList();
		ConnectionManager manager = null;
		Connection c = null;

		ResultSet re = null;

		try {
			manager = new ConnectionManager();
		}
		catch (SQLException e1) {

			e1.printStackTrace();
		}
		c = manager.getConnection();

		try {
			String sql = "SELECT * FROM indirizz WHERE PK_codfis ='" + codicefiscale + "'";
			PreparedStatement prepain = c.prepareStatement(sql);
			re = prepain.executeQuery(sql);
			int i = 0;
			while (re.next()) {
				res.add(i, re.getString("FK_covia"));
				res.add(i + 1, re.getString("Numciv"));
				res.add(i + 2, re.getString("Esponente"));
				res.add(i + 3, re.getString("Scala"));
				res.add(i + 4, re.getString("Interno"));
				res.add(i + 5, re.getString("Piano"));
				res.add(i + 6, re.getString("Datain"));
				res.add(i + 6, re.getString("Datafi"));

				i = i + 7;
			}
			ArrayList arr2 = new ArrayList();

			if (res.contains(null)) {
				for (int j = 0; j < res.size(); j++) {
					if (res.get(j) == null) {
						arr2.add(j, "");
					}
					else {
						arr2.add(j, res.get(j));
					}
				}
				res = arr2;
			}
			c.close();
			log.debug("RISULTATO QUERY DB  " + res);
			return res;

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		;
		return null;
	}

	public ArrayList nazione(String codicenaz) {
		ArrayList res = new ArrayList();
		ConnectionManager manager = null;
		Connection c = null;

		ResultSet re = null;

		try {
			manager = new ConnectionManager();
		}
		catch (SQLException e1) {

			e1.printStackTrace();
		}
		c = manager.getConnection();

		try {
			String sql = "SELECT * FROM nazioni WHERE PK_istnaz  ='" + codicenaz + "'";
			PreparedStatement prepain = c.prepareStatement(sql);
			re = prepain.executeQuery(sql);
			int i = 0;
			while (re.next()) {
				res.add(i, re.getString("descst"));
				res.add(i + 1, re.getString("europa"));

				i = i + 2;
			}
			ArrayList arr2 = new ArrayList();

			if (res.contains(null)) {
				for (int j = 0; j < res.size(); j++) {
					if (res.get(j) == null) {
						arr2.add(j, "");
					}
					else {
						arr2.add(j, res.get(j));
					}
				}
				res = arr2;
			}
			c.close();
			log.debug("RISULTATO QUERY DB  " + res);
			return res;

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		;
		return null;
	}

	public ArrayList comune(String codicecom) {
		ArrayList res = new ArrayList();
		ConnectionManager manager = null;
		Connection c = null;

		ResultSet re = null;

		try {
			manager = new ConnectionManager();
		}
		catch (SQLException e1) {

			e1.printStackTrace();
		}
		c = manager.getConnection();

		try {
			String sql = "SELECT * FROM comuni WHERE PK_istcom  ='" + codicecom + "'";
			PreparedStatement prepain = c.prepareStatement(sql);
			re = prepain.executeQuery(sql);
			int i = 0;
			while (re.next()) {
				res.add(i, re.getString("descco"));
				res.add(i + 1, re.getString("provincia"));
				res.add(i + 2, re.getString("FK_nazione"));

				i = i + 3;
			}
			ArrayList arr2 = new ArrayList();

			if (res.contains(null)) {
				for (int j = 0; j < res.size(); j++) {
					if (res.get(j) == null) {
						arr2.add(j, "");
					}
					else {
						arr2.add(j, res.get(j));
					}
				}
				res = arr2;
			}
			c.close();
			log.debug("RISULTATO QUERY DB  " + res);
			return res;

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		;
		return null;
	}

	public ArrayList comune2(String codicecom) {
		ArrayList res = new ArrayList();

		ConnectionManager manager = null;
		Connection c = null;

		ResultSet re = null;

		try {
			manager = new ConnectionManager();
		}
		catch (SQLException e1) {

			e1.printStackTrace();
		}
		c = manager.getConnection();

		try {
			String sql = "SELECT * FROM comuni WHERE PK_istcom  ='" + codicecom + "'";
			PreparedStatement prepain = c.prepareStatement(sql);
			re = prepain.executeQuery(sql);
			int i = 0;
			while (re.next()) {
				if (re.getString("cap").equals(null)) {
					res.add(i, "Non presente");
				}
				else {
					res.add(i, re.getString("cap"));
				}

				i++;
			}
			ArrayList arr2 = new ArrayList();

			if (res.contains(null)) {
				for (int j = 0; j < res.size(); j++) {
					if (res.get(j) == null) {
						arr2.add(j, "");
					}
					else {
						arr2.add(j, res.get(j));
					}
				}
				res = arr2;
			}
			c.close();
			log.debug("RISULTATO QUERY DB  " + res);
			return res;

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		;
		return null;
	}

	public String tipocitt(String codice) {
		String res = "";
		ConnectionManager manager = null;
		Connection c = null;

		ResultSet re = null;

		try {
			manager = new ConnectionManager();
		}
		catch (SQLException e1) {

			e1.printStackTrace();
		}
		c = manager.getConnection();

		try {
			String sql = "SELECT * FROM tipocitt WHERE PK_codci  ='" + codice + "'";
			PreparedStatement prepain = c.prepareStatement(sql);
			re = prepain.executeQuery(sql);

			while (re.next()) {
				res = re.getString("descrel");

			}
			c.close();
			log.debug("RISULTATO QUERY DB  " + res);
			return res;

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		;
		return null;
	}

	public String statociv(String codice) {
		String res = "";
		ConnectionManager manager = null;
		Connection c = null;

		ResultSet re = null;

		try {
			manager = new ConnectionManager();
		}
		catch (SQLException e1) {

			e1.printStackTrace();
		}
		c = manager.getConnection();

		try {
			String sql = "SELECT * FROM statociv WHERE PK_cod  ='" + codice + "'";
			PreparedStatement prepain = c.prepareStatement(sql);
			re = prepain.executeQuery(sql);

			while (re.next()) {
				res = re.getString("descsc");

			}
			c.close();
			log.debug("RISULTATO QUERY DB  " + res);
			return res;

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		;
		return null;
	}

	public String relapare(String codice) {
		String res = "";
		ConnectionManager manager = null;
		Connection c = null;

		ResultSet re = null;

		try {
			manager = new ConnectionManager();
		}
		catch (SQLException e1) {

			e1.printStackTrace();
		}
		c = manager.getConnection();

		try {
			String sql = "SELECT * FROM relapare WHERE PK_cod  ='" + codice + "'";
			PreparedStatement prepain = c.prepareStatement(sql);
			re = prepain.executeQuery(sql);

			while (re.next()) {
				res = re.getString("descrelf");

			}
			c.close();
			log.debug("RISULTATO QUERY DB  " + res);
			return res;

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		;
		return null;
	}

	public String toponimo(String codfisc) {
		String res = "";
		ConnectionManager manager = null;
		Connection c = null;

		ResultSet re = null;

		try {
			manager = new ConnectionManager();
		}
		catch (SQLException e1) {

			e1.printStackTrace();
		}
		c = manager.getConnection();

		try {
			String codice = indirizz(codfisc).get(0).toString();

			String sql = "SELECT toponimo.descto FROM toponimo LEFT JOIN stradeco ON stradeco.FK_codtop= toponimo.PK_cod WHERE stradeco.PK_codst ='" + codice + "'";
			PreparedStatement prepain = c.prepareStatement(sql);
			re = prepain.executeQuery(sql);

			while (re.next()) {
				res = re.getString("descto");

			}

			c.close();
			log.debug("RISULTATO QUERY DB  " + res);
			return res;

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		;
		return null;
	}

	public String stradeco(String codfisc) {
		String res = "";
		ConnectionManager manager = null;
		Connection c = null;

		ResultSet re = null;

		try {
			manager = new ConnectionManager();
		}
		catch (SQLException e1) {

			e1.printStackTrace();
		}
		c = manager.getConnection();

		try {
			String codice = indirizz(codfisc).get(0).toString();

			String sql = "SELECT * FROM stradeco WHERE PK_codst ='" + codice + "'";
			PreparedStatement prepain = c.prepareStatement(sql);
			re = prepain.executeQuery(sql);

			while (re.next()) {
				res = re.getString("descst");

			}
			c.close();
			log.debug("RISULTATO QUERY DB  " + res);
			return res;

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String toponimoStr(String cod) {
		String res = "";
		ConnectionManager manager = null;
		Connection c = null;

		ResultSet re = null;

		try {
			manager = new ConnectionManager();
		}
		catch (SQLException e1) {

			e1.printStackTrace();
		}
		c = manager.getConnection();

		try {

			String sql = "SELECT toponimo.descto FROM toponimo LEFT JOIN stradeco ON stradeco.FK_codtop= toponimo.PK_cod WHERE stradeco.descst ='" + cod + "'";
			PreparedStatement prepain = c.prepareStatement(sql);
			re = prepain.executeQuery(sql);

			while (re.next()) {
				res = re.getString("descto");

			}
			c.close();
			log.debug("RISULTATO QUERY DB  " + res);
			return res;

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String toponimoS(String cod) {
		String res = "";
		ConnectionManager manager = null;
		Connection c = null;

		ResultSet re = null;

		try {
			manager = new ConnectionManager();
		}
		catch (SQLException e1) {

			e1.printStackTrace();
		}
		c = manager.getConnection();

		try {

			String sql = "SELECT toponimo.descto FROM toponimo LEFT JOIN stradeco ON stradeco.FK_codtop= toponimo.PK_cod WHERE stradeco.PK_codst ='" + cod + "'";
			PreparedStatement prepain = c.prepareStatement(sql);
			re = prepain.executeQuery(sql);

			while (re.next()) {
				res = re.getString("descto");

			}
			c.close();
			log.debug("RISULTATO QUERY DB  " + res);
			return res;

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		;
		return null;
	}

	public String stradecoS(String cod) {
		String res = "";
		ConnectionManager manager = null;
		Connection c = null;

		ResultSet re = null;

		try {
			manager = new ConnectionManager();
		}
		catch (SQLException e1) {

			e1.printStackTrace();
		}
		c = manager.getConnection();

		try {

			String sql = "SELECT descst FROM stradeco WHERE PK_codst ='" + cod + "'";
			PreparedStatement prepain = c.prepareStatement(sql);
			re = prepain.executeQuery(sql);

			while (re.next()) {
				res = re.getString("descst");

			}
			c.close();
			log.debug("RISULTATO QUERY DB  " + res);
			return res;

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		;
		return null;
	}

	public ArrayList tarsut(String codice, String datain, String datafi) {
		ArrayList res = new ArrayList();
		ConnectionManager manager = null;
		Connection c = null;

		ResultSet re = null;

		try {
			manager = new ConnectionManager();
		}
		catch (SQLException e1) {

			e1.printStackTrace();
		}
		c = manager.getConnection();

		try {
			String sql = "";
			if (datain.equals("") || datafi.equals("")) {
				sql = "SELECT * FROM ratetarsu LEFT JOIN tarsu ON ratetarsu.FK_ute = tarsu.PK_codid WHERE tarsu.Codfis = '" + codice
						+ "'AND tarsu.Annorif=(SELECT MAX(Annorif) From tarsu WHERE Codfis='" + codice + "')";

			}
			else {
				sql = "SELECT * FROM ratetarsu LEFT JOIN tarsu ON ratetarsu.FK_ute = tarsu.PK_codid WHERE tarsu.Codfis = '" + codice + "'AND tarsu.Annorif<= '" + datafi + "' and tarsu.Annorif >= '"
						+ datain + "'";
			}

			PreparedStatement prepain = c.prepareStatement(sql);
			re = prepain.executeQuery(sql);
			int i = 0;
			while (re.next()) {

				res.add(i, re.getString("PK_cod"));
				res.add(i + 1, re.getString("FK_ute"));
				res.add(i + 2, re.getString("Nrata"));
				res.add(i + 3, re.getString("importoR"));
				res.add(i + 4, re.getString("importoP"));
				res.add(i + 5, re.getString("idrata"));
				res.add(i + 6, re.getBytes("datagg").toString());

				res.add(i + 7, re.getString("PK_codid"));
				res.add(i + 8, re.getString("Istatcom"));
				res.add(i + 9, re.getString("Codfis"));
				res.add(i + 10, re.getString("Annorif"));
				res.add(i + 11, re.getString("Superficie"));
				res.add(i + 12, re.getString("Zona"));
				res.add(i + 13, re.getString("Destar"));
				res.add(i + 14, re.getString("Importot"));
				res.add(i + 15, re.getString("Mesi"));
				res.add(i + 16, re.getString("ndocu"));
				res.add(i + 17, re.getBytes("Datagg").toString());
				res.add(i + 18, re.getString("scadenza"));
				i = i + 19;
			}
			ArrayList arr2 = new ArrayList();

			if (res.contains(null)) {
				for (int j = 0; j < res.size(); j++) {
					if (res.get(j) == null) {
						arr2.add(j, "");
					}
					else {
						arr2.add(j, res.get(j));
					}
				}
				res = arr2;
			}
			c.close();
			log.debug("RISULTATO QUERY DB TARSU  " + res);

			return res;

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		;
		return null;
	}

	public ArrayList tosapt(String codice) {
		ArrayList res = new ArrayList();
		ConnectionManager manager = null;
		Connection c = null;

		ResultSet re = null;

		try {
			manager = new ConnectionManager();
		}
		catch (SQLException e1) {

			e1.printStackTrace();
		}
		c = manager.getConnection();

		try {
			String sql = "SELECT * FROM rateosapt LEFT JOIN osapt ON rateosapt.FK_ute = osapt.PK_codid WHERE osapt.Codfis = '" + codice
					+ "'AND osapt.tipo ='1' AND osapt.Annorif=(SELECT MAX(Annorif) From osapt WHERE Codfis='" + codice + "' AND tipo='1')";
			PreparedStatement prepain = c.prepareStatement(sql);
			re = prepain.executeQuery(sql);
			int i = 0;
			while (re.next()) {

				res.add(i, re.getString("PK_cod"));
				res.add(i + 1, re.getString("FK_ute"));
				res.add(i + 2, re.getString("Nrata"));
				res.add(i + 3, re.getString("importoR"));
				res.add(i + 4, re.getString("importoP"));
				res.add(i + 5, re.getString("Idrata"));
				res.add(i + 6, re.getString("PK_codid"));
				res.add(i + 7, re.getString("Istatcom"));
				res.add(i + 8, re.getString("Codfis"));
				res.add(i + 9, re.getString("tipo"));
				res.add(i + 10, re.getString("Annorif"));
				res.add(i + 11, re.getString("Superficie"));
				res.add(i + 12, re.getString("Zona"));
				res.add(i + 13, re.getString("Destar"));
				res.add(i + 14, re.getString("importot"));
				res.add(i + 15, re.getString("durata"));
				res.add(i + 16, re.getBytes("datagg").toString());
				res.add(i + 17, re.getString("FK_strada"));
				res.add(i + 18, re.getString("Num_civ"));
				res.add(i + 19, re.getString("Data_fine"));
				i = i + 20;

			}
			ArrayList arr2 = new ArrayList();

			if (res.contains(null)) {
				for (int j = 0; j < res.size(); j++) {
					if (res.get(j) == null) {
						arr2.add(j, "");
					}
					else {
						arr2.add(j, res.get(j));
					}
				}
				res = arr2;
			}
			c.close();
			log.debug("RISULTATO QUERY DB  " + res);
			return res;

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		;
		return null;
	}

	public ArrayList cosapt(String codice) {
		ArrayList res = new ArrayList();
		ConnectionManager manager = null;
		Connection c = null;

		ResultSet re = null;

		try {
			manager = new ConnectionManager();
		}
		catch (SQLException e1) {

			e1.printStackTrace();
		}
		c = manager.getConnection();

		try {
			String sql = "SELECT * FROM rateosapt LEFT JOIN osapt ON rateosapt.FK_ute = osapt.PK_codid WHERE osapt.Codfis = '" + codice
					+ "'AND osapt.tipo ='0' AND osapt.Annorif=(SELECT MAX(Annorif) From osapt WHERE Codfis='" + codice + "' AND tipo='0')";
			PreparedStatement prepain = c.prepareStatement(sql);
			re = prepain.executeQuery(sql);
			int i = 0;
			while (re.next()) {

				res.add(i, re.getString("PK_cod"));
				res.add(i + 1, re.getString("FK_ute"));
				res.add(i + 2, re.getString("Nrata"));
				res.add(i + 3, re.getString("importoR"));
				res.add(i + 4, re.getString("importoP"));
				res.add(i + 5, re.getString("Idrata"));
				res.add(i + 6, re.getString("PK_codid"));
				res.add(i + 7, re.getString("Istatcom"));
				res.add(i + 8, re.getString("Codfis"));
				res.add(i + 9, re.getString("tipo"));
				res.add(i + 10, re.getString("Annorif"));
				res.add(i + 11, re.getString("Superficie"));
				res.add(i + 12, re.getString("Zona"));
				res.add(i + 13, re.getString("Destar"));
				res.add(i + 14, re.getString("importot"));
				res.add(i + 15, re.getString("durata"));
				res.add(i + 16, re.getBytes("datagg").toString());
				res.add(i + 17, re.getString("FK_strada"));
				res.add(i + 18, re.getString("Num_civ"));
				res.add(i + 19, re.getString("Data_fine"));
				i = i + 20;

			}
			ArrayList arr2 = new ArrayList();

			if (res.contains(null)) {
				for (int j = 0; j < res.size(); j++) {
					if (res.get(j) == null) {
						arr2.add(j, "");
					}
					else {
						arr2.add(j, res.get(j));
					}
				}
				res = arr2;
			}
			c.close();
			log.debug("RISULTATO QUERY DB  " + res);
			return res;

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		;
		return null;
	}

	public ArrayList tosapp(String codice) {

		ArrayList res = new ArrayList();
		ConnectionManager manager = null;
		Connection c = null;

		ResultSet re = null;

		try {
			manager = new ConnectionManager();
		}
		catch (SQLException e1) {

			e1.printStackTrace();
		}
		c = manager.getConnection();

		try {
			String sql = "SELECT * FROM osapp WHERE Codfis = '" + codice + "'AND tipo = '1' AND Annorif = (SELECT MAX( Annorif ) FROM osapp WHERE Codfis = '" + codice + "'AND tipo = '1' ) ";

			PreparedStatement prepain = c.prepareStatement(sql);
			re = prepain.executeQuery(sql);
			int i = 0;
			while (re.next()) {
				res.add(i, re.getString("PK_codid"));
				res.add(i + 1, re.getString("Istatcom"));
				res.add(i + 2, re.getString("Codfis"));
				res.add(i + 3, re.getString("Tipo"));
				res.add(i + 4, re.getString("Annorif"));
				res.add(i + 5, re.getString("Superficie"));
				res.add(i + 6, re.getString("Zona"));
				res.add(i + 7, re.getString("Destar"));
				res.add(i + 8, re.getString("importot"));
				res.add(i + 9, re.getBytes("datagg").toString());
				res.add(i + 10, re.getString("FK_strada"));
				res.add(i + 11, re.getString("Numciv"));
				res.add(i + 12, re.getString("Data_fine"));
				res.add(i + 13, re.getString("Data_inizio"));
				res.add(i + 14, re.getString("mesi"));

				i = i + 15;

			}
			ArrayList arr2 = new ArrayList();

			if (res.contains(null)) {
				for (int j = 0; j < res.size(); j++) {
					if (res.get(j) == null) {
						arr2.add(j, "");
					}
					else {
						arr2.add(j, res.get(j));
					}
				}
				res = arr2;
			}
			c.close();
			log.debug("RISULTATO QUERY DB  " + res);
			return res;

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		;
		return null;
	}

	public ArrayList ratatosapp(String codice) {
		ArrayList res = new ArrayList();
		ConnectionManager manager = null;
		Connection c = null;

		ResultSet re = null;

		try {
			manager = new ConnectionManager();
		}
		catch (SQLException e1) {

			e1.printStackTrace();
		}
		c = manager.getConnection();

		try {
			String sql = "SELECT * FROM rateosapp WHERE FK_ute='" + codice + "'";

			PreparedStatement prepain = c.prepareStatement(sql);
			re = prepain.executeQuery(sql);
			int i = 0;
			while (re.next()) {
				res.add(i, re.getString("PK_cod"));
				res.add(i + 1, re.getString("Nrata"));
				res.add(i + 2, re.getString("importoR"));
				res.add(i + 3, re.getString("importoP"));
				res.add(i + 4, re.getString("Idrata"));
				res.add(i + 5, re.getString("Data_sca"));

				i = i + 6;

			}
			ArrayList arr2 = new ArrayList();

			if (res.contains(null)) {
				for (int j = 0; j < res.size(); j++) {
					if (res.get(j) == null) {
						arr2.add(j, "");
					}
					else {
						arr2.add(j, res.get(j));
					}
				}
				res = arr2;
			}
			c.close();
			log.debug("RISULTATO QUERY DB  " + res);
			return res;

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		;
		return null;
	}

	public ArrayList cosapp(String codice) {
		ArrayList res = new ArrayList();
		ConnectionManager manager = null;
		Connection c = null;

		ResultSet re = null;

		try {
			manager = new ConnectionManager();
		}
		catch (SQLException e1) {

			e1.printStackTrace();
		}
		c = manager.getConnection();

		try {
			String sql = "SELECT * FROM osapp WHERE Codfis = '" + codice + "'AND tipo = '0' AND Annorif = (SELECT MAX( Annorif ) FROM osapp WHERE Codfis = '" + codice + "'AND tipo = '0' ) ";

			PreparedStatement prepain = c.prepareStatement(sql);
			re = prepain.executeQuery(sql);
			int i = 0;
			while (re.next()) {
				res.add(i, re.getString("PK_codid"));
				res.add(i + 1, re.getString("Istatcom"));
				res.add(i + 2, re.getString("Codfis"));
				res.add(i + 3, re.getString("Tipo"));
				res.add(i + 4, re.getString("Annorif"));
				res.add(i + 5, re.getString("Superficie"));
				res.add(i + 6, re.getString("Zona"));
				res.add(i + 7, re.getString("Destar"));
				res.add(i + 8, re.getString("importot"));
				res.add(i + 9, re.getString("datagg").toString());
				res.add(i + 10, re.getString("FK_strada"));
				res.add(i + 11, re.getString("Numciv"));
				res.add(i + 12, re.getString("Datafi"));
				res.add(i + 13, re.getString("Datain"));
				res.add(i + 14, re.getString("mesi"));

				i = i + 15;

			}
			ArrayList arr2 = new ArrayList();

			if (res.contains(null)) {
				for (int j = 0; j < res.size(); j++) {
					if (res.get(j) == null) {
						arr2.add(j, "");
					}
					else {
						arr2.add(j, res.get(j));
					}
				}
				res = arr2;
			}
			c.close();
			log.debug("RISULTATO QUERY DB  " + res);
			return res;

		}

		catch (Exception e) {

			e.printStackTrace();
		}
		;
		return null;
	}

	public ArrayList ratacosapp(String codice) {
		ArrayList res = new ArrayList();
		ConnectionManager manager = null;
		Connection c = null;

		ResultSet re = null;

		try {
			manager = new ConnectionManager();
		}
		catch (SQLException e1) {

			e1.printStackTrace();
		}
		c = manager.getConnection();

		try {
			String sql = "SELECT * FROM rateosapp WHERE FK_ute='" + codice + "'";

			PreparedStatement prepain = c.prepareStatement(sql);
			re = prepain.executeQuery(sql);
			int i = 0;
			while (re.next()) {
				res.add(i, re.getString("PK_cod"));
				res.add(i + 1, re.getString("Nrata"));
				res.add(i + 2, re.getString("importoR"));
				res.add(i + 3, re.getString("importoP"));
				res.add(i + 4, re.getString("Idrata"));
				res.add(i + 5, re.getString("Datasca"));

				i = i + 6;

			}
			ArrayList arr2 = new ArrayList();

			if (res.contains(null)) {
				for (int j = 0; j < res.size(); j++) {
					if (res.get(j) == null) {
						arr2.add(j, "");
					}
					else {
						arr2.add(j, res.get(j));
					}
				}
				res = arr2;
			}
			c.close();
			log.debug("RISULTATO QUERY DB  " + res);
			return res;

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		;
		return null;
	}

	public ArrayList immobili(String codice, String datain, String datafi) {
		ArrayList res = new ArrayList();
		ConnectionManager manager = null;
		Connection c = null;

		ResultSet re = null;

		try {
			manager = new ConnectionManager();
		}
		catch (SQLException e1) {

			e1.printStackTrace();
		}
		c = manager.getConnection();

		try {
			String sql = "";

			if (datain.equals("") || datafi.equals("")) {
				sql = "SELECT * FROM immobili LEFT JOIN sitici ON immobili.FK_ute = sitici.PK_codid WHERE sitici.Codfis = '" + codice
						+ "' AND sitici.Annorif=(SELECT MAX(Annorif) From sitici WHERE Codfis='" + codice + "'";
			}
			else {
				sql = "SELECT * FROM immobili LEFT JOIN sitici ON immobili.FK_ute = sitici.PK_codid WHERE sitici.Codfis = '" + codice + "' AND sitici.Annorif <= '" + datafi
						+ "' AND sitici.Annorif >= '" + datain + "'";

			}
			PreparedStatement prepain = c.prepareStatement(sql);
			re = prepain.executeQuery(sql);
			int i = 0;
			while (re.next()) {

				res.add(i, re.getString(1));
				res.add(i + 1, re.getString("FK_ute"));
				res.add(i + 2, re.getString("caratt"));
				res.add(i + 3, re.getString("sezione"));
				res.add(i + 4, re.getString("foglio"));
				res.add(i + 5, re.getString("partic"));
				res.add(i + 6, re.getString("indimm"));
				res.add(i + 7, re.getString("Valoreimmobile"));
				res.add(i + 8, re.getString("Percentualedipossesso"));
				res.add(i + 9, re.getString("mesi"));
				res.add(i + 10, re.getString("possesso"));
				res.add(i + 11, re.getString("abipri"));
				res.add(i + 12, re.getString(13));
				res.add(i + 13, re.getString("PK_codid"));
				res.add(i + 14, re.getString("Istatcom"));
				res.add(i + 15, re.getString("Codfis"));
				res.add(i + 16, re.getString("Annorif"));
				res.add(i + 17, re.getBytes("datagg").toString());
				res.add(i + 18, re.getString("Sub"));
				res.add(i + 19, re.getString("categoria"));
				i = i + 20;

			}
			ArrayList arr2 = new ArrayList();

			if (res.contains(null)) {
				for (int j = 0; j < res.size(); j++) {
					if (res.get(j) == null) {
						arr2.add(j, "");
					}
					else {
						arr2.add(j, res.get(j));
					}
				}
				res = arr2;
			}
			c.close();
			log.debug("RISULTATO QUERY DB  " + res);
			return res;

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		;
		return res;
	}

	public ArrayList anno(String codice, String datain, String datafi) {

		ArrayList res = new ArrayList();
		ConnectionManager manager = null;
		Connection c = null;

		ResultSet re = null;

		try {
			manager = new ConnectionManager();
		}
		catch (SQLException e1) {

			e1.printStackTrace();
		}
		c = manager.getConnection();

		try {
			String sql = "";
			if (datain.equals("") || datafi.equals("")) {
				sql = "SELECT MAX(Annorif) From sitici WHERE Codfis='" + codice + "'";

			}
			else {
				sql = "SELECT Annorif FROM sitici  WHERE Codfis = '" + codice + "' AND Annorif <= '" + datafi + "' AND Annorif >= '" + datain + "'";
			}

			PreparedStatement prepain = c.prepareStatement(sql);
			re = prepain.executeQuery(sql);
			int i = 0;

			while (re.next()) {
				if (datain.equals("") || datafi.equals("")) {
					res.add(i, re.getString("MAX(Annorif)"));
				}
				else {
					res.add(i, re.getString("Annorif"));
				}

				i = i++;

			}
			try {
				int r = Integer.parseInt(res.get(0).toString());
				r = r + 1;
			}
			catch (Exception e) {
				res.add(0, "vuoto");
				return res;
			}
			c.close();
			log.debug("RISULTATO QUERY DB  " + res);
			return res;

		}

		catch (Exception e) {
			e.printStackTrace();

		}
		;
		return res;
	}

	public ArrayList cosap(String codice) {
		ArrayList res = new ArrayList();
		ConnectionManager manager = null;
		Connection c = null;

		ResultSet re = null;

		try {
			manager = new ConnectionManager();
		}
		catch (SQLException e1) {

			e1.printStackTrace();
		}
		c = manager.getConnection();

		try {
			String sql = "SELECT * FROM rateosapp LEFT JOIN osapp ON rateosapp.FK_ute = osapp.PK_codid WHERE osapp.Codfis = '" + codice
					+ "'AND osapp.tipo ='0'AND osapp.Annorif=(SELECT MAX(Annorif) From osapp WHERE Codfis='" + codice + "' AND tipo='0')";
			PreparedStatement prepain = c.prepareStatement(sql);
			re = prepain.executeQuery(sql);
			int i = 0;
			while (re.next()) {

				res.add(i, re.getString("PK_cod"));
				res.add(i + 1, re.getString("FK_ute"));
				res.add(i + 2, re.getString("Nrata"));
				res.add(i + 3, re.getString("importoR"));
				res.add(i + 4, re.getString("importoP"));
				res.add(i + 5, re.getString("Idrata"));
				res.add(i + 6, re.getString("PK_codid"));
				res.add(i + 7, re.getString("Istatcom"));
				res.add(i + 8, re.getString("Codfis"));
				res.add(i + 9, re.getString("Tipo"));
				res.add(i + 10, re.getString("Annorif"));
				res.add(i + 11, re.getString("Superficie"));
				res.add(i + 12, re.getString("Zona"));
				res.add(i + 13, re.getString("Destar"));
				res.add(i + 14, re.getString("importot"));
				res.add(i + 15, re.getString("mesi"));
				res.add(i + 16, re.getBytes("datagg").toString());
				res.add(i + 17, re.getString("FK_strada"));
				res.add(i + 18, re.getString("Num_civ"));
				res.add(i + 19, re.getString("Datasca"));
				i = i + 20;

			}
			ArrayList arr2 = new ArrayList();

			if (res.contains(null)) {
				for (int j = 0; j < res.size(); j++) {
					if (res.get(j) == null) {
						arr2.add(j, "");
					}
					else {
						arr2.add(j, res.get(j));
					}
				}
				res = arr2;
			}
			c.close();
			log.debug("RISULTATO QUERY DB  " + res);
			return res;

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		;
		return null;
	}

	public ArrayList tosap(String codice) {
		ArrayList res = new ArrayList();
		ConnectionManager manager = null;
		Connection c = null;

		ResultSet re = null;

		try {
			manager = new ConnectionManager();
		}
		catch (SQLException e1) {

			e1.printStackTrace();
		}
		c = manager.getConnection();

		try {
			String sql = "SELECT * FROM rateosapp LEFT JOIN osapp ON rateosapp.FK_ute = osapp.PK_codid WHERE osapp.Codfis = '" + codice
					+ "'AND osapp.tipo ='1'AND osapp.Annorif=(SELECT MAX(Annorif) From osapp WHERE Codfis='" + codice + "' AND tipo='1')";
			PreparedStatement prepain = c.prepareStatement(sql);
			re = prepain.executeQuery(sql);
			int i = 0;
			while (re.next()) {

				res.add(i, re.getString("PK_cod"));
				res.add(i + 1, re.getString("FK_ute"));
				res.add(i + 2, re.getString("Nrata"));
				res.add(i + 3, re.getString("importoR"));
				res.add(i + 4, re.getString("importoP"));
				res.add(i + 5, re.getString("Idrata"));
				res.add(i + 6, re.getString("PK_codid"));
				res.add(i + 7, re.getString("Istatcom"));
				res.add(i + 8, re.getString("Codfis"));
				res.add(i + 9, re.getString("Tipo"));
				res.add(i + 10, re.getString("Annorif"));
				res.add(i + 11, re.getString("Superficie"));
				res.add(i + 12, re.getString("Zona"));
				res.add(i + 13, re.getString("Destar"));
				res.add(i + 14, re.getString("importot"));
				res.add(i + 15, re.getString("mesi"));
				res.add(i + 16, re.getBytes("datagg").toString());
				res.add(i + 17, re.getString("FK_strada"));
				res.add(i + 18, re.getString("Num_civ"));
				res.add(i + 19, re.getString("Datasca"));
				i = i + 20;

			}
			ArrayList arr2 = new ArrayList();

			if (res.contains(null)) {
				for (int j = 0; j < res.size(); j++) {
					if (res.get(j) == null) {
						arr2.add(j, "");
					}
					else {
						arr2.add(j, res.get(j));
					}
				}
				res = arr2;
			}
			c.close();
			log.debug("RISULTATO QUERY DB  " + res);
			return res;

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		;
		return null;
	}

	public ArrayList tributi(String codice, String trib) {
		ArrayList res = new ArrayList();
		ConnectionManager manager = null;
		Connection c = null;

		ResultSet re = null;

		try {
			manager = new ConnectionManager();
		}
		catch (SQLException e1) {

			e1.printStackTrace();
		}
		c = manager.getConnection();

		try {
			String sql = "SELECT * 	FROM (pagam LEFT JOIN ratepag ON pagam.PK_codid=ratepag.FK_ute) LEFT JOIN tipotrib ON tipotrib.PK_cod=pagam.FK_codtrib WHERE tipotrib.Pk_cod='" + trib
					+ "' AND pagam.Codfis='" + codice + "'";

			PreparedStatement prepain = c.prepareStatement(sql);
			re = prepain.executeQuery(sql);
			int i = 0;
			while (re.next()) {

				res.add(i, re.getString("PK_codid"));
				res.add(i + 1, re.getString("Istatcom"));
				res.add(i + 2, re.getString("Codfis"));
				res.add(i + 3, re.getString("Annorif"));
				res.add(i + 4, re.getString("FK_codtrib"));
				res.add(i + 5, re.getString("importot"));
				res.add(i + 6, re.getBytes("Datagg").toString());
				res.add(i + 7, re.getString("PK_cod"));
				res.add(i + 8, re.getString("FK_ute"));
				res.add(i + 9, re.getString("Nrata"));
				res.add(i + 10, re.getString("importoR"));
				res.add(i + 11, re.getString("importoP"));
				res.add(i + 12, re.getString("idrata"));
				res.add(i + 13, re.getString("Scadenza"));
				res.add(i + 14, re.getString("destri"));
				i = i + 15;

			}
			ArrayList arr2 = new ArrayList();

			if (res.contains(null)) {
				for (int j = 0; j < res.size(); j++) {
					if (res.get(j) == null) {
						arr2.add(j, "");
					}
					else {
						arr2.add(j, res.get(j));
					}
				}
				res = arr2;
			}
			c.close();
			log.debug("RISULTATO QUERY DB  " + res);
			return res;

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		;
		return null;
	}

	public ArrayList trosapp(String codice) {
		ArrayList res = new ArrayList();
		ConnectionManager manager = null;
		Connection c = null;

		ResultSet re = null;

		try {
			manager = new ConnectionManager();
		}
		catch (SQLException e1) {

			e1.printStackTrace();
		}
		c = manager.getConnection();

		try {
			String sql = "SELECT * FROM osapp LEFT JOIN rateosapp ON rateosapp.FK_ute = osapp.PK_codid WHERE osapp.Codfis = '" + codice
					+ "' AND osapp.Annorif=(SELECT MAX(Annorif) From osapp WHERE Codfis='" + codice + "')";

			PreparedStatement prepain = c.prepareStatement(sql);
			re = prepain.executeQuery(sql);
			int i = 0;
			while (re.next()) {

				res.add(i, re.getString("PK_codid"));
				res.add(i + 1, re.getString("Istatcom"));
				res.add(i + 2, re.getString("Codfis"));
				res.add(i + 3, re.getString("Annorif"));
				res.add(i + 4, re.getString("Tipo"));
				res.add(i + 5, re.getString("importot"));
				res.add(i + 6, re.getBytes("datagg").toString());
				res.add(i + 7, re.getString("PK_cod"));
				res.add(i + 8, re.getString("FK_ute"));
				res.add(i + 9, re.getString("Nrata"));
				res.add(i + 10, re.getString("importoR"));
				res.add(i + 11, re.getString("importoP"));
				res.add(i + 12, re.getString("Idrata"));
				res.add(i + 13, re.getString("Datasca"));

				i = i + 14;

			}
			ArrayList arr2 = new ArrayList();

			if (res.contains(null)) {
				for (int j = 0; j < res.size(); j++) {
					if (res.get(j) == null) {
						arr2.add(j, "");
					}
					else {
						arr2.add(j, res.get(j));
					}
				}
				res = arr2;
			}
			c.close();
			log.debug("RISULTATO QUERY DB  " + res);
			return res;

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		;
		return null;
	}

	public ArrayList trosapt(String codice) {
		ArrayList res = new ArrayList();
		ConnectionManager manager = null;
		Connection c = null;

		ResultSet re = null;

		try {
			manager = new ConnectionManager();
		}
		catch (SQLException e1) {

			e1.printStackTrace();
		}
		c = manager.getConnection();

		try {
			String sql = "SELECT * FROM osapt LEFT JOIN rateosapt ON rateosapt.FK_ute = osapt.PK_codid WHERE osapt.Codfis = '" + codice
					+ "'AND osapt.Annorif=(SELECT MAX(Annorif) From osapt WHERE Codfis='" + codice + "')";

			PreparedStatement prepain = c.prepareStatement(sql);
			re = prepain.executeQuery(sql);
			int i = 0;
			while (re.next()) {

				res.add(i, re.getString("PK_codid"));
				res.add(i + 1, re.getString("Istatcom"));
				res.add(i + 2, re.getString("Codfis"));
				res.add(i + 3, re.getString("Annorif"));
				res.add(i + 4, re.getString("tipo"));
				res.add(i + 5, re.getString("importot"));
				res.add(i + 6, re.getBytes("datagg").toString());
				res.add(i + 7, re.getString("PK_cod"));
				res.add(i + 8, re.getString("FK_ute"));
				res.add(i + 9, re.getString("Nrata"));
				res.add(i + 10, re.getString("importoR"));
				res.add(i + 11, re.getString("importoP"));
				res.add(i + 12, re.getString("Idrata"));
				res.add(i + 13, re.getString("Scadenza"));

				i = i + 14;

			}

			ArrayList arr2 = new ArrayList();

			if (res.contains(null)) {
				for (int j = 0; j < res.size(); j++) {
					if (res.get(j) == null) {
						arr2.add(j, "");
					}
					else {
						arr2.add(j, res.get(j));
					}
				}
				res = arr2;
			}
			c.close();
			log.debug("RISULTATO QUERY DB  " + res);
			return res;

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		;
		return null;
	}
}
