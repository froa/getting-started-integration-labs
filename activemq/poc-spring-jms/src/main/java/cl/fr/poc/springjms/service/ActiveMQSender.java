package cl.fr.poc.springjms.service;


import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by froa on 8/23/15.
 */
@Service
public class ActiveMQSender implements Sender {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(final String text) {

        this.jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message message = session.createTextMessage(text);
                //message.setJMSReplyTo(new ActiveMQQueue("queue.sampleQ2"));
                return message;
            }
        });
    }

    public void send(final Destination destination,final String text) {

        this.jmsTemplate.send(destination,new MessageCreator() {

            @Override
            public Message createMessage(Session session) throws JMSException {
                Message message = session.createTextMessage(text);
                return message;
            }
        });
    }
}
