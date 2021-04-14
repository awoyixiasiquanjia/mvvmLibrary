package com.example.mvvmlibrary.lib_mvvm.net.subscriber;
import android.text.TextUtils;
import android.util.Log;
import com.example.mvvmlibrary.lib_mvvm.base.repository.BaseRepository;
import com.example.mvvmlibrary.lib_mvvm.net.exception.ApiException;
import com.example.mvvmlibrary.lib_mvvm.net.exception.ErrorType;
import com.example.mvvmlibrary.lib_mvvm.net.exception.NullDataException;
import com.example.mvvmlibrary.lib_mvvm.utils.ParamsBuilder;
import io.reactivex.rxjava3.observers.DisposableObserver;

public abstract class RxSubscriber<T> extends DisposableObserver<T> {

    private BaseRepository iBaseRepository;

    private ParamsBuilder paramsBuilder;


    public RxSubscriber(BaseRepository iBaseRepository, ParamsBuilder paramsBuilder) {
        this.iBaseRepository = iBaseRepository;
        this.paramsBuilder = paramsBuilder;
    }


    public RxSubscriber() {

    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i("RxSubscriber","开始请求数据");
        onDataStart();
    }


    @Override
    public void onComplete() {
        Log.i("RxSubscriber","数据请求成功");
    }


    @Override
    public void onError(Throwable e) {
        if (e instanceof NullDataException) {
            onDataComplete();
            onDataNullSuccess(e);
        } else if (e instanceof ApiException) {
            onDataError((ApiException) e);
        } else {
            onDataError(new ApiException(e));
        }
    }


    /**
     * 数据完成-空数据,正常数据,请求错误（某些页面，如果用不了showLoading,结束时重写onDataComplete,使用hideLoadingDialog）
     */
    protected void onDataComplete() {
        Log.i("RxSubscriber","onDataComplete");
        if (null != iBaseRepository && null != paramsBuilder) {

            switch (paramsBuilder.getRequestType()) {

                case ParamsBuilder.REQUEST_TYPE_LOAD_MORE:

                    iBaseRepository.postEndLoadMoreEvent();
                    break;

                case ParamsBuilder.REQUEST_TYPE_REFRESH:
                    iBaseRepository.postEndRefreshingEvent();
                    break;

                case ParamsBuilder.REQUEST_TYPE_NORMAL_LOAD:
                    iBaseRepository.postShowLoadingEvent(false);
                    break;
                case ParamsBuilder.REQUEST_TYPE_NONE:
                    break;

                case ParamsBuilder.REQUEST_TYPE_LOADING_DIALOG:

                    iBaseRepository.postShowDialogLoadingEvent(
                        paramsBuilder.setShowLoading(false).setError(false));
                    break;
            }
        }
    }


    /**
     * 接口请求成功  但是数据是空的
     */
    public abstract void onDataNullSuccess(Throwable ex);

    /**
     * 成功返回数据
     */
    protected abstract void onDataSuccess(T t);


    /**
     * 接口请求错误
     */
    protected void onDataError(ApiException ex) {
        Log.i("RxSubscriber","请求出错");
        //可以容易处理错误码
     //   APIErrorHandling.getInstance().handling(ex);
        onDataComplete();
        if (null != iBaseRepository && null != paramsBuilder) {
            switch (paramsBuilder.getRequestType()) {
                case ParamsBuilder.REQUEST_TYPE_LOAD_MORE:
                case ParamsBuilder.REQUEST_TYPE_REFRESH:

                    break;
                case ParamsBuilder.REQUEST_TYPE_NONE:
                case ParamsBuilder.REQUEST_TYPE_NORMAL_LOAD:
                    switch (ex.code) {
                        case ErrorType.NET_WORK_UN_UNITED:
                            iBaseRepository.postShowNetErrorEvent();
                            break;
                        case ErrorType.NETWORK_ERROR:
                            if (!TextUtils.isEmpty(ex.message)) {
                                iBaseRepository.postShowShoketTimeOutEvent(ex.message);
                            }

                            break;
                        default:
                            if (!TextUtils.isEmpty(ex.message)) {
                                iBaseRepository.postShowErrorEvent(ex.message);
                            }
                            break;
                    }

                    break;

                case ParamsBuilder.REQUEST_TYPE_LOADING_DIALOG:

                    iBaseRepository.postShowDialogLoadingEvent(paramsBuilder.setShowLoading(false)
                        .setErrorLoadingMsg(ex.message)
                        .setError(true));

                    break;
            }
        }
    }


    @Override
    public void onNext(T t) {
        onDataSuccess(t);
        onDataComplete();
    }


    /**
     * 开始网络请求
     */
    protected void onDataStart() {

        if (null != iBaseRepository && null != paramsBuilder) {

            switch (paramsBuilder.getRequestType()) {

                case ParamsBuilder.REQUEST_TYPE_NONE:
                case ParamsBuilder.REQUEST_TYPE_LOAD_MORE:
                case ParamsBuilder.REQUEST_TYPE_REFRESH:
                    iBaseRepository.postShowLoadingEvent(false);
                    break;
                case ParamsBuilder.REQUEST_TYPE_NORMAL_LOAD:

                    iBaseRepository.postShowLoadingEvent(true);

                    break;

                case ParamsBuilder.REQUEST_TYPE_LOADING_DIALOG:

                    iBaseRepository.postShowDialogLoadingEvent(paramsBuilder.setShowLoading(true));

                    break;

            }
        }

    }
}
