package com.yuyang.user.mapper;

import com.yuyang.user.model.SysMenu;
import com.yuyang.user.model.SysUser;

import java.util.List;
import java.util.Map;

/**
 * @author yuyang
 */
public interface SysUserMapper {
    /**
     * 主键删除
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入
     *
     * @param record
     * @return
     */
    int insertSelective(SysUser record);

    /**
     * 主键查询
     *
     * @param id
     * @return
     */
    SysUser selectByPrimaryKey(Long id);

    /**
     * 登录查询
     *
     * @param record
     * @return
     */
    SysUser selectByNameAndPwd(SysUser record);

    /**
     * 更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(SysUser record);

    /**
     * 查询用户菜单
     *
     * @param userid
     * @return
     */
    List<SysMenu> selectMenuListByUserId(Long userid);

    /**
     * 条件查询
     *
     * @param user
     * @return
     */
    List<SysUser> selectByPage(SysUser user);
}