package eng.tz.pa.api.osa.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eng.tz.pa.api.osa.dao.models.CambioResidenzaPartenzeArrivi;
import eng.tz.pa.api.osa.dao.models.ComuneServizi;
import eng.tz.pa.api.osa.dao.models.DwhServizioAttribute;
import eng.tz.pa.api.osa.dao.models.EtaMediaServiziRichiesti;
import eng.tz.pa.api.osa.dao.models.InfoAzienda;
import eng.tz.pa.api.osa.dao.models.RichiesteServiziAzienda;
import eng.tz.pa.api.osa.dao.models.RichiesteServiziPerAzienda;
import eng.tz.pa.api.osa.dao.models.RichiesteServiziUtente;
import eng.tz.pa.api.osa.dao.models.ServiziRichiestiGeolocalizzazione;
import eng.tz.pa.api.osa.dao.models.ServiziRichiestiPerFasciaDiEta;
import eng.tz.pa.api.osa.dao.models.ServiziRichiestiPercCompletati;
import eng.tz.pa.api.osa.dao.models.Servizio;
import eng.tz.pa.api.osa.dao.models.TempoMedioServiziRichiesti;
import eng.tz.pa.api.osa.dao.models.TotaleAccessiServizi;
import eng.tz.pa.api.osa.dao.models.TotaleAccessiServiziPerData;
import eng.tz.pa.api.osa.dao.models.TotaleServiziComune;
import eng.tz.pa.api.osa.service.OsaApiService;
import eng.tz.pa.api.osa.web.dto.BaseResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(value = "/api", headers="Accept=*/*", produces="application/json")
public class OsaApiRestController {
	private static final Logger logger = LoggerFactory.getLogger(OsaApiRestController.class.getName());

	@Autowired
	private OsaApiService osaApiService;

	@GetMapping("/comune-servizi-attivi")
	public ResponseEntity<BaseResponse<ComuneServizi>> comuneServizi() throws ParseException {
		BaseResponse<ComuneServizi> result = new BaseResponse<ComuneServizi>();
	 
		String message = null;
		HttpStatus status = null;
		try {
		 
			status = HttpStatus.OK;

			List<ComuneServizi> listQuery = osaApiService.getListComuneServizi();
			if(listQuery==null || listQuery.isEmpty())
				status=HttpStatus.NO_CONTENT;
			if(listQuery==null)
				listQuery=new ArrayList<ComuneServizi>();	
			result.setEsito(status.value());
			result.setDescrizione(status.getReasonPhrase());
			result.setNumeroOggettiRestituiti(listQuery.size());
			result.setNumeroOggettiTotali(listQuery.size());

			result.setPayload(listQuery);

		return new ResponseEntity<BaseResponse<ComuneServizi>>(result, status);

		} catch (Exception e) {
			message = "Errore nell'estensione della sessione utente";
			logger.error(message, e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		 
		}		 
		result.setEsito(status.value());
		result.setDescrizione(status.getReasonPhrase());
		result.setNumeroOggettiRestituiti(0);
		result.setNumeroOggettiTotali(0);

		result.setPayload(new ArrayList<ComuneServizi>());

		return new ResponseEntity<BaseResponse<ComuneServizi>>(result, status);
	}

 
	@GetMapping("/totale-accessi-servizi")
	public ResponseEntity<BaseResponse<TotaleAccessiServizi>> totaleAccessiServizi() throws ParseException {
		BaseResponse<TotaleAccessiServizi> result = new BaseResponse<TotaleAccessiServizi>();
		 
		String message = null;
		HttpStatus status = null;
		try {
			 
			status = HttpStatus.OK;

			List<TotaleAccessiServizi> listQuery = osaApiService.getListTotaleAccessiServizi();
			if(listQuery==null || listQuery.isEmpty())
				status=HttpStatus.NO_CONTENT;
			if(listQuery==null)
				listQuery=new ArrayList<TotaleAccessiServizi>();	
			
			result.setEsito(status.value());
			result.setDescrizione(status.getReasonPhrase());
			result.setNumeroOggettiRestituiti(listQuery.size());
			result.setNumeroOggettiTotali(listQuery.size());

			result.setPayload(listQuery);

		return new ResponseEntity<BaseResponse<TotaleAccessiServizi>>(result, status);

		} catch (Exception e) {
			message = "Errore nell'estensione della sessione utente";
			logger.error(message, e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			 
		}		 
		result.setEsito(status.value());
		result.setDescrizione(status.getReasonPhrase());
		result.setNumeroOggettiRestituiti(0);
		result.setNumeroOggettiTotali(0);

		result.setPayload(new ArrayList<TotaleAccessiServizi>());

		return new ResponseEntity<BaseResponse<TotaleAccessiServizi>>(result, status);
	}
	
 
	@GetMapping("/totale-servizi-comune")
	public ResponseEntity<BaseResponse<TotaleServiziComune>> totaleServiziComune() throws ParseException {
		BaseResponse<TotaleServiziComune> result = new BaseResponse<TotaleServiziComune>();
		 
		String message = null;
		HttpStatus status = null;
		try {
			 
			status = HttpStatus.OK;

			List<TotaleServiziComune> listQuery = osaApiService.getListTotaleServiziComune();
			if(listQuery==null || listQuery.isEmpty())
				status=HttpStatus.NO_CONTENT;
			if(listQuery==null)
				listQuery=new ArrayList<TotaleServiziComune>();
			
			result.setEsito(status.value());
			result.setDescrizione(status.getReasonPhrase());
			result.setNumeroOggettiRestituiti(listQuery.size());
			result.setNumeroOggettiTotali(listQuery.size());

			result.setPayload(listQuery);

		return new ResponseEntity<BaseResponse<TotaleServiziComune>>(result, status);

		} catch (Exception e) {
			message = "Errore nell'estensione della sessione utente";
			logger.error(message, e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			 
		}		 
		result.setEsito(status.value());
		result.setDescrizione(status.getReasonPhrase());
		result.setNumeroOggettiRestituiti(0);
		result.setNumeroOggettiTotali(0);

		result.setPayload(new ArrayList<TotaleServiziComune>());

		return new ResponseEntity<BaseResponse<TotaleServiziComune>>(result, status);
	}
	
	 
	@GetMapping("/totale-all-servizi")
	public ResponseEntity<BaseResponse<TotaleAccessiServizi>> totaleAllServizi() throws ParseException {
		BaseResponse<TotaleAccessiServizi> result = new BaseResponse<TotaleAccessiServizi>();
		 
		String message = null;
		HttpStatus status = null;
		try {
			 
			status = HttpStatus.OK;

			List<TotaleAccessiServizi> listQuery = osaApiService.getListTotaleAllServizi();
			if(listQuery==null || listQuery.isEmpty())
				status=HttpStatus.NO_CONTENT;
			if(listQuery==null)
				listQuery=new ArrayList<TotaleAccessiServizi>();
			
			result.setEsito(status.value());
			result.setDescrizione(status.getReasonPhrase());
			result.setNumeroOggettiRestituiti(listQuery.size());
			result.setNumeroOggettiTotali(listQuery.size());

			result.setPayload(listQuery);

		return new ResponseEntity<BaseResponse<TotaleAccessiServizi>>(result, status);

		} catch (Exception e) {
			message = "Errore nell'estensione della sessione utente";
			logger.error(message, e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			 
		}		 
		result.setEsito(status.value());
		result.setDescrizione(status.getReasonPhrase());
		result.setNumeroOggettiRestituiti(0);
		result.setNumeroOggettiTotali(0);

		result.setPayload(new ArrayList<TotaleAccessiServizi>());

		return new ResponseEntity<BaseResponse<TotaleAccessiServizi>>(result, status);
	}

 
	@GetMapping("/richieste-servizi-utente")
	public ResponseEntity<BaseResponse<RichiesteServiziUtente>> richiesteServiziUtente() throws ParseException {
		BaseResponse<RichiesteServiziUtente> result = new BaseResponse<RichiesteServiziUtente>();
	 
		String message = null;
		HttpStatus status = null;
		try {
			 
			status = HttpStatus.OK;

			List<RichiesteServiziUtente> listQuery = osaApiService.getListRichiesteServiziUtente();
			if(listQuery==null || listQuery.isEmpty())
				status=HttpStatus.NO_CONTENT;
			if(listQuery==null)
				listQuery=new ArrayList<RichiesteServiziUtente>();
			
			result.setEsito(status.value());
			result.setDescrizione(status.getReasonPhrase());
			result.setNumeroOggettiRestituiti(listQuery.size());
			result.setNumeroOggettiTotali(listQuery.size());

			result.setPayload(listQuery);

		return new ResponseEntity<BaseResponse<RichiesteServiziUtente>>(result, status);

		} catch (Exception e) {
			message = "Errore nell'estensione della sessione utente";
			logger.error(message, e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			 
		}		 
		result.setEsito(status.value());
		result.setDescrizione(status.getReasonPhrase());
		result.setNumeroOggettiRestituiti(0);
		result.setNumeroOggettiTotali(0);

		result.setPayload(new ArrayList<RichiesteServiziUtente>());

		return new ResponseEntity<BaseResponse<RichiesteServiziUtente>>(result, status);
	}
	
 
	@GetMapping("/richieste-servizi-azienda")
	public ResponseEntity<BaseResponse<RichiesteServiziAzienda>> richiesteServiziAzienda() throws ParseException {
		BaseResponse<RichiesteServiziAzienda> result = new BaseResponse<RichiesteServiziAzienda>();
		 
		String message = null;
		HttpStatus status = null;
		try {
			 
			status = HttpStatus.OK;

			List<RichiesteServiziAzienda> listQuery = osaApiService.getListRichiesteServiziAzienda();
			if(listQuery==null || listQuery.isEmpty())
				status=HttpStatus.NO_CONTENT;
			if(listQuery==null)
				listQuery=new ArrayList<RichiesteServiziAzienda>();
			
			result.setEsito(status.value());
			result.setDescrizione(status.getReasonPhrase());
			result.setNumeroOggettiRestituiti(listQuery.size());
			result.setNumeroOggettiTotali(listQuery.size());

			result.setPayload(listQuery);

		return new ResponseEntity<BaseResponse<RichiesteServiziAzienda>>(result, status);

		} catch (Exception e) {
			message = "Errore nell'estensione della sessione utente";
			logger.error(message, e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			 
		}		 
		result.setEsito(status.value());
		result.setDescrizione(status.getReasonPhrase());
		result.setNumeroOggettiRestituiti(0);
		result.setNumeroOggettiTotali(0);

		result.setPayload(new ArrayList<RichiesteServiziAzienda>());

		return new ResponseEntity<BaseResponse<RichiesteServiziAzienda>>(result, status);
	}
	
 
	 @GetMapping("/dettaglio-accessi-servizi/{from}/{to}")
	public ResponseEntity<BaseResponse<TotaleAccessiServiziPerData>> listDettaglioAccessiServizi(@PathVariable("from") String from,
			@PathVariable("to") String to, Locale locale) throws ParseException {
		BaseResponse<TotaleAccessiServiziPerData> result = new BaseResponse<TotaleAccessiServiziPerData>();
		 
		String message = null;
		HttpStatus status = null;
		try {
			 
			status = HttpStatus.OK;

			List<TotaleAccessiServiziPerData> listQuery = osaApiService.getRichiesteServiziPeData(from, to);
			if(listQuery==null || listQuery.isEmpty())
				status=HttpStatus.NO_CONTENT;
			if(listQuery==null)
				listQuery=new ArrayList<TotaleAccessiServiziPerData>();
			result.setDescrizione(status.getReasonPhrase());
			result.setEsito(status.value());
			result.setNumeroOggettiRestituiti(listQuery.size());
			result.setNumeroOggettiTotali(listQuery.size());

			result.setPayload(listQuery);

			return new ResponseEntity<BaseResponse<TotaleAccessiServiziPerData>>(result, status);

		} catch (Exception e) {
			message = "Errore nell'estensione della sessione utente";
			logger.error(message, e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			 
		}

//		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//
//		Date from_date = dateFormat.parse(from);
//		Date to_date = dateFormat.parse(to);
//
//		long timeCurrent = System.currentTimeMillis();

		result.setEsito(status.value());
		result.setDescrizione(status.getReasonPhrase());
		result.setNumeroOggettiRestituiti(0);
		result.setNumeroOggettiTotali(0);

		result.setPayload(new ArrayList<TotaleAccessiServiziPerData>());

		return new ResponseEntity<BaseResponse<TotaleAccessiServiziPerData>>(result, status);
	}
	 
	 @GetMapping("/dettaglio-accessi-servizi-azienda")
		public ResponseEntity<BaseResponse<RichiesteServiziPerAzienda>> listDettaglioAccessiServiziAzienda( Locale locale) throws ParseException {
			BaseResponse<RichiesteServiziPerAzienda> result = new BaseResponse<RichiesteServiziPerAzienda>();
			 
			String message = null;
			HttpStatus status = null;
			try {
				 
				status = HttpStatus.OK;

				List<RichiesteServiziPerAzienda> listQuery = osaApiService.getRichiesteServiziPerAzienda(null);
				if(listQuery==null || listQuery.isEmpty())
					status=HttpStatus.NO_CONTENT;
				if(listQuery==null)
					listQuery=new ArrayList<RichiesteServiziPerAzienda>();
				result.setDescrizione(status.getReasonPhrase());
				result.setEsito(status.value());
				result.setNumeroOggettiRestituiti(listQuery.size());
				result.setNumeroOggettiTotali(listQuery.size());

				result.setPayload(listQuery);

				return new ResponseEntity<BaseResponse<RichiesteServiziPerAzienda>>(result, status);

			} catch (Exception e) {
				message = "Errore nell'estensione della sessione utente";
				logger.error(message, e);
				status = HttpStatus.INTERNAL_SERVER_ERROR;
				 
			}

 

			result.setEsito(status.value());
			result.setDescrizione(status.getReasonPhrase());
			result.setNumeroOggettiRestituiti(0);
			result.setNumeroOggettiTotali(0);

			result.setPayload(new ArrayList<RichiesteServiziPerAzienda>());

			return new ResponseEntity<BaseResponse<RichiesteServiziPerAzienda>>(result, status);
		}

	 @GetMapping("/dettaglio-accessi-servizi-azienda/{iduser}")
		public ResponseEntity<BaseResponse<RichiesteServiziPerAzienda>> listDettaglioAccessiServiziAzienda(@PathVariable("iduser") String iduser, Locale locale) throws ParseException {
			BaseResponse<RichiesteServiziPerAzienda> result = new BaseResponse<RichiesteServiziPerAzienda>();
			 
			String message = null;
			HttpStatus status = null;
			try {
				 
				status = HttpStatus.OK;

				List<RichiesteServiziPerAzienda> listQuery = osaApiService.getRichiesteServiziPerAzienda(iduser);
				if(listQuery==null || listQuery.isEmpty())
					status=HttpStatus.NO_CONTENT;
				if(listQuery==null)
					listQuery=new ArrayList<RichiesteServiziPerAzienda>();
				result.setDescrizione(status.getReasonPhrase());
				result.setEsito(status.value());
				result.setNumeroOggettiRestituiti(listQuery.size());
				result.setNumeroOggettiTotali(listQuery.size());

				result.setPayload(listQuery);

				return new ResponseEntity<BaseResponse<RichiesteServiziPerAzienda>>(result, status);

			} catch (Exception e) {
				message = "Errore nell'estensione della sessione utente";
				logger.error(message, e);
				status = HttpStatus.INTERNAL_SERVER_ERROR;
				 
			}

 

			result.setEsito(status.value());
			result.setDescrizione(status.getReasonPhrase());
			result.setNumeroOggettiRestituiti(0);
			result.setNumeroOggettiTotali(0);

			result.setPayload(new ArrayList<RichiesteServiziPerAzienda>());

			return new ResponseEntity<BaseResponse<RichiesteServiziPerAzienda>>(result, status);
		}
	 
	 @GetMapping("/azienda")
		public ResponseEntity<BaseResponse<InfoAzienda>> listAzienda(Locale locale) throws ParseException {
			BaseResponse<InfoAzienda> result = new BaseResponse<InfoAzienda>();
			 
			String message = null;
			HttpStatus status = null;
			try {
				 
				status = HttpStatus.OK;

				List<InfoAzienda> listQuery = osaApiService.getInfoAzienda();
				if(listQuery==null || listQuery.isEmpty())
					status=HttpStatus.NO_CONTENT;
				if(listQuery==null)
					listQuery=new ArrayList<InfoAzienda>();
				result.setDescrizione(status.getReasonPhrase());
				result.setEsito(status.value());
				result.setNumeroOggettiRestituiti(listQuery.size());
				result.setNumeroOggettiTotali(listQuery.size());

				result.setPayload(listQuery);

				return new ResponseEntity<BaseResponse<InfoAzienda>>(result, status);

			} catch (Exception e) {
				message = "Errore nell'estensione della sessione utente";
				logger.error(message, e);
				status = HttpStatus.INTERNAL_SERVER_ERROR;
				 
			}

 

			result.setEsito(status.value());
			result.setDescrizione(status.getReasonPhrase());
			result.setNumeroOggettiRestituiti(0);
			result.setNumeroOggettiTotali(0);

			result.setPayload(new ArrayList<InfoAzienda>());

			return new ResponseEntity<BaseResponse<InfoAzienda>>(result, status);
		}
	 
	 
	 @GetMapping("/cambio-residenza-partenza-arrivi")
		public ResponseEntity<BaseResponse<CambioResidenzaPartenzeArrivi>> listCambioResidenzaPartenzeArrivi(Locale locale) throws ParseException {
			BaseResponse<CambioResidenzaPartenzeArrivi> result = new BaseResponse<CambioResidenzaPartenzeArrivi>();
			 
			String message = null;
			HttpStatus status = null;
			try {
				 
				status = HttpStatus.OK;

				List<CambioResidenzaPartenzeArrivi> listQuery = osaApiService.getCambioResidenzaPartenzeArrivi();
				if(listQuery==null || listQuery.isEmpty())
					status=HttpStatus.NO_CONTENT;
				if(listQuery==null)
					listQuery=new ArrayList<CambioResidenzaPartenzeArrivi>();
				result.setDescrizione(status.getReasonPhrase());
				result.setEsito(status.value());
				result.setNumeroOggettiRestituiti(listQuery.size());
				result.setNumeroOggettiTotali(listQuery.size());

				result.setPayload(listQuery);

				return new ResponseEntity<BaseResponse<CambioResidenzaPartenzeArrivi>>(result, status);

			} catch (Exception e) {
				message = "Errore nell'estensione della sessione utente";
				logger.error(message, e);
				status = HttpStatus.INTERNAL_SERVER_ERROR;
				 
			}

 

			result.setEsito(status.value());
			result.setDescrizione(status.getReasonPhrase());
			result.setNumeroOggettiRestituiti(0);
			result.setNumeroOggettiTotali(0);

			result.setPayload(new ArrayList<CambioResidenzaPartenzeArrivi>());

			return new ResponseEntity<BaseResponse<CambioResidenzaPartenzeArrivi>>(result, status);
		}
	 
	 
	 @GetMapping("/servizi-attribute/{codeOrName}")
		public ResponseEntity<BaseResponse<DwhServizioAttribute>> listDwhServizioAttribute(@PathVariable("codeOrName") String codeOrName, Locale locale) throws ParseException {
			BaseResponse<DwhServizioAttribute> result = new BaseResponse<DwhServizioAttribute>();
			 
			String message = null;
			HttpStatus status = null;
			try {
				 
				status = HttpStatus.OK;

				List<DwhServizioAttribute> listQuery = osaApiService.getDwhServizioAttribute(codeOrName);
				if(listQuery==null || listQuery.isEmpty())
					status=HttpStatus.NO_CONTENT;
				if(listQuery==null)
					listQuery=new ArrayList<DwhServizioAttribute>();
				result.setDescrizione(status.getReasonPhrase());
				result.setEsito(status.value());
				result.setNumeroOggettiRestituiti(listQuery.size());
				result.setNumeroOggettiTotali(listQuery.size());

				result.setPayload(listQuery);

				return new ResponseEntity<BaseResponse<DwhServizioAttribute>>(result, status);

			} catch (Exception e) {
				message = "Errore nell'estensione della sessione utente";
				logger.error(message, e);
				status = HttpStatus.INTERNAL_SERVER_ERROR;
				 
			}



			result.setEsito(status.value());
			result.setDescrizione(status.getReasonPhrase());
			result.setNumeroOggettiRestituiti(0);
			result.setNumeroOggettiTotali(0);

			result.setPayload(new ArrayList<DwhServizioAttribute>());

			return new ResponseEntity<BaseResponse<DwhServizioAttribute>>(result, status);
		}
	 
	    @GetMapping("/servizi-attribute")
		public ResponseEntity<BaseResponse<DwhServizioAttribute>> listDwhServizioAttribute(Locale locale) throws ParseException {
			BaseResponse<DwhServizioAttribute> result = new BaseResponse<DwhServizioAttribute>();
			 
			String message = null;
			HttpStatus status = null;
			try {
				 
				status = HttpStatus.OK;

				List<DwhServizioAttribute> listQuery = osaApiService.getDwhServizioAttribute(null);
				if(listQuery==null || listQuery.isEmpty())
					status=HttpStatus.NO_CONTENT;
				if(listQuery==null)
					listQuery=new ArrayList<DwhServizioAttribute>();
				result.setDescrizione(status.getReasonPhrase());
				result.setEsito(status.value());
				result.setNumeroOggettiRestituiti(listQuery.size());
				result.setNumeroOggettiTotali(listQuery.size());

				result.setPayload(listQuery);

				return new ResponseEntity<BaseResponse<DwhServizioAttribute>>(result, status);

			} catch (Exception e) {
				message = "Errore nell'estensione della sessione utente";
				logger.error(message, e);
				status = HttpStatus.INTERNAL_SERVER_ERROR;
				 
			}



			result.setEsito(status.value());
			result.setDescrizione(status.getReasonPhrase());
			result.setNumeroOggettiRestituiti(0);
			result.setNumeroOggettiTotali(0);

			result.setPayload(new ArrayList<DwhServizioAttribute>());

			return new ResponseEntity<BaseResponse<DwhServizioAttribute>>(result, status);
		}
	 
	 
	 @GetMapping("/servizi-media-eta")
		public ResponseEntity<BaseResponse<EtaMediaServiziRichiesti>> listEtaMediaServiziRichiesti(Locale locale) throws ParseException {
			BaseResponse<EtaMediaServiziRichiesti> result = new BaseResponse<EtaMediaServiziRichiesti>();
			 
			String message = null;
			HttpStatus status = null;
			try {
				 
				status = HttpStatus.OK;

				List<EtaMediaServiziRichiesti> listQuery = osaApiService.getDwhEtaMediaServiziRichiesti();
				if(listQuery==null || listQuery.isEmpty())
					status=HttpStatus.NO_CONTENT;
				if(listQuery==null)
					listQuery=new ArrayList<EtaMediaServiziRichiesti>();
				result.setDescrizione(status.getReasonPhrase());
				result.setEsito(status.value());
				result.setNumeroOggettiRestituiti(listQuery.size());
				result.setNumeroOggettiTotali(listQuery.size());

				result.setPayload(listQuery);

				return new ResponseEntity<BaseResponse<EtaMediaServiziRichiesti>>(result, status);

			} catch (Exception e) {
				message = "Errore nell'estensione della sessione utente";
				logger.error(message, e);
				status = HttpStatus.INTERNAL_SERVER_ERROR;
				 
			}



			result.setEsito(status.value());
			result.setDescrizione(status.getReasonPhrase());
			result.setNumeroOggettiRestituiti(0);
			result.setNumeroOggettiTotali(0);

			result.setPayload(new ArrayList<EtaMediaServiziRichiesti>());

			return new ResponseEntity<BaseResponse<EtaMediaServiziRichiesti>>(result, status);
		}
	 
	 @GetMapping("/servizi-media-tempo")
		public ResponseEntity<BaseResponse<TempoMedioServiziRichiesti>> listTempoMedioServiziRichiesti(Locale locale) throws ParseException {
			BaseResponse<TempoMedioServiziRichiesti> result = new BaseResponse<TempoMedioServiziRichiesti>();
			 
			String message = null;
			HttpStatus status = null;
			try {
				 
				status = HttpStatus.OK;

				List<TempoMedioServiziRichiesti> listQuery = osaApiService.getDwhTempoMedioServiziRichiesti();
				if(listQuery==null || listQuery.isEmpty())
					status=HttpStatus.NO_CONTENT;
				if(listQuery==null)
					listQuery=new ArrayList<TempoMedioServiziRichiesti>();
				result.setDescrizione(status.getReasonPhrase());
				result.setEsito(status.value());
				result.setNumeroOggettiRestituiti(listQuery.size());
				result.setNumeroOggettiTotali(listQuery.size());

				result.setPayload(listQuery);

				return new ResponseEntity<BaseResponse<TempoMedioServiziRichiesti>>(result, status);

			} catch (Exception e) {
				message = "Errore nell'estensione della sessione utente";
				logger.error(message, e);
				status = HttpStatus.INTERNAL_SERVER_ERROR;
				 
			}



			result.setEsito(status.value());
			result.setDescrizione(status.getReasonPhrase());
			result.setNumeroOggettiRestituiti(0);
			result.setNumeroOggettiTotali(0);

			result.setPayload(new ArrayList<TempoMedioServiziRichiesti>());

			return new ResponseEntity<BaseResponse<TempoMedioServiziRichiesti>>(result, status);
		}
	 
	 
	 @GetMapping("/servizi-completi-incompleti")
		public ResponseEntity<BaseResponse<ServiziRichiestiPercCompletati>> listServiziRichiestiPercCompletati(Locale locale) throws ParseException {
			BaseResponse<ServiziRichiestiPercCompletati> result = new BaseResponse<ServiziRichiestiPercCompletati>();
			 
			String message = null;
			HttpStatus status = null;
			try {
				 
				status = HttpStatus.OK;

				List<ServiziRichiestiPercCompletati> listQuery = osaApiService.getDwhServiziRichiestiPercCompletati();
				if(listQuery==null || listQuery.isEmpty())
					status=HttpStatus.NO_CONTENT;
				if(listQuery==null)
					listQuery=new ArrayList<ServiziRichiestiPercCompletati>();
				result.setDescrizione(status.getReasonPhrase());
				result.setEsito(status.value());
				result.setNumeroOggettiRestituiti(listQuery.size());
				result.setNumeroOggettiTotali(listQuery.size());

				result.setPayload(listQuery);

				return new ResponseEntity<BaseResponse<ServiziRichiestiPercCompletati>>(result, status);

			} catch (Exception e) {
				message = "Errore nell'estensione della sessione utente";
				logger.error(message, e);
				status = HttpStatus.INTERNAL_SERVER_ERROR;
				 
			}



			result.setEsito(status.value());
			result.setDescrizione(status.getReasonPhrase());
			result.setNumeroOggettiRestituiti(0);
			result.setNumeroOggettiTotali(0);

			result.setPayload(new ArrayList<ServiziRichiestiPercCompletati>());

			return new ResponseEntity<BaseResponse<ServiziRichiestiPercCompletati>>(result, status);
		}
	 
	 @GetMapping("/servizi-richiesti-geolocalizzazione")
		public ResponseEntity<BaseResponse<ServiziRichiestiGeolocalizzazione>> listServiziRichiestiGeolocalizzazione(Locale locale) throws ParseException {
			BaseResponse<ServiziRichiestiGeolocalizzazione> result = new BaseResponse<ServiziRichiestiGeolocalizzazione>();
			 
			String message = null;
			HttpStatus status = null;
			try {
				 
				status = HttpStatus.OK;

				List<ServiziRichiestiGeolocalizzazione> listQuery = osaApiService.getDwhServiziGeolocalizzazione();
				if(listQuery==null || listQuery.isEmpty())
					status=HttpStatus.NO_CONTENT;
				if(listQuery==null)
					listQuery=new ArrayList<ServiziRichiestiGeolocalizzazione>();
				result.setDescrizione(status.getReasonPhrase());
				result.setEsito(status.value());
				result.setNumeroOggettiRestituiti(listQuery.size());
				result.setNumeroOggettiTotali(listQuery.size());

				result.setPayload(listQuery);

				return new ResponseEntity<BaseResponse<ServiziRichiestiGeolocalizzazione>>(result, status);

			} catch (Exception e) {
				message = "Errore nell'estensione della sessione utente";
				logger.error(message, e);
				status = HttpStatus.INTERNAL_SERVER_ERROR;
				 
			}



			result.setEsito(status.value());
			result.setDescrizione(status.getReasonPhrase());
			result.setNumeroOggettiRestituiti(0);
			result.setNumeroOggettiTotali(0);

			result.setPayload(new ArrayList<ServiziRichiestiGeolocalizzazione>());

			return new ResponseEntity<BaseResponse<ServiziRichiestiGeolocalizzazione>>(result, status);
		}
	 
	   @GetMapping("/servizio")
		public ResponseEntity<BaseResponse<Servizio>> listServizio(Locale locale) throws ParseException {
			BaseResponse<Servizio> result = new BaseResponse<Servizio>();
			 
			String message = null;
			HttpStatus status = null;
			try {
				 
				status = HttpStatus.OK;

				List<Servizio> listQuery = osaApiService.getDwhServizio();
				if(listQuery==null || listQuery.isEmpty())
					status=HttpStatus.NO_CONTENT;
				if(listQuery==null)
					listQuery=new ArrayList<Servizio>();
				result.setDescrizione(status.getReasonPhrase());
				result.setEsito(status.value());
				result.setNumeroOggettiRestituiti(listQuery.size());
				result.setNumeroOggettiTotali(listQuery.size());

				result.setPayload(listQuery);

				return new ResponseEntity<BaseResponse<Servizio>>(result, status);

			} catch (Exception e) {
				message = "Errore nell'estensione della sessione utente";
				logger.error(message, e);
				status = HttpStatus.INTERNAL_SERVER_ERROR;
				 
			}



			result.setEsito(status.value());
			result.setDescrizione(status.getReasonPhrase());
			result.setNumeroOggettiRestituiti(0);
			result.setNumeroOggettiTotali(0);

			result.setPayload(new ArrayList<Servizio>());

			return new ResponseEntity<BaseResponse<Servizio>>(result, status);
		}
	   
	   
	   @GetMapping("/servizi-fascia-eta")
		public ResponseEntity<BaseResponse<ServiziRichiestiPerFasciaDiEta>> listServiziRichiestiPerFasciaDiEta(Locale locale) throws ParseException {
			BaseResponse<ServiziRichiestiPerFasciaDiEta> result = new BaseResponse<ServiziRichiestiPerFasciaDiEta>();
			 
			String message = null;
			HttpStatus status = null;
			try {
				 
				status = HttpStatus.OK;

				List<ServiziRichiestiPerFasciaDiEta> listQuery = osaApiService.getDwhServiziRichiestiPerFasciaDiEta();
				if(listQuery==null || listQuery.isEmpty())
					status=HttpStatus.NO_CONTENT;
				if(listQuery==null)
					listQuery=new ArrayList<ServiziRichiestiPerFasciaDiEta>();
				result.setDescrizione(status.getReasonPhrase());
				result.setEsito(status.value());
				result.setNumeroOggettiRestituiti(listQuery.size());
				result.setNumeroOggettiTotali(listQuery.size());

				result.setPayload(listQuery);

				return new ResponseEntity<BaseResponse<ServiziRichiestiPerFasciaDiEta>>(result, status);

			} catch (Exception e) {
				message = "Errore nell'estensione della sessione utente";
				logger.error(message, e);
				status = HttpStatus.INTERNAL_SERVER_ERROR;
				 
			}



			result.setEsito(status.value());
			result.setDescrizione(status.getReasonPhrase());
			result.setNumeroOggettiRestituiti(0);
			result.setNumeroOggettiTotali(0);

			result.setPayload(new ArrayList<ServiziRichiestiPerFasciaDiEta>());

			return new ResponseEntity<BaseResponse<ServiziRichiestiPerFasciaDiEta>>(result, status);
		}
	 
}
