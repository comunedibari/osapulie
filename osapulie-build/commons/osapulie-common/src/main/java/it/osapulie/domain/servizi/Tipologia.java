package it.osapulie.domain.servizi;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
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
@Table(name = "tb_tipologia")
public class Tipologia extends AbstractPersistable<Long> {

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
	@ManyToMany(mappedBy = "tipologie", fetch = FetchType.EAGER)
	@OrderBy("nomeServizio ASC")
	private List<Servizio> servizi;

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
}
