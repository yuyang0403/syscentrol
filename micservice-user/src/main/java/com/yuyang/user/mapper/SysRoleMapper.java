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
}