/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.profiloutente.utils;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.liferay.portal.kernel.util.Validator;

import it.osapulie.domain.Delega;
import it.osapulie.profiloutente.model.view.DelegaModel;

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

		// TODO - sostituire il Validator con uno non Liferay
		if (Validator.isNotNull(delega) && delega.getId() != null) {

			delegaModel.setIdDelega(delega.getId());
			delegaModel.setIdDelegante(delega.getIdDelegante());
			delegaModel.setIdDelegato(delega.getIdDelegato());

			// delegaModel.setPiva(delega.getDelegato().getProfessionista().getPartitaIva());
			delegaModel.setServiziAssociati(delega.getServizi());

			delegaModel.setIdComuneIsa(delega.getComuneIsa().getId());
			delegaModel.setPiva(delega.getDelegato().getPartitaIva());
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
}
