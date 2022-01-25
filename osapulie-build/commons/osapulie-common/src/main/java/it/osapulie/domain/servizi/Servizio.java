package it.osapulie.domain.servizi;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import it.osapulie.domain.ComuneISAServizio;
import it.osapulie.domain.Delega;
import it.osapulie.domain.Ente;
import it.osapulie.domain.Template;
import it.osapulie.domain.categoriaimmobile.Tributo;

/**
 * Ogni servizio erogabile (e quindi supportato da OS Apulie) da un {@link Ente} è identificato
 * all'interno del sistema: ogni Porta Di Dominio potrà supportare nessuno, tutti o parte di questi
 * servizi pubblicando un elenco di {@link ServizioErogato}.
 *
 * @author Mario Scalas
 * @author Maria Michela Birtolo
 */
@Entity
@Table(name = "tb_servizio")
public class Servizio extends AbstractPersistable<Long> {

	private static final long serialVersionUID = -4174142364198409072L;

	@Override
	protected void setId(Long id) {
		super.setId(id);
	}

	@Column(name = "nome_servizio", length = 255, unique = true)
	private String nomeServizio;

	@Column(name = "descrizione", length = 255, nullable = false)
	private String descrizione;

	@Column(name = "codice_servizio", length = 5, unique = true)
	private String codiceServizio;

	@Column(name = "uri", nullable = false, length = 256)
	private String uri;

	@Column(name = "uri_scheda", nullable = true, length = 256)
	private String uriScheda;
	@JsonIgnore
	@ManyToMany(mappedBy = "servizi")
	private List<Delega> deleghe;

	@JoinColumn(name = "fk_area_tematica", nullable = false)
	private AreaTematica areaTematica;
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_servizio_tipologia", joinColumns = { @JoinColumn(name = "fk_servizio", referencedColumnName = "ID") }, inverseJoinColumns = {
			@JoinColumn(name = "fk_tipologia", referencedColumnName = "ID") })
	private List<Tipologia> tipologie;
	@JsonIgnore
	@OneToMany(mappedBy = "servizio")
	private List<ComuneISAServizio> comuni;

	@Column(name = "attivo")
	private boolean attivo;

	@OneToOne(mappedBy = "servizio")
	private Tributo tributo;

	@Column(name = "cittadino")
	private boolean cittadino;

	@Column(name = "azienda")
	private boolean azienda;

	@Column(name = "delega")
	private boolean delega;

	@Transient
	private boolean autenticazioneForte;

	@Transient
	private Integer livelloAutenticazione;
	@JsonIgnore
	@OneToMany(mappedBy = "servizio")
	private List<Template> templates;

	/**
	 * @return the nomeServizio
	 */
	public String getNomeServizio() {
		return nomeServizio;
	}

	/**
	 * @param nomeServizio the nomeServizio to set
	 */
	public void setNomeServizio(String nomeServizio) {
		this.nomeServizio = nomeServizio;
	}

	/**
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * @param descrizione the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * @return the codiceServizio
	 */
	public String getCodiceServizio() {
		return codiceServizio;
	}

	/**
	 * @param codiceServizio the codiceServizio to set
	 */
	public void setCodiceServizio(String codiceServizio) {
		this.codiceServizio = codiceServizio;
	}

	/**
	 * @return the uri
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * @param uri the uri to set
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

	/**
	 * @return the uriScheda
	 */
	public String getUriScheda() {
		return uriScheda;
	}

	/**
	 * @param uriScheda the uriScheda to set
	 */
	public void setUriScheda(String uriScheda) {
		this.uriScheda = uriScheda;
	}

	/**
	 * @return the deleghe
	 */
	public List<Delega> getDeleghe() {
		return deleghe;
	}

	/**
	 * @param deleghe the deleghe to set
	 */
	public void setDeleghe(List<Delega> deleghe) {
		this.deleghe = deleghe;
	}

	/**
	 * @return the areaTematica
	 */
	public AreaTematica getAreaTematica() {
		return areaTematica;
	}

	/**
	 * @param areaTematica the areaTematica to set
	 */
	public void setAreaTematica(AreaTematica areaTematica) {
		this.areaTematica = areaTematica;
	}

	/**
	 * @return the tipologie
	 */
	public List<Tipologia> getTipologie() {
		return tipologie;
	}

	/**
	 * @param tipologie the tipologie to set
	 */
	public void setTipologie(List<Tipologia> tipologie) {
		this.tipologie = tipologie;
	}

	/**
	 * @return the comuni
	 */
	public List<ComuneISAServizio> getComuni() {
		return comuni;
	}

	/**
	 * @param comuni the comuni to set
	 */
	public void setComuni(List<ComuneISAServizio> comuni) {
		this.comuni = comuni;
	}

	/**
	 * @return the autenticazioneForte
	 */
	public boolean isAutenticazioneForte() {
		return autenticazioneForte;
	}

	/**
	 * @param autenticazioneForte the autenticazioneForte to set
	 */
	public void setAutenticazioneForte(boolean autenticazioneForte) {
		this.autenticazioneForte = autenticazioneForte;
	}

	/**
	 * @return the attivo
	 */
	public boolean isAttivo() {
		return attivo;
	}

	/**
	 * @param attivo the attivo to set
	 */
	public void setAttivo(boolean attivo) {
		this.attivo = attivo;
	}

	/**
	 * @return the tributo
	 */
	public Tributo getTributo() {
		return tributo;
	}

	/**
	 * @param tributo the tributo to set
	 */
	public void setTributo(Tributo tributo) {
		this.tributo = tributo;
	}

	/**
	 * @return the cittadino
	 */
	public boolean isCittadino() {
		return cittadino;
	}

	/**
	 * @param cittadino the cittadino to set
	 */
	public void setCittadino(boolean cittadino) {
		this.cittadino = cittadino;
	}

	/**
	 * @return the azienda
	 */
	public boolean isAzienda() {
		return azienda;
	}

	/**
	 * @param azienda the azienda to set
	 */
	public void setAzienda(boolean azienda) {
		this.azienda = azienda;
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
	 * @return the livelloAutenticazione
	 */
	public Integer getLivelloAutenticazione() {
		return livelloAutenticazione;
	}

	/**
	 * @param livelloAutenticazione the livelloAutenticazione to set
	 */
	public void setLivelloAutenticazione(Integer livelloAutenticazione) {
		this.livelloAutenticazione = livelloAutenticazione;
	}

	/**
	 * @return the delega
	 */
	public boolean isDelega() {
		return delega;
	}

	/**
	 * @param delega the delega to set
	 */
	public void setDelega(boolean delega) {
		this.delega = delega;
	}

	@Override
	public String toString() {
		return "Servizio [nomeServizio=" + nomeServizio + ", descrizione=" + descrizione + ", codiceServizio="
				+ codiceServizio + ", uri=" + uri + ", uriScheda=" + uriScheda + ", deleghe=" + deleghe
				+ "attivo=" + attivo + ", cittadino=" + cittadino + ", azienda=" + azienda + ", delega="
				+ delega + ", autenticazioneForte=" + autenticazioneForte + ", livelloAutenticazione="
				+ livelloAutenticazione + "]";
	}

}
