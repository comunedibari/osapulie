package it.osapulie.tributi.web.portlet.rimborsocosaptosap.form;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import it.osapulie.tributi.web.portlet.varie.DatiRimborsoGenerali;

/**
 * Classe contenente i dati per la generazione della richiesta di rimborso Cosap/Tosap.
 * 
 * @author Gianluca Pindinelli
 * 
 */
public class DatiRimborsoCosapTosap extends DatiRimborsoGenerali implements Serializable {

	private static final long serialVersionUID = 1L;

	private String numeroDocumento;
	private String contoCorrente;
	private Double importoDocumento;
	private String importoDovuto;

	private String descrizione;

	private String tipoOsap;
	private String tipoOsapString;
	private String anno;

	private List<PosizioneOsap> posizioniOsapList;
	private Map<Integer, PosizioneOsap> posizioniOsapMap;

	public class PosizioneOsap {

		private String indirizzoUtenza;
		private Double superficie;
		private String zonaUtenza;
		private String descrizioneTariffa;
		private Double importoDaPagare;
		private Double importoPagato;
		private Double importoDovuto;
		private Integer mesi;
		private Date dataPagamento;

		public String getIndirizzoUtenza() {
			return indirizzoUtenza;
		}

		public void setIndirizzoUtenza(String indirizzoUtenza) {
			this.indirizzoUtenza = indirizzoUtenza;
		}

		public Double getSuperficie() {
			return superficie;
		}

		public void setSuperficie(Double superficie) {
			this.superficie = superficie;
		}

		public String getZonaUtenza() {
			return zonaUtenza;
		}

		public void setZonaUtenza(String zonaUtenza) {
			this.zonaUtenza = zonaUtenza;
		}

		public String getDescrizioneTariffa() {
			return descrizioneTariffa;
		}

		public void setDescrizioneTariffa(String descrizioneTariffa) {
			this.descrizioneTariffa = descrizioneTariffa;
		}

		public Double getImportoDaPagare() {
			return importoDaPagare;
		}

		public void setImportoDaPagare(Double importoDaPagare) {
			this.importoDaPagare = importoDaPagare;
		}

		public Integer getMesi() {
			return mesi;
		}

		public void setMesi(Integer mesi) {
			this.mesi = mesi;
		}

		public Double getImportoPagato() {
			return importoPagato;
		}

		public void setImportoPagato(Double importoPagato) {
			this.importoPagato = importoPagato;
		}

		public Date getDataPagamento() {
			return dataPagamento;
		}

		public void setDataPagamento(Date dataPagamento) {
			this.dataPagamento = dataPagamento;
		}

		public Double getImportoDovuto() {
			return importoDovuto;
		}

		public void setImportoDovuto(Double importoDovuto) {
			this.importoDovuto = importoDovuto;
		}
	}

	public String getTipoOsap() {
		return tipoOsap;
	}

	public void setTipoOsap(String tipoOsap) {
		this.tipoOsap = tipoOsap;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getContoCorrente() {
		return contoCorrente;
	}

	public void setContoCorrente(String contoCorrente) {
		this.contoCorrente = contoCorrente;
	}

	public Double getImportoDocumento() {
		return importoDocumento;
	}

	public void setImportoDocumento(Double importoDocumento) {
		this.importoDocumento = importoDocumento;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<PosizioneOsap> getPosizioniOsapList() {
		return posizioniOsapList;
	}

	public void setPosizioniOsapList(List<PosizioneOsap> posizioniOsapList) {
		this.posizioniOsapList = posizioniOsapList;
	}

	public Map<Integer, PosizioneOsap> getPosizioniOsapMap() {
		return posizioniOsapMap;
	}

	public void setPosizioniOsapMap(Map<Integer, PosizioneOsap> posizioniOsapMap) {
		this.posizioniOsapMap = posizioniOsapMap;
	}

	public String getImportoDovuto() {
		return importoDovuto;
	}

	public void setImportoDovuto(String importoDovuto) {
		this.importoDovuto = importoDovuto;
	}

	@Override
	public String getAnno() {
		return anno;
	}

	@Override
	public void setAnno(String anno) {
		this.anno = anno;
	}

	public String getTipoOsapString() {
		return tipoOsapString;
	}

	public void setTipoOsapString(String tipoOsapString) {
		this.tipoOsapString = tipoOsapString;
	}

}
