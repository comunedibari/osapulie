package it.osapulie.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.google.gson.Gson;

import eng.tz.la.core.AuditManager;
import eng.tz.la.core.Export;
import eng.tz.la.model.OperationExportFile;
import it.osapulie.domain.Audit;
import it.osapulie.domain.Azienda;
import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.Delega;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.ProfiloUtenteCittadinoAzienda;
import it.osapulie.domain.dto.Fascicolo;
import it.osapulie.domain.fascicoloutente.FascicoloUtente;
import it.osapulie.domain.fascicoloutente.RichiestaServizio;
import it.osapulie.domain.servizi.Servizio;
import it.osapulie.infrastructure.impl.DateUtils;
import it.osapulie.persistence.AuditRepository;
import it.osapulie.persistence.AziendaRepository;
import it.osapulie.persistence.ComuneISARepository;
import it.osapulie.persistence.DelegaRepository;
import it.osapulie.persistence.FascicoloUtenteRepository;
import it.osapulie.persistence.ProfiloUtenteCittadinoRepository;
import it.osapulie.persistence.RichiestaServizioRepository;
import it.osapulie.persistence.ServizioRepository;
import it.osapulie.service.FascicoloUtenteService;
import it.osapulie.service.ServiceLayerException;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.util.audit.AuditConfiguration;
import it.osapulie.util.audit.AuditCustomMeta;
import it.osapulie.util.audit.AuditCustomSettyng;

/**
 * @author Maria Michela Birtolo
 * @author Gianluca Pindinelli
 *
 */
@Service("fascicoloUtenteService")
@Transactional
public class FascicoloUtenteServiceImpl implements FascicoloUtenteService {

	@Inject
	private FascicoloUtenteRepository repositoryFascicolo;

	@Inject
	private ProfiloUtenteCittadinoRepository repositoryCittadino;

	@Inject
	private ServizioRepository servizioRepository;

	@Inject
	private ComuneISARepository comuneISARepository;

	@Inject
	private RichiestaServizioRepository richiestaServizioRepository;

	@Inject
	private AziendaRepository aziendaRepository;

	@Inject
	private DelegaRepository delegaRepository;

	@Inject
	private AuditRepository auditRepository;
	
	@Override
	public FascicoloUtente saveNuovaRichiesta(Fascicolo fascicolo) {

		UserPreferences userPreferences = fascicolo.getUserPreferences();
		Assert.notNull(userPreferences, "userPreferences must be not NULL");
		Assert.notNull(fascicolo.getCodiceServizio(), "codiceServizio must be not NULL");
		
		AuditManager auditManager= AuditConfiguration.configure().audit();//AuditManager.audit(AuditCustomSettyng.get()).setMetaActor(AuditCustomMeta.class);
		auditManager.addInfo("EVENT","FASCICOLO SALVA NUOVA RICHIESTA");
		
		auditManager.addInfo("RICHIESTA SERVIZIO",fascicolo.getServizio()); 
		auditManager.addMetaField("UserPreferences",userPreferences!=null?userPreferences:"null");
		
		// Distinguo se è una richiesta fatta da un cittadino o da azienda
		Azienda azienda = null;
		ProfiloUtenteCittadino profiloUtenteCittadino = null;

		FascicoloUtente fascicoloUtente = null;
		if (userPreferences.getPartitaIvaServizio() != null && !userPreferences.getPartitaIvaServizio().isEmpty()) {
			azienda = aziendaRepository.findByPartitaIva(userPreferences.getPartitaIvaServizio());
			fascicoloUtente = repositoryFascicolo.findByAzienda(azienda);
			
			auditManager.addMetaField("Azienda",azienda!=null?azienda:"null");
		
		}
		else {
			profiloUtenteCittadino = repositoryCittadino.findByCodiceFiscale(userPreferences.getCodiceFiscaleServizio());
			fascicoloUtente = repositoryFascicolo.findByCittadino(profiloUtenteCittadino);
		
			auditManager.addMetaField("ProfiloUtenteCittadino",profiloUtenteCittadino!=null?profiloUtenteCittadino:"null");
		
		}

		List<RichiestaServizio> richieste = null;
		if (fascicoloUtente == null) {
			fascicoloUtente = new FascicoloUtente();

			// Distinguo se è una richiesta fatta da un cittadino o da azienda
			if (azienda != null) {
				fascicoloUtente.setAzienda(azienda);
			
				auditManager.addMetaField("Azienda",azienda!=null?azienda:"null");
			
			}
			else if(profiloUtenteCittadino!=null){
				fascicoloUtente.setCittadino(profiloUtenteCittadino);
			
				auditManager.addMetaField("ProfiloUtenteCittadino",profiloUtenteCittadino);
			
			}else{
				Assert.notNull(profiloUtenteCittadino, "partitaIva or codicefiscale not found");
			}

			richieste = new ArrayList<RichiestaServizio>();
		}
		else {
			richieste = fascicoloUtente.getRichieste();
		}

		RichiestaServizio richiestaServizio = new RichiestaServizio();
		richiestaServizio.setDataRichiesta(DateUtils.getOggi());
		richiestaServizio.setNomeServizio(fascicolo.getServizio());
		richiestaServizio.setFascicolo(fascicoloUtente);
		
		auditManager.addInfo("NOME SERVIZIO",fascicolo.getServizio());
		auditManager.addInfo("CODICE SERVIZIO",fascicolo.getCodiceServizio());
		auditManager.addOperazioneRichiesta("NOME SERVIZIO ="+fascicolo.getServizio()+ "CODICE SERVIZIO ="+fascicolo.getCodiceServizio());
		// Caricamento comune associato
		ComuneISA comuneErogatore = comuneISARepository.findByCodiceIstat(userPreferences.getCodiceIstatComune());
		richiestaServizio.setComuneErogatore(comuneErogatore);
	
		auditManager.addInfo("COMUNE EROGANTE",comuneErogatore.getNome()+"  c.istat-> "+comuneErogatore.getCodiceIstat());
		
		// Delegato
		if (userPreferences.getIdDelega() != null) {
			Delega delega = delegaRepository.findOne(userPreferences.getIdDelega());
			if (delega != null && delega.isAttiva()) {
				ProfiloUtenteCittadinoAzienda delegato = new ProfiloUtenteCittadinoAzienda();
				delegato.setAzienda(delega.getDelegato());
				delegato.setProfiloUtenteCittadino(fascicolo.getIdProfiloUtente());
				richiestaServizio.setDelegato(delegato);
				
				auditManager.addImprontaDelega(delega.getChecksum());
				auditManager.addMetaField("Delega",delega); 
			
			}
		}

		// Set servizio
		Servizio servizio = servizioRepository.findByCodiceServizio(fascicolo.getCodiceServizio());
		richiestaServizio.setServizio(servizio);

		richiestaServizio.setChecksum(fascicolo.getChecksum());
		richiestaServizio.setRicercabileDaComune(fascicolo.isRicercabileDaComune());
		richiestaServizio.setNumeroProtocollo(fascicolo.getNumeroProtocollo());
		
		auditManager.addInfo("Checksum",fascicolo.getChecksum());
		
		if (fascicolo.getInfoAggiuntiveMap().isEmpty()) {
			richiestaServizio.setInfoAggiuntive(fascicolo.getInfoAggiuntive());
			
			auditManager.addInfo("Info Aggiuntive",fascicolo.getInfoAggiuntive());
		
		}
		else {
			Gson gson = new Gson();
			String infoAggiuntiveJson = gson.toJson(fascicolo.getInfoAggiuntiveMap());
			richiestaServizio.setInfoAggiuntive(infoAggiuntiveJson);
		
			auditManager.addInfo("Info Aggiuntive",infoAggiuntiveJson);
		
		}

		richieste.add(richiestaServizio);
		fascicoloUtente.setRichieste(richieste);
		
		FascicoloUtente fascUtente=repositoryFascicolo.save(fascicoloUtente);
		auditManager.addUuidOperazione(FascicoloUtenteServiceImpl.class.getName()+" method:saveNuovaRichiesta");
		if(fascUtente!=null && !fascUtente.isNew()) {
			auditManager.addFineOperazione()
			.addOperazioneRichiesta("NOME SERVIZIO ="+servizio.getNomeServizio()+ "CODICE SERVIZIO ="+servizio.getCodiceServizio());
			auditManager.addEsitoSuccess();//.addInfo("ESITO","OPERAZIONE ESEGUITA CON SUCCESSO");	
		
		}else {
			auditManager.addFineOperazione().addOperazioneRichiesta("NOME SERVIZIO ="+servizio.getNomeServizio()+ "CODICE SERVIZIO ="+servizio.getCodiceServizio());
		 
			auditManager.addEsitoError();
			 
		
		}
		
		try {
			auditManager.build().exportUser(false, new Export() {
				
				@Override
				public void call(OperationExportFile op) throws IOException {
			
					List<Audit> ltsAudit= auditRepository.findByFileName(op.getManager().getCodFileName());
					if(ltsAudit==null || ltsAudit!=null && ltsAudit.size()<1) {
						Date dateCreazione=new Date();
						Audit a= new Audit();
						a.setUserCod(op.getManager().getKeyUser());
						a.setGiornoMeseAnno(op.getManager().getFolderDay());
						a.setFileName(op.getManager().getCodFileName());
						a.setPathFilesystem(op.getManager().getPath_Log_User());
						a.setChecksum("0");
						a.setDataCreazione(dateCreazione);
						a.setDataUltimaModifica(dateCreazione);
						a.setCodiceRegistro("0");
						a.setStato("0");
						a.setCons("0"); 
						auditRepository.save(a);
					}
					
				}
			});
		} catch (UnsupportedEncodingException e) {
		
		} catch (IOException e) {
		
		}
 
		
		return fascUtente;
		
		
		

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.service.FascicoloUtenteService#getRichiestaServizioFascicolUtente(it.osapulie
	 * .domain.ProfiloUtenteCittadino, java.lang.String)
	 */
	@Override
	public RichiestaServizio getRichiestaServizioFascicolUtente(ProfiloUtenteCittadino profiloUtenteCittadino, String infoAggiuntive) {
		return richiestaServizioRepository.findByFascicoloAndInfoAggiuntiveLike(profiloUtenteCittadino.getFascicoloUtente(), "%" + infoAggiuntive + "%");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.FascicoloUtenteService#updateRichiestaServizio(it.osapulie.domain.
	 * fascicoloutente.RichiestaServizio)
	 */
	@Override
	public RichiestaServizio updateRichiestaServizio(RichiestaServizio richiestaServizio) {
		return richiestaServizioRepository.save(richiestaServizio);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.FascicoloUtenteService#countAllRichiesteServizioFascicolo()
	 */
	@Override
	public long countAllRichiesteServizioFascicolo() throws ServiceLayerException {
		return richiestaServizioRepository.count();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.service.FascicoloUtenteService#countAllRichiesteServizioFascicoloByServizio(long)
	 */
	@Override
	public long countAllRichiesteServizioFascicoloByServizio(long idServizio) throws ServiceLayerException {
		return richiestaServizioRepository.countByServizio(idServizio);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.service.FascicoloUtenteService#countAllRichiesteServizioFascicoloByLikeServizio(
	 * java.lang.String)
	 */
	@Override
	public long countAllRichiesteServizioFascicoloByLikeServizio(String nomeServizio) throws ServiceLayerException {
		return richiestaServizioRepository.countByLikeNomeServizio("%" + nomeServizio + "%");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.service.FascicoloUtenteService#getFascicoloUtenteByCfCittadino(java.lang.String)
	 */
	@Override
	public FascicoloUtente getFascicoloUtenteByCfCittadino(String codiceFiscale) throws ServiceLayerException {
		ProfiloUtenteCittadino cittadino = repositoryCittadino.findByCodiceFiscale(codiceFiscale);
		return repositoryFascicolo.findByCittadino(cittadino);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.service.FascicoloUtenteService#getFascicoloUtenteByPIvaAzienda(java.lang.String)
	 */
	@Override
	public FascicoloUtente getFascicoloUtenteByPIvaAzienda(String partitaIva) throws ServiceLayerException {
		Azienda azienda = aziendaRepository.findByPartitaIva(partitaIva);
		return repositoryFascicolo.findByAzienda(azienda);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.FascicoloUtenteService#getFascicoloUtenteById(java.lang.Long)
	 */
	@Override
	public FascicoloUtente getFascicoloUtenteById(Long idFascicolo) throws ServiceLayerException {
		return repositoryFascicolo.findOne(idFascicolo);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.service.FascicoloUtenteService#getRichiesteServizioByCfCittadinoAndServizio(java.
	 * lang.String, java.lang.String, java.util.Date, java.util.Date)
	 */
	@Override
	public List<RichiestaServizio> getRichiesteServizioByCfCittadinoAndServizio(String codiceFiscale, String codiceServizio, Date from, Date to) throws ServiceLayerException {
		return richiestaServizioRepository.findByCodiceFiscaleAndServizio(codiceFiscale, codiceServizio, from, to);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.service.FascicoloUtenteService#getRichiesteServizioByCfCittadinoAndServizio(java.
	 * lang.String, java.lang.String, java.lang.String, java.util.Date, java.util.Date)
	 */
	@Override
	public List<RichiestaServizio> getRichiesteServizioByCfCittadinoAndServizio(String codiceFiscale, String codiceServizio, String nomeServizio, Date from, Date to) throws ServiceLayerException {
		return richiestaServizioRepository.findByCodiceFiscaleAndServizio(codiceFiscale, codiceServizio, nomeServizio, from, to);
	}

}
