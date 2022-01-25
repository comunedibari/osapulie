/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.tributi.web.portlet.calcoloimu.form;

import java.util.Date;
import java.util.List;

import it.osapulie.tributi.web.portlet.varie.DatiRimborsoGenerali;

/**
 * Classe contenente i dati per il calcolo della IMU.
 *
 * @author Damiano Miri
 *
 */
public class DatiCalcoloImu extends DatiRimborsoGenerali {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String categoriaImmobileTributo;
	private String impostaTasi;
	private String esenzione;
	private String importoDetrazione;

	private String indirizzo;
	private String foglio;
	private String num;
	private String quota;
	private String quotaMesi;
	private String categoria;
	private String sezione;
	private String valoreim;
	private String aliquota;
	private String mesi;
	private String detcasa;
	private String dapagar = "";
	private String agevolazione;
	private String pertRenditaCatC2;
	private String pertPossessoPercC2;
	private String pertPossessoMesiC2;
	private String pertAgevolazioneC2;
	private String pertRenditaCatC6;
	private String pertPossessoPercC6;
	private String pertPossessoMesiC6;
	private String pertAgevolazioneC6;
	private String pertRenditaCatC7;
	private String pertPossessoPercC7;
	private String pertPossessoMesiC7;
	private String pertAgevolazioneC7;

	private String totale = "";
	private String totaleNonArrotondato;
	private String rata1 = "";
	private String rata2 = "";

	private String selectCambiata;
	private int numeroImmobiliDaVisura;
	private String numeroDivCalcoloParziale;

	// Serve per permettere il recupero della data corretta ad ogni refresh della pagina
	private Date dataNascitaForm;

	private List<DatiCalcoloImuImmobile> datiRiepilogo;

	// Serve per ricaricare il form di inserimento di un immobile dopo la validazione
	// Viene settato a true solo se
	boolean nuovoImmobile = false;

	private String partitaIva;

	public String getTotaleNonArrotondato() {
		return totaleNonArrotondato;
	}

	public void setTotaleNonArrotondato(String totaleNonArrotondato) {
		this.totaleNonArrotondato = totaleNonArrotondato;
	}

	public List<DatiCalcoloImuImmobile> getDatiRiepilogo() {
		return datiRiepilogo;
	}

	public void setDatiRiepilogo(List<DatiCalcoloImuImmobile> datiRiepilogo) {
		this.datiRiepilogo = datiRiepilogo;
	}

	public String getNumeroDivCalcoloParziale() {
		return numeroDivCalcoloParziale;
	}

	public void setNumeroDivCalcoloParziale(String numeroDivCalcoloParziale) {
		this.numeroDivCalcoloParziale = numeroDivCalcoloParziale;
	}

	public int getNumeroImmobiliDaVisura() {
		return numeroImmobiliDaVisura;
	}

	public void setNumeroImmobiliDaVisura(int numeroImmobiliDaVisura) {
		this.numeroImmobiliDaVisura = numeroImmobiliDaVisura;
	}

	public String getSelectCambiata() {
		return selectCambiata;
	}

	public void setSelectCambiata(String selectCambiata) {
		this.selectCambiata = selectCambiata;
	}

	public void setDaPagar() {
		if (this.getDatiRiepilogo() != null) {
			for (DatiCalcoloImuRiepilogo d : this.getDatiRiepilogo()) {
				if (d.valoreim.equals("")) {
					d.dapagar = String.valueOf(((Double.parseDouble(d.valoreim) * Double.parseDouble(d.aliquota)) / 1000 * Double.valueOf(d.quota) / 100 / 12) * Double.parseDouble(d.mesi));
					if (Double.parseDouble(d.detcasa) > 0) {
						d.dapagar = String.valueOf(Double.parseDouble(d.dapagar) - Double.parseDouble(d.detcasa));
					}
				}
			}
		}
	}

	public String getTotale() {
		return totale;
	}

	public void setTotale(String totale) {
		this.totale = totale;
	}

	public void setTotaleI(String tot, int i) {
		if (i == 1) {
			this.totale = tot;
		}
		else {
			this.totale = String.valueOf(Double.parseDouble(this.totale) + Double.parseDouble(tot));
		}

	}

	public String getRata1() {
		return rata1;
	}

	public void setRata1(String rata1) {
		this.rata1 = rata1;
	}

	public String getRata2() {
		return rata2;
	}

	public void setRate() {
		this.rata1 = String.valueOf(Double.parseDouble(this.totale) / 2);
		this.rata2 = String.valueOf(Double.parseDouble(this.totale) / 2);
	}

	public void setRata2(String rata2) {
		this.rata2 = rata2;
	}

	public void setTotale() {
		double tot = 0;
		if (this.getDatiRiepilogo() != null) {
			for (DatiCalcoloImuRiepilogo d : this.getDatiRiepilogo()) {
				if (d.valoreim.equals("")) {
					tot += Double.parseDouble(d.dapagar);
				}
			}
			this.totale = String.valueOf(tot);
		}
	}

	public String getCategoriaImmobileTributo() {
		return categoriaImmobileTributo;
	}

	public void setCategoriaImmobileTributo(String categoriaImmobileTributo) {
		this.categoriaImmobileTributo = categoriaImmobileTributo;
	}

	public String getImpostaTasi() {
		return impostaTasi;
	}

	public void setImpostaTasi(String impostaTasi) {
		this.impostaTasi = impostaTasi;
	}

	public String getEsenzione() {
		return esenzione;
	}

	public void setEsenzione(String esenzione) {
		this.esenzione = esenzione;
	}

	public String getImportoDetrazione() {
		return importoDetrazione;
	}

	public void setImportoDetrazione(String importoDetrazione) {
		this.importoDetrazione = importoDetrazione;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getFoglio() {
		return foglio;
	}

	public void setFoglio(String foglio) {
		this.foglio = foglio;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getQuota() {
		return quota;
	}

	public void setQuota(String quota) {
		this.quota = quota;
	}

	public String getQuotaMesi() {
		return quotaMesi;
	}

	public void setQuotaMesi(String quotaMesi) {
		this.quotaMesi = quotaMesi;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getSezione() {
		return sezione;
	}

	public void setSezione(String sezione) {
		this.sezione = sezione;
	}

	public String getValoreim() {
		return valoreim;
	}

	public void setValoreim(String valoreim) {
		this.valoreim = valoreim;
	}

	public String getAliquota() {
		return aliquota;
	}

	public void setAliquota(String aliquota) {
		this.aliquota = aliquota;
	}

	public String getMesi() {
		return mesi;
	}

	public void setMesi(String mesi) {
		this.mesi = mesi;
	}

	public String getDetcasa() {
		return detcasa;
	}

	public void setDetcasa(String detcasa) {
		this.detcasa = detcasa;
	}

	public String getDapagar() {
		return dapagar;
	}

	public void setDapagar(String dapagar) {
		this.dapagar = dapagar;
	}

	public String getAgevolazione() {
		return agevolazione;
	}

	public void setAgevolazione(String agevolazione) {
		this.agevolazione = agevolazione;
	}

	public String getPertRenditaCatC2() {
		return pertRenditaCatC2;
	}

	public void setPertRenditaCatC2(String pertRenditaCatC2) {
		this.pertRenditaCatC2 = pertRenditaCatC2;
	}

	public String getPertPossessoPercC2() {
		return pertPossessoPercC2;
	}

	public void setPertPossessoPercC2(String pertPossessoPercC2) {
		this.pertPossessoPercC2 = pertPossessoPercC2;
	}

	public String getPertPossessoMesiC2() {
		return pertPossessoMesiC2;
	}

	public void setPertPossessoMesiC2(String pertPossessoMesiC2) {
		this.pertPossessoMesiC2 = pertPossessoMesiC2;
	}

	public String getPertAgevolazioneC2() {
		return pertAgevolazioneC2;
	}

	public void setPertAgevolazioneC2(String pertAgevolazioneC2) {
		this.pertAgevolazioneC2 = pertAgevolazioneC2;
	}

	public String getPertRenditaCatC6() {
		return pertRenditaCatC6;
	}

	public void setPertRenditaCatC6(String pertRenditaCatC6) {
		this.pertRenditaCatC6 = pertRenditaCatC6;
	}

	public String getPertPossessoPercC6() {
		return pertPossessoPercC6;
	}

	public void setPertPossessoPercC6(String pertPossessoPercC6) {
		this.pertPossessoPercC6 = pertPossessoPercC6;
	}

	public String getPertPossessoMesiC6() {
		return pertPossessoMesiC6;
	}

	public void setPertPossessoMesiC6(String pertPossessoMesiC6) {
		this.pertPossessoMesiC6 = pertPossessoMesiC6;
	}

	public String getPertAgevolazioneC6() {
		return pertAgevolazioneC6;
	}

	public void setPertAgevolazioneC6(String pertAgevolazioneC6) {
		this.pertAgevolazioneC6 = pertAgevolazioneC6;
	}

	public String getPertRenditaCatC7() {
		return pertRenditaCatC7;
	}

	public void setPertRenditaCatC7(String pertRenditaCatC7) {
		this.pertRenditaCatC7 = pertRenditaCatC7;
	}

	public String getPertPossessoPercC7() {
		return pertPossessoPercC7;
	}

	public void setPertPossessoPercC7(String pertPossessoPercC7) {
		this.pertPossessoPercC7 = pertPossessoPercC7;
	}

	public String getPertPossessoMesiC7() {
		return pertPossessoMesiC7;
	}

	public void setPertPossessoMesiC7(String pertPossessoMesiC7) {
		this.pertPossessoMesiC7 = pertPossessoMesiC7;
	}

	public String getPertAgevolazioneC7() {
		return pertAgevolazioneC7;
	}

	public void setPertAgevolazioneC7(String pertAgevolazioneC7) {
		this.pertAgevolazioneC7 = pertAgevolazioneC7;
	}

	public boolean isNuovoImmobile() {
		return nuovoImmobile;
	}

	public void setNuovoImmobile(boolean nuovoImmobile) {
		this.nuovoImmobile = nuovoImmobile;
	}

	public Date getDataNascitaForm() {
		return dataNascitaForm;
	}

	public void setDataNascitaForm(Date dataNascitaForm) {
		this.dataNascitaForm = dataNascitaForm;
	}

	/**
	 * @return the partitaIva
	 */
	public String getPartitaIva() {
		return partitaIva;
	}

	/**
	 * @param partitaIva the partitaIva to set
	 */
	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}

}