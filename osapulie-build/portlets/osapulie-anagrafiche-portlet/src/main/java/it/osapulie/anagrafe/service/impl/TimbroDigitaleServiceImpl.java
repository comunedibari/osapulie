package it.osapulie.anagrafe.service.impl;

import it.eng.tz.avtmb.integration.client.StampsignClientService;
import it.eng.tz.avtmb.integration.client.dto.StampSignAuthResponse;
import it.eng.tz.avtmb.integration.client.dto.StampSignRequest;
import it.eng.tz.avtmb.integration.client.dto.StampSignResponse;
import it.land.securepaperappliance.spservice.xsd.ObjectFactory;
import it.land.securepaperappliance.spservice.xsd.SPServiceResponse;
import it.land.securepaperappliance.spservice.xsd.SignerBean;
import it.osapulie.anagrafe.service.TimbroDigitaleService;
import it.osapulie.anagrafe.service.VisureService;
import it.osapulie.anagrafe.web.portlet.util.impl.PortletConstants;
import it.osapulie.anagrafe.web.ws.output.types.*;
import it.osapulie.domain.Comune;
import it.osapulie.domain.ComuneEstero;
import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.TimbroConfig;
import it.osapulie.infrastructure.ReportService;
import it.osapulie.infrastructure.impl.DateUtils;
import it.osapulie.persistence.ComuneISARepository;
import it.osapulie.service.ComuneEsteroService;
import it.osapulie.service.ComuneService;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.web.portlet.util.impl.LiferayPortletHelperImpl;
import it.osapulie.web.ws.timbrodigitale.SPPortType;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.inject.Inject;
import javax.xml.bind.JAXBElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Implementazione di {@link TimbroDigitaleService} che interroga il servizio remoto offerto da
 * Ancitel che è in grado di confezionare un documento PDF contenente una certificazione anagrafica
 * emessa da un Ufficiale Anagrafico e quindi valida a tutti gli effetti di legge. Questo servizio
 * utilizza la tecnologia del Timbro Digitale.
 *
 * @author Maria Michela Birtolo
 */
@Service("timbroDigitaleService")
public class TimbroDigitaleServiceImpl implements TimbroDigitaleService {

	private final Logger log = LoggerFactory.getLogger(TimbroDigitaleServiceImpl.class);

	@Inject
	private SPPortType timbroDigitaleClient;

	@Inject
	private ComuneISARepository comuneISARepository;

	@Inject
	private VisureService visureService;

	@Inject
	private ComuneService comuneService;

	@Inject
	private ComuneEsteroService comuneEsteroService;

	@Inject
	private ReportService reportService;

	@Inject
	private StampsignClientService stampsignClientService;

	@Override
	public byte[] richiediCertificatoTimbrato(String xml, UserPreferences userPreferences) {

		JAXBElement<byte[]> certificato = null;
		log.debug("xml=" + xml);
		String codiceIstat = userPreferences.getCodiceIstatComune();
		ComuneISA comuneISA = comuneISARepository.findByCodiceIstat(codiceIstat);

		TimbroConfig config = comuneISA.getTimbroConfig();
		log.debug("TDService: username=" + config.getUsername());
		log.debug("TDService: psw=" + config.getPassword());
		log.debug("TDService: pin=" + config.getPin());
		log.debug("TDService: serviceId=" + config.getServiceId());
		log.debug("TDService: domain=" + config.getDomain());
		log.debug("TDService: signServerHost=" + config.getServerHost());

		// SPPortType port = timbroDigitaleClient.getSPHttpSoap11Endpoint();
		List<SignerBean> params = new ArrayList<SignerBean>();
		SignerBean param = new SignerBean();
		ObjectFactory fact = new ObjectFactory();
		param.setDomain(fact.createSignerBeanDomain(""));
		param.setUser(fact.createSignerBeanUser(""));
		param.setPassword(fact.createSignerBeanPassword(""));
		param.setPin(fact.createSignerBeanPin(""));
		param.setHsmIp(fact.createSignerBeanHsmIp(""));
		params.add(param);

		log.debug("prop=" + System.getProperty("javax.net.ssl.keyStore"));
		log.debug("prop=" + System.getProperty("javax.net.ssl.keyStoreType"));
		log.debug("prop=" + System.getProperty("javax.net.ssl.keyStorePassword"));
		log.debug("prop=" + System.getProperty("javax.net.ssl.trustStore"));
		log.debug("prop=" + System.getProperty("javax.net.ssl.trustStorePassword"));
		log.debug("prop=" + System.getProperty("javax.net.ssl.trustStoreType"));

		try {
			SPServiceResponse risposta = timbroDigitaleClient.securizeXML("" + config.getServiceId(), xml.getBytes(), params);
			log.debug("SPServiceResponseStatus=" + risposta.getStatus());
			log.debug("SPServiceResponseReason=" + risposta.getReason().getValue());
			certificato = risposta.getSecuredDocument();
		}
		catch (Exception e) {
			log.error("richiediCertificatoTimbrato :: " + e.getMessage(), e);
		}

		log.debug("certRicevuto=" + certificato);
		return certificato.getValue();
	}

	@Override
	public byte[] richiediCertificatoTimbrato(Map<String,String> servizio, String codiceFiscale, UserPreferences userPreferences, String utilizzoBollo, String numeroBollo, DatiAnagrafici.ComponentiNucleoFamiliare componenteSelezionato,DatiAnagrafici dati) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		DatiAnagraficiGenerali richDatiAna = new DatiAnagraficiGenerali();
		richDatiAna.setCodiceFiscale(codiceFiscale);
		DatiUtente datiUte = visureService.richiediDatiAnagraficiAltriServizi(richDatiAna, userPreferences);
		if (datiUte != null) {
			if (datiUte.getCap() != null) {
				param.put("capRes", datiUte.getCap());
			} else {
				param.put("capRes", "-");
			}

			if (datiUte.getComuneResidenza() != null) {
				param.put("comuneRes", datiUte.getComuneResidenza());
			} else {
				param.put("comuneRes", "-");
			}

			if (datiUte.getProvinciaResidenza() != null) {
				param.put("provRes", datiUte.getProvinciaResidenza());
			} else {
				param.put("provRes", "-");
			}
			if (datiUte.getIndirizzo() != null) {
				param.put("viaRes", dati.getToponimoIndirizzo() + " " + dati.getDescrizioneVia() + " n. " + dati.getNumeroCivico() + " " + dati.getEsponente() + " Scala " + dati.getScala() + " Piano " + dati.getPiano());
			} else {
				param.put("viaRes", "-");
			}

		}
		if (numeroBollo != null && numeroBollo.length() > 0) {
			param.put("bollo", "NUMERO MARCA DA BOLLO : " + numeroBollo);
		} else {
			param.put("bollo", "");
		}

		if (componenteSelezionato.getNumeroAttoNascita() != null) {
			param.put("attoN", "" + componenteSelezionato.getNumeroAttoNascita());
		}
		else {
			param.put("attoN", "-");
		}
		if (componenteSelezionato.getParteNascita() != null) {
			param.put("parteN", componenteSelezionato.getParteNascita());
		}
		else {
			param.put("parteN", "-");
		}
		if (utilizzoBollo != null) {
			param.put("uso", utilizzoBollo);
		}
		else {
			param.put("uso", "-");
		}
		if (componenteSelezionato.getSerieNascita() != null) {
			param.put("serieN", componenteSelezionato.getSerieNascita());
		}
		else {
			param.put("serieN", "-");
		}
		if (componenteSelezionato.getAnnoAttoNascita() != null) {
			param.put("annoAttoN", "" + componenteSelezionato.getAnnoAttoNascita());
		}
		else {
			param.put("annoAttoN", "-");
		}
		if (componenteSelezionato.getStatoCivile() != null) {
			param.put("statoCivile", "" + componenteSelezionato.getStatoCivile());
		}
		else {
			param.put("statoCivile", "-");
		}
		if (componenteSelezionato.getRelazioneParentela() != null) {
			param.put("relazioneParentela", "" + componenteSelezionato.getRelazioneParentela());
		}
		else {
			param.put("relazioneParentela", "-");
		}
		if(servizio == PortletConstants.CODICE_SERVIZIO_RICHIESTA_CERTIFICATO_ISCRIZIONE_LISTE_ELETTORALI) {
			RichiestaDatiElettorali richiesta = new RichiestaDatiElettorali();
			richiesta.setCodiceFiscale(richDatiAna.getCodiceFiscale());
			DatiElettorali var = visureService.richiediDatiElettorali(richiesta, userPreferences);
			List<PosizioniElettorali> lista = var.getPosizioniElettorali();
			if (var != null && lista != null && !lista.isEmpty()) {
				// nel PDF
				param.put("subreportParameters", lista);
			}


			if (lista.get(0).getNumeroSezione() != null) {
				param.put("numeroSezione", "" + lista.get(0).getNumeroSezione());
			} else {
				param.put("numeroSezione", "-");
			}
			if (lista.get(0).getNumeroTesseraElettorale() != null) {
				param.put("numeroTesseraElettorale", "" + lista.get(0).getNumeroTesseraElettorale());
			} else {
				param.put("numeroTesseraElettorale", "-");
			}
			if (lista.get(0).getNumeroSezione() != null) {
				param.put("numeroSezione", "" + lista.get(0).getNumeroSezione());
			} else {
				param.put("numeroSezione", "-");
			}
			if (lista.get(0).getNumeroListaGenerale() != null) {
				param.put("numeroListaGenerale", "" + lista.get(0).getNumeroListaGenerale());
			} else {
				param.put("numeroListaGenerale", "-");
			}
			if (lista.get(0).getNumeroListaSezionale() != null) {
				param.put("numeroListaSezionale", "" + lista.get(0).getNumeroListaSezionale());
			} else {
				param.put("numeroListaSezionale", "-");
			}
		}

		
		Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(componenteSelezionato.getCodiceIstatComuneNascita());
		if (comuneByCodiceISTAT != null) {
			param.put("comuneNascita", comuneByCodiceISTAT.getDenominazione());
			param.put("provinciaNascita", comuneByCodiceISTAT.getProvincia().getDenominazioneProvincia());
		}
		// Ricerca in comuni esteri
		else {
			if (!componenteSelezionato.getCodiceIstatComuneNascita().isEmpty()) {
				ComuneEstero comuneEsteroByCodice = comuneEsteroService.getComuneEsteroByCodice(Integer.parseInt(componenteSelezionato.getCodiceIstatComuneNascita()));
				if (comuneEsteroByCodice != null) {
					param.put("comuneNascita", comuneEsteroByCodice.getDenominazione());
				}
			}
		}
		if (comuneByCodiceISTAT != null) {
			param.put("comuneNascita", comuneByCodiceISTAT.getDenominazione());
			param.put("provinciaNascita", comuneByCodiceISTAT.getProvincia().getDenominazioneProvincia());
		}
		// Ricerca in comuni esteri
		else {
			if (!componenteSelezionato.getCodiceIstatComuneNascita().isEmpty()) {
				ComuneEstero comuneEsteroByCodice = comuneEsteroService.getComuneEsteroByCodice(Integer.parseInt(componenteSelezionato.getCodiceIstatComuneNascita()));
				if (comuneEsteroByCodice != null) {
					param.put("comuneNascita", comuneEsteroByCodice.getDenominazione());
				}
			}
		}


		SimpleDateFormat sdfMese = new SimpleDateFormat("MMMM", Locale.ITALIAN);
		int giornoNascita = componenteSelezionato.getDataNascita().get(Calendar.DAY_OF_MONTH);
		int annoNascita = componenteSelezionato.getDataNascita().get(Calendar.YEAR);
		int meseNascita = componenteSelezionato.getDataNascita().get(Calendar.MONTH);

		//giorni
		if(giornoNascita == 1){
			param.put("giornoNascita", "UNO");
		}
		else if(giornoNascita == 2){
			param.put("giornoNascita", "DUE");
		}
		else if(giornoNascita == 3){
			param.put("giornoNascita", "TRE");
		}
		else if(giornoNascita == 4){
			param.put("giornoNascita", "QUATTRO");
		}
		else if(giornoNascita == 5){
			param.put("giornoNascita", "CINQUE");
		}
		else if(giornoNascita == 6){
			param.put("giornoNascita", "SEI");
		}
		else if(giornoNascita == 7){
			param.put("giornoNascita", "SETTE");
		}
		else if(giornoNascita == 8){
			param.put("giornoNascita", "OTTO");
		}
		else if(giornoNascita == 9){
			param.put("giornoNascita", "NOVE");
		}
		else if(giornoNascita == 10){
			param.put("giornoNascita", "DIECI");
		}
		else if(giornoNascita == 11){
			param.put("giornoNascita", "UNDICI");
		}
		else if(giornoNascita == 12){
			param.put("giornoNascita", "DODICI");
		}
		else if(giornoNascita == 13){
			param.put("giornoNascita", "TREDICI");
		}
		else if(giornoNascita == 14){
			param.put("giornoNascita", "QUATTORDICI");
		}
		else if(giornoNascita == 15){
			param.put("giornoNascita", "QUINDICI");
		}
		else if(giornoNascita == 16){
			param.put("giornoNascita", "SEDICI");
		}
		else if(giornoNascita == 17){
			param.put("giornoNascita", "DICIASSETTE");
		}
		else if(giornoNascita == 18){
			param.put("giornoNascita", "DICIOTTO");
		}
		else if(giornoNascita == 19){
			param.put("giornoNascita", "DICIANNOVE");
		}
		else if(giornoNascita == 20){
			param.put("giornoNascita", "VENTI");
		}
		else if(giornoNascita == 21){
			param.put("giornoNascita", "VENTUNO");
		}
		else if(giornoNascita == 22){
			param.put("giornoNascita", "VENTIDUE");
		}
		else if(giornoNascita == 23){
			param.put("giornoNascita", "VENTITRE");
		}
		else if(giornoNascita == 24){
			param.put("giornoNascita", "VENTIQUATTRO");
		}
		else if(giornoNascita == 25){
			param.put("giornoNascita", "VENTICINQUE");
		}
		else if(giornoNascita == 26){
			param.put("giornoNascita", "VENTISEI");
		}
		else if(giornoNascita == 27){
			param.put("giornoNascita", "VENTISETTE");
		}
		else if(giornoNascita == 28){
			param.put("giornoNascita", "VENTOTTO");
		}
		else if(giornoNascita == 29){
			param.put("giornoNascita", "VENTINOVE");
		}
		else if(giornoNascita == 30){
			param.put("giornoNascita", "TRENTA");
		}
		else if(giornoNascita == 31){
			param.put("giornoNascita", "TRENTUNO");
		}

		//mesi
		if(giornoNascita == 1){
			param.put("meseNascita", "GENNAIO");
		}
		else if(giornoNascita == 2){
			param.put("meseNascita", "FEBBRAIO");
		}
		else if(giornoNascita == 3){
			param.put("meseNascita", "MARZO");
		}
		else if(giornoNascita == 4){
			param.put("meseNascita", "APRILE");
		}
		else if(giornoNascita == 5){
			param.put("meseNascita", "MAGGIO");
		}
		else if(giornoNascita == 6){
			param.put("meseNascita", "GIUGNO");
		}
		else if(giornoNascita == 7){
			param.put("meseNascita", "LUGLIO");
		}
		else if(giornoNascita == 8){
			param.put("meseNascita", "AGOSTO");
		}
		else if(giornoNascita == 9){
			param.put("meseNascita", "SETTEMBRE");
		}
		else if(giornoNascita == 10){
			param.put("meseNascita", "OTTOBRE");
		}
		else if(giornoNascita == 11){
			param.put("meseNascita", "NOVEMBRE");
		}
		else if(giornoNascita == 12){
			param.put("meseNascita", "DICEMBRE");
		}

		//anni

		String annoTemp = "";
		if(String.valueOf(annoNascita).substring(0, 2).equals("20") || String.valueOf(annoNascita).equals("2000")){
			annoTemp = annoTemp.concat("DUEMILA");
		}
		if(String.valueOf(annoNascita).substring(0, 2).equals("19")|| String.valueOf(annoNascita).equals("1900")){
			annoTemp =annoTemp.concat("MILLENOVECENTO");
		}
		if(String.valueOf(annoNascita).substring(2, 3).equals("9")|| String.valueOf(annoNascita).substring(2, 4).equals("90")){
			annoTemp =annoTemp.concat("NOVANTA");
		}
		if(String.valueOf(annoNascita).substring(2, 3).equals("8")|| String.valueOf(annoNascita).substring(2, 4).equals("80")){
			annoTemp =annoTemp.concat("OTTANTA");
		}
		if(String.valueOf(annoNascita).substring(2, 3).equals("7")|| String.valueOf(annoNascita).substring(2, 4).equals("70")){
			annoTemp =annoTemp.concat("SETTANTA");
		}
		if(String.valueOf(annoNascita).substring(2, 3).equals("6")|| String.valueOf(annoNascita).substring(2, 4).equals("60")){
			annoTemp =annoTemp.concat("SESSANTA");
		}
		if(String.valueOf(annoNascita).substring(2, 3).equals("5")|| String.valueOf(annoNascita).substring(2, 4).equals("50")){
			annoTemp =annoTemp.concat("CINQUANTA");
		}
		if(String.valueOf(annoNascita).substring(2, 3).equals("4")|| String.valueOf(annoNascita).substring(2, 4).equals("40")){
			annoTemp =annoTemp.concat("QUARANTA");
		}
		if(String.valueOf(annoNascita).substring(2, 3).equals("3")|| String.valueOf(annoNascita).substring(2, 4).equals("30")){
			annoTemp =annoTemp.concat("TRENTA");
		}
		if(String.valueOf(annoNascita).substring(2, 3).equals("2") || String.valueOf(annoNascita).substring(2, 4).equals("20")){
			annoTemp =annoTemp.concat("VENTI");
		}
		if(String.valueOf(annoNascita).substring(2, 4).equals("21")){
			annoTemp =annoTemp.concat("VENTUNO");
		}
		if(String.valueOf(annoNascita).substring(2, 4).equals("19")){
			annoTemp =annoTemp.concat("DICIANNOVE");
		}
		if(String.valueOf(annoNascita).substring(2, 4).equals("18")){
			annoTemp =annoTemp.concat("DICIOTTO");
		}
		if(String.valueOf(annoNascita).substring(2, 4).equals("17")){
			annoTemp =annoTemp.concat("DICIASSETTE");
		}
		if(String.valueOf(annoNascita).substring(2, 4).equals("16")){
			annoTemp =annoTemp.concat("SEDICI");
		}
		if(String.valueOf(annoNascita).substring(2, 4).equals("15")){
			annoTemp =annoTemp.concat("QUINDICI");
		}
		if(String.valueOf(annoNascita).substring(2, 4).equals("14")){
			annoTemp =annoTemp.concat("QUATTORDICI");
		}
		if(String.valueOf(annoNascita).substring(2, 4).equals("13")){
			annoTemp =annoTemp.concat("TREDICI");
		}
		if(String.valueOf(annoNascita).substring(2, 4).equals("12")){
			annoTemp =annoTemp.concat("DODICI");
		}
		if(String.valueOf(annoNascita).substring(2, 4).equals("11")){
			annoTemp =annoTemp.concat("UNDICI");
		}
		if(String.valueOf(annoNascita).substring(2, 4).equals("10")){
			annoTemp =annoTemp.concat("DIECI");
		}
		if(String.valueOf(annoNascita).substring(3, 4).equals("9") && !String.valueOf(annoNascita).substring(2, 3).equals("1")|| String.valueOf(annoNascita).substring(2, 4).equals("09")){
			annoTemp.concat("NOVE");
		}
		if(String.valueOf(annoNascita).substring(3, 4).equals("8") && !String.valueOf(annoNascita).substring(2, 3).equals("1")|| String.valueOf(annoNascita).substring(2, 4).equals("08")){
			annoTemp =annoTemp.concat("OTTO");
		}
		if(String.valueOf(annoNascita).substring(3, 4).equals("7") && !String.valueOf(annoNascita).substring(2, 3).equals("1")|| String.valueOf(annoNascita).substring(2, 4).equals("07")){
			annoTemp =annoTemp.concat("SETTE");
		}
		if(String.valueOf(annoNascita).substring(3, 4).equals("6") && !String.valueOf(annoNascita).substring(2, 3).equals("1")|| String.valueOf(annoNascita).substring(2, 4).equals("06")){
			annoTemp =annoTemp.concat("SEI");
		}
		if(String.valueOf(annoNascita).substring(3, 4).equals("5") && !String.valueOf(annoNascita).substring(2, 3).equals("1")|| String.valueOf(annoNascita).substring(2, 4).equals("05")){
			annoTemp =annoTemp.concat("CINQUE");
		}
		if(String.valueOf(annoNascita).substring(3, 4).equals("4") && !String.valueOf(annoNascita).substring(2, 3).equals("1")|| String.valueOf(annoNascita).substring(2, 4).equals("04")){
			annoTemp =annoTemp.concat("QUATTRO");
		}
		if(String.valueOf(annoNascita).substring(3, 4).equals("3") && !String.valueOf(annoNascita).substring(2, 3).equals("1")|| String.valueOf(annoNascita).substring(2, 4).equals("03")){
			annoTemp =annoTemp.concat("TRE");
		}
		if(String.valueOf(annoNascita).substring(3, 4).equals("2") && !String.valueOf(annoNascita).substring(2, 3).equals("1")|| String.valueOf(annoNascita).substring(2, 4).equals("02")){
			annoTemp =annoTemp.concat("DUE");
		}
		if(String.valueOf(annoNascita).substring(3, 4).equals("1") && !String.valueOf(annoNascita).substring(2, 3).equals("1")|| String.valueOf(annoNascita).substring(2, 4).equals("01")){
			annoTemp =annoTemp.concat("UNO");
		}
		param.put("annoNascita", annoTemp);
		/*
		param.put("giornoNascita", String.valueOf(giornoNascita));
		param.put("annoNascita", String.valueOf(annoNascita));
		param.put("meseNascita", String.valueOf(meseNascita));
		 */
		//param.put("annoNascita", annoNascita);

		param.put("nome", componenteSelezionato.getNome().toUpperCase());
		param.put("cognome", componenteSelezionato.getCognome().toUpperCase());
		param.put("codiceFiscale", componenteSelezionato.getCodiceFiscale().toUpperCase());
		param.put("dataNascita", DateUtils.getDataGGMMAAAA(componenteSelezionato.getDataNascita().getTime()));

		if (componenteSelezionato.isCittadinanzaItaliana() && componenteSelezionato.getCodiceIstatComuneNascita() != null) {
			if (comuneByCodiceISTAT != null) {
				param.put("comuneNascita", comuneByCodiceISTAT.getDenominazione());
				param.put("provinciaNascita", comuneByCodiceISTAT.getProvincia().getDenominazioneProvincia());
			}
			// Ricerca in comuni esteri
			else {
				if (!componenteSelezionato.getCodiceIstatComuneNascita().isEmpty()) {
					ComuneEstero comuneEsteroByCodice = comuneEsteroService.getComuneEsteroByCodice(Integer.parseInt(componenteSelezionato.getCodiceIstatComuneNascita()));
					if (comuneEsteroByCodice != null) {
						param.put("comuneNascita", comuneEsteroByCodice.getDenominazione());
						param.put("provinciaNascita", "");
					}
				}
			}
		}
		param.put("comune", comuneByCodiceISTAT.getDenominazione());
		String sindacoImgPath = "/opt/osapulie-scenario-2/firme/" + comuneByCodiceISTAT.getCodiceIstat1() + "/sindaco.jpg";
		File file = new File(sindacoImgPath);
		if (file.exists()) {
			param.put("sindaco", sindacoImgPath);
		}
		List<DatiAnagrafici.ComponentiNucleoFamiliare> beans = new ArrayList<DatiAnagrafici.ComponentiNucleoFamiliare>();
		beans.add(componenteSelezionato);
		if(servizio == PortletConstants.CODICE_SERVIZIO_RICHIESTA_CERTIFICATO_ISCRIZIONE_LISTE_ELETTORALI){
			Map<String, String> subreportJrxmlMap = new HashMap<String, String>();
			subreportJrxmlMap.put("subreportParameter", "/reports/richiestaCertificatoIscrizioneListeElettorali_subreport1.jrxml");
			byte[] reportBytes = reportService.jrxmlToPdf(param, beans, userPreferences.getIdComuneIsa(), servizio.get(PortletConstants.CODICE), servizio.get(PortletConstants.REPORT), subreportJrxmlMap);
			// consentire il download del documento.
			reportBytes = this.timbraFile(reportBytes, servizio.get(PortletConstants.PDF), comuneByCodiceISTAT, servizio.get(PortletConstants.DESCRIZIONE));
			return reportBytes;
		}
		else {
			byte[] reportBytes = reportService.jrxmlToPdf(param, beans, userPreferences.getIdComuneIsa(), servizio.get(PortletConstants.CODICE), servizio.get(PortletConstants.REPORT), null);
			// consentire il download del documento.
			reportBytes = this.timbraFile(reportBytes, servizio.get(PortletConstants.PDF), comuneByCodiceISTAT, servizio.get(PortletConstants.DESCRIZIONE));
			return reportBytes;
			}
		}

	public byte[] richiediCertificatoTimbrato(Map<String,String> servizio, String codiceFiscale, UserPreferences userPreferences, String utilizzoBollo, String numeroBollo, DatiAnagrafici.ComponentiNucleoFamiliare componenteSelezionato, boolean subReport, DatiAnagrafici dati) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		DatiAnagraficiGenerali richDatiAna = new DatiAnagraficiGenerali();
		richDatiAna.setCodiceFiscale(codiceFiscale);
		DatiUtente datiUte = visureService.richiediDatiAnagraficiAltriServizi(richDatiAna, userPreferences);
		if (datiUte != null) {
			if (datiUte.getCap() != null) {
				param.put("capRes", datiUte.getCap());
			} else {
				param.put("capRes", "-");
			}

			if (datiUte.getComuneResidenza() != null) {
				param.put("comuneRes", datiUte.getComuneResidenza());
			} else {
				param.put("comuneRes", "-");
			}

			if (datiUte.getProvinciaResidenza() != null) {
				param.put("provRes", datiUte.getProvinciaResidenza());
			} else {
				param.put("provRes", "-");
			}
			if (datiUte.getIndirizzo() != null) {
				param.put("viaRes", dati.getToponimoIndirizzo() + " " + dati.getDescrizioneVia() + " n. " +dati.getNumeroCivico() + " " + dati.getEsponente() + " Scala " + dati.getScala() + " Piano " + dati.getPiano());
			} else {
				param.put("viaRes", "-");
			}


		}
		if (numeroBollo != null && numeroBollo.length() > 0) {
			param.put("bollo", "NUMERO MARCA DA BOLLO : " + numeroBollo);
		} else {
			param.put("bollo", "");
		}

		if (componenteSelezionato.getNumeroAttoNascita() != null) {
			param.put("attoN", "" + componenteSelezionato.getNumeroAttoNascita());
		}
		else {
			param.put("attoN", "-");
		}
		if (componenteSelezionato.getParteNascita() != null) {
			param.put("parteN", componenteSelezionato.getParteNascita());
		}
		else {
			param.put("parteN", "-");
		}
		if (utilizzoBollo != null) {
			param.put("uso", utilizzoBollo);
		}
		else {
			param.put("uso", "-");
		}
		if (componenteSelezionato.getSerieNascita() != null) {
			param.put("serieN", componenteSelezionato.getSerieNascita());
		}
		else {
			param.put("serieN", "-");
		}
		if (componenteSelezionato.getAnnoAttoNascita() != null) {
			param.put("annoAttoN", "" + componenteSelezionato.getAnnoAttoNascita());
		}
		else {
			param.put("annoAttoN", "-");
		}
		if (componenteSelezionato.getStatoCivile() != null) {
			param.put("statoCivile", "" + componenteSelezionato.getStatoCivile());
		}
		else {
			param.put("statoCivile", "-");
		}
		if (componenteSelezionato.getRelazioneParentela() != null) {
			param.put("relazioneParentela", "" + componenteSelezionato.getRelazioneParentela());
		}
		else {
			param.put("relazioneParentela", "-");
		}

		DatiAnagrafici.ComponentiNucleoFamiliare componenteSel = null;
		for (int i = 0; i < dati.getComponentiNucleoFamiliare().size(); i++) {
			DatiAnagrafici.ComponentiNucleoFamiliare componente = dati.getComponentiNucleoFamiliare().get(i);
			if (richDatiAna.getCodiceFiscale() != null && richDatiAna.getCodiceFiscale().equals(componente.getCodiceFiscale())) {
				componenteSel = componente;
				break;
			}
		}

		if(servizio == PortletConstants.CODICE_SERVIZIO_RICHIESTA_CERTIFICATO_STATO_FAMIGLIA) {
			List<DatiAnagrafici.ComponentiNucleoFamiliare> familiari = new ArrayList<DatiAnagrafici.ComponentiNucleoFamiliare>();
			for (int i = 0; i < dati.getComponentiNucleoFamiliare().size(); i++) {
				DatiAnagrafici.ComponentiNucleoFamiliare familiare = dati.getComponentiNucleoFamiliare().get(i);
				if (!familiare.getCodiceFiscale().equals(componenteSel.getCodiceFiscale())) {
					// mellif 20150316: comune e provincia passati come parametri in seguito
					// all'introduzione del codiceIstat
					Comune comuneNascitaFam = comuneService.getComuneByCodiceISTAT(familiare.getCodiceIstatComuneNascita());
					param.put("comuneNascitaFam", comuneNascitaFam.getDenominazione());
					param.put("provinciaNascitaFam", comuneNascitaFam.getProvincia().getDenominazioneProvincia());
					param.put("relazioneParentela", "" + componenteSel.getRelazioneParentela());
					familiare.setCodiceIstatComuneNascitaTrascritto(comuneNascitaFam.getDenominazione());
					familiare.setDescrizioneComuneEsteroNascita(comuneNascitaFam.getProvincia().getDenominazioneProvincia());
					familiari.add(familiare);
				}
			}
			param.put("subreportParameters", familiari);
		}




		Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(componenteSelezionato.getCodiceIstatComuneNascita());
		if (comuneByCodiceISTAT != null) {
			param.put("comuneNascita", comuneByCodiceISTAT.getDenominazione());
			param.put("provinciaNascita", comuneByCodiceISTAT.getProvincia().getDenominazioneProvincia());
		}
		// Ricerca in comuni esteri
		else {
			if (!componenteSelezionato.getCodiceIstatComuneNascita().isEmpty()) {
				ComuneEstero comuneEsteroByCodice = comuneEsteroService.getComuneEsteroByCodice(Integer.parseInt(componenteSelezionato.getCodiceIstatComuneNascita()));
				if (comuneEsteroByCodice != null) {
					param.put("comuneNascita", comuneEsteroByCodice.getDenominazione());
				}
			}
		}
		if (comuneByCodiceISTAT != null) {
			param.put("comuneNascita", comuneByCodiceISTAT.getDenominazione());
			param.put("provinciaNascita", comuneByCodiceISTAT.getProvincia().getDenominazioneProvincia());
		}
		// Ricerca in comuni esteri
		else {
			if (!componenteSelezionato.getCodiceIstatComuneNascita().isEmpty()) {
				ComuneEstero comuneEsteroByCodice = comuneEsteroService.getComuneEsteroByCodice(Integer.parseInt(componenteSelezionato.getCodiceIstatComuneNascita()));
				if (comuneEsteroByCodice != null) {
					param.put("comuneNascita", comuneEsteroByCodice.getDenominazione());
				}
			}
		}


		SimpleDateFormat sdfMese = new SimpleDateFormat("MMMM", Locale.ITALIAN);
		int giornoNascita = componenteSelezionato.getDataNascita().get(Calendar.DAY_OF_MONTH);
		int annoNascita = componenteSelezionato.getDataNascita().get(Calendar.YEAR);
		int meseNascita = componenteSelezionato.getDataNascita().get(Calendar.MONTH);
		param.put("giornoNascita", String.valueOf(giornoNascita));
		param.put("annoNascita", String.valueOf(annoNascita));
		param.put("meseNascita", String.valueOf(meseNascita));

		param.put("nome", componenteSelezionato.getNome().toUpperCase());
		param.put("cognome", componenteSelezionato.getCognome().toUpperCase());
		param.put("codiceFiscale", componenteSelezionato.getCodiceFiscale().toUpperCase());
		param.put("dataNascita", DateUtils.getDataGGMMAAAA(componenteSelezionato.getDataNascita().getTime()));

		if (componenteSelezionato.isCittadinanzaItaliana() && componenteSelezionato.getCodiceIstatComuneNascita() != null) {
			if (comuneByCodiceISTAT != null) {
				param.put("comuneNascita", comuneByCodiceISTAT.getDenominazione());
				param.put("provinciaNascita", comuneByCodiceISTAT.getProvincia().getDenominazioneProvincia());
			}
			// Ricerca in comuni esteri
			else {
				if (!componenteSelezionato.getCodiceIstatComuneNascita().isEmpty()) {
					ComuneEstero comuneEsteroByCodice = comuneEsteroService.getComuneEsteroByCodice(Integer.parseInt(componenteSelezionato.getCodiceIstatComuneNascita()));
					if (comuneEsteroByCodice != null) {
						param.put("comuneNascita", comuneEsteroByCodice.getDenominazione());
						param.put("provinciaNascita", "");
					}
				}
			}
		}
		param.put("comune", comuneByCodiceISTAT.getDenominazione());
		String sindacoImgPath = "/opt/osapulie-scenario-2/firme/" + comuneByCodiceISTAT.getCodiceIstat1() + "/sindaco.jpg";
		File file = new File(sindacoImgPath);
		if (file.exists()) {
			param.put("sindaco", sindacoImgPath);
		}
		List<DatiAnagrafici.ComponentiNucleoFamiliare> beans = new ArrayList<DatiAnagrafici.ComponentiNucleoFamiliare>();
		beans.add(componenteSelezionato);
		if(servizio == PortletConstants.CODICE_SERVIZIO_RICHIESTA_CERTIFICATO_STATO_FAMIGLIA) {
			final String SUB_REPORT_PATH = "/reports/richiestaCertificatoStatoFamiglia_subreport1.jrxml";
			Map<String, String> subreportJrxmlMap = new HashMap<String, String>();
			subreportJrxmlMap.put("subreportParameter", SUB_REPORT_PATH);
			byte[] reportBytes = reportService.jrxmlToPdf(param, beans, userPreferences.getIdComuneIsa(), servizio.get(PortletConstants.CODICE), servizio.get(PortletConstants.REPORT), subreportJrxmlMap);
			reportBytes = this.timbraFile(reportBytes, servizio.get(PortletConstants.PDF), comuneByCodiceISTAT, servizio.get(PortletConstants.DESCRIZIONE));
			return reportBytes;
		}
		else{
		byte[] reportBytes = reportService.jrxmlToPdf(param, beans, userPreferences.getIdComuneIsa(), servizio.get(PortletConstants.CODICE), servizio.get(PortletConstants.REPORT), null);
		// consentire il download del documento.
		reportBytes = this.timbraFile(reportBytes, servizio.get(PortletConstants.PDF), comuneByCodiceISTAT, servizio.get(PortletConstants.DESCRIZIONE));
		return reportBytes;
		}
	}
	public byte[] timbraFile(byte[] reportBytes, String reportFileName, Comune comuneByCodiceISTAT, String reportSubject) throws Exception {
		byte[] reportBytesPdf = null;
		UUID uuidRequest = UUID.randomUUID();

		StampSignAuthResponse stampSignAuthResponse = null;
		try {
			stampSignAuthResponse = stampsignClientService.stampSignAuth(comuneByCodiceISTAT.getCodiceCatastale());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
		StampSignRequest stampSignRequest = null;
		try {
			stampSignRequest = stampsignClientService.fillStampSignRequest(reportBytes, reportSubject, comuneByCodiceISTAT.getCodiceCatastale(), uuidRequest);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
		try {
			StampSignResponse response = stampsignClientService.stampSign(stampSignAuthResponse.getToken().getAccessToken().toString(), stampSignRequest);
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			reportBytesPdf = response.getRisultato().getDownloadFileContent();
		} catch (Exception e) {
			log.error("Errore nell'upload del file", e);
			throw e;
		}
		return reportBytesPdf;
	}
}
