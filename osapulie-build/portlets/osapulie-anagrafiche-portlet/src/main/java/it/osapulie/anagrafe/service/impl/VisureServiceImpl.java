package it.osapulie.anagrafe.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import it.osapulie.anagrafe.service.ServiziAnagrafici;
import it.osapulie.anagrafe.service.VisureService;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagraficiGenerali;
import it.osapulie.anagrafe.web.ws.output.types.DatiElettorali;
import it.osapulie.anagrafe.web.ws.output.types.DatiIndirizzo;
import it.osapulie.anagrafe.web.ws.output.types.DatiUtente;
import it.osapulie.anagrafe.web.ws.output.types.RichiestaDatiAnagrafici;
import it.osapulie.anagrafe.web.ws.output.types.RichiestaDatiElettorali;
import it.osapulie.anagrafe.web.ws.output.types.RichiestaVariazioniDomiciliari;
import it.osapulie.service.impl.AbstractServiceImpl;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.tributi.web.ws.input.types.DichiarazioneTassaRifiutiInputRichiesta;
import it.osapulie.tributi.web.ws.input.types.DichiarazioneTassaRifiutiInputRisposta;

/**
 * Implementazione di {@link VisureService} che interroga l'adapter PDDS e, di conseguenza, il
 * servizio remoto offerto dal particolare ente.
 *
 * @author Mario Scalas
 * @author Maria Michela Birtolo
 */
@Service("visureService")
public class VisureServiceImpl extends AbstractServiceImpl implements VisureService {

	private final Logger log = LoggerFactory.getLogger(VisureServiceImpl.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.anagrafe.service.VisureService#richiediDatiAnagrafici(it.osapulie.anagrafe.web.ws.output.
	 * types .RichiestaDatiAnagrafici)
	 */
	@Override
	public DatiAnagrafici richiediDatiAnagrafici(RichiestaDatiAnagrafici richiesta, UserPreferences userPreferences) {

		log.debug("richiediDatiAnagrafici :: entering method");

		return esegui(ServiziAnagrafici.RICHIESTA_DATI_ANAGRAFICI, richiesta, DatiAnagrafici.class, userPreferences);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.anagrafe.service.VisureService#richiediDatiAnagrafici(it.osapulie.anagrafe.web.ws.output.
	 * types .RichiestaDatiAnagrafici)
	 */
	@Override
	public DatiElettorali richiediDatiElettorali(RichiestaDatiElettorali richiesta, UserPreferences userPreferences) {

		log.debug("richiediDatiElettorali :: entering method");

		return esegui(ServiziAnagrafici.RICHIESTA_DATI_ELETTORALI, richiesta, DatiElettorali.class, userPreferences);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.anagrafe.service.VisureService#richiediDatiAnagraficiAltriServizi(it.osapulie.anagrafiche.
	 * web.ws.types.DatiAnagraficiGenerali, it.osapulie.shared.service.UserPreferences)
	 */
	@Override
	public DatiUtente richiediDatiAnagraficiAltriServizi(DatiAnagraficiGenerali richiesta, UserPreferences userPreferences) {

		log.debug("richiediDatiAnagraficiAltriServizi :: entering method");

		return esegui(ServiziAnagrafici.RICHIESTA_DATI_ANAGRAFICI_GENERALI, richiesta, DatiUtente.class, userPreferences);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.anagrafe.service.VisureService#richiediDatiVariazioniDomiciliari(it.osapulie.anagrafiche.
	 * web.ws.types.RichiestaVariazioniDomiciliari, it.osapulie.shared.service.UserPreferences)
	 */
	@Override
	public DatiIndirizzo richiediDatiVariazioniDomiciliari(RichiestaVariazioniDomiciliari richiesta, UserPreferences userPreferences) {

		log.debug("richiediDatiVariazioniDomiciliari :: entering method");

		return esegui(ServiziAnagrafici.RICHIESTA_DATI_VARIAZIONI_DOMICILIARI, richiesta, DatiIndirizzo.class, userPreferences);
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
	public DichiarazioneTassaRifiutiInputRisposta inviaDichiarazioneTassaRifiuti(DichiarazioneTassaRifiutiInputRichiesta richiesta, UserPreferences userPreferences) {

		log.debug("inviaDichiarazioneTassaRifiuti :: entering method");

		return esegui("dichiarazioneTassaRifiuti", richiesta, DichiarazioneTassaRifiutiInputRisposta.class, userPreferences);
	}

}
