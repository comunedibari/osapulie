package eng.tz.la.model;

import java.io.Serializable;

public class MetaField implements Serializable{

	private static final long serialVersionUID = 2498534320245191755L;
	private String name;
	private Object value;
	private Class<?> type;
	
	public MetaField() {
		 
	}

	public MetaField(String name, Object value, Class<?> type) {
		super();
		this.name = name;
		this.value = value;
		this.type = type;
	}

	public MetaField(String name, Object value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
	 
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "" + (name != null ? "" + name + "==>" : "")
				+ (value != null ? "" + value + " " : "") + (type != null ? "Type:" + type.getName() : "") + "";
	}
	
 
	
}
