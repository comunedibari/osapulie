/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.domain.json.configurazione;

import java.util.List;

/**
 * @author Gianluca Pindinelli
 *
 */
public class Configurazione {

	private String nome;
	private List<Condizione> condizioni;

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the condizioni
	 */
	public List<Condizione> getCondizioni() {
		return condizioni;
	}

	/**
	 * @param condizioni the condizioni to set
	 */
	public void setCondizioni(List<Condizione> condizioni) {
		this.condizioni = condizioni;
	}
}
