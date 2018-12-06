package com.yuyang.common.exception.user;

import com.yuyang.common.exception.ParentException;

/**
 * @author yuyang
 * @create 2018/12/6 19:27
 * @desc
 **/
public class SysManagerException extends ParentException {
    public SysManagerException() {
    }

    public SysManagerException(int errorCode,String message) {
        super(errorCode,message);
    }

    public SysManagerException(String message, Throwable cause) {
        super(message, cause);
    }

    public SysManagerException(Throwable cause) {
        super(cause);
    }
}
