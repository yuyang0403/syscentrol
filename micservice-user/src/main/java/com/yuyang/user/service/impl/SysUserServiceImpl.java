package com.yuyang.user.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.*;
import com.yuyang.common.cache.RedisCache;
import com.yuyang.common.exception.user.SysManagerException;
import com.yuyang.common.user.condition.CreateUserCondition;
import com.yuyang.common.user.condition.ModifyStatusCondition;
import com.yuyang.common.user.vo.RoleInfoVO;
import com.yuyang.common.user.vo.UserInfoVO;
import com.yuyang.user.mapper.SysRoleMapper;
import com.yuyang.user.mapper.SysUserMapper;
import com.yuyang.user.model.SysMenu;
import com.yuyang.user.model.SysRole;
import com.yuyang.user.model.SysUser;
import com.yuyang.user.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author yuyang
 * @create 2018/6/14 14:08
 * @desc
 **/
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
    Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    SysRoleMapper sysRoleMapper;
    @Resource
    RedisCache redisCache;

    @Override
    public SysUser selectSysUserById(Long id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }

    /**
     * 登录用
     *
     * @param user
     * @return
     */
    @Override
    public SysUser selectSysUserByNameAndPwd(SysUser user) {
        SysUser sysUser=sysUserMapper.selectByNameAndPwd(user);
        if(sysUser!=null){
            //登录成功，更新登录次数，最后一次登录时间
            sysUser.setLastLoginTime(new Date());
            sysUser.setLoginNum(sysUser.getLoginNum()==null?0:sysUser.getLoginNum()+1);
            sysUserMapper.updateByPrimaryKeySelective(sysUser);
        }
        return sysUser;
    }

    /**
     * 获取菜单
     *
     * @param token
     * @return
     */
    @Override
    public String findRouterList(String token) {
        Long userid=null;
        if(redisCache.exists(token)){
            userid=Long.parseLong(redisCache.get(token));
        }
        List<SysMenu> menuList = sysUserMapper.selectMenuListByUserId(userid);
        //最上级菜单
        JsonArray parentTree = new JsonArray();
        for (SysMenu menuEntity : menuList) {
            if (menuEntity.getParentId() == 0) {
                JsonObject menuJson = gson.toJsonTree(menuEntity).getAsJsonObject();
                menuJson.addProperty("path", menuEntity.getPath());
                menuJson.addProperty("component", menuEntity.getComponent());
                menuJson.addProperty("name", menuEntity.getName());
                menuJson.addProperty("title", menuEntity.getTitle());
                menuJson.addProperty("icon", menuEntity.getIcon());
                menuJson.add("children", getChildren(menuList, menuEntity));
                parentTree.add(menuJson);
            }
        }
        return parentTree.toString();
    }

    /**
     * 递归获取菜单
     *
     * @param list
     * @param current
     * @return
     */
    private JsonArray getChildren(List<SysMenu> list, SysMenu current) {
        JsonArray childrenArray = new JsonArray();
        for (SysMenu menuEntity : list) {
            if (menuEntity.getParentId().longValue() == current.getId().longValue()) {
                JsonObject menuJson = gson.toJsonTree(menuEntity).getAsJsonObject();
                menuJson.addProperty("path", menuEntity.getPath());
                menuJson.addProperty("component", menuEntity.getComponent());
                menuJson.addProperty("name", menuEntity.getName());
                menuJson.addProperty("title", menuEntity.getTitle());
                menuJson.addProperty("icon", menuEntity.getIcon());
                menuJson.add("children", getChildren(list, menuEntity));
                childrenArray.add(menuJson);
            }
        }
        return childrenArray;
    }

    /**
     * 分页查询
     *
     * @param user
     * @return
     */
    @Override
    public String selectByPage(SysUser user) {
        PageHelper.startPage(user.getPage(), user.getRows());
        List<SysUser> list = sysUserMapper.selectByPage(user);
        PageInfo pageInfo = new PageInfo(list);
        Page page = (Page) list;
        user.setPa(page);
        user.setPageInfo(pageInfo);
        return user.getPageResult();
    }

    /**
     * 根据token获取用户信息
     * @param token
     * @return
     */
    @Override
    public UserInfoVO selectUserByToken(String token) {
        UserInfoVO vo=null;
        if(redisCache.exists(token)){
            Long userid=Long.parseLong(redisCache.get(token));
            vo=new UserInfoVO();
            SysUser sysUser=sysUserMapper.selectByPrimaryKey(userid);
            vo.setLoginName(sysUser.getLoginName());
            vo.setTrueName(sysUser.getTruename());
            vo.setAvatar(sysUser.getAvatar());
            vo.setDesc(sysUser.getDesc());
            vo.setToken(token);
            //放入角色信息
            List<SysRole> sysRoles=sysRoleMapper.selectRolesByUserId(userid);
            if(!CollectionUtils.isEmpty(sysRoles)){
                for (SysRole sysRole : sysRoles) {
                    RoleInfoVO roleInfoVO=new RoleInfoVO();
                    roleInfoVO.setCode(sysRole.getCode());
                    roleInfoVO.setName(sysRole.getName());
                    vo.getRoleList().add(roleInfoVO);
                }
            }
            //放入该用户拥有的所有菜单
            List<SysMenu> menuList = sysUserMapper.selectMenuListByUserId(userid);
            if(!CollectionUtils.isEmpty(menuList)){
                for (SysMenu sysMenu : menuList) {
                    vo.getRouters().add(sysMenu.getName());
                }
            }
        }
        return vo;
    }

    /**
     * 创建用户
     * @param condition
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createOrUpdateUser(CreateUserCondition condition,String token) {
        UserInfoVO userInfoVO=selectUserByToken(token);
        //数据校验 省略
        SysUser sysUser=new SysUser();
        sysUser.setAvatar(condition.getAvatar());
        sysUser.setDesc(condition.getDesc());
        sysUser.setEmail(condition.getEmail());
        sysUser.setLoginName(condition.getLoginName());
        sysUser.setNickName(condition.getNickName());
        sysUser.setPassword(StringUtils.isBlank(condition.getPassword())?condition.getLoginName():condition.getPassword());
        sysUser.setPhone(condition.getPhone());
        sysUser.setStatus(condition.getStatus());
        sysUser.setTruename(condition.getTruename());

        if(condition.getId()!=null){
            //修改
            sysUser.setUpdateBy(userInfoVO.getLoginName());
            sysUser.setUpdateTime(new Date());
            sysUser.setId(condition.getId());
            sysUserMapper.updateByPrimaryKeySelective(sysUser);
            //删除中间表数据
            sysRoleMapper.deleleUserRole(condition.getId());
        }else {
            sysUser.setCreateBy(userInfoVO.getLoginName());
            sysUser.setCreateTime(new Date());
            sysUser.setUserType(1);
            sysUser.setLoginNum(0);
            sysUserMapper.insertSelective(sysUser);
        }
        for (String code : condition.getRoleList()) {
            SysRole role = sysRoleMapper.selectRoleByCode(code);
            //插入中间表
            sysRoleMapper.insertUserRole(sysUser.getId(), role.getId());
        }
    }

    /**
     * 更新状态
     * @param condition
     * @param token
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateUserStatus(ModifyStatusCondition condition, String token) {
        if(condition.getUserId()==null){
            throw new SysManagerException(100000,"参数有误");
        }
        if(StringUtils.isEmpty(condition.getType())){
            throw new SysManagerException(100000,"参数有误");
        }
        //获取该用户信息
        SysUser sysUser=sysUserMapper.selectByPrimaryKey(condition.getUserId());
        if(sysUser==null){
            throw new SysManagerException(100000,"未找到该用户："+condition.getUserId());
        }
        LOGGER.info("开始更新用户："+condition.getUserId()+" 状态，操作类型："+condition.getType());
        sysUser.setUpdateTime(new Date());
        //获取操作人
        sysUser.setUpdateBy(this.selectUserByToken(token).getLoginName());
        switch(condition.getType()){
            case "delete":
                //删除
                if(sysUser.getUserType()==0){
                    throw new SysManagerException(100000,"不可删除用户");
                }
                sysUser.setStatus(2);

                break;
            default:
                break;
        }
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }
}
