package eng.tz.la.jms;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import eng.tz.la.core.AuditManager;

public interface IPrint {
 <T> void print(TextMessage txtMsg,AuditManager auditManager)throws JMSException;
}
