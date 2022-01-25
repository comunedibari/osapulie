package it.osapulie.catasto.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import it.osapulie.catasto.domain.Fabbricato;
import it.osapulie.catasto.persistence.FabbricatoRepository;
import it.osapulie.catasto.service.FabbricatoService;

@Service("fabbricatoService")
public class FabbricatoServiceImpl implements FabbricatoService {

	@Inject
	FabbricatoRepository fabbricatoRepository;
	
	@Override
	public List<Fabbricato> getFabbricatiByCf(String cf) {
		return fabbricatoRepository.getFabbricatiByCf(cf);
	}
}
