package it.osapulie.util.audit;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import com.fasterxml.jackson.databind.ObjectMapper;
//import org.codehaus.jackson.map.ObjectMapper;

import eng.tz.la.core.AuditManager;
import eng.tz.la.core.IAuditConfiguration;
import eng.tz.la.core.IMetaActor;
import eng.tz.la.core.IOutputConverter;
import eng.tz.la.core.IWriteline;
import eng.tz.la.core.Settyngs;
import eng.tz.la.jms.Costants;
import eng.tz.la.jms.IPrint;
import eng.tz.la.jms.Producer;
import eng.tz.la.model.Line;
import it.osapulie.infrastructure.security.OSApulieUserDetails;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditConfiguration implements IAuditConfiguration{
	private static final Logger logger = LoggerFactory.getLogger(ApplicationContextUtil.class.getName());	

	private static AuditConfiguration auditConfiguration; 
	private String topicName;
	private String brokerUrl;
	
	private AuditConfiguration() {
		  this.topicName= ApplicationContextUtil.getResource().getProperty("audit.jms.topic.name");
		  this.brokerUrl= ApplicationContextUtil.getResource().getProperty("audit.jms.broker.url");
		  logger.info("BrokerUrl::"+this.brokerUrl+" TopicName::"+this.topicName);
	}
	
	  public static AuditConfiguration configure() {
		  if(auditConfiguration==null)
		   auditConfiguration=new AuditConfiguration();
		
		  return auditConfiguration;
	 }
	  
	  
	  public AuditManager audit() {
	  return AuditManager.audit(this);
	  }
	
	
	
	  public IMetaActor initialMetaActor()  {
			
			return new IMetaActor() {

				 
				public String getActor() {
					Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
					if (authentication != null) {
						OSApulieUserDetails user = (OSApulieUserDetails) authentication.getPrincipal();
						return user.getUsername();
					}

					return "anonimus";
				}

				 
				public String getOrigin() {
					 
					try {
						return InetAddress.getLocalHost().getHostAddress();
					} catch (UnknownHostException e) {
				 
					}
					return "origin";
				}
 
				 
				public String toPrint() {
					return "" + (getOrigin() != null ? "Origin==>" + getOrigin() : "") 
							+ (getActor() != null ? " Actor==>" + getActor() + " " : "") + "|";
				}
		 
			};
			}

		public IOutputConverter initialOutputConverter() {
			 
			return new IOutputConverter() {
				
				public String encode(Line<?> line)  {
					try {
					ObjectMapper mapper= new ObjectMapper();
				return mapper.writeValueAsString(line);
					}catch (Exception e) {
						logger.info("IOutputConverter Exception Nella Conversione della line=="+line.toString());	
						logger.error("IOutputConverter Exception",e);
						return "";
					}
				}
			};
		}

		public IWriteline initialWriteline()  {
			 
			return new IWriteline() {
				
				public void write(String lineContent, AuditManager auditManager) throws Exception {
				
					if(!Producer.get(getBrokerUrl(),getTopicName()).sendDurable(lineContent,auditManager.getKeyUser(),auditManager.getFolderDay()))
					{
						auditManager.writeLineLogUser(lineContent);
					}
					
					logger.info("IWriteline lineContent == "+lineContent);
				}
			};
		}

		public IPrint initialJMSPrint()  {
			
			return new IPrint() {
				
				public <T> void print(TextMessage txtMsg,AuditManager auditManager) throws JMSException {
					
					String actorId = txtMsg.getStringProperty(Producer.ACTOR);
					String dataFolder = txtMsg.getStringProperty(Producer.DATA_FOLD);
					if (actorId != null && !actorId.isEmpty())
						auditManager.setKeyUser(actorId);
					if (dataFolder != null && !dataFolder.isEmpty())
						auditManager.setDataFold(dataFolder);
					 
				 
					auditManager.writeLineLogUser(txtMsg.getText());
					
					logger.info("IPrint ACTOR=="+actorId+" DATA_FOLD=="+dataFolder+" TextMessage=="+txtMsg.toString());	 
					
				}
			};
		}

		public Settyngs initialSettyngs(){
			
			return  AuditCustomSettyng.get();
		}
		
		public String getTopicName() {
			if(this.topicName==null) {this.topicName=Costants.TOPIC_NAME;}
			return topicName;
		}
		
		public String getBrokerUrl() {
			if(this.brokerUrl==null) {this.brokerUrl=Costants.BROKER_URL;}
			return brokerUrl;
		}
}
