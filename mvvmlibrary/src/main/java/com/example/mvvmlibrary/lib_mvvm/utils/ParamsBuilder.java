package com.example.mvvmlibrary.lib_mvvm.utils;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * 获取请求统一参数,管理统一状态
 */
public class ParamsBuilder {
    private static final int EVENT_BEGIN = 0X100;

    /**
     * 界面什么都不显示的状态
     */
    public final static int REQUEST_TYPE_NONE = 0;

    /**
     * 刷新状态
     */
    public final static int REQUEST_TYPE_REFRESH = EVENT_BEGIN + 10;
    /**
     * 加载更多状态
     */
    public final static int REQUEST_TYPE_LOAD_MORE = EVENT_BEGIN + 20;

    /**
     * 正常加载状态
     */
    public final static int REQUEST_TYPE_NORMAL_LOAD = EVENT_BEGIN + 30;

    /**
     * 弹窗加载框
     */

    public final static int REQUEST_TYPE_LOADING_DIALOG = EVENT_BEGIN + 40;
    /**
     * 接口请求正确 但是返回空数据
     */
    public final static String ON_DATA_NULL_SUCCESS = "on_data_null_success";
    /**
     * 接口请求出错
     */
    public final static String ON_DATA_ERROR = "on_data_error";


    @IntDef({
                REQUEST_TYPE_NORMAL_LOAD, REQUEST_TYPE_REFRESH,
                REQUEST_TYPE_LOAD_MORE, REQUEST_TYPE_NONE, REQUEST_TYPE_LOADING_DIALOG
            })
    @Retention(RetentionPolicy.SOURCE)
    public @interface RequestType {
    }


    //加载状态
    private @RequestType int requestType;

    //返回数据事件 key
    private String data_event_key;

    //是否显示加载loading
    private boolean isShowLoading = false;

    //加载loading 文案
    private String loadingMsg;

    //完成loading文案
    private String endLoadingMsg;

    private String errorLoadingMsg;

    private boolean isError;


    public boolean isError() {
        return isError;
    }


    public ParamsBuilder setError(boolean error) {
        isError = error;
        return this;
    }


    public String getErrorLoadingMsg() {
        return errorLoadingMsg;
    }


    public ParamsBuilder setErrorLoadingMsg(String errorLoadingMsg) {
        this.errorLoadingMsg = errorLoadingMsg;

        return this;
    }


    public String getLoadingMsg() {
        return loadingMsg;
    }


    public ParamsBuilder setLoadingMsg(String loadingMsg) {
        this.loadingMsg = loadingMsg;
        return this;
    }


    public String getEndLoadingMsg() {
        return endLoadingMsg;
    }


    public ParamsBuilder setEndLoadingMsg(String endLoadingMsg) {
        this.endLoadingMsg = endLoadingMsg;

        return this;
    }


    public boolean isShowLoading() {
        return isShowLoading;
    }


    public ParamsBuilder setShowLoading(boolean showLoading) {
        isShowLoading = showLoading;
        return this;
    }


    public int getRequestType() {
        return requestType;
    }


    public ParamsBuilder setRequestType(int requestType) {
        this.requestType = requestType;

        return this;
    }


    public String getData_event_key() {
        return data_event_key;
    }


    public ParamsBuilder setData_event_key(String data_event_key) {
        this.data_event_key = data_event_key;

        return this;
    }


    public static ParamsBuilder build() {
        return new ParamsBuilder();
    }
}
