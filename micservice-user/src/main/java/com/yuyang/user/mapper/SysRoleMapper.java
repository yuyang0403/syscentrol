package com.yuyang.user.mapper;

import com.yuyang.user.model.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    /**
     * 获取用户角色列表
     * @param userid
     * @return
     */
    List<SysRole> selectRolesByUserId(@Param("userid")Long userid);

    /**
     * 获取有效的角色列表
     * @return
     */
    List<SysRole> selectRolesList();

    /**
     * 按照code获取对象
     * @param code
     * @return
     */
    SysRole selectRoleByCode(@Param("code") String code);

    /**
     * 插入中间表
     * @param userId
     * @param roleId
     * @return
     */
    int insertUserRole(@Param("userId")Long userId,@Param("roleId") Long roleId);
}