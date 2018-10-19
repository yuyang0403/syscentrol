package com.yuyang.user.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.yuyang.common.cache.RedisCache;
import com.yuyang.common.constant.Constant;
import com.yuyang.common.util.CookieUtil;
import com.yuyang.user.model.SysUser;
import com.yuyang.user.service.SysUserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author yuyang
 * @create 2018/6/19 14:20
 * @desc
 **/
@RestController
public class LoginController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    SysUserService sysUserService;
    @Autowired
    RedisCache redisCache;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @CrossOrigin(origins = "*")
    public String login(SysUser user, HttpServletRequest request, HttpServletResponse response) {
        JsonObject result = new JsonObject();
        String userid = "";
        try {
            SysUser loginUser = sysUserService.selectSysUserByNameAndPwd(user);
            result.addProperty("error", "0");
            result.add("token", new Gson().toJsonTree(loginUser));
            if (loginUser == null) {
                result.addProperty("error", "1");
                result.addProperty("message", "登录失败，用户名或者密码有误！");
                logger.info(result.toString());
                return result.toString();
            }
            userid = loginUser.getId().toString();
        } catch (Exception e) {
            result.addProperty("error", "1");
            result.addProperty("message", e.getMessage());
            logger.error(result.toString(), e);
            return result.toString();
        }
        String jwtToken = Jwts.builder().setSubject(userid).setIssuedAt(new Date()).claim("userid", userid)
                .signWith(SignatureAlgorithm.HS256, Constant.TOKEN_SECURE).compact();
        result.addProperty("token", jwtToken);
        result.addProperty("userid", userid);
        redisCache.set(Constant.TOKEN_KEY + userid, jwtToken);
        redisCache.expire(Constant.TOKEN_KEY + userid, 20 * 60);
        //添加cookie
        CookieUtil.addCookie(response, "userid", userid, Constant.DEFAULT_COOKIE_PATH, Constant.DEFAULT_COOKIE_MAX_AGE);
        CookieUtil.addCookie(response, "token", jwtToken, Constant.DEFAULT_COOKIE_PATH, Constant.DEFAULT_COOKIE_MAX_AGE);
        logger.info("用户" + userid + "登录成功！token:" + jwtToken);
        return result.toString();
    }

    /**
     * 退出登录
     *
     * @param token
     * @return
     */
    @CrossOrigin(origins = "*")
    @PostMapping("login/logout")
    public void logout(@RequestParam("token") String token, HttpServletRequest request, HttpServletResponse response) {
        Claims claims = null;
        String userid = "";
        try {
            claims = Jwts.parser().setSigningKey(Constant.TOKEN_SECURE).parseClaimsJws(token).getBody();
            userid = claims.get("userid").toString();
        } catch (Exception e) {
            //无需处理该异常
        } finally {
            redisCache.del(Constant.TOKEN_KEY + userid);
            //清除cookie
            CookieUtil.delCookie(request, response, "token");
        }
        logger.info("用户" + userid + "退出登录成功！token:" + token);
    }
}
