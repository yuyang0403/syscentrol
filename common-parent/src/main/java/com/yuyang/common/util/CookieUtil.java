package com.yuyang.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yuyang
 * @create 2018/6/22 10:33
 * @desc
 **/
public class CookieUtil {
    static Logger logger=LoggerFactory.getLogger(CookieUtil.class);

    /**
     * 读取cookie数组，之后迭代出各个cookie
     * @param request
     */
    public static void showCookies(HttpServletRequest request){
        //根据请求数据，找到cookie数组
        Cookie[] cookies = request.getCookies();
        if (null==cookies) {
            //如果没有cookie数组
            logger.info("没有cookie");
        } else {
            for(Cookie cookie : cookies){
                logger.info("cookieName:"+cookie.getName()+",cookieValue:"+ cookie.getValue());
            }
        }
    }

    /**
     * 创建cookie，并将新cookie添加到“响应对象”response中。
     * @param response
     * @param key
     * @param value
     * @param path
     * @param maxAge
     */
    public static void addCookie(HttpServletResponse response,String key,String value,String path,int maxAge){
        //创建新cookie
        Cookie cookie = new Cookie(key,value);
        // 设置存在时间为5分钟
        cookie.setMaxAge(maxAge);
        //设置作用域
        cookie.setPath(path);
        //将cookie添加到response的cookie数组中返回给客户端
        response.addCookie(cookie);
        logger.info("添加一条cookie,key:"+key+"--value:"+value+"--maxAge:"+maxAge);
    }

    /**
     * 修改cookie，可以根据某个cookie的name修改它（不只是name要与被修改cookie一致，path、domain必须也要与被修改cookie一致）
     * @param request
     * @param response
     * @param key
     * @param value
     * @param path
     * @param maxAge
     */
    public static void editCookie(HttpServletRequest request,HttpServletResponse response,String key,String value,String path,int maxAge){
        Cookie[] cookies = request.getCookies();
        if (null==cookies) {
            System.out.println("没有cookies");
        } else {
            for(Cookie cookie : cookies){
                //迭代时如果发现与指定cookieName相同的cookie，就修改相关数据
                if(cookie.getName().equals(key)){
                    //修改value
                    cookie.setValue(value);
                    cookie.setPath(path);
                    // 修改存活时间
                    cookie.setMaxAge(maxAge);
                    //将修改过的cookie存入response，替换掉旧的同名cookie
                    response.addCookie(cookie);
                    logger.info("更新一条cookie,key:"+key+"--value:"+value+"--maxAge:"+maxAge);
                    break;
                }
            }
        }
    }

    /**
     * 删除cookie
     * @param request
     * @param response
     * @param key
     */
    public static void delCookie(HttpServletRequest request, HttpServletResponse response,String key){
        Cookie[] cookies = request.getCookies();
        if (null==cookies) {
            System.out.println("没有cookie");
        } else {
            for(Cookie cookie : cookies){
                //如果找到同名cookie，就将value设置为null，将存活时间设置为0，再替换掉原cookie，这样就相当于删除了。
                if(cookie.getName().equals(key)){
                    cookie.setValue(null);
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    logger.info("删除一条cookie,key:"+key);
                    break;
                }
            }
        }
    }
}
