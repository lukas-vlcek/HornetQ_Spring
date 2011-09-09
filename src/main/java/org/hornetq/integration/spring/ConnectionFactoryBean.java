package org.hornetq.integration.spring;

import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.api.jms.HornetQJMSClient;
import org.hornetq.api.jms.JMSFactoryType;
import org.hornetq.core.remoting.impl.netty.NettyConnectorFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import javax.jms.ConnectionFactory;
import java.util.HashMap;
import java.util.Map;

public class ConnectionFactoryBean implements FactoryBean<ConnectionFactory>, InitializingBean, DisposableBean {

    private ConnectionFactory cf;

    @Override
    public void destroy() throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ConnectionFactory getObject() throws Exception {
        return cf;
    }

    @Override
    public Class<ConnectionFactory> getObjectType() {
        return ConnectionFactory.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        createConnectionFactory();
    }

    private void createConnectionFactory() {

        Map<String, Object> connectionParams = new HashMap<String, Object>();

        connectionParams.put(org.hornetq.core.remoting.impl.netty.TransportConstants.PORT_PROP_NAME, 5446);
        connectionParams.put(org.hornetq.core.remoting.impl.netty.TransportConstants.HOST_PROP_NAME, "localhost");

        TransportConfiguration transportConfiguration = new TransportConfiguration(NettyConnectorFactory.class.getName(), connectionParams);

        cf = (ConnectionFactory) HornetQJMSClient.createConnectionFactoryWithoutHA(JMSFactoryType.TOPIC_CF, transportConfiguration);

    }
}
