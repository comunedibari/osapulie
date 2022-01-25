package eng.tz.la.jms;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eng.tz.la.core.AuditManager;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Reciever {
	private static final Logger logger = LoggerFactory.getLogger(Reciever.class.getName());	
	protected int ackMode = Session.AUTO_ACKNOWLEDGE;
	public boolean startReciver = true;
	private AuditManager auditManager;
	private TopicMessageListener topicMessageListener = new TopicMessageListener();
	private Connection connectionCls = null;
	private Session sessionCls=null;
	private MessageConsumer consumerCls=null;
	public String brokerUrl; //"tcp://10.0.5.9:61616"
	public String topicName;
	public IPrint iPrint = new IPrint() {

		public <T> void print(TextMessage txtMsg,AuditManager auditManager) throws JMSException {
		logger.info("Received a message: DEFAULT iPrint " + txtMsg.getStringProperty(Producer.ACTOR) + " - " + txtMsg.getText());	
		}
	};

	private static Reciever reciever;

	public Reciever() {

	}

	public Reciever(IPrint iPrint) {
		this.iPrint = iPrint;
	}
	public Reciever(String brokerUrl) {
		this.brokerUrl=brokerUrl;
	}
	public Reciever(String brokerUrl ,String topicName) {
		this.brokerUrl=brokerUrl;
		this.topicName = topicName;
	}
	public Reciever(String brokerUrl ,String topicName,IPrint iPrint) {
		this.brokerUrl=brokerUrl;
		this.topicName = topicName;
		this.iPrint = iPrint;
	}
	public Reciever(String brokerUrl ,IPrint iPrint) {
		this.brokerUrl=brokerUrl;
		this.iPrint = iPrint;
	}
	public Reciever(String brokerUrl,IPrint iPrint,AuditManager auditManager) {
		this.brokerUrl=brokerUrl;
		this.iPrint = iPrint;
		this.auditManager=auditManager;
	}
	public Reciever(String brokerUrl,String topicName,IPrint iPrint,AuditManager auditManager) {
		this.brokerUrl=brokerUrl;
		this.topicName = topicName;
		this.iPrint = iPrint;
		this.auditManager=auditManager;
	}
	public Reciever(IPrint iPrint,AuditManager auditManager) {
		this.iPrint = iPrint;
		this.auditManager=auditManager;
	}
	public static Reciever get() {
		if (reciever != null) {
			reciever.stopReciever();
			reciever.closeAll();
		}
			reciever = new Reciever();
		return reciever;
	}

	public static Reciever get(IPrint iPrint) {
		if (reciever != null) {
			reciever.stopReciever();
			reciever.closeAll();
		}
			reciever = new Reciever(iPrint);
		return reciever;
	}
	
	public static Reciever get(IPrint iPrint,AuditManager auditManager) {
		if (reciever != null) {
			reciever.stopReciever();
			reciever.closeAll();
		}
			reciever = new Reciever(iPrint,auditManager);
		return reciever;
	}
	
	public static Reciever get(String brokerUrl,IPrint iPrint,AuditManager auditManager) {
		if (reciever != null) {
			reciever.stopReciever();
			reciever.closeAll();
		}
			reciever = new Reciever(brokerUrl,iPrint,auditManager);
		return reciever;
	}
	public static Reciever get(String brokerUrl,String topicName,IPrint iPrint,AuditManager auditManager) {
		if (reciever != null) {
			reciever.stopReciever();
			reciever.closeAll();
		}
			reciever = new Reciever(brokerUrl,topicName,iPrint,auditManager);
		return reciever;
	}
	public static Reciever get(String brokerUrl,String topicName,IPrint iPrint) {
		if (reciever != null) {
			reciever.stopReciever();
			reciever.closeAll();
		}
			reciever = new Reciever(brokerUrl,topicName,iPrint);
		return reciever;
	}
	public static Reciever get(String brokerUrl,IPrint iPrint) {
		if (reciever != null) {
			reciever.stopReciever();
			reciever.closeAll();
		}
			reciever = new Reciever(brokerUrl,iPrint);
		return reciever;
	}
	
	public static Reciever get(String brokerUrl,String topicName) {
		if (reciever != null) {
			reciever.stopReciever();
			reciever.closeAll();
		}
			reciever = new Reciever(brokerUrl,topicName);
		return reciever;
	}
	
	public static Reciever get(String brokerUrl) {
		if (reciever != null) {
			reciever.stopReciever();
			reciever.closeAll();
		}
			reciever = new Reciever(brokerUrl);
		return reciever;
	}
	
	public void setiPrint(IPrint iPrint) {
		this.iPrint = iPrint;
	}

	public void receive() {

		try {
			if(this.brokerUrl==null) {this.brokerUrl=Costants.BROKER_URL;}
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(this.brokerUrl);
			QueueConnection queueConnection = connectionFactory.createQueueConnection();
			QueueSession queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue queue = (Queue) queueSession.createQueue(Costants.QUEUE_NAME);
			QueueReceiver receiver = queueSession.createReceiver(queue);

			queueConnection.start();
			try {
				logger.info("Reciever -> START");
				while (true) {
					Message message = receiver.receive(1000);
					if (message != null && message instanceof TextMessage) {
						processMessage(message,this.auditManager);
					} else {
						logger.info("Reciever -> BREAK");	
						break;
					}
				}
			} finally {
				queueSession.close();
				queueConnection.close();
				receiver.close();
				logger.info("Reciever -> CLOSE");
			}
		} catch (Exception e) {
			logger.error("Reciever -> ERROR -- Receiving messages failed: ",e);
		}

	}
	
	 public void ReceiverDurableCls()  {
		 
			try {
				// Producer 
				ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(this.getBrokerUrl());
				connectionCls = connectionFactory.createConnection();
				connectionCls.setClientID(Costants.CLIENT_ID_TOPIC_RECEIVE);
				sessionCls = connectionCls.createSession(false, Session.AUTO_ACKNOWLEDGE);
				Topic topic = sessionCls.createTopic(this.getTopicName());
                         
				 consumerCls = sessionCls.createDurableSubscriber(topic, Costants.CONSUMER_ID_TOPIC, "", false);		
				
				 connectionCls.start();
				 logger.info("Reciever -> START");
				while (isStartReciver()) {
	                        
				TextMessage message = (TextMessage) consumerCls.receive();
				if (message != null && message instanceof TextMessage) {
					processMessage(message,this.auditManager);
				} else {
					logger.info("ReceiverDurable -> BREAK");	
					//break;
				}
	 
					}
  
			}catch (Exception e) {
				
			} finally {
				
				closeAll();
				 
			}
	 
	 }
	 
	 public Reciever closeAll() {
		 try {
			 if (connectionCls != null) {
					try {
						connectionCls.close();
					} catch (JMSException e) { }
				}
				if (sessionCls != null) {
					try {
						sessionCls.close();
					} catch (JMSException e) { }
				}
				if (consumerCls != null) {
					try {
						consumerCls.close();
					} catch (JMSException e) { }
				}
 
		 }catch(Exception e) { }
		 
		 return this;
	 }
	 
	 
	 public void ReceiverDurable()  {
	 		Connection connection = null;
	        Session session=null;
	        TopicSubscriber  consumer=null;
			try {
				if(this.brokerUrl==null) {this.brokerUrl=Costants.BROKER_URL;}
				// Producer 
				ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(this.getBrokerUrl());
				connection = connectionFactory.createConnection();
				connection.setClientID(Costants.CLIENT_ID_TOPIC_RECEIVE);
				connection.start();
				session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
				Topic topic = session.createTopic(this.getTopicName());
                      
				 consumer = session.createDurableSubscriber(topic, Costants.CONSUMER_ID_TOPIC, "", false);		
				 consumer.setMessageListener(topicMessageListener);
			  
				 logger.info("Reciever -> START");
				
	 

			}catch (Exception e) {
				
			} finally {
				if (connection != null) {
					try {
						connection.close();
					} catch (JMSException e) { }
				}
				if (session != null) {
					try {
						session.close();
					} catch (JMSException e) { }
				}
				if (consumer != null) {
					try {
						consumer.close();
					} catch (JMSException e) { }
				}
				 
			}
	 
	 }
	 
	public boolean isStartReciver() {
		return this.startReciver;
	}

	public Reciever stopReciever() {
		this.startReciver = false;
		return this;
	}

	public void processMessage(Message message,AuditManager auditManager) {
		try {
			if (message != null) {
				TextMessage txtMsg = (TextMessage) message;
				this.iPrint.print(txtMsg,auditManager);
			}

		} catch (Exception e) {
			logger.error("Reciever -> ERROR -- processMessage: ",e);
		}
	}
	
	
	public class TopicMessageListener implements MessageListener {
 
		private Message lastReceived;

		@Override
		public void onMessage(Message message) {
			logger.info("the received message: " + message);
			lastReceived = message;
			processMessage(message, auditManager); 
			logger.info("Reciever -> TopicMessageListener::  onMessage");
		}

		public Message getLastReceived() {
			return lastReceived;
		}

	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public void setBrokerUrl(String brokerUrl) {
		this.brokerUrl = brokerUrl;
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
