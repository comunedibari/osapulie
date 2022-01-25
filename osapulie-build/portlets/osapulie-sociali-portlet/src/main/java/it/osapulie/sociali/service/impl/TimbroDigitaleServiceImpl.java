package it.osapulie.sociali.service.impl;


import it.land.securepaperappliance.spservice.xsd.ObjectFactory;
import it.land.securepaperappliance.spservice.xsd.SPServiceResponse;
import it.land.securepaperappliance.spservice.xsd.SignerBean;
import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.TimbroConfig;
import it.osapulie.persistence.ComuneISARepository;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.sociali.service.TimbroDigitaleService;
import it.osapulie.web.ws.timbrodigitale.SPPortType;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.xml.bind.JAXBElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Implementazione di {@link TimbroDigitaleService} che interroga il servizio remoto offerto da
 * Ancitel che è in grado di confezionare un documento PDF contenente una certificazione anagrafica
 * emessa da un Ufficiale Anagrafico e quindi valida a tutti gli effetti di legge. Questo servizio
 * utilizza la tecnologia del Timbro Digitale.
 *
 * @author Maria Michela Birtolo
 */
@Service("timbroDigitaleService")
public class TimbroDigitaleServiceImpl implements TimbroDigitaleService {

	private final Logger log = LoggerFactory.getLogger(TimbroDigitaleServiceImpl.class);

	@Inject
	private SPPortType timbroDigitaleClient;

	@Inject
	private ComuneISARepository comuneISARepository;

	@Override
	public byte[] richiediCertificatoTimbrato(String xml, UserPreferences userPreferences) {

		JAXBElement<byte[]> certificato = null;
		log.debug("xml=" + xml);
		String codiceIstat = userPreferences.getCodiceIstatComune();
		ComuneISA comuneISA = comuneISARepository.findByCodiceIstat(codiceIstat);

		TimbroConfig config = comuneISA.getTimbroConfig();
		log.debug("TDService: username=" + config.getUsername());
		log.debug("TDService: psw=" + config.getPassword());
		log.debug("TDService: pin=" + config.getPin());
		log.debug("TDService: serviceId=" + config.getServiceId());
		log.debug("TDService: domain=" + config.getDomain());
		log.debug("TDService: signServerHost=" + config.getServerHost());

		// SPPortType port = timbroDigitaleClient.getSPHttpSoap11Endpoint();
		List<SignerBean> params = new ArrayList<SignerBean>();
		SignerBean param = new SignerBean();
		ObjectFactory fact = new ObjectFactory();
		param.setDomain(fact.createSignerBeanDomain(""));
		param.setUser(fact.createSignerBeanUser(""));
		param.setPassword(fact.createSignerBeanPassword(""));
		param.setPin(fact.createSignerBeanPin(""));
		param.setHsmIp(fact.createSignerBeanHsmIp(""));
		params.add(param);

		log.debug("prop=" + System.getProperty("javax.net.ssl.keyStore"));
		log.debug("prop=" + System.getProperty("javax.net.ssl.keyStoreType"));
		log.debug("prop=" + System.getProperty("javax.net.ssl.keyStorePassword"));
		log.debug("prop=" + System.getProperty("javax.net.ssl.trustStore"));
		log.debug("prop=" + System.getProperty("javax.net.ssl.trustStorePassword"));
		log.debug("prop=" + System.getProperty("javax.net.ssl.trustStoreType"));

		try {
			SPServiceResponse risposta = timbroDigitaleClient.securizeXML("" + config.getServiceId(), xml.getBytes(), params);
			log.debug("SPServiceResponseStatus=" + risposta.getStatus());
			log.debug("SPServiceResponseReason=" + risposta.getReason().getValue());
			certificato = risposta.getSecuredDocument();
		}
		catch (Exception e) {
			log.error("richiediCertificatoTimbrato :: " + e.getMessage(), e);
		}

		log.debug("certRicevuto=" + certificato);
		return certificato.getValue();
	}
}
