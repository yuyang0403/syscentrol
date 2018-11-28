package com.yuyang.user.service;

import com.yuyang.common.user.vo.UserInfoVO;
import com.yuyang.user.model.SysUser;

/**
 * @author yuyang
 * @create 2018/6/14 14:06
 * @desc
 **/
public interface SysUserService {
    SysUser selectSysUserById(Long id);

    /**
     * 登录用
     *
     * @param user
     * @return
     */
    SysUser selectSysUserByNameAndPwd(SysUser user);

    /**
     * 查询用户的菜单
     *
     * @param userid
     * @return
     */
    String selectMenuListByUserId(Long userid);

    /**
     * 分页查询
     *
     * @param user
     * @return
     */
    String selectByPage(SysUser user);

    /**
     * 根据token获取用户信息
     * @param token
     * @return
     */
    UserInfoVO selectUserByToken(String token);
}
