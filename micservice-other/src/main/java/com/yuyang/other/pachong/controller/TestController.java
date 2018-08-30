package com.yuyang.other.pachong.controller;

import com.yuyang.other.pachong.grab.jiangsu.GrabJsCz;
import org.springframework.web.bind.annotation.*;

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
    @CrossOrigin(origins = "*")
    @RequestMapping(value="/pachong/test",method = RequestMethod.GET)
    public Object sendMsg(){
        List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
        return new GrabJsCz().grabListFgw(list);
    }
}
