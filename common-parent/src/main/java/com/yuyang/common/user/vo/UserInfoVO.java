package com.yuyang.common.user.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuyang
 * @create 2018/11/27 17:24
 * @desc
 **/
@Data
public class UserInfoVO {
    @ApiModelProperty("登录名")
    private String loginName;
    @ApiModelProperty("真实姓名")
    private String trueName;
    @ApiModelProperty("token")
    private String token;
    @ApiModelProperty("用户头像")
    private String  avatar;
    @ApiModelProperty("用户描述")
    private String desc;
    @ApiModelProperty("用户角色集合")
    private List<RoleInfoVO> roleList=new ArrayList<>();
    @ApiModelProperty("该用户所有路由")
    private List<String> routers=new ArrayList<>();
}
