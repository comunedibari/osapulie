package it.osapulie.catasto.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import it.osapulie.catasto.domain.Terreno;
import it.osapulie.catasto.persistence.TerrenoRepository;
import it.osapulie.catasto.service.TerrenoService;

@Service("terrenoService")
public class TerrenoServiceImpl implements TerrenoService {

	@Inject
	TerrenoRepository terrenoRepository;
	
	@Override
	public List<Terreno> getTerreniByCf(String cf) {
		return terrenoRepository.getTerreniByCf(cf);
	}

}
