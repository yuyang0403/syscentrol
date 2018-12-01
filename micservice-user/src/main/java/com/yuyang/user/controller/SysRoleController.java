package com.yuyang.user.controller;

import com.yuyang.common.util.ResponseResult;
import com.yuyang.user.model.SysRole;
import com.yuyang.user.model.SysUser;
import com.yuyang.user.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色Controller
 */
@Api("角色后台管理")
@RestController
@RequestMapping("role/manager")
@CrossOrigin(origins = "*")
public class SysRoleController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    SysRoleService sysRoleService;
    @ApiOperation(value="获取角色列表",notes="获取角色列表（不分页）",response = SysRole.class)
    @RequestMapping(value = "findRoleList",method = RequestMethod.POST)
    public ResponseResult<List<SysRole>> findRoleList() {
        ResponseResult<List<SysRole>> responseResult=new ResponseResult<>();
        try {
            responseResult.setData(sysRoleService.selectRolesList());
        } catch (Exception e) {
            responseResult.setErrorCode(500);
            responseResult.setErrorMessage(e.getMessage());
            e.printStackTrace();
        }
        return responseResult;
    }
}
