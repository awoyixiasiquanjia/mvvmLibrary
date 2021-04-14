package com.example.mvvmlibrary.lib_mvvm.net.model;
import java.io.Serializable;
public class HttpResult<T> implements Serializable {
    /**
     * 返回状态码
     */
    private int code;

    private T data;
    /**
     * 信息
     */
    private String message;


    public int getCode() {
        return code;
    }


    public void setCode(int code) {
        this.code = code;
    }


    public String getMsg() {
        return message;
    }


    public void setMsg(String msg) {
        this.message = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
