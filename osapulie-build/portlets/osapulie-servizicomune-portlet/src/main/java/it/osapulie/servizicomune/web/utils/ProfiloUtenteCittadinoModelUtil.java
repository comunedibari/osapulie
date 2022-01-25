/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.servizicomune.web.utils;

import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.servizicomune.model.ProfiloUtenteCittadinoModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Classe di utility per l'oggetto {@link ProfiloUtenteCittadinoModel}.
 *
 * @author Antonio Magrì
 *
 */
public class ProfiloUtenteCittadinoModelUtil {

	protected static Logger log = LoggerFactory.getLogger(ProfiloUtenteCittadinoModelUtil.class.getName());

	public static ProfiloUtenteCittadinoModel getProfiloUtenteCittadino(ProfiloUtenteCittadino profiloUtenteCittadino) {


		ProfiloUtenteCittadinoModel profiloUtenteCittadinoModel = null;

		if(profiloUtenteCittadino!=null){
			profiloUtenteCittadinoModel = new ProfiloUtenteCittadinoModel();
			profiloUtenteCittadinoModel.setId(profiloUtenteCittadino.getId());
			profiloUtenteCittadinoModel.setAutenticazioneForte(profiloUtenteCittadino.isAutenticazioneForte());
			profiloUtenteCittadinoModel.setCanaleAutenticazione(profiloUtenteCittadino.getCanaleAutenticazione());
			profiloUtenteCittadinoModel.setCanaleComunicazione(profiloUtenteCittadino.getCanaleComunicazione());
			profiloUtenteCittadinoModel.setCodiceFiscale(profiloUtenteCittadino.getCodiceFiscale());
			profiloUtenteCittadinoModel.setCognome(profiloUtenteCittadino.getCognome());
			profiloUtenteCittadinoModel.setDataAutenticazioneForte(profiloUtenteCittadino.getDataAutenticazioneForte());
			profiloUtenteCittadinoModel.setIndirizzo(profiloUtenteCittadino.getResidenza()!=null?profiloUtenteCittadino.getResidenza().getVia():"");
			profiloUtenteCittadinoModel.setLivelloAutenticazione(profiloUtenteCittadino.getLivelloAutenticazione());
			profiloUtenteCittadinoModel.setMail(profiloUtenteCittadino.getMail());
			profiloUtenteCittadinoModel.setMailPec(profiloUtenteCittadino.getMailPec());
			profiloUtenteCittadinoModel.setNome(profiloUtenteCittadino.getNome());
			profiloUtenteCittadinoModel.setNumeroCivico(profiloUtenteCittadino.getResidenza()!=null?profiloUtenteCittadino.getResidenza().getNrCivico():"");
			
		}
		

		return profiloUtenteCittadinoModel;
	}

	
}
