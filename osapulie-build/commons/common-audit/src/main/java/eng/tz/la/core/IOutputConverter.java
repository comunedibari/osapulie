package eng.tz.la.core;

import eng.tz.la.model.Line;

public interface IOutputConverter {
	public String encode(Line<?> line) throws Exception;
}
