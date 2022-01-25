package eng.tz.pa.api.osa.web.controller;

import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import eng.tz.pa.api.osa.web.dto.BaseResponse;


@RestController
@RequestMapping("/rest")
public class MainRestController {
	private static final Logger logger = LoggerFactory.getLogger(MainRestController.class.getName());

	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(method = { RequestMethod.GET}, value = { "/protected/estendiSessione" })
	public ResponseEntity<BaseResponse<String>> estendiSessione()
	{
		BaseResponse<String> result = new BaseResponse<String>();
		String resultMsg = null;
		String message = null;
		HttpStatus status = null;
		try {
			resultMsg = "OK";
			status = HttpStatus.OK;
		} catch (Exception e) {
			message = "Errore nell'estensione della sessione utente";
			logger.error(message, e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			resultMsg = "KO";
		}
		result.setEsito(status.value());
		result.setNumeroOggettiRestituiti(1);
		result.setNumeroOggettiTotali(1);
		result.setPayload(Collections.singletonList(resultMsg));
		return new ResponseEntity<BaseResponse<String>>(result, status);
	}
	
	 
	
}
