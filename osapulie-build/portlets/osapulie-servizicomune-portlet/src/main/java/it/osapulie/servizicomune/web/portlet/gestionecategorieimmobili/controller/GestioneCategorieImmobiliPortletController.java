/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.servizicomune.web.portlet.gestionecategorieimmobili.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.categoriaimmobile.Agevolazione;
import it.osapulie.domain.categoriaimmobile.BaseDiCalcolo;
import it.osapulie.domain.categoriaimmobile.CategoriaImmobile;
import it.osapulie.domain.categoriaimmobile.CategoriaImmobileTributo;
import it.osapulie.domain.categoriaimmobile.CategoriaImmobileTributoId;
import it.osapulie.domain.categoriaimmobile.Detrazione;
import it.osapulie.domain.categoriaimmobile.Esenzione;
import it.osapulie.domain.categoriaimmobile.TipologiaCategoriaImmobile;
import it.osapulie.domain.categoriaimmobile.TipologiaDetrazione;
import it.osapulie.domain.categoriaimmobile.Tributo;
import it.osapulie.infrastructure.impl.DateUtils;
import it.osapulie.infrastructure.security.OSApulieUserDetails;
import it.osapulie.service.BaseDiCalcoloService;
import it.osapulie.service.CategoriaImmobileService;
import it.osapulie.service.TipologiaCategoriaImmobileService;
import it.osapulie.service.TipologiaDetrazioneService;
import it.osapulie.service.TributoService;
import it.osapulie.servizicomune.web.portlet.BaseController;
import it.osapulie.servizicomune.web.portlet.gestionecategorieimmobili.form.AgevolazioneForm;
import it.osapulie.servizicomune.web.portlet.gestionecategorieimmobili.form.CategoriaImmobileForm;
import it.osapulie.servizicomune.web.portlet.gestionecategorieimmobili.form.DetrazioneForm;
import it.osapulie.servizicomune.web.portlet.gestionecategorieimmobili.form.EsenzioneForm;
import it.osapulie.servizicomune.web.portlet.gestionecategorieimmobili.form.GestioneCategorieImmobiliForm;
import it.osapulie.servizicomune.web.portlet.gestionecategorieimmobili.form.TributoForm;
import it.osapulie.web.portlet.util.PortletHelper;

/**
 * Portlet per garantire al gestore comunale la gestione delle categorie di immobili proprie del
 * comune di appartenenza.
 *
 * @author Gianluca Pindinelli
 */
@Controller("gestioneCategorieImmobiliPortletController")
@RequestMapping("view")
@SessionAttributes({ "gestioneCategorieImmobiliForm", "canSave" })
public class GestioneCategorieImmobiliPortletController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(GestioneCategorieImmobiliPortletController.class);

	@Inject
	private PortletHelper helper;

	@Inject
	private CategoriaImmobileService categoriaImmobileService;

	@Inject
	private TipologiaCategoriaImmobileService tipologiaCategoriaImmobileService;

	@Inject
	private TributoService tributoService;

	@Inject
	private BaseDiCalcoloService baseDiCalcoloService;

	@Inject
	private TipologiaDetrazioneService tipologiaDetrazioneService;

	@Inject
	@Named("categoriaImmobilePortletValidator")
	private Validator validator;

	@Autowired
	public ResourceBundleMessageSource messageSource;

	/**
	 * Default entry quando la portlet viene visualizzata per la prima volta.
	 *
	 * @param model
	 * @return
	 */
	@RenderMapping
	public String homePage(Model model, PortletRequest request) {

		OSApulieUserDetails osApulieUserDetails = helper.getOsApulieUserDetails();
		// Controllo che l'utente sia un gestore comunale (!= Superadmin)
		boolean access = accessToService(osApulieUserDetails);

		if (access) {
			model.addAttribute("comuneIsa", osApulieUserDetails.getProfiloUtenteCittadino().getGestoreComuneIsa());
			model.addAttribute("access", access);
			model.addAttribute("gestioneCategorieImmobiliForm", loadGestioneAliquoteForm(Integer.parseInt(DateUtils.getAnnoCorrente())));
			model.addAttribute("canSave", new Boolean(false));
		}

		return toLocalViewPath("home");
	}

	@RenderMapping(params = "action=home")
	public String renderHomePage(Model model, PortletRequest request) {

		checkUserAccess(model);
		model.addAttribute("canSave", new Boolean(false));

		return toLocalViewPath("home");
	}

	@RenderMapping(params = "action=addCategoriaImmobile")
	public String addCategoriaImmobile(@ModelAttribute("gestioneCategorieImmobiliForm") GestioneCategorieImmobiliForm gestioneCategorieImmobiliForm, Model model, PortletRequest request) {

		checkUserAccess(model);

		CategoriaImmobileForm categoriaImmobileForm;
		try {
			categoriaImmobileForm = getCategoriaImmobileForm(null);
			categoriaImmobileForm.setAnno(gestioneCategorieImmobiliForm.getAnno());
			model.addAttribute("categoriaImmobileForm", categoriaImmobileForm);
			model.addAttribute("canSave", new Boolean(true));
		}
		catch (Exception e) {
			log.error("addCategoriaImmobile :: " + e.getMessage(), e);
		}

		return toLocalViewPath("edit");
	}

	@RenderMapping(params = "action=edit")
	public String editCategoriaImmobile(@RequestParam Long idCategoriaImmobile, @RequestParam Integer anno, Model model, PortletRequest request) {

		checkUserAccess(model);

		CategoriaImmobile categoriaImmobileById = categoriaImmobileService.getCategoriaImmobileById(idCategoriaImmobile);
		try {
			CategoriaImmobileForm categoriaImmobileForm = getCategoriaImmobileForm(categoriaImmobileById);
			categoriaImmobileForm.setAnno(anno);
			model.addAttribute("categoriaImmobileForm", categoriaImmobileForm);
			model.addAttribute("canSave", new Boolean(true));

		}
		catch (Exception e) {
			log.error("editCategoriaImmobile :: " + e.getMessage(), e);
		}

		return toLocalViewPath("edit");
	}

	@RenderMapping(params = "action=renderEdit")
	public String renderEditPage(Model model) throws Exception {
		checkUserAccess(model);
		return toLocalViewPath("edit");
	}

	@ModelAttribute("tipologieCategorieImmobili")
	public List<TipologiaCategoriaImmobile> getTipologieCategorieImmobili() {

		List<TipologiaCategoriaImmobile> allTipologiaCategoriaImmobile = tipologiaCategoriaImmobileService.getAllTipologiaCategoriaImmobile();

		return allTipologiaCategoriaImmobile;
	}

	@ModelAttribute("basiDiCalcolo")
	public List<BaseDiCalcolo> getBasiDiCalcolo() {

		List<BaseDiCalcolo> allBaseDiCalcolo = baseDiCalcoloService.getAllBaseDiCalcolo();

		return allBaseDiCalcolo;
	}

	@ModelAttribute("tipologieDetrazioni")
	public List<TipologiaDetrazione> getTipologieDetrazioni() {

		List<TipologiaDetrazione> tipologieDetrazioni = tipologiaDetrazioneService.getAllTipologiaDetrazione();

		return tipologieDetrazioni;
	}

	/**
	 * @param model
	 */
	private void checkUserAccess(Model model) {
		OSApulieUserDetails osApulieUserDetails = helper.getOsApulieUserDetails();
		// Controllo che l'utente sia un gestore comunale (!= Superadmin)
		boolean access = accessToService(osApulieUserDetails);

		if (access) {
			model.addAttribute("comuneIsa", osApulieUserDetails.getProfiloUtenteCittadino().getGestoreComuneIsa());
			model.addAttribute("access", access);
		}
	}

	/**
	 * @param osApulieUserDetails
	 * @return
	 */
	private boolean accessToService(OSApulieUserDetails osApulieUserDetails) {
		boolean access = false;

		if (helper.userHasRole(osApulieUserDetails.getLiferayUser(), "Administrator")) {
			access = true;
		}

		if (osApulieUserDetails.getProfiloUtenteCittadino().getGestoreComuneIsa() != null) {
			access = true;
		}

		return access;
	}

	@ModelAttribute("anni")
	public Map<String, String> loadAnni() {

		Map<String, String> anniMap = new LinkedHashMap<String, String>();

		int annoAttuale = Integer.parseInt(DateUtils.getAnnoCorrente());

		for (int i = -3; i < 2; i++) {
			int anno = annoAttuale + i;
			anniMap.put(Integer.toString(anno), Integer.toString(anno));
		}

		return anniMap;
	}

	private GestioneCategorieImmobiliForm loadGestioneAliquoteForm(int anno) {

		GestioneCategorieImmobiliForm gestioneComuneForm = new GestioneCategorieImmobiliForm();
		gestioneComuneForm.setAnno(anno);

		OSApulieUserDetails osApulieUserDetails = helper.getOsApulieUserDetails();
		ComuneISA comuneISA = osApulieUserDetails.getProfiloUtenteCittadino().getGestoreComuneIsa();

		List<CategoriaImmobile> allCategorieImmobili = categoriaImmobileService.getCategorieImmobiliByComuneISAAndAnno(comuneISA.getId(), anno);
		List<CategoriaImmobileForm> categoriaImmobileForms = new ArrayList<CategoriaImmobileForm>();

		for (CategoriaImmobile categoriaImmobile : allCategorieImmobili) {
			CategoriaImmobileForm categoriaImmobileFormNew;
			try {
				categoriaImmobileFormNew = getCategoriaImmobileForm(categoriaImmobile);
				categoriaImmobileForms.add(categoriaImmobileFormNew);
			}
			catch (Exception e) {
				log.error("loadGestioneAliquoteForm :: " + e.getMessage(), e);
			}
		}

		gestioneComuneForm.setCategoriaImmobileForms(categoriaImmobileForms);

		return gestioneComuneForm;
	}

	/**
	 * @param categoriaImmobile
	 * @return
	 */
	private CategoriaImmobileForm getCategoriaImmobileForm(CategoriaImmobile categoriaImmobile) throws Exception {

		CategoriaImmobileForm categoriaImmobileForm = new CategoriaImmobileForm();

		List<TributoForm> tributi = new ArrayList<TributoForm>();
		if (categoriaImmobile != null) {
			categoriaImmobileForm.setIdCategoriaImmobile(categoriaImmobile.getId());
			categoriaImmobileForm.setCoefficienteMoltiplicazione(categoriaImmobile.getCoefficienteMoltiplicazione());
			if (categoriaImmobile.getTipologiaCategoriaImmobile() != null) {
				categoriaImmobileForm.setDescrizione(categoriaImmobile.getTipologiaCategoriaImmobile().getDescrizione());
			}
			if (categoriaImmobile.getBaseDiCalcolo() != null) {
				categoriaImmobileForm.setIdBaseCalcolo(categoriaImmobile.getBaseDiCalcolo().getId());
			}
			if (categoriaImmobile.getTipologiaCategoriaImmobile() != null) {
				categoriaImmobileForm.setIdTipologiaCategoriaImmobile(categoriaImmobile.getTipologiaCategoriaImmobile().getId());
			}

			List<CategoriaImmobileTributo> categorieImmobiliTributi = categoriaImmobile.getCategorieImmobiliTributi();
			if (categorieImmobiliTributi != null) {
				for (CategoriaImmobileTributo categoriaImmobileTributo : categorieImmobiliTributi) {

					Tributo tributo = categoriaImmobileTributo.getTributo();
					if (tributo != null) {
						TributoForm tributoForm = new TributoForm();
						tributoForm.setIdTributo(tributo.getId());
						tributoForm.setNome(tributo.getNome());
						tributoForm.setAliquota(categoriaImmobileTributo.getAliquota());
						// Agevolazioni
						List<AgevolazioneForm> agevolazioniForm = new ArrayList<AgevolazioneForm>();
						List<Agevolazione> agevolazioni = categoriaImmobileTributo.getAgevolazioni();
						if (agevolazioni != null) {
							for (Agevolazione agevolazione : agevolazioni) {
								AgevolazioneForm agevolazioneForm = new AgevolazioneForm();
								agevolazioneForm.setId(agevolazione.getId());
								agevolazioneForm.setNome(agevolazione.getNome());
								agevolazioneForm.setValore(agevolazione.getValore());
								agevolazioniForm.add(agevolazioneForm);
							}
						}
						tributoForm.setAgevolazioni(agevolazioniForm);
						// Detrazioni
						List<DetrazioneForm> detrazioniForm = new ArrayList<DetrazioneForm>();
						List<Detrazione> detrazioni = categoriaImmobileTributo.getDetrazioni();
						if (detrazioni != null) {
							for (Detrazione detrazione : detrazioni) {
								DetrazioneForm detrazioneForm = new DetrazioneForm();
								detrazioneForm.setId(detrazione.getId());
								detrazioneForm.setDescrizione(detrazione.getDescrizione());
								detrazioneForm.setImporto(detrazione.getImporto());
								detrazioneForm.setInfo(detrazione.getInfo());
								detrazioneForm.setIdTipologiaDetrazione(detrazione.getId());
								TipologiaDetrazione tipologiaDetrazione = detrazione.getTipologiaDetrazione();
								if (tipologiaDetrazione != null) {
									detrazioneForm.setIdTipologiaDetrazione(tipologiaDetrazione.getId());
								}
								detrazioniForm.add(detrazioneForm);
							}
						}
						tributoForm.setDetrazioni(detrazioniForm);
						// Esenzioni
						List<EsenzioneForm> esenzioni = new ArrayList<EsenzioneForm>();
						List<Esenzione> esenzioni2 = categoriaImmobileTributo.getEsenzioni();
						if (esenzioni2 != null) {
							for (Esenzione esenzione : esenzioni2) {
								EsenzioneForm esenzioneForm = new EsenzioneForm();
								esenzioneForm.setId(esenzione.getId());
								esenzioneForm.setDescrizione(esenzione.getDescrizione());
								esenzioni.add(esenzioneForm);
							}
						}
						tributoForm.setEsenzioni(esenzioni);
						tributoForm.setNome(tributo.getNome());

						tributi.add(tributoForm);
					}
				}
			}

		}
		// Caricamento nuove istanze tributi
		else {
			List<Tributo> allTributo = tributoService.getAllTributo();
			if (allTributo != null) {
				for (Tributo tributo2 : allTributo) {
					TributoForm tributoForm = new TributoForm();
					tributoForm.setIdTributo(tributo2.getId());
					tributoForm.setNome(tributo2.getNome());
					tributi.add(tributoForm);
				}
			}
		}

		categoriaImmobileForm.setTributi(tributi);

		return categoriaImmobileForm;
	}

	@ActionMapping(params = "action=updateAnno")
	public void updateAnno(@ModelAttribute("gestioneCategorieImmobiliForm") GestioneCategorieImmobiliForm gestioneComuneForm, BindingResult bindingResult, Model model, ActionRequest actionRequest,
			ActionResponse response) {

		Integer anno = gestioneComuneForm.getAnno();

		GestioneCategorieImmobiliForm gestioneComuneFormByAnno = loadGestioneAliquoteForm(anno);
		model.addAttribute("gestioneCategorieImmobiliForm", gestioneComuneFormByAnno);

		response.setRenderParameter("action", "home");
	}

	@ActionMapping(params = "action=save")
	public void save(@ModelAttribute("categoriaImmobileForm") CategoriaImmobileForm categoriaImmobileForm, BindingResult bindingResult, @ModelAttribute("canSave") Boolean canSave, Model model,
			ActionRequest actionRequest, ActionResponse response, SessionStatus sessionStatus) {

		if (actionRequest.getParameter("Salva") != null) {

			if (canSave.booleanValue()) {
				try {
					validator.validate(categoriaImmobileForm, bindingResult);

					if (bindingResult.hasErrors()) {

						for (ObjectError error : bindingResult.getAllErrors()) {
							log.error("Error: " + error.getCode() + " - " + error.getDefaultMessage());
						}

						model.addAttribute("formError", messageSource.getMessage("error.verificareDatiInseriti", null, actionRequest.getLocale()));
						response.setRenderParameter("idCategoriaImmobile", String.valueOf(categoriaImmobileForm.getIdCategoriaImmobile()));
						response.setRenderParameter("anno", String.valueOf(categoriaImmobileForm.getAnno()));

						response.setRenderParameter("action", "renderEdit");
						return;
					}
					else {
						saveData(categoriaImmobileForm);
						model.addAttribute("message", messageSource.getMessage("message.label.operazioneEseguitaCorrettamente", null, actionRequest.getLocale()));

						// Ricarico le liste aggiornate con i nuovi inserimenti
						model.addAttribute("gestioneCategorieImmobiliForm", loadGestioneAliquoteForm(Integer.parseInt(DateUtils.getAnnoCorrente())));
						response.setRenderParameter("action", "home");
					}
				}
				catch (Exception e) {
					log.error("save :: " + e.getMessage(), e);
				}
			}
		}
		else if (actionRequest.getParameter("Annulla") != null) {
			response.setRenderParameter("action", "home");
		}
	}

	/**
	 * @param categoriaImmobileForm
	 */
	private void saveData(CategoriaImmobileForm categoriaImmobileForm) {

		// TODO completare salvataggio (persist e merge)
		OSApulieUserDetails osApulieUserDetails = helper.getOsApulieUserDetails();
		ComuneISA comuneISA = osApulieUserDetails.getProfiloUtenteCittadino().getGestoreComuneIsa();

		/* Estraggo da form i dati da salvare su db */
		CategoriaImmobile categoriaImmobile = new CategoriaImmobile();

		if (categoriaImmobileForm.getIdCategoriaImmobile() > 0) {
			categoriaImmobile = categoriaImmobileService.getCategoriaImmobileById(categoriaImmobileForm.getIdCategoriaImmobile());
		}

		// Coefficiente di moltiplicazione
		categoriaImmobile.setCoefficienteMoltiplicazione(categoriaImmobileForm.getCoefficienteMoltiplicazione());

		// Base di calcolo
		BaseDiCalcolo baseDiCalcolo = baseDiCalcoloService.getBaseDiCalcoloById(categoriaImmobileForm.getIdBaseCalcolo());
		categoriaImmobile.setBaseDiCalcolo(baseDiCalcolo);

		// Tipologia categoria immobile
		TipologiaCategoriaImmobile tci = tipologiaCategoriaImmobileService.getTipologiaCategoriaImmobileById(categoriaImmobileForm.getIdTipologiaCategoriaImmobile());
		categoriaImmobile.setTipologiaCategoriaImmobile(tci);
		// Categorie immobili tributi
		List<TributoForm> tributiForm = categoriaImmobileForm.getTributi();

		if (tributiForm != null) {
			List<CategoriaImmobileTributo> listCIT = new ArrayList<CategoriaImmobileTributo>();

			for (TributoForm tf : tributiForm) {

				CategoriaImmobileTributo cit = new CategoriaImmobileTributo();
				cit.setComuneISA(comuneISA);
				cit.setAliquota(tf.getAliquota());

				// Tributo
				Tributo tributo = tributoService.getTributoById(tf.getIdTributo());
				cit.setTributo(tributo);
				cit.setAnno(categoriaImmobileForm.getAnno());
				// TODO cit.setCodiceTributo();

				// PK
				CategoriaImmobileTributoId pk = new CategoriaImmobileTributoId();
				if (categoriaImmobile.getId() != null) {
					// Modifica di una categoria già esistente sul DB
					pk.setIdCategoriaImmobile(categoriaImmobile.getId());
				}
				else {
					// Aggiunta di una nuova categoria
					pk.setIdCategoriaImmobile(0);
				}
				pk.setIdComuneISA(comuneISA.getId());
				pk.setIdTributo(tributo.getId());
				cit.setPk(pk);

				// Agevolazioni
				if (tf.getAgevolazioni() != null) {
					cit.setAgevolazioni(new ArrayList<Agevolazione>());
					for (AgevolazioneForm af : tf.getAgevolazioni()) {
						if (af.getNome() != null && !af.getNome().isEmpty()) {
							Agevolazione agevolazione = new Agevolazione();
							agevolazione.setNome(af.getNome());
							agevolazione.setValore(af.getValore());
							agevolazione.setCategoriaImmobileTributo(cit);
							cit.getAgevolazioni().add(agevolazione);
						}
					}
				}
				// Detrazioni
				if (tf.getDetrazioni() != null) {
					List<Detrazione> detrazioni = new ArrayList<Detrazione>();
					for (DetrazioneForm det : tf.getDetrazioni()) {
						if (det.getDescrizione() != null && !det.getDescrizione().isEmpty()) {
							Detrazione detrazione = new Detrazione();
							detrazione.setDescrizione(det.getDescrizione());
							detrazione.setInfo(det.getInfo());
							detrazione.setImporto(det.getImporto());
							TipologiaDetrazione tipologiaDet = tipologiaDetrazioneService.getTipologiaDetrazioneById(det.getIdTipologiaDetrazione());
							detrazione.setTipologiaDetrazione(tipologiaDet);
							detrazione.setCategoriaImmobileTributo(cit);
							detrazioni.add(detrazione);
						}
					}
					cit.setDetrazioni(detrazioni);
				}
				// Esenzioni
				if (tf.getEsenzioni() != null) {
					cit.setEsenzioni(new ArrayList<Esenzione>());
					for (EsenzioneForm ef : tf.getEsenzioni()) {
						if (ef.getDescrizione() != null && !ef.getDescrizione().isEmpty()) {
							Esenzione esenzione = new Esenzione();
							esenzione.setCategoriaImmobileTributo(cit);
							esenzione.setDescrizione(ef.getDescrizione());
							cit.getEsenzioni().add(esenzione);
						}
					}
				}
				listCIT.add(cit);
			}
			categoriaImmobile.setCategorieImmobiliTributi(listCIT);
		}
		categoriaImmobileService.salvaCategoriaImmobile(categoriaImmobile);
	}

	@RenderMapping(params = "action=delete")
	public String elimina(@RequestParam("idCategoriaImmobile") long idCategoriaImmobile, Model model, PortletRequest request) {

		CategoriaImmobile categoriaImmobile = categoriaImmobileService.getCategoriaImmobileById(idCategoriaImmobile);
		if (categoriaImmobile != null) {
			categoriaImmobileService.deleteCategoriaImmobile(categoriaImmobile);
		}

		// Aggiorno la lista
		checkUserAccess(model);
		model.addAttribute("gestioneCategorieImmobiliForm", loadGestioneAliquoteForm(Integer.parseInt(DateUtils.getAnnoCorrente())));

		return toLocalViewPath("home");
	}

	@ModelAttribute(value = "listaTipologieDetrazioni")
	public String getListaTipologieDetrazioni() throws Exception {

		String options = " ";
		List<TipologiaDetrazione> tipologieDetrazioni = tipologiaDetrazioneService.getAllTipologiaDetrazione();
		if (tipologieDetrazioni != null) {
			for (TipologiaDetrazione t : tipologieDetrazioni) {
				options += "<option value=" + t.getId() + ">" + t.getNome() + "</option>";
			}
		}
		else {
			options = "<option></option>";
		}
		return options;
	}

	/**
	 *
	 * Utility method per calcolare e restituire il view ID all'interno della corretta alberatura di
	 * sottodirectory delle JSP: in un certo senso si tratta del namespace delle view locali a
	 * questo controller. Ad esempio: <code> toLocalViewPath( "home" ) </code> restituirà <code>
	 * "/gestionecategorieimmobili/home" </code>
	 *
	 * @param viewName l'ID della view locale
	 * @return il view ID completo del path a cui la vista appartiene
	 */
	private static String toLocalViewPath(String viewName) {
		return "gestionecategorieimmobili/" + viewName;
	}
}
