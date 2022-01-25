package it.osapulie.catasto.service;

import java.util.List;

import it.osapulie.catasto.domain.Terreno;

public interface TerrenoService {

	public List<Terreno> getTerreniByCf(String cf);
}
