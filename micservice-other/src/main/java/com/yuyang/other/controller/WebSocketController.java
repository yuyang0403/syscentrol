package com.yuyang.other.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuyang
 * @create 2018/8/27 11:09
 * @desc
 **/
@RestController
public class WebSocketController{
    @RequestMapping(value="/sendMsg",method = RequestMethod.POST,consumes = "application/json")
    public String test(){
        return "test success";
    }
}
