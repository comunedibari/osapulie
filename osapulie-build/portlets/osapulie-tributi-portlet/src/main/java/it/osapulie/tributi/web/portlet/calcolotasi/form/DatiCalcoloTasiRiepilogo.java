package it.osapulie.tributi.web.portlet.calcolotasi.form;

import java.io.Serializable;

/**
 * Classe contenente i dati di riepilogo della visura e del pagamento tasi
 * 
 * @author Halley Sud-Est
 * 
 */
public class DatiCalcoloTasiRiepilogo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String categoriaImmobileTributo;
	String impostaTasi;
	String esenzione;
	String importoDetrazione;

	String indirizzo;
	String foglio;
	String num;
	String quota;
	String quotaMesi;
	String categoria;
	String sezione;
	String valoreim;
	String aliquota;
	String mesi;
	String detcasa;
	String dapagar = "";
	String agevolazione;

	String totaleImmobile = "0";
	String totaleImmobileNonArrotondato = "0";
	String detrazione;

	String codEnte;
	String codTributo;
	String annoDiRiferimento;

	boolean calcoloEffettuato;

	boolean saldo;
	String nImmobili;
	String rateazione;
	String importiACredito;
	String ravvedimento;
	String immobiliVariati;
	String acconto;

	// String rata1="";
	// String rata2="";

	public String getDetrazione() {
		return detrazione;
	}

	public String getCodEnte() {
		return codEnte;
	}

	public void setCodEnte(String codEnte) {
		this.codEnte = codEnte;
	}

	public String getCodTributo() {
		return codTributo;
	}

	public void setCodTributo(String codTributo) {
		this.codTributo = codTributo;
	}

	public String getAnnoDiRiferimento() {
		return annoDiRiferimento;
	}

	public void setAnnoDiRiferimento(String annoDiRiferimento) {
		this.annoDiRiferimento = annoDiRiferimento;
	}

	public void setDetrazione(String detrazione) {
		this.detrazione = detrazione;
	}

	public String getTotaleImmobileNonArrotondato() {
		return totaleImmobileNonArrotondato;
	}

	public void setTotaleImmobileNonArrotondato(String totaleImmobileNonArrotondato) {
		this.totaleImmobileNonArrotondato = totaleImmobileNonArrotondato;
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

	public String getTotaleImmobile() {
		return totaleImmobile;
	}

	public void setTotaleImmobile(String totaleImmobile) {
		this.totaleImmobile = totaleImmobile;
	}

	public boolean isCalcoloEffettuato() {
		return calcoloEffettuato;
	}

	public void setCalcoloEffettuato(boolean calcoloEffettuato) {
		this.calcoloEffettuato = calcoloEffettuato;
	}

	public boolean isSaldo() {
		return saldo;
	}

	public void setSaldo(boolean saldo) {
		this.saldo = saldo;
	}

	public String getnImmobili() {
		return nImmobili;
	}

	public void setnImmobili(String nImmobili) {
		this.nImmobili = nImmobili;
	}

	public String getRateazione() {
		return rateazione;
	}

	public void setRateazione(String rateazione) {
		this.rateazione = rateazione;
	}

	public String getImportiACredito() {
		return importiACredito;
	}

	public void setImportiACredito(String importiACredito) {
		this.importiACredito = importiACredito;
	}

	public String getRavvedimento() {
		return ravvedimento;
	}

	public void setRavvedimento(String ravvedimento) {
		this.ravvedimento = ravvedimento;
	}

	public String getImmobiliVariati() {
		return immobiliVariati;
	}

	public void setImmobiliVariati(String immobiliVariati) {
		this.immobiliVariati = immobiliVariati;
	}

	public String getAcconto() {
		return acconto;
	}

	public void setAcconto(String acconto) {
		this.acconto = acconto;
	}

	// public void setDapagar1() {
	// this.dapagar1 =
	// String.valueOf(((Double.parseDouble(this.valoreim1)*Double.parseDouble(this.aliquota1))/1000*Double.valueOf(this.quota1)/100/12)*Double.parseDouble(this.mesi1));
	// if (Double.parseDouble(this.detcasa1)>0)
	// this.dapagar1=String.valueOf(Double.parseDouble(this.dapagar1)-Double.parseDouble(this.detcasa1));
	// }
	//
	//
	//
	// public void setDapagar2() {
	// this.dapagar2 =
	// String.valueOf(((Double.parseDouble(this.valoreim2)*Double.parseDouble(this.aliquota2))/1000*Double.valueOf(this.quota2)/100/12)*Double.parseDouble(this.mesi2));
	// if (Double.parseDouble(this.detcasa2)>0)
	// this.dapagar2=String.valueOf(Double.parseDouble(this.dapagar2)-Double.parseDouble(this.detcasa2));
	// }
	//
	//
	// public void setDapagar3() {
	// this.dapagar3 =
	// String.valueOf(((Double.parseDouble(this.valoreim3)*Double.parseDouble(this.aliquota3))/1000*Double.valueOf(this.quota3)/100/12)*Double.parseDouble(this.mesi3));
	// if (Double.parseDouble(this.detcasa3)>0)
	// this.dapagar3=String.valueOf(Double.parseDouble(this.dapagar3)-Double.parseDouble(this.detcasa3));
	// }
	//
	//
	// public void setDapagar4() {
	// this.dapagar4 =
	// String.valueOf(((Double.parseDouble(this.valoreim4)*Double.parseDouble(this.aliquota4))/1000*Double.valueOf(this.quota4)/100/12)*Double.parseDouble(this.mesi4));
	// if (Double.parseDouble(this.detcasa4)>0)
	// this.dapagar4=String.valueOf(Double.parseDouble(this.dapagar4)-Double.parseDouble(this.detcasa4));
	// }
	//
	//
	// public void setDapagar5() {
	// this.dapagar5 =
	// String.valueOf(((Double.parseDouble(this.valoreim5)*Double.parseDouble(this.aliquota5))/1000*Double.valueOf(this.quota5)/100/12)*Double.parseDouble(this.mesi5));
	// if (Double.parseDouble(this.detcasa5)>0)
	// this.dapagar5=String.valueOf(Double.parseDouble(this.dapagar5)-Double.parseDouble(this.detcasa5));
	// }
	//
	//
	// public void setTotaleI(String tot, int i) {
	// if (i==1){
	// this.totale=tot;
	// }else {
	// this.totale = String.valueOf(Double.parseDouble(this.totale)+Double.parseDouble(tot));
	// }
	//
	//
	//
	// }

	// public void setRate(){
	// this.rata1 = String.valueOf(Double.parseDouble(this.totale)/2);
	// this.rata2 = String.valueOf(Double.parseDouble(this.totale)/2);
	// }
	// public void setRata2(String rata2) {
	// this.rata2 = rata2;
	// }

	// public void setDaPagar(){
	// if (!this.valoreim1.equals("")) this.setDapagar1();
	// if (!this.valoreim2.equals("")) this.setDapagar2();
	// if (!this.valoreim3.equals("")) this.setDapagar3();
	// if (!this.valoreim4.equals("")) this.setDapagar4();
	// if (!this.valoreim5.equals("")) this.setDapagar5();
	// }
	// public void setTotale() {
	// if (!this.valoreim1.equals("")) this.setTotaleI(dapagar1, 1);
	// if (!this.valoreim2.equals("")) this.setTotaleI(dapagar2, 2);
	// if (!this.valoreim3.equals("")) this.setTotaleI(dapagar3, 3);
	// if (!this.valoreim4.equals("")) this.setTotaleI(dapagar4, 4);
	// if (!this.valoreim5.equals("")) this.setTotaleI(dapagar5, 5);
	// }

}
