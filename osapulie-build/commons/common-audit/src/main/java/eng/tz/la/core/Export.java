package eng.tz.la.core;

import java.io.IOException;

import eng.tz.la.model.OperationExportFile;

public interface Export {
	public void call(OperationExportFile file) throws IOException;

}
