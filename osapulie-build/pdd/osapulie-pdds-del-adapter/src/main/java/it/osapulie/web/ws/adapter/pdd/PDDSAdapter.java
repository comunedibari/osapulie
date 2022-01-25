package it.osapulie.web.ws.adapter.pdd;

import java.io.ByteArrayInputStream;
import java.util.Iterator;

//import org.apache.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.osapulie.web.ws.adapter.OSApulieServiceAdapter;
import it.puglia.rupar.egov.pdd.portaDiDominio.Busta;
import it.puglia.rupar.egov.pdd.portaDiDominio.EccezionePDDS;
import it.puglia.rupar.egov.pdd.portaDiDominio.PortaDelegata;
import it.puglia.rupar.egov.pdd.portaDiDominio.vo.busta.DescrizioneMessaggioVO;
import it.puglia.rupar.egov.pdd.portaDiDominio.vo.busta.MessaggioVO;
import it.puglia.rupar.egov.pdd.portaDiDominio.vo.busta.OraRegistrazioneVO;

public class PDDSAdapter implements OSApulieServiceAdapter {

	private static Logger log = LoggerFactory.getLogger(PDDSAdapter.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.osapulie.ws.adapter.OSApulieServiceAdapter#chiamaEnte(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String chiamaEnte(String area, String mittente, String servizio, String url, String message, String titolo) throws EccezionePDDS {
		PortaDelegata pd = null;
		Busta busta = null;
		String allega = "";
		String risultato = "valore iniziale";
		String errore = "";
		log.debug("Area " + area);
		log.debug("mittente " + mittente);
		log.debug("servizio " + servizio);
		log.debug("url " + url);
		log.debug("message  " + message);
		log.debug("titolo " + titolo);

		try {
			// crea la porta di dominio

			pd = new PortaDelegata();
//			s.debug(pd.getParametriContestoOut());

			// crea la busta e setto i parametri

			busta = new Busta();
			busta.setMittente(area, "mittente", mittente);
			busta.setServizio("SPC", servizio);
			busta.setAzione("Azione");
			busta.setDestinatario(area, servizio, url);
			ByteArrayInputStream bais = new ByteArrayInputStream(message.getBytes());
			DescrizioneMessaggioVO dmvo = new DescrizioneMessaggioVO();
			dmvo.setId("nuovo");
			dmvo.setAllegato(bais);
			dmvo.setHref("");
			dmvo.setLingua(DescrizioneMessaggioVO.ITALIANO_ITALIA);
			dmvo.setPosizione("posizione");
			dmvo.setRole("ruolo");
			dmvo.setTitolo(titolo);
			busta.setAllegato(dmvo);
			String idBusta;
			idBusta = null;

			idBusta = pd.generaID();
			log.debug("ID Busta = " + idBusta);

			MessaggioVO mio = new MessaggioVO();
			mio.setIdentificatore(idBusta);
			mio.setOraRegistrazione(new OraRegistrazioneVO());
			busta.setMessaggio(mio);
			log.debug("Busta " + busta);
			Busta risposta = pd.esegui(busta);
			// allega= (String)risposta.getListaAllegati().get(0);
			// log.debug(risposta.getAllegato("S946"));
//			log.debug(risposta);

			if (risposta.hasAllegati()) {
				Iterator i = null;
				try {
					i = risposta.getListaAllegati().iterator();
				}
				catch (Throwable e) {
					log.error(e.getMessage(), e);

				}
				while (i.hasNext()) {
					DescrizioneMessaggioVO dmv = (DescrizioneMessaggioVO) i.next();
					log.debug(" - Descrizione: " + dmvo);
					if (dmv.getAllegato() == null) {
						log.debug(" - Allegato nullo!");
						errore = "allegato nullo";
					}
					else {
						try {
							risultato = new String(dmv.getAllegatoAsArrayOfByte());
							log.debug(" - Allegato: " + risultato);

						}
						catch (Exception e) {
							log.error(" - Errore lettura allegato!" + e.getMessage(), e);
							errore = e.toString();
						}
					}
				}
			}
			else {
				log.debug(" - Non sono presenti allegati.");
				errore = "non ci sono allegati";
			}

			// log.debug(risposta.get);
			log.debug("valore della risposta  " + risposta);
		}
		catch (Throwable e) {
			System.out.print("Eccezione PDDS " + e);
			throw new EccezionePDDS(e.getMessage());
		}

		if (errore != "") {
			risultato = "errore";
		}
		return risultato;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.osapulie.ws.adapter.OSApulieServiceAdapter#eseguiRichiestaServizio(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public String eseguiRichiestaServizio(String xmlRichiesta, String nomeServizio, String indirizzoPddRemota) {
		// STUB implementation
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<visuraAnagrafica xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"VisuraPosizioneAnagrafica-risposta.xsd\"> "
				+ "	<toponimoIndirizzo>toponimoIndirizzo</toponimoIndirizzo>  <descrizioneVia>descrizioneVia</descrizioneVia>  " + "	<numeroCivico>numeroCivico</numeroCivico>"
				+ "	<componentiList><codiceFiscale>codiceFiscale</codiceFiscale><cognome>cognome</cognome><nome>nome</nome>"
				+ "	<dataNascita>2001-01-01</dataNascita><sesso>sesso</sesso><statoCivile>statoCivile</statoCivile><relazioneParentela>relazioneParentela</relazioneParentela>"
				+ "	<cittadinanzaItaliana>true</cittadinanzaItaliana><identificativoIndividuale>identificativoIndividuale</identificativoIndividuale>"
				+ "	<identificativoFamiglia>identificativoFamiglia</identificativoFamiglia><descComuneNascita>descComuneNascita</descComuneNascita>"
				+ "	<descProvinciaNascita>descProvinciaNascita</descProvinciaNascita></componentiList>" + "</visuraAnagrafica>";
		return xml;
	}

}
