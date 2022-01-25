package it.eng.tz.avtmb.integration.client;

import it.eng.tz.avtmb.integration.client.dto.StampSignAuthResponse;
import it.eng.tz.avtmb.integration.client.dto.StampSignRequest;
import it.eng.tz.avtmb.integration.client.dto.StampSignResponse;

import java.util.UUID;

public interface StampsignClientService {
    public StampSignAuthResponse stampSignAuth(String aoo) throws Exception;
    public StampSignResponse stampSign(String accessToken, StampSignRequest stampSignReq) throws Exception;
    public StampSignRequest fillStampSignRequest(byte[] reportBytes, String reportSubject, String codiceCatastale, UUID uuidRequest) throws Exception;
}
