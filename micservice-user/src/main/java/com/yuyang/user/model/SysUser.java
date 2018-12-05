package com.yuyang.user.model;

import com.yuyang.user.model.page.PageModel;
import lombok.Data;

import java.util.Date;
@Data
public class SysUser extends PageModel {
    private Long id;

    private String loginName;

    private String password;

    private String truename;

    private String phone;

    private Integer userType;

    private String email;

    private String nickName;

    private String weiboId;

    private Integer loginNum;

    private Date lastLoginTime;

    private Integer regFrom;

    private Integer status;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private String avatar;

    private String desc;

    private String remark;

}