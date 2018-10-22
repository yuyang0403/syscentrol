package com.yuyang.other;

import com.yuyang.other.activemq.Producer;
import com.yuyang.other.lua.service.LuaTestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yuyang
 * @create 2018/10/22 10:26
 * @desc
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MqTest {
    @Autowired
    Producer producer;
    @Test
    public void test(){
        producer.sendMsg("获得一张优惠券");
    }
}
