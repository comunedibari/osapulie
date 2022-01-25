/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.web.portlet.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import it.osapulie.domain.Azienda;
import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.protocollo.web.ws.types.DettaglioAllegatoResponse;
import it.osapulie.protocollo.web.ws.types.DettaglioProtocolloResponse;

/**
 * Helper per l'invio delle comunicazioni verso l'esterno. La classe funge da Facade per gestire in
 * automatico le diverse tipologie di chiamate da effettuale alle diverse tipologie di destinatari.
 *
 * @author Gianluca Pindinelli
 *
 */
public interface SenderHelper {

	/**
	 * Invia una comunicazione al Comune ISA passato in input e ritorna la risposta corrispondente.
	 * Si occupa di gestire la comunicazione in base alle preferenze del Comune (PEC o Protocollo),
	 *
	 * @param subject
	 * @param templateName
	 * @param model
	 * @param attachmentFiles
	 * @param comuneISA
	 * @return
	 * @throws Exception
	 *
	 */
	String sendCommunicationToComuneISA(String subject, String templateName, Map<String, String> model, File[] attachmentFiles, ComuneISA comuneISA) throws CommunicationException;

	/**
	 * Invia una comunicazione al Comune ISA passato in input e ritorna la risposta corrispondente.
	 * Si occupa di gestire la comunicazione in base alle preferenze del Comune (PEC o Protocollo),
	 *
	 * @param subject
	 * @param templateName
	 * @param model
	 * @param attachmentUploadItem
	 * @param comuneISA
	 * @return
	 * @throws Exception
	 */
	String sendCommunicationToComuneISA(String subject, String templateName, Map<String, String> model, UploadItem attachmentUploadItem, ComuneISA comuneISA) throws CommunicationException;

	/**
	 * Invia una comunicazione al Cittadino passato in input. Si occupa di gestire la comunicazione
	 * in base alle preferenze del cittadino (Email tradizione o PEC).
	 *
	 * @param subject
	 * @param templateFileName
	 * @param model
	 * @param attachmentFiles
	 * @param profiloUtenteCittadino
	 * @throws Exception
	 */
	void sendCommunicationToCittadino(String subject, String templateFileName, Map<String, String> model, File[] attachmentFiles, ProfiloUtenteCittadino profiloUtenteCittadino)
			throws CommunicationException;

	/**
	 * Invia una richiesta di dettaglio protocollo.
	 *
	 * @param numProtocollo
	 * @param comuneISA
	 * @return
	 * @throws CommunicationException
	 */
	DettaglioProtocolloResponse richiestaDettaglioProtocollo(String numProtocollo, ComuneISA comuneISA) throws CommunicationException;

	/**
	 * Invia una richiesta di dettaglio allegato.
	 *
	 * @param idDocumento
	 * @param comuneISA
	 * @return
	 * @throws CommunicationException
	 */
	DettaglioAllegatoResponse richiestaDettaglioAllegato(String idDocumento, ComuneISA comuneISA) throws CommunicationException;

	/**
	 * @param subject
	 * @param templateName
	 * @param model
	 * @param attachmentFiles
	 * @param azienda
	 * @throws CommunicationException
	 */
	void sendCommunicationToAzienda(String subject, String templateName, Map<String, String> model, File[] attachmentFiles, Azienda azienda) throws CommunicationException;

	/**
	 * Invia una mail in base ai parametri passati in input.
	 * 
	 * @param subject
	 * @param templateName
	 * @param model
	 * @param attachmentFiles
	 * @param email
	 * @throws CommunicationException
	 */
	void sendMail(String subject, String templateName, Map<String, String> model, File[] attachmentFiles, String email) throws CommunicationException;

	/**
	 * Invia una mail in base ai parametri passati in input.
	 * 
	 * @param subject
	 * @param templateName
	 * @param listaEM
	 * @param attachmentFiles
	 * @param email
	 * @throws CommunicationException
	 */

	void sendMailAggOp(String subject, String templateName, Map<String, Object> model, File[] attachmentFiles, String email) throws CommunicationException;

	void sendMailAziendaNew(String subject, String templateName, Map<String, Object> model, File[] attachmentFiles,
			String email) throws CommunicationException;

	
}
