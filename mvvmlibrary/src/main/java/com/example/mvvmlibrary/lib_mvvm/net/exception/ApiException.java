package com.example.mvvmlibrary.lib_mvvm.net.exception;
public class ApiException extends Exception {
    // 异常处理，为速度，不必要设置getter和setter
    public int code;
    public String message;
    public String alert;
    public ApiException(Throwable throwable) {
        super(throwable);
    }


    public ApiException(Throwable throwable, int code, int alert_type) {
        super(throwable);
        this.code = code;
    }

    public ApiException(Throwable throwable, int code, String message) {
        super(throwable);
        this.code = code;
        this.message = message;
    }
}
