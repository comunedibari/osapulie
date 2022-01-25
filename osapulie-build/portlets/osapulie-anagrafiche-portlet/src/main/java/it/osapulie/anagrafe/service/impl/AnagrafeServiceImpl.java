/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.anagrafe.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import it.osapulie.anagrafe.service.AnagrafeService;
import it.osapulie.anagrafe.service.ServiziAnagrafici;
import it.osapulie.anagrafe.web.ws.input.types.DichiarazioneCambioResidenzaRichiesta;
import it.osapulie.anagrafe.web.ws.input.types.DichiarazioneCambioResidenzaRisposta;
import it.osapulie.anagrafe.web.ws.input.types.Tracciamento;
import it.osapulie.domain.Delega;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.infrastructure.security.OSApulieUserDetails;
import it.osapulie.service.DelegaService;
import it.osapulie.service.impl.AbstractServiceImpl;
import it.osapulie.servizi.web.ws.types.StradarioAscotRichiesta;
import it.osapulie.servizi.web.ws.types.StradarioAscotRisposta;
import it.osapulie.shared.service.UserPreferences;

/**
 * @author Gianluca Pindinelli
 *
 */
@Service("anagrafeService")
public class AnagrafeServiceImpl extends AbstractServiceImpl implements AnagrafeService {

	private static final int RECEIVE_TIMEOUT = 270000;

	@Inject
	private DelegaService delegaService;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.anagrafe.service.AnagrafeService#inviaDichiarazione(it.osapulie.anagrafe.web.ws.
	 * input.types.DichiarazioneCambioResidenzaRichiesta,
	 * it.osapulie.shared.service.UserPreferences)
	 */
	@Override
	public DichiarazioneCambioResidenzaRisposta inviaDichiarazione(DichiarazioneCambioResidenzaRichiesta richiesta, UserPreferences userPreferences) {
		log.debug("inviaDichiarazione :: entering method");
		return esegui(ServiziAnagrafici.DICHIARAZIONE_CAMBIO_RESIDENZA, richiesta, DichiarazioneCambioResidenzaRisposta.class, userPreferences, RECEIVE_TIMEOUT);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.anagrafe.service.AnagrafeService#getTracciamento(it.osapulie.infrastructure.
	 * security.OSApulieUserDetails, it.osapulie.shared.service.UserPreferences)
	 */
	@Override
	public Tracciamento getTracciamento(OSApulieUserDetails osApulieUserDetails, UserPreferences userPreferences) {

		Tracciamento tracciamento = new Tracciamento();
		ProfiloUtenteCittadino profiloUtenteCittadinoOperatore = osApulieUserDetails.getProfiloUtenteCittadino();
		// Caricamento PIVA
		String partitaIva = userPreferences.getPartitaIvaServizio();
		Long idDelega = userPreferences.getIdDelega();
		if (idDelega != null) {
			Delega delegaByPk = delegaService.getDelegaByPk(idDelega);
			if (delegaByPk != null) {
				partitaIva = delegaByPk.getDelegato().getPartitaIva();
			}
		}
		tracciamento.setCodiceFiscaleOperatore(profiloUtenteCittadinoOperatore.getCodiceFiscale());
		tracciamento.setEmailOperatore(profiloUtenteCittadinoOperatore.getMail());
		tracciamento.setNomeOperatore(profiloUtenteCittadinoOperatore.getNome() + " " + profiloUtenteCittadinoOperatore.getCognome());
		tracciamento.setPartitaIvaOperatore(partitaIva);
		return tracciamento;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.anagrafe.service.AnagrafeService#inviaRichiestaStradarioAscot(it.osapulie.servizi
	 * .web.ws.types.StradarioAscotRichiesta, it.osapulie.shared.service.UserPreferences)
	 */
	@Override
	public StradarioAscotRisposta inviaRichiestaStradarioAscot(StradarioAscotRichiesta richiesta, UserPreferences userPreferences) {
		log.debug("inviaRichiesta :: entering method");
		return esegui(ServiziAnagrafici.STRADARIO_ASCOT_SERVICE, richiesta, StradarioAscotRisposta.class, userPreferences);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.anagrafe.service.AnagrafeService#getDichiarazioneXml(it.osapulie.anagrafe.web.ws.
	 * input.types.DichiarazioneCambioResidenzaRichiesta)
	 */
	@Override
	public String getDichiarazioneXml(DichiarazioneCambioResidenzaRichiesta richiesta) {
		return getXml(richiesta);
	}

}
