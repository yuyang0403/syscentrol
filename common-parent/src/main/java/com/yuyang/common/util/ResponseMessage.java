package com.yuyang.common.util;

/**
 * @author yuyang
 * @create 2018/6/21 17:01
 * @desc
 **/
public class ResponseMessage {
    private String error;
    private String message;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
