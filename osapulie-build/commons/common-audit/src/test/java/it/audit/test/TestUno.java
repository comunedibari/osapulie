package it.audit.test;
 

import java.util.Date;
import java.util.UUID;

import javax.jms.JMSException;
import javax.jms.TextMessage;

//import com.fasterxml.jackson.databind.ObjectMapper;

import eng.tz.la.core.AuditManager;
import eng.tz.la.core.IAuditConfiguration;
import eng.tz.la.core.IMetaActor;
import eng.tz.la.core.IOutputConverter;
import eng.tz.la.core.IWriteline;
import eng.tz.la.core.Settyngs;
import eng.tz.la.jms.IPrint;
import eng.tz.la.jms.Producer;
import eng.tz.la.model.Info;
import eng.tz.la.model.Line;
import eng.tz.la.model.LogSettyng;
import eng.tz.la.model.MetaField;
import eng.tz.la.model.MetaLine;
import eng.tz.la.model.custom.AuditMetaActor;
import eng.tz.la.model.custom.CustomSettyng;
import it.audit.reflection.test.ClassLogTest;

public class TestUno {

	 MyConfing confing;
	 
	 public TestUno() {
		 confing= new MyConfing();
	}
	 
		public  void mainTest(){
		 
		//	 Producer.get().send("Ecco un mio messaggio!!!","SALVATORE","09102018");
		
			ClassLogTest clReflec=new ClassLogTest();
			clReflec.setCognome("Liberto");
			clReflec.setNome("Luca");
			clReflec.setEta(10.0);
			clReflec.setData(new Date());
			
			AuditManager manager=Esempio_AuditConfiguration.configure().audit()
					.addInizioOperazione()
					.addFineOperazione()
					.addUuidOperazione(UUID.randomUUID().toString())
					.addInfo("PAGE", "index.jsp")
					.addInfo(new Info("Test json", "result json"))
					.addMetaField("classe", clReflec)
					.build(true);
			
			
			
			MetaLine metaLine=new MetaLine(new MetaField("NOME","NICOLA"));
			metaLine
			.addField("VIA","NAZIONE 354",true)
			.addField("CAP", "80056")
			.addField(new MetaField("1","2",Integer.class));
			AuditManager.audit(confing).addInfo("OPERAZIONE","UPLOAD DICHIARAZIONE").setActor("Salvo").laSettyng(CustomSettyng.class)
			.log(metaLine).build(true);
			
			// Result:
			// 11-07-2018 19:59:41.341 Origin==>127.0.0.1 Actor==>Salvo | MetaInfo=[Operazione==>Test1 ], MetaField=[nome==>salvatore ],
			
			
			//OPPURE
			
			AuditManager.audit(confing).addInfo("OPERAZIONE","UPLOAD DICHIARAZIONE").setActor("Giovanni").laSettyng(CustomSettyng.class)
			.addMetaField("Nome", "Salvatore").addMetaField("Cognome", "Mariniello").build();
			// Result:
			//11-07-2018 20:07:08.679 Origin==>127.0.0.1 Actor==>Salvo | MetaInfo=[Operazione==>Test1 ], MetaField=[Nome==>Salvatore Type:java.lang.String, Cognome==>Mariniello Type:java.lang.String], 
			
		
			
			AuditManager.audit(confing).addInfo("OPERAZIONE","UPLOAD DICHIARAZIONE").setActor("Nicola").laSettyng(CustomSettyng.class)
			.addMetaField("Nome", "Salvatore").addMetaField("Cognome", "Mariniello").build();
			// Result:
			//11-07-2018 20:07:08.679 Origin==>127.0.0.1 Actor==>Salvo | MetaInfo=[Operazione==>Test1 ], MetaField=[Nome==>Salvatore Type:java.lang.String, Cognome==>Mariniello Type:java.lang.String], 
	 
			
		}  
	 
	 
	public static void main(String[] args){
		new TestUno().mainTest();
	//	 Producer.get().send("Ecco un mio messaggio!!!","SALVATORE","09102018");
//	
//		MetaLine metaLine=new MetaLine(new MetaField("NOME","NICOLA"));
//		metaLine
//		.addField("VIA","NAZIONE 354",true)
//		.addField("CAP", "80056")
//		.addField(new MetaField("1","2",Integer.class));
//		AuditManager.audit(confing).addInfo("OPERAZIONE","UPLOAD DICHIARAZIONE").setActor("Salvo").laSettyng(CustomSettyng.class)
//		.log(metaLine).build(true);
//		
//		// Result:
//		// 11-07-2018 19:59:41.341 Origin==>127.0.0.1 Actor==>Salvo | MetaInfo=[Operazione==>Test1 ], MetaField=[nome==>salvatore ],
//		
//		
//		//OPPURE
//		
//		AuditManager.audit(confing).addInfo("OPERAZIONE","UPLOAD DICHIARAZIONE").setActor("Giovanni").laSettyng(CustomSettyng.class)
//		.addMetaField("Nome", "Salvatore").addMetaField("Cognome", "Mariniello").build();
//		// Result:
//		//11-07-2018 20:07:08.679 Origin==>127.0.0.1 Actor==>Salvo | MetaInfo=[Operazione==>Test1 ], MetaField=[Nome==>Salvatore Type:java.lang.String, Cognome==>Mariniello Type:java.lang.String], 
//		
//	
//		
//		AuditManager.audit(confing).addInfo("OPERAZIONE","UPLOAD DICHIARAZIONE").setActor("Nicola").laSettyng(CustomSettyng.class)
//		.addMetaField("Nome", "Salvatore").addMetaField("Cognome", "Mariniello").build();
//		// Result:
//		//11-07-2018 20:07:08.679 Origin==>127.0.0.1 Actor==>Salvo | MetaInfo=[Operazione==>Test1 ], MetaField=[Nome==>Salvatore Type:java.lang.String, Cognome==>Mariniello Type:java.lang.String], 
// 
		
	} 
	
	public class MyConfing implements IAuditConfiguration{

		
		public MyConfing() {
			
		}
		
		
	   AuditManager getConfiguration() {
			return  AuditManager.audit(this);
		}
		
	   public AuditManager audit() {
	 
		  return  AuditManager.audit(this);
	}
		
		public IMetaActor initialMetaActor()  {
			// TODO Auto-generated method stub
			return null;
		}

		public IOutputConverter initialOutputConverter() {
			// TODO Auto-generated method stub
			return new IOutputConverter() {
				
				public String encode(Line<?> line) throws Exception {
//					ObjectMapper mapper= new ObjectMapper();
//					return mapper.writeValueAsString(line);
					return line.toString();
				}
			};
		}

		public IWriteline initialWriteline()  {
			// TODO Auto-generated method stub
			return new IWriteline() {
				
				public void write(String lineContent, AuditManager auditManager) throws Exception {
				
					if(!Producer.get().send(lineContent,auditManager.getKeyUser(),auditManager.getFolderDay()))
					{
						auditManager.writeLineLogUser(lineContent);
					}
					
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
	
}
