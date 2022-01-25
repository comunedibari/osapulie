package it.osapulie.anagrafe.web.portlet.util.impl;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import it.osapulie.anagrafe.service.VisureService;
import it.osapulie.anagrafe.web.portlet.util.PortletUtils;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici.ComponentiNucleoFamiliare;
import it.osapulie.anagrafe.web.ws.output.types.DatiElettorali;
import it.osapulie.anagrafe.web.ws.output.types.DatiIndirizzo;
import it.osapulie.anagrafe.web.ws.output.types.DatiIndirizzo.VariazioniDomicilio;
import it.osapulie.anagrafe.web.ws.output.types.DatiUtente;
import it.osapulie.anagrafe.web.ws.output.types.PosizioniElettorali;
import it.osapulie.anagrafe.web.ws.output.types.RichiestaDatiElettorali;
import it.osapulie.domain.Comune;
import it.osapulie.domain.ComuneEstero;
import it.osapulie.domain.Template;
import it.osapulie.domain.servizi.Servizio;
import it.osapulie.infrastructure.impl.DateUtils;
import it.osapulie.infrastructure.impl.StreamUtils;
import it.osapulie.service.ComuneEsteroService;
import it.osapulie.service.ComuneService;
import it.osapulie.service.ServizioService;
import it.osapulie.service.TemplateService;
import it.osapulie.shared.service.UserPreferences;

/**
 * Helper per la generazione dei certificati.
 *
 * @author Mario Scalas
 * @author Maria Michela Birtolo
 * @author Gianluca Pindinelli
 */
@Component("templateCertificatiHelper")
public class TemplateCertificatiHelper {

	private final Logger log = LoggerFactory.getLogger(TemplateCertificatiHelper.class.getName());

	@Inject
	private ComuneService comuneService;

	@Inject
	private ComuneEsteroService comuneEsteroService;

	@Inject
	private VisureService visureService;

	@Inject
	private TemplateService templateService;

	@Inject
	private ServizioService servizioService;

	/**
	 * Metodo che sostituisce nell'xml del certificati i dati del soggetto per cui si richiede il
	 * certificato.
	 *
	 * @param dati
	 * @param componenteSel
	 * @param utilizzo
	 * @param numeroBollo
	 * @param datiUte
	 * @param nomeCert
	 * @param userPreferences
	 * @param codiceServizio
	 * @param osapulieHost
	 * @return
	 */
	public String componiXml(DatiAnagrafici dati, ComponentiNucleoFamiliare componenteSel, String utilizzo, String numeroBollo, DatiUtente datiUte, String nomeCert, UserPreferences userPreferences,
			String codiceServizio, String osapulieHost) throws IOException {

		String result = null;
		if (codiceServizio == null) {
			result = getDatiCertByNomeCertificato(dati, componenteSel, utilizzo, numeroBollo, datiUte, nomeCert, userPreferences, osapulieHost);
		}
		else {
			// Caricamento template da DB (se presente)
			Long idComuneIsa = userPreferences.getIdComuneIsa();
			Servizio servizio = servizioService.getServizioByCodiceServizio(codiceServizio);

			Template template = templateService.getTemplate(idComuneIsa, servizio.getId(), it.osapulie.web.portlet.util.PortletConstants.TEMPLATE_XML);
			if (template != null && template.getContenuto() != null) {
				result = getDatiCert(dati, componenteSel, utilizzo, numeroBollo, datiUte, userPreferences, template.getContenuto(), osapulieHost);
			}
			else {
				result = getDatiCertByNomeCertificato(dati, componenteSel, utilizzo, numeroBollo, datiUte, nomeCert, userPreferences, osapulieHost);
			}
		}

		return result;
	}

	/**
	 * @param dati
	 * @param componenteSel
	 * @param utilizzo
	 * @param numeroBollo
	 * @param datiUte
	 * @param nomeCert
	 * @param userPreferences
	 * @param osapulieHost
	 * @return
	 */
	private String getDatiCertByNomeCertificato(DatiAnagrafici dati, ComponentiNucleoFamiliare componenteSel, String utilizzo, String numeroBollo, DatiUtente datiUte, String nomeCert,
			UserPreferences userPreferences, String osapulieHost) throws IOException {
		InputStream resourceAsStream = TemplateCertificatiHelper.class.getResourceAsStream("/tplCertificati/" + nomeCert + ".xml");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));
		StringBuilder resultBuilder = new StringBuilder("");
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			resultBuilder.append(line);

		}
		String datiCert = resultBuilder.toString();
		datiCert = getDatiCert(dati, componenteSel, utilizzo, numeroBollo, datiUte, userPreferences, datiCert, osapulieHost);
		return datiCert;
	}

	/**
	 * @param dati
	 * @param componenteSel
	 * @param utilizzo
	 * @param numeroBollo
	 * @param datiUte
	 * @param userPreferences
	 * @param datiCert
	 * @param osapulieHost
	 * @return
	 */
	private String getDatiCert(DatiAnagrafici dati, ComponentiNucleoFamiliare componenteSel, String utilizzo, String numeroBollo, DatiUtente datiUte, UserPreferences userPreferences, String datiCert,
			String osapulieHost) {

		datiCert = datiCert.replaceAll("~utilizzo", utilizzo);
		datiCert = datiCert.replaceAll("~osapulieHost", osapulieHost);

		String marcaDaBollo = "";

		if (numeroBollo != null && !numeroBollo.equals("")) {
			marcaDaBollo += "<![CDATA[";
			marcaDaBollo += "<fo:block-container  text-align=\"center\" border=\"medium\" height=\"30mm\" width=\"20mm\">";
			marcaDaBollo += "<fo:block>";
			marcaDaBollo += " <fo:inline font-size=\"7\">";
			marcaDaBollo += "NUMERO MARCA DA BOLLO : " + numeroBollo;
			marcaDaBollo += "</fo:inline>";
			marcaDaBollo += "</fo:block>";
			marcaDaBollo += " </fo:block-container>";
			marcaDaBollo += "]]>";
		}
		datiCert = datiCert.replaceAll("~marcaDaBollo", marcaDaBollo);
		datiCert = datiCert.replaceAll("~nome", componenteSel.getNome());
		datiCert = datiCert.replaceAll("~cognome", componenteSel.getCognome());
		datiCert = datiCert.replaceAll("~codFisc", componenteSel.getCodiceFiscale());
		datiCert = datiCert.replaceAll("~dataN", DateUtils.getDataGGMMAAAA(componenteSel.getDataNascita().getTime()));

		// scrivo "nato" o "nata" in base al sesso del cittadino
		if (componenteSel.getSesso().equals("F")) {
			datiCert = datiCert.replaceAll("~nato_a", "nata");
		}
		else {
			datiCert = datiCert.replaceAll("~nato_a", "nato");
		}

		// Caso certificato di nascita: il giorno e il mese di nascita devono comparire in lettera
		// maiuscola
		SimpleDateFormat sdfMese = new SimpleDateFormat("MMMM", Locale.ITALIAN);
		int giornoNascita = componenteSel.getDataNascita().get(Calendar.DAY_OF_MONTH);
		int annoNascita = componenteSel.getDataNascita().get(Calendar.YEAR);
		datiCert = datiCert.replaceAll("~giornoNascita", PortletUtils.toCharactersForm(giornoNascita).toUpperCase());
		datiCert = datiCert.replaceAll("~meseNascita", sdfMese.format(componenteSel.getDataNascita().getTime()).toUpperCase());
		datiCert = datiCert.replaceAll("~annoNascita", PortletUtils.toCharactersForm(annoNascita).toUpperCase());

		// Caricamento comune da codice ISTAT
		if (componenteSel.isCittadinanzaItaliana() && componenteSel.getCodiceIstatComuneNascita() != null) {
			Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(componenteSel.getCodiceIstatComuneNascita());
			if (comuneByCodiceISTAT != null) {
				datiCert = datiCert.replaceAll("~comuneN", comuneByCodiceISTAT.getDenominazione());
				datiCert = datiCert.replaceAll("~provN", comuneByCodiceISTAT.getProvincia().getDenominazioneProvincia());
			}
			// Ricerca in comuni esteri
			else {
				if (!componenteSel.getCodiceIstatComuneNascita().isEmpty()) {
					ComuneEstero comuneEsteroByCodice = comuneEsteroService.getComuneEsteroByCodice(Integer.parseInt(componenteSel.getCodiceIstatComuneNascita()));
					if (comuneEsteroByCodice != null) {
						datiCert = datiCert.replaceAll("~comuneN", comuneEsteroByCodice.getDenominazione());
						datiCert = datiCert.replace("(~provN)", "");
					}
				}
			}
		}

		// inserisco i componenti del nucleo familiare
		String componenti = "";
		for (int i = 0; i < dati.getComponentiNucleoFamiliare().size(); i++) {
			ComponentiNucleoFamiliare familiare = dati.getComponentiNucleoFamiliare().get(i);

			if (!familiare.getCodiceFiscale().equals(componenteSel.getCodiceFiscale())) {
				componenti += familiare.getNome() + " " + familiare.getCognome() + "\n";
				componenti += "Cod.Fisc. " + familiare.getCodiceFiscale() + "\n";
				if (familiare.isCittadinanzaItaliana() && familiare.getCodiceIstatComuneNascita() != null) {
					Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(familiare.getCodiceIstatComuneNascita());
					if (comuneByCodiceISTAT != null) {
						componenti += "Nato il " + DateUtils.getDataGGMMAAAA(familiare.getDataNascita().getTime()) + " a " + comuneByCodiceISTAT.getDenominazione() + " ( "
								+ comuneByCodiceISTAT.getProvincia().getDenominazioneProvincia() + " ) \n";
					}
				}
				else {
					componenti += "Nato il " + DateUtils.getDataGGMMAAAA(familiare.getDataNascita().getTime()) + " a " + familiare.getDescrizioneComuneEsteroNascita() + " ( "
							+ familiare.getStatoEsteroNascita() + " ) \n";
				}
				String relazioneParentela = familiare.getRelazioneParentela();
				if (relazioneParentela != null) {
					componenti += "" + relazioneParentela.toUpperCase();
				}
			}
		}
		datiCert = datiCert.replaceAll("~componenti", componenti);

		datiCert = datiCert.replaceAll("~dataMorte", (componenteSel.getDataMorte() != null ? DateUtils.getDataGGMMAAAA(componenteSel.getDataMorte().getTime()) : ""));

		if (componenteSel.getCodiceIstatComuneMorte() != null) {
			Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(componenteSel.getCodiceIstatComuneMorte());
			if (comuneByCodiceISTAT != null) {
				datiCert = datiCert.replaceAll("~comuneMorte", (comuneByCodiceISTAT.getDenominazione() != null ? comuneByCodiceISTAT.getDenominazione() : ""));
				datiCert = datiCert.replaceAll("~provMorte",
						(comuneByCodiceISTAT.getProvincia().getDenominazioneProvincia() != null ? comuneByCodiceISTAT.getProvincia().getDenominazioneProvincia() : ""));
			}
		}

		datiCert = datiCert.replaceAll("~attoMorte", (componenteSel.getNumeroAttoMorte() + "" != null ? componenteSel.getNumeroAttoMorte() + "" : ""));
		datiCert = datiCert.replaceAll("~parteMorte", (componenteSel.getParteMorte() != null ? componenteSel.getParteMorte() : ""));
		datiCert = datiCert.replaceAll("~serieMorte", (componenteSel.getSerieMorte() != null ? componenteSel.getSerieMorte() : ""));

		if (componenteSel.isCittadinanzaItaliana()) {
			datiCert = datiCert.replaceAll("~cittadinanza", "E' CITTADINO ITALIANO");
		}
		else {
			datiCert = datiCert.replaceAll("~cittadinanza", "NON E' CITTADINO ITALIANO");
		}
		if (componenteSel.getNumeroAttoNascita() != null) {
			datiCert = datiCert.replaceAll("~attoN", "" + componenteSel.getNumeroAttoNascita());
		}
		else {
			datiCert = datiCert.replaceAll("~attoN", "-");
		}
		if (componenteSel.getParteNascita() != null) {
			datiCert = datiCert.replaceAll("~parteN", componenteSel.getParteNascita());
		}
		else {
			datiCert = datiCert.replaceAll("~parteN", "-");
		}
		if (componenteSel.getSerieNascita() != null) {
			datiCert = datiCert.replaceAll("~serieN", componenteSel.getSerieNascita());
		}
		else {
			datiCert = datiCert.replaceAll("~serieN", "-");
		}
		if (componenteSel.getAnnoAttoNascita() != null) {
			datiCert = datiCert.replaceAll("~annoAttoN", "" + componenteSel.getAnnoAttoNascita());
		}
		else {
			datiCert = datiCert.replaceAll("~annoAttoN", "-");
		}
		String via = (dati.getToponimoIndirizzo() != null && !dati.getToponimoIndirizzo().equals("") ? " " + dati.getToponimoIndirizzo() : "") + " "
				+ (dati.getDescrizioneVia() != null && !dati.getDescrizioneVia().equals("") ? dati.getDescrizioneVia() : "              ") + " n."
				+ (dati.getNumeroCivico() != null && !dati.getNumeroCivico().equals("") ? " " + dati.getNumeroCivico() : "           ")
				+ (dati.getEsponente() != null && !dati.getEsponente().equals("") ? " " + dati.getEsponente() : "")
				+ (dati.getScala() != null && !dati.getScala().equals("") ? " scala " + dati.getScala() : "")
				+ (dati.getPiano() != null && !dati.getPiano().equals("") ? " piano " + dati.getPiano() : "");

		datiCert = datiCert.replaceAll("~via", via);

		if (datiUte != null) {
			if (datiUte.getCap() != null) {
				datiCert = datiCert.replaceAll("~capRes", datiUte.getCap());
			}
			else {
				datiCert = datiCert.replaceAll("~capRes", "-");
			}

			if (datiUte.getComuneResidenza() != null) {
				datiCert = datiCert.replaceAll("~comuneRes", datiUte.getComuneResidenza());
			}
			else {
				datiCert = datiCert.replaceAll("~comuneRes", "-");
			}

			if (datiUte.getProvinciaResidenza() != null) {
				datiCert = datiCert.replaceAll("~provRes", datiUte.getProvinciaResidenza());
			}
			else {
				datiCert = datiCert.replaceAll("~provRes", "-");
			}
		}

		// DATI MATRIMONIO
		datiCert = datiCert.replaceAll("~coniuge", componenteSel.getCognomeNomeConiuge());
		if (componenteSel.getNumeroAttoMatrimonio() != null) {
			datiCert = datiCert.replaceAll("~attoM", "" + componenteSel.getNumeroAttoMatrimonio());
		}
		else {
			datiCert = datiCert.replaceAll("~attoM", "-");
		}
		if (componenteSel.getParteMatrimonio() != null) {
			datiCert = datiCert.replaceAll("~parteM", componenteSel.getParteMatrimonio());
		}
		else {
			datiCert = datiCert.replaceAll("~parteM", "-");
		}
		if (componenteSel.getSerieMatrimonio() != null) {
			datiCert = datiCert.replaceAll("~serieM", componenteSel.getSerieMatrimonio());
		}
		else {
			datiCert = datiCert.replaceAll("~serieM", "-");
		}
		if (componenteSel.getCodiceIstatComuneMatrimonio() != null) {
			Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(componenteSel.getCodiceIstatComuneMatrimonio());
			if (comuneByCodiceISTAT != null) {
				datiCert = datiCert.replaceAll("~comuneM", comuneByCodiceISTAT.getDenominazione());
			}
		}
		else {
			datiCert = datiCert.replaceAll("~comuneM", "-");
		}
		if (componenteSel.getDataMatrimonio() != null) {
			datiCert = datiCert.replaceAll("~dataM", DateUtils.getDataGGMMAAAA(componenteSel.getDataMatrimonio().getTime()));
		}
		else {
			datiCert = datiCert.replaceAll("~dataM", "-");
		}

		if (componenteSel.getStatoCivile() != null) {
			datiCert = datiCert.replaceAll("~statoCivile", componenteSel.getStatoCivile().toUpperCase());
		}
		else {
			datiCert = datiCert.replaceAll("~statoCivile", "-");
		}

		datiCert = datiCert.replaceAll("~comuneFirma", userPreferences.getNomeComune());

		// Ciclo per la generazione della lista, già formattata, di liste elettorali associate ad un
		// cittadino

		// recupero i dati elettorali
		RichiestaDatiElettorali richiesta = new RichiestaDatiElettorali();
		richiesta.setCodiceFiscale(componenteSel.getCodiceFiscale());
		String listeElettorali = "";
		try {
			DatiElettorali datiEle = visureService.richiediDatiElettorali(richiesta, userPreferences);

			List<PosizioniElettorali> posizioniElettorali = datiEle.getPosizioniElettorali();
			if (datiEle != null && posizioniElettorali != null && !posizioniElettorali.isEmpty()) {
				for (PosizioniElettorali p : posizioniElettorali) {
					listeElettorali += "<fo:block space-after=\"2pt\"> ";
					listeElettorali += "LISTA GENERALE N°. " + p.getNumeroListaGenerale();
					listeElettorali += " </fo:block> ";
				}
			}
		}
		catch (Exception e) {
			log.warn("componiXml :: " + e.getMessage());
		}
		datiCert = datiCert.replaceAll("~listeElettorali", listeElettorali);

		// recupero i dati dei familiari (CERTIFICATO STATO FAMIGLIA)
		String listaFamiliari = "";
		for (int i = 0; i < dati.getComponentiNucleoFamiliare().size(); i++) {
			ComponentiNucleoFamiliare familiare = dati.getComponentiNucleoFamiliare().get(i);
			if (!familiare.getCodiceFiscale().equals(componenteSel.getCodiceFiscale()) && familiare.getDataMorte() == null) {
				// mellif 20150316: comune e provincia passati come parametri in seguito
				// all'introduzione del codiceIstat
				Comune comuneNascitaFam = comuneService.getComuneByCodiceISTAT(familiare.getCodiceIstatComuneNascita());

				listaFamiliari += " <fo:block space-before=\"4pt\">";
				listaFamiliari += familiare.getNome() + " " + familiare.getCognome();
				listaFamiliari += " </fo:block>";

				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				String dataNascita = formatter.format(familiare.getDataNascita().getTime());

				listaFamiliari += " <fo:block>";

				if (familiare.getSesso().equals("F")) {
					listaFamiliari += " nata";
				}
				else {
					listaFamiliari += " nato";
				}

				listaFamiliari += " il " + dataNascita;
				if (comuneNascitaFam != null) {
					listaFamiliari += " a " + comuneNascitaFam.getDenominazione() + " (" + comuneNascitaFam.getProvincia().getDenominazioneProvincia() + ")";
				}
				listaFamiliari += " </fo:block>";

				listaFamiliari += " <fo:block>";
				listaFamiliari += " atto n. " + familiare.getNumeroAttoNascita();
				listaFamiliari += " </fo:block>";

				listaFamiliari += " <fo:block>";
				if (familiare.getRelazioneParentela() != null) {
					listaFamiliari += familiare.getRelazioneParentela().toUpperCase();
				}
				listaFamiliari += " </fo:block>";
			}
		}
		datiCert = datiCert.replaceAll("~listaFamiliari", listaFamiliari);

		// l'utente ha scelto prima di invocare il servizio
		datiCert = datiCert.replaceAll("~dataCorr", DateUtils.getDataCorrente());
		return datiCert;
	}

	/**
	 * Metodo che sostituisce nell'xml i dati relativi alla componente elettorale.
	 *
	 * @param dati
	 * @param xml
	 * @return String
	 */
	public String componiXmlDatiElettorali(DatiElettorali dati, String xml) {

		String datiCert = null;
		List<PosizioniElettorali> posizioniElettorali = dati.getPosizioniElettorali();
		if (posizioniElettorali != null && !posizioniElettorali.isEmpty()) {
			for (PosizioniElettorali posizioneElettorale : posizioniElettorali) {
				datiCert = xml.replaceAll("~numSez", "" + posizioneElettorale.getNumeroSezione());
				datiCert = datiCert.replaceAll("~statoIsc", (posizioneElettorale.getAnnoIscrizioneElett() != null ? "ISCRITTO" : "NON ISCRITTO"));
			}
		}

		return datiCert;
	}

	/**
	 * Metodo che sostituisce nell'xml i dati relativi alle variazioni domiciliari.
	 *
	 * @param dati
	 * @param xml
	 * @return String
	 */
	public String componiXmlDatiVariazioniDomiciliari(DatiIndirizzo dati, String xml) {
		String datiCert = "";
		List<VariazioniDomicilio> ele = dati.getVariazioniDomicilio();
		for (int i = 0; i < ele.size(); i++) {
			VariazioniDomicilio dom = ele.get(i);
			datiCert += "dal " + DateUtils.getDataGGMMAAAA(dom.getDataInizioInd().getTime()) + "\n";
			String via = dom.getDescrizioneTopSto() + " " + dom.getDescrizioneViaSto() + " n." + dom.getNumeroCivicoSto()
					+ (dom.getEsponenteSto() != null && !dom.getEsponenteSto().equals("") ? " " + dom.getEsponenteSto() : "")
					+ (dom.getScalaSto() != null && !dom.getScalaSto().equals("") ? " scala " + dom.getScalaSto() : "")
					+ (dom.getPianoSto() != null && !dom.getPianoSto().equals("") ? " piano " + dom.getPianoSto() : "");
			datiCert += "    		in via " + via + "\n";
			datiCert += "    		a " + dom.getDescComune() + "\n\n    		";
		}
		datiCert = xml.replaceAll("~variazioni", datiCert);
		return datiCert;
	}
}
