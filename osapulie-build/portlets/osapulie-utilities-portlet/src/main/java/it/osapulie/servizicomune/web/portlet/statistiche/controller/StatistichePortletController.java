/**
 * Copyright (c) 2011 Links Management & Technology S.p.A.
 */
package it.osapulie.servizicomune.web.portlet.statistiche.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import it.osapulie.domain.servizi.Servizio;
import it.osapulie.service.FascicoloUtenteService;
import it.osapulie.service.ProfiloUtenteService;
import it.osapulie.service.ServizioService;

/**
 * Controller per la gestione delle funzionalità statistiche.
 *
 * @author Gianluca Pindinelli
 */
@Controller("statistichePortletController")
@RequestMapping("view")
public class StatistichePortletController {

	private final Logger log = LoggerFactory.getLogger(StatistichePortletController.class);

	@Inject
	private ProfiloUtenteService profiloUtenteService;

	@Inject
	private FascicoloUtenteService fascicoloUtenteService;

	@Inject
	private ServizioService servizioService;

	@Autowired
	public ResourceBundleMessageSource messageSource;

	@RenderMapping
	public String renderHome(Model model) {

		log.debug("renderHome :: entering method");
		return "statistiche/home";
	}

	@ModelAttribute("statisticheMap")
	public LinkedHashMap<String, String> getStatisticheMap(Locale locale) {

		LinkedHashMap<String, String> statisticheMap = new LinkedHashMap<String, String>();

		// Num. totale utenti registrati
		long countAllProfiloUtenteCittadino = profiloUtenteService.countAllProfiloUtenteCittadino();
		statisticheMap.put(messageSource.getMessage("label.numTotaleUtentiRegistrati", null, locale), String.valueOf(countAllProfiloUtenteCittadino));

		// Caricamento certificati scaricati in base al tipo
		List<Servizio> servizi = servizioService.getServiziAttivi();
		long totaleCertificatiCount = 0;
		if (servizi != null) {
			for (Servizio servizio : servizi) {
				long countAllRichiesteServizioFascicoloByServizio = fascicoloUtenteService.countAllRichiesteServizioFascicoloByServizio(servizio.getId());
				statisticheMap.put(messageSource.getMessage("label.numFruizioneServizio", new String[] { servizio.getNomeServizio() }, locale),
						String.valueOf(countAllRichiesteServizioFascicoloByServizio));
				totaleCertificatiCount += countAllRichiesteServizioFascicoloByServizio;
			}
		}

		// Num. totale certificati
		statisticheMap.put(messageSource.getMessage("label.numTotaleFruizioniServizi", null, locale), String.valueOf(totaleCertificatiCount));

		return statisticheMap;
	}
}
