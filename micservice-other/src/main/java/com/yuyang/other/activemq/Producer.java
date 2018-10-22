package com.yuyang.other.activemq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * @author yuyang
 * @create 2018/8/28 10:01
 * @desc 生产者
 **/
@Component
@Slf4j
public class Producer {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    public void sendMsg(Object msg){
        for (int i=1;i<=100;i++){
            jmsMessagingTemplate.convertAndSend("send_message_second",msg.toString()+i);
            log.info("发出消息："+(msg.toString()+i));
        }
    }
}
