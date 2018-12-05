package com.yuyang.common.user.condition;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author yuyang
 * @create 2018/12/3 19:14
 * @desc
 **/
@Data
public class CreateUserCondition {
    @ApiModelProperty("ID")
    private Long id;
    @ApiModelProperty("状态")
    private Integer status;
    @ApiModelProperty("登录名")
    private String loginName;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("真实姓名")
    private String truename;
    @ApiModelProperty("电话")
    private String phone;
    @ApiModelProperty("邮件")
    private String email;
    @ApiModelProperty("头像")
    private String avatar;
    @ApiModelProperty("描述")
    private String desc;
    @ApiModelProperty("角色code")
    private List<String> roleList;
}
