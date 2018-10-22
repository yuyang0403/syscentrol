package com.yuyang.other.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsMessagingTemplate;

/**
 * @author yuyang
 * @create 2018/10/22 10:09
 * @desc
 **/
@Configuration
public class ActivemqConfig {
    @Value("${activemq.broker-url}")
    private String brokerUrl;
    @Value("${activemq.close-timeout}")
    private Integer closeTimeOut;
    @Value("${activemq.pool.max-connections}")
    private Integer maxConnections;
    @Value("${activemq.send-timeout}")
    private Integer sendTimeOut;
    @Bean
    public JmsMessagingTemplate getJmsMessagingTemplate(){
        JmsMessagingTemplate jmsMessagingTemplate=new JmsMessagingTemplate();
        ActiveMQConnectionFactory connectionFactory=new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(brokerUrl);
        connectionFactory.setCloseTimeout(closeTimeOut);
        connectionFactory.setMaxThreadPoolSize(maxConnections);
        connectionFactory.setSendTimeout(sendTimeOut);
        jmsMessagingTemplate.setConnectionFactory(connectionFactory);
        return jmsMessagingTemplate;
    }
}
