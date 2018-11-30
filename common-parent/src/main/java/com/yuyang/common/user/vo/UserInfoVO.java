package com.yuyang.common.user.vo;

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
    private String loginName;
    private String trueName;
    private String token;
    private String  avatar;
    private String desc;
    private List<RoleInfoVO> roleList=new ArrayList<>();
    private List<String> routers=new ArrayList<>();
}
