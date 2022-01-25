package it.osapulie.domain.servizi;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * Il {@link Servizio} erogato dal comune, con le informazioni necessarie ai client per usufruirne.
 *
 * @author Mario Scalas
 * @deprecated
 */
@Entity
@Table(name = "tb_servizio_erogato")
@Deprecated
public class ServizioErogato extends AbstractPersistable<Long> {

	private static final long serialVersionUID = 5593002594259301979L;

	@JoinColumn(name = "fk_servizio", nullable = false)
	private Servizio servizio;

	@Column(name = "uri", nullable = false, length = 256)
	private String uri;

	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	public Servizio getServizio() {
		return servizio;
	}

	public void setServizio(Servizio servizio) {
		this.servizio = servizio;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
}
