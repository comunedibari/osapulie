package it.osapulie.domain.servizi;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author Gianluca Pindinelli
 *
 */
@Entity
@Table(name = "tb_area_tematica")
public class AreaTematica extends AbstractPersistable<Long> {

	/**
	 *
	 */
	private static final long serialVersionUID = -8258361451934874909L;

	@Override
	protected void setId(Long id) {
		super.setId(id);
	}

	@Column(name = "nome", length = 255, nullable = false)
	private String nome;

	@Column(name = "descrizione", length = 255, nullable = false)
	private String descrizione;
	@JsonIgnore
	@OneToMany(mappedBy = "areaTematica", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@OrderBy("nomeServizio ASC")
	private List<Servizio> servizi;

	@Column(name = "ordine", nullable = false)
	private Integer ordine;

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
