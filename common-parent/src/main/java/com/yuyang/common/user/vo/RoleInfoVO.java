package com.yuyang.common.user.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yuyang
 * @create 2018/11/27 17:28
 * @desc
 **/
@Data
public class RoleInfoVO {
    @ApiModelProperty("角色code")
    private String code;
    @ApiModelProperty("角色名称")
    private String name;
}
