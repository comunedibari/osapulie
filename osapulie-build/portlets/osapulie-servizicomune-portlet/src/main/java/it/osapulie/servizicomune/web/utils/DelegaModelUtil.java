/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.servizicomune.web.utils;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.portal.kernel.util.Validator;

import it.osapulie.domain.Azienda;
import it.osapulie.domain.Delega;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.servizi.Servizio;
import it.osapulie.servizicomune.model.DelegaReportModel;
import it.osapulie.servizicomune.model.ServiziAssociatiReportModel;
import it.osapulie.servizicomune.web.portlet.gestionecaf.form.DelegaModel;

/**
 * Classe di utility per l'oggetto {@link DelegaModel}.
 *
 * @author Gianluca Pindinelli
 *
 */
public class DelegaModelUtil {

	protected static Logger log = LoggerFactory.getLogger(DelegaModelUtil.class.getName());

	public static DelegaModel getDelegaModelByDelega(Delega delega) {

		log.debug("getDelegaModelByDelega :: entering method");

		DelegaModel delegaModel = new DelegaModel();

		if (Validator.isNotNull(delega) && delega.getId() != null) {

			delegaModel.setIdDelega(delega.getId());
			delegaModel.setIdDelegante(delega.getIdDelegante());
			delegaModel.setIdDelegato(delega.getIdDelegato());

			// delegaModel.setPiva(delega.getDelegato().getProfessionista().getPartitaIva());
			delegaModel.setServiziAssociati(delega.getServizi());

			delegaModel.setIdComuneIsa(delega.getComuneIsa().getId());
			delegaModel.setPiva(delega.getDelegato().getPartitaIva());
			delegaModel.setCodiceFiscaleDelegante(delega.getDelegante().getCodiceFiscale());
			if (delega.getAllegato() != null && delega.getAllegato().length > 0 && delega.getNomeAllegato() != null) {
				delegaModel.setNomeAllegato(delega.getNomeAllegato());
			}
			
			delegaModel.setNumeroDocumento((delega.getnDocumento() != null && !delega.getnDocumento().isEmpty()) ? delega.getnDocumento() : "");
			delegaModel.setDataScadenzaDocumento((delega.getDataScadenza() != null && !delega.getDataScadenza().isEmpty()) ? delega.getDataScadenza() : "");
			delegaModel.setRilasciatoDa((delega.getRilasciatoDa() != null && !delega.getRilasciatoDa().isEmpty()) ? delega.getRilasciatoDa() : "");
			
		}

		return delegaModel;
	}

	public static Delega getDelegaByDelegaModel(DelegaModel delegaModel, Delega delega) {

		log.debug("getDelegaByDelegaModel :: entering method");

		// TODO - sostituire il Validator con uno non Liferay
		if (Validator.isNotNull(delegaModel)) {

			delega.setDataRichiesta(new Date());
		}

		return delega;

	}
	
	/**
	 * 
	 * @param delegaModel
	 * @param azienda
	 * @return
	 */
	public static DelegaReportModel getDelegaReportModel(DelegaModel delegaModel, Azienda azienda, ProfiloUtenteCittadino delegato,ProfiloUtenteCittadino delegante, List<Servizio> servizi){
		
		log.debug("getDelegaReportModel :: entering method");
		
		DelegaReportModel drm = new DelegaReportModel();
		
		//recupero dati delegante
		drm.setCognomeDelegante(delegante.getCognome());
		drm.setNomeDelegante(delegante.getNome());
		drm.setnDocumento(delegaModel.getNumeroDocumento());
		drm.setDataScadenzaDocumento(delegaModel.getDataScadenzaDocumento());
		drm.setRilasciatoDa(delegaModel.getRilasciatoDa());
		drm.setCfDelegante(delegaModel.getCodiceFiscaleDelegante());

		
		//drm.setFirmaDelegante(new ByteArrayInputStream(Base64.decodeBase64((delegaModel.getStringFirma()).getBytes())));

		
		//recupero lista servizi attivati per l'utente
		List<ServiziAssociatiReportModel> sarm = new ArrayList<ServiziAssociatiReportModel>();
		if(servizi != null && !servizi.isEmpty()){
			for(Servizio srv:servizi){
				ServiziAssociatiReportModel app = new ServiziAssociatiReportModel();
				app.setNomeServizio(srv.getNomeServizio());
				app.setDescrizione(srv.getDescrizione());
				app.setCodiceServizio(srv.getCodiceServizio());
				sarm.add(app);
			}
		}
		
		drm.setServizi(sarm);
		
		//recupero dati operatore (delegato)
		drm.setCognomeDelegato(delegato.getCognome());
		drm.setNomeDelegato(delegato.getNome());
		drm.setCfDelegato(delegato.getCodiceFiscale());
		
		//recupero dati Azienda
		drm.setTipoAzienda(azienda.getTipo());
		drm.setDenominazione(azienda.getRagioneSociale());
		drm.setCfAzienda(azienda.getPartitaIva());
		drm.setComuneAzienda(azienda.getSede().getComune().getDenominazione());
		drm.setViaAzienda(azienda.getSede().getVia()+" n."+azienda.getSede().getNrCivico());
		drm.setTelAzienda("");
		drm.setPecMailAzienda(azienda.getMailPec()!=null && !azienda.getMailPec().isEmpty() ? azienda.getMailPec() : azienda.getMail());
		
		return drm;
	}
}
