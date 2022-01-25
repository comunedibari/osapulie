/**
 * Copyright (c) 2011 Links Management & Technology S.p.A.
 */

package it.osapulie.infrastructure;

import java.util.Collection;
import java.util.Map;

import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.Template;
import it.osapulie.domain.servizi.Servizio;

/**
 * @author Mario Scalas
 * @author Gianluca Pindinelli
 */
public interface ReportService {

	/**
	 * Genera l'array di byte del file PDF generato da JasperReport corrispondente al descrittore
	 * JRXML passato.
	 *
	 * @param parametersMap
	 * @param dataSource
	 * @param mainReportJrxmlPath
	 * @param subreportJrxmlMap
	 * @return
	 */
	byte[] jrxmlToPdf(Map<String, Object> parametersMap, Collection<?> beans, String mainReportJrxmlPath, Map<String, String> subreportJrxmlMap);

	/**
	 * Genera l'array di byte del file PDF generato da JasperReport corrispondente al descrittore
	 * JRXML passato.
	 *
	 * @param parametersMap
	 * @param beans
	 * @param idComuneISA
	 * @param mainReportJrxmlPath
	 * @param subreportJrxmlMap
	 * @return
	 */
	byte[] jrxmlToPdf(Map<String, Object> parametersMap, Collection<?> beans, Long idComuneISA, String mainReportJrxmlPath, Map<String, String> subreportJrxmlMap);

	/**
	 * Genera l'array di byte del file PDF generato da JasperReport corrispondente al descrittore
	 * JRXML passato. Il metodo carica i templates ricercandoli prima dal {@link Template} associato
	 * al {@link ComuneISA} ed il {@link Servizio}, altrimenti utilizza quelli di sistema.
	 *
	 * @param parametersMap
	 * @param beans
	 * @param idComuneISA
	 * @param codiceServizio
	 * @param mainReportJrxmlPath
	 * @param subreportJrxmlMap
	 * @return
	 */
	byte[] jrxmlToPdf(Map<String, Object> parametersMap, Collection<?> beans, Long idComuneISA, String codiceServizio, String mainReportJrxmlPath, Map<String, String> subreportJrxmlMap);
	
	
	/**
	 * Genera l'array di byte del file PDF generato da JasperReport corrispondente al descrittore
	 * JRXML passato.
	 * 
	 * @param beans
	 * @param mainReportJrxmlPath
	 * @return
	 */
	byte[] jrxmlToPdf(Collection<?> beans, String mainReportJrxmlPath);
}