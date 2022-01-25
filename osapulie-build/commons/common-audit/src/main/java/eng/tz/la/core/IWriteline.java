package eng.tz.la.core;

public interface IWriteline {
	public void write(String lineContent, AuditManager auditManager) throws Exception;
}
