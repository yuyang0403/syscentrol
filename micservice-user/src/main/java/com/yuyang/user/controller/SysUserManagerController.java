package com.yuyang.user.controller;

import com.yuyang.user.model.SysUser;
import com.yuyang.user.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yuyang
 * @create 2018/6/14 14:11
 * @desc 后台管理的controller
 **/
@RestController
@RequestMapping("user/manager")
public class SysUserManagerController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    SysUserService sysUserService;

    @RequestMapping(value = "getUserById/{id}")
    public SysUser getUserById(@PathVariable("id") Long id) {
        return sysUserService.selectSysUserById(id);
    }

    /**
     * 获取菜单tree
     *
     * @return
     */
    @RequestMapping(value = "getMenuList/{userid}")
    @CrossOrigin(origins = "*")
    public String getMenuList(@PathVariable("userid") Long userid) {
        String result = null;
        try {
            result = sysUserService.selectMenuListByUserId(userid);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 用户分页
     *
     * @return
     */
    @RequestMapping(value = "getUserList")
    @CrossOrigin(origins = "*")
    public String getUserList(SysUser user) {
        String result = null;
        try {
            result = sysUserService.selectByPage(user);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }
}
