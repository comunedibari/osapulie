package eng.tz.pa.api.osa.dao.models;

import java.io.Serializable;
//NumeroServiziRichiestiUtente
public class RichiesteServiziAzienda implements Serializable{

	private static final long serialVersionUID = 1L;
	//rs.nome_servizio, rs.data_richiesta, ta.ragione_sociale as utente,uc.ID as coduser 
	private String data_richiesta;
	private String coduser;
	private String servizio;
	private String ragione_sociale;
	
	public RichiesteServiziAzienda() {
		 
	}

	public String getData_richiesta() {
		return data_richiesta;
	}

	public void setData_richiesta(String data_richiesta) {
		this.data_richiesta = data_richiesta;
	}

	public String getCoduser() {
		return coduser;
	}

	public void setCoduser(String coduser) {
		this.coduser = coduser;
	}

	public String getServizio() {
		return servizio;
	}

	public void setServizio(String servizio) {
		this.servizio = servizio;
	}

	public String getRagione_sociale() {
		return ragione_sociale;
	}

	public void setRagione_sociale(String ragione_sociale) {
		this.ragione_sociale = ragione_sociale;
	}
	
 

 
	
	 
	
	
}
