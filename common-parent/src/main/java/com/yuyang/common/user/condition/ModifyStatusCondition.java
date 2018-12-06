package com.yuyang.common.user.condition;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yuyang
 * @create 2018/12/6 19:22
 * @desc
 **/
@Data
public class ModifyStatusCondition {
    @ApiModelProperty("要修改的用户ID")
    private Long userId;
    @ApiModelProperty("操作类型：delete")
    private String type;
}
