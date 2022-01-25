package it.eng.tz.avtmb.integration.client;


import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.Stub;

import org.apache.axis.client.Call;
import org.apache.axis.types.NormalizedString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import it.eng.tz.avtmb.integration.client.dto.Client;
import it.eng.tz.avtmb.integration.client.dto.DocumentStampSignAuthPt;
import it.eng.tz.avtmb.integration.client.dto.StampSignAuthRequest;
import it.eng.tz.avtmb.integration.client.dto.StampSignAuthResponse;

public class GetTokenService {

	protected Logger logger = LoggerFactory.getLogger(GetTokenService.class.getName());

    
    private String stampsignURL = "http://timbro.avmtb/ressvr/soap/stampsignwsdl.wsdl";
    
    private String stampsignClientId;
    
    private String stampsignClientSecret;
   
    private static final QName stampsignQName = new QName("http://stampsign.wsdl.avtmb.tz.eng.it", "stampsign");

    public StampSignAuthResponse stampSignAuth(String aoo) throws Exception {
        URL baseUrl;
        baseUrl = StampsignClientServiceImpl.class.getResource(".");
        URL url = null;
        StampSignAuthResponse response = null;
        try {
            url = new URL(baseUrl, stampsignURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            StampSignService service = new StampSignServiceLocator(stampsignURL, stampsignQName);
            DocumentStampSignAuthPt signAuthPt = service.getdocumentStampSignAuthBinding();
            StampSignAuthRequest request = new StampSignAuthRequest();
            Client client = new Client();
            client.setAoo(new NormalizedString(aoo));
            client.setClientId(new NormalizedString(stampsignClientId));
            request.setClient(client);
            ((Stub) signAuthPt)._setProperty(Call.USERNAME_PROPERTY, this.stampsignClientId);
            ((Stub) signAuthPt)._setProperty(Call.PASSWORD_PROPERTY, this.stampsignClientSecret);
            response = signAuthPt.documentStampSignAuth(request);
            if (response == null )
                throw new Exception("Impossibile conttarre il servizio di autenticazione AVMTB");
            if (response.getErrore() != null)
                throw new Exception("Errore " + response.getErrore().getCodice() + " - " + response.getErrore().getMessaggio());
            if (logger.isDebugEnabled())
                logger.debug("Token Retreived  for AOO: " + aoo + " - accessToken: " + response.getToken().getAccessToken().toString());
        } catch (ServiceException e) {
            logger.error("Token Retreived  for AOO: " + aoo, e.getMessage());
            throw new Exception("Impossibile conttarre il servizio di autenticazione AVMTB", e);
        } catch (RemoteException e) {
            logger.error("Errot in Token Retreive  for AOO: " + aoo, e.getMessage());
            throw new Exception("Impossibile conttarre il servizio di autenticazione AVMTB", e);
        }
        logger.info("Token Retreived  for AOO " + aoo);
        return response;
    }

	public void setStampsignClientId(String stampsignClientId) {
		this.stampsignClientId = stampsignClientId;
	}

	public void setStampsignClientSecret(String stampsignClientSecret) {
		this.stampsignClientSecret = stampsignClientSecret;
	}
    
    
}
