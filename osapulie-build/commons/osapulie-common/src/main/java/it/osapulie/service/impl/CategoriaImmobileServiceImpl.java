/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.osapulie.domain.categoriaimmobile.Agevolazione;
import it.osapulie.domain.categoriaimmobile.CategoriaImmobile;
import it.osapulie.domain.categoriaimmobile.CategoriaImmobileTributo;
import it.osapulie.domain.categoriaimmobile.CategoriaImmobileTributoId;
import it.osapulie.domain.categoriaimmobile.Detrazione;
import it.osapulie.domain.categoriaimmobile.Esenzione;
import it.osapulie.persistence.AgevolazioneRepository;
import it.osapulie.persistence.CategoriaImmobileCustomRepository;
import it.osapulie.persistence.CategoriaImmobileRepository;
import it.osapulie.persistence.CategoriaImmobileTributoRepository;
import it.osapulie.persistence.DetrazioneRepository;
import it.osapulie.persistence.EsenzioneRepository;
import it.osapulie.service.CategoriaImmobileService;
import it.osapulie.service.ServiceLayerException;

/**
 * Implementazione del service per la gestione deggli oggetti {@link CategoriaImmobile}.
 *
 * @author Gianluca Pindinelli
 *
 */
@Service("categoriaImmobileService")
@Transactional
public class CategoriaImmobileServiceImpl implements CategoriaImmobileService {

	@Inject
	private CategoriaImmobileRepository categoriaImmobileRepository;

	@Inject
	private CategoriaImmobileCustomRepository categoriaImmobileCustomRepository;

	@Inject
	private CategoriaImmobileTributoRepository categoriaImmobileTributoRepository;

	@Inject
	private AgevolazioneRepository agevolazioneRepository;

	@Inject
	private DetrazioneRepository detrazioneRepository;

	@Inject
	private EsenzioneRepository esenzioneRepository;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.CategoriaImmobileService#getAllCategorieImmobili()
	 */
	@Override
	public List<CategoriaImmobile> getAllCategorieImmobili() throws ServiceLayerException {

		Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "descrizione"));

		List<CategoriaImmobile> list = (List<CategoriaImmobile>) categoriaImmobileRepository.findAll(sort);

		return list;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.service.CategoriaImmobileService#getCategorieImmobiliByComuneISAAndAnno(java.
	 * lang.Long, java.lang.Integer)
	 */
	@Override
	public List<CategoriaImmobile> getCategorieImmobiliByComuneISAAndAnno(Long idComuneISA, Integer anno) throws ServiceLayerException {
		return categoriaImmobileCustomRepository.findCategorieImmobiliByComuneISAAndAnno(idComuneISA, anno);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.service.CategoriaImmobileService#getCategorieImmobiliByComuneISAAndAnno(java.
	 * lang.Long, java.lang.Integer)
	 */
	@Override
	public List<CategoriaImmobile> getCategorieImmobiliByComuneISAAndAnnoAndServizio(Long idComuneISA, Integer anno, String codServizio) throws ServiceLayerException {
		return categoriaImmobileCustomRepository.findCategorieImmobiliByComuneISAAndAnnoAndServizio(idComuneISA, anno, codServizio);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.CategoriaImmobileService#getCategoriaImmobileById(long)
	 */
	@Override
	public CategoriaImmobile getCategoriaImmobileById(long id) throws ServiceLayerException {
		return categoriaImmobileRepository.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.CategoriaImmobileService#deleteCategoriaImmobile(it.osapulie.domain.
	 * categoriaimmobile.CategoriaImmobileTributo)
	 */
	@Override
	public void deleteCategoriaImmobile(CategoriaImmobileTributo categoriaImmobileTributo) throws ServiceLayerException {
		categoriaImmobileTributoRepository.delete(categoriaImmobileTributo);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.CategoriaImmobileService#deleteCategoriaImmobile(it.osapulie.domain.
	 * categoriaimmobile.CategoriaImmobile)
	 */
	@Override
	public void deleteCategoriaImmobile(CategoriaImmobile categoriaImmobile) throws ServiceLayerException {
		categoriaImmobileRepository.delete(categoriaImmobile);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.CategoriaImmobileService#saveCategoriaImmobile(it.osapulie.domain.
	 * categoriaimmobile.CategoriaImmobile)
	 */
	@Override
	public CategoriaImmobile saveCategoriaImmobile(CategoriaImmobile categoriaImmobile) throws ServiceLayerException {

		CategoriaImmobile save = categoriaImmobileRepository.save(categoriaImmobile);

		List<CategoriaImmobileTributo> categorieImmobiliTributi = categoriaImmobile.getCategorieImmobiliTributi();
		if (categorieImmobiliTributi != null) {
			for (CategoriaImmobileTributo categoriaImmobileTributo : categorieImmobiliTributi) {
				if (categoriaImmobileTributo.getPk().getIdCategoriaImmobile() == 0) {
					categoriaImmobileTributo.getPk().setIdCategoriaImmobile(save.getId());
				}
				CategoriaImmobileTributo save1 = categoriaImmobileTributoRepository.save(categoriaImmobileTributo);

				// Salvataggio agevolazioni
				List<Agevolazione> agevolazioni = categoriaImmobileTributo.getAgevolazioni();
				if (agevolazioni != null) {
					for (Agevolazione agevolazione : agevolazioni) {
						agevolazione.setCategoriaImmobileTributo(save1);
						agevolazione = agevolazioneRepository.save(agevolazione);
					}
				}

				// Salvataggio detrazioni
				List<Detrazione> detrazioni = categoriaImmobileTributo.getDetrazioni();
				if (detrazioni != null) {
					for (Detrazione detrazione : detrazioni) {
						detrazione.setCategoriaImmobileTributo(save1);
						detrazione = detrazioneRepository.save(detrazione);
					}
				}

				// Salvataggio esenzioni
				List<Esenzione> esenzioni = categoriaImmobileTributo.getEsenzioni();
				if (esenzioni != null) {
					for (Esenzione esenzione : esenzioni) {
						esenzione.setCategoriaImmobileTributo(save1);
						esenzione = esenzioneRepository.save(esenzione);
					}
				}

			}
		}

		save = categoriaImmobileRepository.findOne(save.getId());

		return save;
	}

	/*
	 * mellif 17/04/2015 : nuovo metodo di salvataggio della categoria immobile
	 */
	@Override
	public CategoriaImmobile salvaCategoriaImmobile(CategoriaImmobile categoriaImmobile) throws ServiceLayerException {

		CategoriaImmobile save = categoriaImmobileRepository.save(categoriaImmobile);

		List<CategoriaImmobileTributo> categorieImmobiliTributi = categoriaImmobile.getCategorieImmobiliTributi();
		if (categorieImmobiliTributi != null) {
			for (CategoriaImmobileTributo categoriaImmobileTributo : categorieImmobiliTributi) {
				categoriaImmobileTributo.setCategoriaImmobile(save);

				CategoriaImmobileTributoId pk = new CategoriaImmobileTributoId(categoriaImmobileTributo.getComuneISA().getId(), save.getId(), categoriaImmobileTributo.getTributo().getId());
				categoriaImmobileTributo.setPk(pk);

				CategoriaImmobileTributo save1 = categoriaImmobileTributoRepository.save(categoriaImmobileTributo);

				// Salvataggio agevolazioni
				List<Agevolazione> agevolazioni = categoriaImmobileTributo.getAgevolazioni();
				if (agevolazioni != null) {
					for (Agevolazione agevolazione : agevolazioni) {
						agevolazione.setCategoriaImmobileTributo(save1);
						agevolazione = agevolazioneRepository.save(agevolazione);
					}
				}

				// Salvataggio detrazioni
				List<Detrazione> detrazioni = categoriaImmobileTributo.getDetrazioni();
				if (detrazioni != null) {
					for (Detrazione detrazione : detrazioni) {
						detrazione.setCategoriaImmobileTributo(save1);
						detrazione = detrazioneRepository.save(detrazione);
					}
				}

				// Salvataggio esenzioni
				List<Esenzione> esenzioni = categoriaImmobileTributo.getEsenzioni();
				if (esenzioni != null) {
					for (Esenzione esenzione : esenzioni) {
						// esenzione.setCategoriaImmobileTributo(save1);
						esenzione = esenzioneRepository.save(esenzione);
					}
				}

			}
		}

		save = categoriaImmobileRepository.findOne(save.getId());

		return save;
	}

	@Override
	public CategoriaImmobileTributo saveCategoriaImmobileTributo(CategoriaImmobileTributo categoriaImmobileTributo) throws ServiceLayerException {

		CategoriaImmobileTributo save = categoriaImmobileTributoRepository.save(categoriaImmobileTributo);

		return save;
	}
}
