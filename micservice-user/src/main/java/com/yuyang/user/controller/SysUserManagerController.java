package com.yuyang.user.controller;

import com.yuyang.common.user.condition.CreateUserCondition;
import com.yuyang.common.util.ResponseResult;
import com.yuyang.user.model.SysUser;
import com.yuyang.user.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author yuyang
 * @create 2018/6/14 14:11
 * @desc 后台管理的controller
 **/
@Api("用户后台管理")
@RestController
@RequestMapping("user/manager")
@CrossOrigin(origins = "*")
public class SysUserManagerController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    SysUserService sysUserService;

    @ApiOperation(value="按照用户ID获取用户信息",notes="按照用户ID获取用户信息")
    @RequestMapping(value = "getUserById/{id}",method = RequestMethod.POST)
    public SysUser getUserById(@PathVariable("id") Long id) {
        return sysUserService.selectSysUserById(id);
    }

    @ApiOperation(value="按照token获取用户菜单",notes="按照用户ID获取用户信息")
    @RequestMapping(value = "findRouterList",method = RequestMethod.POST)
    public ResponseResult<String> getMenuList(@RequestHeader("token") String token) {
        ResponseResult<String> responseResult=new ResponseResult<>();
        try {
            responseResult.setData(sysUserService.findRouterList(token));
        } catch (Exception e) {
            responseResult.setErrorCode(500);
            responseResult.setErrorMessage(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return responseResult;
    }

    @ApiOperation(value="用户分页",notes="用户分页",response = String.class)
    @RequestMapping(value = "findUserList",method = RequestMethod.POST)
    public ResponseResult<String> getUserList(@ModelAttribute SysUser user) {
        ResponseResult<String> responseResult=new ResponseResult<>();
        try {
            responseResult.setData(sysUserService.selectByPage(user));
        } catch (Exception e) {
            responseResult.setErrorCode(500);
            responseResult.setErrorMessage(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return responseResult;
    }

    @ApiOperation(value="创建或修改用户",notes="创建或修改用户",response = String.class)
    @RequestMapping(value = "createOrUpdateUser",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseResult<String> createOrUpdateUser(@ModelAttribute CreateUserCondition condition,@RequestHeader("token") String token){
        ResponseResult<String> responseResult=new ResponseResult<>();
        try {
            sysUserService.createOrUpdateUser(condition,token);
        } catch (Exception e) {
            responseResult.setErrorCode(500);
            responseResult.setErrorMessage(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return responseResult;
    }
}
