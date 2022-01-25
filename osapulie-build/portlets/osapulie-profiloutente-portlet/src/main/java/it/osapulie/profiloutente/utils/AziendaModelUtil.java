/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.profiloutente.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.liferay.portal.kernel.util.Validator;

import it.osapulie.domain.Azienda;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.ProfiloUtenteCittadinoAzienda;
import it.osapulie.profiloutente.model.view.AziendaModel;

/**
 * Classe di utility per l'oggetto {@link AziendaModel}.
 *
 * @author Gianluca Pindinelli
 *
 */
public class AziendaModelUtil {

	protected static Logger log = LoggerFactory.getLogger(AziendaModelUtil.class.getName());

	public static AziendaModel getAziendaModelFromAzienda(Azienda azienda) {

		log.debug("getAziendaModelFromAzienda :: entering method");

		AziendaModel aziendaModel = new AziendaModel();

		if (Validator.isNotNull(azienda)) {

			aziendaModel = new AziendaModel();

			if (azienda.getId() != null) {
				aziendaModel.setIdAzienda(azienda.getId());
			}
			aziendaModel.setPartitaIva(azienda.getPartitaIva());
			aziendaModel.setRagioneSociale(azienda.getRagioneSociale());
			if (Validator.isNotNull(azienda.getSede()) && Validator.isNotNull(azienda.getSede().getComune())) {
				aziendaModel.setComuneSede(azienda.getSede().getComune().getId());
				aziendaModel.setComuneSedeString(azienda.getSede().getComune().getDenominazione());
				aziendaModel.setNrCivicoSede(azienda.getSede().getNrCivico());
				aziendaModel.setViaSede(azienda.getSede().getVia());
			}

			aziendaModel.setMail(azienda.getMail());
			aziendaModel.setMailPec(azienda.getMailPec());
			aziendaModel.setAziendaInUso(azienda.isInUso());
			aziendaModel.setAziendaAttiva(azienda.isAttiva());
			aziendaModel.setTipoAzienda(azienda.getTipo());
			aziendaModel.setTipoEnte(azienda.getTipoEnte());
			
			aziendaModel.setAziendaAggOperatori(azienda.isAggOperatori());

			List<ProfiloUtenteCittadinoAzienda> profiliUtenteCittadinoAziende = azienda.getProfiliUtenteCittadinoAzienda();

			if (profiliUtenteCittadinoAziende != null) {
				List<ProfiloUtenteCittadino> profiliUtenteCittadino = new ArrayList<ProfiloUtenteCittadino>();
				for (ProfiloUtenteCittadinoAzienda profiloUtenteCittadinoAzienda : profiliUtenteCittadinoAziende) {
					profiliUtenteCittadino.add(profiloUtenteCittadinoAzienda.getProfiloUtenteCittadino());
				}
				aziendaModel.setProfiliUtenteAssociati(profiliUtenteCittadino);
				aziendaModel.setProfiliUtenteAssociatiSearch(new HashSet<ProfiloUtenteCittadino>(profiliUtenteCittadino));
			}
		}

		return aziendaModel;
	}

}
