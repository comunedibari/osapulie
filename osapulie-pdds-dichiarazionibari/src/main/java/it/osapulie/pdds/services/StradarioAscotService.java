package it.osapulie.pdds.services;

import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.osapulie.pdds.adapter.util.XMLHelper;
import it.osapulie.pdds.common.PddService;
import it.osapulie.pdds.util.IndirizzoComparator;
import it.osapulie.servizi.Codifica;
import it.osapulie.servizi.DataServiceFault;
import it.osapulie.servizi.Indirizzo;
import it.osapulie.servizi.StradarioMappingDBService;
import it.osapulie.servizi.web.ws.types.IndirizzoAscot;
import it.osapulie.servizi.web.ws.types.StradarioAscotRichiesta;
import it.osapulie.servizi.web.ws.types.StradarioAscotRisposta;
import it.osapulie.servizi.web.ws.types.StradarioAscotRisposta.Errore;

/**
 *
 * @author Gianluca Pindinelli
 *
 */
public class StradarioAscotService implements PddService {

	/**
	 *
	 */
	private static final String NESSUN_VALORE = "- NESSUN VALORE -";

	private static final String WSDL_STRADARIO_ASCOT_CSV = "wsdl/ascotweb/StradarioAscotCSVService.wsdl";
	private static final String WSDL_STRADARIO_ASCOT_DB = "wsdl/ascotweb/StradarioAscotDBService.wsdl";
	private static final String WSDL_STRADARIO_ASCOT_MAPPING_CSV = "wsdl/ascotweb/StradarioMappingCSVService.wsdl";
	private static final String WSDL_STRADARIO_ASCOT_MAPPING_DB = "wsdl/ascotweb/StradarioMappingDBService.wsdl";

	private final Logger log = LoggerFactory.getLogger(StradarioAscotService.class);

	private XMLHelper xmlHelper;

	private List<Codifica> codificheSitAscot = new ArrayList<Codifica>();

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.pdds.common.PddService#getResponse(java.lang.String)
	 */
	@Override
	public String getResponse(String xml) {
		StradarioAscotRichiesta richiesta = xmlHelper.unmarshal(xml, StradarioAscotRichiesta.class);

		String result = null;
		try {
			StradarioAscotRisposta risp = risposta(richiesta);
			result = xmlHelper.marshal(risp);

		}
		catch (Exception e) {
			log.error("getResponse :: " + e.getMessage(), e);
		}

		return result;

	}

	/**
	 * @param richiesta
	 * @return
	 */
	private StradarioAscotRisposta risposta(StradarioAscotRichiesta richiesta) {

		List<Indirizzo> indirizziAscotList = new ArrayList<Indirizzo>();

		StradarioAscotRisposta risposta = new StradarioAscotRisposta();

		Integer codiceViaAscot = null;
		it.osapulie.servizi.web.ws.types.StradarioAscotRichiesta.Indirizzo indirizzoRichiesta = richiesta.getIndirizzo();
		Integer codiceVia = indirizzoRichiesta.getCodiceVia();
		populateIndirizziList(codiceVia);
		for (Codifica codifica : codificheSitAscot) {
			if (codiceVia.equals(codifica.getCodiceViaSIT().intValue())) {
				codiceViaAscot = codifica.getCodiceViaAscot().intValue();
				break;
			}
		}

		if (codiceViaAscot == null) {
			// Codice via non trovato --> errore
			Errore errore = new Errore();
			errore.setCodice(2);
			errore.setDescrizione("Codice VIA non trovato!");
			risposta.setErrore(errore);
			return risposta;
		}

		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();

			URL wsdlLocation = classloader.getResource(WSDL_STRADARIO_ASCOT_DB);
			it.osapulie.servizi.StradarioAscotDBService stradarioService = new it.osapulie.servizi.StradarioAscotDBService(wsdlLocation);
			indirizziAscotList = stradarioService.getSOAP11Endpoint().getIndirizzi(codiceViaAscot);
		}
		catch (DataServiceFault e) {
			log.error("risposta :: " + e.getMessage(), e);
		}

		List<Indirizzo> indirizziAscot = new ArrayList<Indirizzo>();

		// Ricerca in indirizzi ASCOT
		for (Indirizzo indirizzoAscot : indirizziAscotList) {
			// Se codice frazione == "" --> bisogna inviare NULL nella query di ricerca Ascot
			if (indirizzoRichiesta.getCodiceFrazione() != null && indirizzoRichiesta.getCodiceFrazione().equals("")) {
				indirizzoRichiesta.setCodiceFrazione("0");
			}
			// Ricerca per codice frazione (se esiste)...
			if (indirizzoRichiesta.getCodiceFrazione() != null) {
				// Se Bari...
				if (indirizzoRichiesta.getCodiceFrazione().equals("0")) {
					if (indirizzoAscot.getCodiceFrazione() == null && BigInteger.valueOf(codiceViaAscot).equals(indirizzoAscot.getCodiceVia())) {
						indirizziAscot.add(indirizzoAscot);
					}
				}
				// ...altrimenti altre frazioni
				else {
					if (indirizzoRichiesta.getCodiceFrazione().equals(indirizzoAscot.getCodiceFrazione()) && BigInteger.valueOf(codiceViaAscot).equals(indirizzoAscot.getCodiceVia())) {
						indirizziAscot.add(indirizzoAscot);
					}
				}
			}
			// ...altrinenti ricerca senza codice frazione
			else {
				if (BigInteger.valueOf(codiceViaAscot).equals(indirizzoAscot.getCodiceVia())) {
					indirizziAscot.add(indirizzoAscot);
				}
			}
		}

		List<Indirizzo> indirizziAscotByCivico = new ArrayList<Indirizzo>();
		List<Indirizzo> indirizziAscotByCivicoAndBarrato = new ArrayList<Indirizzo>();
		List<Indirizzo> indirizziAscotByCivicoAndBarratoAndCorte = new ArrayList<Indirizzo>();

		// Se vuota --> non esiste l'indirizzo...
		if (indirizziAscot.isEmpty()) {
			Errore errore = new Errore();
			errore.setCodice(2);
			errore.setDescrizione("Via non trovata!");
			risposta.setErrore(errore);
			return risposta;
		}
		// ...altrimenti ricerca civici
		else {

			for (Indirizzo indirizzoAscot : indirizziAscot) {
				if (BigInteger.valueOf(indirizzoRichiesta.getNumeroCivico()).equals(indirizzoAscot.getNumeroCivico())) {
					indirizziAscotByCivico.add(indirizzoAscot);
				}
			}
		}

		// Se vuota --> non esiste il civico --> indico i civici disponibili
		if (indirizziAscotByCivico.isEmpty()) {

			StringBuilder descrizioneErroreStringBuilder = new StringBuilder("Numero civico '" + indirizzoRichiesta.getNumeroCivico() + "' non trovato. ");
			Set<String> descrizioneCiviciEsistenti = getDescrizioneCiviciEsistenti(indirizziAscot);
			descrizioneErroreStringBuilder.append(getDescrizione(descrizioneCiviciEsistenti));

			Errore errore = new Errore();
			errore.setCodice(3);
			errore.setDescrizione(descrizioneErroreStringBuilder.toString());
			risposta.setErrore(errore);
			return risposta;
		}
		// ...altrimenti ricerca barrato
		else {
			for (Indirizzo indirizzoAscotWithBarrato : indirizziAscotByCivico) {
				if (indirizzoAscotWithBarrato.getBarrato() == null) {
					indirizzoAscotWithBarrato.setBarrato("");
				}
				if (indirizzoAscotWithBarrato.getBarrato().equals(indirizzoRichiesta.getBarrato())) {
					indirizziAscotByCivicoAndBarrato.add(indirizzoAscotWithBarrato);
				}
			}
		}

		// Se vuota --> non esiste il barrato --> indico i barrati disponibili
		if (indirizziAscotByCivicoAndBarrato.isEmpty()) {
			String barrato = indirizzoRichiesta.getBarrato();
			if (barrato == null || barrato.isEmpty()) {
				barrato = NESSUN_VALORE;
			}
			StringBuilder descrizioneErroreStringBuilder = new StringBuilder("Esponente '" + barrato + "' non trovato. ");
			Set<String> descrizioneBarratiEsistenti = getDescrizioneBarratiEsistenti(indirizziAscotByCivico);
			descrizioneErroreStringBuilder.append(getDescrizione(descrizioneBarratiEsistenti));

			Errore errore = new Errore();
			errore.setCodice(4);
			errore.setDescrizione(descrizioneErroreStringBuilder.toString());
			risposta.setErrore(errore);
			return risposta;
		}
		// ...altrimenti ricerca corte
		else {
			for (Indirizzo indirizzo : indirizziAscotByCivicoAndBarrato) {
				if (indirizzo.getCorte() == null) {
					indirizzo.setCorte("");
				}
				if (indirizzo.getCorte().equals(indirizzoRichiesta.getCorte())) {
					indirizziAscotByCivicoAndBarratoAndCorte.add(indirizzo);
				}
			}
		}
		// Se vuota --> non esiste la corte --> indico le corti disponibili
		if (indirizziAscotByCivicoAndBarratoAndCorte.isEmpty()) {
			String corte = indirizzoRichiesta.getCorte();
			if (corte == null || corte.isEmpty()) {
				corte = NESSUN_VALORE;
			}
			StringBuilder descrizioneErroreStringBuilder = new StringBuilder("Corte '" + corte + "' non trovata. ");
			Set<String> descrizioneCortiEsistenti = getDescrizioneCortiEsistenti(indirizziAscotByCivicoAndBarrato);
			descrizioneErroreStringBuilder.append(getDescrizione(descrizioneCortiEsistenti));
			Errore errore = new Errore();
			errore.setCodice(5);
			errore.setDescrizione(descrizioneErroreStringBuilder.toString());
			risposta.setErrore(errore);
			return risposta;
		}
		else {
			if (indirizziAscotByCivicoAndBarratoAndCorte.size() == 1) {
				Indirizzo indirizzoRisultato = indirizziAscotByCivicoAndBarratoAndCorte.get(0);

				IndirizzoAscot indirizzoAscot = new IndirizzoAscot();
				indirizzoAscot.setBarrato(indirizzoRisultato.getBarrato());
				indirizzoAscot.setCodiceFrazione(indirizzoRisultato.getCodiceFrazione());
				indirizzoAscot.setCodiceVia(indirizzoRisultato.getCodiceVia().intValue());
				indirizzoAscot.setCorte(indirizzoRisultato.getCorte());
				indirizzoAscot.setIdentificativoCivico(indirizzoRisultato.getIdNumeroCivico().intValue());
				indirizzoAscot.setNumeroCivico(indirizzoRisultato.getNumeroCivico().intValue());

				risposta.setIndirizzoAscot(indirizzoAscot);
				return risposta;
			}
			else {
				Errore errore = new Errore();
				errore.setCodice(0);
				errore.setDescrizione("Errore generico: numero di risultati non corretto.");
				risposta.setErrore(errore);
				return risposta;
			}
		}
	}

	private Set<String> getDescrizioneCiviciEsistenti(List<Indirizzo> indirizziAscotByCivico) {

		Set<String> result = new TreeSet<String>();
		if (indirizziAscotByCivico != null) {
			Collections.sort(indirizziAscotByCivico, new IndirizzoComparator());
			for (int i = 0; i < indirizziAscotByCivico.size(); i++) {
				Indirizzo indirizzo = indirizziAscotByCivico.get(i);
				result.add(indirizzo.getNumeroCivico().toString());
			}
		}

		return result;
	}

	private Set<String> getDescrizioneBarratiEsistenti(List<Indirizzo> indirizziAscotByCivico) {

		Set<String> result = new TreeSet<String>();
		if (indirizziAscotByCivico != null) {
			for (int i = 0; i < indirizziAscotByCivico.size(); i++) {
				Indirizzo indirizzo = indirizziAscotByCivico.get(i);
				result.add(indirizzo.getBarrato());
			}
		}

		return result;
	}

	private Set<String> getDescrizioneCortiEsistenti(List<Indirizzo> indirizziAscotByCivico) {

		Set<String> result = new TreeSet<String>();
		if (indirizziAscotByCivico != null) {
			for (int i = 0; i < indirizziAscotByCivico.size(); i++) {
				Indirizzo indirizzo = indirizziAscotByCivico.get(i);
				result.add(indirizzo.getCorte());
			}
		}

		return result;
	}

	private String getDescrizione(Set<String> valori) {

		StringBuilder stringBuilder = new StringBuilder("Possibili valori: ");
		if (valori != null) {
			int i = 0;
			// Ordine valori possibili
			for (String valore : valori) {
				if (valore == null || valore.isEmpty()) {
					valore = NESSUN_VALORE;
				}
				stringBuilder.append("\"" + valore + "\"");
				if (i != valori.size() - 1) {
					stringBuilder.append(", ");
				}
				i++;
			}
		}

		return stringBuilder.toString();
	}

	/**
	 * Popola la lista di indirizzi dal WS.
	 *
	 * @param codiceViaSit
	 */
	private void populateIndirizziList(Integer codiceViaSit) {
		try {

			ClassLoader classloader = Thread.currentThread().getContextClassLoader();

			URL wsdlLocation = classloader.getResource(WSDL_STRADARIO_ASCOT_MAPPING_DB);
			StradarioMappingDBService stradarioMappingDBService = new StradarioMappingDBService(wsdlLocation);
			codificheSitAscot = stradarioMappingDBService.getSOAP11Endpoint().getCodifiche(codiceViaSit);

		}
		catch (DataServiceFault e) {
			log.error("populateIndirizziList :: " + e.getMessage(), e);
		}
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

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.pdds.common.PddService#getName()
	 */
	@Override
	public String getName() {
		return "stradarioAscotService";
	}

}
