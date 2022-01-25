/**
 * Copyright (c) 2011 Links Management & Technology S.p.A.
 */

package it.osapulie.domain.fascicoloutente;

import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.data.jpa.domain.AbstractPersistable;

import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.ProfiloUtenteCittadinoAzienda;
import it.osapulie.domain.servizi.Servizio;

/**
 * @author Mario Scalas
 * @author Maria Michela Birtolo
 * @author Gianluca Pindinelli
 */
@Entity
@Table(name = "tb_richiesta_servizio")
public class RichiestaServizio extends AbstractPersistable<Long> {

	private static final long serialVersionUID = -5303720382892396010L;

	@JoinColumn(name = "fk_fascicolo", nullable = false)
	private FascicoloUtente fascicolo;

	@JoinColumn(name = "fk_comune")
	private ComuneISA comuneErogatore;

	@Column(name = "nome_servizio", nullable = false, updatable = false, length = 128)
	private String nomeServizio;

	@Column(name = "numero_protocollo", nullable = true, updatable = false, length = 64)
	private String numeroProtocollo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_richiesta")
	private Date dataRichiesta;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_risposta")
	private Date dataRisposta;

	@JoinColumns({ @JoinColumn(name = "fk_profiloutentecittadino", referencedColumnName = "fk_profiloutentecittadino", nullable = false),
			@JoinColumn(name = "fk_azienda", referencedColumnName = "fk_azienda", nullable = false) })
	private ProfiloUtenteCittadinoAzienda delegato;

	@JoinColumn(name = "fk_servizio", nullable = false)
	private Servizio servizio;

	@Column(name = "ricercabile")
	private boolean ricercabileDaComune;

	/**
	 * Checksum relativa al file generato dal sistema (quando previsto)
	 */
	@Column(name = "checksum", nullable = true, length = 256)
	private String checksum;

	/**
	 * Info aggiiuntive (campo JSON).
	 */
	@Column(name = "info_aggiuntive", nullable = true)
	private String infoAggiuntive;

	@Transient
	private Map<String, String> infoAggiuntiveMap;

	/**
	 * @return the fascicolo
	 */
	public FascicoloUtente getFascicolo() {
		return fascicolo;
	}

	/**
	 * @param fascicolo the fascicolo to set
	 */
	public void setFascicolo(FascicoloUtente fascicolo) {
		this.fascicolo = fascicolo;
	}

	/**
	 * @return the comuneErogatore
	 */
	public ComuneISA getComuneErogatore() {
		return comuneErogatore;
	}

	/**
	 * @param comuneErogatore the comuneErogatore to set
	 */
	public void setComuneErogatore(ComuneISA comuneErogatore) {
		this.comuneErogatore = comuneErogatore;
	}

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
	 * @return the numeroProtocollo
	 */
	public String getNumeroProtocollo() {
		return numeroProtocollo;
	}

	/**
	 * @param numeroProtocollo the numeroProtocollo to set
	 */
	public void setNumeroProtocollo(String numeroProtocollo) {
		this.numeroProtocollo = numeroProtocollo;
	}

	/**
	 * @return the dataRichiesta
	 */
	public Date getDataRichiesta() {
		return dataRichiesta;
	}

	/**
	 * @param dataRichiesta the dataRichiesta to set
	 */
	public void setDataRichiesta(Date dataRichiesta) {
		this.dataRichiesta = dataRichiesta;
	}

	/**
	 * @return the dataRisposta
	 */
	public Date getDataRisposta() {
		return dataRisposta;
	}

	/**
	 * @param dataRisposta the dataRisposta to set
	 */
	public void setDataRisposta(Date dataRisposta) {
		this.dataRisposta = dataRisposta;
	}

	/**
	 * @return the servizio
	 */
	public Servizio getServizio() {
		return servizio;
	}

	/**
	 * @param servizio the servizio to set
	 */
	public void setServizio(Servizio servizio) {
		this.servizio = servizio;
	}

	/**
	 * @return the ricercabileDaComune
	 */
	public boolean isRicercabileDaComune() {
		return ricercabileDaComune;
	}

	/**
	 * @param ricercabileDaComune the ricercabileDaComune to set
	 */
	public void setRicercabileDaComune(boolean ricercabileDaComune) {
		this.ricercabileDaComune = ricercabileDaComune;
	}

	/**
	 * @return the checksum
	 */
	public String getChecksum() {
		return checksum;
	}

	/**
	 * @param checksum the checksum to set
	 */
	public void setChecksum(String checksum) {
		this.checksum = checksum;
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
	 * @return the delegato
	 */
	public ProfiloUtenteCittadinoAzienda getDelegato() {
		return delegato;
	}

	/**
	 * @param delegato the delegato to set
	 */
	public void setDelegato(ProfiloUtenteCittadinoAzienda delegato) {
		this.delegato = delegato;
	}

	/**
	 * @return the infoAggiuntiveMap
	 */
	public Map<String, String> getInfoAggiuntiveMap() {
		return infoAggiuntiveMap;
	}

	/**
	 * @param infoAggiuntiveMap the infoAggiuntiveMap to set
	 */
	public void setInfoAggiuntiveMap(Map<String, String> infoAggiuntiveMap) {
		this.infoAggiuntiveMap = infoAggiuntiveMap;
	}

}
