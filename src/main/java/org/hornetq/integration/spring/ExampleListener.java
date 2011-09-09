package org.hornetq.integration.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MapMessage;

public class ExampleListener implements MessageListener {

    private final static Logger logger = LoggerFactory.getLogger(ExampleListener.class);

    private static String url = null;
    private static String data = null;

    public ExampleListener() {
//        logger.info("Creating ExampleListener {}", this);
    }

    public void onMessage(Message message) {
        try {
            url = ((MapMessage)message).getString("url");
            data = ((MapMessage)message).getString("data");
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
        logger.info("Message received [{}]: {}", this, url + ", " + data);
    }
}
