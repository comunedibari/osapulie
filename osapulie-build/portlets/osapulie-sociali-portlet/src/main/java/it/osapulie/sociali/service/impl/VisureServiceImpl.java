package it.osapulie.sociali.service.impl;


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
import it.osapulie.sociali.service.ServiziAnagrafici;
import it.osapulie.sociali.service.VisureService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Implementazione di {@link VisureService} che interroga l'adapter PDDS e, di conseguenza, il
 * servizio remoto offerto dal particolare ente.
 *
 * @author Mario Scalas
 * @author Maria Michela Birtolo
 */
@Service("visureService")
public class VisureServiceImpl extends AbstractServiceImpl implements VisureService {

	private static Logger log = LoggerFactory.getLogger(VisureServiceImpl.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.sociali.service.VisureService#richiediDatiAnagrafici(it.osapulie.anagrafe.web.ws.output.types
	 * .RichiestaDatiAnagrafici)
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
	 * it.osapulie.sociali.service.VisureService#richiediDatiAnagrafici(it.osapulie.anagrafe.web.ws.output.types
	 * .RichiestaDatiAnagrafici)
	 */
	@Override
	public DatiElettorali richiediDatiElettorali(RichiestaDatiElettorali richiesta, UserPreferences userPreferences) {

		log.debug("richiediDatiElettorali :: entering method");

		return esegui(ServiziAnagrafici.RICHIESTA_DATI_ELETTORALI, richiesta, DatiElettorali.class, userPreferences);
	}

	@Override
	public DatiUtente richiediDatiAnagraficiAltriServizi(DatiAnagraficiGenerali richiesta, UserPreferences userPreferences) {

		log.debug("richiediDatiAnagraficiAltriServizi :: entering method");

		return esegui(ServiziAnagrafici.RICHIESTA_DATI_ANAGRAFICI_GENERALI, richiesta, DatiUtente.class, userPreferences);
	}

	@Override
	public DatiIndirizzo richiediDatiVariazioniDomiciliari(RichiestaVariazioniDomiciliari richiesta, UserPreferences userPreferences) {

		log.debug("richiediDatiVariazioniDomiciliari :: entering method");

		return esegui(ServiziAnagrafici.RICHIESTA_DATI_VARIAZIONI_DOMICILIARI, richiesta, DatiIndirizzo.class, userPreferences);
	}
}
