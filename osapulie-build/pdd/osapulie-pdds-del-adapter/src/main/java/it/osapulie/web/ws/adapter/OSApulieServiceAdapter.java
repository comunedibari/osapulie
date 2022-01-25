/**
 * Copyright (c) 2011 Links Management & Technology S.p.A.
 */

package it.osapulie.web.ws.adapter;

import it.puglia.rupar.egov.pdd.portaDiDominio.EccezionePDDS;

/**
 * @author Fabio
 *
 */
public interface OSApulieServiceAdapter {
    /**
     * 
     * @param xmlRichiesta
     * @param nomeServizio
     * @param indirizzoPddRemota
     * @return
     */
	String eseguiRichiestaServizio(
            String xmlRichiesta,
            String nomeServizio,
            String indirizzoPddRemota );
    /**
     * Remove this.
     * @param area
     * @param mittente
     * @param servizio
     * @param url
     * @param message
     * @param titolo
     * @return
     * @throws EccezionePDDS
     */
    @Deprecated
    String chiamaEnte( String area, String mittente, String servizio, String url, String message, String titolo ) throws EccezionePDDS;
}