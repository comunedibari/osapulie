/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.AbstractPersistable;

import it.osapulie.domain.servizi.Servizio;

/**
 * @author Gianluca Pindinelli
 *
 */
@Entity
@Table(name = "tb_delega")
public class Delega extends AbstractPersistable<Long> {

	/**
	 *
	 */
	private static final long serialVersionUID = -4234465119259411371L;

	@Column(name = "fk_delegato", nullable = false, length = 20)
	private long idDelegato;
	@Column(name = "fk_delegante", nullable = false, length = 20)
	private long idDelegante;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_richiesta", nullable = true, length = 128)
	private Date dataRichiesta;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_attivazione", nullable = true, length = 128)
	private Date dataAttivazione;

	@Column(name = "attiva")
	private boolean attiva;

	@Lob
	@Column(name = "allegato")
	private byte[] allegato;

	@Column(name = "nome_allegato", nullable = true, length = 128)
	private String nomeAllegato;

	@ManyToOne
	@JoinColumn(name = "fk_delegante", insertable = false, updatable = false, nullable = true)
	private ProfiloUtenteCittadino delegante;

	@ManyToOne
	@JoinColumn(name = "fk_delegato", insertable = false, updatable = false, nullable = true)
	private Azienda delegato;

	@ManyToMany
	@JoinTable(name = "tb_delega_servizio", joinColumns = {
			@JoinColumn(name = "fk_delega", referencedColumnName = "ID") }, inverseJoinColumns = @JoinColumn(name = "fk_servizio", referencedColumnName = "ID"))
	private List<Servizio> servizi;

	@JoinColumn(name = "fk_comune_isa", nullable = false)
	private ComuneISA comuneIsa;
	
	//Nuove colonne MEV firma grafometrica
	
	@Column(name = "n_documento", nullable = true, length = 25)
	private String nDocumento;
	
	@Column(name = "data_scadenza", nullable = true, length = 15)
	private String dataScadenza;
	
	@Column(name = "rilasciato_da", nullable = true, length = 128)
	private String rilasciatoDa;
	
	@Column(name = "cf_operatore", nullable = true, length = 16)
	private String cfOperatore;
	
	@Column(name = "checksum", nullable = true, length = 45)
	private String checksum;
	
	@Column(name = "firma_grafometrica")
	private boolean firmaGrafometrica;
	
	@Lob
	@Column(name = "allegato_documento")
	private byte[] allegato_documento;
	
	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	/**
	 * @return the idDelegato
	 */
	public long getIdDelegato() {
		return idDelegato;
	}

	/**
	 * @param idDelegato the idDelegato to set
	 */
	public void setIdDelegato(long idDelegato) {
		this.idDelegato = idDelegato;
	}

	/**
	 * @return the idDelegante
	 */
	public long getIdDelegante() {
		return idDelegante;
	}

	/**
	 * @param idDelegante the idDelegante to set
	 */
	public void setIdDelegante(long idDelegante) {
		this.idDelegante = idDelegante;
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
	 * @return the dataAttivazione
	 */
	public Date getDataAttivazione() {
		return dataAttivazione;
	}

	/**
	 * @param dataAttivazione the dataAttivazione to set
	 */
	public void setDataAttivazione(Date dataAttivazione) {
		this.dataAttivazione = dataAttivazione;
	}

	/**
	 * @return the attiva
	 */
	public boolean isAttiva() {
		return attiva;
	}

	/**
	 * @param attiva the attiva to set
	 */
	public void setAttiva(boolean attiva) {
		this.attiva = attiva;
	}

	/**
	 * @return the delegante
	 */
	public ProfiloUtenteCittadino getDelegante() {
		return delegante;
	}

	/**
	 * @param delegante the delegante to set
	 */
	public void setDelegante(ProfiloUtenteCittadino delegante) {
		this.delegante = delegante;
	}

	/**
	 * @return the delegato
	 */
	public Azienda getDelegato() {
		return delegato;
	}

	/**
	 * @param delegato the delegato to set
	 */
	public void setDelegato(Azienda delegato) {
		this.delegato = delegato;
	}

	/**
	 * @return the servizi
	 */
	public List<Servizio> getServizi() {
		return servizi;
	}

	/**
	 * @param servizi the servizi to set
	 */
	public void setServizi(List<Servizio> servizi) {
		this.servizi = servizi;
	}

	/**
	 * @return the comuneIsa
	 */
	public ComuneISA getComuneIsa() {
		return comuneIsa;
	}

	/**
	 * @param comuneIsa the comuneIsa to set
	 */
	public void setComuneIsa(ComuneISA comuneIsa) {
		this.comuneIsa = comuneIsa;
	}

	/**
	 * @return the allegato
	 */
	public byte[] getAllegato() {
		return allegato;
	}

	/**
	 * @param allegato the allegato to set
	 */
	public void setAllegato(byte[] allegato) {
		this.allegato = allegato;
	}

	/**
	 * @return the nomeAllegato
	 */
	public String getNomeAllegato() {
		return nomeAllegato;
	}

	/**
	 * @param nomeAllegato the nomeAllegato to set
	 */
	public void setNomeAllegato(String nomeAllegato) {
		this.nomeAllegato = nomeAllegato;
	}

	/**
	 * @return the nDocumento
	 */
	public String getnDocumento() {
		return nDocumento;
	}

	/**
	 * @param nDocumento the nDocumento to set
	 */
	public void setnDocumento(String nDocumento) {
		this.nDocumento = nDocumento;
	}

	/**
	 * @return the dataScadenza
	 */
	public String getDataScadenza() {
		return dataScadenza;
	}

	/**
	 * @param dataScadenza the dataScadenza to set
	 */
	public void setDataScadenza(String dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	/**
	 * @return the rilasciatoDa
	 */
	public String getRilasciatoDa() {
		return rilasciatoDa;
	}

	/**
	 * @param rilasciatoDa the rilasciatoDa to set
	 */
	public void setRilasciatoDa(String rilasciatoDa) {
		this.rilasciatoDa = rilasciatoDa;
	}

	/**
	 * @return the cfOperatore
	 */
	public String getCfOperatore() {
		return cfOperatore;
	}

	/**
	 * @param cfOperatore the cfOperatore to set
	 */
	public void setCfOperatore(String cfOperatore) {
		this.cfOperatore = cfOperatore;
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
	
	public void setFirmaGrafometrica(boolean firmaGrafometrica) {
		this.firmaGrafometrica = firmaGrafometrica;
	}
	
	public boolean getFirmaGrafometrica() {
		 return this.firmaGrafometrica;
	 }

	public byte[] getAllegato_documento() {
		return allegato_documento;
	}

	public void setAllegato_documento(byte[] allegato_documento) {
		this.allegato_documento = allegato_documento;
	}

}
