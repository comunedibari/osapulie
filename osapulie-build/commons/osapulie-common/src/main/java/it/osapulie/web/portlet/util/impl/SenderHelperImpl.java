/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.web.portlet.util.impl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import com.liferay.portal.kernel.util.PrefsPropsUtil;

import it.osapulie.domain.Azienda;
import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.protocollo.web.ws.types.Allegato;
import it.osapulie.protocollo.web.ws.types.DettaglioAllegatoRequest;
import it.osapulie.protocollo.web.ws.types.DettaglioAllegatoResponse;
import it.osapulie.protocollo.web.ws.types.DettaglioProtocolloRequest;
import it.osapulie.protocollo.web.ws.types.DettaglioProtocolloResponse;
import it.osapulie.protocollo.web.ws.types.Documento;
import it.osapulie.protocollo.web.ws.types.ImprontaMIME;
import it.osapulie.protocollo.web.ws.types.Mittente;
import it.osapulie.protocollo.web.ws.types.NuovoProtocolloRequest;
import it.osapulie.protocollo.web.ws.types.NuovoProtocolloResponse;
import it.osapulie.protocollo.web.ws.types.PersonaFisica;
import it.osapulie.protocollo.web.ws.types.ProtocolloRequest;
import it.osapulie.service.ProtocolloService;
import it.osapulie.web.portlet.util.CheckSumGenerator;
import it.osapulie.web.portlet.util.CommunicationException;
import it.osapulie.web.portlet.util.MailPecSenderHelper;
import it.osapulie.web.portlet.util.MailSenderHelper;
import it.osapulie.web.portlet.util.PortletConstants;
import it.osapulie.web.portlet.util.PortletUtils;
import it.osapulie.web.portlet.util.SenderHelper;
import it.osapulie.web.portlet.util.UploadItem;

/**
 * Implementazione dell'interfaccia sender che si occupa dell'invio delle comunicazioni.
 *
 * @author Gianluca Pindinelli
 *
 */
@Component("senderHelper")
public class SenderHelperImpl implements SenderHelper {

	protected Logger log = LoggerFactory.getLogger(SenderHelperImpl.class.getName());

	@Inject
	private MailSenderHelper mailSenderHelper;

	@Inject
	private MailPecSenderHelper mailPecSenderHelper;

	@Inject
	private ProtocolloService protocolloServicePDDImpl;

	@Value("${send.email.enable}")
	private String sendEmailEnable;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.web.portlet.util.SenderHelper#sendCommunication(java.lang.String,
	 * java.lang.String, java.util.Map, java.io.File[], it.osapulie.domain.ComuneISA)
	 */
	@Override
	public String sendCommunicationToComuneISA(String subject, String templateName, Map<String, String> model, File[] attachmentFiles, ComuneISA comuneISA) throws CommunicationException {

		log.debug("sendCommunicationToComuneISA :: entering method");

		Assert.notNull(comuneISA, "comuneISA must be not NULL");

		NuovoProtocolloResponse returnValue = null;

		// Controllo impostazioni portale ISA
		boolean pecEnableBoolean = false;
		try {
			String pecEnable = PrefsPropsUtil.getString("pec.mail.session.mail.smtp.enable");
			pecEnableBoolean = Boolean.parseBoolean(pecEnable);

		}
		catch (Exception e) {
			log.error("sendCommunicationToComuneISA :: " + e.getMessage(), e);
		}

		// Invio comunicazione tramite PEC
		if (comuneISA.getCanaleComunicazione().equals(PortletConstants.CANALE_COMUNICAZIONE_PEC)) {
			// Se la PEC ISA è abilitata invio, invio la mail con il server SMTPS all'indirizzo di
			// mail tradizionale del Comune ISA
			if (pecEnableBoolean) {
				sendPec(subject, templateName, model, attachmentFiles, comuneISA);
			}
			// Altrimenti uso il server SMTP standard (non PEC) per l'invio all'indirizzo di mail
			// tradizionale del Comune ISA
			else {
				//in collaudo non vengono mandate email
				if (Boolean.getBoolean(sendEmailEnable))
					mailSenderHelper.sendMail(subject, templateName, model, attachmentFiles, new String[] { comuneISA.getPec() });
			}
		}
		// Invio comunicazione tramite protocollo
		else if (comuneISA.getCanaleComunicazione().equals(PortletConstants.CANALE_COMUNICAZIONE_PROTOCOLLO)) {
			try {
				returnValue = sendProtocollo(subject, model, attachmentFiles, comuneISA);
			}
			catch (IOException e) {
				log.error("sendCommunicationToComuneISA :: " + e.getMessage(), e);
				throw new CommunicationException(e);
			}
		}

		String result = null;

		if (returnValue != null) {
			try {
				result = getProtocolloResult(returnValue);
			}
			catch (Exception e) {
				log.error("sendCommunicationToComuneISA :: " + e.getMessage(), e);
				throw new CommunicationException(e);
			}
		}

		return result;

	}

	/**
	 * @param subject
	 * @param templateName
	 * @param model
	 * @param attachmentFiles
	 * @param comuneISA
	 * @throws CommunicationException
	 */
	private void sendPec(String subject, String templateName, Map<String, String> model, File[] attachmentFiles, ComuneISA comuneISA) throws CommunicationException {

		log.debug("sendPec :: entering method");

		try {
			//in collaudo non vengono mandate email
			if (Boolean.getBoolean(sendEmailEnable))
				mailPecSenderHelper.sendMail(subject, templateName, model, attachmentFiles, new String[] { comuneISA.getPec() });
		}
		catch (CommunicationException e) {
			log.error("sendPec :: " + e.getMessage(), e);
			throw new CommunicationException("sendPec :: Errore durante l'invio della PEC mail all'indirizzo " + comuneISA.getPec(), e);
		}

	}

	/**
	 * @param subject
	 * @param model
	 * @param attachmentFiles il primo è il documento, gli altri diventano allegati del protocollo
	 * @param comuneISA
	 * @return
	 * @throws IOException
	 * @throws CommunicationException
	 */
	private NuovoProtocolloResponse sendProtocollo(String subject, Map<String, String> model, File[] attachmentFiles, ComuneISA comuneISA) throws IOException, CommunicationException {

		log.debug("sendProtocollo :: entering method");

		NuovoProtocolloResponse returnValue = null;

		ProtocolloRequest protocolloRequest = new ProtocolloRequest();
		protocolloRequest.setOggetto(subject);
		protocolloRequest.setAreaOrganizzativaOmogenea(comuneISA.getCodiceAOO());
		protocolloRequest.setAmministrazione(comuneISA.getAmministrazione());

		// Destinatario destinatario = new Destinatario();
		// destinatario.setAmministrazione(comuneISA.getAmministrazione());
		// destinatario.setAreaOrganizzativaOmogenea(comuneISA.getCodiceAOO());
		// protocolloRequest.getDestinatari().add(destinatario);

		Mittente mittente = getMittente(model);

		// Allegati
		if (attachmentFiles != null) {
			if (attachmentFiles.length > 0) {
				File attachmentFile = attachmentFiles[0];

				if (attachmentFile != null) {
					Documento documento = getDocumentoByFile(attachmentFile);
					protocolloRequest.setDocumento(documento);
				}

				if (attachmentFiles.length >= 1) {
					for (int i = 1; i < attachmentFiles.length; i++) {

						File attachmentAllegatoFile = attachmentFiles[i];
						Allegato allegato = new Allegato();

						Documento documentoAllegato = getDocumentoByFile(attachmentAllegatoFile);
						allegato.setDocumento(documentoAllegato);

						protocolloRequest.getAllegati().add(allegato);
					}
				}
			}
		}

		protocolloRequest.setMittente(mittente);

		NuovoProtocolloRequest nuovoProtocolloRequest = new NuovoProtocolloRequest();
		nuovoProtocolloRequest.setProtocolloRequest(protocolloRequest);

		returnValue = protocolloServicePDDImpl.sendProtocollo(nuovoProtocolloRequest, comuneISA);

		return returnValue;
	}

	/**
	 * @param model
	 * @return
	 */
	private Mittente getMittente(Map<String, String> model) {

		Mittente mittente = new Mittente();

		PersonaFisica personaFisica = new PersonaFisica();
		personaFisica.setCodiceFiscale(model.get("codiceFiscale"));
		personaFisica.setNome(model.get("nome"));
		personaFisica.setCognome(model.get("cognome"));
		mittente.setPersonaFisica(personaFisica);

		mittente.setEmail(model.get("email"));
		mittente.setPecEmail(model.get("pecMail"));

		return mittente;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.web.portlet.util.SenderHelper#sendCommunication(java.lang.String,
	 * java.lang.String, java.util.Map, it.osapulie.web.portlet.util.UploadItem,
	 * it.osapulie.domain.ComuneISA)
	 */
	@Override
	public String sendCommunicationToComuneISA(String subject, String templateName, Map<String, String> model, UploadItem attachmentUploadItem, ComuneISA comuneISA) throws CommunicationException {

		log.debug("sendCommunicationToComuneISA :: entering method");

		Assert.notNull(comuneISA, "comuneISA must be not NULL");

		NuovoProtocolloResponse returnValue = null;
		String result = null;

		// Controllo impostazioni portale ISA
		boolean pecEnableBoolean = false;
		try {
			String pecEnable = PrefsPropsUtil.getString("pec.mail.session.mail.smtp.enable");
			pecEnableBoolean = Boolean.parseBoolean(pecEnable);

		}
		catch (Exception e) {
			log.error("sendCommunicationToComuneISA :: " + e.getMessage(), e);
		}

		// Invio comunicazione tramite PEC
		if (comuneISA.getCanaleComunicazione().equals(PortletConstants.CANALE_COMUNICAZIONE_PEC)) {
			// Se la PEC ISA è abilitata invio, invio la mail con il server SMTPS all'indirizzo di
			// mail tradizionale del Comune ISA
			if (pecEnableBoolean) {
				sendPec(subject, templateName, model, attachmentUploadItem, comuneISA);
			}
			// Altrimenti uso il server SMTP standard (non PEC) per l'invio all'indirizzo di mail
			// tradizionale del Comune ISA
			else {
				//in collaudo non vengono mandate email
				if (Boolean.getBoolean(sendEmailEnable))
					mailSenderHelper.sendConfirmationEmail(subject, templateName, model, attachmentUploadItem, new String[] { comuneISA.getPec() });
			}
		}
		// Invio comunicazione tramite protocollo
		else if (comuneISA.getCanaleComunicazione().equals(PortletConstants.CANALE_COMUNICAZIONE_PROTOCOLLO)) {
			try {
				returnValue = sendProtocollo(subject, model, attachmentUploadItem, comuneISA);
				result = getProtocolloResult(returnValue);
			}
			catch (MalformedURLException e) {
				log.error("sendCommunicationToComuneISA :: " + e.getMessage(), e);
				throw new CommunicationException(e);
			}
			catch (IOException e) {
				log.error("sendCommunicationToComuneISA :: " + e.getMessage(), e);
				throw new CommunicationException(e);
			}
			catch (Exception e) {
				log.error("sendCommunicationToComuneISA :: " + e.getMessage(), e);
				throw new CommunicationException(e);
			}
		}

		return result;

	}

	/**
	 * @param subject
	 * @param templateName
	 * @param model
	 * @param attachmentUploadItem
	 * @param comuneISA
	 * @throws IOException
	 */
	private void sendPec(String subject, String templateName, Map<String, String> model, UploadItem attachmentUploadItem, ComuneISA comuneISA) throws CommunicationException {

		log.debug("sendPec :: entering method");

		try {
			//in collaudo non vengono mandate email
			if (Boolean.getBoolean(sendEmailEnable))
				mailPecSenderHelper.sendConfirmationEmail(subject, templateName, attachmentUploadItem, model, new String[] { comuneISA.getPec() });
		}
		catch (CommunicationException e) {
			log.error("sendPec :: " + e.getMessage(), e);
			throw new CommunicationException("sendPec :: Errore durante l'invio della PEC mail all'indirizzo " + comuneISA.getPec(), e);
		}
	}

	/**
	 * @param subject
	 * @param model
	 * @param attachmentUploadItem
	 * @param comuneISA
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws CommunicationException
	 */
	private NuovoProtocolloResponse sendProtocollo(String subject, Map<String, String> model, UploadItem attachmentUploadItem, ComuneISA comuneISA)
			throws MalformedURLException, IOException, CommunicationException {

		log.debug("sendProtocollo :: entering method");

		NuovoProtocolloResponse returnValue = null;

		ProtocolloRequest protocolloRequest = new ProtocolloRequest();

		protocolloRequest.setOggetto(subject);
		protocolloRequest.setAreaOrganizzativaOmogenea(comuneISA.getCodiceAOO());
		protocolloRequest.setAmministrazione(comuneISA.getAmministrazione());

		// Destinatario destinatario = new Destinatario();
		// destinatario.setAmministrazione(comuneISA.getAmministrazione());
		// destinatario.setAreaOrganizzativaOmogenea(comuneISA.getCodiceAOO());
		// protocolloRequest.getDestinatari().add(destinatario);

		Mittente mittente = getMittente(model);

		// Allegati
		if (attachmentUploadItem != null) {
			MultipartFile generatedFile = attachmentUploadItem.getGeneratedFile();

			if (generatedFile != null) {
				Documento documento = getDocumentoByFile(generatedFile);
				protocolloRequest.setDocumento(documento);
			}

			List<MultipartFile> multipartFiles = attachmentUploadItem.getMultipartFiles();
			if (multipartFiles.size() > 0) {

				for (int i = 1; i < multipartFiles.size(); i++) {
					MultipartFile multipartFile = multipartFiles.get(i);
					Allegato allegato = new Allegato();

					Documento documentoAllegato = getDocumentoByFile(multipartFile);
					allegato.setDocumento(documentoAllegato);

					protocolloRequest.getAllegati().add(allegato);
				}
			}
		}

		protocolloRequest.setMittente(mittente);

		NuovoProtocolloRequest nuovoProtocolloRequest = new NuovoProtocolloRequest();
		nuovoProtocolloRequest.setProtocolloRequest(protocolloRequest);

		returnValue = protocolloServicePDDImpl.sendProtocollo(nuovoProtocolloRequest, comuneISA);

		return returnValue;
	}

	/**
	 * @param attachmentFile
	 * @return
	 * @throws IOException
	 */
	private Documento getDocumentoByFile(File attachmentFile) throws IOException {

		Documento documento = new Documento();

		byte[] bytes = FileUtils.readFileToByteArray(attachmentFile);
		documento.setContenuto(bytes);
		documento.setNomeFile(attachmentFile.getName());
		documento.setTitolo(attachmentFile.getName());
		documento.setDettaglio(attachmentFile.getName());

		ImprontaMIME improntaMime = new ImprontaMIME();
		String md5 = CheckSumGenerator.generateCheckSum(bytes);
		improntaMime.setValue(md5);

		documento.setImprontaMIME(improntaMime);
		return documento;
	}

	/**
	 * @param attachmentFile
	 * @return
	 * @throws IOException
	 */
	private Documento getDocumentoByFile(MultipartFile attachmentFile) throws IOException {

		Documento documento = new Documento();

		byte[] bytes = attachmentFile.getBytes();
		documento.setContenuto(bytes);
		documento.setNomeFile(attachmentFile.getOriginalFilename());
		documento.setTitolo(attachmentFile.getName());
		documento.setDettaglio(attachmentFile.getName());

		ImprontaMIME improntaMime = new ImprontaMIME();
		String md5 = CheckSumGenerator.generateCheckSum(bytes);
		improntaMime.setValue(md5);

		documento.setImprontaMIME(improntaMime);
		return documento;
	}

	/**
	 * @param returnValue
	 * @return
	 * @throws Exception
	 */
	private String getProtocolloResult(NuovoProtocolloResponse returnValue) throws Exception {
		if ((returnValue != null) && (returnValue.getNumeroProtocollo() != null) && !returnValue.getNumeroProtocollo().equals("") && (returnValue.getErrore() == null)) {
			return PortletUtils.getNumeroProtocollo(returnValue.getNumeroProtocollo(), returnValue.getDataProtocollo().getTime());
		}
		else {
			throw new Exception(
					"Errore in fase di protocollazione del documento - errore: " + returnValue.getErrore().getCodice() + " - Descrizione errore: " + returnValue.getErrore().getDescrizione());
		}
	}

	/**
	 * Metodo che invia al protocollo uan richiesta di stato avanzamento pratica.
	 *
	 * @param numProtocollo
	 * @param comuneISA
	 * @return DettaglioProtocolloResponse
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	@Override
	public DettaglioProtocolloResponse richiestaDettaglioProtocollo(String numProtocollo, ComuneISA comuneISA) throws CommunicationException {

		log.debug("richiestaDettaglioProtocollo :: entering method");

		DettaglioProtocolloRequest protocolloRequest = new DettaglioProtocolloRequest();

		DettaglioProtocolloResponse risposta;
		try {

			String anno = numProtocollo.substring(0, 4);
			String numero = numProtocollo.substring(4);

			protocolloRequest.setNumeroProtocollo(Long.parseLong(numero));
			protocolloRequest.setAnno(Integer.parseInt(anno));

			log.debug("richiestaDettaglioProtocollo :: invio richiesta");

			risposta = protocolloServicePDDImpl.getProtocollo(protocolloRequest, comuneISA);
		}
		catch (NumberFormatException e) {
			log.error("richiestaDettaglioProtocollo :: " + e.getMessage(), e);
			throw new CommunicationException(e);
		}

		return risposta;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.web.portlet.util.SenderHelper#sendCommunicationToCittadino(java.lang.String,
	 * java.lang.String, java.util.Map, java.io.File[], it.osapulie.domain.ProfiloUtenteCittadino)
	 */
	@Override
	public void sendCommunicationToCittadino(String subject, String templateName, Map<String, String> model, File[] attachmentFiles, ProfiloUtenteCittadino profiloUtenteCittadino)
			throws CommunicationException {

		log.debug("sendCommunicationToCittadino :: entering method");

		boolean pecEnableBoolean = false;
		// Controllo impostazioni portale ISA
		try {
			String pecEnable = PrefsPropsUtil.getString("pec.mail.session.mail.smtp.enable");
			pecEnableBoolean = Boolean.parseBoolean(pecEnable);

		}
		catch (Exception e) {
			log.error("sendCommunicationToCittadino :: " + e.getMessage(), e);
		}

		// Se ISA ha configurato il server di invio PEC
		String destinationeEmailAddress = "";

		if (pecEnableBoolean) {
			String canaleComunicazione = profiloUtenteCittadino.getCanaleComunicazione();
			if ((canaleComunicazione != null) && !canaleComunicazione.equals("")) {
				// Se l'utente ha scelto l'utilizzo della PEC mail per le comunicazioni ufficiali
				if (canaleComunicazione.equals(PortletConstants.CANALE_COMUNICAZIONE_PEC)) {
					destinationeEmailAddress = profiloUtenteCittadino.getMailPec();
				}
				// Altrimenti
				else if (canaleComunicazione.equals(PortletConstants.CANALE_COMUNICAZIONE_EMAIL)) {
					destinationeEmailAddress = profiloUtenteCittadino.getMail();
				}
			}
			else {
				destinationeEmailAddress = profiloUtenteCittadino.getMail();
			}
			// Invio PEC mail
			//in collaudo non vengono mandate email
			//if (Boolean.getBoolean(sendEmailEnable))
				mailPecSenderHelper.sendMail(subject, templateName, model, attachmentFiles, new String[] { destinationeEmailAddress });
		}
		// Se ISA ha configurato solo il server di invio email tradizionali
		else {
			destinationeEmailAddress = profiloUtenteCittadino.getMail();
			//in collaudo non vengono mandate email
			//if (Boolean.getBoolean(sendEmailEnable))
				mailSenderHelper.sendMail(subject, templateName, model, attachmentFiles, new String[] { destinationeEmailAddress });

		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.web.portlet.util.SenderHelper#sendCommunicationToProfessionista(java.lang.String,
	 * java.lang.String, java.util.Map, java.io.File[], it.osapulie.domain.Azienda)
	 */
	@Override
	public void sendCommunicationToAzienda(String subject, String templateName, Map<String, String> model, File[] attachmentFiles, Azienda azienda) throws CommunicationException {

		log.debug("sendCommunicationToAzienda :: entering method");

		boolean pecEnableBoolean = false;
		// Controllo impostazioni portale ISA
		try {
			String pecEnable = PrefsPropsUtil.getString("pec.mail.session.mail.smtp.enable");
			pecEnableBoolean = Boolean.parseBoolean(pecEnable);

		}
		catch (Exception e) {
			log.error("sendCommunicationToAzienda :: " + e.getMessage(), e);
		}

		// Se ISA ha configurato il server di invio PEC
		String destinationeEmailAddress = "";

		if (pecEnableBoolean) {
			if (azienda.getMailPec() != null && !azienda.getMailPec().equals("")) {
				// Se l'utente ha scelto l'utilizzo della PEC mail per le comunicazioni ufficiali
				destinationeEmailAddress = azienda.getMailPec();
			}
			else {
				destinationeEmailAddress = azienda.getMail();
			}
			// Invio PEC mail
			//in collaudo non vengono mandate email
			if (Boolean.getBoolean(sendEmailEnable))
				mailPecSenderHelper.sendMail(subject, templateName, model, attachmentFiles, new String[] { destinationeEmailAddress });
		}
		// Se ISA ha configurato solo il server di invio email tradizionali
		else {
			destinationeEmailAddress = azienda.getMail();
			//in collaudo non vengono mandate email
			//if (Boolean.getBoolean(sendEmailEnable))
				mailSenderHelper.sendMail(subject, templateName, model, attachmentFiles, new String[] { destinationeEmailAddress });

		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.web.portlet.util.SenderHelper#richiestaDettaglioAllegato(java.lang.String,
	 * it.osapulie.domain.ComuneISA)
	 */
	@Override
	public DettaglioAllegatoResponse richiestaDettaglioAllegato(String idDocumento, ComuneISA comuneISA) throws CommunicationException {

		log.debug("richiestaDettaglioAllegato :: entering method");

		DettaglioAllegatoResponse response = null;

		DettaglioAllegatoRequest dettaglioAllegatoRequest = new DettaglioAllegatoRequest();
		dettaglioAllegatoRequest.setCollocazioneTelematica(idDocumento);

		response = protocolloServicePDDImpl.getAllegato(dettaglioAllegatoRequest, comuneISA);

		return response;
	}

	/**
	 * @return the protocolloServicePDDImpl
	 */
	public ProtocolloService getProtocolloService() {
		return protocolloServicePDDImpl;
	}

	/**
	 * @param protocolloServicePDDImpl the protocolloServicePDDImpl to set
	 */
	public void setProtocolloService(ProtocolloService protocolloService) {
		this.protocolloServicePDDImpl = protocolloService;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.web.portlet.util.SenderHelper#sendMail(java.lang.String, java.lang.String,
	 * java.util.Map, java.io.File[], java.lang.String)
	 */
	@Override
	public void sendMail(String subject, String templateName, Map<String, String> model, File[] attachmentFiles, String email) throws CommunicationException {

		boolean pecEnableBoolean = false;

		// Controllo impostazioni portale ISA
		try {
			String pecEnable = PrefsPropsUtil.getString("pec.mail.session.mail.smtp.enable");
			pecEnableBoolean = Boolean.parseBoolean(pecEnable);

		}
		catch (Exception e) {
			log.error("sendMail :: " + e.getMessage(), e);
		}

		if (pecEnableBoolean) {
			//in collaudo non vengono mandate email
			if (Boolean.getBoolean(sendEmailEnable))
				mailPecSenderHelper.sendMail(subject, templateName, model, attachmentFiles, new String[] { email });
		}
		else {
			//in collaudo non vengono mandate email
			if (Boolean.getBoolean(sendEmailEnable))
				mailSenderHelper.sendMail(subject, templateName, model, attachmentFiles, new String[] { email });
		}
	}

	
	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.web.portlet.util.SenderHelper#sendMail(java.lang.String, java.lang.String,
	 * java.util.Map, java.io.File[], java.lang.String)
	 */
	
	@Override
	public void sendMailAggOp(String subject, String templateName,   Map<String, Object> model, File[] attachmentFiles, String email) throws CommunicationException 
	{

		boolean pecEnableBoolean = false;

		// Controllo impostazioni portale ISA
		try {
			String pecEnable = PrefsPropsUtil.getString("pec.mail.session.mail.smtp.enable");
			pecEnableBoolean = Boolean.parseBoolean(pecEnable);

		}
		catch (Exception e) {
			log.error("sendMail :: " + e.getMessage(), e);
		}

		if (pecEnableBoolean) {
			//in collaudo non vengono mandate email
			if (Boolean.getBoolean(sendEmailEnable))
				mailPecSenderHelper.sendMailInsOper(subject, templateName,   model, attachmentFiles, new String[] { email });
		}
		else {
			//in collaudo non vengono mandate email
			//if (Boolean.getBoolean(sendEmailEnable))
				mailSenderHelper.sendMailInsOper(subject, templateName,  model, attachmentFiles, new String[] { email });
		}
	}
	
	
	@Override
	public void sendMailAziendaNew(String subject, String templateName,   Map<String, Object> model, File[] attachmentFiles, String email) throws CommunicationException 
	{

		boolean pecEnableBoolean = false;

		// Controllo impostazioni portale ISA
		try {
			String pecEnable = PrefsPropsUtil.getString("pec.mail.session.mail.smtp.enable");
			pecEnableBoolean = Boolean.parseBoolean(pecEnable);

		}
		catch (Exception e) {
			log.error("sendMail :: " + e.getMessage(), e);
		}

		if (pecEnableBoolean) {
			//in collaudo non vengono mandate email
			if (Boolean.getBoolean(sendEmailEnable))
				mailPecSenderHelper.sendMailInsAziendaNew(subject, templateName,   model, attachmentFiles, new String[] { email });
		}
		else {
			//in collaudo non vengono mandate email
			//if (Boolean.getBoolean(sendEmailEnable))
				mailSenderHelper.sendMailInsAziendaNew(subject, templateName,  model, attachmentFiles, new String[] { email });
		}
	}
	
	
}
