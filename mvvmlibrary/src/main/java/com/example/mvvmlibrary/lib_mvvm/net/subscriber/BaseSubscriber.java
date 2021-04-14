package com.example.mvvmlibrary.lib_mvvm.net.subscriber;
import com.example.mvvmlibrary.lib_mvvm.net.exception.ApiException;
import com.example.mvvmlibrary.lib_mvvm.net.exception.NullDataException;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
public abstract class BaseSubscriber<T> implements Observer<T> {

    @Override public void onSubscribe(@NonNull Disposable d) {

    }


    @Override public void onNext(@NonNull T t) {

        onDataSuccess(t);
    }


    /**
     * 接口请求成功  但是数据是空的
     */
    public void onDataNullSuccess(Throwable ex) {

    }


    /**
     * 成功返回数据
     */
    protected abstract void onDataSuccess(T t);


    protected void onDataError(ApiException ex) {

    }


    @Override public void onComplete() {

    }


    @Override public void onError(@NonNull Throwable e) {

        if (e instanceof NullDataException) {
            onDataNullSuccess(e);
        } else if (e instanceof ApiException) {

            onDataError((ApiException) e);
        } else {
            onDataError(new ApiException(e));
        }
    }

}
