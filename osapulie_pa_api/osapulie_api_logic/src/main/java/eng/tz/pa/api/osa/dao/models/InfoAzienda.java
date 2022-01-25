package eng.tz.pa.api.osa.dao.models;

import java.io.Serializable;

public class InfoAzienda implements Serializable{

	private static final long serialVersionUID = 1L;
//select ragione_sociale,partita_iva, tipo, fk_profiloutentecittadino as iduser FROM tb_azienda where attiva=1 order by ragione_sociale asc;
	private String ragione_sociale;
	private String partita_iva;
	private String tipo;
	private String iduser;
	
	public InfoAzienda() {
		 
	}

	public String getRagione_sociale() {
		return ragione_sociale;
	}

	public void setRagione_sociale(String ragione_sociale) {
		this.ragione_sociale = ragione_sociale;
	}

	public String getPartita_iva() {
		return partita_iva;
	}

	public void setPartita_iva(String partita_iva) {
		this.partita_iva = partita_iva;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getIduser() {
		return iduser;
	}

	public void setIduser(String iduser) {
		this.iduser = iduser;
	}

 
	
 

 
	
	 
	
	
}
