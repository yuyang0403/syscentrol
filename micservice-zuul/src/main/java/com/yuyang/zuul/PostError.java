package com.yuyang.zuul;

import com.netflix.zuul.ZuulFilter;

public class PostError extends ZuulFilter {

    @Override
    public boolean shouldFilter() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public Object run() {
        System.out.println("进入了后置过滤器");
        return null;
    }

    @Override
    public String filterType() {
        // TODO Auto-generated method stub
        return "pre";
    }

    @Override
    public int filterOrder() {
        // TODO Auto-generated method stub
        return 0;
    }

}
