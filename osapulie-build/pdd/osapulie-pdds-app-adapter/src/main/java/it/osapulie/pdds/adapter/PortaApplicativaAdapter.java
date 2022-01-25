/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pdds.adapter;

import java.lang.reflect.Modifier;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

//import org.apache.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import it.osapulie.pdds.adapter.util.EncryptDecryptUtil;
import it.osapulie.pdds.common.PddApplicativaAdapter;
import it.osapulie.pdds.common.PddService;

/**
 * @author Gianluca Pindinelli
 */
public class PortaApplicativaAdapter {

	private static final String INTERNAL_SERVICE_PACKAGE_NAME = "it.osapulie.pdds.service.internal";

	private static Logger log = LoggerFactory.getLogger(PortaApplicativaAdapter.class);

	/**
	 * Classi che implementano {@link PddService} proprie del contesto spring interno.
	 */
	@Autowired
	private List<PddService> pddServicesSpringInternal;

	/**
	 * Classi che implementano {@link PddService} proprie del contesto spring esterno.
	 */
	private List<PddService> pddServicesSpringExternal;

	/**
	 * Classi che implementano {@link PddService} esterne al contesto spring.
	 */
	private List<PddService> containerPddServices;

	private static class SingletonHolder {

		public static PortaApplicativaAdapter instance = null;

		static {
			try {
				ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:META-INF/spring/*-context.xml");
				context.setValidating(false);
				instance = context.getBean(PortaApplicativaAdapter.class);
			}
			catch (BeansException e) {
				log.error(" :: " + e.getMessage(), e);
			}
			catch (IllegalStateException e) {
				log.error(" :: " + e.getMessage(), e);
			}
		}
	}

	public static PortaApplicativaAdapter getInstance() {
		return SingletonHolder.instance;
	}

	/**
	 * Inizializza la lista contenente le istanze di classe che implementano {@link PddService} non
	 * comprese nel package INTERNAL_SERVICE_PACKAGE_NAME.
	 */
	public void init() {

		Set<Class<? extends PddService>> responseClassesSet = PddApplicativaAdapter.getServiceClassesSet("it.osapulie");
		if (responseClassesSet != null) {
			containerPddServices = new ArrayList<PddService>();

			Iterator<Class<? extends PddService>> iterator = responseClassesSet.iterator();
			while (iterator.hasNext()) {
				Class<? extends PddService> class1 = iterator.next();
				URL location = class1.getProtectionDomain().getCodeSource().getLocation();
				String classPathLocation = location.getPath();
				if (classPathLocation != null && classPathLocation.contains("/WEB-INF/lib") && !class1.getPackage().getName().contains(INTERNAL_SERVICE_PACKAGE_NAME)
						&& !Modifier.isAbstract(class1.getModifiers())) {
					PddService newInstance;
					try {
						newInstance = class1.newInstance();
						containerPddServices.add(newInstance);
					}
					catch (InstantiationException e) {
						log.error("init :: " + e.getMessage(), e);
					}
					catch (IllegalAccessException e) {
						log.error("init :: " + e.getMessage(), e);
					}
				}
			}
		}

		// Aggiunta istanze spring external
		if (pddServicesSpringInternal != null) {
			pddServicesSpringExternal = new ArrayList<PddService>();
			for (PddService pddService : pddServicesSpringInternal) {
				if (!pddService.getClass().getPackage().getName().contains(INTERNAL_SERVICE_PACKAGE_NAME)) {
					pddServicesSpringExternal.add(pddService);
				}
			}
		}
	}

	public String getRichiestaPdd(String servizio, String richiesta) {

		if (isHexDigit(richiesta)) {
			try {
				richiesta = EncryptDecryptUtil.decrypt(richiesta, EncryptDecryptUtil.PASSKEY_CONST);
			}
			catch (InvalidKeyException e) {
				log.error("StubPagamenti :: eseguiPagamentoServizio EncryptDecryptUtil :: " + e.getMessage());
			}
			catch (NoSuchAlgorithmException e) {
				log.error("StubPagamenti :: eseguiPagamentoServizio EncryptDecryptUtil :: " + e.getMessage());
			}
			catch (NoSuchProviderException e) {
				log.error("StubPagamenti :: eseguiPagamentoServizio EncryptDecryptUtil :: " + e.getMessage());
			}
			catch (NoSuchPaddingException e) {
				log.error("StubPagamenti :: eseguiPagamentoServizio EncryptDecryptUtil :: " + e.getMessage());
			}
			catch (IllegalBlockSizeException e) {
				log.error("StubPagamenti :: eseguiPagamentoServizio EncryptDecryptUtil :: " + e.getMessage());
			}
			catch (BadPaddingException e) {
				log.error("StubPagamenti :: eseguiPagamentoServizio EncryptDecryptUtil :: " + e.getMessage());
			}
		}

		log.debug("*** RICHIESTA : " + richiesta);
		log.debug("### *** ID SERVIZIO PRELEVATO DA RICHIESTA: " + servizio);

		PddService serviceResponse = getPddServiceByName(servizio);

		if (serviceResponse != null) {
			return serviceResponse.getResponse(richiesta);
		}
		else {
			// Se non ci è noto, meglio dirlo!
			throw new RuntimeException(String.format("Servizio \"%s\" sconosciuto!", servizio));
		}

	}

	/**
	 * Carica l'implementazione di {@link PddService} in base al nome passato in input. Il metodo
	 * verifica se esiste una classe, esterna al contesto Spring, con il nome servizio passato; se
	 * non esiste effettua la ricerca nel contesto spring dell'applicazione.
	 *
	 * @param servizio
	 * @return
	 */
	private PddService getPddServiceByName(String servizio) {

		// Ricerca in classi contesto spring external
		if (pddServicesSpringExternal != null && pddServicesSpringExternal.size() > 0) {
			for (PddService pddService : pddServicesSpringExternal) {
				if (pddService.getName().equals(servizio)) {
					return pddService;
				}
			}
		}

		// Ricerca in classi container
		if (containerPddServices != null) {
			for (PddService pddService : containerPddServices) {
				if (pddService.getName().equals(servizio)) {
					return pddService;
				}
			}
		}

		// Ricerca in classi contesto spring internal
		if (pddServicesSpringInternal != null && pddServicesSpringInternal.size() > 0) {
			for (PddService pddService : pddServicesSpringInternal) {
				if (pddService.getName().equals(servizio)) {
					return pddService;
				}
			}
		}

		return null;
	}

	public boolean isHexDigit(String hexDigit) {
		char[] hexDigitArray = hexDigit.toCharArray();
		int hexDigitLength = hexDigitArray.length;
		boolean isNotHex;
		for (int i = 0; i < hexDigitLength; i++) {
			isNotHex = Character.digit(hexDigitArray[i], 16) == -1;
			if (isNotHex) {
				return false;
			}
		}
		return true;
	}
}
