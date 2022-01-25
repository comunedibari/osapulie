/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.portal.kernel.util.StringUtil;

import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici.ComponentiNucleoFamiliare;
import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.json.configurazione.Condizione;
import it.osapulie.domain.json.configurazione.Configurazione;
import it.osapulie.domain.json.configurazione.ConfigurazioneServizi;
import it.osapulie.domain.json.configurazione.InfoAggiuntive;
import it.osapulie.domain.json.configurazione.Servizio;
import it.osapulie.domain.json.segnalazione.SegnalazioneCustom;
import it.osapulie.service.ComuneISAService;
import it.osapulie.service.ConfigurazioneService;
import it.osapulie.service.ServizioService;

/**
 *
 * @author Gianluca Pindinelli
 *
 */
@Service("configurazioneService")
public class ConfigurazioneServiceImpl implements ConfigurazioneService {

	/**
	 *
	 */
	private static final String CONFIGURAZIONE_SEGNALAZIONE_CUSTOM = "segnalazione_custom";
	private static final String CONFIGURAZIONE_GENERAZIONE_CERTIFICATI = "generazione_certificati";
	private static final String CONFIGURAZIONE_STRADARIO = "stradario";
	private static final String CONFIGURAZIONE_INTEROPERABILITA = "interoperabilita";
	private static final String CONFIGURAZIONE_VISURA_POSIZIONE_TRIBUTARIA = "visura_posizione_tributaria";
	private static final String CONFIGURAZIONE_CAF = "caf";
	private static final String CONDIZIONE_ABILITATO = "abilitato";
	private static final String CONFIGURAZIONE_REDIRECT_SERVIZIO = "redirect_servizio";
	private static final String CONDIZIONE_FORMATO_DATA_YYYY_MM_DD = "yyyy-MM-dd";

	protected Logger log = LoggerFactory.getLogger(ConfigurazioneServiceImpl.class.getName());

	@Inject
	private ComuneISAService comuneISAService;

	@Inject
	private ServizioService servizioService;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.service.CommonService#getCondizioniGenerazioneCertificati(it.osapulie.anagrafe.
	 * web.ws.output.types.DatiAnagrafici.ComponentiNucleoFamiliare, java.lang.String, long)
	 */
	@Override
	public Map<String, Boolean> getCondizioniGenerazioneCertificati(ComponentiNucleoFamiliare componente, String codiceServizio, long idComuneISA) {

		Map<String, Boolean> result = null;

		List<Configurazione> configurazioni = getConfigurazioniServizioComune(codiceServizio, idComuneISA);
		if (configurazioni != null) {
			result = new HashMap<String, Boolean>();
			for (Configurazione configurazione : configurazioni) {
				String nomeConfigurazione = configurazione.getNome();
				if (nomeConfigurazione != null && nomeConfigurazione.equals(CONFIGURAZIONE_GENERAZIONE_CERTIFICATI)) {
					List<Condizione> condizioni = configurazione.getCondizioni();
					if (condizioni != null) {
						for (Condizione condizione : condizioni) {
							String nome = condizione.getNome();
							String campo = condizione.getCampo();
							String condizioneString = condizione.getCondizione();
							String valore = condizione.getValore();
							// Caricamento valore field da componente
							try {
								Method method = ComponentiNucleoFamiliare.class.getMethod("get" + StringUtil.upperCaseFirstLetter(campo));
								Class<?> returnType = method.getReturnType();
								// Calendar
								if (returnType.getCanonicalName().equals(Calendar.class.getCanonicalName())) {
									Calendar invokeResult = (Calendar) method.invoke(componente);
									SimpleDateFormat simpleDateFormat = new SimpleDateFormat(CONDIZIONE_FORMATO_DATA_YYYY_MM_DD);
									Date conditionValue = simpleDateFormat.parse(valore);
									if (condizioneString.equals(">=")) {
										result.put(nome, invokeResult.getTime().after(conditionValue) || invokeResult.getTime().equals(conditionValue));
									}
									else if (condizioneString.equals(">")) {
										result.put(nome, invokeResult.getTime().after(conditionValue));
									}
									else if (condizioneString.equals("<")) {
										result.put(nome, invokeResult.getTime().before(conditionValue));
									}
									else if (condizioneString.equals("<=")) {
										result.put(nome, invokeResult.getTime().before(conditionValue) || invokeResult.getTime().equals(conditionValue));
									}
									else if (condizioneString.equals("==")) {
										result.put(nome, invokeResult.getTime().equals(conditionValue));
									}
								}
								// Date
								else if (returnType.getCanonicalName().equals(Date.class.getCanonicalName())) {
									Date invokeResult = (Date) method.invoke(componente);
									SimpleDateFormat simpleDateFormat = new SimpleDateFormat(CONDIZIONE_FORMATO_DATA_YYYY_MM_DD);
									Date conditionValue = simpleDateFormat.parse(valore);
									if (condizioneString.equals(">=")) {
										result.put(nome, invokeResult.after(conditionValue) || invokeResult.equals(conditionValue));
									}
									else if (condizioneString.equals(">")) {
										result.put(nome, invokeResult.after(conditionValue));
									}
									else if (condizioneString.equals("<")) {
										result.put(nome, invokeResult.before(conditionValue));
									}
									else if (condizioneString.equals("<=")) {
										result.put(nome, invokeResult.before(conditionValue) || invokeResult.equals(conditionValue));
									}
									else if (condizioneString.equals("==")) {
										result.put(nome, invokeResult.equals(conditionValue));
									}
								}
								// Integer
								else if (returnType.getCanonicalName().equals(Integer.class.getCanonicalName())) {
									Integer invokeResult = (Integer) method.invoke(componente);
									Integer conditionValue = Integer.parseInt(valore);
									if (condizioneString.equals(">=")) {
										result.put(nome, invokeResult >= conditionValue);
									}
									else if (condizioneString.equals(">")) {
										result.put(nome, invokeResult > conditionValue);
									}
									else if (condizioneString.equals("<")) {
										result.put(nome, invokeResult < conditionValue);
									}
									else if (condizioneString.equals("<=")) {
										result.put(nome, invokeResult <= conditionValue);
									}
									else if (condizioneString.equals("==")) {
										result.put(nome, invokeResult == conditionValue);
									}
								}
								// Long
								else if (returnType.getCanonicalName().equals(Long.class.getCanonicalName())) {
									Long invokeResult = (Long) method.invoke(componente);
									Long conditionValue = Long.parseLong(valore);
									if (condizioneString.equals(">=")) {
										result.put(nome, invokeResult >= conditionValue);
									}
									else if (condizioneString.equals(">")) {
										result.put(nome, invokeResult > conditionValue);
									}
									else if (condizioneString.equals("<")) {
										result.put(nome, invokeResult < conditionValue);
									}
									else if (condizioneString.equals("<=")) {
										result.put(nome, invokeResult <= conditionValue);
									}
									else if (condizioneString.equals("==")) {
										result.put(nome, invokeResult == conditionValue);
									}
								}
							}
							catch (NoSuchMethodException e) {
								log.error("checkConditions :: " + e.getMessage(), e);
							}
							catch (SecurityException e) {
								log.error("checkConditions :: " + e.getMessage(), e);
							}
							catch (IllegalAccessException e) {
								log.error("checkConditions :: " + e.getMessage(), e);
							}
							catch (IllegalArgumentException e) {
								log.error("checkConditions :: " + e.getMessage(), e);
							}
							catch (InvocationTargetException e) {
								log.error("checkConditions :: " + e.getMessage(), e);
							}
							catch (ParseException e) {
								log.error("checkConditions :: " + e.getMessage(), e);
							}
						}
					}
				}
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.CommonService#getCondizioneGenerazioneCertficiati(java.lang.String,
	 * java.lang.String, long)
	 */
	@Override
	public String getCondizioneGenerazioneCertficiati(String nomeCondizione, String codiceServizio, long idComuneISA) {

		List<Configurazione> configurazioni = getConfigurazioniServizioComune(codiceServizio, idComuneISA);
		if (configurazioni != null) {
			for (Configurazione configurazione : configurazioni) {
				List<Condizione> condizioni = configurazione.getCondizioni();
				if (condizioni != null) {
					for (Condizione condizione : condizioni) {
						String conditzioneString = condizione.getNome();
						if (conditzioneString != null && conditzioneString.equals(nomeCondizione)) {
							String valore = condizione.getValore();
							return valore;
						}
					}
				}
			}
		}

		return null;
	}

	private List<Configurazione> getConfigurazioniServizioComune(String codiceServizio, long idComuneISA) {

		ConfigurazioneServizi configurazioneServizi = getConfigurazioneServiziComune(idComuneISA);
		if (configurazioneServizi != null) {
			List<Servizio> servizi = configurazioneServizi.getServizi();
			if (servizi != null) {
				for (it.osapulie.domain.json.configurazione.Servizio servizio : servizi) {
					if (servizio.getCodice().equals(codiceServizio)) {
						return servizio.getConfigurazioni();
					}
				}
			}
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.CommonService#isStradarioEnable(java.lang.String, long)
	 */
	@Override
	public boolean isStradarioEnable(String codiceServizio, long idComuneISA) {

		Configurazione configurazione = getConfigurazioneServizioComune(codiceServizio, idComuneISA, CONFIGURAZIONE_STRADARIO);
		if (configurazione != null && configurazione.getNome() != null) {
			List<Condizione> condizioni = configurazione.getCondizioni();
			if (condizioni != null) {
				for (Condizione condizione : condizioni) {
					String nome = condizione.getNome();
					if (nome != null && nome.equals(CONDIZIONE_ABILITATO)) {
						if (Boolean.parseBoolean(condizione.getValore())) {
							return true;
						}
					}
				}
			}
		}

		return false;

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.CommonService#isServizioEnableForCAF(java.lang.String, long)
	 */
	@Override
	public boolean isServizioEnableForCAF(String codiceServizio, long idComuneISA) {

		Configurazione configurazione = getConfigurazioneServizioComune(codiceServizio, idComuneISA, CONFIGURAZIONE_CAF);
		if (configurazione != null && configurazione.getNome() != null) {
			List<Condizione> condizioni = configurazione.getCondizioni();
			if (condizioni != null) {
				for (Condizione condizione : condizioni) {
					String nome = condizione.getNome();
					if (nome != null && nome.equals(CONDIZIONE_ABILITATO)) {
						if (Boolean.parseBoolean(condizione.getValore())) {
							return true;
						}
					}
				}
			}
		}

		return false;

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.CommonService#getConfigurazioneServiziComune(long)
	 */
	@Override
	public ConfigurazioneServizi getConfigurazioneServiziComune(long idComuneISA) {

		ConfigurazioneServizi configurazioneServizi = null;

		InfoAggiuntive infoAggiuntive = getInfoAggiuntiveComune(idComuneISA);
		if (infoAggiuntive != null) {
			configurazioneServizi = infoAggiuntive.getConfigurazioneServizi();
		}

		return configurazioneServizi;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.CommonService#getInfoAggiuntiveComune(long)
	 */
	@Override
	public InfoAggiuntive getInfoAggiuntiveComune(long idComuneISA) {

		ComuneISA comuneISA = comuneISAService.getComuneByPk(idComuneISA);

		InfoAggiuntive infoAggiuntive = null;

		if (comuneISA != null) {
			String infoAggiuntiveString = comuneISA.getInfoAggiuntive();

			if (infoAggiuntiveString != null && !infoAggiuntiveString.trim().equals("")) {
				Gson gson = new Gson();
				infoAggiuntive = gson.fromJson(infoAggiuntiveString, InfoAggiuntive.class);
			}
		}
		return infoAggiuntive;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.CommonService#getConfigurazioneServizioComune(java.lang.String,
	 * long, java.lang.String)
	 */
	@Override
	public Configurazione getConfigurazioneServizioComune(String codiceServizio, long idComuneISA, String configurazione) {

		List<Configurazione> configurazioniServizioComune = getConfigurazioniServizioComune(codiceServizio, idComuneISA);

		if (configurazioniServizioComune != null) {
			for (Configurazione configurazioneObject : configurazioniServizioComune) {
				String nomeConfigurazione = configurazioneObject.getNome();
				if (nomeConfigurazione != null && nomeConfigurazione.equals(configurazione)) {
					return configurazioneObject;
				}
			}
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.ConfigurazioneService#getRedirectServizioUrl(java.lang.String, long)
	 */
	@Override
	public String getRedirectServizioUrl(String codiceServizio, long idComuneISA) {

		Configurazione configurazioneServizioComune = getConfigurazioneServizioComune(codiceServizio, idComuneISA, CONFIGURAZIONE_REDIRECT_SERVIZIO);
		if (configurazioneServizioComune != null) {
			List<Condizione> condizioni = configurazioneServizioComune.getCondizioni();
			if (condizioni != null) {
				for (Condizione condizione : condizioni) {
					String nome = condizione.getNome();
					String valore = condizione.getValore();
					if (nome.equals("codice_servizio") && valore != null) {
						it.osapulie.domain.servizi.Servizio servizio = servizioService.getServizioByCodiceServizio(valore);
						return servizio.getUri();
					}
				}
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.service.ConfigurazioneService#getSegnalazioneCustomServizio(java.lang.String,
	 * long)
	 */
	@Override
	public SegnalazioneCustom getSegnalazioneCustomServizio(String codiceServizio, long idComuneISA) {

		SegnalazioneCustom segnalazioneCustom = null;
		Configurazione configurazioneServizioComune = getConfigurazioneServizioComune(codiceServizio, idComuneISA, CONFIGURAZIONE_SEGNALAZIONE_CUSTOM);
		if (configurazioneServizioComune != null) {
			List<Condizione> condizioni = configurazioneServizioComune.getCondizioni();
			if (condizioni != null && !condizioni.isEmpty()) {
				segnalazioneCustom = new SegnalazioneCustom();
				for (Condizione condizione : condizioni) {
					if (condizione.getNome().equals("descrizione")) {
						String valoreCondizione = condizione.getValore();
						segnalazioneCustom.setDescrizione(valoreCondizione);
						Map<String, String> infoAggiuntive = condizione.getInfoAggiuntive();
						if (infoAggiuntive != null) {
							List<String> motivazioni = new ArrayList<String>();
							for (Map.Entry<String, String> entry : infoAggiuntive.entrySet()) {
								motivazioni.add(entry.getValue());
							}
							segnalazioneCustom.setMotivazioni(motivazioni);
						}
					}
					else if (condizione.getNome().equals("email")) {
						segnalazioneCustom.setEmail(condizione.getValore());
					}
					else if (condizione.getNome().equals("oggetto")) {
						segnalazioneCustom.setOggetto(condizione.getValore());
					}
				}
			}
		}
		return segnalazioneCustom;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.ConfigurazioneService#isInteroperabilitaEnable(java.lang.String,
	 * long)
	 */
	@Override
	public boolean isInteroperabilitaEnable(String codiceServizio, long idComuneISA) {

		Configurazione configurazione = getConfigurazioneServizioComune(codiceServizio, idComuneISA, CONFIGURAZIONE_INTEROPERABILITA);
		if (configurazione != null && configurazione.getNome() != null) {
			List<Condizione> condizioni = configurazione.getCondizioni();
			if (condizioni != null) {
				for (Condizione condizione : condizioni) {
					String nome = condizione.getNome();
					if (nome != null && nome.equals(CONDIZIONE_ABILITATO)) {
						if (Boolean.parseBoolean(condizione.getValore())) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.service.ConfigurazioneService#isVisuraPosizioneTributariaEnable(java.lang.String,
	 * long)
	 */
	@Override
	public boolean isVisuraPosizioneTributariaEnable(String codiceServizio, long idComuneISA) {
		Configurazione configurazione = getConfigurazioneServizioComune(codiceServizio, idComuneISA, CONFIGURAZIONE_VISURA_POSIZIONE_TRIBUTARIA);
		if (configurazione != null && configurazione.getNome() != null) {
			List<Condizione> condizioni = configurazione.getCondizioni();
			if (condizioni != null) {
				for (Condizione condizione : condizioni) {
					String nome = condizione.getNome();
					if (nome != null && nome.equals(CONDIZIONE_ABILITATO)) {
						return Boolean.parseBoolean(condizione.getValore());
					}
				}
			}
		}
		return true;
	}
}
