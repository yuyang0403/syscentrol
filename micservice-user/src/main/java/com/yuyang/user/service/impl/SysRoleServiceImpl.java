package com.yuyang.user.service.impl;

import com.yuyang.user.mapper.SysRoleMapper;
import com.yuyang.user.model.SysRole;
import com.yuyang.user.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    SysRoleMapper sysRoleMapper;
    @Override
    public List<SysRole> selectRolesList() {
        return sysRoleMapper.selectRolesList();
    }

    /**
     * 获取某个用户的角色列表
     * @param userId
     * @return
     */
    @Override
    public List<SysRole> selectRoleListByUserId(Long userId) {
        return sysRoleMapper.selectRolesByUserId(userId);
    }
}
