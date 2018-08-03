package com.yuyang.zuul.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilterErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        // TODO Auto-generated method stub
        return "/error";
    }

    @RequestMapping("/error")
    public String error() {
        return "出现异常";
    }
}
