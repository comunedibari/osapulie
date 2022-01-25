package it.osapulie.catasto.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import it.osapulie.catasto.domain.Soggetto;
import it.osapulie.catasto.persistence.SoggettoRepository;
import it.osapulie.catasto.service.SoggettoService;
import it.osapulie.service.ServiceLayerException;

@Service("soggettoService")
public class SoggettoServiceImpl  implements SoggettoService{

	@Inject
	private SoggettoRepository soggettoRepository;
	
	@Override
	public Soggetto getSoggettoByCf(String cf) throws ServiceLayerException{
		return soggettoRepository.getSoggettoByCf(cf);
	}

	@Override
	public int getIdSoggettoByCf(String cf) {
		return soggettoRepository.getIdSoggettoByCf(cf);
	}
	
	

}
