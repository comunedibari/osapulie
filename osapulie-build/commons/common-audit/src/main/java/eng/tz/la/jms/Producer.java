package eng.tz.la.jms;

import java.util.UUID;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Producer {
	private static final Logger logger = LoggerFactory.getLogger(Producer.class.getName());

	static Producer messageProducerClient;
	public static final String ACTOR = "ACTOR";
	public static final String DATA_FOLD = "DATAFOLD";
	public String brokerUrl; //"tcp://10.0.5.9:61616"
	public String topicName;
	public Producer() { }
	
	public Producer(String brokerUrl) {
		 this.brokerUrl=brokerUrl;
	}
	public Producer(String brokerUrl,String topicName) {
		 this.brokerUrl=brokerUrl;
		 this.topicName=topicName;
	}
 
	public static Producer get() {

		if (messageProducerClient == null) {
			messageProducerClient = new Producer();

		}
		return messageProducerClient;
	}

	public static Producer get(String brokerUrl,String topicName) {

		if (messageProducerClient == null) {
			messageProducerClient = new Producer(brokerUrl,topicName);

		}
		return messageProducerClient;
	}
	
	public static Producer get(String brokerUrl) {

		if (messageProducerClient == null) {
			messageProducerClient = new Producer(brokerUrl);

		}
		return messageProducerClient;
	}
	
	public boolean send(String text, String actor, String dataFold) {
		try {
			if(this.brokerUrl==null) {this.brokerUrl=Costants.BROKER_URL;}
			 
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(this.getBrokerUrl());
			Connection connection = connectionFactory.createConnection();
			connection.setClientID(Costants.CLIENT_ID);
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue(Costants.QUEUE_NAME);
			MessageProducer producer = session.createProducer(destination);

			TextMessage testMessage = session.createTextMessage();
			testMessage.setText(text);
			testMessage.setStringProperty(ACTOR, actor);
			testMessage.setStringProperty(DATA_FOLD, dataFold);
			producer.send(testMessage, javax.jms.DeliveryMode.PERSISTENT, javax.jms.Message.DEFAULT_PRIORITY,
					javax.jms.Message.DEFAULT_TIME_TO_LIVE);
			System.out.println("Successfully sent message.");
			if (connection != null)
				connection.close();
			if (session != null)
				session.close();
			if (producer != null)
				producer.close();
			
			return true;

		} catch (Exception e) {
			logger.error("Producer-> send : ERROR ", e);
			return false;
		}

	}

	public boolean sendDurable(String text, String actor, String dataFold) {
		 
		Connection connection = null;
		MessageProducer producer = null;
		Session session = null;
		try {
			// Producer
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(this.getBrokerUrl());
			connection = connectionFactory.createConnection();
			connection.setClientID(Costants.CLIENT_ID_TOPIC_PRODUCER+"-"+UUID.randomUUID().toString());
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Topic topic = session.createTopic(this.getTopicName());
 
			TextMessage textMessage = session.createTextMessage();
			textMessage.setText(text);
			textMessage.setStringProperty(ACTOR, actor);
			textMessage.setStringProperty(DATA_FOLD, dataFold);

			producer = session.createProducer(topic);

			producer.send(textMessage, javax.jms.DeliveryMode.PERSISTENT, javax.jms.Message.DEFAULT_PRIORITY,Message.DEFAULT_TIME_TO_LIVE);
			
			if (connection != null)
				connection.close();
			if (session != null)
				session.close();
			if (producer != null)
				producer.close();
 
			
			return true;
		} catch (Exception e) {
			logger.error("Producer-> sendDurable : ERROR ", e);
			return false;
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
