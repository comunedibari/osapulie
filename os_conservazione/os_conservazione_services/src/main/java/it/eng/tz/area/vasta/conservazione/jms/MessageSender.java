package it.eng.tz.area.vasta.conservazione.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

 
@Component
public class MessageSender {

	@Autowired
	JmsTemplate jmsTemplate;

	public void sendMessage(final String text) {

		jmsTemplate.send(new MessageCreator(){
				@Override
				public Message createMessage(Session session) throws JMSException{
					TextMessage objectMessage = session.createTextMessage(text);
					return objectMessage;
				}
			});
	}

}
