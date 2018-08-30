package com.yuyang.other.pachong.controller;

import com.yuyang.other.pachong.grab.jiangsu.GrabJsCz;
import com.yuyang.other.pachong.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author yuyang
 * @create 2018/8/30 9:47
 * @desc
 **/

@RestController
public class TestController {
    @Autowired
    TestService testService;
    @CrossOrigin(origins = "*")
    @RequestMapping(value="/pachong/test",method = RequestMethod.GET)
    public Object sendMsg(){
        List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
        return new GrabJsCz().grabListFgw(list);
    }
    @CrossOrigin(origins = "*")
    @RequestMapping(value="/pachong/xiaozhu",method = RequestMethod.GET)
    public Object getHtml(){
        return testService.getHtml();
    }
}
