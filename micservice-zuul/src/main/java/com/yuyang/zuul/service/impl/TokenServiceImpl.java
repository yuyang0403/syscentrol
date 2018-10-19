package com.yuyang.zuul.service.impl;

import com.yuyang.common.cache.RedisCache;
import com.yuyang.common.constant.Constant;
import com.yuyang.zuul.service.TokenService;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yuyang
 * @create 2018/6/21 16:28
 * @desc
 **/
@Service("tokenService")
public class TokenServiceImpl implements TokenService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RedisCache redisCache;
    @Override
    public boolean checkToken(String token) {
        Claims claims = null;
        String userid = "";
        try {
            claims = Jwts.parser().setSigningKey(Constant.TOKEN_SECURE).parseClaimsJws(token).getBody();
            userid = claims.get("userid").toString();
        } catch (Exception e) {
            logger.error("token jwt验证失败！");
            return false;
        }
        //验证redis是否过期
        if (!redisCache.exists(Constant.TOKEN_KEY + userid)) {
            logger.info("token 服务器中已过期");
            return false;
        }
        if (!redisCache.get(Constant.TOKEN_KEY + userid).toString().equals(token)) {
            logger.info("token 不同客户端token:" + token + "--redis token:" + redisCache.get(Constant.TOKEN_KEY + userid).toString());
            return false;
        }
        //验证通过重新刷新缓存的key
        redisCache.set(Constant.TOKEN_KEY + userid, token);
        redisCache.expire(Constant.TOKEN_KEY + userid, 20 * 60);
        logger.info("token验证通过。" + token);
        return true;
    }
}
