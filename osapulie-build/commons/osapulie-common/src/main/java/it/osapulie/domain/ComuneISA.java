/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Email;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import it.osapulie.domain.categoriaimmobile.CategoriaImmobileTributo;
import it.osapulie.domain.servizi.Servizio;

/**
 * Anagrafica di un comune.
 *
 * @author Mario Scalas
 * @author Gianluca Pindinelli
 * @author Birtolo Maria Michela
 */
@Entity
@Table(name = "tb_comune_isa")
public class ComuneISA extends AbstractPersistable<Long> {

	private static final long serialVersionUID = -753550391098033459L;

	/**
	 * Codice ISTAT del comune. Vedere http://www.istat.it/it/archivio/6789 .
	 */
	@Column(name = "codice_istat", updatable = true, unique = true, nullable = false, length = 8)
	private String codiceIstat;

	@Column(name = "nome", unique = true, updatable = true, nullable = false, length = 64)
	private String nome;

	@Column(name = "descrizione", updatable = true, nullable = true, length = 512)
	private String descrizione;

	@Column(name = "cap", unique = true, updatable = true, nullable = false, length = 6)
	private String cap;

	@Email
	@Column(name = "pec", updatable = true, nullable = false, length = 128)
	private String pec;
	@JsonIgnore
	@JoinColumn(name = "fk_gestore_comune_isa")
	private ProfiloUtenteCittadino gestoreComune;

	@JoinColumn(name = "fk_timbro_config")
	private TimbroConfig timbroConfig;

	/**
	 * URI del servizio di gateway del comune.
	 */
	@Lob
	@Column(name = "uri_servizio_gw", nullable = false, updatable = true)
	private String uriServizioGateway;

	@Lob
	@Column(name = "uri_protocollo", nullable = true)
	private String uriProtocollo;

	/**
	 * Codice Area Organizzativa Omogenea
	 */
	@Column(name = "codice_area_organizzativa_omogenea", nullable = true, updatable = true, length = 8)
	private String codiceAOO;

	@Column(name = "amministrazione", nullable = true, updatable = true, length = 128)
	private String amministrazione;
	
	@JsonIgnore
	@Lob
	@Column(name = "logo")
	private byte[] logo;

	@Transient
	private CommonsMultipartFile logoFile;

	@Column(name = "download_allegati", nullable = true)
	private Boolean downloadAllegati;

	/**
	 * Stringa che identifica il canale di comunicazione preferito dal comune. Per la codifica
	 * vedere la classe <code>PorteltConstants</code>.
	 */
	@Column(name = "canale_comunicazione", nullable = true)
	private String canaleComunicazione;
	@JsonIgnore
	@OneToMany(mappedBy = "comuneISA", cascade = { CascadeType.MERGE, CascadeType.REMOVE })
	private List<ComuneISAServizio> servizi;
	@JsonIgnore
	@OneToMany(mappedBy = "comuneISA")
	private List<CategoriaImmobileTributo> categorieImmobiliTributi;

	@OneToOne
	@JoinColumn(name = "codice_istat", referencedColumnName = "codiceIstatAN", insertable = false, updatable = false)
	private Comune comune;

	@Column(name = "visura_posizioni_tributarie", nullable = false)
	private Boolean visuraPosizioniTributarieActive;

	@Column(name = "attivo", nullable = false)
	private Boolean attivo;

	@Column(name = "ordine")
	private Integer ordine;

	/**
	 * Campo JSON per la definizione delle informazioni aggiuntive correlate ad un Comune.
	 */
	@Column(name = "info_aggiuntive", nullable = true)
	private String infoAggiuntive;

	@OneToMany(mappedBy = "comuneISA")
	private List<Template> templates;

	/**
	 * Aggiunge un servizio al Comune.
	 *
	 * @param servizio
	 * @param autenticazioneForte
	 * @param attivo
	 */
	public void addServizio(Servizio servizio, boolean autenticazioneForte, Integer livelloAutenticazione, boolean attivo) {

		ComuneISAServizio comuneISAServizio = new ComuneISAServizio();
		comuneISAServizio.setServizio(servizio);
		comuneISAServizio.setIdServizio(servizio.getId());
		comuneISAServizio.setComuneISA(this);
		comuneISAServizio.setIdComuneISA(this.getId());
		comuneISAServizio.setAutenticazioneForte(autenticazioneForte);
		comuneISAServizio.setLivelloAutenticazione(livelloAutenticazione);
		comuneISAServizio.setAttivo(attivo);

		servizi.add(comuneISAServizio);
		// Also add the association object.
		servizio.getComuni().add(comuneISAServizio);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.jpa.domain.AbstractPersistable#setId(java.io.Serializable)
	 */
	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getCodiceIstat() {
		return codiceIstat;
	}

	public void setCodiceIstat(String codiceIstat) {
		this.codiceIstat = codiceIstat;
	}

	public String getUriServizioGateway() {
		return uriServizioGateway;
	}

	public void setUriServizioGateway(String uriServizioGateway) {
		this.uriServizioGateway = uriServizioGateway;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ComuneISA [id=" + getId() + ", codiceIstat=" + codiceIstat + ", nome=" + nome + ", descrizione=" + descrizione + ", cap=" + cap + "]";
	}

	public String getPec() {
		return pec;
	}

	public void setPec(String pec) {
		this.pec = pec;
	}

	public ProfiloUtenteCittadino getGestoreComune() {
		return gestoreComune;
	}

	public void setGestoreComune(ProfiloUtenteCittadino gestoreComune) {
		this.gestoreComune = gestoreComune;
	}

	public TimbroConfig getTimbroConfig() {
		return timbroConfig;
	}

	public void setTimbroConfig(TimbroConfig timbroConfig) {
		this.timbroConfig = timbroConfig;
	}

	public String getCanaleComunicazione() {
		return canaleComunicazione;
	}

	public void setCanaleComunicazione(String canaleComunicazione) {
		this.canaleComunicazione = canaleComunicazione;
	}

	public String getAmministrazione() {
		return amministrazione;
	}

	public void setAmministrazione(String amministrazione) {
		this.amministrazione = amministrazione;
	}

	public String getCodiceAOO() {
		return codiceAOO;
	}

	public void setCodiceAOO(String codiceAOO) {
		this.codiceAOO = codiceAOO;
	}

	/**
	 * @return the downloadAllegati
	 */
	public Boolean getDownloadAllegati() {
		return downloadAllegati;
	}

	/**
	 * @param downloadAllegati the downloadAllegati to set
	 */
	public void setDownloadAllegati(Boolean downloadAllegati) {
		this.downloadAllegati = downloadAllegati;
	}

	/**
	 * @return the uriProtocollo
	 */
	public String getUriProtocollo() {
		return uriProtocollo;
	}

	/**
	 * @param uriProtocollo the uriProtocollo to set
	 */
	public void setUriProtocollo(String uriProtocollo) {
		this.uriProtocollo = uriProtocollo;
	}

	/**
	 * @return the servizi
	 */
	public List<ComuneISAServizio> getServizi() {
		return servizi;
	}

	/**
	 * @param servizi the servizi to set
	 */
	public void setServizi(List<ComuneISAServizio> servizi) {
		this.servizi = servizi;
	}

	/**
	 * @return the logo
	 */
	public byte[] getLogo() {
		return logo;
	}

	/**
	 * @param logo the logo to set
	 */
	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	/**
	 * @return the logoFile
	 */
	public CommonsMultipartFile getLogoFile() {
		return logoFile;
	}

	/**
	 * @param logoFile the logoFile to set
	 */
	public void setLogoFile(CommonsMultipartFile logoFile) {
		this.logoFile = logoFile;
	}

	/**
	 * @return the categorieImmobiliTributi
	 */
	public List<CategoriaImmobileTributo> getCategorieImmobiliTributi() {
		return categorieImmobiliTributi;
	}

	/**
	 * @param categorieImmobiliTributi the categorieImmobiliTributi to set
	 */
	public void setCategorieImmobiliTributi(List<CategoriaImmobileTributo> categorieImmobiliTributi) {
		this.categorieImmobiliTributi = categorieImmobiliTributi;
	}

	/**
	 * @return the comune
	 */
	public Comune getComune() {
		return comune;
	}

	/**
	 * @param comune the comune to set
	 */
	public void setComune(Comune comune) {
		this.comune = comune;
	}

	/**
	 * @return the attivo
	 */
	public Boolean getAttivo() {
		return attivo;
	}

	/**
	 * @param attivo the attivo to set
	 */
	public void setAttivo(Boolean attivo) {
		this.attivo = attivo;
	}

	/**
	 * @return the visuraPosizioniTributarieActive
	 */
	public Boolean getVisuraPosizioniTributarieActive() {
		return visuraPosizioniTributarieActive;
	}

	/**
	 * @param visuraPosizioniTributarieActive the visuraPosizioniTributarieActive to set
	 */
	public void setVisuraPosizioniTributarieActive(Boolean visuraPosizioniTributarieActive) {
		this.visuraPosizioniTributarieActive = visuraPosizioniTributarieActive;
	}

	/**
	 * @return the infoAggiuntive
	 */
	public String getInfoAggiuntive() {
		return infoAggiuntive;
	}

	/**
	 * @param infoAggiuntive the infoAggiuntive to set
	 */
	public void setInfoAggiuntive(String infoAggiuntive) {
		this.infoAggiuntive = infoAggiuntive;
	}

	/**
	 * @return the templates
	 */
	public List<Template> getTemplates() {
		return templates;
	}

	/**
	 * @param templates the templates to set
	 */
	public void setTemplates(List<Template> templates) {
		this.templates = templates;
	}

	/**
	 * @return the ordine
	 */
	public Integer getOrdine() {
		return ordine;
	}

	/**
	 * @param ordine the ordine to set
	 */
	public void setOrdine(Integer ordine) {
		this.ordine = ordine;
	}
}
