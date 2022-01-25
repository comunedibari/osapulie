package eng.tz.la.model;

public class Info {
private String name;
private String value;


public Info() {
 
}

public Info(String name, String value) {
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

public String getValue() {
	return value;
}


public void setValue(String value) {
	this.value = value;
}


@Override
public String toString() {
	return "" + (name != null ? "" + name + "==>" : "")
			+ (value != null ? "" + value + " " : "") + "";
}
}
