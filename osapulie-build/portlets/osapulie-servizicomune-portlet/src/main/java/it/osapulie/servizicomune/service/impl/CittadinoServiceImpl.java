package it.osapulie.servizicomune.service.impl;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici;
import it.osapulie.anagrafe.web.ws.output.types.RichiestaDatiAnagrafici;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici.ComponentiNucleoFamiliare;
import it.osapulie.domain.Comune;
import it.osapulie.service.ComuneService;
import it.osapulie.service.impl.AbstractServiceImpl;
import it.osapulie.servizicomune.service.CittadinoService;
import it.osapulie.servizicomune.service.ServiziAnagrafici;
import it.osapulie.shared.service.UserPreferences;

@Service("cittadinoService")
public class CittadinoServiceImpl extends AbstractServiceImpl implements CittadinoService {

	private static Logger log = LoggerFactory.getLogger(CittadinoServiceImpl.class);

	@Inject
	private ComuneService comuneService;
	
	@Override
	public DatiAnagrafici richiediDatiAnagrafici(RichiestaDatiAnagrafici richiesta, UserPreferences userPreferences) {
		log.debug("richiediDatiAnagrafici :: entering method");
		return esegui(ServiziAnagrafici.RICHIESTA_DATI_ANAGRAFICI, richiesta, DatiAnagrafici.class, userPreferences);
	}

	@Override
	public void addInfoAggiuntiveComponenteToModel(Model model,ComponentiNucleoFamiliare componente) {


		//Atto Nascita
		String codiceIstatComuneNascita = componente.getCodiceIstatComuneNascita();
		if (codiceIstatComuneNascita != null) {
			Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(codiceIstatComuneNascita);
			model.addAttribute("comuneNascita", comuneByCodiceISTAT.getDenominazione());
			model.addAttribute("provinciaNascita", comuneByCodiceISTAT.getProvincia().getDenominazioneProvincia());
		}
		
		//Atto Nascita Trascritto
		String codiceIstatComuneNascitaTrascritto = componente.getCodiceIstatComuneNascitaTrascritto();
		if(codiceIstatComuneNascitaTrascritto!=null){
			Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(codiceIstatComuneNascitaTrascritto);
			model.addAttribute("comuneAttoNascitaTrascritto", comuneByCodiceISTAT.getDenominazione());
			model.addAttribute("provinciaAttoNascitaTrascritto", comuneByCodiceISTAT.getProvincia().getDenominazioneProvincia());
		}
				
		//Atto Matrimonio
		String codiceIstatComuneMatrimonio = componente.getCodiceIstatComuneMatrimonio();
		if (codiceIstatComuneMatrimonio != null) {
			Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(codiceIstatComuneMatrimonio);
			model.addAttribute("comuneMatrimonio", comuneByCodiceISTAT.getDenominazione());
		}
		
		//Atto Matrimonio Trascritto
		String codiceIstatMatrimonioTrascritto = componente.getCodiceIstatComuneMatrimonioTrascritto();
		if(codiceIstatMatrimonioTrascritto!=null){
			Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(codiceIstatMatrimonioTrascritto);
			model.addAttribute("comuneMatrimonioTrascritto", comuneByCodiceISTAT.getDenominazione());
		}
		
		//Atto divorzio
		String codiceIstatComuneDivorzio = componente.getCodiceIstatComuneTribunaleDivorzio();
		if(codiceIstatComuneDivorzio!=null){
			Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(codiceIstatComuneDivorzio);
			model.addAttribute("comuneTribunaleDivorzio", comuneByCodiceISTAT.getDenominazione());
		}
		
		//Atto divorzio trascritto
		String codiceIstatComuneDivorzioTrascritto = componente.getCodiceIstatComuneTrascrizioneDivorzio();
		if(codiceIstatComuneDivorzioTrascritto!=null){
			Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(codiceIstatComuneDivorzioTrascritto);
			model.addAttribute("comuneTribunaleDivorzioTrascritto", comuneByCodiceISTAT.getDenominazione());
		}
				
		//Atto morte
		String codiceIstatMorte = componente.getCodiceIstatComuneMorte();
		if(codiceIstatMorte!=null){
			Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(codiceIstatMorte);
			model.addAttribute("comuneMorte", comuneByCodiceISTAT.getDenominazione());
		}
		
		//Atto morte coniuge trascritto
		String codiceIstatMorteConiugeTrascritto = componente.getCodiceIstatComuneMorteConiugeTrascritto();
		if(codiceIstatMorteConiugeTrascritto!=null){
			Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(codiceIstatMorteConiugeTrascritto);
			model.addAttribute("comuneMorteConiugeTrascritto", comuneByCodiceISTAT.getDenominazione());
		}
	}

}
