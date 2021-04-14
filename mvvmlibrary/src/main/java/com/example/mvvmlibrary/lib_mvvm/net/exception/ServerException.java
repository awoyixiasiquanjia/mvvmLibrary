package com.example.mvvmlibrary.lib_mvvm.net.exception;


import androidx.annotation.Nullable;

/**
 *服务器返回的非200的异常码
 * **/
public class ServerException extends RuntimeException {
    public int code;
    public String message;

    @Nullable
    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ServerException(String message, int code) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
