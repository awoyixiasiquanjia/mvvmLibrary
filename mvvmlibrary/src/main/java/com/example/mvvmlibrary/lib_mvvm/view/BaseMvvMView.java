package com.example.mvvmlibrary.lib_mvvm.view;


/**
 * Created by huangyirui on 2018/4/12.
 */
public interface BaseMvvMView {
    
    /**
     * 网络请求结束
     */
    void onNetComplete();
    
    /**
     * 网络故障
     */
    void onNetError();
    
    /**
     * 无数据
     */
    void onNoData(String noDataMsg);
    
    /**
     * 加载中
     */
    void onLoading();
    
    /**
     *
     */
    void onShowError(String errorMsg);
    
    /**
     *
     */
    void endLoadMore();
}
