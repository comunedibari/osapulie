/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.servizicomune.scheduler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.categoriaimmobile.BaseDiCalcolo;
import it.osapulie.domain.categoriaimmobile.CategoriaImmobileTributo;
import it.osapulie.domain.categoriaimmobile.CategoriaImmobileTributoId;
import it.osapulie.domain.categoriaimmobile.TipologiaCategoriaImmobile;
import it.osapulie.domain.categoriaimmobile.TipologiaDetrazione;
import it.osapulie.service.BaseDiCalcoloService;
import it.osapulie.service.CategoriaImmobileService;
import it.osapulie.service.ComuneISAService;
import it.osapulie.service.ServiceLayerException;
import it.osapulie.service.TipologiaCategoriaImmobileService;
import it.osapulie.service.TipologiaDetrazioneService;
import it.osapulie.service.TributoService;
import it.osapulie.servizicomune.service.ServiziAttiviService;
import it.osapulie.servizicomune.service.TipoServizio;
import it.osapulie.tributi.web.ws.output.types.Agevolazione;
import it.osapulie.tributi.web.ws.output.types.CategoriaImmobile;
import it.osapulie.tributi.web.ws.output.types.CategorieImmobiliRichiesta;
import it.osapulie.tributi.web.ws.output.types.CategorieImmobiliRisposta;
import it.osapulie.tributi.web.ws.output.types.Detrazione;
import it.osapulie.tributi.web.ws.output.types.Esenzione;
import it.osapulie.tributi.web.ws.output.types.TipoDetrazione;
import it.osapulie.tributi.web.ws.output.types.Tributo;

/**
 * @author Gianluca Pindinelli
 *
 */
@Component
public class ServiziComuneTask {

	protected Logger log = LoggerFactory.getLogger(ServiziComuneTask.class.getName());

	@Inject
	private ComuneISAService comuneISAService;

	@Inject
	private ServiziAttiviService serviziAttiviService;

	@Inject
	private TributoService tributoService;

	@Inject
	private CategoriaImmobileService categoriaImmobileService;

	@Inject
	private TipologiaCategoriaImmobileService tipologiaCategoriaImmobileService;

	@Inject
	private TipologiaDetrazioneService tipologiaDetrazioneService;

	@Inject
	private BaseDiCalcoloService baseDiCalcoloService;

	/**
	 *
	 */
	@Scheduled(cron = "${cron.expression.updateCategorieImmobili}")
	public void updateCategorieImmobili() {

		log.info("updateCategorieImmobili :: INIZIO aggiornamento categorie immobili");

		List<ComuneISA> comuni = comuneISAService.getAllComuni();

		if (comuni != null) {
			CategorieImmobiliRichiesta richiesta = new CategorieImmobiliRichiesta();
			richiesta.setAnno(Calendar.getInstance().get(Calendar.YEAR));
			for (ComuneISA comuneISA : comuni) {
				String uriServizioGateway = comuneISA.getUriServizioGateway();
				CategorieImmobiliRisposta categorieImmobiliRisposta;
				try {

					log.info("updateCategorieImmobili :: caricamento categorie per: " + comuneISA.getNome() + " (" + comuneISA.getUriServizioGateway() + ")");

					categorieImmobiliRisposta = serviziAttiviService.richiediCategorieImmobili(richiesta, uriServizioGateway);
					updateCategorieImmobiliComune(categorieImmobiliRisposta, comuneISA);
				}
				catch (Exception e) {
					log.error("updateCategorieImmobili :: fallito caricamento categorie per: " + comuneISA.getNome() + " (" + comuneISA.getUriServizioGateway() + ")", e);
				}
			}
		}

		log.info("updateCategorieImmobili :: aggiornamento categorie immobili TERMINATO!");
	}

	/**
	 * @param categorieImmobiliRisposta
	 * @param comuneISA
	 */
	private void updateCategorieImmobiliComune(CategorieImmobiliRisposta categorieImmobiliRisposta, ComuneISA comuneISA) {

		if (categorieImmobiliRisposta.getErrore() != null) {
			log.error("updateCategorieImmobiliComune :: errore dal servizio " + TipoServizio.RICHIESTA_ELENCO_CATEGORIE_IMMOBILI + " per il comune di : " + comuneISA.getNome() + " :: codice errore : "
					+ categorieImmobiliRisposta.getErrore().getCodice() + ", descrizione : " + categorieImmobiliRisposta.getErrore().getDescrizione());
			return;
		}

		List<CategoriaImmobile> categorieImmobili = categorieImmobiliRisposta.getCategoriaImmobile();
		if (categorieImmobili != null) {

			// Eliminazione dati da db
			List<it.osapulie.domain.categoriaimmobile.CategoriaImmobile> categorieImmobiliByComuneISAAndAnno = categoriaImmobileService.getCategorieImmobiliByComuneISAAndAnno(comuneISA.getId(),
					Calendar.getInstance().get(Calendar.YEAR));
			for (it.osapulie.domain.categoriaimmobile.CategoriaImmobile categoriaImmobile : categorieImmobiliByComuneISAAndAnno) {
				categoriaImmobileService.deleteCategoriaImmobile(categoriaImmobile);
			}

			// Salvataggio dati su db
			for (CategoriaImmobile categoriaImmobileWs : categorieImmobili) {

				it.osapulie.domain.categoriaimmobile.CategoriaImmobile categoriaImmobile = new it.osapulie.domain.categoriaimmobile.CategoriaImmobile();

				String codiceCategoriaImmobile = categoriaImmobileWs.getCodice();
				TipologiaCategoriaImmobile tipologiaCategoriaImmobile = tipologiaCategoriaImmobileService.getTipologiaCategoriaImmobileByCodice(codiceCategoriaImmobile);
				categoriaImmobile.setTipologiaCategoriaImmobile(tipologiaCategoriaImmobile);
				tipologiaCategoriaImmobile.getCategorieImmobili().add(categoriaImmobile);

				categoriaImmobile.setCoefficienteMoltiplicazione(categoriaImmobileWs.getCoefficienteMoltiplicazione());
				BaseDiCalcolo baseDiCalcolo = baseDiCalcoloService.getBaseDiCalcoloByValore(categoriaImmobileWs.getCoefficienteRivalutazione());
				categoriaImmobile.setBaseDiCalcolo(baseDiCalcolo);

				List<CategoriaImmobileTributo> categorieImmobiliTributi = new ArrayList<CategoriaImmobileTributo>();
				List<Tributo> tributi = categoriaImmobileWs.getTributi();
				for (Tributo tributoWs : tributi) {
					CategoriaImmobileTributo categoriaImmobileTributo = new CategoriaImmobileTributo();
					categoriaImmobileTributo.setComuneISA(comuneISA);
					categoriaImmobileTributo.setAnno(Calendar.getInstance().get(Calendar.YEAR));
					categoriaImmobileTributo.setCategoriaImmobile(categoriaImmobile);
					if (tributoWs.getCodice() != null) {
						categoriaImmobileTributo.setCodiceTributo(tributoWs.getCodice().intValue());
					}

					it.osapulie.domain.categoriaimmobile.Tributo tributoByCodice = tributoService.getTributoById(tributoWs.getId().intValue());
					categoriaImmobileTributo.setTributo(tributoByCodice);
					CategoriaImmobileTributoId pk = new CategoriaImmobileTributoId(comuneISA.getId(), 0, tributoByCodice.getId());
					categoriaImmobileTributo.setPk(pk);
					categoriaImmobileTributo.setAliquota(tributoWs.getAliquota());
					List<Agevolazione> agevolazioniWs = tributoWs.getAgevolazioni();
					if (agevolazioniWs != null) {
						List<it.osapulie.domain.categoriaimmobile.Agevolazione> agevolazioni = new ArrayList<it.osapulie.domain.categoriaimmobile.Agevolazione>();
						for (Agevolazione agevolazioneWs : agevolazioniWs) {
							it.osapulie.domain.categoriaimmobile.Agevolazione agevolazione = new it.osapulie.domain.categoriaimmobile.Agevolazione();
							agevolazione.setCategoriaImmobileTributo(categoriaImmobileTributo);
							agevolazione.setNome(agevolazioneWs.getDescrizione());
							agevolazione.setValore(agevolazioneWs.getValore());
							agevolazioni.add(agevolazione);
						}
						categoriaImmobileTributo.setAgevolazioni(agevolazioni);
					}

					List<Detrazione> detrazioniWs = tributoWs.getDetrazioni();
					if (detrazioniWs != null) {
						List<it.osapulie.domain.categoriaimmobile.Detrazione> detrazioni = new ArrayList<it.osapulie.domain.categoriaimmobile.Detrazione>();
						for (Detrazione detrazioneWs : detrazioniWs) {
							it.osapulie.domain.categoriaimmobile.Detrazione detrazione = new it.osapulie.domain.categoriaimmobile.Detrazione();
							detrazione.setCategoriaImmobileTributo(categoriaImmobileTributo);
							detrazione.setDescrizione(detrazioneWs.getDescrizione());
							detrazione.setImporto(detrazioneWs.getImporto());
							detrazione.setInfo(detrazioneWs.getInfo());
							TipoDetrazione tipologiaDetrazioneWs = detrazioneWs.getTipo();
							String codice = tipologiaDetrazioneWs.getCodice();
							try {
								TipologiaDetrazione tipologiaDetrazioneByCodice = tipologiaDetrazioneService.getTipologiaDetrazioneByCodice(codice);
								detrazione.setTipologiaDetrazione(tipologiaDetrazioneByCodice);
							}
							catch (ServiceLayerException e) {
								log.error("updateCategorieImmobiliComune :: " + e.getMessage(), e);
							}
							detrazioni.add(detrazione);
						}
						categoriaImmobileTributo.setDetrazioni(detrazioni);
					}

					List<Esenzione> esenzioniWs = tributoWs.getEsenzioni();
					if (esenzioniWs != null) {
						List<it.osapulie.domain.categoriaimmobile.Esenzione> esenzioni = new ArrayList<it.osapulie.domain.categoriaimmobile.Esenzione>();
						for (Esenzione esenzioneWs : esenzioniWs) {
							it.osapulie.domain.categoriaimmobile.Esenzione esenzione = new it.osapulie.domain.categoriaimmobile.Esenzione();
							esenzione.setCategoriaImmobileTributo(categoriaImmobileTributo);
							esenzione.setDescrizione(esenzioneWs.getDescrizione());
							esenzioni.add(esenzione);
						}
						categoriaImmobileTributo.setEsenzioni(esenzioni);
					}

					categorieImmobiliTributi.add(categoriaImmobileTributo);
				}

				categoriaImmobile.setCategorieImmobiliTributi(categorieImmobiliTributi);

				categoriaImmobile = categoriaImmobileService.saveCategoriaImmobile(categoriaImmobile);
			}
		}
	}
}
