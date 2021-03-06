package com.yuyang.user.service;

import com.yuyang.common.user.condition.CreateUserCondition;
import com.yuyang.common.user.condition.ModifyStatusCondition;
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
     * @param token
     * @return
     */
    String findRouterList(String token);

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

    /**
     * 创建用户
     * @param condition
     */
    void createOrUpdateUser(CreateUserCondition condition,String token);

    /**
     * 更新用户状态
     */
    void updateUserStatus(ModifyStatusCondition condition,String token);
}
