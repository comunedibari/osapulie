/************************************************************************************
 * Copyright (c) 2011, 2014 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.servizicomune.scheduler;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import it.osapulie.domain.ComuneISA;
import it.osapulie.service.ComuneISAService;
import it.osapulie.servizi.web.ws.types.Servizio;
import it.osapulie.servizicomune.service.ServiziAttiviService;
import it.osapulie.servizicomune.web.utils.PortletUtils;

/**
 * Task per aggiornare nel sistema la tabella di relazione tra servizi e comuni. Il task si occupa
 * di contattare il servizio per il caricamento della lista servizi presente sulle PDD applicative
 * dei comuni.
 *
 * @author Gianluca Pindinelli
 *
 */
@Component
public class CatalogoServiziTask {

	protected Logger log = LoggerFactory.getLogger(CatalogoServiziTask.class);

	@Inject
	private ServiziAttiviService serviziAttiviService;

	@Inject
	private ComuneISAService comuneISAService;

	@Scheduled(cron = "${cron.expression.updateComuniServiziRelationship}")
	public void updateComuniServiziRelationship() {

		log.info("updateComuneServiziRelationship :: INIZIO aggiornamento relazioni Comuni <-> Servizi");

		List<ComuneISA> comuni = comuneISAService.getComuniAttivi();

		if (comuni != null) {
			for (ComuneISA comuneISA : comuni) {
				try {

					log.info("updateComuneServiziRelationship :: caricamento servizi attivi per: " + comuneISA.getNome() + " (" + comuneISA.getUriServizioGateway() + ")");

					List<Servizio> serviziAttiviComuneISA = serviziAttiviService.getServiziAttiviComuneISA(comuneISA);
					serviziAttiviService.updateServiziAttiviComuneISA(serviziAttiviComuneISA, comuneISA);

					int totalServices = serviziAttiviComuneISA.size();
					int activeServices = PortletUtils.countActiveServices(serviziAttiviComuneISA);
					int inactiveServices = totalServices - activeServices;
					int strongAuthenticationServices = PortletUtils.countStrongAutenticationServices(serviziAttiviComuneISA);

					log.info("updateComuneServiziRelationship :: aggiornamento relazioni Comuni <-> Servizi per " + comuneISA.getNome() + " TERMINATO (totali: " + totalServices + ", attivi: "
							+ activeServices + ", non attivi: " + inactiveServices + ", con autenticazione forte: " + strongAuthenticationServices + ")");
				}
				catch (Exception e) {
					log.error("updateComuneServiziRelationship :: fallito caricamento servizi attivi per: " + comuneISA.getNome() + " (" + comuneISA.getUriServizioGateway() + ")", e);
				}
			}
		}

		log.info("updateComuneServiziRelationship :: aggiornamento relazioni Comuni <-> Servizi TERMINATO!");
	}
}
