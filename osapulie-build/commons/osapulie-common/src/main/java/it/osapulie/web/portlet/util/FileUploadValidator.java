/*******************************************************************************
 *
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.web.portlet.util;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import it.linksmt.tools.versig.SignatureManager;
import it.linksmt.tools.versig.exception.SignatureVerificationException;
import it.linksmt.tools.versig.factory.SignatureManagerFactory;
import it.osapulie.domain.Azienda;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.fascicoloutente.FascicoloUtente;
import it.osapulie.domain.fascicoloutente.RichiestaServizio;
import it.osapulie.domain.servizi.Servizio;
import it.osapulie.persistence.FascicoloUtenteRepository;
import it.osapulie.persistence.RichiestaServizioRepository;
import it.osapulie.persistence.ServizioRepository;

/**
 * Form validator per {@link UploadItem}.
 *
 * @author Maria Michela Birtolo
 * @author Gianluca Pindinelli
 */
@Component("fileUploadValidator")
public class FileUploadValidator implements Validator {

	private final Logger log = LoggerFactory.getLogger(FileUploadValidator.class);

	@Inject
	private ServizioRepository servizioRepository;

	@Inject
	private FascicoloUtenteRepository repositoryFascicolo;

	@Inject
	private RichiestaServizioRepository repositoryRihciesta;

	@Override
	public boolean supports(Class<?> clazz) {
		return UploadItem.class.isAssignableFrom(clazz);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {

	}

	/**
	 * Validazione upload file per il cittadino.
	 *
	 * @param target
	 * @param errors
	 * @param cittadino
	 * @param codiceServizio
	 */
	public void validate(Object target, Errors errors, ProfiloUtenteCittadino cittadino, String codiceServizio) {

		UploadItem upload = (UploadItem) target;

		List<MultipartFile> files = upload.getMultipartFiles();
		MultipartFile generatedfile = upload.getGeneratedFile();

		if (generatedfile == null || generatedfile.isEmpty()) {
			errors.rejectValue("generatedFile", "required.fileUpload");
		}

		try {
			if (!generatedfile.isEmpty()) {
				String checksum = null;
				if (upload.isSignedFiles()) {
					boolean verifica = SignatureManager.checkPkcs7Signature(generatedfile.getBytes(), null, null);
					if (!verifica) {
						errors.rejectValue("generatedFile", "error.checkPkcs7Signature");
					}

					for (int i = 0; i < files.size(); i++) {
						log.debug("Test upload: " + i + " = " + files.get(i).getSize());
						if (files.get(i).getSize() > 0) {
							boolean verifica2 = SignatureManager.checkPkcs7Signature(files.get(i).getBytes(), null, null);
							if (!verifica2) {
								errors.rejectValue("multipartFiles[" + i + "]", "error.checkPkcs7Signature");
							}
						}
						else {
							errors.rejectValue("multipartFiles[" + i + "]", "NotEmpty.field.required");
						}
					}
					boolean isCades = SignatureManagerFactory.isCades(generatedfile.getBytes());
					if (verifica && isCades) {
						// recupero dal doc firmato quello originale
						byte[] data = SignatureManager.getData(generatedfile.getBytes());
						// mi ricalcolo il checksum
						checksum = CheckSumGenerator.generateCheckSum(data);
						log.debug("checksum=" + checksum);
					}

					// verifico se esiste nel db una tupla corrispondente all'impronta ottenuta
					// (solo per firme CADES)
					if (isCades) {
						Servizio servizio = servizioRepository.findByCodiceServizio(codiceServizio);
						FascicoloUtente fascicolo = repositoryFascicolo.findByCittadino(cittadino);
						RichiestaServizio richiesta = repositoryRihciesta.findByFascicoloAndChecksumAndServizio(fascicolo, checksum, servizio);
						if (richiesta == null) {
							errors.rejectValue("generatedFile", "error.checkSum");
						}
					}
				}
				else {
					checksum = CheckSumGenerator.generateCheckSum(generatedfile.getBytes());
					for (int i = 0; i < files.size(); i++) {
						log.debug("Test upload: " + i + " = " + files.get(i).getSize());
						if (files.get(i).getSize() == 0) {
							errors.rejectValue("multipartFiles[" + i + "]", "NotEmpty.field.required");
						}
					}
				}

			}
		}
		catch (IOException ioe) {
			errors.rejectValue("generatedFile", "error.fileUpload");
		}
		catch (SignatureVerificationException e) {
			errors.rejectValue("generatedFile", "error.getData");
			log.error("validate :: " + e.getMessage(), e);
		}
	}

	/**
	 * Validazione upload file per l'azienda.
	 *
	 * @param target
	 * @param errors
	 * @param azienda
	 * @param codiceServizio
	 */
	public void validate(Object target, Errors errors, Azienda azienda, String codiceServizio) {

		UploadItem upload = (UploadItem) target;

		List<MultipartFile> files = upload.getMultipartFiles();
		MultipartFile generatedfile = upload.getGeneratedFile();

		if (generatedfile == null || generatedfile.isEmpty()) {
			errors.rejectValue("generatedFile", "required.fileUpload");
		}

		try {
			if (!generatedfile.isEmpty()) {
				String checksum = null;
				if (upload.isSignedFiles()) {
					boolean verifica = SignatureManager.checkPkcs7Signature(generatedfile.getBytes(), null, null);
					if (!verifica) {
						errors.rejectValue("generatedFile", "error.checkPkcs7Signature");
					}

					for (int i = 0; i < files.size(); i++) {
						log.debug("Test upload: " + i + " = " + files.get(i).getSize());
						if (files.get(i).getSize() > 0) {
							boolean verifica2 = SignatureManager.checkPkcs7Signature(files.get(i).getBytes(), null, null);
							if (!verifica2) {
								errors.rejectValue("multipartFiles[" + i + "]", "error.checkPkcs7Signature");
							}
						}
						else {
							errors.rejectValue("multipartFiles[" + i + "]", "NotEmpty.field.required");
						}
					}
					if (verifica) {
						// recupero dal doc firmato quello originale
						byte[] data = SignatureManager.getData(generatedfile.getBytes());
						// mi ricalcolo il checksum
						checksum = CheckSumGenerator.generateCheckSum(data);
					}
				}
				else {
					checksum = CheckSumGenerator.generateCheckSum(generatedfile.getBytes());
					for (int i = 0; i < files.size(); i++) {
						log.debug("Test upload: " + i + " = " + files.get(i).getSize());
						if (files.get(i).getSize() == 0) {
							errors.rejectValue("multipartFiles[" + i + "]", "NotEmpty.field.required");
						}
					}
				}
				log.debug("checksum=" + checksum);
				// verifico se esiste nel db una tupla corrispondente all'impronta ottenuta
				Servizio servizio = servizioRepository.findByCodiceServizio(codiceServizio);
				FascicoloUtente fascicolo = repositoryFascicolo.findByAzienda(azienda);
				RichiestaServizio richiesta = repositoryRihciesta.findByFascicoloAndChecksumAndServizio(fascicolo, checksum, servizio);
				if (richiesta == null) {
					errors.rejectValue("generatedFile", "error.checkSum");
				}
			}
		}
		catch (IOException ioe) {
			errors.rejectValue("generatedFile", "error.fileUpload");
		}
		catch (SignatureVerificationException e) {
			errors.rejectValue("generatedFile", "error.getData");
			log.error("validate :: " + e.getMessage(), e);
		}
	}
}
