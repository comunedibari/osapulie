package it.osapulie.domain;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * Un'area aggregata associa più comuni in un unico ente che condivide un'istanza del
 * Nodo di Gestione di OS Apulie.
 * 
 * @author Mario Scalas
 */
@Entity
@Table( name = "tb_area_aggregata" )
public class AreaAggregata extends AbstractPersistable<Long> {
	private static final long serialVersionUID = -2926083727470150459L;

	@Column( name = "nome", length = 64, nullable = false, unique = true )
	private String nome;
	
	@Column( name = "descrizione", length = 256, nullable = true ) 
	private String descrizione;
	
	@ManyToMany( fetch = FetchType.LAZY )
	@JoinTable( name = "tb_area_aggregata_comune",
		joinColumns = @JoinColumn( name = /* area aggregata*/ "area_id", referencedColumnName="ID" ),
		inverseJoinColumns = @JoinColumn( name = /* comune */ "comune_id", referencedColumnName="ID" ) )
	private List<ComuneISA> comuni;

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

	public List<ComuneISA> getComuni() {
		return comuni;
	}

	public void setComuni(List<ComuneISA> comuni) {
		this.comuni = comuni;
	}
}
