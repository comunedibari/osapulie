package it.osapulie.catasto.service;

import java.util.List;

import it.osapulie.catasto.domain.Fabbricato;

public interface FabbricatoService {
	
	public List<Fabbricato> getFabbricatiByCf(String cf);

}
