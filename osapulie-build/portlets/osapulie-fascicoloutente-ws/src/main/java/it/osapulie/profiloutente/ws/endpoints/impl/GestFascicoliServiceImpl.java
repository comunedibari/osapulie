package it.osapulie.profiloutente.ws.endpoints.impl;

import it.osapulie.domain.dto.Fascicolo;
import it.osapulie.domain.fascicoloutente.FascicoloUtente;
import it.osapulie.domain.servizi.Servizio;
import it.osapulie.profiloutente.ws.dto.EsitoDto;
import it.osapulie.profiloutente.ws.dto.FascicoloInsertDto;
import it.osapulie.profiloutente.ws.dto.FascicoloUtenteDto;
import it.osapulie.profiloutente.ws.dto.FascicoloUtenteResponse;
import it.osapulie.profiloutente.ws.dto.RichiestaFascicoloDto;
import it.osapulie.profiloutente.ws.dto.RisultatoDto;
import it.osapulie.profiloutente.ws.endpoints.GestFascicoliService;
import it.osapulie.profiloutente.ws.trasformer.FascicoloInsertTrasformer;
import it.osapulie.profiloutente.ws.trasformer.FascicoloUtenteTrasformer;
import it.osapulie.service.FascicoloUtenteService;
import it.osapulie.service.ServizioService;

import javax.jws.WebService;
import javax.validation.Valid;

import org.apache.cxf.annotations.SchemaValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.liferay.portal.kernel.util.Validator;

@Component
@WebService(endpointInterface = "it.osapulie.profiloutente.ws.endpoints.GestFascicoliService", serviceName = "gestFascicoliService")
@SchemaValidation
public class GestFascicoliServiceImpl extends SpringBeanAutowiringSupport implements GestFascicoliService{
	private final Logger log = LoggerFactory.getLogger(GestFascicoliServiceImpl.class);
	
	@Autowired
	private FascicoloUtenteService fascicoloUtenteService;
	
	@Autowired
	private ServizioService servizioService;
	
	@Value("#{applicationProperties['passwordWS']}")
	private String passwordWS;
	
	private FascicoloUtenteTrasformer fascicoloUtenteTrasformer = new FascicoloUtenteTrasformer(FascicoloUtenteDto.class, FascicoloUtente.class);
	private FascicoloInsertTrasformer fascicoloTrasformer = new FascicoloInsertTrasformer(FascicoloInsertDto.class, Fascicolo.class);
	
	@Override
	public FascicoloUtenteResponse ottieniFascicoloUtente(@Valid RichiestaFascicoloDto richiestaFascicolo){
		log.debug("WS - Ottieni Fascicolo Utente: " + richiestaFascicolo);
		FascicoloUtenteResponse risposta = new FascicoloUtenteResponse();
		
		FascicoloUtente fascicolo = null;
		
		try{
			if(!richiestaFascicolo.getPassword().equals(passwordWS)){
				throw new Exception("Password errata");
			}
			if(richiestaFascicolo.getCodiceFiscale()!=null && !richiestaFascicolo.getCodiceFiscale().isEmpty() && richiestaFascicolo.getPartitaIva()!=null && !richiestaFascicolo.getPartitaIva().isEmpty()){
				EsitoDto esito = new EsitoDto();
				esito.setValore(-1);
				esito.setDescrizione("Inserire soltanto il codice fiscale o la partita iva");
				risposta.setEsitoDto(esito);
			}else if(richiestaFascicolo.getCodiceFiscale()!=null && !richiestaFascicolo.getCodiceFiscale().isEmpty()){
				fascicolo = fascicoloUtenteService.getFascicoloUtenteByCfCittadino(richiestaFascicolo.getCodiceFiscale());
			}else if(richiestaFascicolo.getPartitaIva()!=null && !richiestaFascicolo.getPartitaIva().isEmpty()){
				fascicolo = fascicoloUtenteService.getFascicoloUtenteByPIvaAzienda(richiestaFascicolo.getPartitaIva());
			}
		
			if(fascicolo==null && risposta.getEsitoDto()==null){
				EsitoDto esito = new EsitoDto();
				esito.setValore(-1);
				esito.setDescrizione("Fascicolo Utente non trovato");
				risposta.setEsitoDto(esito);
			}else if(fascicolo!=null){
				EsitoDto esito = new EsitoDto();
				esito.setDescrizione("read success");
				esito.setValore(1);
				FascicoloUtenteDto fascicoloDto = fascicoloUtenteTrasformer.toDto(fascicolo);
				RisultatoDto risultato = new RisultatoDto();
				risultato.setFascicoloUtenteDto(fascicoloDto);
				risposta.setRisultatoDto(risultato);
				risposta.setEsitoDto(esito);
			}
		}catch(Exception ex){
			log.error("ottieniFascicoloUtente :: Exception with message " + ex.getMessage());
			EsitoDto esito = new EsitoDto();
			esito.setValore(-1);
			esito.setDescrizione("Errore: " + ex.getMessage());
			risposta.setEsitoDto(esito);
		}
		
		return risposta;
	}

	@Override
	public FascicoloUtenteResponse inserisciFascicolo(@Valid FascicoloInsertDto fascicoloDto) {
		log.debug("WS - inserisciFascicolo: " + fascicoloDto);
		FascicoloUtenteResponse risposta = new FascicoloUtenteResponse();
		try{
			if(!fascicoloDto.getPassword().equals(passwordWS)){
				throw new Exception("Password errata");
			}
			if(fascicoloDto.getPartitaIvaServizio()!=null && !fascicoloDto.getPartitaIvaServizio().isEmpty() && fascicoloDto.getCodiceFiscaleServizio()!=null && !fascicoloDto.getCodiceFiscaleServizio().isEmpty()){
				EsitoDto esito = new EsitoDto();
				esito.setValore(-1);
				esito.setDescrizione("Inserire soltanto il codice fiscale o la partita iva");
				risposta.setEsitoDto(esito);
			}else{
				
					if(fascicoloDto.getCodiceServizio()==null || fascicoloDto.getCodiceServizio().isEmpty()){
						throw new Exception("Occorre inserire il codice servizio");
					}
					Servizio servizio = servizioService.getServizioByCodiceServizio(fascicoloDto.getCodiceServizio());
					if(servizio==null){
						throw new Exception("Servizio con codice " + fascicoloDto.getCodiceServizio() + " non trovato");
					}
					Fascicolo fascicolo = fascicoloTrasformer.fromDto(fascicoloDto);
					String descirzioneServizio = fascicoloDto.getDescrizioneServizio();
					if(Validator.isNotNull(descirzioneServizio) && !descirzioneServizio.isEmpty()){
						fascicolo.setServizio(descirzioneServizio);
					}else{
						fascicolo.setServizio(servizio.getNomeServizio());
					}
					FascicoloUtente fascicoloAggiornato = fascicoloUtenteService.saveNuovaRichiesta(fascicolo);
					EsitoDto esito = new EsitoDto();
					esito.setDescrizione("insert success");
					esito.setValore(1);
					FascicoloUtenteDto fascicoloDtoNew = fascicoloUtenteTrasformer.toDto(fascicoloAggiornato);
					RisultatoDto risultato = new RisultatoDto();
					risultato.setFascicoloUtenteDto(fascicoloDtoNew);
					risposta.setRisultatoDto(risultato);
					risposta.setEsitoDto(esito);
			}
		}catch(Exception ex){
			log.error("inserisciFascicolo :: Exception with message " + ex.getMessage());
			EsitoDto esito = new EsitoDto();
			esito.setValore(-1);
			esito.setDescrizione("Errore: " + ex.getMessage());
			risposta.setEsitoDto(esito);
		}
		return risposta;
	}
}