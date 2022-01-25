package it.osapulie.web.ws.pddsintegration.pdd;

import it.puglia.rupar.egov.pdd.portaDiDominio.Busta;
import it.puglia.rupar.egov.pdd.portaDiDominio.EccezionePDDS;
import it.puglia.rupar.egov.pdd.portaDiDominio.PortaDelegata;
import it.puglia.rupar.egov.pdd.portaDiDominio.vo.busta.DescrizioneMessaggioVO;
import it.puglia.rupar.egov.pdd.portaDiDominio.vo.busta.EccezioneVO;
import it.puglia.rupar.egov.pdd.portaDiDominio.vo.busta.IdentificativoParteVO;
import it.puglia.rupar.egov.pdd.portaDiDominio.vo.busta.MessaggioVO;
import it.puglia.rupar.egov.pdd.portaDiDominio.vo.busta.OraRegistrazioneVO;
import it.puglia.rupar.egov.pdd.portaDiDominio.vo.busta.ProfiloCollaborazioneVO;
import it.puglia.rupar.egov.pdd.portaDiDominio.vo.busta.ProfiloTrasmissioneVO;
import it.puglia.rupar.egov.pdd.portaDiDominio.vo.busta.RiscontroVO;
import it.puglia.rupar.egov.pdd.portaDiDominio.vo.busta.TrasmissioneVO;
import it.puglia.rupar.egov.pdd.portaDiDominio.vo.contesto.ParametroVO;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

//import org.apache.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.omg.CORBA.portable.ApplicationException;

/**
 * Classe che implementa la porta delegata per i servizi che interrogano le porte applicative
 *
 * @author Maria Michela Birtolo
 */

public class PortaDelegataComuni {

	/**
	 * Porta Delegata
	 */
	public PortaDelegata pd = null;
	/**
	 * Logger
	 */
	public Logger cat;

	/**
	 * Costruttore della classe
	 *
	 * @throws EccezionePDDS
	 */
	public PortaDelegataComuni() throws EccezionePDDS {
		// Costruisce la porta delegata
		try {
			cat = LoggerFactory.getLogger(getClass().getName());
			pd = new PortaDelegata();
		}
		catch (EccezionePDDS e) {
			throw e;
		}
	}

	/**
	 * Mostra i parametri dei contesti
	 */
	@SuppressWarnings("unchecked")
	public void mostraParametriContesto() {
		Map pcIN = pd.getParametriContestoIn();
		Map pcOUT = pd.getParametriContestoOut();

		Iterator key = null;
		String valoreKey = null;
		ParametroVO parametro = null;

		System.out.println("\n** PARAMETRI CONTESTO INGRESSO **");
		if (pcIN == null) {
			System.out.println(" - Il contesto di ingresso non ha parametri!");
		}
		else {
			key = pcIN.keySet().iterator();
			while (key.hasNext()) {
				valoreKey = (String) key.next();
				parametro = (ParametroVO) pcIN.get(valoreKey);
				System.out.println(" - " + parametro);
			}
		}

		System.out.println("\n** PARAMETRI CONTESTO USCITA **");
		if (pcOUT == null) {
			System.out.println(" - Il contesto di uscita non ha parametri!");
		}
		else {
			key = pcOUT.keySet().iterator();
			while (key.hasNext()) {
				valoreKey = (String) key.next();
				parametro = (ParametroVO) pcOUT.get(valoreKey);
				System.out.println(" - " + parametro);
			}
		}
	}

	/**
	 * Mostra i trattamenti settati nei file di contesto
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	public void mostraTrattamentiContesto() {
		/*
		 * Mostra Trattamenti Contesto IN
		 */
		List ltcIN = pd.getListTrattamentiIn();
		System.out.println("\n** TRATTAMENTI CONTESTO INGRESSO **");
		if ((ltcIN == null) || (ltcIN != null && ltcIN.size() <= 0)) {
			System.out.println(" - Il contesto di ingresso non ha trattamenti!");
		}
		else {
			Iterator i = ltcIN.iterator();
			while (i.hasNext()) {
				String nomeTrattamento = (String) i.next();

				// preleva le proprietà del trattamento
				String attivo = pd.getActiveFromTrattamentoIn(nomeTrattamento);
				String bloccante = pd.getBloccanteFromTrattamentoIn(nomeTrattamento);
				String classe = pd.getClasseFromTrattamentoIn(nomeTrattamento);
				String descrizione = pd.getDescrizioneFromTrattamentoIn(nomeTrattamento);
				int ordine = pd.getOrdineFromTrattamentoIn(nomeTrattamento);

				// visualizza proprietà del trattamento
				System.out.println("\n - Trattamento: " + nomeTrattamento);
				if (attivo != null) {
					System.out.println("   - ATTIVO: " + (attivo.equals("ON") == true ? "SI" : "NO"));
				}
				else {
					System.out.println("   - ATTIVO: attributo non presente");
				}

				if (bloccante != null) {
					System.out.println("   - BLOCCANTE: " + (bloccante.equals("SI") == true ? "SI" : "NO"));
				}
				else {
					System.out.println("   - BLOCCANTE: attributo non presente");
				}

				if (ordine == pd.ORDINE_NON_PRESENTE) {
					System.out.println("   - ORDINE: attributo non presente");
				}
				else {
					System.out.println("   - ORDINE: " + ordine);
				}

				if (classe != null) {
					System.out.println("   - CLASSE: " + classe);
				}
				else {
					System.out.println("   - CLASSE: attributo non presente");
				}

				if (descrizione != null) {
					System.out.println("   - DESCRIZIONE: " + descrizione);

				}
				else {
					System.out.println("   - DESCRIZIONE: attributo non presente");
				}

				// preleva i e mostra parametri del trattamento
				Map pT = pd.getParametriFromTrattamentoIn(nomeTrattamento);
				if (pT == null) {
					System.out.println("   - PARAMETRI TRATTAMENTO: non presenti");
				}
				else {
					Iterator key = pT.keySet().iterator();
					while (key.hasNext()) {
						String valoreKey = (String) key.next();
						ParametroVO parametro = (ParametroVO) pT.get(valoreKey);
						System.out.println("     - " + parametro);
					}
				}
			}
		}

		/*
		 * Mostra Trattamenti Contesto OUT
		 */
		List ltcOUT = pd.getListTrattamentiOut();
		System.out.println("\n** TRATTAMENTI CONTESTO USCITA **");
		if ((ltcOUT == null) || (ltcOUT != null && ltcOUT.size() <= 0)) {
			System.out.println(" - Il contesto di ingresso non ha trattamenti!");
		}
		else {
			Iterator i = ltcOUT.iterator();
			while (i.hasNext()) {
				String nomeTrattamento = (String) i.next();

				// preleva le proprietà del trattamento
				String attivo = pd.getActiveFromTrattamentoOut(nomeTrattamento);
				String bloccante = pd.getBloccanteFromTrattamentoOut(nomeTrattamento);
				String classe = pd.getClasseFromTrattamentoOut(nomeTrattamento);
				String descrizione = pd.getDescrizioneFromTrattamentoOut(nomeTrattamento);
				int ordine = pd.getOrdineFromTrattamentoOut(nomeTrattamento);

				// visualizza proprietà del trattamento
				System.out.println("\n - Trattamento: " + nomeTrattamento);
				if (attivo != null) {
					System.out.println("   - ATTIVO: " + (attivo.equals("ON") == true ? "SI" : "NO"));
				}
				else {
					System.out.println("   - ATTIVO: attributo non presente");
				}

				if (bloccante != null) {
					System.out.println("   - BLOCCANTE: " + (bloccante.equals("SI") == true ? "SI" : "NO"));
				}
				else {
					System.out.println("   - BLOCCANTE: attributo non presente");
				}

				if (ordine == pd.ORDINE_NON_PRESENTE) {
					System.out.println("   - ORDINE: attributo non presente");
				}
				else {
					System.out.println("   - ORDINE: " + ordine);
				}

				if (classe != null) {
					System.out.println("   - CLASSE: " + classe);
				}
				else {
					System.out.println("   - CLASSE: attributo non presente");
				}

				if (descrizione != null) {
					System.out.println("   - DESCRIZIONE: " + descrizione);

				}
				else {
					System.out.println("   - DESCRIZIONE: attributo non presente");
				}

				// preleva i e mostra parametri del trattamento
				Map pT = pd.getParametriFromTrattamentoOut(nomeTrattamento);
				if (pT == null) {
					System.out.println("   - PARAMETRI TRATTAMENTO: non presenti");
				}
				else {
					Iterator key = pT.keySet().iterator();
					while (key.hasNext()) {
						String valoreKey = (String) key.next();
						ParametroVO parametro = (ParametroVO) pT.get(valoreKey);
						System.out.println("     - " + parametro);
					}
				}
			}
		}
	}

	/**
	 * Verifica se UDDI è abilitato. Restituisce null se l'UDDI non è abilitato, altrimenti
	 * restituisce l'URL dell'UDDI.
	 *
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public String getUddiURL() {
		String vRit = null;

		String nomeParametroRegUDDI = "UrlIndice";

		Map pcOUT = pd.getParametriContestoOut();
		if (pcOUT == null) {
			return vRit;
		}

		if (pcOUT.containsKey(nomeParametroRegUDDI)) {
			ParametroVO pvo = (ParametroVO) pcOUT.get(nomeParametroRegUDDI);
			vRit = pvo.getContenuto();
		}

		return vRit;
	}

	/**
	 * Setta (e conseguentemente abilita) l'inquiry point del registro UDDI
	 *
	 * @param url URL dell'indice
	 *
	 * @throws Exception
	 */

	@SuppressWarnings("unchecked")
	public void setUddiURL(String url) throws Exception {
		Map pcOUT = new HashMap();
		String nomeParametroRegUDDI = "UrlIndice";

		ParametroVO parametro = new ParametroVO(nomeParametroRegUDDI, url, "URL indice di ricerca");
		pcOUT.put(parametro.getNome(), parametro);

		pd.setContextOUT(pcOUT);
	}

	/**
	 * Invia una busta
	 *
	 * @param send busta da trasmettere
	 * @return busta ricevuta, null in caso di errore nella trasmissione
	 *
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Busta inviaBusta(Busta send) throws Exception {
		cat.debug("invio la busta");
		Busta vRit = null;

		String id = send.getMessaggio().getIdentificatore();

		vRit = pd.esegui(send);

		if (vRit == null) {
			System.out.println("\nMessaggio di tipo 'MessaggioSingoloOneWay' consegnato.");
			return vRit;
		}

		String idResp = vRit.getMessaggio().getIdentificatore();
		if (id.equals(idResp)) {
			cat.debug("** Si sono verificati degli errori durante la trasmissione");
			System.out.println("** Si sono verificati degli errori durante la trasmissione");
			if (vRit.hasEccezioni()) {
				Iterator i = vRit.getListaEccezioni().iterator();
				while (i.hasNext()) {
					EccezioneVO evo = (EccezioneVO) i.next();
					System.out.println(" - " + evo);
				}
			}

			// segnala l'errore
			vRit = null;
		}

		return vRit;
	}

	/**
	 * Costruisce una busta da trasmettere
	 *
	 * @param param
	 * @return busta costruita
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	private Busta costruisciBusta(String richiesta, String servizio, String url) throws Exception {
		Busta busta = null;

		try {
			/*
			 * [RICHIESTO] Costruisco inizializzo la busta. Per default: - il profilo di
			 * collaborazione è impostato a EGOV_IT_ServizioSincrono - il profilo di trasmissione è
			 * impostato a EGOV_IT_PIUDIUNAVOLTA
			 */
			busta = new Busta();

			/*
			 * [RICHIESTO] genero identificativo busta
			 */
			String idBusta = null;

			// primo metodo:
			// - Il progressivo è generato internamente
			// - Il codice amministrazione è ricavato dal parametro 'CodiceAmministrazione' del
			// contesto OUT
			// - Il codice porta è ricavato dal parametro 'CodicePorta' del contesto OUT
			idBusta = pd.generaID();

			if (idBusta == null) {
				// generazione dell'errore di intestazione
				System.out.println("ERRORE: Impossibile generare l'ID della busta");
				cat.debug("ERRORE: Impossibile generare l'ID della busta");
			}

			/*
			 * [RICHIESTO] setto la struttura 'MESSAGGIO' della busta
			 */
			MessaggioVO mvo = new MessaggioVO();
			mvo.setIdentificatore(idBusta);

			OraRegistrazioneVO orvo = new OraRegistrazioneVO();
			// orvo.setTempo(orvo.EGOV_IT_LOCALE);
			orvo.setTempo(orvo.EGOV_IT_SPC);
			System.out.println(Calendar.getInstance().toString());
			orvo.setContenuto(Calendar.getInstance());
			mvo.setOraRegistrazione(orvo);

			// Opzionale
			GregorianCalendar scadenza = new GregorianCalendar(Locale.ITALIAN);
			scadenza.add(scadenza.DAY_OF_MONTH, 3);
			// mvo.setScadenza(scadenza);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd");
			System.out.println(sdf.format(scadenza.getTime()));

			// Opzionale
			// mvo.setRiferimentoMessaggio(idBusta);
			busta.setMessaggio(mvo);

			/*
			 * [OPZIONALE] Setto il profilo di collaborazione.
			 *
			 * Per default, quando viene creata una nuova busta viene settato a
			 * EGOV_IT_ServizioSincrono.
			 */
			ProfiloCollaborazioneVO pcvo = new ProfiloCollaborazioneVO();

			pcvo.setContenuto(pcvo.EGOV_IT_ServizioSincrono);
			// pcvo.setContenuto(pcvo.EGOV_IT_MessaggioSingoloOneWay);
			// pcvo.setContenuto(pcvo.EGOV_IT_ServizioAsincronoAsimmetrico);
			busta.setProfiloCollaborazione(pcvo);
			// busta.setProfiloCollaborazione(null); // resetta il profilo di collaborazione

			/*
			 * [OPZIONALE] Setto il profilo di trasmissione
			 *
			 * Per default, quando viene creata una nuova busta viene settato a
			 * EGOV_IT_PIUDIUNAVOLTA
			 */
			ProfiloTrasmissioneVO ptvo = new ProfiloTrasmissioneVO();
			// ptvo.setInoltro(ptvo.EGOV_IT_PIUDIUNAVOLTA);
			ptvo.setInoltro(ptvo.EGOV_IT_ALPIUUNAVOLTA);
			// ptvo.setConfermaRicezione(true);
			busta.setProfiloTrasmissione(ptvo);
			// busta.setProfiloTrasmissione(null); // resetta il profilo di trasmissione

			/*
			 * [OPZIONALE] Setta Azione
			 */
			busta.setAzione(servizio);
			busta.setDestinatario("SPC", servizio, url);
			// String intestazione = busta.getIntestazione();
			// System.out.println("intestazione: "+intestazione);

			/*
			 * Aggiungi un Allegato (il Payload del messaggio trasmesso)
			 */
			// -----------------------

			/*
			 * ByteArrayOutputStream baos = new ByteArrayOutputStream(); ObjectOutputStream p = new
			 * ObjectOutputStream(baos); p.writeObject( param.toXml() );
			 */
			cat.debug("Creo il msg di richiesta");
			// String richiesta = createHeaderMessage();
			// richiesta = richiesta + param.toXml() +"</SOAP-ENV:Body></SOAP-ENV:Envelope>";

			ByteArrayInputStream bais = new ByteArrayInputStream(richiesta.getBytes());

			DescrizioneMessaggioVO dmvo = new DescrizioneMessaggioVO();
			dmvo.setAllegato(bais);
			dmvo.setHref("");
			dmvo.setRole("");
			dmvo.setId("corpo");
			dmvo.setLingua(dmvo.ITALIANO_ITALIA);
			dmvo.setPosizione("");

			dmvo.setTitolo(servizio);
			busta.setAllegato(dmvo);

			// busta.setAllegatoSenzaDescrizione("corpo",bais);

			// ------------------------

			String vi = busta.validaIntestazione();
			if (vi.equalsIgnoreCase("ok")) {
				System.out.println(" - Valida Intestazione Busta: OK");
				cat.debug(" - Valida Intestazione Busta: OK");
			}
			else {
				System.out.println(" - Intestazione non valida: " + vi);
				cat.debug(" - Intestazione non valida: " + vi);
			}
			List validaBusta = busta.validaBusta();
			if (validaBusta != null) {
				for (Object object : validaBusta) {
					cat.debug(" - validaBusta: " + object);
				}
			}
		}
		catch (Exception e) {
			throw e;
		}

		return busta;
	}

	/**
	 * Processa la busta ricevuta
	 *
	 * @param busta ricevuta
	 * @throws Exception
	 */
	@SuppressWarnings({ "static-access", "unchecked" })
	private String processaRisposta(Busta busta) throws Exception {
		if (busta == null) {
			return null;
		}
		String resp = null;
		// try{
		IdentificativoParteVO ipvo = null;

		// Processa IntestazioneMessaggio
		System.out.println("\n** Intestazione Messaggio **");

		ipvo = busta.getMittente();

		System.out.println(" - Mittente: " + ipvo);

		ipvo = busta.getDestinatario();
		System.out.println(" - Destinatario: " + ipvo);

		ProfiloCollaborazioneVO pcvo = null;
		pcvo = busta.getProfiloCollaborazione();
		System.out.println(" - Profilo Collaborazione: " + pcvo);

		System.out.println(" - Collaborazione: " + busta.getCollaborazione());

		System.out.println(" - Servizio: " + busta.getServizio());

		System.out.println(" - Azione: " + busta.getAzione());
		cat.debug(" - Azione: " + busta.getAzione());
		MessaggioVO mvo = null;

		mvo = busta.getMessaggio();
		System.out.println(" - Messaggio: " + mvo);
		cat.debug(" - Messaggio: " + mvo);

		ProfiloTrasmissioneVO ptvo = null;
		ptvo = busta.getProfiloTrasmissione();
		System.out.println(" - Profilo Trasmissione: " + ptvo);

		int seq = busta.getSequenza();
		if (seq >= 0) {
			System.out.println(" - Sequenza: " + busta.getSequenza());
		}
		else if (seq == busta.SEQUENZA_NON_VALIDA) {
			System.out.println(" - Sequenza non valida ");
		}
		else if (seq == busta.SEQUENZA_NON_PRESENTE) {
			System.out.println(" - Sequenza non presente");
		}

		// Processa Riscontri
		System.out.println("\n** Lista Riscontri **");
		if (busta.hasRiscontri()) {
			Iterator i = busta.getListaRiscontri().iterator();
			while (i.hasNext()) {
				RiscontroVO rvo = (RiscontroVO) i.next();
				System.out.println(" - Riscontro: " + rvo);
			}
		}
		else {
			System.out.println(" - Nessun Riscontro.");
		}

		// Processa Trasmissioni
		System.out.println("\n** Lista Trasmissioni **");
		if (busta.hasTrasmissioni()) {
			Iterator i = busta.getListaTrasmissioni().iterator();
			while (i.hasNext()) {
				TrasmissioneVO tvo = (TrasmissioneVO) i.next();
				System.out.println(" - Trasmissione: " + tvo);
			}
		}
		else {
			System.out.println(" - Nessuna Trasmissione.");
		}

		// Processa Eccezioni
		System.out.println("\n** Lista Eccezioni **");
		cat.debug("\n** Lista Eccezioni **");
		if (busta.hasEccezioni()) {
			Iterator i = busta.getListaEccezioni().iterator();
			while (i.hasNext()) {
				EccezioneVO evo = (EccezioneVO) i.next();
				System.out.println(" - Eccezione: " + evo);
				cat.debug(" - Eccezione: " + evo);
			}
		}
		else {
			System.out.println(" - Nessuna Eccezione.");
			cat.debug(" - Nessuna Eccezione.");
		}

		// Processa allegati con descrizione messaggio
		System.out.println("\n** Lista Allegati **");
		cat.debug("\n** Lista Allegati **");
		if (busta.hasAllegati()) {
			Iterator i = null;
			try {
				i = busta.getListaAllegati().iterator();
			}
			catch (Throwable e) {
				System.out.println(e.toString());
				return null;
			}
			while (i.hasNext()) {
				DescrizioneMessaggioVO dmvo = (DescrizioneMessaggioVO) i.next();
				System.out.println(" - Descrizione: " + dmvo);
				if (dmvo.getAllegato() == null) {
					System.out.println("   - Allegato nullo!");
					cat.debug("   - Allegato nullo!");
				}
				else {
					try {
						byte[] corpo = dmvo.getAllegatoAsArrayOfByte();

						resp = new String(corpo);
						cat.debug("   - Contenuto Allegato: " + resp);

						/*
						 * JAXBContext jc = JAXBContext.newInstance("it.osapulie.web.ws.types");
						 * //Creare l'unmarshaller Unmarshaller um = jc.createUnmarshaller();
						 * //Annullare il marshalling di contenuti XML del file myDoc.xml nel
						 * proprio oggetto Java oggetto Java. DatiAnagrafici datiAna =
						 * (DatiAnagrafici) um.unmarshal(new InputSource(new StringReader(tmp)));
						 * cat.debug("   - DatiAnagrafici via: " + datiAna.getDescrizioneVia());
						 */
						System.out.println("   - Dimensioni Allegato: " + dmvo.getAllegatoAsArrayOfByte().length);
						cat.debug("   - Dimensioni Allegato: " + dmvo.getAllegatoAsArrayOfByte().length);
					}
					catch (Exception e) {
						e.printStackTrace();
						System.out.println("   - Errore lettura allegato!");
						cat.error("   - Errore lettura allegato!");
						cat.error("   - Errore: ", e.getCause());
						/*
						 * PDDException[] errori = PDDException.parse(new
						 * String(dmvo.getAllegatoAsArrayOfByte())); if(errori!= null &&
						 * errori.length > 0) throw new
						 * ApplicationException(errori[0].getCodiceErrore
						 * (),errori[0].getDescrizioneErrore());
						 */
					}
				}
			}
		}
		else {
			System.out.println(" - Non sono presenti allegati.");
			cat.debug("   - Non sono presenti allegati");
		}

		// processa allegati senza descrizione allegato
		System.out.println("\n** Lista Allegati senza descrizione messaggio **");
		if (busta.hasAllegatiSenzaDescrizione()) {
			List lia = busta.getListaIdAllegatiSenzaDescrizione();
			Iterator i = lia.iterator();

			while (i.hasNext()) {
				String idAllegato = (String) i.next();
				byte[] dataASD = busta.getAllegatoSenzaDescrizioneAsArray(idAllegato);
				int size = dataASD != null ? dataASD.length : -1;
				System.out.println("   - Allegato senza descrizione: " + idAllegato + " [" + size + "]");
			}
			// deserializza il corpo
			byte[] corpo = busta.getAllegatoSenzaDescrizioneAsArray("corpo");

			if (corpo != null) {
				// ObjectInputStream p = new ObjectInputStream(new ByteArrayInputStream(corpo));
				// if(SERVIZIO.equals("getEsenzioniAttive")){
				// resp = (GetEsenzioniAttiveResponse)p.readObject();

				// }

			}
			else {
				System.out.println("Corpo NON TROVATO!!!");
			}
		}
		else {
			System.out.println(" - Non sono presenti allegati senza descrizione.");
		}

		return resp;
	}

	/**
	 * Metodo che avvia l'esecuzione della porta delegata
	 *
	 * @param param
	 * @return GetAssistibileInAnagrafeResponse
	 * @throws ApplicationException
	 * @throws Exception
	 */
	public synchronized String esegui(String richiesta, String servizio, String url) throws Exception {
		String risposta = new String();

		System.out.println("\n\n\n--*** LEGGE I PARAMETRI DAL CONTESTO ***--");
		this.mostraParametriContesto();

		System.out.println("\n\n\n--*** LEGGE I TRATTAMENTI DAL CONTESTO ***--");
		this.mostraTrattamentiContesto();

		System.out.println("\n\n\n--*** CONTROLLA ABILITAZIONE UDDI ***--");
		String urlUDDI = this.getUddiURL();
		if (urlUDDI == null) {
			System.out.println(" - UDDI è disabilitato");
		}
		else {
			System.out.println(" - UDDI è abilitato: " + urlUDDI);
		}

		System.out.println("\n\n\n--*** COSTRUISCE BUSTA DA TRASMETTERE ***--");
		cat.debug("\n\n\n--*** COSTRUISCE BUSTA DA TRASMETTERE ***--");
		Busta send = this.costruisciBusta(richiesta, servizio, url);
		if (send == null) {
			cat.debug(" - Busta nulla... non la posso trasmettere... ");
			System.out.println(" - Busta nulla... non la posso trasmettere... ");
			return null;
		}

		System.out.println("\n\n\n--*** TRASMETTE BUSTA ***--");
		cat.debug("\n\n\n--*** TRASMETTE BUSTA ***--");
		Busta recive = null;
		recive = this.inviaBusta(send);
		if (recive == null) {
			return null;
		}
		System.out.println(" - OK");

		System.out.println("\n\n\n--*** PROCESSA RISPOSTA ***--");
		cat.debug("\n\n\n--*** PROCESSA RISPOSTA ***--");
		risposta = this.processaRisposta(recive);

		return risposta;
	}
}
