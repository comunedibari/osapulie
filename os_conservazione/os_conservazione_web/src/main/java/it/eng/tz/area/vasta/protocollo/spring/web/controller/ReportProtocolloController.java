package it.eng.tz.area.vasta.protocollo.spring.web.controller;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.eng.tz.area.vasta.protocollo.spring.web.dto.BaseResponse;

@RestController
@RequestMapping("/rest")
public class ReportProtocolloController {
	private static final Logger logger = LoggerFactory.getLogger(ReportProtocolloController.class.getName());



	@RequestMapping(method = { RequestMethod.GET }, value = { "/protected/reportProtocollazione" }, produces = {"application/json" })
	public ResponseEntity<BaseResponse<String>> reportProtocolli(@RequestParam(name="from", required=false) String from, @RequestParam(name="to", required=false) String to) {
		
		BaseResponse<String> result = new BaseResponse<>();
		result.setEsitoOperazione(HttpStatus.OK.value());
		result.setDescrizioneOperazione("OK");
		result.setNumeroOggettiRestituiti(1);
		result.setNumeroOggettiTotali(1);
		result.setPayload(Collections.singletonList("Ciao"));
		return ResponseEntity.ok().body(result);
	}
}
