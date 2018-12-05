package com.yuyang.user.service;

import com.yuyang.user.model.SysRole;

import java.util.List;

/**
 * 角色
 */
public interface SysRoleService {
    /**
     * 获取有效的角色列表
     * @return
     */
    List<SysRole> selectRolesList();

    /**
     * 获取某个用户的所有角色信息
     * @param userId
     * @return
     */
    List<SysRole> selectRoleListByUserId(Long userId);
}
