package com.example.mvvmlibrary.lib_mvvm.net.Transformer;
import android.text.TextUtils;

import com.example.mvvmlibrary.lib_mvvm.net.exception.ExceptionEngine;
import com.example.mvvmlibrary.lib_mvvm.net.exception.NullDataException;
import com.example.mvvmlibrary.lib_mvvm.net.exception.ServerException;
import com.example.mvvmlibrary.lib_mvvm.net.model.HttpResult;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
public class DefaultTransformer<T> implements ObservableTransformer<HttpResult<T>, T> {

    public static <T> DefaultTransformer<T> create() {
        return new DefaultTransformer<T>();
    }


    private static DefaultTransformer instance = null;


    public <T> DefaultTransformer() {
    }


    /**
     * 双重校验锁单例(线程安全)
     */
    public static <T> DefaultTransformer<T> getInstance() {
        if (instance == null) {
            synchronized (DefaultTransformer.class) {
                if (instance == null) {
                    instance = new DefaultTransformer<T>();
                }
            }
        }
        return instance;
    }





    @Override
    public @NonNull ObservableSource<T> apply(@NonNull Observable<HttpResult<T>> upstream) {
        return upstream
            .subscribeOn(Schedulers.computation())
            .unsubscribeOn(Schedulers.computation())
            .map(new Function<HttpResult<T>, T>() {
                private String trace_id = "";
                @Override
                public T apply(HttpResult<T> httpResult) throws Exception {

                    if (httpResult.getCode() == 200) {
                        if (null != httpResult.getData()) {
                            return httpResult.getData();
                        } else {
                            throw new NullDataException();
                        }
                    } else {
                        throw new ServerException(httpResult.getMsg(), httpResult.getCode());
                    }

                }
            })
            .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends T>>() {
                @Override public ObservableSource<? extends T> apply(@NonNull Throwable throwable)
                    throws Throwable {
                    //在控制台输入异常信息，帮助开发者定位
                    throwable.printStackTrace();
                    //ExceptionEngine为处理异常的驱动器
                    return Observable.error(ExceptionEngine.handleException(throwable));
                }
            }).observeOn(AndroidSchedulers.mainThread());
    }
}

