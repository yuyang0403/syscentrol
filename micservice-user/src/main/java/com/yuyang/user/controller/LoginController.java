package com.yuyang.user.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.yuyang.common.cache.RedisCache;
import com.yuyang.common.constant.Constant;
import com.yuyang.common.user.vo.UserInfoVO;
import com.yuyang.common.util.CookieUtil;
import com.yuyang.user.model.SysUser;
import com.yuyang.user.service.SysUserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Api(tags = "后台登录接口")
@CrossOrigin(origins = "*")
public class LoginController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    SysUserService sysUserService;
    @Resource
    RedisCache redisCache;
    @ApiOperation(value = "登录", response = String.class, notes = "登录")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(SysUser user, HttpServletResponse response) {
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
        redisCache.set(jwtToken,userid);
        redisCache.expire(jwtToken, 20 * 60);
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
    @ApiOperation(value = "退出登录", response = String.class, notes = "退出登录")
    @PostMapping("login/logout")
    public void logout(@RequestParam(value="token",required = false) String token, HttpServletRequest request, HttpServletResponse response) {
        //退出登录不需要验证token
        UserInfoVO userInfoVO=null;
        try {
            if(StringUtils.isNotBlank(token)) {
                redisCache.del(token);
                //获取用户信息
                userInfoVO=sysUserService.selectUserByToken(token);
            }
        } catch (Exception e) {
            //无需处理该异常
            logger.error("退出登录发生异常",e);
        }
        logger.info("用户退出登录成功！token:" + token+"用户信息："+new Gson().toJson(userInfoVO));
    }
    @ApiOperation(value = "根据token获取用户信息", response = String.class, notes = "根据token获取用户信息")
    @GetMapping("info")
    public UserInfoVO selectUserByToken(@RequestParam("token") String token){
        return sysUserService.selectUserByToken(token);
    }
}
