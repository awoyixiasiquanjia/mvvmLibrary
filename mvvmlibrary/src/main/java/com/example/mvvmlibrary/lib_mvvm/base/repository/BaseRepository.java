package com.example.mvvmlibrary.lib_mvvm.base.repository;
import android.app.Application;

import com.example.mvvmlibrary.lib_mvvm.base.livedata.LiveBus;
import com.example.mvvmlibrary.lib_mvvm.base.livedata.SingleLiveEvent;
import com.example.mvvmlibrary.lib_mvvm.net.RetrofitBuilder;
import com.example.mvvmlibrary.lib_mvvm.net.Transformer.DefaultTransformer;
import com.example.mvvmlibrary.lib_mvvm.net.exception.ApiException;
import com.example.mvvmlibrary.lib_mvvm.net.model.HttpResult;
import com.example.mvvmlibrary.lib_mvvm.net.subscriber.RxSubscriber;
import com.example.mvvmlibrary.lib_mvvm.utils.ParamsBuilder;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public abstract class BaseRepository<I> implements IBaseRepository {
    protected I apiServer;
    protected Application mApplication;
    protected CompositeDisposable mCompositeDisposable;
    private UIChangeLiveData uiChangeLiveData;
    protected ParamsBuilder paramsBuilder;
    protected static BaseRepository baseRepository;
    public BaseRepository() {
        this.baseRepository = this;
        paramsBuilder = ParamsBuilder.build();
        if (apiServer == null && null != getServerClass()) {
            apiServer =
                RetrofitBuilder.getInstance(getBaseUrl()).build().create(getServerClass());
        }
    }


    public BaseRepository(Application application) {
        this.mApplication = application;
        this.baseRepository = this;
        paramsBuilder = ParamsBuilder.build();

        mCompositeDisposable = new CompositeDisposable();

        if (apiServer == null && null != getServerClass()) {
            apiServer =
                RetrofitBuilder.getInstance(getBaseUrl()).build().create(getServerClass());
        }

    }

    public static BaseRepository getBaseRepository() {
        return baseRepository;
    }


    /**
     * 页面状态改变的livedata
     */
    public UIChangeLiveData getUiChangeLiveData() {

        if (null == uiChangeLiveData) {

            uiChangeLiveData = new UIChangeLiveData();

        }
        return uiChangeLiveData;
    }


    /**
     * 获取请求统一参数
     */
    public ParamsBuilder getParamsBuilder() {
        paramsBuilder = ParamsBuilder.build();
        return paramsBuilder;
    }


    /**
     * 管理RxJava，主要针对RxJava异步操作造成的内存泄漏
     */
    public <T> void addDisposable(Observable<HttpResult<T>> flowable, final ParamsBuilder paramsBuilder) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        RxSubscriber<T> rxSubscriber = new RxSubscriber<T>(this, paramsBuilder) {
            @Override public void onDataNullSuccess(Throwable ex) {
                postData(paramsBuilder.getData_event_key(), paramsBuilder.ON_DATA_NULL_SUCCESS,
                    new ApiException(ex));
            }

            @Override protected void onDataSuccess(T t) {

                postData(paramsBuilder.getData_event_key(), t);

            }


            @Override protected void onDataError(ApiException ex) {
                super.onDataError(ex);
                if (null != mCompositeDisposable && !mCompositeDisposable.isDisposed()) {
                    postData(paramsBuilder.getData_event_key(), paramsBuilder.ON_DATA_ERROR, ex);
                }

            }
        };

       flowable.compose(new DefaultTransformer<>()).subscribe(rxSubscriber);

        mCompositeDisposable.add(rxSubscriber);

    }


    /**
     * 创建单一LiveEvent
     */
    protected <T> SingleLiveEvent<T> createLiveData(SingleLiveEvent<T> liveData) {
        if (liveData == null) {
            liveData = new SingleLiveEvent<>();
        }
        return liveData;
    }


    /**
     * 当viewmodel销毁 清除所有rxjava异步请求
     */
    @Override public void onCleared() {

        try {
            if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
                mCompositeDisposable.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取server类
     */
    protected abstract Class<I> getServerClass();


    /**
     * 获取统一url
     */
    protected String getBaseUrl() {
        return "http://10.39.35.11:8071/";
    }

    public final class UIChangeLiveData extends SingleLiveEvent {

        private SingleLiveEvent<String> showErrorEvent;

        private SingleLiveEvent<Void> showNetErrorEvent;

        private SingleLiveEvent<String> showSocketTimeOutEvent;

        private SingleLiveEvent<Boolean> showLoadingEvent;

        private SingleLiveEvent<Void> endRefreshingEvent;

        private SingleLiveEvent<Void> endLoadMoreEvent;

        private SingleLiveEvent<ParamsBuilder> showDialogLoadingEvent;


        public SingleLiveEvent<ParamsBuilder> getShowDialogLoadingEvent() {
            return showDialogLoadingEvent = createLiveData(showDialogLoadingEvent);
        }


        public SingleLiveEvent<String> getShowErrorEvent() {
            return showErrorEvent = createLiveData(showErrorEvent);
        }


        public SingleLiveEvent<Boolean> getShowLoadingEvent() {
            return showLoadingEvent = createLiveData(showLoadingEvent);
        }


        public SingleLiveEvent getEndRefreshingEvent() {
            return endRefreshingEvent = createLiveData(endRefreshingEvent);
        }


        public SingleLiveEvent<Void> getEndLoadMoreEvent() {
            return endLoadMoreEvent = createLiveData(endLoadMoreEvent);
        }


        public SingleLiveEvent<Void> getShowNetErrorEvent() {
            return showNetErrorEvent = createLiveData(showNetErrorEvent);
        }


        public SingleLiveEvent<String> getShowSocketTimeOutEvent() {
            return showSocketTimeOutEvent = createLiveData(showSocketTimeOutEvent);
        }
    }


    public void postShowErrorEvent(String errpr_msg) {

        if (null != uiChangeLiveData) {

            uiChangeLiveData.getShowErrorEvent().postValue(errpr_msg);
        }
    }


    public void postShowNetErrorEvent() {
        if (null != uiChangeLiveData) {
            uiChangeLiveData.getShowNetErrorEvent().call();
        }
    }


    public void postShowShoketTimeOutEvent(String error_msg) {

        if (null != uiChangeLiveData) {
            uiChangeLiveData.getShowSocketTimeOutEvent().postValue(error_msg);
        }
    }


    public void postShowLoadingEvent(Boolean isShowLoading) {

        if (uiChangeLiveData != null) {

            uiChangeLiveData.getShowLoadingEvent().postValue(isShowLoading);
        }
    }


    public void postShowDialogLoadingEvent(ParamsBuilder paramsBuilder) {

        if (uiChangeLiveData != null) {

            uiChangeLiveData.getShowDialogLoadingEvent().postValue(paramsBuilder);
        }
    }


    public void postEndRefreshingEvent() {

        if (null != uiChangeLiveData) {

            uiChangeLiveData.getEndRefreshingEvent().call();
        }
    }


    public void postEndLoadMoreEvent() {
        if (null != uiChangeLiveData) {

            uiChangeLiveData.getEndLoadMoreEvent().call();
        }
    }


    /**
     * 发送数据 事件
     */
    protected <T> void postData(String eventKey, T t) {
        postData(eventKey, null, t);
    }


    protected <T> void postData(String eventKey, String tag, T t) {
          LiveBus.getDefault().get(eventKey, tag).post(t);
    }

}
