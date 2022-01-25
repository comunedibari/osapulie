package it.osapulie.service;

import it.osapulie.domain.servizi.ServizioErogato;

import java.util.List;

/**
 * Il Catalog dei Servizi espone metodi per interrogare la PA del comune riguardo i servizi supportati.
 * @author Mario Scalas
 */
public interface CatalogoServizi {
	boolean isServizioSupportato( Long idEnte, String nomeServizio );
	List<ServizioErogato> getElencoServiziErogati();
	List<ServizioErogato> getServizioErogato( String nomeServizio );
}
