/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.osapulie.domain.BozzaDocumenti;
import it.osapulie.persistence.BozzaDocumentiRepository;
import it.osapulie.service.BozzaDocumentiService;
import it.osapulie.service.ServiceLayerException;

/**
 * Implementazione del service per gli oggetti {@link BozzaDocumenti}.
 *
 * @author Gianluca De Felice
 *
 */
@Service("bozzaDocumentiService")
@Transactional
public class BozzaDocumentiServiceImpl implements BozzaDocumentiService {

	@Inject
	private BozzaDocumentiRepository bozzaDocumentiRepository;
	
	@Override
	public BozzaDocumenti getBozzaDocumentiByPk(long idBozzaDocumenti) throws ServiceLayerException {
		return bozzaDocumentiRepository.findOne(idBozzaDocumenti);
	}

	@Override
	public void saveBozzaDocumenti(BozzaDocumenti bozzaDocumenti) throws ServiceLayerException {
		try {
			bozzaDocumentiRepository.save(bozzaDocumenti);
		}
		catch (Exception e) {
			throw new ServiceLayerException("There was an error when saving an object of type BozzaDocumenti :: " + e.getMessage(), e);
		}
	}

	@Override
	public void saveListBozzaDocumenti(List<BozzaDocumenti> listBozzaDocumenti) throws ServiceLayerException {
		try {
			for (BozzaDocumenti bozzaDocumenti: listBozzaDocumenti)
				bozzaDocumentiRepository.save(bozzaDocumenti);
		}
		catch (Exception e) {
			throw new ServiceLayerException("There was an error when saving an object of type BozzaDocumenti :: " + e.getMessage(), e);
		}
	}

	@Override
	public List<BozzaDocumenti> getAllBozzaDocumenti() throws ServiceLayerException {
		return (List<BozzaDocumenti>) bozzaDocumentiRepository.findAll();
	}

	@Override
	public void deleteBozzaDocumenti(long idBozzaDocumenti) throws ServiceLayerException {
		bozzaDocumentiRepository.delete(idBozzaDocumenti);
	}

	@Override
	public BozzaDocumenti updateBozzaDocumenti(BozzaDocumenti bozzaDocumenti) throws ServiceLayerException {
		return bozzaDocumentiRepository.save(bozzaDocumenti);
	}

	@Override
	public List<BozzaDocumenti> findByIdBozza(Long idBozza) throws ServiceLayerException {
		return bozzaDocumentiRepository.findByIdBozza(idBozza);
	}

}
