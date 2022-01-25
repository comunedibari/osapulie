package it.osapulie.tributi.service.impl;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagraficiGenerali;
import it.osapulie.anagrafe.web.ws.output.types.DatiUtente;
import it.osapulie.anagrafe.web.ws.output.types.RichiestaDatiAnagrafici;
import it.osapulie.domain.Delega;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.infrastructure.security.OSApulieUserDetails;
import it.osapulie.service.DelegaService;
import it.osapulie.service.impl.AbstractServiceImpl;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.tributi.service.ServiziTributi;
import it.osapulie.tributi.service.TributiService;
import it.osapulie.tributi.web.ws.input.types.DichiarazioneTassaRifiutiInputRichiesta;
import it.osapulie.tributi.web.ws.input.types.DichiarazioneTassaRifiutiInputRisposta;
import it.osapulie.tributi.web.ws.input.types.Tracciamento;
import it.osapulie.tributi.web.ws.output.types.CategorieImmobiliRichiesta;
import it.osapulie.tributi.web.ws.output.types.CategorieImmobiliRisposta;
import it.osapulie.tributi.web.ws.output.types.DichiarazioneTassaImmobiliRichiesta;
import it.osapulie.tributi.web.ws.output.types.DichiarazioneTassaImmobiliRisposta;
import it.osapulie.tributi.web.ws.output.types.DichiarazioneTassaRifiutiRichiesta;
import it.osapulie.tributi.web.ws.output.types.DichiarazioneTassaRifiutiRisposta;
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaRichiesta;
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaRisposta;
import it.osapulie.tributi.web.ws.output.types.VisuraTassaRifiutiRichiesta;
import it.osapulie.tributi.web.ws.output.types.VisuraTassaRifiutiRisposta;
import it.osapulie.web.ws.pddsintegration.PddsIntegration;

/**
 * Implementazione di {@link TributiService} che interroga l'adapter PDDS e, di conseguenza, il
 * servizio remoto offerto dal particolare ente.
 *
 * @author Maria Michela Birtolo
 * @author Gianluca Pindinelli
 */
@Service("tributiService")
public class TributiServiceImpl extends AbstractServiceImpl implements TributiService {

	private final Logger log = LoggerFactory.getLogger(TributiServiceImpl.class);

	private static final int RECEIVE_TIMEOUT = 270000;

	@Inject
	private DelegaService delegaService;

	@Inject
	protected PddsIntegration pddsIntegrationInput;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.tributi.service.TributiService#richiediDatiAnagrafici(it.osapulie.anagrafe.web.ws
	 * .output.types.RichiestaDatiAnagrafici, it.osapulie.shared.service.UserPreferences)
	 */
	@Override
	public DatiAnagrafici richiediDatiAnagrafici(RichiestaDatiAnagrafici richiesta, UserPreferences userPreferences) {

		log.debug("richiediDatiAnagrafici :: entering method");

		return esegui(ServiziTributi.RICHIESTA_DATI_ANAGRAFICI, richiesta, DatiAnagrafici.class, userPreferences);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.tributi.service.TributiService#richiediDatiAnagraficiAltriServizi(it.osapulie.
	 * anagrafe.web.ws.output.types.DatiAnagraficiGenerali,
	 * it.osapulie.shared.service.UserPreferences)
	 */
	@Override
	public DatiUtente richiediDatiAnagraficiAltriServizi(DatiAnagraficiGenerali richiesta, UserPreferences userPreferences) {

		log.debug("richiediDatiAnagraficiAltriServizi :: entering method");

		return esegui(ServiziTributi.RICHIESTA_DATI_ANAGRAFICI_GENERALI, richiesta, DatiUtente.class, userPreferences);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.tributi.service.TributiService#richiediPosizioneTributaria(it.osapulie.tributi.
	 * web.ws.output.types.VisuraPosizioneTributariaRichiesta,
	 * it.osapulie.shared.service.UserPreferences)
	 */
	@Override
	public VisuraPosizioneTributariaRisposta richiediPosizioneTributaria(VisuraPosizioneTributariaRichiesta richiesta, UserPreferences userPreferences) {

		log.debug("richiediPagamentiIcp :: entering method");

		return esegui(ServiziTributi.RICHIESTA_DATI_TRIBUTARI, richiesta, VisuraPosizioneTributariaRisposta.class, userPreferences);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.tributi.service.TributiService#richiediSituazioneTassaImmobili(it.osapulie.
	 * tributi.web.ws.output.types.DichiarazioneTassaImmobiliRichiesta,
	 * it.osapulie.shared.service.UserPreferences)
	 */
	@Override
	public DichiarazioneTassaImmobiliRisposta richiediSituazioneTassaImmobili(DichiarazioneTassaImmobiliRichiesta richiesta, UserPreferences userPreferences) {

		log.debug("richiediSituazioneTassaImmobili :: entering method");

		return esegui(ServiziTributi.RICHIESTA_DATI_TASSA_IMMOBILI, richiesta, DichiarazioneTassaImmobiliRisposta.class, userPreferences);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.tributi.service.TributiService#richiediSituazioneTassaRifiuti(it.osapulie.tributi
	 * .web.ws.output.types.DichiarazioneTassaRifiutiRichiesta,
	 * it.osapulie.shared.service.UserPreferences)
	 */
	@Override
	public DichiarazioneTassaRifiutiRisposta richiediSituazioneTassaRifiuti(DichiarazioneTassaRifiutiRichiesta richiesta, UserPreferences userPreferences) {

		log.debug("richiediSituazioneTassaRifiuti :: entering method");

		return esegui(ServiziTributi.RICHIESTA_DATI_TASSA_RIFIUTI, richiesta, DichiarazioneTassaRifiutiRisposta.class, userPreferences);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.tributi.service.TributiService#richiediDatiCategorieImmobili(it.osapulie.tributi.
	 * web.ws.output.types.CategorieImmobiliRichiesta, it.osapulie.shared.service.UserPreferences)
	 */
	@Override
	public CategorieImmobiliRisposta richiediDatiCategorieImmobili(CategorieImmobiliRichiesta richiesta, UserPreferences userPreferences) {

		log.debug("richiediDatiCategorieImmobili :: entering method");

		return esegui(ServiziTributi.RICHIESTA_DATI_CALCOLO_TASSA_IMMOBILI, richiesta, CategorieImmobiliRisposta.class, userPreferences);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.tributi.service.TributiService#inviaDichiarazioneTassaRifiuti(it.osapulie.tributi
	 * .web.ws.input.types.DichiarazioneTassaRifiutiInputRichiesta,
	 * it.osapulie.shared.service.UserPreferences)
	 */
	@Override
	public DichiarazioneTassaRifiutiInputRisposta inviaDichiarazione(DichiarazioneTassaRifiutiInputRichiesta richiesta, UserPreferences userPreferences) {

		log.debug("inviaDichiarazione :: entering method");

		return esegui(ServiziTributi.DICHIARAZIONE_TASSA_RIFIUTI, richiesta, DichiarazioneTassaRifiutiInputRisposta.class, userPreferences, RECEIVE_TIMEOUT);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.tributi.service.TributiCommonService#getTracciamento(it.osapulie.infrastructure.
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
		if (partitaIva != null && !partitaIva.isEmpty()) {
			tracciamento.setPartitaIvaOperatore(partitaIva);
		}
		return tracciamento;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.tributi.service.TributiService#richiediVisuraTassaRifiuti(it.osapulie.tributi.web
	 * .ws.output.types.VisuraTassaRifiutiRichiesta, it.osapulie.shared.service.UserPreferences)
	 */
	@Override
	public VisuraTassaRifiutiRisposta richiediVisuraTassaRifiuti(VisuraTassaRifiutiRichiesta richiesta, UserPreferences userPreferences) {

		log.debug("richiediVisuraTassaRifiuti :: entering method");

		return esegui(ServiziTributi.VISURA_TASSA_RIFIUTI, richiesta, VisuraTassaRifiutiRisposta.class, userPreferences);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.tributi.service.TributiService#getDichiarazioneXml(it.osapulie.tributi.web.ws.
	 * input.types.DichiarazioneTassaRifiutiInputRichiesta)
	 */
	@Override
	public String getDichiarazioneXml(DichiarazioneTassaRifiutiInputRichiesta richiesta) {
		return getXml(richiesta);
	}
}
