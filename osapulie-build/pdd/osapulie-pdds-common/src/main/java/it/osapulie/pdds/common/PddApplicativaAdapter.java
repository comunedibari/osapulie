/************************************************************************************
 * Copyright (c) 2011, 2014 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pdds.common;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;

/**
 * Classe adapter che si occupa delle operazioni riguardanti lo smistamento delle chiamate verso le
 * implementazioni dei servizi esistenti.
 *
 * @author Gianluca Pindinelli
 *
 */
public class PddApplicativaAdapter {

	/**
	 *
	 */
	protected static Logger log = LoggerFactory.getLogger(PddApplicativaAdapter.class.getName());

	/**
	 * Ritorna la mappa contenente le istanze dei servizi disponibili; la chiave rappresenta il nome
	 * dato al servizio.
	 *
	 * @param pddServicePackage il package contenente le classi che implementano {@link PddService}
	 * @return
	 */
	public static Map<String, PddService> getServiceClassesMap(String pddServicePackage) {

		Map<String, PddService> map = PddApplicativaSingleton.getInstance().getResponseClassMap();

		if (map.isEmpty()) {
			addClassesToMap(pddServicePackage, map);
		}
		else {
			// Controllo che le classi esistenti in mappa non siano dello stesso package, altrimenti
			// le aggiungo
			for (Map.Entry<String, PddService> entry : map.entrySet()) {

				PddService value = entry.getValue();
				if (!value.getClass().getPackage().toString().equals(pddServicePackage)) {
					addClassesToMap(pddServicePackage, map);
					break;
				}
			}
		}
		return map;
	}

	/**
	 * Ritorna il set contenente le istanze dei servizi disponibili.
	 *
	 * @param pddServicePackage il package contenente le classi che implementano {@link PddService}
	 * @return
	 */
	public static Set<Class<? extends PddService>> getServiceClassesSet(String pddServicePackage) {

		Reflections reflections = new Reflections(ClasspathHelper.forPackage(pddServicePackage), new SubTypesScanner());

		Set<Class<? extends PddService>> allClassesByType = reflections.getSubTypesOf(PddService.class);

		return allClassesByType;
	}

	/**
	 * @param pddServicePackage
	 * @param map
	 */
	private static void addClassesToMap(String pddServicePackage, Map<String, PddService> map) {

		Reflections reflections = new Reflections(ClasspathHelper.forPackage(pddServicePackage), new SubTypesScanner());

		Set<Class<? extends PddService>> allClassesByType = reflections.getSubTypesOf(PddService.class);

		Iterator<Class<? extends PddService>> iterator = allClassesByType.iterator();

		while (iterator.hasNext()) {
			Class<? extends it.osapulie.pdds.common.PddService> responseClass = iterator.next();

			try {
				PddService newInstance = responseClass.newInstance();
				String serviceName = newInstance.getName();
				map.put(serviceName, newInstance);
			}
			catch (InstantiationException e) {
				log.error("getResponseClassesMap :: " + e.getMessage(), e);
			}
			catch (IllegalAccessException e) {
				log.error("getResponseClassesMap :: " + e.getMessage(), e);
			}
		}
	}

	public static void main(String[] args) {
		Set<Class<? extends PddService>> responseClassesMap = PddApplicativaAdapter.getServiceClassesSet("");

		System.out.println(responseClassesMap);
	}
}
