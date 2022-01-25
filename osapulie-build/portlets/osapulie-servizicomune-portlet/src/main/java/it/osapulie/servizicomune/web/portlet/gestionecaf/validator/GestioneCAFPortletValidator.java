/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.servizicomune.web.portlet.gestionecaf.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.osapulie.domain.Delega;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.service.DelegaService;
import it.osapulie.service.ProfiloUtenteService;
import it.osapulie.servizicomune.web.portlet.gestionecaf.form.DelegaModel;

/**
 * Form validator per {@link DelegaModel}.
 *
 * @author Gianluca Pindinelli
 */
@Component("gestioneCAFPortletValidator")
public class GestioneCAFPortletValidator implements Validator {

	@Autowired
	private DelegaService delegaService;

	@Autowired
	private ProfiloUtenteService profiloUtenteService;

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return DelegaModel.class.isAssignableFrom(clazz);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {

		DelegaModel delegaModel = (DelegaModel) target;
/*

		if (delegaModel.getIdDelega() == 0 && delegaModel.getAllegato() != null && delegaModel.getAllegato().isEmpty()) {
			errors.rejectValue("allegato", "error.label.required");
		}
*/

		if (delegaModel.getIdComuneIsa() == 0) {
			errors.rejectValue("idComuneIsa", "error.label.required");
		}

		if (delegaModel.getServiziAssociatiStrings() == null || delegaModel.getServiziAssociatiStrings().isEmpty()) {
			errors.rejectValue("serviziAssociatiStrings", "error.label.servizi.required");
		}

		if (delegaModel.getIdDelega() != 0) {
			Delega delega = delegaService.getDelegaByPk(delegaModel.getIdDelega());
			String piva = delegaModel.getPiva();
			String codiceFiscaleDelegante = delegaModel.getCodiceFiscaleDelegante();
			ProfiloUtenteCittadino delegante = profiloUtenteService.getProfiloUtenteCittadinoByCf(codiceFiscaleDelegante);
			List<Delega> delegaList = delegante.getDelegheDelegati();
			for (Delega delega2 : delegaList) {
				// Controllo che non sia possibile modificare il comune di una delega se ne
				// esiste un'altra che contiene la stessa PIVA
				if (delega.getId().longValue() != delega2.getId().longValue() && delegaModel.getIdComuneIsa() == delega2.getComuneIsa().getId() && piva.equals(delega2.getDelegato().getPartitaIva())) {
					errors.rejectValue("piva", "error.label.delegatoJustAdded");
					break;
				}
			}
		}

		if (delegaModel.getNumeroDocumento() == null || delegaModel.getNumeroDocumento().isEmpty()){
			errors.rejectValue("numeroDocumento", "error.label.servizi.required");
		}

		if(delegaModel.getCodiceFiscaleDelegante() == null || delegaModel.getCodiceFiscaleDelegante().isEmpty()){
			errors.rejectValue("codiceFiscaleDelegante", "error.label.required");
		}

		if(delegaModel.getDataScadenzaDocumento() == null || delegaModel.getDataScadenzaDocumento().isEmpty()){
			errors.rejectValue("dataScadenzaDocumento", "error.label.required");
		}

		if(delegaModel.getRilasciatoDa() == null || delegaModel.getRilasciatoDa().isEmpty()){
			errors.rejectValue("rilasciatoDa", "error.label.required");
		}

        //Bisogna distinguere i casi dove si sceglie la sottomissione della delega con allegato o con la firma grafometrica
		if (delegaModel.getTipoAcquisizione().equalsIgnoreCase("allegato") && delegaModel.getIdDelega() == 0 && (delegaModel.getAllegato() == null || delegaModel.getAllegato().isEmpty())) {
			errors.rejectValue("allegato", "error.label.required");
		}

        if(delegaModel.getTipoAcquisizione().equalsIgnoreCase("firmaGrafometrica") && delegaModel.getIdDelega() == 0 && (delegaModel.getStringFirma() == null || delegaModel.getStringFirma().isEmpty())){
            errors.rejectValue("stringFirma", "error.label.required");
        }

        if(delegaModel.getTipoAcquisizione().equalsIgnoreCase("firmaGrafometrica") && delegaModel.getIdDelega() == 0 && (delegaModel.getDocumento() == null || delegaModel.getDocumento().isEmpty())){
            errors.rejectValue("documento", "error.label.required");
        }


	}
}
