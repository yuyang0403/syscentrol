package com.yuyang.other.activemq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author yuyang
 * @create 2018/8/28 10:15
 * @desc
 **/
@Component
@Slf4j
public class Consumer1 {
    @JmsListener(destination = "send_message_second")
    public void receiveQueue(String text){
      log.info("Consumer1收到消息："+text);
    }
}
