package com.yuyang.zuul.service;

/**
 * @author yuyang
 * @create 2018/6/21 16:27
 * @desc
 **/
public interface TokenService {
    /**
     * 验证当前token是否有效
     * @param token
     * @return
     */
    boolean checkToken(String token);
}
