package com.yuyang.common.exception;

/**
 * @author yuyang
 * @create 2018/12/6 19:29
 * @desc
 **/
public class ParentException extends RuntimeException{
    private int errorCode;
    public ParentException() {
    }

    public ParentException(int errorCode,String message) {
        super(message);
        this.errorCode=errorCode;
    }

    public ParentException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParentException(Throwable cause) {
        super(cause);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
