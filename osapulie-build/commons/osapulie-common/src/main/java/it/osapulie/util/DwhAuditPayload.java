package it.osapulie.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.osapulie.util.dto.DwhDataminingDTO;
import it.osapulie.util.dto.DwhServizioAttributeDTO;
import it.osapulie.util.dto.DwhTempiMediDTO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DwhAuditPayload {
	
	private DwhDataminingDTO datamining;
	private DwhServizioAttributeDTO servizioAttribute;
	private DwhTempiMediDTO tempiMedi;
	
	public DwhDataminingDTO getDatamining() {
		return datamining;
	}
	public void setDatamining(DwhDataminingDTO datamining) {
		this.datamining = datamining;
	}
	public DwhServizioAttributeDTO getServizioAttribute() {
		return servizioAttribute;
	}
	public void setServizioAttribute(DwhServizioAttributeDTO servizioAttribute) {
		this.servizioAttribute = servizioAttribute;
	}
	public DwhTempiMediDTO getTempiMedi() {
		return tempiMedi;
	}
	public void setTempiMedi(DwhTempiMediDTO tempiMedi) {
		this.tempiMedi = tempiMedi;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DwhAuditPayload [datamining=");
		builder.append(datamining);
		builder.append(", servizioAttribute=");
		builder.append(servizioAttribute);
		builder.append(", tempiMedi=");
		builder.append(tempiMedi);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
	
}

