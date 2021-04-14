package com.example.mvvmlibrary.lib_mvvm.net.exception;


//处理code码
public interface ErrorType {
    /**
     * 正常
     */
    int SUCCESS = 0;
    /**
     * 未知错误
     */
    int UNKNOWN = 1004;
    /**
     * 解析错误
     */
    int PARSE_ERROR = 1001;
    /**
     * 网络错误
     */
    int NETWORK_ERROR = 1002;
    /**
     * 网络未连接
     */
    int NET_WORK_UN_UNITED = 1005;
    /**
     * 协议出错
     */
    int HTTP_ERROR = 1003;

    /**
     * 未登录
     */
    int NOT_LOGIN = 10200;

    /**
     * 10202
     */
    int PHONE_NUM_NOT_PERFECT = 10202;

    int COMMENT_CONTAINT_NO_LEGAL = 1000;

    int No_JOINED_PLAN = 20301;

    /**
     * 未登录
     */
    int LOGIN_Dialog = 10207;
}

