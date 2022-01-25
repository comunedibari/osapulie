package it.eng.tz.area.vasta.conservazione.ws.sip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.eng.tz.area.vasta.conservazione.ws.exception.ConservazioneSipException;
import it.eng.tz.area.vasta.conservazione.ws.sip.dao.ConservazioneSipDao;
import it.eng.tz.area.vasta.conservazione.ws.sip.dao.model.ConservazioneSipModel;

@Service
public class ConservazioneSipSvcImpl implements ConservazioneSipSvc {
	@Autowired
	private ConservazioneSipDao dao;
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(value="txMgrConservazioneSip", rollbackFor= {ConservazioneSipException.class}, readOnly=false)
	public void salvaConservazioneSip(ConservazioneSipModel model) throws ConservazioneSipException {
		dao.salvaConservazioneSip(model);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(value="txMgrConservazioneSip", rollbackFor= {ConservazioneSipException.class}, readOnly=false)
	public void aggiornaConservazioneSip(ConservazioneSipModel model) throws ConservazioneSipException {
		dao.aggiornaConservazioneSip(model);
	}
	@Override
	@Transactional(value="txMgrConservazioneSip", rollbackFor= {ConservazioneSipException.class}, readOnly=true)
	public List<String> daProcessare() throws ConservazioneSipException {
		return dao.recuperaDaProcesare();
	}
	@Override
	@Transactional(value="txMgrConservazioneSip", rollbackFor= {ConservazioneSipException.class}, readOnly=true)
	public List<String> recuperaFolderProcessati() throws ConservazioneSipException {
		return dao.recuperaFolderProcessati();
	}
	@Override
	@Transactional(value="txMgrConservazioneSip", rollbackFor= {ConservazioneSipException.class}, readOnly=true)
	public List<String> recuperaFolderDaProcessare() throws ConservazioneSipException {
		return dao.recuperaFolderDaProcessare();
	}
	
	@Override
	@Transactional(value="txMgrConservazioneSip", rollbackFor= {ConservazioneSipException.class}, readOnly=true)
	public List<String> recuperaAllFolder() throws ConservazioneSipException {
		return dao.recuperaAllFolder();
	}
	
	@Override
	@Transactional(value="txMgrConservazioneSip", rollbackFor= {ConservazioneSipException.class}, readOnly=true)
	public boolean isFolderExist(String folder) throws ConservazioneSipException {
		return dao.isFolderExist(folder);
	}
	
}
