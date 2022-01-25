/************************************************************************************
 * Copyright (c) 2011, 2014 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pdds.adapter;

import it.puglia.rupar.egov.pdd.portaDiDominio.Busta;
import it.puglia.rupar.egov.pdd.portaDiDominio.EccezionePDDS;
import it.puglia.rupar.egov.pdd.portaDiDominio.InterfacciaServizio;
import it.puglia.rupar.egov.pdd.portaDiDominio.PortaApplicativa;
import it.puglia.rupar.egov.pdd.portaDiDominio.vo.busta.DescrizioneMessaggioVO;
import it.puglia.rupar.egov.pdd.portaDiDominio.vo.busta.MessaggioVO;
import it.puglia.rupar.egov.pdd.portaDiDominio.vo.busta.OraRegistrazioneVO;
import it.puglia.rupar.egov.pdd.portaDiDominio.vo.busta.ProfiloCollaborazioneVO;
import it.puglia.rupar.egov.pdd.portaDiDominio.vo.busta.ProfiloTrasmissioneVO;

import java.io.ByteArrayInputStream;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author Gianluca Pindinelli
 *
 */
public class PortaApplicativaEnte extends PortaApplicativa implements InterfacciaServizio {

	protected Logger log = LoggerFactory.getLogger(PortaApplicativaEnte.class.getName());

	public PortaApplicativaEnte() throws EccezionePDDS {
		super(PortaApplicativa.DEDICATA);
	}

	public Busta applica(Busta arg0) throws Exception {

		Busta msgOut = null;
		String richiesta = "";
		try {
			if (arg0.hasAllegati()) {
				Iterator i = null;
				try {
					i = arg0.getListaAllegati().iterator();
				}
				catch (Throwable e) {
					// System.out.println(e.toString());
					return msgOut;
				}
				while (i.hasNext()) {
					DescrizioneMessaggioVO dmvo = (DescrizioneMessaggioVO) i.next();
					log.debug("applica :: Descrizione: " + dmvo);
					if (dmvo.getAllegato() == null) {
						log.debug("applica :: Allegato nullo!");
					}
					else {
						try {
							String tmp = new String(dmvo.getAllegatoAsArrayOfByte());
							log.debug("applica :: Allegato: " + tmp);
							richiesta = tmp;
						}
						catch (Exception e) {
							log.error("applica :: Errore lettura allegato: " + e.getMessage(), e);
						}
					}
				}
			}
			else {
				log.debug("applica :: Non sono presenti allegati");
			}

			msgOut = arg0.creaBustaRisposta();

			String id = this.generaID();
			MessaggioVO mvo = new MessaggioVO(id, new OraRegistrazioneVO());
			mvo.setRiferimentoMessaggio(arg0.getMessaggio().getIdentificatore());
			ProfiloCollaborazioneVO pcvo = arg0.getProfiloCollaborazione();
			msgOut.setProfiloCollaborazione(pcvo);

			ProfiloTrasmissioneVO ptvo = arg0.getProfiloTrasmissione();
			msgOut.setProfiloTrasmissione(ptvo);

			String azione = msgOut.getIntestazxioneJaxb().getIntestazioneMessaggio().getAzione();

			String xml = PortaApplicativaAdapter.getInstance().getRichiestaPdd(azione, richiesta);

			log.debug("applica :: RISPOSTA XML ALLEGATA : " + xml);
			ByteArrayInputStream bais = new ByteArrayInputStream(xml.getBytes());
			DescrizioneMessaggioVO dmvo = new DescrizioneMessaggioVO();
			dmvo.setId("nuovo");
			dmvo.setAllegato(bais);

			dmvo.setHref("");
			dmvo.setLingua(DescrizioneMessaggioVO.ITALIANO_ITALIA);
			dmvo.setPosizione("posizione");
			dmvo.setRole("ruolo");
			dmvo.setTitolo("Messaggio di risposta");

			msgOut.setMessaggio(mvo);
			msgOut.setAllegato(dmvo);

		}
		catch (Exception e) {
			log.error("applica :: " + e.getMessage(), e);
		}

		return msgOut;
	}
}
