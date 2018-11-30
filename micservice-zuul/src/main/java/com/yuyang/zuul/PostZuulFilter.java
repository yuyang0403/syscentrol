package com.yuyang.zuul;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.yuyang.common.util.Constant;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class PostZuulFilter extends ZuulFilter {
    Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Override
    public boolean shouldFilter() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public Object run() {
        System.out.println("进入了后置过滤器");
        RequestContext ctx = RequestContext.getCurrentContext();
        //获取响应流
        try {
            InputStream stream = ctx.getResponseDataStream();
            String resContent=null;
            if(stream!=null) {
                resContent= IOUtils.toString(stream, StandardCharsets.UTF_8);
                LOGGER.info("响应数据："+resContent);
                JSONObject jsonObject=JSONObject.parseObject(resContent);
                if(jsonObject!=null&&jsonObject.containsKey("errorCode")&&!jsonObject.getString("errorCode").equals("0")){
                    ctx.setSendZuulResponse(false);
                    ctx.setResponseStatusCode(jsonObject.getInteger("errorCode"));
                    ctx.set("isSuccess", false);
                    LOGGER.info("错误编码："+jsonObject.getInteger("errorCode"));
                }
            }
            ctx.setResponseBody(resContent);
        } catch (Exception e) {
            LOGGER.error("转换响应参数出错",e);
        }
        return null;
    }

    @Override
    public String filterType() {
        // TODO Auto-generated method stub
        return "post";
    }

    @Override
    public int filterOrder() {
        // TODO Auto-generated method stub
        return 0;
    }

}
