/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.service.impl;

import java.io.Serializable;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import it.osapulie.domain.Azienda;
import it.osapulie.domain.Backup;
import it.osapulie.domain.Bozza;
import it.osapulie.domain.BozzaDocumenti;
import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.Delega;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.ProfiloUtenteCittadinoAzienda;
import it.osapulie.domain.json.segnalazione.SegnalazioneCustom;
import it.osapulie.enumeration.TipoAzienda;
import it.osapulie.service.AziendaService;
import it.osapulie.service.BackupService;
import it.osapulie.service.BozzaDocumentiService;
import it.osapulie.service.BozzaService;
import it.osapulie.service.CommonService;
import it.osapulie.service.ComuneISAService;
import it.osapulie.service.DelegaService;
import it.osapulie.service.ProfiloUtenteService;
import it.osapulie.service.ServizioService;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.web.portlet.util.PortletConstants;
import it.osapulie.web.portlet.util.PortletHelper;
import it.osapulie.web.portlet.util.RoleConstants;

/**
 * Servizi comuni per le portlet del portale.
 *
 * @author Gianluca Pindinelli
 *
 */
@Service("commonService")
public class CommonServiceImpl implements CommonService {

	private static final String SEGNALAZIONI_PORTLET_ID = "osapuliesegnalazione_WAR_osapulieservizicomuneportlet";
	private static final String GESTIONE_UTENTI_PORTLET_ID = "osapuliegestioneutenti_WAR_osapulieservizicomuneportlet";

	protected Logger log = LoggerFactory.getLogger(CommonServiceImpl.class.getName());

	@Inject
	private ComuneISAService comuneISAService;

	@Inject
	private ProfiloUtenteService profiloUtenteService;

	@Inject
	private DelegaService delegaService;

	@Inject
	private PortletHelper helper;

	@Inject
	private AziendaService aziendaService;

	@Inject
	private BozzaService bozzaService;

	@Inject
	private BozzaDocumentiService bozzaDocumentiService;

	@Inject
	private ServizioService servizioService;

	@Inject
	private BackupService backupService;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.service.CommonService#getCurrentProfiloUtenteCittadino(it.osapulie.shared.service
	 * .UserPreferences)
	 */
	@Override
	public ProfiloUtenteCittadino getCurrentProfiloUtenteCittadino(UserPreferences userPreferences) {

		String codiceFiscale = userPreferences.getCodiceFiscaleServizio();

		ProfiloUtenteCittadino profiloUtenteCittadinoByCf = null;

		Long idDelega = userPreferences.getIdDelega();
		if (idDelega != null) {
			Delega delegaByPk = delegaService.getDelegaByPk(idDelega);
			if (delegaByPk != null && delegaByPk.isAttiva()) {
				profiloUtenteCittadinoByCf = delegaByPk.getDelegante();
			}
		}
		else {
			profiloUtenteCittadinoByCf = profiloUtenteService.getProfiloUtenteCittadinoByCf(codiceFiscale);
		}

		return profiloUtenteCittadinoByCf;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.CommonService#removeReferenteCAFRole(long)
	 */
	@Override
	public void removeResponsabileCAFRole(long idProfiloUtenteCittadino) {
		ProfiloUtenteCittadino profiloUtenteCittadino = profiloUtenteService.getProfiloUtenteCittadinoById(idProfiloUtenteCittadino);
		List<Azienda> aziende = profiloUtenteCittadino.getAziende();
		int size = 0;
		Iterator<Azienda> iterAz = aziende.iterator();
		
		//Dato che ora ci sono due tipi di Aziende: CAF ed AZIENDA per role RESPONSABILE_CAF, bisogna rimuoverlo solo se non esistono altri CAF associati
		if(aziende != null){
			while (size <= 1 && iterAz.hasNext()){
				if(iterAz.next().getTipo().toString().equalsIgnoreCase(TipoAzienda.CAF.toString()))
					size++;
			}
		}
		if (aziende != null && size <= 1) {
			helper.removeRoleUser(profiloUtenteCittadino.getCodiceFiscale(), RoleConstants.RESPONSABILE_CAF);
		}
	}
	
	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.CommonService#removeResponsabileAZIENDARole(long)
	 */
	@Override
	public void removeResponsabileAZIENDARole(long idProfiloUtenteCittadino) {
		ProfiloUtenteCittadino profiloUtenteCittadino = profiloUtenteService.getProfiloUtenteCittadinoById(idProfiloUtenteCittadino);
		List<Azienda> aziende = profiloUtenteCittadino.getAziende();
		int size = 0;
		Iterator<Azienda> iterAz = aziende.iterator();
		
		//Dato che ora ci sono due tipi di Aziende: CAF ed AZIENDA per role RESPONSABILE_AZIENDA, bisogna rimuoverlo solo se non esistono altre AZIENDE associate
		if(aziende != null){
			while (size <= 1 && iterAz.hasNext()){
				if(iterAz.next().getTipo().toString().equalsIgnoreCase(TipoAzienda.AZIENDA.toString()))
					size++;
			}
		}
		if (aziende != null && size <= 1) {
			helper.removeRoleUser(profiloUtenteCittadino.getCodiceFiscale(), RoleConstants.RESPONSABILE_AZIENDA);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.CommonService#removeOperatoreCAFRole(long, long)
	 */
	@Override
	public void removeOperatoreCAFRole(long idAzienda, long idProfiloUtenteCittadino) {

		ProfiloUtenteCittadino profiloUtenteCittadino = profiloUtenteService.getProfiloUtenteCittadinoById(idProfiloUtenteCittadino);
		List<ProfiloUtenteCittadinoAzienda> profiliUtenteCittadinoAziende = profiloUtenteCittadino.getProfiliUtenteCittadinoAziende();
		if (profiliUtenteCittadinoAziende != null && profiliUtenteCittadinoAziende.size() <= 1) {
			Azienda azienda = aziendaService.getAziendaByPk(idAzienda);
			if (azienda.getTipo().equals(TipoAzienda.CAF.toString())) {
				// Eliminazione ruolo Operatore CAF
				helper.removeRoleUser(profiloUtenteCittadino.getCodiceFiscale(), RoleConstants.OPERATORE_CAF);
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.CommonService#removeOperatoreAZIENDARole(long, long)
	 */
	@Override
	public void removeOperatoreAZIENDARole(long idAzienda, long idProfiloUtenteCittadino) {
		
		ProfiloUtenteCittadino profiloUtenteCittadino = profiloUtenteService.getProfiloUtenteCittadinoById(idProfiloUtenteCittadino);
		List<ProfiloUtenteCittadinoAzienda> profiliUtenteCittadinoAziende = profiloUtenteCittadino.getProfiliUtenteCittadinoAziende();
		if (profiliUtenteCittadinoAziende != null && profiliUtenteCittadinoAziende.size() <= 1) {
			Azienda azienda = aziendaService.getAziendaByPk(idAzienda);
			if (azienda.getTipo().equals(TipoAzienda.AZIENDA.toString())) {
				// Eliminazione ruolo Operatore CAF
				helper.removeRoleUser(profiloUtenteCittadino.getCodiceFiscale(), RoleConstants.OPERATORE_AZIENDA);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.CommonService#saveBozza(java.io.Serializable,
	 * it.osapulie.shared.service.UserPreferences, long)
	 */
	@Override
	public void saveBozzaDocumenti(Serializable serializable, UserPreferences userPreferences, long idServizio, BozzaDocumenti bozzaDocumenti) {

		saveBozza(serializable, userPreferences, idServizio);
		bozzaDocumenti.setBozza(getBozza(userPreferences, idServizio));
		bozzaDocumentiService.saveBozzaDocumenti(bozzaDocumenti);
		
	}
	
	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.CommonService#saveBozza(java.io.Serializable,
	 * it.osapulie.shared.service.UserPreferences, long)
	 */
	@Override
	public void saveBozza(Serializable serializable, UserPreferences userPreferences, long idServizio) {

		Bozza bozza = getBozza(userPreferences, idServizio);
		// Insert/Update
		if (bozza == null) {
			bozza = new Bozza();

			// Caricamento ComuneISA
			Long idComuneIsa = userPreferences.getIdComuneIsa();
			ComuneISA comuneISA = comuneISAService.getComuneByPk(idComuneIsa);
			bozza.setComuneISA(comuneISA);

			// Caricamento cittadino/azienda
			String codiceFiscaleServizio = userPreferences.getCodiceFiscaleServizio();
			String partitaIvaServizio = userPreferences.getPartitaIvaServizio();

			// Profilo Utente
			if (codiceFiscaleServizio != null) {
				ProfiloUtenteCittadino profiloUtenteCittadinoByCf = profiloUtenteService.getProfiloUtenteCittadinoByCf(codiceFiscaleServizio);
				bozza.setProfiloUtenteCittadino(profiloUtenteCittadinoByCf);
			}
			else if (partitaIvaServizio != null) {
				Azienda aziendaByPiva = aziendaService.getAziendaByPiva(partitaIvaServizio);
				bozza.setAzienda(aziendaByPiva);
			}

			Long idDelega = userPreferences.getIdDelega();
			if (idDelega != null) {
				Delega delegaByPk = delegaService.getDelegaByPk(idDelega);
				if (delegaByPk != null) {
					bozza.setProfiloUtenteCittadino(delegaByPk.getDelegante());
				}
			}
		}

		Gson gson = new Gson();
		String json = gson.toJson(serializable);
		bozza.setContenuto(json);

		// Servizio
		it.osapulie.domain.servizi.Servizio servizioById = servizioService.getServizioById(idServizio);
		bozza.setServizio(servizioById);

		bozzaService.saveBozza(bozza);
	}
	
	public List<BozzaDocumenti> getBozzaDocumenti(UserPreferences userPreferences, long idServizio) {

		List<BozzaDocumenti> result = null;
		
		Bozza bozza = getBozza(userPreferences, idServizio);
		result = bozzaDocumentiService.findByIdBozza(bozza.getId());
		
		return result;
	}

	
	
	/**
	 *
	 * @param idServizio
	 * @param userPreferences
	 * @return
	 */
	private Bozza getBozza(UserPreferences userPreferences, long idServizio) {

		String codiceFiscaleServizio = userPreferences.getCodiceFiscaleServizio();
		String partitaIvaServizio = userPreferences.getPartitaIvaServizio();
		Long idComuneIsa = userPreferences.getIdComuneIsa();
		Long idDelega = userPreferences.getIdDelega();

		Bozza bozza = null;

		// Delega
		if (idDelega != null) {
			Delega delegaByPk = delegaService.getDelegaByPk(idDelega);
			ProfiloUtenteCittadino delegante = delegaByPk.getDelegante();
			bozza = bozzaService.getBozzaCittadinoByComuneAndServizio(delegante.getId(), idComuneIsa, idServizio);
		}
		// Cittadino/Azienda
		else {
			if (codiceFiscaleServizio != null) {
				ProfiloUtenteCittadino profiloUtenteCittadinoByCf = profiloUtenteService.getProfiloUtenteCittadinoByCf(codiceFiscaleServizio);
				bozza = bozzaService.getBozzaCittadinoByComuneAndServizio(profiloUtenteCittadinoByCf.getId(), idComuneIsa, idServizio);
			}
			else if (partitaIvaServizio != null) {
				Azienda aziendaByPiva = aziendaService.getAziendaByPiva(partitaIvaServizio);
				bozza = bozzaService.getBozzaAziendaByComuneAndServizio(aziendaByPiva.getId(), idComuneIsa, idServizio);
			}
		}
		return bozza;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.service.CommonService#deleteBozza(it.osapulie.shared.service.UserPreferences,
	 * long)
	 */
	@Override
	public void deleteBozza(UserPreferences userPreferences, long idServizio) {
		Bozza bozza = getBozza(userPreferences, idServizio);
		if (bozza != null) {
			bozzaService.deleteBozza(bozza.getId());
		}
	}
	
	
	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.service.CommonService#deleteBozzaDocumenti(it.osapulie.shared.service.UserPreferences,
	 * long)
	 */
	@Override
	public void deleteBozzaDocumenti(UserPreferences userPreferences, long idServizio) {
		List<BozzaDocumenti> bozzaDocumenti = getBozzaDocumenti(userPreferences,idServizio);
		for (BozzaDocumenti bozzaDoc : bozzaDocumenti)
		if (bozzaDocumenti != null) {
			bozzaDocumentiService.deleteBozzaDocumenti(bozzaDoc.getId());
		}
	}
	

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.CommonService#getBozza(it.osapulie.shared.service.UserPreferences,
	 * long, java.lang.Class)
	 */
	@Override
	public <T> T getBozza(UserPreferences userPreferences, long idServizio, Class<T> classType) {

		T result = null;

		Bozza bozza = getBozza(userPreferences, idServizio);

		if (bozza != null) {
			String contenuto = bozza.getContenuto();
			Gson gson = new Gson();
			result = gson.fromJson(contenuto, classType);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.CommonService#getGestioneUtentiUrl(javax.portlet.PortletRequest,
	 * java.lang.String, java.util.Map, java.lang.String)
	 */
	@Override
	public String getGestioneUtentiUrl(PortletRequest request, String codiceFiscale, Map<String, String> sourceParameters, String sourceLifecycle) {

		String destinationPortletUrl = null;
		try {
			String encodedReturnUrl = null;

			if (sourceParameters == null) {
				sourceParameters = new HashMap<String, String>();
			}

			sourceParameters.put(PortletConstants.GESTIONE_UTENTI_CODICE_FISCALE_REQUEST_PARAMETER, codiceFiscale);

			String currentPortletUrl = helper.createPortletURL(request, sourceParameters, null, sourceLifecycle);
			encodedReturnUrl = URLEncoder.encode(currentPortletUrl, "UTF-8");

			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put(PortletConstants.GESTIONE_UTENTI_CODICE_FISCALE_REQUEST_PARAMETER, codiceFiscale);
			if (encodedReturnUrl != null) {
				parameters.put(PortletConstants.GESTIONE_UTENTI_RETURN_URL_REQUEST_PARAMETER, encodedReturnUrl);
			}

			destinationPortletUrl = helper.createPortletURL(request, parameters, GESTIONE_UTENTI_PORTLET_ID, null);
		}
		catch (Exception e) {
			log.error("getGstioneUtentiUrl :: " + e.getMessage(), e);
		}
		return destinationPortletUrl;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.service.CommonService#getInvioSegnalazioneCustomUrl(javax.portlet.PortletRequest,
	 * java.util.Map, java.lang.String, java.util.Map, java.lang.String)
	 */
	@Override
	public String getInvioSegnalazioneCustomUrl(PortletRequest request, Map<String, String> successReturnRequestParameters, String successReturnLifecycle,
			Map<String, String> cancelReturnRequestParameters, String cancelReturnLifecycle) {

		String destinationPortletUrl = null;

		try {

			String successReturnEndocedUrl = null;
			String cancelReturnEndocedUrl = null;

			if (successReturnRequestParameters == null) {
				successReturnRequestParameters = new HashMap<String, String>();
			}

			// Generazione URL di success return
			String successReturnPortletUrl = helper.createPortletURL(request, successReturnRequestParameters, null, successReturnLifecycle);
			successReturnEndocedUrl = URLEncoder.encode(successReturnPortletUrl, "UTF-8");

			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("action", "segnalazioneCustom");
			if (successReturnEndocedUrl != null) {
				parameters.put(PortletConstants.SEGNALAZIONI_SUCCESS_RETURN_URL_REQUEST_PARAMETER, successReturnEndocedUrl);
			}

			// Generazione URL di cancellazione operazione
			if (cancelReturnRequestParameters == null) {
				cancelReturnRequestParameters = new HashMap<String, String>();
			}

			String cancelReturnPortletUrl = helper.createPortletURL(request, cancelReturnRequestParameters, null, cancelReturnLifecycle);
			cancelReturnEndocedUrl = URLEncoder.encode(cancelReturnPortletUrl, "UTF-8");

			if (cancelReturnEndocedUrl != null) {
				parameters.put(PortletConstants.SEGNALAZIONI_CANCEL_RETURN_URL_REQUEST_PARAMETER, cancelReturnEndocedUrl);
			}

			destinationPortletUrl = helper.createPortletURL(request, parameters, SEGNALAZIONI_PORTLET_ID, null);
		}
		catch (Exception e) {
			log.error("getInvioSegnalazioneCustomUrl :: " + e.getMessage(), e);
		}
		return destinationPortletUrl;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.CommonService#addSegnalazioneCustomToSession(javax.portlet.
	 * PortletSession, it.osapulie.domain.json.segnalazione.SegnalazioneCustom)
	 */
	@Override
	public void addSegnalazioneCustomToSession(PortletSession session, SegnalazioneCustom segnalazioneCustom) {
		Gson gson = new Gson();
		String json = gson.toJson(segnalazioneCustom);
		session.setAttribute(PortletConstants.SEGNALAZIONE_CUSTOM_SHARED_ATTRIBUTE, json, PortletSession.APPLICATION_SCOPE);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.CommonService#getSegnalazioneCustomFromSession(javax.portlet.
	 * PortletSession)
	 */
	@Override
	public SegnalazioneCustom getSegnalazioneCustomFromSession(PortletSession session) {
		SegnalazioneCustom segnalazioneCustom = null;
		if (session.getAttribute(PortletConstants.SEGNALAZIONE_CUSTOM_SHARED_ATTRIBUTE, PortletSession.APPLICATION_SCOPE) != null) {
			String segnalazioneCustomJson = (String) session.getAttribute(PortletConstants.SEGNALAZIONE_CUSTOM_SHARED_ATTRIBUTE, PortletSession.APPLICATION_SCOPE);
			Gson gson = new Gson();
			segnalazioneCustom = gson.fromJson(segnalazioneCustomJson, SegnalazioneCustom.class);
		}
		return segnalazioneCustom;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.CommonService#saveBackup(java.io.Serializable,
	 * it.osapulie.shared.service.UserPreferences, long)
	 */
	@Override
	public Backup saveBackup(String xml, UserPreferences userPreferences, long idServizio) {

		Backup backup = getBackup(userPreferences, idServizio);

		if (backup == null) {
			backup = new Backup();
			// Caricamento ComuneISA
			Long idComuneIsa = userPreferences.getIdComuneIsa();
			ComuneISA comuneISA = comuneISAService.getComuneByPk(idComuneIsa);
			backup.setComuneISA(comuneISA);

			// Caricamento cittadino/azienda
			String codiceFiscaleServizio = userPreferences.getCodiceFiscaleServizio();
			String partitaIvaServizio = userPreferences.getPartitaIvaServizio();

			// Profilo Utente
			if (codiceFiscaleServizio != null) {
				ProfiloUtenteCittadino profiloUtenteCittadinoByCf = profiloUtenteService.getProfiloUtenteCittadinoByCf(codiceFiscaleServizio);
				backup.setProfiloUtenteCittadino(profiloUtenteCittadinoByCf);
			}
			else if (partitaIvaServizio != null) {
				Azienda aziendaByPiva = aziendaService.getAziendaByPiva(partitaIvaServizio);
				backup.setAzienda(aziendaByPiva);
			}

			Long idDelega = userPreferences.getIdDelega();
			if (idDelega != null) {
				Delega delegaByPk = delegaService.getDelegaByPk(idDelega);
				if (delegaByPk != null) {
					backup.setProfiloUtenteCittadino(delegaByPk.getDelegante());
				}
			}
		}

		backup.setContenuto(xml);

		// Servizio
		it.osapulie.domain.servizi.Servizio servizioById = servizioService.getServizioById(idServizio);
		backup.setServizio(servizioById);

		backup = backupService.saveBackup(backup);

		return backup;
	}

	/**
	 *
	 * @param idServizio
	 * @param userPreferences
	 * @return
	 */
	private Backup getBackup(UserPreferences userPreferences, long idServizio) {

		String codiceFiscaleServizio = userPreferences.getCodiceFiscaleServizio();
		String partitaIvaServizio = userPreferences.getPartitaIvaServizio();
		Long idComuneIsa = userPreferences.getIdComuneIsa();
		Long idDelega = userPreferences.getIdDelega();

		Backup backup = null;

		// Delega
		if (idDelega != null) {
			Delega delegaByPk = delegaService.getDelegaByPk(idDelega);
			ProfiloUtenteCittadino delegante = delegaByPk.getDelegante();
			backup = backupService.getBackupCittadinoByComuneAndServizio(delegante.getId(), idComuneIsa, idServizio);
		}
		// Cittadino/Azienda
		else {
			if (codiceFiscaleServizio != null) {
				ProfiloUtenteCittadino profiloUtenteCittadinoByCf = profiloUtenteService.getProfiloUtenteCittadinoByCf(codiceFiscaleServizio);
				backup = backupService.getBackupCittadinoByComuneAndServizio(profiloUtenteCittadinoByCf.getId(), idComuneIsa, idServizio);
			}
			else if (partitaIvaServizio != null) {
				Azienda aziendaByPiva = aziendaService.getAziendaByPiva(partitaIvaServizio);
				backup = backupService.getBackupAziendaByComuneAndServizio(aziendaByPiva.getId(), idComuneIsa, idServizio);
			}
		}
		return backup;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.CommonService#deleteBackup(long)
	 */
	@Override
	public void deleteBackup(long idBackup) {
		backupService.deleteBackup(idBackup);
	}

	
}
