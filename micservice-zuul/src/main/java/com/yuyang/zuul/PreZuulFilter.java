package com.yuyang.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.yuyang.common.constant.Constant;
import com.yuyang.common.util.CookieUtil;
import com.yuyang.zuul.service.TokenService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PreZuulFilter extends ZuulFilter {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    TokenService tokenService;

    @Override
    public boolean shouldFilter() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //user-login/login
        logger.info("请求地址：" + request.getRequestURI());
        if (request.getRequestURI().contains("/user-login/login")) {
            logger.info("请求的地址是登录/登出,不进行token校验");
            return null;
        }
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)||token.equals("null")) {
            //没有token参数，重新登录
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(100001);
            ctx.set("isSuccess", false);
            logger.error(ctx.getResponseBody());
            return null;
        }
        if (!tokenService.checkToken(token)) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(100002);
            ctx.set("isSuccess", false);
            logger.error(ctx.getResponseBody());
            return null;
        }
        //刷新cookie
        CookieUtil.editCookie(request, response, "token", token, Constant.DEFAULT_COOKIE_PATH, Constant.DEFAULT_COOKIE_MAX_AGE);
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
