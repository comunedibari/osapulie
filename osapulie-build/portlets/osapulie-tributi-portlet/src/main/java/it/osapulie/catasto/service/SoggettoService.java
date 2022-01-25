package it.osapulie.catasto.service;

import it.osapulie.catasto.domain.Soggetto;

public interface SoggettoService {
	
	/**
	 * Restituisce un soggetto in base al suo codice fiscale
	 * 
	 * */

	public Soggetto getSoggettoByCf(String cf);
	
	public int getIdSoggettoByCf(String cf);
}
