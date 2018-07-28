package com.yuyang.user.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.*;
import com.yuyang.user.mapper.SysUserMapper;
import com.yuyang.user.model.SysMenu;
import com.yuyang.user.model.SysUser;
import com.yuyang.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yuyang
 * @create 2018/6/14 14:08
 * @desc
 **/
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    @Autowired
    SysUserMapper sysUserMapper;
    @Override
    public SysUser selectSysUserById(Long id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }

    /**
     * 登录用
     * @param user
     * @return
     */
    @Override
    public SysUser selectSysUserByNameAndPwd(SysUser user) {
        return sysUserMapper.selectByNameAndPwd(user);
    }

    /**
     * 获取菜单
     * @param userid
     * @return
     */
    @Override
    public String selectMenuListByUserId(Long userid) {
        List<SysMenu> menuList=sysUserMapper.selectMenuListByUserId(userid);
        //最上级菜单
        JsonArray parentTree=new JsonArray();
        for (SysMenu menuEntity : menuList) {
            if(menuEntity.getParentId()==0) {
                JsonObject menuJson = gson.toJsonTree(menuEntity).getAsJsonObject();
                menuJson.addProperty("text", menuEntity.getTitle());
                menuJson.addProperty("iconCls", menuEntity.getIcon());
                menuJson.add("children",getChildren(menuList,menuEntity));
                parentTree.add(menuJson);
            }
        }
        return parentTree.toString();
    }

    /**
     * 递归获取菜单
     * @param list
     * @param current
     * @return
     */
    private JsonArray getChildren(List<SysMenu> list,SysMenu current){
        JsonArray childrenArray=new JsonArray();
        for (SysMenu menuEntity : list) {
            if(menuEntity.getParentId().longValue()==current.getId().longValue()) {
                JsonObject menuJson = gson.toJsonTree(menuEntity).getAsJsonObject();
                menuJson.addProperty("text", menuEntity.getTitle());
                menuJson.addProperty("iconCls", menuEntity.getIcon());
                menuJson.add("children", getChildren(list, menuEntity));
                childrenArray.add(menuJson);
            }
        }
        return childrenArray;
    }

    /**
     * 分页查询
     * @param user
     * @return
     */
    @Override
    public String selectByPage(SysUser user) {
        PageHelper.startPage(user.getPage(),user.getRows());
        List<SysUser> list=sysUserMapper.selectByPage(user);
        PageInfo pageInfo = new PageInfo(list);
        Page page = (Page) list;
        user.setPa(page);
        user.setPageInfo(pageInfo);
        return user.getPageResult();
    }
}
