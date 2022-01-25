/**
 * Copyright (c) 2011 Links Management & Technology S.p.A.
 */
package it.osapulie.servizicomune.web.portlet.testdichiarazioni.controller;

import java.io.StringWriter;
import java.util.Calendar;

import javax.inject.Inject;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;

import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import it.osapulie.anagrafe.web.ws.input.types.DichiarazioneCambioResidenzaRichiesta;
import it.osapulie.anagrafe.web.ws.input.types.DichiarazioneCambioResidenzaRisposta;
import it.osapulie.domain.Backup;
import it.osapulie.infrastructure.XMLHelper;
import it.osapulie.infrastructure.impl.DateUtils;
import it.osapulie.service.BackupService;
import it.osapulie.servizicomune.web.portlet.testdichiarazioni.form.TestDichiarazioniForm;
import it.osapulie.servizicomune.web.service.AnagrafeService;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.web.ws.pddsintegration.PddsIntegration;

/**
 * Controller per il test dei servizi di dichiarazione mediante Porta di dominio.
 *
 * @author Gianluca Pindinelli
 */
@Controller("testDichiarazioniPortletController")
@RequestMapping("view")
public class TestDichiarazioniPortletController {

	private final Logger log = LoggerFactory.getLogger(TestDichiarazioniPortletController.class);

	@Autowired
	protected XMLHelper xmlHelper;

	@Autowired
	protected PddsIntegration pddsIntegration;

	@Autowired
	private BackupService backupService;

	@Inject
	private AnagrafeService anagrafeService;

	@RenderMapping
	public String renderHome(Model model) {

		log.debug("renderHome :: entering method");
		model.addAttribute("annoCorrente", DateUtils.getAnnoCorrente());

		return "testdichiarazioni/home";
	}

	@ModelAttribute("testDichiarazioniForm")
	public TestDichiarazioniForm getTestServiziForm() {
		return new TestDichiarazioniForm();
	}

	@ActionMapping(params = "action=avviaTest")
	public void avviaTest(@ModelAttribute TestDichiarazioniForm testDichiarazioniForm, Model model, ActionResponse response, PortletSession session, PortletRequest portletRequest) {

		log.info("INIZIO ******************" + Calendar.getInstance().toString());

		log.debug("avviaTest :: entering method");
		try {
			if (testDichiarazioniForm.getIdBackup() != null) {
				Backup backupByPk = backupService.getBackupByPk(testDichiarazioniForm.getIdBackup());

				String xmlDichiarazione = backupByPk.getContenuto();
				DichiarazioneCambioResidenzaRichiesta dichiarazioneCambioResidenzaRichiesta = xmlHelper.unmarshal(xmlDichiarazione, DichiarazioneCambioResidenzaRichiesta.class);
				UserPreferences userPreferences = new UserPreferences();
				userPreferences.setUriServizioGateway("https://comunedibari.spcoop.gov.it/pdds/services/PortaDominioApplicativa");
				// userPreferences.setUriServizioGateway("http://10.0.5.19:9080/pdds/services/PortaDominioApplicativa");
				DichiarazioneCambioResidenzaRisposta risposta = anagrafeService.inviaDichiarazione(dichiarazioneCambioResidenzaRichiesta, userPreferences);
				String xml = xmlHelper.marshal(risposta);
				model.addAttribute("xml", prettyPrintXml(xml));

			}
		}
		catch (Exception e) {
			log.error("avviaTest :: " + e.getMessage(), e);
		}

		log.info("FINE ******************" + Calendar.getInstance().toString());

		model.addAttribute("testDichiarazioniForm", testDichiarazioniForm);

	}

	/**
	 *
	 * @param xml
	 * @return
	 */
	public String prettyPrintXml(String xml) {

		final StringWriter sw;

		try {
			final OutputFormat format = OutputFormat.createPrettyPrint();
			final org.dom4j.Document document = DocumentHelper.parseText(xml);
			sw = new StringWriter();
			final XMLWriter writer = new XMLWriter(sw, format);
			writer.write(document);
		}
		catch (Exception e) {
			throw new RuntimeException("Error pretty printing xml:\n" + xml, e);
		}
		return sw.toString();
	}

}
