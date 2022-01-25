/**
 * Copyright (c) 2011 Links Management & Technology S.p.A.
 */
package it.osapulie.servizicomune.web.portlet.testservizi.controller;

import java.io.StringWriter;
import java.util.Collections;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import it.osapulie.anagrafe.web.ws.output.types.DatiAnagraficiGenerali;
import it.osapulie.anagrafe.web.ws.output.types.RichiestaDatiAnagrafici;
import it.osapulie.domain.ComuneISA;
import it.osapulie.infrastructure.XMLHelper;
import it.osapulie.infrastructure.impl.DateUtils;
import it.osapulie.service.ComuneISAService;
import it.osapulie.servizicomune.web.portlet.testservizi.form.TestServiziForm;
import it.osapulie.servizicomune.web.utils.ComuneISAComparator;
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaRichiesta;
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaRichiesta.ArcoTemporale;
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaRichiesta.TipoTributo;
import it.osapulie.web.ws.pddsintegration.PddsIntegration;

/**
 * Controller per il test dei servizi mediante Porta di dominio.
 *
 * @author Gianluca Pindinelli
 */
@Controller("testServiziPortletController")
@RequestMapping("view")
public class TestServiziPortletController {

	private final Logger log = LoggerFactory.getLogger(TestServiziPortletController.class);

	public static final String RICHIESTA_DATI_ANAGRAFICI = "richiestaDatiAnagrafici";
	public static final String RICHIESTA_DATI_ANAGRAFICI_GENERALI = "datiAnagraficiGenerali";
	public static final String RICHIESTA_DATI_TRIBUTARI = "visuraPosizioneTributariaRichiesta";

	@Autowired
	private ComuneISAService comuneISAService;

	@Autowired
	protected XMLHelper xmlHelper;

	@Autowired
	protected PddsIntegration pddsIntegration;

	@RenderMapping
	public String renderHome(Model model) {

		log.debug("renderHome :: entering method");
		model.addAttribute("annoCorrente", DateUtils.getAnnoCorrente());

		return "testservizi/home";
	}

	@ModelAttribute("testServiziForm")
	public TestServiziForm getTestServiziForm() {
		return new TestServiziForm();
	}

	@ActionMapping(params = "action=avviaTest")
	public void avviaTest(@ModelAttribute TestServiziForm testServiziForm, @RequestParam("startYear") Integer yearStart, @RequestParam("endYear") Integer yearEnd, Model model, ActionResponse response,
			PortletSession session, PortletRequest portletRequest) {

		log.debug("avviaTest :: entering method");

		String codiceFiscale = testServiziForm.getCodiceFiscale().toUpperCase();
		String partitaIva = testServiziForm.getPartitaIva().toUpperCase();
		String codiceIstatComune = testServiziForm.getIdComune();
		if (testServiziForm != null && codiceIstatComune != null) {
			try {
				ComuneISA comuneByCodiceIstat = comuneISAService.getComuneByCodiceIstat(codiceIstatComune);

				if (codiceFiscale != null) {
					RichiestaDatiAnagrafici richiesta = new RichiestaDatiAnagrafici();
					richiesta.setCodiceFiscale(codiceFiscale);

					try {
						String xmlDatiAnagrafici = pddsIntegration.getRichiestaPdd(xmlHelper.marshal(richiesta), RICHIESTA_DATI_ANAGRAFICI, comuneByCodiceIstat.getUriServizioGateway());
						model.addAttribute("xmlDatiAnagrafici", prettyPrintXml(xmlDatiAnagrafici));
					}
					catch (Throwable e) {
						log.error("avviaTest :: " + e.getMessage(), e);
						model.addAttribute("xmlDatiAnagrafici", e.getMessage());
					}
				}

				if (codiceFiscale != null) {
					DatiAnagraficiGenerali richDatiAna = new DatiAnagraficiGenerali();
					richDatiAna.setCodiceFiscale(codiceFiscale);

					try {
						String xmlDatiAnagraficiGenerali = pddsIntegration.getRichiestaPdd(xmlHelper.marshal(richDatiAna), RICHIESTA_DATI_ANAGRAFICI_GENERALI,
								comuneByCodiceIstat.getUriServizioGateway());
						model.addAttribute("xmlDatiAnagraficiGenerali", prettyPrintXml(xmlDatiAnagraficiGenerali));
					}
					catch (Throwable e) {
						log.error("avviaTest :: " + e.getMessage(), e);
						model.addAttribute("xmlDatiAnagraficiGenerali", e.getMessage());
					}
				}

				VisuraPosizioneTributariaRichiesta richiestaTrib = new VisuraPosizioneTributariaRichiesta();
				if (partitaIva != null && !partitaIva.trim().equals("")) {
					richiestaTrib.setPartitaIva(partitaIva);
				}
				else if (codiceFiscale != null) {
					richiestaTrib.setCodiceFiscale(codiceFiscale);
				}
				ArcoTemporale arcoTemporale = new ArcoTemporale();
				arcoTemporale.setAnnoInizio(yearStart);
				arcoTemporale.setAnnoFine(yearEnd);
				richiestaTrib.setArcoTemporale(arcoTemporale);
				TipoTributo tipoTributo = new TipoTributo();
				tipoTributo.setALL("ALL");
				richiestaTrib.setTipoTributo(tipoTributo);

				// memorizzo il campo
				model.addAttribute("yearStart", yearStart);
				model.addAttribute("yearEnd", yearEnd);

				try {
					String xmlVisuraPosizioneTributaria = pddsIntegration.getRichiestaPdd(xmlHelper.marshal(richiestaTrib), RICHIESTA_DATI_TRIBUTARI, comuneByCodiceIstat.getUriServizioGateway());
					model.addAttribute("xmlVisuraPosizioneTributaria", prettyPrintXml(xmlVisuraPosizioneTributaria));
				}
				catch (Throwable e) {
					log.error("avviaTest :: " + e.getMessage(), e);
					model.addAttribute("xmlVisuraPosizioneTributaria", e.getMessage());
				}
			}
			catch (Exception e) {
				log.error("avviaTest :: " + e.getMessage(), e);
			}
		}

		model.addAttribute("testServiziForm", testServiziForm);

	}

	@ModelAttribute("comuni")
	public List<ComuneISA> getComuni() {

		List<ComuneISA> results = comuneISAService.getAllComuni();

		Collections.sort(results, new ComuneISAComparator());

		return results;
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
