package com.yuyang.common.util;

import lombok.Data;

/**
 * @author yuyang
 * @create 2018/11/30 14:06
 * @desc
 **/
@Data
public class ResponseResult<T> {
    private int errorCode=0;
    /**
     * 错误信息
     */
    private String errorMessage="";
    /**
     * 返回数据
     */
    private T data;
}
