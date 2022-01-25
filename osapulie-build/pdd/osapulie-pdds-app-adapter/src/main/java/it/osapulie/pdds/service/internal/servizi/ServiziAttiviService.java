/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pdds.service.internal.servizi;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

//import org.apache.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.osapulie.pdds.adapter.util.XMLHelper;
import it.osapulie.pdds.common.PddService;
import it.osapulie.pdds.database.ConnectionManager;
import it.osapulie.servizi.web.ws.types.RichiestaServiziAttivi;
import it.osapulie.servizi.web.ws.types.ServiziAttivi;
import it.osapulie.servizi.web.ws.types.Servizio;

/**
 * La classe con l'immissione del codice fiscale, partita iva e tipologia di autenticazione fornisce
 * l'elenco dei servizi esposti dalla porta applicativa del comune seguendo lo schema
 * ServiziAttivi_risposta.xsd
 *
 * @author Gianluca Pindinelli
 *
 */
public class ServiziAttiviService implements PddService {

	private final Logger log = LoggerFactory.getLogger(ServiziAttiviService.class);

	private XMLHelper xmlHelper;

	@Override
	public String getResponse(String xml) {

		RichiestaServiziAttivi richiesta = xmlHelper.unmarshal(xml, RichiestaServiziAttivi.class);

		String result = null;
		try {
			ServiziAttivi risp = risposta(richiesta.getCodiceFiscale());
			result = xmlHelper.marshal(risp);

		}
		catch (Exception e) {
			log.error("getResponse :: " + e.getMessage(), e);
		}

		return result;

	}

	private ServiziAttivi risposta(String codFiscale) {

		ServiziAttivi serviziAttivi = new ServiziAttivi();

		ConnectionManager manager = null;
		Connection c = null;

		ResultSet re = null;

		try {
			manager = new ConnectionManager();
		}
		catch (SQLException e1) {
			log.error("risposta :: " + e1.getMessage(), e1);
		}
		c = manager.getConnection();

		try {
			String sql = "SELECT codiceServizio,descrizioneServizio,urlPortaApplicativa,dataAggiornamento,autenticazioneForte,livelloAutenticazione,attivo FROM servizi_attivi order by codiceServizio";

			PreparedStatement prepain = c.prepareStatement(sql);

			re = prepain.executeQuery(sql);

			while (re.next()) {

				Servizio servizio = new Servizio();

				String codiceServizio = re.getString("codiceServizio");
				String descrizioneServizio = re.getString("descrizioneServizio");
				String urlPortaApplicativa = re.getString("urlPortaApplicativa");
				Date dataAggiornamento = re.getDate("dataAggiornamento");
				boolean autenticazioneForte = re.getBoolean("autenticazioneForte");
				boolean attivo = re.getBoolean("attivo");
				int livelloAutenticazione = re.getInt("livelloAutenticazione");

				servizio.setAutenticazioneForte(autenticazioneForte);
				servizio.setLivelloAutenticazione(livelloAutenticazione);
				servizio.setCodiceServizio(codiceServizio);
				if (dataAggiornamento != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(dataAggiornamento);
					servizio.setDataAggiornamento(calendar);
				}
				servizio.setDescrizioneServizio(descrizioneServizio);
				servizio.setUrlPortaApplicativa(urlPortaApplicativa);
				servizio.setAttivo(attivo);
				serviziAttivi.getServizio().add(servizio);
			}

		}

		catch (Exception e) {
			log.error("risposta :: " + e.getMessage(), e);
		}
		finally {
			try {
				c.close();
			}
			catch (SQLException e) {
				log.error("risposta :: " + e.getMessage(), e);
			}
		}
		return serviziAttivi;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.pdds.service.AbstractResponse#getName()
	 */
	@Override
	public String getName() {
		return "richiestaServiziAttivi";
	}

	/**
	 * @return the xmlHelper
	 */
	public XMLHelper getXmlHelper() {
		return xmlHelper;
	}

	/**
	 * @param xmlHelper the xmlHelper to set
	 */
	public void setXmlHelper(XMLHelper xmlHelper) {
		this.xmlHelper = xmlHelper;
	}

}
