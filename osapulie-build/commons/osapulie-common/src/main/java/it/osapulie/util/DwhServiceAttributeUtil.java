package it.osapulie.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
 
import javax.portlet.PortletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.osapulie.domain.Azienda;
import it.osapulie.domain.Comune;
import it.osapulie.domain.Delega;
import it.osapulie.domain.DwhServizioAttribute;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.servizi.Servizio;
import it.osapulie.service.CommonService;
import it.osapulie.service.ComuneService;
import it.osapulie.service.DelegaService;
import it.osapulie.service.DwhService;

import it.osapulie.service.ServizioService;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.util.dto.DwhServizioAttributeDTO;

public class DwhServiceAttributeUtil {

	private String codiceServizio;
	private String uuidOperazione;
	private CommonService commonService;
	private ComuneService comuneService;
	private DelegaService delegaService;
	private ServizioService servizioService;

	private PortletRequest request;
	private DwhService dwhService;
	private DwhServizioAttribute dwAttribute;
	private DwhServizioAttributeDTO dwAttributeDto;
	

	private Servizio servizio;
	private Azienda azienda;
	private UserPreferences userPreferences;
	private Comune comune;
	private ProfiloUtenteCittadino profiloUtenteCittadino;
	private InfoUserUtil infoUserUtil;

	private static DwhServiceAttributeUtil factoryAttr;
	protected static Logger log = LoggerFactory.getLogger(DwhServiceAttributeUtil.class);

	public static DwhServiceAttributeUtil get(CommonService commonService, ComuneService comuneService,
			DelegaService delegaService, ServizioService servizioService, UserPreferences userPreferences,
			PortletRequest request, DwhService dwhService) {

		factoryAttr = new DwhServiceAttributeUtil(commonService, comuneService, delegaService, servizioService, userPreferences,
				request, dwhService);
		return factoryAttr;
	}

	private DwhServiceAttributeUtil(CommonService commonService, ComuneService comuneService,
			DelegaService delegaService, ServizioService servizioService, UserPreferences userPreferences,
			PortletRequest request, DwhService dwhService) {
		super();
		this.commonService = commonService;
		this.comuneService = comuneService;
		this.delegaService = delegaService;
		this.servizioService = servizioService;
		this.userPreferences = userPreferences;
		this.request = request;
		this.dwhService = dwhService;
		this.dwAttribute = new DwhServizioAttribute();
	}

	private DwhServizioAttribute saveDwhServiceAttribute() {
	
		dwAttribute.setCittadino_autenticazione_forte(cittadino().isAutenticazioneForte());
		dwAttribute.setCittadino_canale_autenticazione(cittadino().getCanaleAutenticazione());
		dwAttribute.setCittadino_comune(cittadino().getComuneIsa()!=null?cittadino().getComuneIsa().getNome():null);
		dwAttribute.setCittadino_eta(userUtil().getEta() + "");
		dwAttribute.setCittadino_livello_autenticazione(cittadino().getLivelloAutenticazione()!=null?cittadino().getLivelloAutenticazione().intValue():0);
		dwAttribute.setCittadino_provincia(cittadino_provincia());
		dwAttribute.setCittadino_regione(cittadino_regione());
		dwAttribute.setCittadino_sesso(userUtil().getSesso());
		dwAttribute.setCittadino_userid(cittadino().getId() + "");
		dwAttribute.setComune(comune().getDenominazione());
		dwAttribute.setData_evento(new Date());
		

		prewriteEnte(dwAttribute);

		dwAttribute.setHost_app("OSAPulie");
		dwAttribute.setServizio_autenticazone(servizio().isAutenticazioneForte());
		dwAttribute.setServizio_data_richiesta(new Date());
		dwAttribute.setServizio_fine(null);
		dwAttribute.setServizio_inizio(new Date());
		dwAttribute.setServizio_code(codiceServizio);
		dwAttribute.setServizio_nome(servizio().getNomeServizio());
		dwAttribute.setServizio_parametro1(null);
		dwAttribute.setServizio_parametro2(null);
		dwAttribute.setServizio_parametro3(null);

		dwAttribute.setServizio_uri(servizio().getUri());
		dwAttribute.setUuid(uuidOperazione);

		return dwhService.saveServizioAttribute(dwAttribute);

	}

	public DwhServizioAttribute salva() {
		try {
			return saveDwhServiceAttribute();
		} catch (Exception e) {
			log.error("DwhServizioAttribute salva",e);
		}
		return null;
	}
	
	public DwhServizioAttributeDTO getServizioAttribute() throws IllegalAccessException, InvocationTargetException {
		dwAttribute.setCittadino_autenticazione_forte(cittadino().isAutenticazioneForte());
		dwAttribute.setCittadino_canale_autenticazione(cittadino().getCanaleAutenticazione());
		dwAttribute.setCittadino_comune(cittadino().getComuneIsa()!=null?cittadino().getComuneIsa().getNome():null);
		dwAttribute.setCittadino_eta(userUtil().getEta() + "");
		dwAttribute.setCittadino_livello_autenticazione(cittadino().getLivelloAutenticazione()!=null?cittadino().getLivelloAutenticazione().intValue():0);
		dwAttribute.setCittadino_provincia(cittadino_provincia());
		dwAttribute.setCittadino_regione(cittadino_regione());
		dwAttribute.setCittadino_sesso(userUtil().getSesso());
		dwAttribute.setCittadino_userid(cittadino().getId() + "");
		dwAttribute.setComune(comune().getDenominazione());
		dwAttribute.setData_evento(new Date());
		

		prewriteEnte(dwAttribute);

		dwAttribute.setHost_app("OSAPulie");
		dwAttribute.setServizio_autenticazone(servizio().isAutenticazioneForte());
		dwAttribute.setServizio_data_richiesta(new Date());
		dwAttribute.setServizio_fine(null);
		dwAttribute.setServizio_inizio(new Date());
		dwAttribute.setServizio_code(codiceServizio);
		dwAttribute.setServizio_nome(servizio().getNomeServizio());
		dwAttribute.setServizio_parametro1(null);
		dwAttribute.setServizio_parametro2(null);
		dwAttribute.setServizio_parametro3(null);

		dwAttribute.setServizio_uri(servizio().getUri());
		dwAttribute.setUuid(uuidOperazione);
		dwAttributeDto = new DwhServizioAttributeDTO();
		log.info("DwhSerivzioAttributeDTO: "+dwAttributeDto);
		log.info("DwhSerivzioAttribute: "+dwAttribute);
		BeanUtils.copyProperties(dwAttributeDto, dwAttribute);

		return dwAttributeDto;
	}

	public DwhServiceAttributeUtil setUUID(String uuid) {
		this.uuidOperazione = uuid;
		return this;
	}

 

	private String cittadino_regione() {
		try {
		if (cittadino().getComuneIsa() != null && cittadino().getComuneIsa().getComune() != null
				&& cittadino().getComuneIsa().getComune().getProvincia() != null)
			return cittadino().getComuneIsa().getComune().getProvincia().getDenominazioneRegione();
		}catch (Exception e) {
			log.error("method: cittadino_regione",e);	 
		}
		return null; 
	}

	private String cittadino_provincia() {
		try {
		if (cittadino().getComuneIsa() != null && cittadino().getComuneIsa().getComune() != null
				&& cittadino().getComuneIsa().getComune().getProvincia() != null)
			return cittadino().getComuneIsa().getComune().getProvincia().getDenominazioneProvincia();
		}catch (Exception e) {
			log.error("method: cittadino_provincia",e); 
		}
		return null;
	}

	private void prewriteEnte(DwhServizioAttribute dwAttribute) {

		try {
			if (ente().getResponsabile() != null) {
				dwAttribute.setEnte_partita_iva(ente().getPartitaIva());
				dwAttribute.setEnte_tipo(ente().getTipo());
				dwAttribute.setEnte_userid("" + ente().getResponsabile().getId());
				if (ente().getResponsabile() != null
						&& ente().getResponsabile().getComuneIsa() != null
						&& ente().getResponsabile().getComuneIsa().getComune() != null)
					dwAttribute.setEnte_comune(ente().getResponsabile().getComuneIsa().getComune().getDenominazione());
				if (ente().getResponsabile() != null
						&& ente().getResponsabile().getComuneIsa() != null
						&& ente().getResponsabile().getComuneIsa().getComune() != null
						&& ente().getResponsabile().getComuneIsa().getComune().getProvincia() != null) {
					dwAttribute.setEnte_provincia(ente().getResponsabile().getComuneIsa().getComune().getProvincia()
							.getDenominazioneProvincia());
					dwAttribute.setEnte_regione(ente().getResponsabile().getComuneIsa().getComune().getProvincia()
							.getDenominazioneRegione());
				}
			}
		} catch (Exception e) {
			log.error("method: prewriteEnte",e); 
		}

	}
	
	

	private Servizio servizio() {
		try {
		
		log.info("COD_SERVIZIO:: "+this.codiceServizio);
		if (this.servizio == null)
			this.servizio = servizioService.getServizioByCodiceServizio(this.codiceServizio);
		}catch (Exception e) {
			log.error("method: servizio",e); 	 
		}
		if (this.servizio == null)
		servizio= new Servizio();
		return this.servizio;
	}

	public DwhServiceAttributeUtil setCodiceServizio(String codiceServizio) {
		this.codiceServizio = codiceServizio;
		return this;
	}

	private UserPreferences userPreferences() {
		if (this.userPreferences == null) {
			this.userPreferences= new UserPreferences();
		log.info("object: userPreferences [null] settyng default new"); 
		}
		return this.userPreferences;
	}
	
	
	private Azienda ente() {
		try {
		if (this.azienda == null) {
			Long idDelega = userPreferences().getIdDelega();
			if (idDelega != null) {
				Delega delegaByPk = delegaService.getDelegaByPk(idDelega);
				if (delegaByPk != null && delegaByPk.isAttiva()) {
					this.azienda = delegaByPk.getDelegato();
				}
			}
		}
		}catch (Exception e) {
			log.error("method: ente",e); 	
		}
		if (this.azienda == null)
			azienda= new Azienda();
		return this.azienda;

	}

	private ProfiloUtenteCittadino cittadino() {
		if (this.profiloUtenteCittadino == null)
			this.profiloUtenteCittadino = commonService.getCurrentProfiloUtenteCittadino(userPreferences());
		if (this.profiloUtenteCittadino == null)
			profiloUtenteCittadino=new ProfiloUtenteCittadino();
		return this.profiloUtenteCittadino; 
	}

	private Comune comune() {
		if (this.comune == null)
			this.comune = comuneService.getComuneByCodiceISTAT(userPreferences().getCodiceIstatComune());
		if (this.comune == null)
			comune= new Comune();
		return this.comune;
	}

	private InfoUserUtil userUtil() {
		try {
		if (this.infoUserUtil == null)
			this.infoUserUtil = new InfoUserUtil(userPreferences().getCodiceFiscaleServizio());
		}catch (Exception e) {
		this.infoUserUtil = new InfoUserUtil("AAAAAA14A01AAAAA");
		log.info("object: infoUserUtil [null] settyng default code AAAAAA14A01AAAAA"); 
		log.error("method: userUtil",e);
		}
		 
		return this.infoUserUtil;
	}

	public static DwhServizioAttribute saveTimeFine(DwhService dwhService, IUpdateAttribute updateAttribute) {
		return dwhService.updateServizioAttribute(updateAttribute);
	}

}
