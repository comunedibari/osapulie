package it.audit.test;

import javax.jms.JMSException;
import javax.jms.TextMessage;

// import com.fasterxml.jackson.databind.ObjectMapper;

import eng.tz.la.core.AuditManager;
import eng.tz.la.core.IAuditConfiguration;
import eng.tz.la.core.IMetaActor;
import eng.tz.la.core.IOutputConverter;
import eng.tz.la.core.IWriteline;
import eng.tz.la.core.Settyngs;
import eng.tz.la.jms.IPrint;
import eng.tz.la.jms.Producer;
import eng.tz.la.model.Line;
import eng.tz.la.model.LogSettyng;
import eng.tz.la.model.custom.AuditMetaActor;

public class Esempio_AuditConfiguration implements IAuditConfiguration{
	private static Esempio_AuditConfiguration auditConfiguration; 
	private Esempio_AuditConfiguration() { }
	
	  public static Esempio_AuditConfiguration configure() {
		  if(auditConfiguration==null)
		   auditConfiguration=new Esempio_AuditConfiguration();
		  return auditConfiguration;
	 }
	  
	  
	  public AuditManager audit() {
	  return AuditManager.audit(auditConfiguration);
	  }
	
	
	
	  public IMetaActor initialMetaActor()  {
			
			return new AuditMetaActor();
		}

		public IOutputConverter initialOutputConverter() {
			// TODO Auto-generated method stub
			return new IOutputConverter() {
				
				public String encode(Line<?> line) throws Exception {
					// ObjectMapper mapper= new ObjectMapper();
					 // return mapper.writeValueAsString(line);
					return line.toString();
				}
			};
		}

		public IWriteline initialWriteline()  {
			// TODO Auto-generated method stub
			return new IWriteline() {
				
				public void write(String lineContent, AuditManager auditManager) throws Exception {
				
//					if(!Producer.get().send(content.getEncodeConverter(),auditManager.getKeyUser(),auditManager.getFolderDay()))
//					{
//					 
//						auditManager.writeLineLogUser(content);
//					}
					auditManager.writeLineLogUser(lineContent);
					System.out.println("IWriteline MIO CONTENT "+lineContent);
				}
			};
		}

		public IPrint initialJMSPrint()  {
			// TODO Auto-generated method stub
			return new IPrint() {
				
				public <T> void print(TextMessage txtMsg,AuditManager auditManager) throws JMSException {
					
					String actorId = txtMsg.getStringProperty(Producer.ACTOR);
					String dataFolder = txtMsg.getStringProperty(Producer.DATA_FOLD);
					if (actorId != null && !actorId.isEmpty())
						auditManager.setKeyUser(actorId);
					if (dataFolder != null && !dataFolder.isEmpty())
						auditManager.setDataFold(dataFolder);
					 
				 
					auditManager.writeLineLogUser(txtMsg.getText());
					
					System.out.println("IPrint MIO CONTENT "+txtMsg.toString());	 
					
				}
			};
		}

		public Settyngs initialSettyngs(){
			// TODO Auto-generated method stub
			return new Settyngs() {
				
				public void settyng(LogSettyng settyng) {
					settyng.setPath_home("C:\\test-log");
					
				}
			};
		}

}
