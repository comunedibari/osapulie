package eng.tz.la.model;

import java.util.ArrayList;
import java.util.Arrays;

public class MetaInfo extends ArrayList<Info> {

	private static final long serialVersionUID = 4142050442047142209L;


	public MetaInfo() {
		 
	}
	
	public MetaInfo(Info info) {
		super.add(info);
	}
	
	@Override
	public boolean add(Info arg0) {
		return super.add(arg0);
	}
	
	
	@Override
	public String toString() {
		return "" + (toArray() != null ? "MetaInfo=" + Arrays.toString(toArray()) + ", " : "")
				+  "";
	}	
}
