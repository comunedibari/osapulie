/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pdds.service.internal.tributi;

import java.math.BigInteger;
import java.net.URL;
import java.util.List;

//import org.apache.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.osapulie.pdds.adapter.util.XMLHelper;
import it.osapulie.pdds.common.PddService;
import it.osapulie.pdds.ws.client.categorieimmobili.CategorieImmobiliServiceLocator;
import it.osapulie.pdds.ws.client.categorieimmobili.CategorieImmobiliServicePortType;
import it.osapulie.pdds.ws.client.categorieimmobili.Tb_agevolazione;
import it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile;
import it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_agevolazione;
import it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_detrazione;
import it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_tributo;
import it.osapulie.pdds.ws.client.categorieimmobili.Tb_detrazione;
import it.osapulie.pdds.ws.client.categorieimmobili.Tb_esenzione;
import it.osapulie.pdds.ws.client.categorieimmobili.Tb_tipologia_detrazione;
import it.osapulie.tributi.web.ws.output.types.Agevolazione;
import it.osapulie.tributi.web.ws.output.types.CategoriaImmobile;
import it.osapulie.tributi.web.ws.output.types.CategorieImmobiliRichiesta;
import it.osapulie.tributi.web.ws.output.types.CategorieImmobiliRisposta;
import it.osapulie.tributi.web.ws.output.types.CategorieImmobiliRisposta.Errore;
import it.osapulie.tributi.web.ws.output.types.Detrazione;
import it.osapulie.tributi.web.ws.output.types.Esenzione;
import it.osapulie.tributi.web.ws.output.types.TipoDetrazione;
import it.osapulie.tributi.web.ws.output.types.Tributo;

/**
 *
 * @author Gianluca Pindinelli
 *
 */
public class CategorieImmobiliService implements PddService {

	private final Logger log = LoggerFactory.getLogger(CategorieImmobiliService.class);

	private XMLHelper xmlHelper;

	@Override
	public String getResponse(String xml) {

		CategorieImmobiliRichiesta richiesta = xmlHelper.unmarshal(xml, CategorieImmobiliRichiesta.class);

		CategorieImmobiliRisposta risposta = new CategorieImmobiliRisposta();

		int anno = richiesta.getAnno();

		String result = null;
		try {

			URL wsdlLocation = new URL("http://localhost:9764/services/categorieImmobiliService?wsdl");
			CategorieImmobiliServiceLocator categorieImmobiliServiceLocator = new CategorieImmobiliServiceLocator();
			CategorieImmobiliServicePortType secureSOAP11Endpoint = categorieImmobiliServiceLocator.getSecureSOAP11Endpoint(wsdlLocation);

			Tb_categoriaimmobile[] select_all_tb_categoriaimmobile_by_anno_operation = secureSOAP11Endpoint.select_all_tb_categoriaimmobile_by_anno_operation(anno);

			if (select_all_tb_categoriaimmobile_by_anno_operation == null || select_all_tb_categoriaimmobile_by_anno_operation.length == 0) {
				Errore errore = new Errore();
				errore.setCodice(3);
				risposta.setErrore(errore);
			}
			else {
				List<CategoriaImmobile> categorieImmobili = risposta.getCategoriaImmobile();

				for (Tb_categoriaimmobile tb_categoriaimmobile : select_all_tb_categoriaimmobile_by_anno_operation) {

					CategoriaImmobile categoriaImmobile = new CategoriaImmobile();
					categoriaImmobile.setCodice(tb_categoriaimmobile.getCodice());
					categoriaImmobile.setCoefficienteMoltiplicazione(tb_categoriaimmobile.getCoefficienteMoltiplicazione());
					categoriaImmobile.setCoefficienteRivalutazione(tb_categoriaimmobile.getCoefficienteRivalutazione());
					categoriaImmobile.setDescrizione(tb_categoriaimmobile.getDescrizione());
					categoriaImmobile.setPercentualeInquilinoComodatario(tb_categoriaimmobile.getPercentualeInquilinoComodatario());
					categoriaImmobile.setPercentualeProprietario(tb_categoriaimmobile.getPercentualeProprietario());

					Tb_categoriaimmobile_tributo[] select_with_key_tb_categoriaimmobile_tributo_operation = secureSOAP11Endpoint
							.select_with_key_tb_categoriaimmobile_tributo_operation(tb_categoriaimmobile.getID().intValue());

					if (select_with_key_tb_categoriaimmobile_tributo_operation != null && select_with_key_tb_categoriaimmobile_tributo_operation.length > 0) {

						List<Tributo> tributi = categoriaImmobile.getTributi();

						for (Tb_categoriaimmobile_tributo tb_categoriaimmobile_tributo : select_with_key_tb_categoriaimmobile_tributo_operation) {
							BigInteger fk_tributo = tb_categoriaimmobile_tributo.getFk_tributo();
							Tributo tributo = new Tributo();
							tributo.setAliquota(tb_categoriaimmobile_tributo.getAliquota());
							tributo.setCodice(tb_categoriaimmobile_tributo.getCodice_tributo());
							tributo.setId(fk_tributo);

							// Agevolazioni
							Tb_categoriaimmobile_agevolazione[] select_with_key_tb_categoriaimmobile_agevolazione_operation = secureSOAP11Endpoint
									.select_with_key_tb_categoriaimmobile_agevolazione_operation(tb_categoriaimmobile.getID().intValue(), fk_tributo.intValue());

							if (select_with_key_tb_categoriaimmobile_agevolazione_operation != null && select_with_key_tb_categoriaimmobile_agevolazione_operation.length > 0) {
								List<Agevolazione> agevolazioni = tributo.getAgevolazioni();
								for (Tb_categoriaimmobile_agevolazione tb_categoriaimmobile_agevolazione : select_with_key_tb_categoriaimmobile_agevolazione_operation) {
									Agevolazione agevolazione = new Agevolazione();
									BigInteger fk_agevolazione = tb_categoriaimmobile_agevolazione.getFk_agevolazione();
									Tb_agevolazione[] select_with_key_tb_agevolazione_operation = secureSOAP11Endpoint.select_with_key_tb_agevolazione_operation(fk_agevolazione.intValue());
									if (select_with_key_tb_agevolazione_operation != null && select_with_key_tb_agevolazione_operation.length > 0) {
										Tb_agevolazione tb_agevolazione = select_with_key_tb_agevolazione_operation[0];
										agevolazione.setDescrizione(tb_agevolazione.getNome());
										agevolazione.setValore(tb_agevolazione.getValore());
										agevolazioni.add(agevolazione);
									}
								}
							}

							// Detrazioni
							Tb_categoriaimmobile_detrazione[] select_with_key_tb_categoriaimmobile_detrazione_operation = secureSOAP11Endpoint
									.select_with_key_tb_categoriaimmobile_detrazione_operation(tb_categoriaimmobile.getID().intValue(), fk_tributo.intValue());
							if (select_with_key_tb_categoriaimmobile_detrazione_operation != null && select_with_key_tb_categoriaimmobile_detrazione_operation.length > 0) {
								List<Detrazione> detrazioni = tributo.getDetrazioni();
								for (Tb_categoriaimmobile_detrazione tb_categoriaimmobile_detrazione : select_with_key_tb_categoriaimmobile_detrazione_operation) {
									Detrazione detrazione = new Detrazione();
									BigInteger fk_detrazione = tb_categoriaimmobile_detrazione.getFk_detrazione();
									Tb_detrazione[] select_with_key_tb_detrazione_operation = secureSOAP11Endpoint.select_with_key_tb_detrazione_operation(fk_detrazione.intValue());
									if (select_with_key_tb_detrazione_operation != null && select_with_key_tb_detrazione_operation.length > 0) {
										Tb_detrazione tb_detrazione = select_with_key_tb_detrazione_operation[0];
										detrazione.setDescrizione(tb_detrazione.getDescrizione());
										detrazione.setImporto(tb_detrazione.getImporto());
										detrazione.setInfo(tb_detrazione.getInfo());
										BigInteger fk_tipologia_detrazione = tb_detrazione.getFk_tipologia_detrazione();
										Tb_tipologia_detrazione[] select_with_key_tb_tipologia_detrazione_operation = secureSOAP11Endpoint
												.select_with_key_tb_tipologia_detrazione_operation(fk_tipologia_detrazione.intValue());
										if (select_with_key_tb_tipologia_detrazione_operation != null && select_with_key_tb_tipologia_detrazione_operation.length > 0) {
											Tb_tipologia_detrazione tb_tipologia_detrazione = select_with_key_tb_tipologia_detrazione_operation[0];
											TipoDetrazione tipologiaDetrazione = new TipoDetrazione();
											tipologiaDetrazione.setCodice(tb_tipologia_detrazione.getCodice());
											tipologiaDetrazione.setDescrizione(tb_tipologia_detrazione.getDescrizione());
											detrazione.setTipo(tipologiaDetrazione);
										}
									}
									detrazioni.add(detrazione);
								}
							}

							// Esenzioni
							Tb_esenzione[] select_with_key_tb_esenzione_operation = secureSOAP11Endpoint.select_with_key_tb_esenzione_operation(tb_categoriaimmobile.getID().intValue(),
									fk_tributo.intValue());
							if (select_with_key_tb_esenzione_operation != null && select_with_key_tb_esenzione_operation.length > 0) {
								List<Esenzione> esenzioni = tributo.getEsenzioni();
								for (Tb_esenzione tb_esenzione : select_with_key_tb_esenzione_operation) {
									Esenzione esenzione = new Esenzione();
									esenzione.setDescrizione(tb_esenzione.getDescrizione());
									esenzioni.add(esenzione);
								}
							}

							tributi.add(tributo);
						}

						categorieImmobili.add(categoriaImmobile);
					}
				}
			}
			result = xmlHelper.marshal(risposta);

		}
		catch (Exception e) {
			log.error("getResponse :: " + e.getMessage(), e);
			Errore errore = new Errore();
			errore.setCodice(4);
			errore.setDescrizione(e.getMessage());
			risposta.setErrore(errore);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.pdds.service.AbstractResponse#getName()
	 */
	@Override
	public String getName() {
		return "categorieImmobiliService";
	}

	/**
	 * @return the xmlHelper
	 */
	public XMLHelper getXmlHelper() {
		return xmlHelper;
	}

	/**
	 * @param xmlHelper the xmlHelper to set
	 */
	public void setXmlHelper(XMLHelper xmlHelper) {
		this.xmlHelper = xmlHelper;
	}

}
