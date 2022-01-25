/**
 * Copyright (c) 2011 Links Management & Technology S.p.A.
 */
package it.osapulie.servizicomune.web.portlet.stampa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

/**
 * Portlet per la stampa delle pagine in cui è inserita.
 *
 * @author Gianluca Pindinelli
 */
@Controller("stampaPortletController")
@RequestMapping("view")
public class StampaPortletController {

	private final Logger log = LoggerFactory.getLogger(StampaPortletController.class);

	@RenderMapping
	public String renderHome(Model model) {

		log.debug("renderHome :: entering method");
		return "stampa/home";
	}
}
