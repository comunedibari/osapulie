package it.eng.tz.area.vasta.osapulie.ws.stubs.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import it.eng.tz.area.vasta.osapulie.ws.stubs.pddsintegrationSOAP.GetRichiestaPdd;
import it.eng.tz.area.vasta.osapulie.ws.stubs.pddsintegrationSOAP.GetRichiestaPddResponse;
import it.eng.tz.area.vasta.osapulie.ws.stubs.pddsintegrationSOAP.ObjectFactory;

@Endpoint
public class PddsIntegrationSOAPEndpoint {
	private static final Logger logger = LoggerFactory.getLogger(PddsIntegrationSOAPEndpoint.class.getName());
	private static final String NAME_SPACE_URI = "http://pddsintegration.osapulie.it";

	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getRichiestaPdd")
	@ResponsePayload
	public GetRichiestaPddResponse getRichiestaPdd(@RequestPayload GetRichiestaPdd request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getRichiestaPdd CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		GetRichiestaPddResponse result = of.createGetRichiestaPddResponse();

		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

		if (request.getServizio().equals("datiAnagraficiGenerali")){
			xml = xml + datiAnagraficiGenerali();
		}
		if (request.getServizio().equals("dichiarazioneCambioResidenza")){
			xml = xml + dichiarazioneCambioResidenza();
		}
		if (request.getServizio().equals("richiestaDatiAnagrafici")){
			xml = xml + richiestaDatiAnagrafici();
		}
		if (request.getServizio().equals("richiestaDatiElettorali")){
			xml = xml + richiestaDatiElettorali();
		}
		if (request.getServizio().equals("richiestaServiziAttivi")){
			xml = xml + richiestaServiziAttivi();
		}
		if (request.getServizio().equals("stradarioAscotService")){
			xml = xml + stradarioAscotService();
		}
		if (request.getServizio().equals("stradarioService")){
			xml = xml + stradarioService();
		}
		if (request.getServizio().equals("visuraDichiarazioneTassaImmobili")){
			xml = xml + visuraDichiarazioneTassaImmobili();
		}
		if (request.getServizio().equals("visuraDichiarazioneTassaRifiuti")){
			xml = xml + visuraDichiarazioneTassaRifiuti();
		}
		if (request.getServizio().equals("visuraDocumentiTributi")){
			xml = xml + visuraDocumentiTributi();
		}
		if (request.getServizio().equals("visuraPosizioneTributariaRichiesta")){
			xml = xml + visuraPosizioneTributaria();
		}
		if (request.getServizio().equals("visuraServiziCimiteriali")){
			xml = xml + visuraServiziCimiteriali();
		}
		if (request.getServizio().equals("visuraTosapRichiesta")){
			xml = xml + visuraTosapRichiesta();
		}
		if (request.getServizio().equals("visuraTosapTemporaneaRichiesta")){
			xml = xml + visuraTosapTemporaneaRichiesta();
		}
		
		result.setOut(xml);		
		return result;
	}

	public String richiestaDatiAnagrafici() {
		return "<visuraAnagrafica xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"VisuraPosizioneAnagrafica-risposta.xsd\"> "
				+ "<toponimoIndirizzo>Via</toponimoIndirizzo>"
				+ "<descrizioneVia>Viabile</descrizioneVia>"
				+ "<numeroCivico>88</numeroCivico>"
				+ "<codiceFiscale>PLABRN80A01L259V</codiceFiscale>"
				+ "<cognome>Palo</cognome>"
				+ "<nome>Bruno</nome>"
				+ "<dataNascita>1980-01-01</dataNascita>"
				+ "<sesso>M</sesso>"
				+ "<statoCivile>celibe</statoCivile>"
				+ "<relazioneParentela>relazioneParentela</relazioneParentela>"
				+ "<cittadinanzaItaliana>true</cittadinanzaItaliana>"
				+ "<identificativoIndividuale>identificativoIndividuale</identificativoIndividuale>"
				+ "<identificativoFamiglia>identificativoFamiglia</identificativoFamiglia>"
				+ "<descComuneNascita>descComuneNascita</descComuneNascita>"
				+ "<descProvinciaNascita>descProvinciaNascita</descProvinciaNascita>"
				+ "<componentiNucleoFamiliare>" + 
				"<codiceFiscale>LMBMRC85B18A662E</codiceFiscale>" + 
				"<identificativo>identificativo</identificativo>" + 
				"<cognome>Lamberti</cognome>" + 
				"<nome>Marco</nome>" + 
				"<dataNascita>1985-02-18T00:00:00+01:00</dataNascita>" + 
				"<sesso>M</sesso>" + 
				"<statoCivile>celibe</statoCivile>" + 
				"<celibe>prova</celibe>" + 
				"<nubile>prova</nubile>" + 
				"<coniugato>prova</coniugato>" + 
				"<coniugata>prova</coniugata>" + 
				"<divorziato>prova</divorziato>" + 
				"<divorziata>prova</divorziata>" + 
				"<vedovo>prova</vedovo>" + 
				"<vedova>prova</vedova>" + 
				"<relazioneParentela>prova</relazioneParentela>" + 
				"<cognomeNomeConiuge>prova</cognomeNomeConiuge>" + 
				"<cittadinanzaItaliana>TRUE</cittadinanzaItaliana>" + 
				"<codiceIstatComuneNascita>072006</codiceIstatComuneNascita>" + 
				"<numeroAttoNascita>1</numeroAttoNascita>" + 
				"<parteNascita>prova</parteNascita>" + 
				"<serieNascita>prova</serieNascita>" + 
				"<annoAttoNascita>1</annoAttoNascita>" + 
				"<ufficioNascita>prova</ufficioNascita>" + 
				"<numeroAttoNascitaTrascritto>1</numeroAttoNascitaTrascritto>" + 
				"<parteNascitaTrascritto>prova</parteNascitaTrascritto>" + 
				"<serieNascitaTrascritto>prova</serieNascitaTrascritto>" + 
				"<annoNascitaTrascritto>1985</annoNascitaTrascritto>" + 
				"<codiceIstatComuneNascitaTrascritto>071024</codiceIstatComuneNascitaTrascritto>" + 
				"<numeroAttoMatrimonio>1</numeroAttoMatrimonio>" + 
				"<parteMatrimonio>prova</parteMatrimonio>" + 
				"<serieMatrimonio>prova</serieMatrimonio>" + 
				"<annoMatrimonio>1</annoMatrimonio>" + 
				"<ufficioMatrimonio>prova</ufficioMatrimonio>" + 
				"<codiceIstatComuneMatrimonio>999999</codiceIstatComuneMatrimonio>" + 
				"<luogoMatrimonio>prova</luogoMatrimonio>" + 
				"<dataMatrimonio>2010-05-25T00:00:00+01:00</dataMatrimonio>" + 
				"<numeroAttoMatrimonioTrascritto>prova</numeroAttoMatrimonioTrascritto>" + 
				"<parteMatrimonioTrascritto>prova</parteMatrimonioTrascritto>" + 
				"<serieMatrimonioTrascritto>prova</serieMatrimonioTrascritto>" + 
				"<annoMatrimonioTrascritto>1</annoMatrimonioTrascritto>" + 
				"<codiceIstatComuneMatrimonioTrascritto>999999</codiceIstatComuneMatrimonioTrascritto>" + 
				"<dataDecorrenzaDivorzio>2010-05-25T00:00:00+01:00</dataDecorrenzaDivorzio>" + 
				"<dataSentenzaDivorzio>2010-05-25T00:00:00+01:00</dataSentenzaDivorzio>" + 
				"<numeroSentenzaDivorzio>1</numeroSentenzaDivorzio>" + 
				"<codiceIstatComuneTribunaleDivorzio>999999</codiceIstatComuneTribunaleDivorzio>" + 
				"<tipoDivorzio>prova</tipoDivorzio>" + 
				"<annullamento>prova</annullamento>" + 
				"<cessazione>prova</cessazione>" + 
				"<scioglimento>prova</scioglimento>" + 
				"<dataAttoDivorzio>2010-05-25T00:00:00+01:00</dataAttoDivorzio>" + 
				"<numeroAttoDivorzio>1</numeroAttoDivorzio>" + 
				"<parteDivorzio>prova</parteDivorzio>" + 
				"<serieDivorzio>prova</serieDivorzio>" + 
				"<volumeDivorzio>prova</volumeDivorzio>" + 
				"<ufficioDivorzio>prova</ufficioDivorzio>" + 
				"<dataAttoDivorzioTrascritto>2010-05-25T00:00:00+01:00</dataAttoDivorzioTrascritto>" + 
				"<numeroAttoDivorzioTrascritto>1</numeroAttoDivorzioTrascritto>" + 
				"<parteDivorzioTrascritto>prova</parteDivorzioTrascritto>" + 
				"<serieDivorzioTrascritto>prova</serieDivorzioTrascritto>" + 
				"<volumeDivorzioTrascritto>prova</volumeDivorzioTrascritto>" + 
				"<ufficioDivorzioTrascritto>prova</ufficioDivorzioTrascritto>" + 
				"<codiceIstatComuneTrascrizioneDivorzio>071024</codiceIstatComuneTrascrizioneDivorzio>" + 
				"<dataAttoVedovanza>2010-05-25T00:00:00+01:00</dataAttoVedovanza>" + 
				"<numeroAttoVedovanza>1</numeroAttoVedovanza>" + 
				"<parteVedovanza>prova</parteVedovanza>" + 
				"<serieVedovanza>prova</serieVedovanza>" + 
				"<volumeVedovanza>prova</volumeVedovanza>" + 
				"<ufficioVedovanza>prova</ufficioVedovanza>" + 
				"<codiceIstatComuneMorte>071024</codiceIstatComuneMorte>" + 
				"<dataMorte>2010-05-25T00:00:00+01:00</dataMorte>" + 
				"<numeroAttoMorte>1</numeroAttoMorte>" + 
				"<parteMorte>prova</parteMorte>" + 
				"<serieMorte>prova</serieMorte>" + 
				"<ufficioMorte>prova</ufficioMorte>" + 
				"<annoMorte>1</annoMorte>" + 
				"<codiceIstatComuneMorteConiugeTrascritto>999999</codiceIstatComuneMorteConiugeTrascritto>" + 
				"<numeroAttoMorteConiugeTrascritto>1</numeroAttoMorteConiugeTrascritto>" + 
				"<parteMorteConiugeTrascritto>prova</parteMorteConiugeTrascritto>" + 
				"<serieMorteConiugeTrascritto>prova</serieMorteConiugeTrascritto>" + 
				"<annoMorteConiugeTrascritto>1</annoMorteConiugeTrascritto>" + 
				"<professione>prova</professione>" + 
				"<titoloStudio>prova</titoloStudio>" + 
				"<numeroCartaIdentita>prova</numeroCartaIdentita>" + 
				"<dataCartaIdentita>2010-05-25T00:00:00+01:00</dataCartaIdentita>" + 
				"<validaCartaIdentita>TRUE</validaCartaIdentita>" + 
				"<identificativoNucleoFamiliare>1</identificativoNucleoFamiliare>" + 
				"</componentiNucleoFamiliare>"

		
				+ "</visuraAnagrafica>";
	}
	
	public String visuraDichiarazioneTassaRifiuti() {
		return "<dichiarazioneTassaRifiutiRisposta xmlns=\"http://types.output.tributi.osapulie.it\" xsi:noNamespaceSchemaLocation=\"VisuraTassaRifiuti-risposta.xsd\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" + 
				"<elencoPagamenti annoRiferimento=\"2018\" descrizioneTassa=\"descrizione\">" + 
				"<posizioni>" + 
				"<identificativoUtenza>aaaa</identificativoUtenza>" + 
				"<indirizzoUtenza>" + 
				"<comune>" + 
				"<codice>C</codice>" + 
				"<descrizione>C</descrizione>" + 
				"</comune>" + 
				"<via>" + 
				"<codice>C</codice>" + 
				"<descrizione>C</descrizione>" + 
				"</via>" + 
				"<civico>" + 
				"<numero>87</numero>" + 
				"</civico>" + 
				"</indirizzoUtenza>" + 
				"<superficie>111</superficie>" + 
				"<tipoSuperficie>aaa</tipoSuperficie>" + 
				"<caratteristicaImmobile>1</caratteristicaImmobile>" + 
				"<numero>1</numero>" + 
				"<foglio>1</foglio>" + 
				"<particella>1</particella>" + 
				"<destinazione>" + 
				"<codice>C</codice>" + 
				"<descrizione>C</descrizione>" + 
				"</destinazione>" + 
				"<tipo>aaa</tipo>" + 
				"<categoriaImmobile>" + 
				"<codice>C</codice>" + 
				"<descrizione>C</descrizione>" + 
				"</categoriaImmobile>" + 
				"<aliquota>aaa</aliquota>" + 
				"<agevolazione>ad</agevolazione>" + 
				"<importoAgevolazione>1</importoAgevolazione>" + 
				"<importoRiduzioni>1</importoRiduzioni>" + 
				"<importoTariffa>1</importoTariffa>" + 
				"<importoDaPagare>1</importoDaPagare>" + 
				"<addizionaleComunale>1</addizionaleComunale>" + 
				"<maggiorazioneStato>1</maggiorazioneStato>" + 
				"<addizionaleProvinciale>1</addizionaleProvinciale>" + 
				"<dataInizioOccupazione>2010-05-25T00:00:00+01:00</dataInizioOccupazione>" + 
				"<occupazioneNucleoFamiliare>" + 
				"<importoTariffa>1</importoTariffa>" + 
				"<dataInizioOccupazione>2010-05-25T00:00:00+01:00</dataInizioOccupazione>" + 
				"<dataFineOccupazione>2010-05-25T00:00:00+01:00</dataFineOccupazione>" + 
				"<numeroComponenti>1</numeroComponenti>" + 
				"</occupazioneNucleoFamiliare>" + 
				"</posizioni>" + 
				"<rate>" + 
				"<importoRata>11</importoRata>" + 
				"<scadenzaRata>2010-05-25T00:00:00+01:00</scadenzaRata>" + 
				"<importoPagato>0</importoPagato>" + 
				"</rate>" + 
				"<dataInizio>2010-05-25T00:00:00+01:00</dataInizio>" + 
				"<dataFine>2010-05-25T00:00:00+01:00</dataFine>" + 
				"</elencoPagamenti>" + 
				"</dichiarazioneTassaRifiutiRisposta>";
	}
	
	public String visuraDocumentiTributi() {
		return "<visuraDocumentiRisposta xmlns=\"http://types.output.documenti.osapulie.it\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"VisuraDocumenti-risposta.xsd\">"
				+ "<documento  xmlns=\"http://types.output.documenti.osapulie.it\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"DocumentoType.xsd\">"
				+ "<id>a</id>"
				+ "<nome>a.doc</nome>"
				+ "<descrizione>a</descrizione>"
				+ "<contentType>a</contentType>"
				+ "</documento>"
				+ "</visuraDocumentiRisposta>";
	}
	
	public String datiAnagraficiGenerali() {
		return "<datiUtente xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"AltriServiziDati-Anagrafici-richiesta.xsd\">"
				+ "<nome>descrizioneVia</nome>"
				+ "<cognome>descrizioneVia</cognome>"
				+ "<dataNascita>2010-05-25T00:00:00+01:00</dataNascita>"
				+ "<comuneNascita>descrizioneVia</comuneNascita>"
				+ "<provinciaNascita>descrizioneVia</provinciaNascita>"
				+ "<indirizzo>descrizioneVia</indirizzo>"
				+ "<cap>descrizioneVia</cap>"
				+ "<comuneResidenza>descrizioneVia</comuneResidenza>"
				+ "<provinciaResidenza>descrizioneVia</provinciaResidenza>"
				+ "<codiceIstatResidenza>999999</codiceIstatResidenza>"
				+ "</datiUtente>";
	}
	
	public String richiestaDatiElettorali() {
		return "<datiElettorali xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"VisuraPosizioneElettorale-risposta.xsd\"> " + 
				"<posizioniElettorali>" + 
				"<toponimoIndirizzo>prova</toponimoIndirizzo>" + 
				"<descrizioneVia>prova</descrizioneVia>" + 
				"<numeroCivico>prova</numeroCivico>" + 
				"<esponente>prova</esponente>" + 
				"<piano>prova</piano>" + 
				"<scala>prova</scala>" + 
				"<interno>prova</interno>" + 
				"<cognome>prova</cognome>" + 
				"<nome>prova</nome>" + 
				"<dataNascita>2010-05-25T00:00:00+01:00</dataNascita>" + 
				"<dataVerbaleIscrizione>2010-05-25T00:00:00+01:00</dataVerbaleIscrizione>" + 
				"<numVerbaleIscrizione>prova</numVerbaleIscrizione>" + 
				"<annoIscrizioneElett>1</annoIscrizioneElett>" + 
				"<numeroFascicolo>prova</numeroFascicolo>" + 
				"<numeroListaGenerale>prova</numeroListaGenerale>" + 
				"<numeroListaSezionale>prova</numeroListaSezionale>" + 
				"<descrizioneLista>prova</descrizioneLista>" + 
				"<tipoElettore>prova</tipoElettore>" + 
				"<numeroSezione>1</numeroSezione>" + 
				"<numeroTesseraElettorale>prova</numeroTesseraElettorale>" + 
				"<dataRilascioTesseraElett>prova</dataRilascioTesseraElett>" + 
				"<iscrizioneAlboPresidente>TRUE</iscrizioneAlboPresidente>" + 
				"<iscrizioneAlboScrutatori>TRUE</iscrizioneAlboScrutatori>" + 
				"<numVerbIscrAlboScrut>1</numVerbIscrAlboScrut>" + 
				"<dataVerbIscrAlboScrut>2010-05-25T00:00:00+01:00</dataVerbIscrAlboScrut>" + 
				"</posizioniElettorali>" + 
				"</datiElettorali>";
	}
	
	public String richiestaServiziAttivi() {
		return "<serviziAttivi xsi:noNamespaceSchemaLocation=\"ServiziAttivi-risposta.xsd\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" + 
				"<servizio>" + 
				"<codiceServizio>codiceServizio</codiceServizio>" + 
				"<descrizioneServizio>descrizioneServizio</descrizioneServizio>" + 
				"<urlPortaApplicativa>http://localhost:8080/OsapulieWsStub/ws/pddsIntegrationSOAP</urlPortaApplicativa>" + 
				"<dataAggiornamento>2010-05-25T00:00:00+01:00</dataAggiornamento>" + 
				"<autenticazioneForte>FALSE</autenticazioneForte>" + 
				"<livelloAutenticazione>1</livelloAutenticazione>" + 
				"<attivo>TRUE</attivo>" + 
				"</servizio>" + 
				"</serviziAttivi>";
	}

	private String visuraServiziCimiteriali() {
		return "<pagamentiServiziCimiterialiRisposta  xmlns=\"http://types.output.tributi.osapulie.it\" xsi:noNamespaceSchemaLocation=\"PagamentiServiziCimiteriali-risposta.xsd\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" + 
				"<elencoPagamentiCimiteriali annoRiferimento=\"2018\">" + 
				"<posizioniServiziCimiteriali>" + 
				"<defunti>" + 
				"<nomeDefunto>nomeDefunto</nomeDefunto>" + 
				"<dataNascita>2010-05-25T00:00:00+01:00</dataNascita>" + 
				"<dataMorte>2010-05-25T00:00:00+01:00</dataMorte>" + 
				"</defunti>" + 
				"<nomecimitero>nomecimitero</nomecimitero>" + 
				"<posizione>posizione</posizione>" + 
				"<lampadaVotiva>" + 
				"<mesi>1</mesi>" + 
				"<tariffa>tariffa</tariffa>" + 
				"<importoLampada>1</importoLampada>" + 
				"</lampadaVotiva>" + 
				"</posizioniServiziCimiteriali>" + 
				"<rate>" + 
				"<importoRata>1</importoRata>" + 
				"<scadenzaRata>2010-05-25T00:00:00+01:00</scadenzaRata>" + 
				"<numeroRata>1</numeroRata>" + 
				"<identificativoRata>identificativoRata</identificativoRata>" + 
				"<importoPagato>1</importoPagato>" + 
				"<dataPagamento>2010-05-25T00:00:00+01:00</dataPagamento>" + 
				"</rate>" + 
				"<dataAggiornamento>2010-05-25T00:00:00+01:00</dataAggiornamento>" + 
				"</elencoPagamentiCimiteriali>" + 
				"</pagamentiServiziCimiterialiRisposta>";
	}

	private String visuraPosizioneTributaria() {
		return "<visuraPosizioneTributariaRisposta xmlns=\"http://types.output.tributi.osapulie.it\" xsi:noNamespaceSchemaLocation=\"PosizioneTributaria-risposta.xsd\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" + 
			"<elencoPagamentiTassaRifiuti annoRiferimento=\"2018\">" + 
			"<posizioni>" + 
			"<identificativoUtenza>aaaa</identificativoUtenza>" + 
			"<indirizzoUtenza>" + 
			"<comune>" + 
			"<codice>C</codice>" + 
			"<descrizione>C</descrizione>" + 
			"</comune>" + 
			"<via>" + 
			"<codice>C</codice>" + 
			"<descrizione>C</descrizione>" + 
			"</via>" + 
			"<civico>" + 
			"<numero>87</numero>" + 
			"</civico>" + 
			"</indirizzoUtenza>" + 
			"<superficie>111</superficie>" + 
			"<tipoSuperficie>aaa</tipoSuperficie>" + 
			"<caratteristicaImmobile>1</caratteristicaImmobile>" + 
			"<numero>1</numero>" + 
			"<foglio>1</foglio>" + 
			"<particella>1</particella>" + 
			"<destinazione>" + 
			"<codice>C</codice>" + 
			"<descrizione>C</descrizione>" + 
			"</destinazione>" + 
			"<tipo>aaa</tipo>" + 
			"<categoriaImmobile>" + 
			"<codice>C</codice>" + 
			"<descrizione>C</descrizione>" + 
			"</categoriaImmobile>" + 
			"<aliquota>aaa</aliquota>" + 
			"<agevolazione>ad</agevolazione>" + 
			"<importoAgevolazione>1</importoAgevolazione>" + 
			"<importoRiduzioni>1</importoRiduzioni>" + 
			"<importoTariffa>1</importoTariffa>" + 
			"<importoDaPagare>1</importoDaPagare>" + 
			"<addizionaleComunale>1</addizionaleComunale>" + 
			"<maggiorazioneStato>1</maggiorazioneStato>" + 
			"<addizionaleProvinciale>1</addizionaleProvinciale>" + 
			"<dataInizioOccupazione>2010-05-25T00:00:00+01:00</dataInizioOccupazione>" + 
			"<occupazioneNucleoFamiliare>" + 
			"<importoTariffa>1</importoTariffa>" + 
			"<dataInizioOccupazione>2010-05-25T00:00:00+01:00</dataInizioOccupazione>" + 
			"<dataFineOccupazione>2010-05-25T00:00:00+01:00</dataFineOccupazione>" + 
			"<numeroComponenti>1</numeroComponenti>" + 
			"</occupazioneNucleoFamiliare>" + 
			"</posizioni>" + 
			"<rate>" + 
			"<importoRata>11</importoRata>" + 
			"<scadenzaRata>2010-05-25T00:00:00+01:00</scadenzaRata>" + 
			"<importoPagato>0</importoPagato>" + 
			"</rate>" + 
			"<dataInizio>2010-05-25T00:00:00+01:00</dataInizio>" + 
			"<dataFine>2010-05-25T00:00:00+01:00</dataFine>" + 
			"</elencoPagamentiTassaRifiuti>" + 
			"<elencoPagamentiOsapTemporanea annoRiferimento=\"2018\">" + 
			"<indirizzoUtenza>indirizzoUtenza</indirizzoUtenza>" + 
			"<superficie>1</superficie>" + 
			"<zona>zona</zona>" + 
			"<descrizioneTariffa>descrizioneTariffa</descrizioneTariffa>" + 
			"<durataOccupazione>1</durataOccupazione>" + 
			"<importoDaPagare>1</importoDaPagare>" + 
			"<importoPagato>1</importoPagato>" + 
			"<dataPagamento>2010-05-25T00:00:00+01:00</dataPagamento>" + 
			"</elencoPagamentiOsapTemporanea>" + 
			"<elencoPagamentiOsapPermananente annoRiferimento=\"2018\" importoDocumento=\"11\">" + 
			"<posizioniOsap>" + 
			"<indirizzoUtenza>indirizzoUtenza</indirizzoUtenza>" + 
			"<superficie>1</superficie>" + 
			"<zonaUtenza>zonaUtenza</zonaUtenza>" + 
			"<descrizioneTariffa>descrizioneTariffa</descrizioneTariffa>" + 
			"<importoDaPagare>1</importoDaPagare>" + 
			"<mesi>1</mesi>" + 
			"</posizioniOsap>" + 
			"<rate>" + 
			"<importoRata>1</importoRata>" + 
			"<scadenzaRata>2010-05-25T00:00:00+01:00</scadenzaRata>" + 
			"<numeroRata>1</numeroRata>" + 
			"<identificativoRata>identificativoRata</identificativoRata>" + 
			"<importoPagato>1</importoPagato>" + 
			"<dataPagamento>2010-05-25T00:00:00+01:00</dataPagamento>" + 
			"</rate>" + 
			"</elencoPagamentiOsapPermananente>" + 
			"<elencoPagamentiTassaImmobili annoRiferimento=\"2018\">" + 
			"<posizioni>" + 
			"<identificativoUtenza>identificativoUtenza</identificativoUtenza>" + 
			"<indirizzoUtenza> " + 
			"<comune> " + 
			"<codice>codice</codice> " + 
			"<descrizione>descrizione</descrizione> " + 
			"</comune> " + 
			"<via> " + 
			"<codice>codice</codice> " + 
			"<descrizione>descrizione</descrizione> " + 
			"</via> " + 
			"<civico> " + 
			"<numero>87</numero> " + 
			"</civico> " + 
			"</indirizzoUtenza>" + 
			"<superficie>1</superficie>" + 
			"<tipoSuperficie>tipoSuperficie</tipoSuperficie>" + 
			"<caratteristicaImmobile>1</caratteristicaImmobile>" + 
			"<numero>1</numero>" + 
			"<foglio>1</foglio>" + 
			"<particella>1</particella>" + 
			"<valoreImmobile>1</valoreImmobile>" + 
			"<destinazione>" + 
			"<codice>C</codice>" + 
			"<descrizione>C</descrizione>" + 
			"</destinazione>	" + 
			"<tipo>tipo</tipo>" + 
			"<categoriaImmobile>" + 
			"<codice>C</codice>" + 
			"<descrizione>C</descrizione>" + 
			"</categoriaImmobile>" + 
			"<aliquota>1</aliquota>" + 
			"<importoDovuto>1</importoDovuto>" + 
			"<importoDetrazioneAbPrincipale>1</importoDetrazioneAbPrincipale>" + 
			"<mesiDetrazione>1</mesiDetrazione>" + 
			"<percentualePossesso>1</percentualePossesso>" + 
			"<mesiPossesso>1</mesiPossesso>" + 
			"<posseduto3112>TRUE</posseduto3112>" + 
			"<riduzione>TRUE</riduzione>" + 
			"<abitazionePrincipale>TRUE</abitazionePrincipale>" + 
			"<esclusoEsente>TRUE</esclusoEsente>" + 
			"</posizioni>" + 
			"<rate>" + 
			"<numeroFabbricati>1</numeroFabbricati>" + 
			"<importoDaPagare>1</importoDaPagare>" + 
			"<tipoRata>acconto</tipoRata>" + 
			"<importoPagato>1</importoPagato>" + 
			"<dataPagamento>2010-05-25T00:00:00+01:00</dataPagamento>" + 
			"</rate>" + 
			"<dataAggiornamento>2010-05-25T00:00:00+01:00</dataAggiornamento>" + 
			"</elencoPagamentiTassaImmobili>" + 
			"<elencoPagamentiTassaPubblicita annoRiferimento=\"2018\">" + 
			"<posizioniPubblicita>" + 
			"<indirizzoPubblicita>indirizzoPubblicita</indirizzoPubblicita>" + 
			"<descrizionePubblicita>descrizionePubblicita</descrizionePubblicita>" + 
			"<mq>1</mq>" + 
			"<mesi>1</mesi>" + 
			"<importoPubblicita>1</importoPubblicita>" + 
			"</posizioniPubblicita>" + 
			"<rate>" + 
			"<importoRata>1</importoRata>" + 
			"<scadenzaRata>2010-05-25T00:00:00+01:00</scadenzaRata>" + 
			"<numeroRata>1</numeroRata>" + 
			"<identificativoRata>identificativoRata</identificativoRata>" + 
			"<importoPagato>1</importoPagato>" + 
			"<dataPagamento>2010-05-25T00:00:00+01:00</dataPagamento>" + 
			"</rate>" + 
			"</elencoPagamentiTassaPubblicita>" + 
			"<elencoPagamentiTassaAffissioni annoRiferimento=\"2018\">" + 
			"<posizioniAffissione>" + 
			"<numeroFogli>1</numeroFogli>" + 
			"<numeroManifesti>1</numeroManifesti>" + 
			"<giorniAffissione>1</giorniAffissione>" + 
			"<tariffa>1</tariffa>" + 
			"<dimensioneManifesti>70 x 100</dimensioneManifesti>" + 
			"</posizioniAffissione>" + 
			"<rate>" + 
			"<importoRata>1</importoRata>" + 
			"<scadenzaRata>2010-05-25T00:00:00+01:00</scadenzaRata>" + 
			"<numeroRata>1</numeroRata>" + 
			"<identificativoRata>identificativoRata</identificativoRata>" + 
			"<importoPagato>1</importoPagato>" + 
			"<dataPagamento>2010-05-25T00:00:00+01:00</dataPagamento>" + 
			"</rate>" + 
			"</elencoPagamentiTassaAffissioni>" + 
			"<elencoPagamentiTassaCimiteriali annoRiferimento=\"2018\">" + 
			"<posizioniServiziCimiteriali>" + 
			"<defunti>" + 
			"<nomeDefunto>nomeDefunto</nomeDefunto>" + 
			"<dataNascita>2010-05-25T00:00:00+01:00</dataNascita>" + 
			"<dataMorte>2010-05-25T00:00:00+01:00</dataMorte>" + 
			"</defunti>" + 
			"<nomecimitero>nomecimitero</nomecimitero>" + 
			"<posizione>posizione</posizione>" + 
			"<lampadaVotiva>" + 
			"<mesi>1</mesi>" + 
			"<tariffa>tariffa</tariffa>" + 
			"<importoLampada>1</importoLampada>" + 
			"</lampadaVotiva>" + 
			"</posizioniServiziCimiteriali>" + 
			"<rate>" + 
			"<importoRata>1</importoRata>" + 
			"<scadenzaRata>2010-05-25T00:00:00+01:00</scadenzaRata>" + 
			"<numeroRata>1</numeroRata>" + 
			"<identificativoRata>identificativoRata</identificativoRata>" + 
			"<importoPagato>1</importoPagato>" + 
			"<dataPagamento>2010-05-25T00:00:00+01:00</dataPagamento>" + 
			"</rate>" + 
			"<dataAggiornamento>2010-05-25T00:00:00+01:00</dataAggiornamento>" + 
			"</elencoPagamentiTassaCimiteriali>" + 
			"</visuraPosizioneTributariaRisposta>";
			}

	private String visuraDichiarazioneTassaImmobili() {
		
		return "<dichiarazioneTassaImmobiliRisposta xmlns=\"http://types.output.tributi.osapulie.it\" xsi:noNamespaceSchemaLocation=\"DichiarazioneTassaImmobili-risposta.xsd\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" + 
				"<situazione annoRiferimento=\"2018\" descrizioneTassa=\"IMU\">" + 
				"<DatiTassaImmobili>" + 
				"<posizioni>" + 
				"<identificativoUtenza>identificativoUtenza</identificativoUtenza>" + 
				"<indirizzoUtenza>" + 
				"<comune>" + 
				"<codice>codice</codice>" + 
				"<descrizione>descrizione</descrizione> " + 
				"</comune>" + 
				"<via>" + 
				"<codice>codice</codice>" + 
				"<descrizione>descrizione</descrizione>" + 
				"</via>" + 
				"<civico>" + 
				"<numero>87</numero>" + 
				"</civico>" + 
				"</indirizzoUtenza>" + 
				"<superficie>1</superficie>" + 
				"<tipoSuperficie>tipoSuperficie</tipoSuperficie>" + 
				"<caratteristicaImmobile>1</caratteristicaImmobile>" + 
				"<numero>1</numero>" + 
				"<foglio>1</foglio>" + 
				"<particella>1</particella>" + 
				"<valoreImmobile>1</valoreImmobile>" + 
				"<destinazione>" + 
				"<codice>C</codice>" + 
				"<descrizione>C</descrizione>" + 
				"</destinazione>" + 
				"<tipo>tipo</tipo>" + 
				"<categoriaImmobile>" + 
				"<codice>C</codice>" + 
				"<descrizione>C</descrizione>" + 
				"</categoriaImmobile>" + 
				"<aliquota>1</aliquota>" + 
				"<importoDovuto>1</importoDovuto>" + 
				"<importoDetrazioneAbPrincipale>1</importoDetrazioneAbPrincipale>" + 
				"<mesiDetrazione>1</mesiDetrazione>" + 
				"<percentualePossesso>1</percentualePossesso>" + 
				"<mesiPossesso>1</mesiPossesso>" + 
				"<posseduto3112>TRUE</posseduto3112>" + 
				"<riduzione>TRUE</riduzione>" + 
				"<abitazionePrincipale>TRUE</abitazionePrincipale>" + 
				"<esclusoEsente>TRUE</esclusoEsente>" + 
				"</posizioni>" + 
				"<rate>" + 
				"<numeroFabbricati>1</numeroFabbricati>" + 
				"<importoDaPagare>1</importoDaPagare>" + 
				"<tipoRata>acconto</tipoRata>" + 
				"<importoPagato>1</importoPagato>" + 
				"<dataPagamento>2010-05-25T00:00:00+01:00</dataPagamento>" + 
				"</rate>" + 
				"<dataAggiornamento>2010-05-25T00:00:00+01:00</dataAggiornamento>" + 
				"</DatiTassaImmobili>" + 
				"</situazione>" + 
				"</dichiarazioneTassaImmobiliRisposta>";
	}
	
	private String stradarioService() {
		return "<stradarioRisposta xmlns=\"http://types.servizi.osapulie.it\" xsi:noNamespaceSchemaLocation=\"Stradario-risposta.xsd\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" + 
				"<indirizzo>" + 
				"<via>" + 
				"<identificativo>11</identificativo>" + 
				"<denominazione>denominazione via</denominazione>" + 
				"<civico>" + 
				"<identificativo>11</identificativo>" + 
				"<numero>11</numero>" + 
				"</civico>" + 
				"</via>" + 
				"<civico>" + 
				"<identificativo>11</identificativo>" + 
				"<numero>11</numero>" + 
				"</civico>" + 
				"</indirizzo>" + 
				"</stradarioRisposta>";
	}
	
	private String stradarioAscotService() {
		return "<stradarioAscotRisposta xmlns=\"http://types.servizi.osapulie.it\" xsi:noNamespaceSchemaLocation=\"StradarioAscot-risposta.xsd\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" + 
				"<indirizzoAscot>" + 
				"<codiceVia>11</codiceVia>" + 
				"<identificativoCivico>1111</identificativoCivico>" + 
				"<numeroCivico>87</numeroCivico>" + 
				"</indirizzoAscot>" + 
				"</stradarioAscotRisposta>";
	}
	
	private String dichiarazioneCambioResidenza() {
		return "<dichiarazioneCambioResidenzaRisposta xmlns=\"http://types.input.anagrafe.osapulie.it\" xsi:noNamespaceSchemaLocation=\"DichiarazioneCambioResidenza-risposta.xsd\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" + 
				"<idPratica>123456789</idPratica>" + 
				"<dataInserimento>2010-05-25T00:00:00+01:00</dataInserimento>" + 
				"<numeroProtocollo>1</numeroProtocollo>" + 
				"<dataProtocollo>2010-05-25T00:00:00+01:00</dataProtocollo>" + 
				"</dichiarazioneCambioResidenzaRisposta>";
	}

	private String visuraTosapTemporaneaRichiesta() {
		return "<pagamentiOsapTemporaneaRisposta xmlns=\"http://types.output.tributi.osapulie.it\" xsi:noNamespaceSchemaLocation=\"PagamentoOsapTemporanea-risposta.xsd\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" + 
				"<elencoOsapTemporanea numeroDocumento=\"numeroDocumento\" importoDocumento=\"12\" contoCorrente=\"contoCorrente\" annoRiferimento=\"2017\">" + 
				"<posizioniOsap>" + 
				"<indirizzoUtenza>indirizzoUtenza</indirizzoUtenza>" + 
				"<superficie>12</superficie>" + 
				"<zona>zona</zona>" + 
				"<descrizioneTariffa>descrizioneTariffa</descrizioneTariffa>" + 
				"<durataOccupazione>12</durataOccupazione>" + 
				"<importoDaPagare>12</importoDaPagare>" + 
				"<importoPagato>12</importoPagato>" + 
				"<dataPagamento>2010-05-25T00:00:00+01:00</dataPagamento>" + 
				"</posizioniOsap>" + 
				"<dataAggiornamento>2010-05-25T00:00:00+01:00</dataAggiornamento>" + 
				"</elencoOsapTemporanea>" + 
				"</pagamentiOsapTemporaneaRisposta>";
	}

	private String visuraTosapRichiesta() {
		return "<pagamentiOsapPermanenteRisposta xmlns=\"http://types.output.tributi.osapulie.it\" xsi:noNamespaceSchemaLocation=\"PagamentoOsapPermanente-risposta.xsd\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" + 
				"<elencoOsapPermanente numeroDocumento=\"numeroDocumento\" importoDocumento=\"12\" contoCorrente=\"contoCorrente\" annoRiferimento=\"2017\">" + 
				"<posizioniOsap>" + 
				"<indirizzoUtenza>indirizzoUtenza</indirizzoUtenza>" + 
				"<superficie>12</superficie>" + 
				"<zonaUtenza>zonaUtenza</zonaUtenza>" + 
				"<descrizioneTariffa>descrizioneTariffa</descrizioneTariffa>" + 
				"<importoDaPagare>12</importoDaPagare>" + 
				"<mesi>12</mesi>" + 
				"</posizioniOsap>" + 
				"<rate>" + 
				"<importoRata>12</importoRata>" + 
				"<scadenzaRata>2010-05-25T00:00:00+01:00</scadenzaRata>" + 
				"<numeroRata>2</numeroRata>" + 
				"<identificativoRata>identificativoRata</identificativoRata>" + 
				"<importoPagato>12</importoPagato>" + 
				"<dataPagamento>2010-05-25T00:00:00+01:00</dataPagamento>" + 
				"</rate>" + 
				"</elencoOsapPermanente>" + 
				"</pagamentiOsapPermanenteRisposta>";
	}

}