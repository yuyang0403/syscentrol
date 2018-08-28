package com.yuyang.other.controller;

import com.yuyang.other.activemq.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuyang
 * @create 2018/8/28 10:08
 * @desc
 **/
@RestController
public class ActiveMqController {
    @Autowired
    Producer producer;
    @RequestMapping(value="/activemq/sendMsg/{msg}",method = RequestMethod.GET)
    public String sendMsg(@PathVariable("msg") String msg){
        producer.sendMsg(msg);
        return "test success";
    }
}
