package it.eng.tz.area.vasta.conservazione.jms.conf;

import java.util.Arrays;

import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.SimpleMessageConverter;
import org.springframework.jms.support.destination.DestinationResolver;
import it.eng.tz.area.vasta.conservazione.jms.MessageReceiver;


@Configuration
@ComponentScan(basePackages= {"it.eng.tz.area.vasta.conservazione.jms"})
public class MessagingConfiguration   {

	private static final String DEFAULT_BROKER_URL = "tcp://10.0.5.13:61616";
	private static final String NAME_TOPIC = "LogsAuditTopic";
	private static final String CLIENT_ID = "systemOsapulieReceive";
	
	@Value("${audit.broker.url}")
	private String brokerUrl;
	
	@Value("${audit.topic.name}")
	private String topicName;

	@Value("${audit.client.id}")
	private String clientId;
	
	
	@Autowired
	MessageReceiver messageReceiver;
	
	
	
	@Bean
	public ConnectionFactory connectionFactory(){
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL(brokerUrl);
		connectionFactory.setClientID(clientId);
		//connectionFactory.setTrustedPackages(Arrays.asList("it.eng.tz.area.vasta.conservazione.jms"));
		return connectionFactory;
	}

	/*
	 * Inutilizzato.
	 */
	 
//	@Bean
//	public ConnectionFactory cachingConnectionFactory(){
//		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//		connectionFactory.setTargetConnectionFactory(connectionFactory());
//		connectionFactory.setSessionCacheSize(10);
//		return connectionFactory;
//	}

	/*
	 * Contenitore listener dei messaggi, utilizzato per richiamare messageReceiver.onMessage 
	 * sulla ricezione dei messaggi.
	 */
	
	@Bean
	public MessageListenerContainer getContainer(){
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(connectionFactory());
		container.setDestination(new ActiveMQTopic(topicName+"?consumer.retroactive=true"));
		container.setDestinationName(topicName);
		container.setDestinationResolver(destinationResolver());
		container.setDurableSubscriptionName("systemUser");
		container.setSessionAcknowledgeMode(2);
		container.setMessageListener(messageReceiver);
		return container;
	}

	
	/*
	 * Utilizzato per l'invio di messaggi.
	 */
	@Bean 
	public JmsTemplate jmsTemplate(){
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(connectionFactory());
		template.setPubSubDomain(true);
		template.setDefaultDestinationName(topicName);
		template.setDeliveryMode(DeliveryMode.PERSISTENT);
		template.setDeliveryPersistent(true);
		return template;
	}
	
	
	@Bean
	DestinationResolver destinationResolver(){
		return new DestinationResolver() {
			
			@Override
			public Destination resolveDestinationName(Session arg0, String arg1, boolean arg2) throws JMSException {
				 
				return arg0.createTopic(topicName);
			}
		};
	}
	
	@Bean 
	MessageConverter converter(){
		return new SimpleMessageConverter();
	}

	 
 
}
