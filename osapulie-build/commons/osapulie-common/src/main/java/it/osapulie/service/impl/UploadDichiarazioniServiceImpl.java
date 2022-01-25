package it.osapulie.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.portlet.PortletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.osapulie.domain.Azienda;
import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.persistence.ComuneISARepository;
import it.osapulie.service.AziendaService;
import it.osapulie.service.ProfiloUtenteService;
import it.osapulie.service.UploadDichiarazioniService;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.web.portlet.util.CommunicationException;
import it.osapulie.web.portlet.util.PortletConstants;
import it.osapulie.web.portlet.util.SenderHelper;
import it.osapulie.web.portlet.util.UploadItem;

/**
 * Helper per i casi d'uso che prevedono upload di dichiarazioni con relativa verifica firma
 * digitale e successivo invio mail alla pec del comune o protocollo.
 *
 * @author Maria Michela Birtolo
 * @author Gianluca Pindinelli
 */
@Service("uploadDichiarazioniService")
@Transactional
public class UploadDichiarazioniServiceImpl implements UploadDichiarazioniService {

	private static final String TEMPLATE_DICHIARAZIONI_CITTADINO = "velocityTemplate/dichiarazioni_cittadino.vm";
	private static final String TEMPLATE_DICHIARAZIONI_AZIENDA = "velocityTemplate/dichiarazioni_azienda.vm";

	private final Logger log = LoggerFactory.getLogger(UploadDichiarazioniServiceImpl.class);

	@Inject
	private SenderHelper senderHelper;

	@Inject
	private ComuneISARepository comuneISARepository;

	@Inject
	private ProfiloUtenteService profiloUtenteService;

	@Inject
	private AziendaService aziendaService;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.service.UploadDichiarazioniService#processaUploadDichiarazioni(java.lang.String,
	 * it.osapulie.web.portlet.util.UploadItem, it.osapulie.domain.ProfiloUtenteCittadino,
	 * it.osapulie.shared.service.UserPreferences)
	 */
	@Override
	@Deprecated
	public String processaUploadDichiarazioni(String tipologia, UploadItem uploadItem, ProfiloUtenteCittadino profiloUtente, UserPreferences userPreferences) throws PortletException, IOException {

		String codiceIstat = userPreferences.getCodiceIstatComune();

		ComuneISA comuneErogatore = comuneISARepository.findByCodiceIstat(codiceIstat);

		String returnValue = sendMailForCittadino(tipologia, uploadItem, comuneErogatore, profiloUtente);

		return returnValue;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.service.UploadDichiarazioniService#processaUploadDichiarazioni(java.lang.String,
	 * it.osapulie.web.portlet.util.UploadItem, it.osapulie.shared.service.UserPreferences)
	 */
	@Override
	public String processaUploadDichiarazioni(String tipologia, UploadItem uploadItem, UserPreferences userPreferences) throws PortletException, IOException {

		String returnValue = null;

		String codiceIstat = userPreferences.getCodiceIstatComune();

		ComuneISA comuneErogatore = comuneISARepository.findByCodiceIstat(codiceIstat);

		String partitaIvaServizio = userPreferences.getPartitaIvaServizio();
		String codiceFiscaleServizio = userPreferences.getCodiceFiscaleServizio();
		// Invio in base al profilo selezionato
		if (partitaIvaServizio != null) {

			Azienda azienda = aziendaService.getAziendaByPiva(partitaIvaServizio);

			if (azienda == null) {
				throw new IOException("processaUploadDichiarazioni :: Errore durante l'invio della comunicazione all'azienda: azienda è NULL");
			}

			returnValue = sentMailForAzienda(tipologia, uploadItem, comuneErogatore, azienda);
		}
		else {

			ProfiloUtenteCittadino profiloUtente = profiloUtenteService.getProfiloUtenteCittadinoByCf(codiceFiscaleServizio);

			if (profiloUtente == null) {
				throw new IOException("processaUploadDichiarazioni :: Errore durante l'invio della comunicazione al cittadino: profiloUtente è NULL");
			}

			returnValue = sendMailForCittadino(tipologia, uploadItem, comuneErogatore, profiloUtente);
		}

		return returnValue;
	}

	/**
	 * @param tipologia
	 * @param uploadItem
	 * @param comuneErogatore
	 * @param profiloUtente
	 * @return
	 * @throws IOException
	 */
	private String sendMailForCittadino(String tipologia, UploadItem uploadItem, ComuneISA comuneErogatore, ProfiloUtenteCittadino profiloUtente) throws IOException {

		String returnValue = null;

		Map<String, String> model = new HashMap<String, String>();
		model.put("dichiarazione", tipologia);
		model.put("nome", profiloUtente.getNome());
		model.put("cognome", profiloUtente.getCognome());
		model.put("codiceFiscale", profiloUtente.getCodiceFiscale());
		model.put("pecMail", profiloUtente.getMailPec());
		model.put("email", profiloUtente.getMail());

		try {
			returnValue = senderHelper.sendCommunicationToComuneISA(tipologia, TEMPLATE_DICHIARAZIONI_CITTADINO, model, uploadItem, comuneErogatore);
		}
		catch (CommunicationException e) {
			log.error("processaUploadDichiarazioni :: " + e.getMessage(), e);
			String message = null;
			if (comuneErogatore.getCanaleComunicazione().equals(PortletConstants.CANALE_COMUNICAZIONE_PEC)) {
				message = "processaUploadDichiarazioni :: Errore durante l'invio della PEC mail all'indirizzo " + comuneErogatore.getPec();
			}
			else if (comuneErogatore.getCanaleComunicazione().equals(PortletConstants.CANALE_COMUNICAZIONE_PROTOCOLLO)) {
				message = "processaUploadDichiarazioni :: Errore durante l'invio al protocollo ";
			}
			throw new IOException(message, e);
		}
		return returnValue;
	}

	/**
	 * @param tipologia
	 * @param uploadItem
	 * @param comuneErogatore
	 * @param azienda
	 * @return
	 * @throws IOException
	 */
	private String sentMailForAzienda(String tipologia, UploadItem uploadItem, ComuneISA comuneErogatore, Azienda azienda) throws IOException {

		String returnValue = null;

		Map<String, String> model = new HashMap<String, String>();
		model.put("dichiarazione", tipologia);
		model.put("ragioneSociale", azienda.getRagioneSociale());
		model.put("piva", azienda.getPartitaIva());
		model.put("pecMail", azienda.getMailPec());
		model.put("email", azienda.getMail());

		try {
			returnValue = senderHelper.sendCommunicationToComuneISA(tipologia, TEMPLATE_DICHIARAZIONI_AZIENDA, model, uploadItem, comuneErogatore);
		}
		catch (CommunicationException e) {
			log.error("processaUploadDichiarazioni :: " + e.getMessage(), e);
			String message = null;
			if (comuneErogatore.getCanaleComunicazione().equals(PortletConstants.CANALE_COMUNICAZIONE_PEC)) {
				message = "processaUploadDichiarazioni :: Errore durante l'invio della PEC mail all'indirizzo " + comuneErogatore.getPec();
			}
			else if (comuneErogatore.getCanaleComunicazione().equals(PortletConstants.CANALE_COMUNICAZIONE_PROTOCOLLO)) {
				message = "processaUploadDichiarazioni :: Errore durante l'invio al protocollo ";
			}
			throw new IOException(message, e);
		}
		return returnValue;
	}
}
