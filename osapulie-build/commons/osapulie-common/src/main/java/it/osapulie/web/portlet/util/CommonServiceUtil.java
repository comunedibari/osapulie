/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.web.portlet.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import it.osapulie.service.impl.AbstractServiceImpl;
import it.osapulie.servizi.web.ws.types.StradarioRichiesta;
import it.osapulie.servizi.web.ws.types.StradarioRisposta;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.web.WebLayerException;

/**
 * @author Gianluca Pindinelli
 *
 */
@Service
public class CommonServiceUtil extends AbstractServiceImpl {

	private final Logger log = LoggerFactory.getLogger(CommonServiceUtil.class.getName());

	@Value("#{applicationProperties['evaluation.service.enable']}")
	private Boolean evaluationServiceEnable;

	@Value("#{applicationProperties['evaluation.service.token.url']}")
	private String evaluationServiceTokenUrl;

	@Value("#{applicationProperties['evaluation.service.url']}")
	private String evaluationServiceUrl;

	public String getEvaluationServiceUrl(String codiceServizio) {

		String result = null;
		// Caricamento token
		try {
			URL evaluationServiceTokenURL = new URL(evaluationServiceTokenUrl);

			HttpURLConnection evaluationServiceTokenHttpConection = (HttpURLConnection) evaluationServiceTokenURL.openConnection();
			evaluationServiceTokenHttpConection.setRequestMethod("GET");

			int responseCode = evaluationServiceTokenHttpConection.getResponseCode();
			log.debug("getEvaluationServiceUrl :: Sending 'GET' request to URL : " + evaluationServiceTokenUrl);
			log.debug("getEvaluationServiceUrl :: Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(evaluationServiceTokenHttpConection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			log.debug("getEvaluationServiceUrl :: Response : " + response.toString());

			JsonElement jelement = new JsonParser().parse(response.toString());
			JsonObject jobject = jelement.getAsJsonObject();
			jobject = jobject.getAsJsonObject("risposta");
			String code = jobject.get("code").getAsString();
			String token = jobject.get("token").getAsString();

			log.debug("getEvaluationServiceUrl :: code : " + code);
			log.debug("getEvaluationServiceUrl :: token : " + token);

			// Generazione URL
			if (token != null && !token.trim().equals("") && code.equals("")) {
				result = evaluationServiceUrl + codiceServizio + "&token=" + token;
			}
			else {
				throw new WebLayerException("Errore durante la generazione della URL per raggiungere il sistema di valutazione :: code : " + code);
			}
		}
		catch (MalformedURLException e) {
			log.error("getEvaluationServiceUrl :: " + e.getMessage(), e);
			throw new WebLayerException("Errore durante la generazione della URL per raggiungere il sistema di valutazione ", e);
		}
		catch (IOException e) {
			log.error("getEvaluationServiceUrl :: " + e.getMessage(), e);
			throw new WebLayerException("Errore durante la generazione della URL per raggiungere il sistema di valutazione ", e);
		}

		return result;
	}

	/**
	 * Metodo che invoca la porta e restituisce i dati relativi allo stradario (ricerca in base ai
	 * parametri in input).
	 *
	 * @param richiesta
	 * @param userPreferences
	 * @return
	 */
	public StradarioRisposta richiediStradario(StradarioRichiesta richiesta, UserPreferences userPreferences) {

		log.debug("richiediStradario :: entering method");

		return esegui("stradarioService", richiesta, StradarioRisposta.class, userPreferences);
	}

}
