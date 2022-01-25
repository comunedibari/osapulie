package it.osapulie.pratiche.web.portlet.varie;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import it.linksmt.tools.versig.SignatureManager;
import it.linksmt.tools.versig.exception.SignatureVerificationException;
import it.osapulie.pratiche.web.ws.types.DocInfo;

@Component("docUploadValidator")
public class DocUploadValidator implements Validator {

	private final Logger log = LoggerFactory.getLogger(DocUploadValidator.class.getName());

	@Override
	public boolean supports(Class<?> clazz) {
		return UploadItemPratiche.class.isAssignableFrom(clazz);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		UploadItemPratiche upload = (UploadItemPratiche) target;

		File generatedfile = upload.getGeneratedFile();

		DocInfo documento = upload.getDocumento();

		if (documento == null) {
			if (generatedfile == null || generatedfile.length() == 0) {
				errors.rejectValue("generatedFile", "NotEmpty.field.required");
			}
			if (generatedfile != null && generatedfile.length() > 10485760) {
				errors.rejectValue("generatedFile", "Maximum.upload.size.exceded");
			}

			try {
				if (generatedfile != null && generatedfile.length() > 0 && generatedfile.length() <= 10485760) {

					byte[] filebyte = FileCopyUtils.copyToByteArray(generatedfile);

					boolean verifica = false;
					try {
						verifica = SignatureManager.checkPkcs7Signature(filebyte, null, null);
					}
					catch (SignatureVerificationException e) {
						log.error("validate :: " + e.getMessage(), e);
					}
					if (!verifica) {
						errors.rejectValue("generatedFile", "error.checkPkcs7Signature");
					}
				}
			}
			catch (IOException ioe) {
				errors.rejectValue("generatedFile", "error.fileUpload");
			}

		}
		else {

			if (documento.getFile() == null || documento.getFile().getContent().length == 0) {
				errors.rejectValue("generatedFile", "NotEmpty.field.required");
			}
			if (documento.getFile() != null && documento.getFile().getContent().length > 10485760) {
				errors.rejectValue("generatedFile", "Maximum.upload.size.exceded");
			}
			if (documento.getFile() != null && documento.getFile().getContent().length > 0 && documento.getFile().getContent().length <= 10485760) {

				byte[] filebyte = documento.getFile().getContent();

				boolean verifica = false;
				try {
					verifica = SignatureManager.checkPkcs7Signature(filebyte, null, null);
				}
				catch (SignatureVerificationException e) {
					log.error("validate :: " + e.getMessage(), e);
				}
				if (!verifica) {
					errors.rejectValue("generatedFile", "error.checkPkcs7Signature");
				}
			}
		}
	}
}
