package org.hornetq.integration.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ExampleTest {

    private final static Logger logger = LoggerFactory.getLogger(ExampleTest.class);

    private ClassPathXmlApplicationContext consumerContext = null;
    private ClassPathXmlApplicationContext producerContext = null;

    @BeforeClass
    public void setUp() throws InterruptedException {
        startConsumer();
    }

    @AfterClass
    public void TearDown() throws InterruptedException {
        stopConsumer();
        stopProducer();
    }

    @Test
    public void startProducerAfterConsumerTest() throws InterruptedException {

        Thread.sleep(1000); // give consumer some time to fully startup and find out that no server is running
        startProducer();

        MessageSender topicSender = (MessageSender) producerContext.getBean("MessageSenderToTopic");
        MessageSender queueSender = (MessageSender) producerContext.getBean("MessageSenderToQueue");

        logger.info("Giving JMS some time to fully setup...");
        Thread.sleep(5000);

        logger.info("Sending messages...");
        topicSender.simpleSend();
        queueSender.simpleSend();
        Thread.sleep(100);
    }

    private void startConsumer() {
        logger.info("Starting consumer");
        consumerContext = new ClassPathXmlApplicationContext(new String[]{"consumer-jms-beans.xml"});
        logger.info("Consumer context retrieved");
    }

    private void stopConsumer() {
        if (consumerContext != null) { consumerContext.destroy(); }
        logger.info("Consumer context destroyed");
    }

    private void startProducer() {
        logger.info("Starting producer");
        producerContext = new ClassPathXmlApplicationContext(new String[]{"embedded-jms-beans.xml"});
        logger.info("Producer context retrieved");
    }

    private void stopProducer() {
        if (producerContext != null) { producerContext.destroy(); }
        logger.info("Producer context destroyed");
    }

}
