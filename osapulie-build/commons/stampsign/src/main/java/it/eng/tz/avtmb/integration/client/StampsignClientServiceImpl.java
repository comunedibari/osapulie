package it.eng.tz.avtmb.integration.client;


import it.eng.tz.avtmb.integration.client.dto.*;
import org.apache.axis.client.Call;
import org.apache.axis.transport.http.HTTPConstants;
import org.apache.axis.types.Id;
import org.apache.axis.types.NormalizedString;
import org.apache.axis.types.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.Stub;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component("stampsignClientService")
public class StampsignClientServiceImpl implements StampsignClientService {

    protected Logger logger = LoggerFactory.getLogger(StampsignClientServiceImpl.class.getName());

    @Value("${area.vasta.stampsign.ws.url}")
    private String stampsignURL = "http://timbro.avmtb/ressvr/soap/stampsignwsdl.wsdl";
    @Value("${area.vasta.stampsign.ws.client.id}")
    private String stampsignClientId = "BARI_TIMBRO";
    @Value("${area.vasta.stampsign.ws.client.secret}")
    private String stampsignClientSecret = "timbroDigitale123$!";
    @Value("${area.vasta.stampsign.ws.signer}")
    private String stampsignSigner;
    @Value("${area.vasta.stampsign.ws.composizione.documento}")
    private String composizioneDocumento;
    @Value("${area.vasta.stampsign.ws.fine.validita.plus.month}")
    private Integer fineValiditaPlusMonth;
    @Value("${area.vasta.stampsign.ws.fine.verifica.plus.month}")
    private Integer fineVerificaPlusMonth;
    @Value("${area.vasta.stampsign.ws.iri.amministrazione.conservato}")
    private String iriAmministrazioneConservato;
    @Value("${area.vasta.stampsign.ws.modalita.verifica}")
    private String modalitaVerifica;
    @Value("${area.vasta.stampsign.ws.posizione.timbro}")
    private String posizioneTimbro;
    @Value("${area.vasta.stampsign.ws.titolo.amministrazione.prodotto}")
    private String titoloAmministrazioneProdotto;
    @Value("${area.vasta.stampsign.ws.iri.amministrazione}")
    private String iriAmministrazione;
    @Value("${area.vasta.stampsign.ws.single.stamp.size}")
    private String singleStampSize;
    @Value("${area.vasta.stampsign.ws.tipo.documento}")
    private String tipoDocumento;

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

    public StampSignResponse stampSign(String accessToken, StampSignRequest stampSignReq) throws Exception {
        URL baseUrl;
        baseUrl = StampsignClientServiceImpl.class.getResource(".");
        URL url = null;
        try {
            url = new URL(baseUrl, stampsignURL);
        } catch (MalformedURLException e) {
            logger.error("Preparing call", e.getMessage());
            throw new Exception("Impossibile invocare il servizip di StampSign AVMTB", e);
        }
        StampSignService service = new StampSignServiceLocator(stampsignURL, stampsignQName);
        DocumentStampSignPt stampSignPt = service.getdocumentStampSignBinding();
        Map<String, String> headers = new Hashtable<String, String>();
        headers.put("Authorization", "Bearer " + accessToken);
        ((Stub) stampSignPt)._setProperty(HTTPConstants.REQUEST_HEADERS, headers);
        StampSignResponse response = stampSignPt.documentStampSign(stampSignReq);
        logger.info("response Retreived  for StampSignRequest " + stampSignReq);
        return response;
    }


    public static void main(String args[]) throws Exception {
        StampsignClientServiceImpl service = new StampsignClientServiceImpl();
        StampSignAuthResponse response = service.stampSignAuth("c_a662");
        NormalizedString accessToken = response.getToken().getAccessToken();
        System.out.println(accessToken);
        StampSignRequest stampSignReq = fillStampSignRequest();
        service.stampSign(accessToken.toString(), stampSignReq);

    }

/*
    public StampSignRequest fillSignRequest(byte[] reportBytes, String reportSubject, UUID uuidRequest)throws Exception {
        StampSignRequest request = new StampSignRequest();
        Sign sign = new Sign();
        sign.setClientId(new NormalizedString(this.stampsignClientId));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sign.setTransactionId(new NormalizedString(uuidRequest.toString()));
        sign.setFileDocumento(reportBytes);
        request.setRequestType(RequestType.SIGN);
        request.setId(new Id("id_" + uuidRequest.toString()));
        request.setSign(sign);
        logger.info("fillStampSignRequest " + request);
        return request;
    }
*/


    public StampSignRequest fillStampSignRequest(byte[] reportBytes, String reportSubject, String codiceCatastale, UUID uuidRequest) throws Exception {
        StampSignRequest request = new StampSignRequest();
        StampSign stampSign = new StampSign();
        stampSign.setAuthority(new NormalizedString(codiceCatastale));
        stampSign.setClientId(new NormalizedString(this.stampsignClientId));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        stampSign.setDataDocumento(getCalendar(sdf.format(new Date()), "yyyy-MM-dd", 0));
        stampSign.setComposizioneDocumento(ComposizioneDocumento.fromValue(this.composizioneDocumento));
        stampSign.setDocumentInResponse(DocumentInResponseType.SI);
        ExtraMeta extraMeta = new ExtraMeta();
        extraMeta.setDataFineValidita(getCalendar(sdf.format(new Date()), "yyyy-MM-dd", this.fineValiditaPlusMonth));
        extraMeta.setDataFineVerifica(getCalendar(sdf.format(new Date()), "yyyy-MM-dd", this.fineVerificaPlusMonth));
        try {
            extraMeta.setIriAmministrazioneConservato(new URI(this.iriAmministrazioneConservato));
        } catch (URI.MalformedURIException e) {
            logger.error("fillStampSignRequest URI.MalformedURIException IriAmministrazioneConservato", e.getMessage());
            throw new Exception("Impossibile invocare il servizip di StampSigna AVMTB - fillStampSignRequest URI.MalformedURIException IriAmministrazioneConservato", e);
        }
        extraMeta.setModalitaVerifica(this.modalitaVerifica);
        extraMeta.setPosizioneTimbro(PosizioneTimbro.fromValue(this.posizioneTimbro));
        extraMeta.setTitoloAmministrazioneProdotto(this.titoloAmministrazioneProdotto);
        stampSign.setExtraMetaDoc(extraMeta);
        stampSign.setIdentificativoDocumento(new NormalizedString("CERTIFICATO"));
        try {
            stampSign.setIriAmministrazione(new URI(this.iriAmministrazione));
        } catch (URI.MalformedURIException e) {
            logger.error("fillStampSignRequest URI.MalformedURIException IriAmministrazione", e.getMessage());
            throw new Exception("Impossibile invocare il servizip di Timbratura AVMTB - fillStampSignRequest URI.MalformedURIException IriAmministrazione", e);
        }
        stampSign.setOggettoDocumento(new NormalizedString(reportSubject));
        //Qui andrà un controllo sul cod catastale per definire il signer corretto (Sindaci dei comuni)
        //per ora ci sono degli if con i valori cablati, da sostituire con i filter
        if (stampSign.getAuthority().equals("A662")) {
            stampSign.setSigner(new NormalizedString(this.stampsignSigner));
        }
        else if (stampSign.getAuthority().equals("H645")) {
            stampSign.setSigner(new NormalizedString("sindaco.ruvo"));
        }
        else if (stampSign.getAuthority().equals("A893")) {
            stampSign.setSigner(new NormalizedString("sindaco.bitonto"));
        }
        else if (stampSign.getAuthority().equals("L425")) {
            stampSign.setSigner(new NormalizedString("sindaco.triggiano"));
        }
        else if (stampSign.getAuthority().equals("B998")) {
            stampSign.setSigner(new NormalizedString("sindaco.cassano"));
        }
        else stampSign.setSigner(new NormalizedString(this.stampsignSigner));
        stampSign.setSingleStampSize(SingleStampSizeType.fromValue(this.singleStampSize));
        stampSign.setTipoDocumento(TipoDocumento.fromValue(this.tipoDocumento));
        stampSign.setTransactionId(new NormalizedString(uuidRequest.toString()));
        stampSign.setFileDocumento(reportBytes);
        request.setRequestType(RequestType.BOTH);
        request.setId(new Id("id_" + uuidRequest.toString()));
        request.setStampSign(stampSign);
        logger.info("fillStampSignRequest " + request);
        return request;
    }

    private static StampSignRequest fillStampSignRequest() {
        StampSignRequest request = new StampSignRequest();
        StampSign stampSign = new StampSign();
        stampSign.setAuthority(new NormalizedString("comune.bari"));
        stampSign.setClientId(new NormalizedString("BARI_TIMBRO"));
        stampSign.setDataDocumento(getCalendar("2020-05-05", "yyyy-MM-dd", 0));
        stampSign.setComposizioneDocumento(ComposizioneDocumento.paginaSingola);
        stampSign.setDocumentInResponse(DocumentInResponseType.SI);
        ExtraMeta extraMeta = new ExtraMeta();
        extraMeta.setDataFineValidita(getCalendar("2020-07-05", "yyyy-MM-dd", 0));
        extraMeta.setDataFineVerifica(getCalendar("2020-07-05", "yyyy-MM-dd", 0));
        extraMeta.setDataProtocolloRicevuto(getCalendar("2020-05-04", "yyyy-MM-dd", 0));
        try {
            extraMeta.setIriAmministrazioneConservato(new URI("http://www.comune.bari.it"));
        } catch (URI.MalformedURIException e) {
            e.printStackTrace();
        }
        extraMeta.setModalitaVerifica("EngTimbro");
        extraMeta.setNumeroProtocolloRicevuto(new NormalizedString("250548/2020"));
        extraMeta.setPosizioneTimbro(PosizioneTimbro.footer);
        extraMeta.setTitoloAmministrazioneProdotto("http://www.comune.bari.it");
        stampSign.setExtraMetaDoc(extraMeta);
        stampSign.setIdentificativoDocumento(new NormalizedString("CERTIFICATO"));
        try {
            stampSign.setIriAmministrazione(new URI("http://www.comune.bari.it"));
        } catch (URI.MalformedURIException e) {
            e.printStackTrace();
        }
        stampSign.setOggettoDocumento(new NormalizedString("CERTICATO"));
        stampSign.setSigner(new NormalizedString("a.cantatore"));
        stampSign.setSingleStampSize(SingleStampSizeType.QUARANTA);
        stampSign.setTipoDocumento(TipoDocumento.principale);
        stampSign.setTransactionId(new NormalizedString("37239482462"));
        try {
            stampSign.setFileDocumento(getBytesDocumento("C:\\Users\\gchirico\\Desktop\\TEST MUNICIPIA.pdf"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        request.setRequestType(RequestType.BOTH);
        request.setId(new Id("id_9c716376-a0d5-4a0b-ac92-4a8987189d96"));
        request.setStampSign(stampSign);
        return request;
    }

    private static byte[] getBytesDocumento(String s) throws IOException {
        Path path = Paths.get(s);
        byte[] data = Files.readAllBytes(path);
        return data;
    }


    public static GregorianCalendar getCalendar(String date, String pattern, int plusMonth) {
        GregorianCalendar calender = new GregorianCalendar();
        try {
            calender.setTime(stringToJavaDate(date, pattern));
            calender.add(Calendar.MONTH, plusMonth);
        } catch (ParseException e) {
            return null;
        }
        return calender;
    }

    public static Date stringToJavaDate(String sDate, String pattern) throws ParseException {
        Date date = null;
        date = new SimpleDateFormat(pattern, Locale.ITALY).parse(sDate);
        return date;
    }

    
    
}
