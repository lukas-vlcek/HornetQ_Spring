package org.hornetq.integration.spring;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;

public class MessageSender {
//    private ConnectionFactory connectionFactory;
    private Destination destination;

    private JmsTemplate jmsTemplate;

//    public ConnectionFactory getConnectionFactory() {
//        return connectionFactory;
//    }

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
//        this.connectionFactory = connectionFactory;
        this.jmsTemplate = new JmsTemplate(connectionFactory);
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }
    /*
    public void send(String msg) {
        try {
            Connection conn = connectionFactory.createConnection();
            Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(destination);
            TextMessage message = session.createTextMessage(msg);
            producer.send(message);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    */
    public void simpleSend() {
        this.jmsTemplate.send(this.destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
//              return session.createTextMessage("hello queue world");
              MapMessage msg =  session.createMapMessage();
                msg.setString("url","http://x.x.x/here");
                msg.setString("data","dhj3q49lkj");
                return msg;
            }
        });
    }

}
