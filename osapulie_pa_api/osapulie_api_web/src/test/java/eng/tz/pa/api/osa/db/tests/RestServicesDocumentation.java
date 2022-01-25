package eng.tz.pa.api.osa.db.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.modifyUris;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.documentationConfiguration;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;


import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.headers.HeaderDescriptor;
import org.springframework.restdocs.headers.HeaderDocumentation;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.request.ParameterDescriptor;
import org.springframework.restdocs.request.RequestParametersSnippet;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestServicesDocumentation {
	private static final Logger logger = LoggerFactory.getLogger(RestServicesDocumentation.class.getName());
	@Rule
	public JUnitRestDocumentation restDoc = new JUnitRestDocumentation();
	private RequestSpecification documentationSpec;

	@Before
	public void setup() {
		this.documentationSpec = new RequestSpecBuilder().addFilter(documentationConfiguration(restDoc)).build();
	}

	
	
	@Test
	public void testTotale_Accessi_Servizi() {
	 
		try {
 			
			
			 given(this.documentationSpec)
             .accept(MediaType.APPLICATION_JSON.toString())
             .filter(document("totale-accessi-servizi",
             preprocessRequest(modifyUris().scheme("http")
                     .host("localhost")
                     .removePort()),
             preprocessResponse(prettyPrint()),
             responseFields(
                     fieldWithPath("esito").description("Codice HttpStatus"),
                     fieldWithPath("descrizione").description("Descrizione HttpStatus"),
                     fieldWithPath("numeroOggettiTotali").description("Totale oggetti trovati"),
                     fieldWithPath("numeroOggettiRestituiti").description("Totale oggetti restituiti"),
                     fieldWithPath("payload").type(JsonFieldType.ARRAY).description("Lista Oggetti").optional(),
                     fieldWithPath("payload[].descrizione").description("Nome Servizio Attivo"),
                     fieldWithPath("payload[].totale").type(JsonFieldType.NUMBER).description("Totale Accessi")
                     
             )))
            // .body("{\"esito\" : 200,\"descrizione\" : \"HttpStatus OK\",\"numeroOggettiTotali\" : 35,\"numeroOggettiRestituiti\" : 35,\"payload\" : [ {\"descrizione\" : \"CERTIFICATO STATO LIBERO\",\"totale\" : 230} ]}")
             .when()
             .port(8083)
             .get("/osapaapi/api/totale-accessi-servizi")
             .then()
             .assertThat()
             .statusCode(is(200));
			
			
			
		} catch (Exception e) {
			logger.error("Errore nel processo di login", e);
		}
	}
	
	
	
	@Test
	public void testComune_servizi_attivi() {
	 
		try {
 			
			
			 given(this.documentationSpec)
             .accept(MediaType.APPLICATION_JSON.toString())
             .filter(document("comune-servizi-attivi",
             preprocessRequest(modifyUris().scheme("http")
                     .host("localhost")
                     .removePort()),
             preprocessResponse(prettyPrint()),
             responseFields(
                     fieldWithPath("esito").description("Codice HttpStatus"),
                     fieldWithPath("descrizione").description("Descrizione HttpStatus"),
                     fieldWithPath("numeroOggettiTotali").description("Totale oggetti trovati"),
                     fieldWithPath("numeroOggettiRestituiti").description("Totale oggetti restituiti"),
                     fieldWithPath("payload").type(JsonFieldType.ARRAY).description("Lista Oggetti").optional(),
                     fieldWithPath("payload[].nome").description("Comune"),
                     fieldWithPath("payload[].descrizione").description("Info Comune"),
                     fieldWithPath("payload[].servizio").type(JsonFieldType.STRING).description("Nome Servizio Attivo")
                     
             ) ))
             .when()
             .port(8083)
             .get("/osapaapi/api/comune-servizi-attivi")
             .then()
             .assertThat()
             .statusCode(is(200));
			
			
			
		} catch (Exception e) {
			logger.error("Errore nel processo di login", e);
		}
	}
	
	@Test
	public void testTotale_servizi_comune() {
	 
		try {
 			
			
			 given(this.documentationSpec)
             .accept(MediaType.APPLICATION_JSON.toString())
             .filter(document("totale-servizi-comune",
             preprocessRequest(modifyUris().scheme("http")
                     .host("localhost")
                     .removePort()),
             preprocessResponse(prettyPrint()),
             responseFields(
                     fieldWithPath("esito").description("Codice HttpStatus"),
                     fieldWithPath("descrizione").description("Descrizione HttpStatus"),
                     fieldWithPath("numeroOggettiTotali").description("Totale oggetti trovati"),
                     fieldWithPath("numeroOggettiRestituiti").description("Totale oggetti restituiti"),
                     fieldWithPath("payload").type(JsonFieldType.ARRAY).description("Lista Oggetti").optional(),
                     fieldWithPath("payload[].descrizione").description("Info Comune"),
                     fieldWithPath("payload[].totale").type(JsonFieldType.NUMBER).description("Totale Servizi Comune")
                     
             )))
             .when()
             .port(8083)
             .get("/osapaapi/api/totale-servizi-comune")
             .then()
             .assertThat()
             .statusCode(is(200));
			
			
			
		} catch (Exception e) {
			logger.error("Errore nel processo di login", e);
		}
	}
	
	
	@Test
	public void testTotale_all_servizi() {
	 
		try {
 			
			
			 given(this.documentationSpec)
             .accept(MediaType.APPLICATION_JSON.toString())
             .filter(document("totale-all-servizi",
             preprocessRequest(modifyUris().scheme("http")
                     .host("localhost")
                     .removePort()),
             preprocessResponse(prettyPrint()),
             responseFields(
                     fieldWithPath("esito").description("Codice HttpStatus"),
                     fieldWithPath("descrizione").description("Descrizione HttpStatus"),
                     fieldWithPath("numeroOggettiTotali").description("Totale oggetti trovati"),
                     fieldWithPath("numeroOggettiRestituiti").description("Totale oggetti restituiti"),
                     fieldWithPath("payload").type(JsonFieldType.ARRAY).description("Lista Oggetti").optional(),
                     fieldWithPath("payload[].descrizione").description("descrizione"),
                     fieldWithPath("payload[].totale").type(JsonFieldType.NUMBER).description("Totale di tutti i Servizi")
                     
             )))
             .when()
             .port(8083)
             .get("/osapaapi/api/totale-all-servizi")
             .then()
             .assertThat()
             .statusCode(is(200));
			
			
			
		} catch (Exception e) {
			logger.error("Errore nel processo di login", e);
		}
	}
	
	
	@Test
	public void testRichieste_servizi_azienda() {
	 
		try {
 			
			
			 given(this.documentationSpec)
             .accept(MediaType.APPLICATION_JSON.toString())
             .filter(document("richieste-servizi-azienda",
             preprocessRequest(modifyUris().scheme("http")
                     .host("localhost")
                     .removePort()),
             preprocessResponse(prettyPrint()),
             responseFields(
                     fieldWithPath("esito").description("Codice HttpStatus"),
                     fieldWithPath("descrizione").description("Descrizione HttpStatus"),
                     fieldWithPath("numeroOggettiTotali").description("Totale oggetti trovati"),
                     fieldWithPath("numeroOggettiRestituiti").description("Totale oggetti restituiti"),
                     fieldWithPath("payload").type(JsonFieldType.ARRAY).description("Lista Oggetti").optional(),
                     fieldWithPath("payload[].data_richiesta").description("Data richiesta servizio"),
                     fieldWithPath("payload[].coduser").description("Cod Utente"),
                     fieldWithPath("payload[].servizio").description("Nome Servizio"),
                     fieldWithPath("payload[].ragione_sociale").type(JsonFieldType.STRING).description("Ragione sociale azienda")
                     
             ))) 
             .when()
             .port(8083)
             .get("/osapaapi/api/richieste-servizi-azienda")
             .then()
             .assertThat()
             .statusCode(is(200));
			
			
			
		} catch (Exception e) {
			logger.error("Errore nel processo di login", e);
		}
	}
	@Test
	public void testDettaglio_Accessi_Servizi_Azienda() {
	 
		try {
 			
			
			 given(this.documentationSpec)
             .accept(MediaType.APPLICATION_JSON.toString())
             .filter(document("dettaglio-accessi-servizi-azienda",
             preprocessRequest(modifyUris().scheme("http")
                     .host("localhost")
                     .removePort()),
             preprocessResponse(prettyPrint()),
             responseFields(
                     fieldWithPath("esito").description("Codice HttpStatus"),
                     fieldWithPath("descrizione").description("Descrizione HttpStatus"),
                     fieldWithPath("numeroOggettiTotali").description("Totale oggetti trovati"),
                     fieldWithPath("numeroOggettiRestituiti").description("Totale oggetti restituiti"),
                     fieldWithPath("payload").type(JsonFieldType.ARRAY).description("Lista Oggetti").optional(),
                     fieldWithPath("payload[].totale").type(JsonFieldType.NUMBER).description("Totale Richieste"),
                     fieldWithPath("payload[].data_richiesta").description("Data Richiesta"),
                     fieldWithPath("payload[].servizio").description("Nome Servizio"),
                     fieldWithPath("payload[].ragione_sociale").description("Ragione sociale azienda"),
                     fieldWithPath("payload[].piva").type(JsonFieldType.STRING).description("Cod/Fisc - Partita Iva azienda")
                     
                     
                     
             )))
             //.body("")
             .when()
             .port(8083)
             .get("/osapaapi/api/dettaglio-accessi-servizi-azienda/10169")
             .then()
             .assertThat()
             .statusCode(is(200));
			
			
			
		} catch (Exception e) {
			logger.error("Errore nel processo di login", e);
		}
	}

	@Test
	public void testDettaglio_Accessi_Servizi_Per_Data() {
	 
		try {
 			
			
			 given(this.documentationSpec)
             .accept(MediaType.APPLICATION_JSON.toString())
             .filter(document("dettaglio-accessi-servizi",
             preprocessRequest(modifyUris().scheme("http")
                     .host("localhost")
                     .removePort()),
             preprocessResponse(prettyPrint()),
             responseFields(
                     fieldWithPath("esito").description("Codice HttpStatus"),
                     fieldWithPath("descrizione").description("Descrizione HttpStatus"),
                     fieldWithPath("numeroOggettiTotali").description("Totale oggetti trovati"),
                     fieldWithPath("numeroOggettiRestituiti").description("Totale oggetti restituiti"),
                     fieldWithPath("payload").type(JsonFieldType.ARRAY).description("Lista Oggetti").optional(),
                     fieldWithPath("payload[].data").description("Data Richiesta"),
                     fieldWithPath("payload[].totale").type(JsonFieldType.NUMBER).description("Totale Richieste"),
                     fieldWithPath("payload[].nome_Servizio").description("Nome Servizio")
            
                     
                     
                     
             )))
             //.body("")
             .when()
             .port(8083)
             .get("/osapaapi/api/dettaglio-accessi-servizi/2018-10-11/2018-10-13")
             .then()
             .assertThat()
             .statusCode(is(200));
			
			
			
		} catch (Exception e) {
			logger.error("Errore nel processo di login", e);
		}
	}
	
	@Test
	public void testInfo_azienda() {
	 
		try {
 			
			
			 given(this.documentationSpec)
             .accept(MediaType.APPLICATION_JSON.toString())
             .filter(document("azienda",
             preprocessRequest(modifyUris().scheme("http")
                     .host("localhost")
                     .removePort()),
             preprocessResponse(prettyPrint()),
             responseFields(
                     fieldWithPath("esito").description("Codice HttpStatus"),
                     fieldWithPath("descrizione").description("Descrizione HttpStatus"),
                     fieldWithPath("numeroOggettiTotali").description("Totale oggetti trovati"),
                     fieldWithPath("numeroOggettiRestituiti").description("Totale oggetti restituiti"),
                     fieldWithPath("payload").type(JsonFieldType.ARRAY).description("Lista Oggetti").optional(),
                     fieldWithPath("payload[].ragione_sociale").description("Denominazione  azienda"),
                     fieldWithPath("payload[].partita_iva").description("Cod/Fisc - Partita Iva azienda"),
                     fieldWithPath("payload[].tipo").description("Tipologia Azienda"),
                     fieldWithPath("payload[].iduser").description("Cod Utente")
                     
             ))) 
             .when()
             .port(8083)
             .get("/osapaapi/api/azienda")
             .then()
             .assertThat()
             .statusCode(is(200));
			
			
			
		} catch (Exception e) {
			logger.error("Errore nel processo di login", e);
		}
	}

	
	@Test
	public void testCambioResidenzaPartenzeArrivi() {
	 
		try {
 			
			
			 given(this.documentationSpec)
             .accept(MediaType.APPLICATION_JSON.toString())
             .filter(document("cambio-residenza-partenza-arrivi",
             preprocessRequest(modifyUris().scheme("http")
                     .host("localhost")
                     .removePort()),
             preprocessResponse(prettyPrint()),
             responseFields(
                     fieldWithPath("esito").description("Codice HttpStatus"),
                     fieldWithPath("descrizione").description("Descrizione HttpStatus"),
                     fieldWithPath("numeroOggettiTotali").description("Totale oggetti trovati"),
                     fieldWithPath("numeroOggettiRestituiti").description("Totale oggetti restituiti"),
                     fieldWithPath("payload").type(JsonFieldType.ARRAY).description("Lista Oggetti").optional(),
                     fieldWithPath("payload[].totale").type(JsonFieldType.NUMBER).description("Totale migrati per comune partenza"),
                     fieldWithPath("payload[].comune_partenza").description("Denominazione Comune Partenza"),
                     fieldWithPath("payload[].cod_istat_partenza").description("Codice Istat comune partenza"),
                     fieldWithPath("payload[].comune_arrivo").description("Denominazione Comune Arrivo"),
                     fieldWithPath("payload[].cod_istat_arrivo").description("Codice Istat comune Arrivo")
                     
             ))) 
             .when()
             .port(8083)
             .get("/osapaapi/api/cambio-residenza-partenza-arrivi")
             .then()
             .assertThat()
             .statusCode(is(200));
			
			
			
		} catch (Exception e) {
			logger.error("Errore nel processo di login", e);
		}
	}
	
	
	//DWH
	
	@Test
	public void testServiziAttribute() {
	 
		try {
 			
			
			 given(this.documentationSpec)
             .accept(MediaType.APPLICATION_JSON.toString())
             .filter(document("servizi-attribute",
             preprocessRequest(modifyUris().scheme("http")
                     .host("localhost")
                     .removePort()),
             preprocessResponse(prettyPrint()),
             responseFields(
                     fieldWithPath("esito").description("Codice HttpStatus"),
                     fieldWithPath("descrizione").description("Descrizione HttpStatus"),
                     fieldWithPath("numeroOggettiTotali").description("Totale oggetti trovati"),
                     fieldWithPath("numeroOggettiRestituiti").description("Totale oggetti restituiti"),
                     fieldWithPath("payload").type(JsonFieldType.ARRAY).description("Lista Oggetti").optional(),
                     fieldWithPath("payload[].uuid").description("Codice Operazione"),
                     fieldWithPath("payload[].data_evento").description("Timestamp data operazione"),
                     fieldWithPath("payload[].comune").description("Nome Comune"),
                     fieldWithPath("payload[].host_app").description("Nome Host App"),
                     fieldWithPath("payload[].servizio_code").description("Codice Srvizio"),
                     fieldWithPath("payload[].servizio_nome").description("Nome del Srvizio"),
                     fieldWithPath("payload[].cittadino_userid").description("UserId del Cittadino"),
                     fieldWithPath("payload[].cittadino_eta").description("Eta' Cittadino"),
                     fieldWithPath("payload[].cittadino_sesso").description("Cittadino sesso"),
                     fieldWithPath("payload[].cittadino_comune").description("Cittadino comune"),
                     fieldWithPath("payload[].cittadino_provincia").description("Cittadino provincia"),
                     fieldWithPath("payload[].cittadino_regione").description("Cittadino regione"),
                     fieldWithPath("payload[].cittadino_autenticazione_forte").description("Cittadino autenticazione forte"),
                     fieldWithPath("payload[].cittadino_livello_autenticazione").description("Cittadino livello autenticazione"),
                     fieldWithPath("payload[].cittadino_canale_autenticazione").description("Cittadino canale autenticazione"),
                     fieldWithPath("payload[].ente_tipo").description("Ente tipo"),
                     fieldWithPath("payload[].ente_partita_iva").description("Ente partita iva"),
                     fieldWithPath("payload[].ente_userid").description("Ente userid"),
                     fieldWithPath("payload[].ente_comune").description("Ente comune"),
                     fieldWithPath("payload[].ente_provincia").description("Ente provincia"),
                     fieldWithPath("payload[].ente_regione").description("Ente regione"),
                     fieldWithPath("payload[].servizio_parametro1").description("Servizio parametro1"),
                     fieldWithPath("payload[].servizio_parametro2").description("Servizio parametro2"),
                     fieldWithPath("payload[].servizio_parametro3").description("Servizio parametro3"),
                     fieldWithPath("payload[].servizio_uri").description("Servizio uri"),
                     fieldWithPath("payload[].servizio_protocollo").description("Num. protocollo"),
                     fieldWithPath("payload[].servizio_data_richiesta").description("Servizio data richiesta"),
                     fieldWithPath("payload[].servizio_autenticazone").description("Servizio autenticazone"),
                     fieldWithPath("payload[].servizio_inizio").description("Timestamp data inizio operazione"),
                     fieldWithPath("payload[].servizio_fine").description("Timestamp data fine operazione")

                     
             ))) 
             .when()
             .port(8083)
             .get("/osapaapi/api/servizi-attribute/TD05")
             .then()
             .assertThat()
             .statusCode(is(200));
			
			
			
		} catch (Exception e) {
			logger.error("Errore nel processo di login", e);
		}
	}
	
 
	@Test
	public void testServiziMediaEta() {
	 
		try {
 			
			
			 given(this.documentationSpec)
             .accept(MediaType.APPLICATION_JSON.toString())
             .filter(document("servizi-media-eta",
             preprocessRequest(modifyUris().scheme("http")
                     .host("localhost")
                     .removePort()),
             preprocessResponse(prettyPrint()),
             responseFields(
                     fieldWithPath("esito").description("Codice HttpStatus"),
                     fieldWithPath("descrizione").description("Descrizione HttpStatus"),
                     fieldWithPath("numeroOggettiTotali").description("Totale oggetti trovati"),
                     fieldWithPath("numeroOggettiRestituiti").description("Totale oggetti restituiti"),
                     fieldWithPath("payload").type(JsonFieldType.ARRAY).description("Lista Oggetti").optional(),
                     fieldWithPath("payload[].eta").description("Eta' media del richiedente servizio"),
                     fieldWithPath("payload[].sesso").description("Sesso del richiedente servizio"),
                     fieldWithPath("payload[].servizio_code").description("Codice Servizio"),
                     fieldWithPath("payload[].servizio_nome").description("Nome Servizio")

                     
             ))) 
             .when()
             .port(8083)
             .get("/osapaapi/api/servizi-media-eta")
             .then()
             .assertThat()
             .statusCode(is(200));
			
			
			
		} catch (Exception e) {
			logger.error("Errore nel processo di login", e);
		}
	}
	
 //servizi-media-tempo
	@Test
	public void testServiziMediaTempo() {
	 
		try {
 			
			
			 given(this.documentationSpec)
             .accept(MediaType.APPLICATION_JSON.toString())
             .filter(document("servizi-media-tempo",
             preprocessRequest(modifyUris().scheme("http")
                     .host("localhost")
                     .removePort()),
             preprocessResponse(prettyPrint()),
             responseFields(
                     fieldWithPath("esito").description("Codice HttpStatus"),
                     fieldWithPath("descrizione").description("Descrizione HttpStatus"),
                     fieldWithPath("numeroOggettiTotali").description("Totale oggetti trovati"),
                     fieldWithPath("numeroOggettiRestituiti").description("Totale oggetti restituiti"),
                     fieldWithPath("payload").type(JsonFieldType.ARRAY).description("Lista Oggetti").optional(),
                     fieldWithPath("payload[].tempo_medio").description("Tempo medio di esecuzione in millisecondi (ms)"),
                     fieldWithPath("payload[].servizio_code").description("Codice Servizio"),
                     fieldWithPath("payload[].servizio_nome").description("Nome Servizio")

                     
             ))) 
             .when()
             .port(8083)
             .get("/osapaapi/api/servizi-media-tempo")
             .then()
             .assertThat()
             .statusCode(is(200));
			
			
			
		} catch (Exception e) {
			logger.error("Errore nel processo di login", e);
		}
	}
	
	
	//servizi-completi-incompleti
	
	@Test
	public void testServiziCompletiIncompleti() {
	 
		try {
 			
			
			 given(this.documentationSpec)
             .accept(MediaType.APPLICATION_JSON.toString())
             .filter(document("servizi-completi-incompleti",
             preprocessRequest(modifyUris().scheme("http")
                     .host("localhost")
                     .removePort()),
             preprocessResponse(prettyPrint()),
             responseFields(
                     fieldWithPath("esito").description("Codice HttpStatus"),
                     fieldWithPath("descrizione").description("Descrizione HttpStatus"),
                     fieldWithPath("numeroOggettiTotali").description("Totale oggetti trovati"),
                     fieldWithPath("numeroOggettiRestituiti").description("Totale oggetti restituiti"),
                     fieldWithPath("payload").type(JsonFieldType.ARRAY).description("Lista Oggetti").optional(),
                     fieldWithPath("payload[].totale_richieste").description("Totale richieste servizio"),
                     fieldWithPath("payload[].completati").description("Totale operazioni concluse di servizio richiesto"),
                     fieldWithPath("payload[].incompleti").description("Totale operazioni di servizio richiesto Non completate per qualche motivo"),
                     fieldWithPath("payload[].percentuale_completamento").description("Percentuale dei servizi richiesti conclusi"),
                     fieldWithPath("payload[].servizio_nome").description("Nome Servizio"),
                     fieldWithPath("payload[].servizio_code").description("Codice Servizio")

                     
             ))) 
             .when()
             .port(8083)
             .get("/osapaapi/api/servizi-completi-incompleti")
             .then()
             .assertThat()
             .statusCode(is(200));
			
			
			
		} catch (Exception e) {
			logger.error("Errore nel processo di login", e);
		}
	}
	
	//servizi-richiesti-geolocalizzazione
	
	@Test
	public void testServiziGeolocalizzazione() {
	 
		try {
 			
			
			 given(this.documentationSpec)
             .accept(MediaType.APPLICATION_JSON.toString())
             .filter(document("servizi-richiesti-geolocalizzazione",
             preprocessRequest(modifyUris().scheme("http")
                     .host("localhost")
                     .removePort()),
             preprocessResponse(prettyPrint()),
             responseFields(
                     fieldWithPath("esito").description("Codice HttpStatus"),
                     fieldWithPath("descrizione").description("Descrizione HttpStatus"),
                     fieldWithPath("numeroOggettiTotali").description("Totale oggetti trovati"),
                     fieldWithPath("numeroOggettiRestituiti").description("Totale oggetti restituiti"),
                     fieldWithPath("payload").type(JsonFieldType.ARRAY).description("Lista Oggetti").optional(),
                     fieldWithPath("payload[].servizio_totale_richieste").description("Totale richieste servizio"),
                     fieldWithPath("payload[].totale_locale").description("Totale servizio richiesti per localita'"),
                     fieldWithPath("payload[].cap_locale").description("C A P"),
                     fieldWithPath("payload[].percentuale_locale").description("Percentuale dei servizi richiesti per localita'"),
                     fieldWithPath("payload[].servizio_code").description("Codice Servizio"),
                     fieldWithPath("payload[].servizio_nome").description("Nome Servizio"),
                     fieldWithPath("payload[].citta_locale").description("Citta'"),
                     fieldWithPath("payload[].regione_locale").description("Regione"),
                     fieldWithPath("payload[].latitudine").description("Latitudine"),
                     fieldWithPath("payload[].longitudine").description("Longitudine"),
                     fieldWithPath("payload[].anno").description("Anno")

                     
             ))) 
             .when()
             .port(8083)
             .get("/osapaapi/api/servizi-richiesti-geolocalizzazione")
             .then()
             .assertThat()
             .statusCode(is(200));
			
			
			
		} catch (Exception e) {
			logger.error("Errore nel processo di login", e);
		}
	}
	
	@Test
	public void testServizio() {
	 
		try {
 			
			
			 given(this.documentationSpec)
             .accept(MediaType.APPLICATION_JSON.toString())
             .filter(document("servizio",
             preprocessRequest(modifyUris().scheme("http")
                     .host("localhost")
                     .removePort()),
             preprocessResponse(prettyPrint()),
             responseFields(
                     fieldWithPath("esito").description("Codice HttpStatus"),
                     fieldWithPath("descrizione").description("Descrizione HttpStatus"),
                     fieldWithPath("numeroOggettiTotali").description("Totale oggetti trovati"),
                     fieldWithPath("numeroOggettiRestituiti").description("Totale oggetti restituiti"),
                     fieldWithPath("payload").type(JsonFieldType.ARRAY).description("Lista Oggetti").optional(),
                     fieldWithPath("payload[].servizio_id").description("Id Servizio"),
                     fieldWithPath("payload[].servizio_code").description("Codice Servizio"),
                     fieldWithPath("payload[].servizio_nome").description("Nome Servizio")
  

                     
             ))) 
             .when()
             .port(8083)
             .get("/osapaapi/api/servizio")
             .then()
             .assertThat()
             .statusCode(is(200));
			
			
			
		} catch (Exception e) {
			logger.error("Errore nel processo di login", e);
		}
	}	
	
	@Test
	public void testServiziPerFasciaEta() {
	 
		try {
 			
			
			 given(this.documentationSpec)
             .accept(MediaType.APPLICATION_JSON.toString())
             .filter(document("servizi-fascia-eta",
             preprocessRequest(modifyUris().scheme("http")
                     .host("localhost")
                     .removePort()),
             preprocessResponse(prettyPrint()),
             responseFields(
                     fieldWithPath("esito").description("Codice HttpStatus"),
                     fieldWithPath("descrizione").description("Descrizione HttpStatus"),
                     fieldWithPath("numeroOggettiTotali").description("Totale oggetti trovati"),
                     fieldWithPath("numeroOggettiRestituiti").description("Totale oggetti restituiti"),
                     fieldWithPath("payload").type(JsonFieldType.ARRAY).description("Lista Oggetti").optional(),
                     fieldWithPath("payload[].totale_servizi").description("Numero totale del servizio richiesto"),
                     fieldWithPath("payload[].eta_ninore_18").description("Numero servizi richiesti da utenti di anni minore di 18"),
                     fieldWithPath("payload[].percentuale_18").description("Percentuale servizio richiesto da utenti di anni minore di 18"),
                     fieldWithPath("payload[].eta_tra_1839").description("Numero servizi richiesti da utenti tra i 18 e 39 anni"),
                     fieldWithPath("payload[].percentuale_1839").description("Percentuale servizio richiesto da utenti tra i 18 e 39 anni"),
                     fieldWithPath("payload[].eta_tra_4059").description("Numero servizi richiest da utenti tra i 40 e 59 anni"),
                     fieldWithPath("payload[].percentuale_4059").description("Percentuale servizio richiesto da utenti tra i 40 e 59 anni"),
                     fieldWithPath("payload[].eta_oltre_60").description("Numero servizi richiesti da utenti oltre i 60 anni"),
                     fieldWithPath("payload[].percentuale_60").description("Percentuale servizio richiesto da utenti oltre i 60 anni"),
                     fieldWithPath("payload[].anno").description("Anno Servizio"),
                     fieldWithPath("payload[].servizio_code").description("Codice Servizio"),
                     fieldWithPath("payload[].servizio_nome").description("Nome Servizio")

                     
             ))) 
             .when()
             .port(8083)
             .get("/osapaapi/api/servizi-fascia-eta")
             .then()
             .assertThat()
             .statusCode(is(200));
			
			
			
		} catch (Exception e) {
			logger.error("Errore nel processo di login", e);
		}
	}
	
	
	
	
}
