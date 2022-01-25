package it.eng.tz.area.vasta.conservazione.ws.soap.services.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.xml.bind.JAXBElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.ws.client.core.WebServiceTemplate;

import it.eng.tz.area.vasta.conservazione.util.HttpHeaderUtil;
import it.eng.tz.area.vasta.conservazione.ws.exception.WsIrisException;
import it.eng.tz.area.vasta.conservazione.ws.sip.dao.model.ConservazioneSipModel;
import it.eng.tz.area.vasta.conservazione.ws.sip.service.ConservazioneSipSvc;
import it.eng.tz.area.vasta.conservazione.ws.soap.callbacks.IrisWsCallback;
import it.eng.tz.area.vasta.conservazione.ws.soap.receive.sip.client.Attachment;
import it.eng.tz.area.vasta.conservazione.ws.soap.receive.sip.client.ObjectFactory;
import it.eng.tz.area.vasta.conservazione.ws.soap.receive.sip.client.ReceiveSip;
import it.eng.tz.area.vasta.conservazione.ws.soap.receive.sip.client.ReceiveSip.SipReceive;
import it.eng.tz.area.vasta.conservazione.ws.soap.receive.sip.client.ReceiveSipResponse;
import it.eng.tz.area.vasta.conservazione.ws.soap.receive.sip.client.SipReceiveResponse;
import it.eng.tz.area.vasta.conservazione.ws.soap.receive.sip.client.WsGenericServiceOutput;
import it.eng.tz.area.vasta.conservazione.ws.soap.results.ReceiveSipResult;
import it.eng.tz.area.vasta.conservazione.ws.soap.results.WsSipResultError;
import it.eng.tz.area.vasta.conservazione.ws.soap.results.WsSipResultSuccess;
import it.eng.tz.area.vasta.conservazione.ws.soap.services.ReceiveSipSvc;

@Service
public class ReceiveSipSvcImpl implements ReceiveSipSvc {
	private static final Logger logger = LoggerFactory.getLogger(ReceiveSipSvcImpl.class.getName());
	@Autowired
	@Qualifier("receiveSipWsTemplate")
	private WebServiceTemplate wsTmp;
	@Value("${area.vasta.conservazione.sip.basic.username}")
	private String username;
	@Value("${area.vasta.conservazione.sip.basic.password}")
	private String password;
	@Value("${area.vasta.conservazione.ws.receive.sip.url}")
	private String wsReceiveSipUrl;
	@Autowired
	private ConservazioneSipSvc css;

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ReceiveSipResult inviaConservazione(String tipoSip, String tipoProtezione, String improntaAlgoritmo,
			String improntaCodifica, String impronta, File fileDaConservare,String folder) throws WsIrisException {
		if( fileDaConservare == null )
		{
			throw new IllegalArgumentException("Impossibile proseguire con la conservazione. Archivio ZIP nullo");
		}
		if( !StringUtils.hasText(improntaAlgoritmo) )
		{
			throw new IllegalArgumentException("Impossibile proseguire con la conservazione. Impronta algoritmo nulla");
		}		
		if( !StringUtils.hasText(improntaCodifica) )
		{
			throw new IllegalArgumentException("Impossibile proseguire con la conservazione. Impronta codifica nulla");
		}		
		if( !StringUtils.hasText(impronta) )
		{
			throw new IllegalArgumentException("Impossibile proseguire con la conservazione. Impronta arichivio da conservare nulla");
		}		
		if( !StringUtils.hasText(tipoSip) )
		{
			if( logger.isWarnEnabled() )
			{
				logger.warn("Passato tipoSip nullo o vuoto, Setto valore di default");
			}
			tipoSip = "?";
		}
		if( !StringUtils.hasText(tipoProtezione) )
		{
			if( logger.isWarnEnabled() )
			{
				logger.warn("Passato tipoProtezione nullo o vuoto, Setto valore di default");
			}
			tipoProtezione = "?";
		}
		ObjectFactory of = new ObjectFactory();
		ReceiveSip receiveSipReq = of.createReceiveSip();
		SipReceive sr = of.createReceiveSipSipReceive();
		sr.setImpronta(impronta);
		sr.setImprontaAlgoritmo(improntaAlgoritmo);
		sr.setImprontaCodifica(improntaCodifica);
		sr.setTipoProtezione(tipoProtezione);
		sr.setTipoSip(tipoSip);
		receiveSipReq.setSipReceive(sr);
		List<Attachment> attachments = receiveSipReq.getArg1();
		Attachment attachment = of.createAttachment();
		attachment.setDh(new DataHandler(new FileDataSource(fileDaConservare)));
		attachments.add(attachment);
		try {

			String soapAction = "";
			JAXBElement<ReceiveSip> theRequest = of.createReceiveSip(receiveSipReq);
			List<HttpHeaderUtil> httpHeaders = new ArrayList<>(2);
			httpHeaders.add(new HttpHeaderUtil("H_P_APP_PWD", password));
			httpHeaders.add(new HttpHeaderUtil("H_P_APP_ID", username));
			JAXBElement<ReceiveSipResponse> wsResult = (JAXBElement<ReceiveSipResponse>) this.wsTmp.marshalSendAndReceive(wsReceiveSipUrl, theRequest, new IrisWsCallback(httpHeaders, null, soapAction) );
			SipReceiveResponse srr = wsResult.getValue().getSipReceiveResponse();
			WsSipResultSuccess successo = new WsSipResultSuccess();
			if( StringUtils.hasText(srr.getIdSip()) )
			{
				successo.setSipId(srr.getIdSip());
			}
			WsSipResultError errore = new WsSipResultError();
			if( srr != null && srr.getServiceReturn() != null )
			{
				WsGenericServiceOutput output = srr.getServiceReturn();
				successo.setSipCode(output.getCodEsito());
				successo.setSipMessage(output.getDesEsito());
				errore.setReceiveSipErrorCode(output.getErrCode());
				errore.setReceiveSipErrorMessage(output.getErrMessage());
			}
			ReceiveSipResult result = new ReceiveSipResult(successo, errore);
			if( logger.isDebugEnabled() )
			{
				logger.debug("Ricevuto questo risultato da RECEIVE SIP {}", result);
			}
			//Se l'invocazione del WS è OK inserisco il record nel DB
			if( !result.isRicevutoErrore() ) {
				ConservazioneSipModel csm = new ConservazioneSipModel();
				csm.setId(srr.getIdSip());
				csm.setFolder(folder);
				css.salvaConservazioneSip(csm);
			}
			return result;
		} catch (Exception e) {
			 
			logger.error("Errore nell'invio del file di conservazione {}", fileDaConservare.getName(), e);
			throw new WsIrisException("Errore nell'invio del file di conservazione " + fileDaConservare.getName(), e);
		}
	}
}