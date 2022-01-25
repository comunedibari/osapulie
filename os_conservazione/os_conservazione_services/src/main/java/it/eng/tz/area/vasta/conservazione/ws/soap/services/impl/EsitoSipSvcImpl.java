package it.eng.tz.area.vasta.conservazione.ws.soap.services.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.activation.DataHandler;
import javax.xml.bind.JAXBElement;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.xml.transform.StringResult;

import it.eng.tz.area.vasta.conservazione.util.HttpHeaderUtil;
import it.eng.tz.area.vasta.conservazione.ws.exception.WsIrisException;
import it.eng.tz.area.vasta.conservazione.ws.sip.dao.model.ConservazioneSipModel;
import it.eng.tz.area.vasta.conservazione.ws.sip.result.client.CodiceEsitoElabSIP;
import it.eng.tz.area.vasta.conservazione.ws.sip.result.client.SIPResult;
import it.eng.tz.area.vasta.conservazione.ws.sip.result.client.SIPResult.ElaborazioneSIP;
import it.eng.tz.area.vasta.conservazione.ws.sip.service.ConservazioneSipSvc;
import it.eng.tz.area.vasta.conservazione.ws.soap.callbacks.IrisWsCallback;
import it.eng.tz.area.vasta.conservazione.ws.soap.esito.sip.client.Attachment;
import it.eng.tz.area.vasta.conservazione.ws.soap.esito.sip.client.EsitoSip;
import it.eng.tz.area.vasta.conservazione.ws.soap.esito.sip.client.EsitoSip.SipEsito;
import it.eng.tz.area.vasta.conservazione.ws.soap.esito.sip.client.EsitoSipResponse;
import it.eng.tz.area.vasta.conservazione.ws.soap.esito.sip.client.ObjectFactory;
import it.eng.tz.area.vasta.conservazione.ws.soap.esito.sip.client.SipEsitoResponse;
import it.eng.tz.area.vasta.conservazione.ws.soap.esito.sip.client.WsGenericServiceOutput;
import it.eng.tz.area.vasta.conservazione.ws.soap.results.ControlloEsitoSipResult;
import it.eng.tz.area.vasta.conservazione.ws.soap.results.WsControlloEsitoSipResultSuccess;
import it.eng.tz.area.vasta.conservazione.ws.soap.results.WsSipResultError;
import it.eng.tz.area.vasta.conservazione.ws.soap.services.EsitoSipSvc;
import it.eng.tz.area.vasta.protocollo.utils.Utils;

@Service
public class EsitoSipSvcImpl implements EsitoSipSvc {
	private static final Logger logger = LoggerFactory.getLogger(ReceiveSipSvcImpl.class.getName());
	@Autowired
	@Qualifier("esitoSipWsTemplate")
	private WebServiceTemplate wsTmp;
	@Value("${area.vasta.conservazione.sip.basic.username}")
	private String username;
	@Value("${area.vasta.conservazione.sip.basic.password}")
	private String password;
	@Value("${area.vasta.conservazione.ws.esito.sip.url}")
	private String wsEsitoSipUrl;
	private static final String TASK_ID = EsitoSipSvcImpl.class.getName();
	@Autowired
	@Qualifier("sipResultMarshaller")
	private Jaxb2Marshaller sipResultMarshaller;
	@Autowired
	private ConservazioneSipSvc css;
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ControlloEsitoSipResult controllaConservazione(String idSip) throws WsIrisException {
		if(!StringUtils.hasText(idSip)) {
			throw new IllegalArgumentException("Impossibile proseguire. Passato un ID SIP nullo o vuoto");
		}
		String errorMessage = "";
		StopWatch sw = new StopWatch(TASK_ID);
		try {
			ObjectFactory of = new ObjectFactory();
			EsitoSip es = of.createEsitoSip();
			SipEsito se = of.createEsitoSipSipEsito();
			se.setIdSip(idSip);
			es.setSipEsito(se);
			String soapAction = "";
			List<HttpHeaderUtil> httpHeaders = new ArrayList<>(2);
			httpHeaders.add(new HttpHeaderUtil("H_P_APP_PWD", password));
			httpHeaders.add(new HttpHeaderUtil("H_P_APP_ID", username));
			sw.start("CONTROLLO ESITO SIP "+idSip);
			JAXBElement<EsitoSip> theRequest = of.createEsitoSip(es);
			JAXBElement<EsitoSipResponse> wsResult = (JAXBElement<EsitoSipResponse>) this.wsTmp.marshalSendAndReceive(wsEsitoSipUrl, theRequest, new IrisWsCallback(httpHeaders, null, soapAction) );
			sw.stop();
			WsControlloEsitoSipResultSuccess successo = new WsControlloEsitoSipResultSuccess();
			EsitoSipResponse response = wsResult.getValue();
			Attachment att = response.getSipEsitoResponse().getEsitoSip();
			SIPResult sipResult = null;
			boolean erroreElaborazioneSip = false;
			if(att != null) {
				
				sipResult = toSipResult(att.getDh());
				ElaborazioneSIP elSip = sipResult.getElaborazioneSIP();
				CodiceEsitoElabSIP codElab = elSip.getEsito().getCodice();
				switch (codElab) {
				case OK:
					erroreElaborazioneSip = false;
					break;

				default:
					erroreElaborazioneSip = true;
					break;
				}
			}
			SipEsitoResponse sipResp = response.getSipEsitoResponse();
			if( StringUtils.hasText(sipResp.getStatoSip()) )
			{
				successo.setStatoSip(sipResp.getStatoSip());
				successo.setSipId("NB");
			}
			WsGenericServiceOutput wso = sipResp.getServiceReturn();
			successo.setSipCode(wso.getCodEsito());
			successo.setSipMessage(wso.getDesEsito());
			WsSipResultError errore = new WsSipResultError();
			errore.setReceiveSipErrorCode(wso.getErrCode());
			errore.setReceiveSipErrorMessage(wso.getErrMessage());
			ControlloEsitoSipResult result = new ControlloEsitoSipResult(successo, errore, sipResult);
			//Controllo se ci sono errori o meno ed aggiorno l'eventuale record
			ConservazioneSipModel csm = new ConservazioneSipModel();
			csm.setId(idSip);
			csm.setDataProcessamento(new Date());
			csm.setErrore(result.isRicevutoErrore());
			if( erroreElaborazioneSip )
			{
				StringResult sr = new StringResult();
				sipResultMarshaller.marshal(sipResult, sr);
				String sipResultXml = sr.toString();
				csm.setSipResultXml(sipResultXml);
				csm.setErrore(true);
			}
			csm.setProcessato(true);
			css.aggiornaConservazioneSip(csm);
			return result;
		} catch (Exception e) {
			
			errorMessage = "Errore nel controllo conservazione per id SIP "+idSip+"; "+e.getMessage();
			logger.error(errorMessage, e);
			throw new WsIrisException(e); 
		}
		finally {
			boolean forcedStop = false;
			if( sw.isRunning() )
			{
				forcedStop = true;
				sw.stop();
			}
			Utils.printTaskInfo(logger, sw, forcedStop, errorMessage);
		}
	}
	@Override
	public SIPResult toSipResult(DataHandler dh) throws WsIrisException {
		if( dh == null )
		{
			if( logger.isInfoEnabled() )
			{
				logger.info("Nessun datahandler passato in ingresso. Restituisco null");
			}
			return null;
		}
		InputStream is = null;
		try {
			is = dh.getInputStream() != null ? dh.getInputStream() : dh.getDataSource().getInputStream() != null ? dh.getDataSource().getInputStream() : null;
			if( is == null )
			{
				if( logger.isWarnEnabled() )
				{
					logger.warn("Impossibile effettuare unmarshaller dell'attachment. Nessun inputstream trovato");
				}
				return null;
			}
			return (SIPResult) this.sipResultMarshaller.unmarshal(new StreamSource(is));
		} catch (Exception e) {
			
			String message = "Errore nel recupero delle informazioni esito SIP (unmarshalling di SIPResult.xml)";
			logger.error(message, e);
			throw new WsIrisException(message, e);
		} finally {
			if( is != null ) {
				
				try {
					is.close();
				} catch (IOException e) {
					logger.warn("Errore nella chiusura dell'input stream", e);
				}
			}
		}
	}
}