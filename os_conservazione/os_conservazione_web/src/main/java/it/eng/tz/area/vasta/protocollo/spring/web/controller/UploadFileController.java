package it.eng.tz.area.vasta.protocollo.spring.web.controller;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import it.eng.tz.area.vasta.protocollo.spring.web.dto.FileUploadResponseDto;
import it.eng.tz.area.vasta.protocollo.spring.web.dto.UploadedFileDto;

@RestController
@RequestMapping("/rest")
public class UploadFileController {
	private static final Logger logger = LoggerFactory.getLogger(UploadFileController.class.getName());

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(method = { RequestMethod.POST }, value = { "/protected/uploadRiversamenti" })
	public ResponseEntity<FileUploadResponseDto> uploadRiversamentoManuale(	
																			@RequestParam(required = true, value = "uploadedRiversamenti") MultipartFile mpf,
																			@RequestParam(required = true, value = "motivazione") String motivazione
																		   )

	{
		List<UploadedFileDto> uploadedFiles = null;
		String fileName = mpf.getOriginalFilename();
		if( logger.isDebugEnabled() )
		{
			logger.debug("Nome file uploadato: "+fileName);
		}
		try {
			//TODO: Leggere e creare i protocolli TUTTO IN UNA SOLA TRANSAZIONE e.g. riveramentiManualiSvc.salvaRiveramentiManuali(protocolliManuali);
		} catch (Exception e) {
			String message = "Errore nell'upload del file dei riversamenti manuali "+fileName+"; "+e.getMessage();
			logger.error(message, e);
			uploadedFiles = Collections.singletonList(new UploadedFileDto("", mpf.getOriginalFilename(), mpf.getSize(), "", "", "", "", message));
		}
		// Restituisco sempre un HTTP Status 200 ma con un array di files in cui
		// eventualmente è contenuto il messaggio di errore
		return new ResponseEntity<FileUploadResponseDto>(new FileUploadResponseDto(uploadedFiles), HttpStatus.OK);
	}
}
