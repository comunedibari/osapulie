package eng.tz.la.model;

import java.io.Serializable;

public class AttributiServizio implements Serializable {

	private static final long serialVersionUID = 1L;
	private String timeString;
	private String idServizio; 
	private String uuidTransazione;
	private String origin;
	private String actor;
	private String codServizio;
	private String callGeo;
	private MetaInfo attribute;
	
	public AttributiServizio() {
 
	}
	
	public String getTimeString() {
		return timeString;
	}
	
	public void setTimeString(String timeString) {
		this.timeString = timeString;
	}
	
	public String getIdServizio() {
		return idServizio;
	}
	
	public void setIdServizio(String idServizio) {
		this.idServizio = idServizio;
	}
	
	public String getUuidTransazione() {
		return uuidTransazione;
	}
	
	public void setUuidTransazione(String uuidTransazione) {
		this.uuidTransazione = uuidTransazione;
	}
	
	public MetaInfo getAttribute() {
		return attribute;
	}
	
	public void setAttribute(MetaInfo attribute) {
		this.attribute = attribute;
	}
	
	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}
	public String getCodServizio() {
		return codServizio;
	}

	public void setCodServizio(String codServizio) {
		this.codServizio = codServizio;
	}
	
	public void setCallGeo(String callGeo) {
		this.callGeo = callGeo;
	}
	
	public String getCallGeo() {
		return callGeo;
	}

 

	@Override
	public String toString() {
		return "AttributiServizio [timeString=" + timeString + ", idServizio=" + idServizio +", actor=" + actor +", origin=" + origin + ", uuidTransazione="
				+ uuidTransazione + ", codServizio="
						+ codServizio + ", callGeo="
						+ callGeo + ", " + attribute + "]";
	}
 
	 
	
}
