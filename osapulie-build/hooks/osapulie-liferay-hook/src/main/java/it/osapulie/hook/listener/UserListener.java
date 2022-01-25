/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.hook.listener;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.portal.ModelListenerException;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;

import it.osapulie.domain.Comune;
import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.Indirizzo;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.hook.utils.ApplicationBootstrap;

/**
 * Listener per l'esecuzione di comandi relativi operazioni sull'utente (oggetto {@link User}).
 *
 * @author Gianluca Pindinelli, Gianni Barone
 *
 */
public class UserListener implements ModelListener<User> {

	protected Logger log = LoggerFactory.getLogger(UserListener.class.getName());

	/*
	 * (non-Javadoc)
	 *
	 * @see com.liferay.portal.model.ModelListener#onAfterAddAssociation(java.lang.Object,
	 * java.lang.String, java.lang.Object)
	 */
	@Override
	public void onAfterAddAssociation(Object arg0, String arg1, Object arg2) throws ModelListenerException {

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.liferay.portal.model.ModelListener#onAfterCreate(java.lang.Object)
	 */
	@Override
	public void onAfterCreate(User user) throws ModelListenerException {

		// Creazione utente su ISA
		try {
			ProfiloUtenteCittadino profilo = new ProfiloUtenteCittadino();
			profilo.setCodiceFiscale(user.getScreenName().toUpperCase());
			profilo.setCognome(user.getLastName());
			profilo.setMail(user.getEmailAddress());
			profilo.setNome(user.getFirstName());
			profilo.setPassword(user.getPassword());
			profilo.setId(user.getUserId());

			Indirizzo indirizzo = new Indirizzo();

			// Assegnazione comune di default
			Comune comune = ApplicationBootstrap.INSTANCE.getComuneRepository().findOne(-1L);
			indirizzo.setComune(comune);
			profilo.setResidenza(indirizzo);
			// Assegnazione comune di default
			ComuneISA comuneIsa = ApplicationBootstrap.INSTANCE.getComuneISARepository().findOne(-1L);
			profilo.setComuneIsa(comuneIsa);

			ApplicationBootstrap.INSTANCE.getProfiloUtenteCittadinoRepository().save(profilo);

		}
		catch (Exception e) {
			log.error("onAfterCreate :: " + e.getMessage(), e);
		}

		// Associazione utente a community OSApulie
		try {
			List<Group> groups = GroupLocalServiceUtil.getGroups(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			Group osApulieGroup = null;
			for (Group group : groups) {
				if (group.getName().equals(PropsUtil.get("osapulie.community"))) {
					osApulieGroup = group;
				}
			}
			long[] groupIds = new long[1];
			if (osApulieGroup != null) {
				groupIds[0] = osApulieGroup.getGroupId();
				GroupLocalServiceUtil.addUserGroups(user.getUserId(), groupIds);
			}

		}
		catch (SystemException e) {
			log.error("onAfterCreate :: " + e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.liferay.portal.model.ModelListener#onAfterRemove(java.lang.Object)
	 */
	@Override
	public void onAfterRemove(User user) throws ModelListenerException {

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.liferay.portal.model.ModelListener#onAfterRemoveAssociation(java.lang.Object,
	 * java.lang.String, java.lang.Object)
	 */
	@Override
	public void onAfterRemoveAssociation(Object arg0, String arg1, Object arg2) throws ModelListenerException {
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.liferay.portal.model.ModelListener#onAfterUpdate(java.lang.Object)
	 */
	@Override
	public void onAfterUpdate(User user) throws ModelListenerException {
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.liferay.portal.model.ModelListener#onBeforeAddAssociation(java.lang.Object,
	 * java.lang.String, java.lang.Object)
	 */
	@Override
	public void onBeforeAddAssociation(Object arg0, String arg1, Object arg2) throws ModelListenerException {
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.liferay.portal.model.ModelListener#onBeforeCreate(java.lang.Object)
	 */
	@Override
	public void onBeforeCreate(User arg0) throws ModelListenerException {
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.liferay.portal.model.ModelListener#onBeforeRemove(java.lang.Object)
	 */
	@Override
	public void onBeforeRemove(User user) throws ModelListenerException {

		// Eliminazione utente da ISA ed LDAP
		try {
			ApplicationBootstrap.INSTANCE.getProfilazioneUtenteService().deleteById(String.valueOf(user.getUserId()));
		}
		catch (Exception e) {
			log.error("onAfterRemove :: " + e.getMessage(), e);
		}

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.liferay.portal.model.ModelListener#onBeforeRemoveAssociation(java.lang.Object,
	 * java.lang.String, java.lang.Object)
	 */
	@Override
	public void onBeforeRemoveAssociation(Object arg0, String arg1, Object arg2) throws ModelListenerException {
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.liferay.portal.model.ModelListener#onBeforeUpdate(java.lang.Object)
	 */
	@Override
	public void onBeforeUpdate(User arg0) throws ModelListenerException {
	}

}
