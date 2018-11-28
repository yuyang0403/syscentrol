package com.yuyang.user.model;

import lombok.Data;

import java.util.Date;
@Data
public class SysMenu {
    private Long id;

    private Long parentId;

    private String name;

    private String title;

    private String path;

    private String component;

    private String icon;

    private Integer orderIndex;

    private String description;

    private Integer status;

    private Date createdTime;

    private Date updateTime;

    private String createdBy;

    private String updateBy;

    private String remark;

}