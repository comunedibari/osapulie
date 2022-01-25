package it.osapulie.tributi.web.portlet.rimborsotassaimmobili.form;

import java.io.Serializable;

import it.osapulie.tributi.web.portlet.varie.DatiRimborsoGenerali;

/**
 * Classe contenente i dati per la generazione della richiesta di rimborso ICI
 *
 * @author Maria Michela Birtolo
 *
 */
public class DatiRimborsoTassaImmobili extends DatiRimborsoGenerali implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	String indirizzo1;
	String foglio1;
	String num1;
	String quota1;
	String categoria1;
	String sezione1;
	String dovuto1;
	String versato1;

	String indirizzo2;
	String foglio2;
	String num2;
	String quota2;
	String categoria2;
	String sezione2;
	String dovuto2;
	String versato2;

	String indirizzo3;
	String foglio3;
	String num3;
	String quota3;
	String categoria3;
	String sezione3;
	String dovuto3;
	String versato3;

	String indirizzo4;
	String foglio4;
	String num4;
	String quota4;
	String categoria4;
	String sezione4;
	String dovuto4;
	String versato4;

	String indirizzo5;
	String foglio5;
	String num5;
	String quota5;
	String categoria5;
	String sezione5;
	String dovuto5;
	String versato5;

	String allegatoUno;
	String allegatoDue;
	String allegatoTre;
	String allegatoQuattro;

	private String descrizioneTassa;

	public String getDovuto1() {
		return dovuto1;
	}

	public void setDovuto1(String dovuto1) {
		this.dovuto1 = dovuto1;
	}

	public String getVersato1() {
		return versato1;
	}

	public void setVersato1(String versato1) {
		this.versato1 = versato1;
	}

	public String getDovuto2() {
		return dovuto2;
	}

	public void setDovuto2(String dovuto2) {
		this.dovuto2 = dovuto2;
	}

	public String getVersato2() {
		return versato2;
	}

	public void setVersato2(String versato2) {
		this.versato2 = versato2;
	}

	public String getDovuto3() {
		return dovuto3;
	}

	public void setDovuto3(String dovuto3) {
		this.dovuto3 = dovuto3;
	}

	public String getVersato3() {
		return versato3;
	}

	public void setVersato3(String versato3) {
		this.versato3 = versato3;
	}

	public String getDovuto4() {
		return dovuto4;
	}

	public void setDovuto4(String dovuto4) {
		this.dovuto4 = dovuto4;
	}

	public String getVersato4() {
		return versato4;
	}

	public void setVersato4(String versato4) {
		this.versato4 = versato4;
	}

	public String getDovuto5() {
		return dovuto5;
	}

	public void setDovuto5(String dovuto5) {
		this.dovuto5 = dovuto5;
	}

	public String getVersato5() {
		return versato5;
	}

	public void setVersato5(String versato5) {
		this.versato5 = versato5;
	}

	public String getIndirizzo1() {
		return indirizzo1;
	}

	public void setIndirizzo1(String indirizzo1) {
		this.indirizzo1 = indirizzo1;
	}

	public String getFoglio1() {
		return foglio1;
	}

	public void setFoglio1(String foglio1) {
		this.foglio1 = foglio1;
	}

	public String getNum1() {
		return num1;
	}

	public void setNum1(String num1) {
		this.num1 = num1;
	}

	public String getQuota1() {
		return quota1;
	}

	public void setQuota1(String quota1) {
		this.quota1 = quota1;
	}

	public String getCategoria1() {
		return categoria1;
	}

	public void setCategoria1(String categoria1) {
		this.categoria1 = categoria1;
	}

	public String getSezione1() {
		return sezione1;
	}

	public void setSezione1(String sezione1) {
		this.sezione1 = sezione1;
	}

	public String getIndirizzo2() {
		return indirizzo2;
	}

	public void setIndirizzo2(String indirizzo2) {
		this.indirizzo2 = indirizzo2;
	}

	public String getFoglio2() {
		return foglio2;
	}

	public void setFoglio2(String foglio2) {
		this.foglio2 = foglio2;
	}

	public String getNum2() {
		return num2;
	}

	public void setNum2(String num2) {
		this.num2 = num2;
	}

	public String getQuota2() {
		return quota2;
	}

	public void setQuota2(String quota2) {
		this.quota2 = quota2;
	}

	public String getCategoria2() {
		return categoria2;
	}

	public void setCategoria2(String categoria2) {
		this.categoria2 = categoria2;
	}

	public String getSezione2() {
		return sezione2;
	}

	public void setSezione2(String sezione2) {
		this.sezione2 = sezione2;
	}

	public String getIndirizzo3() {
		return indirizzo3;
	}

	public void setIndirizzo3(String indirizzo3) {
		this.indirizzo3 = indirizzo3;
	}

	public String getFoglio3() {
		return foglio3;
	}

	public void setFoglio3(String foglio3) {
		this.foglio3 = foglio3;
	}

	public String getNum3() {
		return num3;
	}

	public void setNum3(String num3) {
		this.num3 = num3;
	}

	public String getQuota3() {
		return quota3;
	}

	public void setQuota3(String quota3) {
		this.quota3 = quota3;
	}

	public String getCategoria3() {
		return categoria3;
	}

	public void setCategoria3(String categoria3) {
		this.categoria3 = categoria3;
	}

	public String getSezione3() {
		return sezione3;
	}

	public void setSezione3(String sezione3) {
		this.sezione3 = sezione3;
	}

	public String getIndirizzo4() {
		return indirizzo4;
	}

	public void setIndirizzo4(String indirizzo4) {
		this.indirizzo4 = indirizzo4;
	}

	public String getFoglio4() {
		return foglio4;
	}

	public void setFoglio4(String foglio4) {
		this.foglio4 = foglio4;
	}

	public String getNum4() {
		return num4;
	}

	public void setNum4(String num4) {
		this.num4 = num4;
	}

	public String getQuota4() {
		return quota4;
	}

	public void setQuota4(String quota4) {
		this.quota4 = quota4;
	}

	public String getCategoria4() {
		return categoria4;
	}

	public void setCategoria4(String categoria4) {
		this.categoria4 = categoria4;
	}

	public String getSezione4() {
		return sezione4;
	}

	public void setSezione4(String sezione4) {
		this.sezione4 = sezione4;
	}

	public String getIndirizzo5() {
		return indirizzo5;
	}

	public void setIndirizzo5(String indirizzo5) {
		this.indirizzo5 = indirizzo5;
	}

	public String getFoglio5() {
		return foglio5;
	}

	public void setFoglio5(String foglio5) {
		this.foglio5 = foglio5;
	}

	public String getNum5() {
		return num5;
	}

	public void setNum5(String num5) {
		this.num5 = num5;
	}

	public String getQuota5() {
		return quota5;
	}

	public void setQuota5(String quota5) {
		this.quota5 = quota5;
	}

	public String getCategoria5() {
		return categoria5;
	}

	public void setCategoria5(String categoria5) {
		this.categoria5 = categoria5;
	}

	public String getSezione5() {
		return sezione5;
	}

	public void setSezione5(String sezione5) {
		this.sezione5 = sezione5;
	}

	/**
	 * @return the descrizioneTassa
	 */
	public String getDescrizioneTassa() {
		return descrizioneTassa;
	}

	/**
	 * @param descrizioneTassa the descrizioneTassa to set
	 */
	public void setDescrizioneTassa(String descrizioneTassa) {
		this.descrizioneTassa = descrizioneTassa;
	}

	public String getAllegatoUno() {
		return allegatoUno;
	}

	public void setAllegatoUno(String allegatoUno) {
		this.allegatoUno = allegatoUno;
	}

	public String getAllegatoDue() {
		return allegatoDue;
	}

	public void setAllegatoDue(String allegatoDue) {
		this.allegatoDue = allegatoDue;
	}

	public String getAllegatoTre() {
		return allegatoTre;
	}

	public void setAllegatoTre(String allegatoTre) {
		this.allegatoTre = allegatoTre;
	}

	public String getAllegatoQuattro() {
		return allegatoQuattro;
	}

	public void setAllegatoQuattro(String allegatoQuattro) {
		this.allegatoQuattro = allegatoQuattro;
	}
}
