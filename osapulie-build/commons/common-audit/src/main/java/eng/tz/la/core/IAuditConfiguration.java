package eng.tz.la.core;

import eng.tz.la.jms.IPrint;

public interface IAuditConfiguration {
	public AuditManager audit();
	
	public IMetaActor initialMetaActor();

	public IOutputConverter initialOutputConverter();

	public IWriteline initialWriteline();

	public IPrint initialJMSPrint();

	public Settyngs initialSettyngs();
}
