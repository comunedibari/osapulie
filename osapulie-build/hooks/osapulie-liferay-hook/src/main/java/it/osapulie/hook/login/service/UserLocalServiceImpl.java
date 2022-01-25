package it.osapulie.hook.login.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserLocalServiceWrapper;

import it.osapulie.domain.Pin;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.hook.utils.ApplicationBootstrap;
import it.osapulie.infrastructure.security.OSApulieUserDetails;
import it.osapulie.infrastructure.security.ProfilazioneUtenteException;
import it.osapulie.web.portlet.util.PortletConstants;

/**
 * Implementazione adHoc del service dell'oggetto {@link User} per OSApulie.
 * 
 * @author Gianluca Pindinelli
 * 
 */
public class UserLocalServiceImpl extends UserLocalServiceWrapper {

	protected Logger log = LoggerFactory.getLogger(UserLocalServiceImpl.class.getName());

	public UserLocalServiceImpl(UserLocalService userLocalService) {
		super(userLocalService);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.liferay.portal.service.UserLocalServiceWrapper#updatePassword(long,
	 * java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public User updatePassword(long userId, String password1, String password2, boolean passwordReset) throws PortalException, SystemException {

		// Update password liferay user
		User userUpdated = super.updatePassword(userId, password1, password2, passwordReset);

		// Update password su ISA + reset autenticazione forte
		OSApulieUserDetails osApulieUserDetails;
		try {
			osApulieUserDetails = ApplicationBootstrap.INSTANCE.getProfilazioneUtenteService().getById(String.valueOf(userUpdated.getUserId()));
			ProfiloUtenteCittadino profiloUtenteCittadino = osApulieUserDetails.getProfiloUtenteCittadino();
			profiloUtenteCittadino.setPassword(password1);
			// Annullo l'autenticazione forte
			profiloUtenteCittadino.setAutenticazioneForte(false);
			profiloUtenteCittadino.setDataAutenticazioneForte(null);
			ApplicationBootstrap.INSTANCE.getProfilazioneUtenteService().update(osApulieUserDetails);

			// Annullamento PIN
			Pin pin = ApplicationBootstrap.INSTANCE.getPinService().getLastPin(userUpdated.getUserId());
			if (pin != null && pin.getStato().equals(PortletConstants.STATO_PIN_ASSEGNATO)) {
				pin.setStato(PortletConstants.STATO_PIN_ANNULLATO);
				ApplicationBootstrap.INSTANCE.getPinService().updatePin(pin);
			}

		}
		catch (ProfilazioneUtenteException e) {
			log.error("updatePassword :: " + e.getMessage(), e);
		}

		return userUpdated;
	}
}
