/**
 *
 */
package it.osapulie.infrastructure.impl;

import static it.osapulie.infrastructure.impl.StreamUtils.closeStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.Template;
import it.osapulie.domain.servizi.Servizio;
import it.osapulie.infrastructure.ReportService;
import it.osapulie.service.ComuneISAService;
import it.osapulie.service.ServizioService;
import it.osapulie.service.TemplateService;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Classe manager per la creazione del report PDF tramite JasperReport.
 *
 * @author Gianluca Pindinelli
 */
@Component("exportPDFManager")
public class ReportServiceImpl implements ReportService {

	private final Logger log = LoggerFactory.getLogger(ReportServiceImpl.class);

	@Inject
	private ComuneISAService comuneISAservice;

	@Inject
	private TemplateService templateService;

	@Inject
	private ServizioService servizioService;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.linksmt.osapulie.infrastructure.ReportService#jrxmlToPdf(java.util.Map,
	 * java.util.Collection, java.lang.String, java.util.Map)
	 */
	@Override
	public byte[] jrxmlToPdf(Map<String, Object> parametersMap, Collection<?> beans, String mainReportJrxmlPath, Map<String, String> subreportJrxmlMap) {
		return getBytes(parametersMap, beans, null, null, mainReportJrxmlPath, subreportJrxmlMap);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.infrastructure.ReportService#jrxmlToPdf(java.util.Map, java.util.Collection,
	 * java.lang.Long, java.lang.String, java.util.Map)
	 */
	@Override
	public byte[] jrxmlToPdf(Map<String, Object> parametersMap, Collection<?> beans, Long idComuneISA, String mainReportJrxmlPath, Map<String, String> subreportJrxmlMap) {
		return getBytes(parametersMap, beans, idComuneISA, null, mainReportJrxmlPath, subreportJrxmlMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.osapulie.infrastructure.ReportService#jrxmlToPdf(java.util.Map, java.util.Collection,
	 * java.lang.Long, java.lang.String, java.lang.String, java.util.Map)
	 */
	@Override
	public byte[] jrxmlToPdf(Map<String, Object> parametersMap, Collection<?> beans, Long idComuneISA, String codiceServizio, String mainReportJrxmlPath, Map<String, String> subreportJrxmlMap) {
		Servizio servizio = servizioService.getServizioByCodiceServizio(codiceServizio);
		return getBytes(parametersMap, beans, idComuneISA, servizio.getId(), mainReportJrxmlPath, subreportJrxmlMap);
	}
	
	/*
	 * (non-Javadoc)
	 * @see it.osapulie.infrastructure.ReportService#jrxmlToPdf(java.util.Collection, java.lang.String)
	 */
	@Override
	public byte[] jrxmlToPdf(Collection<?> beans, String mainReportJrxmlPath){
		return getBytes(null, beans, null, null, mainReportJrxmlPath, null);
	}

	/**
	 * @param parametersMap
	 * @param beans
	 * @param idComuneISA
	 * @param idServizio
	 * @param tipo
	 * @param mainReportJrxmlPath
	 * @param subreportJrxmlMap
	 * @return
	 */
	private byte[] getBytes(Map<String, Object> parametersMap, Collection<?> beans, Long idComuneISA, Long idServizio, String mainReportJrxmlPath, Map<String, String> subreportJrxmlMap) {

		// Aggiunta logo comune
		if (idComuneISA != null && idComuneISA != 0 && idComuneISA != -1) {
			ComuneISA comuneISA = comuneISAservice.getComuneByPk(idComuneISA);
			byte[] logo = comuneISA.getLogo();
			if (logo != null) {
				ByteArrayInputStream inputStream = new ByteArrayInputStream(logo);
				parametersMap.put("logo", inputStream);
			}
		}

		InputStream mainReportInputStream = null;
		Map<String, InputStream> subreportInputStreamMap = new HashMap<String, InputStream>();
		if (idComuneISA != null && idComuneISA != 0 && idComuneISA != -1 && idServizio != null && idServizio != 0) {
			// Caricamento template principale da DB
			Template template = templateService.getTemplate(idComuneISA, idServizio, it.osapulie.web.portlet.util.PortletConstants.TEMPLATE_JASPER_REPORT);
			if (template != null) {
				if (template.getContenuto() != null) {
					mainReportInputStream = new ByteArrayInputStream(template.getContenuto().getBytes());
				}
				// Caricamento subreports da DB
				fillSubReports(subreportInputStreamMap, template);
			}
			else {
				mainReportInputStream = ReportServiceImpl.class.getResourceAsStream(mainReportJrxmlPath);
				if (subreportJrxmlMap != null && !subreportJrxmlMap.isEmpty()) {
					for (Map.Entry<String, String> entry : subreportJrxmlMap.entrySet()) {
						InputStream subReportInputStream = ReportServiceImpl.class.getResourceAsStream(entry.getValue());
						subreportInputStreamMap.put(entry.getKey(), subReportInputStream);
					}
				}
			}
		}
		else {
			mainReportInputStream = ReportServiceImpl.class.getResourceAsStream(mainReportJrxmlPath);
			if (subreportJrxmlMap != null && !subreportJrxmlMap.isEmpty()) {
				for (Map.Entry<String, String> entry : subreportJrxmlMap.entrySet()) {
					InputStream subReportInputStream = ReportServiceImpl.class.getResourceAsStream(entry.getValue());
					subreportInputStreamMap.put(entry.getKey(), subReportInputStream);
				}
			}
		}

		return getBytes(parametersMap, beans, mainReportInputStream, subreportInputStreamMap);
	}

	/**
	 * Aggiunge alla mappa i subreport leggendoli ricorsivamente dall'oggetto {@link Template}.
	 *
	 * @param subreportInputStreamMap
	 * @param template
	 */
	private void fillSubReports(Map<String, InputStream> subreportInputStreamMap, Template template) {

		List<Template> subTemplates = template.getSubTemplates();
		if (subTemplates != null && !subTemplates.isEmpty()) {
			for (Template subTemplate : subTemplates) {
				if (subTemplate.getContenuto() != null) {
					InputStream subReportInputStream = new ByteArrayInputStream(subTemplate.getContenuto().getBytes());
					subreportInputStreamMap.put(subTemplate.getNome(), subReportInputStream);
				}
				fillSubReports(subreportInputStreamMap, subTemplate);
			}
		}
	}

	/**
	 * Carica l'array di byte corrispondente al PDF.
	 *
	 * @param parametersMap
	 * @param mainReportJrxmlPath
	 * @param dataSource
	 * @param out
	 * @return
	 */
	private byte[] getBytes(Map<String, Object> parametersMap, Collection<?> beans, InputStream mainReportInputStream, Map<String, InputStream> subreportInputStreamMap) {

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		JRDataSource dataSource = wrapCollection(beans);
		try {
			JasperReport jasperReport = JasperCompileManager.compileReport(mainReportInputStream);

			if (subreportInputStreamMap != null && !subreportInputStreamMap.isEmpty()) {
				for (Map.Entry<String, InputStream> entry : subreportInputStreamMap.entrySet()) {
					JasperReport subJasperReport = JasperCompileManager.compileReport(entry.getValue());
					parametersMap.put(entry.getKey(), subJasperReport);
				}
			}

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametersMap, dataSource);
			JasperExportManager.exportReportToPdfStream(jasperPrint, out);
			return out.toByteArray();
		}
		catch (Throwable e) {
			log.error(e.getMessage(), e);
		}
		finally {
			closeStream(out);
		}
		return null;
	}

	private static JRBeanCollectionDataSource wrapCollection(Collection<?> beans) {
		return new JRBeanCollectionDataSource(beans);
	}
}
